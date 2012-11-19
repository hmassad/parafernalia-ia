package proveedor.beans.local;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.ProductoVO;

@Local
public interface ProductosSessionBeanLocal {

	void createProducto(ProductoVO producto) throws MalformedURLException,
			RemoteException;

	void deleteProducto(String codigo);

	Collection<ProductoVO> getProductos();

	ProductoVO getProducto(String codigo);

}
