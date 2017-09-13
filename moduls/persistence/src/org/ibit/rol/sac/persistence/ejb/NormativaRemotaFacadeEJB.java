package org.ibit.rol.sac.persistence.ejb;

import java.util.List;
import java.util.Iterator;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.util.RemotoUtils;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

/**
 * SessionBean para mantener y consultar Normativa Remota.
 *
 * @ejb.bean
 *  name="sac/persistence/NormativaRemotaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.NormativaRemotaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class NormativaRemotaFacadeEJB extends HibernateEJB {

	/** Serial Version UID. **/
	private static final long serialVersionUID = 1L;


	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea una Normativa Remota
	 * 
	 * @throws HibernateException 
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param normativaExtRemota	Indica la normativaRemota a guardar.
	 * 
	 * @return Devuelve el identificador de la normativa  remota.
	 */
	public Long grabarNormativaRemota(final NormativaRemota normativaExtRemota) {

		Session session = getSession();

		try {     	

			if( normativaExtRemota != null ) {

				if ( normativaExtRemota.getId() != null ) {

					session.update(normativaExtRemota);

				} else {

					session.save(normativaExtRemota);

				}

				session.flush();

			}

			return normativaExtRemota.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un Normativa Remota a partir de su identificador externo y elo identificador de la UARemota.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idExterno	Indica el identificador externo de la normativa  remota.
	 * 
	 * @param	idUaRemota	Indica el identificador de la unidad administrativa remota.
	 * 
	 * @return Devuelve <code>NormativaRemota</code> solicitada.
	 */
	public NormativaRemota obtenerNormativaRemota(Long idExterno,Long idUaRemota) {

		Session session = getSession();
		try {

			StringBuilder consulta = new StringBuilder(" from NormativaRemota as nr ");
			consulta.append(" where nr.idExterno = :idExterno ");
			consulta.append(" and nr.administracionRemota.id = :idUARemota ");

			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idExterno", idExterno);
			query.setParameter("idUARemota", idUaRemota);
			NormativaRemota normativa = (NormativaRemota )query.uniqueResult();

			if ( normativa != null )
				Hibernate.initialize( normativa.getProcedimientos() );

			return normativa;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene una Normativa Remota segun su idExterno y el IdRemoto actualmente
	 * de la AdministracionRemota
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador remoto
	 * 
	 * @param idExtNorm	Identificador de la normativa .
	 * 
	 * @return Devuelve <code>NormativaRemota</code> solicitada.
	 */
	public NormativaRemota obtenerNormativaRemota(final String idRemoto, final Long idExtNorm) {

		final Session session = getSession();

		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null ) {
				throw new EJBException("No existe ninguna Administración Remota asociada al idRemoto");
			}
			
			return RemotoUtils.recogerNormativaRemota( session, idExtNorm, admin.getId() );

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene las Normativas de un procedimiento.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idProcedimiento	Indentificador del procedimiento.
	 * 
	 * @return <code>List<Normativa></code> de las normativas del procedimiento solicitado.
	 */
	public List<Normativa> obtenerNormativasProcedimiento(Long idProcedimiento) {

		Session session = getSession();
		try {
			
			StringBuilder consulta = new StringBuilder("select elements(proc.normativas) ");
			consulta.append(" from ProcedimientoLocal as proc ");
			consulta.append(" where proc.id = :idProcedimiento ");
			
			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idProcedimiento", idProcedimiento);
			
			List<Normativa> normativas = query.list();
			for( Normativa normativa : normativas ) {

				for ( final String idioma : normativa.getLangs() ) {
					
					log.debug( "entra: " + normativa.getId() );
					final TraduccionNormativa traduccion = (TraduccionNormativa) normativa.getTraduccion(idioma);
					if ( traduccion != null )
						Hibernate.initialize( traduccion.getArchivo() );
					
				}
				
			}
			
			return normativas;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Añade un nuevo procedimiento a la normativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idProcedimiento	Identificador de un procedimiento.
	 * 
	 * @param	idNormativa	Identificador de una normativa.
	 */
	public void anyadirProcedimiento( Long idProcedimiento, Long idNormativa) {

		Session session = getSession();
		try {

			NormativaRemota normativa = (NormativaRemota) session.load(NormativaRemota.class, idNormativa);
			ProcedimientoRemoto procedimiento = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, idProcedimiento);
			
			procedimiento.getNormativas().add(normativa);
			normativa.getProcedimientos().add(procedimiento);
			
			session.flush();
			
		} catch (HibernateException e) {
			
			throw new EJBException(e);
			
		} finally {
			
			close(session);
			
		}
		
	}
	

	/**
	 * Borra una normativa remota. Se duplica paro no tener roles.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de la normativa remota.
	 */
	public void borrarNormativaRemota(Long id) {
		
		Session session = getSession();
		
		try {
			
			Normativa normativa = (Normativa) session.load(Normativa.class, id);
			Iterator iterator = normativa.getProcedimientos().iterator();
			while ( iterator.hasNext() ) {
				
				ProcedimientoLocal procedimiento = (ProcedimientoLocal) iterator.next();
				procedimiento.getNormativas().remove(normativa);
				
			}
			
			if ( normativa instanceof NormativaRemota ) {
				
				AdministracionRemota admin = ( (NormativaRemota) normativa ).getAdministracionRemota();
				if ( admin != null )
					admin.removeNormativaRemota( (NormativaRemota) normativa);
				
			}
			
			session.delete(normativa);
			session.flush();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Borra una Normativa Remota según su identificador externo.
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador de  la normativa remota.
	 * 
	 * @param idExterno	Identificador externo de la normativa.
	 */
	public void borrarNormativaRemota(final String idRemoto, final Long idExterno) {

		final Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			final NormativaRemota Remota = obtenerNormativaRemota( idExterno, admin.getId() );

			if ( Remota != null ) {
				
				admin.removeNormativaRemota(Remota);
				session.delete(Remota);
				session.flush();
				
			}
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Elimina un procedimiento de la normativa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador de la normativa remota.
	 * 
	 * @param ideNormativa	Identificador de la normativa.
	 * 
	 * @param idProcedimiento	Identificador del procedimiento.
	 * */
	public void eliminarProcNormativa(String idRemoto, Long ideNormativa, Long idProcedimiento) {
		
		Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			ProcedimientoRemoto procedimiento = RemotoUtils.recogerProcedimiento( session, idProcedimiento, admin.getId() );
			NormativaRemota ner = RemotoUtils.recogerNormativaRemota( session, ideNormativa, admin.getId() );

			procedimiento.getNormativas().remove(ner);
			ner.getProcedimientos().remove(procedimiento);
			session.flush();

		} catch (HibernateException e) {
			
			throw new EJBException(e);
			
		} finally {
			
			close(session);
			
		}
		
	}	
	
}
