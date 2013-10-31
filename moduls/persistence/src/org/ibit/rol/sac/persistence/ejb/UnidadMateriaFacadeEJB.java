package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.persistence.ws.Actualizador;

/**
 * SessionBean para mantener y consultar UnidadMateria. (PORMAD)
 *
 * @ejb.bean
 * name="sac/persistence/UnidadMateriaFacade"
 * jndi-name="org.ibit.rol.sac.persistence.UnidadMateriaFacade"
 * type="Stateless"
 * view-type="remote"
 * transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UnidadMateriaFacadeEJB extends HibernateEJB {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3203877720788468776L;

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    /**
     * Crea o actualiza una UnidadMateria
     * 
     * @param unidadMateria Indica la unidad materia a guardar
     * 
     * @param	idUnidad	Identificador de una unidad
     * 
     * @param	idMateria	Identificador de una materia
     * 
     * @return Devuelve el identificador  de la unidad materia guardada
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarUnidadMateria(UnidadMateria unidadMateria, Long idUnidad, Long idMateria) {
    	
        Session session = getSession();
        
        try {
        	
        	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUnidad);
        	Hibernate.initialize(unidad.getHijos());
        	
            if ( unidadMateria.getId() == null ) {
            	
                Materia materia = (Materia) session.load(Materia.class, idMateria);
                unidad.addUnidadMateria(unidadMateria);
                materia.addUnidadMateria(unidadMateria);
                
            } else {
            	
                session.update(unidadMateria);
                
            }

            session.flush();
            
            Hibernate.initialize(unidad.getHijos());
            Actualizador.actualizar(unidad);
            
            return unidadMateria.getId();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
    * Obtiene una UnidadMateria.
    * 
    * @param id	Identificador de la unidad materia solicitada.
    * 
    * @return	Devuelve <code>UnidadMateria</code> solicitada.
    * 
    * @ejb.interface-method
    * 
    * @ejb.permission unchecked="true"
    */
   public UnidadMateria obtenerUnidadMateria(Long id) {
	   
       Session session = getSession();
       
       try {
    	   
           return (UnidadMateria) session.load(UnidadMateria.class, id);
           
       } catch (HibernateException he) {
    	   
           throw new EJBException(he);
           
       } finally {
    	   
           close(session);
           
       }
       
   }
   

   /**
     * Borra una UnidadMateria.
     * 
     * @param id	Identificador de la unidad materia a borrar.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarUnidadMateria(Long id) {
    	
        Session session = getSession();
        
        try {
        	
            UnidadMateria unidadMateria = (UnidadMateria) session.load(UnidadMateria.class, id);
            
            final UnidadAdministrativa unidadA = unidadMateria.getUnidad();
            
            unidadMateria.getMateria().removeUnidadMateria(unidadMateria);
            unidadMateria.getUnidad().removeUnidadMateria(unidadMateria);
            session.delete(unidadMateria);
            session.flush();
            
            Hibernate.initialize(unidadA.getHijos());
            Actualizador.actualizar(unidadA);
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

}
