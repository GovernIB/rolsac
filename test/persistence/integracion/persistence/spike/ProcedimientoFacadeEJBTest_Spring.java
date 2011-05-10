package test.integracion.persistence.spike;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.SessionContext;

import net.sf.ehcache.CacheManager;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;

import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;
import org.junit.Before;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import test.integracion.persistence.mock.MockProcedimientoFacadeEJB;

//VER EL TEST COMPLETO EN MIS_TESTS.TXT

public class ProcedimientoFacadeEJBTest_Spring extends 
//AbstractDependencyInjectionSpringContextTests {
AbstractTransactionalSpringContextTests {

	MockProcedimientoFacadeEJB procBean;	//afegit en dao.xml
	
	AccesoManagerLocal accesoManager; 
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource.xml",
				"dao.xml"
				};
	}

	@Before
	public void onSetUp() throws Exception {
		procBean = (MockProcedimientoFacadeEJB) applicationContext.getBean("procedimientoFacadeEJB");
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		  

		Long tramId=111L;
		Long procId=111L;

		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
	

		Principal p=EasyMock.createMock(Principal.class);
		EasyMock.expect(p.getName()).andReturn("u92770");
		EasyMock.replay(p);
		
		SessionContext sctx=EasyMock.createMock(SessionContext.class);
		EasyMock.expect(sctx.getCallerPrincipal()).andReturn(p);
		EasyMock.expect(sctx.isCallerInRole("sacsystem")).andReturn(true).anyTimes();
		EasyMock.replay(sctx);
		
		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios);
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);

		ProcedimientoDelegate procDelegate=	EasyMock.createMock(ProcedimientoDelegate.class);
		FichaDelegate fichDelegate=	EasyMock.createMock(FichaDelegate.class);
		
		ProcedimientoLocal proc=new ProcedimientoLocal();
		proc.setId(246L);
		procDelegate.indexBorraProcedimiento(proc);
		procDelegate.indexInsertaProcedimiento(proc, null);
		EasyMock.replay(procDelegate);
	
		procBean.setAccesoManager(accesoManager);
		procBean.setSessionFactory(sf);
		procBean.setSessionContext(sctx);
			
	}

	//OK 22.12.09  PROC
	public void testObtenerProcedimiento() throws HibernateException  {
		long pid=247;
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		assertEquals(pid, (long)p.getId());
		//_(p);
		_(p.getTramites().size());
		_(p.getDocumentos().size());
		_(p.getMaterias().size());
		_(p.getHechosVitalesProcedimientos().size());
		
		_(p.getHechosVitalesProcedimientos());
		

	}

	//OK 22.12.09  PROC
	public void _testConsultarProcedimientoConTramites()  {
		//long pid=392637L;
		long pid=246L;
		//ProcedimientoLocal p=procBean.obtenerProcedimiento(392637L);
		ProcedimientoLocal p=procBean.consultarProcedimiento(pid);  //tambien lee tramites completos
		_(p);
		assertEquals(pid, (long)p.getId());
		
		TraduccionProcedimientoLocal t = (TraduccionProcedimientoLocal) p.getTraduccion();
		_(t);
		assertEquals(t.getNombre(),"Sol.licitud de canvi de grup d'agència de viatges");
		
		List<Tramite> trams = p.getTramites();
		assertEquals(trams.size(),133);
		
		//aixo obte el nom del primer tramit
		Iterator<Tramite> it=trams.iterator();
		Tramite tram = it.next();
		assertEquals(pid,(long)tram.getProcedimiento().getId());
		TraduccionTramite tt=(TraduccionTramite) tram.getTraduccion();
		assertNotNull(tt.getNombre());
	}
	
	//OK 22.12.09
	public void _testObtenerProcedimientoConTramites() {
		long pid=246L;
		//ProcedimientoLocal p=procBean.obtenerProcedimiento(392637L);
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);  //tambien lee tramites completos
		_("tramites="+p.getTramites());
	}
	
	//PROC
	
	//OK  22.12.09
	public void _testBusquedaProcedimientos()  {
		Map<String,Object>  params = new HashMap<String, Object>();
		Map<String,Object>  trad = new HashMap<String, Object>();
		trad.put("idioma","ca");
		List<ProcedimientoLocal> procs= procBean.buscarProcedimientos(params, trad);
		for(ProcedimientoLocal p:procs) p.toString();
	}
	

	//OK 22.12.09
	public void _testGrabarProcedimientoSimple() {
		Long idUA=40L;
		EasyMock.expect(accesoManager.tieneAccesoUnidad(idUA, false)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);

		ProcedimientoLocal p = creaProcedimientoLocal();
		Long pid=procBean.grabarProcedimiento(p, idUA);
		assertNotNull(pid);
	}

	//------------------------------------------------------------
	
	
	private ProcedimientoLocal creaProcedimientoLocal() {
		ProcedimientoLocal p=new ProcedimientoLocal();
		TraduccionProcedimientoLocal tp=new TraduccionProcedimientoLocal();
		tp.setNombre("procediment de test");
		p.setTraduccion("ca", tp);
		p.setValidacion(Validacion.PUBLICA);
		return p;
	}

	
	//public pq es crida en altres tests
	public Tramite creaTramite(String nomTramit) {
		/*
		selectOption("fase", "Instrucción");
        setTextField("textoFechaCaducidad","10/02/2010");	
        setTextField("textoFechaPublicacion","10/12/2009");
        setTextField("textoFechaActualizacion","10/12/2009");
        setTextField("idUA","1"); //1 = "Govern de les Illes Balears");
        setTextField("descTaxa","descripcion taxa"); 
        setTextField("formaPagamentTaxa","en efectiu");
        setTextField("codiTaxa","123456");
        setTextField("versio","1");
        setTextField("urlExterna","http://urlExterna");
        setTextField("traducciones[0].nombre",nomTramit);
        setTextField("traducciones[0].documentacion","portar dni");
        setTextField("traducciones[0].plazos","3 meses");

		 */
		Tramite t=new Tramite();
		//t.setId(0);  si id=null -> hibernate=insert, sino hibernate=update
		t.setCodiVuds("20091010");
		t.setOrden(0L);
		
		Calendar c=Calendar.getInstance();
		c.set(2010,2,10);
		t.setDataCaducitat(c.getTime());
		c.set(2009,12,10);
	    t.setDataPublicacio(c.getTime());
		c.set(2010,2,10);
	    t.setDataActualitzacio(c.getTime());
	    
	    /* FIXME 
		t.setCodiTaxa("12345");
		t.setDescTaxa("desc");
		t.setFormaPagamentTaxa("");
		*/
		
/*	collections se ponen desde otro action	
		Set<Formulario> formularios=new HashSet<Formulario>();
		Formulario f=new Formulario();
		//f.setId(123L);
		f.setUrl("URL1");
		formularios.add(f);
		t.setFormularios(formularios);
		
		Set<String> docsPresentar = new HashSet<String>();
		docsPresentar.add("DOC1");
		t.setDocsPresentar(docsPresentar);

		Set<Documento> docsInformatius = new HashSet<Documento>();
		Documento docinf=new Documento();
		docinf.setId(333L);
		docsInformatius.add(docinf);
		t.setDocsInformatius(docsInformatius);
*/
		
		TraduccionTramite traduccio=new TraduccionTramite();
		traduccio.setNombre(nomTramit);
		traduccio.setDescripcion("desc");
		traduccio.setObservaciones("obs");
		traduccio.setPlazos("3 mesos");

		
		t.setTraduccion("ca", traduccio);
		ProcedimientoLocal procedimiento = new ProcedimientoLocal();
		procedimiento.setFamilia(new Familia());
		t.setProcedimiento(procedimiento);
		
		// es comenta perque aquest id es posa al fer la crida a grabar()
		//UnidadAdministrativa oc = new UnidadAdministrativa(202L); 
		//t.setOrganCompetent(oc);
		
		return t;
	}
	
	private Documento creaDocumento(String tit)  {
		Documento d=new Documento();
		TraduccionDocumento td=new TraduccionDocumento();
		td.setTitulo(tit);
		d.setTraduccion("ca", td);
		return d;
	}

	private void _(Object o){ System.out.println(o); }
}
