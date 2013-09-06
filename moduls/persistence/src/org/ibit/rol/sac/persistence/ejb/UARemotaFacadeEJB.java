package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EdificioRemoto;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoUA;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.RemotoUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

/**
 * SessionBean para mantener y consultar Fichas Remotas (PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/UARemotaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.UARemotaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UARemotaFacadeEJB extends HibernateEJB {

	/**
	 * Obtiene referéncia al ejb de control de Acceso.
	 * 
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();

	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza una UnidadAdministrativa Remota
	 * Si el parámetro {@link AdministracionRemota} es null recoje la {@link AdministracionRemota}
	 * del usuario que ha iniciado la sesión
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"

	 * @param idRemoto	Identificador de la unidad administrativa.
	 * 
	 * @param uaRemota	Indica la unidad administrativa a guardar.
	 * 
	 * @param idPadre	Identificador de la unidad administrativa padre.
	 * 
	 * @param codEstandarTratamiento	Código estándar del tratamiento.
	 * 
	 * @param codEstandarMateria	Código estándar de las materias.
	 * 
	 * @return Devuelve el identificador de la unidad administrativa guardada.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarUARemota(final String idRemoto, UnidadAdministrativaRemota uaRemota, Long idPadre , String codEstandarTratamiento ,String[] codEstandarMateria) {

		final Session session = getSession();

		try {

			AdministracionRemota adminRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( adminRemota == null )
				throw new EJBException("No existe ninguna Administración Remota asociada");

			uaRemota.setAdministracionRemota(adminRemota);

			return grabarUARemota( session, uaRemota, idPadre, codEstandarTratamiento, codEstandarMateria );

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza una UnidadAdministrativa Remota
	 * Si la variable {@link AdministracionRemota} es null recoje la {@link AdministracionRemota}
	 * del usuario que ha iniciado la sesion
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param uaRemota	Indica la unidad administrativa a guardar.
	 * 
	 * @param idPadre	Identificador de la unidad administrativa padre.
	 * 
	 * @param codEstandarTratamiento	Código estándar del tratamiento.
	 * 
	 * @param codEstandarMateria	Código estándar de las materias.
	 * 
	 * @return	Devuelve el identificador de la unidad administrativa guardada.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarUARemota(UnidadAdministrativaRemota uaRemota, Long idPadre, String codEstandarTratamiento, String[] codEstandarMateria) {

		final Session session = getSession();
		try {

			AdministracionRemota adminRemota = null;

			if ( uaRemota.getAdministracionRemota() != null ) {

				adminRemota = (AdministracionRemota) session.load( AdministracionRemota.class, 
						uaRemota.getAdministracionRemota().getId() );
			}

			if ( adminRemota == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada");

			uaRemota.setAdministracionRemota(adminRemota);

			return grabarUARemota(session, uaRemota, idPadre, codEstandarTratamiento, codEstandarMateria);

		}catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza una UnidadAdministrativa Remota.
	 * Si el parametro {@link AdministracionRemota} es null recoje la {@link AdministracionRemota}
	 * del usuario que ha iniciado la sesion.
	 * 
	 * @param session	Sesión de hibernate.
	 * 
	 * @param uaRemota	Unidad administrativa a guardar.
	 * 
	 * @param idPadre	Identificador de la unidad administrativa padre.
	 * 
	 * @param codEstandarTratamiento	Código estándar del tratamiento.
	 * 
	 * @param codEstandarMateria	Código estándar de las materias.
	 * 
	 * @return	Devuelve el identificador de la unidad administrativa a guardar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private Long grabarUARemota(final Session session, UnidadAdministrativaRemota uaRemota, Long idPadre, String codEstandarTratamiento, String[] codEstandarMateria) throws HibernateException {

		AdministracionRemota adminRemota = uaRemota.getAdministracionRemota();

		//Valida si tiene padre
		UnidadAdministrativaRemota uaPadre = null;
		if ( idPadre != null )
			uaPadre = (UnidadAdministrativaRemota) session.load( UnidadAdministrativaRemota.class, idPadre );

		Tratamiento tratamiento = RemotoUtils.recogerTratamientoCE( session, codEstandarTratamiento );
		if ( tratamiento != null )
			uaRemota.setTratamiento(tratamiento);

		uaRemota.setEspacioTerrit( adminRemota.getEspacioTerrit() );

		if ( uaRemota.getId() == null ) {

			if ( uaPadre == null ) {

				//si es una unidad Raiz le doy un orden
				Query query = session.getNamedQuery("unidades.root.count");
				uaRemota.setOrden( ( (Integer) query.list().get(0) ).intValue() );

			} else {

				//Si no lo es la añado a su padre
				uaPadre.addHijo(uaRemota);

			}

			adminRemota.addUnidadAdministrativaRemota(uaRemota);
			uaRemota.setAdministracionRemota(adminRemota);
			//Guardo la unidad

			session.save(uaRemota);

		} else {

			//Miro si la nueva version de unidad es Raiz
			boolean newIsNull = ( idPadre == null );

			//Miro si la anterior version de unidad es Raiz
			boolean oldIsNull = ( uaRemota.getPadre() == null );

			//Marco que a cambiado si a cambiado algo con respecto a la version anterior
			boolean change = ( newIsNull ? !oldIsNull : !uaPadre.getId().equals(uaRemota.getPadre().getId() ) );

			//Guardo los cambios
			session.update(uaRemota);

			if ( change ) {

				if ( !oldIsNull ) {
					UnidadAdministrativa oldPadre = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, uaRemota.getPadre().getId() );

					//Si antes no era raiz elimino las referencias de su antiguo padre
					oldPadre.removeHijo(uaRemota);

				}

				if ( !newIsNull ) {

					//Si no es raiz le asigno un padre
					uaPadre.addHijo(uaRemota);

				}

				if ( newIsNull || oldIsNull ) {

					//Si es o fue raiz reseteo el orden de las unidades raiz
					session.flush();
					Query query = session.getNamedQuery("unidades.root");
					List<UnidadAdministrativa> lista = query.list();
					for ( int i = 0 ; i < lista.size() ; i++ ) {

						UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
						uni.setOrden(i);

					}

				}

			}

		}


		Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, codEstandarMateria);
		if ( materias != null && !materias.isEmpty() ) {

			if ( uaRemota.getUnidadesMaterias() == null ) {

				uaRemota.setUnidadesMaterias( new HashSet<UnidadMateria>() );

			} else {

				for ( UnidadMateria unidadMateria : (Set<UnidadMateria>) uaRemota.getUnidadesMaterias() ) {

					boolean relacionado = false;
					for ( Materia materia : materias ) {

						if ( relacionado = materia.getId().equals(unidadMateria.getMateria().getId() ) ) {

							materias.remove(materia);
							break;

						}

					}

					if ( !relacionado ) {

						unidadMateria.getMateria().removeUnidadMateria(unidadMateria);
						uaRemota.removeUnidadMateria(unidadMateria);
						session.delete(unidadMateria);

					}

				}

			}

			for ( Materia materia : materias ) {

				UnidadMateria uniMat = new UnidadMateria();
				uniMat.setMateria(materia);
				uniMat.setUnidad(uaRemota);
				uaRemota.addUnidadMateria(uniMat);
				materia.addUnidadMateria(uniMat);
				session.save(uniMat);

			}

		} else {

			if ( uaRemota.getUnidadesMaterias() != null ) {

				for ( UnidadMateria uniMat : (Set<UnidadMateria>) uaRemota.getUnidadesMaterias() ) {

					uniMat.getMateria().removeUnidadMateria(uniMat);
					uaRemota.removeUnidadMateria(uniMat);
					session.delete(uniMat);

				}

			}

		}

		session.flush();
		return uaRemota.getId();

	}


	/**
	 * Obtiene una UnidadAdministrativa Remota según su idExterno.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idExtUA	Identificador externo de la unidad administrativa.
	 */
	public UnidadAdministrativaRemota obtenerUARemotaAdmin(final Long idAdmin, final Long idExtUA) {

		Session session = getSession();
		try {

			return RemotoUtils.recogerUnidad(session, idExtUA, idAdmin);

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 *  @deprecated No se usa.
	 * Obtiene una UnidadAdministrativa Remota segun su idExterno y la AdministracionRemota
	 * que pertenezca al usuario actualmente logueado
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativaRemota obtenerUARemota(final String idRemoto, final Long idExtUA){
		Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if(admin==null)
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerUnidad(session, idExtUA, admin.getId());
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Obtiene  Unidad Administrativa Remota según su id.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 * 
	 * @return Devuelve <code>UnidadAdministrativaRemota</code> solicitada.
	 */
	public UnidadAdministrativaRemota obtenerUARemota(Long id) {

		Session session = getSession();

		try {

			UnidadAdministrativaRemota ua = (UnidadAdministrativaRemota) session.load( UnidadAdministrativaRemota.class, id );
			Hibernate.initialize( ua.getTratamiento() );
			Hibernate.initialize( ua.getEdificios() );
			Hibernate.initialize( ua.getUnidadesMaterias() );
			Hibernate.initialize( ua.getPadre() );
			Hibernate.initialize( ua.getHijos() );
			Hibernate.initialize( ua.getFichasUA() );
			Hibernate.initialize( ua.getProcedimientos() );
			Hibernate.initialize( ua.getNormativas() );

			return ua;

		} catch (HibernateException he) {

			throw new EJBException("No se ha encontrado la unidad administrativa solicitada", he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene una AdministracionRemota según su idRemoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador de la unidad administrativa.
	 * 
	 * @return	Devuelve <code>AdministracionRemota</code> solicitada.
	 */
	public AdministracionRemota obtenerAdministracionRemota(final String idRemoto) {

		Session session = getSession();
		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota( session, idRemoto );

			return admin;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 *  @deprecated No se usa.
	 * Lista las UnidadAdministrativa remotas
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<UnidadAdministrativaRemota> listarUARemotas(final String idRemoto){
		Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if(admin==null)
				throw new EJBException("No existe ninguna Administración Remota asociada al idRemoto");

			return admin.getUnidadesRemotas();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}


	/**
	 * Borra una UnidadAdministrativa Remota segun su id.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador de la unidad administrativa a borrar.
	 * 
	 * @param idExt	Identificador externo de la unidad administrativa.
	 */
	public void borrarUARemota(final String idRemoto, final Long idExt) {

		final Session session = getSession();
		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota( session, idRemoto );

			if ( admin == null )
				throw new EJBException("No existe ninguna Administración Remota asociada al idRemoto");

			final UnidadAdministrativaRemota uaRemota = RemotoUtils.recogerUnidad( session, idExt, admin.getId() );
			if ( uaRemota != null ) {

				if ( !uaRemota.isRaiz() ) {

					borrarUnidadAdministrativa( session, uaRemota );

				} else {

					borrarUnidadAdministrativaRaiz( session, uaRemota );

				}

			}

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra una unidad administrativa.
	 * 
	 * @param session	Indica la sesión de hibernate.
	 * 
	 * @param ua	Unidad administrativa a borrar.
	 * 
	 * @throws HibernateException
	 */
	private void borrarUnidadAdministrativa(Session session, UnidadAdministrativaRemota ua) throws HibernateException {

		Historico historico = getHistorico(session, ua);
		( (HistoricoUA) historico).setUa(null);

		ua.getEdificios().clear();
		ua.getUsuarios().clear();

		for ( FichaUA ficha : ua.getFichasUA() ) {

			if ( ficha != null ) {

				Seccion seccion = ficha.getSeccion();
				seccion.removeFichaUA(ficha);

			}

		}

		ua.getPadre().removeHijo(ua);

		for ( UnidadMateria uniMat : (Set<UnidadMateria>) ua.getUnidadesMaterias() )
			session.delete(uniMat);

		if ( ua instanceof UnidadAdministrativaRemota ) {

			AdministracionRemota admin = ( (UnidadAdministrativaRemota) ua ).getAdministracionRemota();
			if ( admin != null )
				admin.removeUnidadAdministrativaRemota( (UnidadAdministrativaRemota) ua );

		}

		session.delete(ua);
		session.flush();

	}


	/**
	 * Borra una unidad administrativa raíz.
	 * 
	 * @param session	Indica la sesión de hibernate.
	 * 
	 * @param ua	Indica la unidad administrativa a borrar.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private void borrarUnidadAdministrativaRaiz(Session session, UnidadAdministrativaRemota ua) throws HibernateException {

		Historico historico = getHistorico(session, ua);
		( (HistoricoUA) historico).setUa(null);

		ua.getEdificios().clear();
		ua.getUsuarios().clear();
		Usuario usuario = getUsuario(session);
		usuario.getUnidadesAdministrativas().remove(ua);

		for ( FichaUA ficha : ua.getFichasUA() ) {

			if ( ficha != null ) {

				Seccion seccion = ficha.getSeccion();
				seccion.removeFichaUA(ficha);

			}

		}

		for ( UnidadMateria uniMat : (Set<UnidadMateria>) ua.getUnidadesMaterias() ) {

			session.delete(uniMat);

		}

		AdministracionRemota admin = ( (UnidadAdministrativaRemota) ua).getAdministracionRemota();
		if ( admin != null ) 
			admin.removeUnidadAdministrativaRemota( (UnidadAdministrativaRemota) ua );

		session.delete(ua);
		session.flush();

		Query query = session.getNamedQuery("unidades.root");
		List lista = query.list();
		for ( int i = 0 ; i < lista.size() ; i++ ) {

			UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
			uni.setOrden(i);

		}

	}


	/**
	 * @deprecated No se usa.
	 * Busca todos los {@link UnidadAdministrativaRemota} cuyo nombre contenga el String de entrada
	 *
	 * @param busqueda
	 * @param idioma
	 * @return lista de {@link UnidadAdministrativaRemota}
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<UnidadAdministrativaRemota> buscar(final String busqueda, final String idioma){
		List<UnidadAdministrativaRemota> resultado;

		if (busqueda == null || "".equals(busqueda.trim())) {
			resultado = Collections.emptyList();
		} else {
			Session session = getSession();
			try {
				Query query = session.createQuery("from UnidadAdministrativaRemota as uni, " +
						"      uni.traducciones as trad " +
						"where index(trad) = :idioma " +
						"  and upper(trad.nombre) like :busqueda" +
						"  and uni.validacion = :validacion" );
				query.setString("idioma", idioma);
				query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
				query.setInteger("validacion", Validacion.PUBLICA);
				resultado = (List<UnidadAdministrativaRemota>)query.list();
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}

		return resultado;
	}


	/**
	 * Lista las unidades Administrativas Remotas por ámbito(Autonomico, Insular)(PORMAD).
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param ambito	Indica el espacio territorial de una unidad administrativa remota.
	 * 
	 * @return Devuelve <code>List<UnidadAdministrativaRemota></code> filtradas por el ámbito
	 */
	public List<UnidadAdministrativaRemota> listarUARemotasPorAmbito(Long ambito) {

		Session session = getSession();
		try {

			Query query = session.createQuery(
					" from UnidadAdministrativaRemota as ua " +
							" where ua.validacion = :validacion " +
					" and ua.espacioTerrit.nivel = :ambito");

			query.setParameter( "validacion", Validacion.PUBLICA );
			query.setParameter( "ambito", ambito );
			query.setCacheable(true);

			List<UnidadAdministrativaRemota> unidades = query.list();
			for ( UnidadAdministrativaRemota unidad : unidades ) {

				Hibernate.initialize( unidad.getFotop() );

			}

			return unidades;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra una unidad administrativa raíz.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id	Identificador de la unidad administrativa.
	 */
	public void borrarUnidadAdministrativaRaizRemota(Long id) {

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

			Iterator iteradorEdificios = ua.getEdificios().iterator();
			while ( iteradorEdificios.hasNext() ) {

				Edificio edificio = (Edificio) iteradorEdificios.next();
				edificio.getUnidadesAdministrativas().remove(ua);

			}

			ua.getEdificios().clear();
			ua.getUsuarios().clear();
			Usuario usuario = getUsuario(session);
			usuario.getUnidadesAdministrativas().remove(ua);

			List fichasUA = ua.getFichasUA();
			Iterator iteradorFichasUA = fichasUA.iterator();
			while ( iteradorFichasUA.hasNext() ) {

				FichaUA ficha = (FichaUA) iteradorFichasUA.next();
				if ( ficha != null ) {

					Seccion seccion = ficha.getSeccion();
					seccion.removeFichaUA(ficha);

				}

			}

			for ( UnidadMateria uniMat : (Set<UnidadMateria>) ua.getUnidadesMaterias() ) {

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
	 * Borra una unidad administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * 
	 * @param id	Identificador de la unidad adminsitrativa.
	 */
	public void borrarUnidadAdministrativaRemota(Long id) throws Exception {

		Session session = getSession();

		try {

			UnidadAdministrativa ua = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, id );
			if ( ua.isRaiz() ) {

				throw new SecurityException("No puede eliminar una unidad raíz.");

			} else {

				if ( !getAccesoManager().tieneAccesoUnidad( ua.getPadre().getId(), true ) )
					throw new SecurityException("No tiene acceso al padre de la unidad");

			}

			addOperacion( session, ua, Auditoria.BORRAR );
			Historico historico = getHistorico(session, ua);
			( (HistoricoUA) historico ).setUa(null);

			Iterator iteradorEdificios = ua.getEdificios().iterator();
			while ( iteradorEdificios.hasNext() ) {

				Edificio edificio = (Edificio) iteradorEdificios.next();
				edificio.getUnidadesAdministrativas().remove(ua);

			}

			ua.getEdificios().clear();
			ua.getUsuarios().clear();
			List fichasUA = ua.getFichasUA();
			Iterator iteradorFichasUA = fichasUA.iterator();
			while ( iteradorFichasUA.hasNext() ) {

				FichaUA ficha = (FichaUA) iteradorFichasUA.next();
				if ( ficha != null ) {

					ficha.getFicha().removeFichaUA(ficha);
					ficha.getSeccion().removeFichaUA(ficha);
					ficha.setUnidadAdministrativa(null);

					//session.update(ficha);
					session.delete(ficha);
					// Ficha ficha = fichaUA.getFicha();
					// ficha.removeFichaUA(fichaUA);
					// Seccion seccion = ficha.getSeccion();
					// seccion.removeFichaUA(ficha);

				}

			}

			ua.getPadre().removeHijo(ua);
			for ( UnidadMateria uniMat : (Set<UnidadMateria>) ua.getUnidadesMaterias() ) {

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

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Añade la ua al índice en todos los idiomas.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param ua	Indica la unidad administrativa.
	 * 
	 * @param filter	Indica <code>ModelFilterObject</code>.
	 */
	public void indexInsertaUARemota(UnidadAdministrativa ua,  ModelFilterObject filter)  {
		
		try {

			if ( filter == null ) 
				filter = obtenerFilterObject(ua);

			Iterator iterator = ua.getLangs().iterator();
			while ( iterator.hasNext() ) {
				
				String idi = (String) iterator.next();
				IndexObject io = new IndexObject();

				io.setId( Catalogo.SRVC_UO + "." + ua.getId() );
				io.setClasificacion(Catalogo.SRVC_UO);

				io.setMicro( filter.getMicrosite_id() ); 
				io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );
				io.setCaducidad("");
				io.setPublicacion(""); 
				io.setDescripcion("");
				io.addTextLine( ua.getResponsable() );

				TraduccionUA trad = ( (TraduccionUA) ua.getTraduccion(idi) );
				if ( trad != null ) {

					io.setUrl( "/govern/organigrama/area.do?coduo=" + ua.getId() + "&lang=" + idi );
					io.setTituloserviciomain( filter.getTraduccion(idi).getMaintitle() );


					if ( trad.getNombre() != null ) {
						
						io.setTitulo( trad.getNombre() ); //para dar mas peso al titulo
						for ( int i = 0 ; i < 5 ; i++ )
							io.addTextLine( trad.getNombre() );
						
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
					DelegateUtil.getIndexerDelegate().insertaObjeto( io, idi );
				
			}
			
		} catch (Exception ex) {
			
			log.warn( "[indexInsertaUA:" + ua.getId() + "] No se ha podido indexar UA. " + ex.getMessage() );
			
		}

	}


	/**
	 * Método que obtiene un bean con el filtro para la indexación.
	 * 
	 * @param ua	Indica la unidad administrativa.
	 * 
	 * @return Devuelve <code>ModelFilterObject</code>
	 */
	private ModelFilterObject obtenerFilterObject(UnidadAdministrativa ua) {
		
		ModelFilterObject filter = new ModelFilterObject();

		//de momento, familia y microsites a null
		filter.setFamilia_id(null);    	
		filter.setMicrosite_id(null);
		filter.setSeccion_id(null);

		Iterator iterlang = ua.getLangs().iterator();
		while ( iterlang.hasNext() ) {

			String idioma = (String) iterlang.next();
			String txids = Catalogo.KEY_SEPARADOR;
			String txtexto = " ";//espacio en blanco, que es para tokenizar
			Iterator iter;

			TraModelFilterObject trafilter = new TraModelFilterObject();

			//titulo		
			trafilter.setMaintitle( ( (TraduccionUA) ua.getTraduccion(idioma) ).getNombre() );

			txids = Catalogo.KEY_SEPARADOR;
			txtexto = " ";
			txids += ua.getId() + Catalogo.KEY_SEPARADOR;
			txtexto += ( (TraduccionUA) ua.getTraduccion(idioma) ).getNombre() + " ";
			txtexto += ( (TraduccionUA) ua.getTraduccion(idioma) ).getAbreviatura() + " ";
			txtexto += ( (TraduccionUA) ua.getTraduccion(idioma) ).getPresentacion() + " ";

			//OBTENER DIRECCIONES
			if ( ua.getEdificios() != null ) {
				
				iter = ua.getEdificios().iterator();
				while ( iter.hasNext() ) {
					
					Edificio edificio = (Edificio) iter.next();
					txtexto += edificio.getDireccion() + " ";
					txtexto += edificio.getTelefono() + " ";
					
				}
				
			}
			
			filter.setUo_id( ( txids.length() == 1 ) ? null : txids );
			trafilter.setUo_text( ( txtexto.length() == 1 ) ? null : txtexto );
			
			//OBTENER LAS MATERIAS (además de las materias se ponen los textos de los HECHOS VITALES)
			if ( ua.getUnidadesMaterias() != null ) {
				
				txids = Catalogo.KEY_SEPARADOR;
				txtexto = " ";
				iter = ua.getUnidadesMaterias().iterator();
				while ( iter.hasNext() ) {
					UnidadMateria uamat = (UnidadMateria) iter.next();

					txids += uamat.getMateria().getId() + Catalogo.KEY_SEPARADOR; 
					if ( uamat.getMateria().getTraduccion(idioma) != null ) {
						
						txtexto += ( (TraduccionMateria) uamat.getMateria().getTraduccion(idioma) ).getNombre() + " ";
						txtexto+=( (TraduccionMateria) uamat.getMateria().getTraduccion(idioma) ).getDescripcion() + " ";
						txtexto+=( (TraduccionMateria) uamat.getMateria().getTraduccion(idioma) ).getPalabrasclave() + " ";
						
					}

				}
				
				filter.setMateria_id( ( txids.length() == 1 ) ? null : txids );
				trafilter.setMateria_text( ( txtexto.length() == 1 ) ? null : txtexto );
				
			}

			filter.addTraduccion(idioma, trafilter);

		}
		
		return filter;
		
	}

	
	/**
	 * Obtiene un Edificio Remoto desde su id externo y su id de la UARemota.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idExterno	Identificador externo del edificio remoto.
	 * 
	 * @param idUARemota	Identificador de la unidad administrativa remota.
	 * 
	 * @return Devuelve <code>EdificioRemoto</code> solicitado.
	 */
	public EdificioRemoto obtenerEdificioRemoto(Long idExterno, Long idUaRemota) {
		
		Session session = getSession();
		try {

			Query query = session.createQuery("from EdificioRemoto as edificio where edificio.idExterno = :idExterno and edificio.administracionRemota.id = :idUaRemota");
			query.setParameter( "idExterno", idExterno );
			query.setParameter( "idUaRemota", idUaRemota );
			EdificioRemoto edificio = (EdificioRemoto) query.uniqueResult();

			if ( edificio != null )
				Hibernate.initialize( edificio.getUnidadesAdministrativas() );

			return edificio;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene un Edificio Remoto segun su idExterno y el IdRemoto actualmente
	 * de la AdministracionRemota.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador del edificio remoto.
	 * 
	 * @param idExtEdificio	Identificador externo del edificio.
	 * 
	 * @return Devuelve <code>EdificioRemoto</code> solicitado.
	 */
	public EdificioRemoto obtenerEdificioRemoto(final String idRemoto, final Long idExtEdificio) {
		
		final Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota( session, idRemoto );

			if ( admin == null )
				throw new EJBException("No existe ninguna Administración Remota asociada al idRemoto");

			return RemotoUtils.recogerEdificio( session, idExtEdificio, admin.getId() );
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Crea o actualiza un Edificio en la importación de una Unidad Administrativa. Se duplica para no tener roles.
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param edificioRemoto	Edificio remoto a guardar.
	 * 
	 * @return Devuelve el identificador del edificio remoto solicitado.
	 */
	public Long grabarEdificioRemoto(EdificioRemoto edificioRemoto) {
		
		Session session = getSession();
		try {
			
			if ( edificioRemoto != null ) {
				
				session.saveOrUpdate(edificioRemoto);
				session.flush();
				
			}
			
			return edificioRemoto.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Borra un edificio remoto. Se duplica paro no tener roles.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador del edificio remoto.
	 */
	public void borrarEdificioRemoto(Long id) {
		
		Session session = getSession();
		try {
			
			Edificio edificio = (Edificio) session.load( Edificio.class, id );
			Iterator iterator = edificio.getUnidadesAdministrativas().iterator();
			while ( iterator.hasNext() ) {
				
				UnidadAdministrativa ua = (UnidadAdministrativa) iterator.next();
				ua.getEdificios().remove(edificio);
				
			}
			
			if ( edificio instanceof EdificioRemoto ) {
				
				AdministracionRemota admin = ( (EdificioRemoto) edificio ).getAdministracionRemota();
				if ( admin != null )
					admin.removeEdificioRemoto( (EdificioRemoto) edificio );
				
			}
			
			session.delete(edificio);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Obtiene los edificios de una Unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param ua	Identificador de la unidad administrativa.
	 */
	public List<Edificio> obtenerEdificiosUA(Long ua) {
		
		Session session = getSession();
		try {
			
			Query query = session.createQuery(" select elements(ua.edificios) from UnidadAdministrativa as ua where ua.id = :idUA ");
			query.setParameter( "idUA", ua );

			return query.list();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Borra un Edifico Remoto según su idExterno.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador del edificio remoto.
	 * 
	 * @param idExterno	Identificador externo del edificio.
	 */
	public void borrarEdificioRemoto(String idRemoto, Long idExterno) {

		Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			EdificioRemoto edificioRemoto = RemotoUtils.recogerEdificio( session, idExterno, admin.getId() );
			Iterator iterator = edificioRemoto.getUnidadesAdministrativas().iterator();
			while ( iterator.hasNext() ) {
				
				UnidadAdministrativa ua = (UnidadAdministrativa) iterator.next();                    
				ua.getEdificios().remove(edificioRemoto);
				
			}
			
			if ( edificioRemoto != null ) {
				
				admin.removeEdificioRemoto(edificioRemoto);
				session.delete(edificioRemoto);
				session.flush();
				
			}
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Elimina una unidad del edificio
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true" 
	 *
	 * @param idRemoto	Identificador de la unidad administrativa remota.
	 * 
	 * @param idEdif	Identificador del edificio.
	 * 
	 * @param idUA	Identificador de la unidad administrativa.
	 */
	public void eliminarUnidad(String idRemoto,Long idEdif, Long idUA) {
		
		Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if ( admin == null )
				throw new EJBException("No existe ninguna Administración Remota asociada al idRemoto");

			UnidadAdministrativa ua = RemotoUtils.recogerUnidad( session, idUA, admin.getId() );	            
			EdificioRemoto edi = RemotoUtils.recogerEdificio( session, idEdif, admin.getId() );
			edi.getUnidadesAdministrativas().remove(ua);
			session.flush();

		} catch (HibernateException e) {
			
			throw new EJBException(e);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 *  @deprecated No se usa.
	 * PORMAD
	 * Lista de fichas y procedimientos remotas mas recientes
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<Object> listarNovedadesPMA (int length, boolean caducados, Long idRemota) {
		List<Object> listOrdenada = new ArrayList<Object>();
		Session session = getSession();
		try {

			//Obtemos las fichas
			Query queryFic = session.createQuery(
					"Select DISTINCT f from FichaRemota as f , fua in f.fichasua " +
							"where f.validacion = :validacion " +
							"and f.fechaActualizacion is not null " +
							"and fua is not null " +
							"and f.administracionRemota.id = :idRemota " +
							(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
					"order by f.fechaActualizacion desc");
			queryFic.setParameter("validacion", Validacion.PUBLICA);
			queryFic.setParameter("idRemota", idRemota);
			if(!caducados){queryFic.setParameter("fecha", DateUtils.today());}
			queryFic.setMaxResults(length);
			queryFic.setCacheable(true);
			List<FichaRemota> listFicha = queryFic.list();
			listOrdenada.addAll(listFicha); 

			//Obtemos los procedimientos
			Query queryProc = session.createQuery(
					"Select DISTINCT p from ProcedimientoRemoto as p " +
							"where p.fechaActualizacion is not null and p.validacion = :validacion " +
							"and p.administracionRemota.id = :idRemota " +
							(!caducados ? "AND ( p.fechaCaducidad is null or p.fechaCaducidad >= :fecha ) " : "") +
					"order by p.fechaActualizacion desc");
			queryProc.setParameter("validacion", Validacion.PUBLICA);
			queryProc.setParameter("idRemota", idRemota);
			if(!caducados){queryProc.setParameter("fecha", DateUtils.today());}
			queryProc.setMaxResults(length);
			queryProc.setCacheable(true);
			List<ProcedimientoRemoto> listProc = queryProc.list();
			//Ordenaci�n
			listOrdenada.addAll(listProc);
			Collections.sort(listOrdenada, new Comparator() {
				public int compare(Object o1, Object o2) {
					Date data1 = null;
					if (o1 instanceof FichaRemota){ 
						data1 = ((Ficha)o1).getFechaActualizacion();

					}
					else if (o1 instanceof ProcedimientoLocal){
						data1 = ((ProcedimientoRemoto)o1).getFechaActualizacion();
					}

					Date data2 = null;
					if (o2 instanceof FichaRemota){ 
						data2 = ((Ficha)o2).getFechaActualizacion();
					}
					else if (o2 instanceof ProcedimientoLocal){
						data2 = ((ProcedimientoRemoto)o2).getFechaActualizacion();
					}
					return -data1.compareTo(data2);
				}
			}); 

			return listOrdenada;


		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 *  @deprecated No se usa.
	 * PORMAD
	 * Lista de fichas y procedimientos remotas destacadas
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<Object> listarDestacadosPMA (int length, boolean caducados, Long idRemota) {
		List<Object> listOrdenada = new ArrayList<Object>();
		Session session = getSession();
		try {

			Query queryFic = session.createQuery(
					"select f from FichaRemota f, HistoricoFicha hf, Estadistica e , fua in f.fichasua " +
							"where hf.ficha = f and e.historico = hf " +
							"and fua is not null " +
							"and f.validacion = :validacion " +
							"and f.administracionRemota.id = :idRemota " +
							(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
							// "group by" ha de posar explicitament tots els camps, veure:
							// http://opensource.atlassian.com/projects/hibernate/browse/HB-1003
							"group by f.id, f.idExterno, f.urlRemota, f.administracionRemota, f.fechaCaducidad, " +
							"f.icono, f.imagen, f.baner, f.validacion, f.info, f.fechaPublicacion, f.fechaActualizacion, f.urlVideo, f.urlForo, f.foro_tema, f.responsable " +
					"order by sum(e.contador) desc");
			queryFic.setParameter("validacion", Validacion.PUBLICA);
			queryFic.setParameter("idRemota", idRemota);
			if(!caducados){queryFic.setParameter("fecha", DateUtils.today());}
			queryFic.setMaxResults(length);
			queryFic.setCacheable(true);

			List<FichaRemota> listFicha = queryFic.list();
			listOrdenada.addAll(listFicha); 

			//Obtemos los procedimientos
			Query queryProc = session.createQuery(
					"select p from ProcedimientoRemoto p, HistoricoProcedimiento hp, Estadistica e " +
							"where hp.procedimiento = p and e.historico = hp and p.validacion = :validacion " +
							"and p.administracionRemota.id = :idRemota " +
							(!caducados ? "AND ( p.fechaCaducidad is null or p.fechaCaducidad >= :fecha ) " : "") +
							// "group by" ha de posar explicitament tots els camps, veure:
							// http://opensource.atlassian.com/projects/hibernate/browse/HB-1003
							"group by p.id, p.idExterno, p.urlRemota, p.administracionRemota, p.signatura, " +
							" p.fechaCaducidad, p.fechaPublicacion, p.fechaActualizacion, p.validacion, p.tramite, " +
							" p.version, p.info, p.unidadAdministrativa, p.familia , p.url, p.orden, p.orden2, p.orden3,p.iniciacion, p.indicador, p.ventanillaUnica, p.info, p.responsable, p.taxa, p.organResolutori" +
					" order by sum(e.contador) desc");
			queryProc.setParameter("validacion", Validacion.PUBLICA);
			queryProc.setParameter("idRemota", idRemota);
			queryProc.setMaxResults(length);
			if(!caducados){queryProc.setParameter("fecha", DateUtils.today());}
			queryProc.setCacheable(true);
			List<ProcedimientoRemoto> listProc = queryProc.list();
			listOrdenada.addAll(listProc); 
			return listOrdenada;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
}
