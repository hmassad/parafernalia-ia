package servicios;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebServiceFacadeBeanServiceLocator serviceLocator = new WebServiceFacadeBeanServiceLocator();
		try {
			WebServiceFacadeBeanProxy proxy = new WebServiceFacadeBeanProxy();
			proxy.setEndpoint("http://127.0.0.1:8080/proveedorEAR-proveedorEJB/WebServiceFacadeBean");
			WebServiceFacadeBean port = serviceLocator.getWebServiceFacadeBeanPort();
			System.out.println(port.getListaPrecios());
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
