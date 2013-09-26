package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Suscriptor;

/**
 * @deprecated Utilizado únicamente desde el back antiguo
 * SessionBean para actualizar un objecto suscriptor.
 *
 * @ejb.bean
 *  name="sac/persistence/ActualizarSuscriptorFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ActualizarSuscriptorFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="RequiresNew"
 */
public abstract class ActualizarSuscriptorFacadeEJB extends PaginatedHibernateEJB {

	private static final long serialVersionUID = 1L;


	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
   
    
    /**
     *  @deprecated Utilizado únicamente desde el back antiguo
     * Actualiza un Suscriptor. 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param suscriptor	<code>Suscriptor</code> a actualizar.
     */
    public void actualizaSuscriptor(Suscriptor suscriptor) {
        Session session = getSession();
        
        try {
        	
            	session.saveOrUpdate(suscriptor);
                session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
}

