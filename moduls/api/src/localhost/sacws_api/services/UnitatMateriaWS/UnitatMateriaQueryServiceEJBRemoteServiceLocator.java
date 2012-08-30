/**
 * UnitatMateriaQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.UnitatMateriaWS;

public class UnitatMateriaQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaQueryServiceEJBRemoteService {

    public UnitatMateriaQueryServiceEJBRemoteServiceLocator() {
    }


    public UnitatMateriaQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UnitatMateriaQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UnitatMateriaWS
    private java.lang.String UnitatMateriaWS_address = "https://localhost:8443/sacws-api/services/UnitatMateriaWS";

    public java.lang.String getUnitatMateriaWSAddress() {
        return UnitatMateriaWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UnitatMateriaWSWSDDServiceName = "UnitatMateriaWS";

    public java.lang.String getUnitatMateriaWSWSDDServiceName() {
        return UnitatMateriaWSWSDDServiceName;
    }

    public void setUnitatMateriaWSWSDDServiceName(java.lang.String name) {
        UnitatMateriaWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaQueryServiceEJBRemote getUnitatMateriaWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UnitatMateriaWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUnitatMateriaWS(endpoint);
    }

    public localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaQueryServiceEJBRemote getUnitatMateriaWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaWSSoapBindingStub _stub = new localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getUnitatMateriaWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUnitatMateriaWSEndpointAddress(java.lang.String address) {
        UnitatMateriaWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaWSSoapBindingStub _stub = new localhost.sacws_api.services.UnitatMateriaWS.UnitatMateriaWSSoapBindingStub(new java.net.URL(UnitatMateriaWS_address), this);
                _stub.setPortName(getUnitatMateriaWSWSDDServiceName());
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
        if ("UnitatMateriaWS".equals(inputPortName)) {
            return getUnitatMateriaWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/UnitatMateriaWS", "UnitatMateriaQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/UnitatMateriaWS", "UnitatMateriaWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UnitatMateriaWS".equals(portName)) {
            setUnitatMateriaWSEndpointAddress(address);
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
