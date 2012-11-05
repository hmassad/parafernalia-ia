package materiaPrima.beans;

import java.util.Collection;
import java.util.Hashtable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import materiaPrima.vo.PedidoMateriaPrimaVO;

/**
 * Session Bean implementation class FachadaSessionBean
 */
@Stateless
@Local({ MateriaPrimaSessionBeanLocal.class })
public class FachadaSessionBean implements FachadaSessionBeanRemote {

	InitialContext initialContext;
	MateriaPrimaSessionBeanLocal materiaPrimaSessionBeanLocal;

	/**
	 * Default constructor.
	 */
	public FachadaSessionBean() throws NamingException {
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		// Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		initialContext = new InitialContext(props);

		materiaPrimaSessionBeanLocal = (MateriaPrimaSessionBeanLocal) initialContext.lookup("materiaPrimaEAR/MateriaPrimaSessionBean/local");
	}

	public Collection<PedidoMateriaPrimaVO> getPedidos() {
		return materiaPrimaSessionBeanLocal.getPedidos();
	}

}
