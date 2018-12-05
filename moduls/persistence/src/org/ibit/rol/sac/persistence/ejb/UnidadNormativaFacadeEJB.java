package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar UnidadNormativa. (PORMAD)
 *
 * @ejb.bean
 * name="sac/persistence/UnidadNormativaFacade"
 * jndi-name="org.ibit.rol.sac.persistence.UnidadNormativaFacade"
 * type="Stateless"
 * view-type="remote"
 * transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UnidadNormativaFacadeEJB extends HibernateEJB
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3203877720788468776L;
	

	/**
	 * Obtiene referéncia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();
	
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
	@Override
	public void ejbCreate() throws CreateException
	{
        super.ejbCreate();
    }
	
	
	/**
     * Crea o actualiza una UnidadNormativa
     * @param unidadNormativa Indica la unidad Normativa a guardar
     * @param	idUnidad	Identificador de una unidad
     * @param	idNormativa	Identificador de una Normativa
     * @return Devuelve el identificador  de la unidad Normativa guardada
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public Long grabarUnidadNormativa(UnidadNormativa unidadNormativa, Long idUnidad, Long idNormativa) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUnidad);
        	Hibernate.initialize(unidad.getHijos());
        	
        	if (unidadNormativa.getId() == null) {
        		
        		Normativa normativa = (Normativa) session.load(Normativa.class, idNormativa);
        		unidad.addUnidadNormativa(unidadNormativa);
        		normativa.addUnidadNormativa(unidadNormativa);
        		
        	} else {
        		
        		session.update(unidadNormativa);
        		
        	}
        	
        	session.flush();
        	
        	Hibernate.initialize(unidad.getHijos());
        	Hibernate.initialize(unidad.getUnidadesMaterias());
            Actualizador.actualizar(unidad);
            
            return unidadNormativa.getId();
            
        } catch (HibernateException he) {
        	
        	throw new EJBException(he);
        	
        } finally {
        	
        	close(session);
        	
        }
		
	}
	
	/**
     * Crea o actualiza una UnidadNormativa
     * @param unidadNormativa Indica la unidad Normativa a guardar
     * @param	idUnidad	Identificador de una unidad
     * @param	idNormativa	Identificador de una Normativa
     * @return Devuelve el identificador  de la unidad Normativa guardada
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
	public void grabarUnidadesNormativa(List<UnidadNormativa> unidadesNormativaNuevas, List<Long> unidadesNormativaABorrar) {
		
		Session session = getSession();
		
		try {
			
			// Primero borramos las especificadas.
			for (Long id : unidadesNormativaABorrar) {
				
				UnidadNormativa unidadNormativa = (UnidadNormativa) session.load(UnidadNormativa.class, id);
				
				borrarUnidadNormativa(id);
			}
			
			// Creamos/actualizamos relaciones unidad-Normativa.
			for (UnidadNormativa um : unidadesNormativaNuevas) {
				
				if (um != null) {
					
					if (um.getId() == null) {
						
						UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, um.getUnidadAdministrativa().getId());
						unidad.addUnidadNormativa(um);
						
						Normativa normativa = (Normativa)session.load(Normativa.class, um.getNormativa().getId());
						normativa.addUnidadNormativa(um);
						
						session.flush();
						
						Hibernate.initialize(unidad.getHijos());
						Hibernate.initialize(unidad.getUnidadesMaterias());
						Actualizador.actualizar(unidad);
						
					} else {
						
						session.update(um);
						
					}
					
				}
				
			}
						                        
        } catch (HibernateException he) {
        	
        	throw new EJBException(he);
        	
        } finally {
        	
        	close(session);
        	
        }
		
	}
	
	/**
     * Borra una UnidadNormativa.
     * @param id	Identificador de la unidad Normativa a borrar.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
	public void borrarUnidadNormativa(Long id)
	{
		Session session = getSession();
		try {
			UnidadNormativa unidadNormativa = (UnidadNormativa) session.load(UnidadNormativa.class, id);
			final UnidadAdministrativa unidadA = unidadNormativa.getUnidadAdministrativa();
			if (!getAccesoManager().tieneAccesoUnidad(unidadA.getId(), true)) {
				throw new EJBException("No tiene acceso para modificar la relacion de la UA : " + unidadA.getId());
			}
			unidadNormativa.getNormativa().removeUnidadNormativa(unidadNormativa);
            unidadNormativa.getUnidadAdministrativa().removeUnidadNormativa(unidadNormativa);
            session.delete(unidadNormativa);
            session.flush();
            Hibernate.initialize(unidadA.getHijos());
            Hibernate.initialize(unidadA.getUnidadesMaterias());
            Actualizador.actualizar(unidadA);
            
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }
	}
	
}
