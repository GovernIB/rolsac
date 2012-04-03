package org.ibit.rol.sac.persistence.ejb;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.SessionContext;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.SessionFactory;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.ibit.rol.sac.model.Ordenable;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.junit.Before;
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
import org.ibit.rol.sac.persistence.ws.Actualizador;

import test.integracion.persistence.mock.MockTramiteFacadeEJB;

/*
 *  Per executar aquest test d'integració es requereix 
 *  1) posar el flag -javaagent:test/lib/powermock-rule/powermock-module-javaagent-1.4.10.jar
 *  2) tenir  powermock-module-javaagent-1.4.10.jar abans que junit en el classpath
 *   
    Arguments VM:  
   
	-javaagent:test/lib/powermock-rule-agent/powermock-module-javaagent-1.4.10.jar
	-Dlog4j.debug=true
	-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.Log4JLogger
	-Dlog4j.configuration="log4j-persistence.xml"
	-Xmx756m
 *  
 * 
 */


@RunWith(SpringJUnit4ClassRunner.class)  // para crear tests transactionales con spring usando anotaciones
@ContextConfiguration(locations={"/dataSource_persistence.xml", "/dao_persistence.xml"})

@PrepareForTest(Actualizador.class)  //para permitir crear mock de una clase final 
@PowerMockIgnore("org.apache.commons.logging.*")  // para q no salga duplicate visibility error

public class TramiteFacadeEJBITest {
	
    protected static Log log = LogFactory.getLog(TramiteFacadeEJBITest.class);


	@Autowired private ApplicationContext applicationContext;

	@Rule    public PowerMockRule rule = new PowerMockRule();  //la rule es para no extender
	
	TramiteFacadeEJB tramiteEjbMock;	
	

	@Before
	public void onSetUp() throws Exception {
		
		tramiteEjbMock = (MockTramiteFacadeEJB) applicationContext.getBean("tramiteFacadeEJB");

		Principal p=PowerMock.createMock(Principal.class);
		
		EasyMock.expect(p.getName()).andReturn("u92770");
		EasyMock.replay(p);
		
		SessionContext sctx=EasyMock.createMock(SessionContext.class);
		EasyMock.expect(sctx.getCallerPrincipal()).andReturn(p);
		EasyMock.expect(sctx.isCallerInRole("sacsystem")).andReturn(true).anyTimes();
		EasyMock.replay(sctx);
		
		
		
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		
		tramiteEjbMock.setSessionFactory(sf);
		tramiteEjbMock.setSessionContext(sctx);
	}
	
	
	//@Test
	public void listarTramitsSenseTraduccioEnCatala() {
		List<Tramite> tramits = llistarTramitsSenseCatala();
		for(Tramite t: tramits) {
			log.info(t.getId());
		}
		log.info("total = "+tramits.size());
		
		
	}
	
	/**
	 * ESCENARI: borrar tramites sin traduccion en catala
	 * 
	 * DONAT QUE: hi ha tramits sense traduccio en catala
	 * I QUE: tenim un mock del actualitzador
	 * QUAN: es crida a ejbMock.borrarTramite
	 * ALESHORES: borrem els tramites sense  traduccio en catala 
	 * I: es verifica que no queden tramites sense traduccio en catala 
	 */
	@Test
	public void borrarTramitesSenseTraduccioEnCatala() {
		
		// DONAT QUE: hi ha tramits sense traduccio en catala
		log.info("comprobant..");
		int numTramSenseCatala =  llistarTramitsSenseCatala().size();
		if(0==numTramSenseCatala) { 
			log.info("no hi ha cap tramit sense catala");
			return;
		}
		
		log.info("trams que cal borrar "+numTramSenseCatala);
		
		// I QUE: tenim un mock del actualitzador
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
		Actualizador.borrar(EasyMock.anyObject(),EasyMock.anyObject());
		
		 
		// ALESHORES: borrem els tramits sense  traduccio en catala 
		List<Tramite> trams = llistarTramitsSenseCatala(); 
		for(Tramite tram : trams) {
			log.info(tram.getId());
			if(null==tram.getTraduccion()) {
				log.info("borrant..");
				tramiteEjbMock.borrarTramite(tram.getId());
				
			}
			
		}
		// I: no hi ha tramediments sense traduccio en catala
		log.info("comprobant..");
		numTramSenseCatala = llistarTramitsSenseCatala().size();
		assertSame(0,numTramSenseCatala);
		
		
	}
	
	private List<Tramite> llistarTramitsSenseCatala() {
		List<Tramite> trams = null; 
		List<Tramite> tramsSenseCatala = new ArrayList<Tramite>();
		
		//el motiu pel que es crea un query enlloc de fer un criteria es perque
		//es veu que el criteria fa inner joins, i per tant no retorna els trams
		//amb traduccio null
		
		try {
			String hql="from Tramite p";
			Query query;
			query = tramiteEjbMock.getSession().createQuery(hql);
			trams = query.list();

		} catch (HibernateException e) {
			log.error("error en el query",e);
		}
		
		for(Tramite tram : trams) {
			if(tramitNoEsCatala(tram)) {	
				tramsSenseCatala.add(tram);
			}
		}
		return tramsSenseCatala;	
	}


	private boolean tramitNoEsCatala(Tramite tram) {
		return null==tram.getTraduccion("ca");
	}


}
 