package proveedor.beans;

import java.util.Collection;

import javax.ejb.Stateless;

import proveedor.vo.ProductoVO;

/**
 * Session Bean implementation class ListaPreciosSessionBean
 */
@Stateless
public class ListaPreciosSessionBean implements ListaPreciosSessionBeanLocal {

	/**
	 * Default constructor.
	 */
	public ListaPreciosSessionBean() {
		// TODO Auto-generated constructor stub
	}

	public void createProducto(String codigo, String descripcion, String caracteristica, String marca, String origen, float precioUnitario) {
		// TODO Auto-generated method stub

	}

	public void deleteProducto(String codigo) {
		// TODO Auto-generated method stub

	}

	public Collection<ProductoVO> getProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductoVO getProducto(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
