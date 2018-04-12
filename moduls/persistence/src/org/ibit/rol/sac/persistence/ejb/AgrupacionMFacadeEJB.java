package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar Agrupaciones Materia.
 *
 * @ejb.bean
 *  name="sac/persistence/AgrupacionMFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.AgrupacionMFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class AgrupacionMFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

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
	 * Obtiene una Agrupacion materia.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de la agrupación de materias a obtener.
	 * @return Devuelve una instancia de tipo <code>AgrupacionMateria</code>.	
	 */
	public AgrupacionMateria obtenerAgrupacionMaterias(Long id) {
		
		
		Session session = getSession();		
		
		try {
			
			AgrupacionMateria agrMateria = (AgrupacionMateria) session.load(AgrupacionMateria.class , id);
			session.refresh(agrMateria);
			Hibernate.initialize(agrMateria.getMateriasAgrupacionM());
			
			return agrMateria;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	

	
	/**
	 * Borra una Agrupacion de materias.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param id	Identificador de la agrupación de materias.
	 */
	public void borrarAgrupacionM(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionMateria agrupacion = (AgrupacionMateria) session.load(AgrupacionMateria.class, id);
			if (agrupacion.getMateriasAgrupacionM() != null) {
				for (MateriaAgrupacionM materiaAgrupacionM : agrupacion.getMateriasAgrupacionM()) {
					if (materiaAgrupacionM != null) {
						session.delete(materiaAgrupacionM);
					}
				}
			}
			
			session.delete(agrupacion);
			session.flush();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza una Agrupacion de materias.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param agrMateria Entidad de tipo AgrupacionMateria a almnacenar
	 * @param listaMateriasAsignadas Listado de materias asignadas que se van a borrar. Se actualizarán las materias
	 * relacionadas con las presentes en agrMateria.getMateriasAgrupacionM().
	 * 
	 * @return Devuelve el identificador de la agrupación de materias guardada.
	 */
	public Long guardarAgrupacionMaterias(AgrupacionMateria agrMateria, List<MateriaAgrupacionM> listaMateriasAsignadas) {
		
		Session session = getSession();
		
		try {
			
			// Borramos las anteriores.
			if (listaMateriasAsignadas != null) {
				for (MateriaAgrupacionM materiaAgrupacionMaterias : listaMateriasAsignadas) {
					if (materiaAgrupacionMaterias != null) {
						session.delete(materiaAgrupacionMaterias);
					}
				}
			}
			
			// Guardamos las nuevas.
			List<MateriaAgrupacionM> listaMateriaAgrupacionMaterias = agrMateria.getMateriasAgrupacionM();
			for (MateriaAgrupacionM materiaAgrupacionMaterias : listaMateriaAgrupacionMaterias) {
				session.saveOrUpdate(materiaAgrupacionMaterias);
			}
			
			session.saveOrUpdate(agrMateria);
			session.flush();
			
			return agrMateria.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	/**
	 * Retorna la lista de materias relacionadas con una agrupación de materias.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id Clave primaria de la agrupación de materias de la cual queremos obtener sus materias relacionadas.
	 * 
	 * @return Devuelve la lista de materias relacionadas con la agrupación de materias especificada por parámetro.
	 */
	public List<MateriaAgrupacionM> obtenerMateriasAgrupacion(Long id) {
		
		List<MateriaAgrupacionM> resultado = new ArrayList<MateriaAgrupacionM>();
		
		Session session = getSession();

    	try {
    		
    		StringBuilder consulta = new StringBuilder(" select m from MateriaAgrupacionM as m ");
    		consulta.append(" where m.agrupacion.id = :idAgrupacion ");
    		consulta.append(" order by m.orden asc ");

    		Query query = session.createQuery( consulta.toString() );
    		query.setParameter("idAgrupacion", id, Hibernate.LONG);

    		resultado = query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}
		
		return resultado;
		
	}
	
	 /**
		 * Consulta las Agrupaciones de Materias en funcion del filtro generico
		 * 
		 * @ejb.interface-method
	     * @ejb.permission unchecked="true"	 
	 */
	public ResultadoBusqueda consultaAgrupacionMaterias(FiltroGenerico filtro) {
				
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		String lang = filtro.getLang();
		Long id = filtro.getId();
		Map <String,String> parametros = new HashMap<String,String>();
		
		
		
		StringBuilder select = new StringBuilder("SELECT am ");
		StringBuilder selectCount = new StringBuilder("SELECT count(am) ");
		StringBuilder from = new StringBuilder(" FROM AgrupacionMateria as am , am.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");			
				
		try {
				
			if(id!=null && id>0) {
				where.append(" AND am.id = :id");
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
