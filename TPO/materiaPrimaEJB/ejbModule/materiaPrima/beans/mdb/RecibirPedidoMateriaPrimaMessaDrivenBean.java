package materiaPrima.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import materiaPrima.beans.local.PedidoMateriaPrimaSessionBeanLocal;
import materiaPrima.documentos.SolMatPri;
import materiaPrima.documentos.SolMatPri.Item;
import materiaPrima.vo.PedidoMateriaPrimaItemVO;
import materiaPrima.vo.PedidoMateriaPrimaVO;

/**
 * Message-Driven Bean implementation class for:
 * RecibirPedidoMateriaPrimaMessaDrivenBean
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/pedidoMateriaPrima") })
public class RecibirPedidoMateriaPrimaMessaDrivenBean implements
		MessageListener {

	@EJB
	PedidoMateriaPrimaSessionBeanLocal pedidoMateriaPrimaSessionBeanLocal;

	public RecibirPedidoMateriaPrimaMessaDrivenBean() {
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;

			SolMatPri solMatPri = SolMatPri.deserialize(textMessage.getText());

			PedidoMateriaPrimaVO pedidoMateriaPrimaVO = new PedidoMateriaPrimaVO();
			pedidoMateriaPrimaVO.setId(solMatPri.getId());
			pedidoMateriaPrimaVO.setFecha(solMatPri.getFecha());
			pedidoMateriaPrimaVO.setEntregado(false);
			for (Item item : solMatPri.getItems()) {
				pedidoMateriaPrimaVO.getItems().add(
						new PedidoMateriaPrimaItemVO(item.getId(), item
								.getCodigo(), item.getCantidad()));
			}

			pedidoMateriaPrimaSessionBeanLocal
					.recibirPedidoMateriaPrima(pedidoMateriaPrimaVO);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
