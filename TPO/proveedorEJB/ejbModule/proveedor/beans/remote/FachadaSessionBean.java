package proveedor.beans.remote;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import proveedor.beans.local.ListaPreciosSessionBeanLocal;
import proveedor.beans.local.MateriaPrimaSessionBeanLocal;
import proveedor.beans.local.PedidoCasaCentralSessionBeanLocal;
import proveedor.beans.local.PedidoMateriaPrimaSessionBeanLocal;
import proveedor.beans.local.ProductosSessionBeanLocal;
import proveedor.beans.local.ProveedorSessionBeanLocal;
import proveedor.beans.local.UnidadesSessionBeanLocal;
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
public class FachadaSessionBean implements FachadaSessionBeanRemote {

	@EJB
	ListaPreciosSessionBeanLocal listaPreciosSessionBeanLocal;

	@EJB
	MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal;

	@EJB
	ProductosSessionBeanLocal productosSessionBeanLocal;

	@EJB
	ProveedorSessionBeanLocal proveedorSessionBeanLocal;

	@EJB
	UnidadesSessionBeanLocal unidadesSessionBeanLocal;

	@EJB
	PedidoMateriaPrimaSessionBeanLocal pedidoMateriaPrimaSessionBeanLocal;

	@EJB
	PedidoCasaCentralSessionBeanLocal pedidoCasaCentralSessionBeanLocal;

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
		pedidoMateriaPrimaSessionBeanLocal.createPedidoMateriaPrima(pedidoVO);
	}

	public void deletePedidoMateriaPrima(int id) {
		pedidoMateriaPrimaSessionBeanLocal.deletePedidoMateriaPrima(id);
	}

	public Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima() {
		return pedidoMateriaPrimaSessionBeanLocal.getPedidosMateriaPrima();
	}

	public PedidoMateriaPrimaVO getPedidoMateriaPrima(int id) {
		return pedidoMateriaPrimaSessionBeanLocal.getPedidoMateriaPrima(id);
	}

	public void enviarPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		pedidoMateriaPrimaSessionBeanLocal.enviarPedidoMateriaPrima(pedidoMateriaPrimaVO);
	}

	public void recibirPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		pedidoMateriaPrimaSessionBeanLocal.recibirPedidoMateriaPrima(pedidoMateriaPrimaVO);
	}

	public void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidoCasaCentralSessionBeanLocal.createPedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void deletePedidoCasaCentral(int id) {
		pedidoCasaCentralSessionBeanLocal.deletePedidoCasaCentral(id);
	}

	public Collection<PedidoCasaCentralVO> getPedidosCasaCentral() {
		return pedidoCasaCentralSessionBeanLocal.getPedidosCasaCentral();
	}

	public PedidoCasaCentralVO getPedidoCasaCentral(int id) {
		return pedidoCasaCentralSessionBeanLocal.getPedidoCasaCentral(id);
	}

	public void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidoCasaCentralSessionBeanLocal.recibirPedidoCasaCentral(pedidoCasaCentralVO);
	}

	public void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		pedidoCasaCentralSessionBeanLocal.enviarPedidoCasaCentral(pedidoCasaCentralVO);
	}

}
