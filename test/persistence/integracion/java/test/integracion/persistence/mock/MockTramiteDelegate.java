package test.integracion.persistence.mock;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJBException;

import junit.extensions.TestDecorator;

import net.sf.hibernate.SessionFactory;

import org.easymock.EasyMock;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegateImpl;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

//import es.caib.vuds.ActualizacionVudsException;
//import es.caib.vuds.ValidateVudsException;



public class MockTramiteDelegate extends TramiteDelegate {

	
	HashMap<Long, Tramite> tramites = new HashMap<Long, Tramite>();
	
	MockTramiteFacadeEJB mockFacade;
	
	public MockTramiteDelegate() {
		setImpl(new MockTramiteFacadeEJB());
	}
	
	public void setSessionFactory(SessionFactory sf) { ((MockTramiteFacadeEJB)getImpl()).setSessionFactory(sf);} 

	
	public void anyadirFormulario(Long tramiteId, Long formularioId)
			throws DelegateException {
		// TODO Auto-generated method stub
		
	}

	public void borrarTramite(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarFormulario(Long tramiteId, Long formularioId) {
		// TODO Auto-generated method stub
		
	}

	/* TODO implementar grabarTramite
	 * 
	public Long grabarTramite(Tramite tramite, Long idOC) throws DelegateException, ValidateVudsException, ActualizacionVudsException {
		AccesoManagerLocal mockAccesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(mockAccesoManager.tieneAccesoProcedimiento(tramite.getId())).andReturn(true).times(1);
		EasyMock.expect(mockAccesoManager.tieneAccesoUnidad(1L,false)).andReturn(true).times(1);
		EasyMock.replay(mockAccesoManager);
		setAccesoManagerLocal(mockAccesoManager);

		if(0==tramite.getId()) tramite.setId(null);
		
//	comentado temporalmente. Segun el test interesa que destinaratios sean unos u otros.
//		
//		List<Destinatario> destinatarios=Collections.emptyList();
//		DestinatarioDelegate destDelegate = EasyMock.createMock(DestinatarioDelegate.class);
//		try {
//			EasyMock.expect(destDelegate.listarDestinatarios()).andReturn(destinatarios);
//
//		} catch (DelegateException e) {
//			e.printStackTrace();
//		}
//		EasyMock.replay(destDelegate);
//		Actualizador.setDestDelegate(destDelegate);
		
		return super.grabarTramite(tramite, idOC);
		

	}
*/
	public Tramite obtenerTramite(Long id) throws DelegateException {
		return super.obtenerTramite(id); 
	}

	public void afegirDocumentInformatiu(Long tramiteId, Long docInfId) {
		// TODO Auto-generated method stub
		
	}

	public void afegirDocumentPresentar(Long tramiteId, Long docPreId) {
		// TODO Auto-generated method stub
		
	}

	public void afegirRequisit(Long tramiteId, Long reqId) {
		// TODO Auto-generated method stub
		
	}

	public Long grabarDocument(DocumentTramit doc, Long tid) throws DelegateException {
		AccesoManagerLocal mockAccesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(mockAccesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(mockAccesoManager);
		setAccesoManagerLocal(mockAccesoManager);
		return super.grabarDocument(doc, tid);
	}

	
	
	public DocumentTramit obtenirDocument(Long docId) throws DelegateException {
		return super.obtenirDocument(docId);
	}

	public DocumentTramit obtenirDocumentInformatiu(Long docInfId) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumentTramit obtenirFormulari(Long formularioId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long grabarTaxa(org.ibit.rol.sac.model.Taxa taxa, Long tid) throws DelegateException {
		AccesoManagerLocal mockAccesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(mockAccesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(mockAccesoManager);
		setAccesoManagerLocal(mockAccesoManager);
		return super.grabarTaxa(taxa, tid);
	}
	
	
	protected AccesoManagerLocal getAccesoManager() {
		return ((MockTramiteFacadeEJB)getImpl()).getAccesoManager();
	}
	
	protected void setAccesoManagerLocal(AccesoManagerLocal mockAccesoManager) {
		((MockTramiteFacadeEJB)getImpl()).setAccesoManager(mockAccesoManager);

	}
	

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

}
