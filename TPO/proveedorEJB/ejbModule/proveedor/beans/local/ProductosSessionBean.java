package proveedor.beans.local;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.WebServiceRef;

import proveedor.configuration.Configuration;
import proveedor.documentos.NvoProd;
import proveedor.model.Producto;
import proveedor.vo.ProductoVO;
import casaCentral.webservice.RemoteSessionBean;
import casaCentral.webservice.RemoteSessionBeanService;

/**
 * Session Bean implementation class ProductosSessionBean
 */
@Stateless
public class ProductosSessionBean implements ProductosSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	@WebServiceRef(wsdlLocation = Configuration.CasaCentralWebServiceLocation
			+ "?wsdl")
	static RemoteSessionBeanService service;

	public ProductosSessionBean() {
	}

	public void createProducto(ProductoVO productoVO)
			throws MalformedURLException, RemoteException {
		Producto producto = Producto.toProducto(productoVO);
		entityManager.persist(producto);

		NvoProd nvoProd = new NvoProd(productoVO.getCodigo(),
				productoVO.getCaracteristica(), productoVO.getMarca(),
				productoVO.getOrigen(), productoVO.getTipo(),
				Configuration.Proveedor.getCuit());
		String xml = nvoProd.serialize();

		RemoteSessionBean port = service.getRemoteSessionBeanPort();
		port.nuevoRodamiento(xml);
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
