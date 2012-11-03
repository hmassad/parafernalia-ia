package materiaPrima.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for:
 * SolicitadorMateriaPrimaMessageDrivenBean
 * 
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "solicitudMateriaPrima")
public class SolicitadorMateriaPrimaMessageDrivenBean implements MessageListener {

	/**
	 * Default constructor.
	 */
	public SolicitadorMateriaPrimaMessageDrivenBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		// TODO Auto-generated method stub

	}

}
