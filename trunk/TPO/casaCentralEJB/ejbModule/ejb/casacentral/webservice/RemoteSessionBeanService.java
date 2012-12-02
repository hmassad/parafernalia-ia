package ejb.casacentral.webservice;

import javax.ejb.Remote;

@Remote
public interface RemoteSessionBeanService {

	public String cotizarRodamiento(String xml);

	public String nuevoRodamiento(String xml);

}
