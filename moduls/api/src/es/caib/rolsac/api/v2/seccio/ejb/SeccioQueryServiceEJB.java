package es.caib.rolsac.api.v2.seccio.ejb;

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
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaUtils;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.ejb.MateriaQueryServiceEJB;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de seccion.
 *
 * @ejb.bean
 *  name="sac/api/SeccioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.seccio.ejb.SeccioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class SeccioQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = 3127570531525600547L;

	private static Log log = LogFactory.getLog(MateriaQueryServiceEJB.class);

	private static final String HQL_SECCIO_CLASS = "Seccion";
	private static final String HQL_SECCIO_ALIAS = "s";
	private static final String HQL_SECCIO_FITXA_CLASS = HQL_SECCIO_ALIAS + ".fichasUA";
	private static final String HQL_SECCIO_FITXA_ALIAS = "fua";
	private static final String HQL_FITXA_CLASS = HQL_SECCIO_FITXA_ALIAS + ".ficha";
	private static final String HQL_FITXA_ALIAS = "f";
	private static final String HQL_UA_CLASS = HQL_SECCIO_FITXA_ALIAS + ".unidadAdministrativa";
	private static final String HQL_UA_ALIAS = "f";
	private static final String HQL_FILLES_CLASS = HQL_SECCIO_ALIAS + ".hijos";
	private static final String HQL_FILLES_ALIAS = "sf";
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
	 * Obtiene el numero de secciones hijas.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumFilles(long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {            
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_FILLES_ALIAS, new SeccioCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
			entities.add(new FromClause(HQL_FILLES_CLASS, HQL_FILLES_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_FILLES_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			SeccioCriteria sc = new SeccioCriteria();
			sc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
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
	 * Obtiene el numero de fichas.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumFitxes(long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {            
			criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, new FitxaCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
			entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
			entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			SeccioCriteria sc = new SeccioCriteria();
			sc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
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
	 * Obtiene el numero de secciones padre.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumPares(long id) {
		return this.llistarPares(id).size();
	}

	/**
	 * Obtiene el numero de unidades administrtativas.
	 * @param id
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumUnitatsAdministratives(long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {            
			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, new UnitatAdministrativaCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
			entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			SeccioCriteria sc = new SeccioCriteria();
			sc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
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
	 * Obtiene listado de secciones padre.
	 * @param id
	 * @return List<SeccioDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<SeccioDTO> llistarPares(long id) {
		List<SeccioDTO> llistaPares = new ArrayList<SeccioDTO>();
		SeccioDTO pare = null;
		while ((pare = obtenirSeccioPare(id)) != null){
			llistaPares.add(pare);
			id = pare.getId();                        
		}
		return llistaPares;
	}

	private SeccioDTO obtenirSeccioPare(Long id) {
		SeccioCriteria seccioCriteria = new SeccioCriteria();
		seccioCriteria.setId(String.valueOf(id));
		RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
		SeccioDTO fill = ejb.obtenirSeccio(seccioCriteria);
		if (fill.getPadre() == null) {
			return null;
		} else {
			seccioCriteria.setId(String.valueOf(fill.getPadre()));
			return ejb.obtenirSeccio(seccioCriteria);   
		}        
	}

	/**
	 * Obtiene listado de secciones hija.
	 * @param id
	 * @param seccioCriteria
	 * @return List<SeccioDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) {
		List<SeccioDTO> seccioDTOList = new ArrayList<SeccioDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {            
			if (StringUtils.isBlank(seccioCriteria.getOrdenacio())) {
				seccioCriteria.setOrdenacio(HQL_FILLES_ALIAS + ".orden");
			}

			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_FILLES_ALIAS, HQL_TRADUCCIONES_ALIAS, seccioCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
			entities.add(new FromClause(HQL_FILLES_CLASS, HQL_FILLES_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_FILLES_ALIAS, entities, seccioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			SeccioCriteria sc = new SeccioCriteria();
			sc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Seccion> seccioResult = (List<Seccion>) query.list();
			for (Seccion seccio : seccioResult) {
				seccioDTOList.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class,  seccio, seccioCriteria.getIdioma()));
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

		return seccioDTOList;
	}

	/**
	 * Obtiene listado de fichas.
	 * @param id
	 * @param fitxaCriteria
	 * @return List<FichaDTO>
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

			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
			entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
			entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));

			QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			FitxaUtils.parseActiu(criteris, fitxaCriteria, HQL_FITXA_ALIAS, qb);
			criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FILLES_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaCriteria);
			qb.extendCriteriaObjects(criteris);

			SeccioCriteria sc = new SeccioCriteria();
			sc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Ficha> fitxesResult = (List<Ficha>) query.list();

			for (Ficha fitxa : fitxesResult) {

					fitxesDTOList.add(
							(FitxaDTO)BasicUtils.entityToDTO(
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
	 * Obtiene listado de uniaddes administrativas.
	 * @param id
	 * @param unitatAdministrativaCriteria
	 * @return List<UnitatAdministrativaDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {

		List<UnitatAdministrativaDTO> uaDTOList = new Vector<UnitatAdministrativaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {

			criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_FILLES_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
			entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
			entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
			qb.extendCriteriaObjects(criteris);

			SeccioCriteria sc = new SeccioCriteria();
			sc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<UnidadAdministrativa> uaResult = (List<UnidadAdministrativa>) query.list();
			for (UnidadAdministrativa unitatAdministrativa : uaResult) {
				uaDTOList.add((UnitatAdministrativaDTO)BasicUtils.entityToDTO(
						UnitatAdministrativaDTO.class,  
						unitatAdministrativa, 
						unitatAdministrativaCriteria.getIdioma())
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

		return new ArrayList<UnitatAdministrativaDTO>(uaDTOList);

	}

	/**
	 * Obtiene seccion padre.
	 * @param padre
	 * @return SeccioDTO
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public SeccioDTO obtenirPare(Long padre) {
		SeccioCriteria seccioCriteria = new SeccioCriteria();
		seccioCriteria.setId(String.valueOf(padre));
		RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
		return ejb.obtenirSeccio(seccioCriteria);
	}

}
