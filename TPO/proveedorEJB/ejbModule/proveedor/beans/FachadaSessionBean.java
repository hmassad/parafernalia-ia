package proveedor.beans;

import java.util.Collection;
import java.util.Hashtable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import proveedor.vo.ListaPreciosVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaVO;
import proveedor.vo.ProductoVO;
import proveedor.vo.ProveedorVO;
import proveedor.vo.UnidadVO;

/**
 * Session Bean implementation class FachadaSessionBean
 */
@Stateless
@Local({ ListaPreciosSessionBeanLocal.class, MateriaPrimaSessionBeanLocal.class, ProductosSessionBeanLocal.class, ProveedorSessionBeanLocal.class,
		UnidadesSessionBeanLocal.class, PedidoMateriaPrimaSessionBeanLocal.class })
public class FachadaSessionBean implements FachadaSessionBeanRemote {

	InitialContext initialContext;
	ListaPreciosSessionBeanLocal listaPreciosSessionBeanLocal;
	MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal;
	ProductosSessionBeanLocal productosSessionBeanLocal;
	ProveedorSessionBeanLocal proveedorSessionBeanLocal;
	UnidadesSessionBeanLocal unidadesSessionBeanLocal;
	PedidoMateriaPrimaSessionBeanLocal pedidosMateriaPrimaSessionBeanLocal;
	PedidoCasaCentralSessionBeanLocal pedidosCasaCentralSessionBeanLocal;

	public FachadaSessionBean() throws NamingException {
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		// Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		initialContext = new InitialContext(props);

		listaPreciosSessionBeanLocal = (ListaPreciosSessionBeanLocal) initialContext.lookup("proveedorEAR/ListaPreciosSessionBean/local");
		materiaPrimaSessionBeanLocal = (MateriaPrimaSessionBeanLocal) initialContext.lookup("proveedorEAR/MateriaPrimaSessionBean/local");
		productosSessionBeanLocal = (ProductosSessionBeanLocal) initialContext.lookup("proveedorEAR/ProductosSessionBean/local");
		proveedorSessionBeanLocal = (ProveedorSessionBeanLocal) initialContext.lookup("proveedorEAR/ProveedorSessionBean/local");
		unidadesSessionBeanLocal = (UnidadesSessionBeanLocal) initialContext.lookup("proveedorEAR/UnidadesSessionBean/local");
		pedidosMateriaPrimaSessionBeanLocal = (PedidoMateriaPrimaSessionBeanLocal) initialContext
				.lookup("proveedorEAR/PedidoMateriaPrimaSessionBeanLocal/local");
		pedidosCasaCentralSessionBeanLocal = (PedidoCasaCentralSessionBeanLocal) initialContext.lookup("proveedorEAR/PedidoCasaCentralSessionBeanLocal/local");
	}

	public void createListaPrecios(ListaPreciosVO listaPreciosVO) {
		listaPreciosSessionBeanLocal.createListaPrecios(listaPreciosVO);
	}

	public void deleteListaPrecios(int id) {
		listaPreciosSessionBeanLocal.deleteListaPrecios(id);
	}

	public Collection<ListaPreciosVO> getListaPrecios() {
		return listaPreciosSessionBeanLocal.getListaPrecios();
	}

	public ListaPreciosVO getListaPrecios(int id) {
		return listaPreciosSessionBeanLocal.getListaPrecios(id);
	}

	public ListaPreciosVO getUltimaListaPrecios() {
		return listaPreciosSessionBeanLocal.getUltimaListaPrecios();
	}

	public void createMateriaPrima(MateriaPrimaVO materiaPrima) {
		materiaPrimaSessionBeanLocal.createMateriaPrima(materiaPrima);
	}

	public void deleteMateriaPrima(String codigo) {
		materiaPrimaSessionBeanLocal.deleteMateriaPrima(codigo);
	}

	public Collection<MateriaPrimaVO> getMateriasPrimas() {
		return materiaPrimaSessionBeanLocal.getMateriasPrimas();
	}

	public MateriaPrimaVO getMateriaPrima(String codigo) {
		return materiaPrimaSessionBeanLocal.getMateriaPrima(codigo);
	}

	public void createProducto(ProductoVO productoVO) {
		productosSessionBeanLocal.createProducto(productoVO);
	}

	public void deleteProducto(String codigo) {
		productosSessionBeanLocal.deleteProducto(codigo);
	}

	public Collection<ProductoVO> getProductos() {
		return productosSessionBeanLocal.getProductos();
	}

	public ProductoVO getProducto(String codigo) {
		return productosSessionBeanLocal.getProducto(codigo);
	}

	public ProveedorVO getProveedor() {
		return proveedorSessionBeanLocal.getProveedor();
	}

	public void updateProveedor(ProveedorVO proveedorVO) {
		proveedorSessionBeanLocal.updateProveedor(proveedorVO);
	}

	public void createUnidad(UnidadVO unidadVo) {
		unidadesSessionBeanLocal.createUnidad(unidadVo);
	}

	public void deleteUnidad(String codigo) {
		unidadesSessionBeanLocal.deleteUnidad(codigo);
	}

	public Collection<UnidadVO> getUnidades() {
		return unidadesSessionBeanLocal.getUnidades();
	}

	public UnidadVO getUnidad(String codigo) {
		return unidadesSessionBeanLocal.getUnidad(codigo);
	}

	public void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoVO) {
		pedidosMateriaPrimaSessionBeanLocal.createPedidoMateriaPrima(pedidoVO);
	}

	public void deletePedidoMateriaPrima(int id) {
		pedidosMateriaPrimaSessionBeanLocal.deletePedidoMateriaPrima(id);
	}

	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		return pedidosMateriaPrimaSessionBeanLocal.getPedidosMateriaPrima();
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		return pedidosMateriaPrimaSessionBeanLocal.getPedidoMateriaPrima(id);
	}

	public void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidosCasaCentralSessionBeanLocal.createPedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void deletePedidoCasaCentral(int id) {
		pedidosCasaCentralSessionBeanLocal.deletePedidoCasaCentral(id);
	}

	public Collection<PedidoCasaCentralVO> getPedidosCasaCentral() {
		return pedidosCasaCentralSessionBeanLocal.getPedidosCasaCentral();
	}

	public PedidoCasaCentralVO getPedidoCasaCentral(int id) {
		return pedidosCasaCentralSessionBeanLocal.getPedidoCasaCentral(id);
	}
}
