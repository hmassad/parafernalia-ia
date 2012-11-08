package proveedor.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.PedidoMateriaPrimaVO;

@Local
public interface PedidoMateriaPrimaSessionBeanLocal {

	void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoVO);

	void deletePedidoMateriaPrima(int id);

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima();

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(boolean entregado);

	PedidoMateriaPrimaVO getPedidoMateriaPrima(int id);

}
