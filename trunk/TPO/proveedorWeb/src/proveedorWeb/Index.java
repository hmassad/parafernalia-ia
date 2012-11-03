package proveedorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proveedor.beans.FachadaSessionBeanRemote;

/**
 * Servlet implementation class Index
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		String message = "no funco man!!";
		try {
			InitialContext initContext;

			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			// Url completa de ubicacion del servidor de aplicaciones
			props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
			// Objeto del tipo InitialContext
			initContext = new InitialContext(props);

			FachadaSessionBeanRemote fachada = (FachadaSessionBeanRemote) initContext.lookup("proveedorEAR/FachadaSessionBean/remote");
			message = fachada.getDate().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
