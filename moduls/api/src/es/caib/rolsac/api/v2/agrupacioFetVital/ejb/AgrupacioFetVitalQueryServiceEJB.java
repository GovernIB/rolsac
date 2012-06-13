package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.HechoVital;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

public class AgrupacioFetVitalQueryServiceEJB {
    
    private static final String HQL_AGRUPACIO_FET_VITAL_CLASS = "AgrupacionHechoVital";
    private static final String HQL_AGRUPACIO_FET_VITAL_ALIAS = "afv";
    
    private static final String HQL_FET_VITAL_AGRUPACIO_CLASS = HQL_AGRUPACIO_FET_VITAL_ALIAS + ".hechosVitalesAgrupacionHV";
    private static final String HQL_FET_VITAL_AGRUPACIO_ALIAS = "fva";    
    
    private static final String HQL_FET_VITAL_CLASS = HQL_FET_VITAL_AGRUPACIO_ALIAS + ".hechoVital";
    private static final String HQL_FET_VITAL_ALIAS = "fv";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public ArxiuDTO getFotografia(long idFoto) {
        return EJBUtils.getArxiuDTO(idFoto);
    }
    
    public ArxiuDTO getIcona(long idIcona) {
        return EJBUtils.getArxiuDTO(idIcona);
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return EJBUtils.getArxiuDTO(idIconaGran);
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) {
        PublicObjectiuCriteria publicObjectiuCriteria = new PublicObjectiuCriteria();
        publicObjectiuCriteria.setId(String.valueOf(idPublic));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirPublicObjectiu(publicObjectiuCriteria);
    }

    public int getNumFetsVitals(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, new FetVitalCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_AGRUPACIO_CLASS, HQL_FET_VITAL_AGRUPACIO_ALIAS));             
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));  
            QueryBuilder qb = new QueryBuilder(HQL_FET_VITAL_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioFetVitalCriteria afvc = new AgrupacioFetVitalCriteria();
            afvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS, afvc);
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

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        List<FetVitalDTO> fetVitalDTOList = new ArrayList<FetVitalDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        if (StringUtils.isBlank(fetVitalCriteria.getOrdenacio())) {
            fetVitalCriteria.setOrdenacio(HQL_FET_VITAL_AGRUPACIO_ALIAS + ".orden");
            }
        
        try {            
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, HQL_TRADUCCIONES_ALIAS, fetVitalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_AGRUPACIO_CLASS, HQL_FET_VITAL_AGRUPACIO_ALIAS));             
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));                       
            QueryBuilder qb = new QueryBuilder(HQL_FET_VITAL_ALIAS, entities, fetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioFetVitalCriteria afvc = new AgrupacioFetVitalCriteria();
            afvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS, afvc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<HechoVital> fetsVitalsResult = (List<HechoVital>) query.list();

            for (HechoVital fetVital : fetsVitalsResult) {
                fetVitalDTOList.add((FetVitalDTO) BasicUtils.entityToDTO(FetVitalDTO.class,  fetVital, fetVitalCriteria.getIdioma()));
            }
            
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

        return fetVitalDTOList;
    }

}
