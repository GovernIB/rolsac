package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.ActivacionSuscripcion;

/**
 * SessionBean para consultar claves de activacion de suscripcion.
 *
 * @ejb.bean
 *  name="sac/persistence/ActivacionSuscripcionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ActivacionSuscripcionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class ActivacionSuscripcionFacadeEJB extends PaginatedHibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    


    /**
     * Obtiene Clave de Activacion
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public ActivacionSuscripcion obtener(String email, String idImagen) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from ActivacionSuscripcion as a where a.email = :email and a.idImagen = :idImagen");
            query.setParameter("email", email);
            query.setParameter("idImagen", idImagen);
            return (ActivacionSuscripcion) query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
}
