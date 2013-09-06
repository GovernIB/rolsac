package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.persistence.intf.ExcepcioDocumentacioFacadeHome;
import org.ibit.rol.sac.persistence.intf.ExcepcioDocumentacioFacade;
import org.ibit.rol.sac.persistence.util.ExcepcioDocumentacioFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate per manipular excepcions de documentacio.
 */
public class ExcepcioDocumentacioDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -1542197568196968517L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */


	public Long gravarExcepcioDocumentacio(ExcepcioDocumentacio excepcio)  throws DelegateException {
		try {
			return getFacade().gravarExcepcioDocumentacio(excepcio);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public ResultadoBusqueda llistarExcepcioDocumentacio(int pagina, int resultats) throws DelegateException {
		try {
			return getFacade().llistarExcepcioDocumentacio(pagina,resultats);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public List<ExcepcioDocumentacio> llistarExcepcioDocumentacio() throws DelegateException {
		try {
			return getFacade().llistarExcepcioDocumentacio();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public ExcepcioDocumentacio obtenirExcepcioDocumentacio(Long id) throws DelegateException {
		try {
			return getFacade().obtenirExcepcioDocumentacio(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public boolean teRelacioCatalegDocuments(Long id) throws DelegateException {
		try {
			return getFacade().teRelacioCatalegDocuments(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public boolean teRelacioDocumentTramit(Long id) throws DelegateException {
		try {
			return getFacade().teRelacioDocumentTramit(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public void esborrarExcepcioDocumentacio(Long id) throws DelegateException {
		try {
			getFacade().esborrarExcepcioDocumentacio(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private ExcepcioDocumentacioFacade getFacade() throws RemoteException {
		return (ExcepcioDocumentacioFacade) facadeHandle.getEJBObject();
	}

	protected ExcepcioDocumentacioDelegate() throws DelegateException {
		try {
			ExcepcioDocumentacioFacadeHome home = ExcepcioDocumentacioFacadeUtil.getHome();
			ExcepcioDocumentacioFacade remote = home.create();
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
