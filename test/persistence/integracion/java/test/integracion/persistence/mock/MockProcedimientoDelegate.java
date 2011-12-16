package test.integracion.persistence.mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;


import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.easymock.EasyMock;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;


/**
 * aquest Mock es fa extendre del EJB per poder fer tests d'integracio.
 * @author u92770
 *
 */
public class MockProcedimientoDelegate extends ProcedimientoDelegate {

	
	AccesoManagerLocal mockAccesoManager;
	MockProcedimientoFacadeEJB mockFacade = new MockProcedimientoFacadeEJB();
	
	public MockProcedimientoDelegate() {
		setImpl(mockFacade);
	}
	
	public void setSessionFactory(SessionFactory sf) { ((MockProcedimientoFacadeEJB)getImpl()).setSessionFactory(sf);} 
	
	public void setAccesoManager(AccesoManagerLocal manager) { mockAccesoManager=manager;}
	
	public AccesoManagerLocal getAccesoManager() {
		return mockAccesoManager;
	}

	
	protected boolean userIsOper() {
		return true;
	}
	
	protected boolean userIsSuper() {
			return true;
	}

	public ProcedimientoLocal obtenerProcedimiento(Long id) throws DelegateException {
		return super.obtenerProcedimiento(id);
	}


	@Override
	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) throws DelegateException {
		mockAccesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(mockAccesoManager.tieneAccesoProcedimiento(procedimiento.getId())).andReturn(true).times(1);
		EasyMock.expect(mockAccesoManager.tieneAccesoUnidad(1L,false)).andReturn(true).times(1);
		EasyMock.replay(mockAccesoManager);
		mockFacade.setAccesoManager(mockAccesoManager);
		if(0==procedimiento.getId()) procedimiento.setId(null);
		
		
		List<Destinatario> destinatarios=Collections.emptyList();
		DestinatarioDelegate destDelegate = EasyMock.createMock(DestinatarioDelegate.class);
		try {
			EasyMock.expect(destDelegate.listarDestinatarios()).andReturn(destinatarios);
		} catch (DelegateException e) {
			e.printStackTrace();
		}
		EasyMock.replay(destDelegate);
//		Actualizador.setDestDelegate(destDelegate);
		
		return super.grabarProcedimiento(procedimiento, idUA);
	}
	
	
	@Override
		public void anyadirTramite(Long tramiteId, Long procId) throws DelegateException {
			mockAccesoManager = EasyMock.createMock(AccesoManagerLocal.class);
			EasyMock.expect(mockAccesoManager.tieneAccesoProcedimiento(procId)).andReturn(true).times(1);
			EasyMock.expect(mockAccesoManager.tieneAccesoUnidad(1L,false)).andReturn(true).times(1);
			EasyMock.replay(mockAccesoManager);
			mockFacade.setAccesoManager(mockAccesoManager);	
			super.anyadirTramite(tramiteId, procId);
		}

	@Override
		public List buscarProcedimientos(Map parametros, Map traduccion) throws DelegateException {
			return super.buscarProcedimientos(parametros, traduccion);
		}
	
	
		protected void addOperacion(Session session, ProcedimientoLocal pr,
				int operacion) throws HibernateException {

		}
	
		protected boolean tieneAcceso(Usuario usuario,
						ProcedimientoLocal procedimiento) {
			return true;
		}
	
		protected Usuario getUsuario(Session session) throws HibernateException {
			return null;
		}
}
