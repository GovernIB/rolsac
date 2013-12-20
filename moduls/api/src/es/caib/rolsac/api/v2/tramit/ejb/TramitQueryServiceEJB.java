package es.caib.rolsac.api.v2.tramit.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de tramites.
 *
 * @ejb.bean
 *  name="sac/api/TramitQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.tramit.ejb.TramitQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class TramitQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -5415566296133638705L;

    private static Log log = LogFactory.getLog(TramitQueryServiceEJB.class);
  
    private static final String HQL_DOC_TRAMITE_CLASS = "DocumentTramit";
    private static final String HQL_DOC_TRAMITE_ALIAS = "dt";
    private static final String HQL_TAXA_CLASS = "Taxa";
    private static final String HQL_TAXA_ALIAS = "ta"; 
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    private int getNumDocumentsTramit(long id, int tipus) {
        List<CriteriaObject> criteris;
        int numResultats = 0;
        Session session = null;
        DocumentTramitCriteria documentTramitCriteria = new DocumentTramitCriteria();
        documentTramitCriteria.setTramit(String.valueOf(id));
        documentTramitCriteria.setTipus(String.valueOf(tipus));
        try {
            criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class, HQL_DOC_TRAMITE_ALIAS, documentTramitCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_DOC_TRAMITE_CLASS, HQL_DOC_TRAMITE_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOC_TRAMITE_ALIAS, entities, null, null, true);
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
     * Obtiene el numero de documentos informativos.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumDocumentsInformatius(long id) {        
        return getNumDocumentsTramit(id, DocumentTramit.DOCINFORMATIU);
    }

    /**
     * Obtiene el numero de documentos formulario.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumFormularis(long id) {
        return getNumDocumentsTramit(id, DocumentTramit.FORMULARI);
    }

    /**
     * Obtiene el numero de tasas.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumTaxes(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
        int numResultats = 0;
        TaxaCriteria taxaCriteria = new TaxaCriteria();
        taxaCriteria.setTramit(String.valueOf(id));

        try {
            criteris = BasicUtils.parseCriterias(TaxaCriteria.class, HQL_TAXA_ALIAS, taxaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TAXA_CLASS, HQL_TAXA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TAXA_ALIAS, entities, null, null, true);
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
     * Obtiene el procedimiento.
     * @param idProc
     * @return ProcedimentDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimentDTO obtenirProcediment(long idProc) {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId(String.valueOf(idProc));
        if (procedimentCriteria.getIdioma() == null) {
            procedimentCriteria.setIdioma(Locale.getDefault().getLanguage());
        }
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirProcediment(procedimentCriteria);
    }

    /**
     * Obtiene el organo competente.
     * @param idUa
     * @return UnitatAdministrativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatAdministrativaDTO obtenirOrganCompetent(long idUa) {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId(String.valueOf(idUa));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();        
        return rolsacEJB.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);        
    }
    
    /**
     * Obtiene listado de documentos informativos.
     * @param id
     * @param documentTramitCriteria
     * @return List<DocumentTramitDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id, DocumentTramitCriteria documentTramitCriteria) {
        documentTramitCriteria.setTramit(String.valueOf(id));
        documentTramitCriteria.setTipus(String.valueOf(DocumentTramit.DOCINFORMATIU));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarDocumentTramit(documentTramitCriteria);
    }
    
    /**
     * Obtiene listado de formularios.
     * @param id
     * @param documentTramitCriteria
     * @return List<DocumentTramitDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) {
        documentTramitCriteria.setTramit(String.valueOf(id));
        documentTramitCriteria.setTipus(String.valueOf(DocumentTramit.FORMULARI));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarDocumentTramit(documentTramitCriteria);
    }

    /**
     * Obtiene listado tasas.
     * @param id
     * @param taxaCriteria
     * @return List<TaxaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) {
        taxaCriteria.setTramit(String.valueOf(id));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarTaxes(taxaCriteria);
    }
    
}
