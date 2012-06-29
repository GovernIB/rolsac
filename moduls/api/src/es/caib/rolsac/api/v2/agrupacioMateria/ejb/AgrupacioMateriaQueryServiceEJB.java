package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Materia;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

/**
 * SessionBean para consultas de agrupaciones de materias.
 *
 * @ejb.bean
 *  name="sac/api/AgrupacioMateriaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.agrupacioMateria.ejb.AgrupacioMateriaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class AgrupacioMateriaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -3538074701846345919L;

    private static Log log = LogFactory.getLog(AgrupacioMateriaQueryServiceEJB.class);
    
    private static final String HQL_AGRUPACION_MATERIA_CLASS = "AgrupacionMateria";
    private static final String HQL_AGRUPACION_MATERIA_ALIAS = "am";
    private static final String HQL_MATERIA_AGRUPACION_CLASS = HQL_AGRUPACION_MATERIA_ALIAS + ".materiasAgrupacionM";
    private static final String HQL_MATERIA_AGRUPACION_ALIAS = "ma";
    private static final String HQL_MATERIA_CLASS = HQL_MATERIA_AGRUPACION_ALIAS + ".materia";
    private static final String HQL_MATERIA_ALIAS = "m";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
            
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene la seccion.
     * @param idSeccio
     * @return SeccioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */   
    public SeccioDTO obtenirSeccio(long idSeccio) {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId(String.valueOf(idSeccio));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirSeccio(seccioCriteria);
    }

    /**
     * Obtiene el listado de materias.
     * @param id
     * @param materiaCriteria
     * @return List<MateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        if (StringUtils.isBlank(materiaCriteria.getOrdenacio())) {
            materiaCriteria.setOrdenacio(HQL_MATERIA_AGRUPACION_ALIAS + ".orden");
            }
      
        try {            
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS, materiaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACION_MATERIA_CLASS, HQL_AGRUPACION_MATERIA_ALIAS));            
            entities.add(new FromClause(HQL_MATERIA_AGRUPACION_CLASS, HQL_MATERIA_AGRUPACION_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioMateriaCriteria amc = new AgrupacioMateriaCriteria();
            amc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACION_MATERIA_ALIAS, amc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Materia> materiaResult = (List<Materia>) query.list();

            for (Materia materia : materiaResult) {
                materiaDTOList.add((MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class,  materia, materiaCriteria.getIdioma()));
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

        return materiaDTOList;
    }

    /**
     * Obtiene el numero de materias
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
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, new MateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACION_MATERIA_CLASS, HQL_AGRUPACION_MATERIA_ALIAS));            
            entities.add(new FromClause(HQL_MATERIA_AGRUPACION_CLASS, HQL_MATERIA_AGRUPACION_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioMateriaCriteria amc = new AgrupacioMateriaCriteria();
            amc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACION_MATERIA_ALIAS, amc);
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

}
