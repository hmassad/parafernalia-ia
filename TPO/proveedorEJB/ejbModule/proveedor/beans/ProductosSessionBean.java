package proveedor.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.MateriaPrima;
import proveedor.model.MateriaPrimaProducto;
import proveedor.model.Producto;
import proveedor.model.Unidad;
import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.ProductoVO;
import proveedor.vo.UnidadVO;

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
		entityManager.persist(toProducto(productoVO));
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
			productosVO.add(toProductoVO(p));
		}
		return productosVO;
	}

	public ProductoVO getProducto(String codigo) {
		Producto producto = entityManager.find(Producto.class, codigo);
		return toProductoVO(producto);
	}

	public static ProductoVO toProductoVO(Producto producto) {
		Collection<MateriaPrimaProductoVO> mppVOs = new ArrayList<MateriaPrimaProductoVO>();
		for (MateriaPrimaProducto mpp : producto.getMateriasPrimasProducto()) {
			MateriaPrimaVO mpVO = MateriaPrimaSessionBean.toMateriaPrimaVO(mpp.getMateriaPrima());
			UnidadVO uVO = new UnidadVO(mpp.getUnidad().getCodigo(), mpp.getUnidad().getDescripcion());
			MateriaPrimaProductoVO mppVO = new MateriaPrimaProductoVO(mpVO, mpp.getCantidad(), uVO);
			mppVOs.add(mppVO);
		}
		return new ProductoVO(producto.getCodigo(), producto.getDescripcion(), producto.getCaracteristica(), producto.getMarca(), producto.getOrigen(), mppVOs);
	}

	private static Producto toProducto(ProductoVO productoVO) {
		Producto producto = new Producto();
		producto.setCodigo(productoVO.getCodigo());
		producto.setDescripcion(productoVO.getDescripcion());
		producto.setCaracteristica(productoVO.getCaracteristica());
		producto.setMarca(productoVO.getMarca());
		producto.setOrigen(productoVO.getOrigen());
		for (MateriaPrimaProductoVO mppVO : productoVO.getMateriasPrimasProducto()) {
			MateriaPrima mp = MateriaPrimaSessionBean.toMateriaPrima(mppVO.getMateriaPrima());
			Unidad u = new Unidad(mppVO.getUnidad().getCodigo(), mppVO.getUnidad().getDescripcion());
			MateriaPrimaProducto mpp = new MateriaPrimaProducto(producto, mp, mppVO.getCantidad(), u);
			producto.getMateriasPrimasProducto().add(mpp);
		}
		return producto;
	}
}
