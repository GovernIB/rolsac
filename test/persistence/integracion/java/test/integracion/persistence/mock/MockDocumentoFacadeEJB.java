package test.integracion.persistence.mock;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import org.ibit.rol.sac.persistence.ejb.DocumentoFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import net.sf.hibernate.SessionFactory;


public class MockDocumentoFacadeEJB extends DocumentoFacadeEJB {

	AccesoManagerLocal accesoManager;
	
	public void setSessionFactory(SessionFactory sf) { this.sessionFactory=sf;} 
	
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
	
	

}
