package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.apache.axis.utils.StringUtils;
import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaResumen;
import org.ibit.rol.sac.model.FichaResumenUA;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoUA;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.Cadenas;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Unidades Administrativas.
 *
 * @ejb.bean
 *  name="sac/persistence/UnidadAdministrativaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.UnidadAdministrativaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UnidadAdministrativaFacadeEJB extends HibernateEJB implements UnidadAdministrativaDelegateI {

	private static final long serialVersionUID = 6954366130820517158L;

	
	/** Nom pel govern de les illes a la taula d'unitats orgàniques */
	public static final String NOM_GOVERN_ILLES = "Govern de les Illes Balears";

	
	/** ID comodín de unidad administrativa */
	public static final String EMPTY_ID = "-1";

	
	/**
	 * Obtiene refer�ncia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();

	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	
	/**
	 * Crea una Unidad Administrativa raíz.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system}"
	 * 
	 * @param unidad	Indica la unidad administrativa a crear.
	 * 
	 * @return Devuelve el identificador de la unidad administrativa.
	 */
	public Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad) {
		
		Session session = getSession();
		
		try {
			
			int orden = numUnidadesRaiz(session);
			unidad.setOrden(orden);
			session.save(unidad);
			addOperacion( session, unidad, Auditoria.INSERTAR );
			session.flush();
			Actualizador.actualizar(unidad);
			
			return unidad.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Crea una Unidad Administrativa
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 * 
	 * @param unidad	Indica la unidad administrativa a crear.
	 * 
	 * @param idPadre	Identificador de la unidad adminsitrativa padre.
	 * 
	 * @return Devuelve el identificador de la unidad administrativa padre.
	 */
	public Long crearUnidadAdministrativa(UnidadAdministrativa unidad, Long idPadre) {
		
		Session session = getSession();
		
		try {
			
			if ( !getAccesoManager().tieneAccesoUnidad( idPadre, true ) ) {
				
				throw new SecurityException("No tiene acceso a la unidad");
				
			}

			UnidadAdministrativa padre = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idPadre );
			padre.addHijo(unidad);
			session.save(unidad);
			addOperacion( session, unidad, Auditoria.INSERTAR );
			session.flush();
			Actualizador.actualizar(unidad);
			
			return unidad.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Actualiza una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * 
	 * @param unidad	Indica la unidad administrativa a actualizar.
	 * 
	 * @param idPadre	Identificador de la unidad administrativa.
	 */
	public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad, Long idPadre) {
		
		Session session;

		Long idPadreAntigua = ( unidad.getPadre() == null ? null : unidad.getPadre().getId() );

		boolean newIsNull = ( idPadre == null );
		boolean oldIsNull = ( idPadreAntigua == null );

		if ( newIsNull && !userIsSystem() ) {
			
			throw new SecurityException("Solo el usuario de sistema puede crear raices.");
			
		}

		boolean change = ( newIsNull ? !oldIsNull : !idPadre.equals(idPadreAntigua) );

		if ( change && !oldIsNull && !newIsNull ) {
			
			if ( !getAccesoManager().tieneAccesoMoverOrganigrama( idPadreAntigua, idPadre ) ) {
				
				throw new SecurityException("No tiene acceso al nodo superior anterior o al actual.");
				
			}
			
		}

		if ( !getAccesoManager().tieneAccesoUnidad( unidad.getId(), true ) ) {
			
			throw new SecurityException("No tiene acceso a la unidad");
			
		}

		session = getSession();
		
		try {
			
			session.update(unidad);
			
			if (change) {
				
				if ( !oldIsNull ) {
					
					UnidadAdministrativa oldPadre = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idPadreAntigua );
					oldPadre.removeHijo(unidad);
					
				}
				
				if ( !newIsNull ) {
					
					UnidadAdministrativa newPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idPadre);
					newPadre.addHijo(unidad);
					
				}
				
				if ( newIsNull || oldIsNull ) {
					
					session.flush();
					Query query = session.getNamedQuery("unidades.root");
					List<UnidadAdministrativa> lista = castList(UnidadAdministrativa.class, query.list() );
					
					for ( int i = 0; i < lista.size(); i++ ) {
						
						UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
						uni.setOrden(i);
						
					}
				}
				
			}
			
			addOperacion( session, unidad, Auditoria.MODIFICAR );
			session.flush();
			Actualizador.actualizar(unidad);
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 *  @deprecated	No se usa
	 * Lista los hijos de una unidad Administrativa.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List listarDescendientesConse(String id) {
		Session session = getSession();
		try {
			Query query = session.createQuery("");
			query = session.createQuery("select ua.id from UnidadAdministrativa as ua " +
					"WHERE ua.padre="+id+" OR  ua.padre IN (SELECT ua2.padre from UnidadAdministrativa as ua2 WHERE ua2.padre="+id+")");
			query.setCacheable(true);
			return query.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Lista los hijos de una unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return	Devuelve <code>List<UnidadAdministrativa></code> de los hijos de la unidad administrativa solicitada.
	 */
	public List<UnidadAdministrativa> listarHijosUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			Hibernate.initialize( ua.getHijos() );
			List<UnidadAdministrativa> result = new ArrayList<UnidadAdministrativa>();
			
			for ( int i = 0; i < ua.getHijos().size(); i++ ) {
				
				UnidadAdministrativa uaHijo = (UnidadAdministrativa) ua.getHijos().get(i);
				if (uaHijo != null && visible(uaHijo)) {
					
					result.add(uaHijo);
					
				}
				
			}
			
			return result;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	/**
	 * @deprecated	Se usa desde el back antiguo.
	 * Lista las unidades Administrativas raiz de un usuario.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<UnidadAdministrativa> listarUnidadesAdministrativasRaiz() {
		Session session = getSession();
		try {
			Usuario usu = getUsuario(session);
			if (userIsSystem()) {
				return castList(UnidadAdministrativa.class, session.createCriteria(UnidadAdministrativa.class).add(Expression.isNull("padre")).list());
			} else {
				//List uas = new ArrayList(session.filter(usu.getUnidadesAdministrativas(), "where this.padre is null"));
				// Les arrel no tenen perque tenir el pare null ja que un usuari pot tenir assignat un node qualsevol.
				List<UnidadAdministrativa> uas = new ArrayList<UnidadAdministrativa>( castSet(UnidadAdministrativa.class, usu.getUnidadesAdministrativas()) );

				// Eliminamos unidades duplicadas, por haber ya un antecesor.
				Set<UnidadAdministrativa> duplicadas = new HashSet<UnidadAdministrativa>();

				for (int i = 0; i < uas.size(); i++) {

					UnidadAdministrativa unidad = (UnidadAdministrativa) uas.get(i);
					UnidadAdministrativa padre = unidad.getPadre();
					boolean duplicada = false;

					while (!duplicada && padre != null) {

						if (uas.contains(padre)) {
							duplicada = true;
							duplicadas.add(unidad);
						}
						padre = padre.getPadre();
					}

				}

				uas.removeAll(duplicadas);
				return uas;
			}
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 *  @deprecated	No se usa
	 * Obtiene la primera unidad Administrativas raiz de un usuario.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa obtenerPrimeraUnidadAdministrativaRaiz() {
		Session session = getSession();
		try {
			List<UnidadAdministrativa> result;
			Criteria criterio = session.createCriteria(UnidadAdministrativa.class);
			criterio.add(Expression.isNull("padre"));
			criterio.addOrder(Order.asc("orden"));
			result = castList(UnidadAdministrativa.class, criterio.list());
			return (UnidadAdministrativa) result.get(0);

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Lista las unidades Administrativas raiz de un usuario que estan publicadas o no.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param publicadas	Indica si las unidades administrativas están publicadas.
	 * 
	 * @return Devuelve <code>List<UnidadAdministrativa></code> de todas las unidades administrativas publicadas.
	 */
	public List<UnidadAdministrativa> listarUnidadesAdministrativasRaiz(boolean publicadas) {
		
		Session session = getSession();
		
		try {
			
			Usuario usu = getUsuario(session);

			Criteria criteria = session.createCriteria(UnidadAdministrativa.class);
			criteria.add( Expression.isNull("padre") );
			criteria.add( publicadas ? Expression.isNotNull("businessKey") : Expression.isNull("businessKey") );

			if ( !userIsSystem() ) {
				
				criteria.createAlias( "usuarios", "usu" );
				criteria.add( Expression.eq( "usu.username", usu.getUsername() ) );
				
			}

			return castList( UnidadAdministrativa.class, criteria.list() );

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Lista las unidades Administrativas raiz que estan o no publicadas.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<UnidadAdministrativa> listarTodasUnidadesAdministrativasRaiz() {

		Session session = getSession();

		try {

			return castList(UnidadAdministrativa.class, 
					session.createCriteria(UnidadAdministrativa.class).add(Expression.isNull("padre")).list());

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	
	/**
	 * Lista los padres de unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad adminsitrativa solicitada.
	 * 
	 * @return	Devuelve <code>List<UnidadAdministrativa></code> de la unidad administrativa solicitada. 
	 */
	public List<UnidadAdministrativa> listarPadresUnidadAdministrativa(Long id) {
		
		Session session = getSession();
		List<UnidadAdministrativa> padres = new Vector<UnidadAdministrativa>();

		try {
			
			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			padres.add(unidadAdministrativa);

			UnidadAdministrativa padre = unidadAdministrativa.getPadre();
			while ( padre != null ) {
				
				padres.add(padre);
				padre = padre.getPadre();
				
			}
			
			Collections.reverse(padres);
			
			return padres;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * @deprecated	Se usa desde el back antiguo
	 * Lista los padres de unidad Administrativa, comprobando el acceso.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<UnidadAdministrativa> listarPadresUnidadAdministrativaAcceso(Long id) {

		List<UnidadAdministrativa> padres = listarPadresUnidadAdministrativa(id);
		List<UnidadAdministrativa> ret = new Vector<UnidadAdministrativa>();

		for (int i=0;i<padres.size();i++) {
			UnidadAdministrativa uni = (UnidadAdministrativa)padres.get(i);
			if (getAccesoManager().tieneAccesoUnidad( uni.getId(), false)) 
				ret.add(uni);
		}

		return ret;

	}

	
	/**
	 * @deprecated	No se usa
	 * Listar BusinessKeys de la unidades administrativas.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List listarBusinessKey() {
		Session session = getSession();
		try {
			Query query = session.createQuery("select ua.businessKey from UnidadAdministrativa ua where ua.businessKey is not null");
			query.setCacheable(true);
			return query.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 *  @deprecated	Se usa en el back antiguo
	 * Busca todas las Unidades Administrativas que cumplen los criterios de b�squeda
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List<UnidadAdministrativa> buscarUnidadesAdministrativas(Map parametros, Map traduccion) {
		Session session = getSession();
		try {
			Usuario usuario = getUsuario(session);
			List<String> params = new ArrayList<String>();

			String sQuery = populateQuery(parametros, traduccion, params);
			Query query = session.createQuery("from UnidadAdministrativa as unidad, unidad.traducciones as trad " + sQuery);
			for (int i = 0; i < params.size(); i++) {
				String o = params.get(i);
				query.setString(i, o);
			}
			List<UnidadAdministrativa> uas = castList(UnidadAdministrativa.class, query.list());

			List<UnidadAdministrativa> uasAux = new ArrayList<UnidadAdministrativa>();
			for (Iterator<UnidadAdministrativa> iterUas = uas.iterator(); iterUas.hasNext();) {
				UnidadAdministrativa ua = (UnidadAdministrativa) iterUas.next();
				if (tieneAcceso(usuario, ua, false)) {
					uasAux.add(ua);
				}
			}

			return uasAux;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Obtiene una Unidad Administrativa
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>UnidadAdministrativa</code> solicitada.
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativa(Long id) {
		
		Session session = getSession();
		
		try {
			
			return (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	

	/**
	 * @deprecated	No se usa
	 * Obtiene una Unidad Administrativa {PORMAD}
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativaPM(Long id) {
		Session session = getSession();
		UnidadAdministrativa ua = null;
		try {
			ua = (UnidadAdministrativa) session.load(
					UnidadAdministrativa.class, id);
			Hibernate.initialize(ua.getEdificios()); 

			return ua;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Dice si existe una unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	id Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>true</code> si existe la unidad adminsitrativa.
	 */
	public boolean existeUnidadAdministrativa(Long id) {
		
		Session session = getSession();
		
		try {
			
			Criteria criteri = session.createCriteria(UnidadAdministrativa.class);
			criteri.add( Expression.eq( "id", id ) );
			UnidadAdministrativa unidad = (UnidadAdministrativa) criteri.uniqueResult();
			
			return unidad != null;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	

	/**
	 * @deprecated	No se usa
	 * Obtiene la informaci�n general de una Unidad Administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa informacionGeneral(Long id) {
		Session session = getSession();
		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			if (visible(ua)) {
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getHijos());
				return ua;
			} else {
				throw new SecurityException("El usuario no tiene el rol operador");
			}
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Obtiene informacion para el front de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa solicitada.
	 * 
	 * @return Devuelve <code>UnidadAdministrativa</code> solicitada.
	 */
	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(Long id) {

		Session session = getSession();

		try {

			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );

			if ( visible(ua) ) {

				Hibernate.initialize( ua.getFotop() );
				Hibernate.initialize( ua.getFotog() );
				Hibernate.initialize( ua.getLogoh() );
				Hibernate.initialize( ua.getLogov() );
				Hibernate.initialize( ua.getLogos() );
				Hibernate.initialize( ua.getLogot() );
				Hibernate.initialize( ua.getTratamiento() );
				Hibernate.initialize( ua.getEspacioTerrit() );
				Hibernate.initialize( ua.getEdificios() );
				Hibernate.initialize( ua.getUnidadesMaterias() );
				Hibernate.initialize( ua.getPadre() );
				Hibernate.initialize( ua.getHijos() );
				Hibernate.initialize( ua.getProcedimientos() );
				Hibernate.initialize( ua.getPersonal() );
				Hibernate.initialize( ua.getNormativas() );
				Hibernate.initialize( ua.getUsuarios() );
				
				return ua;

			} else {

				throw new SecurityException( "La unidad administrativa no es publica con id " + ua.getId() );

			}

		} catch (HibernateException he) {

			throw new EJBException( "No se ha encontrado la unitat administrativa solicitada", he );

		} finally {

			close(session);

		}

	}
	
	


	/**
	 * @deprecated	No se usa.
	 * Obtiene informacion para el front de una Unidad Administrativa (PORMAD)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa consultarUAPormad(Long id) {
		Session session = getSession();
		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			if (ua.getValidacion().equals(Validacion.PUBLICA)) {
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getEdificios());
				Hibernate.initialize(ua.getHijos());
				return ua;
			} else {
				throw new SecurityException("La unidad administrativa no es Publica");
			}
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Obtiene informacion para el front de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa solicitada.
	 * 
	 * @return Devuelve <code>UnidadAdministrativa</code> solicitada.
	 */
	public UnidadAdministrativa consultarUnidadAdministrativa(Long id) {

		Session session = getSession();

		try {

			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );

			if ( visible(ua) ) {
				
				Hibernate.initialize( ua.getFotop() );
				Hibernate.initialize( ua.getFotog() );
				Hibernate.initialize( ua.getLogoh() );
				Hibernate.initialize( ua.getLogov() );
				Hibernate.initialize( ua.getLogos() );
				Hibernate.initialize( ua.getLogot() );
				Hibernate.initialize( ua.getTratamiento() );
				Hibernate.initialize( ua.getEspacioTerrit() );
				Hibernate.initialize( ua.getEdificios() );
				Hibernate.initialize( ua.getUnidadesMaterias() );
				Hibernate.initialize( ua.getPadre() );
				Hibernate.initialize( ua.getHijos() );
				Hibernate.initialize( ua.getFichasUA() );
				Hibernate.initialize( ua.getTodasfichas() );
				Hibernate.initialize( ua.getProcedimientos() );
				Hibernate.initialize( ua.getPersonal() );
				Hibernate.initialize( ua.getNormativas() );

				if ( userIsSuper() ) {
					
					Hibernate.initialize( ua.getUsuarios() );
					
				}
				
				return ua;
				
			} else {
				
				throw new SecurityException( "La unidad administrativa no se publica con id " + ua.getId() );
				
			}

		} catch (HibernateException he) {
			
			throw new EJBException( "No se ha encontrado la unidad administrativa solicitada", he );
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	


	/**
	 *  @deprecated	Se usa en el back antiguo
	 * Obtiene informacion para el front de una Unidad Administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id) {
		Session session = getSession();

		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {
				Hibernate.initialize(ua.getFotop());
				Hibernate.initialize(ua.getFotog());
				Hibernate.initialize(ua.getLogoh());
				Hibernate.initialize(ua.getLogov());
				Hibernate.initialize(ua.getLogos());
				Hibernate.initialize(ua.getLogot());
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getEspacioTerrit());
				Hibernate.initialize(ua.getEdificios());
				Hibernate.initialize(ua.getUnidadesMaterias());
				Hibernate.initialize(ua.getPadre());
				Hibernate.initialize(ua.getHijos());
				Hibernate.initialize(ua.getFichasUA());
				Hibernate.initialize(ua.getTodasfichas());
				Hibernate.initialize(ua.getProcedimientos());
				Hibernate.initialize(ua.getPersonal());
				Hibernate.initialize(ua.getNormativas());

				if (userIsAdmin()) {
					Hibernate.initialize(ua.getUsuarios());
				}
				return ua;
			} else {
				log.error("L'unidad administrativa no es visible;");
				return null;
			}

		} catch (HibernateException he) {
			throw new EJBException("No s' ha trobat la unitat administrativa sol�licitada",he);
		} finally {
			close(session);
		}
	}
	
	

	/**
	 * @deprecated	No se usa.
	 * Obtiene informacion para el front del pormad (PORMAD)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa consultarUnidadAdministrativaPORMAD(Long id) {
		Session session = getSession();
		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			if (visible(ua)) {
				Hibernate.initialize(ua.getFotop());
				Hibernate.initialize(ua.getFotog());
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getEdificios());
				Hibernate.initialize(ua.getHijos());

				return ua;
			} else {
				throw new SecurityException("El usuario no tiene el rol operador");
			}

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	

	
	/**
	 * @deprecated	No se usa
	 * Repara el orden de todas las unidades administrativas(PORMAD)
	 * @ejb.interface-method
	 * @ejb.permission unchecked = "true"
	 */
	@SuppressWarnings("unchecked")
	public void repararOrdenFichasUA() {
		Session session = getSession();
		try {
			Criteria criteria = session.createCriteria(UnidadAdministrativa.class);

			List<UnidadAdministrativa> unidades= criteria.list();
			for(UnidadAdministrativa unidad : unidades){
				unidad.setFichasUA(resetOrden(unidad.getFichasUA()));
			}
			session.flush();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	private List<FichaUA> resetOrden(List<FichaUA> fichas){
		
		List<FichaUA> resultado = new ArrayList<FichaUA>();
		int temp = 0;
		
		for ( FichaUA ficha : fichas ) {
			
			if ( ficha != null ) {
				
				ficha.setOrden(temp);
				temp++;
				resultado.add(ficha);
				
			}
			
		}
		
		return resultado;
		
	}
	
	

	/**
	 * @deprecated	No se usa
	 * Obtiene una Unidad Administrativa por el nombre.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativaPorNombre(String nombre) {
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("unidades.byname");
			query.setParameter("nombre", nombre);
			query.setMaxResults(1);
			query.setCacheable(true);

			List<UnidadAdministrativa> result = castList(UnidadAdministrativa.class, query.list());

			if (result.isEmpty()) {
				return null;
			}
			UnidadAdministrativa ua = (UnidadAdministrativa) result.get(0);
			if (visible(ua)) {
				return ua;
			} else {
				throw new SecurityException("El usuario no tiene el rol operador");
			}
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Obtiene una Unidad Administrativa por el codigo Estandar(PORMAD).
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param codEstandar Indica el código estándar de una unidad administrativa.
	 * 
	 * @return <code>UnidadAdministrativa</code> solicitada.
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(String codEstandar) {
		
		Session session = getSession();
		
		try {
			
			Query query = session.createQuery("from UnidadAdministrativa as ua where ua.codigoEstandar = :codEst");
			
			query.setParameter( "codEst", codEstandar );
			query.setMaxResults(1);
			query.setCacheable(true);
			
			List<UnidadAdministrativa> result = castList(UnidadAdministrativa.class, query.list());
			
			if ( result.isEmpty() ) {
				
				return null;
				
			}
			
			UnidadAdministrativa ua = (UnidadAdministrativa) result.get(0);
			
			if ( visible(ua) ) {
				
				Hibernate.initialize( ua.getFotop() );
				Hibernate.initialize( ua.getHijos() );
				Hibernate.initialize( ua.getFotog() );
				Hibernate.initialize( ua.getLogoh() );
				Hibernate.initialize( ua.getLogov() );
				Hibernate.initialize( ua.getLogos() );
				Hibernate.initialize( ua.getLogot() );
				Hibernate.initialize( ua.getTratamiento() );
				Hibernate.initialize( ua.getUnidadesMaterias() );
				Hibernate.initialize( ua.getEdificios() );
				
				return ua;
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
				
			}
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene la foto pequeña de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de una unidad administrativa.
	 * 
	 * @return	Devuelve <code>Archivo</code> que contiene la foto pequeña de la unidad administrativa solicitada.
	 */
	public Archivo obtenerFotoPequenyaUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			
			if ( visible(ua) ) {
				
				Hibernate.initialize( ua.getFotop() );
				
				return ua.getFotop();
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
				
			}
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene la foto grande de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>Archivo</code> que contiene la foto grande de la unidad administrativa solcitada.
	 */
	public Archivo obtenerFotoGrandeUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			
			if (visible(ua)) {
				
				Hibernate.initialize(ua.getFotog());
				
				return ua.getFotog();
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
				
			}

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo horitzontal de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>Archivo</code> con el logo horizontal de la unidad administrativa.
	 */
	public Archivo obtenerLogoHorizontalUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			
			if (visible(ua)) {
				
				Hibernate.initialize( ua.getLogoh() );
				
				return ua.getLogoh();
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
			}

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo vertical de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>Archivo</code> con el logo vertical de la unidad administrativa solicitada.
	 */
	public Archivo obtenerLogoVerticalUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			
			if ( visible(ua) ) {
				
				Hibernate.initialize( ua.getLogov() );
				return ua.getLogov();
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
				
			}

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo saludo de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>Archivo</code> con el logo saludo de la unidad administrativa solicitada.
	 */
	public Archivo obtenerLogoSaludoUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			
			if ( visible(ua) ) {
				
				Hibernate.initialize( ua.getLogos() );
				
				return ua.getLogos();
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
				
			}

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	//  CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo saludo vertical de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id 	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>Archivo</code> que contiene el logo saludo verticval de la unidad administrativa solicitada.
	 */
	public Archivo obtenerLogoSaludoVerticalUA(Long id) {
		
		Session session = getSession();
		
		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			
			if ( visible(ua) ) {
				
				Hibernate.initialize( ua.getLogot() );
				
				return ua.getLogot();
				
			} else {
				
				throw new SecurityException("El usuario no tiene el rol operador");
				
			}

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	


	/**
	 * @deprecated	No se usa
	 * Intercambia el orden de las UA
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 */
	public void cambiarOrden(Long ua1_id, Long ua2_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoUnidad(ua1_id, true)) {
				throw new SecurityException("No tiene acceso a la unidad anterior");
			}
			if (!getAccesoManager().tieneAccesoUnidad(ua2_id, true)) {
				throw new SecurityException("No tiene acceso a la unidad posterior");
			}
			UnidadAdministrativa p1 = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua1_id);
			UnidadAdministrativa p2 = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua2_id);
			long aux = p1.getOrden();
			p1.setOrden(p2.getOrden());
			p2.setOrden(aux);
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	

	/**
	 * @deprecated	Se usa en el back antiguo
	 * Añade un nuevo edificio a la unidad administrativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 */
	public void anyadirEdificio(Long edificio_id, Long ua_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoUnidad(ua_id, true)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}
			Edificio edificio = (Edificio) session.load(Edificio.class, edificio_id);
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua_id);
			Hibernate.initialize(ua.getHijos());
			Hibernate.initialize(ua.getUnidadesMaterias());


			edificio.getUnidadesAdministrativas().add(ua);  	//con inverse=true 

			ua.getEdificios().add(edificio);  				//sin inverse=true

			session.flush();
			Actualizador.actualizar(edificio,ua.getId());
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	

	
	/**
	 *  @deprecated	Se usa en el back antiguo.
	 * elimina un edificio del al unidad administrativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 */
	public void eliminarEdificio(Long edificio_id, Long ua_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoUnidad(ua_id, true)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}
			Edificio edificio = (Edificio) session.load(Edificio.class, edificio_id);
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua_id);
			Hibernate.initialize(ua.getHijos());
			Hibernate.initialize(ua.getUnidadesMaterias());
			ua.getEdificios().remove(edificio);
			edificio.getUnidadesAdministrativas().remove(ua);
			session.flush();
			Actualizador.borrar(edificio,ua.getId());

		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}


	/**
	 * Autorización para eliminar una  Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 * 
	 * @param idUa	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>true</code> si tiene acceso.
	 */
	public boolean autorizarEliminarUA(Long idUa) {
		
		return ( getAccesoManager().tieneAccesoUnidad( idUa, true ) );
		
	}


	/**
	 * Valida si el usuario tiene privilegios para crear una unidad administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve <code>true</code> si tiene permisos para crear una unidad adminsitrativa.
	 */
	public Boolean autorizarCrearUA() {
		
		Boolean autorizacion;
		
		try {
			
			autorizacion = getAccesoManager().tieneAccesoCrearUnidad();
			
		} catch (SecurityException se) {
			
			autorizacion = Boolean.FALSE;
			
		}
		
		return autorizacion;
		
	}


	/**
	 * Elimina una unidad administrativa. Se podra eliminar la UA, 
	 * si no tiene elementos relacionados (Procedimientos,Normativas,etc.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 * 
	 * @param idUA	Identificador de la unidad administrativa.
	 */
	public void eliminarUaSinRelaciones(Long idUA) {
		
		Session session = getSession();

		try {

			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idUA );
			Boolean isRaiz = ua.isRaiz();

			if ( !isRaiz ) {
				
				// Si no és arrel, el pot borrar només si té accés a l'antecesor. 
				if ( !autorizarEliminarUA( ua.getPadre().getId() ) )
					throw new SecurityException("No tiene acceso al antecesor de la unidad.");
				
			} else {
				
				// Si és arrel, només si és ADMIN o SYSTEM i té accés a l'unitat.
				if ( !userIsAdmin() || !autorizarEliminarUA( ua.getId() ) )
					throw new SecurityException("No tiene acceso a la unidad.");
				
			}

			addOperacion( session, ua, Auditoria.BORRAR );
			getHistorico( session, ua );

			session = deleteCodUaHistorico( session, ua );   

			ua.getEdificios().clear();
			ua.getUsuarios().clear();
			
			if ( !isRaiz ) {
				
				ua.getPadre().removeHijo(ua);
				
			}else{
				
				Usuario usuario = getUsuario(session);
				usuario.getUnidadesAdministrativas().remove(ua);
				
			}

			Long id = ua.getId();
			if( ua instanceof UnidadAdministrativaRemota ) {
				
				AdministracionRemota admin = ( (UnidadAdministrativaRemota) ua ).getAdministracionRemota();
				if( admin != null )
					admin.removeUnidadAdministrativaRemota( (UnidadAdministrativaRemota) ua );
				
			}else{
				
				Actualizador.borrar( new UnidadAdministrativa(id) );
				
			}

			session.delete(ua);
			session.flush();

			if ( isRaiz ) {
				
				Query query = session.getNamedQuery("unidades.root");
				List<?> lista = query.list();
				for ( int i = 0 ; i < lista.size() ; i++ ) {
					UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
					uni.setOrden(i);
				}
				
				session.flush();
				
			}
			
		} catch (HibernateException he) {
			
			throw new EJBException("El sistema está actualizando los datos, espere unos minutos.");
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Borra una unidad administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id 	Identificador de la unidad administrativa solicitada.
	 */
	public void borrarUnidadAdministrativa(Long id) {

		Session session = getSession();

		try {
			
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			
			if ( ua.isRaiz() ) {
				
				throw new SecurityException("No puede eliminar una unidad raiz.");
				
			} else {
				
				if ( !getAccesoManager().tieneAccesoUnidad( ua.getPadre().getId(), true ) ) {
					
					throw new SecurityException("No tiene acceso al padre de la unidad");
					
				}
				
			}

			Long idPadre = ua.getPadre().getId();  
			session.update(ua);

			UnidadAdministrativa uaPadre = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idPadre );
			uaPadre.darDeBajaUA(ua);
			ua.darDeBajaHijosUA(ua);

			addOperacion( session, ua, Auditoria.BORRAR );
			session.flush();

			if ( ua instanceof UnidadAdministrativaRemota ) {
				
				AdministracionRemota admin = ( (UnidadAdministrativaRemota ) ua ).getAdministracionRemota();
				if ( admin != null )
					admin.removeUnidadAdministrativaRemota( (UnidadAdministrativaRemota) ua );
				
			}else{
				
				Actualizador.borrar(ua);
				
			}

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}

	}


	/**
	 * Borra una unidad administrativa raiz.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id	Identificador de la unidad administrativa
	 */
	public void borrarUnidadAdministrativaRaiz(Long id) {
		
		Session session = getSession();

		try {
			
			if ( !getAccesoManager().tieneAccesoUnidad( id, true ) ) {
				
				throw new SecurityException("No tiene acceso a la unidad");
				
			}

			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			if ( !ua.isRaiz() ) {
				
				throw new SecurityException("No puede eliminar una unidad que no sea raiz.");
				
			}

			addOperacion( session, ua, Auditoria.BORRAR );
			Historico historico = getHistorico( session, ua );
			( (HistoricoUA) historico ).setUa(null);

			ua.getEdificios().clear();
			ua.getUsuarios().clear();
			Usuario usuario = getUsuario(session);
			usuario.getUnidadesAdministrativas().remove(ua);
			List fichasUA = ua.getFichasUA();
			Iterator iter = fichasUA.iterator();
			
			while ( iter.hasNext() ) {
				
				FichaUA ficha = (FichaUA) iter.next();
				if ( ficha != null ) {
					
					Seccion seccion = ficha.getSeccion();
					seccion.removeFichaUA(ficha);
					
				}
				
			}

			Set<UnidadMateria> listaUnidadMateria = (Set<UnidadMateria>) ua.getUnidadesMaterias();
			for ( UnidadMateria uniMat : listaUnidadMateria ) {
				
				uniMat.getMateria().removeUnidadMateria(uniMat);
				session.delete(uniMat);
				
			}

			if ( ua instanceof UnidadAdministrativaRemota ) {
				
				AdministracionRemota admin = ( (UnidadAdministrativaRemota) ua ).getAdministracionRemota();
				if ( admin != null )
					admin.removeUnidadAdministrativaRemota( (UnidadAdministrativaRemota) ua );
				
			} else {
				
				Actualizador.borrar( new UnidadAdministrativa(id) );
				
			}

			session.delete(ua);
			session.flush();

			Query query = session.getNamedQuery("unidades.root");
			List lista = query.list();
			for ( int i = 0 ; i < lista.size() ; i++ ) {
				
				UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
				uni.setOrden(i);
				
			}
			
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	

	/**
	 *  @deprecated	No se usa
	 * Obtiene una unidad administrativa por BusinessKey.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa obtenerUnidadPorBusinessKey(String bk) {
		Session session = getSession();
		try {
			Criteria criterio = session.createCriteria(UnidadAdministrativa.class);
			criterio.setMaxResults(1);
			criterio.add(Expression.eq("businessKey", bk));
			List result = criterio.list();
			if (result.isEmpty()) {
				return null;
			}

			return (UnidadAdministrativa) result.get(0);
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}

	}
	
	

	/**
	 * @deprecated	Se usa en el back antiguo.
	 * Fija el BusinessKey de una Unidad Administrativa.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void fijarBusinessKey(Long id, String businessKey) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoUnidad(id, true)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			ua.setBusinessKey(businessKey);
			Actualizador.actualizar(ua);
			session.flush();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	

	
	/**
	 * @deprecated	Se usa en el back antiguo
	 * Borrar el BusinessKey de una Unidad Administrativa.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public boolean borrarBusinessKey(String businessKey) {
		Session session = getSession();
		try {
			Criteria criterio = session.createCriteria(UnidadAdministrativa.class);
			criterio.setMaxResults(1);
			criterio.add(Expression.eq("businessKey", businessKey));
			List result = criterio.list();
			if (result.isEmpty()) {
				return false;
			}

			UnidadAdministrativa ua = (UnidadAdministrativa) result.get(0);
			if (!getAccesoManager().tieneAccesoUnidad(ua.getId(), true)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}

			ua.setBusinessKey(null);
			Actualizador.actualizar(ua);
			session.flush();
			return true;
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	

	
	/* Metode fet per el portal de salut, fet de na Marilen */
	/**
	 * @deprecated	No se usa.
	 * carga el arbol de unidades Administrativas a partir de un id
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa cargarArbolUnidad(Long id) {
		Session session = getSession();
		try {
			return cargarHijosUnidad(id,session);
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Carga los identificadores de una unidad y de sus hijos de manera recursiva.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission  unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>List<Long></code> con los identificadores de los hijos de la unidad administrativa solicitada.
	 */
	public List<Long> cargarArbolUnidadId(Long id) {
		
		Session sessio = getSession();
		
		try{
			
			List<Long> ids = new ArrayList<Long>();
			ids.add(id);
			cargarHijosUnidadId( id, ids, sessio );
			
			return ids;
			
		}catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(sessio);
			
		}
		
	}
	

	/**
	 * @deprecated	No se usa
	 * Lista las unidades Administrativas por Ambito(0:Autonomico, 1:Insular)(PORMAD)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<UnidadAdministrativa> listarUnidadesAdministrativasPorAmbito(Long ambito) {
		Session session = getSession();
		try {
			Query query = session.createQuery("from UnidadAdministrativa as ua where"+
					" ua.validacion = :validacion and " +
					" ua.espacioTerrit.nivel= :ambito and" +
					" ua.padre is null");
			query.setParameter("validacion", Validacion.PUBLICA);
			query.setParameter("ambito", ambito);
			query.setCacheable(true);

			List<UnidadAdministrativa> unidades = query.list();
			for(UnidadAdministrativa unidad: unidades){
				Hibernate.initialize(unidad.getFotop());
			}
			return unidades;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	

	/**
	 * @deprecated	No se usa.
	 * Lista las unidades Administrativas por Ambito(0:Autonomico, 1:Insular)(PORMAD)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<UnidadAdministrativa> buscarUnidadesAdministrativasPorAmbito(Long ambito, final String busqueda, final String idioma) {
		Session session = getSession();
		try {

			/*Query query = session.createQuery("from UnidadAdministrativa as ua, ua.traducciones as trad where index(trad) = :idioma and "+
                                              " ua.validacion = :validacion and " +
                                              " ua.espacioTerrit.nivel= :ambito and " +
                                              " upper(trad.nombre) like :busqueda");*/

			Query query = session.createQuery(" from UnidadAdministrativa as ua, " +
					"      ua.traducciones as trad " +
					"where index(trad) = :idioma " +
					"  and upper(trad.nombre) like :busqueda " +
					"  and ua.espacioTerrit.nivel = :ambito " +     
					"  and ua.validacion = :validacion");
			query.setInteger("validacion", Validacion.PUBLICA);
			query.setString("idioma", idioma);
			query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
			query.setLong("ambito", ambito);
			query.setCacheable(true);

			List<UnidadAdministrativa> unidades = query.list();
			for(UnidadAdministrativa unidad: unidades){
				Hibernate.initialize(unidad.getFotop());
			}
			return unidades;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	

	/**
	 *  @deprecated	No se usa
	 * Lista las unidades Administrativas de un determiando espacio territorial (PORMAD)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<UnidadAdministrativa> listarUAEspacioTerritorial(Long idEspacio) {
		Session session = getSession();
		try {
			Query query = session.createQuery("from UnidadAdministrativa as ua " +
					"where ua.validacion = :validacion " +
					"  and ua.espacioTerrit.id= :idEspacio");
			query.setParameter("validacion", Validacion.PUBLICA);
			query.setParameter("idEspacio", idEspacio);
			query.setCacheable(true);

			List<UnidadAdministrativa> unidades = query.list();
			return unidades;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	
	/**
	 * Busca todos los {@link UnidadAdministrativa} cuyo nombre contenga el String de entrada(PORMAD)
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param nombre	Indica el texto que se busca como nombre de unidades administrativas.
	 * 
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @return lista de {@link UnidadAdministrativa}
	 */
	@SuppressWarnings("unchecked")
	public List<UnidadAdministrativa> buscar(final String nombre, final String idioma) {
		
		List<UnidadAdministrativa> resultado;
		if ( nombre != null && !"".equals( nombre.trim() ) ) {
			
			Session session = getSession();
			
			try {
				
				Query query = session.createQuery(
						"from UnidadAdministrativa as uni, " +
						"	  uni.traducciones as trad " +
								
						"where index(trad) = :idioma " +
						"	and upper(trad.nombre) like :busqueda" +
						"	and uni.validacion = :validacion" );
				
				query.setString( "idioma", idioma );
				query.setString( "busqueda", "%" + nombre.trim().toUpperCase() + "%" );
				query.setInteger( "validacion", Validacion.PUBLICA );
				
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
	
	


	/**
	 * @deprecated	No se usa
	 * Obtiene la unidad asociada al espacio territorial y del tipo indicado(PORMAD).
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(Long idEspacio, String tipo) {
		Session session = getSession();
		try {
			Query query = session.createQuery("");
			if("AJU".equals(tipo)) {
				query = session.createQuery("from UnidadAdministrativa as ua " +
						"where ua.espacioTerrit.id = :idEspacio" +
						"  and ua.validacion = :validacion");
				query.setParameter("idEspacio", idEspacio);
				query.setParameter("validacion", Validacion.PUBLICA);
				query.setCacheable(true);

			} else if("CONSELL".equals(tipo)) {
				query = session.createQuery("select ua " +
						"  from UnidadAdministrativa as ua, " +
						"       EspacioTerritorial as espte" +
						" where ua.espacioTerrit = espte.padre" +
						"   and espte.id = :idEspacio " +
						"   and ua.codigoEstandar = :codEstUA" +
						"   and ua.validacion = :validacion");
				query.setParameter("idEspacio", idEspacio);
				query.setParameter("codEstUA", "CONSELL");
				query.setParameter("validacion", Validacion.PUBLICA);
				query.setCacheable(true);
			} else if( "GOV".equals(tipo)) {
				query = session.createQuery("select ua " +
						"  from UnidadAdministrativa as ua, " +
						"       EspacioTerritorial as espte " +
						" where ua.espacioTerrit = espte.padre.padre " +
						"   and espte.id = :idEspacio " +
						"   and ua.codigoEstandar = :codEstUA" +
						"   and ua.validacion = :validacion");
				query.setParameter("idEspacio", idEspacio);
				query.setParameter("codEstUA", "GOV");
				query.setParameter("validacion", Validacion.PUBLICA);
				query.setCacheable(true);
			}
			UnidadAdministrativa ua =  (UnidadAdministrativa)query.uniqueResult();
			if (ua != null) {
				Hibernate.initialize(ua.getUnidadesMaterias());
			}
			return  ua;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	

	/**
	 * Obtiene la unidad asociada al espacio territorial y del tipo indicado(PORMAD).
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 *
	 * @param idEspacio	id del espacio territorial del que queremos obtener una determinada UA
	 * 
	 * @para tipo	tipo de búsqueda que queremos generar (AJU - AYUNTAMIENTO, CONSELL -  CONSELLS INSULARS, GOV - GOVERN)
	 * 
	 * @para UAOpcionales	En el caso de tipo = CONSELL o GOV, puede haber múltipes UA para un determinado espacio territorial,
	 *                     en esta lista estan las opciones a filtrar.
	 *                     
	 * @return Devuelve la {@link UnidadAdministrativa} deseada.
	 */
	@SuppressWarnings("unchecked")
	public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(Long idEspacio, String tipo, List UAOpcionales) {
		
		Session session = getSession();
		
		try {
			
			Query query = session.createQuery("");
			
			if( "AJU".equals(tipo) ) {

				query = session.createQuery(
						" from UnidadAdministrativa as ua" +
				
						" where ua.espacioTerrit.id = :idEspacio" +
						"   and ua.padre is null" +
						"   and ua.validacion = :validacion");

				query.setParameter( "idEspacio", idEspacio );
				query.setParameter( "validacion", Validacion.PUBLICA );
				query.setCacheable(true);

			} else if ("CONSELL".equals(tipo) ) {

				query = session.createQuery(
						" select ua " +
				
						" from UnidadAdministrativa as ua," +
						"      EspacioTerritorial as espte" +
						
						"  where espte.id = :idEspacio" +
						"    and espte.padre.id = ua.espacioTerrit.id" +
						"    and ua.padre is null" +
						"    and ua.validacion = :validacion");

				query.setParameter( "idEspacio", idEspacio );
				query.setParameter( "validacion", Validacion.PUBLICA );
				query.setCacheable(true);
				
			} else if ( "GOV".equals(tipo) ) {

				query = session.createQuery(
						" select ua " +
						" from UnidadAdministrativa as ua, " +
						"      EspacioTerritorial as espte " +
						" where espte.id = :idEspacio" +
						"   and espte.padre.padre.id = ua.espacioTerrit.id" +
						"   and ua.padre is null" +
						"   and ua.validacion = :validacion");
				
				query.setParameter( "idEspacio", idEspacio );
				query.setParameter( "validacion", Validacion.PUBLICA );
				query.setCacheable(true);
				
			}

			if ( "AJU".equals(tipo) ) {
				
				UnidadAdministrativa ua =  (UnidadAdministrativa) query.uniqueResult();
				if ( ua != null ) {
					
					Hibernate.initialize(ua.getUnidadesMaterias());
					
				}
				
				return  ua;
				
			} else {
				
				List unidadesAdministrativas = query.list();
				
				for (Iterator i = unidadesAdministrativas.iterator(); i.hasNext();) {
					
					UnidadAdministrativa ua = (UnidadAdministrativa) i.next();
					if (ua.getCodigoEstandar() != null && ua.getCodigoEstandar().trim().length() != 0) {
						
						Iterator iteradorUAopcionales = UAOpcionales.iterator();
						while ( iteradorUAopcionales.hasNext() ) {
							
							String codEstUA = (String) iteradorUAopcionales.next();
							if ( ua.getCodigoEstandar().equals(codEstUA) ) {
								
								Hibernate.initialize( ua.getUnidadesMaterias() );
								
								return ua;
								
							}
							
						}
						
					}
					
					
				}
			}
			
			return null;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * @deprecated	No se usa
	 * Devuelve un {@link Map} que contiene 2 {@link List}, uno de {@link ProcedimientoLocal}
	 * y el otro de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 *
	 * Los key del {@link Map} son los String "PROCEDIMIENTOS" y "FICHAS"  (PORMAD)
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idUA	Identificador de la unidad adminsitrativa.
	 * 
	 * @param idMat	Identificador de la materia.
	 * 
	 * @param ceSec
	 * 
	 * @return Devuelve un {@link Map} que contiene 2 {@link List}, uno de {@link ProcedimientoLocal}
	 * y el otro de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 */
	public Map<String,List<?>> listarProcFichSecMat(final Long idUA, final Long idMat, final String ceSec ){
		
		Map<String,List<?>> resultado = new HashMap<String,List<?>>();
		
		if( idUA !=null && idMat != null ) {
			
			Session session = getSession();
			
			try {
				
				List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();
				List<Ficha> fichas = new ArrayList<Ficha>();

				UnidadAdministrativa unidad = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idUA );
				if ( unidad instanceof UnidadAdministrativaRemota ) {
					
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcFichSecMat( session, unidadRemota.getAdministracionRemota(), idMat, ceSec, procs, fichas );
					
				}else{
					
					listarProcFichSecMatRecur( session, unidad, idMat, ceSec, procs, fichas );
				}
				
				resultado.put( "PROCEDIMIENTOS", procs );
				resultado.put( "FICHAS", fichas );
				
			} catch (HibernateException he) {
				
				throw new EJBException(he);
				
			} finally {
				
				close(session);
				
			}
			
		}else{
			
			resultado.put( "PROCEDIMIENTOS", Collections.emptyList() );
			resultado.put( "FICHAS", Collections.emptyList() );
			
		}

		return resultado;
		
	}


	/**
	 * Devuelve todas las {@link Seccion} relacionadas con una {@link UnidadAdministrativa}
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idUA	Identificador de la unidad admistrativa solicitada.
	 * 
	 * @return Devuelve <code>List<Seccion></code> de la unidad administrativa solicitada.
	 */
	public List<Seccion> listarSeccionesUA(final Long idUA) {

		List<Seccion> resultado = null;

		if ( idUA != null ) {

			Session session = getSession();

			try {

				resultado = new ArrayList<Seccion>();

				Query query = session.createQuery(
						" SELECT DISTINCT seccion " +
				
						" FROM FichaUA AS fua, " +
						"	   Seccion AS seccion " +
						
						" WHERE fua.seccion.id = seccion.id AND fua.unidadAdministrativa.id = :idUA ");
				
				query.setParameter("idUA", idUA);

				resultado = query.list();

				// Inicializamos hijos para no tener error de lazy al comprobar si el List contiene
				// o no elementos, intentando saber si tiene hijos para crear correctamente el DTO
				// asociado al elemento Seccion (SeccionDTO.fills).
				Iterator<Seccion> itSeccion = resultado.iterator();
				while ( itSeccion.hasNext() ) {

					Seccion seccion = itSeccion.next();
					Hibernate.initialize( seccion.getHijos() );

				}

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
	 * Devuelve el número de {@link Ficha} relacionadas con una {@link UnidadAdministrativa} y una {@link Seccion}.
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idUA Identificador de la unidad administrativa.
	 * 
	 * @param idSeccion	Identificador de la sección.
	 * 
	 * @return Devuelve el número de fichas relacionadas a una unidad administrativa y a una sección.
	 */
	public Long cuentaFichasSeccionUA(final Long idUA, final Long idSeccion) {

		Long resultado = Long.valueOf(0);

		if ( idUA != null && idSeccion != null ) {

			Session session = getSession();

			try {

				Query query = session.createQuery(
						" SELECT COUNT(*) " +
				
						" FROM FichaUA AS fua " +
						
						" WHERE fua.unidadAdministrativa.id = :idUA " +
						"	AND fua.seccion.id = :idSeccion");
				
				query.setParameter( "idUA", idUA );
				query.setParameter( "idSeccion", idSeccion );

				resultado = ( (Integer) query.uniqueResult() ).longValue();

			} catch (HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		}

		return resultado;


	}
	
	
	/**
	 *  @deprecated	Se usa desde el back antiguo
	 * Devuelve un {@link Map} que contiene 2 {@link List}, uno de {@link ProcedimientoLocal}
	 * y el otro de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 *
	 * Los key del {@link Map} son los String "PROCEDIMIENTOS" y "FICHAS" (PORMAD)
	 *
	 * @param idUA
	 * @param idHV
	 * @param ceSec
	 * @return
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Map<String,List<?>> listarProcFichSecHV(final Long idUA, final Long idHV, final String ceSec ){
		Map<String,List<?>> resultado = new HashMap<String,List<?>>();
		if(idUA!=null && idHV!=null){
			Session session = getSession();
			try {
				List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();
				List<Ficha> fichas = new ArrayList<Ficha>();

				UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
				if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcFichSecHV(session, unidadRemota.getAdministracionRemota(), idHV, ceSec, procs, fichas);
				}else{
					listarProcFichSecHVRecur(session, unidad, idHV, ceSec, procs, fichas);
				}

				resultado.put("PROCEDIMIENTOS", procs);
				resultado.put("FICHAS", fichas);
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}else{
			resultado.put("PROCEDIMIENTOS", Collections.emptyList());
			resultado.put("FICHAS", Collections.emptyList());
		}

		return resultado;
	}

	
	/**
	 * @deprecated	No se usa.
	 * Busca todos los {@link ProcedimientoLocal} que contengan la palabra de la busqueda que
	 * esten relacionados con una {@link UnidadAdministrativa} (PORMAD)
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> buscarProcedimientosUA(final Long idUA, final String busqueda, final String idioma,final Date dataInici, final Date dataFi ){
		List<ProcedimientoLocal> resultado;
		if(idUA!=null && busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
			try {
				resultado =  new ArrayList<ProcedimientoLocal>();

				UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
				if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					buscarProcedimientosUA(session, busqueda, idioma, dataInici,dataFi,unidadRemota.getAdministracionRemota(), resultado );
				}else{
					buscarProcedimientosUARecur(session, busqueda, idioma,dataInici,dataFi, unidad, resultado);
				}

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
	 *  @deprecated	No se usa
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true" 
	 */
	public ModelFilterObject obtenerFilterObject(UnidadAdministrativa ua) {
		ModelFilterObject filter = new ModelFilterObject();


		//de momento, familia y microsites a null

		filter.setFamilia_id(null);    	
		filter.setMicrosite_id(null);
		filter.setSeccion_id(null);


		Iterator iterlang = ua.getLangs().iterator();
		while (iterlang.hasNext()){

			String idioma = (String) iterlang.next();
			String txids=Catalogo.KEY_SEPARADOR;
			String txtexto=" ";//espacio en blanco, que es para tokenizar
			Iterator iter;

			TraModelFilterObject trafilter = new TraModelFilterObject();

			//titulo		
			trafilter.setMaintitle( ((TraduccionUA)ua.getTraduccion(idioma)).getNombre() );


			txids=Catalogo.KEY_SEPARADOR;
			txtexto=" ";
			txids += ua.getId()+Catalogo.KEY_SEPARADOR;
			txtexto += ((TraduccionUA)ua.getTraduccion(idioma)).getNombre()+" ";
			txtexto += ((TraduccionUA)ua.getTraduccion(idioma)).getAbreviatura()+" ";
			txtexto += ((TraduccionUA)ua.getTraduccion(idioma)).getPresentacion()+" ";


			//OBTENER DIRECCIONES
			if (ua.getEdificios()!=null) {
				iter = ua.getEdificios().iterator();
				while (iter.hasNext()) {
					Edificio edificio = (Edificio)iter.next();
					txtexto += edificio.getDireccion()+" ";
					txtexto += edificio.getTelefono()+" ";
				}
			}

			filter.setUo_id( (txids.length()==1) ? null: txids);
			trafilter.setUo_text( (txtexto.length()==1) ? null: txtexto);

			//OBTENER LAS MATERIAS (ademas de las materias se ponen los textos de los HECHOS VITALES)
			if (ua.getUnidadesMaterias()!=null) {
				txids=Catalogo.KEY_SEPARADOR;
				txtexto=" ";
				iter = ua.getUnidadesMaterias().iterator();
				while (iter.hasNext()) {
					UnidadMateria uamat = (UnidadMateria)iter.next();


					txids+=uamat.getMateria().getId()+Catalogo.KEY_SEPARADOR; 
					if (uamat.getMateria().getTraduccion(idioma)!=null) {
						txtexto+=((TraduccionMateria)uamat.getMateria().getTraduccion(idioma)).getNombre() + " ";
						txtexto+=((TraduccionMateria)uamat.getMateria().getTraduccion(idioma)).getDescripcion() + " ";
						txtexto+=((TraduccionMateria)uamat.getMateria().getTraduccion(idioma)).getPalabrasclave() + " ";
					}

				}

				filter.setMateria_id( (txids.length()==1) ? null: txids);
				trafilter.setMateria_text( (txtexto.length()==1) ? null: txtexto);
			}

			filter.addTraduccion(idioma, trafilter);

		}



		return filter;
	}  	


	/**
	 * Añade la unidad administrativa al índice en todos los idiomas
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param unidadAdministrativa	Indica la unidad administrativa que se va a añadir al índice en todos los idiomas.
	 */
	public void indexInsertaUA(UnidadAdministrativa unidadAdministrativa,  ModelFilterObject filter)  {

		try {

			if ( filter == null ) filter = obtenerFilterObject(unidadAdministrativa);

			Iterator iterator = unidadAdministrativa.getLangs().iterator();
			while ( iterator.hasNext() ) {
				
				String idi = (String) iterator.next();
				IndexObject io = new IndexObject();

				io.setId( Catalogo.SRVC_UO + "." + unidadAdministrativa.getId() );
				io.setClasificacion(Catalogo.SRVC_UO);

				io.setMicro( filter.getMicrosite_id() ); 
				io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );

				io.setCaducidad("");
				io.setPublicacion(""); 
				io.setDescripcion("");

				io.addTextLine( unidadAdministrativa.getResponsable() );

				TraduccionUA trad = ( (TraduccionUA) unidadAdministrativa.getTraduccion(idi) );
				if ( trad != null ) {

					io.setUrl( "/govern/organigrama/area.do?coduo=" + unidadAdministrativa.getId() + "&lang=" + idi );
					io.setTituloserviciomain( filter.getTraduccion(idi).getMaintitle() );


					if ( trad.getNombre() != null ) {
						io.setTitulo( trad.getNombre() );
						
						//para dar mas peso al titulo
						for ( int i = 0 ; i < 5 ; i++ ) {
							
							io.addTextLine( trad.getNombre() );
							
						}
						
					}

					if ( trad.getPresentacion() != null )  {
						
						if ( trad.getPresentacion().length() > 200 ) {
							
							io.setDescripcion( trad.getPresentacion().substring(0,199) + "..." );
							
						} else {
							
							io.setDescripcion( trad.getPresentacion() );
							
						}
						
					}

					io.addTextopcionalLine( filter.getTraduccion(idi).getMateria_text() );
					io.addTextopcionalLine( filter.getTraduccion(idi).getSeccion_text() );
					io.addTextopcionalLine( filter.getTraduccion(idi).getUo_text() );

				}

				if ( io.getText().length() > 0 )
					org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);
				
			}
			
		} catch (Exception ex) {
			log.warn( "[indexInsertaUA:" + unidadAdministrativa.getId() + "] No se ha podido indexar UA. " + ex.getMessage() );
		}

	}

	
	/**
	 * Elimina la ua en el indice en todos los idiomas
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 */
	public void indexBorraUA(Long id)  {

		try {

				List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
				for ( int i = 0 ; i < langs.size() ; i++ )
					DelegateUtil.getIndexerDelegate().borrarObjeto( Catalogo.SRVC_UO + "." + id, "" + langs.get(i) );

		} catch (DelegateException ex) {
			log.warn( "[indexBorraFicha:" + id + "] No se ha podido borrar del indice la ficha. " + ex.getMessage() );
		}

	}

	/* ================================================ */
	/* ==  MÉTODOS PRIVADOS  ========================== */
	/* ================================================ */

	private int numUnidadesRaiz(Session session) throws HibernateException {
		
		Query query = session.getNamedQuery("unidades.root.count");
		
		return ( (Integer) query.list().get(0) ).intValue();
		
	}
	

	/**
	 * Construye la consulta de búsqueda segun los parámetros
	 */
	private String populateQuery(Map parametros, Map traduccion, List params) {
		String aux = "";

		for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
			String key = (String) iter1.next();
			Object value = parametros.get(key);
			if (value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (aux.length() > 0) aux = aux + " and ";
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( unidad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( unidad." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else if (value instanceof Date) {
					if (aux.length() > 0) aux = aux + " and ";
					aux = aux + "unidad." + key + " = '" + value + "'";
				} else {
					if (aux.length() > 0) aux = aux + " and ";
					aux = aux + "unidad." + key + " = " + value;
				}
			}
		}

		// Tratamiento de traducciones
		if (!traduccion.isEmpty()) {
			if (aux.length() > 0) aux = aux + " and ";
			aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
			traduccion.remove("idioma");
		}
		for (Iterator iter2 = traduccion.keySet().iterator(); iter2.hasNext();) {
			String key = (String) iter2.next();
			Object value = traduccion.get(key);
			if (value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " and upper( trad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " and upper( trad." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else {
					aux = aux + " and trad." + key + " = ? ";
					params.add(value);
				}
			}
		}

		return aux;
	}

	
	/**
	 * Construye la consulta de búsqueda multiidioma en todos los campos
	 */
	private String i18nPopulateQuery(Map traducciones, List params) {
		String aux = "";

		for (Iterator iterTraducciones = traducciones.keySet().iterator(); iterTraducciones.hasNext();) {

			String key = (String) iterTraducciones.next();
			Object value = traducciones.get(key);

			if (value != null) {
				if (aux.length() > 0) aux = aux + " or ";
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( trad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( trad." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else {
					aux = aux + " trad." + key + " = ? ";
					params.add(value);
				}
			}
		}

		return aux;
	}        

	
	/**
	 * Carga las unidades hijas de una unidad de manera recursiva.
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @param session	Sesión de hibernate.
	 * 
	 * @return Devuelve <code>UnidadAdministrativa</code>.
	 * 
	 * @throws HibernateException
	 */
	private UnidadAdministrativa cargarHijosUnidad(Long id, Session session) throws HibernateException {
		
		UnidadAdministrativa unidad = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
		Hibernate.initialize( unidad.getHijos() );
		List hijos = unidad.getHijos();
		
		for( int i = 0 ; i < hijos.size() ; i++ ) {
			
			UnidadAdministrativa uni = (UnidadAdministrativa) hijos.get(i);
			cargarHijosUnidad( uni.getId(), session );
			
		}
		
		return unidad;
		
	}
	

	/**
	 * Carga todos los ids de las unidades hijas de una unidad de manera recursiva.
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @param listaIdUAhijas	Lista con los identificadores de los hijos de una determinada unidad administrativa.
	 * 
	 * @param session	Sesión de hibernate.
	 * 
	 * @throws HibernateException
	 */
	private void cargarHijosUnidadId(Long id, List listaIdUAhijas, Session session) throws HibernateException {
		
		UnidadAdministrativa unidad = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
		List hijos = unidad.getHijos();

		for( int i = 0 ; i < hijos.size() ; i++ ) {

			UnidadAdministrativa uni = (UnidadAdministrativa) hijos.get(i);
			if ( uni != null ) {
				
				listaIdUAhijas.add( uni.getId() );
				cargarHijosUnidadId( uni.getId(), listaIdUAhijas, session );
				
			}
			
		}

	}

	
	/**
	 * @deprecated Usado por un método que no se usa.
	 * Método recursivo que recorre todo el árbol de una {@link UnidadAdministrativa} rellenando
	 * un listado de {@link ProcedimientoLocal} asociados a las unidades una {@link Materia} y
	 * rellenado un listado de {@link Ficha} asociadas a las unidades, a una {@link Materia} y al
	 * CodigoEstandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session	Sesión de hinernate.
	 * 
	 * @param unidad 	Unidad administrativa sobre la que recorrer su árbol de hijos
	 * 
	 * @param idMat	Identificador de la materia relacionada con las fichas y procedimientos.
	 * 
	 * @param ceSec	CodigoEstandar de una {@link Seccion} relacionada con las Fichas.
	 * 
	 * @param listaProcedimientos Lista de {@link ProcedimientoLocal} a rellenar
	 * 
	 * @param listaFichas	Lista de {@link Ficha} a rellenar
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcFichSecMatRecur(Session session ,final UnidadAdministrativa unidad, final Long idMat, final String ceSec, 
			final List<ProcedimientoLocal> listaProcedimientos, final List<Ficha> listaFichas) throws HibernateException {
		
		//Navegacion por el árbol de hijos.
		for ( UnidadAdministrativa hijo : unidad.getHijos() ) {
			
			Hibernate.initialize( hijo.getHijos() );
			if( hijo.getHijos() != null ) {
				
				listarProcFichSecMatRecur( session, hijo, idMat, ceSec, listaProcedimientos, listaFichas );
				
			}
			
		}

		//Consulta que devuelve los procedimientos relacionados.
		Query query = session.createQuery(
				"Select DISTINCT proc " +
		
				"From ProcedimientoLocal as proc, " +
				"	  Materia as mat " +
				
				"Where proc.unidadAdministrativa.id = :idUA " +
				"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
				"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
				"	AND proc.validacion = :validacion " +
				"	AND mat.id=:idMat AND mat in elements(proc.materias) ");
		
		query.setLong( "idUA",  unidad.getId() );
		query.setLong( "idMat", idMat );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		
		listaProcedimientos.addAll( query.list() );

		//Consulta que devuelve las fichas relacionadas.
		query = session.createQuery(
				"Select DISTINCT f " +
		
				"From FichaUA as fua," +
				"	  fua.ficha as f, " +
				"	  Materia as mat " +
				
				"Where fua.unidadAdministrativa.id = :idUA " +
				"	AND fua.seccion.codigoEstandard = :ceSec " +
				"	AND f.validacion = :validacion " +
				"	AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
				"	AND mat.id = :idMat " +
				"	AND mat in elements(f.materias) ");
		
		query.setLong( "idUA",  unidad.getId() );
		query.setLong( "idMat", idMat );
		query.setString( "ceSec", ceSec );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		
		listaFichas.addAll( query.list() );
		
	}

	
	/**
	 * @deprecated	No se usa
	 * Rellena un listado de {@link ProcedimientoLocal} y {@link Ficha} relacionados con
	 * una {@link AdministracionRemota}, una {@link Materia} y el codigo
	 * estandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session
	 * @param admin AdministracionRemota sobre la que buscar
	 * @param idMat,  Materia relacionada con las fichas y procedimientos
	 * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	 * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
	 * @param fichas, Lista de {@link Ficha} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcFichSecMat(Session session ,final AdministracionRemota admin, final Long idMat, final String ceSec, final List<ProcedimientoLocal> procs, final List<Ficha> fichas) throws HibernateException{

		//Query que retorna los procedimientos relacionados
		Query query = session.createQuery("Select DISTINCT proc From ProcedimientoRemoto as proc, Materia as mat " +
				"where proc.administracionRemota.id=:idAdmin AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
				"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
				"AND mat.id=:idMat AND mat in elements(proc.materias) ");
		query.setLong("idAdmin",  admin.getId());
		query.setLong("idMat", idMat);
		query.setParameter("validacion", Validacion.PUBLICA);
		query.setParameter("fecha", DateUtils.today());
		procs.addAll(query.list());

		//Query que retorna las fichas relacionadas
		query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, Materia as mat " +
				"where f.administracionRemota.id=:idAdmin AND fua.ficha = f " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				"AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
				"AND mat.id=:idMat AND mat in elements(f.materias) ");
		query.setLong("idAdmin",  admin.getId());
		query.setLong("idMat", idMat);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
		query.setParameter("fecha", DateUtils.today());
		fichas.addAll(query.list());
	}


	/**
	 * @deprecated Usado por un método que no se usa.
	 * Método recursivo que recorre todo el árbol de una {@link UnidadAdministrativa} rellenando
	 * un listado de {@link ProcedimientoLocal} asociados a las unidades un {@link HechoVital} y
	 * rellenado un listado de {@link Ficha} asociadas a las unidades, a un {@link HechoVital} y al
	 * CodigoEstandar de una {@link Seccion} (PORMAD)
	 *
	 * @param session	Sesión de hibernate.
	 * 
	 * @param unidad	Unidad administrativa sobre la que recorrer su árbol de hijos.
	 * 
	 * @param idHechoVital	Identificador del HechoVital relacionado con las fichas y procedimientos.
	 * 
	 * @param codEstandar	CodigoEstandar de una {@link Seccion} relacionada con las Fichas.
	 * 
	 * @param listaProcedimientos	Lista de {@link ProcedimientoLocal} a rellenar.
	 * 
	 * @param listaFichas	Lista de {@link Ficha} a rellenar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcFichSecHVRecur(Session session ,final UnidadAdministrativa unidad, final Long idHechoVital, final String codEstandar, 
			final List<ProcedimientoLocal> listaProcedimientos, final List<Ficha> listaFichas) throws HibernateException {
		
		
		//Navegacion por el árbol de hijos
		for ( UnidadAdministrativa hijo : unidad.getHijos() ) {
			
			Hibernate.initialize( hijo.getHijos() );
			if( hijo.getHijos() != null )				
				listarProcFichSecHVRecur( session, hijo, idHechoVital, codEstandar, listaProcedimientos, listaFichas );
			
		}

		
		//Query que retorna los procedimientos relacionados
		Query query = session.createQuery(
				"Select DISTINCT proc " +
		
				"From ProcedimientoLocal as proc, " +
				"	  HechoVitalProcedimiento as hvproc, " +
				"	  hvproc.hechoVital as hecho " +
				
				"Where proc.unidadAdministrativa.id = :idUA " +
				"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
				"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
				"	AND proc.validacion = :validacion " +
				"	AND hecho.id = :idHV " +
				"	AND hvproc in elements(proc.hechosVitalesProcedimientos) ");
		
		query.setLong( "idUA",  unidad.getId() );
		query.setLong( "idHV", idHechoVital );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		listaProcedimientos.addAll( query.list() );
		

		//Query que retorna las fichas relacionadas
		query = session.createQuery(
				"Select DISTINCT f " +
		
				"From FichaUA as fua, " +
				"	  fua.ficha as f, " +
				"	  HechoVital as hecho " +
				
				"Where fua.unidadAdministrativa.id = :idUA " +
				"	AND fua.seccion.codigoEstandard = :ceSec " +
				"	AND f.validacion = :validacion " +
				"	AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
				"	AND hecho.id = :idHV " +
				"	AND hecho in elements(f.hechosVitales) ");
		
		query.setLong( "idUA",  unidad.getId() );
		query.setLong( "idHV", idHechoVital );
		query.setString( "ceSec", codEstandar );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		listaFichas.addAll( query.list() );
		
	}
	

	/**
	 * @deprecated Usado por un método que no se usa.
	 * Rellena un listado de {@link ProcedimientoLocal} y {@link Ficha} relacionados con
	 * una {@link AdministracionRemota}, un {@link HechoVital} y el codigo
	 * estandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session	Sesión de hibernate.
	 * 
	 * @param adminRemota	Indica una administración remota.
	 * 
	 * @param idHechoVital	HechoVital relacionada con las fichas y procedimientos.
	 * 
	 * @param codEstandar	CodigoEstandar de una {@link Seccion} relacionada con las Fichas.
	 * 
	 * @param listaProcedimientos	Lista de {@link ProcedimientoLocal} a rellenar.
	 * 
	 * @param listaFichas	Lista de {@link Ficha} a rellenar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcFichSecHV(Session session ,final AdministracionRemota adminRemota, final Long idHechoVital, final String codEstandar, 
			final List<ProcedimientoLocal> listaProcedimientos, final List<Ficha> listaFichas) throws HibernateException{

		
		//Query que retorna los procedimientos relacionados
		Query query = session.createQuery(
				"Select DISTINCT proc " +
		
				"From ProcedimientoRemoto as proc, " +
				"	  HechoVitalProcedimiento as hvproc, " +
				"	  hvproc.hechoVital as hecho " +
				
				"Where proc.administracionRemota.id = :idAdmin " +
				"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
				"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
				"	AND proc.validacion = :validacion " +
				"	AND hecho.id = :idHV " +
				"	AND hvproc in elements(proc.hechosVitalesProcedimientos) ");
		
		query.setLong( "idAdmin",  adminRemota.getId() );
		query.setLong( "idHV", idHechoVital );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		listaProcedimientos.addAll( query.list() );

		
		//Query que retorna las fichas relacionadas
		query = session.createQuery(
				"Select DISTINCT f " +
		
				"From FichaUA as fua, " +
				"	  FichaRemota as f, " +
				"	  HechoVital as hecho " +
				
				"Where f.administracionRemota.id = :idAdmin " +
				"	AND fua.ficha=f " +
				"	AND fua.seccion.codigoEstandard = :ceSec " +
				"	AND f.validacion = :validacion " +
				"	AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
				"	AND hecho.id = :idHV " +
				"	AND hecho in elements(f.hechosVitales) ");
		
		query.setLong( "idAdmin",  adminRemota.getId() );
		query.setLong( "idHV", idHechoVital );
		query.setString( "ceSec", codEstandar );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		
		listaFichas.addAll(query.list());
		
	}


	/**
	 * @deprecated Usado por un método que no se usa.
	 * Método recursivo que Busca {@link ProcedimientoLocal} relacionados que están en
	 * el árbol de una {@link UnidadAdministrativa} (PORMAD)
	 *
	 * @param session	Sesión de hibernate.
	 * 
	 * @param nombre	Nombre del procedimiento.
	 * 
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param fechaInicio	Fecha de inicio del procedimiento.
	 * 
	 * @param fechaFin	Fecha de finlaización de un procedimiento.
	 * 
	 * @param unidad	Indica una unidad administrativa relacionada al procedimiento buscado.
	 * 
	 * @param listaProcedimientos	Lista de procedimientos solicitados.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void buscarProcedimientosUARecur(Session session, final String nombre, final String idioma ,final Date fechaInicio, 
			final Date fechaFin,final UnidadAdministrativa unidad, final List<ProcedimientoLocal> listaProcedimientos) throws HibernateException {
		
		//Navegación por el árbol de hijos
		for ( UnidadAdministrativa hijo : unidad.getHijos() ) {
			
			Hibernate.initialize( hijo.getHijos() );
			if ( hijo.getHijos() != null )
				buscarProcedimientosUARecur( session, nombre, idioma, fechaInicio, fechaFin, hijo, listaProcedimientos );
			
		}

		String sQuery = "";
		
		//Query que retorna los procedimientos relacionados
		if ( fechaInicio != null && fechaFin != null ) {
			
			sQuery = "Select DISTINCT proc " +
			
					"From ProcedimientoLocal as proc, " +
					"	  proc.traducciones as trad " +
					
					"Where proc.unidadAdministrativa.id = :idUA " +
					"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
					"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
					"	AND (proc.fechaPublicacion between :dataInici and :dataFi) " +
					"	AND proc.validacion = :validacion " +
					"	AND index(trad) = :idioma " +
					"	AND upper(trad.nombre) like :busqueda ";
		} else {
			
			sQuery = "Select DISTINCT proc " +
			
					"From ProcedimientoLocal as proc, " +
					"	  proc.traducciones as trad " +
					
					"Where proc.unidadAdministrativa.id = :idUA " +
					"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
					"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
					"	AND proc.validacion = :validacion " +
					"	AND index(trad) = :idioma " +
					"	AND upper(trad.nombre) like :busqueda ";
		}

		Query query = session.createQuery(sQuery);
		query.setLong( "idUA",  unidad.getId() );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		query.setString( "idioma", idioma );
		
		if ( fechaInicio != null && fechaFin != null ) {
			
			query.setParameter( "dataInici", null );
			query.setParameter( "dataFi", null );
			
		}

		query.setString( "busqueda", "%" + nombre.trim().toUpperCase() + "%" );
		listaProcedimientos.addAll( query.list() );
		
	}
	

	/**
	 * @deprecated Usado por un método que no se usa.
	 * Busca {@link ProcedimientoLocal} relacionados con la busqueda que esten en una
	 * {@link AdministracionRemota} (PORMAD)
	 *
	 * @param session	Sesión de hibernate-
	 * 
	 * @param nombre	Nombre del procedimiento a buscar.	
	 * 
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param fechaInicio	Fecha de inicio del procedimiento.
	 * 
	 * @param fechaFin	Fecha de finalización del procedimiento.
	 * 
	 * @param adminRemoto	Indica la administración remota a la que pertenece el procedimiento
	 * 
	 * @param listaProcedimientos	Listado de procedimientos a rellenar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void buscarProcedimientosUA(Session session , final String nombre, final String idioma, final Date fechaInicio, 
			final Date fechaFin, final AdministracionRemota adminRemoto, final List<ProcedimientoLocal> listaProcedimientos) throws HibernateException {
		
		String sQuery = "";
		if ( fechaInicio != null && fechaFin != null ) {
			
			sQuery = "Select DISTINCT proc " +
			
					"From ProcedimientoRemoto as proc, " +
					"	  proc.traducciones as trad " +
					
					"Where proc.administracionRemota.id = :idAdmin " +
					"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
					"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
					"	AND ( proc.fechaPublicacion between :dataInici and :dataFi ) " +
					"	AND proc.validacion = :validacion " +
					"	AND index(trad) = :idioma and upper(trad.nombre) like :busqueda ";
		} else {
			
			sQuery = "Select DISTINCT proc " +
			
					"From ProcedimientoRemoto as proc, " +
					"	  proc.traducciones as trad " +
					
					"Where proc.administracionRemota.id = :idAdmin " +
					"	AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
					"	AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) " +
					"	AND proc.validacion = :validacion " +
					"	AND index(trad) = :idioma " +
					"	AND upper(trad.nombre) like :busqueda ";
		}

		Query query = session.createQuery(sQuery);
		query.setLong( "idAdmin",  adminRemoto.getId() );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		query.setString( "idioma", idioma );
		
		if ( fechaInicio != null && fechaFin != null ) {
			
			query.setParameter( "dataInici", fechaInicio );
			query.setParameter( "dataFi", fechaFin );
			
		}

		query.setString( "busqueda", "%" + nombre.trim().toUpperCase() + "%" );
		listaProcedimientos.addAll( query.list() );
		
	}

	
	/**
	 * @deprecated No se usa
	 * Busca todos los {@link FichaLocal} que contengan la palabra de la
	 * busqueda que esten relacionados con una {@link UnidadAdministrativa}
	 * (PORMAD)
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Set<Ficha> buscarFichasUA(final String busqueda,
			final String idioma, final Date dataInici, final Date dataFi) {
		
		Set<Ficha> resultado;
		if ( busqueda != null && !"".equals( busqueda.trim() ) ) {
			
			Session session = getSession();
			
			try {
				
				resultado = new HashSet<Ficha>();
				String sQuery = "";
				
				if ( dataInici != null && dataFi != null ) {
					
					sQuery = "Select DISTINCT fic " +
					
							" From Ficha as fic, " +
							"	   fic.traducciones as trad " +
							
							" Where ( fic.fechaCaducidad is null OR fic.fechaCaducidad >= :fecha ) " +
							"	AND ( fic.fechaPublicacion is null OR fic.fechaPublicacion < :fecha ) " +
							"	AND ( fic.fechaPublicacion between :dataInici AND :dataFi ) " +
							"	AND fic.validacion = :validacion " +
							"	AND index(trad) = :idioma " +
							"	AND upper(trad.titulo) like :busqueda ";
					
				} else {
					
					sQuery = "Select DISTINCT fic " +
					
							" From Ficha as fic, " +
							"	   fic.traducciones as trad " +
							
							" Where ( fic.fechaCaducidad is null OR fic.fechaCaducidad >= :fecha ) " +
							"	AND ( fic.fechaPublicacion is null OR fic.fechaPublicacion < :fecha ) " +
							"	AND fic.validacion = :validacion " +
							"	AND index(trad) = :idioma " +
							"	AND upper(trad.titulo) like :busqueda ";
					
				}

				Query query = session.createQuery(sQuery);
				query.setParameter( "validacion", Validacion.PUBLICA );
				query.setParameter( "fecha", DateUtils.today() );
				query.setString( "idioma", idioma );
				
				if ( dataInici != null && dataFi != null ) {
					
					query.setParameter( "dataInici", dataInici );
					query.setParameter( "dataFi", dataFi );
					
				}

				query.setString( "busqueda", "%" + busqueda.trim().toUpperCase() + "%" );
				resultado.addAll( query.list() );

			} catch (HibernateException he) {
				
				throw new EJBException(he);
				
			} finally {
				
				close(session);
				
			}
			
		} else {
			
			resultado = Collections.emptySet();
			
		}

		return resultado;
		
	}
	

	/**
	 * MODIFICACIONS MARILEN PER ADAPTAR EL PORINF FA2
	 */

	/*********FICHASMATERIA*********/

	/**
	 * @deprecated	No se usa
	 * Rellena un listado {@link Ficha} relacionados con
	 * una {@link AdministracionRemota}, una {@link Materia} y el codigo
	 * estandar de una {@link Seccion}  (PORINF)
	 *
	 * @param session
	 * @param admin AdministracionRemota sobre la que buscar
	 * @param idMat,  Materia relacionada con las fichas y procedimientos
	 * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	 * @param fichas, Lista de {@link Ficha} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarFichSecMat(Session session ,final AdministracionRemota admin, final Long idMat, final String ceSec, final List<Ficha> fichas,boolean caducados) throws HibernateException{

		//Query que retorna las fichas relacionadas
		Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, Materia as mat " +
				"where f.administracionRemota.id=:idAdmin AND fua.ficha = f " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "")+
				"AND mat.id=:idMat AND mat in elements(f.materias) ");
		query.setLong("idAdmin",  admin.getId());
		query.setLong("idMat", idMat);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
		if(!caducados){query.setParameter("fecha", DateUtils.today());}
		fichas.addAll(query.list());
	}


	/**
	 * Devuelve un {@link List} de {@link FichaUA}, relacionadas con una {@link UnidadAdministrativa} y una {@link Seccion}.
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idUA	Identificador de la unidad administrativa solicitada.
	 * 
	 * @param	idSeccion	Identificador de la sección solicitada.
	 * 
	 * @return	Devuelve <code>List<FichaResumenUA></code> relacionadas con una unidad administrativa y una sección.
	 */
	public List<FichaResumenUA> listarFichasSeccionUA(final Long idUA, final Long idSeccion) {

		List<FichaResumenUA> fichas = new ArrayList<FichaResumenUA>();

		if ( idUA != null && idSeccion != null ) {

			Session session = getSession();
			
			try {
				
				Query query = session.createQuery(
						
						"FROM FichaResumenUA AS fichaUA " +
								
						"WHERE fichaUA.idUa = :idUA " +
						
						"	AND fichaUA.idSeccio = :idSeccion " +
						
						"ORDER BY fichaUA.orden");
				
				query.setParameter( "idUA", idUA );
				query.setParameter( "idSeccion", idSeccion );
				
				fichas = (List<FichaResumenUA>) query.list();

			} catch (HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		} else {

			return Collections.emptyList();

		}

		return fichas;

	}


	/**
	 * @deprecated	No se usa
	 * Devuelve un {@link List} de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 *
	 * @param idUA
	 * @param idMat
	 * @param ceSec
	 * @return
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<Ficha> listarFichSecMat(final Long idUA, final Long idMat, final String ceSec,boolean caducados){

		//if (!userIsInfo() && caducados) throws SecurityException("Errror");

		List<Ficha> fichas = new ArrayList<Ficha>();
		if(idUA!=null && idMat!=null){
			Session session = getSession();
			try {
				UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
				if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarFichSecMat(session, unidadRemota.getAdministracionRemota(), idMat, ceSec, fichas,caducados);
				}else{
					listarFichSecMatRecur(session, unidad, idMat, ceSec, fichas,caducados);
				}
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}else{
			return Collections.emptyList();
		}

		return fichas;
	}


	/**
	 * @deprecated Usado únicamente por un método que no se usa.
	 * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
	 * listado de {@link Ficha} asociadas a las unidades, a una {@link Materia} y al
	 * CodigoEstandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session
	 * @param unidad sobre la que recorrer su arbol de hijos
	 * @param idMat,  Materia relacionada con las fichas y procedimientos
	 * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	 * @param fichas, Lista de {@link Ficha} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarFichSecMatRecur(Session session ,final UnidadAdministrativa unidad, final Long idMat, final String ceSec, final List<Ficha> fichas,boolean caducados) throws HibernateException{
		//Navegacion por el arbol de hijos
		for (UnidadAdministrativa hijo : unidad.getHijos()) {
			Hibernate.initialize(hijo.getHijos());
			if(hijo.getHijos()!=null){
				listarFichSecMatRecur(session, hijo, idMat, ceSec, fichas,caducados);
			}
		}


		//Query que retorna las fichas relacionadas
		Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, fua.ficha as f, Materia as mat " +
				"where fua.unidadAdministrativa.id=:idUA " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
				"AND mat.id=:idMat AND mat in elements(f.materias) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idMat", idMat);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
		if(!caducados){query.setParameter("fecha", DateUtils.today());}
		fichas.addAll(query.list());
	}


	/*********PROCEDIMIENTOSMATERIA*********/


	/**
	 *  @deprecated	No se usa
	 * Devuelve un {@link List} de {@link ProcedimientoLocal}
	 * Relacionados con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 *
	 * @param idUA
	 * @param idMat
	 * @return
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> listarProcMat(final Long idUA, final Long idMat, final String[] codMat, boolean include,boolean caducados){
		List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();
		if(idUA!=null && idMat!=null){
			Session session = getSession();
			try {
				UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
				if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcMat(session, unidadRemota.getAdministracionRemota(), idMat, codMat, procs,include,caducados);
				}else{
					listarProcMatRecur(session, unidad, idMat, codMat, procs, include,caducados);
				}
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}else{
			return Collections.emptyList();
		}

		return procs;
	}

	
	/**
	 * @deprecated Usado por un método que no se usa.
	 * Método recursivo que recorre todo el árbol de una {@link UnidadAdministrativa} rellenando
	 * un listado de {@link ProcedimientoLocal} asociados a las unidades una {@link Materia} y al
	 * CodigoEstandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session	Sesión de hibernate.
	 * 
	 * @param unidad 	Indica la unidad administrativa sobre la que recorrer su árbol de hijos.
	 * 
	 * @param idMateria	Identificador de la materia relacionada con las fichas y procedimientos.
	 * 
	 * @param listaProcedimientos	Lista de {@link ProcedimientoLocal} a rellenar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcMatRecur(Session session ,final UnidadAdministrativa unidad, final Long idMateria, final String[] codMat, 
			final List<ProcedimientoLocal> listaProcedimientos, boolean include, boolean caducados) throws HibernateException {
		
		//Navegación por el árbol de hijos
		for ( UnidadAdministrativa hijo : unidad.getHijos() ) {
			
			Hibernate.initialize( hijo.getHijos() );
			if ( hijo.getHijos() != null ) {
				
				listarProcMatRecur( session, hijo, idMateria, codMat, listaProcedimientos, include, caducados );
				
			}
			
		}
		
		StringBuilder consulta = new StringBuilder("Select DISTINCT proc");
		
		consulta.append(" From ProcedimientoLocal as proc, Materia as mat ");
		consulta.append(" Where proc.unidadAdministrativa.id = :idUA ");
		
		if ( !caducados )
			consulta.append(" AND ( proc.fechaCaducidad is null OR proc.fechaCaducidad >= :fecha ) ");
		
		consulta.append(" AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) ");
		consulta.append(" AND proc.validacion = :validacion ");
		consulta.append(" AND mat.id = :idMat AND mat in elements(proc.materias) ");
		consulta.append( ( include ? "AND exists" : "AND not exists") );
		consulta.append(" ( select m from proc.materias m where m.codigoEstandar in (:codigos) ) ");
			
			

		//Query que retorna los procedimientos relacionados
		Query query = session.createQuery( consulta.toString() );
		query.setLong( "idUA",  unidad.getId() );
		query.setLong( "idMat", idMateria );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		query.setParameterList( "codigos", codMat );
		listaProcedimientos.addAll( query.list() );		
		
	}


	/**
	 * @deprecated Usado por un método que no se usa.
	 * Rellena un listado de {@link ProcedimientoLocal} relacionados con
	 * una {@link AdministracionRemota}, una {@link Materia} y el codigo
	 * estandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session	Sesión de hibernate.
	 * 
	 * @param admin	Indica la AdministracionRemota sobre la que buscar.
	 * 
	 * @param idMateria	Identificador de la materia relacionada con las fichas y procedimientos.

	 * @param listaProcedimientos	Lista de {@link ProcedimientoLocal} a rellenar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcMat(Session session, final AdministracionRemota admin, final Long idMat, final String[] codMat, 
			final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException {

		StringBuilder consulta = new StringBuilder(" Select DISTINCT proc ");
		consulta.append(" From ProcedimientoRemoto as proc, Materia as mat ");
		consulta.append(" Where proc.administracionRemota.id = :idAdmin ");
		
		if ( !caducados )
			consulta.append(" AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) ");
		
		consulta.append(" AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) "); 
		consulta.append(" AND proc.validacion = :validacion ");
		consulta.append( ( include ? " AND exists" : "AND not exists " ) );
		consulta.append(" ( select m from proc.materias m where m.codigoEstandar in (:codigos) ) ");
		
		
		Query query = session.createQuery( consulta.toString() );
		query.setLong( "idAdmin",  admin.getId() );
		query.setLong( "idMat", idMat );
		query.setParameter( "validacion", Validacion.PUBLICA );
		query.setParameter( "fecha", DateUtils.today() );
		query.setParameterList( "codigos", codMat );
		
		procs.addAll( query.list() );
		
	}


	/****FICHASHECHOS VITALES***/


	/**
	 * @deprecated	No se usa
	 * Devuelve un {@link List}  de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * un {@link HechoVital} y una {@link Seccion}.
	 *
	 * @param idUA
	 * @param idHV
	 * @param ceSec
	 * @return
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<Ficha> listarFichSecHV(final Long idUA, final Long idHV, final String ceSec,boolean caducados){

		if(idUA!=null && idHV!=null){
			Session session = getSession();
			try {

				List<Ficha> fichas = new ArrayList<Ficha>();

				UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
				if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarFichSecHV(session, unidadRemota.getAdministracionRemota(), idHV, ceSec, fichas,caducados);
				}else{
					listarFichSecHVRecur(session, unidad, idHV, ceSec, fichas,caducados);
				}

				return fichas;
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}else{
			return Collections.emptyList();
		}

	}

	
	/**
	 * @deprecated Usado por un método que no se usa.
	 * Rellena un listado {@link Ficha} relacionados con
	 * una {@link AdministracionRemota}, un {@link HechoVital} y el codigo
	 * estandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session	Sesión de hibernate.
	 * 
	 * @param admin AdministracionRemota
	 * @param idHV,  HechoVital relacionada con las fichas y procedimientos
	 * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	 * @param fichas, Lista de {@link Ficha} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarFichSecHV(Session session ,final AdministracionRemota admin, final Long idHV, final String ceSec, final List<Ficha> fichas,boolean caducados) throws HibernateException{


		
		//Query que retorna las fichas relacionadas
		Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, HechoVital as hecho " +
				"where f.administracionRemota.id=:idAdmin AND fua.ficha=f " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
				"AND hecho.id=:idHV AND hecho in elements(f.hechosVitales) ");
		
		query.setLong( "idAdmin",  admin.getId() );
		query.setLong( "idHV", idHV );
		query.setString( "ceSec", ceSec );
		query.setParameter( "validacion", Validacion.PUBLICA );
		if (!caducados) 
			query.setParameter( "fecha", DateUtils.today() );
		
		fichas.addAll( query.list() );
		
	}


	/**
	 * @deprecated Usado por un método que no se usa.
	 * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
	 * un listado de {@link Ficha} asociadas a las unidades, a un {@link HechoVital} y al
	 * CodigoEstandar de una {@link Seccion} (PORMAD)
	 *
	 * @param session
	 * @param unidad sobre la que recorrer su arbol de hijos
	 * @param idHV,  HechoVital relacionada con las fichas y procedimientos
	 * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	 * @param fichas, Lista de {@link Ficha} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarFichSecHVRecur(Session session ,final UnidadAdministrativa unidad, final Long idHV, final String ceSec,  final List<Ficha> fichas,boolean caducados) throws HibernateException{
		//Navegacion por el arbol de hijos
		for (UnidadAdministrativa hijo : unidad.getHijos()) {
			Hibernate.initialize(hijo.getHijos());
			if(hijo.getHijos()!=null){
				listarFichSecHVRecur(session, hijo, idHV, ceSec, fichas,caducados);
			}
		}



		//Query que retorna las fichas relacionadas
		Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, fua.ficha as f, HechoVital as hecho " +
				"where fua.unidadAdministrativa.id=:idUA " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
				"AND hecho.id=:idHV AND hecho in elements(f.hechosVitales) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idHV", idHV);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
		if(!caducados){query.setParameter("fecha", DateUtils.today());}
		fichas.addAll(query.list());

	}


	/***PROCEDIMIENTOSHECHOSVITALES ***/

	/**
	 * @deprecated	No se usa
	 * Devuelve un {@link List} de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link HechoVital} y una {@link Seccion}.
	 *

	 *
	 * @param idUA
	 * @param idHV
	 * @return
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> listarProcHV(final Long idUA, final Long idHV, final String[] codMat, boolean include,boolean caducados){

		if(idUA!=null && idHV!=null){
			Session session = getSession();
			try {
				List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();


				UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
				if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcHV(session, unidadRemota.getAdministracionRemota(), idHV, codMat, procs, include,caducados);
				}else{
					listarProcHVRecur(session, unidad, idHV, codMat, procs, include,caducados);
				}

				return procs;
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}else{
			return Collections.emptyList();
		}

	}

	/**
	 * @deprecated	Usado por un método que no se usa.
	 * Rellena un listado de {@link ProcedimientoLocal} relacionados con
	 * una {@link AdministracionRemota}, un {@link HechoVital} y el codigo
	 * estandar de una {@link Seccion}  (PORMAD)
	 *
	 * @param session
	 * @param admin AdministracionRemota
	 * @param idHV,  HechoVital relacionada con las fichas y procedimientos
	 * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcHV(Session session ,final AdministracionRemota admin, final Long idHV, final String[] codMat, final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException{

		//Query que retorna los procedimientos relacionados
		Query query = session.createQuery("Select DISTINCT proc From ProcedimientoRemoto as proc, HechoVitalProcedimiento as hvproc, hvproc.hechoVital as hecho " +
				"where proc.administracionRemota.id=:idAdmin " +
				(!caducados ? "AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " : "") +
				"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
				"AND hecho.id=:idHV AND hvproc in elements(proc.hechosVitalesProcedimientos) " +
				" and " + (include?"exists":"not exists") + " (select m from proc.materias m where m.codigoEstandar in (:codigos)) ");
		query.setLong("idAdmin",  admin.getId());
		query.setLong("idHV", idHV);
		query.setParameter("validacion", Validacion.PUBLICA);
		query.setParameter("fecha", DateUtils.today());
		query.setParameterList("codigos", codMat);
		procs.addAll(query.list());

	}


	/**
	 * @deprecated Usado por un método que no se usa.
	 * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
	 * un listado de {@link ProcedimientoLocal} asociados a las unidades un {@link HechoVital} a un {@link HechoVital} y al
	 * CodigoEstandar de una {@link Seccion} (PORMAD)
	 *
	 * @param session
	 * @param unidad sobre la que recorrer su arbol de hijos
	 * @param idHV,  HechoVital relacionada con las fichas y procedimientos
	 * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void listarProcHVRecur(Session session ,final UnidadAdministrativa unidad, final Long idHV, final String[] codMat, final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException{
		//Navegacion por el arbol de hijos
		for (UnidadAdministrativa hijo : unidad.getHijos()) {
			Hibernate.initialize(hijo.getHijos());
			if(hijo.getHijos()!=null){
				listarProcHVRecur(session, hijo, idHV,codMat ,procs, include,caducados);
			}
		}

		//Query que retorna los procedimientos relacionados
		Query query = session.createQuery("Select DISTINCT proc From ProcedimientoLocal as proc, HechoVitalProcedimiento as hvproc, hvproc.hechoVital as hecho " +
				"where proc.unidadAdministrativa.id=:idUA " +
				(!caducados ? "AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " : "") +
				"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
				"AND hecho.id=:idHV AND hvproc in elements(proc.hechosVitalesProcedimientos) " +
				" and " + (include?"exists":"not exists") + " (select m from proc.materias m where m.codigoEstandar in (:codigos)) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idHV", idHV);
		query.setParameter("validacion", Validacion.PUBLICA);
		query.setParameter("fecha", DateUtils.today());
		query.setParameterList("codigos", codMat);
		procs.addAll(query.list());
	}


	/**
	 * @deprecated	No se usa.
	 * Compone la miga de pan de la unidad administrativa
	 * @param idua
	 * @param idioma 
	 * @return String 
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public StringBuffer getUaMolla(Long idua, String _idioma) {

		StringBuffer tmp = new StringBuffer(" ");
		try {
			UnidadAdministrativaDelegate uadel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate();
			UnidadAdministrativa uniadm=uadel.obtenerUnidadAdministrativa(idua);
			while ((uniadm!=null) && (uniadm.getId().longValue()!=1)) {
				StringBuffer ua_sbuf = new StringBuffer( ((TraduccionUA)uniadm.getTraduccion(_idioma)).getNombre() );
				Cadenas.initAllTab(ua_sbuf); //primera letra en mayusculas
				String ua_texto=Cadenas.initTab(ua_sbuf.toString()); // articulos a minusculas
				String ua_url="/govern/organigrama/area.do?lang=" + _idioma + "&coduo="+uniadm.getId();
				tmp.insert(0, "<li>&gt; <a href=\"" + ua_url + "\">" + ua_texto + "</a>" + "</li>" );
				uniadm = uniadm.getPadre();
			}
			if (tmp.length()>3) tmp.delete(tmp.length()-3, tmp.length());
			else tmp.append("&nbsp;");
		} catch (DelegateException e) {
			tmp = new StringBuffer("&nbsp;");			
		} 
		return tmp;
	}		


	/**
	 * Compone la miga de pan de la unidad administrativa para el sacback2
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idUA	Identificador de la unidad administrativa.
	 * 
	 * @param idioma Indica el idioma en que se realiza la búsqueda.
	 * 
	 * @param url	Indica la url de la miga de pan.
	 * 
	 * @param uaIdPlaceholder	??
	 * 
	 * @return Devuelve una cadena de texto con la miga de pan.
	 */
	public StringBuffer getUaMollaBack2(Long idUA, String idioma, String url, String uaIdPlaceholder) {

		StringBuffer migaPan = new StringBuffer(" ");

		try {

			UnidadAdministrativa uniadm = obtenerUnidadAdministrativa(idUA);
			boolean tieneAcceso = getAccesoManager().tieneAccesoUnidad( uniadm.getId(), false );

			while ( uniadm != null && tieneAcceso ) {

				StringBuffer uaSbuf = new StringBuffer( ( (TraduccionUA) uniadm.getTraduccion(idioma) ).getNombre() );
				Cadenas.initAllTab(uaSbuf); //primera letra en mayusculas

				String uaTexto = Cadenas.initTab( uaSbuf.toString() ); // articulos a minusculas
				String uaURL  = url.replaceFirst( uaIdPlaceholder, uniadm.getId().toString() );

				StringBuffer migaPanUA = new StringBuffer("<li class=\"ua");

				if ( idUA.equals( uniadm.getId() ) )
					migaPanUA.append(" seleccionat");

				// Obtenemos todos los padres de la jerarquía.
				List padres = listarPadresUnidadAdministrativa( uniadm.getId() );
				String padreUAId = "";

				if ( padres.size() > 1 ) {
					// El penúltimo elemento es el padre directo de la UA
					UnidadAdministrativa padre = (UnidadAdministrativa) padres.get( padres.size() - 2 );
					padreUAId = Long.toString( padre.getId() );
				}

				migaPanUA.append("\" data-clave_ua_padre=\"");
				migaPanUA.append(padreUAId);
				migaPanUA.append("\"><div><a class=\"ua\" href=\"");
				migaPanUA.append(uaURL);
				migaPanUA.append("\">");
				migaPanUA.append(uaTexto);
				migaPanUA.append("</a><a class=\"desplegar\" href=\"javascript:void(0);\"></a></div></li>");

				migaPan.insert( 0, migaPanUA );

				uniadm = uniadm.getPadre();
				if ( uniadm != null )
					tieneAcceso = getAccesoManager().tieneAccesoUnidad( uniadm.getId(), false );

			}

		} catch (EJBException e) {

			migaPan = new StringBuffer("&nbsp;");

		}

		return migaPan;

	}	
	

	/**
	 * Devuelve las unidades administrativas que coinciden con los criterios  de búsqueda
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */	
	public ResultadoBusqueda buscadorUnidadesAdministrativas(Map<String, Object> parametros, Map<String, String> traduccion, Long id, String idioma, boolean uaFilles, boolean uaMeves, Long materia, String pagina, String resultats) {
		
		//TODO 26/08/2013: Refactorizar.
		Session session = getSession();
		List<UnidadAdministrativa> listaUnidadesAdministrativas = new ArrayList<UnidadAdministrativa>();

		try {

			if ( !userIsOper() )
				parametros.put("validacion", Validacion.PUBLICA);

			List params = new ArrayList();
			String i18nQuery = "";

			if ( traduccion.get("idioma") != null ) {	
				
				i18nQuery = populateQuery(parametros, traduccion, params);
				
			} else {
				
				String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if ( paramsQuery.length() == 0 ) {
					
					i18nQuery += "";
					
				} else {
					
					i18nQuery += paramsQuery + " and ";
					
				}	
				
				i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ")";
				
			}	

			String select   = "select new UnidadAdministrativa( unidad.id, unidad.codigoEstandar, trad.nombre, unidad.orden, index(trad) ) ";
			String from     = "from UnidadAdministrativa as unidad, unidad.traducciones trad ";
			String where   = "where " + i18nQuery + " and unidad.padre = " + id + " ";			
			String orderBy = "order by unidad.orden";
			
			if ( userIsSystem() ) {
				
				if ( id == null ) {
					
					where =  "where " + i18nQuery + (uaFilles ? "" : " and unidad.padre is null ");
					
				} else if ( uaMeves && uaFilles ) {
					
					where   = "where " + i18nQuery;
					
				} else if (uaFilles) {
					
					where = " where " + i18nQuery + " and unidad.padre in (" + cargarArbolUnidadId(id).toString().replaceAll("\\[|\\]", "") + ")";
					
				}
				
			} else {
				
				String cadenaFiltro = obtenerCadenaFiltroUA( id, uaFilles, uaMeves );
				if ( StringUtils.isEmpty(cadenaFiltro) )
					cadenaFiltro = EMPTY_ID;
				
				where = where.replaceFirst("and unidad.padre = " + id, " ");
				where += "and (unidad.id in(" + cadenaFiltro + ") " +
						( id != null ? "or unidad.padre = " + id : "" )  + ") " +
						( id != null ? "and unidad.id != " + id + " " : "");
			}
			
			if ( materia != null ) {
				where += " and unidad.id in (select uam.unidad.id " +
						"from UnidadMateria as uam " +
						"where uam.materia.id = " + materia + ") ";
			}
			
			Query query = session.createQuery( select + from + where + orderBy);

			// Asignar parámetros
			for ( int i = 0 ; i < params.size() ; i++ ) {
				
				String o = (String) params.get(i);
				query.setString(i, o);
				
			}			

			int resultadosMax = new Integer(resultats).intValue();
			int primerResultado = new Integer(pagina).intValue() * resultadosMax;

			listaUnidadesAdministrativas = castList( UnidadAdministrativa.class, query.list() );

			if ( resultadosMax != RESULTATS_CERCA_TOTS ) {
				
				query.setFirstResult(primerResultado);
				query.setMaxResults(resultadosMax);
				
			}				

			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
			resultadoBusqueda.setListaResultados( listaUnidadesAdministrativas );
			resultadoBusqueda.setTotalResultados( listaUnidadesAdministrativas.size() );			

			return resultadoBusqueda;

		} catch (DelegateException ex) {
			
			log.warn( "[obtenerCadenaFiltroUA: " + id + "] No se ha podido llamar al método. " + ex.getMessage() );
			
			throw new EJBException(ex);
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}	

	
	/**
	 * Asigna a una unidad administratival un nuevo orden y reordena los elementos afectados.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @param ordenNuevo	Indica el nuevo orden para la unidad administrativa.
	 * 
	 * @param ordenAnterior	Indica el orden anterior de la unidad administrativa.
	 * 
	 * @param idPadre	Identificador de la unidad administrativa padre.
	 */	
	public void reordenar( Long id, Integer ordenNuevo, Integer ordenAnterior, Long idPadre ) {

		Session session = getSession();
		List<UnidadAdministrativa> listaUAs = new ArrayList<UnidadAdministrativa>();

		try {
			
			StringBuilder consulta = new StringBuilder("select ua from UnidadAdministrativa as ua ");
			
			if ( idPadre != null ) {
				
				consulta.append(" where ua.padre = :idPadre ");
				
			} else {
				
				consulta.append(" where ua.padre is null ");
				
			}

			consulta.append(" order by ua.orden asc ");
			
			Query query = session.createQuery( consulta.toString() );
			
			if ( idPadre != null )
				query.setParameter("idPadre", idPadre);

			listaUAs = castList( UnidadAdministrativa.class, query.list() );

			// Modificar sólo los elementos entre la posición del elemento que cambia 
			// de orden y su nueva posición 
			int ordenMayor = ordenNuevo > ordenAnterior ? ordenNuevo : ordenAnterior;
			int ordenMenor = ordenMayor == ordenNuevo ? ordenAnterior : ordenNuevo;

			// Si el nuevo orden es mayor que el anterior, desplazar los elementos 
			// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
			int incremento = ordenNuevo > ordenAnterior ? -1 : 1;        			

			for ( UnidadAdministrativa unidadAdministrativa: listaUAs ) {        		    

				long orden = unidadAdministrativa.getOrden();

				if ( orden >= ordenMenor && orden <= ordenMayor ) {

					if ( id.equals(unidadAdministrativa.getId() ) ) {
						
						unidadAdministrativa.setOrden( ordenNuevo );
						
					} else {
						
						unidadAdministrativa.setOrden( orden + incremento );
						
					}   
					
				}

				// Revisamos si tiene edificios y en caso contrario lo limpiamos
				if ( unidadAdministrativa.getEdificios().size() == 0 ) 
					unidadAdministrativa.setEdificios(null);
				
				// Revisamos si tiene materias y en caso contrario lo limpiamos
				if ( unidadAdministrativa.getUnidadesMaterias().size() == 0 ) 
					unidadAdministrativa.setUnidadesMaterias(null);

				// Actualizar todo para asegurar que no hay duplicados ni huecos
				session.saveOrUpdate(unidadAdministrativa);
				
			}

			session.flush();

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}    	    
		
	}

	/**
	 * Método que devuelve una cadena csv de ids de unidades administrativas según los 
	 * parámetros pasados.
	 * 	 
	 * @param idUA	Identificador de la unidada administrativa.
	 * @param uaHijas	Indica si la unidad administrativa tiene unidades hijas.
	 * @param uaPropias
	 * @param session
	 * @return
	 * @throws HibernateException
	 * @throws DelegateException
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public String obtenerCadenaFiltroUA(Long idUA, boolean uaHijas, boolean uaPropias)	throws DelegateException {

		Set<Long> uas = new HashSet<Long>();
		Set<Long> uasIds = new HashSet<Long>();

		if ( idUA != null )
			uas.add(idUA);

		if ( uaPropias )
			uas.addAll( getIdsUnidadesAdministrativasUsuario( ctx.getCallerPrincipal().getName() ) );  

		if ( uaHijas ) {
			
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

			for ( Long uaActual : uas ) {
				
				uasIds.add( uaActual );
				List<Long> idsDescendientes = castList( Long.class, uaDelegate.cargarArbolUnidadId( uaActual ) ) ;
				uasIds.addAll( idsDescendientes );
				
			}

		} else {

			for ( Long uaActual : uas )
				uasIds.add( uaActual );
			
		}

		StringBuilder consultaUA = new StringBuilder();
		String separador = "";

		for ( Long uaId : uasIds ) {
			
			consultaUA.append(separador).append(uaId);
			separador = ", ";
			
		}

		return consultaUA.toString();
		
	}	


	/**
	 * Se encarga de actualizar las fichas de una sección relacionada con una UA.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idUA	Identificador de la unidad administrativa.
	 * 
	 * @param idSeccion Identificador de la sección.
	 * 
	 * @param listaIdFichas	Lista que contiene los identificadores de las fichas.
	 * 
	 * @throws EJBException
	 */
	public void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<Long> listaIdFichas) {

		Session session = getSession();

		try {

			// Primero borramos las relaciones anteriores.
			StringBuilder consulta = new StringBuilder("FROM FichaResumenUA AS fichaUA ");
			consulta.append("WHERE fichaUA.idUa = :idUA AND fichaUA.idSeccio = :idSeccion");

			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idUA", idUA);
			query.setParameter("idSeccion", idSeccion);
			
			List<FichaResumenUA> listaFichasUA = query.list();
			for ( FichaResumenUA fua : listaFichasUA ) {  

				FichaResumen ficha = (FichaResumen) session.load( FichaResumen.class, fua.getFicha().getId() );
				ficha.removeFichaUA(fua);

				session.delete(fua);

			}

			session.flush();

			// Creamos nuevas fichas UA/Sección y actualizamos orden a medida que se crean.
			int orden = 5;

			for ( Long idFicha : listaIdFichas ) {

				FichaResumen ficha = (FichaResumen) session.load( FichaResumen.class, idFicha );

				// Obtenemos ficha para actualizar orden, ya que el método anterior no nos deja especificarlo.
				FichaResumenUA fichaUA = new FichaResumenUA();
				fichaUA.setIdUa(idUA);	
				fichaUA.setIdSeccio(idSeccion);
				fichaUA.setFicha(ficha);
				fichaUA.setOrden(orden);

				session.saveOrUpdate(fichaUA);

				orden = orden + 5;

			}

			session.flush();

		} catch (HibernateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

	}


	/**
	 * @deprecated No se usa.
	 * */
	private String initTab(String texte) {

		String[][] canvis = new String[][] { {" I "  , " i "},
				{" O "  , " o "},
				{" De " , " de "},
				{" Del ", " del "},
				{" D'"  , " d'"},
				{" A "  , " a "},
				{" En " , " en "},
				{" El " , " el "},
				{" L'"  , " l'"},
				{" La " , " la "},
				{" Als ", " als "},
				{" Els ", " els "},
				{" Les ", " les "},
				{" Al " , " al "},
				{" Amb ", " amb "},
				{" Que ", " que "},
				{" Per ", " per "},
				{"l.L"  , "l�l"},
				{"l�L"  , "l�l"}
		};

		// evitam nullPointerExceptions
		if (texte == null) return null;

		StringBuffer buf = new StringBuffer(texte);
		initAllTab(buf);
		String original = buf.toString();
		for (int i=0; i < canvis.length; i++) {
			replace(original, buf, canvis[i][0], canvis[i][1] );
		}

		return buf.toString();
	}        		

	
	/**
	 * @deprecated Utiliza por un método que no se usa.
	 * */
	private void initAllTab (StringBuffer texte) {

		final int LONG = texte.length();
		boolean primer = true;
		char c;

		for (int i=0; i<LONG; i++) {
			c = texte.charAt(i);
			if ( c == ' ' || c == '.' || c == '\'' || c == '"' ) {
				primer = true;
			} else {
				if (primer) {
					texte.setCharAt(i,Character.toUpperCase(c));
					primer = false;
				} else {
					texte.setCharAt(i,Character.toLowerCase(c));
				}
			}
		}	      
	} 	

	
	/**
	 * @deprecated Usado por un método que no se usa.
	 * */
	private void replace (String original, StringBuffer texte, String patro, String canvi) {

		final int LONG_CANVI = canvi.length();

		if ( patro.length() != LONG_CANVI ) {
			throw new RuntimeException ("els patrons de canvis a Format han de tenir la mateixa llargada!");
		}

		// per cada ocurrencia del canvi feim el canvi en el texte original
		for ( int index = original.indexOf(patro); index != -1; index = original.indexOf(patro,index+LONG_CANVI)) {
			texte.replace( index, index + LONG_CANVI, canvi);
		}
	}	

	
	/**
	 *  Obtiene una lista de identificadores de las unidades admnistrativas que pertenecen a un usuario.
	 * 
	 * @param nombreUsuario	Cadena de texto que indica el nombre del usuario.
	 * 
	 * @return	Devuelve <code>List<Long></code> de identificadores de unidades administrativas.
	 * */
	private List<Long> getIdsUnidadesAdministrativasUsuario(String nombreUsuario) {

		Session session = getSession();

		try {
			
			StringBuilder consulta = new StringBuilder("select ua.id ");
			consulta.append(" from UnidadAdministrativa as ua, ua.usuarios as uaUsu ");
			consulta.append(" where uaUsu.username = :nombreUsuario ");
			
			Query query = session.createQuery( consulta.toString() );
			query.setParameter( "nombreUsuario", nombreUsuario );

			return query.list(); 

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}		
		
	}

}