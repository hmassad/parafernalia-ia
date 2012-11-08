package proveedorWeb.ejb;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import proveedor.beans.remote.FachadaSessionBeanRemote;

public class ProveedorClient {

	private ProveedorClient() {

	}

	private static FachadaSessionBeanRemote fachadaSessionBeanRemote;

	/**
	 * @throws NamingException
	 */
	public static FachadaSessionBeanRemote get() throws NamingException {
		if (fachadaSessionBeanRemote == null) {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			// Url completa de ubicacion del servidor de aplicaciones
			props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
			// Objeto del tipo InitialContext
			InitialContext initialContext = new InitialContext(props);

			fachadaSessionBeanRemote = (FachadaSessionBeanRemote) initialContext
					.lookup("proveedorEAR/FachadaSessionBean/remote");
		}
		return fachadaSessionBeanRemote;
	}
}
