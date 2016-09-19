package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.SilencioAdm;



/**
 * Define métodos estáticos para obtener delegates.
 */
public final class DelegateUtil {

    private DelegateUtil() {
    }

    public static PerfilDelegate getPerfilDelegate() {
        return (PerfilDelegate) DelegateFactory.getDelegate(PerfilDelegate.class);
    }

    public static UsuarioDelegate getUsuarioDelegate() {
        return (UsuarioDelegate) DelegateFactory.getDelegate(UsuarioDelegate.class);
    }
    
    public static PerfilGestorDelegate getPerfilGestorDelegate() {
        return (PerfilGestorDelegate) DelegateFactory.getDelegate(PerfilGestorDelegate.class);
    }
    
    public static IdiomaDelegate getIdiomaDelegate() {
    	IdiomaDelegateI impl = (IdiomaDelegateImpl) DelegateFactory.getDelegate(IdiomaDelegateImpl.class);
    	IdiomaDelegate del = new IdiomaDelegate();
        del.setImpl(impl);
        return del;
    }

    public static UnidadAdministrativaDelegate getUADelegate() {
        UnidadAdministrativaDelegateI impl= (UnidadAdministrativaDelegateImpl)DelegateFactory.getDelegate(UnidadAdministrativaDelegateImpl.class);
        UnidadAdministrativaDelegate del=new UnidadAdministrativaDelegate();
        del.setImpl(impl);
        return del;
    }

    public static EdificioDelegate getEdificioDelegate() {
        return (EdificioDelegate) DelegateFactory.getDelegate(EdificioDelegate.class);
    }

    public static PersonalDelegate getPersonalDelegate() {
        return (PersonalDelegate) DelegateFactory.getDelegate(PersonalDelegate.class);
    }

    public static TratamientoDelegate getTratamientoDelegate() {
        return (TratamientoDelegate) DelegateFactory.getDelegate(TratamientoDelegate.class);
    }

    public static FamiliaDelegate getFamiliaDelegate() {
    	FamiliaDelegateI impl= (FamiliaDelegateImpl) DelegateFactory.getDelegate(FamiliaDelegateImpl.class);
    	FamiliaDelegate del = new FamiliaDelegate();
    	del.setImpl(impl);
    	return del;
    }
    
    public static IniciacionDelegate getIniciacionDelegate() {
    	IniciacionDelegateI impl =(IniciacionDelegateImpl)DelegateFactory.getDelegate(IniciacionDelegateImpl.class);
    	IniciacionDelegate del = new IniciacionDelegate();
    	del.setImpl(impl);
    	return del;
    }
    
    public static MateriaDelegate getMateriaDelegate() {
        MateriaDelegateI impl= (MateriaDelegateImpl) DelegateFactory.getDelegate(MateriaDelegateImpl.class);
        MateriaDelegate del = new MateriaDelegate();
    	del.setImpl(impl);
    	return del;
    }

    public static IconoFamiliaDelegate getIconoFamiliaDelegate() {
        return (IconoFamiliaDelegate) DelegateFactory.getDelegate(IconoFamiliaDelegate.class);
    }

    public static IconoMateriaDelegate getIconoMateriaDelegate() {
        return (IconoMateriaDelegate) DelegateFactory.getDelegate(IconoMateriaDelegate.class);
    }

    public static TipoNormativaDelegate getTipoNormativaDelegate() {
        return (TipoNormativaDelegate) DelegateFactory.getDelegate(TipoNormativaDelegate.class);
    }

    public static SeccionDelegate getSeccionDelegate() {
        return (SeccionDelegate) DelegateFactory.getDelegate(SeccionDelegate.class);
    }

    public static BoletinDelegate getBoletinDelegate() {
        return (BoletinDelegate) DelegateFactory.getDelegate(BoletinDelegate.class);
    }

    public static HechoVitalDelegate getHechoVitalDelegate() {
        return (HechoVitalDelegate) DelegateFactory.getDelegate(HechoVitalDelegate.class);
    }

    public static HechoVitalProcedimientoDelegate getHechoVitalProcedimientoDelegate() {
        return (HechoVitalProcedimientoDelegate) DelegateFactory.getDelegate(HechoVitalProcedimientoDelegate.class);
    }

