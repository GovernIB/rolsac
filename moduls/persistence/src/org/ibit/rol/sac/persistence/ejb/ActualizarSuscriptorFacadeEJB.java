package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.Suscriptor;


/**
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

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
   
    
    
    /**
     * Actualiza un Suscriptor. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void actualizaSuscriptor(Suscriptor sus) {
        Session session = getSession();
        try {
            	session.saveOrUpdate(sus);
                session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
}

