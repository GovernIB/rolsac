/**
 * EdificiQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.EdificiWS;

public class EdificiQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.EdificiWS.EdificiQueryServiceEJBRemoteService {

    public EdificiQueryServiceEJBRemoteServiceLocator() {
    }


    public EdificiQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EdificiQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EdificiWS
    private java.lang.String EdificiWS_address = "https://localhost:8443/sacws-api/services/EdificiWS";

    public java.lang.String getEdificiWSAddress() {
        return EdificiWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EdificiWSWSDDServiceName = "EdificiWS";

    public java.lang.String getEdificiWSWSDDServiceName() {
        return EdificiWSWSDDServiceName;
    }

    public void setEdificiWSWSDDServiceName(java.lang.String name) {
        EdificiWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.EdificiWS.EdificiQueryServiceEJBRemote getEdificiWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EdificiWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEdificiWS(endpoint);
    }

    public localhost.sacws_api.services.EdificiWS.EdificiQueryServiceEJBRemote getEdificiWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.EdificiWS.EdificiWSSoapBindingStub _stub = new localhost.sacws_api.services.EdificiWS.EdificiWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getEdificiWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEdificiWSEndpointAddress(java.lang.String address) {
        EdificiWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.EdificiWS.EdificiQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.EdificiWS.EdificiWSSoapBindingStub _stub = new localhost.sacws_api.services.EdificiWS.EdificiWSSoapBindingStub(new java.net.URL(EdificiWS_address), this);
                _stub.setPortName(getEdificiWSWSDDServiceName());
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
        if ("EdificiWS".equals(inputPortName)) {
            return getEdificiWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/EdificiWS", "EdificiQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/EdificiWS", "EdificiWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EdificiWS".equals(portName)) {
            setEdificiWSEndpointAddress(address);
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
