package proveedor.beans;

import java.util.Hashtable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Session Bean implementation class PublicacionListaPreciosSessionBean
 */
@Stateless
@Local({ ListaPreciosSessionBeanLocal.class })
public class PublicacionListaPreciosSessionBean implements PublicacionListaPreciosSessionBeanRemote {

	InitialContext initialContext;
	ListaPreciosSessionBeanLocal listaPreciosSessionBeanLocal;

	public PublicacionListaPreciosSessionBean() throws NamingException {
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		// Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		initialContext = new InitialContext(props);

		listaPreciosSessionBeanLocal = (ListaPreciosSessionBeanLocal) initialContext.lookup("proveedorEAR/ListaPreciosSessionBean/local");
	}

	@Override
	public String getListaPrecios() {
		return null;
	}

}
