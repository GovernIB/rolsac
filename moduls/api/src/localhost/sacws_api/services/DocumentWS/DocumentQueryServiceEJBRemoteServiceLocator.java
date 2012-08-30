/**
 * DocumentQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.DocumentWS;

public class DocumentQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.DocumentWS.DocumentQueryServiceEJBRemoteService {

    public DocumentQueryServiceEJBRemoteServiceLocator() {
    }


    public DocumentQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DocumentQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DocumentWS
    private java.lang.String DocumentWS_address = "https://localhost:8443/sacws-api/services/DocumentWS";

    public java.lang.String getDocumentWSAddress() {
        return DocumentWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DocumentWSWSDDServiceName = "DocumentWS";

    public java.lang.String getDocumentWSWSDDServiceName() {
        return DocumentWSWSDDServiceName;
    }

    public void setDocumentWSWSDDServiceName(java.lang.String name) {
        DocumentWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.DocumentWS.DocumentQueryServiceEJBRemote getDocumentWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DocumentWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDocumentWS(endpoint);
    }

    public localhost.sacws_api.services.DocumentWS.DocumentQueryServiceEJBRemote getDocumentWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.DocumentWS.DocumentWSSoapBindingStub _stub = new localhost.sacws_api.services.DocumentWS.DocumentWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getDocumentWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDocumentWSEndpointAddress(java.lang.String address) {
        DocumentWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.DocumentWS.DocumentQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.DocumentWS.DocumentWSSoapBindingStub _stub = new localhost.sacws_api.services.DocumentWS.DocumentWSSoapBindingStub(new java.net.URL(DocumentWS_address), this);
                _stub.setPortName(getDocumentWSWSDDServiceName());
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
        if ("DocumentWS".equals(inputPortName)) {
            return getDocumentWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/DocumentWS", "DocumentQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/DocumentWS", "DocumentWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DocumentWS".equals(portName)) {
            setDocumentWSEndpointAddress(address);
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
