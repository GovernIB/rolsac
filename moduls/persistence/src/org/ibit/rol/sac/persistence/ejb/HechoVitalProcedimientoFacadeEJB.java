package org.ibit.rol.sac.persistence.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;

/**
 * SessionBean para mantener y consultar Hecho Vital Procedimiento.
 *
 * @ejb.bean
 *  name="sac/persistence/HechoVitalProcedimientoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HechoVitalProcedimientoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HechoVitalProcedimientoFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Crea o actualiza los HechoVitalProcedimiento de una coleccion.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param	hechoVitalProcedimiento	Indica el hechoVitalProcedimiento
     * 
     */
    public void grabarHechoVitalProcedimientos(Collection<HechoVitalProcedimiento> hechoVitalProcedimiento) {
    	
        Session session = getSession();
        
        try {
        	
        	for ( HechoVitalProcedimiento hvp : hechoVitalProcedimiento ) {
        		
	            if ( hvp.getId() == null ) {
	            	
	                HechoVital hecho = (HechoVital) session.load( HechoVital.class, hvp.getHechoVital().getId() );
	                ProcedimientoLocal proc = (ProcedimientoLocal) session.load( ProcedimientoLocal.class, hvp.getProcedimiento().getId() );
	                Hibernate.initialize( proc.getMaterias() );
	                Hibernate.initialize( proc.getHechosVitalesProcedimientos() );
	                hecho.addHechoVitalProcedimientoRespetandoOrden(hvp);
	                proc.addHechoVitalProcedimiento(hvp);  // siempre respeta orden

	            } else {
	            	
	                session.update(hvp);
	                
	            }
	            
        	}
        	
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    /**
     * Crea o actualiza los HechoVitalProcedimiento de una coleccion, borrando previamente los especificados, vía ID,
     * en la colección de elementos hvProcIds.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param hechoVitalProcedimiento Indica el hechoVitalProcedimiento.
     * @param hvProcIds Indica los IDs de los HechoVitalProcedimiento que se desean borrar previamente.
     * 
     */
    public void grabarHechoVitalProcedimientos(Collection<HechoVitalProcedimiento> hechoVitalProcedimiento, 
    		Collection<Long> hvProcIds) {
    	
        Session session = getSession();
        
        try {
        	
        	borrarHechoVitalProcedimientos(hvProcIds);
        	
        	for ( HechoVitalProcedimiento hvp : hechoVitalProcedimiento ) {
        		
	            if ( hvp.getId() == null ) {
	            	
	                HechoVital hecho = (HechoVital)session.load( HechoVital.class, hvp.getHechoVital().getId() );
	                ProcedimientoLocal proc = (ProcedimientoLocal)session.load( ProcedimientoLocal.class, hvp.getProcedimiento().getId() );
	                Hibernate.initialize( proc.getMaterias() );
	                Hibernate.initialize( proc.getHechosVitalesProcedimientos() );
	                hecho.addHechoVitalProcedimientoRespetandoOrden(hvp);
	                proc.addHechoVitalProcedimiento(hvp);  // siempre respeta orden

	            } else {
	            	
	                session.update(hvp);
	                
	            }
	            
        	}
        	
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    /**
     * Incrementa el orden de un hecho vital - procedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void subirOrden(Long id) {
        Session session = getSession();
        try {
            HechoVitalProcedimiento hechovp1 = (HechoVitalProcedimiento) session.load(HechoVitalProcedimiento.class, id);
            int orden = hechovp1.getOrden();
            if (orden > 0) {
                HechoVital hecho = hechovp1.getHechoVital();
                List listaHVP = hecho.getHechosVitalesProcedimientos();

                HechoVitalProcedimiento hechovp2 = (HechoVitalProcedimiento) listaHVP.get(orden - 1);

                hechovp2.setOrden(orden);
                listaHVP.set(orden, hechovp2);

                hechovp1.setOrden(orden - 1);
                listaHVP.set(orden - 1, hechovp1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Borra una coleccion de HechoVitalProcedimiento.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param	listaHechoVitalProcedimiento	Indica un hyechoVitalProcedimiento.
     */
    public void borrarHechoVitalProcedimientos(Collection<Long> listaHechoVitalProcedimiento) {
    	
        Session session = getSession();
        HechoVitalProcedimiento hechoVitalProcedimiento;
        
        try {
        	
        	for ( Long id : listaHechoVitalProcedimiento ) {
        		
	            hechoVitalProcedimiento = (HechoVitalProcedimiento) session.load( HechoVitalProcedimiento.class, id );
	            hechoVitalProcedimiento.getHechoVital().removeHechoVitalProcedimiento(hechoVitalProcedimiento);
	            
	            ProcedimientoLocal procedimiento = hechoVitalProcedimiento.getProcedimiento();
	            Hibernate.initialize( procedimiento.getMaterias() );
	            Hibernate.initialize( procedimiento.getHechosVitalesProcedimientos() );
	            procedimiento.removeHechoVitalProcedimiento( hechoVitalProcedimiento );
	            
	            session.delete(hechoVitalProcedimiento);

        	}
        	
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
}
