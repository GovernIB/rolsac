package org.ibit.rol.sac.persistence.ejb;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.ibit.rol.sac.model.Comentario;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

/**
 * SessionBean para gestionar usuarios.
 *
 * @ejb.bean
 *  name="sac/persistence/UsuarioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.UsuarioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UsuarioFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

	/**
	 * Obtiene referéncia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();


	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un nuevo usuario.
	 * 
	 * @param usuario	Indica un usuario a guardar.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void grabarUsuario(Usuario usuario) {

		Session session = getSession();

		try {

			if ( !userIsAdmin() && !getUsuario(session).hasRaizAccess() )
				throw new SecurityException("No puede administrar usuarios");

			if ( "sacsystem".equals(usuario.getPerfil()) )
				throw new SecurityException("No puede crear usuarios de sistema");

			session.saveOrUpdate(usuario);
			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene una lista de usuarios.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List buscarUsuarios(Map parametros) {

		Session session = getSession();

		try {

			Criteria criteria = session.createCriteria(Usuario.class);
			populateCriteria(criteria, parametros);

			return criteria.list();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Obtiene un usuario.
	 * 
	 * @param	id	Identificador de un usuario
	 * 
	 * @return Devuelve <code>Usuario</code> solicitado.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.oper},${role.super}"
	 */
	public Usuario obtenerUsuario(Long id) {

		Session session = getSession();

		try {

			Usuario usuario = (Usuario) session.load(Usuario.class, id);
			Hibernate.initialize( usuario.getUnidadesAdministrativas() );
			Hibernate.initialize( usuario.getPerfilsGestor() );

			return usuario;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un usuario filtrado por nombre
	 * 
	 * @param username	Indica el nombre de usuario
	 * 
	 * @return Devuelve <code>Usuario</code> solicitado.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.oper},${role.super}"
	 */
	public Usuario obtenerUsuariobyUsername(String username) {

		Session session = getSession();

		try {

			Criteria criteri = session.createCriteria(Usuario.class);
			criteri.add( Expression.eq("username", username) );
			Usuario usu = (Usuario) criteri.uniqueResult();
			Hibernate.initialize( usu.getUnidadesAdministrativas() );

			return usu;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Asigna la administración de una unidad a un usuario.
	 * 
	 * @param	idUsuario	Identificador de un usuario
	 * 
	 * @param	idUA	Identificador de una unidad administrativa
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void asignarUnidad(Long idUsuario, Long idUA) {

		Session session = getSession();

		try {

			Usuario caller = getUsuario(session);
			Usuario usuario = (Usuario) session.load(Usuario.class, idUsuario);
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);

			if ( !userIsSystem() &&  (!caller.hasRaizAccess() || !caller.hasAccess(ua)) )
				throw new SecurityException("Debe ser administrador de unidad y tener acceso a este arbol");

			ua.getUsuarios().add(usuario);
			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Desasigna la administración de una unidad a un usuario.
	 * 
	 * @param	idUsuario	Identificador de un usuario
	 * 
	 * @param	idUA	Identificador de una unidad administrativa
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void desasignarUnidad(Long idUsuario, Long idUA) {

		Session session = getSession();

		try {

			Usuario caller = getUsuario(session);
			Usuario usuario = (Usuario) session.load(Usuario.class, idUsuario);
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);

			if ( !userIsSystem() && (!caller.hasRaizAccess() || !caller.hasAccess(ua)) )
				throw new SecurityException("Debe ser administrador de la unidad y tener acceso a este arbol");

			ua.getUsuarios().remove(usuario);
			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Borra un usuario.
	 * 
	 * @param	id	Identificador de un usuario
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void borrarUsuario(Long id) {

		Session session = getSession();

		try {

			if ( !userIsSystem() && !getUsuario(session).hasRaizAccess() )
				throw new SecurityException("No puede administrar usuarios");

			Usuario usuario = (Usuario) session.load(Usuario.class, id);

			if ( !userIs( usuario.getPerfil() ) )
				throw new SecurityException("No puede borrar usuarios de un perfil superior al suyo");

			Iterator iteradorUA = usuario.getUnidadesAdministrativas().iterator();
			while ( iteradorUA.hasNext() ) {

				UnidadAdministrativa ua = (UnidadAdministrativa) iteradorUA.next();
				ua.getUsuarios().remove(usuario);

			}

			List comentarios = session.find("from Comentario as com where com.usuario.id = ?", id, Hibernate.LONG);
			Iterator iteradorComentarios = comentarios.iterator();
			while ( iteradorComentarios.hasNext() ) {

				Comentario comentario = (Comentario) iteradorComentarios.next();
				comentario.setUsuario(null);

			}

			session.delete(usuario);
			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	private void populateCriteria(Criteria criteria, Map parametros) throws HibernateException {

		parametros.remove("id");
		
		// Si se especifica el id de una UA como filtro, toca hacer un JOIN, así que no
		// se puede procesar directamente. Guardaremos el id y lo procesaremos al salir del bucle.
		boolean hayIdUA = parametros.containsKey("idUA");
		boolean hayIdPerfilGestor = parametros.containsKey("idPerfilGestor");
		Long idUA = null;
		Long idPerfilGestor = null;

		
		if (hayIdUA) {
			idUA = (Long)parametros.get("idUA");
			parametros.remove("idUA");
		}
		if (hayIdPerfilGestor) {
			idPerfilGestor = (Long)parametros.get("idPerfilGestor");
			parametros.remove("idPerfilGestor");
		}
		
		Iterator iterator = parametros.keySet().iterator(); 
		while ( iterator.hasNext() ) {

			String key = (String) iterator.next();
			Object value = parametros.get(key);

			if ( value != null ) {

				if ( value instanceof String ) {

					String sValue = (String) value;

					if ( sValue.length() > 0 ) {

						String valor = "%" + sValue + "%";
						criteria.add( Expression.ilike(key, valor) );

					}

				} else {

					criteria.add( Expression.eq(key, value) );

				}

			}

		}
		
		// Procesamos el id de la UA solicitada como filtro dentro del objeto criteria.
		if (hayIdUA) {	
			criteria = criteria.createCriteria("unidadesAdministrativas").add(Expression.eq("id", idUA));
		}
		
		if (hayIdPerfilGestor) {
			criteria = criteria.createCriteria("perfilsGestor").add(Expression.eq("id", idPerfilGestor));
		}

	}
	
	
	/**
	 * PORMAD
	 * Obtiene un usuario filtrado por el nombre de usuario
	 * 
	 * @param	username	Indica el nombre de usuario
	 * 
	 * @return	Devuelve <code>Usuario</code>
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public Usuario obtenerUsuariobyUsernamePMA(String username) {

		Session session = getSession();

		try {

			Criteria criteri = session.createCriteria(Usuario.class);
			criteri.add( Expression.eq("username", username) );
			Usuario usu = (Usuario) criteri.uniqueResult();

			return usu;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

}
