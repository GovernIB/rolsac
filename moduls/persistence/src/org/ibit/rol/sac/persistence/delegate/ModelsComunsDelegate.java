package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.persistence.intf.ModelsComunsFacade;
import org.ibit.rol.sac.persistence.intf.ModelsComunsFacadeHome;
import org.ibit.rol.sac.persistence.util.ModelsComunsFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;


/**
 * Business delegate para manipular els models comuns de documentació
 */
public class ModelsComunsDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    
    public ResultadoBusqueda llistarModelsComuns(int pagina, int resultats, String idioma) throws DelegateException {
      try {
        return getFacade().llistarModelsComuns(pagina,resultats,idioma);
      } catch (RemoteException e) {
        throw new DelegateException(e);
      }
    }
    
    public DocumentTramit obtenirModelComu(Long id) throws DelegateException {
        try {
            return getFacade().obtenirModelComu(id);
          } catch (RemoteException e) {
            throw new DelegateException(e);
          }
     }
    
    public Long gravarModelComu(DocumentTramit doc) throws DelegateException {
        try {
            return getFacade().gravarModelComu(doc);
          } catch (RemoteException e) {
            throw new DelegateException(e);
          }
     }
   
    public void esborrarModelComu(Long id) throws DelegateException {
        try {
            getFacade().esborrarModelComu(id);
          } catch (RemoteException e) {
            throw new DelegateException(e);
          }
     }
    
    public void reordenar(Long id, Long ordenNuevo, Long ordenAnterior ) throws DelegateException {
    	try {
    		getFacade().reordenar(id, ordenNuevo, ordenAnterior);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    


    /*=========================================================*/
    /*======================== REFERENCIA AL FACADE ==============*/
    /*=========================================================*/

    private Handle facadeHandle;

    private ModelsComunsFacade getFacade() throws RemoteException {
        return (ModelsComunsFacade) facadeHandle.getEJBObject();
    }

    protected ModelsComunsDelegate() throws DelegateException {
        try {
        	ModelsComunsFacadeHome home = ModelsComunsFacadeUtil.getHome();
        	ModelsComunsFacade remote = home.create();
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
