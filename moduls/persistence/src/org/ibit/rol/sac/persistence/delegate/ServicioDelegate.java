package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validable;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorServicioCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.ServicioFacade;
import org.ibit.rol.sac.persistence.intf.ServicioFacadeHome;
import org.ibit.rol.sac.persistence.util.ServicioFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Session;
/**
 * Business delegate para manipular servicios.
 */
public class ServicioDelegate implements StatelessDelegate {
	
	private static final long serialVersionUID = 1L;
	
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#grabarServicio(org.ibit.rol.sac.model.Servicio, java.lang.Long)
	 */
    public Long grabarServicio(Servicio servicio, Long idUA) throws DelegateException {
        try {
            return getFacade().grabarServicio(servicio, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
   	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#reordenarDocumentos(java.lang.Long, java.util.List)
   	 */
       public void reordenarDocumentos(final Long idServicio, final List<Long> idDocumentos) throws DelegateException {
           try {
                getFacade().reordenarDocumentos(idServicio, idDocumentos);
           } catch (RemoteException e) {
               throw new DelegateException(e);
           }
       }
       
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#obtenerServicio(java.lang.Long)
	 */
    public Servicio obtenerServicio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerServicio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
     * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#obtenerServicioParaSolr(java.lang.Long)
    	 */
    public Servicio obtenerServicioParaSolr(Long id, Session iSession) throws DelegateException {
    	try {
          return getFacade().obtenerServicioParaSolr(id, iSession);
    	} catch (RemoteException e) {
           throw new DelegateException(e);
      }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#obtenerServicioNewBack(java.lang.Long)
	 */
    public Servicio obtenerServicioNewBack(Long id) throws DelegateException {
        try {
            return getFacade().obtenerServicioNewBack(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscarServicios(java.util.Map, java.util.Map)
	 */
    public List buscarServicios(Map param, Map trad) throws DelegateException {
        try {
            return getFacade().buscarServicios(param, trad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscadorServicios(java.util.Map, java.util.Map, org.ibit.rol.sac.model.UnidadAdministrativa, boolean, boolean)
	 */
    public ResultadoBusqueda buscadorServicios(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible, String en_plazo, String telematico) throws DelegateException {
        try {
            return getFacade().buscadorServicios(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital, publicObjectiu, pagina, resultats, visible, en_plazo, telematico);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscarServiciosMateria(java.lang.Long)
	 */
    public List buscarServiciosMateria(Long id) throws DelegateException {
        try {
            return getFacade().buscarServiciosMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#borrarServicio(java.lang.Long)
	 */
    public void borrarServicio(Long id) throws DelegateException {
        try {
            getFacade().borrarServicio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#listarServiciosUA(java.lang.Long)
	 */
    public List listarServiciosUA(Long id) throws DelegateException {
        try {
            return getFacade().listarServiciosUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public boolean publico(Servicio servicio) {
    	final Date now = new Date();
    	boolean noCaducado = ((servicio.getFechaDespublicacion() == null) || servicio.getFechaDespublicacion().after(now));
    	boolean publicado = ((servicio.getFechaPublicacion() == null) || servicio.getFechaPublicacion().before(now));
    	return this.visible(servicio) && noCaducado && publicado;
    }
    
    protected boolean visible(Validable validable) {
        return (validable.getValidacion().equals(Validacion.PUBLICA) || validable.getValidacion().equals(Validacion.RESERVA ));
    }
   
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#listarServiciosPublicosHechoVital(java.lang.Long)
	 */
    public List listarServiciosPublicosHechoVital(Long id) throws DelegateException {
        try {
            return getFacade().listarServiciosPublicosHechoVital(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* PORMAD */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#listarServiciosPublicosUAHVMateria(java.lang.Long, java.lang.String[], java.lang.String[])
	 */
    @SuppressWarnings("unchecked")
	public List<Long> listarIdsServiciosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
        try {
            return getFacade().listarIdsServiciosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#consultarServicio(java.lang.Long)
	 */
    public Servicio consultarServicio(Long id) throws DelegateException {
        try {
            return getFacade().consultarServicio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscarServiciosActivos(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.Date)
	 */
    public int buscarServiciosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
        try {
            return getFacade().buscarServiciosActivos(listaUnidadAdministrativaId, fechaCaducidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscarServiciosCaducados(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.Date)
	 */
    public int buscarServiciosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
        try {
            return getFacade().buscarServiciosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public List<Servicio> listarServiciosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
        try {
            return getFacade().listarServiciosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    public ResultadoBusqueda buscadorServicios(BuscadorServicioCriteria bc) throws DelegateException {
        try {
            return getFacade().buscadorServicios(bc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public boolean isServicioConEstadoPublicacionPublica(Long idServicio) throws DelegateException {
    	try {
            return getFacade().isServicioConEstadoPublicacionPublica(idServicio);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().indexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException {
    	try {
            return getFacade().indexarSolr(solrIndexer, idElemento, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().desindexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private ServicioFacade getFacade() throws RemoteException {
        return (ServicioFacade) facadeHandle.getEJBObject();
    }
    
    protected ServicioDelegate() throws DelegateException {
        try {
            ServicioFacadeHome home = ServicioFacadeUtil.getHome();
            ServicioFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public List<Long> buscarIdsServicios() throws DelegateException {
		try {
            return getFacade().buscarIdsServicios();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	public List<Long> listarServiciosOrganoResolutori(Long idOrganoResolutori) throws DelegateException {
		try {
            return getFacade().listarServiciosOrganoResolutori(idOrganoResolutori);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public List<Servicio> listarServiciosNormativa(Long idElemento) throws DelegateException {
		try {
            return getFacade().listarServiciosNormativa(idElemento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	public void actualizarServicio(Servicio proc) throws DelegateException {
		try {
            getFacade().actualizarServicio(proc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public List<Long> getServiciosEstadoSIAAlterado() throws DelegateException {
		try {
           return getFacade().getServiciosEstadoSIAAlterado();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
	/**
	 * Devuelve true si alguna normativa esta derogada.
	 */
	public boolean isNormativaDerogada(Long id)   throws DelegateException {
		try {
	           return getFacade().isNormativaDerogada(id);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}

	public ResultadoBusqueda consultaServicios(FiltroGenerico filtro)  throws DelegateException {
		try {
	           return getFacade().consultaServicios(filtro);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}
    
	
}
