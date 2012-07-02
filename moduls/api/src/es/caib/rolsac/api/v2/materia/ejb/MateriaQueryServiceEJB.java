package es.caib.rolsac.api.v2.materia.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

/**
 * SessionBean para consultas de materia.
 *
 * @ejb.bean
 *  name="sac/api/MateriaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.materia.ejb.MateriaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class MateriaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -219381711492499464L;

    private static Log log = LogFactory.getLog(MateriaQueryServiceEJB.class);
    
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
    private static final String HQL_UA_CLASS = HQL_UNIDAD_MATERIA_ALIAS + ".unidad";
    private static final String HQL_UA_ALIAS = "uA";
    private static final String HQL_ICONA_MATERIA_CLASS = HQL_MATERIA_ALIAS + ".iconos";
    private static final String HQL_ICONA_MATERIA_ALIAS = "i";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";  
 
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene la fotografia.
     * @param idFoto
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO getFotografia(long idFoto) {
        return getArxiuDTO(idFoto);
    }
    
    /**
     * Obtiene el icono.
     * @param idIcona
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO getIcona(long idIcona) {
        return getArxiuDTO(idIcona);
    }
    
    /**
     * Obtiene la icona gran.
     * @param idIconaGran
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return getArxiuDTO(idIconaGran);
    }
    
    /**
     * Obtiene el numero de fichas.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumFitxes(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de agrupaciones materia.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumAgrupacioMateries(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de procedimientos.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumProcedimentsLocals(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de unidades materia.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumUnitatsMateries(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene el numero de iconos.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumIcones(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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
     * Obtiene listado de procedimientos
     * @param id
     * @param procedimentCriteria
     * @return List<ProcedimentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>) query.list();
            for (ProcedimientoLocal procediment : procedimentsResult) {
                procedimentsDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class,  procediment, procedimentCriteria.getIdioma()));
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

        return procedimentsDTOList;
    }

    /**
     * Obtiene listado de fichas
     * @param id
     * @param fitxaCriteria
     * @return List<FitxaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {        
        List<FitxaDTO> fitxesDTOList = new ArrayList<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<Ficha> fitxesResult = (List<Ficha>) query.list();
            for (Ficha fitxa : fitxesResult) {
                fitxesDTOList.add((FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class,  fitxa, fitxaCriteria.getIdioma()));
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

        return fitxesDTOList;
    }

    /**
     * Obtiene listado de agrupaciones materia.
     * @param id
     * @param agrupacioMateriaCriteria
     * @return List<AgrupacioMateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        List<AgrupacioMateriaDTO> agrupacioMateriaDTOList = new ArrayList<AgrupacioMateriaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<AgrupacionMateria> agrupacionsMateriaResult = (List<AgrupacionMateria>) query.list();
            for (AgrupacionMateria agrupacionMateria : agrupacionsMateriaResult) {
                agrupacioMateriaDTOList.add((AgrupacioMateriaDTO) BasicUtils.entityToDTO(AgrupacioMateriaDTO.class,  agrupacionMateria, agrupacioMateriaCriteria.getIdioma()));
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

        return agrupacioMateriaDTOList;
    }

    /**
     * Obtiene listado de unidades materia.
     * @param id
     * @param unitatMateriaCriteria
     * @return List<UnitatMateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) {
        List<UnitatMateriaDTO> unitatsMateriesDTOList = new ArrayList<UnitatMateriaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<UnidadMateria> unitatMateriesResult = (List<UnidadMateria>) query.list();
            for (UnidadMateria unidadMateria : unitatMateriesResult) {
                unitatsMateriesDTOList.add((UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class,  unidadMateria, unitatMateriaCriteria.getIdioma()));
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

        return unitatsMateriesDTOList;
    }

    /**
     * Obtiene listado de iconos materia.
     * @param id
     * @param iconaMateriaCriteria
     * @return List<IconaMateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        List<IconaMateriaDTO> iconaMateriaDTOList = new ArrayList<IconaMateriaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<IconoMateria> iconesMateriaResult = (List<IconoMateria>) query.list();
            for (IconoMateria iconaMateria : iconesMateriaResult) {
                iconaMateriaDTOList.add((IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class,  iconaMateria, null));
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

        return iconaMateriaDTOList;
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
        List<UnitatAdministrativaDTO> unitatsAdministrativesDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {            
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_UNIDAD_MATERIA_CLASS, HQL_UNIDAD_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            MateriaCriteria mc = new MateriaCriteria();
            mc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, mc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();
            for (UnidadAdministrativa unidadAdministrativa : unitatAdministrativaResult) {
                unitatsAdministrativesDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unidadAdministrativa, unitatAdministrativaCriteria.getIdioma()));
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

        return unitatsAdministrativesDTOList;
    }


}
