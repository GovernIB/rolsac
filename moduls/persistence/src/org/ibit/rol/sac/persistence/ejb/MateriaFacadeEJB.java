package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoMateria;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;
import org.ibit.rol.sac.persistence.util.RemotoUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

/**
 * SessionBean para mantener y consultar materias.
 *
 * @ejb.bean
 *  name="sac/persistence/MateriaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.MateriaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class MateriaFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 3277261235228031289L;
	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza una materia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param materia	Indica la materia a guardar.
	 * 
	 * @return	Devuelve el identificador de la materia guardada.
	 */
	public Long grabarMateria(Materia materia) {

		Session session = getSession();
		try {

			session.saveOrUpdate(materia);
			session.flush();

			generarSolrPendiente(materia.getId());
			return materia.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Lista todas las materias del menú de administración (nuevo backoffice).
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	pagina	Indica la última página visitada.
	 * 
	 * @param	resultados	Indica el número de resultados por página.
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @return	Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todas las materias.
	 */    
	public ResultadoBusqueda listarMaterias(int pagina, int resultados, String idioma) {

		return listarTablaMaestraPaginada( pagina, resultados, listarTMMaterias(idioma) );

	}


	/**
	 * Lista todas las materias.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return	Devuelve un listado de todas las materias.
	 */        
	public List listarMaterias() {
		
		Session session = getSession();

		try {
			
			Criteria criteri = session.createCriteria(Materia.class);
			
			return criteri.addOrder( Order.asc("codigoEstandar") ).list();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}   
		
	}
	

	/**
	 * Listar las materias del menú de administración (nuevo backoffice)
	 * 
	 * @param	lang	Indica el idioam en que se realiza la búsqueda.
	 * 
	 * @return Devuelve un listado de materias.
	 */
	private List listarTMMaterias(String lang) {
		
		Session session = getSession();

		try {
			
			StringBuilder consulta = new StringBuilder("select mat.id, mat.codigoEstandar, trad.nombre ");
			consulta.append("from Materia as mat, mat.traducciones as trad ");
			consulta.append("where index(trad) = :idioma ");
			consulta.append("order by mat.codigoEstandar asc");
			
			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idioma", lang);
			
			return query.list();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}   
		
	}

	
	/**
	 *  @deprecated No se usa
	 * Lista todas las materias para el front.
	 * @ejb.interface-method
	 * @ejb.permission unchecked= "true"
	 */
	@Deprecated
	public List<Materia> listarMateriasFront() {
		Session session = getSession();
		try {
			Criteria criteri = session.createCriteria(Materia.class);
			return criteri.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene una materia. (metode que s'emplea per el backoffice i el frontoffice.
	 * Hem llevat els rols (PORMAD)
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la materia.
	 * 
	 * @return Devuelve la materia solicitada.
	 */
	public Materia obtenerMateria(Long id) {
		
		Session session = getSession();
		
		try {
			
			Materia materia = (Materia)session.load(Materia.class, id);
			Hibernate.initialize( materia.getFoto() );
			Hibernate.initialize( materia.getIcono() );
			Hibernate.initialize( materia.getIconoGrande() );
			Hibernate.initialize( materia.getProcedimientosLocales() );
			Hibernate.initialize( materia.getIconos() );
			
			Iterator iteradorIdiomas = materia.getLangs().iterator();
			while ( iteradorIdiomas.hasNext() ) {
				
				String lang = (String) iteradorIdiomas.next();
				TraduccionMateria traduccion = (TraduccionMateria) materia.getTraduccion(lang);
				
				if ( traduccion != null ) {
					
					Hibernate.initialize( traduccion.getDistribComp() );
					Hibernate.initialize( traduccion.getNormativa() );
					Hibernate.initialize( traduccion.getContenido() );
					
				}
				
			}
			
			return materia;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	/**
	 * Obtiene el icono de perfil de una materia. 
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idMateria	Identificador de la materia.
	 * 
	 * @return Devuelve una lista de iconos de materia.
	 */
	public List<IconoMateria> obtenerIconosPerfil(Long idMateria) {
		
		Session session = getSession();
		
		try {
			
			StringBuilder consulta = new StringBuilder(" select new IconoMateria(icono.id, iconos.nombre) from IconoMateria icono ");
			consulta.append(" inner join icono.icono as iconos ");
			consulta.append(" where icono.materia = :id ");
			consulta.append(" order by icono.icono.nombre desc ");
			
			Query query = session.createQuery(consulta.toString());
			query.setParameter("id", idMateria);
			
			return query.list();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	/**
	 * Borra una Materia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param	id	Identificador de la materia a borrar.
	 */
	public void borrarMateria(Long id) {
		
		Session session = getSession();
		try {
			
			Materia materia = (Materia) session.load(Materia.class, id);

			Set procedimientos = materia.getProcedimientosLocales();
			Set fichas = materia.getFichas();
			Set iconos = materia.getIconos();
			Iterator iter = iconos.iterator();
			while ( iter.hasNext() ) {
				
				IconoMateria icono = (IconoMateria) iter.next();
				PerfilCiudadano perfil = icono.getPerfil();
				perfil.removeIconoMateria(icono);
				
			}
			
			if (procedimientos.size() != 0 || fichas.size() != 0 )
				throw new EJBException("La materia contiene registros asociados");

			addOperacion( session, materia, Auditoria.BORRAR );
			Historico historico = getHistorico(session, materia);
			( (HistoricoMateria) historico ).setMateria(null);
			Actualizador.borrar(materia);

			generarSolrPendiente(materia.getId());
			
			session.delete(materia);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	/**
	 * Obtiene el archivo distribución competencial de una Materia.(PORMAD)
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador del archivo solicitado.
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param	useDefault	Indica si se utiliza el idioma por defecto.
	 * 
	 * @return Devuelve <code>Archivo</code> solicitado.
	 */
	public Archivo obtenerDistribComp(Long id, String idioma, boolean useDefault) {
		
		Session session = getSession();
		try {
			
			Materia materia = (Materia) session.load(Materia.class, id);
			TraduccionMateria tradMateria = (TraduccionMateria) materia.getTraduccion(idioma);
			if ( tradMateria == null || tradMateria.getDistribComp() == null ) {
				
				if ( useDefault ) {
					
					tradMateria = (TraduccionMateria) materia.getTraduccion();
					
				} else {
					
					return null;
					
				}
				
			}
			
			Hibernate.initialize( tradMateria.getDistribComp() );
			
			return tradMateria.getDistribComp();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene el archivo de normativa de una Materia.(PORMAD)
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador del archivo solicitado.
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param	useDefault	Indica si se utiliza el idioma por defecto.
	 * 
	 * @return Devuelve <code>Archivo</code> solicitado.
	 */
	public Archivo obtenerNormativa(Long id, String lang, boolean useDefault) {
		
		Session session = getSession();
		try {
			
			Materia materia = (Materia) session.load(Materia.class, id);
			TraduccionMateria tradMateria = (TraduccionMateria) materia.getTraduccion(lang);
			if ( tradMateria == null || tradMateria.getNormativa() == null ) {
				
				if ( useDefault ) {
					
					tradMateria = (TraduccionMateria) materia.getTraduccion();
					
				} else {
					
					return null;
					
				}
				
			}
			
			Hibernate.initialize( tradMateria.getNormativa() );
			
			return tradMateria.getNormativa();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene el archivo de contenido de una Materia. (PORMAD)
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id	Identificador del archivo solicitado.
	 * 
	 * @param	idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param	useDefault	Indica si se utiliza el idioma por defecto.
	 * 
	 * @return Devuelve <code>Archivo</code> con el contenido solicitado..
	 */
	public Archivo obtenerContenido(Long id, String lang, boolean useDefault) {
		
		Session session = getSession();
		try {
			
			Materia materia = (Materia) session.load(Materia.class, id);
			TraduccionMateria tradMateria = (TraduccionMateria) materia.getTraduccion(lang);
			if ( tradMateria == null || tradMateria.getContenido() == null ) {
				
				if ( useDefault ) {
					
					tradMateria = (TraduccionMateria) materia.getTraduccion();
					
				} else {
					
					return null;
					
				}
				
			}
			
			Hibernate.initialize( tradMateria.getContenido() );
			
			return tradMateria.getContenido();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene la foto de una Materia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la foto solicitada.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene la foto solicitada.
	 */
	public Archivo obtenerFoto(Long id) {
		
		Session session = getSession();
		try {
			
			Materia materia = (Materia) session.load(Materia.class, id);
			Hibernate.initialize( materia.getFoto() );
			
			return materia.getFoto();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene el icono de una Materia.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la foto solicitada.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene el icono solicitado.
	 * 
	 */
	public Archivo obtenerIcono(Long id) {
		
		Session session = getSession();
		try {
			
			Materia materia = (Materia) session.load(Materia.class, id);
			Hibernate.initialize( materia.getIcono() );
			
			return materia.getIcono();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 *  @deprecated No se usa 
	 * Obtiene el icono grande de una Materia
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public Archivo obtenerIconoGrande(Long id) {
		Session session = getSession();
		try {
			Materia materia = (Materia) session.load(Materia.class, id);
			Hibernate.initialize(materia.getIconoGrande());
			return materia.getIconoGrande();            
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * @deprecated No se usa 
	 * A partir de un Array de Strings con los codigos
	 * estandar de las materias recojo un {@link Set} de {@link Materia} con las materias
	 * contenidas cuyo codigo Estandar este en el Array de Strings.(PORMAD)
	 * 
	 * @param ceMaterias
	 * @return Un {@link Set} de {@link Materia}
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Deprecated
	public Set<Materia> obtenerMateriasCE(final String[] ceMaterias){
		Session session = getSession();
		try {
			Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, ceMaterias);
			return materias;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 *  @deprecated Usado desde el back antiguo
	 * A partir de un Strings con el codigo estandar de las materia recojo
	 * la {@link Materia} correspondiente (PORMAD)
	 * 
	 * @param codigosEstandarMateria
	 * @return {@link Materia}
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public Materia obtenerMateriaCE(final String codigosEstandarMateria){
		Session session = getSession();
		try {
			Query query = session.createQuery("from Materia materia where materia.codigoEstandar=:codigo");
			query.setString("codigo", codigosEstandarMateria);
			return (Materia)query.uniqueResult();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 *  @deprecated Usado desde el back antiguo
	 * Busca todos los {@link Materia} cuyo nombre contenga el String de entrada(PORMAD)
	 * 
	 * @param busqueda
	 * @param idioma
	 * @return lista de {@link Materia}
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Materia> buscar(final String busqueda, final String idioma){
		List<Materia> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
			try {
				Query query = session.createQuery("from Materia as mat, mat.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
				query.setString("idioma", idioma);
				query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
				resultado = query.list();
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}else{
			resultado = Collections.emptyList();
		}

		return resultado;
	}


	/**
	 * @deprecated Usado desde el back antiguo
	 * Obtiene el listado de fichas y procedimientos de la materia
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Deprecated
	public Materia obtenerMateriaFichasProced (Long id) {
		
        Session session = getSession();
        try {
    		Materia materia = (Materia) session.load(Materia.class, id);
    		Hibernate.initialize(materia.getFichas());
    		Hibernate.initialize(materia.getProcedimientosLocales());
            return materia;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
        
    }
	
	
	/**
	 * Obtener listado de materias según un listado de IDs
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<Materia> obtenerMateriasPorIDs(String ids, String idioma)
	{
		List<Materia> resultado;
		Session session = getSession();
		try {
			Query query = session.createQuery("from Materia as mat, mat.traducciones as trad where index(trad) = :idioma and mat.id in (" + ids + ") ");
        	query.setString("idioma", idioma);
        	resultado = query.list();
			return resultado;
		} catch (HibernateException he){
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Obtener listado de materias según un listado de IDs
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<Materia> obtenerMateriasPorIDs(List<Long> materias, String idioma) {
		
		List<Materia> resultado;
		Session session = getSession();
		
		try {
			
			Query query = session.createQuery("from Materia as mat, mat.traducciones as trad where index(trad) = :idioma and mat.id in (:materias) ");
        	query.setParameter("idioma", idioma);
        	query.setParameterList("materias", materias);
        	resultado = query.list();
        	
			return resultado;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Lista todas las {@link UnidadAdministrativa} relacionadas con la Materia, vía la tabla RSC_UNAMAT.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id 	Identificador de la Materia.
	 *  
	 * @return lista de {@link UnidadAdministrativa}
	 */
	public List<UnidadAdministrativa> listarUAsMateria(Long id) {

		List<UnidadAdministrativa> resultado;

		if ( id != null ) {

			Session session = getSession();

			try {

				Query query = session.createQuery(" SELECT unimat.unidad FROM UnidadMateria AS unimat WHERE unimat.materia.id = :id ");
				query.setLong("id", id);

				resultado = query.list();

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
	 * Método que busca todos los elementos que se relacionana con la materia y les marca la accion.
	 * @param idMateria
	 */
	private void generarSolrPendiente(final Long idMateria) {

		//Primero las fichas que se relacionan con el hechovital.
		Session session = getSession();
		//La acción es indexar (porque habrá que actualizar la información)
		final Long accion = 1l;
		try {
			SolrPendienteDelegate solrPendienteDelegate = DelegateUtil.getSolrPendienteDelegate();
			//Primero busca las fichas relacionadas.
			StringBuilder consulta = new StringBuilder("select fic.id from Materia materia left join materia.fichas fic where materia.id = "+idMateria);					
			Query query = session.createQuery( consulta.toString() );
			query.setCacheable(true);
			final List<Long> idFichas =  castList(Long.class,query.list());
			for(Long idFicha : idFichas) {
				solrPendienteDelegate.grabarSolrPendiente(EnumCategoria.ROLSAC_FICHA.toString(), idFicha, accion);
			}
			
			//Luego los procedimientos
			consulta = new StringBuilder("select proc.id from Materia materia left join materia.procedimientosLocales proc where materia.id = "+idMateria);					
			query = session.createQuery( consulta.toString() );
			query.setCacheable(true);			
			final List<Long> idProcedimientos =  castList(Long.class, query.list());
			for(Long idProcedimiento : idProcedimientos) {
				solrPendienteDelegate.grabarSolrPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO.toString(), idProcedimiento, accion);
			}
			
			//Luego los procedimientos
			consulta = new StringBuilder("select ua.id from UnidadAdministrativa ua left join ua.unidadesMaterias umat where umat.id = " + idMateria);
			query = session.createQuery( consulta.toString() );
			query.setCacheable(true);
			final List<Long> idUAs =  castList(Long.class, query.list());
			for(Long idUA : idUAs) {
				solrPendienteDelegate.grabarSolrPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA.toString(), idUA, accion);
			}
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} catch (DelegateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	
	 /**
	 * Consulta las Materias en funcion del filtro generico
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda consultaMaterias(FiltroGenerico filtro){
	
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		Long id = filtro.getId();
		String lang = filtro.getLang();
		Map <String,String> parametros = new HashMap<String,String>();
					
		String codigoAgrupacionMaterias = filtro.getValor(FiltroGenerico.FILTRO_MATERIAS_AGRUPACIONMATERIAS);
		String codigoUA = filtro.getValor(FiltroGenerico.FILTRO_MATERIAS_UA);
		String codigoFicha = filtro.getValor(FiltroGenerico.FILTRO_MATERIAS_FICHA);		
		
		
		StringBuilder select = new StringBuilder("SELECT m ");
		StringBuilder selectCount = new StringBuilder("SELECT count(m) ");
		StringBuilder from = new StringBuilder(" FROM Materia as m, m.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");		
				
		try {
			
			if(id!=null && id>0) {
				where.append(" AND m.id = :id");
				parametros.put("id", id.toString());					
			}
			
			Boolean hayWhere = false;
			
						
			if(!StringUtils.isEmpty(codigoAgrupacionMaterias)) {	
				from.append(", m.materiasAgrupacionM as mag");
				where.append(" AND mag.id = :codigoAgrupacionMaterias ");
				parametros.put("codigoAgrupacionMaterias", codigoAgrupacionMaterias);					
			}
			
			if(!StringUtils.isEmpty(codigoUA)) {
				from.append(", m.unidadesmaterias as um, um.unidad as ua");
				where.append(" AND ua.id = :codigoUA ");
				parametros.put("codigoUA", codigoUA);					
			}				
							
			if(!StringUtils.isEmpty(codigoFicha)) {
				from.append(", m.fichas as f");
				where.append(" AND f.id = :codigoFicha ");
				parametros.put("codigoFicha", codigoFicha);					
			}	
	
			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
			
	
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	
	}


	
}
