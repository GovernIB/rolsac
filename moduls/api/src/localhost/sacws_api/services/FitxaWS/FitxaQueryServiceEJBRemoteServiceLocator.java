/**
 * FitxaQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.FitxaWS;

public class FitxaQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.FitxaWS.FitxaQueryServiceEJBRemoteService {

    public FitxaQueryServiceEJBRemoteServiceLocator() {
    }


    public FitxaQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FitxaQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FitxaWS
    private java.lang.String FitxaWS_address = "https://localhost:8443/sacws-api/services/FitxaWS";

    public java.lang.String getFitxaWSAddress() {
        return FitxaWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FitxaWSWSDDServiceName = "FitxaWS";

    public java.lang.String getFitxaWSWSDDServiceName() {
        return FitxaWSWSDDServiceName;
    }

    public void setFitxaWSWSDDServiceName(java.lang.String name) {
        FitxaWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.FitxaWS.FitxaQueryServiceEJBRemote getFitxaWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FitxaWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFitxaWS(endpoint);
    }

    public localhost.sacws_api.services.FitxaWS.FitxaQueryServiceEJBRemote getFitxaWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.FitxaWS.FitxaWSSoapBindingStub _stub = new localhost.sacws_api.services.FitxaWS.FitxaWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getFitxaWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFitxaWSEndpointAddress(java.lang.String address) {
        FitxaWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.FitxaWS.FitxaQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.FitxaWS.FitxaWSSoapBindingStub _stub = new localhost.sacws_api.services.FitxaWS.FitxaWSSoapBindingStub(new java.net.URL(FitxaWS_address), this);
                _stub.setPortName(getFitxaWSWSDDServiceName());
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
        if ("FitxaWS".equals(inputPortName)) {
            return getFitxaWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/FitxaWS", "FitxaQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/FitxaWS", "FitxaWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FitxaWS".equals(portName)) {
            setFitxaWSEndpointAddress(address);
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
