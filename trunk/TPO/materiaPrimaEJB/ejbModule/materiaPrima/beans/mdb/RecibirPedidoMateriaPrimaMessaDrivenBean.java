package materiaPrima.beans.mdb;

import java.util.Hashtable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import materiaPrima.configuration.Configuration;
import materiaPrima.documentos.MatPri;
import materiaPrima.documentos.SolMatPri;
import materiaPrima.documentos.SolMatPri.Item;
import materiaPrima.vo.PedidoMateriaPrimaItemVO;
import materiaPrima.vo.PedidoMateriaPrimaVO;

/**
 * Message-Driven Bean implementation class for:
 * RecibirPedidoMateriaPrimaMessaDrivenBean
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/pedidoMateriaPrima") })
public class RecibirPedidoMateriaPrimaMessaDrivenBean implements
		MessageListener {

	public RecibirPedidoMateriaPrimaMessaDrivenBean() {
	}

	public void onMessage(Message message) {
		try {
			TextMessage receivedTextMessage = (TextMessage) message;

			SolMatPri solMatPri = SolMatPri.deserialize(receivedTextMessage
					.getText());

			PedidoMateriaPrimaVO pedidoMateriaPrimaVO = new PedidoMateriaPrimaVO();
			pedidoMateriaPrimaVO.setId(solMatPri.getId());
			pedidoMateriaPrimaVO.setFecha(solMatPri.getFecha());
			pedidoMateriaPrimaVO.setEntregado(false);
			for (Item item : solMatPri.getItems()) {
				pedidoMateriaPrimaVO.getItems().add(
						new PedidoMateriaPrimaItemVO(item.getId(), item
								.getCodigo(), item.getCantidad()));
			}

			MatPri matPri = new MatPri(pedidoMateriaPrimaVO.getId());

			String contenido = matPri.serialize();

			// mandar mensaje a cola
			try {
				Hashtable<String, String> props = new Hashtable<String, String>();
				props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
						"org.jnp.interfaces.NamingContextFactory");
				props.put(InitialContext.PROVIDER_URL, "jnp://"
						+ Configuration.ProveedorHost + ":1099");
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

							TextMessage textMessage = qSession
									.createTextMessage();
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

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
