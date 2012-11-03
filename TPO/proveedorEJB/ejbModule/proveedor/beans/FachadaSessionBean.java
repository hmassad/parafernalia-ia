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
@Local({ ListaPreciosSessionBeanLocal.class, MateriaPrimaSessionBeanLocal.class })
public class FachadaSessionBean implements FachadaSessionBeanRemote, ListaPreciosSessionBeanLocal, MateriaPrimaSessionBeanLocal {

	InitialContext initialContext;

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
	}

	/**
	 * @see ListaPreciosSessionBeanLocal#getProducto(String)
	 */
	public ProductoVO getProducto(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see MateriaPrimaSessionBeanLocal#getDate()
	 */
	public Date getDate() {
		try {
			MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal = (MateriaPrimaSessionBeanLocal) initialContext
					.lookup("proveedorEAR/MateriaPrimaSessionBean/local");
			return materiaPrimaSessionBeanLocal.getDate();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @see ListaPreciosSessionBeanLocal#deleteProducto(String)
	 */
	public void deleteProducto(String codigo) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ListaPreciosSessionBeanLocal#createProducto(String, String, String,
	 *      String, String, float)
	 */
	public void createProducto(String codigo, String descripcion, String caracteristica, String marca, String origen, float precioUnitario) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ListaPreciosSessionBeanLocal#getProductos()
	 */
	public Collection<ProductoVO> getProductos() {
		// TODO Auto-generated method stub
		return null;
	}

}
