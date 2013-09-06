package org.ibit.rol.sac.persistence.delegate;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.persistence.intf.UARemotaFacade;
import org.ibit.rol.sac.persistence.intf.UARemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.UARemotaFacadeUtil;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EdificioRemoto;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.util.Set;
import java.util.List;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular fichas.
 */
public class UARemotaDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public Long grabarUARemota(UnidadAdministrativaRemota uaRemota, Long idPadre , String ceTratamiento ,String[] ceMaterias) throws DelegateException{
		try {
            return getFacade().grabarUARemota(uaRemota, idPadre, ceTratamiento,ceMaterias);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
	public Long grabarUARemota(final String idRemoto, UnidadAdministrativaRemota uaRemota, Long idPadre , String ceTratamiento ,String[] ceMaterias) throws DelegateException{
		try {
            return getFacade().grabarUARemota(idRemoto, uaRemota, idPadre, ceTratamiento,ceMaterias);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
	/** @deprecated No se usa. */
	public UnidadAdministrativaRemota obtenerUARemotaAdmin(final Long idAdmin, final Long idExtUA) throws DelegateException{
		try {
            return getFacade().obtenerUARemotaAdmin(idAdmin, idExtUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
	public UnidadAdministrativaRemota obtenerUARemota(final String idRemoto, final Long idExtUA) throws DelegateException{
		try {
            return getFacade().obtenerUARemota(idRemoto,idExtUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	

    public UnidadAdministrativaRemota obtenerUARemota(Long id) throws DelegateException{
		try {
            return getFacade().obtenerUARemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
	
	public AdministracionRemota obtenerAdministracionRemota(final String idRemoto) throws DelegateException{
		try {
            return getFacade().obtenerAdministracionRemota(idRemoto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
	/** @deprecated No se usa. */
	@SuppressWarnings("unchecked")
	public Set<UnidadAdministrativaRemota> listarUARemotas(final String idRemoto) throws DelegateException{
		try {
            return getFacade().listarUARemotas(idRemoto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	
	public void borrarUARemota(final String idRemoto, final Long idExtUA) throws DelegateException{
		try {
            getFacade().borrarUARemota(idRemoto,idExtUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	

	/** @deprecated No se usa. */
    public List<UnidadAdministrativaRemota> buscar(final String busqueda, final String idioma) throws DelegateException {
        try {
            return getFacade().buscar(busqueda, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }


    public List<UnidadAdministrativaRemota> listarUARemotasPorAmbito(Long ambito) throws DelegateException {
        try {
            return getFacade().listarUARemotasPorAmbito(ambito);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public void borrarUnidadAdministrativaRemota(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadAdministrativaRemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }
    

    public void borrarUnidadAdministrativaRaizRemota(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadAdministrativaRaizRemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public void indexInsertaUARemota(UnidadAdministrativa ua, ModelFilterObject filter) throws DelegateException {
        try {
            getFacade().indexInsertaUARemota(ua, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
     
    public EdificioRemoto obtenerEdificioRemoto(Long idExterno,Long idUaRemota) throws DelegateException {
        try {
            return getFacade().obtenerEdificioRemoto(idExterno, idUaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public EdificioRemoto obtenerEdificioRemoto(String idRemoto,Long idExtEdificio) throws DelegateException {
        try {
            return getFacade().obtenerEdificioRemoto(idRemoto, idExtEdificio);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public Long grabarEdificioRemoto(EdificioRemoto edificioRemoto) throws DelegateException {
        try {
            return getFacade().grabarEdificioRemoto(edificioRemoto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public List<Edificio> obtenerEdificiosUA(Long ua) throws DelegateException {
        try {
            return getFacade().obtenerEdificiosUA(ua);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public void borrarEdificioRemoto(Long id) throws DelegateException {
        try {
            getFacade().borrarEdificioRemoto(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    
    public void borrarEdificioRemoto(String idRemoto,Long idExt) throws DelegateException {
        try {
            getFacade().borrarEdificioRemoto(idRemoto,idExt);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    
    public void eliminarUnidad(String idRemoto,Long idEdif, Long idUA) throws DelegateException {
        try {
            getFacade().eliminarUnidad(idRemoto,idEdif,idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    
    /** @deprecated No se usa. */
    public List<Object> listarNovedadesPMA(int length, boolean caducados, Long idRemota) throws DelegateException {
        try {
            return getFacade().listarNovedadesPMA(length, caducados, idRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    /** @deprecated No se usa. */
    public List<Object> listarDestacadosPMA(int length, boolean caducados, Long idRemota) throws DelegateException {
        try {
            return getFacade().listarDestacadosPMA(length, caducados, idRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private UARemotaFacade getFacade() throws RemoteException {
        return (UARemotaFacade) facadeHandle.getEJBObject();
    }

    protected UARemotaDelegate() throws DelegateException {
        try {
            UARemotaFacadeHome home = UARemotaFacadeUtil.getHome();
            UARemotaFacade remote = home.create();
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
