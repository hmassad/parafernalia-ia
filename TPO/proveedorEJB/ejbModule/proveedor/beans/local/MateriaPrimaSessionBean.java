package proveedor.beans.local;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.MateriaPrima;
import proveedor.vo.MateriaPrimaVO;

/**
 * Session Bean implementation class MateriaPrimaSessionBean
 */
@Stateless
public class MateriaPrimaSessionBean implements MateriaPrimaSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public MateriaPrimaSessionBean() {
	}

	public void createMateriaPrima(MateriaPrimaVO materiaPrimaVO) {
		MateriaPrima materiaPrima = MateriaPrima.toMateriaPrima(materiaPrimaVO);
		materiaPrima = entityManager.merge(materiaPrima);
	}

	public void deleteMateriaPrima(String codigo) {
		MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class,
				codigo);
		entityManager.remove(materiaPrima);
	}

	public MateriaPrimaVO getMateriaPrima(String codigo) {
		MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class,
				codigo);
		return MateriaPrima.toMateriaPrimaVO(materiaPrima);
	}

	@SuppressWarnings("unchecked")
	public Collection<MateriaPrimaVO> getMateriasPrimas() {
		Query query = entityManager
				.createQuery("SELECT MP FROM MateriaPrima MP");
		Collection<MateriaPrimaVO> materiasPrimaVOs = new ArrayList<MateriaPrimaVO>();
		for (MateriaPrima materiaPrima : (Collection<MateriaPrima>) query
				.getResultList()) {
			materiasPrimaVOs.add(MateriaPrima.toMateriaPrimaVO(materiaPrima));
		}
		return materiasPrimaVOs;
	}

	public void ingresarStock(String codigoMateriaPrima, int cantidad) {
		MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class,
				codigoMateriaPrima);
		materiaPrima.setStock(materiaPrima.getStock() + cantidad);
		entityManager.merge(materiaPrima);
	}

	public void descontarStock(String codigoMateriaPrima, int cantidad) {
		MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class,
				codigoMateriaPrima);
		materiaPrima.setStock(materiaPrima.getStock() - cantidad);
		entityManager.merge(materiaPrima);
	}
}
