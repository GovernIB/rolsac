package es.caib.rolsac.api.v2.unitatAdministrativa.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaUtils;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.co.NormativaByUnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.tractament.TractamentCriteria;
import es.caib.rolsac.api.v2.tractament.TractamentDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

/**
 * SessionBean para consultas de unidades administrativas.
 *
 * @ejb.bean
 *  name="sac/api/UnitatAdministrativaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.unitatAdministrativa.ejb.UnitatAdministrativaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class UnitatAdministrativaQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = -8879384159290107289L;

	private static Log log = LogFactory.getLog(UnitatAdministrativaQueryServiceEJB.class);

	private static final String HQL_UA_CLASS = "UnidadAdministrativa";
	private static final String HQL_UA_ALIAS = "ua";
	private static final String HQL_UA_HIJOS_CLASS = HQL_UA_ALIAS + ".hijos";
	private static final String HQL_UA_HIJOS_ALIAS = "hua";
	private static final String HQL_EDIFICI_CLASS = HQL_UA_ALIAS + ".edificios";
	private static final String HQL_EDIFICI_ALIAS = "ed";
	private static final String HQL_PERSONAL_CLASS = HQL_UA_ALIAS + ".personal";
	private static final String HQL_PERSONAL_ALIAS = "per";
	private static final String HQL_NORMATIVA_LOCAL_CLASS = "NormativaLocal";
	private static final String HQL_NORMATIVA_ALIAS = "n";
	private static final String HQL_PROCEDIMIENTO_CLASS = HQL_UA_ALIAS + ".procedimientos";
	private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
	private static final String HQL_TRAMIT_CLASS = HQL_UA_ALIAS + ".tramites";
	private static final String HQL_TRAMIT_ALIAS = "t";
	private static final String HQL_UNITAT_MATERIA_CLASS = HQL_UA_ALIAS + ".unidadesMaterias";
	private static final String HQL_UNITAT_MATERIA_ALIAS = "um";
	private static final String HQL_MATERIA_CLASS = HQL_UNITAT_MATERIA_ALIAS + ".materia";
	private static final String HQL_MATERIA_ALIAS = "um";
	private static final String HQL_USUARI_CLASS = HQL_UA_ALIAS + ".usuarios";
	private static final String HQL_USUARI_ALIAS = "usu";
	private static final String HQL_FITXA_UA_CLASS = HQL_UA_ALIAS + ".fichasUA";
	private static final String HQL_FITXA_UA_ALIAS = "fua";
	private static final String HQL_FITXA_CLASS = HQL_FITXA_UA_ALIAS + ".ficha";
	private static final String HQL_FITXA_ALIAS = "f";
	private static final String HQL_SECCION_CLASS = HQL_FITXA_UA_ALIAS + ".seccion";
	private static final String HQL_SECCION_ALIAS = "s";
	private static final String HQL_TRATAMIENTO_CLASS = "Tratamiento";
	private static final String HQL_TRATAMIENTO_ALIAS = "t";
	private static final String HQL_TRADUCCIONES_ALIAS = "trad";

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Obtiene el padre.
	 * @param idPare
	 * @return UnitatAdministrativaDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnitatAdministrativaDTO obtenirPare(long idPare) {
		UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
		uaCriteria.setId(String.valueOf(idPare));
		RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
		return ejb.obtenirUnitatAdministrativa(uaCriteria);
	}

	/**
	 * Obtiene el espacio territorial.
	 * @param idEt
	 * @return EspaiTerritorialDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) {
		EspaiTerritorialCriteria etCriteria = new EspaiTerritorialCriteria();
		etCriteria.setId(String.valueOf(idEt));
		RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
		return ejb.obtenirEspaiTerritorial(etCriteria);
	}

	/**
	 * Obtiene el tratamiento.
	 * @param idTract
	 * @return TractamentDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public TractamentDTO obtenirTractament(long idTract, TractamentCriteria tractamentCriteria) {
		
		List<CriteriaObject> criteris;
		TractamentDTO tractDTO = null;
		Session session = null;

		try {
			
			tractamentCriteria.setId(String.valueOf(idTract));
			criteris = BasicUtils.parseCriterias(TractamentCriteria.class, HQL_TRATAMIENTO_ALIAS, HQL_TRADUCCIONES_ALIAS, tractamentCriteria);
			
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_TRATAMIENTO_CLASS, HQL_TRATAMIENTO_ALIAS));
			
			QueryBuilder qb = new QueryBuilder(HQL_TRATAMIENTO_ALIAS, entities, tractamentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			
			Query query = qb.createQuery(session);
			Tratamiento tractament = (Tratamiento) query.uniqueResult();
			if (tractament != null) {
				tractDTO = (TractamentDTO)BasicUtils.entityToDTO(TractamentDTO.class, tractament, tractamentCriteria.getIdioma());
			}
			
		} catch (HibernateException e) {
			
			log.error(e);
			
		} catch (CriteriaObjectParseException e) {
			
			log.error(e);
			
		} catch (QueryBuilderException e) {
			
			log.error(e);
			
		} finally {
			
			close(session);
			
		}
		
		return tractDTO;
		
	}

	/**
	 * Obtiene listado de hijas.
	 * @param id
	 * @param unitatAdministrativaCriteria
	 * @return List<UnitatAdministrativaDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UnitatAdministrativaDTO> llistarFilles(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
		List<UnitatAdministrativaDTO> unitatAdministrativaDTOList = new ArrayList<UnitatAdministrativaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {   
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_HIJOS_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_UA_HIJOS_CLASS, HQL_UA_HIJOS_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_UA_HIJOS_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();
			for (UnidadAdministrativa unidadAdministrativa : unitatAdministrativaResult) {
				unitatAdministrativaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unidadAdministrativa, unitatAdministrativaCriteria.getIdioma()));
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return unitatAdministrativaDTOList;
	}

	/**
	 * Obtiene los ids de todos los descendientes de una UA
	 * @param id
	 * @return List<Long>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<UnitatAdministrativaDTO> llistarDescendents(long uaId)
	{
		List<UnitatAdministrativaDTO> uasList = new ArrayList<UnitatAdministrativaDTO>();
		try {
			UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
			uaCriteria.setId(String.valueOf(uaId));
			RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
			UnitatAdministrativaDTO uaDto = ejb.obtenirUnitatAdministrativa(uaCriteria);
			obtenirDescendents(uaDto, uasList);

		} catch (QueryServiceException e) {
			log.error(e);
		}
		return uasList;
	}

	// Obtiene recursivamente los descendientes de la UA
	private void obtenirDescendents(UnitatAdministrativaDTO ua, List<UnitatAdministrativaDTO> descendientes) throws QueryServiceException {
		descendientes.add(ua);
		List<UnitatAdministrativaDTO> uas = llistarFilles(ua.getId(), new UnitatAdministrativaCriteria());
		for (UnitatAdministrativaDTO uaDto: uas) {
			obtenirDescendents(uaDto, descendientes);
		}
	}

	/**
	 * Obtiene listado de edificios.
	 * @param id
	 * @param edificiCriteria
	 * @return List<EdificiDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) {
		List<EdificiDTO> edificiDTOList = new ArrayList<EdificiDTO>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {            
			criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, HQL_TRADUCCIONES_ALIAS, edificiCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_EDIFICI_ALIAS, entities, edificiCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Edificio> edificiResult = (List<Edificio>) query.list();
			for (Edificio edifici : edificiResult) {
				edificiDTOList.add((EdificiDTO) BasicUtils.entityToDTO(EdificiDTO.class,  edifici, edificiCriteria.getIdioma()));
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return edificiDTOList;
	}

	/**
	 * Obtiene listado de personal.
	 * @param id
	 * @param personalCriteria
	 * @return List<PersonalDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) {
		List<PersonalDTO> personalDTOList = new ArrayList<PersonalDTO>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {            
			criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Personal> personalResult = (List<Personal>) query.list();
			for (Personal personal : personalResult) {
				personalDTOList.add((PersonalDTO) BasicUtils.entityToDTO(PersonalDTO.class, personal, null));
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return personalDTOList;
	}

	/**
	 * Obtiene listado de normativas.
	 * @param id
	 * @param normativaCriteria
	 * @return List<NormativaDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
		List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
		List<FromClause> entities = new ArrayList<FromClause>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {
			session = getSession();

			criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
			CriteriaObject normativaByUACO = new NormativaByUnitatAdministrativaCriteria(HQL_UA_ALIAS);
			normativaByUACO.parseCriteria(String.valueOf(id));
			criteris.add(normativaByUACO);

			entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
			entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".unidadAdministrativa", HQL_UA_ALIAS));  
			QueryBuilder qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			Query query = qb.createQuery(session);
			List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();

			NormativaDTO dto;
			for (NormativaLocal normativa : normativasLocalesResult) {
				dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
				dto.setLocal(true);
				normativaDTOList.add(dto);
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return normativaDTOList;
	}

	/**
	 * Obtiene listado de procedimientos.
	 * @param id
	 * @param procedimentCriteria
	 * @return List<ProcedimentDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {

		List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {      

			criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, HQL_TRADUCCIONES_ALIAS, procedimentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, procedimentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>)query.list();
            
            for (ProcedimientoLocal procediment : procedimentsResult) {
                
				procedimentsDTOList.add(
					(ProcedimentDTO)BasicUtils.entityToDTO(
						ProcedimentDTO.class, 
						procediment, 
						procedimentCriteria.getIdioma()
					)
				);
				
                
            }

		} catch (HibernateException e) {

			log.error(e);

		} catch (CriteriaObjectParseException e) {

			log.error(e);

		} catch (QueryBuilderException e) {

			log.error(e);

		} finally {

			close(session);

		}

		return procedimentsDTOList;

	}

	/**
	 * Obtiene listado de tramites.
	 * @param id
	 * @param tramitCriteria
	 * @return List<TramitDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
		List<TramitDTO> tramitDTOList = new ArrayList<TramitDTO>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {            
			criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMIT_ALIAS, HQL_TRADUCCIONES_ALIAS, tramitCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_TRAMIT_ALIAS, entities, tramitCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Tramite> tramitResult = (List<Tramite>) query.list();
			for (Tramite tramit : tramitResult) {
				tramitDTOList.add((TramitDTO) BasicUtils.entityToDTO(TramitDTO.class,  tramit, tramitCriteria.getIdioma()));
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return tramitDTOList;
	}

	/**
	 * Obtiene listado de usuarios.
	 * @param id
	 * @param usuariCriteria
	 * @return List<UsuariDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) {
		List<UsuariDTO> usuariDTOList = new ArrayList<UsuariDTO>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {            
			criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Usuario> usuarioResult = (List<Usuario>) query.list();
			for (Usuario usuario : usuarioResult) {
				usuariDTOList.add((UsuariDTO) BasicUtils.entityToDTO(UsuariDTO.class, usuario, null));
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return usuariDTOList;
	}

	/**
	 * Obtiene listado de fichas.
	 * @param id
	 * @param fitxaCriteria
	 * @return List<FitxaDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {

		List<FitxaDTO> fitxesDTOList = new ArrayList<FitxaDTO>();
		List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
		Session session = null;

		try {

			if ( StringUtils.isBlank( fitxaCriteria.getOrdenacio() ) ) {
				fitxaCriteria.setOrdenacio(HQL_FITXA_UA_ALIAS + ".orden");
			}

			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add( new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS) );
			entities.add( new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS) );
			entities.add( new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS) );
			QueryBuilder qb = new QueryBuilder( HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS );
			FitxaUtils.parseActiu( criteris, fitxaCriteria, HQL_FITXA_ALIAS, qb );
			criteris = BasicUtils.parseCriterias( FitxaCriteria.class, HQL_FITXA_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaCriteria );
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias( UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac );
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Ficha> fitxesResult = (List<Ficha>) query.list();

			for ( Ficha fitxa : fitxesResult ) {

				fitxesDTOList.add(
						(FitxaDTO) BasicUtils.entityToDTO(
								FitxaDTO.class,  
								fitxa, 
								fitxaCriteria.getIdioma()
								)
						);


			}

		} catch (HibernateException e) {

			log.error(e);

		} catch (CriteriaObjectParseException e) {

			log.error(e);

		} catch (QueryBuilderException e) {

			log.error(e);

		} finally {

			close(session);

		}

		return fitxesDTOList;

	}

	/**
	 * Obtiene listado de secciones.
	 * @param id
	 * @param seccioCriteria
	 * @return List<SeccioDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {

		List<SeccioDTO> seccioDTOList = new Vector<SeccioDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {

			if (StringUtils.isBlank(seccioCriteria.getOrdenacio())) {
				seccioCriteria.setOrdenacio(HQL_FITXA_UA_ALIAS + ".ordenseccion");
			}

			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCION_ALIAS, HQL_TRADUCCIONES_ALIAS, seccioCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
			entities.add(new FromClause(HQL_SECCION_CLASS, HQL_SECCION_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_SECCION_ALIAS, entities, seccioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Seccion> seccionResult = (List<Seccion>) query.list();
			for (Seccion seccio : seccionResult) {
				seccioDTOList.add((SeccioDTO)BasicUtils.entityToDTO(
						SeccioDTO.class,  
						seccio, 
						seccioCriteria.getIdioma())
						);
			}

		} catch (HibernateException e) {

			log.error(e);

		} catch (CriteriaObjectParseException e) {

			log.error(e);

		} catch (QueryBuilderException e) {

			log.error(e);

		} finally {

			close(session);

		}

		return new ArrayList<SeccioDTO>(seccioDTOList);

	}

	/**
	 * Obtiene listado de materias.
	 * @param id
	 * @param materiaCriteria
	 * @return List<MateriaDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
		List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;
		try {            
			criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS, materiaCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));
			entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Materia> materiaResult = (List<Materia>) query.list();
			for (Materia materia : materiaResult) {
				materiaDTOList.add((MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class,  materia, materiaCriteria.getIdioma()));
			}
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return materiaDTOList;
	}

	/**
	 * Obtiene foto pequenya.
	 * @param fotop
	 * @return ArxiuDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ArxiuDTO obtenirFotop(Long fotop) {
		return getArxiuDTO(fotop);
	}

	/**
	 * Obtiene foto grande.
	 * @param fotog
	 * @return ArxiuDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ArxiuDTO obtenirFotog(Long fotog) {
		return getArxiuDTO(fotog);
	}

	/**
	 * Obtiene logo horizontal.
	 * @param logoh
	 * @return ArxiuDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ArxiuDTO obtenirLogoh(Long logoh) {
		return getArxiuDTO(logoh);
	}

	/**
	 * Obtiene logo vertical.
	 * @param logov
	 * @return ArxiuDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ArxiuDTO obtenirLogov(Long logov) {
		return getArxiuDTO(logov);
	}

	/**
	 * Obtiene logo saludo horizontal.
	 * @param logos
	 * @return ArxiuDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ArxiuDTO obtenirLogos(Long logos) {
		return getArxiuDTO(logos);
	}

	/**
	 * Obtiene logo saludo vertical.
	 * @param logot
	 * @return ArxiuDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ArxiuDTO obtenirLogot(Long logot) {
		return getArxiuDTO(logot);
	}

	/**
	 * Obtiene numero de hijas.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumFilles(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {           
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_HIJOS_ALIAS, HQL_TRADUCCIONES_ALIAS, new UnitatAdministrativaCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_UA_HIJOS_CLASS, HQL_UA_HIJOS_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_UA_HIJOS_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de edificios.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumEdificis(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {           
			criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, HQL_TRADUCCIONES_ALIAS, new EdificiCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_EDIFICI_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de personal.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumPersonal(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;

		try {           
			criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, new PersonalCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de personal.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumProcediments(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {           
			criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, new ProcedimentCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de normativas.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumNormatives(long id) {        
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		List<FromClause> entities = new ArrayList<FromClause>();
		try {            
			session = getSession();

			criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, new NormativaCriteria());
			CriteriaObject normativaByUACO = new NormativaByUnitatAdministrativaCriteria(HQL_UA_ALIAS);
			normativaByUACO.parseCriteria(String.valueOf(id));
			criteris.add(normativaByUACO);
			entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
			entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".unidadAdministrativa", HQL_UA_ALIAS));  

			QueryBuilder qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de tramites.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumTramits(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {           
			criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMIT_ALIAS, new TramitCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_TRAMIT_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de usuarios.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumUsuaris(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {           
			criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, new UsuariCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de fichas.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumFitxes(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {            
			criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, new FitxaCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
			entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de secciones.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumSeccions(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCION_ALIAS, new SeccioCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
			entities.add(new FromClause(HQL_SECCION_CLASS, HQL_SECCION_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_SECCION_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene numero de materias.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumMateries(Long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {
			criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, new MateriaCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));
			entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
			uac.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (HibernateException e) {
			log.error(e);
		} catch (CriteriaObjectParseException e) {
			log.error(e);
		} catch (QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

}
