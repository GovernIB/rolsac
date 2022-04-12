package es.caib.rolsac.api.v2.rolsac.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.DocumentoNormativa;
import org.ibit.rol.sac.model.DocumentoServicio;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionLopdLegitimacion;
import org.ibit.rol.sac.model.TraduccionPlataforma;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionServicio;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Usuario;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaDTO;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoCriteria;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaUtils;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.plantilla.PlantillaCriteria;
import es.caib.rolsac.api.v2.plantilla.PlantillaDTO;
import es.caib.rolsac.api.v2.plataforma.PlataformaCriteria;
import es.caib.rolsac.api.v2.plataforma.PlataformaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentUtils;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioUtils;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para consultas de ROLSAC.
 *
 * @ejb.bean name="sac/api/RolsacQueryServiceEJB"
 *           jndi-name="es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public class RolsacQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = 6406577402253277346L;

	private static Log log = LogFactory.getLog(RolsacQueryServiceEJB.class);

	private static final String HQL_TRADUCCIONES_ALIAS = "trad";
	private static final String HQL_PROCEDIMIENTO_CLASS = "ProcedimientoLocal";
	private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
	private static final String HQL_SERVICIO_CLASS = "Servicio";
	private static final String HQL_SERVICIO_ALIAS = "s";
	private static final String HQL_MATERIA_CLASS = "Materia";
	private static final String HQL_MATERIA_ALIAS = "m";
	private static final String HQL_CATALOGO_DOCUMENTOS_CLASS = "CatalegDocuments";
	private static final String HQL_CATALOGO_DOCUMENTOS_ALIAS = "cd";
	private static final String HQL_EXCEPCION_DOCUMENTACION_CLASS = "ExcepcioDocumentacio";
	private static final String HQL_EXCEPCION_DOCUMENTACION_ALIAS = "ex";
	private static final String HQL_TRAMIT_CLASS = "Tramite";
	private static final String HQL_TRAMIT_ALIAS = "t";
	private static final String HQL_UA_CLASS = "UnidadAdministrativa";
	private static final String HQL_UA_ALIAS = "ua";
	private static final String HQL_FITXA_CLASS = "Ficha";
	private static final String HQL_FITXA_ALIAS = "fi";
	private static final String HQL_NORMATIVA_CLASS = "Normativa";
	private static final String HQL_NORMATIVA_ALIAS = "n";
	private static final String HQL_PERSONAL_CLASS = "Personal";
	private static final String HQL_PERSONAL_ALIAS = "per";
	private static final String HQL_DOC_TRAMITE_CLASS = "DocumentTramit";
	private static final String HQL_DOC_NORMATIVA_CLASS = "DocumentoNormativa";
	private static final String HQL_DOC_SERVICIO_CLASS = "DocumentoServicio";
	private static final String HQL_DOC_TRAMITE_ALIAS = "dt";
	private static final String HQL_DOC_NORMATIVA_ALIAS = "dn";
	private static final String HQL_DOC_SERVICIO_ALIAS = "ds";
	private static final String HQL_USUARI_CLASS = "Usuario";
	private static final String HQL_USUARI_ALIAS = "usu";
	private static final String HQL_TAXA_CLASS = "Taxa";
	private static final String HQL_TAXA_ALIAS = "ta";
	private static final String HQL_AGRUPACIO_FET_VITAL_CLASS = "AgrupacionHechoVital";
	private static final String HQL_AGRUPACIO_FET_VITAL_ALIAS = "afv";
	private static final String HQL_AGRUPACIO_MATERIA_CLASS = "AgrupacionMateria";
	private static final String HQL_AGRUPACIO_MATERIA_ALIAS = "am";
	private static final String HQL_BUTLLETI_CLASS = "Boletin";
	private static final String HQL_BUTLLETI_ALIAS = "b";
	private static final String HQL_DOCUMENT_CLASS = "Documento";
	private static final String HQL_DOCUMENT_ALIAS = "doc";
	private static final String HQL_EDIFICI_CLASS = "Edificio";
	private static final String HQL_EDIFICI_ALIAS = "ed";
	private static final String HQL_FET_VITAL_CLASS = "HechoVital";
	private static final String HQL_FET_VITAL_ALIAS = "fv";
	private static final String HQL_PLATAFORMA_CLASS = "Plataforma";
	private static final String HQL_PLATAFORMA_ALIAS = "pt";
	private static final String HQL_PLANTILLA_CLASS = "TramitePlantilla";
	private static final String HQL_PLANTILLA_ALIAS = "plant";
	private static final String HQL_ENLLAC_CLASS = "Enlace";
	private static final String HQL_ENLLAC_ALIAS = "en";
	private static final String HQL_ESPAI_TERRITORIAL_CLASS = "EspacioTerritorial";
	private static final String HQL_ESPAI_TERRITORIAL_ALIAS = "et";
	private static final String HQL_FAMILIA_CLASS = "Familia";
	private static final String HQL_FAMILIA_ALIAS = "fa";
	private static final String HQL_PUBLIC_OBJECTIU_CLASS = "PublicoObjetivo";
	private static final String HQL_PUBLIC_OBJECTIU_ALIAS = "po";
	private static final String HQL_FITXA_UA_CLASS = "FichaUA";
	private static final String HQL_FITXA_UA_ALIAS = "fua";
	private static final String HQL_FORMULARI_CLASS = "Formulario";
	private static final String HQL_FORMULARI_ALIAS = "form";
	private static final String HQL_ICONA_FAMILIA_CLASS = "IconoFamilia";
	private static final String HQL_ICONA_FAMILIA_ALIAS = "iFam";
	private static final String HQL_ICONA_MATERIA_CLASS = "IconoMateria";
	private static final String HQL_ICONA_MATERIA_ALIAS = "iFMat";
	private static final String HQL_MATERIA_AGRUPACIO_CLASS = "MateriaAgrupacionM";
	private static final String HQL_MATERIA_AGRUPACIO_ALIAS = "ma";
	private static final String HQL_PERFIL_CLASS = "PerfilCiudadano";
	private static final String HQL_PERFIL_ALIAS = "pc";
	private static final String HQL_SECCIO_CLASS = "Seccion";
	private static final String HQL_SECCIO_ALIAS = "s";
	private static final String HQL_TIPUS_CLASS = "Tipo";
	private static final String HQL_TIPUS_ALIAS = "ti";
	private static final String HQL_TIPUS_AFECTACIO_CLASS = "TipoAfectacion";
	private static final String HQL_TIPUS_AFECTACIO_ALIAS = "tia";
	private static final String HQL_UNITAT_MATERIA_CLASS = "UnidadMateria";
	private static final String HQL_UNITAT_MATERIA_ALIAS = "um";
	private static final String HQL_INICIACIO_CLASS = "Iniciacion";
	private static final String HQL_INICIACIO_ALIAS = "inici";
	private static final String HQL_IDIOMA_CLASS = "Idioma";
	private static final String HQL_IDIOMA_ALIAS = "idi";

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Obtiene excepción de documentación.
	 *
	 * @param excepcioDocumentacioCriteria
	 * @return ExcepcioDocumentacioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) {

		List<CriteriaObject> criteris;
		ExcepcioDocumentacioDTO excepcioDocumentacioDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(ExcepcioDocumentacioCriteria.class, HQL_EXCEPCION_DOCUMENTACION_ALIAS,
					HQL_TRADUCCIONES_ALIAS, excepcioDocumentacioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS, HQL_EXCEPCION_DOCUMENTACION_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_EXCEPCION_DOCUMENTACION_ALIAS, entities,
					excepcioDocumentacioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final ExcepcioDocumentacio excepcioDocumentacio = (ExcepcioDocumentacio) query.uniqueResult();
			if (excepcioDocumentacio != null) {
				excepcioDocumentacioDTO = (ExcepcioDocumentacioDTO) BasicUtils.entityToDTO(
						ExcepcioDocumentacioDTO.class, excepcioDocumentacio, excepcioDocumentacioCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return excepcioDocumentacioDTO;

	}

	/**
	 * Obtiene excepciones de documentación.
	 *
	 * @param excepcioDocumentacioCriteria
	 * @return List<ExcepcioDocumentacioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) {
		List<CriteriaObject> criteris;
		final List<ExcepcioDocumentacioDTO> excepcioDocumentacioDTOList = new ArrayList<ExcepcioDocumentacioDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(ExcepcioDocumentacioCriteria.class, HQL_EXCEPCION_DOCUMENTACION_ALIAS,
					HQL_TRADUCCIONES_ALIAS, excepcioDocumentacioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS, HQL_EXCEPCION_DOCUMENTACION_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_EXCEPCION_DOCUMENTACION_ALIAS, entities,
					excepcioDocumentacioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<ExcepcioDocumentacio> excepcioDocumentacioResult = query.list();
			for (final ExcepcioDocumentacio excepcioDocumentacio : excepcioDocumentacioResult) {
				excepcioDocumentacioDTOList
						.add((ExcepcioDocumentacioDTO) BasicUtils.entityToDTO(ExcepcioDocumentacioDTO.class,
								excepcioDocumentacio, excepcioDocumentacioCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}
		return excepcioDocumentacioDTOList;
	}

	/**
	 * Obtiene un catalogo de documentos.
	 *
	 * @param catalegDocumentsCriteria
	 * @return CatalegDocumentsDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public CatalegDocumentsDTO obtenirCatalegDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria) {

		List<CriteriaObject> criteris;
		CatalegDocumentsDTO catalegDocumentsDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(CatalegDocumentsCriteria.class, HQL_CATALOGO_DOCUMENTOS_ALIAS,
					HQL_TRADUCCIONES_ALIAS, catalegDocumentsCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_CATALOGO_DOCUMENTOS_ALIAS, entities,
					catalegDocumentsCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final CatalegDocuments catalegDocuments = (CatalegDocuments) query.uniqueResult();
			if (catalegDocuments != null) {
				catalegDocumentsDTO = (CatalegDocumentsDTO) BasicUtils.entityToDTO(CatalegDocumentsDTO.class,
						catalegDocuments, catalegDocumentsCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return catalegDocumentsDTO;

	}

	/**
	 * Obtiene catálogos de documentos.
	 *
	 * @param catalegDocumentsCriteria
	 * @return List<CatalegDocumentsDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria) {
		List<CriteriaObject> criteris;
		final List<CatalegDocumentsDTO> catalegDocumentsDTOList = new ArrayList<CatalegDocumentsDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(CatalegDocumentsCriteria.class, HQL_CATALOGO_DOCUMENTOS_ALIAS,
					HQL_TRADUCCIONES_ALIAS, catalegDocumentsCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_CATALOGO_DOCUMENTOS_ALIAS, entities,
					catalegDocumentsCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<CatalegDocuments> catalegDocumentsResult = query.list();
			for (final CatalegDocuments catalegDocuments : catalegDocumentsResult) {
				catalegDocumentsDTOList.add((CatalegDocumentsDTO) BasicUtils.entityToDTO(CatalegDocumentsDTO.class,
						catalegDocuments, catalegDocumentsCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}
		return catalegDocumentsDTOList;
	}

	/**
	 * Obtiene un procedimiento.
	 *
	 * @param procedimentCriteria
	 * @return ProcedimentDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ProcedimentDTO obtenirProcediment(final ProcedimentCriteria procedimentCriteria) {

		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		ProcedimentDTO procedimentDTO = null;
		Session session = null;

		// Guardamos el estado de la UA para que no influya en la query
		final String estadoUA = procedimentCriteria.getEstadoUA();
		procedimentCriteria.setEstadoUA(null);

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, procedimentCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS, qb);
			criteris.addAll(BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, procedimentCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final ProcedimientoLocal procedimentLocal = (ProcedimientoLocal) query.uniqueResult();

			if (procedimentLocal != null) {

				if (estadoUA == null
						|| procedimentLocal.getUnidadAdministrativa().getValidacion() == Integer.parseInt(estadoUA)) {

					procedimentDTO = (ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class, procedimentLocal,
							procedimentCriteria.getIdioma());

					if (procedimentDTO.getLopdDestinatario() == null && ((TraduccionProcedimientoLocal) procedimentLocal
							.getTraduccion(procedimentCriteria.getIdioma())).getLopdDestinatario() != null) {
						procedimentDTO.setLopdDestinatario(((TraduccionProcedimientoLocal) procedimentLocal
								.getTraduccion(procedimentCriteria.getIdioma())).getLopdDestinatario());
					}

					if (procedimentDTO.getLopdDerechos() == null && ((TraduccionProcedimientoLocal) procedimentLocal
							.getTraduccion(procedimentCriteria.getIdioma())).getLopdDerechos() != null) {
						procedimentDTO.setLopdDerechos(((TraduccionProcedimientoLocal) procedimentLocal
								.getTraduccion(procedimentCriteria.getIdioma())).getLopdDerechos());
					}
				}

			}

			if (procedimentDTO != null) {
				String cabecera;
				if ("ca".equals(procedimentCriteria.getIdioma())) {
					cabecera = System.getProperty("es.caib.rolsac.lopd.cabecera.ca");
				} else {
					cabecera = System.getProperty("es.caib.rolsac.lopd.cabecera.es");
				}
				procedimentDTO.setLopdCabecera(cabecera);
			}

			if (procedimentLocal != null && procedimentLocal.getLopdLegitimacion() != null) {
				procedimentDTO.setLopdLegitimacionNombre(((TraduccionLopdLegitimacion) procedimentLocal
						.getLopdLegitimacion().getTraduccion(procedimentCriteria.getIdioma())).getNombre());
				procedimentDTO
						.setLopdLegitimacionIdentificador(procedimentLocal.getLopdLegitimacion().getIdentificador());
			}

			if (procedimentLocal != null) {
				if (procedimentLocal.isComun()) {
					procedimentDTO.setLopdResponsable(System
							.getProperty("es.caib.rolsac.lopd.responsable.comun." + procedimentCriteria.getIdioma()));
				} else if (procedimentLocal.getServicioResponsable() != null) {
					final String ua = getUAByCodigoDir3(procedimentLocal.getServicioResponsable(),
							procedimentCriteria.getIdioma(), new ArrayList<Long>());
					if (ua != null) {
						procedimentDTO.setLopdResponsable(ua);
					}
				}
			}
		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return procedimentDTO;

	}

	/**
	 * Obtiene procedimientos.
	 *
	 * @param procedimentCriteria
	 * @return List<ProcedimentDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<ProcedimentDTO> llistarProcediments(final ProcedimentCriteria procedimentCriteria) {

		final List<ProcedimentDTO> procedimentDTOList = new ArrayList<ProcedimentDTO>();
		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, procedimentCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			final Restriction telematicoVigente = ProcedimentUtils.ParseTelematicoVigente(procedimentCriteria,
					HQL_PROCEDIMIENTO_ALIAS);

			if (telematicoVigente != null)
				qb.addRestriction(telematicoVigente);

			ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS, qb);

			criteris.addAll(BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, procedimentCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<ProcedimientoLocal> procedimentsResult = query.list();

			for (final ProcedimientoLocal procediment : procedimentsResult) {
				procedimentDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class, procediment,
						procedimentCriteria.getIdioma()));
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return procedimentDTOList;

	}

	/**
	 * Cuenta procedimientos.
	 *
	 * @param procedimentCriteria
	 * @return int
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumProcediments(final ProcedimentCriteria procedimentCriteria) {

		Integer numResultats = -1;
		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, procedimentCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS, true);

			final Restriction telematicoVigente = ProcedimentUtils.ParseTelematicoVigente(procedimentCriteria,
					HQL_PROCEDIMIENTO_ALIAS);

			if (telematicoVigente != null)
				qb.addRestriction(telematicoVigente);

			ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS);

			criteris.addAll(BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, procedimentCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			numResultats = ((Integer) query.uniqueResult()).intValue();

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return numResultats;

	}

	/**
	 * Obtiene un servicio.
	 *
	 * @param servicioCriteria
	 * @return ServicioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ServicioDTO obtenirServicio(final ServicioCriteria servicioCriteria) {

		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		ServicioDTO servicioDTO = null;
		Session session = null;

		// Guardamos el estado de la UA para que no influya en la query
		final String estadoUA = servicioCriteria.getEstadoUA();
		servicioCriteria.setEstadoUA(null);

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_SERVICIO_ALIAS, entities, servicioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			ServicioUtils.parseActiu(criteris, servicioCriteria, HQL_SERVICIO_ALIAS, qb);
			criteris.addAll(BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, servicioCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Servicio servicio = (Servicio) query.uniqueResult();

			if (servicio != null) {

				if (estadoUA == null || servicio.getOrganoInstructor().getValidacion() == Integer.parseInt(estadoUA)) {

					servicioDTO = (ServicioDTO) BasicUtils.entityToDTO(ServicioDTO.class, servicio,
							servicioCriteria.getIdioma());
				}

				if (servicio.isLopdActivo()) {
					if (servicioDTO.getLopdDestinatario() == null
							&& ((TraduccionServicio) servicio.getTraduccion(servicioCriteria.getIdioma()))
									.getLopdDestinatario() != null) {
						servicioDTO.setLopdDestinatario(
								((TraduccionServicio) servicio.getTraduccion(servicioCriteria.getIdioma()))
										.getLopdDestinatario());
					}

					if (servicioDTO.getLopdDerechos() == null
							&& ((TraduccionServicio) servicio.getTraduccion(servicioCriteria.getIdioma()))
									.getLopdDerechos() != null) {
						servicioDTO.setLopdDerechos(
								((TraduccionServicio) servicio.getTraduccion(servicioCriteria.getIdioma()))
										.getLopdDerechos());
					}

					if (servicioDTO != null) {
						String cabecera;
						if ("ca".equals(servicioCriteria.getIdioma())) {
							cabecera = System.getProperty("es.caib.rolsac.lopd.cabecera.ca");
						} else {
							cabecera = System.getProperty("es.caib.rolsac.lopd.cabecera.es");
						}
						servicioDTO.setLopdCabecera(cabecera);
					}

					if (servicio.getLopdLegitimacion() != null) {
						servicioDTO.setLopdLegitimacionNombre(((TraduccionLopdLegitimacion) servicio
								.getLopdLegitimacion().getTraduccion(servicioCriteria.getIdioma())).getNombre());
						servicioDTO.setLopdLegitimacionIdentificador(servicio.getLopdLegitimacion().getIdentificador());
					}

					if (servicio.isComun()) {
						servicioDTO.setLopdResponsable(System
								.getProperty("es.caib.rolsac.lopd.responsable.comun." + servicioCriteria.getIdioma()));
					} else if (servicio.getServicioResponsable() != null) {
						final String ua = getUAByCodigoDir3(servicio.getServicioResponsable(),
								servicioCriteria.getIdioma(), new ArrayList<Long>());
						if (ua != null) {
							servicioDTO.setLopdResponsable(ua);
						}
					}
				} else {
					servicioDTO.setLopdCabecera(null);
					servicioDTO.setLopdDerechos(null);
					servicioDTO.setLopdDestinatario(null);
					servicioDTO.setLopdFinalidad(null);
					servicioDTO.setLopdInfoAdicional(null);
					servicioDTO.setLopdLegitimacion(null);
					servicioDTO.setLopdLegitimacionIdentificador(null);
					servicioDTO.setLopdLegitimacionNombre(null);
					servicioDTO.setLopdResponsable(null);
				}
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return servicioDTO;

	}

	/**
	 * Obtiene el nombre de la UA que tenga codigo DIR3
	 *
	 * @param servicioResponsable
	 * @param lang
	 * @return
	 */
	private String getUAByCodigoDir3(final UnidadAdministrativa servicioResponsable, final String lang,
			final List<Long> idAntecesores) {

		try {
			// SQL:
			// select *
			// from
			// (
			// select UNA_CODI, UNA_CODUNA, UNA_CODDR3, (select tun_nombre from RSC_TRAUNA
			// where tun_coduna = UNA_CODI and tun_codidi = 'ca') tradca
			// , (select tun_nombre from RSC_TRAUNA where tun_coduna = UNA_CODI and
			// tun_codidi = 'es') trades
			// , LEVEL
			// from RSC_UNIADM UNAADM
			// --where una_coddr3 != null
			// START WITH UNA_CODI = 1964
			// connect by prior UNA_CODUNA = UNA_CODI
			// order by LEVEL ASC
			// ) UNIADM_DIR3
			// where rownum = 1
			// and una_coddr3 IS NOT NULL;

			if (servicioResponsable == null) {
				return "";
			} else if (idAntecesores.contains(servicioResponsable.getId())) {
				log.error("Se ha producido un ciclo en getPadreDir3 con el id:" + servicioResponsable.getId());
				return "";
			} else if (servicioResponsable.getCodigoDIR3() != null && !servicioResponsable.getCodigoDIR3().isEmpty()) {
				return ((TraduccionUA) servicioResponsable.getTraduccion(lang)).getNombre();
			} else {
				idAntecesores.add(servicioResponsable.getId());
				return getUAByCodigoDir3(servicioResponsable.getPadre(), lang, idAntecesores);
			}
		} catch (final Exception e) {
			log.error("Se ha producido un ciclo en getPadreDir3 con el id:" + servicioResponsable.getId());
			return "";
		}
	}

	/**
	 * Obtiene servicios.
	 *
	 * @param servicioCriteria
	 * @return List<ServicioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<ServicioDTO> llistarServicios(final ServicioCriteria servicioCriteria) {

		final List<ServicioDTO> serviciosDTOList = new ArrayList<ServicioDTO>();
		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_SERVICIO_ALIAS, entities, servicioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			final Restriction vigente = ServicioUtils.ParseVigente(servicioCriteria, HQL_SERVICIO_ALIAS);
			final Restriction telematico = ServicioUtils.ParseTelematico(servicioCriteria, HQL_SERVICIO_ALIAS);

			if (vigente != null)
				qb.addRestriction(vigente);

			if (telematico != null)
				qb.addRestriction(telematico);

			ServicioUtils.parseActiu(criteris, servicioCriteria, HQL_SERVICIO_ALIAS, qb);

			criteris.addAll(BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, servicioCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Servicio> serviciosResult = query.list();

			for (final Servicio servicio : serviciosResult) {
				serviciosDTOList.add((ServicioDTO) BasicUtils.entityToDTO(ServicioDTO.class, servicio,
						servicioCriteria.getIdioma()));
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return serviciosDTOList;

	}

	/**
	 * Cuenta servicios.
	 *
	 * @param servicioCriteria
	 * @return int
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumServicios(final ServicioCriteria servicioCriteria) {

		Integer numResultats = -1;
		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_SERVICIO_ALIAS, entities, servicioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS, true);

			final Restriction vigente = ServicioUtils.ParseVigente(servicioCriteria, HQL_SERVICIO_ALIAS);
			final Restriction telematico = ServicioUtils.ParseTelematico(servicioCriteria, HQL_SERVICIO_ALIAS);

			if (vigente != null)
				qb.addRestriction(vigente);

			if (telematico != null)
				qb.addRestriction(telematico);

			ServicioUtils.parseActiu(criteris, servicioCriteria, HQL_SERVICIO_ALIAS);

			criteris.addAll(BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, servicioCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			numResultats = ((Integer) query.uniqueResult()).intValue();

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return numResultats;

	}

	/**
	 * Obtiene una materia.
	 *
	 * @param materiaCriteria
	 * @return MateriaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public MateriaDTO obtenirMateria(final MateriaCriteria materiaCriteria) {
		List<CriteriaObject> criteris;
		MateriaDTO materiaDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					materiaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, materiaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Materia materia = (Materia) query.uniqueResult();
			if (materia != null) {
				materiaDTO = (MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class, materia,
						materiaCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return materiaDTO;
	}

	/**
	 * Obtiene materias.
	 *
	 * @param materiaCriteria
	 * @return List<MateriaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<MateriaDTO> llistarMateries(final MateriaCriteria materiaCriteria) {
		List<CriteriaObject> criteris;
		final List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					materiaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, materiaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Materia> materiaResult = query.list();
			for (final Materia materia : materiaResult) {
				materiaDTOList.add(
						(MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class, materia, materiaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}
		return materiaDTOList;
	}

	/**
	 * Obtiene un tramite.
	 *
	 * @param tramiteCriteria
	 * @return TramiteDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public TramitDTO obtenirTramit(final TramitCriteria tramitCriteria) {
		List<CriteriaObject> criteris;
		TramitDTO tramitDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMIT_ALIAS, HQL_TRADUCCIONES_ALIAS,
					tramitCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TRAMIT_ALIAS, entities, tramitCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Tramite tramit = (Tramite) query.uniqueResult();
			if (tramit != null) {
				tramitDTO = (TramitDTO) BasicUtils.entityToDTO(TramitDTO.class, tramit, tramitCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return tramitDTO;
	}

	/**
	 * Obtiene tramites.
	 *
	 * @param tramiteCriteria
	 * @return List<TramiteDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<TramitDTO> llistarTramit(final TramitCriteria tramitCriteria) {
		final List<TramitDTO> tramitDTOList = new ArrayList<TramitDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMIT_ALIAS, HQL_TRADUCCIONES_ALIAS,
					tramitCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TRAMIT_ALIAS, entities, tramitCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Tramite> tramitsResult = query.list();
			for (final Tramite tramit : tramitsResult) {
				tramitDTOList
						.add((TramitDTO) BasicUtils.entityToDTO(TramitDTO.class, tramit, tramitCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return tramitDTOList;
	}

	/**
	 * Obtiene una unidad administrativa.
	 *
	 * @param uaCriteria
	 * @return UnitatAdministrativaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(final UnitatAdministrativaCriteria uaCriteria) {
		List<CriteriaObject> criteris;
		UnitatAdministrativaDTO uaDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, uaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, uaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final UnidadAdministrativa ua = (UnidadAdministrativa) query.uniqueResult();
			if (ua != null) {
				uaDTO = (UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class, ua,
						uaCriteria.getIdioma());

				uaDTO.setIdioma(uaCriteria.getIdioma());
			}

		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return uaDTO;
	}

	/**
	 * Obtiene unidades administrativas.
	 *
	 * @param uaCriteria
	 * @return List<UnitatAdministrativaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(final UnitatAdministrativaCriteria uaCriteria) {
		final List<UnitatAdministrativaDTO> uaDTOList = new ArrayList<UnitatAdministrativaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, uaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, uaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<UnidadAdministrativa> uaResult = query.list();
			for (final UnidadAdministrativa ua : uaResult) {
				uaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class, ua,
						uaCriteria.getIdioma()));

			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return uaDTOList;
	}

	/**
	 * Obtiene una plataforma.
	 *
	 * @param plataformaCriteria
	 * @return PlataformaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public PlataformaDTO obtenirPlataforma(final PlataformaCriteria plataformaCriteria) {
		List<CriteriaObject> criteris;
		PlataformaDTO plataformaDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PlataformaCriteria.class, HQL_PLATAFORMA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					plataformaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PLATAFORMA_CLASS, HQL_PLATAFORMA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PLATAFORMA_ALIAS, entities, plataformaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Plataforma plataforma = (Plataforma) query.uniqueResult();
			if (plataforma != null) {
				plataformaDTO = (PlataformaDTO) BasicUtils.entityToDTO(PlataformaDTO.class, plataforma,
						plataformaCriteria.getIdioma());

				final TraduccionPlataforma traPlt = (TraduccionPlataforma) plataforma
						.getTraduccion(plataformaCriteria.getIdioma());

				if (traPlt.getDescripcion() != null) {
					plataformaDTO.setDescripcion(traPlt.getDescripcion());
				}
				if (traPlt.getUrlAcceso() != null) {
					plataformaDTO.setUrlAcceso(traPlt.getUrlAcceso());
				}
			}

		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return plataformaDTO;
	}

	/**
	 * Obtiene una plantilla.
	 *
	 * @param plantillaCriteria
	 * @return PlantillaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public PlantillaDTO obtenirPlantilla(final PlantillaCriteria plantillaCriteria) {
		final List<CriteriaObject> criteris;
		PlantillaDTO plantillaDTO = null;
		Session session = null;

		try {
			// criteris = BasicUtils.parseCriterias(PlantillaCriteria.class,
			// HQL_PLANTILLA_ALIAS, HQL_TRADUCCIONES_ALIAS,
			// plantillaCriteria);
			//
			// final List<FromClause> entities = new ArrayList<FromClause>();
			// entities.add(new FromClause(HQL_PLANTILLA_CLASS, HQL_PLANTILLA_ALIAS));
			//
			// final QueryBuilder qb = new QueryBuilder(HQL_PLANTILLA_ALIAS, entities,
			// plantillaCriteria.getIdioma(),
			// HQL_TRADUCCIONES_ALIAS);
			// qb.extendCriteriaObjects(criteris);
			//
			// session = getSession();
			// final Query query = qb.createQuery(session);
			// final TramitePlantilla plantilla = (TramitePlantilla) query.uniqueResult();
			// if (plantilla != null) {
			// plantillaDTO = (PlantillaDTO) BasicUtils.entityToDTO(PlantillaDTO.class,
			// plantilla,
			// plantillaCriteria.getIdioma());
			//
			// final TraduccionTramitePlantilla traPlt = (TraduccionTramitePlantilla)
			// plantilla
			// .getTraduccion(plantillaCriteria.getIdioma());
			//
			// if (traPlt.getNombre() != null) {
			// plantillaDTO.setNombre(traPlt.getNombre());
			// }
			// }

			session = getSession();
			// final StringBuilder consulta = new StringBuilder("SELECT DISTINCT plant.id,
			// plant.identificador, plant.version, plant.parametros FROM TramitePlantilla AS
			// plant, plant.traducciones AS trad WHERE INDEX(trad) = :idioma AND plant.id =
			// :codigo ");
			final StringBuilder consulta = new StringBuilder(
					"SELECT DISTINCT plant.id, plant.identificador, plant.version, plant.plataforma.id, plant.parametros FROM TramitePlantilla AS plant, plant.traducciones AS trad WHERE INDEX(trad) = :idioma AND plant.id = :codigo  ");

			final Query query = session.createQuery(consulta.toString());

			query.setParameter("idioma", plantillaCriteria.getIdioma());
			query.setParameter("codigo", Long.valueOf(plantillaCriteria.getId()));

			final Object[] oplantilla = (Object[]) query.uniqueResult();
			if (oplantilla != null) {
				plantillaDTO = new PlantillaDTO();
				plantillaDTO.setId((Long) oplantilla[0]);
				plantillaDTO.setIdentificador((String) oplantilla[1]);
				plantillaDTO.setVersion((String) oplantilla[2]);
				plantillaDTO.setPlataforma((Long) oplantilla[3]);
				plantillaDTO.setParametros((String) oplantilla[4]);
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
			// } catch (final CriteriaObjectParseException e) {
			// log.error(e);
			// throw new EJBException(e);
			// } catch (final QueryBuilderException e) {
			// log.error(e);
			// throw new EJBException(e);
		} finally {
			close(session);
		}

		return plantillaDTO;
	}

	/**
	 * Obtiene un hecho vital.
	 *
	 * @param fetVitalCriteria
	 * @return FetVitalDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FetVitalDTO obtenirFetVital(final FetVitalCriteria fetVitalCriteria) {
		List<CriteriaObject> criteris;
		FetVitalDTO fetVitalDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, HQL_TRADUCCIONES_ALIAS,
					fetVitalCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FET_VITAL_ALIAS, entities, fetVitalCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final HechoVital fetVital = (HechoVital) query.uniqueResult();
			if (fetVital != null) {
				fetVitalDTO = (FetVitalDTO) BasicUtils.entityToDTO(FetVitalDTO.class, fetVital,
						fetVitalCriteria.getIdioma());

				final TraduccionHechoVital traHV = (TraduccionHechoVital) fetVital
						.getTraduccion(fetVitalCriteria.getIdioma());
				if (traHV.getDistribComp() != null) {
					fetVitalDTO.setFoto(traHV.getDistribComp().getId());
				}
				if (traHV.getNormativa() != null) {
					fetVitalDTO.setIcono(traHV.getNormativa().getId());
				}
				if (traHV.getContenido() != null) {
					fetVitalDTO.setIconoGrande(traHV.getContenido().getId());
				}
			}

		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return fetVitalDTO;
	}

	/**
	 * Obtiene hechos vitales.
	 *
	 * @param fetVitalCriteria
	 * @return FetVitalDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<FetVitalDTO> llistarFetsVitals(final FetVitalCriteria fetVitalCriteria) {
		List<CriteriaObject> criteris;
		final List<FetVitalDTO> fetVitalDTOList = new ArrayList<FetVitalDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, HQL_TRADUCCIONES_ALIAS,
					fetVitalCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FET_VITAL_ALIAS, entities, fetVitalCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<HechoVital> fvResult = query.list();
			for (final HechoVital fv : fvResult) {
				fetVitalDTOList
						.add((FetVitalDTO) BasicUtils.entityToDTO(FetVitalDTO.class, fv, fetVitalCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return fetVitalDTOList;
	}

	/**
	 * Obtiene una ficha.
	 *
	 * @param fitxaCriteria
	 * @return FitxaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FitxaDTO obtenirFitxa(final FitxaCriteria fitxaCriteria) {

		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		FitxaDTO fitxaDTO = null;
		Session session = null;

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			FitxaUtils.parseActiu(criteris, fitxaCriteria, HQL_FITXA_ALIAS, qb);

			criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					fitxaCriteria);

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Ficha fitxa = (Ficha) query.uniqueResult();

			if (fitxa != null) {
				fitxaDTO = (FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class, fitxa, fitxaCriteria.getIdioma());
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return fitxaDTO;

	}

	/**
	 * Obtiene fichas.
	 *
	 * @param fitxaCriteria
	 * @return List<FitxaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<FitxaDTO> llistarFitxes(final FitxaCriteria fitxaCriteria) {

		final List<FitxaDTO> fitxaDTOList = new ArrayList<FitxaDTO>();
		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			FitxaUtils.parseActiu(criteris, fitxaCriteria, HQL_FITXA_ALIAS, qb);
			criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					fitxaCriteria);

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Ficha> fitxaResult = query.list();

			for (final Ficha ficha : fitxaResult) {

				fitxaDTOList.add((FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class, ficha, fitxaCriteria.getIdioma()));

			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return fitxaDTOList;

	}

	/**
	 * Obtiene un documento tramite.
	 *
	 * @param documentTramitCriteria
	 * @return DocumentTramitDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public DocumentTramitDTO obtenirDocumentTramit(final DocumentTramitCriteria documentTramitCriteria) {
		DocumentTramitDTO docTramitDTO = null;
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class, HQL_DOC_TRAMITE_ALIAS,
					HQL_TRADUCCIONES_ALIAS, documentTramitCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_DOC_TRAMITE_CLASS, HQL_DOC_TRAMITE_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_DOC_TRAMITE_ALIAS, entities,
					documentTramitCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final DocumentTramit docTramit = (DocumentTramit) query.uniqueResult();
			if (docTramit != null) {
				docTramitDTO = (DocumentTramitDTO) BasicUtils.entityToDTO(DocumentTramitDTO.class, docTramit,
						documentTramitCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return docTramitDTO;
	}

	/**
	 * Obtiene documentos tramite.
	 *
	 * @param documentTramitCriteria
	 * @return List<DocumentTramitDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTramitDTO> llistarDocumentTramit(final DocumentTramitCriteria documentTramitCriteria) {
		final List<DocumentTramitDTO> documentsTramitDTOList = new ArrayList<DocumentTramitDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class, HQL_DOC_TRAMITE_ALIAS,
					HQL_TRADUCCIONES_ALIAS, documentTramitCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_DOC_TRAMITE_CLASS, HQL_DOC_TRAMITE_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_DOC_TRAMITE_ALIAS, entities,
					documentTramitCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<DocumentTramit> documentTramitsResult = query.list();
			for (final DocumentTramit documentTramit : documentTramitsResult) {
				documentsTramitDTOList.add((DocumentTramitDTO) BasicUtils.entityToDTO(DocumentTramitDTO.class,
						documentTramit, documentTramitCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return documentsTramitDTOList;
	}

	/**
	 * Obtiene documentos normativa.
	 *
	 * @param documentoNormativaCriteria
	 * @return List<DocumentNormativaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentoNormativaDTO> llistarDocumentoNormativa(
			final DocumentoNormativaCriteria documentoNormativaCriteria) {
		/*
		 * Session session = null;
		 *
		 * List<DocumentoNormativaDTO> documentosDTO = new
		 * ArrayList<DocumentoNormativaDTO>(); try {
		 *
		 * session = getSession(); StringBuilder consulta = new
		 * StringBuilder("select docNor ");
		 * consulta.append(" from DocumentoNormativa as docNor ");
		 * //consulta.append("where index(trad) = :idioma ");
		 * consulta.append(" where docNor.normativa.id = :idNormativa");
		 * consulta.append(" order by docNor.id asc");
		 *
		 * Query query = session.createQuery(consulta.toString());
		 * query.setParameter("idNormativa", documentoNormativaCriteria.getId());
		 *
		 * List<DocumentoNormativa> documentos = query.list(); for (DocumentoNormativa
		 * documento : documentos) { documentosDTO.add((DocumentoNormativaDTO)
		 * BasicUtils.entityToDTO( DocumentoNormativaDTO.class, documento,
		 * documentoNormativaCriteria.getIdioma())); } return documentosDTO; } catch
		 * (HibernateException e) { log.error(e); throw new EJBException(e); } finally {
		 * close(session); }
		 */

		final List<DocumentoNormativaDTO> documentsNormativaDTOList = new ArrayList<DocumentoNormativaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentoNormativaCriteria.class, HQL_DOC_NORMATIVA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, documentoNormativaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_DOC_NORMATIVA_CLASS, HQL_DOC_NORMATIVA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_DOC_NORMATIVA_ALIAS, entities,
					documentoNormativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<DocumentoNormativa> documentoNormativasResult = query.list();
			for (final DocumentoNormativa documentoNormativa : documentoNormativasResult) {
				documentsNormativaDTOList
						.add((DocumentoNormativaDTO) BasicUtils.entityToDTO(DocumentoNormativaDTO.class,
								documentoNormativa, documentoNormativaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return documentsNormativaDTOList;
	}

	/**
	 * Obtiene documentos Servicio.
	 *
	 * @param documentoServicioCriteria
	 * @return List<DocumentServicioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentoServicioDTO> llistarDocumentoServicio(
			final DocumentoServicioCriteria documentoServicioCriteria) {

		final List<DocumentoServicioDTO> documentsServicioDTOList = new ArrayList<DocumentoServicioDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentoServicioCriteria.class, HQL_DOC_SERVICIO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, documentoServicioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_DOC_SERVICIO_CLASS, HQL_DOC_SERVICIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_DOC_SERVICIO_ALIAS, entities,
					documentoServicioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<DocumentoServicio> documentoServiciosResult = query.list();
			for (final DocumentoServicio documentoServicio : documentoServiciosResult) {
				documentsServicioDTOList.add((DocumentoServicioDTO) BasicUtils.entityToDTO(DocumentoServicioDTO.class,
						documentoServicio, documentoServicioCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return documentsServicioDTOList;
	}

	/**
	 * Obtiene una normativa.
	 *
	 * @param normativaCriteria
	 * @return NormativaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public NormativaDTO obtenirNormativa(final NormativaCriteria normativaCriteria) {
		NormativaDTO normativaDTO = null;
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					normativaCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_NORMATIVA_CLASS, HQL_NORMATIVA_ALIAS));
			final QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Normativa normativa = (Normativa) query.uniqueResult();
			if (normativa != null) {
				normativaDTO = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa,
						normativaCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return normativaDTO;
	}

	/**
	 * Obtiene normativas.
	 *
	 * @param normativaCriteria
	 * @return List<NormativaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<NormativaDTO> llistarNormatives(final NormativaCriteria normativaCriteria) {
		final List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					normativaCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_NORMATIVA_CLASS, HQL_NORMATIVA_ALIAS));
			final QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Normativa> normativasResult = query.list();

			NormativaDTO dto;
			for (final Normativa normativa : normativasResult) {
				dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa,
						normativaCriteria.getIdioma());
				normativaDTOList.add(dto);
			}

		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return normativaDTOList;
	}

	/**
	 * Obtiene un miembro de personal.
	 *
	 * @param personalCriteria
	 * @return PersonalDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public PersonalDTO obtenirPersonal(final PersonalCriteria personalCriteria) {
		List<CriteriaObject> criteris;
		PersonalDTO personalDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Personal personal = (Personal) query.uniqueResult();
			if (personal != null) {
				personalDTO = (PersonalDTO) BasicUtils.entityToDTO(PersonalDTO.class, personal,
						personalCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return personalDTO;
	}

	/**
	 * Obtiene lista de personal.
	 *
	 * @param personalCriteria
	 * @return List<PersonalDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<PersonalDTO> llistarPersonal(final PersonalCriteria personalCriteria) {
		List<CriteriaObject> criteris;
		final List<PersonalDTO> personalDTOList = new ArrayList<PersonalDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Personal> personalResult = query.list();
			for (final Personal personal : personalResult) {
				personalDTOList.add((PersonalDTO) BasicUtils.entityToDTO(PersonalDTO.class, personal,
						personalCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return personalDTOList;
	}

	/**
	 * Obtiene un usuario.
	 *
	 * @param usuariCriteria
	 * @return UsuariDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UsuariDTO obtenirUsuari(final UsuariCriteria usuariCriteria) {
		List<CriteriaObject> criteris;
		UsuariDTO usuariDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Usuario usuari = (Usuario) query.uniqueResult();
			if (usuari != null) {
				usuariDTO = (UsuariDTO) BasicUtils.entityToDTO(UsuariDTO.class, usuari, usuariCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return usuariDTO;
	}

	/**
	 * Obtiene usuarios.
	 *
	 * @param usuariCriteria
	 * @return List<UsuariDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UsuariDTO> llistarUsuaris(final UsuariCriteria usuariCriteria) {
		List<CriteriaObject> criteris;
		final List<UsuariDTO> usuariDTOList = new ArrayList<UsuariDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Usuario> usuariResult = query.list();
			for (final Usuario usuari : usuariResult) {
				usuariDTOList
						.add((UsuariDTO) BasicUtils.entityToDTO(UsuariDTO.class, usuari, usuariCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return usuariDTOList;
	}

	/**
	 * Obtiene idiomas.
	 *
	 * @param idiomaCriteria
	 * @return List<IdiomaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<IdiomaDTO> llistarIdiomes(final IdiomaCriteria idiomaCriteria) {

		List<CriteriaObject> criteris;
		final List<IdiomaDTO> idiomaDTOList = new ArrayList<IdiomaDTO>();
		Session session = null;

		try {

			criteris = BasicUtils.parseCriterias(IdiomaCriteria.class, HQL_IDIOMA_ALIAS, idiomaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_IDIOMA_CLASS, HQL_IDIOMA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_IDIOMA_ALIAS, entities, "", "");
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Idioma> idiomaResult = query.list();
			for (final Idioma f : idiomaResult) {
				idiomaDTOList.add((IdiomaDTO) BasicUtils.entityToDTO(IdiomaDTO.class, f, idiomaCriteria.getIdioma()));
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return idiomaDTOList;

	}

	/**
	 * Obtiene un idioma.
	 *
	 * @param idiomaCriteria
	 * @return IdiomaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public IdiomaDTO obtenirIdioma(final IdiomaCriteria idiomaCriteria) {

		List<CriteriaObject> criteris;
		IdiomaDTO idiomaDTO = null;
		Session session = null;

		try {

			criteris = BasicUtils.parseCriterias(IdiomaCriteria.class, HQL_IDIOMA_ALIAS, idiomaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_IDIOMA_CLASS, HQL_IDIOMA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_IDIOMA_ALIAS, entities, "", "");
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Idioma idioma = (Idioma) query.uniqueResult();
			if (idioma != null) {
				idiomaDTO = (IdiomaDTO) BasicUtils.entityToDTO(IdiomaDTO.class, idioma, idiomaCriteria.getIdioma());
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return idiomaDTO;

	}

	/**
	 * Obtiene una tasa.
	 *
	 * @param taxaCriteria
	 * @return TaxaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public TaxaDTO obtenirTaxa(final TaxaCriteria taxaCriteria) {
		TaxaDTO taxaDTO = null;
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TaxaCriteria.class, HQL_TAXA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					taxaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TAXA_CLASS, HQL_TAXA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TAXA_ALIAS, entities, taxaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Taxa taxa = (Taxa) query.uniqueResult();
			if (taxa != null) {
				taxaDTO = (TaxaDTO) BasicUtils.entityToDTO(TaxaDTO.class, taxa, taxaCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return taxaDTO;
	}

	/**
	 * Obtiene tasas.
	 *
	 * @param taxaCriteria
	 * @return List<TaxaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<TaxaDTO> llistarTaxes(final TaxaCriteria taxaCriteria) {
		final List<TaxaDTO> taxesDTOList = new ArrayList<TaxaDTO>();
		List<CriteriaObject> criteris;
		Session sessio = null;

		try {
			criteris = BasicUtils.parseCriterias(TaxaCriteria.class, HQL_TAXA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					taxaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TAXA_CLASS, HQL_TAXA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TAXA_ALIAS, entities, taxaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			sessio = getSession();
			final Query query = qb.createQuery(sessio);
			final List<Taxa> taxesResult = query.list();
			for (final Taxa taxa : taxesResult) {
				taxesDTOList.add((TaxaDTO) BasicUtils.entityToDTO(TaxaDTO.class, taxa, taxaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(sessio);
		}

		return taxesDTOList;
	}

	/**
	 * Obtiene una agrupacion hecho vital.
	 *
	 * @param afvCriteria
	 * @return AgrupacioFetVitalDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(final AgrupacioFetVitalCriteria afvCriteria) {
		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		AgrupacioFetVitalDTO afvDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS,
					HQL_TRADUCCIONES_ALIAS, afvCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_FET_VITAL_ALIAS, entities, afvCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final AgrupacionHechoVital afv = (AgrupacionHechoVital) query.uniqueResult();
			if (afv != null) {
				afvDTO = (AgrupacioFetVitalDTO) BasicUtils.entityToDTO(AgrupacioFetVitalDTO.class, afv,
						afvCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return afvDTO;
	}

	/**
	 * Obtiene agrupaciones hecho vital.
	 *
	 * @param afvCriteria
	 * @return List<AgrupacioFetVitalDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(final AgrupacioFetVitalCriteria afvCriteria) {
		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		final List<AgrupacioFetVitalDTO> afvDTOList = new ArrayList<AgrupacioFetVitalDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS,
					HQL_TRADUCCIONES_ALIAS, afvCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_FET_VITAL_ALIAS, entities, afvCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<AgrupacionHechoVital> afvResult = query.list();
			for (final AgrupacionHechoVital afv : afvResult) {
				afvDTOList.add((AgrupacioFetVitalDTO) BasicUtils.entityToDTO(AgrupacioFetVitalDTO.class, afv,
						afvCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return afvDTOList;
	}

	/**
	 * Obtiene una agrupacion materia.
	 *
	 * @param amCriteria
	 * @return AgrupacioMateriaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public AgrupacioMateriaDTO obtenirAgrupacioMateria(final AgrupacioMateriaCriteria amCriteria) {
		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		AgrupacioMateriaDTO afvDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACIO_MATERIA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, amCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_AGRUPACIO_MATERIA_CLASS, HQL_AGRUPACIO_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_MATERIA_ALIAS, entities, amCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final AgrupacionMateria am = (AgrupacionMateria) query.uniqueResult();
			if (am != null) {
				afvDTO = (AgrupacioMateriaDTO) BasicUtils.entityToDTO(AgrupacioMateriaDTO.class, am,
						amCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return afvDTO;
	}

	/**
	 * Obtiene agrupaciones materia.
	 *
	 * @param amCriteria
	 * @return List<AgrupacioMateriaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(final AgrupacioMateriaCriteria amCriteria) {
		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		final List<AgrupacioMateriaDTO> amDTOList = new ArrayList<AgrupacioMateriaDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACIO_MATERIA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, amCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_AGRUPACIO_MATERIA_CLASS, HQL_AGRUPACIO_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_MATERIA_ALIAS, entities, amCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<AgrupacionMateria> amResult = query.list();
			for (final AgrupacionMateria am : amResult) {
				amDTOList.add((AgrupacioMateriaDTO) BasicUtils.entityToDTO(AgrupacioMateriaDTO.class, am,
						amCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return amDTOList;
	}

	/**
	 * Obtiene un boletin.
	 *
	 * @param butlletiCriteria
	 * @return ButlletiDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ButlletiDTO obtenirButlleti(final ButlletiCriteria butlletiCriteria) {
		List<CriteriaObject> criteris;
		ButlletiDTO butlletiDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(ButlletiCriteria.class, HQL_BUTLLETI_ALIAS, butlletiCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_BUTLLETI_CLASS, HQL_BUTLLETI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_BUTLLETI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Boletin butlleti = (Boletin) query.uniqueResult();
			if (butlleti != null) {
				butlletiDTO = (ButlletiDTO) BasicUtils.entityToDTO(ButlletiDTO.class, butlleti,
						butlletiCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return butlletiDTO;
	}

	/**
	 * Obtiene lista de boletines.
	 *
	 * @param butlletiCriteria
	 * @return List<ButlletiDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<ButlletiDTO> llistarButlletins(final ButlletiCriteria butlletiCriteria) {
		List<CriteriaObject> criteris;
		final List<ButlletiDTO> butlletiDTOList = new ArrayList<ButlletiDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(ButlletiCriteria.class, HQL_BUTLLETI_ALIAS, butlletiCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_BUTLLETI_CLASS, HQL_BUTLLETI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_BUTLLETI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Boletin> butlletiResult = query.list();
			for (final Boletin boletin : butlletiResult) {
				butlletiDTOList.add(
						(ButlletiDTO) BasicUtils.entityToDTO(ButlletiDTO.class, boletin, butlletiCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return butlletiDTOList;
	}

	/**
	 * Obtiene un documento.
	 *
	 * @param documentCriteria
	 * @return DocumentDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public DocumentDTO obtenirDocument(final DocumentCriteria docCriteria) {
		List<CriteriaObject> criteris;
		DocumentDTO docDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentCriteria.class, HQL_DOCUMENT_ALIAS, HQL_TRADUCCIONES_ALIAS,
					docCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_DOCUMENT_CLASS, HQL_DOCUMENT_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_DOCUMENT_ALIAS, entities, docCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Documento doc = (Documento) query.uniqueResult();
			if (doc != null) {
				docDTO = (DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class, doc, docCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return docDTO;
	}

	/**
	 * Obtiene lista de documentos.
	 *
	 * @param documentCriteria
	 * @return List<DocumentDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentDTO> llistarDocuments(final DocumentCriteria docCriteria) {
		List<CriteriaObject> criteris;
		final List<DocumentDTO> docDTOList = new ArrayList<DocumentDTO>();
		Session sessio = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentCriteria.class, HQL_DOCUMENT_ALIAS, HQL_TRADUCCIONES_ALIAS,
					docCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_DOCUMENT_CLASS, HQL_DOCUMENT_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_DOCUMENT_ALIAS, entities, docCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			sessio = getSession();
			final Query query = qb.createQuery(sessio);
			final List<Documento> docResult = query.list();
			for (final Documento doc : docResult) {
				docDTOList.add((DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class, doc, docCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(sessio);
		}

		return docDTOList;
	}

	/**
	 * Obtiene un edificio.
	 *
	 * @param edificiCriteria
	 * @return EdificiDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public EdificiDTO obtenirEdifici(final EdificiCriteria edificiCriteria) {
		List<CriteriaObject> criteris;
		EdificiDTO edificiDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, HQL_TRADUCCIONES_ALIAS,
					edificiCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_EDIFICI_ALIAS, entities, edificiCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Edificio edifici = (Edificio) query.uniqueResult();
			if (edifici != null) {
				edificiDTO = (EdificiDTO) BasicUtils.entityToDTO(EdificiDTO.class, edifici,
						edificiCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return edificiDTO;
	}

	/**
	 * Obtiene edificios.
	 *
	 * @param edificiCriteria
	 * @return List<EdificiDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<EdificiDTO> llistarEdificis(final EdificiCriteria edificiCriteria) {
		List<CriteriaObject> criteris;
		final List<EdificiDTO> edificisDTOList = new ArrayList<EdificiDTO>();
		Session sessio = null;

		try {
			criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, HQL_TRADUCCIONES_ALIAS,
					edificiCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_EDIFICI_ALIAS, entities, edificiCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			sessio = getSession();
			final Query query = qb.createQuery(sessio);
			final List<Edificio> edificiResult = query.list();
			for (final Edificio ed : edificiResult) {
				edificisDTOList
						.add((EdificiDTO) BasicUtils.entityToDTO(EdificiDTO.class, ed, edificiCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(sessio);
		}

		return edificisDTOList;
	}

	/**
	 * Obtiene un enlace.
	 *
	 * @param enllacCriteria
	 * @return EnllacDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public EnllacDTO obtenirEnllac(final EnllacCriteria enllacCriteria) {
		List<CriteriaObject> criteris;
		EnllacDTO enllacDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(EnllacCriteria.class, HQL_ENLLAC_ALIAS, HQL_TRADUCCIONES_ALIAS,
					enllacCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ENLLAC_CLASS, HQL_ENLLAC_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ENLLAC_ALIAS, entities, enllacCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Enlace enllac = (Enlace) query.uniqueResult();
			if (enllac != null) {
				enllacDTO = (EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class, enllac, enllacCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return enllacDTO;
	}

	/**
	 * Obtiene un enlace.
	 *
	 * @param enllacTelematicoCriteria
	 * @return EnllacTelematicoDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public EnllacTelematicoDTO obtenirEnllacTelematico(final EnllacTelematicoCriteria enllacCriteria) {

		final EnllacTelematicoDTO enllacDTO = new EnllacTelematicoDTO();

		String idTramite = "";
		String numVersion = "";
		final String idioma = enllacCriteria.getIdioma();
		final Long idplataforma;
		final Long idplantilla;
		String parametros = "";
		final String idTramiteRolsac = String.valueOf(enllacCriteria.getIdentificador());

		if (enllacCriteria.isServicio()) {
			final ServicioCriteria servicioCriteria = new ServicioCriteria();
			servicioCriteria.setIdioma(idioma);
			servicioCriteria.setId(idTramiteRolsac);
			final ServicioDTO servicio = obtenirServicio(servicioCriteria);
			idTramite = servicio.getTramiteId();
			numVersion = servicio.getTramiteVersion();
			idplataforma = servicio.getPlataforma();
			idplantilla = null;
			parametros = servicio.getParametros();
		} else {

			final TramitCriteria tramitCriteria = new TramitCriteria();
			tramitCriteria.setIdioma(idioma);
			tramitCriteria.setId(idTramiteRolsac);
			final TramitDTO tramit = obtenirTramit(tramitCriteria);
			idTramite = tramit.getIdTraTel();
			numVersion = String.valueOf(tramit.getVersio());
			idplataforma = tramit.getPlataforma();
			idplantilla = tramit.getTramitePlantilla();
			parametros = tramit.getParametros();
		}

		String url;
		if (idplantilla == null) {

			if (idplataforma == null) {
				log.error(" Error obteniendo la plataforma con  idTramiteRolsac:" + idTramiteRolsac + " idioma:"
						+ idioma + " numversion:" + numVersion);
				throw new EJBException("El idplataforma es nulo con idTramite: " + idTramiteRolsac);
			}

			final PlataformaCriteria plataformaCriteria = new PlataformaCriteria();
			plataformaCriteria.setIdioma(idioma);
			plataformaCriteria.setId(idplataforma.toString());
			final PlataformaDTO plataforma = obtenirPlataforma(plataformaCriteria);
			url = plataforma.getUrlAcceso();

			url = url.replace("${idTramitePlataforma}", idTramite);
			url = url.replace("${versionTramitePlatorma}", numVersion);
			if (parametros == null) {
				parametros = "";
			}
			url = url.replace("${parametros}", parametros);
			url = url.replace("${servicio}", String.valueOf(enllacCriteria.isServicio()));
			url = url.replace("${idTramiteRolsac}", idTramiteRolsac);
		} else {
			final PlantillaCriteria plantillaCriteria = new PlantillaCriteria();
			plantillaCriteria.setIdioma(idioma);
			plantillaCriteria.setId(idplantilla.toString());
			final PlantillaDTO plantilla = obtenirPlantilla(plantillaCriteria);

			final PlataformaCriteria plataformaCriteria = new PlataformaCriteria();
			plataformaCriteria.setIdioma(idioma);
			plataformaCriteria.setId(plantilla.getPlataforma().toString());
			final PlataformaDTO plataforma = obtenirPlataforma(plataformaCriteria);
			url = plataforma.getUrlAcceso();

			url = url.replace("${idTramitePlataforma}", plantilla.getIdentificador());
			url = url.replace("${versionTramitePlatorma}", plantilla.getVersion());
			if (parametros == null) {
				parametros = "";
			}
			url = url.replace("${parametros}", plantilla.getParametros());
			url = url.replace("${servicio}", String.valueOf(enllacCriteria.isServicio()));
			url = url.replace("${idTramiteRolsac}", idTramiteRolsac);
		}
		enllacDTO.setEnlace(url);
		return enllacDTO;
	}

	/**
	 * Obtiene lista de enlaces.
	 *
	 * @param enllacCriteria
	 * @return List<EnllacDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<EnllacDTO> llistarEnllacos(final EnllacCriteria enllacCriteria) {
		List<CriteriaObject> criteris;
		final List<EnllacDTO> enllacDTOList = new ArrayList<EnllacDTO>();
		Session sessio = null;

		try {
			criteris = BasicUtils.parseCriterias(EnllacCriteria.class, HQL_ENLLAC_ALIAS, HQL_TRADUCCIONES_ALIAS,
					enllacCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ENLLAC_CLASS, HQL_ENLLAC_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ENLLAC_ALIAS, entities, enllacCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			sessio = getSession();
			final Query query = qb.createQuery(sessio);
			final List<Enlace> enllacResult = query.list();
			for (final Enlace e : enllacResult) {
				enllacDTOList.add((EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class, e, enllacCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(sessio);
		}

		return enllacDTOList;
	}

	/**
	 * Obtiene un espacio territorial.
	 *
	 * @param etCriteria
	 * @return EspaiTerritorialDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public EspaiTerritorialDTO obtenirEspaiTerritorial(final EspaiTerritorialCriteria etCriteria) {
		List<CriteriaObject> criteris;
		EspaiTerritorialDTO etDTO = null;
		Session sessio = null;

		try {
			criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ESPAI_TERRITORIAL_ALIAS,
					HQL_TRADUCCIONES_ALIAS, etCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ESPAI_TERRITORIAL_CLASS, HQL_ESPAI_TERRITORIAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ESPAI_TERRITORIAL_ALIAS, entities, etCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			sessio = getSession();
			final Query query = qb.createQuery(sessio);
			final EspacioTerritorial et = (EspacioTerritorial) query.uniqueResult();
			if (et != null) {
				etDTO = (EspaiTerritorialDTO) BasicUtils.entityToDTO(EspaiTerritorialDTO.class, et,
						etCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(sessio);
		}

		return etDTO;
	}

	/**
	 * Obtiene espacios territoriales.
	 *
	 * @param etCriteria
	 * @return List<EspaiTerritorialDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(final EspaiTerritorialCriteria etCriteria) {
		List<CriteriaObject> criteris;
		final List<EspaiTerritorialDTO> etDTOList = new ArrayList<EspaiTerritorialDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ESPAI_TERRITORIAL_ALIAS,
					HQL_TRADUCCIONES_ALIAS, etCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ESPAI_TERRITORIAL_CLASS, HQL_ESPAI_TERRITORIAL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ESPAI_TERRITORIAL_ALIAS, entities, etCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<EspacioTerritorial> etResult = query.list();
			for (final EspacioTerritorial e : etResult) {
				etDTOList.add((EspaiTerritorialDTO) BasicUtils.entityToDTO(EspaiTerritorialDTO.class, e,
						etCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return etDTOList;
	}

	/**
	 * Obtiene una familia.
	 *
	 * @param familiaCriteria
	 * @return FamiliaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FamiliaDTO obtenirFamilia(final FamiliaCriteria familiaCriteria) {
		List<CriteriaObject> criteris;
		FamiliaDTO familiaDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					familiaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FAMILIA_ALIAS, entities, familiaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Familia familia = (Familia) query.uniqueResult();
			if (familia != null) {
				familiaDTO = (FamiliaDTO) BasicUtils.entityToDTO(FamiliaDTO.class, familia,
						familiaCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return familiaDTO;
	}

	/**
	 * Obtiene familias.
	 *
	 * @param familiaCriteria
	 * @return List<FamiliaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<FamiliaDTO> llistarFamilies(final FamiliaCriteria familiaCriteria) {
		List<CriteriaObject> criteris;
		final List<FamiliaDTO> familiaDTOList = new ArrayList<FamiliaDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					familiaCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FAMILIA_ALIAS, entities, familiaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Familia> familiaResult = query.list();
			for (final Familia f : familiaResult) {
				familiaDTOList
						.add((FamiliaDTO) BasicUtils.entityToDTO(FamiliaDTO.class, f, familiaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return familiaDTOList;
	}

	/**
	 * Obtiene publico objetivo.
	 *
	 * @param publicObjectiuCriteria
	 * @return PublicObjectiuDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public PublicObjectiuDTO obtenirPublicObjectiu(final PublicObjectiuCriteria publicObjectiuCriteria) {
		List<CriteriaObject> criteris;
		PublicObjectiuDTO publicObjectiuDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PublicObjectiuCriteria.class, HQL_PUBLIC_OBJECTIU_ALIAS,
					HQL_TRADUCCIONES_ALIAS, publicObjectiuCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PUBLIC_OBJECTIU_CLASS, HQL_PUBLIC_OBJECTIU_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PUBLIC_OBJECTIU_ALIAS, entities,
					publicObjectiuCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final PublicoObjetivo publicoObjetivo = (PublicoObjetivo) query.uniqueResult();
			if (publicoObjetivo != null) {
				publicObjectiuDTO = (PublicObjectiuDTO) BasicUtils.entityToDTO(PublicObjectiuDTO.class, publicoObjetivo,
						publicObjectiuCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return publicObjectiuDTO;
	}

	/**
	 * Obtiene publicos objetivo.
	 *
	 * @param publicObjectiuCriteria
	 * @return List<PublicObjectiuDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<PublicObjectiuDTO> llistarPublicsObjectius(final PublicObjectiuCriteria poCriteria) {
		List<CriteriaObject> criteris;
		final List<PublicObjectiuDTO> poDTOList = new ArrayList<PublicObjectiuDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PublicObjectiuCriteria.class, HQL_PUBLIC_OBJECTIU_ALIAS,
					HQL_TRADUCCIONES_ALIAS, poCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PUBLIC_OBJECTIU_CLASS, HQL_PUBLIC_OBJECTIU_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PUBLIC_OBJECTIU_ALIAS, entities, poCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<PublicoObjetivo> poResult = query.list();
			for (final PublicoObjetivo po : poResult) {
				poDTOList.add((PublicObjectiuDTO) BasicUtils.entityToDTO(PublicObjectiuDTO.class, po,
						poCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return poDTOList;
	}

	/**
	 * Obtiene ficha UA.
	 *
	 * @param fuaCriteria
	 * @return FitxaUADTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FitxaUADTO obtenirFitxaUA(final FitxaUACriteria fuaCriteria) {
		List<CriteriaObject> criteris;
		FitxaUADTO fuaDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FITXA_UA_ALIAS, fuaCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FITXA_UA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final FichaUA fua = (FichaUA) query.uniqueResult();
			if (fua != null) {
				fuaDTO = (FitxaUADTO) BasicUtils.entityToDTO(FitxaUADTO.class, fua, fuaCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return fuaDTO;
	}

	/**
	 * Obtiene lista de fichas UA.
	 *
	 * @param fuaCriteria
	 * @return List<FitxaUADTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<FitxaUADTO> llistarFitxesUA(final FitxaUACriteria fuaCriteria) {
		List<CriteriaObject> criteris;
		final List<FitxaUADTO> fuaDTOList = new ArrayList<FitxaUADTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FITXA_UA_ALIAS, fuaCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FITXA_UA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<FichaUA> fuaResult = query.list();
			for (final FichaUA fua : fuaResult) {
				fuaDTOList.add((FitxaUADTO) BasicUtils.entityToDTO(FitxaUADTO.class, fua, fuaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return fuaDTOList;
	}

	/**
	 * Obtiene un formulario.
	 *
	 * @param formCriteria
	 * @return formulariDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FormulariDTO obtenirFormulari(final FormulariCriteria formCriteria) {
		List<CriteriaObject> criteris;
		FormulariDTO formDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FormulariCriteria.class, HQL_FORMULARI_ALIAS, formCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FORMULARI_CLASS, HQL_FORMULARI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FORMULARI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Formulario form = (Formulario) query.uniqueResult();
			if (form != null) {
				formDTO = (FormulariDTO) BasicUtils.entityToDTO(FormulariDTO.class, form, formCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return formDTO;
	}

	/**
	 * Obtiene formularios.
	 *
	 * @param formCriteria
	 * @return List<formulariDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<FormulariDTO> llistarFormularis(final FormulariCriteria formCriteria) {
		List<CriteriaObject> criteris;
		final List<FormulariDTO> formDTOList = new ArrayList<FormulariDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(FormulariCriteria.class, HQL_FORMULARI_ALIAS, formCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FORMULARI_CLASS, HQL_FORMULARI_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FORMULARI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Formulario> formResult = query.list();
			for (final Formulario form : formResult) {
				formDTOList
						.add((FormulariDTO) BasicUtils.entityToDTO(FormulariDTO.class, form, formCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return formDTOList;
	}

	/**
	 * Obtiene un icono familia.
	 *
	 * @param ifCriteria
	 * @return IconaFamiliaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public IconaFamiliaDTO obtenirIconaFamilia(final IconaFamiliaCriteria ifCriteria) {
		List<CriteriaObject> criteris;
		IconaFamiliaDTO ifDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_FAMILIA_ALIAS, ifCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ICONA_FAMILIA_CLASS, HQL_ICONA_FAMILIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ICONA_FAMILIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final IconoFamilia icoFam = (IconoFamilia) query.uniqueResult();
			if (icoFam != null) {
				ifDTO = (IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, icoFam, ifCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return ifDTO;
	}

	/**
	 * Obtiene lista de iconos familia.
	 *
	 * @param ifCriteria
	 * @return List<IconaFamiliaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<IconaFamiliaDTO> llistarIconesFamilies(final IconaFamiliaCriteria ifCriteria) {
		List<CriteriaObject> criteris;
		final List<IconaFamiliaDTO> ifDTOList = new ArrayList<IconaFamiliaDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_FAMILIA_ALIAS, ifCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ICONA_FAMILIA_CLASS, HQL_ICONA_FAMILIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ICONA_FAMILIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<IconoFamilia> ifResult = query.list();
			for (final IconoFamilia iFam : ifResult) {
				ifDTOList.add(
						(IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, iFam, ifCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return ifDTOList;
	}

	/**
	 * Obtiene un icono materia.
	 *
	 * @param imCriteria
	 * @return IconaMateriaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public IconaMateriaDTO obtenirIconaMateria(final IconaMateriaCriteria imCriteria) {
		List<CriteriaObject> criteris;
		IconaMateriaDTO imDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, imCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final IconoMateria icoMat = (IconoMateria) query.uniqueResult();
			if (icoMat != null) {
				imDTO = (IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, icoMat, imCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return imDTO;
	}

	/**
	 * Obtiene iconos materia.
	 *
	 * @param imCriteria
	 * @return List<IconaMateriaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<IconaMateriaDTO> llistarIconesMateries(final IconaMateriaCriteria imCriteria) {
		List<CriteriaObject> criteris;
		final List<IconaMateriaDTO> imDTOList = new ArrayList<IconaMateriaDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, imCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<IconoMateria> imResult = query.list();
			for (final IconoMateria iMat : imResult) {
				imDTOList.add(
						(IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, iMat, imCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return imDTOList;
	}

	/**
	 * Obtiene una materia agrupacion.
	 *
	 * @param maCriteria
	 * @return MateriaAgrupacioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public MateriaAgrupacioDTO obtenirMateriaAgrupacio(final MateriaAgrupacioCriteria maCriteria) {
		List<CriteriaObject> criteris;
		MateriaAgrupacioDTO imDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(MateriaAgrupacioCriteria.class, HQL_MATERIA_AGRUPACIO_ALIAS,
					maCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_MATERIA_AGRUPACIO_CLASS, HQL_MATERIA_AGRUPACIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACIO_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final MateriaAgrupacionM ma = (MateriaAgrupacionM) query.uniqueResult();
			if (ma != null) {
				imDTO = (MateriaAgrupacioDTO) BasicUtils.entityToDTO(MateriaAgrupacioDTO.class, ma,
						maCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return imDTO;
	}

	/**
	 * Obtiene lista de materias agrupacion.
	 *
	 * @param maCriteria
	 * @return List<MateriaAgrupacioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(final MateriaAgrupacioCriteria maCriteria) {
		List<CriteriaObject> criteris;
		final List<MateriaAgrupacioDTO> imDTOList = new ArrayList<MateriaAgrupacioDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(MateriaAgrupacioCriteria.class, HQL_MATERIA_AGRUPACIO_ALIAS,
					maCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_MATERIA_AGRUPACIO_CLASS, HQL_MATERIA_AGRUPACIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACIO_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<MateriaAgrupacionM> maResult = query.list();
			for (final MateriaAgrupacionM ma : maResult) {
				imDTOList.add((MateriaAgrupacioDTO) BasicUtils.entityToDTO(MateriaAgrupacioDTO.class, ma,
						maCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return imDTOList;
	}

	/**
	 * Obtiene un perfil.
	 *
	 * @param perfilCriteria
	 * @return PerfilDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public PerfilDTO obtenirPerfil(final PerfilCriteria perfilCriteria) {
		List<CriteriaObject> criteris;
		PerfilDTO perfilDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, HQL_TRADUCCIONES_ALIAS,
					perfilCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PERFIL_ALIAS, entities, perfilCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final PerfilCiudadano perfil = (PerfilCiudadano) query.uniqueResult();
			if (perfil != null) {
				perfilDTO = (PerfilDTO) BasicUtils.entityToDTO(PerfilDTO.class, perfil, perfilCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return perfilDTO;
	}

	/**
	 * Obtiene perfiles.
	 *
	 * @param perfilCriteria
	 * @return List<PerfilDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<PerfilDTO> llistarPerfils(final PerfilCriteria perfilCriteria) {
		List<CriteriaObject> criteris;
		final List<PerfilDTO> perfilDTOList = new ArrayList<PerfilDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, HQL_TRADUCCIONES_ALIAS,
					perfilCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_PERFIL_ALIAS, entities, perfilCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<PerfilCiudadano> perfilResult = query.list();
			for (final PerfilCiudadano p : perfilResult) {
				perfilDTOList.add((PerfilDTO) BasicUtils.entityToDTO(PerfilDTO.class, p, perfilCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return perfilDTOList;
	}

	/**
	 * Obtiene una seccion.
	 *
	 * @param seccioCriteria
	 * @return SeccioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public SeccioDTO obtenirSeccio(final SeccioCriteria seccioCriteria) {
		List<CriteriaObject> criteris;
		SeccioDTO seccioDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, HQL_TRADUCCIONES_ALIAS,
					seccioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_SECCIO_ALIAS, entities, seccioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Seccion seccio = (Seccion) query.uniqueResult();
			if (seccio != null) {
				seccioDTO = (SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class, seccio, seccioCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return seccioDTO;
	}

	/**
	 * Obtiene secciones.
	 *
	 * @param seccioCriteria
	 * @return List<SeccioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<SeccioDTO> llistarSeccions(final SeccioCriteria seccioCriteria) {
		List<CriteriaObject> criteris;
		final List<SeccioDTO> seccioDTOList = new ArrayList<SeccioDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, HQL_TRADUCCIONES_ALIAS,
					seccioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_SECCIO_ALIAS, entities, seccioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Seccion> seccioResult = query.list();
			for (final Seccion s : seccioResult) {
				seccioDTOList.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class, s, seccioCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return seccioDTOList;
	}

	/**
	 * Obtiene una unidad materia.
	 *
	 * @param umCriteria
	 * @return UnitatMateriaDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnitatMateriaDTO obtenirUnitatMateria(final UnitatMateriaCriteria umCriteria) {
		List<CriteriaObject> criteris;
		UnitatMateriaDTO umDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(UnitatMateriaCriteria.class, HQL_UNITAT_MATERIA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, umCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_UNITAT_MATERIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final UnidadMateria um = (UnidadMateria) query.uniqueResult();
			if (um != null) {
				umDTO = (UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class, um, umCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return umDTO;
	}

	/**
	 * Obtiene unidades materia.
	 *
	 * @param umCriteria
	 * @return List<UnitatMateriaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UnitatMateriaDTO> llistarUnitatsMateries(final UnitatMateriaCriteria umCriteria) {
		List<CriteriaObject> criteris;
		final List<UnitatMateriaDTO> umDTOList = new ArrayList<UnitatMateriaDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(UnitatMateriaCriteria.class, HQL_UNITAT_MATERIA_ALIAS,
					HQL_TRADUCCIONES_ALIAS, umCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_UNITAT_MATERIA_ALIAS, entities, umCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<UnidadMateria> umResult = query.list();
			for (final UnidadMateria um : umResult) {
				umDTOList.add(
						(UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class, um, umCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return umDTOList;
	}

	/**
	 * Obtiene un tipo.
	 *
	 * @param tipusCriteria
	 * @return TipusDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public TipusDTO obtenirTipus(final TipusCriteria tipusCriteria) {
		List<CriteriaObject> criteris;
		TipusDTO tipusDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TipusCriteria.class, HQL_TIPUS_ALIAS, HQL_TRADUCCIONES_ALIAS,
					tipusCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TIPUS_CLASS, HQL_TIPUS_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TIPUS_ALIAS, entities, tipusCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Tipo tipus = (Tipo) query.uniqueResult();
			if (tipus != null) {
				tipusDTO = (TipusDTO) BasicUtils.entityToDTO(TipusDTO.class, tipus, tipusCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return tipusDTO;
	}

	/**
	 * Obtiene tipos.
	 *
	 * @param tipusCriteria
	 * @return List<TipusDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<TipusDTO> llistarTipus(final TipusCriteria tipusCriteria) {
		List<CriteriaObject> criteris;
		final List<TipusDTO> tipusDTOList = new ArrayList<TipusDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TipusCriteria.class, HQL_TIPUS_ALIAS, HQL_TRADUCCIONES_ALIAS,
					tipusCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TIPUS_CLASS, HQL_TIPUS_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TIPUS_ALIAS, entities, tipusCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Tipo> tipusResult = query.list();
			for (final Tipo t : tipusResult) {
				tipusDTOList.add((TipusDTO) BasicUtils.entityToDTO(TipusDTO.class, t, tipusCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return tipusDTOList;
	}

	/**
	 * Obtiene un tipo afectacion.
	 *
	 * @param tipusAfectacioCriteria
	 * @return TipusAfectacioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public TipusAfectacioDTO obtenirTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria) {
		List<CriteriaObject> criteris;
		TipusAfectacioDTO tipusAfectacioDTO = null;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TipusAfectacioCriteria.class, HQL_TIPUS_AFECTACIO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, tipusAfectacioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TIPUS_AFECTACIO_CLASS, HQL_TIPUS_AFECTACIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TIPUS_AFECTACIO_ALIAS, entities,
					tipusAfectacioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final TipoAfectacion tipusAfectacio = (TipoAfectacion) query.uniqueResult();
			if (tipusAfectacio != null) {
				tipusAfectacioDTO = (TipusAfectacioDTO) BasicUtils.entityToDTO(TipusAfectacioDTO.class, tipusAfectacio,
						tipusAfectacioCriteria.getIdioma());
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return tipusAfectacioDTO;
	}

	/**
	 * Obtiene tipos afectacion.
	 *
	 * @param tipusAfectacioCriteria
	 * @return List<TipusAfectacioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<TipusAfectacioDTO> llistarTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria) {
		List<CriteriaObject> criteris;
		final List<TipusAfectacioDTO> tipusAfectacioDTOList = new ArrayList<TipusAfectacioDTO>();
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(TipusAfectacioCriteria.class, HQL_TIPUS_AFECTACIO_ALIAS,
					HQL_TRADUCCIONES_ALIAS, tipusAfectacioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TIPUS_AFECTACIO_CLASS, HQL_TIPUS_AFECTACIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_TIPUS_AFECTACIO_ALIAS, entities,
					tipusAfectacioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<TipoAfectacion> tipoAfectacionResult = query.list();
			for (final TipoAfectacion t : tipoAfectacionResult) {
				tipusAfectacioDTOList.add((TipusAfectacioDTO) BasicUtils.entityToDTO(TipusAfectacioDTO.class, t,
						tipusAfectacioCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return tipusAfectacioDTOList;
	}

	/**
	 * Obtiene lista de tipos de iniciación.
	 *
	 * @param iniciacioCriteria
	 * @return List<IniciacioDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<IniciacioDTO> llistarTipusIniciacions(final IniciacioCriteria iniciacioCriteria) {

		List<CriteriaObject> criteris;
		final List<IniciacioDTO> iniciacioDTOList = new ArrayList<IniciacioDTO>();
		Session session = null;

		try {

			criteris = BasicUtils.parseCriterias(IniciacioCriteria.class, HQL_INICIACIO_ALIAS, HQL_TRADUCCIONES_ALIAS,
					iniciacioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_INICIACIO_CLASS, HQL_INICIACIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_INICIACIO_ALIAS, entities, iniciacioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<Iniciacion> iniciacioResult = query.list();

			for (final Iniciacion iniciacion : iniciacioResult) {
				iniciacioDTOList.add((IniciacioDTO) BasicUtils.entityToDTO(IniciacioDTO.class, iniciacion,
						iniciacioCriteria.getIdioma()));
			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return iniciacioDTOList;

	}

	/**
	 * Obtiene un tipo de iniciación.
	 *
	 * @param iniciacioCriteria
	 * @return IniciacioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public IniciacioDTO obtenirTipusIniciacio(final IniciacioCriteria iniciacioCriteria) {

		List<CriteriaObject> criteris;
		IniciacioDTO iniciacioDTO = null;
		Session session = null;

		try {

			criteris = BasicUtils.parseCriterias(IniciacioCriteria.class, HQL_INICIACIO_ALIAS, HQL_TRADUCCIONES_ALIAS,
					iniciacioCriteria);

			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_INICIACIO_CLASS, HQL_INICIACIO_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_INICIACIO_ALIAS, entities, iniciacioCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final Iniciacion iniciacion = (Iniciacion) query.uniqueResult();

			if (iniciacion != null) {

				iniciacioDTO = (IniciacioDTO) BasicUtils.entityToDTO(IniciacioDTO.class, iniciacion,
						iniciacioCriteria.getIdioma());

			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final CriteriaObjectParseException e) {

			log.error(e);
			throw new EJBException(e);

		} catch (final QueryBuilderException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return iniciacioDTO;

	}

	/**
	 * Cuenta fitxas.
	 *
	 * @param fitxaCriteria
	 * @return int
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumFitxes(final FitxaCriteria fitxaCriteria) {

		Integer numResultats = -1;
		final List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));

			final QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS, true);

			FitxaUtils.parseActiu(criteris, fitxaCriteria, HQL_FITXA_ALIAS, qb);

			criteris.addAll(BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, HQL_TRADUCCIONES_ALIAS,
					fitxaCriteria));

			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			numResultats = ((Integer) query.uniqueResult()).intValue();

		} catch (final HibernateException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
			throw new EJBException(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
			throw new EJBException(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene un silencio administrativo.
	 *
	 * @param codSilencio
	 * @param idioma
	 * @return SilencioDTO
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */

	public SilencioDTO obtenirSilenci(final Long codSilencio, final String idioma) {

		SilencioDTO silencioDTO = null;
		Session session = null;

		try {

			session = getSession();
			final StringBuilder consulta = new StringBuilder("select sil ");
			consulta.append("from SilencioAdm as sil, sil.traducciones as trad ");
			consulta.append("where index(trad) = :idioma ");
			consulta.append(" and sil.id = :codSilencio");
			consulta.append(" order by trad.nombre asc");

			final Query query = session.createQuery(consulta.toString());
			query.setParameter("idioma", idioma);
			query.setParameter("codSilencio", codSilencio);

			final SilencioAdm silencio = (SilencioAdm) query.uniqueResult();

			if (silencio != null) {

				silencioDTO = (SilencioDTO) BasicUtils.entityToDTO(SilencioDTO.class, silencio, idioma);

			}

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

		return silencioDTO;

	}

	public Archivo obtenirArxiu(final Long idArxiu) {

		Session session = null;

		try {

			session = getSession();
			final StringBuilder consulta = new StringBuilder("select arc ");
			consulta.append("from Archivo as arc ");
			consulta.append("where arc.id = :idArxiu ");

			final Query query = session.createQuery(consulta.toString());
			query.setParameter("idArxiu", idArxiu);

			final Archivo archivo = (Archivo) query.uniqueResult();

			return archivo;

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}

	}

	public Documento getDocumentArchiu(final Long idArxiu) {
		Session session = null;

		try {

			session = getSession();
			final StringBuilder consulta = new StringBuilder("select docu ");
			consulta.append("from Documento as docu join docu.traducciones as tradDocu ");
			consulta.append("where tradDocu.archivo.id=:code  ");

			final Query query = session.createQuery(consulta.toString());
			query.setParameter("code", idArxiu);

			final Documento archivo = (Documento) query.uniqueResult();

			return archivo;

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}
	}

	public DocumentTramit getDocumentTramitArchiu(final Long idArxiu) {
		Session session = null;

		try {

			session = getSession();
			final StringBuilder consulta = new StringBuilder("select docu ");
			consulta.append("from DocumentTramit docu join docu.traducciones as tradDocu ");
			consulta.append("where tradDocu.archivo.id=:code  ");

			final Query query = session.createQuery(consulta.toString());
			query.setParameter("code", idArxiu);

			final DocumentTramit archivo = (DocumentTramit) query.uniqueResult();

			return archivo;

		} catch (final HibernateException e) {

			log.error(e);
			throw new EJBException(e);

		} finally {

			close(session);

		}
	}

}