package proveedor.beans;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.ProductoVO;

@Local
public interface ProductosSessionBeanLocal {

	void createProducto(String codigo, String descripcion, String caracteristica, String marca, String origen, float precioUnitario);

	void deleteProducto(String codigo);

	Collection<ProductoVO> getProductos();

	ProductoVO getProducto(String codigo);

}
