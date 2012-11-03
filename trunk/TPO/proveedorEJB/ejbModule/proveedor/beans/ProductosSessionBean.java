package proveedor.beans;

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

	@PersistenceContext(unitName="proveedor")
	private EntityManager entityManager;

	public ProductosSessionBean() {
	}

	public void createProducto(String codigo, String descripcion, String caracteristica, String marca, String origen, float precioUnitario) {
		Producto producto = new Producto(codigo, descripcion, caracteristica, marca, origen, precioUnitario);
		entityManager.persist(producto);
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
			productosVO.add(new ProductoVO(p.getCodigo(), p.getDescripcion(), p.getCaracteristica(), p.getMarca(), p.getOrigen(), p.getPrecioUnitario()));
		}
		return productosVO;
	}

	public ProductoVO getProducto(String codigo) {
		Producto producto = entityManager.find(Producto.class, codigo);
		return new ProductoVO(producto.getCodigo(), producto.getDescripcion(), producto.getCaracteristica(), producto.getMarca(), producto.getOrigen(),
				producto.getPrecioUnitario());
	}

}
