
package proveedor.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the proveedor.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetListaPrecios_QNAME = new QName("http://servicios/", "getListaPrecios");
    private final static QName _GetListaPreciosResponse_QNAME = new QName("http://servicios/", "getListaPreciosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: proveedor.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListaPreciosResponse }
     * 
     */
    public GetListaPreciosResponse createGetListaPreciosResponse() {
        return new GetListaPreciosResponse();
    }

    /**
     * Create an instance of {@link GetListaPrecios }
     * 
     */
    public GetListaPrecios createGetListaPrecios() {
        return new GetListaPrecios();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaPrecios }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios/", name = "getListaPrecios")
    public JAXBElement<GetListaPrecios> createGetListaPrecios(GetListaPrecios value) {
        return new JAXBElement<GetListaPrecios>(_GetListaPrecios_QNAME, GetListaPrecios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaPreciosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios/", name = "getListaPreciosResponse")
    public JAXBElement<GetListaPreciosResponse> createGetListaPreciosResponse(GetListaPreciosResponse value) {
        return new JAXBElement<GetListaPreciosResponse>(_GetListaPreciosResponse_QNAME, GetListaPreciosResponse.class, null, value);
    }

}
