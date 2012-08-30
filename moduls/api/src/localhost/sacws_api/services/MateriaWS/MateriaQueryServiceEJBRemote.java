/**
 * MateriaQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.MateriaWS;

public interface MateriaQueryServiceEJBRemote extends java.rmi.Remote {
    public java.lang.Object[] llistarUnitatsAdministratives(long in0, es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFitxes(long in0, es.caib.rolsac.api.v2.fitxa.FitxaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarIconesMateries(long in0, es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarProcedimentsLocals(long in0, es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in1) throws java.rmi.RemoteException;
    public int getNumFitxes(long in0) throws java.rmi.RemoteException;
    public int getNumProcedimentsLocals(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO getFotografia(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO getIcona(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO getIconaGran(long in0) throws java.rmi.RemoteException;
    public int getNumAgrupacioMateries(long in0) throws java.rmi.RemoteException;
    public int getNumUnitatsMateries(long in0) throws java.rmi.RemoteException;
    public int getNumIcones(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarAgrupacioMateries(long in0, es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarUnitatsMateria(long in0, es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria in1) throws java.rmi.RemoteException;
}
