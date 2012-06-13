package es.caib.rolsac.api.v2.tramit.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceEJB {

    private static Log log = LogFactory.getLog(TramitQueryServiceEJB.class);
  
    private static final String HQL_DOC_TRAMITE_CLASS = "DocumentTramit";
    private static final String HQL_DOC_TRAMITE_ALIAS = "dt";
    private static final String HQL_TAXA_CLASS = "Taxa";
    private static final String HQL_TAXA_ALIAS = "ta"; 
    
    public TramitQueryServiceEJB() {
    }

   private int getNumDocumentsTramit(long id, int tipus) {
        
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        DocumentTramitCriteria documentTramitCriteria = new DocumentTramitCriteria();
        documentTramitCriteria.setTramit(String.valueOf(id));
        documentTramitCriteria.setTipus(String.valueOf(tipus));

        try {
            criteris = BasicUtils.parseCriterias(DocumentTramitCriteria.class, HQL_DOC_TRAMITE_ALIAS, documentTramitCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_DOC_TRAMITE_CLASS, HQL_DOC_TRAMITE_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOC_TRAMITE_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    } 
    
    public int getNumDocumentsInformatius(long id) {        
        return getNumDocumentsTramit(id, DocumentTramit.DOCINFORMATIU);
    }

    public int getNumFormularis(long id) {
        return getNumDocumentsTramit(id, DocumentTramit.FORMULARI);
    }

    public int getNumTaxes(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        TaxaCriteria taxaCriteria = new TaxaCriteria();
        taxaCriteria.setTramit(String.valueOf(id));

        try {
            criteris = BasicUtils.parseCriterias(TaxaCriteria.class, HQL_TAXA_ALIAS, taxaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TAXA_CLASS, HQL_TAXA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TAXA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }
    
    public ProcedimentDTO obtenirProcediment(long idProc) {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId(String.valueOf(idProc));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirProcediment(procedimentCriteria);
    }

    public UnitatAdministrativaDTO obtenirOrganCompetent(long idUa) {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId(String.valueOf(idUa));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();        
        return rolsacEJB.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);        
    }
    
    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id,
            DocumentTramitCriteria documentTramitCriteria) {
        documentTramitCriteria.setTramit(String.valueOf(id));
        documentTramitCriteria.setTipus(String.valueOf(DocumentTramit.DOCINFORMATIU));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarDocumentTramit(documentTramitCriteria);
    }
    
    public List<DocumentTramitDTO> llistarFormularis(long id, 
            DocumentTramitCriteria documentTramitCriteria) {
        documentTramitCriteria.setTramit(String.valueOf(id));
        documentTramitCriteria.setTipus(String.valueOf(DocumentTramit.FORMULARI));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarDocumentTramit(documentTramitCriteria);
    }

    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) {
        taxaCriteria.setTramit(String.valueOf(id));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarTaxes(taxaCriteria);
    }
    
}
