/**
 * WsSIAConsultarActuacionesIdentificacion_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar;

public class WsSIAConsultarActuacionesIdentificacion_ServiceLocator extends org.apache.axis.client.Service implements org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_Service {

    public WsSIAConsultarActuacionesIdentificacion_ServiceLocator() {
    }


    public WsSIAConsultarActuacionesIdentificacion_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsSIAConsultarActuacionesIdentificacion_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsSIAConsultarActuacionesIdentificacionSOAP
    private java.lang.String wsSIAConsultarActuacionesIdentificacionSOAP_address = "https://pre-sia2.redsara.es/axis2/services/wsSIAConsultarActuacionesIdentificacion?wsdl";

    public java.lang.String getwsSIAConsultarActuacionesIdentificacionSOAPAddress() {
        return wsSIAConsultarActuacionesIdentificacionSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName = "wsSIAConsultarActuacionesIdentificacionSOAP";

    public java.lang.String getwsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName() {
        return wsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName;
    }

    public void setwsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName(java.lang.String name) {
        wsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName = name;
    }

    public org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_PortType getwsSIAConsultarActuacionesIdentificacionSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsSIAConsultarActuacionesIdentificacionSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsSIAConsultarActuacionesIdentificacionSOAP(endpoint);
    }

    public org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_PortType getwsSIAConsultarActuacionesIdentificacionSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacionSOAPStub _stub = new org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacionSOAPStub(portAddress, this);
            _stub.setPortName(getwsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsSIAConsultarActuacionesIdentificacionSOAPEndpointAddress(java.lang.String address) {
        wsSIAConsultarActuacionesIdentificacionSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacionSOAPStub _stub = new org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacionSOAPStub(new java.net.URL(wsSIAConsultarActuacionesIdentificacionSOAP_address), this);
                _stub.setPortName(getwsSIAConsultarActuacionesIdentificacionSOAPWSDDServiceName());
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
        if ("wsSIAConsultarActuacionesIdentificacionSOAP".equals(inputPortName)) {
            return getwsSIAConsultarActuacionesIdentificacionSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.map.es/sgca/consultar", "wsSIAConsultarActuacionesIdentificacion");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar", "wsSIAConsultarActuacionesIdentificacionSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsSIAConsultarActuacionesIdentificacionSOAP".equals(portName)) {
            setwsSIAConsultarActuacionesIdentificacionSOAPEndpointAddress(address);
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
