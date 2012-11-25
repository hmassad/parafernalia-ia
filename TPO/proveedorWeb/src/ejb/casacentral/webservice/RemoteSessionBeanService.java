/**
 * RemoteSessionBeanService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ejb.casacentral.webservice;

public interface RemoteSessionBeanService extends javax.xml.rpc.Service {
    public java.lang.String getRemoteSessionBeanPortAddress();

    public ejb.casacentral.webservice.RemoteSessionBean getRemoteSessionBeanPort() throws javax.xml.rpc.ServiceException;

    public ejb.casacentral.webservice.RemoteSessionBean getRemoteSessionBeanPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
