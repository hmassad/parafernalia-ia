package casaCentral.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import casaCentral.documentos.NvoProd;

/**
 * Message-Driven Bean implementation class for:
 * RecibirPedidoCasaCentralMessageDrivenBean
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/nvoProdQueue") })
public class RecibirNvoProdMessageDrivenBean implements MessageListener {

	public RecibirNvoProdMessageDrivenBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println();
		System.out.println();
		System.out.println("RECIBIDO NUEVO PRODUCTO");
		System.out.println();
		TextMessage textMessage = (TextMessage) message;
		try {
			NvoProd nvoProd = NvoProd.deserialize(textMessage.getText());
			System.out.println(nvoProd);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
