package org.ibit.rol.sac.persistence.ws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.ws.Actualizador;
import org.ibit.rol.sac.persistence.ws.ReportarFallo;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import test.unitario.persistence.mock.MockDestinatarioDelegate;


import es.caib.test.common.LogSpy;


/**
 * Esta clase es un test unitario del Actualizador, asi que el resto de clases dependendintes (ReportarFallo, web services..)
 * se simulan. Para testear todo hay que ejecutar un test de integracion.
 * @author enric
 *
 */

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("org.apache.commons.logging")
@PrepareForTest({ReportarFallo.class,ActualizacionServicio.class})
public class ActualizadorTest extends junit.framework.TestCase {

	DestinatarioDelegate destDelegateMock = EasyMock.createMock(DestinatarioDelegate.class);


	List<Destinatario> destinatarios=new ArrayList<Destinatario>();
	Destinatario dest;
	
	@Override
	protected void setUp() throws Exception {
	}
	
	public void test_TODO_ActualitzarProcedimentoEnvia2Responsables() {
		//unit test que comproba que s'envien 2 responsables en el web service. 
		//el test es pasa si en el log apareix el responsable correcte, i si esta present
		//en el camp del ProcedimentoTransferible.
		

	}
	
	@PrepareForTest({FichaTransferible.class,DelegateUtil.class})
	public void testActualitzarFichaEnvia2Responsables() throws InterruptedException, DelegateException {
		//test que comproba que s'envia el camp responsable en el web service. 
		//el test es pasa si en el log apareix el responsable correcte, i si esta present
		//en el camp del FichaTransferible.

		
		System.setProperty("es.caib.rolsac.numResponsables", "2");
		
		//mock log
		LogSpy log=new LogSpy();
		Actualizador.setLog(log);
		ActualizadorFicha.log=log;

		
		//mock Ficha
		Ficha ficha=new Ficha();
		ficha.setId(101L);
		
		//mock FichaTransferible
		String responsables="a@aa,b@bb";
		FichaTransferible ft = new FichaTransferible();
		
		
		//mock FichaTransferible.generar()
		PowerMock.mockStatic(FichaTransferible.class);
		EasyMock.expect(FichaTransferible.generar(ficha)).andReturn(ft);
		PowerMock.replay(FichaTransferible.class);

	
		//mock Usuario
		Usuario u1=new Usuario();
		u1.setNombre("usuario1");
		u1.setEmail("a@aa");
		
		Usuario u2=new Usuario();
		u2.setNombre("usuario2");
		u2.setEmail("b@bb");
		
		//mock UsuarioDelegate
		UsuarioDelegate ud=EasyMock.createMock(UsuarioDelegate.class);
		EasyMock.expect(ud.obtenerUsuariobyUsernamePMA("usuario1")).andReturn(u1);
		EasyMock.expect(ud.obtenerUsuariobyUsernamePMA("usuario2")).andReturn(u2);
		EasyMock.replay(ud);				
		
		
		//mock Auditoria
		List listaAuditorias = new ArrayList<Auditoria>();
		Auditoria a=new Auditoria();
		a.setUsuario("usuario1");
		listaAuditorias.add(a);
		a=new Auditoria();
		a.setUsuario("usuario2");
		listaAuditorias.add(a);
		
		
		//mock AuditoriaDelegate
		AuditoriaDelegate ad=EasyMock.createMock(AuditoriaDelegate.class);
		EasyMock.expect(ad.listarAuditoriasFichaPMA(EasyMock.anyLong())).andReturn(listaAuditorias);
		EasyMock.replay(ad);				

		
		
		//mock DelegateUtil.getAuditoriaDelegate()
		//mock DelegateUtil.getUsuarioDelegate()
		PowerMock.mockStatic(DelegateUtil.class);
		EasyMock.expect(DelegateUtil.getAuditoriaDelegate()).andReturn(ad);
		EasyMock.expect(DelegateUtil.getUsuarioDelegate()).andReturn(ud);
		PowerMock.replay(DelegateUtil.class);
		
		
		//mock FichaUATransferible.generar()
		
		
		
		set0Destinatarios();

		Actualizador.actualizar(ficha);
		
		//ens sincronitzem amb el final del thread. 
		while(null==Actualizador.getThreadActualizador());
		_(Actualizador.getThreadActualizador());
		Actualizador.getThreadActualizador().join();
		
		assertTrue(log.containsInfoMsg("responsables="+responsables));	
		assertEquals(responsables, ft.getResponsable());

	}
	


