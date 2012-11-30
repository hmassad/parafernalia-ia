
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
    private final static QName _PruebaMetodo1Response_QNAME = new QName("http://webservice.casacentral.ejb/", "pruebaMetodo1Response");
    private final static QName _PruebaMetodo1_QNAME = new QName("http://webservice.casacentral.ejb/", "pruebaMetodo1");
    private final static QName _NuevoRodamiento_QNAME = new QName("http://webservice.casacentral.ejb/", "nuevoRodamiento");
    private final static QName _PreciosDeRodamientos_QNAME = new QName("http://webservice.casacentral.ejb/", "preciosDeRodamientos");
    private final static QName _PreciosDeRodamientosResponse_QNAME = new QName("http://webservice.casacentral.ejb/", "preciosDeRodamientosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: casaCentral.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PruebaMetodo1 }
     * 
     */
    public PruebaMetodo1 createPruebaMetodo1() {
        return new PruebaMetodo1();
    }

    /**
     * Create an instance of {@link PreciosDeRodamientosResponse }
     * 
     */
    public PreciosDeRodamientosResponse createPreciosDeRodamientosResponse() {
        return new PreciosDeRodamientosResponse();
    }

    /**
     * Create an instance of {@link NuevoRodamientoResponse }
     * 
     */
    public NuevoRodamientoResponse createNuevoRodamientoResponse() {
        return new NuevoRodamientoResponse();
    }

    /**
     * Create an instance of {@link PruebaMetodo1Response }
     * 
     */
    public PruebaMetodo1Response createPruebaMetodo1Response() {
        return new PruebaMetodo1Response();
    }

    /**
     * Create an instance of {@link PreciosDeRodamientos }
     * 
     */
    public PreciosDeRodamientos createPreciosDeRodamientos() {
        return new PreciosDeRodamientos();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PruebaMetodo1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "pruebaMetodo1Response")
    public JAXBElement<PruebaMetodo1Response> createPruebaMetodo1Response(PruebaMetodo1Response value) {
        return new JAXBElement<PruebaMetodo1Response>(_PruebaMetodo1Response_QNAME, PruebaMetodo1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PruebaMetodo1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "pruebaMetodo1")
    public JAXBElement<PruebaMetodo1> createPruebaMetodo1(PruebaMetodo1 value) {
        return new JAXBElement<PruebaMetodo1>(_PruebaMetodo1_QNAME, PruebaMetodo1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NuevoRodamiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "nuevoRodamiento")
    public JAXBElement<NuevoRodamiento> createNuevoRodamiento(NuevoRodamiento value) {
        return new JAXBElement<NuevoRodamiento>(_NuevoRodamiento_QNAME, NuevoRodamiento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreciosDeRodamientos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "preciosDeRodamientos")
    public JAXBElement<PreciosDeRodamientos> createPreciosDeRodamientos(PreciosDeRodamientos value) {
        return new JAXBElement<PreciosDeRodamientos>(_PreciosDeRodamientos_QNAME, PreciosDeRodamientos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreciosDeRodamientosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.casacentral.ejb/", name = "preciosDeRodamientosResponse")
    public JAXBElement<PreciosDeRodamientosResponse> createPreciosDeRodamientosResponse(PreciosDeRodamientosResponse value) {
        return new JAXBElement<PreciosDeRodamientosResponse>(_PreciosDeRodamientosResponse_QNAME, PreciosDeRodamientosResponse.class, null, value);
    }

}
