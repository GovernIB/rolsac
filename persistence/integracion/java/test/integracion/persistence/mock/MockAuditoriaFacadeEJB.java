package test.integracion.persistence.mock;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import net.sf.hibernate.SessionFactory;

import org.ibit.rol.sac.persistence.ejb.AuditoriaFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

public class MockAuditoriaFacadeEJB extends AuditoriaFacadeEJB {

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	
	AccesoManagerLocal accesoManager;
	
	public void setAccesoManager(AccesoManagerLocal accesoManager) {
		this.accesoManager = accesoManager;
	}
	
	public void setSessionFactory(SessionFactory sf) { this.sessionFactory=sf;} 

}
