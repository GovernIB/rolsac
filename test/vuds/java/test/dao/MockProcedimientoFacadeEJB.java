package test.dao;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.ejb.ProcedimientoFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;


public class MockProcedimientoFacadeEJB extends ProcedimientoFacadeEJB {

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
	
	@Override
	protected boolean userIsOper() {
		return true;
	}
	
	@Override
	protected boolean userIsSuper() {
			return true;
	}

	@Override
	protected void addOperacion(Session session, ProcedimientoLocal pr,
			int operacion) throws HibernateException {
	}

}
