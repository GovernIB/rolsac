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
public abstract class AdministracionRemotaFacadeEJB extends HibernateEJB {

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
	 */
	public Long grabarAdministracionRemota(
			AdministracionRemota administracionRemota) {
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
	 * Lista todas las Administraciones Remotas (nuevo backoffice).
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda listarAdministracionRemota(int pagina, int resultats) {
		return listarTablaMaestraPaginada(pagina, resultats, listarAdministracionRemota());
	}
	
	/**
	 * Lista todas las Administraciones Remotas.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public List<AdministracionRemota> listarAdministracionRemota() {
		Session session = getSession();
		try {
			Criteria criteri = session
					.createCriteria(AdministracionRemota.class);
			return criteri.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una AdministracionRemota
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public AdministracionRemota obtenerAdministracionRemota(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
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
	 * Obtiene una AdministracionRemota por su IdRemoto
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public AdministracionRemota obtenerAdministracionRemota(String idRemoto) {
		Session session = getSession();
		try {
			Query query =  session.createQuery("select admin from AdministracionRemota as admin where admin.idRemoto=:idRemoto");
			query.setString("idRemoto", idRemoto);
			return (AdministracionRemota)query.uniqueResult();
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
	 */
	public void borrarAdministracionRemota(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
			session.delete(administracionRemota);
			session.flush();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void alta() {
		Session session = getSession();
		try {
			FichaRemota fichaRemota = new FichaRemota();
			fichaRemota.setFechaCaducidad(new Date());
			fichaRemota.setFechaActualizacion(new Date());			
			TraduccionFichaRemota remota = new TraduccionFichaRemota();
			remota.setTitulo("titulo");
			remota.setUrl("url");
			remota.setUrlRemota("urlRemota");
			remota.setDescripcion("descripcion");
			fichaRemota.setTraduccion("es", remota);
			session.saveOrUpdate(fichaRemota);
			session.flush();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista todas las Fichas de una AR.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<FichaRemota> listarFichasRemotas(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getFichasRemotas());
			return administracionRemota.getFichasRemotas();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista todos los Procedimientos de una AR.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<ProcedimientoRemoto> listarProcedimientosRemotos(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota
					.getProcedimientosRemotos());
			return administracionRemota.getProcedimientosRemotos();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Lista todos los Edificios de una AR.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<EdificioRemoto> listarEdificiosRemotos(Long id) {

		Session session = getSession();
		try {

			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);

			Hibernate.initialize(administracionRemota.getEdificiosRemotos());

			return administracionRemota.getEdificiosRemotos();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Lista todos los tramites de una AR.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<TramiteRemoto> listarTramitesRemotos(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getTramitesRemotos());
			return administracionRemota.getTramitesRemotos();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


    /**
	 * Lista todas las normativas de una AR.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<NormativaExternaRemota> listarNormativasExternasRemotas(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getNormativasExternasRemotas());
			return administracionRemota.getNormativasExternasRemotas();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista todos las UA de una AR.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<UnidadAdministrativaRemota> listarUARemotas(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
					.load(AdministracionRemota.class, id);
			Hibernate.initialize(administracionRemota.getUnidadesRemotas());
			return administracionRemota.getUnidadesRemotas();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Mira la AdministracionRemota ha sido inizializada
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public boolean isEmpty(Long id) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = (AdministracionRemota) session
				.load(AdministracionRemota.class, id);
			return administracionRemota.getUnidadesRemotas().isEmpty();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Mira la AdministracionRemota ha sido inizializada
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public boolean isEmpty(String idRemoto) {
		Session session = getSession();
		try {
			AdministracionRemota administracionRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if(administracionRemota==null)
	    		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			return administracionRemota.getUnidadesRemotas().isEmpty();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Obtiene el logop de una {@link AdministracionRemota}
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Archivo obtenerLogop(Long id) {
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
     * Obtiene el logog de una {@link AdministracionRemota}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerLogog(Long id) {
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
	
	/**
     * Borra un Logop de una {@link AdministracionRemota} determinado.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarLogop(Long id) {
    	Session session = getSession();
    	try {
    		AdministracionRemota admin = (AdministracionRemota) session.load(AdministracionRemota.class, id);
    		session.delete(admin.getLogop());
    		admin.setLogop(null);
    		session.flush();
    	} catch (HibernateException e) {
    		throw new EJBException(e);
    	} finally {
    		close(session);
    	}
    }
    
    /**
     * Borra un Logog de una {@link AdministracionRemota} determinado.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarLogog(Long id) {
    	Session session = getSession();
    	try {
    		AdministracionRemota admin = (AdministracionRemota) session.load(AdministracionRemota.class, id);
    		session.delete(admin.getLogog());
    		admin.setLogog(null);
    		session.flush();
    	} catch (HibernateException e) {
    		throw new EJBException(e);
    	} finally {
    		close(session);
    	}
    }
    
    
}
