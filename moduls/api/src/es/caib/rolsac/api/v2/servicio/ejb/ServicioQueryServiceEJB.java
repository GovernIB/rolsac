package es.caib.rolsac.api.v2.servicio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.PublicoObjetivo;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalServicioCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.co.NormativaByServicioCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para consultas de servicios.
 *
 * @ejb.bean
 *  name="sac/api/ServicioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.servicio.ejb.ServicioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public class ServicioQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 3727131208139935651L;

    private static Log log = LogFactory.getLog(ServicioQueryServiceEJB.class);
    
    private static final String HQL_SERVICIO_CLASS = "Servicio";
    private static final String HQL_SERVICIO_ALIAS = "p";
    private static final String HQL_NORMATIVAS_CLASS = "Normativa";
    private static final String HQL_NORMATIVAS_ALIAS = "n";
    private static final String HQL_MATERIAS_CLASS = HQL_SERVICIO_ALIAS + ".materias";
    private static final String HQL_MATERIAS_ALIAS = "m";
    private static final String HQL_HECHOS_VITALES_SERVICIO_CLASS = HQL_SERVICIO_ALIAS + ".hechosVitalesServicios";
    private static final String HQL_HECHOS_VITALES_SERVICIO_ALIAS = "hp";
    private static final String HQL_HECHOS_VITALES_CLASS = HQL_HECHOS_VITALES_SERVICIO_ALIAS + ".hechoVital";
    private static final String HQL_HECHOS_VITALES_ALIAS = "h";
    private static final String HQL_DOCUMENTOS_CLASS = HQL_SERVICIO_ALIAS + ".documentos";
    private static final String HQL_DOCUMENTOS_ALIAS = "d";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    private static final String HQL_PUBLICO_OBJETIVO_CLASS = HQL_SERVICIO_ALIAS + ".publicosObjetivo";
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
     * Obtiene el numero de normativas del servicio.
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
            CriteriaObject normativaByServCO = new NormativaByServicioCriteria("p");
            normativaByServCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByServCO);
            List<FromClause> entities = new ArrayList<FromClause>();
            
            entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVAS_CLASS, HQL_NORMATIVAS_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".servicios", HQL_SERVICIO_ALIAS));            
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
     * Obtiene el numero de materias del servicio.
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
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_MATERIAS_CLASS, HQL_MATERIAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIAS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
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
     * Obtiene el numero de documentos del servicio.
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
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
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
     * Obtiene el numero de hechos vitales del servicio.
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
            criteris = BasicUtils.parseCriterias(FetVitalServicioCriteria.class, HQL_HECHOS_VITALES_SERVICIO_ALIAS, new FetVitalServicioCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_SERVICIO_CLASS, HQL_HECHOS_VITALES_SERVICIO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_SERVICIO_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
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
     * Obtiene listado de normativas del servicio.
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
            CriteriaObject normativaByServCO = new NormativaByServicioCriteria(HQL_SERVICIO_ALIAS);
            normativaByServCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByServCO);
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVAS_CLASS, HQL_NORMATIVAS_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".servicios", HQL_SERVICIO_ALIAS));            
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            List<Normativa> normativasResult = query.list();

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
     * Obtiene listado de materias del servicio.
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
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_MATERIAS_CLASS, HQL_MATERIAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIAS_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Materia> materiesResult = query.list();

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
     * Obtiene listado de hechos vitales del servicio.
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
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_SERVICIO_CLASS, HQL_HECHOS_VITALES_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_CLASS, HQL_HECHOS_VITALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_ALIAS, entities, fetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<HechoVital> fetsVitalsResult = query.list();

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
     * Obtiene listado de documentos del servicio.
     * @param id
     * @param documentoCriteria
     * @return List<DocumentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<DocumentoServicioDTO> llistarDocuments(long id, DocumentoServicioCriteria documentoServicioCriteria) {
        List<DocumentoServicioDTO> documentsDTOList = new ArrayList<DocumentoServicioDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

   /*     try {            
            criteris = BasicUtils.parseCriterias(DocumentoServicioCriteria.class, HQL_DOCUMENTOS_ALIAS, HQL_TRADUCCIONES_ALIAS, documentoServicioCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, documentoServicioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<DocumentoServicio> documentosResult = query.list();
            for (DocumentoServicio document : documentosResult) {
                documentsDTOList.add((DocumentoServicioDTO) BasicUtils.entityToDTO(DocumentoServicioDTO.class,  document, documentoServicioCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
        } catch (QueryBuilderException e) {
            log.error(e);
        } finally {
            close(session);
        }*/
        
        
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.llistarDocumentoServicio(documentoServicioCriteria);

      //  return documentsDTOList;
    }
    
    /**
     * Obtiene listado de publicos objetivos del servicio.
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
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            entities.add(new FromClause(HQL_PUBLICO_OBJETIVO_CLASS, HQL_PUBLICO_OBJETIVO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PUBLICO_OBJETIVO_ALIAS, entities, poCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            ServicioCriteria pc = new ServicioCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<PublicoObjetivo> poResult = query.list();

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
