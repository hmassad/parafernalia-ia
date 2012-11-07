package proveedor.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.ProductoVO;

@Local
public interface ProductosSessionBeanLocal {

	void createProducto(ProductoVO producto);

	void deleteProducto(String codigo);

	Collection<ProductoVO> getProductos();

	ProductoVO getProducto(String codigo);

}
