package org.ibit.rol.sac.persistence.ejb;

import java.util.Iterator;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionEnlace;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.*;

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
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param enlace	Indica una entidad de tipo Enlace a guardar.
     * 
     * @param idProcedimiento	Identificador del procedimiento. "<code>Este parámetro no se usa</code>"
     * 
     * @param idFicha	Identificador de una ficha.
     * 
     * @return Devuelve el identificador del enlace guardado.
     */
    public Long grabarEnlace(Enlace enlace, Long idProcedimiento, Long idFicha) {
    	
        Session session = getSession();
        
        try {
        	
        	Ficha ficha = null;
            if ( enlace.getId() == null ) {

                if ( idFicha != null ) {
                	
                    if ( !getAccesoManager().tieneAccesoFicha(idFicha) ) {
                    	
                        throw new SecurityException("No tiene acceso a la ficha.");
                        
                    }
                    
                    ficha = (Ficha) session.load( Ficha.class, idFicha );
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
     * @deprecated Se usa desde back antiguo 
     * Obtiene un Enlace.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public Enlace obtenerEnlace(Long id) {
        Session session = getSession();
        try {
        	Enlace enl =  (Enlace) session.load(Enlace.class, id);
            for (Iterator iterator = enl.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionEnlace traduccion = (TraduccionEnlace) enl.getTraduccion(lang);
            }
            return enl;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Borra un enlace.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param id	Identificador del enlace a borrar.
     */
    public void borrarEnlace(Long id) {
    	
        Session session = getSession();
        
        try {
        	
        	Enlace enlace = (Enlace) session.load( Enlace.class, id );
            if ( enlace.getFicha() != null )            	
            	enlace.getFicha().removeEnlace(enlace);
            	
            session.delete(enlace);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);     
            
        } finally {
        	
            close(session);
            
        }
        
    }


    /**
     * @deprecated Se usa desde back antiguo 
     * Actualiza los ordenes de los enlaces de una Ficha
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenEnlaces(Map map) {
  
    	
    	Session session = getSession();
        try {
        	Long id;
        	int valor_orden=0;
        	List enl_orden = new ArrayList();
        	
        	Iterator it = map.entrySet().iterator();
        	while (it.hasNext()) {
        		Map.Entry e = (Map.Entry)it.next();
        	
            	String paramName = e.getKey().toString();
            	if (paramName.startsWith("orden_enl")) {
            		id=  Long.valueOf(paramName.substring(9)).longValue();
             		String[] parametros=(String[])e.getValue();
            		valor_orden= Integer.parseInt(parametros[0]);            		
            		
            		Enlace enlace = (Enlace) session.load(Enlace.class, id);
            		enlace.setOrden(valor_orden);
            		enl_orden.add(enlace);
            	}
            }
            session.flush();
            
            Collections.sort( enl_orden, new EnlsFichaComparator() );
            
            Long contador= Long.parseLong("1");
        	
        	Iterator itenl = enl_orden.iterator();
        	Enlace enl=null;
    		while (itenl.hasNext()) {
    			enl=(Enlace)itenl.next();
    			enl.setOrden(contador);
    			contador+=1;
    		}
            session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
            
    }
	
    class EnlsFichaComparator implements Comparator {
    	
		public int compare(Object o1, Object o2) { 
			
			Long x1 = new Long ( ( (Enlace) o1 ).getOrden() );
			Long x2 = new Long ( ( (Enlace) o2 ).getOrden() );
			
			return x1.compareTo( x2 );
			
		}
		
	}
    
}
