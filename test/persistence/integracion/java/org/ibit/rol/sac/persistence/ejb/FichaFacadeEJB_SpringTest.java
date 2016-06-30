package org.ibit.rol.sac.persistence.ejb;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.SessionContext;

import net.sf.hibernate.SessionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.junit.runner.RunWith;

import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;
import org.ibit.rol.sac.persistence.ws.ReportarFallo;

import test.integracion.persistence.mock.MockAuditoriaFacadeEJB;
import test.integracion.persistence.mock.MockFichaFacadeEJB;
import test.integracion.persistence.mock.MockProcedimientoFacadeEJB;
import test.integracion.persistence.mock.MockUsuarioFacadeEJB;
import es.caib.test.common.LogSpy;



@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportarFallo.class)
@PowerMockIgnore("org.apache.commons.logging")  // para q no salga duplicate visibility error

public class FichaFacadeEJB_SpringTest extends 
//AbstractDependencyInjectionSpringContextTests {
AbstractTransactionalSpringContextTests {

	
	protected static Log log = LogFactory.getLog(FichaFacadeEJB_SpringTest.class);
	
	MockFichaFacadeEJB fichaBean;	//afegit en dao.xml
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource.xml",
				"dao.xml"
				};
	}

	public void onSetUp() throws Exception {
		fichaBean = (MockFichaFacadeEJB) applicationContext.getBean("fichaFacadeEJB");

//		AccesoManagerLocal accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
//		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(EasyMock.anyLong())).andReturn(true).anyTimes();
//		EasyMock.expect(accesoManager.tieneAccesoUnidad(EasyMock.anyLong(), EasyMock.anyBoolean())).andReturn(true).anyTimes();
//		EasyMock.replay(accesoManager);

		Principal p=EasyMock.createMock(Principal.class);
		EasyMock.expect(p.getName()).andReturn("u92770");
		EasyMock.replay(p);
		
		SessionContext sctx=EasyMock.createMock(SessionContext.class);
		EasyMock.expect(sctx.getCallerPrincipal()).andReturn(p);
		EasyMock.expect(sctx.isCallerInRole("sacsystem")).andReturn(true).anyTimes();
		EasyMock.replay(sctx);
		
		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		
		Destinatario d=new Destinatario();
		d.setNombre("Portal de l'Informador");
		d.setIdRemoto("GOVERN");
		d.setEndpoint("https://proves.caib.es/rolsacpmaesb/services/actualiza");
		d.setEmail("ejaen@dgtic.caib.es");
		destinatarios.add(d);
		
		 
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).anyTimes();
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);
		
		ProcedimientoDelegate procDelegate=	EasyMock.createMock(ProcedimientoDelegate.class);
		
		ProcedimientoLocal proc=new ProcedimientoLocal();
		proc.setId(246L);				
		EasyMock.replay(procDelegate);
		
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		
		//procBean.setAccesoManager(accesoManager);
		fichaBean.setSessionFactory(sf);
		fichaBean.setSessionContext(sctx);
		
	}


	public void test01BuscarFichas() {
		Map<String,String> parametros = new HashMap<String, String>();
		Map<String,String> traduccion = new HashMap<String, String>();
		
		traduccion.put("idioma", "ca");
		traduccion.put("titulo", "DONA");
		List<ProcedimientoLocal> procs = fichaBean.buscarFichas(parametros, traduccion);
		assertTrue(0<procs.size());
	}
	
	/*
	@PrepareForTest({FichaTransferible.class,DelegateUtil.class})
	public void testGrabarProcedimientoSeEnviaResponsableAdministracionRemota() throws InterruptedException, DelegateException {
		
		System.setProperty("es.caib.rolsac.numResponsables", "2");
		
		//mock log
		LogSpy log=new LogSpy();
		Actualizador.setLog(log);
		
		
		//mock Ficha
		Ficha f=new Ficha();
		f.setId(101L);
		
		//mock FichaTransferible
		String responsable="responsables=ejaen@rolsac";
		FichaTransferible ft = new FichaTransferible();
		
		
		//mock FichaTransferible.generar()
		PowerMock.mockStatic(FichaTransferible.class);
		EasyMock.expect(FichaTransferible.generar(f)).andReturn(ft);
		PowerMock.replay(FichaTransferible.class);

		
		//mock DelegateUtil.getAuditoriaDelegate()
		//mock DelegateUtil.getUsuarioDelegate()

		MockUsuarioFacadeEJB usuarioBean = (MockUsuarioFacadeEJB)applicationContext.getBean("usuarioFacadeEJB");
		UsuarioDelegate ud = new UsuarioDelegate();
		ud.setImpl(usuarioBean);

		MockAuditoriaFacadeEJB auditoriaBean = (MockAuditoriaFacadeEJB)applicationContext.getBean("auditoriaFacadeEJB");
		AuditoriaDelegate ad= new AuditoriaDelegate();
		ad.setImpl(auditoriaBean );

		PowerMock.mockStatic(DelegateUtil.class);
		EasyMock.expect(DelegateUtil.getAuditoriaDelegate()).andReturn(ad);
		EasyMock.expect(DelegateUtil.getUsuarioDelegate()).andReturn(ud);
		PowerMock.replay(DelegateUtil.class);
		
		
		ProcedimientoLocal proc=//creaProcedimientoLocal();
		procBean.obtenerProcedimiento(247L);
		proc.setResponsable("");
		procBean.grabarProcedimiento(proc, 202L);
		while(null==Actualizador.getThreadActualizador());
		_(Actualizador.getThreadActualizador());
		Actualizador.getThreadActualizador().join();
		
		assertTrue(log.containsDebugMsg("Actualizando un Procedimiento"));
		assertTrue(log.containsDebugMsg(responsable));	
		_(Arrays.toString(log.getInfoMsgs().toArray()));
	}
	*/
	
	
	
	// private -------------------------------------------------------


	
	
	private ProcedimientoLocal creaProcedimientoLocal() {
		ProcedimientoLocal p=new ProcedimientoLocal();
		TraduccionProcedimientoLocal tp=new TraduccionProcedimientoLocal();
		tp.setNombre("procediment de test");
		p.setTraduccion("ca", tp);
		p.setValidacion(Validacion.PUBLICA);
		
		Materia m=new Materia();
		m.setCodigoEstandar("123");
		Set<Materia> materias = new HashSet<Materia>();
		materias.add(m);
		p.setMaterias(materias);
		
		
		Set<HechoVitalProcedimiento> shvp = new HashSet<HechoVitalProcedimiento>();

		HechoVitalProcedimiento hvp = new HechoVitalProcedimiento();
		
		HechoVital hv=new HechoVital();
		hv.setCodigoEstandar("12345");

		hvp.setHechoVital(hv);

		p.setHechosVitalesProcedimientos(shvp);
		return p;
	}

	
	
	private void _(Object o){ System.out.println(o); }
}
 