	public void testCalActualitzarProcedimientoPortalInformador() {
		
		Actualizador actualizador = new Actualizador();
		
		dest=new Destinatario();
		dest.setNombre("PortalInformador");
		dest.setEndpoint("miendpoint");
		dest.setEmail("bustia@rolsac");

		ProcedimientoLocal procLocal = new ProcedimientoLocal();
		assertTrue(actualizador.calActualizarElDestinatari(dest, procLocal));
		
	}
	

	public void testCalActualitzarTramiteVuds() {
		
		Actualizador actualizador = new Actualizador();
		
		dest=new Destinatario();
		dest.setNombre("VUDS");
		dest.setEndpoint("miendpoint");
		dest.setEmail("bustia@rolsac");

		Tramite tram = new Tramite(); 
		tram.setOperativa(Operativa.MODIFICA);
		
		ProcedimientoLocal procedimiento = new ProcedimientoLocal();
		procedimiento.setVentanillaUnica("1");
		tram.setProcedimiento(procedimiento);
		assertTrue(actualizador.calActualizarElDestinatari(dest, tram));
		
	}
	
	public void testCalActualitzarTramiteNoVuds() {
		
		Actualizador actualizador = new Actualizador();
		
		dest=new Destinatario();
		dest.setNombre("Portal informador");
		dest.setEndpoint("miendpoint");
		dest.setEmail("bustia@rolsac");

		Tramite tram = new Tramite(); 
		
		assertFalse(actualizador.calActualizarElDestinatari(dest, tram));
		
	}
	
	public void testCalActualitzarOficinaIndra() {
		
		System.setProperty("es.indra.caib.rolsac.oficina","S");
		Actualizador actualizador = new Actualizador();
		assertFalse(actualizador.calActualizarElDestinatari(dest, null));
		System.setProperty("es.indra.caib.rolsac.oficina","N");

	}
	
	
	public void testActualizarProcedimientoPMA_OK() throws InterruptedException {

		MockActualizarProcedimiento ok = new MockActualizarProcedimiento() {
    		@Override
    		void tryActualizarProcedimiento (ActualizacionServicio actServ)
    				throws WSInvocatorException {
    			EasyMock.expect(actServ.actualizarProcedimiento((ProcedimientoTransferible)EasyMock.anyObject())).
				andReturn(true);
    		}
    	};
		establecerMocksActualizador(ok);
		LogSpy log = obtenerLoggerActualizador();
		ActualizadorProcedimiento.log=log;
		
		establecerUrlProcedimientoTransferible();

		ProcedimientoLocal p = crearProcedimiento();
		Actualizador.actualizar(p);
		esperarFinActualizador();

		assertTrue(log.containsInfoMsg("actualizando el destinatario: VUDS"));
		assertTrue(log.containsInfoMsg("actualizacion enviada OK"));
		
	}

	
	public void testActualizarProcedimientoPMA_FAIL() throws InterruptedException {

		MockActualizarProcedimiento fail = new MockActualizarProcedimiento() {
    		@Override
    		void tryActualizarProcedimiento (ActualizacionServicio actServ)
    				throws WSInvocatorException {
    			Throwable th = new Exception(MSG_EXCEPCION);
    			WSInvocatorException e=new WSInvocatorException(th);
    			EasyMock.expect(actServ.actualizarProcedimiento((ProcedimientoTransferible)EasyMock.anyObject())).
    							andThrow(e);
    		}
    	};
		establecerMocksActualizador(fail);
		LogSpy log = obtenerLoggerActualizador();
		ActualizadorProcedimiento.log=log;

		establecerUrlProcedimientoTransferible();

		ProcedimientoLocal p = crearProcedimiento();
		Actualizador.actualizar(p);
		esperarFinActualizador();

		assertTrue(log.containsInfoMsg("actualizando el destinatario: VUDS"));
		assertFalse(log.containsInfoMsg("actualizacion enviada OK"));
		assertTrue(log.containsErrorMsg("error actualizando -> "+"java.lang.Exception: "+MSG_EXCEPCION ));
		
	}
	
