package test.dao;

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


public class ProcedimientoFacadeEJBTest_Spring extends 
//AbstractDependencyInjectionSpringContextTests {
AbstractTransactionalSpringContextTests {

	MockTramiteFacadeEJB tramiteBean;		//afegit en dao.xml
	MockProcedimientoFacadeEJB procBean;	//afegit en dao.xml
	MockDocumentoFacadeEJB docBean; 		//afegit en dao.xml
	MockFormularioFacadeEJB forBean;  		//afegit en dao.xml 
	
	AccesoManagerLocal accesoManager; 
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource.xml",
				"dao.xml"
				};
	}

	@Before
	public void onSetUp() throws Exception {
		tramiteBean = (MockTramiteFacadeEJB) applicationContext.getBean("tramiteFacadeEJB");
		procBean = (MockProcedimientoFacadeEJB) applicationContext.getBean("procedimientoFacadeEJB");
		docBean = (MockDocumentoFacadeEJB)applicationContext.getBean("documentoFacadeEJB");
		forBean = (MockFormularioFacadeEJB)applicationContext.getBean("formularioFacadeEJB");
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		  

		Long tramId=111L;
		Long procId=111L;

		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		/*
		EasyMock.expect(accesoManager.tieneAccesoTramite(tramId)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(222L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(-1L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(0L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(394419L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(procId)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(246L)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		*/

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
		
		tramiteBean.setAccesoManager(accesoManager);
		tramiteBean.setSessionFactory(sf);
		
		procBean.setAccesoManager(accesoManager);
		procBean.setSessionFactory(sf);
		procBean.setSessionContext(sctx);
		
		docBean.setAccesoManager(accesoManager);
		docBean.setSessionFactory(sf);
		docBean.setProcDel(procDelegate);
		docBean.setFichDel(fichDelegate);

		
	}

	public void _testLoadOracleClass() {

		Class clazz=null;
		try {
			String name="org.ibit.rol.sac.model.types.OracleClobType";
			ClassLoader   contextClassLoader = Thread.currentThread().getContextClassLoader();
			if (contextClassLoader!=null) {
				clazz=contextClassLoader.loadClass(name);
			} 
			else {
				clazz = Class.forName(name);
			}
		}
		catch (Exception   e) {
			e.printStackTrace();

		}
		_(clazz);
	}
    	         

	public void _testSessionFactory() {
		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		assertNotNull(sf);
	}

		
	//OK 22.12.09  PROC
	public void _testObtenerProcedimiento()  {
		long pid=246;
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		assertEquals(pid, (long)p.getId());
		//_(p);
		_(p.getTramites().size());
		_(p.getDocumentos().size());
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
	
	//OK 22.12.09  PROC

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
	
	//PROC

	//OK 22.12.09
	public void _testGrabarProcedimientoSimple() {
		Long idUA=40L;
		EasyMock.expect(accesoManager.tieneAccesoUnidad(idUA, false)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);

		ProcedimientoLocal p = creaProcedimientoLocal();
		Long pid=procBean.grabarProcedimiento(p, idUA);
		assertNotNull(pid);
	}
	
/*
	//FALLA. no pone id tramite en formulario
	public void testGrabarProcedimientoConFormulario() {
		Long idUA=40L;
		EasyMock.expect(accesoManager.tieneAccesoUnidad(idUA, false)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);

		ProcedimientoLocal p = creaProcedimientoLocal();
		Long pid=procBean.grabarProcedimiento(p, idUA);
		assertNotNull(pid);
	}
*/
	
	
/*
	//OK
	public void _testGrabar_i_ObtenirTramitAmbFormulari() {
		String nomTramit="tramit test "+new Date();
		ProcedimientoLocal p=procBean.obtenerProcedimiento(246L);
		Tramite t=creaTramite(nomTramit);
		t.setProcedimiento(p);
		
		Long tid=tramiteBean.grabarTramite(t,202L);
		assertNotNull(tid);

		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		
		Formulario f=new Formulario();
		f.setUrl("URL1");
		long fid=forBean.grabarFormulario(f);
		tramiteBean.anyadirFormulario(tid, fid);

		t=tramiteBean.obtenerTramite(tid);
		_(t);

		assertNotSame(0,t.getFormularios().size());
	}
*/
	//OK
	public void _testObtenirFormulari() {
			long fid=394566;
			Formulario f=forBean.obtenerFormulario(fid);
			_(f);
	}
	
	//OK 15/12/2009
	public void _testObtenirFormulari_LLegirTramit() {
		long tid=394477;
		long fid=394566;

		Formulario f=forBean.obtenerFormulario(fid);
		_(f);
		Tramite t=f.getTramite();
		_(t);
	}
	
	public void _testLlegirTramitAmbFormulario() {
		long tid=394477;
		Tramite t=tramiteBean.obtenerTramite(tid);
		_(t);
	}
	
	//OK 25/06/2010
	public void _testI16_AfegirIEsborrarTramitUltim() throws DelegateException {
		long pid=247;
		ProcedimientoLocal p=procBean.consultarProcedimiento(pid);  //tambien lee tramites completos

		
		Tramite t=creaTramite("tramit de test");
		t.setProcedimiento(p);
		
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		procBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);

		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).times(2);
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);

		
		long tid=tramiteBean.grabarTramite(t, 202L);
		_(tid);
		int ntrams = p.getTramites().size();
		procBean.anyadirTramite(tid, pid);  //añade el tramite en un proc. temporal. Ull! p no se modifica.
		
		p=procBean.consultarProcedimiento(pid); //hem de tornar a consultar el proc, perque p no es modifica. 
		assertEquals(ntrams+1,p.getTramites().size());
		
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		tramiteBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		
		procBean.eliminarTramite(tid, pid);
		tramiteBean.borrarTramite(tid);
		
		p=procBean.consultarProcedimiento(pid);
		assertEquals(ntrams,p.getTramites().size());
	}
	
	//OK 25/06/2010
	public void _testI16_AfegirIEsborrarTramitEnmig() throws DelegateException {
		long pid=247;
		ProcedimientoLocal p=procBean.consultarProcedimiento(pid);  //tambien lee tramites completos

		
		Tramite t=creaTramite("tramit de test");
		t.setProcedimiento(p);
		
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		procBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);

		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).times(2);
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);

		
		long tid=tramiteBean.grabarTramite(t, 202L);
		_("afegir tramit "+tid);
		int ntrams = p.getTramites().size();
		procBean.anyadirTramite(tid, pid);  //añade el tramite en un proc. temporal. Ull! p no se modifica.
		
		p=procBean.consultarProcedimiento(pid); //hem de tornar a consultar el proc, perque p no es modifica.
		assertEquals(ntrams+1,p.getTramites().size());

		assert 2>ntrams;
		
		tid=p.getTramites().get(1).getId();  //agafem el tramit 2
		_("borrar tramit "+tid);
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		tramiteBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		
		procBean.eliminarTramite(tid, pid);
		tramiteBean.borrarTramite(tid);
		
		p=procBean.consultarProcedimiento(pid);
		assertEquals(ntrams,p.getTramites().size());
	}

	
	/**
	 * necesito los IDs de los procedimientos
	 * @throws DelegateException 
	 * 
	 */
	public void _testI15OrdenaTramits() throws DelegateException {
		long pid=247;
		
		
		ProcedimientoLocal p=procBean.consultarProcedimiento(pid);  //tambien lee tramites completos
		List<Tramite> tramites = p.getTramites();
		Map<String,String[]> map =new HashMap<String, String[]>();

		Iterator<Tramite> it=tramites.iterator();

		String[] expectedOrder=new String[tramites.size()]; 
		
		//ordenem per ordre invers
		int orden=tramites.size();

		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		procBean.setAccesoManager(accesoManager);

		while(it.hasNext()) {
			String[]params={Integer.toString(orden)};
			long tid=it.next().getId();
			EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);

			String id="orden"+tid;
			map.put(id,params);
			orden--;
			expectedOrder[orden]=Long.toString(tid);
		}
		EasyMock.replay(accesoManager);

		_("expected="+Arrays.toString(expectedOrder));
		
		
		procBean.actualizarOrdenTramites(map);
		
		p=procBean.consultarProcedimiento(pid);  //tambien lee tramites completos
		tramites = p.getTramites();

		String[] actualOrder=new String[tramites.size()];
		it=tramites.iterator();
		while(it.hasNext()) {
			Tramite t=it.next();
			Long ord=t.getOrden();
			_(ord);
			actualOrder[ord.intValue()]=t.getId().toString();
		}
		
		_("actual="+Arrays.toString(actualOrder));


		for(int i=0;i<tramites.size();i++) assertEquals(expectedOrder[i],actualOrder[i]);
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
