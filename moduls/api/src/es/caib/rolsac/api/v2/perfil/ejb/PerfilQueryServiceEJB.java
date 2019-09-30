package es.caib.rolsac.api.v2.perfil.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilQueryService;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para consultas de perfiles.
 *
 * @ejb.bean name="sac/api/PerfilQueryServiceEJB"
 *           jndi-name="es.caib.rolsac.api.v2.perfil.ejb.PerfilQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class PerfilQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = -7461793014109413857L;

	private static Log log = LogFactory.getLog(PerfilQueryService.class);

	private static final String HQL_PERFIL_CLASS = "PerfilCiudadano";
	private static final String HQL_PERFIL_ALIAS = "perf";
	private static final String HQL_ICONES_FAMILIA_CLASS = HQL_PERFIL_ALIAS + ".iconosFamilia";
	private static final String HQL_ICONES_FAMILIA_ALIAS = "icoFam";
	private static final String HQL_ICONES_MATERIA_CLASS = HQL_PERFIL_ALIAS + ".iconosMateria";
	private static final String HQL_ICONES_MATERIA_ALIAS = "icoMat";

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Obtiene listado de iconos famlia.
	 * 
	 * @param id
	 * @param iconaFamiliaCriteria
	 * @return List<IconaFamiliaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<IconaFamiliaDTO> llistarIconesFamilia(final long id, final IconaFamiliaCriteria iconaFamiliaCriteria) {
		final List<IconaFamiliaDTO> iconaDTOList = new ArrayList<IconaFamiliaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONES_FAMILIA_CLASS,
					iconaFamiliaCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
			entities.add(new FromClause(HQL_ICONES_FAMILIA_CLASS, HQL_ICONES_FAMILIA_ALIAS));
			final QueryBuilder qb = new QueryBuilder(HQL_ICONES_FAMILIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			final PerfilCriteria pc = new PerfilCriteria();
			pc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<IconoFamilia> iconaResult = query.list();
			for (final IconoFamilia icona : iconaResult) {
				iconaDTOList.add((IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, icona,
						iconaFamiliaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return iconaDTOList;
	}

	/**
	 * Obtiene listado de iconos materia.
	 * 
	 * @param id
	 * @param iconaMateriaCriteria
	 * @return List<IconaMateriaDTO>
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<IconaMateriaDTO> llistarIconesMateria(final long id, final IconaMateriaCriteria iconaMateriaCriteria) {
		final List<IconaMateriaDTO> iconaDTOList = new ArrayList<IconaMateriaDTO>();
		List<CriteriaObject> criteris;
		Session session = null;

		try {
			criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONES_FAMILIA_CLASS,
					iconaMateriaCriteria);
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
			entities.add(new FromClause(HQL_ICONES_MATERIA_CLASS, HQL_ICONES_MATERIA_ALIAS));
			final QueryBuilder qb = new QueryBuilder(HQL_ICONES_MATERIA_ALIAS, entities, null, null);
			qb.extendCriteriaObjects(criteris);

			final PerfilCriteria pc = new PerfilCriteria();
			pc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			final List<IconoMateria> iconaResult = query.list();
			for (final IconoMateria icona : iconaResult) {
				iconaDTOList.add((IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, icona,
						iconaMateriaCriteria.getIdioma()));
			}
		} catch (final HibernateException e) {
			log.error(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return iconaDTOList;
	}

	/**
	 * Obtiene listado de iconos familia.
	 * 
	 * @param id
	 * @return int
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumIconesFamilia(final long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;

		try {
			criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONES_FAMILIA_ALIAS,
					new IconaFamiliaCriteria());
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
			entities.add(new FromClause(HQL_ICONES_FAMILIA_CLASS, HQL_ICONES_FAMILIA_ALIAS));
			final QueryBuilder qb = new QueryBuilder(HQL_ICONES_FAMILIA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			final PerfilCriteria pc = new PerfilCriteria();
			pc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (final HibernateException e) {
			log.error(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

	/**
	 * Obtiene listado de iconos materia.
	 * 
	 * @param id
	 * @return int
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int getNumIconesMateria(final long id) {
		List<CriteriaObject> criteris;
		Session session = null;
		int numResultats = 0;
		try {
			criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONES_FAMILIA_ALIAS,
					new IconaMateriaCriteria());
			final List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
			entities.add(new FromClause(HQL_ICONES_MATERIA_CLASS, HQL_ICONES_MATERIA_ALIAS));
			final QueryBuilder qb = new QueryBuilder(HQL_ICONES_MATERIA_ALIAS, entities, null, null, true);
			qb.extendCriteriaObjects(criteris);

			final PerfilCriteria pc = new PerfilCriteria();
			pc.setId(String.valueOf(id));
			criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			final Query query = qb.createQuery(session);
			numResultats = getNumberResults(query);
		} catch (final HibernateException e) {
			log.error(e);
		} catch (final CriteriaObjectParseException e) {
			log.error(e);
		} catch (final QueryBuilderException e) {
			log.error(e);
		} finally {
			close(session);
		}

		return numResultats;
	}

}