package proveedor.beans.local;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.Producto;
import proveedor.vo.ProductoVO;

/**
 * Session Bean implementation class ProductosSessionBean
 */
@Stateless
public class ProductosSessionBean implements ProductosSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public ProductosSessionBean() {
	}

	public void createProducto(ProductoVO productoVO) {
		Producto producto = Producto.toProducto(productoVO);
		producto = entityManager.merge(producto);
	}

	public void deleteProducto(String codigo) {
		Producto producto = entityManager.find(Producto.class, codigo);
		entityManager.remove(producto);
	}

	@SuppressWarnings("unchecked")
	public Collection<ProductoVO> getProductos() {
		Query query = entityManager.createQuery("SELECT P FROM Producto P");
		Collection<ProductoVO> productosVO = new ArrayList<ProductoVO>();
		for (Producto p : (Collection<Producto>) query.getResultList()) {
			productosVO.add(Producto.toProductoVO(p));
		}
		return productosVO;
	}

	public ProductoVO getProducto(String codigo) {
		Producto producto = entityManager.find(Producto.class, codigo);
		return Producto.toProductoVO(producto);
	}
}
