package test.unitario.persistence.mock;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.ejb.IniciacionFacadeEJB;

public class MockIniciacionDelegate extends IniciacionDelegate {

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}


	@Override
	public List listarIniciacion() throws DelegateException {
		List<Iniciacion> Iniciacions = new ArrayList<Iniciacion>();
		for(int i=1;i<3; i++) {
			Iniciacions.add(IniciacionFactory(100L+i, "ca"));
		}
		return Iniciacions;
	}
	
	
	public static Iniciacion IniciacionFactory(Long id, String lang)  {
		Iniciacion m = new Iniciacion();
		m.setId(id);
		TraduccionIniciacion traduccion = new TraduccionIniciacion();
		traduccion.setNombre("mi nombre");
		m.setTraduccion(lang, traduccion);
		return m;
	}
}
