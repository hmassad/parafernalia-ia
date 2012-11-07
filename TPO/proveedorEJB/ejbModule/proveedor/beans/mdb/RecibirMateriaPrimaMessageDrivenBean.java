package proveedor.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import proveedor.beans.remote.FachadaSessionBeanRemote;
import proveedor.documentos.MatPri;
import proveedor.vo.PedidoMateriaPrimaVO;

/**
 * Message-Driven Bean implementation class for:
 * RecibirMateriaPrimaMessageDrivenBean
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/entregaMateriaPrimaQueue") })
public class RecibirMateriaPrimaMessageDrivenBean implements MessageListener {

	@EJB
	FachadaSessionBeanRemote fachadaSessionBeanRemote;

	public RecibirMateriaPrimaMessageDrivenBean() {
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;

			// transformar texto en MatPri
			MatPri matPri = MatPri.deserialize(textMessage.getText());

			// TODO transformar MatPri en PedidoMateriaPrimaVO
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO = null;

			fachadaSessionBeanRemote.recibirPedidoMateriaPrima(pedidoMateriaPrimaVO);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
