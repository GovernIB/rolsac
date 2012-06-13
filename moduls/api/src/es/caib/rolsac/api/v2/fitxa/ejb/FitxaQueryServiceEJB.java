package es.caib.rolsac.api.v2.fitxa.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceEJB {

    private static final String HQL_FICHA_CLASS = "Ficha";
    private static final String HQL_FICHA_ALIAS = "f";

    private static final String HQL_ENLACES_CLASS = HQL_FICHA_ALIAS + ".enlaces";
    private static final String HQL_ENLACES_ALIAS = "e";
    
    private static final String HQL_DOCUMENTOS_CLASS = HQL_FICHA_ALIAS + ".documentos";
    private static final String HQL_DOCUMENTOS_ALIAS = "d";    
    
    private static final String HQL_HECHOS_VITALES_CLASS = HQL_FICHA_ALIAS + ".hechosVitales";
    private static final String HQL_HECHOS_VITALES_ALIAS = "h";
    
    private static final String HQL_FICHASUA_CLASS = HQL_FICHA_ALIAS + ".fichasua";
    private static final String HQL_FICHASUA_ALIAS = "fua";
    
    private static final String HQL_UA_CLASS = HQL_FICHASUA_ALIAS + ".unidadAdministrativa";
    private static final String HQL_UA_ALIAS = "ua";
    
    private static final String HQL_SECCION_CLASS = HQL_FICHASUA_ALIAS + ".seccion";
    private static final String HQL_SECCION_ALIAS = "s";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public FitxaQueryServiceEJB(){
        
    }
    
    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) {
        List<EnllacDTO> enllassosDTOList = new ArrayList<EnllacDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            
            if (StringUtils.isBlank(enllacCriteria.getOrdenacio())) {
                enllacCriteria.setOrdenacio(HQL_ENLACES_ALIAS + ".orden");
                }
            
            criteris = BasicUtils.parseCriterias(EnllacCriteria.class, HQL_ENLACES_ALIAS, HQL_TRADUCCIONES_ALIAS, enllacCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_ENLACES_CLASS, HQL_ENLACES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ENLACES_ALIAS, entities, enllacCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Enlace> enllassosResult = (List<Enlace>) query.list();
            sessio.close();

            for (Enlace enllac : enllassosResult) {
                enllassosDTOList.add((EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class,  enllac, enllacCriteria.getIdioma()));
            }
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

        return enllassosDTOList;
    }

    @SuppressWarnings("deprecation")
    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        List<DocumentDTO> documentsDTOList = new ArrayList<DocumentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(DocumentCriteria.class, HQL_DOCUMENTOS_ALIAS, HQL_TRADUCCIONES_ALIAS, documentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, documentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Documento> documentosResult = (List<Documento>) query.list();
            sessio.close();

            for (Documento document : documentosResult) {
                documentsDTOList.add((DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class,  document, documentCriteria.getIdioma()));
            }
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

        return documentsDTOList;
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        List<FetVitalDTO> fetsVitalsDTOList = new ArrayList<FetVitalDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_HECHOS_VITALES_ALIAS, HQL_TRADUCCIONES_ALIAS, fetVitalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_CLASS, HQL_HECHOS_VITALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_ALIAS, entities, fetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<HechoVital> fetsVitalsResult = (List<HechoVital>) query.list();
            sessio.close();

            for (HechoVital fetVital : fetsVitalsResult) {
                fetsVitalsDTOList.add((FetVitalDTO) BasicUtils.entityToDTO(FetVitalDTO.class,  fetVital, fetVitalCriteria.getIdioma()));
            }
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

        return fetsVitalsDTOList;
    }
    
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        Set<UnitatAdministrativaDTO> unitatADministrativaDTOSet = new HashSet<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {          
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_FICHASUA_CLASS, HQL_FICHASUA_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();

            for (UnidadAdministrativa unitatAdministrativa : unitatAdministrativaResult) {
                unitatADministrativaDTOSet.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unitatAdministrativa, unitatAdministrativaCriteria.getIdioma()));
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

        return new ArrayList<UnitatAdministrativaDTO>(unitatADministrativaDTOSet);
    }
    
    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        Set<SeccioDTO> seccioDTOSet = new HashSet<SeccioDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCION_ALIAS, HQL_TRADUCCIONES_ALIAS, seccioCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_FICHASUA_CLASS, HQL_FICHASUA_ALIAS));
            entities.add(new FromClause(HQL_SECCION_CLASS, HQL_SECCION_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_SECCION_ALIAS, entities, seccioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Seccion> seccionResult = (List<Seccion>) query.list();

            for (Seccion seccio : seccionResult) {
                seccioDTOSet.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class,  seccio, seccioCriteria.getIdioma()));
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

        return new ArrayList<SeccioDTO>(seccioDTOSet);
    }
    
    public int getNumDocuments(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(DocumentCriteria.class, HQL_DOCUMENTOS_ALIAS, new DocumentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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

    public int getNumEnllacos(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(EnllacCriteria.class, HQL_ENLACES_ALIAS, new EnllacCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_ENLACES_CLASS, HQL_ENLACES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ENLACES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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

    public int getNumFetsVitals(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_HECHOS_VITALES_ALIAS, new FetVitalCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_CLASS, HQL_HECHOS_VITALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_FICHASUA_CLASS, HQL_FICHASUA_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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

    public int getNumSeccions(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {
            
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCION_ALIAS, new SeccioCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_FICHASUA_CLASS, HQL_FICHASUA_ALIAS));
            entities.add(new FromClause(HQL_SECCION_CLASS, HQL_SECCION_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_SECCION_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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

    public ArxiuDTO obtenirIcona(Long icono) {
        return EJBUtils.getArxiuDTO(icono);
    }

    public ArxiuDTO obtenirImatge(Long imagen) {
        return EJBUtils.getArxiuDTO(imagen);
    }

    public ArxiuDTO obtenirBaner(Long baner) {
        return EJBUtils.getArxiuDTO(baner);
    }
    
}
