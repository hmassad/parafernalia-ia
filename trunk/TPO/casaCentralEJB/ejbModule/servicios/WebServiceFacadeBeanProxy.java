package servicios;

public class WebServiceFacadeBeanProxy implements servicios.WebServiceFacadeBean {
  private String _endpoint = null;
  private servicios.WebServiceFacadeBean webServiceFacadeBean = null;
  
  public WebServiceFacadeBeanProxy() {
    _initWebServiceFacadeBeanProxy();
  }
  
  public WebServiceFacadeBeanProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceFacadeBeanProxy();
  }
  
  private void _initWebServiceFacadeBeanProxy() {
    try {
      webServiceFacadeBean = (new servicios.WebServiceFacadeBeanServiceLocator()).getWebServiceFacadeBeanPort();
      if (webServiceFacadeBean != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceFacadeBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceFacadeBean)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceFacadeBean != null)
      ((javax.xml.rpc.Stub)webServiceFacadeBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public servicios.WebServiceFacadeBean getWebServiceFacadeBean() {
    if (webServiceFacadeBean == null)
      _initWebServiceFacadeBeanProxy();
    return webServiceFacadeBean;
  }
  
  public java.lang.String getListaPrecios() throws java.rmi.RemoteException{
    if (webServiceFacadeBean == null)
      _initWebServiceFacadeBeanProxy();
    return webServiceFacadeBean.getListaPrecios();
  }
  
  
}