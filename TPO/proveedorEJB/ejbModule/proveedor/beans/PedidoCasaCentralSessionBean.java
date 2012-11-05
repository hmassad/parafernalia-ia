package proveedor.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.PedidoCasaCentral;
import proveedor.model.PedidoCasaCentralItem;
import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;

/**
 * Session Bean implementation class PedidoPendienteSessionBean
 */
@Stateless
public class PedidoCasaCentralSessionBean implements PedidoCasaCentralSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public PedidoCasaCentralSessionBean() {
	}

	public void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		entityManager.persist(toPedidoCasaCentral(pedidoCasaCentralVO));
	}

	public void deletePedidoCasaCentral(int id) {
		PedidoCasaCentral pedidoCasaCentral = entityManager.find(PedidoCasaCentral.class, id);
		entityManager.remove(pedidoCasaCentral);
	}

	public PedidoCasaCentralVO getPedidoCasaCentral(int id) {
		PedidoCasaCentral pedidoCasaCentral = entityManager.find(PedidoCasaCentral.class, id);
		return toPedidoCasaCentralVO(pedidoCasaCentral);
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoCasaCentralVO> getPedidosCasaCentral() {
		Query query = entityManager.createQuery("SELECT P FROM PedidoCasaCentral P");
		Collection<PedidoCasaCentralVO> pedidosCasaCentralVO = new ArrayList<PedidoCasaCentralVO>();
		for (PedidoCasaCentral pedidoCasaCentral : (Collection<PedidoCasaCentral>) query.getResultList()) {
			pedidosCasaCentralVO.add(toPedidoCasaCentralVO(pedidoCasaCentral));
		}
		return pedidosCasaCentralVO;
	}

	public static PedidoCasaCentralVO toPedidoCasaCentralVO(PedidoCasaCentral pedidoCasaCentral) {
		Collection<PedidoCasaCentralItemVO> items = new ArrayList<PedidoCasaCentralItemVO>();
		for (PedidoCasaCentralItem pcci : pedidoCasaCentral.getItems()) {
			items.add(new PedidoCasaCentralItemVO(pcci.getId(), pcci.getCodigo(), pcci.getCantidad()));
		}
		return new PedidoCasaCentralVO(pedidoCasaCentral.getId(), items);
	}

	public static PedidoCasaCentral toPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		Collection<PedidoCasaCentralItem> items = new ArrayList<PedidoCasaCentralItem>();
		for (PedidoCasaCentralItemVO pcci : pedidoCasaCentralVO.getItems()) {
			items.add(new PedidoCasaCentralItem(pcci.getId(), pcci.getCodigo(), pcci.getCantidad()));
		}
		return new PedidoCasaCentral(pedidoCasaCentralVO.getId(), items);
	}
}
