package proveedor.beans.remote;

import java.rmi.RemoteException;

import servicios.WebServiceFacadeBeanProxy;

public class WebServiceFacadeBeanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// URL url;
		// try {
		// url = new URL(
		// "http://127.0.0.1:8080/proveedorEAR-proveedorEJB/WebServiceFacadeBean?wsdl");
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// return;
		// }
		//
		// QName qname = new
		// QName("http://127.0.0.1:8080/proveedorEAR-proveedorEJB/",
		// "WebServiceFacadeBean");
		//
		// ServiceFactory factory;
		// try {
		// factory = ServiceFactory.newInstance();
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// return;
		// }
		//
		// Service service;
		// try {
		// service = factory.createService(url, qname);
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// return;
		// }
		//
		// WebServiceFacade endpoint;
		// try {
		// endpoint = (WebServiceFacade) service
		// .getPort(WebServiceFacade.class);
		// } catch (ServiceException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return;
		// }
		//
		// System.out.println(endpoint.getListaPrecios());

		WebServiceFacadeBeanProxy proxy = new WebServiceFacadeBeanProxy("http://127.0.0.1:8080/proveedorEAR-proveedorEJB/WebServiceFacadeBean?wsdl");
		try {
			System.out.println(proxy.getListaPrecios());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
