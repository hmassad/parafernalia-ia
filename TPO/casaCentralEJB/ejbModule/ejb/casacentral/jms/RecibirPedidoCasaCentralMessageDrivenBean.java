package ejb.casacentral.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import ejb.casacentral.documentos.OrCompCCAceptada;

/**
 * Message-Driven Bean implementation class for:
 * RecibirPedidoCasaCentralMessageDrivenBean
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ordenCompraAcepQueue") })
public class RecibirPedidoCasaCentralMessageDrivenBean implements
		MessageListener {

	public RecibirPedidoCasaCentralMessageDrivenBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println();
		System.out.println();
		System.out.println("RECIBIDO PEDIDO CASA CENTRAL");
		System.out.println();
		TextMessage textMessage = (TextMessage) message;
		try {
			OrCompCCAceptada orCompCCAceptada = OrCompCCAceptada
					.deserialize(textMessage.getText());
			System.out.println(orCompCCAceptada);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
