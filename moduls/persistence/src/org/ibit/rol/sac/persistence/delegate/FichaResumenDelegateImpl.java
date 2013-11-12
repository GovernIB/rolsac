package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.intf.FichaResumenFacade;
import org.ibit.rol.sac.persistence.intf.FichaResumenFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaResumenFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

public class FichaResumenDelegateImpl implements StatelessDelegate, FichaResumenDelegateI
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    /* (non-Javadoc)
     * @see org.ibit.rol.sac.persistence.delegate.FichaResumenDelegateI#buscarFichas(java.util.Map, java.util.Map, org.ibit.rol.sac.model.UnidadAdministrativa, boolean, boolean)
     */
    public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible) throws DelegateException {
        try {
            return getFacade().buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles, uaMeves, campoOrdenacion, orden, pagina, resultats, campoVisible);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private FichaResumenFacade getFacade() throws RemoteException {
        return (FichaResumenFacade) facadeHandle.getEJBObject();
    }
    
    protected FichaResumenDelegateImpl() throws DelegateException {
        try {
            FichaResumenFacadeHome home = FichaResumenFacadeUtil.getHome();
            FichaResumenFacade remote = home.create();
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
