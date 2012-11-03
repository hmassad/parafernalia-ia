package proveedorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proveedor.beans.FachadaSessionBeanRemote;
import proveedor.vo.ProductoVO;

/**
 * Servlet implementation class Index
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InitialContext initContext;

	/**
	 * @throws NamingException
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() throws NamingException {
		super();
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		// Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		// Objeto del tipo InitialContext
		initContext = new InitialContext(props);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		FachadaSessionBeanRemote fachada;
		
		try{
			fachada = (FachadaSessionBeanRemote) initContext.lookup("proveedorEAR/FachadaSessionBean/remote");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("no encontró el session bean");
			out.print(e.getStackTrace());
			return;
		}

		out.println(fachada.getDate().toString());

		fachada.createProducto("1-1", "1-2", "1-3", "1-4", "1-5", 16);
		fachada.createProducto("2-1", "2-2", "2-3", "2-4", "2-5", 26);
		fachada.createProducto("3-1", "3-2", "3-3", "3-4", "3-5", 36);
		fachada.createProducto("4-1", "4-2", "4-3", "4-4", "4-5", 46);
		
		for(ProductoVO p : fachada.getProductos()){
			out.println(p.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
