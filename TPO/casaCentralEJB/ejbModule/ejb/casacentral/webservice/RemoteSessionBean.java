package ejb.casacentral.webservice;

import java.rmi.RemoteException;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import proveedor.documentos.NvoProd;

@Stateless
@WebService
public class RemoteSessionBean implements RemoteSessionBeanService {

	public RemoteSessionBean() {
	}

	@WebMethod
	public String preciosDeRodamientos(String arg0) throws RemoteException {
		return arg0;
	}

	@WebMethod
	public String nuevoRodamiento(String arg0) throws RemoteException {
		NvoProd nvoProd = NvoProd.deserialize(arg0);
		System.out.println("RECIBIDO NUEVO PRODUCTO");
		System.out.println(nvoProd.toString());
		return arg0;
	}

	@WebMethod
	public String pruebaMetodo1() throws RemoteException {
		return "test";
	}

}
