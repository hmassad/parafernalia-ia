package proveedor.beans.local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.beans.local.ProveedorSessionBeanLocal;
import proveedor.model.Proveedor;
import proveedor.vo.ProveedorVO;

/**
 * Session Bean implementation class ProveedorSessionBean
 */
@Stateless
public class ProveedorSessionBean implements ProveedorSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public ProveedorSessionBean() {
	}

	public void updateProveedor(ProveedorVO proveedorVO) {
		entityManager.merge(Proveedor.toProveedor(proveedorVO));
	}

	public ProveedorVO getProveedor() {
		Query query = entityManager.createQuery("SELECT P FROM Proveedor P");
		try {
			return Proveedor.toProveedorVO((Proveedor) query.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}
}
