/**
 * WebServiceFacadeBeanServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package servicios;

@SuppressWarnings("serial")
public class WebServiceFacadeBeanServiceLocator extends
		org.apache.axis.client.Service implements
		servicios.WebServiceFacadeBeanService {

	public WebServiceFacadeBeanServiceLocator() {
	}

	public WebServiceFacadeBeanServiceLocator(
			org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public WebServiceFacadeBeanServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WebServiceFacadeBeanPort
	private java.lang.String WebServiceFacadeBeanPort_address = "http://127.0.0.1:8080/casaCentralEAR-casaCentralEJB/WebServiceFacadeBean";

	public java.lang.String getWebServiceFacadeBeanPortAddress() {
		return WebServiceFacadeBeanPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String WebServiceFacadeBeanPortWSDDServiceName = "WebServiceFacadeBeanPort";

	public java.lang.String getWebServiceFacadeBeanPortWSDDServiceName() {
		return WebServiceFacadeBeanPortWSDDServiceName;
	}

	public void setWebServiceFacadeBeanPortWSDDServiceName(java.lang.String name) {
		WebServiceFacadeBeanPortWSDDServiceName = name;
	}

	public servicios.WebServiceFacadeBean getWebServiceFacadeBeanPort()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WebServiceFacadeBeanPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWebServiceFacadeBeanPort(endpoint);
	}

	public servicios.WebServiceFacadeBean getWebServiceFacadeBeanPort(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			servicios.WebServiceFacadeBeanBindingStub _stub = new servicios.WebServiceFacadeBeanBindingStub(
					portAddress, this);
			_stub.setPortName(getWebServiceFacadeBeanPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWebServiceFacadeBeanPortEndpointAddress(
			java.lang.String address) {
		WebServiceFacadeBeanPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (servicios.WebServiceFacadeBean.class
					.isAssignableFrom(serviceEndpointInterface)) {
				servicios.WebServiceFacadeBeanBindingStub _stub = new servicios.WebServiceFacadeBeanBindingStub(
						new java.net.URL(WebServiceFacadeBeanPort_address),
						this);
				_stub.setPortName(getWebServiceFacadeBeanPortWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException(
				"There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null"
								: serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("WebServiceFacadeBeanPort".equals(inputPortName)) {
			return getWebServiceFacadeBeanPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://servicios/",
				"WebServiceFacadeBeanService");
	}

	@SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://servicios/",
					"WebServiceFacadeBeanPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("WebServiceFacadeBeanPort".equals(portName)) {
			setWebServiceFacadeBeanPortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(
					"Cannot set Endpoint Address for Unknown Port " + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
