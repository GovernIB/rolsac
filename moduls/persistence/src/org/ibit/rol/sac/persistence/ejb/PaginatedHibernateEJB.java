package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;

import org.ibit.rol.sac.model.criteria.PaginacionCriteria;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Bean con la funcionalidad b�sica para interactuar con HIBERNATE de forma paginada.
 *
 * @ejb.bean
 *  view-type="remote"
 *  generate="false"
 * @ejb.security-role-ref role-name="sacsystem" role-link="${role.system}"
 * @ejb.security-role-ref role-name="sacadmin" role-link="${role.admin}"
 * @ejb.security-role-ref role-name="sacsuper" role-link="${role.super}"
 * @ejb.security-role-ref role-name="sacoper" role-link="${role.oper}"
 */
public class PaginatedHibernateEJB {

	private static int RESULTATS_CERCA_TOTS = 99999;

	
	/**
	 * Método que devuelve un listado páginado.
	 * 
	 * @param query Indica una instancia de <code>Query</code> que representa la consulta de hibernate.
	 * @param criteria	Indica los criterios de paginación.
	 * @return Devuelve <code>ResultadoBusqueda</code> con los datos del listado.
	 */
	public static ResultadoBusqueda obtenerListadoPaginado(Query query, PaginacionCriteria criteria) throws HibernateException {

		ResultadoBusqueda resultado = new ResultadoBusqueda();
		int totalResultados = criteria.getPagRes();
		resultado.setTotalResultados(query.list().size());

		if ( totalResultados != RESULTATS_CERCA_TOTS ) {

			query.setFirstResult( criteria.getPagPag() * totalResultados );
			query.setMaxResults(totalResultados);

		}

		resultado.setListaResultados(query.list());

		return resultado;

	}

}