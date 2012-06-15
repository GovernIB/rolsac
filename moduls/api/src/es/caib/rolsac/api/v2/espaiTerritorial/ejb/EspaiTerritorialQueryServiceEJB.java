package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceEJB {

    private static final String HQL_ET_CLASS = "EspacioTerritorial";
    private static final String HQL_ET_ALIAS = "et";
    
    private static final String HQL_UA_CLASS = HQL_ET_ALIAS + ".unidades";
    private static final String HQL_UA_ALIAS = "ua";
    
    private static final String HQL_ET_HIJOS_CLASS = HQL_ET_ALIAS + ".hijos";
    private static final String HQL_ET_HIJOS_ALIAS = "ua";
    
    
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public int getNumFills(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_HIJOS_ALIAS, new EspaiTerritorialCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_ET_HIJOS_CLASS, HQL_ET_HIJOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ET_HIJOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
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

    public int getNumUnitatsAdministratives(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, new UnitatAdministrativaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
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

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        List<EspaiTerritorialDTO> espaiTerritorialDTO = new ArrayList<EspaiTerritorialDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {          
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_HIJOS_ALIAS, HQL_TRADUCCIONES_ALIAS, espaiTerritorialCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_ET_HIJOS_CLASS, HQL_ET_HIJOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, espaiTerritorialCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<EspacioTerritorial> espaiTerritorialResult = (List<EspacioTerritorial>) query.list();

            for (EspacioTerritorial espaiTerritorial : espaiTerritorialResult) {
                espaiTerritorialDTO.add((EspaiTerritorialDTO) BasicUtils.entityToDTO(EspaiTerritorialDTO.class,  espaiTerritorial, espaiTerritorialCriteria.getIdioma()));
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

        return espaiTerritorialDTO;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> unitatADministrativaDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {          
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();

            for (UnidadAdministrativa unitatAdministrativa : unitatAdministrativaResult) {
                unitatADministrativaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unitatAdministrativa, unitatAdministrativaCriteria.getIdioma()));
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

        return unitatADministrativaDTOList;
    }

    public EspaiTerritorialDTO obtenirPare(Long idPadre) {
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId(String.valueOf(idPadre));
        return ejb.obtenirEspaiTerritorial(espaiTerritorialCriteria);
    }

    public ArxiuDTO obtenirMapa(Long idMapa) {
        return EJBUtils.getArxiuDTO(idMapa);
    }

    public ArxiuDTO obtenirLogo(Long idLogo) {
        return EJBUtils.getArxiuDTO(idLogo);
    }

}
