package proveedor.beans.remote;

import javax.ejb.Remote;

@Remote
public interface WebServiceFacade {

	String getListaPrecios();

}
