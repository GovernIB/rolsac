/**
 * FormulariQueryServiceEJBRemoteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.FormulariWS;

public class FormulariQueryServiceEJBRemoteServiceLocator extends org.apache.axis.client.Service implements localhost.sacws_api.services.FormulariWS.FormulariQueryServiceEJBRemoteService {

    public FormulariQueryServiceEJBRemoteServiceLocator() {
    }


    public FormulariQueryServiceEJBRemoteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FormulariQueryServiceEJBRemoteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FormulariWS
    private java.lang.String FormulariWS_address = "https://localhost:8443/sacws-api/services/FormulariWS";

    public java.lang.String getFormulariWSAddress() {
        return FormulariWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FormulariWSWSDDServiceName = "FormulariWS";

    public java.lang.String getFormulariWSWSDDServiceName() {
        return FormulariWSWSDDServiceName;
    }

    public void setFormulariWSWSDDServiceName(java.lang.String name) {
        FormulariWSWSDDServiceName = name;
    }

    public localhost.sacws_api.services.FormulariWS.FormulariQueryServiceEJBRemote getFormulariWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FormulariWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFormulariWS(endpoint);
    }

    public localhost.sacws_api.services.FormulariWS.FormulariQueryServiceEJBRemote getFormulariWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.sacws_api.services.FormulariWS.FormulariWSSoapBindingStub _stub = new localhost.sacws_api.services.FormulariWS.FormulariWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getFormulariWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFormulariWSEndpointAddress(java.lang.String address) {
        FormulariWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.sacws_api.services.FormulariWS.FormulariQueryServiceEJBRemote.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.sacws_api.services.FormulariWS.FormulariWSSoapBindingStub _stub = new localhost.sacws_api.services.FormulariWS.FormulariWSSoapBindingStub(new java.net.URL(FormulariWS_address), this);
                _stub.setPortName(getFormulariWSWSDDServiceName());
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
        if ("FormulariWS".equals(inputPortName)) {
            return getFormulariWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/FormulariWS", "FormulariQueryServiceEJBRemoteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/FormulariWS", "FormulariWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FormulariWS".equals(portName)) {
            setFormulariWSEndpointAddress(address);
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