	final String MSG_EXCEPCION = "mi error";
	
	private void establecerUrlProcedimientoTransferible() {
		System.setProperty(ProcedimientoTransferible.URL_PROC,"http://miurl");
	}

	

	private ProcedimientoLocal crearProcedimiento() {
		
		ProcedimientoLocal p=new ProcedimientoLocal(111L);
		UnidadAdministrativa ua=new UnidadAdministrativa(123L);
		p.setUnidadAdministrativa(ua);
		
		Set<Materia> materias = new HashSet<Materia>();
		p.setMaterias(materias);
		
		Set<HechoVitalProcedimiento> hvitales = new HashSet<HechoVitalProcedimiento>();
		
		p.setHechosVitalesProcedimientos(hvitales);
		
		return p;
	}

	private void establecerMocksActualizador(MockActualizarProcedimiento  mockActProc) {
		crearMockDestinatarioDelegate();
    	crearMockMetodoStatic_reportar();
    	
    	ActualizacionServicio mockActServ = mockActProc.crearMockActualizacionServicio() ;
	    crearMockMetodoStatic_createActualizacionServicio(mockActServ);
	}

	
	

	private void crearMockMetodoStatic_createActualizacionServicio(
			ActualizacionServicio mockActServ) {
		try {
			
			PowerMock.mockStatic(ActualizacionServicio.class);
			EasyMock.expect(ActualizacionServicio.createActualizacionServicio(
																(String)EasyMock.anyObject(),
																(String)EasyMock.anyObject())).
								andReturn(mockActServ);
		} catch (WSInvocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PowerMock.replay(ActualizacionServicio.class);
	}

	
	abstract class MockActualizarProcedimiento {
	
		private ActualizacionServicio crearMockActualizacionServicio() {
			ActualizacionServicio mockActServ = EasyMock.createMock(ActualizacionServicio.class);

			try {
				tryActualizarProcedimiento (mockActServ);

			} catch (WSInvocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			EasyMock.replay(mockActServ);


			return mockActServ;
		}

		abstract void tryActualizarProcedimiento (ActualizacionServicio actServ) throws WSInvocatorException;

	}

	
	private void crearMockMetodoStatic_reportar() {
		PowerMock.mockStatic(ReportarFallo.class);
    	ReportarFallo.reportar(EasyMock.anyObject(),
    						EasyMock.anyBoolean(), 
    						(Destinatario)EasyMock.anyObject(), 
    						(Exception)EasyMock.anyObject());
    	PowerMock.expectLastCall();
    	PowerMock.replay(ReportarFallo.class);
	}

	private void crearMockDestinatarioDelegate() {
		Actualizador.setDestDelegate(new MockDestinatarioDelegate());
	}

	private LogSpy obtenerLoggerActualizador() {
		LogSpy log=new LogSpy();
		Actualizador.setLog(log);
		return log;
	}

	private void esperarFinActualizador() throws InterruptedException {
		while(null==Actualizador.getThreadActualizador());
		Actualizador.getThreadActualizador().join();
	}
	
	
	private void set0Destinatarios() throws DelegateException {
		destinatarios=new ArrayList<Destinatario>();
		EasyMock.expect(destDelegateMock.listarDestinatarios()).andReturn(destinatarios).times(2);
		EasyMock.replay(destDelegateMock);
		Actualizador.setDestDelegate(destDelegateMock);
	}

	
	private void _(Object o)  { System.out.println(o); }
	
	
	//ver otros tests en la version 1.1.2.1
	
	

}
