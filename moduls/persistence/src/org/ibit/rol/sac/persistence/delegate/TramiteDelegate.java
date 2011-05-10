package org.ibit.rol.sac.persistence.delegate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ElementTramit;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.intf.TramiteFacade;
import org.ibit.rol.sac.persistence.intf.TramiteFacadeHome;
import org.ibit.rol.sac.persistence.util.TramiteFacadeUtil;

import es.caib.persistence.vuds.ActualizacionVudsException;
import es.caib.persistence.vuds.ValidateVudsException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Business delegate para manipular Tramites.
 */
public class TramiteDelegate  {

	TramiteDelegateI impl;

	public TramiteDelegateI getImpl() {
		return impl;
	}

	public void setImpl(TramiteDelegateI impl) {
		this.impl = impl;
	}

   public boolean autorizaCrearTramite(Long idProcedimiento) throws DelegateException  {
    	return impl.autorizaCrearTramite(idProcedimiento); 
    }
    
        
    public boolean autorizaModificarTramite(Long idTramite) throws DelegateException {
       	return impl.autorizaModificarTramite(idTramite);
    }   	

	public void actualizarOrdenDocs(Map<String, String[]> map, long tid)
			throws DelegateException {
		impl.actualizarOrdenDocs(map, tid);
	}
	
	public void borrarDocument(Long id) throws DelegateException {
		impl.borrarDocument(id);
	}

	public void borrarTaxa(Long id) throws DelegateException {
		impl.borrarTaxa(id);
	}

	public void borrarTramite(Long id) throws DelegateException {
		impl.borrarTramite(id);
	}

	public Long grabarDocument(DocumentTramit doc, Long tid)
			throws DelegateException {
		return impl.grabarDocument(doc, tid);
	}

	public Long grabarTaxa(Taxa taxa, Long tid) throws DelegateException {
		return impl.grabarTaxa(taxa, tid);
	}

	public Long grabarTramite(Tramite tramite, Long idOC)
			throws DelegateException, ValidateVudsException, ActualizacionVudsException {
		return impl.grabarTramite(tramite, idOC);
	}

	public Tramite obtenerTramite(Long id) throws DelegateException {
		return impl.obtenerTramite(id);
	}

	public DocumentTramit obtenirDocument(Long docId) throws DelegateException {
		return impl.obtenirDocument(docId);
	}

	public Taxa obtenirTaxa(Long docId) throws DelegateException {
		return impl.obtenirTaxa(docId);
	}
	
}
