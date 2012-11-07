package proveedor.beans.local;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

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

import proveedor.documentos.OrCompCCAceptada;
import proveedor.model.PedidoCasaCentral;
import proveedor.model.PedidoCasaCentralItem;
import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;

/**
 * Session Bean implementation class PedidoPendienteSessionBean
 */
@Stateless
public class PedidoCasaCentralSessionBean implements
		PedidoCasaCentralSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public PedidoCasaCentralSessionBean() {
	}

	public void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		entityManager.persist(toPedidoCasaCentral(pedidoCasaCentralVO));
	}

	public void deletePedidoCasaCentral(int id) {
		PedidoCasaCentral pedidoCasaCentral = entityManager.find(
				PedidoCasaCentral.class, id);
		entityManager.remove(pedidoCasaCentral);
	}

	public PedidoCasaCentralVO getPedidoCasaCentral(int id) {
		PedidoCasaCentral pedidoCasaCentral = entityManager.find(
				PedidoCasaCentral.class, id);
		return toPedidoCasaCentralVO(pedidoCasaCentral);
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoCasaCentralVO> getPedidosCasaCentral() {
		Query query = entityManager
				.createQuery("SELECT P FROM PedidoCasaCentral P");
		Collection<PedidoCasaCentralVO> pedidosCasaCentralVO = new ArrayList<PedidoCasaCentralVO>();
		for (PedidoCasaCentral pedidoCasaCentral : (Collection<PedidoCasaCentral>) query
				.getResultList()) {
			pedidosCasaCentralVO.add(toPedidoCasaCentralVO(pedidoCasaCentral));
		}
		return pedidosCasaCentralVO;
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoCasaCentralVO> getPedidosCasaCentralByEntregado(
			boolean entregado) {
		Query query = entityManager.createQuery(
				"SELECT PCC FROM PedidoCasaCentral PCC WHERE entregado = 1?")
				.setParameter(1, entregado);
		Collection<PedidoCasaCentralVO> pedidosCasaCentralVO = new ArrayList<PedidoCasaCentralVO>();
		for (PedidoCasaCentral pedidoCasaCentral : (Collection<PedidoCasaCentral>) query
				.getResultList()) {
			pedidosCasaCentralVO.add(PedidoCasaCentral
					.toPedidoCasaCentralVO(pedidoCasaCentral));
		}
		return pedidosCasaCentralVO;
	}

	public void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		// TODO verificar si con el stock actual se completa el pedido
		// TODO pedir el doble de MateriaPrima que haga falta
		// TODO si se puede cumplir el pedido, enviar pedido
	}

	public void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {

		OrCompCCAceptada orCompCCAceptada = new OrCompCCAceptada(
				pedidoCasaCentralVO.getNroOrdenCompra());
		String contenido = orCompCCAceptada.serialize();

		try {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
			InitialContext ctx = new InitialContext(props);

			Queue queue = (Queue) ctx.lookup("queue/ordenCompraAcepQueue");

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

	public static PedidoCasaCentralVO toPedidoCasaCentralVO(
			PedidoCasaCentral pedidoCasaCentral) {
		Collection<PedidoCasaCentralItemVO> items = new ArrayList<PedidoCasaCentralItemVO>();
		for (PedidoCasaCentralItem pedidoCasaCentralItem : pedidoCasaCentral
				.getItems())
			items.add(PedidoCasaCentralItem
					.toPedidoCasaCentralItemVO(pedidoCasaCentralItem));
		return new PedidoCasaCentralVO(pedidoCasaCentral.getId(),
				pedidoCasaCentral.getEntregado(), pedidoCasaCentral.getFecha(),
				pedidoCasaCentral.getNroOrdenCompra(), items);
	}

	public static PedidoCasaCentral toPedidoCasaCentral(
			PedidoCasaCentralVO pedidoCasaCentralVO) {
		PedidoCasaCentral pedidoCasaCentral = new PedidoCasaCentral();
		pedidoCasaCentral.setId(pedidoCasaCentralVO.getId());
		pedidoCasaCentral.setEntregado(pedidoCasaCentralVO.getEntregado());
		pedidoCasaCentral.setFecha(pedidoCasaCentralVO.getFecha());
		pedidoCasaCentral.setNroOrdenCompra(pedidoCasaCentralVO
				.getNroOrdenCompra());
		for (PedidoCasaCentralItemVO pedidoCasaCentralItem : pedidoCasaCentralVO
				.getItems())
			pedidoCasaCentral.getItems().add(
					PedidoCasaCentralItem.toPedidoCasaCentralItem(
							pedidoCasaCentral, pedidoCasaCentralItem));
		return pedidoCasaCentral;
	}

}
