package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.axis.utils.StringUtils;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar Agrupaciones Hechos Vitales.(PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/AgrupacionHVFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.AgrupacionHVFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class AgrupacionHVFacadeEJB extends HibernateEJB {
	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}
	
	/**
	 * Crea o actualiza una Agrupacion Hecho Vital.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param agrHechoVital	Agrupación de hechos vitales a guardar.
	 * @return Identificador de la agrupación de hechos vitales.
	 */
	public Long guardarAgrupacionHV(AgrupacionHechoVital agrHechoVital) {
		
		Session session = getSession();
		
		try {
		
			session.saveOrUpdate(agrHechoVital);
			session.flush();
			
			return agrHechoVital.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	/**
	 * Crea o actualiza una Agrupacion Hecho Vital.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param agrHechoVital	Agrupación de hechos vitales a guardar.
	 * @param listaHechosVitalesAsignados	Listado de hechos vitales asignados a la agrupación de hchos vitales.
	 * @return Identificador de la agrupación de hechos vitales.
	 */
	public Long guardarAgrupacionHV(AgrupacionHechoVital agrHechoVital, List<HechoVitalAgrupacionHV> listaHechosVitalesAsignados) {
		
		Session session = getSession();
		
		try {
		
			for (HechoVitalAgrupacionHV hechoVitalAgrupacionHV : listaHechosVitalesAsignados) {
				if (hechoVitalAgrupacionHV != null) {
					session.delete(hechoVitalAgrupacionHV);
				}
			}
			
			List<HechoVitalAgrupacionHV> listaHechoVitalAgrupacionHV = agrHechoVital.getHechosVitalesAgrupacionHV();
			for (HechoVitalAgrupacionHV hechoVitalAgrupacionHV : listaHechoVitalAgrupacionHV) {
				session.saveOrUpdate(hechoVitalAgrupacionHV);
			}
		
			session.saveOrUpdate(agrHechoVital);
			session.flush();
			
			return agrHechoVital.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	/**
	 * Lista todas las Agrupaciones Hechos Vitales.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param pagina	Indica el número de página actual.
	 * @param resultats	Indica la cantidad de resultados a devolver por página.
	 * @param idioma	Indica el idioma.
	 * @return  Devuelve <code>ResultadoBusqueda</code> que contiene una lista de todas las agrupaciones de hechos vitales.
	 */
	public ResultadoBusqueda listarAgrupacionesHVHechosVitales(int pagina, int resultats, String idioma)
	{
		return listarTablaMaestraPaginada(pagina, resultats, listarTablaMaestraAgrupacionHechoVital(idioma));
	}
	
	
	/**
	 * Lista las agrupaciones hechos vitals para el menú de administración.
	 * 
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * @return Devuelve <code>List</code> de agrupaciones de hechos vitales filtrado por idioma y ordenado ascendentemente por código.
	 */
	private List listarTablaMaestraAgrupacionHechoVital(String idioma)
	{
		Session session = getSession();
		try {
			Query query = session.createQuery(
					"select ahv.id, " +
					"		ahv.codigoEstandar, " +
					"		trad.nombre " +
					"from AgrupacionHechoVital as ahv, " +
					"	  ahv.traducciones as trad " +
					"where index(trad) = :idioma " +
					"order by ahv.codigoEstandar asc");
			
			query.setParameter("idioma", idioma);
			return query.list();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene una Agrupacion hecho vital.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una agrupación de hechos vitales.
	 * @return Devuelve <code>AgrupacionHechoVital.</code> solicitada junto con su foto e iconos.
	 */
	public AgrupacionHechoVital obtenerAgrupacionHV(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionHechoVital agrHechoVital = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
			Hibernate.initialize(agrHechoVital.getHechosVitalesAgrupacionHV());
			Hibernate.initialize(agrHechoVital.getFoto());
			Hibernate.initialize(agrHechoVital.getIcono());
			Hibernate.initialize(agrHechoVital.getIconoGrande());
			
			return agrHechoVital;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Borra una Agrupacion de hechos vitales.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param id	Identificador de agrupación de hecho vital.
	 */
	public void borrarAgrupacionHV(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionHechoVital agrupacion = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
			List<HechoVitalAgrupacionHV> listaHechosAgr = agrupacion.getHechosVitalesAgrupacionHV();
			for (HechoVitalAgrupacionHV hechoAgr : listaHechosAgr) {
				HechoVital hecho = hechoAgr.getHechoVital();
				hecho.removeHechoVitalAgrupacionHV(hechoAgr);
			}
			
			PublicoObjetivo publicoObjetivo = agrupacion.getPublico();
			publicoObjetivo.removeAgrupacion(agrupacion);
			
			session.delete(agrupacion);
			session.flush();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene la Foto de una Agrupacion de hechos vitales.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una agrupacion de hechos vitales.
	 * @return Devuelve <code>Archivo</code> con la foto de una agrupación de hechos vitales.
	 */
	public Archivo obtenerFoto(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionHechoVital hecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
			Hibernate.initialize(hecho.getFoto());
			
			return hecho.getFoto();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene el icono de una Agrupacion de hechos vitales.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una agrupación de hechos vitales.
	 * @return Devuelve <code>Archivo</code> con el icono de una agrupación de hechos vitales.
	 */
	public Archivo obtenerIcono(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionHechoVital hecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
			Hibernate.initialize(hecho.getIcono());
			
			return hecho.getIcono();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene el icono grande de una Agrupacion de hechos vitales.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una agrupación de hechos vitales.
	 * @return Devuelve <code>Archivo</code> con el icono grande de una agrupación de hechos vitales.
	 */
	public Archivo obtenerIconoGrande(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionHechoVital hecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
			Hibernate.initialize(hecho.getIconoGrande());
			
			return hecho.getIconoGrande();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	 /**
		 * Consulta las Agrupaciones de hechos vitales en funcion del filtro generico
		 * 
		 * @ejb.interface-method
	     * @ejb.permission unchecked="true"
		 */
		public ResultadoBusqueda consultaAgrupacionsFetsVitals(FiltroGenerico filtro){
		
			Session session = getSession();	
			Integer pageSize = filtro.getPageSize();
			Integer pageNumber = filtro.getPage();
			String lang = filtro.getLang();
			Long id = filtro.getId();
			Map <String,String> parametros = new HashMap<String,String>();
			
			String publico = filtro.getValor(FiltroGenerico.FILTRO_AFV_PUBLICO);
			
			
			StringBuilder select = new StringBuilder("SELECT ahv ");
			StringBuilder selectCount = new StringBuilder("SELECT count(ahv) ");
			StringBuilder from = new StringBuilder(" FROM AgrupacionHechoVital as ahv, ahv.traducciones as trad ") ;
			StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
			parametros.put("lang",lang);
			StringBuilder order = new StringBuilder(" ORDER BY ahv.codigoEstandar ASC");			
					
			try {
					
				if(id!=null && id>0) {
					where.append(" AND ahv.id = :id");
					parametros.put("id", id.toString());					
				}
				
				if(!StringUtils.isEmpty(publico)) {
					where.append(" AND ahv.publico = :publico");
					parametros.put("publico", publico);
				}	
					 
				return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
		
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}

		}
	
	
	
}
