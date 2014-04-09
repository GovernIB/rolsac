package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Tratamientos.
 *
 * @ejb.bean
 *  name="sac/persistence/TratamientoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TratamientoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TratamientoFacadeEJB extends HibernateEJB{

	private static final long serialVersionUID = -2146085960416377575L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	
	/**
	 * Crea o actualiza un Tratamiento
	 * 
	 * @param	tratamiento	Indica el tratamiento a guardar
	 * 
	 * @return Devuelve el identificador del tratamiento guardado
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public Long grabarTratamiento(Tratamiento tratamiento) {
		
		Session session = getSession();
		
		try {
			
			session.saveOrUpdate(tratamiento);
			session.flush();
			
			return tratamiento.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene un Tratamiento
	 * 
	 * @param	id	Identificador del tratamiento solicitado
	 * 
	 * @return	Devuelve <code>Tratamiento</code> solicitado
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public Tratamiento obtenerTratamiento(Long id) {
		
		Session session = getSession();
		
		try {
			
			return (Tratamiento) session.load(Tratamiento.class, id);
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Lista todos los Tratamientos
	 * 
	 * @param	pagina	Indica la última página visitada.
	 * 
	 * @param	resultats	Indica el número de resultados por página
	 * 
	 * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todos los tratamientos
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public ResultadoBusqueda listarTratamientos(int pagina, int resultats) {
		return listarTablaMaestraPaginada(pagina, resultats, listarTratamientos());
	}    

	
	/**
	 * Lista todos los Tratamientos
	 *
	 * @return Devuelve <code>List<Tratamiento></code>
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<Tratamiento> listarTratamientos() {
		
		Session session = getSession();
		
		try {
			
			Criteria criteri = session.createCriteria(Tratamiento.class);
			
			return castList(Tratamiento.class, criteri.list());
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Indica si un tratamiento tiene unidades asociadas.
	 * 
	 * @param id	Identificador del tratamiento
	 * 
	 * @return Devuelve <code>true</code> si el tratamiento tiene unidades asociadas
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public boolean tieneUnidades(Long id) {
		
		Session session = getSession();
		
		try {
			
			List<UnidadAdministrativa> uas = castList( UnidadAdministrativa.class, session.find("from UnidadAdministrativa as ua where ua.tratamiento.id = ?", id, Hibernate.LONG) );
			
			return ( uas.size() != 0 );
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Borra un tratamiento
	 * 
	 * @param	id	Identificador de un tratamiento
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void borrarTratamiento(Long id) {
		
		Session session = getSession();
		
		try {
						
			if ( tieneUnidades(id) )
				throw new EJBException("El tratamiento contiene Unidades Administrativas");
			
			Tratamiento tratamiento = (Tratamiento) session.load(Tratamiento.class, id);
			session.delete(tratamiento);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
}
