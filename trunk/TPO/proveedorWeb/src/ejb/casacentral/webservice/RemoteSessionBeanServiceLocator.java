/**
 * RemoteSessionBeanServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ejb.casacentral.webservice;

@SuppressWarnings("serial")
public class RemoteSessionBeanServiceLocator extends
		org.apache.axis.client.Service implements
		ejb.casacentral.webservice.RemoteSessionBeanService {

	public RemoteSessionBeanServiceLocator() {
	}

	public RemoteSessionBeanServiceLocator(
			org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public RemoteSessionBeanServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for RemoteSessionBeanPort
	private java.lang.String RemoteSessionBeanPort_address = "http://Nitzap-PC:8080/CasaEar-casa-negocio/RemoteSessionBean";

	public java.lang.String getRemoteSessionBeanPortAddress() {
		return RemoteSessionBeanPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String RemoteSessionBeanPortWSDDServiceName = "RemoteSessionBeanPort";

	public java.lang.String getRemoteSessionBeanPortWSDDServiceName() {
		return RemoteSessionBeanPortWSDDServiceName;
	}

	public void setRemoteSessionBeanPortWSDDServiceName(java.lang.String name) {
		RemoteSessionBeanPortWSDDServiceName = name;
	}

	public ejb.casacentral.webservice.RemoteSessionBean getRemoteSessionBeanPort()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(RemoteSessionBeanPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getRemoteSessionBeanPort(endpoint);
	}

	public ejb.casacentral.webservice.RemoteSessionBean getRemoteSessionBeanPort(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			ejb.casacentral.webservice.RemoteSessionBeanBindingStub _stub = new ejb.casacentral.webservice.RemoteSessionBeanBindingStub(
					portAddress, this);
			_stub.setPortName(getRemoteSessionBeanPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setRemoteSessionBeanPortEndpointAddress(java.lang.String address) {
		RemoteSessionBeanPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@SuppressWarnings({ "rawtypes" })
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (ejb.casacentral.webservice.RemoteSessionBean.class
					.isAssignableFrom(serviceEndpointInterface)) {
				ejb.casacentral.webservice.RemoteSessionBeanBindingStub _stub = new ejb.casacentral.webservice.RemoteSessionBeanBindingStub(
						new java.net.URL(RemoteSessionBeanPort_address), this);
				_stub.setPortName(getRemoteSessionBeanPortWSDDServiceName());
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
	@SuppressWarnings({ "rawtypes" })
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("RemoteSessionBeanPort".equals(inputPortName)) {
			return getRemoteSessionBeanPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName(
				"http://webservice.casacentral.ejb/",
				"RemoteSessionBeanService");
	}

	@SuppressWarnings({ "rawtypes" })
	private java.util.HashSet ports = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName(
					"http://webservice.casacentral.ejb/",
					"RemoteSessionBeanPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("RemoteSessionBeanPort".equals(portName)) {
			setRemoteSessionBeanPortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(
					" Cannot set Endpoint Address for Unknown Port" + portName);
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
