package proveedor.configuration;

import proveedor.vo.ProveedorVO;

public class Configuration {

	public final static String MateriaPrimaHost = "192.168.0.2";

	public final static ProveedorVO Proveedor = new ProveedorVO("06", "Grupo 6",
			"0810-666-6666", "Lima 717", "Ciudad Autónoma de Buenos Aires",
			"Buenos Aires", "1000");

	// Casa Central Local
	public final static String CasaCentralHost = "192.168.0.2";

	public final static String CasaCentralWebServiceLocation = "http://"
			+ CasaCentralHost
			+ ":8080/casaCentralEAR-casaCentralEJB/RemoteSessionBean";

	// Casa Central 01
	// public final static String CasaCentralHost = "172.16.176.29";
	//
	// public final static String CasaCentralWebServiceLocation = "http://"
	// + CasaCentralHost + ":8080/CasaEar-casa-negocio/RemoteSessionBean";

	// Casa Central 05
	// public final static String CasaCentralHost = "172.16.176.34";
	//
	// public final static String CasaCentralWebServiceLocation = "http://"
	// + CasaCentralHost + ":8080/TPOEAR-TPO/RemoteSessionBean";
}
