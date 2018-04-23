package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

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
public abstract class UnidadMateriaFacadeEJB extends HibernateEJB
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3203877720788468776L;
	
	
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
     * Crea o actualiza una UnidadMateria
     * @param unidadMateria Indica la unidad materia a guardar
     * @param	idUnidad	Identificador de una unidad
     * @param	idMateria	Identificador de una materia
     * @return Devuelve el identificador  de la unidad materia guardada
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public Long grabarUnidadMateria(UnidadMateria unidadMateria, Long idUnidad, Long idMateria) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUnidad);
        	Hibernate.initialize(unidad.getHijos());
        	
        	if (unidadMateria.getId() == null) {
        		
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
     * Crea o actualiza una UnidadMateria
     * @param unidadMateria Indica la unidad materia a guardar
     * @param	idUnidad	Identificador de una unidad
     * @param	idMateria	Identificador de una materia
     * @return Devuelve el identificador  de la unidad materia guardada
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public void grabarUnidadesMateria(List<UnidadMateria> unidadesMateriaNuevas, List<Long> unidadesMateriaABorrar) {
		
		Session session = getSession();
		
		try {
			
			// Primero borramos las especificadas.
			for (Long id : unidadesMateriaABorrar)
				borrarUnidadMateria(id);
			
			// Creamos/actualizamos relaciones unidad-materia.
			for (UnidadMateria um : unidadesMateriaNuevas) {
				
				if (um != null) {
					
					if (um.getId() == null) {
						
						UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, um.getUnidad().getId());
						unidad.addUnidadMateria(um);
						
						Materia materia = (Materia)session.load(Materia.class, um.getMateria().getId());
						materia.addUnidadMateria(um);
						
						session.flush();
						
						Hibernate.initialize(unidad.getHijos());
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
     * Borra una UnidadMateria.
     * @param id	Identificador de la unidad materia a borrar.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public void borrarUnidadMateria(Long id)
	{
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
	
	
	/**
  	 * Consulta las unidades materia en funcion del filtro generico
  	 * 
  	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
  	 */
  	public ResultadoBusqueda consultaUnidadMateria(FiltroGenerico filtro){
  	
  		Session session = getSession();	
  		Integer pageSize = filtro.getPageSize();
  		Integer pageNumber = filtro.getPage();
  		String lang = filtro.getLang();
  		Long id = filtro.getId();
  		Map <String,String> parametros = new HashMap<String,String>();
  		
  		StringBuilder select = new StringBuilder("SELECT um ");
  		StringBuilder selectCount = new StringBuilder("SELECT count(um) ");
  		StringBuilder from = new StringBuilder(" FROM UnidadMateria as um , um.traducciones as trad ") ;
  		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
  		parametros.put("lang",lang);

  		StringBuilder order = new StringBuilder("");			
  				
  		try {
  				
  			if(id!=null && id>0) {
  				where.append(" AND um.id = :id");
  				parametros.put("id", id.toString());					
  			}
  				 
  			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
  	
  		} catch (HibernateException he) {
  			throw new EJBException(he);
  		} finally {
  			close(session);
  		}

  	}
}
