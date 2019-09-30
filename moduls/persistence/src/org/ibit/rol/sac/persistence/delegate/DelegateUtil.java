package org.ibit.rol.sac.persistence.delegate;

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

	public static PlataformaDelegate getPlataformaDelegate() {
		final PlataformaDelegateI impl = (PlataformaDelegateImpl) DelegateFactory
				.getDelegate(PlataformaDelegateImpl.class);
		final PlataformaDelegate del = new PlataformaDelegate();
		del.setImpl(impl);
		return del;
	}

	public static IdiomaDelegate getIdiomaDelegate() {
		final IdiomaDelegateI impl = (IdiomaDelegateImpl) DelegateFactory.getDelegate(IdiomaDelegateImpl.class);
		final IdiomaDelegate del = new IdiomaDelegate();
		del.setImpl(impl);
		return del;
	}

	public static UnidadAdministrativaDelegate getUADelegate() {
		final UnidadAdministrativaDelegateI impl = (UnidadAdministrativaDelegateImpl) DelegateFactory
				.getDelegate(UnidadAdministrativaDelegateImpl.class);
		final UnidadAdministrativaDelegate del = new UnidadAdministrativaDelegate();
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
		final FamiliaDelegateI impl = (FamiliaDelegateImpl) DelegateFactory.getDelegate(FamiliaDelegateImpl.class);
		final FamiliaDelegate del = new FamiliaDelegate();
		del.setImpl(impl);
		return del;
	}

	public static IniciacionDelegate getIniciacionDelegate() {
		final IniciacionDelegateI impl = (IniciacionDelegateImpl) DelegateFactory
				.getDelegate(IniciacionDelegateImpl.class);
		final IniciacionDelegate del = new IniciacionDelegate();
		del.setImpl(impl);
		return del;
	}

	public static MateriaDelegate getMateriaDelegate() {
		final MateriaDelegateI impl = (MateriaDelegateImpl) DelegateFactory.getDelegate(MateriaDelegateImpl.class);
		final MateriaDelegate del = new MateriaDelegate();
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

	public static HechoVitalServicioDelegate getHechoVitalServicioDelegate() {
		return (HechoVitalServicioDelegate) DelegateFactory.getDelegate(HechoVitalServicioDelegate.class);
	}

	public static FichaDelegate getFichaDelegate() {
		final FichaDelegateI impl = (FichaDelegateImpl) DelegateFactory.getDelegate(FichaDelegateImpl.class);
		final FichaDelegate del = new FichaDelegate();
		del.setImpl(impl);
		return del;
	}

	public static FichaResumenDelegate getFichaResumenDelegate() {
		final FichaResumenDelegateI impl = (FichaResumenDelegateImpl) DelegateFactory
				.getDelegate(FichaResumenDelegateImpl.class);
		final FichaResumenDelegate del = new FichaResumenDelegate();
		del.setImpl(impl);
		return del;
	}

	public static NormativaDelegate getNormativaDelegate() {
		return (NormativaDelegate) DelegateFactory.getDelegate(NormativaDelegate.class);
	}

	public static ProcedimientoDelegate getProcedimientoDelegate() {
		final ProcedimientoDelegateI impl = (ProcedimientoDelegateImpl) DelegateFactory
				.getDelegate(ProcedimientoDelegateImpl.class);
		final ProcedimientoDelegate del = new ProcedimientoDelegate();
		del.setImpl(impl);
		return del;
	}

	public static TipoAfectacionDelegate getTipoAfectacionDelegate() {
		return (TipoAfectacionDelegate) DelegateFactory.getDelegate(TipoAfectacionDelegate.class);
	}

	public static DocumentoDelegate getDocumentoDelegate() {
		final DocumentoDelegateI impl = (DocumentoDelegateImpl) DelegateFactory
				.getDelegate(DocumentoDelegateImpl.class);
		final DocumentoDelegate del = new DocumentoDelegate();
		del.setImpl(impl);
		return del;
	}

	public static DocumentoResumenDelegate getDocumentoResumenDelegate() {
		return (DocumentoResumenDelegate) DelegateFactory.getDelegate(DocumentoResumenDelegate.class);
	}

	public static TramiteDelegate getTramiteDelegate() {
		final TramiteDelegateI impl = (TramiteDelegateImpl) DelegateFactory.getDelegate(TramiteDelegateImpl.class);
		final TramiteDelegate del = new TramiteDelegate();
		del.setImpl(impl);
		return del;
	}

	public static AuditoriaDelegate getAuditoriaDelegate() {
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

	public static DestinatarioDelegate getDestinatarioDelegate() {
		final DestinatarioDelegateI impl = (DestinatarioDelegateImpl) DelegateFactory
				.getDelegate(DestinatarioDelegateImpl.class);
		final DestinatarioDelegate del = new DestinatarioDelegate();
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

	public static EnlaceDelegate getEnlaceDelegate() {
		return (EnlaceDelegate) DelegateFactory.getDelegate(EnlaceDelegate.class);
	}

	public static ArchivoDelegate getArchivoDelegate() {
		return (ArchivoDelegate) DelegateFactory.getDelegate(ArchivoDelegate.class);
	}

	public static TramiteRemotoDelegate getTramiteRemotoDelegate() {
		return (TramiteRemotoDelegate) DelegateFactory.getDelegate(TramiteRemotoDelegate.class);
	}

	public static NormativaRemotaDelegate getNormativaRemotaDelegate() {
		return (NormativaRemotaDelegate) DelegateFactory.getDelegate(NormativaRemotaDelegate.class);
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
		final SolrPendienteDelegateI impl = (SolrPendienteDelegateImpl) DelegateFactory
				.getDelegate(SolrPendienteDelegateImpl.class);
		final SolrPendienteDelegate del = new SolrPendienteDelegate();
		del.setImpl(impl);
		return del;
	}

	public static SolrPendienteJobDelegate getSolrPendienteJobDelegate() {
		final SolrPendienteJobDelegateI impl = (SolrPendienteJobDelegateImpl) DelegateFactory
				.getDelegate(SolrPendienteJobDelegateImpl.class);
		final SolrPendienteJobDelegate del = new SolrPendienteJobDelegate();
		del.setImpl(impl);
		return del;
	}

	public static SolrPendienteProcesoDelegate getSolrPendienteProcesoDelegate() {
		final SolrPendienteProcesoDelegateI impl = (SolrPendienteProcesoDelegateImpl) DelegateFactory
				.getDelegate(SolrPendienteProcesoDelegateImpl.class);
		final SolrPendienteProcesoDelegate del = new SolrPendienteProcesoDelegate();
		del.setImpl(impl);
		return del;
	}

	public static SilencioAdmDelegate getSilencioAdmDelegate() {
		final SilencioAdmDelegateI impl = (SilencioAdmDelegateImpl) DelegateFactory
				.getDelegate(SilencioAdmDelegateImpl.class);
		final SilencioAdmDelegate del = new SilencioAdmDelegate();
		del.setImpl(impl);
		return del;
	}

	public static SiaPendienteProcesoDelegate getSiaPendienteProcesoDelegate() {
		return (SiaPendienteProcesoDelegate) DelegateFactory.getDelegate(SiaPendienteProcesoDelegate.class);
	}

	public static SiaDelegate getSiaDelegate() {
		final SiaDelegateI impl = (SiaDelegateImpl) DelegateFactory.getDelegate(SiaDelegateImpl.class);
		final SiaDelegate del = new SiaDelegate();
		del.setImpl(impl);
		return del;
	}

	public static UnidadNormativaDelegate getUnidadNormativaDelegate() {
		return (UnidadNormativaDelegate) DelegateFactory.getDelegate(UnidadNormativaDelegate.class);
	}

	public static DocumentoNormativaDelegate getDocumentoNormativaDelegate() {
		return (DocumentoNormativaDelegate) DelegateFactory.getDelegate(DocumentoNormativaDelegate.class);
	}

	public static DocumentoServicioDelegate getDocumentoServicioDelegate() {
		return (DocumentoServicioDelegate) DelegateFactory.getDelegate(DocumentoServicioDelegate.class);
	}

	public static ServicioDelegate getServicioDelegate() {
		return (ServicioDelegate) DelegateFactory.getDelegate(ServicioDelegate.class);
	}
}
