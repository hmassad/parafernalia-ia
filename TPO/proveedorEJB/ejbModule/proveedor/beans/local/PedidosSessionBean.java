package proveedor.beans.local;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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

import proveedor.configuration.Configuration;
import proveedor.documentos.OrCompCCAceptada;
import proveedor.documentos.SolMatPri;
import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaItemVO;
import proveedor.vo.PedidoMateriaPrimaVO;

@Stateless
public class PedidosSessionBean implements PedidosSessionBeanLocal {

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
		pedidoMateriaPrimaSessionBeanLocal
				.createPedidoMateriaPrima(pedidoMateriaPrimaVO);

		// transformar PedidoMateriaPrimaVO en SolMatPri
		SolMatPri solMatPri = new SolMatPri();
		solMatPri.setId(pedidoMateriaPrimaVO.getId());
		solMatPri.setFecha(pedidoMateriaPrimaVO.getFecha());
		int i = 0;
		for (PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO : pedidoMateriaPrimaVO
				.getItems()) {
			solMatPri.getItems().add(
					new SolMatPri.Item(i, pedidoMateriaPrimaItemVO
							.getMateriaPrima().getCodigo(),
							pedidoMateriaPrimaItemVO.getCantidad()));
			i++;
		}
		String contenido = solMatPri.serialize();

		// mandar mensaje a cola
		try {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://"
					+ Configuration.MateriaPrimaHost + ":1099");
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
		PedidoMateriaPrimaVO pedidoMateriaPrimaVO = pedidoMateriaPrimaSessionBeanLocal
				.getPedidoMateriaPrima(id);
		pedidoMateriaPrimaVO.setEntregado(true);
		pedidoMateriaPrimaSessionBeanLocal
				.updatePedidoMateriaPrima(pedidoMateriaPrimaVO);

		// actualizar stock de MateriaPrima
		for (PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO : pedidoMateriaPrimaVO
				.getItems()) {
			materiaPrimaSessionBeanLocal.ingresarStock(pedidoMateriaPrimaItemVO
					.getMateriaPrima().getCodigo(), pedidoMateriaPrimaItemVO
					.getCantidad());
		}

		// buscar los PedidoCasaCentral que se puedan completar y enviar
		for (PedidoCasaCentralVO pedidoCasaCentralVO : pedidoCasaCentralSessionBeanLocal
				.getPedidosCasaCentralByEntregado(false)) {
			boolean entregable = true;

			// obtener las cantidades en total de materia prima del pedido de
			// casa central
			Map<String, Integer> materiasPrimasPedido = new HashMap<String, Integer>();
			for (PedidoCasaCentralItemVO pcciVO : pedidoCasaCentralVO
					.getItems()) {
				for (MateriaPrimaProductoVO mppVO : pcciVO.getProducto()
						.getMateriasPrimasProducto()) {
					String codigo = mppVO.getMateriaPrima().getCodigo();
					int cantidad = mppVO.getCantidad() * pcciVO.getCantidad();
					if (materiasPrimasPedido.containsKey(mppVO
							.getMateriaPrima().getCodigo())) {
						cantidad += materiasPrimasPedido.get(mppVO
								.getMateriaPrima().getCodigo());
					}
					materiasPrimasPedido.put(codigo, cantidad);
				}
			}

			for (String codigoMP : materiasPrimasPedido.keySet()) {
				int cantidadPedido = materiasPrimasPedido.get(codigoMP);
				MateriaPrimaVO materiaPrimaVO = materiaPrimaSessionBeanLocal
						.getMateriaPrima(codigoMP);
				int cantidadStock = materiaPrimaVO.getStock();
				if (cantidadStock < cantidadPedido) {
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

		// guardar PedidoCasaCentral
		pedidoCasaCentralVO.setEntregado(false);
		pedidoCasaCentralSessionBeanLocal
				.createPedidoCasaCentral(pedidoCasaCentralVO);

		// obtener las cantidades en total de materia prima que se necesitan
		// para cumplir el pedido de casa central
		Map<String, Integer> materiasPrimasPedido = new HashMap<String, Integer>();
		for (PedidoCasaCentralItemVO pcciVO : pedidoCasaCentralVO.getItems()) {
			for (MateriaPrimaProductoVO mppVO : pcciVO.getProducto()
					.getMateriasPrimasProducto()) {
				String codigoMP = mppVO.getMateriaPrima().getCodigo();
				int cantidad = mppVO.getCantidad() * pcciVO.getCantidad();
				if (materiasPrimasPedido.containsKey(codigoMP)) {
					cantidad += materiasPrimasPedido.get(codigoMP);
				}
				materiasPrimasPedido.put(codigoMP, cantidad);
			}
		}

		// verificar si con el stock actual se completa el pedido
		PedidoMateriaPrimaVO pedidoMateriaPrimaVO = new PedidoMateriaPrimaVO();
		pedidoMateriaPrimaVO.setFecha(new Date());
		pedidoMateriaPrimaVO.setEntregado(false);

		for (String codigoMP : materiasPrimasPedido.keySet()) {
			MateriaPrimaVO materiaPrimaVO = materiaPrimaSessionBeanLocal
					.getMateriaPrima(codigoMP);
			int cantidadStock = materiaPrimaVO.getStock();
			int cantidadPedido = materiasPrimasPedido.get(codigoMP);
			if (cantidadStock < cantidadPedido) {
				pedidoMateriaPrimaVO.getItems().add(
						new PedidoMateriaPrimaItemVO(new MateriaPrimaVO(
								codigoMP, null, 0),
								(cantidadPedido - cantidadStock) * 2));
			}
		}

		if (pedidoMateriaPrimaVO.getItems().size() > 0) {
			// si no se puede cumplir el pedido, pedir el doble de
			// MateriaPrima que haga falta
			enviarPedidoMateriaPrima(pedidoMateriaPrimaVO);
		} else {
			// si se puede cumplir el pedido, enviar pedido
			enviarPedidoCasaCentral(pedidoCasaCentralVO);
		}
	}

	public void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {

		// enviar mensaje a cola
		OrCompCCAceptada orCompCCAceptada = new OrCompCCAceptada(
				pedidoCasaCentralVO.getNroOrdenCompra());
		String contenido = orCompCCAceptada.serialize();

		try {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://"
					+ Configuration.CasaCentralHost + ":1099");
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
			return;
		}

		// descontar stock
		for (PedidoCasaCentralItemVO pedidoCasaCentraItemlVO : pedidoCasaCentralVO
				.getItems()) {
			for (MateriaPrimaProductoVO materiaPrimaProducto : pedidoCasaCentraItemlVO
					.getProducto().getMateriasPrimasProducto()) {
				int cantidad = materiaPrimaProducto.getCantidad()
						* pedidoCasaCentraItemlVO.getCantidad();
				materiaPrimaSessionBeanLocal.descontarStock(
						materiaPrimaProducto.getMateriaPrima().getCodigo(),
						cantidad);
			}
		}

		// marcar pedido como entregado
		pedidoCasaCentralVO.setEntregado(true);
		pedidoCasaCentralSessionBeanLocal
				.updatePedidoCasaCentral(pedidoCasaCentralVO);
	}

}
