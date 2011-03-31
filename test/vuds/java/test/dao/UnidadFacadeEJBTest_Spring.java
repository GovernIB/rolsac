package test.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.SessionContext;

import net.sf.hibernate.SessionFactory;

import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.junit.Before;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;


public class UnidadFacadeEJBTest_Spring extends 
//AbstractDependencyInjectionSpringContextTests {
AbstractTransactionalSpringContextTests {

	MockTramiteFacadeEJB tramiteBean;		//afegit en dao.xml
	MockProcedimientoFacadeEJB procBean;	//afegit en dao.xml
	MockDocumentoFacadeEJB docBean; 		//afegit en dao.xml
	MockFormularioFacadeEJB forBean;  		//afegit en dao.xml  por compatibilitat anterior 

	MockUnidadFacadeEJB uoBean;
	AccesoManagerLocal accesoManager; 

	Long tid=394519L;
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource.xml",
				"dao.xml"
				};
	}

	@Before
	public void onSetUp() throws Exception {
		/* 
		uoBean = (MockUnidadFacadeEJB)applicationContext.getBean("unidadFacadeEJB");
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(accesoManager.tieneAccesoUnidad(1L, true)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);
		uoBean.setAccesoManager(accesoManager);
		uoBean.setSessionFactory(sf);
		*/
	
	}


	public void _testSessionFactory() {
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		assertNotNull(sf);
	}

	public void _testAnyadirEdificio_ActualizadorSinDestinatarios() throws DelegateException {

		//establecer Actualizador
		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).times(2);
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);
		
		uoBean.anyadirEdificio(77L, 1L);
		assertEquals(2,uoBean.consultarUnidadAdministrativa(1L).getEdificios().size());
		
		uoBean.eliminarEdificio(77L, 1L);
		assertEquals(1,uoBean.consultarUnidadAdministrativa(1L).getEdificios().size());
	}

	public void testAnyadirEdificio_Actualizador1Destinatario() throws DelegateException {
		
		//establecer beans
		uoBean = (MockUnidadFacadeEJB)applicationContext.getBean("unidadFacadeEJB");
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(accesoManager.tieneAccesoUnidad(1L, true)).andReturn(true).times(4);
		EasyMock.replay(accesoManager);
		uoBean.setAccesoManager(accesoManager);
		uoBean.setSessionFactory(sf);
		
		//establecer Actualizador
		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		Destinatario dest=new Destinatario();
		dest.setNombre("Portal de l'Informador");
		dest.setEndpoint("https://intranet.caib.es/rolsacpmaesb/services/actualiza");
		dest.setIdRemoto("GOVERN");
		destinatarios.add(dest);
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).times(4);
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);
		

		uoBean.anyadirEdificio(77L, 1L);
		assertEquals(2,uoBean.consultarUnidadAdministrativa(1L).getEdificios().size());
		
		uoBean.eliminarEdificio(77L, 1L);
		assertEquals(1,uoBean.consultarUnidadAdministrativa(1L).getEdificios().size());
		
		  try {
				Actualizador.getActualizador().join();  //esperem a que termini el thread Actualitzador
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	
	public void _testBorrarEdificio() {
		uoBean.eliminarEdificio(77L, 1L);
		assertEquals(1,uoBean.consultarUnidadAdministrativa(1L).getEdificios().size());
	}
		
	// private -------------------------------------------------------

	private void _(Object o){ System.out.println(o); }
}
 