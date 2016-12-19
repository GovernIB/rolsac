/**
 * WsSIAConsultarActuacionesIdentificacion_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar;

public interface WsSIAConsultarActuacionesIdentificacion_PortType extends java.rmi.Remote {
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.enviaSIA.EnviaSIA consultarSIA(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException;
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.enviaSIA.EnviaSIA consultarSIA_v2_4(org.ibit.rol.sac.persistence.ws.sia.consultar.v2_4.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException;
    public org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.enviaSIA.EnviaSIA consultarSIA_v3_0(org.ibit.rol.sac.persistence.ws.sia.consultar.v3_0.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException;
}
