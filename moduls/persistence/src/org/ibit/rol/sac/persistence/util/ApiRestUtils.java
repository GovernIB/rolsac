package org.ibit.rol.sac.persistence.util;

import java.util.Map;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

public class ApiRestUtils {

	public ApiRestUtils() {
		super();
	}
	/**
	 * Permite ejecutar una consulta en sesion, rellenando los valores de resultadoBusqueda
	 * @param session
	 * @param pageSize indica el tamaño de la página
	 * @param pageNumber indica el numero de página
	 * @param select clausula select para obtener los resultados
	 * @param selectCount clausula select count(x) para obtener el número total de resultados
	 * @param from clausula from 
	 * @param where clausula where
	 * @param order clausula order (para select count se omite)
	 * @param parametros
	 * @return
	 * @throws HibernateException
	 */
	public static ResultadoBusqueda ejecutaConsultaGenerica(
			Session session,
			Integer pageSize,
			Integer pageNumber,
			String select,
			String selectCount,
			String from,
			String where,
			String order,
			Map <String,String> parametros) throws HibernateException{
		
		StringBuilder consulta = new StringBuilder("");
		StringBuilder consultaCount = new StringBuilder("");		
		
		//Creamos la consulta de listado
		consulta.append(select);			
		consulta.append(from);
		consulta.append(where);
		consulta.append(order);
		Query query = session.createQuery(consulta.toString());
		
		//Creamos la consulta para retornar el numero de elementos
		consultaCount.append(selectCount);
		consultaCount.append(from);
		consultaCount.append(where);
		Query queryCount = session.createQuery(consultaCount.toString());
		
		//Añadimos todos los parámetros.
		for (Map.Entry<String, String> param : parametros.entrySet()){
		    query.setParameter(param.getKey(), param.getValue());
		    queryCount.setParameter(param.getKey(), param.getValue());
		}				
		 query.setFirstResult((pageNumber - 1) * pageSize);
		 query.setMaxResults(pageSize);				
	
		 
		 ResultadoBusqueda res = new ResultadoBusqueda();
		 res.setListaResultados(query.list());
		 res.setTotalResultados((Integer) queryCount.uniqueResult());
		 
		return res;
		
	}

}
