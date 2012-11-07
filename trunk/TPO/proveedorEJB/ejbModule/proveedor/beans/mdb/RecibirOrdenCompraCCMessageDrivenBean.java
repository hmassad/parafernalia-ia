package proveedor.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import proveedor.beans.local.PedidoCasaCentralSessionBeanLocal;
import proveedor.documentos.OrCompCC;
import proveedor.documentos.OrCompCC.Item;
import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;

/**
 * Message-Driven Bean implementation class for:
 * RecibirOrdenCompraCCMessageDrivenBean
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ordenCompraQueue") })
public class RecibirOrdenCompraCCMessageDrivenBean implements MessageListener {

	@EJB
	PedidoCasaCentralSessionBeanLocal pedidoCasaCentralSessionBeanLocal;

	public RecibirOrdenCompraCCMessageDrivenBean() {
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;

			// transformar texto en OrCompCC
			OrCompCC orCompCC = OrCompCC.deserialize(textMessage.getText());

			// transformar OrCompCC en PedidoCasaCentralVO
			PedidoCasaCentralVO pedidoCasaCentralVO = new PedidoCasaCentralVO();
			pedidoCasaCentralVO.setNroOrdenCompra(orCompCC.getNroOrdenCompra());
			pedidoCasaCentralVO.setFecha(orCompCC.getFecha());
			for (Item item : orCompCC.getItemsOCCC()) {
				PedidoCasaCentralItemVO pedidoCasaCentralItemVO = new PedidoCasaCentralItemVO();
				pedidoCasaCentralItemVO.setCantidad(item.getCantidad());
				pedidoCasaCentralItemVO.setCodigo(item.getRodamiento()
						.getCodigoRodamiento());
				pedidoCasaCentralVO.getItems().add(pedidoCasaCentralItemVO);
			}

			pedidoCasaCentralSessionBeanLocal
					.recibirPedidoCasaCentral(pedidoCasaCentralVO);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
