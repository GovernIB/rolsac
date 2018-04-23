package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

/**
 * SessionBean para mantener y consultar Materia AgrupacionMateria. 
 *
 * @ejb.bean
 *  name="sac/persistence/MateriaAgrupacionMFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.MateriaAgrupacionMFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class MateriaAgrupacionMFacadeEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Lista todas las materias agrupadas.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param pagina	Número de página actual.
	 * @param resultats	Número de resultados por página.
	 * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado con todas las materias agrupadas.
	 */
	public ResultadoBusqueda listarAgrupacionMaterias(int pagina, int resultats) {
		return listarTablaMaestraPaginada( pagina, resultats, listarAgrupacionMaterias() );   
	}


	/**
	 * Lista todas las materias agrupadas.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @return Devuelve una lista con todas las materias agrupadas.
	 */
	public List listarAgrupacionMaterias() {

		Session session = getSession();

		try {

			Criteria criteri = session.createCriteria(AgrupacionMateria.class);

			return criteri.addOrder(Order.asc("codigoEstandar")).list();

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Lista todas las materias relacionadas de una agrupacionMateria.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @return Devuelve un iterador con todas las materias asignadas a una agrupaci�n de materias.
	 */
	public List<Materia> obtenerMateriasRelacionadas(Long idAgrupacionMateria, String idioma) {

		List<Materia> materias = Collections.emptyList();
		Session session = getSession();

		try {
			
			StringBuilder consulta = new StringBuilder(" select new Materia(materia.id, trad.nombre, mam.orden) from Materia materia, materia.traducciones as trad ");
			consulta.append(" inner join fetch materia.materiasAgrupacionM as mam ");
			consulta.append(" inner join mam.agrupacion as agrupacion ");
			consulta.append(" where index(trad) = :idioma ");
			consulta.append(" and agrupacion = :idAgrupacionMateria ");
			consulta.append(" order by mam.orden asc ");
			
			Query query = session.createQuery(consulta.toString());
			query.setParameter("idAgrupacionMateria", idAgrupacionMateria);
			query.setParameter("idioma", idioma);
			
			materias = query.list();

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}

		return materias;

	}
	
	
	  /**
	 * Consulta las materia agrupacion en funcion del filtro generico
	 * 
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda consultaMateriaAgrupacion(FiltroGenerico filtro){
	
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		String lang = filtro.getLang();
		Long id = filtro.getId();
		Map <String,String> parametros = new HashMap<String,String>();
		
		StringBuilder select = new StringBuilder("SELECT am ");
		StringBuilder selectCount = new StringBuilder("SELECT count(am) ");
		StringBuilder from = new StringBuilder(" FROM MateriaAgrupacionM as am ") ;
		StringBuilder where =new StringBuilder("");
		//parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");			
				
		try {
				
			if(id!=null && id>0) {
				where.append(" WHERE am.id = :id");
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
