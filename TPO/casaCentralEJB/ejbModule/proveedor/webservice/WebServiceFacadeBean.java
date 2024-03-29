
package proveedor.webservice;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1-b03-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "WebServiceFacadeBean", targetNamespace = "http://servicios/")
public interface WebServiceFacadeBean {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getListaPrecios", targetNamespace = "http://servicios/", className = "proveedor.webservice.GetListaPrecios")
    @ResponseWrapper(localName = "getListaPreciosResponse", targetNamespace = "http://servicios/", className = "proveedor.webservice.GetListaPreciosResponse")
    public String getListaPrecios();

}
