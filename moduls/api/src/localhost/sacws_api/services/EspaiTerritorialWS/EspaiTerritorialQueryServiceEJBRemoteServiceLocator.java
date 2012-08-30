/**
 * EspaiTerritorialQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.EspaiTerritorialWS;

public class EspaiTerritorialQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialQueryServiceEJBRemoteService {

    public EspaiTerritorialQueryServiceEJBRemoteServiceLocator() {
    }


    public EspaiTerritorialQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EspaiTerritorialQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EspaiTerritorialWS
    private java.lang.String EspaiTerritorialWS_address = "https://localhost:8443/sacws-api/services/EspaiTerritorialWS";

    public java.lang.String getEspaiTerritorialWSAddress() {
        return EspaiTerritorialWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EspaiTerritorialWSWSDDServiceName = "EspaiTerritorialWS";

    public java.lang.String getEspaiTerritorialWSWSDDServiceName() {
        return EspaiTerritorialWSWSDDServiceName;
    }

    public void setEspaiTerritorialWSWSDDServiceName(java.lang.String name) {
        EspaiTerritorialWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialQueryServiceEJBRemote getEspaiTerritorialWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EspaiTerritorialWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEspaiTerritorialWS(endpoint);
    }

    public localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialQueryServiceEJBRemote getEspaiTerritorialWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialWSSoapBindingStub _stub = new localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getEspaiTerritorialWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEspaiTerritorialWSEndpointAddress(java.lang.String address) {
        EspaiTerritorialWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialWSSoapBindingStub _stub = new localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialWSSoapBindingStub(new java.net.URL(EspaiTerritorialWS_address), this);
                _stub.setPortName(getEspaiTerritorialWSWSDDServiceName());
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
        if ("EspaiTerritorialWS".equals(inputPortName)) {
            return getEspaiTerritorialWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/EspaiTerritorialWS", "EspaiTerritorialQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/EspaiTerritorialWS", "EspaiTerritorialWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EspaiTerritorialWS".equals(portName)) {
            setEspaiTerritorialWSEndpointAddress(address);
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
