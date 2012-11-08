package materiaPrima.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import materiaPrima.vo.PedidoMateriaPrimaVO;

@Local
public interface PedidoMateriaPrimaSessionBeanLocal {

	void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void deletePedidoMateriaPrima(int id);

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima();

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(boolean entregado);

	PedidoMateriaPrimaVO getPedidoMateriaPrima(int id);

	void recibirPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void enviarPedidoMateriaPrima(int id);
}
