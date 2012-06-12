package test.unitario.persistence.mock;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;

public class MockFamiliaDelegate extends FamiliaDelegate {

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	
	@Override
	public List listarFamilias() throws DelegateException {
		List<Familia> Familias = new ArrayList<Familia>();
		for(int i=1;i<3; i++) {
			Familias.add(familiaFactory(100L+i, "ca"));
		}
		return Familias;
	}
	
	
	public static Familia familiaFactory(Long id, String lang)  {
		Familia m = new Familia();
		m.setId(id);
		TraduccionFamilia traduccion = new TraduccionFamilia();
		traduccion.setNombre("mi nombre");
		m.setTraduccion(lang, traduccion);
		return m;
	}
}
