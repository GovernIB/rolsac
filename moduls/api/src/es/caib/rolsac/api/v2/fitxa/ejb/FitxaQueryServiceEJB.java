package es.caib.rolsac.api.v2.fitxa.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.enllac.EnllacOrdenacio;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PublicoObjetivo;
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
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de fichas.
 *
 * @ejb.bean
 *  name="sac/api/FitxaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.fitxa.ejb.FitxaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public class FitxaQueryServiceEJB extends HibernateEJB {
    
    private static final long serialVersionUID = 3754585493900603537L;

    private static Log log = LogFactory.getLog(FitxaQueryServiceEJB.class);

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
    private static final String HQL_PUBLICO_OBJETIVO_CLASS = HQL_FICHA_ALIAS + ".publicosObjetivo";
    private static final String HQL_PUBLICO_OBJETIVO_ALIAS = "po";
    private static final String HQL_MATERIAS_CLASS = HQL_FICHA_ALIAS + ".materias";
    private static final String HQL_MATERIAS_ALIAS = "mat";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene listado de enlaces.
     * @param id
     * @param enllacCriteria
     * @return List<EnllacDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) {
        List<EnllacDTO> enllassosDTOList = new ArrayList<EnllacDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        try {
            
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

            session = getSession();
            Query query = qb.createQuery(session);
            List<Enlace> enllassosResult = (List<Enlace>) query.list();
            for (Enlace enllac : enllassosResult) {
                enllassosDTOList.add((EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class,  enllac, enllacCriteria.getIdioma()));
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

        return enllassosDTOList;
    }

    /**
     * Obtiene listado de documentos.
     * @param id
     * @param documentCriteria
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
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, documentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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
     * Obtiene listado de hechos vitales.
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
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_HECHOS_VITALES_CLASS, HQL_HECHOS_VITALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_HECHOS_VITALES_ALIAS, entities, fetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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
     * Obtiene listado de unidades administrativas.
     * @param id
     * @param unitatAdministrativaCriteria
     * @return List<UnitatAdministrativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
    	
        List<UnitatAdministrativaDTO> unitatADministrativaDTOList = new Vector<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>)query.list();
            for (UnidadAdministrativa unitatAdministrativa : unitatAdministrativaResult) {
                unitatADministrativaDTOList.add((UnitatAdministrativaDTO)BasicUtils.entityToDTO(
        				UnitatAdministrativaDTO.class, 
        				unitatAdministrativa, 
        				unitatAdministrativaCriteria.getIdioma())
        		);
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

        return new ArrayList<UnitatAdministrativaDTO>(unitatADministrativaDTOList);
        
    }
   
    /**
     * Obtiene listado de secciones.
     * @param id
     * @param seccioCriteria
     * @return List<SeccioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
    	
        List<SeccioDTO> seccioDTOList = new Vector<SeccioDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        
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

            session = getSession();
            Query query = qb.createQuery(session);
            List<Seccion> seccionResult = (List<Seccion>) query.list();
            for (Seccion seccio : seccionResult) {
                seccioDTOList.add((SeccioDTO)BasicUtils.entityToDTO(
                		SeccioDTO.class,  
                		seccio, 
                		seccioCriteria.getIdioma())
        		);
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

        return new ArrayList<SeccioDTO>(seccioDTOList);
        
    }
    
    /**
     * Obtiene listado de fichas UA.
     * @param id
     * @param fitxaUACriteria
     * @return List<FitxaUADTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FitxaUADTO> llistarFitxesUA(long id, FitxaUACriteria fitxaUACriteria) {
    	
        List<FitxaUADTO> fichaDTOList = new Vector<FitxaUADTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        
        try {
        	fitxaUACriteria.setFitxa(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FICHASUA_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaUACriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_FICHASUA_CLASS, HQL_FICHASUA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FICHASUA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<FichaUA> fichaResult = (List<FichaUA>) query.list();
            for (FichaUA fichaUA : fichaResult) {
                fichaDTOList.add((FitxaUADTO)BasicUtils.entityToDTO(
                		FitxaUADTO.class,  
                		fichaUA, 
                		fitxaUACriteria.getIdioma())
        		);
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

        return new ArrayList<FitxaUADTO>(fichaDTOList);
    }
    
    /**
     * Obtiene el numero de documentos.
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
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_DOCUMENTOS_CLASS, HQL_DOCUMENTOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_DOCUMENTOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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
     * Obtiene el numero de enlaces.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumEnllacos(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de hechos vitales.
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
     * Obtiene el numero de unidades administrativas.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumUnitatsAdministratives(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de secciones.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumSeccions(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el icono.
     * @param icono
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirIcona(Long icono) {
        return getArxiuDTO(icono);
    }

    /**
     * Obtiene la imagen.
     * @param imagen
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirImatge(Long imagen) {
        return getArxiuDTO(imagen);
    }

    /**
     * Obtiene el baner.
     * @param baner
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirBaner(Long baner) {
        return getArxiuDTO(baner);
    }
    
    /**
     * Obtiene listado de publicos objetivos de la ficha.
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
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_PUBLICO_OBJETIVO_CLASS, HQL_PUBLICO_OBJETIVO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PUBLICO_OBJETIVO_ALIAS, entities, poCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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

        return new ArrayList<PublicObjectiuDTO>(poDTOList);
    }
    
    /**
     * Obtiene listado de materias.
     * @param id
     * @param materiaCriteria
     * @return List<EnllacDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
    	
        List<MateriaDTO> materiasDTOList = new ArrayList<MateriaDTO>();
        List<CriteriaObject> criterios;
        Session session = null;
        
        try {         
            
            criterios = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIAS_ALIAS, HQL_TRADUCCIONES_ALIAS, materiaCriteria);
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_MATERIAS_CLASS, HQL_MATERIAS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIAS_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criterios);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criterios = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
            qb.extendCriteriaObjects(criterios);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Materia> materiasResult = (List<Materia>)query.list();
            for (Materia enllac : materiasResult) {
                materiasDTOList.add((MateriaDTO)BasicUtils.entityToDTO(MateriaDTO.class,  enllac, materiaCriteria.getIdioma()));
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

        return materiasDTOList;
        
    }
    
    /**
     * Obtiene el numero de enlaces.
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
            entities.add(new FromClause(HQL_FICHA_CLASS, HQL_FICHA_ALIAS));
            entities.add(new FromClause(HQL_MATERIAS_CLASS, HQL_MATERIAS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIAS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FitxaCriteria fc = new FitxaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHA_ALIAS, fc);
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
    
}
