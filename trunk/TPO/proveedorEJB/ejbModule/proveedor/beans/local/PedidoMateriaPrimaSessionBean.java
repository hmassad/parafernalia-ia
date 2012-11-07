package proveedor.beans.local;

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

import proveedor.beans.remote.FachadaSessionBeanRemote;
import proveedor.documentos.SolMatPri;
import proveedor.model.MateriaPrima;
import proveedor.model.PedidoMateriaPrima;
import proveedor.model.PedidoMateriaPrimaItem;
import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaItemVO;
import proveedor.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class PedidoPendienteSessionBean
 */
@Stateless
public class PedidoMateriaPrimaSessionBean implements
		PedidoMateriaPrimaSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
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

	public void enviarPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {

		// insertar pedidoMateriaPrima
		entityManager.persist(PedidoMateriaPrima
				.toPedidoMateriaPrima(pedidoMateriaPrimaVO));

		// transformar PedidoMateriaPrimaVO en SolMatPri
		SolMatPri solMatPri = new SolMatPri();
		solMatPri.setId(pedidoMateriaPrimaVO.getId());
		solMatPri.setFecha(pedidoMateriaPrimaVO.getFecha());
		for (PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO : pedidoMateriaPrimaVO
				.getItems()) {
			solMatPri.getItems().add(
					new SolMatPri.Item(pedidoMateriaPrimaItemVO.getId(),
							pedidoMateriaPrimaItemVO.getCodigo(),
							pedidoMateriaPrimaItemVO.getCantidad(),
							pedidoMateriaPrimaItemVO.getUnidad().getCodigo()));
		}
		String contenido = solMatPri.serialize();

		// mandar mensaje a cola
		try {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
			InitialContext ctx = new InitialContext(props);

			Queue queue = (Queue) ctx.lookup("queue/pedidoMateriaPrima");

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

	public void recibirPedidoMateriaPrima(int id) {
		// buscar PedidoMateriaPrima y marcar como entregado
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(
				PedidoMateriaPrima.class, id);
		pedidoMateriaPrima.setEntregado(true);
		entityManager.persist(pedidoMateriaPrima);

		// actualizar stock de MateriaPrima
		for (PedidoMateriaPrimaItem pedidoMateriaPrimaItem : pedidoMateriaPrima
				.getItems()) {
			MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class,
					pedidoMateriaPrimaItem.getCodigo());
			materiaPrima.setStock(materiaPrima.getStock()
					+ pedidoMateriaPrimaItem.getCantidad());
			entityManager.persist(materiaPrima);
		}

		// TODO buscar PedidoCasaCentral que se puedan completar con lo pedido y
		// enviar a Casa Central

		Collection<PedidoCasaCentralVO> pedidoCasaCentralVOs = new ArrayList<PedidoCasaCentralVO>();

		for (PedidoCasaCentralVO pedidoCasaCentralVO : pedidoCasaCentralVOs) {
			fachadaSessionBeanRemote
					.enviarPedidoCasaCentral(pedidoCasaCentralVO);
		}
	}

}