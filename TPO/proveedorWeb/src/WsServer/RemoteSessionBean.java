/**
 * RemoteSessionBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsServer;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RemoteSessionBean extends java.rmi.Remote {
	@WebMethod
	public java.lang.String preciosDeRodamientos(java.lang.String arg0)
			throws java.rmi.RemoteException;

	@WebMethod
	public java.lang.String nuevoRodamiento(java.lang.String arg0)
			throws java.rmi.RemoteException;

	@WebMethod
	public java.lang.String pruebaMetodo1() throws java.rmi.RemoteException;
}
