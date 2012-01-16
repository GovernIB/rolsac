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
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportarFallo.class)
@PowerMockIgnore("org.apache.commons.logging")  // para q no salga duplicate visibility error

public class UnidadAdministrativaFacadeEJB_SpringTest { 
// extends AbstractTransactionalSpringContextTests {

	
	protected static Log log = LogFactory.getLog(UnidadAdministrativaFacadeEJB_SpringTest.class);
	
	@Rule public PowerMockRule rule = new PowerMockRule();
	
	@Autowired
	ApplicationContext applicationContext;
	
	MockUnidadFacadeEJB uaBean;	//afegit en dao.xml
	
	MockEdificioFacadeEJB edificioBean;
	
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource_persistence.xml",
				"dao_persistence.xml"
				};
	}

	public void onSetUp() throws Exception {
		uaBean = (MockUnidadFacadeEJB) applicationContext.getBean("unidadFacadeEJB");


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
	
	/**
	 * ESCENARI: afegir un edifici a una UA
	 * 
	 * DONAT QUE: existeix la UA=7
	 * I QUE: existeix un edifici=1
	 * I QUE: tenim un mock del actualitzador
	 * I QUE: tenim el nombre d'edificis d'aquesta UA  
	 * QUAN: es crida a unidadFacadeEJB.anyadirEdificio
	 * ALESHORES: el nombre d'edificis s'incrementa en 1
	 * I: per restaurar tot s'esborra l'edifici en la UA
	 */
	@PrepareForTest({Actualizador.class})
	@Test
	public void test01AnyadirEdificioUnidad() {

		// DONAT QUE: existeix la UA=7
		Long ua_id=1L;
		UnidadAdministrativa ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);

		//I QUE: existeix un edifici=1
		Long edificio_id=7L;

		// I QUE: tenim un mock del actualitzador
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
		Actualizador.borrar(EasyMock.anyObject(),EasyMock.anyObject());
    	PowerMock.expectLastCall();
    	PowerMock.replay(Actualizador.class);
    	
    	// I QUE: tenim el nombre d'edificis d'aquesta UA
		int nedificios = ua.getEdificios().size();

		// QUAN: es crida a unidadFacadeEJB.anyadirEdificio
		uaBean.anyadirEdificio(edificio_id, ua_id);

		// ALESHORES: el nombre d'edificis s'incrementa en 1
		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		int nedificiosAfter = ua.getEdificios().size();

		assertEquals(nedificios+1,nedificiosAfter);
	
		//I: per restaurar tot s'esborra l'edifici en la UA
		uaBean.eliminarEdificio(edificio_id, ua_id);
		
		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		nedificiosAfter = ua.getEdificios().size();
		assertEquals(nedificios,nedificiosAfter);
	
	}
	
	
	/**
	 * ESCENARI: afegir un curriculum del responsable
	 * 
	 * DONAT QUE: existeix la UA=7
	 * I QUE: tenim un mock del actualitzador
	 * I QUE: la UA té el curriculum HTML del responsable en catala
	 * I QUE: te permisos tieneAccesoMoverOrganigrama
	 * I QUE: te permisos tieneAccesoUnidad
	 * QUAN: es crida a unidadFacadeEJB.actualizarUnidadAdministrativa
	 * ALESHORES: quan es recupera la UA te el cv  
	 * 
	 */
	@PrepareForTest({Actualizador.class})
	public void testAñadirCVResponsable() {
		// DONAT QUE: existeix la UA=7
		Long ua_id=1L;
		UnidadAdministrativa ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);

		// I QUE: tenim un mock del actualitzador
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject());
    	PowerMock.expectLastCall();
    	PowerMock.replay(Actualizador.class);
	
		// I QUE: la UA té el curriculum HTML del responsable en catala  
		String cv ="tengo master del universo";
		TraduccionUA traduccioUA=(TraduccionUA)ua.getTraduccion("ca");
		traduccioUA.setCvResponsable(cv);

		// I QUE: te permisos tieneAccesoMoverOrganigrama
		// ver MockAccesoManager
		
		// I QUE: te permisos tieneAccesoUnidad
		// ver MockAccesoManager
		
		// QUAN: es crida a unidadFacadeEJB.actualizarUnidadAdministrativa
		uaBean.actualizarUnidadAdministrativa(ua, 1L);
		
		// ALESHORES: quan es recupera la UA te el cv  
		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		traduccioUA=(TraduccionUA)ua.getTraduccion("ca");
		
		assertEquals(cv,traduccioUA.getCvResponsable());
		
	}

	private void _(Object o){ System.out.println(o); }
}
 