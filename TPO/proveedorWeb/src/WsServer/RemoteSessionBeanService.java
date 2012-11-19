/**
 * RemoteSessionBeanService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsServer;

public interface RemoteSessionBeanService extends javax.xml.rpc.Service {
    public java.lang.String getRemoteSessionBeanPortAddress();

    public WsServer.RemoteSessionBean getRemoteSessionBeanPort() throws javax.xml.rpc.ServiceException;

    public WsServer.RemoteSessionBean getRemoteSessionBeanPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
