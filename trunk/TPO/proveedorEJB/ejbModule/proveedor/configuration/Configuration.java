package proveedor.configuration;

import proveedor.vo.ProveedorVO;

public class Configuration {

	public final static String MateriaPrimaHost = "127.0.0.1";

	public final static String CasaCentralHost = "127.0.0.1";

	public final static String CasaCentralWebServiceLocation = "http://"
			+ CasaCentralHost
			+ ":8080/CasaEAR-casa-negocio/RemoteSessionBean";

	public final static ProveedorVO Proveedor = new ProveedorVO("6", "Grupo 6",
			"0810-666-6666", "Lima 717", "Ciudad Aut�noma de Buenos Aires",
			"Buenos Aires", "1000");
}
