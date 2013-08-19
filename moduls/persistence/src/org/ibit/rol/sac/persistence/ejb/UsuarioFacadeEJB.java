package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Comentario;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    /**
     * Obtiene refer�ncia al ejb de control de Acceso.
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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void grabarUsuario(Usuario usuario) {
        Session session = getSession();
        try {
            if (!userIsAdmin() && !getUsuario(session).hasRaizAccess()) {
                throw new SecurityException("No puede administrar usuarios");
            }

            if ("sacsystem".equals(usuario.getPerfil())) {
                throw new SecurityException("No puede crear usuarios de sistema");
            }

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
            Criteria criteri = session.createCriteria(Usuario.class);
            populateCriteria(criteri, parametros);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.oper},${role.super}"
     */
    public Usuario obtenerUsuario(Long id) {
        Session session = getSession();
        try {
            Usuario usuario = (Usuario) session.load(Usuario.class, id);
            Hibernate.initialize(usuario.getUnidadesAdministrativas());

            return usuario;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un usuario dado el username
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.oper},${role.super}"
     */
    public Usuario obtenerUsuariobyUsername(String username) {
        Session session = getSession();
        try {
            
            Criteria criteri = session.createCriteria(Usuario.class);
            criteri.add(Expression.eq("username", username));
            Usuario usu = (Usuario)criteri.uniqueResult();
            Hibernate.initialize(usu.getUnidadesAdministrativas());
            return usu;            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }    
    
    /**
     * Obtiene un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public List listarUsuariosPerfil(String perfil) {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Usuario.class);
            if (perfil != null) {
                criteri.add(Expression.eq("perfil", perfil));
            }

            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Asigna la administraci�n de una unidad a un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void asignarUnidad(Long usuario_id, Long ua_id) {
        Session session = getSession();
        try {
            Usuario caller = getUsuario(session);

            Usuario usuario = (Usuario) session.load(Usuario.class, usuario_id);
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua_id);

            if (!userIsSystem() && (!caller.hasRaizAccess() || !caller.hasAccess(ua)) ) {
                throw new SecurityException("Debe ser administrador de unidad y tener acceso a este arbol");
            }

            ua.getUsuarios().add(usuario);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Desasigna la administraci�n de una unidad a un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void desasignarUnidad(Long usuario_id, Long ua_id) {
        Session session = getSession();
        try {
            Usuario caller = getUsuario(session);

            Usuario usuario = (Usuario) session.load(Usuario.class, usuario_id);
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua_id);

            if (!userIsSystem() && (!caller.hasRaizAccess() || !caller.hasAccess(ua)) ) {
                throw new SecurityException("Debe ser administrador de la unidad y tener acceso a este arbol");
            }

            ua.getUsuarios().remove(usuario);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene las unidades administrativas de un usuario
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarUA(Long id) {
        Session session = getSession();
        try {
            Usuario usuario = (Usuario) session.load(Usuario.class, id);
            Hibernate.initialize(usuario.getUnidadesAdministrativas());
            return new ArrayList(usuario.getUnidadesAdministrativas());
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarUsuario(Long id) {
        Session session = getSession();
        try {
            if (!userIsSystem() && !getUsuario(session).hasRaizAccess()) {
                throw new SecurityException("No puede administrar usuarios");
            }

            Usuario usuario = (Usuario) session.load(Usuario.class, id);
            
            if (!userIs(usuario.getPerfil())) {
                throw new SecurityException("No puede borrar usuarios de un perfil superior al suyo");
            }

            for (Iterator iterator = usuario.getUnidadesAdministrativas().iterator(); iterator.hasNext();) {
                UnidadAdministrativa ua = (UnidadAdministrativa) iterator.next();
                ua.getUsuarios().remove(usuario);
            }

            List comentarios = session.find("from Comentario as com where com.usuario.id = ?", id, Hibernate.LONG);
            for (Iterator iterator = comentarios.iterator(); iterator.hasNext();) {
                Comentario comentario = (Comentario) iterator.next();
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

    private void populateCriteria(Criteria criteri, Map parametros) {
        parametros.remove("id");
        for (Iterator iterator = parametros.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = parametros.get(key);
            if (value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                    	String valor = "%" + sValue + "%";
                        criteri.add(Expression.ilike(key, valor));
                    }
                } else {
                    criteri.add(Expression.eq(key, value));
                }
            }
        }
    }

    /**
     * A�ade una nueva unidad
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void anyadirUnidad(Long unidad_id, Long usu_id) {
        Session session = getSession();
        try {
            Usuario usu = (Usuario) session.load(Usuario.class, usu_id);
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
            usu.getUnidadesAdministrativas().add(unidad);
            session.flush();

        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * elimina una unidad del usuario
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void eliminarUnidad(Long unidad_id, Long usu_id) {
        Session session = getSession();
        try {
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
            Usuario usu = (Usuario) session.load(Usuario.class, usu_id);
            usu.getUnidadesAdministrativas().remove(unidad);
            session.flush();
            
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }
    
    /**
     * PORMAD
     * Obtiene un usuario dado el username
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Usuario obtenerUsuariobyUsernamePMA(String username) {
        Session session = getSession();
        try {
            
            Criteria criteri = session.createCriteria(Usuario.class);
            criteri.add(Expression.eq("username", username));
            Usuario usu = (Usuario)criteri.uniqueResult();
            return usu;            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
}
