package materiaPrima.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import materiaPrima.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class FachadaSessionBean
 */
@Stateless
public class FachadaSessionBean implements FachadaSessionBeanRemote {

	@EJB
	PedidoMateriaPrimaSessionBeanLocal pedidoMateriaPrimaSessionBeanLocal;

	public FachadaSessionBean() {
	}

	public void createPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		pedidoMateriaPrimaSessionBeanLocal
				.createPedidoMateriaPrima(pedidoMateriaPrimaVO);
	}

	public void deletePedidoMateriaPrima(int id) {
		pedidoMateriaPrimaSessionBeanLocal.deletePedidoMateriaPrima(id);
	}

	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		return pedidoMateriaPrimaSessionBeanLocal.getPedidosMateriaPrima();
	}

	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(
			boolean entregado) {
		return pedidoMateriaPrimaSessionBeanLocal
				.getPedidosMateriaPrimaByEntregado(entregado);
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		return pedidoMateriaPrimaSessionBeanLocal.getPedidoMateriaPrima(id);
	}

	public void recibirPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		pedidoMateriaPrimaSessionBeanLocal
				.recibirPedidoMateriaPrima(pedidoMateriaPrimaVO);
	}

	public void enviarPedidoMateriaPrima(int id) {
		pedidoMateriaPrimaSessionBeanLocal.enviarPedidoMateriaPrima(id);
	}

}
