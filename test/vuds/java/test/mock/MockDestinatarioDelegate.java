package test.mock;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.ejb.DestinatarioFacadeEJB;
import org.ibit.rol.sac.persistence.ejb.FamiliaFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

public class MockDestinatarioDelegate extends DestinatarioDelegate {

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	protected AccesoManagerLocal getAccesoManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Destinatario> listarDestinatarios() throws DelegateException {
		Destinatario dest = new Destinatario();
		dest.setEmail("cualquier@dgtic.caib.es");
		dest.setEndpoint("http://127.0.0.1:18080/axis2/services/GestorWebserviceBeanService");
		dest.setNombre("VUDS");
		List<Destinatario> dests = new ArrayList<Destinatario>();
		dests.add(dest);
		return dests;
	}
}
