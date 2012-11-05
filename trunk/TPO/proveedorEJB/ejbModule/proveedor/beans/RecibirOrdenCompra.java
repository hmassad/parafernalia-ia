package proveedor.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: RecibirOrdenCompra
 * 
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "recibirOrdenCompra")
public class RecibirOrdenCompra implements MessageListener {

	/**
	 * Default constructor.
	 */
	public RecibirOrdenCompra() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		// TODO registrar orden de compra
		// TODO si hay stock disponible, enviar el pedido y salir
		// TODO pedir a proveedor de materia prima el doble de lo que falte para completar la orden
	}

}
