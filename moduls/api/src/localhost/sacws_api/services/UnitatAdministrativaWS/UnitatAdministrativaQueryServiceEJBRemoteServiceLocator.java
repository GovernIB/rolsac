/**
 * UnitatAdministrativaQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.UnitatAdministrativaWS;

public class UnitatAdministrativaQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaQueryServiceEJBRemoteService {

    public UnitatAdministrativaQueryServiceEJBRemoteServiceLocator() {
    }


    public UnitatAdministrativaQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UnitatAdministrativaQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UnitatAdministrativaWS
    private java.lang.String UnitatAdministrativaWS_address = "https://localhost:8443/sacws-api/services/UnitatAdministrativaWS";

    public java.lang.String getUnitatAdministrativaWSAddress() {
        return UnitatAdministrativaWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UnitatAdministrativaWSWSDDServiceName = "UnitatAdministrativaWS";

    public java.lang.String getUnitatAdministrativaWSWSDDServiceName() {
        return UnitatAdministrativaWSWSDDServiceName;
    }

    public void setUnitatAdministrativaWSWSDDServiceName(java.lang.String name) {
        UnitatAdministrativaWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaQueryServiceEJBRemote getUnitatAdministrativaWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UnitatAdministrativaWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUnitatAdministrativaWS(endpoint);
    }

    public localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaQueryServiceEJBRemote getUnitatAdministrativaWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaWSSoapBindingStub _stub = new localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getUnitatAdministrativaWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUnitatAdministrativaWSEndpointAddress(java.lang.String address) {
        UnitatAdministrativaWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaWSSoapBindingStub _stub = new localhost.sacws_api.services.UnitatAdministrativaWS.UnitatAdministrativaWSSoapBindingStub(new java.net.URL(UnitatAdministrativaWS_address), this);
                _stub.setPortName(getUnitatAdministrativaWSWSDDServiceName());
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
        if ("UnitatAdministrativaWS".equals(inputPortName)) {
            return getUnitatAdministrativaWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/UnitatAdministrativaWS", "UnitatAdministrativaQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/UnitatAdministrativaWS", "UnitatAdministrativaWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UnitatAdministrativaWS".equals(portName)) {
            setUnitatAdministrativaWSEndpointAddress(address);
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
