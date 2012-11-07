package materiaPrima.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import materiaPrima.documentos.MatPri;
import materiaPrima.model.PedidoMateriaPrima;
import materiaPrima.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class PedidoPendienteSessionBean
 */
@Stateless
public class PedidoMateriaPrimaSessionBean implements
		PedidoMateriaPrimaSessionBeanLocal {

	@PersistenceContext(unitName = "materiaPrima")
	private EntityManager entityManager;

	@EJB
	FachadaSessionBeanRemote fachadaSessionBeanRemote;

	public PedidoMateriaPrimaSessionBean() {
	}

	public void createPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		entityManager.persist(PedidoMateriaPrima
				.toPedidoMateriaPrima(pedidoMateriaPrimaVO));
	}

	public void deletePedidoMateriaPrima(int id) {
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(
				PedidoMateriaPrima.class, id);
		entityManager.remove(pedidoMateriaPrima);
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(
				PedidoMateriaPrima.class, id);
		return PedidoMateriaPrima.toPedidoMateriaPrimaVO(pedidoMateriaPrima);
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		Query query = entityManager
				.createQuery("SELECT PMP FROM PedidoMateriaPrima PMP");
		Collection<PedidoMateriaPrimaVO> pedidosMateriaPrimaVO = new ArrayList<PedidoMateriaPrimaVO>();
		for (PedidoMateriaPrima pedidoMateriaPrima : (Collection<PedidoMateriaPrima>) query
				.getResultList()) {
			pedidosMateriaPrimaVO.add(PedidoMateriaPrima
					.toPedidoMateriaPrimaVO(pedidoMateriaPrima));
		}
		return pedidosMateriaPrimaVO;
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(
			boolean entregado) {
		Query query = entityManager.createQuery(
				"SELECT PMP FROM PedidoMateriaPrima PMP WHERE entregado = 1?")
				.setParameter(1, entregado);
		Collection<PedidoMateriaPrimaVO> pedidosMateriaPrimaVO = new ArrayList<PedidoMateriaPrimaVO>();
		for (PedidoMateriaPrima pedidoMateriaPrima : (Collection<PedidoMateriaPrima>) query
				.getResultList()) {
			pedidosMateriaPrimaVO.add(PedidoMateriaPrima
					.toPedidoMateriaPrimaVO(pedidoMateriaPrima));
		}
		return pedidosMateriaPrimaVO;
	}

	public void recibirPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		// insertar pedidoMateriaPrima
		entityManager.persist(PedidoMateriaPrima
				.toPedidoMateriaPrima(pedidoMateriaPrimaVO));
	}

	public void enviarPedidoMateriaPrima(int id) {
		MatPri matPri = new MatPri(id);

		String contenido = matPri.serialize();

		// mandar mensaje a cola
		try {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
			InitialContext ctx = new InitialContext(props);

			Queue queue = (Queue) ctx.lookup("queue/entregaMateriaPrima");

			// buscar la Connection Factory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");

			QueueConnection queueConnection = null;
			try {
				queueConnection = qfactory.createQueueConnection();

				QueueSession qSession = null;
				try {
					qSession = queueConnection.createQueueSession(false,
							Session.AUTO_ACKNOWLEDGE);

					QueueSender queueSender = null;
					try {
						queueSender = qSession.createSender(queue);

						TextMessage textMessage = qSession.createTextMessage();
						textMessage.setText(contenido);

						queueSender.send(textMessage);
					} finally {
						queueSender.close();
					}
				} finally {
					qSession.close();
				}
			} finally {
				queueConnection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