    public static FichaDelegate getFichaDelegate(){
        FichaDelegateI impl= (FichaDelegateImpl) DelegateFactory.getDelegate(FichaDelegateImpl.class);
        FichaDelegate del = new FichaDelegate();
    	del.setImpl(impl);
    	return del;
    }
    
    public static FichaResumenDelegate getFichaResumenDelegate(){
        FichaResumenDelegateI impl= (FichaResumenDelegateImpl) DelegateFactory.getDelegate(FichaResumenDelegateImpl.class);
        FichaResumenDelegate del = new FichaResumenDelegate();
    	del.setImpl(impl);
    	return del;
    }

    public static NormativaDelegate getNormativaDelegate(){
        return (NormativaDelegate) DelegateFactory.getDelegate(NormativaDelegate.class);
    }

    public static ProcedimientoDelegate getProcedimientoDelegate(){
        ProcedimientoDelegateI impl= (ProcedimientoDelegateImpl) DelegateFactory.getDelegate(ProcedimientoDelegateImpl.class);
        ProcedimientoDelegate del = new ProcedimientoDelegate();
    	del.setImpl(impl);
    	return del;
    }

    public static TipoAfectacionDelegate getTipoAfectacionDelegate(){
        return (TipoAfectacionDelegate) DelegateFactory.getDelegate(TipoAfectacionDelegate.class);
    }

    public static DocumentoDelegate getDocumentoDelegate(){
    	DocumentoDelegateI impl= (DocumentoDelegateImpl) DelegateFactory.getDelegate(DocumentoDelegateImpl.class);
    	DocumentoDelegate del = new DocumentoDelegate();
      	del.setImpl(impl);
      	return del;
    }
    
    public static DocumentoResumenDelegate getDocumentoResumenDelegate(){
        return (DocumentoResumenDelegate) DelegateFactory.getDelegate(DocumentoResumenDelegate.class);
    }
    
    public static TramiteDelegate getTramiteDelegate(){
        TramiteDelegateI impl =  (TramiteDelegateImpl)DelegateFactory.getDelegate(TramiteDelegateImpl.class);
        TramiteDelegate del = new TramiteDelegate();
    	del.setImpl(impl);
    	return del;
    }

    public static AuditoriaDelegate getAuditoriaDelegate(){
        return (AuditoriaDelegate) DelegateFactory.getDelegate(AuditoriaDelegate.class);
    }
 

    public static EstadisticaDelegate getEstadisticaDelegate() {
        return (EstadisticaDelegate) DelegateFactory.getDelegate(EstadisticaDelegate.class);
    }
    
    public static AgrupacionHVDelegate getAgrupacionHVDelegate() {
        return (AgrupacionHVDelegate) DelegateFactory.getDelegate(AgrupacionHVDelegate.class);
    }
    
    public static PublicoObjetivoDelegate getPublicoObjetivoDelegate() {
        return (PublicoObjetivoDelegate) DelegateFactory.getDelegate(PublicoObjetivoDelegate.class);
    }
    
    public static AdministracionRemotaDelegate getAdministracionRemotaDelegate() {
        return (AdministracionRemotaDelegate) DelegateFactory.getDelegate(AdministracionRemotaDelegate.class);
    }


    public static UnidadMateriaDelegate getUnidadMateriaDelegate() {
        return (UnidadMateriaDelegate) DelegateFactory.getDelegate(UnidadMateriaDelegate.class);
    }

    public static EspacioTerritorialDelegate getEspacioTerritorialDelegate() {
        return (EspacioTerritorialDelegate) DelegateFactory.getDelegate(EspacioTerritorialDelegate.class);
    }
    
    public static FichaRemotaDelegate getFichaRemotaDelegate() {
        return (FichaRemotaDelegate) DelegateFactory.getDelegate(FichaRemotaDelegate.class);
    }
    
    public static UARemotaDelegate getUARemotaDelegate() {
        return (UARemotaDelegate) DelegateFactory.getDelegate(UARemotaDelegate.class);
    }
    
