package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Archivo;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.Collection;

/**
 * SessionBean para mantener y consultar IconoFamilia.
 *
 * @ejb.bean
 *  name="sac/persistence/IconoFamiliaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IconoFamiliaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IconoFamiliaFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = -5169068748031601688L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un Iconofamilia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param icono	Indica el icono a guardar.
	 * 
	 * @param idFamilia	Identificador de la familia a la que pertecene el icono.
	 * 
	 * @param idPerfil	Identificador del perfil al que está asociado el icono.
	 * 
	 * @return Devuelve el identificador del icono guardado
	 */
	public Long grabarIconoFamilia(IconoFamilia icono, Long idFamilia, Long idPerfil) {

		Session session = getSession();
		try {

			if ( icono.getId() == null ) {

				Familia familia = (Familia) session.load( Familia.class, idFamilia );
				PerfilCiudadano perfil = (PerfilCiudadano) session.load( PerfilCiudadano.class, idPerfil );
				familia.addIcono(icono);
				perfil.addIconoFamilia(icono);

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
	 * Obtiene un iconofamilia.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public IconoFamilia obtenerIconoFamilia(Long id) {
		Session session = getSession();
		try {
			IconoFamilia iconoFamilia = (IconoFamilia) session.load(IconoFamilia.class, id);
			Hibernate.initialize(iconoFamilia.getIcono());
			return iconoFamilia;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene el icono de la familia.
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
			
			IconoFamilia iconoFamilia = (IconoFamilia) session.load( IconoFamilia.class, id );
			Hibernate.initialize( iconoFamilia.getIcono() );
			
			return iconoFamilia.getIcono();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	/**
	 * Borra una coleccion de IconoFamilia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param listadoIconos	Listado de identificadores de iconos para borrar.
	 */
	public void borrarIconosFamilia(Collection<Long> listadoIconos) {
		
		Session session = getSession();
		IconoFamilia icono;
		try {
			
			for ( Long iconoId : listadoIconos ) {
				
				icono = (IconoFamilia) session.load( IconoFamilia.class, iconoId );
				icono.getFamilia().removeIcono(icono);
				icono.getPerfil().removeIconoFamilia(icono);
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
