package casaCentral.beans.remote;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import casaCentral.documentos.NvoProd;

/**
 * Session Bean implementation class PublicacionListaPreciosSessionBean
 */
@Stateless
@WebService(targetNamespace = "http://servicios/")
public class WebServiceFacadeBean implements WebServiceFacade {

	public WebServiceFacadeBean() {
	}

	@WebMethod
	public void notificarNuevoRodamiento(String xml) {

		NvoProd nvoProd = NvoProd.deserialize(xml);
		System.out.println();
		System.out.println();
		System.out.println("RECIBIDO NUEVO PRODUCTO");
		System.out.println();
		System.out.println(nvoProd.toString());

	}

}
