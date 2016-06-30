package org.ibit.rol.sac.persistence.ejb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

import net.sf.hibernate.SessionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.hibernate.hql.ast.tree.DotNode;
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
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;


import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;
import org.ibit.rol.sac.persistence.ws.ReportarFallo;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import es.caib.persistence.vuds.ActualizacionVudsException;
import es.caib.persistence.vuds.ValidateVudsException;

import test.integracion.persistence.mock.MockDocumentoFacadeEJB;
import test.integracion.persistence.mock.MockFormularioFacadeEJB;
import test.integracion.persistence.mock.MockProcedimientoFacadeEJB;
import test.integracion.persistence.mock.MockTramiteFacadeEJB;




@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportarFallo.class)
@PowerMockIgnore("org.apache.commons.logging")  // para q no salga duplicate visibility error

public class TramiteFacadeEJB_SpringTest extends 
//AbstractDependencyInjectionSpringContextTests {
AbstractTransactionalSpringContextTests {
/*
	  <html:option value="1" key="tramite.validacio.publica" />
      <html:option value="2" key="tramite.validacio.interna" />
      <html:option value="3" key="tramite.validacio.reserva" />
   */
	
	
	final long PUBLICO=1;
	final long INTERNA=2;
	final long RESERVA=3;
	 
	protected static Log log = LogFactory.getLog(TramiteFacadeEJB_SpringTest.class);
	
	MockTramiteFacadeEJB tramiteBean;		//afegit en dao.xml
	MockProcedimientoFacadeEJB procBean;	//afegit en dao.xml
	MockDocumentoFacadeEJB docBean; 		//afegit en dao.xml
	MockFormularioFacadeEJB forBean;  		//afegit en dao.xml  por compatibilitat anterior 

	AccesoManagerLocal accesoManager; 

	Long tid=394519L;
	
	protected String[] getConfigLocations() {
		return new String[] {
				"dataSource.xml",
				"dao.xml"
				};
	}

	public void onSetUp() throws Exception {
		log.debug("hola");
		tramiteBean = (MockTramiteFacadeEJB) applicationContext.getBean("tramiteFacadeEJB");
		procBean = (MockProcedimientoFacadeEJB) applicationContext.getBean("procedimientoFacadeEJB");
		docBean = (MockDocumentoFacadeEJB)applicationContext.getBean("documentoFacadeEJB");
		forBean = (MockFormularioFacadeEJB)applicationContext.getBean("formularioFacadeEJB");

		SessionFactory sf=(SessionFactory)applicationContext.getBean("sessionFactory");
		  

		Long tramId=247L;
		Long procId=111L;

		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		EasyMock.expect(accesoManager.tieneAccesoTramite(tramId)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(222L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(-1L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(0L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoTramite(394419L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(procId)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(246L)).andReturn(true).times(1);
		EasyMock.expect(accesoManager.tieneAccesoUnidad(40L, false)).andReturn(true).times(1);
		//EasyMock.replay(accesoManager);

		Principal p=EasyMock.createMock(Principal.class);
		EasyMock.expect(p.getName()).andReturn("u92770");
		EasyMock.replay(p);
		
		SessionContext sctx=EasyMock.createMock(SessionContext.class);
		EasyMock.expect(sctx.getCallerPrincipal()).andReturn(p);
		EasyMock.expect(sctx.isCallerInRole("sacsystem")).andReturn(true).anyTimes();
		EasyMock.replay(sctx);
		
		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		Destinatario d=new Destinatario();
		d.setNombre("VUDS");
		destinatarios.add(d);
		 
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).anyTimes();
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);
		
		

		ProcedimientoDelegate procDelegate=	EasyMock.createMock(ProcedimientoDelegate.class);
		FichaDelegate fichDelegate=	EasyMock.createMock(FichaDelegate.class);
		
		ProcedimientoLocal proc=new ProcedimientoLocal();
		proc.setId(246L);
				
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

	//OK 15.12.09  18/12/2009  21/12/2009
	public void _testObtenirTramitsSenseDocuments() throws InterruptedException {
		long tid=394724L;
		Tramite t=tramiteBean.obtenerTramite(394724L); //394419L);
		_(t);
		assertEquals(tid, (long)t.getId());
	}


	public void _testAddDocumentInformatiuEnTramit_SenseGrabar() {
		long tid=394419L;
		Tramite t=tramiteBean.obtenerTramite(tid);
		
		int ndocsi = t.getDocsInformatius().size();
		int form = t.getFormularios().size();

		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.DOCINFORMATIU);
		
		t.addDocument(d);
		
		 assertEquals(ndocsi+1,t.getDocsInformatius().size());
		 assertEquals(form,t.getFormularios().size());		
		
		
	}
	
	
	//OK 23/12/2009  
	public void _testObtenerDocumentoInformatiuTramit()  {
		long docId=394934; //394801;  OK
		DocumentTramit d=tramiteBean.obtenirDocument(docId);
		assertEquals(docId, (long)d.getId());
		assertEquals(0,d.getTipus());
		_(d);
		TraduccionDocumento td = (TraduccionDocumento )d.getTraduccion();
		_(td.getTitulo());  //23.12.09
	}

	//
	public void _testObtenerFormulariTramit()  {
		long docId=398057;  //394882; //394934; //394801;  OK
		DocumentTramit d=tramiteBean.obtenirDocument(docId);
		assertEquals(docId, (long)d.getId());
		assertEquals(Document.FORMULARI,d.getTipus());
		_(d);
		TraduccionDocumento td = (TraduccionDocumento )d.getTraduccion();
		_(td.getTitulo());
		_(td.getArchivo());
	}
	
	public void _testObtenirDocument() {
		
	}
	
	
	//OK 25.02.2010		30.12.09	23.12.09  
	public void _testAfegirDocumentInformatiuSimpleEnTramit() {
		Tramite t=tramiteBean.obtenerTramite(tid); 
		int ndocsi = t.getDocsInformatius().size();
		_(ndocsi);
		int form = t.getFormularios().size();

		assertNotNull(t);
		
		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.DOCINFORMATIU);
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		_(docId);
		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.DOCINFORMATIU,d.getTipus());

		assertEquals((long)tid,(long)d.getTramit().getId());  //23.12.09
		
		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(form,t.getFormularios().size());
		 assertEquals(ndocsi+1,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion();
		assertEquals("doc111",td.getTitulo());
		 
		_(t);
		 
	}

	//OK 25.02.2010
	public void _testAfegirIBorrarFormulariSimpleEnTramit() {
		Tramite t=tramiteBean.obtenerTramite(tid); 
		int ndocsi = t.getDocsInformatius().size();
		_(ndocsi);
		int nforms = t.getFormularios().size();

		assertNotNull(t);
		
		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.FORMULARI);
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		_(docId);
		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.FORMULARI,d.getTipus());

		assertEquals((long)tid,(long)d.getTramit().getId());  //23.12.09
		
		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(nforms+1,t.getFormularios().size());
		 assertEquals(ndocsi,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion();
		assertEquals("doc111",td.getTitulo());
		
		_(t);
		
		//borrar document
		tramiteBean.borrarDocument(docId);
		
		 t=tramiteBean.obtenerTramite(tid);
		assertEquals(nforms,t.getFormularios().size());
		d=null;
		try {
			d=tramiteBean.obtenirDocument(docId);	
		} catch (Exception e) {
			
		}
		
		assertNull(d);
	}

	public void _testObtenerProc_AfegirIBorrarDocumentInformatiuSimpleEnTramit() {

		long pid=247L;
		
		//obtenir procediment
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		assertEquals(pid, (long)p.getId());
		assertTrue(p.getTramites().iterator().hasNext());
		
		long tid = p.getTramites().iterator().next().getId();

		

		//afegir document en tramit
		Tramite t=tramiteBean.obtenerTramite(tid); 
		int ndocsi = t.getDocsInformatius().size();
		_(ndocsi);
		int form = t.getFormularios().size();

		assertNotNull(t);
		
		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.DOCINFORMATIU);
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		_(docId);
		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.DOCINFORMATIU,d.getTipus());

		assertEquals((long)tid,(long)d.getTramit().getId());  //23.12.09
		
		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(form,t.getFormularios().size());
		 assertEquals(ndocsi+1,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion();
		assertEquals("doc111",td.getTitulo());
		
		_(t);
		
		//borrar document
		tramiteBean.borrarDocument(docId);
		
		 t=tramiteBean.obtenerTramite(tid);
		assertEquals(ndocsi,t.getDocsInformatius().size());
		d=null;
		try {
			d=tramiteBean.obtenirDocument(docId);	
		} catch (Exception e) {
			
		}
		assertNull(d);

		//obtenir procediment
		
		_("**** obteniendo procediment");
		
		p=procBean.obtenerProcedimiento(pid);
		
		
		Iterator<Tramite> it= p.getTramites().iterator();
		while(it.hasNext()) {
			if(tid == it.next().getId()) {
				t=tramiteBean.obtenerTramite(tid); 
				assertEquals(ndocsi, t.getDocsInformatius().size());
			}
		}
		
		
	}

	//OK 25.02.2009		15.02.09
	public void _testAfegirIBorrarDocumentInformatiuSimpleEnTramit() {
		
		Tramite t=tramiteBean.obtenerTramite(tid); 
		int ndocsi = t.getDocsInformatius().size();
		_(ndocsi);
		int form = t.getFormularios().size();

		assertNotNull(t);
		
		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.DOCINFORMATIU);
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		_(docId);
		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.DOCINFORMATIU,d.getTipus());

		assertEquals((long)tid,(long)d.getTramit().getId());  //23.12.09
		
		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(form,t.getFormularios().size());
		 assertEquals(ndocsi+1,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion();
		assertEquals("doc111",td.getTitulo());
		
		_(t);
		
		//borrar document
		tramiteBean.borrarDocument(docId);
		
		 t=tramiteBean.obtenerTramite(tid);
		assertEquals(ndocsi,t.getDocsInformatius().size());
		d=null;
		try {
			d=tramiteBean.obtenirDocument(docId);	
		} catch (Exception e) {
			
		}
		assertNull(d);
	}




	
	//OK 30/12/2009
	//FALLA 19/01/2010 pq hi ha docs informatius repetits amb mateix num orden, i al fer el Set no es posen tots.
	public void _testAfegirDocumentInformatiuAmbArxiuEnTramit() throws IOException {
		long tid=394419L;

		Tramite t=tramiteBean.obtenerTramite(tid); 
		int ndocsi = t.getDocsInformatius().size();
		_(ndocsi);
		int form = t.getFormularios().size();
		_(form);
		
		assertNotNull(t);
		
		String fname="spy.properties";
		Archivo archivo=creaArchivo(fname);
		DocumentTramit d = creaDocument("doc111", archivo);
		d.setTipus(DocumentTramit.DOCINFORMATIU);
		d.setArchivo(creaArchivo("spy.properties"));
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		_(docId);
		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.DOCINFORMATIU,d.getTipus());

		assertEquals((long)tid,(long)d.getTramit().getId());  //23.12.09
		
		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(form,t.getFormularios().size());
		 assertEquals(ndocsi+1,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion();
		assertEquals("doc111",td.getTitulo());
		Archivo arxiuLlegit=td.getArchivo();
		 
		assertEquals(archivo.getNombre(),arxiuLlegit.getNombre());
		assertEquals(archivo.getPeso(),arxiuLlegit.getPeso());
		
		_(t);
		_(arxiuLlegit);
		 
	}

	
	
	public void _testObtenerArxiuTramit() throws IOException {
		long tid=395316;
		Tramite t=tramiteBean.obtenerTramite(tid);
		Set<DocumentTramit> forms=t.getFormularios();
		DocumentTramit dt=forms.iterator().next();
		
		TraduccionDocumento td=(TraduccionDocumento) dt.getTraduccion();
		_(td.getArchivo());

	
	}

	public void _testTmpObtenerTramite() throws IOException {
		long tid=395316;
		Tramite t=tramiteBean.obtenerTramite(tid);
		_(t.getFormularios());
	
	}

	
	public void _testTmpBorrarDocuments() {
		long tid=394419;
		long docs[]={ 395385 };
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(docs.length);
		EasyMock.replay(accesoManager);

		for(long d:docs) {
			
			tramiteBean.borrarDocument(d);
		}
	}
	
	//OK 23/02/2010 - 21/01/2010 - 11/05/2010
	//Atencio, no es pot usar P6Log (BLOB) -> cal modificar el driver en db.properties
	public void _testAfegirFormulariAmbArxiuEnTramit() throws IOException {
		tid=396203L;
		_(tid);
		afegirDocumentAmbArxiuEnTramit("formDoc1",tid, DocumentTramit.FORMULARI);
	}
	/* TODO
	public void testIndicendia() throws IOException {
		tid=396203L;
		_(tid);
		afegirDocumentAmbArxiuEnTramit("formDoc2",tid, DocumentTramit.FORMULARI);
		
	}
	*/
	
	//OK 21/01/2010
	public void _testBorrarTramitsDesdeArxiu() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\tramits3.txt"));
		String tid_s=null;
		ArrayList<Long> tids = new ArrayList<Long>();
		while(null!=(tid_s=br.readLine())) {
			//_("borrant "+tid_s);
			long tid=Long.parseLong(tid_s);
			EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
			tids.add(tid);
		}
		EasyMock.replay(accesoManager);

		for(Long tid:tids) { _("borrant "+tid); tramiteBean.borrarTramite(tid);}
		br.close();
	}
	
	//OK 22.12.2009
	public void testAfegirFormulariEnTramit() {

		tid=621639L;
		Tramite t=tramiteBean.obtenerTramite(tid);
		int ndocsi = t.getDocsInformatius().size();
		int form = t.getFormularios().size();

		assertNotNull(t);
		
		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.FORMULARI);
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.FORMULARI,d.getTipus());

		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(form+1,t.getFormularios().size());
		 assertEquals(ndocsi,t.getDocsInformatius().size());

		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion("ca");
		assertEquals("doc111",td.getTitulo());

		 
		_(t);
		 
	}
	
	
	//OK 22/12/09
	public void _testObtenerTramiteConProcedimentos() throws InterruptedException {
		long tid=394419L; // 621639L; //394724L;//394419L);
		Tramite t=tramiteBean.obtenerTramite(tid); 
		assertNotNull(t.getProcedimiento());
		_(t);
		_(t.getDocsInformatius().size());
		_(t.getFormularios().size());
		assertEquals(tid, (long)t.getId());
	}


	
	/*
	public void _testAfegirDocumentoEnProcedimiento() {
		Documento d= creaDocumento("doc11");
		docBean.grabarDocumento(d, 246L, null, null,null);
	}
	*/

	//OK 16.09.2010 - 23.02.2010 -  22.12.09 
	public void _testAfegirTramitNoVuds_SenseDocuments() throws ValidateVudsException, ActualizacionVudsException {

		long pid=247L;
		
		//obtenim el pare
		String nomTramit="tramit test "+new Date();
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		int ntramits=p.getTramites().size();
		
		//afegim un fill
		Tramite t=creaTramite(nomTramit,1,0,0,false);

		t.setProcedimiento(p);  //TODO no recuerdo porque se pone aqui el procedimiento.
		_(t);
		Long tid=tramiteBean.grabarTramite(t,202L);
		_(tid);
		assertNotNull(tid);
		t=tramiteBean.obtenerTramite(tid);
		_(t);
		assertNotNull(t.getProcedimiento());
		assertNotNull(t.getOrganCompetent());
		_("formularios="+t.getFormularios());
		assertEquals(0, t.getFormularios().size());

		
		//actualitzam el pare
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		procBean.anyadirTramite(t.getId(), pid);		
		p=procBean.obtenerProcedimiento(pid);
		
		assertEquals(ntramits+1,p.getTramites().size());
	}
	
	//OK 22.12.2009
	public void testAfegirDocumentInformatiuEnTramit() {

		tid=621639L;
		Tramite t=tramiteBean.obtenerTramite(tid);
		int ndocsi = t.getDocsInformatius().size();
		int form = t.getFormularios().size();

		assertNotNull(t);
		
		DocumentTramit d = creaDocument("doc111");
		d.setTipus(DocumentTramit.DOCINFORMATIU);
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
	
		Long docId= tramiteBean.grabarDocument(d, tid);

		d=tramiteBean.obtenirDocument(docId);
		assertEquals(DocumentTramit.DOCINFORMATIU,d.getTipus());

		 t=tramiteBean.obtenerTramite(tid);
		 
  		 _(ndocsi);
		 _(t.getDocsInformatius().size());

		 assertEquals(form,t.getFormularios().size());
		 assertEquals(ndocsi+1,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion("ca");
		assertEquals("doc111",td.getTitulo());

		 
		_(t);
		 
	}


	public void _testCrearTramitEnVentanilla() throws ActualizacionVudsException  {

		// el tramit s'envia a la ventanilla quan es public i no te data d'actualitzacio vuds.
		// si funciona OK, no es genera cap excepcio i el camp data d'actualitzacio vuds no esta buit.
		// si es genera una ActualizacionVudsException, vol dir  
		
		long pid=247L;
		
		//obtenim el pare
		String nomTramit="tramit test "+new Date();
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		int ntramits=p.getTramites().size();
		
		//creem un tramit de test
		Tramite t=creaTramite(nomTramit,0,0,0,true);

		t.setProcedimiento(p);  //TODO no recuerdo porque se pone aqui el procedimiento.
		t.setValidacio(PUBLICO);
		t.setDataActualitzacioVuds("");
		_(t);
		
		Long tid=null;
		try {
			tid = tramiteBean.grabarTramite(t,202L);
			_(tid);
			assertNotNull(tid);
			t=tramiteBean.obtenerTramite(tid);
			assertEquals(t.getOperativa(),Operativa.CREA);
			assertNotNull(t.getDataActualitzacioVuds());
			assertNotSame("",t.getDataActualitzacioVuds());
			_(t);
		} catch (ValidateVudsException e) {
			_(Arrays.toString(e.getCampsSenseValidar()));
			fail();
		}
		finally {
			//actualitzam el pare
			EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(1);
			EasyMock.replay(accesoManager);
			procBean.anyadirTramite(t.getId(), pid);		
			p=procBean.obtenerProcedimiento(pid);
			assertEquals(ntramits+1,p.getTramites().size());
		}

	}

	
	// el siguiente test prueba que al grabar un tramite interno de ventanilla unica no se
	// valida los campos vuds
	public void __testGrabarTramiteNoValidaVuds() {
		
	}
	
	
	// el siguiente test prueba que al grabar un tramite publico de ventanilla unica si se
	// validan los campos vuds

	
	public void _testCrearIModificarTramitEnVentanilla() throws ActualizacionVudsException  {

		// el tramit s'envia a la ventanilla quan es public i no te data d'actualitzacio vuds.
		// el test pasa si no es genera cap excepcio i el camp data d'actualitzacio vuds no esta buit.
		// si es genera una ActualizacionVudsException, vol dir
		
		//pas 1 crear tramit
		//pas 2 modificar tramit
		
		//pas 1 crear tramit en la ventanilla
		long pid=247L;
		
		//obtenim el pare
		String nomTramit="tramit test "+new Date();
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		int ntramits=p.getTramites().size();
		
		//creem un tramit de test
		Tramite t=creaTramite(nomTramit,0,0,0,true);

		t.setProcedimiento(p);  //TODO no recuerdo porque se pone aqui el procedimiento.
		t.setValidacio(PUBLICO);
		t.setDataActualitzacioVuds("");
		_(t);
		
		Long tid=null;
		try {
			tid = tramiteBean.grabarTramite(t,202L);
			_(tid);
			assertNotNull(tid);
			assertEquals(t.getOperativa(),Operativa.CREA);
			String data=t.getDataActualitzacioVuds();
			assertNotNull(data);
			assertNotSame("",data);
			_(t);
		} catch (ValidateVudsException e) {
			_(Arrays.toString(e.getCampsSenseValidar()));
			fail();
		}
		finally {
			//actualitzam el pare
			EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(1);
			EasyMock.replay(accesoManager);
			procBean.anyadirTramite(t.getId(), pid);		
			p=procBean.obtenerProcedimiento(pid);
			assertEquals(ntramits+1,p.getTramites().size());
		}

		
		//pas 2 modificar tramit  en la ventanilla
		try {
			accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
			EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
			EasyMock.replay(accesoManager);
			tramiteBean.setAccesoManager(accesoManager);


			t=tramiteBean.obtenerTramite(tid);
			t.setProcedimiento(p);

			String data1=t.getDataActualitzacioVuds();
			tid = tramiteBean.grabarTramite(t,202L);
			_(tid);
			assertNotNull(tid);
			assertEquals(t.getOperativa(),Operativa.MODIFICA); //esto hay q hacerlo antes de obtener el tramite modificado, 
															// pq operativa no se guarda en base de datos
			
			t=tramiteBean.obtenerTramite(tid);
			t.setProcedimiento(p);
			
			String data2=t.getDataActualitzacioVuds();

			
			assertNotNull(data2);
			assertNotSame("",data2);
			assertNotSame(data1,data2);
			_(t);
		} catch (ValidateVudsException e) {
			_(Arrays.toString(e.getCampsSenseValidar()));
			fail();
		}
	}

/*	
	public void testFallaActualitzarVuds() throws DelegateException {
		// este test verifica que si no se ha podido actualizar el servidor VUDS, 
		// se genera una excepcion ActualizacionVudsException

		long pid=247L;
		
		//obtenim el pare
		String nomTramit="tramit test "+new Date();
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		int ntramits=p.getTramites().size();
		
		//creem un tramit de test
		Tramite t=creaTramite(nomTramit,0,0,0,true);
		
		t.setProcedimiento(p);  //TODO no recuerdo porque se pone aqui el procedimiento.
		t.setValidacio(PUBLICO);
		t.setDataActualitzacioVuds("");
		_(t);

		//crear destinatario mock
		List<Destinatario> destinatarios=new ArrayList<Destinatario>();
		
//		Destinatario d=new DestinatarioVUDS() {
//			
//			@Override
//			public void actualiza(Object elem) throws WSInvocatorException {
//				throw new WSInvocatorException(null);
//		}};
		
//		d.setNombre("VUDS");
//		destinatarios.add(d);
		
		//creacion de un destinatario delegate mock
		DestinatarioDelegate delegateMock = EasyMock.createMock(DestinatarioDelegate.class);
		EasyMock.expect(delegateMock.listarDestinatarios()).andReturn(destinatarios).times(2);
		EasyMock.replay(delegateMock);
		Actualizador.setDestDelegate(delegateMock);
		

		//creacion de un metodo mock para reportarFallo
		PowerMockito.mockStatic(ReportarFallo.class);

		//ReportarFallo fallo=mock(ReportarFallo.class);
		
		PowerMockito.doNothing().when(ReportarFallo.class); //.reportar(t, false, d, org.mockito.Matchers.any(Exception.class));
				
		
		Long tid=null;
		try {
			tid = tramiteBean.grabarTramite(t,202L);
			_(tid);
			assertNotNull(tid);
			assertEquals(t.getOperativa(),Operativa.CREA);
			String data=t.getDataActualitzacioVuds();
			assertNotNull(data);
			assertNotSame("",data);
			_(t);
		} catch (ValidateVudsException e) {
			_(Arrays.toString(e.getCampsSenseValidar()));
			fail();
		} catch (ActualizacionVudsException e) {
			return;
		}
		finally {
			//actualitzam el pare
			EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(1);
			EasyMock.replay(accesoManager);
			procBean.anyadirTramite(t.getId(), pid);		
			p=procBean.obtenerProcedimiento(pid);
			assertEquals(ntramits+1,p.getTramites().size());
		}
	}
*/	
 
	//OK 23.02.2010
	public void _testAfegirTramit_Complert() throws IOException, ValidateVudsException, ActualizacionVudsException {
		
		long pid=247;
		
		//obtenim el pare
		String nomTramit="tramit test "+new Date();
		ProcedimientoLocal p=procBean.obtenerProcedimiento(pid);
		int ntramits=p.getTramites().size();
		
		//afegim un fill
		Tramite t=creaTramite(nomTramit,1,0,0,true);

		t.setProcedimiento(p);  //TODO no recuerdo porque se pone aqui el procedimiento.
		_(t);
		Long tid=tramiteBean.grabarTramite(t,202L);
		
		afegirDocumentAmbArxiuEnTramit("doc1", tid, DocumentTramit.DOCINFORMATIU);
		afegirDocumentAmbArxiuEnTramit("form1", tid, DocumentTramit.FORMULARI);
		afegirTaxaEnTramit("taxa1", tid);
		
		_(tid);
		assertNotNull(tid);
		t=tramiteBean.obtenerTramite(tid);
		_(t);
		assertNotNull(t.getProcedimiento());
		assertNotNull(t.getOrganCompetent());
		_("formularios="+t.getFormularios());
		assertSame(1, t.getFormularios().size());
		assertSame(1, t.getDocsInformatius().size());
		assertSame(1, t.getTaxes().size());

		
		//actualitzam el pare
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		procBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoProcedimiento(pid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		procBean.anyadirTramite(t.getId(), pid);		
		p=procBean.obtenerProcedimiento(pid);
		
		assertEquals(ntramits+1,p.getTramites().size());
	}

	
	//OK 22.12.2009
	public void _testLlegirFormulari_v1() {
			long fid=394566;
			Formulario f=forBean.obtenerFormulario(fid);
			_(f);
	}

	//OK 22.12.2009
	public void _testLlegirFormulari_v2() {
			long fid=394836;
			DocumentTramit f=tramiteBean.obtenirDocument(fid);
			assertEquals(DocumentTramit.FORMULARI, f.getTipus());
			_(f);
	}
	
	//OK 22/12/2009
	//TODO migrar formularis existents en RSC_FORMUL cap a RSC_DOCTRA
	public void _testObtenirFormulariV1_LlegirTramit() {
		long tid=394477;
		long fid=394566;

		Formulario f=forBean.obtenerFormulario(fid);
		_(f);
		Tramite t=f.getTramite();
		_(t);
		Set<DocumentTramit> fs = t.getFormularios();
		for(Iterator<DocumentTramit> it=fs.iterator();it.hasNext(); ) {
			if(fid==it.next().getId()) fail();
		}
	}
	
	//OK 22/12/2009
	public void _testObtenirFormulariV2_LlegirTramit() {
		long fid=394836;

		DocumentTramit f=tramiteBean.obtenirDocument(fid);
		_(f);
		Tramite t=f.getTramit();
		_(t);
		Set<DocumentTramit> fs = t.getFormularios();
		assertNotSame(0,fs.size());
		for(Iterator<DocumentTramit> it=fs.iterator();it.hasNext(); ) {
			if(fid==it.next().getId()) return;
		}
		fail();
	}
	
	//OK 18/01/2010
	public void _testReordenarFormularisTramit() throws DelegateException {
		long tid=394419L;

		Tramite t=tramiteBean.obtenerTramite(tid);
		
		Set<DocumentTramit> forms=t.getFormularios();
		Map<String,String[]> map=new HashMap<String, String[]>();
		for(Iterator<DocumentTramit> it=forms.iterator();it.hasNext();) {
			DocumentTramit doc=it.next();
			String name="orden_doc"+doc.getId();
			map.put(name, new String[]{Long.toString(doc.getOrden())});
		}
		_(t.getFormularios());

		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);

		tramiteBean.actualizarOrdenDocs(map,tid);
		t=tramiteBean.obtenerTramite(tid);
		forms = t.getFormularios();
		
		assertTrue(isSorted(forms));
		
	}

	//OK 18/01/2010
	public void _testReordenarDocsInformatius() throws DelegateException {
		long tid=394419L;

		Tramite t=tramiteBean.obtenerTramite(tid);
		
		Set<DocumentTramit> docs=t.getDocsInformatius();
		_(docs);
		assertTrue(isSorted(docs));
		
		Map<String,String[]> map=new HashMap<String, String[]>();
		for(Iterator<DocumentTramit> it=docs.iterator();it.hasNext();) {
			DocumentTramit doc=it.next();
			String name="orden_doc"+doc.getId();
			map.put(name, new String[]{Long.toString(doc.getOrden())});
		}


		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);

		tramiteBean.actualizarOrdenDocs(map,tid);
		
		t=tramiteBean.obtenerTramite(tid);
		docs = t.getDocsInformatius();
		assertTrue(isSorted(docs));
		
	}

	
	public void _testReadArchivo() throws IOException {
		String fname="spy.properties";
		Archivo a=creaArchivo(fname);
		assertNotSame(0,a.getPeso());
		assertEquals(fname,a.getNombre());
		_(a);
	}
		

	//OK 23.02.2010 - 22.12.2009
	public void _testAfegirBorrarTaxaEnTramit() {
		long tid=394564L;

		//pas 1: afegir taxa
		
		long ntaxes=tramiteBean.obtenerTramite(tid).getTaxes().size();
		 
		
		long taxId = afegirTaxaEnTramit("cod-001",tid);
		_(taxId);
/*		
		Tramite t=tramiteBean.obtenerTramite(tid);
		int ntax = t.getTaxes().size();

		Taxa tx=creaTaxa("cod-001");
		
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(2);
		EasyMock.replay(accesoManager);
	
		Long taxId= tramiteBean.grabarTaxa(tx, tid);

		tx=tramiteBean.obtenirTaxa(taxId);
		TraduccionTaxa td=(TraduccionTaxa) tx.getTraduccion();
		assertEquals("codi-001",td.getCodificacio());
		
		 t=tramiteBean.obtenerTramite(tid);
		 assertEquals(ntax+1,t.getTaxes().size());
		 
		_(t);
*/		
		//pas 2: borrar taxa
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		tramiteBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		tramiteBean.borrarTaxa(taxId);
		
		assertEquals(ntaxes,tramiteBean.obtenerTramite(tid).getTaxes().size());
		 
	}

	
	
	// private -------------------------------------------------------

	private long afegirTaxaEnTramit(String codi, long tid)  {
		Tramite t=tramiteBean.obtenerTramite(tid);
		assertNotNull(t);

		int ntaxes=t.getTaxes().size();
		
		
		Taxa taxa=creaTaxa("cod-001");
		TraduccionTaxa tt=(TraduccionTaxa)taxa.getTraduccion();
		
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		tramiteBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		long taxid=tramiteBean.grabarTaxa(taxa, tid);

		t=tramiteBean.obtenerTramite(tid);
		assertEquals(ntaxes+1,t.getTaxes().size());
		
		Taxa taxa2=tramiteBean.obtenirTaxa(taxid);
		TraduccionTaxa tt2=(TraduccionTaxa)taxa2.getTraduccion();
		 
		assertTrue(tt.equals(tt2));
		
		return taxid;
	}

	
	private long afegirDocumentAmbArxiuEnTramit(String nom, long tid, int tipus) throws IOException {

		Tramite t=tramiteBean.obtenerTramite(tid);
		long orden=0;
		int nforms=0;
		int ndocs=0;
		
		if (tipus==DocumentTramit.FORMULARI) { 
			nforms = t.getFormularios().size();
			for(Iterator<DocumentTramit> it=t.getFormularios().iterator();it.hasNext();) {
				DocumentTramit dt=it.next();
				if (dt.getOrden()> orden) orden=dt.getOrden(); 
			}
		}
		else if (tipus==DocumentTramit.DOCINFORMATIU) { 
			ndocs = t.getDocsInformatius().size();
			for(Iterator<DocumentTramit> it=t.getFormularios().iterator();it.hasNext();) {
				DocumentTramit dt=it.next();
				if (dt.getOrden()> orden) orden=dt.getOrden(); 
			}
		} 

		assertNotNull(t);
		
		String fname="spy.properties";
		Archivo archivo=creaArchivo(fname);
		DocumentTramit d = creaDocument(nom, archivo);
		d.setTipus(tipus);
		d.setArchivo(creaArchivo("spy.properties"));
		d.setOrden(orden+1);	
		
		accesoManager = EasyMock.createMock(AccesoManagerLocal.class);
		tramiteBean.setAccesoManager(accesoManager);
		EasyMock.expect(accesoManager.tieneAccesoTramite(tid)).andReturn(true).times(1);
		EasyMock.replay(accesoManager);
		Long docId= tramiteBean.grabarDocument(d, tid);
		_(docId);
		_("document grabat: "+ d);
		d=tramiteBean.obtenirDocument(docId);
		assertEquals(tipus,d.getTipus());
		assertEquals((long)tid,(long)d.getTramit().getId());  //23.12.09
		
		t=tramiteBean.obtenerTramite(tid);
		 
		if (tipus==DocumentTramit.FORMULARI) 
			 assertEquals(nforms+1,t.getFormularios().size());
		else if (tipus==DocumentTramit.DOCINFORMATIU)
			 assertEquals(ndocs+1,t.getDocsInformatius().size());
		 
		TraduccionDocumento td=(TraduccionDocumento) d.getTraduccion();
		assertEquals(nom,td.getTitulo());
		Archivo arxiuLlegit=td.getArchivo();
		 
		assertEquals(archivo.getNombre(),arxiuLlegit.getNombre());
		assertEquals(archivo.getPeso(),arxiuLlegit.getPeso());
		
		_(t);
		_(arxiuLlegit);
		
		return docId;
	}

	
	private boolean isSorted(Set<DocumentTramit> docs) {
		if(1==docs.size()) return true;
		boolean sorted=true;
		long old=-1;
		Iterator<DocumentTramit> it=docs.iterator();
		while(it.hasNext()) {
			long s1=it.next().getOrden();
			if(old>s1) {sorted=false; break;}
			old=s1;
		}
		return sorted;
	}
	
	private ProcedimientoLocal creaProcedimientoLocal() {
		ProcedimientoLocal p=new ProcedimientoLocal();
		TraduccionProcedimientoLocal tp=new TraduccionProcedimientoLocal();
		tp.setNombre("procediment de test");
		p.setTraduccion("ca", tp);
		p.setValidacion(Validacion.PUBLICA);
		return p;
	}

	
	//public pq es crida en altres tests
	public Tramite creaTramite(String nomTramit, int ntaxes, int ndocsInf, int nforms,boolean vuds) {
	
		
		Tramite t=new Tramite();
		//t.setId(0);  si id=null -> hibernate=insert, sino hibernate=update
		
		
		TraduccionTramite tt=new TraduccionTramite();
		tt.setNombre(nomTramit);
		tt.setDescripcion("la meva descripcio");
		tt.setObservaciones("les meves observacions");
		tt.setPlazos("3 mesos");
		tt.setLugar("el meu lloc");
		tt.setRequisits("ser major de 18 anys");
		tt.setDocumentacion("-DNI -Llibre de familia");
		
		t.setTraduccion("ca", tt);
		ProcedimientoLocal procedimiento = new ProcedimientoLocal();
		procedimiento.setId(247L);
		procedimiento.setFamilia(new Familia());
		Iniciacion ini=new Iniciacion();
		ini.setId(0L);
		TraduccionIniciacion ti=new TraduccionIniciacion();
		ti.setNombre("ofici");
		ini.setTraduccion("ca", ti);
		procedimiento.setIniciacion(ini);
		procedimiento.setTaxa("on");
		if(vuds) procedimiento.setVentanillaUnica("1");
		
		TraduccionProcedimientoLocal trapro = new TraduccionProcedimientoLocal();
		trapro.setResultat("un resultat");
		procedimiento.setTraduccion("ca", trapro);

		UnidadAdministrativa oc = new UnidadAdministrativa(202L);
		TraduccionUA toc = new TraduccionUA();
		toc.setNombre("govern illes balears");
		oc.setTraduccion("ca", toc);
		procedimiento.setUnidadAdministrativa(oc);

		t.setProcedimiento(procedimiento);

		t.setValidacio(PUBLICO);
		
		t.setCodiVuds("0001"); //new Date().toString());
		t.setDescCodiVuds("el meu codi vuds");
		
		Calendar c=Calendar.getInstance();
		c.set(2010,2,10);
		t.setDataCaducitat(c.getTime());
		c.set(2009,12,10);
	    t.setDataPublicacio(c.getTime());
		c.set(2010,2,10);
	    t.setDataActualitzacio(c.getTime());
	    
		
		Set<DocumentTramit> formularios=new HashSet<DocumentTramit>();
		/*
		for(int i=0; i<nforms; i++) {
			DocumentTramit f=new DocumentTramit();
			f.setTraduccion(idioma, traduccion)
			f.se
			f.setId((long)i);
			f.setUrl("la meva url");
			f.setArchivo(creaArchivo("el meu arxiu"+i));
			
		}
		*/
		t.setFormularios(formularios);
		
		
		Set<DocumentTramit> dinf=new HashSet<DocumentTramit>();
		t.setDocsInformatius(dinf);

		
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
		
		UnidadAdministrativa ocr = new UnidadAdministrativa(202L);
		TraduccionUA tua = new TraduccionUA();
		tua.setNombre("govern illes balears");
		ocr.setTraduccion("ca", tua);
		t.setOrganCompetent(ocr);


	
		Set<Taxa> taxes=new HashSet<Taxa>();
		for (int i=0; i<ntaxes; i++) {
			Taxa tx=new Taxa();
			TraduccionTaxa ttx=new TraduccionTaxa();
			ttx.setCodificacio("cod-00"+i);
			ttx.setDescripcio("descripcio tasa "+i);
			ttx.setFormaPagament("pagament tasa "+i);
			tx.setTraduccion("ca", ttx);
			taxes.add(tx);
		}
		t.setTaxes(taxes);
		
		
		// es comenta perque aquest id es posa al fer la crida a grabar()
		//UnidadAdministrativa oc = new UnidadAdministrativa(202L); 
		//t.setOrganCompetent(oc);
		
		// si vuds, traduir els camps
		if(vuds) {
			tt=new TraduccionTramite();
			tt.setNombre(nomTramit);
			tt.setDescripcion("mi descripcion");
			tt.setObservaciones("mis observaciones");
			tt.setPlazos("3 meses");
			tt.setLugar("mi sitio");
			tt.setRequisits("ser mayor de 18 anys");
			tt.setDocumentacion("-DNI -Libro de familia");
			t.setTraduccion("es", tt);			
		}
		
		return t;
	}
	
	private DocumentTramit creaDocument(String tit)  {
		DocumentTramit d=new DocumentTramit();
		TraduccionDocumento td=new TraduccionDocumento();
		td.setTitulo(tit);
		d.setTraduccion("ca", td);
		
		td=new TraduccionDocumento();
		td.setTitulo("english title");
		d.setTraduccion("en", td);
		
		return d;
	}
	
	private Taxa creaTaxa(String codi) {
		Taxa t=new Taxa();
		TraduccionTaxa tt=new TraduccionTaxa(); 
		tt.setCodificacio(codi);
		tt.setDescripcio("taxa de prova");
		tt.setFormaPagament("es gratis");
		t.setTraduccion("ca", tt);
		
		tt=new TraduccionTaxa(); 
		tt.setCodificacio("codi-001");
		tt.setDescripcio("test tax");
		tt.setFormaPagament("free");
		t.setTraduccion("en", tt);
		
		return t;
		
		
	}

	public  DocumentTramit creaDocument(String tit, Archivo a)  {
		DocumentTramit d=new DocumentTramit();
		TraduccionDocumento td=new TraduccionDocumento();
		td.setTitulo(tit);
		td.setArchivo(a);
		d.setTraduccion("ca", td);
		return d;
	}
	
	public Archivo creaArchivo(String name) throws IOException {
		Archivo archivo=new Archivo();
		
		InputStream is = ClassLoader.getSystemResourceAsStream(name);
		byte[] data=new byte[is.available()];
		is.read(data);
		is.close();
		
		archivo.setMime("text/plain");
	    archivo.setNombre(name);
	    archivo.setPeso(data.length);
	    archivo.setDatos(data);
		
		return archivo;
	}
	private void _(Object o){ System.out.println(o); }
}
