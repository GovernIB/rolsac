package test.integracion.persistence.mock;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import net.sf.hibernate.SessionFactory;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validable;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.ejb.FamiliaFacadeEJB;
import org.ibit.rol.sac.persistence.ejb.UnidadAdministrativaFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

public class MockUnidadAdministrativaDelegate extends UnidadAdministrativaDelegate {

	public static String userID;
	
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}
	
	AccesoManagerLocal mockAccesoManager;
	
	public void setAccesoManager(AccesoManagerLocal manager) { mockAccesoManager=manager;}
	
	public AccesoManagerLocal getAccesoManager() {
		// TODO Auto-generated method stub
		return mockAccesoManager;
	}

	
	@Override
	public List<UnidadAdministrativa> listarUnidadesAdministrativasRaiz() throws DelegateException {
		List<UnidadAdministrativa> uas = new ArrayList<UnidadAdministrativa>();
		if(userID.equals("rsanz")) {
			uas.add(obtenerUnidadAdministrativa(10L));
			return uas; 
		}
		if(userID.equals("u92770")) {
			uas.add(obtenerUnidadAdministrativa(1L));
			return uas; 
		}
		return null;
	}
	
	protected boolean visible(Validable validable) {
		return true;
	}

}
