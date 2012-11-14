package proveedor.beans.local;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.PedidoMateriaPrima;
import proveedor.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class PedidoPendienteSessionBean
 */
@Stateless
public class PedidoMateriaPrimaSessionBean implements
		PedidoMateriaPrimaSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public PedidoMateriaPrimaSessionBean() {
	}

	public void createPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		PedidoMateriaPrima pedidoMateriaPrima = PedidoMateriaPrima
				.toPedidoMateriaPrima(pedidoMateriaPrimaVO);
		entityManager.persist(pedidoMateriaPrima);
		pedidoMateriaPrimaVO.setId(pedidoMateriaPrima.getId());
	}

	public void deletePedidoMateriaPrima(int id) {
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(
				PedidoMateriaPrima.class, id);
		entityManager.remove(pedidoMateriaPrima);
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		PedidoMateriaPrima pedidoMateriaPrima = entityManager.find(
				PedidoMateriaPrima.class, id);
		return PedidoMateriaPrima.toPedidoMateriaPrimaVO(pedidoMateriaPrima);
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		Query query = entityManager
				.createQuery("SELECT PMP FROM PedidoMateriaPrima PMP");
		Collection<PedidoMateriaPrimaVO> pedidosMateriaPrimaVO = new ArrayList<PedidoMateriaPrimaVO>();
		for (PedidoMateriaPrima pedidoMateriaPrima : (Collection<PedidoMateriaPrima>) query
				.getResultList()) {
			pedidosMateriaPrimaVO.add(PedidoMateriaPrima
					.toPedidoMateriaPrimaVO(pedidoMateriaPrima));
		}
		return pedidosMateriaPrimaVO;
	}

	@SuppressWarnings("unchecked")
	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(
			boolean entregado) {
		Query query = entityManager.createQuery(
				"SELECT PMP FROM PedidoMateriaPrima PMP WHERE PMP.entregado = :entregado")
				.setParameter("entregado", entregado);
		Collection<PedidoMateriaPrimaVO> pedidosMateriaPrimaVO = new ArrayList<PedidoMateriaPrimaVO>();
		for (PedidoMateriaPrima pedidoMateriaPrima : (Collection<PedidoMateriaPrima>) query
				.getResultList()) {
			pedidosMateriaPrimaVO.add(PedidoMateriaPrima
					.toPedidoMateriaPrimaVO(pedidoMateriaPrima));
		}
		return pedidosMateriaPrimaVO;
	}

	public void updatePedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		PedidoMateriaPrima pedidoMateriaPrima = PedidoMateriaPrima
				.toPedidoMateriaPrima(pedidoMateriaPrimaVO);
		pedidoMateriaPrima = entityManager.merge(pedidoMateriaPrima);
	}

}
