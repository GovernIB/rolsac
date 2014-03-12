package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoMateria;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.util.RemotoUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;


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
			
			return (List<IconoMateria>)query.list();
			
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
	@SuppressWarnings("unchecked")
	public List<Materia> buscar(final String busqueda, final String idioma){
		List<Materia> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
			try {
				Query query = session.createQuery("from Materia as mat, mat.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
				query.setString("idioma", idioma);
				query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
				resultado = (List<Materia>)query.list();
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
        	resultado = (List<Materia>)query.list();
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
        	resultado = (List<Materia>)query.list();
        	
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

				resultado = (List<UnidadAdministrativa>) query.list();

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


}
