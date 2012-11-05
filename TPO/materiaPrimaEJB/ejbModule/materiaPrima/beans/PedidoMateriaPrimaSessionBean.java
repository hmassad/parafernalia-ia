package materiaPrima.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import materiaPrima.model.PedidoMateriaPrima;
import materiaPrima.model.PedidoMateriaPrimaItem;
import materiaPrima.vo.PedidoMateriaPrimaItemVO;
import materiaPrima.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class MateriaPrimaSessionBean
 */
@Stateless
public class PedidoMateriaPrimaSessionBean implements MateriaPrimaSessionBeanLocal {

	@PersistenceContext(unitName = "materiaPrima")
	private EntityManager entityManager;

	public PedidoMateriaPrimaSessionBean() {
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoMateriaPrimaVO> getPedidos() {
		Query query = entityManager.createQuery("SELECT MP FROM PedidoMateriaPrima MP");
		Collection<PedidoMateriaPrimaVO> pedidos = new ArrayList<PedidoMateriaPrimaVO>();
		for (PedidoMateriaPrima pedidoMateriaPrima : (Collection<PedidoMateriaPrima>) query.getResultList()) {
			pedidos.add(toPedidoMateriaPrimaVO(pedidoMateriaPrima));
		}
		return pedidos;
	}

	public static PedidoMateriaPrimaVO toPedidoMateriaPrimaVO(PedidoMateriaPrima pedidoMateriaPrima) {
		Collection<PedidoMateriaPrimaItemVO> items = new ArrayList<PedidoMateriaPrimaItemVO>();
		for (PedidoMateriaPrimaItem pmpi : pedidoMateriaPrima.getItems()) {
			items.add(new PedidoMateriaPrimaItemVO(pmpi.getId(), pmpi.getCodigo(), pmpi.getCantidad()));
		}
		return new PedidoMateriaPrimaVO(pedidoMateriaPrima.getId(), pedidoMateriaPrima.getEntregado(), items);
	}

	public static PedidoMateriaPrima toPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		Collection<PedidoMateriaPrimaItem> items = new ArrayList<PedidoMateriaPrimaItem>();
		for (PedidoMateriaPrimaItemVO pmpiVO : pedidoMateriaPrimaVO.getItems()) {
			items.add(new PedidoMateriaPrimaItem(pmpiVO.getId(), pmpiVO.getCodigo(), pmpiVO.getCantidad()));
		}
		return new PedidoMateriaPrima(pedidoMateriaPrimaVO.getId(), pedidoMateriaPrimaVO.getEntregado(), items);
	}
}
