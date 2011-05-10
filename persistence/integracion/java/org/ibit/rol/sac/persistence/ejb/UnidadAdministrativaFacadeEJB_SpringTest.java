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
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
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
import test.integracion.persistence.mock.MockEdificioFacadeEJB;
import test.integracion.persistence.mock.MockFichaFacadeEJB;
import test.integracion.persistence.mock.MockProcedimientoFacadeEJB;
import test.integracion.persistence.mock.MockUnidadAdministrativaDelegate;
import test.integracion.persistence.mock.MockUnidadFacadeEJB;
import test.integracion.persistence.mock.MockUsuarioFacadeEJB;
import es.caib.test.common.LogSpy;



@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportarFallo.class)
@PowerMockIgnore("org.apache.commons.logging")  // para q no salga duplicate visibility error

public class UnidadAdministrativaFacadeEJB_SpringTest extends 
//AbstractDependencyInjectionSpringContextTests {
AbstractTransactionalSpringContextTests {

	
	protected static Log log = LogFactory.getLog(UnidadAdministrativaFacadeEJB_SpringTest.class);
	
	MockUnidadFacadeEJB uaBean;	//afegit en dao.xml
	
	MockEdificioFacadeEJB edificioBean;
	
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource.xml",
				"dao.xml"
				};
	}

	public void onSetUp() throws Exception {
		uaBean = (MockUnidadFacadeEJB) applicationContext.getBean("unidadFacadeEJB");

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
		
		
		
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		
		//procBean.setAccesoManager(accesoManager);
		uaBean.setSessionFactory(sf);
		uaBean.setSessionContext(sctx);
		
		
		edificioBean = (MockEdificioFacadeEJB) applicationContext.getBean("edificioFacadeEJB");
		edificioBean.setSessionFactory(sf);
		edificioBean.setSessionContext(sctx);
		
	}
	
	/*@PrepareForTest({FichaTransferible.class,DelegateUtil.class})
	 * */
	
	@PrepareForTest({Actualizador.class})
	public void _test03ListarEdificios() {
		
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
    	PowerMock.expectLastCall();
    	PowerMock.replay(Actualizador.class);
		
		Long ua_id=1L;
		
		
		UnidadAdministrativa ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		
		_(ua.getEdificios());
		_(ua.getEdificios().size());
				
	}
/* */
	
	@PrepareForTest({Actualizador.class})
	public void test01AñadirEdificioAUnidad() {
		
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
		Actualizador.borrar(EasyMock.anyObject(),EasyMock.anyObject());
    	PowerMock.expectLastCall();
    	PowerMock.replay(Actualizador.class);
		
		Long edificio_id=7L;
		Long ua_id=1L;
		
		UnidadAdministrativa ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		int nedificios = ua.getEdificios().size();

		uaBean.anyadirEdificio(edificio_id, ua_id);

		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		int nedificiosAfter = ua.getEdificios().size();

		assertEquals(nedificios+1,nedificiosAfter);
	
		uaBean.eliminarEdificio(edificio_id, ua_id);
		
		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		nedificiosAfter = ua.getEdificios().size();
		assertEquals(nedificios,nedificiosAfter);

		
	
	}
	
	/*
	@PrepareForTest({Actualizador.class})
	public void test02AñadirUnidadAEdificio() {
		
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
    	PowerMock.expectLastCall();
    	PowerMock.replay(Actualizador.class);
		
		Long edificio_id=77L;
		Long ua_id=1L;
		
		Edificio edificio = edificioBean.obtenerEdificio(edificio_id);
		_(edificio.getUnidadesAdministrativas().size());
		
		edificioBean.anyadirUnidad(ua_id, edificio_id);
		
		edificio = edificioBean.obtenerEdificio(edificio_id);
		_(edificio.getUnidadesAdministrativas().size());
		
	}
	*/
		
	private void _(Object o){ System.out.println(o); }
}
 