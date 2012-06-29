package es.caib.rolsac.api.v2.fitxa.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
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
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
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
        Set<UnitatAdministrativaDTO> unitatADministrativaDTOSet = new HashSet<UnitatAdministrativaDTO>();
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
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();
            for (UnidadAdministrativa unitatAdministrativa : unitatAdministrativaResult) {
                unitatADministrativaDTOSet.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unitatAdministrativa, unitatAdministrativaCriteria.getIdioma()));
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

        return new ArrayList<UnitatAdministrativaDTO>(unitatADministrativaDTOSet);
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
        Set<SeccioDTO> seccioDTOSet = new HashSet<SeccioDTO>();
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
                seccioDTOSet.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class,  seccio, seccioCriteria.getIdioma()));
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

        return new ArrayList<SeccioDTO>(seccioDTOSet);
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
            numResultats  = ((Integer) query.uniqueResult()).intValue();
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
            numResultats  = ((Integer) query.uniqueResult()).intValue();
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
            numResultats  = ((Integer) query.uniqueResult()).intValue();
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
            numResultats  = ((Integer) query.uniqueResult()).intValue();
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
            numResultats  = ((Integer) query.uniqueResult()).intValue();
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
        return EJBUtils.getArxiuDTO(icono);
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
        return EJBUtils.getArxiuDTO(imagen);
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
        return EJBUtils.getArxiuDTO(baner);
    }
    
}
