package materiaPrima.beans;

import javax.ejb.MessageDriven;
import javax.jms.MessageProducer;

/**
 * Message-Driven Bean implementation class for:
 * EntregarPedidoMateriaPrimaMessageDrivenBean
 * 
 */
@MessageDriven(mappedName = "recibirMateriaPrima", messageListenerInterface = MessageProducer.class)
public class EntregarPedidoMateriaPrimaMessageDrivenBean {

	/**
	 * Default constructor.
	 */
	public EntregarPedidoMateriaPrimaMessageDrivenBean() {
		// TODO Auto-generated constructor stub
	}

}
