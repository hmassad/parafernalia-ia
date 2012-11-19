package WsServer;

public class RemoteSessionBeanProxy implements WsServer.RemoteSessionBean {
  private String _endpoint = null;
  private WsServer.RemoteSessionBean remoteSessionBean = null;
  
  public RemoteSessionBeanProxy() {
    _initRemoteSessionBeanProxy();
  }
  
  public RemoteSessionBeanProxy(String endpoint) {
    _endpoint = endpoint;
    _initRemoteSessionBeanProxy();
  }
  
  private void _initRemoteSessionBeanProxy() {
    try {
      remoteSessionBean = (new WsServer.RemoteSessionBeanServiceLocator()).getRemoteSessionBeanPort();
      if (remoteSessionBean != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)remoteSessionBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)remoteSessionBean)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (remoteSessionBean != null)
      ((javax.xml.rpc.Stub)remoteSessionBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WsServer.RemoteSessionBean getRemoteSessionBean() {
    if (remoteSessionBean == null)
      _initRemoteSessionBeanProxy();
    return remoteSessionBean;
  }
  
  public java.lang.String preciosDeRodamientos(java.lang.String arg0) throws java.rmi.RemoteException{
    if (remoteSessionBean == null)
      _initRemoteSessionBeanProxy();
    return remoteSessionBean.preciosDeRodamientos(arg0);
  }
  
  public java.lang.String nuevoRodamiento(java.lang.String arg0) throws java.rmi.RemoteException{
    if (remoteSessionBean == null)
      _initRemoteSessionBeanProxy();
    return remoteSessionBean.nuevoRodamiento(arg0);
  }
  
  public java.lang.String pruebaMetodo1() throws java.rmi.RemoteException{
    if (remoteSessionBean == null)
      _initRemoteSessionBeanProxy();
    return remoteSessionBean.pruebaMetodo1();
  }
  
  
}