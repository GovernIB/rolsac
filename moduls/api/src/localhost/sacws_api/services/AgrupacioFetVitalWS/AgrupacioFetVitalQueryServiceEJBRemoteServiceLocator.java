/**
 * AgrupacioFetVitalQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.AgrupacioFetVitalWS;

public class AgrupacioFetVitalQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalQueryServiceEJBRemoteService {

    public AgrupacioFetVitalQueryServiceEJBRemoteServiceLocator() {
    }


    public AgrupacioFetVitalQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AgrupacioFetVitalQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AgrupacioFetVitalWS
    private java.lang.String AgrupacioFetVitalWS_address = "https://localhost:8443/sacws-api/services/AgrupacioFetVitalWS";

    public java.lang.String getAgrupacioFetVitalWSAddress() {
        return AgrupacioFetVitalWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AgrupacioFetVitalWSWSDDServiceName = "AgrupacioFetVitalWS";

    public java.lang.String getAgrupacioFetVitalWSWSDDServiceName() {
        return AgrupacioFetVitalWSWSDDServiceName;
    }

    public void setAgrupacioFetVitalWSWSDDServiceName(java.lang.String name) {
        AgrupacioFetVitalWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalQueryServiceEJBRemote getAgrupacioFetVitalWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AgrupacioFetVitalWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAgrupacioFetVitalWS(endpoint);
    }

    public localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalQueryServiceEJBRemote getAgrupacioFetVitalWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalWSSoapBindingStub _stub = new localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getAgrupacioFetVitalWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAgrupacioFetVitalWSEndpointAddress(java.lang.String address) {
        AgrupacioFetVitalWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalWSSoapBindingStub _stub = new localhost.sacws_api.services.AgrupacioFetVitalWS.AgrupacioFetVitalWSSoapBindingStub(new java.net.URL(AgrupacioFetVitalWS_address), this);
                _stub.setPortName(getAgrupacioFetVitalWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AgrupacioFetVitalWS".equals(inputPortName)) {
            return getAgrupacioFetVitalWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/AgrupacioFetVitalWS", "AgrupacioFetVitalQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/AgrupacioFetVitalWS", "AgrupacioFetVitalWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AgrupacioFetVitalWS".equals(portName)) {
            setAgrupacioFetVitalWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
