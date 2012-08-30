/**
 * ProcedimentQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.ProcedimentWS;

public class ProcedimentQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.ProcedimentWS.ProcedimentQueryServiceEJBRemoteService {

    public ProcedimentQueryServiceEJBRemoteServiceLocator() {
    }


    public ProcedimentQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProcedimentQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProcedimentWS
    private java.lang.String ProcedimentWS_address = "https://localhost:8443/sacws-api/services/ProcedimentWS";

    public java.lang.String getProcedimentWSAddress() {
        return ProcedimentWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProcedimentWSWSDDServiceName = "ProcedimentWS";

    public java.lang.String getProcedimentWSWSDDServiceName() {
        return ProcedimentWSWSDDServiceName;
    }

    public void setProcedimentWSWSDDServiceName(java.lang.String name) {
        ProcedimentWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.ProcedimentWS.ProcedimentQueryServiceEJBRemote getProcedimentWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProcedimentWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProcedimentWS(endpoint);
    }

    public localhost.sacws_api.services.ProcedimentWS.ProcedimentQueryServiceEJBRemote getProcedimentWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.ProcedimentWS.ProcedimentWSSoapBindingStub _stub = new localhost.sacws_api.services.ProcedimentWS.ProcedimentWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getProcedimentWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProcedimentWSEndpointAddress(java.lang.String address) {
        ProcedimentWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.ProcedimentWS.ProcedimentQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.ProcedimentWS.ProcedimentWSSoapBindingStub _stub = new localhost.sacws_api.services.ProcedimentWS.ProcedimentWSSoapBindingStub(new java.net.URL(ProcedimentWS_address), this);
                _stub.setPortName(getProcedimentWSWSDDServiceName());
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
        if ("ProcedimentWS".equals(inputPortName)) {
            return getProcedimentWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/ProcedimentWS", "ProcedimentQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/ProcedimentWS", "ProcedimentWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProcedimentWS".equals(portName)) {
            setProcedimentWSEndpointAddress(address);
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
