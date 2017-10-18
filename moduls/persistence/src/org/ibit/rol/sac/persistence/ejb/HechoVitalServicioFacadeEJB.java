package org.ibit.rol.sac.persistence.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalServicio;
import org.ibit.rol.sac.model.Servicio;

/**
 * SessionBean para mantener y consultar Hecho Vital Servicio.
 *
 * @ejb.bean
 *  name="sac/persistence/HechoVitalServicioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HechoVitalServicioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HechoVitalServicioFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Crea o actualiza los HechoVitalServicio de una coleccion.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param	hechoVitalServicio	Indica el hechoVitalServicio
     * 
     */
    public void grabarHechoVitalServicios(Collection<HechoVitalServicio> hechoVitalServicio) {
    	
        Session session = getSession();
        
        try {
        	
        	for ( HechoVitalServicio hvp : hechoVitalServicio ) {
        		
	            if ( hvp.getId() == null ) {
	            	
	                HechoVital hecho = (HechoVital) session.load( HechoVital.class, hvp.getHechoVital().getId() );
	                Servicio serv = (Servicio) session.load( Servicio.class, hvp.getServicio().getId() );
	                Hibernate.initialize( serv.getMaterias() );
	                Hibernate.initialize( serv.getHechosVitalesServicios() );
	                hecho.addHechoVitalServicioRespetandoOrden(hvp);
	                serv.addHechoVitalServicio(hvp);  // siempre respeta orden

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
     * Crea o actualiza los HechoVitalServicio de una coleccion, borrando previamente los especificados, vía ID,
     * en la colección de elementos hvProcIds.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param hechoVitalServicio Indica el hechoVitalServicio.
     * @param hvProcIds Indica los IDs de los HechoVitalServicio que se desean borrar previamente.
     * 
     */
    public void grabarHechoVitalServicios(Collection<HechoVitalServicio> hechoVitalServicio, 
    		Collection<Long> hvProcIds) {
    	
        Session session = getSession();
        
        try {
        	
        	borrarHechoVitalServicios(hvProcIds);
        	
        	for ( HechoVitalServicio hvp : hechoVitalServicio ) {
        		
	            if ( hvp.getId() == null ) {
	            	
	                HechoVital hecho = (HechoVital)session.load( HechoVital.class, hvp.getHechoVital().getId() );
	                Servicio proc = (Servicio)session.load( Servicio.class, hvp.getServicio().getId() );
	                Hibernate.initialize( proc.getMaterias() );
	                Hibernate.initialize( proc.getHechosVitalesServicios() );
	                hecho.addHechoVitalServicioRespetandoOrden(hvp);
	                proc.addHechoVitalServicio(hvp);  // siempre respeta orden

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
     * Incrementa el orden de un hecho vital - servicio.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void subirOrden(Long id) {
        Session session = getSession();
        try {
            HechoVitalServicio hechovp1 = (HechoVitalServicio) session.load(HechoVitalServicio.class, id);
            int orden = hechovp1.getOrden();
            if (orden > 0) {
                HechoVital hecho = hechovp1.getHechoVital();
                List listaHVP = hecho.getHechosVitalesServicios();

                HechoVitalServicio hechovp2 = (HechoVitalServicio) listaHVP.get(orden - 1);

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
     * Borra una coleccion de HechoVitalServicio.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param	listaHechoVitalServicio	Indica un hyechoVitalServicio.
     */
    public void borrarHechoVitalServicios(Collection<Long> listaHechoVitalServicio) {
    	
        Session session = getSession();
        HechoVitalServicio hechoVitalServicio;
        
        try {
        	
        	for ( Long id : listaHechoVitalServicio ) {
        		
	            hechoVitalServicio = (HechoVitalServicio) session.load( HechoVitalServicio.class, id );
	            hechoVitalServicio.getHechoVital().removeHechoVitalServicio(hechoVitalServicio);
	            
	            Servicio servicio = hechoVitalServicio.getServicio();
	            Hibernate.initialize( servicio.getMaterias() );
	            Hibernate.initialize( servicio.getHechosVitalesServicios() );
	            servicio.removeHechoVitalServicio( hechoVitalServicio );
	            
	            session.delete(hechoVitalServicio);

        	}
        	
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
}
