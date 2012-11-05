package proveedor.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		entityManager.merge(toProveedor(proveedorVO));
	}

	public ProveedorVO getProveedor() {
		Query query = entityManager.createQuery("SELECT P FROM Proveedor P");
		try {
			return toProveedorVO((Proveedor) query.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}

	public static ProveedorVO toProveedorVO(Proveedor proveedor) {
		return new ProveedorVO(proveedor.getCuit(), proveedor.getRazonSocial(), proveedor.getTelefono(), proveedor.getDireccion(), proveedor.getCiudad(),
				proveedor.getProvincia(), proveedor.getCodigoPostal());
	}

	public static Proveedor toProveedor(ProveedorVO proveedorVO) {
		return new Proveedor(proveedorVO.getCuit(), proveedorVO.getRazonSocial(), proveedorVO.getTelefono(), proveedorVO.getDireccion(),
				proveedorVO.getCiudad(), proveedorVO.getProvincia(), proveedorVO.getCodigoPostal());
	}
}
