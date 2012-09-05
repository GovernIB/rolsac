/**
 * EstadisticaInsertServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.EstadisticaWS;

public interface EstadisticaInsertServiceEJBRemote extends java.rmi.Remote {
    public boolean gravarEstadisticaFitxa(long in0) throws java.rmi.RemoteException;
    public boolean gravarEstadisticaFitxaPerMateria(long in0, long in1) throws java.rmi.RemoteException;
    public boolean gravarEstadisticaFitxaPerUA(long in0, long in1) throws java.rmi.RemoteException;
    public boolean gravarEstadisticaMateria(long in0) throws java.rmi.RemoteException;
    public boolean gravarEstadisticaNormativa(long in0) throws java.rmi.RemoteException;
    public boolean gravarEstadisticaProcediment(long in0) throws java.rmi.RemoteException;
    public boolean gravarEstadisticaUnitatAdministrativa(long in0) throws java.rmi.RemoteException;
}
