package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validable;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.persistence.intf.ProcedimientoFacade;
import org.ibit.rol.sac.persistence.intf.ProcedimientoFacadeHome;
import org.ibit.rol.sac.persistence.util.ProcedimientoFacadeUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular procedimientos.
 */
public class ProcedimientoDelegateImpl implements StatelessDelegate, ProcedimientoDelegateI {
	
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#grabarProcedimiento(org.ibit.rol.sac.model.ProcedimientoLocal, java.lang.Long)
	 */
    public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) throws DelegateException {
        try {
            return getFacade().grabarProcedimiento(procedimiento, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientos()
	 */
    public List listarProcedimientos() throws DelegateException {
        try {
            return getFacade().listarProcedimientos();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#obtenerProcedimiento(java.lang.Long)
	 */
    public ProcedimientoLocal obtenerProcedimiento(Long id) throws DelegateException {
        try {
            return getFacade().obtenerProcedimiento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#obtenerProcedimientoNewBack(java.lang.Long)
	 */
    public ProcedimientoLocal obtenerProcedimientoNewBack(Long id) throws DelegateException {
        try {
            return getFacade().obtenerProcedimientoNewBack(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientos(java.util.Map, java.util.Map)
	 */
    public List buscarProcedimientos(Map param, Map trad) throws DelegateException {
        try {
            return getFacade().buscarProcedimientos(param, trad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscadorProcedimientos(java.util.Map, java.util.Map, org.ibit.rol.sac.model.UnidadAdministrativa, boolean, boolean)
	 */
    public ResultadoBusqueda buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible, String en_plazo, String telematico) throws DelegateException {
        try {
            return getFacade().buscadorProcedimientos(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital, publicObjectiu, pagina, resultats, visible, en_plazo, telematico);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosMateria(java.lang.Long)
	 */
    public List buscarProcedimientosMateria(Long id) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosTexto(java.lang.String)
	 */
    public List buscarProcedimientosTexto(String texto) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosTexto(texto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosUATexto(java.lang.Long, java.lang.String)
	 */
    public List buscarProcedimientosUATexto(Long idUnidad, String texto) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosUATexto(idUnidad, texto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosUATexto(java.lang.Long, java.lang.String, java.lang.String)
	 */
    public List buscarProcedimientosUATexto(Long idUnidad, String texto, String idioma) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosUATexto(idUnidad, texto, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#anyadirTramite(java.lang.Long, java.lang.Long)
	 */
    public void anyadirTramite(Long tramite_id, Long proc_id) throws DelegateException {
        try {
            getFacade().anyadirTramite(tramite_id, proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#eliminarTramite(java.lang.Long, java.lang.Long)
	 */
    public void eliminarTramite(Long tramite_id, Long proc_id) throws DelegateException {
        try {
            getFacade().eliminarTramite(tramite_id, proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#borrarProcedimiento(java.lang.Long)
	 */
    public void borrarProcedimiento(Long id) throws DelegateException {
        try {
            getFacade().borrarProcedimiento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosUA(java.lang.Long)
	 */
    public List listarProcedimientosUA(Long id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosUO(java.lang.Long, java.lang.Integer)
	 */
    public List listarProcedimientosUO(Long id,Integer conse) throws DelegateException {
        try {
            return getFacade().listarProcedimientosUO(id,conse);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosPublicosUA(java.lang.Long)
	 */
    public List listarProcedimientosPublicosUA(Long id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosPublicosUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    private boolean publico(ProcedimientoLocal proc) {
    	final Date now = new Date();
    	boolean noCaducado = ((proc.getFechaCaducidad() == null) || proc.getFechaCaducidad().after(now));
    	boolean publicado = ((proc.getFechaPublicacion() == null) || proc.getFechaPublicacion().before(now));
    	return this.visible(proc) && noCaducado && publicado;
    }
    
    protected boolean visible(Validable validable) {
        return (validable.getValidacion().equals(Validacion.PUBLICA) || validable.getValidacion().equals(Validacion.RESERVA ));
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosPublicosUA(java.lang.Long)
	 */
    public List listarProcedimientosPublicos() throws DelegateException {
    	List procedimientosPublicos = new ArrayList();
    	try {
            List todosProcedimientos = getFacade().listarProcedimientos();
            for (Iterator i = todosProcedimientos.iterator(); i.hasNext();) {
            	ProcedimientoLocal pl = (ProcedimientoLocal) i.next();
            	if (this.publico(pl)) {
            		procedimientosPublicos.add(pl);
            	}
            }
        } catch (RemoteException e) {
        	throw new DelegateException(e);
        }
    	return procedimientosPublicos;
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosPublicosHechoVital(java.lang.Long)
	 */
    public List listarProcedimientosPublicosHechoVital(Long id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosPublicosHechoVital(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* PORMAD */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosPublicosUAHVMateria(java.lang.Long, java.lang.String[], java.lang.String[])
	 */
    @SuppressWarnings("unchecked")
	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
        try {
            return getFacade().listarIdsProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#consultarProcedimiento(java.lang.Long)
	 */
    public ProcedimientoLocal consultarProcedimiento(Long id) throws DelegateException {
        try {
            return getFacade().consultarProcedimiento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#obtenerFilterObject(org.ibit.rol.sac.model.ProcedimientoLocal)
	 */
	public ModelFilterObject obtenerFilterObject(ProcedimientoLocal proc) throws DelegateException {   
		try {
            return getFacade().obtenerFilterObject( proc );
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#indexInsertaProcedimiento(org.ibit.rol.sac.model.ProcedimientoLocal, org.ibit.lucene.indra.model.ModelFilterObject)
	 */
    public void indexInsertaProcedimiento(ProcedimientoLocal proc, ModelFilterObject filter) throws DelegateException {
        try {
            getFacade().indexInsertaProcedimiento(proc, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#indexBorraProcedimiento(org.ibit.rol.sac.model.ProcedimientoLocal)
	 */
    public void indexBorraProcedimiento(ProcedimientoLocal pro) throws DelegateException {
        try {
            getFacade().indexBorraProcedimiento(pro);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void actualizarOrdenTramites(ArrayList<Long> tramitesId) throws DelegateException {
    	try {
    		getFacade().actualizarOrdenTramites(tramitesId);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public boolean existeOtroTramiteInicioProcedimiento(Long procId, Long tramiteId) throws DelegateException {
        try {
            return getFacade().existeOtroTramiteInicioProcedimiento(procId, tramiteId);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosActivos(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.Date)
	 */
    public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosActivos(listaUnidadAdministrativaId, fechaCaducidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosCaducados(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.Date)
	 */
    public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
        try {
            return getFacade().listarProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    public ResultadoBusqueda buscadorProcedimientos(BuscadorProcedimientoCriteria bc) throws DelegateException {
        try {
            return getFacade().buscadorProcedimientos(bc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private ProcedimientoFacade getFacade() throws RemoteException {
        return (ProcedimientoFacade) facadeHandle.getEJBObject();
    }
    
    protected ProcedimientoDelegateImpl() throws DelegateException {
        try {
            ProcedimientoFacadeHome home = ProcedimientoFacadeUtil.getHome();
            ProcedimientoFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
}
