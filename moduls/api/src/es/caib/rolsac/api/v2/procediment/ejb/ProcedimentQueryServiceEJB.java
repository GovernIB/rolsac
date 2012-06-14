package es.caib.rolsac.api.v2.procediment.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.Tramite;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalProcedimentCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.co.NormativaByProcedimientoCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;


@SuppressWarnings("deprecation")
public class ProcedimentQueryServiceEJB {

    private static Log log = LogFactory.getLog(ProcedimentQueryServiceEJB.class);
    
    private static final String HQL_PROCEDIMIENTO_CLASS = "ProcedimientoLocal";
    private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
    
    private static final String HQL_TRAMITES_CLASS = HQL_PROCEDIMIENTO_ALIAS + ".tramites";
    private static final String HQL_TRAMITES_ALIAS = "t";
    
    private static final String HQL_NORMATIVAS_LOCAL_CLASS = "NormativaLocal";
    private static final String HQL_NORMATIVAS_EXTERNA_CLASS = "NormativaExterna";
    private static final String HQL_NORMATIVAS_ALIAS = "n";
    
    private static final String HQL_MATERIAS_CLASS = HQL_PROCEDIMIENTO_ALIAS + ".materias";
    private static final String HQL_MATERIAS_ALIAS = "m";
    
    private static final String HQL_HECHOS_VITALES_PROCEDIMIENTO_CLASS = HQL_PROCEDIMIENTO_ALIAS + ".hechosVitalesProcedimientos";
    private static final String HQL_HECHOS_VITALES_PROCEDIMIENTO_ALIAS = "hp";
    private static final String HQL_HECHOS_VITALES_CLASS = HQL_HECHOS_VITALES_PROCEDIMIENTO_ALIAS + ".hechoVital";
    private static final String HQL_HECHOS_VITALES_ALIAS = "h";
    
    private static final String HQL_DOCUMENTOS_CLASS = HQL_PROCEDIMIENTO_ALIAS + ".documentos";
    private static final String HQL_DOCUMENTOS_ALIAS = "d";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public ProcedimentQueryServiceEJB() {
    }

    public int getNumTramits(long id) {

        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMITES_ALIAS, new TramitCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_TRAMITES_CLASS, HQL_TRAMITES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TRAMITES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
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

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) {
        List<CriteriaObject> criteris;
        Session sessio = null;    
        QueryBuilder qb = null;
        Query query = null;
        int numResultats = 0;
        
        try {
            
            sessio = HibernateUtils.getSessionFactory().openSession(); 
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, new NormativaCriteria());
            CriteriaObject normativaByProcCO = new NormativaByProcedimientoCriteria("p");
            normativaByProcCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByProcCO);
            List<FromClause> entities = new ArrayList<FromClause>();
            
            if (tipus == TIPUS_NORMATIVA.TOTES || tipus == TIPUS_NORMATIVA.LOCAL){
                entities.add(new FromClause(HQL_NORMATIVAS_LOCAL_CLASS, HQL_NORMATIVAS_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".procedimientos", HQL_PROCEDIMIENTO_ALIAS));            
                qb = new QueryBuilder("n", entities, null, null, true);
                qb.extendCriteriaObjects(criteris);                            
                query = qb.createQuery(sessio);
                numResultats = ((Integer) query.uniqueResult()).intValue();
            }
            
            if (tipus == TIPUS_NORMATIVA.TOTES || tipus == TIPUS_NORMATIVA.EXTERNA){
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVAS_EXTERNA_CLASS, HQL_NORMATIVAS_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".procedimientos", HQL_PROCEDIMIENTO_ALIAS));            
                qb = new QueryBuilder(HQL_NORMATIVAS_ALIAS, entities, null, null, true);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(sessio);
                numResultats += ((Integer) query.uniqueResult()).intValue();
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

    public int getNumMateries(long id) {
        
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIAS_ALIAS, new MateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_MATERIAS_CLASS, HQL_MATERIAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIAS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
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

    public int getNumDocuments(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(DocumentCriteria.class, HQL_DOCUMENTOS_ALIAS, new DocumentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
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
            criteris = BasicUtils.parseCriterias(FetVitalProcedimentCriteria.class, HQL_HECHOS_VITALES_PROCEDIMIENTO_ALIAS, new FetVitalProcedimentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_PROCEDIMIENTO_CLASS, HQL_HECHOS_VITALES_PROCEDIMIENTO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_PROCEDIMIENTO_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
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
    
    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        List<TramitDTO> tramitsDTOList = new ArrayList<TramitDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMITES_ALIAS, HQL_TRADUCCIONES_ALIAS, tramitCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_TRAMITES_CLASS, HQL_TRAMITES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TRAMITES_ALIAS, entities, tramitCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Tramite> tramitesResult = (List<Tramite>) query.list();
            sessio.close();

            for (Tramite tramite : tramitesResult) {
                tramitsDTOList.add((TramitDTO) BasicUtils.entityToDTO(TramitDTO.class,  tramite, tramitCriteria.getIdioma()));
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

        return tramitsDTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {

        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            CriteriaObject normativaByProcCO = new NormativaByProcedimientoCriteria(HQL_PROCEDIMIENTO_ALIAS);
            normativaByProcCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByProcCO);
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVAS_LOCAL_CLASS, HQL_NORMATIVAS_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".procedimientos", HQL_PROCEDIMIENTO_ALIAS));            
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();
            
            List<NormativaExterna> normativasExternasResult = null;
            if (incluirExternas) {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
                criteris.add(normativaByProcCO);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVAS_EXTERNA_CLASS, HQL_NORMATIVAS_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".procedimientos", HQL_PROCEDIMIENTO_ALIAS));            
                qb = new QueryBuilder(HQL_NORMATIVAS_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
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
    
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        List<MateriaDTO> materiesDTOList = new ArrayList<MateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIAS_ALIAS, HQL_TRADUCCIONES_ALIAS, materiaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_MATERIAS_CLASS, HQL_MATERIAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIAS_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Materia> materiesResult = (List<Materia>) query.list();
            sessio.close();

            for (Materia materia : materiesResult) {
                materiesDTOList.add((MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class,  materia, materiaCriteria.getIdioma()));
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

        return materiesDTOList;
    }
    
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {

        List<FetVitalDTO> fetsVitalsDTOList = new ArrayList<FetVitalDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_HECHOS_VITALES_ALIAS, HQL_TRADUCCIONES_ALIAS, fetVitalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_PROCEDIMIENTO_CLASS, HQL_HECHOS_VITALES_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_CLASS, HQL_HECHOS_VITALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_ALIAS, entities, fetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
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

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        List<DocumentDTO> documentsDTOList = new ArrayList<DocumentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(DocumentCriteria.class, HQL_DOCUMENTOS_ALIAS, HQL_TRADUCCIONES_ALIAS, documentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, documentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
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
    
}
