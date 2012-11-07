package proveedor.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.PedidoCasaCentralVO;

@Local
public interface PedidoCasaCentralSessionBeanLocal {

	void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void deletePedidoCasaCentral(int id);

	Collection<PedidoCasaCentralVO> getPedidosCasaCentral();

	PedidoCasaCentralVO getPedidoCasaCentral(int id);
	
	void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);
}
