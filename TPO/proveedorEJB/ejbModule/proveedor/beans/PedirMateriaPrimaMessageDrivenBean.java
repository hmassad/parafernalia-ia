package proveedor.beans;

import javax.ejb.MessageDriven;
import javax.jms.MessageProducer;

/**
 * Message-Driven Bean implementation class for:
 * PedirMateriaPrimaMessageDrivenBean
 * 
 */
@MessageDriven(mappedName = "pedirMateriaPrima", messageListenerInterface = MessageProducer.class)
public class PedirMateriaPrimaMessageDrivenBean {

	/**
	 * Default constructor.
	 */
	public PedirMateriaPrimaMessageDrivenBean() {
		// TODO Auto-generated constructor stub
	}

}
