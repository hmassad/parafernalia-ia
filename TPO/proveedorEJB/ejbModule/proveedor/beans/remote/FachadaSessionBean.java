package proveedor.beans.remote;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import proveedor.beans.local.ListaPreciosSessionBeanLocal;
import proveedor.beans.local.MateriaPrimaSessionBeanLocal;
import proveedor.beans.local.PedidoCasaCentralSessionBeanLocal;
import proveedor.beans.local.PedidoMateriaPrimaSessionBeanLocal;
import proveedor.beans.local.PedidosSessionBeanLocal;
import proveedor.beans.local.ProductosSessionBeanLocal;
import proveedor.beans.local.ProveedorSessionBeanLocal;
import proveedor.vo.ListaPreciosVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaVO;
import proveedor.vo.ProductoVO;
import proveedor.vo.ProveedorVO;

/**
 * Session Bean implementation class FachadaSessionBean
 */
@Stateless
public class FachadaSessionBean implements FachadaSessionBeanRemote {

	@EJB
	ListaPreciosSessionBeanLocal listaPreciosSessionBeanLocal;

	@EJB
	MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal;

	@EJB
	PedidoMateriaPrimaSessionBeanLocal pedidoMateriaPrimaSessionBeanLocal;

	@EJB
	PedidoCasaCentralSessionBeanLocal pedidoCasaCentralSessionBeanLocal;

	@EJB
	ProductosSessionBeanLocal productosSessionBeanLocal;

	@EJB
	PedidosSessionBeanLocal pedidosSessionBeanLocal;

	@EJB
	ProveedorSessionBeanLocal proveedorSessionBeanLocal;

	public FachadaSessionBean() {
	}

	public void createListaPrecios(ListaPreciosVO listaPreciosVO) {
		listaPreciosSessionBeanLocal.createListaPrecios(listaPreciosVO);
	}

	public void deleteListaPrecios(int id) {
		listaPreciosSessionBeanLocal.deleteListaPrecios(id);
	}

	public Collection<ListaPreciosVO> getListasPrecios() {
		return listaPreciosSessionBeanLocal.getListasPrecios();
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

	public void createProducto(ProductoVO productoVO) throws RemoteException,
			MalformedURLException, ServiceException {
		productosSessionBeanLocal.createProducto(productoVO);

//		new NuevoRodamientoWebServiceClient()
//				.notificarNuevoProducto(productoVO);
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

	public void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoVO) {
		pedidoMateriaPrimaSessionBeanLocal.createPedidoMateriaPrima(pedidoVO);
	}

	public void deletePedidoMateriaPrima(int id) {
		pedidoMateriaPrimaSessionBeanLocal.deletePedidoMateriaPrima(id);
	}

	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		return pedidoMateriaPrimaSessionBeanLocal.getPedidosMateriaPrima();
	}

	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(
			boolean entregado) {
		return pedidoMateriaPrimaSessionBeanLocal
				.getPedidosMateriaPrimaByEntregado(entregado);
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		return pedidoMateriaPrimaSessionBeanLocal.getPedidoMateriaPrima(id);
	}

	public void updatePedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		pedidoMateriaPrimaSessionBeanLocal
				.updatePedidoMateriaPrima(pedidoMateriaPrimaVO);
	}

	public void enviarPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		pedidosSessionBeanLocal.enviarPedidoMateriaPrima(pedidoMateriaPrimaVO);
	}

	public void recibirPedidoMateriaPrima(int id) {
		pedidosSessionBeanLocal.recibirPedidoMateriaPrima(id);
	}

	public void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidoCasaCentralSessionBeanLocal
				.createPedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void deletePedidoCasaCentral(int id) {
		pedidoCasaCentralSessionBeanLocal.deletePedidoCasaCentral(id);
	}

	public Collection<PedidoCasaCentralVO> getPedidosCasaCentral() {
		return pedidoCasaCentralSessionBeanLocal.getPedidosCasaCentral();
	}

	public Collection<PedidoCasaCentralVO> getPedidosCasaCentralByEntregado(
			boolean entregado) {
		return pedidoCasaCentralSessionBeanLocal
				.getPedidosCasaCentralByEntregado(entregado);
	}

	public PedidoCasaCentralVO getPedidoCasaCentral(int id) {
		return pedidoCasaCentralSessionBeanLocal.getPedidoCasaCentral(id);
	}

	public void updatePedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidoCasaCentralSessionBeanLocal
				.updatePedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidosSessionBeanLocal.recibirPedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidosSessionBeanLocal.enviarPedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void ingresarStock(String codigoMateriaPrima, int cantidad) {
		materiaPrimaSessionBeanLocal
				.ingresarStock(codigoMateriaPrima, cantidad);
	}

	public void descontarStock(String codigoMateriaPrima, int cantidad) {
		materiaPrimaSessionBeanLocal.descontarStock(codigoMateriaPrima,
				cantidad);
	}

	public ProveedorVO getProveedor() {
		return proveedorSessionBeanLocal.getProveedor();
	}

}
