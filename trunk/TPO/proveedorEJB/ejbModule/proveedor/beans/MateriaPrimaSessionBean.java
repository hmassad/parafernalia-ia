package proveedor.beans;

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
		entityManager.persist(toMateriaPrima(materiaPrimaVO));
	}

	public void deleteMateriaPrima(String codigo) {
		MateriaPrima materiaPrima = entityManager.find(MateriaPrima.class, codigo);
		entityManager.remove(materiaPrima);
	}

	public MateriaPrimaVO getMateriaPrima(String codigo) {
		MateriaPrima materiaPrimaVO = entityManager.find(MateriaPrima.class, codigo);
		return toMateriaPrimaVO(materiaPrimaVO);
	}

	@SuppressWarnings("unchecked")
	public Collection<MateriaPrimaVO> getMateriasPrimas() {
		Query query = entityManager.createQuery("SELECT MP FROM MateriaPrima MP");
		Collection<MateriaPrimaVO> materiasPrimasVO = new ArrayList<MateriaPrimaVO>();
		for (MateriaPrima materiaPrima : (Collection<MateriaPrima>) query.getResultList()) {
			materiasPrimasVO.add(toMateriaPrimaVO(materiaPrima));
		}
		return materiasPrimasVO;
	}

	public static MateriaPrimaVO toMateriaPrimaVO(MateriaPrima materiaPrima) {
		return new MateriaPrimaVO(materiaPrima.getCodigo(), materiaPrima.getDescripcion(), materiaPrima.getStock());
	}

	public static MateriaPrima toMateriaPrima(MateriaPrimaVO materiaPrimaVO) {
		return new MateriaPrima(materiaPrimaVO.getCodigo(), materiaPrimaVO.getDescripcion(), materiaPrimaVO.getStock());
	}
}
