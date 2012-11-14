package casaCentral.webServices;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NuevoRodamientoWebService extends Remote {

	public String nuevoRodamiento(String rodamientos) throws RemoteException;

}
