package proveedor.beans.local;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
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

	public void createProducto(ProductoVO productoVO)
			throws MalformedURLException, RemoteException {
		Producto producto = Producto.toProducto(productoVO);
		entityManager.persist(producto);

		// NvoProd nvoProd = new NvoProd(productoVO.getCodigo(),
		// productoVO.getCaracteristica(), productoVO.getMarca(),
		// productoVO.getOrigen(), productoVO.getTipo(),
		// productoVO.getCodigo());
		// String xml = nvoProd.serialize();
		//
		// URL wsdlDocumentLocation = new URL(
		// proveedor.configuration.Configuration.CasaCentralWebServiceLocation
		// + "?wsdl");
		// QName serviceName = new QName("http://webservice.casacentral.ejb/",
		// "RemoteSessionBeanService");
		// Service service = Service.create(wsdlDocumentLocation, serviceName);
		// RemoteSessionBean port = service.getPort(RemoteSessionBean.class);
		// port.nuevoRodamiento(xml);
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
