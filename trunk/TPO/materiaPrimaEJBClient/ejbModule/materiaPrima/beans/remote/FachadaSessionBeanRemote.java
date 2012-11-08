package materiaPrima.beans.remote;

import java.util.Collection;

import javax.ejb.Remote;

import materiaPrima.vo.PedidoMateriaPrimaVO;

@Remote
public interface FachadaSessionBeanRemote {

	void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void deletePedidoMateriaPrima(int id);

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima();

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(
			boolean entregado);

	PedidoMateriaPrimaVO getPedidoMateriaPrima(int id);

	void recibirPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void enviarPedidoMateriaPrima(int id);

}
