package proveedor.beans.local;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.beans.local.UnidadesSessionBeanLocal;
import proveedor.model.Unidad;
import proveedor.vo.UnidadVO;

/**
 * Session Bean implementation class UnidadesSessionBean
 */
@Stateless
public class UnidadesSessionBean implements UnidadesSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public UnidadesSessionBean() {
	}

	public void createUnidad(UnidadVO unidadVO) {
		entityManager.persist(Unidad.toUnidad(unidadVO));
	}

	public void deleteUnidad(String codigo) {
		Unidad unidad = entityManager.find(Unidad.class, codigo);
		entityManager.remove(unidad);
	}

	@SuppressWarnings("unchecked")
	public Collection<UnidadVO> getUnidades() {
		Query query = entityManager.createQuery("SELECT U FROM Unidad U");
		Collection<UnidadVO> unidadVOs = new ArrayList<UnidadVO>();
		for (Unidad u : (Collection<Unidad>) query.getResultList()) {
			unidadVOs.add(Unidad.toUnidadVO(u));
		}
		return unidadVOs;
	}

	public UnidadVO getUnidad(String codigo) {
		Unidad unidad = entityManager.find(Unidad.class, codigo);
		return Unidad.toUnidadVO(unidad);
	}
}
