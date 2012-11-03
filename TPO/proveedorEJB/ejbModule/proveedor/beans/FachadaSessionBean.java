package proveedor.beans;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import proveedor.vo.ProductoVO;

/**
 * Session Bean implementation class FachadaSessionBean
 */
@Stateless
@Local({ ListaPreciosSessionBeanLocal.class, MateriaPrimaSessionBeanLocal.class, ProductosSessionBeanLocal.class })
public class FachadaSessionBean implements FachadaSessionBeanRemote, ListaPreciosSessionBeanLocal, MateriaPrimaSessionBeanLocal, ProductosSessionBeanLocal {

	InitialContext initialContext;
	ProductosSessionBeanLocal productosSessionBeanLocal;
	MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal;

	/**
	 * Default constructor.
	 * 
	 * @throws NamingException
	 */
	public FachadaSessionBean() throws NamingException {
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		// Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		// Objeto del tipo InitialContext
		initialContext = new InitialContext(props);

		materiaPrimaSessionBeanLocal = (MateriaPrimaSessionBeanLocal) initialContext.lookup("proveedorEAR/MateriaPrimaSessionBean/local");
		productosSessionBeanLocal = (ProductosSessionBeanLocal) initialContext.lookup("proveedorEAR/ProductosSessionBean/local");
	}

	/**
	 * @see MateriaPrimaSessionBeanLocal#getDate()
	 */
	public Date getDate() {
		return materiaPrimaSessionBeanLocal.getDate();
	}

	/**
	 * @see ProductosSessionBeanLocal#getProducto(String)
	 */
	public ProductoVO getProducto(String codigo) {
		return productosSessionBeanLocal.getProducto(codigo);
	}

	/**
	 * @see ProductosSessionBeanLocal#deleteProducto(String)
	 */
	public void deleteProducto(String codigo) {
		productosSessionBeanLocal.deleteProducto(codigo);
	}

	/**
	 * @see ProductosSessionBeanLocal#createProducto(String, String, String,
	 *      String, String, float)
	 */
	public void createProducto(String codigo, String descripcion, String caracteristica, String marca, String origen, float precioUnitario) {
		productosSessionBeanLocal.createProducto(codigo, descripcion, caracteristica, marca, origen, precioUnitario);
	}

	/**
	 * @see ProductosSessionBeanLocal#getProductos()
	 */
	public Collection<ProductoVO> getProductos() {
		return productosSessionBeanLocal.getProductos();
	}

}
