package proveedor.beans.remote;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.Remote;
import javax.xml.rpc.ServiceException;

import proveedor.vo.ListaPreciosVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.PedidoCasaCentralVO;
import proveedor.vo.PedidoMateriaPrimaVO;
import proveedor.vo.ProductoVO;
import proveedor.vo.ProveedorVO;

@Remote
public interface FachadaSessionBeanRemote {

	// Materia Prima

	void createMateriaPrima(MateriaPrimaVO materiaPrima);

	void deleteMateriaPrima(String codigo);

	Collection<MateriaPrimaVO> getMateriasPrimas();

	MateriaPrimaVO getMateriaPrima(String codigo);

	void ingresarStock(String codigoMateriaPrima, int cantidad);

	void descontarStock(String codigoMateriaPrima, int cantidad);

	// Lista de Precios

	void createListaPrecios(ListaPreciosVO listaPreciosVO);

	void deleteListaPrecios(int id);

	ListaPreciosVO getListaPrecios(int id);

	Collection<ListaPreciosVO> getListasPrecios();

	ListaPreciosVO getUltimaListaPrecios();

	// Pedido Casa Central

	void createPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void deletePedidoCasaCentral(int id);

	Collection<PedidoCasaCentralVO> getPedidosCasaCentral();

	Collection<PedidoCasaCentralVO> getPedidosCasaCentralByEntregado(
			boolean entregado);

	PedidoCasaCentralVO getPedidoCasaCentral(int id);

	void updatePedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);
	
	// Pedido Materia Prima

	void createPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void deletePedidoMateriaPrima(int id);

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrima();

	Collection<PedidoMateriaPrimaVO> getPedidosMateriaPrimaByEntregado(
			boolean entregado);

	PedidoMateriaPrimaVO getPedidoMateriaPrima(int id);

	void updatePedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);
	
	// Productos

	void createProducto(ProductoVO producto) throws RemoteException,
			MalformedURLException, ServiceException;

	void deleteProducto(String codigo);

	Collection<ProductoVO> getProductos();

	ProductoVO getProducto(String codigo);

	// Pedidos

	void enviarPedidoMateriaPrima(PedidoMateriaPrimaVO pedidoMateriaPrimaVO);

	void recibirPedidoMateriaPrima(int id);

	void recibirPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	void enviarPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO);

	// Proveedor
	
	ProveedorVO getProveedor();

}
