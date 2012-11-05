package proveedor.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.PedidoMateriaPrima;
import proveedor.model.PedidoMateriaPrimaItem;
import proveedor.vo.PedidoMateriaPrimaItemVO;
import proveedor.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class PedidoPendienteSessionBean
 */
@Stateless
public class PedidoMateriaPrimaSessionBean implements PedidoMateriaPrimaSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public PedidoMateriaPrimaSessionBean() {
	}

	public void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		entityManager.persist(toPedidoMateriaPrima(pedidoMateriaPrimaVO));
	}

	public void deletePedidoMateriaPrima(int id) {
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(PedidoMateriaPrima.class, id);
		entityManager.remove(pedidoMateriaPrima);
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(PedidoMateriaPrima.class, id);
		return toPedidoMateriaPrimaVO(pedidoMateriaPrima);
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		Query query = entityManager.createQuery("SELECT P FROM PedidoMateriaPrima P");
		Collection<PedidoMateriaPrimaVO> pedidosMateriaPrimaVO = new ArrayList<PedidoMateriaPrimaVO>();
		for (PedidoMateriaPrima pedidoMateriaPrima : (Collection<PedidoMateriaPrima>) query.getResultList()) {
			pedidosMateriaPrimaVO.add(toPedidoMateriaPrimaVO(pedidoMateriaPrima));
		}
		return pedidosMateriaPrimaVO;
	}

	public static PedidoMateriaPrimaVO toPedidoMateriaPrimaVO(PedidoMateriaPrima pedidoMateriaPrima) {
		Collection<PedidoMateriaPrimaItemVO> items = new ArrayList<PedidoMateriaPrimaItemVO>();
		for (PedidoMateriaPrimaItem pcci : pedidoMateriaPrima.getItems()) {
			items.add(new PedidoMateriaPrimaItemVO(pcci.getId(), pcci.getCodigo(), pcci.getCantidad()));
		}
		return new PedidoMateriaPrimaVO(pedidoMateriaPrima.getId(), items);
	}

	public static PedidoMateriaPrima toPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		Collection<PedidoMateriaPrimaItem> items = new ArrayList<PedidoMateriaPrimaItem>();
		for (PedidoMateriaPrimaItemVO pcci : pedidoMateriaPrimaVO.getItems()) {
			items.add(new PedidoMateriaPrimaItem(pcci.getId(), pcci.getCodigo(), pcci.getCantidad()));
		}
		return new PedidoMateriaPrima(pedidoMateriaPrimaVO.getId(), items);
	}
}
