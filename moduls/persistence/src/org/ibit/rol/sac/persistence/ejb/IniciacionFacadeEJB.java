package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegateI;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Iniciacion
 *
 * @ejb.bean
 *  name="sac/persistence/IniciacionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IniciacionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IniciacionFacadeEJB extends HibernateEJB implements IniciacionDelegateI {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Obtiene una  iniciación.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param id	Identificador de la iniciación.
	 * 
	 * @return Devuelve la iniciación solictada.
	 */
	public Iniciacion obtenerIniciacion(Long id) {

		Session session = getSession();
		try {

			Iniciacion iniciacion = (Iniciacion) session.load(Iniciacion.class, id);

			return iniciacion;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Lista todas las iniciaciones.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	pagina	Número de página actual.
	 * 
	 * @param	resultats	Número de resultados por página.
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @return	Devuelve ResultadoBusqueda con un listado de todas las iniciaciones.
	 */
	public ResultadoBusqueda listarIniciacion(int pagina, int resultats, String idioma) {

		return listarTablaMaestraPaginada( pagina, resultats, listarTMIniciacion(idioma) );

	}


	private List listarTMIniciacion(String idioma) {

		Session session = getSession();

		try {

			StringBuilder consulta = new StringBuilder("select ini.id, ini.codigoEstandar, trad.nombre ");
			consulta.append("from Iniciacion as ini, ini.traducciones as trad ");
			consulta.append("where index(trad) = :idioma ");
			consulta.append("order by ini.codigoEstandar asc");

			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idioma", idioma);

			return query.list();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}    	

	}


	/**
	 * Lista todas las iniciaciones.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve una lista de todas las iniciaciones.
	 */
	public List listarIniciacion() {

		Session session = getSession();
		try {

			Criteria criteri = session.createCriteria(Iniciacion.class);

			return criteri.list();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza un tipo Iniciacion.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param	tipo	Iniciación a guardar.
	 * 
	 * @return Identificador de la iniciación guardada.
	 */
	public Long grabarIniciacion(Iniciacion tipo) {

		Session session = getSession();
		try {

			session.saveOrUpdate(tipo);
			session.flush();

			return tipo.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra un Tipo Iniciacion.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id	Identificador de la iniciación a borrar.
	 */
	public void borrarIniciacion(Long id) {
		
		Session session = getSession();
		try {
			
			Iniciacion tipo  = (Iniciacion) session.load(Iniciacion.class, id);
			session.delete(tipo);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

}
