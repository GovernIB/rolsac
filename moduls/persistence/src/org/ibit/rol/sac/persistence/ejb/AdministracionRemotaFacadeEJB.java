package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.EdificioRemoto;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.NormativaExternaRemota;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionFichaRemota;
import org.ibit.rol.sac.model.TramiteRemoto;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.persistence.util.RemotoUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar las AdministracionRemota (PORMAD)
 * 
 * @ejb.bean name="sac/persistence/AdministracionRemotaFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.AdministracionRemotaFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class AdministracionRemotaFacadeEJB extends HibernateEJB
{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}
	
	
	/**
	 * Crea o actualiza un AdministracionRemota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param administracionRemota Entidad administracionRemota a guardar
	 * @return Identificador de la administración remota guardada.
	 */
	public Long grabarAdministracionRemota(AdministracionRemota administracionRemota)
	{
		Session session = getSession();
		try {
			session.saveOrUpdate(administracionRemota);
			session.flush();
			return administracionRemota.getId();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Lista todas las Administraciones Remotas
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param pagina	Número de página actual.
	 * @param resultats	Cantidad de resultados por página.
	 * @return  Devuelve <code>ResultadoBusqueda</code> que contiene una lista de todas las Administraciones  remotas.
	 */
	public ResultadoBusqueda listarAdministracionRemota(int pagina, int resultats)
	{
		return listarTablaMaestraPaginada(pagina, resultats, listarTablaMaestraAdministracionRemota());
	}
	
	
	/**
	 * Lista todas las Administraciones remotas (menú Administración).
	 * 
	 * @return Devuelve un <code>List</code>.
	 */
	private List listarTablaMaestraAdministracionRemota()
	{
		Session session = getSession();
		try {
			Query query = session.createQuery(
					"select admRemota.id, " +
					"		admRemota.idRemoto, " +
					"		admRemota.nombre " +
					"from AdministracionRemota as admRemota " +
					"order by admRemota.nombre asc");
			
			return query.list();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>AdministracionRemota</code>.
	 */
	public AdministracionRemota obtenerAdministracionRemota(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getLogog());
			Hibernate.initialize(administracionRemota.getLogop());
			Hibernate.initialize(administracionRemota.getUnidadesRemotas());
			
			return administracionRemota;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Borra una AdministracionRemota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param id	Identificador de una administración remota
	 */
	public void borrarAdministracionRemota(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			session.delete(administracionRemota);
			session.flush();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todas las fichas de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Set<FichaRemota></code> de todas las fichas de una administración remota almacenadas.
	 */
	@SuppressWarnings("unchecked")
	public Set<FichaRemota> listarFichasRemotas(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getFichasRemotas());
			
			return administracionRemota.getFichasRemotas();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todos los procedimientos de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Set<ProcedimientoRemoto></code> de todos los procedimientos de una administración remota.
	 */
	@SuppressWarnings("unchecked")
	public Set<ProcedimientoRemoto> listarProcedimientosRemotos(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getProcedimientosRemotos());
			
			return administracionRemota.getProcedimientosRemotos();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todos los edificios remotos de todos los edificios de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Set<EdificioRemoto></code> de todos los edificios de una administración remota.
	 */
	@SuppressWarnings("unchecked")
	public Set<EdificioRemoto> listarEdificiosRemotos(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getEdificiosRemotos());
			
			return administracionRemota.getEdificiosRemotos();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todos los trámites de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Set<TramiteRemoto></code> de todos los trámites de una administración remota.
	 */
	@SuppressWarnings("unchecked")
	public Set<TramiteRemoto> listarTramitesRemotos(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getTramitesRemotos());
			
			return administracionRemota.getTramitesRemotos();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todas las normativas externas remotas.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Set<NormativaExternaRemota></code> de normativas externas remotas.
	 */
	@SuppressWarnings("unchecked")
	public Set<NormativaExternaRemota> listarNormativasExternasRemotas(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getNormativasExternasRemotas());
			
			return administracionRemota.getNormativasExternasRemotas();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todas las unidades administrativas de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Set<UnidadAdministrativaRemota></code> de todas las unidades administrativas de una administración remota. 
	 */
	@SuppressWarnings("unchecked")
	public Set<UnidadAdministrativaRemota> listarUARemotas(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getUnidadesRemotas());
			
			return administracionRemota.getUnidadesRemotas();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Valida que la AdministracionRemota haya sido inizializada.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>true</code> si no se ha encontrado ninguna unidad remota
	 */
	public boolean isEmpty(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			
			return administracionRemota.getUnidadesRemotas().isEmpty();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Valida que la AdministracionRemota haya sido inizializada
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>true</code> si no ha encontrado ninguna unidad remota.
	 */
	public boolean isEmpty(String idRemoto)
	{
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if (administracionRemota == null) {
				throw new EJBException("No existe ninguna Administración Remota asociada al idRemoto");
			}
			
			return administracionRemota.getUnidadesRemotas().isEmpty();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene logop de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Archivo</code> con el logop de una administración remota.
	 */
	public Archivo obtenerLogop(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota admin = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(admin.getLogop());
			
			return admin.getLogop();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene logog de una administración remota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de una administración remota.
	 * @return Devuelve <code>Archivo</code> con el logog de una administración remota.
	 */
	public Archivo obtenerLogog(Long id)
	{
		Session session = getSession();
		try {
			AdministracionRemota admin = (AdministracionRemota) session.load(AdministracionRemota.class, id);
			Hibernate.initialize(admin.getLogog());
			
			return admin.getLogog();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
}
