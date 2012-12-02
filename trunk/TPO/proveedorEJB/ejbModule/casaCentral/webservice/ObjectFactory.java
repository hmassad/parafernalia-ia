
package casaCentral.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the casaCentral.webservice package. 
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

    private final static QName _NuevoRodamientoResponse_QNAME = new QName("http://webservice.casacentral.ejb/", "nuevoRodamientoResponse");
    private final static QName _CotizarRodamiento_QNAME = new QName("http://webservice.casacentral.ejb/", "cotizarRodamiento");
    private final static QName _CotizarRodamientoResponse_QNAME = new QName("http://webservice.casacentral.ejb/", "cotizarRodamientoResponse");
    private final static QName _NuevoRodamiento_QNAME = new QName("http://webservice.casacentral.ejb/", "nuevoRodamiento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: casaCentral.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NuevoRodamientoResponse }
     * 
     */
    public NuevoRodamientoResponse createNuevoRodamientoResponse() {
        return new NuevoRodamientoResponse();
    }

    /**
     * Create an instance of {@link CotizarRodamiento }
     * 
     */
    public CotizarRodamiento createCotizarRodamiento() {
        return new CotizarRodamiento();
    }

    /**
     * Create an instance of {@link CotizarRodamientoResponse }
     * 
     */
    public CotizarRodamientoResponse createCotizarRodamientoResponse() {
        return new CotizarRodamientoResponse();
    }

    /**
     * Create an instance of {@link NuevoRodamiento }
     * 
     */
    public NuevoRodamiento createNuevoRodamiento() {
        return new NuevoRodamiento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NuevoRodamientoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "nuevoRodamientoResponse")
    public JAXBElement<NuevoRodamientoResponse> createNuevoRodamientoResponse(NuevoRodamientoResponse value) {
        return new JAXBElement<NuevoRodamientoResponse>(_NuevoRodamientoResponse_QNAME, NuevoRodamientoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CotizarRodamiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "cotizarRodamiento")
    public JAXBElement<CotizarRodamiento> createCotizarRodamiento(CotizarRodamiento value) {
        return new JAXBElement<CotizarRodamiento>(_CotizarRodamiento_QNAME, CotizarRodamiento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CotizarRodamientoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "cotizarRodamientoResponse")
    public JAXBElement<CotizarRodamientoResponse> createCotizarRodamientoResponse(CotizarRodamientoResponse value) {
        return new JAXBElement<CotizarRodamientoResponse>(_CotizarRodamientoResponse_QNAME, CotizarRodamientoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NuevoRodamiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "nuevoRodamiento")
    public JAXBElement<NuevoRodamiento> createNuevoRodamiento(NuevoRodamiento value) {
        return new JAXBElement<NuevoRodamiento>(_NuevoRodamiento_QNAME, NuevoRodamiento.class, null, value);
    }

}
