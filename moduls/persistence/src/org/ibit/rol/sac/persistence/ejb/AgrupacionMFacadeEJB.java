package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.MateriaAgrupacionM;

/**
 * SessionBean para mantener y consultar Agrupaciones Materia.
 *
 * @ejb.bean
 *  name="sac/persistence/AgrupacionMFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.AgrupacionMFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class AgrupacionMFacadeEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 *  @deprecated Se usa desde el back antiguo
	 * Crea o actualiza una Agrupacion Materia.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param agrMateria	Entidad de tipo AgrupacionMateria a guardar.
	 * 
	 * @return Devuelve el identificador de la <code>AgrupacionMateria</code>.
	 */
	public Long guardarAgrupacionMaterias(AgrupacionMateria agrMateria) {
		Session session = getSession();

		try {

			session.saveOrUpdate(agrMateria);
			session.flush();

			return agrMateria.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
	}


	/**
	 *  @deprecated Usado desde el back antiguo
	 * Lista todas las Agrupaciones de materias.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve <code>List<AgrupacionMateria></code> de todas las agrupaciones de materias almacenadas.
	 */
	@SuppressWarnings("unchecked")
	public List<AgrupacionMateria> listarAgrupacionM() {
		Session session = getSession();

		try {

			Criteria criteri = session.createCriteria(AgrupacionMateria.class);

			return criteri.list();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene una Agrupacion materia.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la agrupación de materias a obtener.
	 * 
	 * @return Devuelve una instancia de tipo <code>AgrupacionMateria</code>.	
	 */
	public AgrupacionMateria obtenerAgrupacionMaterias(Long id) {

		Session session = getSession();

		try {

			AgrupacionMateria agrMateria = (AgrupacionMateria) session.load( AgrupacionMateria.class , id );
			Hibernate.initialize( agrMateria.getMateriasAgrupacionM() );

			return agrMateria;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene una Agrupacion Materia segun el nombre.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param nombre	Nombre de la agrupación de materias que se utiliza como filtro en la búsqueda.
	 * 
	 * @return Devuelve <code>AgrupacionMateria<code> filtrada por nombre.
	 */
	public AgrupacionMateria obtenerAgrupacionMPorNombre(String nombre) {

		Session session = getSession();

		try {

			Query query = session.getNamedQuery("agrupacionm.byname");
			query.setParameter( "nombre" , nombre );
			query.setMaxResults(1);
			query.setCacheable(true);

			AgrupacionMateria agrMateria = null;
			List result = query.list();

			if ( !result.isEmpty() ) {

				agrMateria = (AgrupacionMateria) result.get(0);
				Hibernate.initialize( agrMateria.getMateriasAgrupacionM() );

			}

			return agrMateria;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra una Agrupacion de materias.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id	Identificador de la agrupación de materias.
	 */
	public void borrarAgrupacionM(Long id) {
		
		Session session = getSession();

		try {

			AgrupacionMateria agrupacion = (AgrupacionMateria) session.load( AgrupacionMateria.class , id );

			if ( agrupacion.getMateriasAgrupacionM() != null ) {

				for ( MateriaAgrupacionM materiaAgrupacionM : agrupacion.getMateriasAgrupacionM() ) {

					if ( materiaAgrupacionM != null ) 
						session.delete(materiaAgrupacionM);
				}

			}

			session.delete(agrupacion);
			session.flush();

			//FIXME: 14/08/2013: Se intenta borrar por duplicado, se puede eliminar?
			session.delete(agrupacion);
			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 *  @deprecated No se usa
	 * Lista todas las Materias y sus Agrupaciones de materias.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve <code>List<MateriaAgrupacionM></code> de todas las agrupaciones de materias almacenadas y sus relaciones con materias.
	 */
	@SuppressWarnings("unchecked")
	public List<MateriaAgrupacionM> listarAgrupacionesMMaterias() {
		Session session = getSession();

		try {

			Criteria criteri = session.createCriteria(MateriaAgrupacionM.class);
			criteri.addOrder( Order.asc("orden") );
			criteri.setCacheable(true);

			List<MateriaAgrupacionM> result = criteri.list();
			for ( MateriaAgrupacionM agrupacion : result ) {

				Hibernate.initialize( agrupacion.getMateria() );

			}

			return result;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 *  @deprecated Usado desde el back antiguo
	 * Busca todos los {@link AgrupacionMateria} cuyo nombre contenga el String de entrada
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param nombre	Nombre de la agrupación de materias que se utiliza como filtro en la búsqueda.
	 * 
	 * @param idioma	Indica el idioma que se utiliza para filtrar en la búsqueda.
	 * 
	 * @return Devuelve <code>List<AgrupacionMateria></code> de todas las agrupaciones de materias si el nombre no es nulo o vacío, en caso contrario devuelve una lista vacía.
	 */
	@SuppressWarnings("unchecked")
	public List<AgrupacionMateria> buscar(final String nombre, final String idioma) {
		List<AgrupacionMateria> resultado = Collections.emptyList();

		if ( nombre != null && !"".equals( nombre.trim() ) ) {

			Session session = getSession();

			try {

				Query query = session.createQuery(
						"from AgrupacionMateria as agrM," +
						"     agrM.traducciones as trad " +

                        "where index(trad) = :idioma " +
						"    and upper(trad.nombre) like :nombre");

				query.setString("idioma", idioma);
				query.setString( "nombre" , "%" + nombre.trim().toUpperCase() + "%" );

				resultado = (List<AgrupacionMateria>) query.list();

			} catch (HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		}

		return resultado;

	}


	/**
	 * Crea o actualiza una Agrupacion de materias.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param agrMateria Entidad de tipo AgrupacionMateria a almnacenar
	 * 
	 * @param listaMateriasAsignadas	Listado de materias asignadas a la agrupación de materias.
	 * 
	 * @return Devuelve el identificador de la agrupación de materias guardada.
	 */
	public Long guardarAgrupacionMaterias(AgrupacionMateria agrMateria, List<MateriaAgrupacionM> listaMateriasAsignadas) {

		Session session = getSession();

		try {

			if ( listaMateriasAsignadas != null ) {

				for ( MateriaAgrupacionM materiaAgrupacionMaterias : listaMateriasAsignadas ) {

					if ( materiaAgrupacionMaterias != null ) 
						session.delete(materiaAgrupacionMaterias);

				}

			}

			List<MateriaAgrupacionM> listaMateriaAgrupacionMaterias = agrMateria.getMateriasAgrupacionM();

			for ( MateriaAgrupacionM materiaAgrupacionMaterias : listaMateriaAgrupacionMaterias )
				session.saveOrUpdate(materiaAgrupacionMaterias);

			session.saveOrUpdate(agrMateria);
			session.flush();

			return agrMateria.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

}
