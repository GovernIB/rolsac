package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.persistence.intf.NormativaFacade;
import org.ibit.rol.sac.persistence.intf.NormativaFacadeHome;
import org.ibit.rol.sac.persistence.util.NormativaFacadeUtil;

/**
 * Business delegate para manipular normativas.
 */
public class NormativaDelegate implements StatelessDelegate {
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public boolean autorizaCrearNormativa(Integer validacionNormativa) throws DelegateException {
        try {
            return getFacade().autorizaCrearNormativa(validacionNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public boolean autorizaModificarNormativa(Long idNormativa) throws DelegateException {
        try {
            return getFacade().autorizaModificarNormativa(idNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }      
	
    public Long grabarNormativaLocal(NormativaLocal norma, Long idUA) throws DelegateException {
        try {
            return getFacade().grabarNormativaLocal(norma, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Long grabarNormativaExterna(NormativaExterna norma) throws DelegateException {
        try {
            return getFacade().grabarNormativaExterna(norma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarNormativas() throws DelegateException {
        try {
            return getFacade().listarNormativas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Normativa obtenerNormativa(Long id) throws DelegateException {
        try {
            return getFacade().obtenerNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarNormativas(Map parametros, Map traduccion, String tipo) throws DelegateException {
        try {
            return getFacade().buscarNormativas(parametros, traduccion, tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List buscarNormativas(Map parametros, Map traduccion, String tipo, Long idUA, boolean uaMeves, boolean uaFilles, String campoOrdenacion, String orden) throws DelegateException {
        try {
            return getFacade().buscarNormativas(parametros, traduccion, tipo, idUA, uaMeves, uaFilles, campoOrdenacion, orden);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    

    public List buscarNormativas(String texto) throws DelegateException {
        try {
            return getFacade().buscarNormativas(texto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarNormativasTipo(Long id, String tipo) throws DelegateException {
        try {
            return getFacade().buscarNormativasTipo(id, tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarNormativasBoletin(Long id, String tipo) throws DelegateException {
        try {
            return getFacade().buscarNormativasBoletin(id, tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarNormativasUA(Long id, String tipo) throws DelegateException {
        try {
            return getFacade().buscarNormativasUA(id, tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarNormativasNoRelacionadas(String tipo) throws DelegateException {
        try {
            return getFacade().buscarNormativasNoRelacionadas(tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void anyadirAfectacion(Long normativaAfectada_id, Long tipafec_id, Long normativaQueAfecta_id) throws DelegateException {
        try {
            getFacade().anyadirAfectacion(normativaAfectada_id, tipafec_id, normativaQueAfecta_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void eliminarAfectacion(Long normativaQueAfecta_id, Long tipoAfec_id, Long normativaAeliminar_id) throws DelegateException {
        try {
            getFacade().eliminarAfectacion(normativaQueAfecta_id, tipoAfec_id, normativaAeliminar_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void anyadirProcedimiento(Long proc_id, Long norm_id) throws DelegateException {
        try {
            getFacade().anyadirProcedimiento(proc_id, norm_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void eliminarProcedimiento(Long proc_id, Long norm_id) throws DelegateException {
        try {
            getFacade().eliminarProcedimiento(proc_id, norm_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerArchivoNormativa(Long id, String lang, boolean useDefault) throws DelegateException {
        try {
            return getFacade().obtenerArchivoNormativa(id, lang, useDefault);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarNormativa(Long id) throws DelegateException {
        try {
            getFacade().borrarNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarNormativasUA(Long id) throws DelegateException {
        try {
            return getFacade().listarNormativasUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public ModelFilterObject obtenerFilterObject(Normativa norma) throws DelegateException {   
		try {
            return getFacade().obtenerFilterObject( norma );
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public void indexBorraNormativa(Normativa nor) throws DelegateException {
		try {
            getFacade().indexBorraNormativa(nor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }  
    	
   	public void indexInsertaNormativa(Normativa norma, ModelFilterObject filter) throws DelegateException {   
		try {
            getFacade().indexInsertaNormativa(norma, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }  
   	
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private NormativaFacade getFacade() throws RemoteException {
        return (NormativaFacade) facadeHandle.getEJBObject();
    }

    protected NormativaDelegate() throws DelegateException {
        try {
            NormativaFacadeHome home = NormativaFacadeUtil.getHome();
            NormativaFacade remote = home.create();
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
