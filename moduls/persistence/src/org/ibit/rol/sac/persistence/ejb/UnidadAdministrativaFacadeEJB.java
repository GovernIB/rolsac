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
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param unidad	Indica la unidad administrativa a actualizar.
	 * @param idPadre	Identificador de la unidad administrativa.
	 */
	public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad, Long idPadre)
	{
		Session session;
		Long idPadreAntigua = (unidad.getPadre() == null ? null : unidad.getPadre().getId());
		boolean newIsNull = (idPadre == null);
		boolean oldIsNull = (idPadreAntigua == null);
		
		if (newIsNull && !userIsSystem()) {
			throw new SecurityException("Solo el usuario de sistema puede crear raices.");
		}
		
		boolean change = (newIsNull ? !oldIsNull : !idPadre.equals(idPadreAntigua));
		if (change && !oldIsNull && !newIsNull) {
			if (!getAccesoManager().tieneAccesoMoverOrganigrama(idPadreAntigua, idPadre)) {
				throw new SecurityException("No tiene acceso al nodo superior anterior o al actual.");
			}
		}
		
		if (!getAccesoManager().tieneAccesoUnidad( unidad.getId(), true )) {
			throw new SecurityException("No tiene acceso a la unidad");
		}
		
		session = getSession();
		try {
			session.update(unidad);
			if (change) {
				if (!oldIsNull) {
					UnidadAdministrativa oldPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idPadreAntigua);
					oldPadre.removeHijo(unidad);
				}
				if (!newIsNull) {
					UnidadAdministrativa newPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idPadre);
					newPadre.addHijo(unidad);
				}
				if (newIsNull || oldIsNull) {
					session.flush();
					Query query = session.getNamedQuery("unidades.root");
					List<UnidadAdministrativa> lista = castList(UnidadAdministrativa.class, query.list());
					for (int i = 0; i < lista.size(); i++) {
						UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
						uni.setOrden(i);
					}
				}
			}
			addOperacion(session, unidad, Auditoria.MODIFICAR);
			session.flush();
			Actualizador.actualizar(unidad);
			
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
	 * Lista las unidades Administrativas raiz de un usuario.
	 * 
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