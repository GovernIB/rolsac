/**
 * ExcepcioDocumentacioQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.excepcioDocumentacio.ws;

public class ExcepcioDocumentacioQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioQueryServiceEJBRemoteService {

    public ExcepcioDocumentacioQueryServiceEJBRemoteServiceLocator() {
    }


    public ExcepcioDocumentacioQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ExcepcioDocumentacioQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ExcepcioDocumentacioWS
    private java.lang.String ExcepcioDocumentacioWS_address = "https://localhost:8443/sacws-api/services/ExcepcioDocumentacioWS";

    public java.lang.String getExcepcioDocumentacioWSAddress() {
        return ExcepcioDocumentacioWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ExcepcioDocumentacioWSWSDDServiceName = "ExcepcioDocumentacioWS";

    public java.lang.String getExcepcioDocumentacioWSWSDDServiceName() {
        return ExcepcioDocumentacioWSWSDDServiceName;
    }

    public void setExcepcioDocumentacioWSWSDDServiceName(java.lang.String name) {
        ExcepcioDocumentacioWSWSDDServiceName = name;
    }

    public es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioQueryServiceEJBRemote getExcepcioDocumentacioWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ExcepcioDocumentacioWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getExcepcioDocumentacioWS(endpoint);
    }

    public es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioQueryServiceEJBRemote getExcepcioDocumentacioWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioWSSoapBindingStub _stub = new es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getExcepcioDocumentacioWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setExcepcioDocumentacioWSEndpointAddress(java.lang.String address) {
        ExcepcioDocumentacioWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioWSSoapBindingStub _stub = new es.caib.rolsac.api.v2.excepcioDocumentacio.ws.ExcepcioDocumentacioWSSoapBindingStub(new java.net.URL(ExcepcioDocumentacioWS_address), this);
                _stub.setPortName(getExcepcioDocumentacioWSWSDDServiceName());
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
        if ("ExcepcioDocumentacioWS".equals(inputPortName)) {
            return getExcepcioDocumentacioWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.excepcioDocumentacio.v2.api.rolsac.caib.es", "ExcepcioDocumentacioQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.excepcioDocumentacio.v2.api.rolsac.caib.es", "ExcepcioDocumentacioWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ExcepcioDocumentacioWS".equals(portName)) {
            setExcepcioDocumentacioWSEndpointAddress(address);
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
