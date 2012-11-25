package ejb.casacentral.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import proveedor.documentos.NvoProd;
import proveedor.vo.ProductoVO;

public class CasaCentralClient {

	private CasaCentralClient() {
	}

	private static RemoteSessionBean remoteSessionBean;

	private static RemoteSessionBean get() throws MalformedURLException {

		if (remoteSessionBean == null) {
			Service service = Service
					.create(new URL(
							proveedor.configuration.Configuration.CasaCentralWebServiceLocation
									+ "?wsdl"), new QName("http://webservice.casacentral.ejb/",
							"RemoteSessionBeanService"));
			remoteSessionBean = service.getPort(RemoteSessionBean.class);
		}
		return remoteSessionBean;
	}

	public static void nuevoRodamiento(ProductoVO productoVO)
			throws RemoteException, MalformedURLException {
		NvoProd nvoProd = new NvoProd(productoVO.getCodigo(),
				productoVO.getCaracteristica(), productoVO.getMarca(),
				productoVO.getOrigen(), productoVO.getTipo(),
				"6");
		String xml = nvoProd.serialize();

		get().nuevoRodamiento(xml);
	}

}
