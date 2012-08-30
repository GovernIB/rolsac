/**
 * PerfilQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.PerfilWS;

public class PerfilQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.PerfilWS.PerfilQueryServiceEJBRemoteService {

    public PerfilQueryServiceEJBRemoteServiceLocator() {
    }


    public PerfilQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PerfilQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PerfilWS
    private java.lang.String PerfilWS_address = "https://localhost:8443/sacws-api/services/PerfilWS";

    public java.lang.String getPerfilWSAddress() {
        return PerfilWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PerfilWSWSDDServiceName = "PerfilWS";

    public java.lang.String getPerfilWSWSDDServiceName() {
        return PerfilWSWSDDServiceName;
    }

    public void setPerfilWSWSDDServiceName(java.lang.String name) {
        PerfilWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.PerfilWS.PerfilQueryServiceEJBRemote getPerfilWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PerfilWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPerfilWS(endpoint);
    }

    public localhost.sacws_api.services.PerfilWS.PerfilQueryServiceEJBRemote getPerfilWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.PerfilWS.PerfilWSSoapBindingStub _stub = new localhost.sacws_api.services.PerfilWS.PerfilWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getPerfilWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPerfilWSEndpointAddress(java.lang.String address) {
        PerfilWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.PerfilWS.PerfilQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.PerfilWS.PerfilWSSoapBindingStub _stub = new localhost.sacws_api.services.PerfilWS.PerfilWSSoapBindingStub(new java.net.URL(PerfilWS_address), this);
                _stub.setPortName(getPerfilWSWSDDServiceName());
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
        if ("PerfilWS".equals(inputPortName)) {
            return getPerfilWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/PerfilWS", "PerfilQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/PerfilWS", "PerfilWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PerfilWS".equals(portName)) {
            setPerfilWSEndpointAddress(address);
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
