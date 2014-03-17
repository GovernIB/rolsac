package org.ibit.rol.sac.persistence.ejb;

import java.util.Comparator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

/**
 * SessionBean para mantener y consultar Enlaces.
 *
 * @ejb.bean
 *  name="sac/persistence/EnlaceFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EnlaceFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */

public abstract class EnlaceFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

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
     * Crea o actualiza un Enlace.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param enlace	Indica una entidad de tipo Enlace a guardar.
     * @param idProcedimiento	Identificador del procedimiento. "<code>Este parámetro no se usa</code>"
     * @param idFicha	Identificador de una ficha.
     * @return Devuelve el identificador del enlace guardado.
     */
    public Long grabarEnlace(Enlace enlace, Long idProcedimiento, Long idFicha) {
    	
    	Session session = getSession();
    	
    	try {
    		
    		Ficha ficha = null;
    		
    		if (enlace.getId() == null) {
    			
    			if (idFicha != null) {
    				
    				if (!getAccesoManager().tieneAccesoFicha(idFicha)) {
    					throw new SecurityException("No tiene acceso a la ficha.");
    				}
    				
    				ficha = (Ficha) session.load(Ficha.class, idFicha);
    				ficha.addEnlace(enlace);
    				
    			}
    			
    			session.save(enlace);
    			
    		} else {
    			
    			session.update(enlace);
    			
    		}
    		
    		session.flush();
    		
    		return enlace.getId();
    		
    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}
    	
    }
    
    /**
     * Borra un enlace.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param id	Identificador del enlace a borrar.
     */
    public void borrarEnlace(Long id)
    {
    	Session session = getSession();
    	try {
    		Enlace enlace = (Enlace) session.load(Enlace.class, id);
    		if (enlace.getFicha() != null) {
    			enlace.getFicha().removeEnlace(enlace);
    		}
    		session.delete(enlace);
    		session.flush();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    class EnlsFichaComparator implements Comparator
    {
    	public int compare(Object o1, Object o2) {
    		
    		Long x1 = new Long(((Enlace) o1).getOrden());
    		Long x2 = new Long(((Enlace) o2).getOrden());
    		
    		return x1.compareTo(x2);
    	}
    }
    
}
