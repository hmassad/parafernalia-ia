package materiaPrimaWeb.ejb;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import materiaPrima.beans.FachadaSessionBeanRemote;

public class MateriaPrimaClient {

	 private MateriaPrimaClient() {
	 }

	private static FachadaSessionBeanRemote fachadaSessionBeanRemote;

	/**
	 * @throws NamingException
	 */
	public static FachadaSessionBeanRemote get() throws NamingException {
		if (fachadaSessionBeanRemote == null) {
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
			InitialContext initialContext = new InitialContext(props);

			fachadaSessionBeanRemote = (FachadaSessionBeanRemote) initialContext.lookup("materiaPrimaEAR/FachadaSessionBean/remote");
		}
		return fachadaSessionBeanRemote;
	}
}
