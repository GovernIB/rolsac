package org.ibit.rol.sac.persistence.delegate;

import java.util.Map;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tramite;

import es.caib.vuds.ActualizacionVudsException;
import es.caib.vuds.ValidateVudsException;

public interface TramiteDelegateI {

	public abstract Long grabarTramite(Tramite tramite, Long idOC)
			throws DelegateException, ValidateVudsException, ActualizacionVudsException;

	public abstract Tramite obtenerTramite(Long id) throws DelegateException;

	/*
	    @Deprecated
	    public void anyadirFormulario(Long tramite_id, Long formulario_id) throws DelegateException {
	    	
	        try {
	            getFacade().anyadirFormulario(tramite_id, formulario_id);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	        
	    }
	 */
	/*
	    @Deprecated
	    public void eliminarFormulario(Long tramite_id, Long formulario_id) throws DelegateException {
	        try {
	            getFacade().eliminarFormulario(tramite_id, formulario_id);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	    }
	 */
	public abstract void borrarTramite(Long id) throws DelegateException;

	public abstract Long grabarDocument(DocumentTramit doc, Long tid)
			throws DelegateException;

	public abstract DocumentTramit obtenirDocument(Long docId)
			throws DelegateException;

	public abstract void borrarDocument(Long id) throws DelegateException;

	public abstract void actualizarOrdenDocs(Map<String, String[]> map, long tid)
			throws DelegateException;

	public abstract Taxa obtenirTaxa(Long docId) throws DelegateException;

	public abstract Long grabarTaxa(Taxa taxa, Long tid)
			throws DelegateException;

	public abstract void borrarTaxa(Long id) throws DelegateException;

	public abstract boolean autorizaCrearTramite(Long idProcedimiento) throws DelegateException;

	public abstract boolean autorizaModificarTramite(Long idTramite) throws DelegateException;

}