package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.GrupoSuscripcion;

/**
 * SessionBean para mantener y consultar envios de suscripcion.
 *
 * @ejb.bean
 *  name="sac/persistence/EnvioSuscripcionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EnvioSuscripcionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */


public abstract class EnvioSuscripcionFacadeEJB extends PaginatedHibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Inicializo los parámetros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init(Long id) {
    	super.tampagina=10;
    	super.pagina=0;
    	//super.select="";
    	super.select="";
    	super.from=" from EnvioSuscripcion envio ";
    	super.where=" where envio.tipoSuscripcion.id="+id.toString();
    	super.whereini=" ";
    	super.orderby="";

    	super.camposfiltro= new String[] {"envio.asunto", "envio.titulo"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }

    /**
     * Inicializo los parámetros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init() {
    	super.tampagina=10;
    	super.pagina=0;
    	//super.select="";
    	super.select="";
    	super.from=" from EnvioSuscripcion envio ";
    	super.where=" ";
    	super.whereini=" ";
    	super.orderby="";

    	super.camposfiltro= new String[] {"envio.asunto", "envio.titulo"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }    


    /**
     * Crea o actualiza un envio.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public Long grabarEnvio(EnvioSuscripcion envio) {
        Session session = getSession();
        try {
            session.saveOrUpdate(envio);
            session.flush();
            return envio.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Actualiza un envio como enviado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void actualizaComoEnviado(Long id) {
        Session session = getSession();
        try {
         	EnvioSuscripcion envio = (EnvioSuscripcion) session.load(EnvioSuscripcion.class, id);
         	envio.setFechaEnviado(new Date());
         	envio.setEstado(EnvioSuscripcion.EJECUTADO);
            session.saveOrUpdate(envio);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todos los Trabajos
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public List listarTrabajos() {
        Session session = getSession();
        try {
        	parametrosCons(); // Establecemos los parámetros de la paginación
           	
        	Query query = session.createQuery(select+from+where+orderby);
            query.setFirstResult(cursor-1);
            query.setMaxResults(tampagina);
        	return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Lista todos los envios.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEnvios() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(EnvioSuscripcion.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Lista todas los envios pendientes
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List listarPendientes() {
        Session session = getSession();
        try {
            Query query = session.createQuery("from EnvioSuscripcion as e where e.fechaEnvio < :fechaActual and e.estado = 'P'");
            query.setParameter("fechaActual", new Date());
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * añade todos los grupos al envio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void anyadirAllGrupos( List grupos, Long id) {
        Session session = getSession();
        try {
         	EnvioSuscripcion envio = (EnvioSuscripcion) session.load(EnvioSuscripcion.class, id);
        	
         	envio.getGrupos().clear();
         	
			for(Iterator it=grupos.iterator(); it.hasNext();)
			{	
        		GrupoSuscripcion grp = (GrupoSuscripcion) it.next();
        		envio.getGrupos().add(grp);
        	}
        	session.saveOrUpdate(envio);
            session.flush();
            
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }
    
    /**
     * elimina una grupo del envio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void eliminarGrupos(String[] grupos, Long id) {
        Session session = getSession();
        try {
         	EnvioSuscripcion envio = (EnvioSuscripcion) session.load(EnvioSuscripcion.class, id);
        	GrupoSuscripcion grp;
        	
        	for (int i=0;i<grupos.length;i++) {
        		grp = (GrupoSuscripcion) session.load(GrupoSuscripcion.class, new Long (grupos[i]));
            	envio.getGrupos().remove(grp);
        	}
        	session.saveOrUpdate(envio);
            session.flush();
            
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }


    /**
     * Comprueba que el elemento pertenece al TipoSuscripcion
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public boolean checkTipo(Long idTipo, Long id) {
        Session session = getSession();
        try {
        	Query query = session.createQuery("from EnvioSuscripcion envio where envio.tipoSuscripcion.id="+idTipo.toString()+" and envio.id="+id.toString());
        	return query.list().isEmpty();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    } 

    /**
     * Obtiene un envio. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EnvioSuscripcion obtenerEnvio(Long id) {
        Session session = getSession();
        try {
            EnvioSuscripcion envio = (EnvioSuscripcion) session.load(EnvioSuscripcion.class, id);
            Hibernate.initialize(envio.getGrupos());
            return envio;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * borra un Envio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarEnvio(Long id) {
        Session session = getSession();
        try {
        	Transaction tx = session.beginTransaction();
        	EnvioSuscripcion envio = (EnvioSuscripcion) session.load(EnvioSuscripcion.class, id);
            session.delete(envio);
            
            session.flush();
            
            tx.commit();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
}
