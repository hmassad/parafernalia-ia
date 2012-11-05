package proveedor.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for:
 * RecibirMateriaPrimaMessageDrivenBean
 * 
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "recibirMateriaPrima")
public class RecibirMateriaPrimaMessageDrivenBean implements MessageListener {

	/**
	 * Default constructor.
	 */
	public RecibirMateriaPrimaMessageDrivenBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		// TODO borrar el pedido o marcarlo como entregado
		// TODO actualizar stock de materiaPrima
		// TODO avisar a casa central del pedido
	}

}
