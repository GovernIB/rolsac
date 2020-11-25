package es.indra.rol.sac.integracion.listener.traductor;

import javax.servlet.ServletContextEvent;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;
import org.springframework.mock.web.MockServletContext;

import es.caib.test.common.LogSpy;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;


public class TraductorInitializerTest extends TestCase {

	public void test01TraductorHabilitatSenseHost() {
		System.setProperty(TraductorInitializer.FLAG_TRADUCTOR, "S");
		
	
		initializer = new TraductorInitializer(){
			protected Traductor crearTraductor() throws Exception {
				Traductor mockTraductor=PowerMock.createMock(Traductor.class);
				EasyMock.expect(mockTraductor.getTranslationServerUrl()).andReturn("");
				PowerMock.replay(mockTraductor);
				return mockTraductor;
			}
		};
		initializer.contextInitialized(mockEvent);

		
		assertTrue(logger.containsDebugMsg("Carregant Rolsac amb traducci� autom�tica"));
		assertNotNull(mockEvent.getServletContext().getAttribute("traductor"));

	}
	
	
	public void test02TraductorHabilitatAmbHost() {
		System.setProperty(TraductorInitializer.FLAG_TRADUCTOR, "S");
		System.setProperty(TraductorInitializer.SERVIDOR_TRADUCTOR, "host");
		
		

		
		initializer = new TraductorInitializer(){
			protected Traductor crearTraductor() throws Exception {
				Traductor mockTraductor = PowerMock.createMock(Traductor.class);
				mockTraductor.setTranslationServerUrl("host");
				EasyMock.expect(mockTraductor.getTranslationServerUrl()).andReturn("host");
				PowerMock.replay(mockTraductor);

				return mockTraductor;
			}
		};
			
		initializer.contextInitialized(mockEvent);
		
		
		assertTrue(logger.containsDebugMsg("Carregant Rolsac amb traducci� autom�tica"));
		assertTrue(logger.containsDebugMsg("URL de servidor de traducci�: host"));
		assertNotNull(mockEvent.getServletContext().getAttribute("traductor"));

	}
	
	public void test03TraductorInhabilitatSensePropietat() {
	
		initializer = new TraductorInitializer(){
			protected Traductor crearTraductor() throws Exception {
				Traductor mockTraductor=PowerMock.createMock(Traductor.class);
				EasyMock.expect(mockTraductor.getTranslationServerUrl()).andReturn("");
				PowerMock.replay(mockTraductor);
				return mockTraductor;
			}
		};
		initializer.contextInitialized(mockEvent);

		assertTrue(logger.containsDebugMsg("Carregant Rolsac sense traducci� autom�tica"));
		
	}

	public void test04TraductorInhabilitatAmbPropietatFalse() {
		System.setProperty(TraductorInitializer.FLAG_TRADUCTOR, "N");

		initializer = new TraductorInitializer(){
			protected Traductor crearTraductor() throws Exception {
				Traductor mockTraductor=PowerMock.createMock(Traductor.class);
				EasyMock.expect(mockTraductor.getTranslationServerUrl()).andReturn("");
				PowerMock.replay(mockTraductor);
				return mockTraductor;
			}
		};
		initializer.contextInitialized(mockEvent);

		assertTrue(logger.containsDebugMsg("Carregant Rolsac sense traducci� autom�tica"));
		
	}

	
	
	private LogSpy logger=null;
	private ServletContextEvent mockEvent;
	private TraductorInitializer initializer;
	private Traductor mockTraductor;
	
	@Override
	protected void setUp() throws Exception {

		mockEvent = PowerMock.createMock(ServletContextEvent.class);
		MockServletContext context = new MockServletContext();
		EasyMock.expect(mockEvent.getServletContext()).andReturn(context).anyTimes();
		PowerMock.replay(mockEvent);

		logger = new LogSpy();
		initializer.log = logger;
		
	
	
	}
	
	@Override
	protected void tearDown() throws Exception {
		System.clearProperty(TraductorInitializer.FLAG_TRADUCTOR);
		System.clearProperty(TraductorInitializer.SERVIDOR_TRADUCTOR);

	}

}
