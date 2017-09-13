package es.caib.rolsac.api.v2.procediment.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Tramite;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalProcedimentCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.co.NormativaByProcedimientoCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

/**
 * SessionBean para consultas de procedimientos.
 *
 * @ejb.bean
 *  name="sac/api/ProcedimentQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.procediment.ejb.ProcedimentQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public class ProcedimentQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 3727131208139935651L;

    private static Log log = LogFactory.getLog(ProcedimentQueryServiceEJB.class);
    
    private static final String HQL_PROCEDIMIENTO_CLASS = "ProcedimientoLocal";
    private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
    private static final String HQL_TRAMITES_CLASS = HQL_PROCEDIMIENTO_ALIAS + ".tramites";
    private static final String HQL_TRAMITES_ALIAS = "t";
    private static final String HQL_NORMATIVAS_CLASS = "Normativa";
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
    private static final String HQL_PUBLICO_OBJETIVO_CLASS = HQL_PROCEDIMIENTO_ALIAS + ".publicosObjetivo";
    private static final String HQL_PUBLICO_OBJETIVO_ALIAS = "po";

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene el numero de tramites del procedimiento.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public int getNumTramits(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de normativas del procedimiento.
     * @param id
     * @param tipus
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumNormatives(long id, long tipus) {
        List<CriteriaObject> criteris;
        Session session = null;    
        QueryBuilder qb = null;
        Query query = null;
        int numResultats = 0;
        
        try {
            session = getSession(); 
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, new NormativaCriteria());
            CriteriaObject normativaByProcCO = new NormativaByProcedimientoCriteria("p");
            normativaByProcCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByProcCO);
            List<FromClause> entities = new ArrayList<FromClause>();
            
            entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVAS_CLASS, HQL_NORMATIVAS_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".procedimientos", HQL_PROCEDIMIENTO_ALIAS));            
            qb = new QueryBuilder(HQL_NORMATIVAS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            query = qb.createQuery(session);
            numResultats = numResultats + getNumberResults(query);
        
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
     * Obtiene el numero de materias del procedimiento.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumMateries(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de documentos del procedimiento.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumDocuments(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de hechos vitales del procedimiento.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumFetsVitals(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene listado de tramites del procedimiento.
     * @param id
     * @param tramitCriteria
     * @return List<TramitDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        List<TramitDTO> tramitsDTOList = new ArrayList<TramitDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMITES_ALIAS, HQL_TRADUCCIONES_ALIAS, tramitCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_TRAMITES_CLASS, HQL_TRAMITES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TRAMITES_ALIAS, entities, null, null);//tramitCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Tramite> tramitesResult = castList(Tramite.class, query.list());
            for (Tramite tramite : tramitesResult) {
                tramitsDTOList.add((TramitDTO) BasicUtils.entityToDTO(TramitDTO.class,  tramite, tramitCriteria.getIdioma()));
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

        return tramitsDTOList;
    }
    
    /**
     * Obtiene listado de normativas del procedimiento.
     * @param id
     * @param normativaCriteria
     * @return List<NormativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
       
        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            CriteriaObject normativaByProcCO = new NormativaByProcedimientoCriteria(HQL_PROCEDIMIENTO_ALIAS);
            normativaByProcCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByProcCO);
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVAS_CLASS, HQL_NORMATIVAS_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".procedimientos", HQL_PROCEDIMIENTO_ALIAS));            
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            List<Normativa> normativasResult = (List<Normativa>) query.list();

            NormativaDTO dto;
            for (Normativa normativa : normativasResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                normativaDTOList.add(dto);
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

        return normativaDTOList;
    }
    
    /**
     * Obtiene listado de materias del procedimiento.
     * @param id
     * @param materiaCriteria
     * @return List<MateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        List<MateriaDTO> materiesDTOList = new ArrayList<MateriaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<Materia> materiesResult = (List<Materia>) query.list();

            for (Materia materia : materiesResult) {
                materiesDTOList.add((MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class,  materia, materiaCriteria.getIdioma()));
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

        return materiesDTOList;
    }
    
    /**
     * Obtiene listado de hechos vitales del procedimiento.
     * @param id
     * @param fetVitalCriteria
     * @return List<FetVitalDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {

        List<FetVitalDTO> fetsVitalsDTOList = new ArrayList<FetVitalDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<HechoVital> fetsVitalsResult = (List<HechoVital>) query.list();

            for (HechoVital fetVital : fetsVitalsResult) {
                fetsVitalsDTOList.add((FetVitalDTO) BasicUtils.entityToDTO(FetVitalDTO.class,  fetVital, fetVitalCriteria.getIdioma()));
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

        return fetsVitalsDTOList;
    }

    /**
     * Obtiene listado de documentos del procedimiento.
     * @param id
     * @param documentoCriteria
     * @return List<DocumentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        List<DocumentDTO> documentsDTOList = new ArrayList<DocumentDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<Documento> documentosResult = (List<Documento>) query.list();
            for (Documento document : documentosResult) {
                documentsDTOList.add((DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class,  document, documentCriteria.getIdioma()));
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

        return documentsDTOList;
    }
    
    /**
     * Obtiene listado de publicos objetivos del procedimiento.
     * @param id
     * @param poCriteria
     * @return List<PublicObjectiuDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) {
    	
        List<PublicObjectiuDTO> poDTOList = new ArrayList<PublicObjectiuDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {            
            criteris = BasicUtils.parseCriterias(PublicObjectiuCriteria.class, HQL_PUBLICO_OBJETIVO_ALIAS, HQL_TRADUCCIONES_ALIAS, poCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            entities.add(new FromClause(HQL_PUBLICO_OBJETIVO_CLASS, HQL_PUBLICO_OBJETIVO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PUBLICO_OBJETIVO_ALIAS, entities, poCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ProcedimentCriteria pc = new ProcedimentCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<PublicoObjetivo> poResult = (List<PublicoObjetivo>) query.list();

            for (PublicoObjetivo po : poResult) {
                poDTOList.add((PublicObjectiuDTO) BasicUtils.entityToDTO(PublicObjectiuDTO.class,  po, poCriteria.getIdioma()));
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

        return poDTOList;
        
    }
    
}
