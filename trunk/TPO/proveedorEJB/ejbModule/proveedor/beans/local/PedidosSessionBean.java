package proveedor.beans.local;

import java.util.Date;
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

import proveedor.documentos.OrCompCCAceptada;
import proveedor.documentos.SolMatPri;
import proveedor.model.MateriaPrima;
import proveedor.model.PedidoMateriaPrima;
import proveedor.model.PedidoMateriaPrimaItem;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaItemVO;
import proveedor.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class PedidosSessionBean
 */
@Stateless
public class PedidosSessionBean implements PedidosSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	@EJB
	private MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal;

	@EJB
	private PedidoMateriaPrimaSessionBeanLocal pedidoMateriaPrimaSessionBeanLocal;

	@EJB
	private PedidoCasaCentralSessionBeanLocal pedidoCasaCentralSessionBeanLocal;

	public PedidosSessionBean() {
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
		int i = 0;
		for (PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO : pedidoMateriaPrimaVO
				.getItems()) {
			solMatPri.getItems().add(
					new SolMatPri.Item(i, pedidoMateriaPrimaItemVO.getCodigo(),
							pedidoMateriaPrimaItemVO.getCantidad()));
			i++;
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
			materiaPrimaSessionBeanLocal.ingresarStock(
					pedidoMateriaPrimaItem.getCodigo(),
					pedidoMateriaPrimaItem.getCantidad());
		}

		// buscar PedidoCasaCentral que se puedan completar con lo pedido y
		// enviar a Casa Central
		for (PedidoCasaCentralVO pedidoCasaCentralVO : pedidoCasaCentralSessionBeanLocal
				.getPedidosCasaCentralByEntregado(false)) {
			boolean entregable = true;
			for (PedidoCasaCentralItemVO pedidoCasaCentralItemVO : pedidoCasaCentralVO
					.getItems()) {
				MateriaPrimaVO materiaPrimaVO = materiaPrimaSessionBeanLocal
						.getMateriaPrima(pedidoCasaCentralItemVO.getCodigo());
				if (materiaPrimaVO.getStock() < pedidoCasaCentralItemVO
						.getCantidad()) {
					entregable = false;
					break;
				}
			}
			if (entregable) {
				enviarPedidoCasaCentral(pedidoCasaCentralVO);
			}
		}
	}

	public void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		// verificar si con el stock actual se completa el pedido
		PedidoMateriaPrimaVO pedidoMateriaPrimaVO = new PedidoMateriaPrimaVO();
		pedidoMateriaPrimaVO.setFecha(new Date());
		pedidoMateriaPrimaVO.setEntregado(false);

		for (PedidoCasaCentralItemVO item : pedidoCasaCentralVO.getItems()) {
			MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class,
					item.getCodigo());
			if (materiaPrima.getStock() < item.getCantidad()) {
				pedidoMateriaPrimaVO.getItems().add(
						new PedidoMateriaPrimaItemVO(item.getCodigo(), item
								.getCantidad() - materiaPrima.getStock()));
			}
		}
		if (pedidoMateriaPrimaVO.getItems().size() > 0) {
			// si no se puede cumplir el pedido, pedir el doble de
			// MateriaPrima que haga falta
			for (PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO : pedidoMateriaPrimaVO
					.getItems())
				pedidoMateriaPrimaItemVO.setCantidad(pedidoMateriaPrimaItemVO
						.getCantidad() * 2);
			enviarPedidoMateriaPrima(pedidoMateriaPrimaVO);
		} else {
			// si se puede cumplir el pedido, enviar pedido
			enviarPedidoCasaCentral(pedidoCasaCentralVO);
		}
	}

	public void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {

		// descontar stock
		for (PedidoCasaCentralItemVO pedidoCasaCentraItemlVO : pedidoCasaCentralVO
				.getItems()) {
			materiaPrimaSessionBeanLocal.descontarStock(
					pedidoCasaCentraItemlVO.getCodigo(),
					pedidoCasaCentraItemlVO.getCantidad());
		}

		// enviar mensaje a cola
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

}
