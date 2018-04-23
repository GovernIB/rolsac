package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar Tipo Afectacion.
 *
 * @ejb.bean
 *  name="sac/persistence/TipoAfectacionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TipoAfectacionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TipoAfectacionFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 5246339899571321776L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un tipo Afectacion.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param tipo	Indica el tipo de afectación a guardar.
	 * 
	 * @return	Devuelve el identificador del tipo de afectación a guardar.
	 */
	public Long grabarTipoAfectacion(TipoAfectacion tipo) {

		Session session = getSession();
		try {

			session.saveOrUpdate(tipo);
			session.flush();
			return tipo.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Lista todos los tipos afectaciones.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param	pagina	Indica la última página visitada.
	 * 
	 * @param	resultats	Indica el número de resultados por página.
	 * 
	 * @return	Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todos los tipos de afectaciones.
	 */
	public ResultadoBusqueda listarTiposAfectaciones(int pagina, int resultats) {

		return listarTablaMaestraPaginada( pagina, resultats, listarTiposAfectaciones() );

	}


	/**
	 * Lista todos los tipos afectaciones.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return	Devuelve <code>List<TipoAfectacion></code>.
	 */
	public List<TipoAfectacion> listarTiposAfectaciones() {

		Session session = getSession();
		try {

			Criteria criteri = session.createCriteria(TipoAfectacion.class);

			return criteri.list();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un tipo afectacion.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param	id	Identificador del tipo de afectación
	 * 
	 * @return Devuelve <code>TipoAfectacion</code> solicitado.
	 */
	public TipoAfectacion obtenerTipoAfectacion(Long id) {

		Session session = getSession();
		try {

			TipoAfectacion tipoAfec = (TipoAfectacion) session.load(TipoAfectacion.class, id);

			return tipoAfec;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Nos dice si un tipo Afectacion tiene afectaciones.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public boolean tieneAfectaciones(Long id){
		Session session = getSession();
		try {
			List result = session.find("select normativa from Normativa as normativa, afectacion in normativa.afectadas where afectacion.tipoAfectacion.id = ?", id, Hibernate.LONG);
			if(!result.isEmpty()){
				return true;
			} else {
				return false;
			}

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Borra un Tipo Afectacion.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param	id	Identificador del tipo de afectación a borrar.
	 */
	public void borrarTipoAfectacion(Long id) {
		
		Session session = getSession();
		try {
			
			TipoAfectacion tipoAfec = (TipoAfectacion) session.load(TipoAfectacion.class, id);
			
			if ( tieneAfectaciones(id) )
				throw new EJBException("El tipo tiene afectaciones asociadas");

			session.delete(tipoAfec);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	
	/**
	 *  Metodo para consultar los Tipos de afectacion. 
	 * @param filtro generico
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
	 * @return
	 */
    public ResultadoBusqueda consultaTipoAfectacion(FiltroGenerico filtro)  {
		
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		String lang = filtro.getLang();
		Long id = filtro.getId();
		Map <String,String> parametros = new HashMap<String,String>();
		
		
		StringBuilder select = new StringBuilder("SELECT t ");
		StringBuilder selectCount = new StringBuilder("SELECT count(t) ");
		StringBuilder from = new StringBuilder(" FROM TipoAfectacion as t, t.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");			
				
		try {
				
			if(id!=null && id>0) {
				where.append(" AND t.id = :id");
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
