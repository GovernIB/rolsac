package org.ibit.rol.sac.persistence.ejb;

import java.security.Principal;
import javax.ejb.SessionContext;

import net.sf.hibernate.SessionFactory;

import org.easymock.EasyMock;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import test.integracion.persistence.mock.MockEdificioFacadeEJB;
import test.integracion.persistence.mock.MockUnidadFacadeEJB;

/*
 *  Per executar aquest test d'integració es requereix 
 *  1) posar el flag -javaagent:test/lib/powermock-rule/powermock-module-javaagent-1.4.10.jar
 *  2) tenir  powermock-module-javaagent-1.4.10.jar abans que junit en el classpath 
 *  
 *  El rollback no funciona. Cal mirar perquè.
 * 
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/dataSource_persistence.xml", "/dao_persistence.xml"})

@PrepareForTest(Actualizador.class)
@PowerMockIgnore("org.apache.commons.logging.*")  // para q no salga duplicate visibility error

public class UnidadAdministrativaFacadeEJBTest {

	@Autowired private ApplicationContext applicationContext;

	@Rule    public PowerMockRule rule = new PowerMockRule();
	
	MockUnidadFacadeEJB uaBean;	
	
	MockEdificioFacadeEJB edificioBean;
	
	@BeforeTransaction
	public void onSetUp() throws Exception {
		
		uaBean = (MockUnidadFacadeEJB) applicationContext.getBean("unidadFacadeEJB");

		Principal p=PowerMock.createMock(Principal.class);
		
		EasyMock.expect(p.getName()).andReturn("u92770");
		EasyMock.replay(p);
		
		SessionContext sctx=EasyMock.createMock(SessionContext.class);
		EasyMock.expect(sctx.getCallerPrincipal()).andReturn(p);
		EasyMock.expect(sctx.isCallerInRole("sacsystem")).andReturn(true).anyTimes();
		EasyMock.replay(sctx);
		
		
		
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		
		uaBean.setSessionFactory(sf);
		uaBean.setSessionContext(sctx);

		edificioBean = (MockEdificioFacadeEJB) applicationContext.getBean("edificioFacadeEJB");
		edificioBean.setSessionFactory(sf);
		edificioBean.setSessionContext(sctx);
		
	}
	
	/**
	 * ESCENARI: afegir un edifici a una UA
	 * 
	 * DONAT QUE: existeix la UA=7
	 * I QUE: existeix un edifici=5
	 * I QUE: tenim un mock del actualitzador
	 * I QUE: tenim el nombre d'edificis d'aquesta UA  
	 * QUAN: es crida a unidadFacadeEJB.anyadirEdificio
	 * ALESHORES: el nombre d'edificis s'incrementa en 1
	 */
	@Test
	@Transactional
	public void anyadirEdificioUnidad() {

		// DONAT QUE: existeix la UA=7
		Long ua_id=7L;
		UnidadAdministrativa ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);

		//I QUE: existeix un edifici=5
		Long edificio_id=5L;

		// I QUE: tenim un mock del actualitzador
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
		Actualizador.borrar(EasyMock.anyObject(),EasyMock.anyObject());
    	//PowerMock.expectLastCall();
    	//PowerMock.replay(Actualizador.class);
    	
    	// I QUE: tenim el nombre d'edificis d'aquesta UA
		int nedificios = ua.getEdificios().size();
		

		// QUAN: es crida a unidadFacadeEJB.anyadirEdificio
		uaBean.anyadirEdificio(edificio_id, ua_id);

		// ALESHORES: el nombre d'edificis s'incrementa en 1
		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		int nedificiosAfter = ua.getEdificios().size();

		assertEquals(nedificios+1,nedificiosAfter);

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
	@Test
	@Transactional
	public void anyadirCVResponsable() {
		// DONAT QUE: existeix la UA=7
		Long ua_id=7L;
		Long ua_id_padre=1L;
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
		uaBean.actualizarUnidadAdministrativa(ua, ua_id_padre);
		
		// ALESHORES: quan es recupera la UA te el cv  
		ua = uaBean.obtenerUnidadAdministrativaPM(ua_id);
		traduccioUA=(TraduccionUA)ua.getTraduccion("ca");
		
		assertEquals(cv,traduccioUA.getCvResponsable());
		
	}


}
 