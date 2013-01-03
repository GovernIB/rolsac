package es.caib.rolsac.api.v2.excepcioDocumentacio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.DocumentTramit;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.catalegDocuments.ejb.CatalegDocumentsQueryServiceEJB;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

/**
 * SessionBean para consultas de excepciones de documentos.
 * 
 * @ejb.bean name="sac/api/ExcepcioDocumentacioQueryServiceEJB" jndi-name=
 *           "es.caib.rolsac.api.v2.excepcioDocumentacio.ejb.ExcepcioDocumentacioQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public class ExcepcioDocumentacioQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = -7369613879679734360L;

	private static Log log = LogFactory.getLog(CatalegDocumentsQueryServiceEJB.class);

	private static final String HQL_EXCEPCION_DOCUMENTACION_CLASS = "ExcepcioDocumentacio";
	private static final String HQL_EXCEPCION_DOCUMENTACION_ALIAS = "ed";	
	private static final String HQL_CATALOGO_DOCUMENTOS_CLASS = HQL_EXCEPCION_DOCUMENTACION_ALIAS + ".catalegDocuments";
	private static final String HQL_CATALOGO_DOCUMENTOS_ALIAS = "cd";
	private static final String HQL_DOCUMENTO_TRAMITE_CLASS = HQL_EXCEPCION_DOCUMENTACION_ALIAS + ".docsRequerits";
	private static final String HQL_DOCUMENTO_TRAMITE_ALIAS = "dt";
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
	 * Obtiene el numero de catálogos de documentos de la excepción de documentación.
	 * 
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumCatalegsDocuments(long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;

		try {
			criteris = BasicUtils.parseCriterias(CatalegDocumentsCriteria.class,
					HQL_CATALOGO_DOCUMENTOS_ALIAS, new CatalegDocumentsCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS,
					HQL_EXCEPCION_DOCUMENTACION_ALIAS));
			entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS,
					HQL_CATALOGO_DOCUMENTOS_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_CATALOGO_DOCUMENTOS_ALIAS,
					entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			ExcepcioDocumentacioCriteria ed = new ExcepcioDocumentacioCriteria();
			ed.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(
					ExcepcioDocumentacioCriteria.class,
					HQL_EXCEPCION_DOCUMENTACION_ALIAS, ed);
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
	 * Obtiene el numero de documentos trámite de la excepción de documentación.
	 * 
	 * @return int
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumDocumentsTramit(long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;

		try {
			criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class,
					HQL_DOCUMENTO_TRAMITE_ALIAS, new DocumentTramitCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS,
					HQL_EXCEPCION_DOCUMENTACION_ALIAS));
			entities.add(new FromClause(HQL_DOCUMENTO_TRAMITE_CLASS,
					HQL_DOCUMENTO_TRAMITE_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTO_TRAMITE_ALIAS,
					entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			ExcepcioDocumentacioCriteria ed = new ExcepcioDocumentacioCriteria();
			ed.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(
					ExcepcioDocumentacioCriteria.class,
					HQL_EXCEPCION_DOCUMENTACION_ALIAS, ed);
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
	 * Obtiene el listado de documentos trámites de la excepción de documentación.
	 * 
	 * @param id
	 * @param documentTramitCriteria
	 * @return List<DocumentTramitDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<DocumentTramitDTO> llistarDocumentsTramits(long id,
			DocumentTramitCriteria documentTramitCriteria) {

		List<DocumentTramitDTO> documentTramitDTOList = new ArrayList<DocumentTramitDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class,
					HQL_DOCUMENTO_TRAMITE_ALIAS, HQL_TRADUCCIONES_ALIAS,
					documentTramitCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS, HQL_EXCEPCION_DOCUMENTACION_ALIAS));
			entities.add(new FromClause(HQL_DOCUMENTO_TRAMITE_CLASS, HQL_DOCUMENTO_TRAMITE_ALIAS));
			
			QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTO_TRAMITE_ALIAS,
					entities, documentTramitCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			
			qb.extendCriteriaObjects(criteris);

			ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria = new ExcepcioDocumentacioCriteria();
			excepcioDocumentacioCriteria.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(ExcepcioDocumentacioCriteria.class, HQL_EXCEPCION_DOCUMENTACION_ALIAS, excepcioDocumentacioCriteria);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<DocumentTramit> llistaDocumentsTramits = castList(DocumentTramit.class, query.list());

			for (DocumentTramit documentTramit : llistaDocumentsTramits) {
				documentTramitDTOList.add((DocumentTramitDTO) BasicUtils
						.entityToDTO(DocumentTramitDTO.class, documentTramit,
								documentTramitCriteria.getIdioma()));
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

		return documentTramitDTOList;

	}
	
	/**
	 * Obtiene el listado de documentos trámites de la excepción de documentación.
	 * 
	 * @param id
	 * @param documentTramitCriteria
	 * @return List<CatalegDocumentsDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(long id,
			CatalegDocumentsCriteria catalegDocumentsCriteria) {

		List<CatalegDocumentsDTO> catalegDocumentsDTOList = new ArrayList<CatalegDocumentsDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(CatalegDocumentsCriteria.class,
					HQL_CATALOGO_DOCUMENTOS_ALIAS, HQL_TRADUCCIONES_ALIAS,
					catalegDocumentsCriteria);
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS, HQL_EXCEPCION_DOCUMENTACION_ALIAS));
			entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));
			
			QueryBuilder qb = new QueryBuilder(HQL_CATALOGO_DOCUMENTOS_ALIAS,
					entities, catalegDocumentsCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			
			qb.extendCriteriaObjects(criteris);

			ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria = new ExcepcioDocumentacioCriteria();
			excepcioDocumentacioCriteria.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(ExcepcioDocumentacioCriteria.class, HQL_EXCEPCION_DOCUMENTACION_ALIAS, excepcioDocumentacioCriteria);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<CatalegDocuments> llistaCatalegsDocuments = castList(CatalegDocuments.class, query.list());

			for (CatalegDocuments catalegDocuments : llistaCatalegsDocuments) {
				catalegDocumentsDTOList.add((CatalegDocumentsDTO) BasicUtils
						.entityToDTO(CatalegDocumentsDTO.class, catalegDocuments,
								catalegDocumentsCriteria.getIdioma()));
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

		return catalegDocumentsDTOList;

	}					
}