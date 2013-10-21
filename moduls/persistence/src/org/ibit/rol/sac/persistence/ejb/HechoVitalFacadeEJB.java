package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.FetchMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionHechoVital;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Hechos Vitales.
 *
 * @ejb.bean
 *  name="sac/persistence/HechoVitalFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HechoVitalFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HechoVitalFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 4424545544639940449L;


	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un Hecho Vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param	hechoVital	Indica el hecho vital a guardar.
	 * 
	 * @return Devuelve el identificador del hecho vital guardado.
	 */
	public Long grabarHechoVital(HechoVital hechoVital) {

		Session session = getSession();

		try {

			if ( hechoVital.getId() == null ) {

				Criteria criteria = session.createCriteria(HechoVital.class);
				List<HechoVital> result = castList( HechoVital.class, criteria.list() );

				if ( result.isEmpty() ) {

					hechoVital.setOrden(0);

				} else {

					hechoVital.setOrden( result.size() );

				}

			}

			session.saveOrUpdate(hechoVital);
			session.flush();

			return hechoVital.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Incrementa el orden de un hecho vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param	id	Identificador del hecho vital.
	 */
	@SuppressWarnings("unchecked")
	public void subirOrden(Long id) {

		Session session = getSession();

		try {

			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			int orden = hechoVital.getOrden();

			if ( orden > 0 ) {

				Criteria criteri = session.createCriteria(HechoVital.class);
				criteri.addOrder( Order.asc("orden") );
				List<HechoVital> result = criteri.list();

				HechoVital hechoVitalAux = (HechoVital) result.get( orden - 1 );

				hechoVitalAux.setOrden(orden);
				result.set( orden, hechoVitalAux );

				hechoVital.setOrden( orden - 1 );
				result.set( orden - 1, hechoVital );

			}

			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Lista todas los Hechos Vitales (nuevo backoffice, tablas maestras).
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"

	 * @param pagina	Indica la última página visitada.
	 * 
	 * @param resultados	Indica el número de registros por página.
	 * 
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @return	Devuelve <code>ResultadoBusqueda</code> que contiene eun listado de todos los hechos vitales. 
	 */
	public ResultadoBusqueda listarHechosVitales(int pagina, int resultados, String idioma) {

		return listarTablaMaestraPaginada( pagina, resultados, listarTMHechosVitales( idioma ) );

	}


	/**
	 * Lista todas los Hechos Vitales.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return	Devuelve <code>List<HechoVital></code>.
	 */
	public List<HechoVital> listarHechosVitales() {

		Session session = getSession();

		try {

			Criteria criteri = session.createCriteria(HechoVital.class);
			criteri.addOrder( Order.asc("orden") );
			criteri.setCacheable(true);

			return castList( HechoVital.class, criteri.list() );

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/** 
	 * Lista todos los hechos vitales.
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @return Devuelve <code>List<?></code> de todos los hechos vitales.
	 * */
	private List<?> listarTMHechosVitales(String idioma) {

		corregirOrdenacion();
		Session session = getSession();

		try {

			Query query = session.createQuery(
					"select hechoVital.id, " +
							"		hechoVital.orden, " +
							"		trad.nombre " +

    				"from HechoVital as hechoVital, " +
    				"	  hechoVital.traducciones as trad " +

    				"where index(trad) = :idioma " +

					"order by hechoVital.orden asc");

			query.setParameter("idioma", idioma);

			return query.list();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}    	

	}


	/**
	 * @deprecated No se usa
	 * Lista todas los Hechos Vitales y sus procedimientos.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<HechoVital> listarHechosVitalesProcedimientos() {
		Session session = getSession();
		try {
			Criteria criteri = session.createCriteria(HechoVital.class);
			criteri.addOrder(Order.asc("orden"));
			criteri.setCacheable(true);

			List<HechoVital> result = castList(HechoVital.class, criteri.list());
			for (int i = 0; i < result.size(); i++) {
				HechoVital hechoVital = (HechoVital) result.get(i);
				Hibernate.initialize(hechoVital.getHechosVitalesProcedimientos());
			}

			return result;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * @deprecated No se usa
	 * Listar Hechos Vitales AgrupacionHechoVital
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<HechoVital> listarHechosVitalesAgrupacionHV(Long agrupacion_id) {
		Session session = getSession();
		try {
			List<HechoVital> result = new ArrayList<HechoVital>();
			AgrupacionHechoVital agrupacion = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, agrupacion_id);
			for (HechoVitalAgrupacionHV temp : agrupacion.getHechosVitalesAgrupacionHV()) {
				HechoVital hechovital = temp.getHechoVital();
				result.add(hechovital);
			}
			return result;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Obtiene un hecho vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador de un hecho vital.
	 * 
	 * @return	Devuelve <code>HechoVital</code>.
	 */
	public HechoVital obtenerHechoVital(Long id) {

		Session session = getSession();

		try {

			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			Hibernate.initialize( hechoVital.getFoto() );
			Hibernate.initialize( hechoVital.getIcono() );
			Hibernate.initialize( hechoVital.getIconoGrande() );
			Hibernate.initialize( hechoVital.getHechosVitalesProcedimientos() );

			Iterator<String> iterator = hechoVital.getLangs().iterator();
			while ( iterator.hasNext() ) {

				String lang = (String) iterator.next();
				TraduccionHechoVital traduccion = (TraduccionHechoVital) hechoVital.getTraduccion(lang);
				if ( traduccion != null ) {

					Hibernate.initialize( traduccion.getDistribComp() );
					Hibernate.initialize( traduccion.getNormativa() );
					Hibernate.initialize( traduccion.getContenido() );

				}

			}

			return hechoVital;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un hecho vital según el nombre.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param nombre	Indica el nombre del hecho vital.
	 * 
	 * @return	Devuelve <code>HechoVital</code> solicitado.
	 */
	public HechoVital obtenerHechoVitalPorNombre(String nombre) {

		Session session = getSession();

		try {

			Query query = session.getNamedQuery("hechos.byname");
			query.setParameter( "nombre", nombre );
			query.setMaxResults(1);
			query.setCacheable(true);
			List<HechoVital> result = castList( HechoVital.class, query.list() );
			if ( result.isEmpty() )
				return null;

			HechoVital hechoVital = (HechoVital) result.get(0);
			Hibernate.initialize( hechoVital.getIcono() );
			Hibernate.initialize( hechoVital.getHechosVitalesProcedimientos() );

			return hechoVital;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene la foto de un hecho vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador del hecho vital.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene la foto del hecho vital.
	 */
	public Archivo obtenerFoto(Long id) {
		
		Session session = getSession();
		
		try {
			
			HechoVital hecho = (HechoVital) session.load( HechoVital.class, id );
			Hibernate.initialize( hecho.getFoto() );
			
			return hecho.getFoto();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Obtiene el icono de un hecho vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador del hecho vital.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene el icono del hecho vital.
	 */
	public Archivo obtenerIcono(Long id) {
		
		Session session = getSession();
		
		try {
			
			HechoVital hecho = (HechoVital) session.load( HechoVital.class, id );
			Hibernate.initialize( hecho.getIcono() );
			
			return hecho.getIcono();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene el icono grande de un hecho vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador del hecho vital.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene el icono grande del hecho vital. 
	 */
	public Archivo obtenerIconoGrande(Long id) {
		
		Session session = getSession();
		
		try {
			
			HechoVital hecho = (HechoVital) session.load( HechoVital.class, id );
			Hibernate.initialize( hecho.getIconoGrande() );
			
			return hecho.getIconoGrande();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Borra un Hecho Vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param	id	Identificador de un hecho vital.
	 */
	public void borrarHechoVital(Long id) {
		
		Session session = getSession();
		
		try {
			
			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			for ( Ficha ficha : castSet( Ficha.class, hechoVital.getFichas() ) )
				ficha.removeHechovital(hechoVital);

			List<HechoVitalProcedimiento> listadoHechoVitalProcedimiento = castList( HechoVitalProcedimiento.class, hechoVital.getHechosVitalesProcedimientos() );

			Iterator<HechoVitalProcedimiento> iteradorHechoVitalProcedimiento = listadoHechoVitalProcedimiento.iterator(); 
			while ( iteradorHechoVitalProcedimiento.hasNext() ) {
				
				HechoVitalProcedimiento hechoVitalProcedimiento = (HechoVitalProcedimiento) iteradorHechoVitalProcedimiento.next();
				ProcedimientoLocal proc = hechoVitalProcedimiento.getProcedimiento();
				proc.removeHechoVitalProcedimiento(hechoVitalProcedimiento);
				
			}

			Set<HechoVitalAgrupacionHV> listadoHechoVitalAgrHV = hechoVital.getHechosVitalesAgrupacionHV();
			Iterator<HechoVitalAgrupacionHV> iteradorHechoVitalAgrHV = listadoHechoVitalAgrHV.iterator();
			while ( iteradorHechoVitalAgrHV.hasNext() ) {
				
				HechoVitalAgrupacionHV hechova = (HechoVitalAgrupacionHV) iteradorHechoVitalAgrHV.next();
				AgrupacionHechoVital agru = hechova.getAgrupacion();
				agru.removeHechoVitalAgrupacionHV(hechova);
				
			}

			Criteria criteria = session.createCriteria(HechoVital.class);
			criteria.add( Expression.gt( "orden", new Integer( hechoVital.getOrden() ) ) );
			List<HechoVital> hechos = castList( HechoVital.class, criteria.list() );

			for ( int i = 0 ; i < hechos.size() ; i++ ) {
				
				HechoVital hec = (HechoVital) hechos.get(i);
				hec.setOrden(i);
				
			}

			session.delete(hechoVital);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene la distribucion competencial de un Hecho Vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador de un hecho vital.
	 * 
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param	useDefault	Indica si utiliza la traducción por defecto.
	 * 
	 * @return Devuelve <code>Archivo</code> que contiene la distribución competencial de un hecho vital.
	 */
	public Archivo obtenerDistribComp(Long id, String idioma, boolean useDefault) {
		
		Session session = getSession();
		
		try {

			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			TraduccionHechoVital traduccion = (TraduccionHechoVital) hechoVital.getTraduccion(idioma);
			if ( traduccion == null || traduccion.getDistribComp() == null ) {
				
				if (useDefault) {
					
					traduccion = (TraduccionHechoVital) hechoVital.getTraduccion();
					
				} else {
					
					return null;
					
				}
				
			}
			
			Hibernate.initialize( traduccion.getDistribComp() );
			
			return traduccion.getDistribComp();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Obtiene la normativa aplicada de un Hecho Vital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador de un hecho vital
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param	useDefault	Indica si utiliza la traducción por defecto.
	 * 
	 * @return Devuelve <code>Archivo</code> que contiene la normativa del hecho vital.
	 */
	public Archivo obtenerNormativa(Long id, String idioma, boolean useDefault) {
		
		Session session = getSession();
		
		try {
			
			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			TraduccionHechoVital traduccion = (TraduccionHechoVital) hechoVital.getTraduccion(idioma);
			if ( traduccion == null || traduccion.getNormativa() == null ) {
				
				if ( useDefault ) {
					
					traduccion = (TraduccionHechoVital) hechoVital.getTraduccion();
					
				} else {
					
					return null;
					
				}
				
			}
			
			Hibernate.initialize( traduccion.getNormativa() );
			
			return traduccion.getNormativa();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene el contenido de un HechoVital.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador de un hecho vital
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param	useDefault	Indica si utiliza la traducción por defecto.
	 * 
	 * @return Devuelve <code>Archivo</code> que contiene el contenido de un hecho vital.
	 */
	public Archivo obtenerContenido(Long id, String idioma, boolean useDefault) {

		Session session = getSession();
		
		try {
			
			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			TraduccionHechoVital traduccion = (TraduccionHechoVital) hechoVital.getTraduccion(idioma);
			if ( traduccion == null || traduccion.getContenido() == null ) {
				
				if (useDefault) {
					
					traduccion = (TraduccionHechoVital) hechoVital.getTraduccion();
					
				} else {
					
					return null;
					
				}
				
			}
			
			Hibernate.initialize( traduccion.getContenido() );
			
			return traduccion.getContenido();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * A partir de un Array de Strings con los codigos
	 * estandar de los HechosVitales recojo un {@link Set} de {@link HechoVital}
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param codEstandar	Código estándar de un hecho vital.
	 * 
	 * @return Un {@link List} de {@link HechoVital}
	 */
	public List<HechoVital> obtenerHechosVitalesCE(final String[] codEstandar) {
		
		final List<HechoVital> resultado = new ArrayList<HechoVital>();
		for ( String codigoEstandar : codEstandar ) {
			
			HechoVital hechoVital =  obtenerHechoVitalCE(codigoEstandar);
			if ( hechoVital != null )
				resultado.add(hechoVital);
			
		}
		
		return resultado;
		
	}
	

	/**
	 * A partir de un String con el código estandar del HechoVital recojo
	 * la {@link HechoVital} correspondiente
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param codEstandar	Código esándar de un hecho vital.
	 * 
	 * @return {@link HechoVital}
	 */
	public HechoVital obtenerHechoVitalCE(final String codEstandar) {
		
		Session session = getSession();
		
		try {
			
			Query query = session.createQuery("from HechoVital as hv where hv.codigoestandar = :codigo");
			query.setString( "codigo", codEstandar );
			
			return (HechoVital) query.uniqueResult();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Obtiene todos los grupos {@link HechoVitalAgrupacionHV} a los que pertenece un determinado hecho vital
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idHechoVital	Identificador de un hecho vital.
	 * 
	 * @return lista de {@link HechoVitalAgrupacionHV}
	 */
	public Set<HechoVitalAgrupacionHV> obtenerGruposHechoVital(Long idHechoVital) {
		
		Session session = getSession();
		
		try {
			
			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, idHechoVital );
			Hibernate.initialize( hechoVital.getHechosVitalesAgrupacionHV() );
			
			return hechoVital.getHechosVitalesAgrupacionHV();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Busca todos los {@link HechoVital} cuyo nombre contenga el String de entrada
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param nombre	Indica el nombre de un hecho vital.
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * @return lista de {@link HechoVital}
	 */
	public List<HechoVital> buscar(final String nombre, final String idioma)
	{
		List<HechoVital> resultado;
		if (nombre != null && !"".equals(nombre.trim())) {
			Session session = getSession();
			
			try {
				Query query = session.createQuery("from HechoVital as hev, hev.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
				query.setString("idioma", idioma);
				query.setString("busqueda", "%" + nombre.trim().toUpperCase() + "%");
				
				resultado = castList( HechoVital.class, query.list() );
				
			} catch (HibernateException he) {
				throw new EJBException(he);
				
			} finally {
				close(session);
			}
			
		} else {
			resultado = Collections.emptyList();
		}
		
		return resultado;
	}
	
	
	/**
     * Busca todos los {@link HechoVital} cuyo id se encuentre en la entrada
     * 
     * @param busqueda
     * @param idioma
     * @return lista de {@link HechoVital}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public List buscarPorIds(List<Long> ids)
	{
		List<HechoVital> resultado;
		if (ids == null || ids.size() == 0)
    		return Collections.EMPTY_LIST;
    	
    	Session session = getSession();
    	try {
    		Criteria criteria = session.createCriteria(HechoVital.class);
    		criteria.setFetchMode("traducciones", FetchMode.LAZY);
    		criteria.add(Expression.in("id", ids));
    		resultado = criteria.list();
    		for (HechoVital hv: resultado)
    			Hibernate.initialize(hv.getHechosVitalesProcedimientos());
    		
    		return resultado; 
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
	}
	
	
	/**
	 * Asigna a un hecho vital un nuevo orden y reordena los elementos afectados.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param	id	Identificador de un hecho vital.
	 * @param	ordenNuevo	Indica el nuevo orden del hecho vital.
	 * @param	ordenAnterior	Indica el antiguo orden del hecho vital.
	 */
	public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior)
	{
		Session session = getSession();
		
		try {
			Criteria criteria = session.createCriteria(HechoVital.class);
			criteria.addOrder(Order.asc("orden"));
			List<HechoVital> listaHechosVitales = castList(HechoVital.class, criteria.list());
			
			// Modificar sólo los elementos entre la posición del elemento que cambia 
			// de orden y su nueva posición
			int ordenMayor = (ordenNuevo > ordenAnterior ? ordenNuevo : ordenAnterior);
			int ordenMenor = (ordenMayor == ordenNuevo ? ordenAnterior : ordenNuevo);
			
			// Si el nuevo orden es mayor que el anterior, desplazar los elementos 
			// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
			int incremento = (ordenNuevo > ordenAnterior ? -1 : 1);
			for (HechoVital hechoVital : listaHechosVitales) {
				int orden = hechoVital.getOrden();
				if (orden >= ordenMenor && orden <= ordenMayor) {
					if (id.equals(hechoVital.getId()))
						hechoVital.setOrden(ordenNuevo);
					else
						hechoVital.setOrden(orden + incremento);
				}
				
				// Actualizar todo para asegurar que no hay duplicados ni huecos
				session.saveOrUpdate(hechoVital);
			}
			
			session.flush();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
			
		} finally {
			close(session);
		}
	}   
	

	/**
	 * Busca registros con el campo "orden" repetido y en caso afirmativo corrige
	 * toda la tabla ordenando los registros convenientemente. 
	 */
	private void corregirOrdenacion() {

		if ( !isOrdenesRepetidos() ) return;

		Session session = getSession();

		try {
			
			Criteria criteria = session.createCriteria(HechoVital.class);
			criteria.addOrder( Order.asc("orden") );
			List<HechoVital> listaHechosVitales = castList( HechoVital.class, criteria.list() );

			// Asegurar que la secuencia de orden incrementa de uno en uno        	
			for ( int i = 0 ; i < listaHechosVitales.size() ; i++ ) {
				
				HechoVital hechoVital = listaHechosVitales.get(i);
				hechoVital.setOrden(i);
				session.saveOrUpdate(hechoVital);
				
			}

			session.flush();

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Comprueba si hay registros con el campo "orden" repetido
	 * 
	 * @return Devuelve <code>true</code> si existen registros con el campo orden repetido.
	 */
	private boolean isOrdenesRepetidos() {
		
		Session session = getSession();

		try {
			
			Query query = session.createQuery(
					"select hechoVital.orden " +
			
					"from HechoVital as hechoVital " +
							
					"group by hechoVital.orden " +
					
					"having count(hechoVital.orden) > 1");
			
			return !query.list().isEmpty();
			
		} catch (HibernateException e) {
			
			throw new EJBException(e);
			
		} finally {
			
			close(session);
			
		}   
		
	}    


	/**
	 * Lista los hechos vitales según los públicos objetivos asignados.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param Set<?> publicosObjetivo	Listado de públicos objetivos.
	 * 
	 * @param String idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @return Lista de todos los Hechos Vitales que puede tener un procedimiento restringido por sus públicos objetivos asignados
	 */
	public List<HechoVital> listarHechosVitales(Set<?> publicosObjetivo, String idioma) {

		Session session = getSession();

		try {

			Query query = session.createQuery(
					"select hechoVital.id, trad.nombre, hechoVital.orden, po.id " +

    				"from HechoVital hechoVital, " +
    				"	hechoVital.traducciones as trad, " +
    				"	AgrupacionHechoVital as agrHechoVital, " +
    				"	HechoVitalAgrupacionHV as hva, " +
    				"	PublicoObjetivo as po  " +

    				"where ( index(trad) = :idioma ) " +
    				"	and ( hva.agrupacion.id = agrHechoVital.id ) " +
    				"	and ( hva.hechoVital.id = hechoVital.id ) and ( agrHechoVital.publico.id = po.id ) " +
    				"	and ( po.id in (:publicosObjetivo) ) " +

					"order by hechoVital.orden asc ");

			query.setParameter( "idioma", idioma );
			query.setParameterList( "publicosObjetivo", publicosObjetivo );

			List<Object[]> l = query.list();
			List<HechoVital> listaHechoVital = new Vector<HechoVital>();

			for ( Object[] o : l ) {

				HechoVital hechoVital = new HechoVital();
				hechoVital.setId( (Long) o[0] );

				TraduccionHechoVital traduccion = new TraduccionHechoVital(); 
				traduccion.setNombre( (String) o[1] );
				hechoVital.setTraduccion( idioma , traduccion );

				listaHechoVital.add(hechoVital);

			}

			return listaHechoVital;			

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

}