    public static ProcedimientoRemotoDelegate getProcedimientoRemotoDelegate() {
        return (ProcedimientoRemotoDelegate) DelegateFactory.getDelegate(ProcedimientoRemotoDelegate.class);
    }

    public static DestinatarioDelegate getDestinatarioDelegate(){
    	DestinatarioDelegateI impl = (DestinatarioDelegateImpl)  DelegateFactory.getDelegate(DestinatarioDelegateImpl.class);
    	DestinatarioDelegate del= new DestinatarioDelegate();
    	del.setImpl(impl);
    	return del;
    }
    
    public static AgrupacionMDelegate getAgrupacionMDelegate() {
        return (AgrupacionMDelegate) DelegateFactory.getDelegate(AgrupacionMDelegate.class);
    }

    public static MateriaAgrupacionMDelegate getMateriaAgrupacionMDelegate() {
        return (MateriaAgrupacionMDelegate) DelegateFactory.getDelegate(MateriaAgrupacionMDelegate.class);
    }
    
    public static EngineJdbcDaoDelegate getEngineJdbcDaoDelegate() {
        return (EngineJdbcDaoDelegate) DelegateFactory.getDelegate(EngineJdbcDaoDelegate.class);
    }
    
    public static EnlaceDelegate getEnlaceDelegate(){
        return (EnlaceDelegate) DelegateFactory.getDelegate(EnlaceDelegate.class);
    }
    
    public static ArchivoDelegate getArchivoDelegate(){
        return (ArchivoDelegate)  DelegateFactory.getDelegate(ArchivoDelegate.class);
    }
    
    public static TramiteRemotoDelegate getTramiteRemotoDelegate() {
        return (TramiteRemotoDelegate) DelegateFactory.getDelegate(TramiteRemotoDelegate.class);
    }
    
    public static NormativaExternaRemotaDelegate getNormativaExternaRemotaDelegate() {
        return (NormativaExternaRemotaDelegate) DelegateFactory.getDelegate(NormativaExternaRemotaDelegate.class);
    }
    public static CatalegDocumentsDelegate getCatalegDocumentsDelegate() {
      return (CatalegDocumentsDelegate) DelegateFactory.getDelegate(CatalegDocumentsDelegate.class);
    }
    public static ExcepcioDocumentacioDelegate getExcepcioDocumentacioDelegate() {
      return (ExcepcioDocumentacioDelegate) DelegateFactory.getDelegate(ExcepcioDocumentacioDelegate.class);
    }
    
    public static ModelsComunsDelegate geModelsComunsDelegate() {
        return (ModelsComunsDelegate) DelegateFactory.getDelegate(ModelsComunsDelegate.class);
      }
    
    public static SolrPendienteDelegate getSolrPendienteDelegate() {
    	SolrPendienteDelegateI impl = (SolrPendienteDelegateImpl) DelegateFactory.getDelegate(SolrPendienteDelegateImpl.class);
    	SolrPendienteDelegate del = new SolrPendienteDelegate();
        del.setImpl(impl);
        return del;
    }
    
    public static SolrPendienteJobDelegate getSolrPendienteJobDelegate() {
    	SolrPendienteJobDelegateI impl = (SolrPendienteJobDelegateImpl) DelegateFactory.getDelegate(SolrPendienteJobDelegateImpl.class);
    	SolrPendienteJobDelegate del = new SolrPendienteJobDelegate();
        del.setImpl(impl);
        return del;
    }
    public static SolrPendienteProcesoDelegate getSolrPendienteProcesoDelegate() {
		SolrPendienteProcesoDelegateI impl = (SolrPendienteProcesoDelegateImpl) DelegateFactory.getDelegate(SolrPendienteProcesoDelegateImpl.class);
		SolrPendienteProcesoDelegate del = new SolrPendienteProcesoDelegate();
        del.setImpl(impl);
        return del;
	}
    public static SilencioAdmDelegate getSilencioAdmDelegate() {
    	SilencioAdmDelegateI impl= (SilencioAdmDelegateImpl) DelegateFactory.getDelegate(SilencioAdmDelegateImpl.class);
    	SilencioAdmDelegate del = new SilencioAdmDelegate();
    	del.setImpl(impl);
    	return del;
    }
}