package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.DocumentoRemoto;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.persistence.util.RemotoUtils;



/**
 * SessionBean para mantener y consultar Procedimientos Remotos (PORMAD)
 * 
 * @ejb.bean name="sac/persistence/ProcedimientoRemotoFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.ProcedimientoRemotoFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class ProcedimientoRemotoFacadeEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idRemoto	Identificador remoto del procedimiento
	 * 
	 * @param	procedimiento	Procedimiento remoto a guardar.
	 * 
	 * @param	codEstandarMaterias	Indica el código estándar de las materias asignadas.
	 * 
	 * @param	codEstandarHechosVitales	Indica el código estándar de los hechos vitales asignados.
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarProcedimientoRemoto(final String idRemoto ,final ProcedimientoRemoto procedimiento,
			final String[] codEstandarMaterias, final String[] codEstandarHechosVitales) {

		final Session session = getSession();
		try {

			AdministracionRemota adminRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( adminRemota == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada");

			procedimiento.setAdministracionRemota(adminRemota);

			return grabarProcedimientoRemoto(session, procedimiento, codEstandarMaterias, codEstandarHechosVitales);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	procedimiento	PRocedimiento remoto a guardar.
	 * 
	 * @param	ceMaterias	Indica el código estándar de las materias asignadas.
	 * 
	 * @param	ceHechos	Indica el código estándar de los hechos vitales asignadas.
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarProcedimientoRemoto(final ProcedimientoRemoto procedimiento,
			final String[] ceMaterias, final String[] ceHechos) {

		final Session session = getSession();
		try {

			AdministracionRemota adminRemota = null;

			if ( procedimiento.getAdministracionRemota() != null ) {

				adminRemota = (AdministracionRemota) session.load(AdministracionRemota.class, 
						procedimiento.getAdministracionRemota().getId());

			}

			if ( adminRemota == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada");

			procedimiento.setAdministracionRemota(adminRemota);

			return grabarProcedimientoRemoto(session, procedimiento, ceMaterias, ceHechos);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @param session	Indica la sesión de hibernate.
	 * 
	 * @param procedimiento	Indica el procedimiento remoto a guardar.
	 * 
	 * @param codEstandarMaterias	Indica el código estándar de las materias asignadas.
	 * 
	 * @param codEstandarHechosVitales	Indica el código estándar de los hechos vitales asignados.
	 * 
	 * @return Devuelve el identificador del procedimiento remoto guardado.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private Long grabarProcedimientoRemoto(final Session session, final ProcedimientoRemoto procedimiento,
			final String[] codEstandarMaterias, final String[] codEstandarHechosVitales) throws HibernateException {

		final Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, codEstandarMaterias);
		if ( materias != null ) {

			procedimiento.setMaterias(materias);

		} else {

			procedimiento.setMaterias(Collections.EMPTY_SET);

		}

		AdministracionRemota adminRemota = procedimiento.getAdministracionRemota();

		if ( procedimiento.getId() != null ) {

			session.update(procedimiento);

		} else {

			session.save(procedimiento);
			adminRemota.addProcedimientoRemoto(procedimiento);
			procedimiento.setAdministracionRemota(adminRemota);

		}

		final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session, codEstandarHechosVitales);
		if ( hechos != null && !hechos.isEmpty() ) {

			if ( procedimiento.getHechosVitalesProcedimientos() == null ) {

				procedimiento.setHechosVitalesProcedimientos( new HashSet<HechoVitalProcedimiento>() );

			} else {

				Set<HechoVitalProcedimiento> s = procedimiento.getHechosVitalesProcedimientos();
				for ( final HechoVitalProcedimiento heProc : s ) {

					boolean relacionado = false;
					for ( final HechoVital hecho : hechos ) {

						if ( relacionado = hecho.getId().equals( heProc.getHechoVital().getId() ) ) {

							hechos.remove(hecho);
							break;

						}

					}

					if ( !relacionado ) {

						heProc.getHechoVital().removeHechoVitalProcedimiento(heProc);
						procedimiento.removeHechoVitalProcedimiento(heProc);
						session.delete(heProc);

					}

				}

			}

			for ( final HechoVital hecho : hechos ) {

				final HechoVitalProcedimiento hvProc = new HechoVitalProcedimiento();
				hvProc.setHechoVital(hecho);
				hvProc.setProcedimiento(procedimiento);
				procedimiento.addHechoVitalProcedimiento(hvProc);
				hecho.addHechoVitalProcedimiento(hvProc);
				session.save(hvProc);

			}

		} else {

			procedimiento.setHechosVitalesProcedimientos(Collections.EMPTY_SET);

		}

		session.flush();

		return procedimiento.getId();

	}



	/**
	 * Obtiene un Procedimiento Remoto segun su idExterno y el usuario
	 * actualmente logueado
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador de un procedimiento remoto.
	 * 
	 * @param	idExterno	Identificador externo del procedimiento
	 * 
	 * @return Devuelve el procedimiento remoto solicitado.
	 */
	public ProcedimientoRemoto obtenerProcedimientoRemoto( final String idRemoto, final Long idExterno) {

		final Session session = getSession();
		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerProcedimiento( session, idExterno, admin.getId() );

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un Procedimiento Remoto segun su idExterno y una
	 * AdministracionRemota
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idExterno	Identificador externo
	 * 
	 * @param	idAdministracion	Identificador de la administración remota.
	 * 
	 * @return	Devuelve el procedimiento remoto solicitado.
	 */
	public ProcedimientoRemoto obtenerProcedimientoRemoto(final Long idExterno, final Long idAdministracion) {

		final Session session = getSession();
		try {

			return RemotoUtils.recogerProcedimiento(session, idExterno, idAdministracion);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Borra un Procedimiento Remoto según su idExterno
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idRemoto	Identificador del procedimiento remoto.
	 * 
	 * @param	idExterno	Identificador externo del procedimiento.
	 */
	public void borrarProcedimientoRemoto(final String idRemoto ,final Long idExterno) {

		final Session session = getSession();
		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");


			final ProcedimientoRemoto procedimiento = RemotoUtils.recogerProcedimiento( session, idExterno, admin.getId() );

			if ( procedimiento != null ) {

				admin.removeProcedimientoRemoto(procedimiento);
				procedimiento.getNormativas().clear();
				procedimiento.getUnidadAdministrativa().removeProcedimientoLocal(procedimiento);
				Historico historico = getHistorico(session, procedimiento);
				( (HistoricoProcedimiento) historico ).setProcedimiento(null);

				for ( final Materia mat : procedimiento.getMaterias() )
					mat.getProcedimientosLocales().remove(procedimiento);

				for (final HechoVitalProcedimiento hecho : procedimiento.getHechosVitalesProcedimientos() )
					session.delete(hecho);

				session.delete(procedimiento);
				session.flush();

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Obtiene la iniciacion de un procedimiento dado su código estándar
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public Iniciacion obtenerIniciacion(final String codEstandarInciacion) {
		
		final Session session = getSession();
		try {	
			
			return RemotoUtils.recogerIniciacionCE(session, codEstandarInciacion);
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	//Para no hacer un facade de documento con dos metodos, se han decidido poner aqui.
	/**
	 * Obtiene un Documento Remoto apartir de su id externo y su id de la UARemota
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public DocumentoRemoto obtenerDocumentoRemoto(Long idExterno, Long idAdmin) {
		
		Session session = getSession();
		try {

			StringBuilder consulta = new StringBuilder(" from DocumentoRemoto as dr where dr.idExterno = :idExterno ");
			consulta.append(" and dr.administracionRemota.id = :idAdmin");
			
			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idExterno", idExterno);
			query.setParameter("idAdmin", idAdmin);
			
			DocumentoRemoto documento = (DocumentoRemoto) query.uniqueResult();
			if ( documento != null )
				Hibernate.initialize( documento.getProcedimiento() );

			return documento;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene un Documento Remoto segun su idExterno y el IdRemoto actualmente
	 * de la AdministracionRemota
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param 	idRemoto	Identificador remoto de un documento.
	 * 
	 * @param	idExtDoc	Identificador remoto de la administración.
	 */
	public DocumentoRemoto obtenerDocumentoRemoto(final String idRemoto, final Long idExtDoc) {
		
		final Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerDocumento( session, idExtDoc, admin.getId() );
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Crea o actualiza un Documento Remoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public Long grabarDocumentoRemoto(DocumentoRemoto documento, Long idProcedimiento, Long idFicha) {
		
		Session session = getSession();
		try {
			
			FichaRemota ficha = null;
			ProcedimientoRemoto procedimiento = null;
			if ( documento.getId() == null ) {
				
				if ( idFicha != null ) {
					
					ficha = (FichaRemota) session.load(FichaRemota.class, idFicha);
					ficha.addDocumento(documento);
					
				}
				
				if ( idProcedimiento != null ) {
					
					procedimiento = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, idProcedimiento);
					procedimiento.addDocumento(documento);
					
				}
				
				session.save(documento);
				
			} else {
				
				session.update(documento);
				
			}
			
			session.flush();
			
			return documento.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Borra un documento Remoto segun su Identificador externo
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public void borrarDocumentoRemoto(final String idRemoto ,final Long idExterno) {

		final Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			StringBuilder consulta = new StringBuilder(" from DocumentoRemoto as dr where dr.idExterno = :idExterno ");
			consulta.append(" and dr.administracionRemota.id = :idAdmin");
			
			Query query = session.createQuery("from DocumentoRemoto as dr where dr.idExterno="+idExterno+" and dr.administracionRemota.id="+ admin.getId());
			query.setParameter("idExterno", idExterno);
			query.setParameter("idAdmin", admin.getId());
			
			DocumentoRemoto documentoRemoto = (DocumentoRemoto) query.uniqueResult();

			if ( documentoRemoto != null ) {
				
				Hibernate.initialize( documentoRemoto.getProcedimiento() );
				ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load( ProcedimientoRemoto.class, documentoRemoto.getProcedimiento().getId() );
				procRemoto.removeDocumento(documentoRemoto);
				admin.removeDocumentoRemoto(documentoRemoto);

				session.delete(documentoRemoto);
				session.flush();
				
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}



}
