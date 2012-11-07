package proveedor.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import proveedor.beans.local.PedidoMateriaPrimaSessionBeanLocal;
import proveedor.documentos.MatPri;

/**
 * Message-Driven Bean implementation class for:
 * RecibirMateriaPrimaMessageDrivenBean
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/entregaMateriaPrima") })
public class RecibirMateriaPrimaMessageDrivenBean implements MessageListener {

	@EJB
	PedidoMateriaPrimaSessionBeanLocal pedidoMateriaPrimaSessionBeanLocal;

	public RecibirMateriaPrimaMessageDrivenBean() {
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;

			MatPri matPri = MatPri.deserialize(textMessage.getText());

			pedidoMateriaPrimaSessionBeanLocal.recibirPedidoMateriaPrima(matPri
					.getId());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
