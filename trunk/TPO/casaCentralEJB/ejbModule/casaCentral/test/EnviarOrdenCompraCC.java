package casaCentral.test;

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

import casaCentral.documentos.OrCompCC;
import casaCentral.documentos.OrCompCC.Cliente;
import casaCentral.documentos.OrCompCC.Item;
import casaCentral.documentos.OrCompCC.Item.Rodamiento;

public class EnviarOrdenCompraCC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OrCompCC orCompCC = new OrCompCC();

			orCompCC.setNroOrdenCompra("nroOrdenCompra1");
			orCompCC.setFecha(new Date());
			orCompCC.setCliente(new Cliente("cuit"));

			orCompCC.getItemsOCCC()
					.add(new Item("nroItem1", new Rodamiento(
							"codigoRodamiento1"), 1));
			orCompCC.getItemsOCCC()
					.add(new Item("nroItem2", new Rodamiento(
							"codigoRodamiento2"), 2));
			orCompCC.getItemsOCCC()
					.add(new Item("nroItem3", new Rodamiento(
							"codigoRodamiento3"), 3));

			String contenido = orCompCC.serialize();

			Hashtable<String, String> props = new Hashtable<String, String>();

			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");

			InitialContext ctx = new InitialContext(props);

			// buscar la Connection Factory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");

			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/ordenCompraQueue");

			// crear la connection y la session a partir de la connection
			QueueConnection qCon = qfactory.createQueueConnection();
			QueueSession qSession = qCon.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// crear un producer para enviar mensajes usando la session
			QueueSender qSender = qSession.createSender(queue);

			TextMessage message = qSession.createTextMessage();
			message.setText(contenido);

			// enviar el mensaje
			qSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
