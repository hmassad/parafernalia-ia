package casaCentral.beans.remote;

import javax.ejb.Remote;

@Remote
public interface WebServiceFacade {

	void notificarNuevoRodamiento(String xml);

}
