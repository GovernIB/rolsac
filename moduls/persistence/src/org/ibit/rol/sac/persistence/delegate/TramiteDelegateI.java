package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tramite;


public interface TramiteDelegateI {

	public abstract Long grabarTramite(Tramite tramite, Long idOC) throws DelegateException;

	public abstract Tramite obtenerTramite(Long id) throws DelegateException;
	
	public abstract void borrarTramite(Long id) throws DelegateException;

	public abstract Long grabarDocument(DocumentTramit doc, Long tid)
			throws DelegateException;

	public abstract DocumentTramit obtenirDocument(Long docId)
			throws DelegateException;

	public abstract void borrarDocument(Long id) throws DelegateException;
	
	public abstract void borrarDocumentos(Tramite tramite, List<DocumentTramit> documentos)
			throws DelegateException;

	public abstract void actualizarOrdenDocs(Map<String, String[]> map, long tid)
			throws DelegateException;
	
	public abstract void actualizarOrdenTasas(Map<String, String[]> map, long tid)
	throws DelegateException;

	public abstract Taxa obtenirTaxa(Long docId) throws DelegateException;

	public abstract Long grabarTaxa(Taxa taxa, Long tid)
			throws DelegateException;

	public abstract void borrarTaxa(Long id) throws DelegateException;

	public abstract boolean autorizaCrearTramite(Long idProcedimiento) throws DelegateException;

	public abstract boolean autorizaModificarTramite(Long idTramite) throws DelegateException;

}