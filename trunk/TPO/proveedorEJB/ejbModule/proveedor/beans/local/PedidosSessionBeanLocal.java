package proveedor.beans.local;

import javax.ejb.Local;

import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaVO;

@Local
public interface PedidosSessionBeanLocal {

	void enviarPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void recibirPedidoMateriaPrima(int id);

	void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

}
