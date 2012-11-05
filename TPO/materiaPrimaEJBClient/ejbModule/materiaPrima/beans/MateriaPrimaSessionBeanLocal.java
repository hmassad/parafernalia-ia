package materiaPrima.beans;

import java.util.Collection;

import javax.ejb.Local;

import materiaPrima.vo.PedidoMateriaPrimaVO;

@Local
public interface MateriaPrimaSessionBeanLocal {

	Collection<PedidoMateriaPrimaVO> getPedidos();

}
