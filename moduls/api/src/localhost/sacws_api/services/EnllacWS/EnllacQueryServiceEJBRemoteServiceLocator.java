/**
 * EnllacQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.EnllacWS;

public class EnllacQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.EnllacWS.EnllacQueryServiceEJBRemoteService {

    public EnllacQueryServiceEJBRemoteServiceLocator() {
    }


    public EnllacQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EnllacQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EnllacWS
    private java.lang.String EnllacWS_address = "https://localhost:8443/sacws-api/services/EnllacWS";

    public java.lang.String getEnllacWSAddress() {
        return EnllacWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EnllacWSWSDDServiceName = "EnllacWS";

    public java.lang.String getEnllacWSWSDDServiceName() {
        return EnllacWSWSDDServiceName;
    }

    public void setEnllacWSWSDDServiceName(java.lang.String name) {
        EnllacWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.EnllacWS.EnllacQueryServiceEJBRemote getEnllacWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EnllacWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEnllacWS(endpoint);
    }

    public localhost.sacws_api.services.EnllacWS.EnllacQueryServiceEJBRemote getEnllacWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.EnllacWS.EnllacWSSoapBindingStub _stub = new localhost.sacws_api.services.EnllacWS.EnllacWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getEnllacWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEnllacWSEndpointAddress(java.lang.String address) {
        EnllacWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.EnllacWS.EnllacQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.EnllacWS.EnllacWSSoapBindingStub _stub = new localhost.sacws_api.services.EnllacWS.EnllacWSSoapBindingStub(new java.net.URL(EnllacWS_address), this);
                _stub.setPortName(getEnllacWSWSDDServiceName());
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
        if ("EnllacWS".equals(inputPortName)) {
            return getEnllacWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/EnllacWS", "EnllacQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/EnllacWS", "EnllacWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EnllacWS".equals(portName)) {
            setEnllacWSEndpointAddress(address);
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
