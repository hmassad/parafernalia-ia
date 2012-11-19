package WsServer;

import javax.ejb.Remote;

@Remote
public interface RemoteSessionBeanService {

    public java.lang.String preciosDeRodamientos(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String nuevoRodamiento(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String pruebaMetodo1() throws java.rmi.RemoteException;

}
