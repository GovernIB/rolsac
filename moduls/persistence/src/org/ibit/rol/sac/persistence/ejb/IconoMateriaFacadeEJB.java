package org.ibit.rol.sac.persistence.ejb;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;

/**
 * SessionBean para mantener y consultar IconoMateria.
 *
 * @ejb.bean
 *  name="sac/persistence/IconoMateriaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IconoMateriaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IconoMateriaFacadeEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un IconoMateria.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param icono	Indica el icono a guardar.
	 * 
	 * @param idMateria	Identificador de la materia asociada al icono.
	 * 
	 * @param idPerfil	Identificador del perfil asociado al icono.
	 * 
	 * @return	Devuelve el identificador del icono guardado.
	 */
	public Long grabarIconoMateria(IconoMateria icono, Long idMateria, Long idPerfil) {

		Session session = getSession();
		try {

			if ( icono.getId() == null ) {

				Materia materia = (Materia)session.load( Materia.class, idMateria );
				PerfilCiudadano perfil = (PerfilCiudadano)session.load( PerfilCiudadano.class, idPerfil );
				materia.addIcono(icono);
				perfil.addIconoMateria(icono);

			} else {

				session.update(icono);

			}

			session.flush();

			return icono.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un IconoMateria.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de un icono.
	 * 
	 * @return	Devuelve <code>IconoMateria</code> solicitado.
	 */
	public IconoMateria obtenerIconoMateria(Long id) {
		
		Session session = getSession();
		try {
			
			IconoMateria icono = (IconoMateria) session.load( IconoMateria.class, id );
			Hibernate.initialize( icono.getIcono() );
			
			return icono;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene el icono de la materia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de un icono.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene el icono solicitado.
	 */
	public Archivo obtenerIcono(Long id) {
		
		Session session = getSession();
		try {
			
			IconoMateria iconoMateria = (IconoMateria) session.load( IconoMateria.class, id );
			Hibernate.initialize( iconoMateria.getIcono() );
			
			return iconoMateria.getIcono();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * @deprecated Únicamente se usa desde el back antiguo 
	 * Borra un IconoMateria.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void borrarIconoMateria(Long id) {
		Session session = getSession();
		try {
			IconoMateria icono = (IconoMateria) session.load(IconoMateria.class, id);
			icono.getMateria().removeIcono(icono);
			icono.getPerfil().removeIconoMateria(icono);
			session.delete(icono);
			session.flush();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Borra una coleccion de IconoMateria.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param listaIconos	Listado de identificadores de iconos a borrar.
	 */
	public void borrarIconosMateria(Collection<Long> listaIconos) {
		
		Session session = getSession();
		IconoMateria icono;
		try {
			
			for ( Long iconoId : listaIconos ) {
				
				icono = (IconoMateria) session.load( IconoMateria.class, iconoId );
				icono.getMateria().removeIcono(icono);
				icono.getPerfil().removeIconoMateria(icono);
				session.delete(icono);
				
			}
			
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

}
