package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Archivo;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;




/**
 * SessionBean para obtener archivos.
 *
 * @ejb.bean
 *  name="sac/persistence/ArchivoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ArchivoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class ArchivoFacadeEJB extends HibernateEJB {

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    
    /**
     * obtiene el archivo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerArchivo(Long id) {
        Session session = getSession();
        try {
        	Archivo archi = new Archivo();
        	
        	Query query = session.createQuery("from Archivo archi where archi.id="+id.toString());
        	if (query.list().size()==1)	archi=(Archivo)query.list().get(0);
            Hibernate.initialize(archi);
        	
        	return archi;

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    

	
}
