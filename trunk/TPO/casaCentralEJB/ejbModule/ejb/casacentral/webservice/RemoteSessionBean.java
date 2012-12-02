package ejb.casacentral.webservice;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ejb.casacentral.documentos.NvoProd;

@Stateless
@WebService
public class RemoteSessionBean implements RemoteSessionBeanService {

	public RemoteSessionBean() {
	}

	@WebMethod
	public String cotizarRodamiento(String xml) {
		return null;
	}

	@WebMethod
	public String nuevoRodamiento(String xml) {
		NvoProd nvoProd = NvoProd.deserialize(xml);
		System.out.println("RECIBIDO NUEVO PRODUCTO");
		System.out.println(nvoProd.toString());
		return xml;
	}
}
