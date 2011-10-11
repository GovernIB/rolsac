package org.ibit.rol.sac.persistence.delegate;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.webcaib.ActuacioMinModel;
import org.ibit.rol.sac.model.webcaib.ActuacioModel;
import org.ibit.rol.sac.persistence.intf.ProcedimientoFacade;
import org.ibit.rol.sac.persistence.intf.ProcedimientoFacadeHome;
import org.ibit.rol.sac.persistence.util.ProcedimientoFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public List buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves) throws DelegateException {
        try {
            return getFacade().buscadorProcedimientos(parametros, traduccion, ua, uaFilles, uaMeves);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscarProcedimientosFamilia(java.lang.Long)
	 */
    public List buscarProcedimientosFamilia(Long id) throws DelegateException {
        try {
            return getFacade().buscarProcedimientosFamilia(id);
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
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#anyadirNormativa(java.lang.Long, java.lang.Long)
	 */
    public void anyadirNormativa(Long norm_id, Long proc) throws DelegateException {
        try {
            getFacade().anyadirNormativa(norm_id, proc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#eliminarNormativa(java.lang.Long, java.lang.Long)
	 */
    public void eliminarNormativa(Long norm_id, Long proc_id) throws DelegateException {
        try {
            getFacade().eliminarNormativa(norm_id, proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#anyadirMateria(java.lang.Long, java.lang.Long)
	 */
    public void anyadirMateria(Long materia_id, Long proc_id) throws DelegateException {
        try {
            getFacade().anyadirMateria(materia_id, proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#eliminarMateria(java.lang.Long, java.lang.Long)
	 */
    public void eliminarMateria(Long materia_id, Long proc_id) throws DelegateException {
        try {
            getFacade().eliminarMateria(materia_id, proc_id);
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
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#actualizarOrdenPros3(java.util.Map)
	 */
    public void actualizarOrdenPros3(Map map) throws DelegateException {
        try {
            getFacade().actualizarOrdenPros3(map);
        } catch (RemoteException e) { 
            throw new DelegateException(e);
        }
    }
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#actualizarOrdenPros2(java.util.Map)
	 */
    public void actualizarOrdenPros2(Map map) throws DelegateException {
        try {
            getFacade().actualizarOrdenPros2(map);
        } catch (RemoteException e) { 
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#actualizarOrdenPros(java.util.Map)
	 */
    public void actualizarOrdenPros(Map map) throws DelegateException {
        try {
            getFacade().actualizarOrdenPros(map);
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

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosHechoVital(java.lang.Long)
	 */
    public List listarProcedimientosHechoVital(Long hecho_id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosHechoVital(hecho_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
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
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
        try {
            return getFacade().listarProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
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

    /* PORMAD*/
   /* public List listarProcedimientosHechoVitalUA(Long hecho_id, Long ua_id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosHechoVitalUA(hecho_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }*/

     /* PORMAD*/
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#listarProcedimientosMateriaUA(java.lang.Long, java.lang.Long)
	 */
    public List listarProcedimientosMateriaUA(Long materia_id, Long ua_id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosMateriaUA(materia_id, ua_id);
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
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#buscar(java.lang.String, java.lang.String)
	 */
    public List<ProcedimientoLocal> buscar(final String busqueda, final String idioma) throws DelegateException {
    	try {
            return getFacade().buscar(busqueda,idioma);
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
    
    public void actualizarOrdenTramites(Map params) throws DelegateException {
    	   try {
               getFacade().actualizarOrdenTramites(params);
           } catch (RemoteException e) {
               throw new DelegateException(e);
           }    
        }
    
    public boolean autorizaCrearProcedimiento(Integer validacionProcedimiento) throws DelegateException {
 	   try {
           return getFacade().autorizaCrearProcedimiento(validacionProcedimiento);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }
    
    public boolean autorizaModificarProcedimiento(Long idProcedimiento) throws DelegateException {
 	   try {
           return getFacade().autorizaModificarProcedimiento(idProcedimiento);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }
    
    public ProcedimientoLocal obtenerProcedimientoPM(Long id) throws DelegateException {
        try {
            return getFacade().obtenerProcedimientoPM(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    

    //WEBCAIB//
    
    public ActuacioModel getActuacio ( String code, String idioma, String previ ) throws DelegateException {
        try {
            return getFacade().getActuacio(code, idioma, previ);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    
    
    public Collection actuacionsByMateria ( Long codiMateria, String idioma ) throws DelegateException {
        try {
            return getFacade().actuacionsByMateria(codiMateria, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } 
    }
    
    
    public Collection actuacionsByUORSS ( Long codiUO, String idioma ) throws DelegateException {
        try {
            return getFacade().actuacionsByUORSS(codiUO, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    
    public Collection actuacionsByUO ( Long codiUO, String idioma ) throws DelegateException {
        try {
            return getFacade().actuacionsByUO(codiUO, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }    
    
    
    public Collection actuacionsMasVisto () throws DelegateException {
        try {
            return getFacade().actuacionsMasVisto();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Collection actuacionsByWord ( String words, String idioma, String solovigor ) throws DelegateException {
        try {
            return getFacade().actuacionsByWord(words, idioma, solovigor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } 
    }
    
    public Integer cuentaActuacionsByUO ( Long codiUO, String idioma ) throws DelegateException {
        try {
            return getFacade().cuentaActuacionsByUO(codiUO, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Integer cuentaActuacionsByFamiliaUO ( Long codiFamilia, Long coduo, String idioma ) throws DelegateException {
        try {
            return getFacade().cuentaActuacionsByFamiliaUO(codiFamilia, coduo, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }     	
    }
    
    public Collection actuacionsByAvanzado ( String condi, String idioma, String uo, String solovigor, String idisel ) throws DelegateException {
        try {
            return getFacade().actuacionsByAvanzado(condi, idioma, uo, solovigor, idisel);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }     	
    }
    
    public Collection actuacionsByFamilia ( Long codiFamilia, String idioma ) throws DelegateException {
        try {
            return getFacade().actuacionsByFamilia(codiFamilia, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Collection<ActuacioMinModel> actuacionsByFamiliaUO ( Long codiFamilia, Long coduo, String idioma ) throws DelegateException {
        try {
            return getFacade().actuacionsByFamiliaUO(codiFamilia, coduo, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }      	
    }
    
    public Collection actuacionsByFamiliaMat( Long codiFamilia, Long codiMateria, String idioma ) throws DelegateException {
        try {
            return getFacade().actuacionsByFamiliaMat(codiFamilia, codiMateria, idioma);
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
