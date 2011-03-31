package test.dao;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import net.sf.hibernate.SessionFactory;


public class MockTramiteFacadeEJB extends TramiteFacadeEJB {

	AccesoManagerLocal accesoManager;
	
	public void setAccesoManager(AccesoManagerLocal manager) { accesoManager=manager;}
	
	@Override
	public AccesoManagerLocal getAccesoManager() {
		// TODO Auto-generated method stub
		return accesoManager;
	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void anyadirFormulario(Long tramiteId, Long formularioId)
			throws DelegateException {
		// TODO Auto-generated method stub
		
	}

	public DocumentTramit obtenirDocumentInformatiu(Long docInfId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public boolean autorizaCrearTramite(Long idProcedimiento)
			throws SecurityException {
		return true;
	}
	

}
