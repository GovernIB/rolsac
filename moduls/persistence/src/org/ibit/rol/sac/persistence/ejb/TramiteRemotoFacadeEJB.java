package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.TramiteRemoto;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.RemotoUtils;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.*;

/**
 * SessionBean para mantener y consultar Tramites Remotos.
 *
 * @ejb.bean
 *  name="sac/persistence/TramiteRemotoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TramiteRemotoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TramiteRemotoFacadeEJB extends HibernateEJB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 815404026351672812L;

	/**
	 * Obtiene referencia al ejb de control de Acceso.
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
	 * Crea una Trámite Remoto
	 * 
	 * @param	tramiteRemoto	Indica el trámite remoto a guardar
	 * 
	 * @param	idProcedimiento	Indentificador de un procedimiento
	 * 
	 * @param	idUA	Identificador de una unidad administrativa
	 * 
	 * @return Devuelve el identificador del trámite guardado
	 * 
	 * @throws HibernateException 
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public Long grabarTramiteRemoto(final TramiteRemoto tramiteRemoto, final Long idProcedimiento,Long idUA) {

		Session session = getSession();

		try {

			if ( idProcedimiento != null ) {

				ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, idProcedimiento);
				procRemoto.addTramite(tramiteRemoto);
				tramiteRemoto.setProcedimiento(procRemoto);

				//obtenemos el órgano competente y lo añadimos
				if ( idUA != null ) {

					UnidadAdministrativaRemota organoCompentente = RemotoUtils.recogerUnidad(session, idUA, tramiteRemoto.getAdministracionRemota().getId() );
					if ( organoCompentente != null ) {

						tramiteRemoto.setOrganCompetent(organoCompentente);
						organoCompentente.getTramites().add(tramiteRemoto);

					}

				}

				if ( tramiteRemoto.getId() != null ) {

					session.update(tramiteRemoto);

				} else {

					session.save(tramiteRemoto);

				}

				session.flush();

			}

			return tramiteRemoto.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Actualiza un Trámite en la importación de una UA. Se duplica paro no tener roles.
	 * 
	 * @param	tramiteRemoto	Indica un trámite remoto a guardar.
	 * 
	 * @param	idUA	Identificador de una unidad administrativa.
	 * 
	 * @return	Devuelve el identificador del trámite guardado.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Long grabarTramiteRemoto(TramiteRemoto tramiteRemoto,Long idUA) {

		Session session = getSession();

		try {

			if ( idUA != null ) {

				UnidadAdministrativaRemota organoCompententeTrans = RemotoUtils.recogerUnidad( session, idUA, tramiteRemoto.getAdministracionRemota().getId() );

				if ( organoCompententeTrans != null ) {

					if ( tramiteRemoto.getOrganCompetent() != null ) { //En el caso que sea el mismo no hacemos nada si es diferente lo cambiamos por el nuevo

						if ( !organoCompententeTrans.getId().equals(tramiteRemoto.getOrganCompetent().getId()) ) {

							tramiteRemoto.setOrganCompetent(organoCompententeTrans);
							organoCompententeTrans.getTramites().add(tramiteRemoto);

						}

					} else { //En el caso que no haya lo añadimos como nuevo

						tramiteRemoto.setOrganCompetent(organoCompententeTrans);
						organoCompententeTrans.getTramites().add(tramiteRemoto);

					}

				} else { //Si no existe el organo competente transferido lo ponemos a null

					tramiteRemoto.setOrganCompetent(null);

				}

			}

			if ( tramiteRemoto != null ) {

				session.saveOrUpdate(tramiteRemoto);
				session.flush();

			}

			return tramiteRemoto.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene los tramites de un procedimiento
	 * 
	 * @param idProcedimiento	Identificador de un procedimiento
	 * 
	 * @return	Devuelve <code>List<Tramite></code> de un procedimiento
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<Tramite> obtenerTramitesProcedimiento(Long idProcedimiento) {

		Session session = getSession();

		try {

			Query query = session.createQuery("select elements(proc.tramites) from ProcedimientoLocal as proc where proc.id = :idProcedimiento");
			query.setParameter("idProcedimiento", idProcedimiento);

			List<Tramite> tramites = (List<Tramite>) query.list();
			List<Tramite> tramFinales = new ArrayList<Tramite>();

			for ( Tramite tramite : tramites ) {

				if( publico(tramite) ) {

					Hibernate.initialize(tramite.getFormularios());

					for ( DocumentTramit formulario : tramite.getFormularios() ) {

						for ( final String idioma : (Collection<String>) tramite.getLangs() ) {

							final TraduccionDocumento traduccion = (TraduccionDocumento) formulario.getTraduccion(idioma);

							if ( traduccion != null )					
								Hibernate.initialize(traduccion.getArchivo());
						}

					}

					Hibernate.initialize(tramite.getDocsInformatius());

					for ( DocumentTramit docInformatiu : tramite.getDocsInformatius() ) {

						for ( final String idioma : (Collection<String>) tramite.getLangs() ) {

							final TraduccionDocumento traduccion = (TraduccionDocumento) docInformatiu.getTraduccion(idioma);

							if ( traduccion != null )					
								Hibernate.initialize(traduccion.getArchivo());

						}

					}

					Hibernate.initialize(tramite.getTaxes());
					tramFinales.add(tramite);

				}

			}	

			return tramFinales;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	protected boolean publico(Tramite tram) {

		final Date now = new Date();
		boolean noCaducado = ( tram.getDataCaducitat() == null || tram.getDataCaducitat().after(now) );
		boolean publicado = ( tram.getDataPublicacio() == null || tram.getDataPublicacio().before(now) );
		boolean visible = ( tram.getValidacio() == null || Validacion.PUBLICA.equals( Integer.valueOf(tram.getValidacio().toString()) ) );

		return visible && noCaducado && publicado;

	}


	/**
	 * Obtiene un Trámite Remoto a partir de su identificador externo y el identificador de la UARemota
	 * 
	 * @param	idExterno Identificador externo del trámite
	 * 
	 * @param	idUaRemota	Identificador de una unidad administrativa
	 * 
	 * @return	Devuelve <code>TramiteRemoto</code> solicitado.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public TramiteRemoto obtenerTramiteRemoto(Long idExterno,Long idUaRemota) {

		Session session = getSession();

		try {


			Query query = session.createQuery("from TramiteRemoto as tr where tr.idExterno = :idExterno and tr.administracionRemota.id = :idUaRemota");
			query.setParameter("idExterno", idExterno);
			query.setParameter("idUaRemota", idUaRemota);

			TramiteRemoto tramite = (TramiteRemoto) query.uniqueResult();

			if ( tramite != null )
				Hibernate.initialize(tramite.getProcedimiento());

			return tramite;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un Trámite Remoto
	 * 
	 * @param	idRemoto	Indica el identificador remoto del trámite
	 * 
	 * @param	idExtTramite	Indica el identificador externo del trámite
	 * 
	 * @return Devuelve <code>TramiteRemoto</code> filtrado por el identificador externo del trámite 
	 * y el identificador remoto de la unidad administrativa
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public TramiteRemoto obtenerTramiteRemoto(final String idRemoto, final Long idExtTramite) {

		final Session session = getSession();

		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerTramite(session, idExtTramite, admin.getId());
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Borra un trámite remoto. Se duplica paro no tener roles.
	 * 
	 * @param	Indentificador del trámite a borrar.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public void borrarTramiteRemoto(Long id) {
		
		Session session = getSession();
		
		try {

			Tramite tramite = (Tramite) session.load(Tramite.class, id);

			ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load( ProcedimientoRemoto.class, tramite.getProcedimiento().getId() );
			if ( tramite.getOrganCompetent() != null ) {
				
				UnidadAdministrativaRemota organoCompetente = (UnidadAdministrativaRemota) session.load( UnidadAdministrativaRemota.class, tramite.getOrganCompetent().getId() );
				organoCompetente.getTramites().remove(tramite);
				
			}

			procRemoto.removeTramite(tramite);

			if ( tramite instanceof TramiteRemoto ) {
				
				AdministracionRemota admin = ((TramiteRemoto) tramite).getAdministracionRemota();
				
				if ( admin != null )
					admin.removeTramiteRemoto( (TramiteRemoto) tramite );
				
			}
			
			session.delete(tramite);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Borra un trámite Remoto
	 * 
	 * @param	idRemoto Identificador del trámite remoto
	 * 
	 * @param	idExterno	Identificador externo del trámite
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public void borrarTramiteRemoto(final String idRemoto ,final Long idExterno) {

		final Session session = getSession();
		
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			Query query = session.createQuery("from TramiteRemoto as tr where tr.idExterno = :idExterno and tr.administracionRemota.id = :idUARemota");
			query.setParameter("idExterno", idExterno);
			query.setParameter("idUARemota", admin.getId());
			
			TramiteRemoto tramiteRemoto = (TramiteRemoto) query.uniqueResult();

			if ( tramiteRemoto != null ) {
				
				Hibernate.initialize(tramiteRemoto.getProcedimiento());
				ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load( ProcedimientoRemoto.class, tramiteRemoto.getProcedimiento().getId() );
				procRemoto.removeTramite(tramiteRemoto);
				admin.removeTramiteRemoto(tramiteRemoto);
				
				if ( tramiteRemoto.getOrganCompetent() != null ) {
					
					UnidadAdministrativaRemota organoCompetente = (UnidadAdministrativaRemota) session.load( UnidadAdministrativaRemota.class, tramiteRemoto.getOrganCompetent().getId() );
					organoCompetente.getTramites().remove(tramiteRemoto);
					
				}
				
				session.delete(tramiteRemoto);
				session.flush();
				
			}
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

}
