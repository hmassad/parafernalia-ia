package proveedor.beans.local;

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
public class PedidoCasaCentralSessionBean implements
		PedidoCasaCentralSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public PedidoCasaCentralSessionBean() {
	}

	public void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		entityManager.persist(toPedidoCasaCentral(pedidoCasaCentralVO));
	}

	public void deletePedidoCasaCentral(int id) {
		PedidoCasaCentral pedidoCasaCentral = entityManager.find(
				PedidoCasaCentral.class, id);
		entityManager.remove(pedidoCasaCentral);
	}

	public PedidoCasaCentralVO getPedidoCasaCentral(int id) {
		PedidoCasaCentral pedidoCasaCentral = entityManager.find(
				PedidoCasaCentral.class, id);
		return toPedidoCasaCentralVO(pedidoCasaCentral);
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoCasaCentralVO> getPedidosCasaCentral() {
		Query query = entityManager
				.createQuery("SELECT P FROM PedidoCasaCentral P");
		Collection<PedidoCasaCentralVO> pedidosCasaCentralVO = new ArrayList<PedidoCasaCentralVO>();
		for (PedidoCasaCentral pedidoCasaCentral : (Collection<PedidoCasaCentral>) query
				.getResultList()) {
			pedidosCasaCentralVO.add(toPedidoCasaCentralVO(pedidoCasaCentral));
		}
		return pedidosCasaCentralVO;
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoCasaCentralVO> getPedidosCasaCentralByEntregado(
			boolean entregado) {
		Query query = entityManager.createQuery(
				"SELECT PCC FROM PedidoCasaCentral PCC WHERE entregado = 1?")
				.setParameter(1, entregado);
		Collection<PedidoCasaCentralVO> pedidosCasaCentralVO = new ArrayList<PedidoCasaCentralVO>();
		for (PedidoCasaCentral pedidoCasaCentral : (Collection<PedidoCasaCentral>) query
				.getResultList()) {
			pedidosCasaCentralVO.add(PedidoCasaCentral
					.toPedidoCasaCentralVO(pedidoCasaCentral));
		}
		return pedidosCasaCentralVO;
	}

	public static PedidoCasaCentralVO toPedidoCasaCentralVO(
			PedidoCasaCentral pedidoCasaCentral) {
		Collection<PedidoCasaCentralItemVO> items = new ArrayList<PedidoCasaCentralItemVO>();
		for (PedidoCasaCentralItem pedidoCasaCentralItem : pedidoCasaCentral
				.getItems())
			items.add(PedidoCasaCentralItem
					.toPedidoCasaCentralItemVO(pedidoCasaCentralItem));
		return new PedidoCasaCentralVO(pedidoCasaCentral.getId(),
				pedidoCasaCentral.getEntregado(), pedidoCasaCentral.getFecha(),
				pedidoCasaCentral.getNroOrdenCompra(), items);
	}

	public static PedidoCasaCentral toPedidoCasaCentral(
			PedidoCasaCentralVO pedidoCasaCentralVO) {
		PedidoCasaCentral pedidoCasaCentral = new PedidoCasaCentral();
		pedidoCasaCentral.setId(pedidoCasaCentralVO.getId());
		pedidoCasaCentral.setEntregado(pedidoCasaCentralVO.getEntregado());
		pedidoCasaCentral.setFecha(pedidoCasaCentralVO.getFecha());
		pedidoCasaCentral.setNroOrdenCompra(pedidoCasaCentralVO
				.getNroOrdenCompra());
		for (PedidoCasaCentralItemVO pedidoCasaCentralItem : pedidoCasaCentralVO
				.getItems())
			pedidoCasaCentral.getItems().add(
					PedidoCasaCentralItem.toPedidoCasaCentralItem(
							pedidoCasaCentral, pedidoCasaCentralItem));
		return pedidoCasaCentral;
	}

}
