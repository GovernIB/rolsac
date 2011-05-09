package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.ibit.rol.sac.model.SuscriptorClave;


/**
 * SessionBean para mantener y consultar envios de suscripcion.
 *
 * @ejb.bean
 *  name="sac/persistence/SuscriptorClaveFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SuscriptorClaveFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SuscriptorClaveFacadeEJB extends PaginatedHibernateEJB {

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
    	super.select="";
    	super.from=" from SuscriptorClave suscripC ";
    	super.where=" where suscripC.tipoSuscripcion.id="+id.toString();
    	super.whereini=" ";
    	super.orderby=" order by suscripC.email";

    	super.camposfiltro= new String[] {"suscripC.mail"};
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
    	super.from=" from SuscriptorClave suscripC ";
    	super.where=" ";
    	super.whereini=" ";
    	super.orderby=" order by suscripC.email";

    	super.camposfiltro= new String[] {"suscripC.mail"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }    

    /**
     * Crea o actualiza un envio.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Long grabarSuscriptorClave(SuscriptorClave suscriptor) {
        Session session = getSession();
        try {
            session.saveOrUpdate(suscriptor);
            session.flush();
            return suscriptor.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la clave del suscriptor. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Long recuperarSuscriptorClave( Long tipo, String email) {
        Session session = getSession();
        try {
        	Query query = session.createQuery("from SuscriptorClave s where s.tipoSuscripcion.id="+tipo.toString()+" and s.email='"+email+"'");
        	List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            SuscriptorClave suscriptorclave = (SuscriptorClave) result.get(0);
        	return suscriptorclave.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    
    }
    /**
     * Obtiene la clave del suscriptor. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean esSuscriptorCaib( String email, String clave) {
        Session session = getSession();
        try {
        	Query query = session.createQuery("from SuscriptorClave s where s.tipoSuscripcion.id=2 and s.activacion='"+clave+"' and s.email='"+email+"'");
        	List result = query.list();
            if (result.isEmpty()) {
                return false;
            }
            return true;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    
    }
    /**
     * Obtiene un suscriptor. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public SuscriptorClave obtenerSuscriptorClave(Long id) {
        Session session = getSession();
        try {
        	SuscriptorClave suscriptor = (SuscriptorClave) session.load(SuscriptorClave.class, id);
            return suscriptor;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * borra un Envio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.subsc}"
     */
    public void borrarSuscriptorClave(Long id) {
        Session session = getSession();
        try {
        	Transaction tx = session.beginTransaction();
        	SuscriptorClave sclave = (SuscriptorClave) session.load(SuscriptorClave.class, id);
            session.delete(sclave);
            
            session.flush();
            
            tx.commit();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
