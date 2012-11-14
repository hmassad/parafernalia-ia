package proveedor.configuration;

import proveedor.vo.ProveedorVO;

public class Configuration {

	public final static String MateriaPrimaHost = "127.0.0.1";

	public final static String CasaCentralHost = "127.0.0.1";

	public final static String NuevoRodamientoWebServiceAddress = "http://"
			+ CasaCentralHost
			+ ":8080/casa-central-presentacion/services/RemoteSessionBean";

	public final static ProveedorVO Proveedor = new ProveedorVO("6", "Grupo 6",
			"0810-666-6666", "Lima 717", "Ciudad Autónoma de Buenos Aires",
			"Buenos Aires", "1000");
}
