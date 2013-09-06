package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.ibit.rol.sac.model.HistoricoEnvio;

/**
 * @deprecated Clase que únicamente se usa desde el back antiguo.
 * SessionBean para mantener y consultar envios de suscripcion.
 *
 * @ejb.bean
 *  name="sac/persistence/HistoricoEnvioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HistoricoEnvioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HistoricoEnvioFacadeEJB extends PaginatedHibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    
    /**
     * @deprecated No se usa 
     * Inicializo los par�metros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init(Long id) {
    	super.tampagina=10;
    	super.pagina=0;
    	//super.select="";
    	super.select="";
    	super.from=" from HistoricoEnvio historico ";
    	super.where=" where historico.tipoSuscripcion.id="+id.toString();
    	super.whereini=" ";
    	super.orderby="";

    	super.camposfiltro= new String[] {};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }

    
    /**
     * @deprecated No se usa 
     * Inicializo los par�metros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init() {
    	super.tampagina=10;
    	super.pagina=0;
    	//super.select="";
    	super.select="";
    	super.from=" from HistoricoEnvio historico ";
    	super.where=" ";
    	super.whereini=" ";
    	super.orderby="";

    	super.camposfiltro= new String[] {};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }    


    /**
     * @deprecated No se usa
     * Crea o actualiza un envio.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Long grabarHistoricoEnvio(HistoricoEnvio historico) {
        Session session = getSession();
        try {
            session.saveOrUpdate(historico);
            session.flush();
            return historico.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     *  @deprecated No se usa 
     * Lista todos los Envios
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public List listarHistoricos() {
        Session session = getSession();
        try {
        	parametrosCons(); // Establecemos los par�metros de la paginaci�n
           	
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
      * @deprecated No se usa
     * Lista todos los envios.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEnvios() {
        Session session = getSession();
        try {
    		Query query = session.createQuery( "FROM HistoricoEnvio he order by he.fechaEnvio desc");
    		return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * @deprecated No se usa
     * Obtiene un envio. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public HistoricoEnvio obtenerHistoricoEnvio(Long id) {
        Session session = getSession();
        try {
            HistoricoEnvio historico = (HistoricoEnvio) session.load(HistoricoEnvio.class, id);
            return historico;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * @deprecated Se usa desde el back antiguo
     * Obtiene un envio. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public HistoricoEnvio obtenerUltimoHistoricoEnvio() {
        Session session = getSession();
        try {
    		Query query = session.createQuery( "FROM HistoricoEnvio he  where he.fechaEnvio is not null order by he.fechaEnvio desc");
            query.setMaxResults(1);
    		List his = query.list();
    		if(his.size() != 0) return (HistoricoEnvio) his.get(0);
    		return null;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * @deprecated No se usa
     * borra un Envio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarHistoricoEnvio(Long id) {
        Session session = getSession();
        try {
        	Transaction tx = session.beginTransaction();
        	HistoricoEnvio historico = (HistoricoEnvio) session.load(HistoricoEnvio.class, id);
            session.delete(historico);
            
            session.flush();
            
            tx.commit();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
}
