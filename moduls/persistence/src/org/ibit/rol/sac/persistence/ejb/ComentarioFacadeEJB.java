package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.Comentario;
import org.ibit.rol.sac.model.ComentarioFicha;
import org.ibit.rol.sac.model.ComentarioProcedimiento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ReportarErrorComentario;

/**
 * @deprecated
 * SessionBean para mantener y consultar Comentarios.
 *
 * @ejb.bean
 *  name="sac/persistence/ComentarioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ComentarioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
//TODO: 20/08/2013 Los métodos de esta clase están deprecated, muchos no son referenciados desde ninguna parte de la aplicación y otros únicamente los utiliza el back antiguo.
public abstract class ComentarioFacadeEJB extends HibernateEJB {
	
	public static final String excepcionSeguridadComentario = "No tiene acceso al comentario";

    /**
     * Obtiene referencia al ejb de control de Acceso.
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
     *  @deprecated
     * Crea un comentario de ficha.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.info}"
     */
    public Long comentarFicha(String motivo, String titulo, String contenido, Long idFicha,String idioma) {
        Session session = getSession();
        try {
            ComentarioFicha comentarioFicha = new ComentarioFicha();
            comentarioFicha.setMotivo(motivo);
            comentarioFicha.setTitulo(titulo);
            comentarioFicha.setContenido(contenido);
            comentarioFicha.setFecha(new Date());
            Usuario usuario = getUsuario(session);
            comentarioFicha.setUsuario(usuario);
            comentarioFicha.setAutor(usuario.getNombre());
            Ficha ficha = (Ficha) session.load(Ficha.class, idFicha);
            comentarioFicha.setFicha(ficha);
            session.save(comentarioFicha);
            session.flush();
            return comentarioFicha.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     *  @deprecated
     * Reporta el error de un comentario
     * @ejb.interface-method
     * @ejb.permission role-name="${role.info}"
     */
    public void reportarErrorComentario(Long id,String motivo, String titulo, String contenido,String idioma,String tipo) {
        Session session = getSession();
        try {
        	Usuario informador = getUsuario(session);
        	if ("ficha".equals(tipo)) {
                //llamo al mail
                Ficha ficha = (Ficha) session.load(Ficha.class, id);
                if(ficha instanceof FichaRemota){
                    if("ERROR".equals(motivo)){
                    	log.debug("Se ha reportado un error en la ficha :"+ficha.getId());
                    	ReportarErrorComentario.reportarError(ficha,titulo,contenido,informador,idioma);
                    }
                }
                else{
                	log.warn("[EeportarErrorComentario] El comentario que intenta reportar el error no es de una ficha remota :"+ficha.getId());
                }
        	}
        	
        	 else if ("procedimiento".equals(tipo)) {
                 ProcedimientoLocal proc = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
                log.debug("*************************proc: "+proc.getResponsable());
                 if(proc instanceof ProcedimientoRemoto){
                     if("ERROR".equals(motivo)){
                     	log.debug("Se ha reportado un error en el procedimiento :"+proc.getId());
                     	ReportarErrorComentario.reportarError(proc,titulo,contenido,informador,idioma);
                     }
                 }
                 else{
                 	log.warn("[EeportarErrorComentario] El comentario que intenta reportar el error no es de un procedimiento remoto :"+proc.getId());
                 }
        	 }
           


        }	    
         catch (HibernateException he) {
	        throw new EJBException(he);
	    } finally {
	        close(session);
	    }

    }


    /**
     *  @deprecated
     * Crea un comentario de ficha.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.info}"
     */
    public Long comentarProcedimiento(String motivo, String titulo, String contenido, Long idProc,String idioma) {
        Session session = getSession();
        try {
            ComentarioProcedimiento comentarioProc = new ComentarioProcedimiento();
            comentarioProc.setMotivo(motivo);
            comentarioProc.setTitulo(titulo);
            comentarioProc.setContenido(contenido);
            comentarioProc.setFecha(new Date());
            Usuario usuario = getUsuario(session);
            comentarioProc.setUsuario(usuario);
            comentarioProc.setAutor(usuario.getNombre());
            ProcedimientoLocal proc = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, idProc);
            comentarioProc.setProcedimiento(proc);
            session.save(comentarioProc);
            session.flush();
            return comentarioProc.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     *  @deprecated
     * Obtiene un Comentario.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param id	Identificador del comentario a obtener.
     * 
     * @return Devuelve <code>Comentario</code> solicitado.
     */
    public Comentario obtenerComentario(Long id) {
    	
        Session session = getSession();
        
        try {
        	
            return (Comentario) session.get(Comentario.class, id);
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     *  @deprecated
     * Lista los comentarios de una ficha
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     * 
     * @param idFicha	Identificador de una ficha.
     * 
     * @return Devuelve <code>List</code> de comentarios de la ficha solicitada.
     */
    public List listarComentariosFicha(Long idFicha) {
    	
        Session session = getSession();
        
        try {
        	
            return session.createCriteria(ComentarioFicha.class).createAlias("ficha", "fic").add(Expression.eq("fic.id", idFicha)).addOrder(Order.desc("fecha")).list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
    }
    

    /**
     *  @deprecated
     * Lista los comentarios de una ficha por motivo
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public List listarComentariosFicha(Long idFicha, String motivo) {
        Session session = getSession();
        try {
            return session.createCriteria(ComentarioFicha.class)
                    .add(Expression.eq("motivo", motivo))
                    .createAlias("ficha", "fic")
                    .add(Expression.eq("fic.id", idFicha))
                    .addOrder(Order.asc("subsanado"))
                    .addOrder(Order.desc("fecha"))
                    .list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**PORMAD
     *  @deprecated
     * Lista los comentarios de una ficha excepto los que tengan como motivo el que le pasamos
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public List listarComentariosFichaExceptoMotivo(Long idFicha, String motivo) {
        Session session = getSession();
        try {
            return session.createCriteria(ComentarioFicha.class)
                    .add(Expression.not(Expression.eq("motivo", motivo)))
                    .createAlias("ficha", "fic")
                    .add(Expression.eq("fic.id", idFicha))
                    .list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     *  @deprecated
     * Lista los comentarios de un procedimiento
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     * 
     * @param idProcedimiento	Identificador del procedimiento.
     * 
     * @return Devuelve <code>List</code> de los comentarios del procedimiento solicitado.
     */
    public List listarComentariosProcedimiento(Long idProcedimiento) {
        Session session = getSession();
        
        try {
        	
            return session.createCriteria(ComentarioProcedimiento.class)
                    .createAlias("procedimiento", "proc")
                    .add(Expression.eq("proc.id", idProcedimiento))
                    .addOrder(Order.desc("fecha"))
                    .list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     *  @deprecated
     * Lista los comentarios de un procedimiento por motivo
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public List listarComentariosProcedimiento(Long idProc, String motivo) {
        Session session = getSession();
        try {
            return session.createCriteria(ComentarioProcedimiento.class)
                    .add(Expression.eq("motivo", motivo))
                    .createAlias("procedimiento", "proc")
                    .add(Expression.eq("proc.id", idProc))
                    .addOrder(Order.asc("subsanado"))
                    .addOrder(Order.desc("fecha"))
                    .list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**PORMAD
     *  @deprecated
     * Lista los comentarios de una ficha excepto los que tengan como motivo el que le pasamos
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public List listarComentariosProcedimientoExceptoMotivo(Long idProc, String motivo) {
        Session session = getSession();
        try {
            return session.createCriteria(ComentarioProcedimiento.class)
                    .add(Expression.not(Expression.eq("motivo", motivo)))
                    .createAlias("procedimiento", "proc")
                    .add(Expression.eq("proc.id", idProc))
                    .list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     *  @deprecated
     * Lista ultimos comentarios.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public List listarUltimosComentarios(int offset, int number) {
        Session session = getSession();
        try {
            return session.createCriteria(Comentario.class)
                    .addOrder(Order.desc("fecha"))
                    .setFirstResult(offset)
                    .setMaxResults(number)
                    .list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
     /**
      *  @deprecated
     * Lista ultimos comentarios de un motivo determinado.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public List listarUltimosComentarios(String motivo, int offset, int number) {
        Session session = getSession();
        try {
            return session.createCriteria(Comentario.class)
                    .add(Expression.eq("motivo", motivo))
                    .addOrder(Order.desc("fecha"))
                    .setFirstResult(offset)
                    .setMaxResults(number)
                    .list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     *  @deprecated
     * Obtener el n�mero de comentarios
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public Integer numeroComentarios() {
        Session session = getSession();
        try {
            Query query = session.createQuery("select count(c) from Comentario as c");
            return (Integer) query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     *  @deprecated
     * Obtener el n�mero de comentarios
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public Integer numeroComentarios(String motivo) {
        Session session = getSession();
        try {
            Query query = session.createQuery("select count(c) from Comentario as c where c.motivo = :motivo");
            query.setParameter("motivo", motivo);
            return (Integer) query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    

    /**
     *  @deprecated
     * Borrar un comentario.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     * 
     * @param id	Identificador del comentario a borrar.
     */
    public void borrarComentario(Long id) {
    	
        Session session = getSession();
        
        try {
        	
            if ( !getAccesoManager().tieneAccesoComentario(id) ) {
                throw new SecurityException(excepcionSeguridadComentario);
            }
            
            Comentario comentario = (Comentario) session.load( Comentario.class, id );
            session.delete(comentario);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     *  @deprecated
     * Marcar como subsanado un comentario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
     */
    public void subsanarComentario(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoComentario(id)) {
                throw new SecurityException("No tiene acceso al comentario");
            }
            Comentario comentario = (Comentario) session.load(Comentario.class, id);
            comentario.setSubsanado(true);
            session.saveOrUpdate(comentario);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
}