/**
 * RolsacQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.RolsacWS;

public class RolsacQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.RolsacWS.RolsacQueryServiceEJBRemoteService {

    public RolsacQueryServiceEJBRemoteServiceLocator() {
    }


    public RolsacQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RolsacQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RolsacWS
    private java.lang.String RolsacWS_address = "https://localhost:8443/sacws-api/services/RolsacWS";

    public java.lang.String getRolsacWSAddress() {
        return RolsacWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RolsacWSWSDDServiceName = "RolsacWS";

    public java.lang.String getRolsacWSWSDDServiceName() {
        return RolsacWSWSDDServiceName;
    }

    public void setRolsacWSWSDDServiceName(java.lang.String name) {
        RolsacWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.RolsacWS.RolsacQueryServiceEJBRemote getRolsacWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RolsacWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRolsacWS(endpoint);
    }

    public localhost.sacws_api.services.RolsacWS.RolsacQueryServiceEJBRemote getRolsacWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.RolsacWS.RolsacWSSoapBindingStub _stub = new localhost.sacws_api.services.RolsacWS.RolsacWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getRolsacWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRolsacWSEndpointAddress(java.lang.String address) {
        RolsacWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.RolsacWS.RolsacQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.RolsacWS.RolsacWSSoapBindingStub _stub = new localhost.sacws_api.services.RolsacWS.RolsacWSSoapBindingStub(new java.net.URL(RolsacWS_address), this);
                _stub.setPortName(getRolsacWSWSDDServiceName());
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
        if ("RolsacWS".equals(inputPortName)) {
            return getRolsacWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "RolsacQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "RolsacWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RolsacWS".equals(portName)) {
            setRolsacWSEndpointAddress(address);
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
