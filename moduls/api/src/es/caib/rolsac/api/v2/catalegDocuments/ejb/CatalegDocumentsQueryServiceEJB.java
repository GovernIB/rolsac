package es.caib.rolsac.api.v2.catalegDocuments.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

/**
 * SessionBean para consultas de catalogos de documentos.
 * 
 * @ejb.bean name="sac/api/CatalegDocumentsQueryServiceEJB" jndi-name=
 *           "es.caib.rolsac.api.v2.catalegDocuments.ejb.CatalegDocumentsQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public class CatalegDocumentsQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = -2139089856182612416L;

	private static Log log = LogFactory.getLog(CatalegDocumentsQueryServiceEJB.class);

	private static final String HQL_CATALOGO_DOCUMENTOS_CLASS = "CatalegDocuments";
	private static final String HQL_CATALOGO_DOCUMENTOS_ALIAS = "cd";
	private static final String HQL_DOCUMENTO_TRAMITE_CLASS = HQL_CATALOGO_DOCUMENTOS_ALIAS + ".doctramite";
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
	 * Obtiene el numero de documentos trámite del catálogo de documentos.
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
			criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class, HQL_DOCUMENTO_TRAMITE_ALIAS, new DocumentTramitCriteria());
			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));
			entities.add(new FromClause(HQL_DOCUMENTO_TRAMITE_CLASS, HQL_DOCUMENTO_TRAMITE_ALIAS));
			QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTO_TRAMITE_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			CatalegDocumentsCriteria cd = new CatalegDocumentsCriteria();
			cd.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(CatalegDocumentsCriteria.class, HQL_CATALOGO_DOCUMENTOS_ALIAS, cd);
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
	 * Obtiene el listado de documents trámites del catalogo de documentos.
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
			entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));
			entities.add(new FromClause(HQL_DOCUMENTO_TRAMITE_CLASS, HQL_DOCUMENTO_TRAMITE_ALIAS));
			
			QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTO_TRAMITE_ALIAS,
					entities, documentTramitCriteria.getIdioma(),
					HQL_TRADUCCIONES_ALIAS);
			
			qb.extendCriteriaObjects(criteris);

			CatalegDocumentsCriteria catalegDocumentsCriteria = new CatalegDocumentsCriteria();
			catalegDocumentsCriteria.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(CatalegDocumentsCriteria.class, HQL_CATALOGO_DOCUMENTOS_ALIAS, catalegDocumentsCriteria);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<DocumentTramit> llistaDocumentsTramits = castList(
					DocumentTramit.class, query.list());

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

}
