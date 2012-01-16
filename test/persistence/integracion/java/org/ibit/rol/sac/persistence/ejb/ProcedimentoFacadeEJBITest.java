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
import org.ibit.rol.sac.model.ProcedimientoLocal;
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

import test.integracion.persistence.mock.MockProcedimientoFacadeEJB;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/dataSource_persistence.xml", "/dao_persistence.xml"})

@PrepareForTest(Actualizador.class)
@PowerMockIgnore("org.apache.commons.logging.*")  // para q no salga duplicate visibility error

public class ProcedimentoFacadeEJBITest {
	
    protected static Log log = LogFactory.getLog(ProcedimentoFacadeEJBITest.class);


	@Autowired private ApplicationContext applicationContext;

	@Rule    public PowerMockRule rule = new PowerMockRule();
	
	ProcedimientoFacadeEJB procedimentEjbMock;	
	

	@Before
	public void onSetUp() throws Exception {
		
		procedimentEjbMock = (MockProcedimientoFacadeEJB) applicationContext.getBean("procedimientoFacadeEJB");

		Principal p=PowerMock.createMock(Principal.class);
		
		EasyMock.expect(p.getName()).andReturn("u92770");
		EasyMock.replay(p);
		
		SessionContext sctx=EasyMock.createMock(SessionContext.class);
		EasyMock.expect(sctx.getCallerPrincipal()).andReturn(p);
		EasyMock.expect(sctx.isCallerInRole("sacsystem")).andReturn(true).anyTimes();
		EasyMock.replay(sctx);
		
		
		
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		
		procedimentEjbMock.setSessionFactory(sf);
		procedimentEjbMock.setSessionContext(sctx);
	}
	
	/**
	 * ESCENARI: borrar procedimientos sin traduccion en catala
	 * 
	 * DONAT QUE: hi ha procediments sense traduccio en catala
	 * I QUE: tenim un mock del actualitzador
	 * QUAN: es crida a ejbMock.borrarProcedimiento
	 * ALESHORES: borrem els procediments sense  traduccio en catala 
	 * I: no hi ha procediments sense traduccio en catala 
	 */
	@Test
	public void borrarProcedimentsSenseTraduccioEnCatala() {
		
		// DONAT QUE: hi ha procediments sense traduccio en catala
		log.info("comprobant..");
		int numProcsSenseCatala =  llistarProcedimentsSenseCatala().size();
		if(0==numProcsSenseCatala) { 
			log.info("no hi ha cap procediment sense catala");
			return;
		}
		
		log.info("procs que cal borrar "+numProcsSenseCatala);
		
		// I QUE: tenim un mock del actualitzador
		PowerMock.mockStatic(Actualizador.class);
		Actualizador.actualizar(EasyMock.anyObject(),EasyMock.anyObject());
		Actualizador.borrar(EasyMock.anyObject(),EasyMock.anyObject());
		
		 
		// ALESHORES: borrem els procediments sense  traduccio en catala 
		List<ProcedimientoLocal> procs = llistarProcedimentsSenseCatala(); 
		for(ProcedimientoLocal proc : procs) {
			log.info(proc.getId());
			if(null==proc.getTraduccion()) {
				log.info("borrant..");
				procedimentEjbMock.borrarProcedimiento(proc.getId());
				
			}
			
		}
		// I: no hi ha procediments sense traduccio en catala
		log.info("comprobant..");
		numProcsSenseCatala = llistarProcedimentsSenseCatala().size();
		assertSame(0,numProcsSenseCatala);
		
		
	}

	private List<ProcedimientoLocal> llistarProcedimentsSenseCatala() {
		List<ProcedimientoLocal> procs = null; 
		List<ProcedimientoLocal> procsSenseCatala = new ArrayList<ProcedimientoLocal>();
		
		//el motiu pel que es crea un query enlloc de fer un criteria es perque
		//es veu que el criteria fa inner joins, i per tant no retorna els procs
		//amb traduccio null
		
		String hql="from ProcedimientoLocal p";
		Query query;
		try {
			query = procedimentEjbMock.getSession().createQuery(hql);
			procs = query.list();

		} catch (HibernateException e) {
			log.error("error en el query",e);
		}
		
		for(ProcedimientoLocal proc : procs) {
			if(procedimentNoEsCatala(proc)) {
				procsSenseCatala.add(proc);
			}
		}
		return procsSenseCatala;	
	}


	private boolean procedimentNoEsCatala(ProcedimientoLocal proc) {
		return null==proc.getTraduccion("ca");
	}

	
}
 