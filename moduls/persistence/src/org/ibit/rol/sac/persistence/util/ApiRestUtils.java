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
			if("true".equals(param.getValue()) || "false".equals(param.getValue())){
				query.setParameter(param.getKey(), "true".equals(param.getValue()));
			    queryCount.setParameter(param.getKey(), "true".equals(param.getValue()));
			}else {
				query.setParameter(param.getKey(), param.getValue());
				queryCount.setParameter(param.getKey(), param.getValue());
			}
		    
		}				
		 query.setFirstResult((pageNumber - 1) * pageSize);
		 query.setMaxResults(pageSize);				
	
		 
		 ResultadoBusqueda res = new ResultadoBusqueda();
		 res.setListaResultados(query.list());
		 res.setTotalResultados((Integer) queryCount.uniqueResult());
		 
		return res;
		
	}
	
	/**
	 * NEW: PERMITE exactamente LO MISMO que ejecutaConsultaGenerica PERO USA UN map de STRING-OBJECT COMO OBJETO DE LOS PARÁMETROS PARA EVITAR PROBLEMAS EN POSTGRES
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
	public static ResultadoBusqueda ejecutaConsultaGenerica_new(
			Session session,
			Integer pageSize,
			Integer pageNumber,
			String select,
			String selectCount,
			String from,
			String where,
			String order,
			Map <String,Object> parametros) throws HibernateException{
		
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
		for (Map.Entry<String, Object> param : parametros.entrySet()){
			if("true".equals(param.getValue()) || "false".equals(param.getValue())){
				query.setParameter(param.getKey(), "true".equals(param.getValue()));
			    queryCount.setParameter(param.getKey(), "true".equals(param.getValue()));
			}else {
				query.setParameter(param.getKey(), param.getValue());
				queryCount.setParameter(param.getKey(), param.getValue());
			}
		    
		}				
		 query.setFirstResult((pageNumber - 1) * pageSize);
		 query.setMaxResults(pageSize);				
	
		 
		 ResultadoBusqueda res = new ResultadoBusqueda();
		 res.setListaResultados(query.list());
		 res.setTotalResultados((Integer) queryCount.uniqueResult());
		 
		return res;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * "true" si el num es >=1. "false" si num no es un numero o el num es <=0  
	 * @param num
	 * @return
	 */
	public static String intToBool(String num ) {		
		try {
			int n = Integer.parseInt(num);
			return intToBool(n);
		} catch (Exception e) {
			return "false";
		}		
	}
	
	/**
	 * "true" si el num es >=1. "false" en caso contrario
	 * @param num
	 * @return
	 */
	public static String intToBool(int num ) {		
		return num>=1?"true":"false";
	}
	
	

}
