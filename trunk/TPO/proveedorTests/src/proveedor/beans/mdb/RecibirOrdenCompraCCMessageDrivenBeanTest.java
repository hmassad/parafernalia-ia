package proveedor.beans.mdb;

import java.util.Date;
import java.util.Hashtable;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import proveedor.documentos.OrCompCC;
import proveedor.documentos.OrCompCC.Cliente;
import proveedor.documentos.OrCompCC.Item;
import proveedor.documentos.OrCompCC.Item.Rodamiento;

public class RecibirOrdenCompraCCMessageDrivenBeanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		OrCompCC orCompCC = new OrCompCC();

		orCompCC.setNroOrdenCompra("nroOrdenCompra1");
		orCompCC.setFecha(new Date());
		orCompCC.setCliente(new Cliente("6"));

		orCompCC.getItemsOCCC().add(
				new Item("nroItem1", new Rodamiento("1"), 10));
//		orCompCC.getItemsOCCC().add(
//				new Item("nroItem2", new Rodamiento("codigoRodamiento2"), 2));
//		orCompCC.getItemsOCCC().add(
//				new Item("nroItem3", new Rodamiento("codigoRodamiento3"), 3));

		// enviar mensaje a cola
		String contenido = orCompCC.serialize();

		try {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
			InitialContext ctx = new InitialContext(props);

			Queue queue = (Queue) ctx.lookup("queue/ordenCompraQueue");

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

	}

}
