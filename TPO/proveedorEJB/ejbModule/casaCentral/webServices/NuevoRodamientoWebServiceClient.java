package casaCentral.webServices;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import proveedor.configuration.Configuration;
import proveedor.documentos.NvoProd;
import proveedor.vo.ProductoVO;

public class NuevoRodamientoWebServiceClient {

	public void notificarNuevoProducto(ProductoVO productoVO)
			throws MalformedURLException, ServiceException, RemoteException {

		NvoProd nvoProd = new NvoProd(productoVO.getCodigo(),
				productoVO.getCaracteristica(), productoVO.getMarca(),
				productoVO.getOrigen(), productoVO.getTipo(),
				productoVO.getCodigo());
		String xml = nvoProd.serialize();

		URL url = new URL(Configuration.NuevoRodamientoWebServiceAddress
				+ "?wsdl");

		QName qname = new QName("http://webservice.casacentral.ejb",
				"RemoteSessionBean");

		ServiceFactory factory = ServiceFactory.newInstance();
		Service service = factory.createService(url, qname);

		NuevoRodamientoWebService endpoint = (NuevoRodamientoWebService) service
				.getPort(NuevoRodamientoWebService.class);

		endpoint.nuevoRodamiento(xml);
	}
}
