package org.ibit.rol.sac.persistence.ejb;

import junit.framework.TestCase;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.easymock.EasyMock;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.dao.saver.TramiteDAOSaver;
import org.ibit.rol.sac.persistence.remote.ActualizadorFacade;
import org.ibit.rol.sac.persistence.saver.TramiteSaver;
import org.ibit.rol.sac.persistence.ws.Actualizador;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

import es.caib.test.common.LogSpy;
import test.unitario.persistence.mock.MockTramiteFacadeEJB;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore("org.apache.commons.logging")  // para q no salga duplicate visibility error
@PrepareForTest(Actualizador.class)

public class TramiteFacadeEJBTest extends TestCase {

	MockTramiteFacadeEJB tramiteFacadeMock;

	LogSpy log; 

	@Override
	protected void setUp() throws Exception {
		log = new LogSpy();
		ActualizadorFacade.log=log;
	}
	
	public void testGrabarTramitePMAActualizaPortalPMA() {
		Tramite tramite=crearTramitePMA(); 
		Long idOC=123L;
		final Long tramId=4444L;
		
		TramiteDAOSaver tramiteDAOSaver=new TramiteDAOSaver() {
			@Override
			public Long grabarTramite(Tramite tramite, Long idOC,
					Session session) throws HibernateException {
				return tramId;
			}
		};

		tramiteFacadeMock = new MockTramiteFacadeEJB();
		TramiteSaver tramiteSaver = new TramiteSaver(tramiteFacadeMock);
		tramiteSaver.setTramiteDAOSaver(tramiteDAOSaver);
		
		tramiteFacadeMock.setTramiteSaver(tramiteSaver);
		
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(tramite);
		PowerMock.expectLastCall();
		
		
		assertEquals(tramId,tramiteFacadeMock.grabarTramite(tramite, idOC));
		assertTrue(log.containsDebugMsg("actualizando en actualizador pma"));
		assertFalse(log.containsDebugMsg("actualizando en actualizador vuds"));
	}
	
	


	public void testGrabarTramiteVUDSActualizaPortalVUDStambienPMA() {
		Tramite tramite=crearTramiteVUDS(); 
		Long idOC=123L;
		final Long tramId=4444L;
		
		TramiteDAOSaver tramiteDAOSaver=new TramiteDAOSaver() {
			@Override
			public Long grabarTramite(Tramite tramite, Long idOC,
					Session session) throws HibernateException {
				return tramId;
			}
		};

		tramiteFacadeMock = new MockTramiteFacadeEJB();
		TramiteSaver tramiteSaver = new TramiteSaver(tramiteFacadeMock);
		tramiteSaver.setTramiteDAOSaver(tramiteDAOSaver);
		
		tramiteFacadeMock.setTramiteSaver(tramiteSaver);
		
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(tramite);
		PowerMock.expectLastCall();
		
		
		assertEquals(tramId,tramiteFacadeMock.grabarTramite(tramite, idOC));
		assertTrue(log.containsDebugMsg("actualizando en actualizador vuds"));
		assertTrue(log.containsDebugMsg("actualizando en actualizador pma"));
	}
	
	
	private Tramite crearTramitePMA() {
		Tramite tramite=new Tramite();
		ProcedimientoLocal proc=new ProcedimientoLocal();
		proc.setVentanillaUnica("0");
		tramite.setProcedimiento(proc);
		return tramite;

	}

	
	private Tramite crearTramiteVUDS() {
		Tramite tramite=new Tramite();
		ProcedimientoLocal proc=new ProcedimientoLocal();
		proc.setVentanillaUnica("1");
		tramite.setProcedimiento(proc);
		return tramite;

	}
}
