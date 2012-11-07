package proveedor.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.PedidoCasaCentralVO;

@Local
public interface PedidoCasaCentralSessionBeanLocal {

	void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void deletePedidoCasaCentral(int id);

	Collection<PedidoCasaCentralVO> getPedidosCasaCentral();

	Collection<PedidoCasaCentralVO> getPedidosCasaCentralByEntregado(
			boolean entregado);

	PedidoCasaCentralVO getPedidoCasaCentral(int id);

	void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);
}
