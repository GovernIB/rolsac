package es.caib.rolsac.api.v2.materia.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadMateria;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;


public class MateriaQueryServiceEJB {

//    private static Log log = LogFactory.getLog(MateriaQueryServiceEJB.class);
    
    private static final String HQL_MATERIA_CLASS = "Materia";
    private static final String HQL_MATERIA_ALIAS = "m";
    
    private static final String HQL_FICHAS_CLASS = HQL_MATERIA_ALIAS + ".fichas";
    private static final String HQL_FICHAS_ALIAS = "f";
    
    private static final String HQL_PROCEDIMIENTOS_LOCALES_CLASS = HQL_MATERIA_ALIAS + ".procedimientosLocales";
    private static final String HQL_PROCEDIMIENTOS_LOCALES_ALIAS = "p";    
    
    private static final String HQL_MATERIA_AGRUPACION_CLASS = HQL_MATERIA_ALIAS + ".materiasAgrupacionM";
    private static final String HQL_MATERIA_AGRUPACION_ALIAS = "ma";
    private static final String HQL_AGRUPACION_MATERIA_CLASS = HQL_MATERIA_AGRUPACION_ALIAS + ".agrupacion";
    private static final String HQL_AGRUPACION_MATERIA_ALIAS = "am";    
    
    private static final String HQL_UNIDAD_MATERIA_CLASS = HQL_MATERIA_ALIAS + ".unidadesmaterias";
    private static final String HQL_UNIDAD_MATERIA_ALIAS = "u";
    
    private static final String HQL_ICONA_MATERIA_CLASS = HQL_MATERIA_ALIAS + ".iconos";
    private static final String HQL_ICONA_MATERIA_ALIAS = "i";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";  
 
    public MateriaQueryServiceEJB() {
    }
    
    public ArxiuDTO getFotografia(long idFoto) {
        return EJBUtils.getArxiuDTO(idFoto);
    }
    
    public ArxiuDTO getIcona(long idIcona) {
        return EJBUtils.getArxiuDTO(idIcona);
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return EJBUtils.getArxiuDTO(idIconaGran);
    }
    
    public int getNumFitxes(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHAS_ALIAS, new FitxaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_FICHAS_CLASS, HQL_FICHAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FICHAS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
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

    public int getNumAgrupacioMateries(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_MATERIA_AGRUPACION_ALIAS, new AgrupacioMateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_AGRUPACION_CLASS, HQL_MATERIA_AGRUPACION_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACION_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
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

    public int getNumProcedimentsLocals(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTOS_LOCALES_ALIAS, new ProcedimentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTOS_LOCALES_CLASS, HQL_PROCEDIMIENTOS_LOCALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTOS_LOCALES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
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

    public int getNumUnitatsMateries(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(UnitatMateriaCriteria.class, HQL_UNIDAD_MATERIA_ALIAS, new UnitatMateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_UNIDAD_MATERIA_CLASS, HQL_UNIDAD_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UNIDAD_MATERIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
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

    public int getNumIcones(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, new IconaMateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
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

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTOS_LOCALES_ALIAS, HQL_TRADUCCIONES_ALIAS, procedimentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTOS_LOCALES_CLASS, HQL_PROCEDIMIENTOS_LOCALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTOS_LOCALES_ALIAS, entities, procedimentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>) query.list();

            for (ProcedimientoLocal procediment : procedimentsResult) {
                procedimentsDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class,  procediment, procedimentCriteria.getIdioma()));
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

        return procedimentsDTOList;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {        
        List<FitxaDTO> fitxesDTOList = new ArrayList<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHAS_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_FICHAS_CLASS, HQL_FICHAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FICHAS_ALIAS, entities, fitxaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Ficha> fitxesResult = (List<Ficha>) query.list();

            for (Ficha fitxa : fitxesResult) {
                fitxesDTOList.add((FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class,  fitxa, fitxaCriteria.getIdioma()));
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

        return fitxesDTOList;
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        List<AgrupacioMateriaDTO> agrupacioMateriaDTOList = new ArrayList<AgrupacioMateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {          
            criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACION_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS, agrupacioMateriaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_AGRUPACION_CLASS, HQL_MATERIA_AGRUPACION_ALIAS));
            entities.add(new FromClause(HQL_AGRUPACION_MATERIA_CLASS, HQL_AGRUPACION_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_AGRUPACION_MATERIA_ALIAS, entities, agrupacioMateriaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<AgrupacionMateria> agrupacionsMateriaResult = (List<AgrupacionMateria>) query.list();

            for (AgrupacionMateria agrupacionMateria : agrupacionsMateriaResult) {
                agrupacioMateriaDTOList.add((AgrupacioMateriaDTO) BasicUtils.entityToDTO(AgrupacioMateriaDTO.class,  agrupacionMateria, agrupacioMateriaCriteria.getIdioma()));
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

        return agrupacioMateriaDTOList;
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) {
        List<UnitatMateriaDTO> unitatsMateriesDTOList = new ArrayList<UnitatMateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(UnitatMateriaCriteria.class, HQL_UNIDAD_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatMateriaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_UNIDAD_MATERIA_CLASS, HQL_UNIDAD_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UNIDAD_MATERIA_ALIAS, entities, unitatMateriaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadMateria> unitatMateriesResult = (List<UnidadMateria>) query.list();

            for (UnidadMateria unidadMateria : unitatMateriesResult) {
                unitatsMateriesDTOList.add((UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class,  unidadMateria, unitatMateriaCriteria.getIdioma()));
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

        return unitatsMateriesDTOList;
    }

    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        List<IconaMateriaDTO> iconaMateriaDTOList = new ArrayList<IconaMateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, iconaMateriaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<IconoMateria> iconesMateriaResult = (List<IconoMateria>) query.list();

            for (IconoMateria iconaMateria : iconesMateriaResult) {
                iconaMateriaDTOList.add((IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class,  iconaMateria, null));
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

        return iconaMateriaDTOList;
    }



}
