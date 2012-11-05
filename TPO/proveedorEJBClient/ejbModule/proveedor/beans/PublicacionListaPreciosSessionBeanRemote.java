package proveedor.beans;

import javax.ejb.Remote;

@Remote
public interface PublicacionListaPreciosSessionBeanRemote {

	String getListaPrecios();
	
}
