/**
 * MateriaQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.MateriaWS;

public class MateriaQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.MateriaWS.MateriaQueryServiceEJBRemoteService {

    public MateriaQueryServiceEJBRemoteServiceLocator() {
    }


    public MateriaQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MateriaQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MateriaWS
    private java.lang.String MateriaWS_address = "https://localhost:8443/sacws-api/services/MateriaWS";

    public java.lang.String getMateriaWSAddress() {
        return MateriaWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MateriaWSWSDDServiceName = "MateriaWS";

    public java.lang.String getMateriaWSWSDDServiceName() {
        return MateriaWSWSDDServiceName;
    }

    public void setMateriaWSWSDDServiceName(java.lang.String name) {
        MateriaWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.MateriaWS.MateriaQueryServiceEJBRemote getMateriaWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MateriaWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMateriaWS(endpoint);
    }

    public localhost.sacws_api.services.MateriaWS.MateriaQueryServiceEJBRemote getMateriaWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.MateriaWS.MateriaWSSoapBindingStub _stub = new localhost.sacws_api.services.MateriaWS.MateriaWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getMateriaWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMateriaWSEndpointAddress(java.lang.String address) {
        MateriaWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.MateriaWS.MateriaQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.MateriaWS.MateriaWSSoapBindingStub _stub = new localhost.sacws_api.services.MateriaWS.MateriaWSSoapBindingStub(new java.net.URL(MateriaWS_address), this);
                _stub.setPortName(getMateriaWSWSDDServiceName());
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
        if ("MateriaWS".equals(inputPortName)) {
            return getMateriaWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/MateriaWS", "MateriaQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/MateriaWS", "MateriaWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MateriaWS".equals(portName)) {
            setMateriaWSEndpointAddress(address);
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
