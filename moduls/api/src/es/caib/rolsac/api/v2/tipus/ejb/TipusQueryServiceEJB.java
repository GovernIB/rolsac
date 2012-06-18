package es.caib.rolsac.api.v2.tipus.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.co.NormativaByTipoCriteria;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

public class TipusQueryServiceEJB {    
    
    private static Log log = LogFactory.getLog(TipusQueryServiceEJB.class);
    
    private static final String HQL_TIPUS_ALIAS = "t";
    
    private static final String HQL_NORMATIVA_LOCAL_CLASS = "NormativaLocal";
    private static final String HQL_NORMATIVA_EXTERNA_CLASS = "NormativaExterna";
    private static final String HQL_NORMATIVA_ALIAS = "n";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public int getNumNormatives(Long id, TIPUS_NORMATIVA tipus) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        Session sessio = null;    
        QueryBuilder qb = null;
        Query query = null;
        int numResultats = 0;
        
        try {
            sessio = HibernateUtils.getSessionFactory().openSession(); 
            CriteriaObject normativaByTipusCO = new NormativaByTipoCriteria(HQL_TIPUS_ALIAS);
            normativaByTipusCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByTipusCO);
            List<FromClause> entities = new ArrayList<FromClause>();
            
            if (tipus == TIPUS_NORMATIVA.TOTES || tipus == TIPUS_NORMATIVA.LOCAL){
                entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".tipo", HQL_TIPUS_ALIAS));            
                qb = new QueryBuilder("n", entities, null, null, true);
                qb.extendCriteriaObjects(criteris);                            
                query = qb.createQuery(sessio);
                numResultats = (Integer) query.uniqueResult();
            }
            
            if (tipus == TIPUS_NORMATIVA.TOTES || tipus == TIPUS_NORMATIVA.EXTERNA){
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".tipo", HQL_TIPUS_ALIAS));            
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null, true);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(sessio);
                numResultats += (Integer) query.uniqueResult();
            }

            sessio.close();
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace(); // TODO: delete me.
                }
            }
        }

        return numResultats;
    }

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            CriteriaObject normativaByTipusCO = new NormativaByTipoCriteria(HQL_TIPUS_ALIAS);
            normativaByTipusCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByTipusCO);
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".tipo", HQL_TIPUS_ALIAS));            
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();
            List<NormativaExterna> normativasExternasResult = null;
            if (incluirExternas) {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
                criteris.add(normativaByTipusCO);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".tipo", HQL_TIPUS_ALIAS));            
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(sessio);
                normativasExternasResult = (List<NormativaExterna>) query.list();
            }
            sessio.close();

            NormativaDTO dto;
            for (NormativaLocal normativa : normativasLocalesResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }
            if (incluirExternas) {
                for (NormativaExterna normativa : normativasExternasResult) {
                    dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                    dto.setLocal(false);
                    normativaDTOList.add(dto);
                }
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace(); // TODO: delete me.
                }
            }
        }

        return normativaDTOList;
    }

}
