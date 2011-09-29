package test.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.RequestProcessor;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.micromodel.Traduccion;
import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.sun.mail.iap.Response;

import es.map.vuds.si.service.webservice.TramiteVuds;


import servletunit.struts.Common;
import servletunit.struts.MockStrutsTestCase;


/**
 * 
 * 1) Per veure els paquets SOAP, descomentar en log4j.properties:
 * 
 * #log4j.logger.httpclient.wire=DEBUG,LOGFILE
 * 
 * 
 * 2) Per veure detalls en el stub, descomentar els system.out.println de:
 * 
 * public class GestorWebserviceBeanServiceStub extends org.apache.axis2.client.Stub
 *
 * 
 */

public class BuscarCodisVudsAction_ITest_STC extends MockStrutsTestCase {

	public BuscarCodisVudsAction_ITest_STC(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public void setUp() { try {
		super.setUp();
        setInitParameter("validating","false");
        //_(Thread.currentThread().getContextClassLoader());
        
    	IdiomaDelegate idiomaDelegate = EasyMock.createMock(IdiomaDelegate.class);
    	String[] langs={"ca","es","en","de"};
		EasyMock.expect(idiomaDelegate.listarLenguajes()).andReturn(Arrays.asList(langs)).times(6);
		EasyMock.replay(idiomaDelegate);
    	DocumentoForm.idiomaDelegate = idiomaDelegate;


	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }

    public void tearDown() { try {
		super.tearDown();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }

    /**
     *  Aquest test necessita tenir el servidor vuds web proves EN MARXA
     *  C:\j2ee-developer\webservices\axis2-1.5\bin\axis2-1.5\bin\axis2server.bat
     */
    //OK 02Sep10, 26gen2010 
    public void _test01ConsultarCodisVuds_SenseFiltre() {
    	String path="/contenido/tramite/vuds/buscarCodis";
    	MockHttpServletRequest request=createMockRequest(path);
    	
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        List<TramiteVuds> codis =(List<TramiteVuds>)request.getAttribute("codis");
        
        assertNotSame(0,codis.size());
        for(TramiteVuds t:codis) {_(t.getIdTramiteVuds()+" "+t.getDescripcionTramiteVuds());}
        
    }
    
    /**
     *  Aquest test necessita tenir el servidor vuds web proves EN MARXA
     *  C:\j2ee-developer\webservices\axis2-1.5\bin\axis2-1.5\bin\axis2server.bat
     */
    //OK 02sep10, 8feb2010  
    public void test01ConsultarCodisVudsFiltre_OK() {
    	String path="/contenido/tramite/vuds/buscarCodis";
    	
    	//1. No filtrar
    	MockHttpServletRequest request=createMockRequest(path);
    	//request.addParameter("filtre", "C0001");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        List<TramiteVuds> codis =(List<TramiteVuds>)request.getAttribute("codis");
        assertNotSame(0,codis.size());
        for(TramiteVuds t:codis) {_(t.getIdTramiteVuds()+" "+t.getDescripcionTramiteVuds());}
        
    	//2. Filtrar per codi vàlid
    	request=createMockRequest(path);
    	request.addParameter("filtre", "C0001");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<TramiteVuds>)request.getAttribute("codis");
        assertNotSame(0,codis.size());
        for(TramiteVuds t:codis) {_(t.getIdTramiteVuds()+" "+t.getDescripcionTramiteVuds());}

    	//3. Filtrar per codi invàlid
    	request=createMockRequest(path);
    	request.addParameter("filtre", "M0001");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<TramiteVuds>)request.getAttribute("codis");
        assertSame(0,codis.size());

        
        //4. Filtrar per un desc que retorna  1 codi  
    	request=createMockRequest(path);
    	request.addParameter("filtre", "desc tramit 0001");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<TramiteVuds>)request.getAttribute("codis");
        _(codis.size());
        assertSame(1,codis.size());
        

    }
    
    
    
    /**
     *  Aquest test necessita tenir el servidor vuds web proves APAGAT
     */
    public void _test02ConsultarCodisVuds_FiltreZeroTrobats() {
    	String path="/contenido/tramite/vuds/buscarCodis";
    	MockHttpServletRequest request=createMockRequest(path);

    	request.addParameter("filtre", "M0001");

        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
    	
        List<TramiteVuds> codis =(List<TramiteVuds>)request.getAttribute("codis");
        
        assertEquals(0,codis.size());
        
        //assertEquals("1111", codis.get(0).getIdTramiteVuds());
        
    }
    
    //OK 8feb2010
    public void _test03ConsultarCodisVudsCaseInsensitive() {
    	String path="/contenido/tramite/vuds/buscarCodis";
    	
    	MockHttpServletRequest request=createMockRequest(path);
    	//2. Filtrar per texte minúscules
    	request=createMockRequest(path);
    	request.addParameter("filtre", "permiso");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
        List<TramiteVuds> codis =(List<TramiteVuds>)request.getAttribute("codis");
        codis =(List<TramiteVuds>)request.getAttribute("codis");
        int ncodis=codis.size();
        assertNotSame(0,ncodis);
        for(TramiteVuds t:codis) {_(t.getIdTramiteVuds()+" "+t.getDescripcionTramiteVuds());}

    	//2. Filtrar per texte majúscules 
    	request=createMockRequest(path);
    	request.addParameter("filtre", "PERMISO");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<TramiteVuds>)request.getAttribute("codis");
        assertSame(ncodis,codis.size());

    }

    //OK 8feb2010
    public void _test04ConsultarCodisVudsGuardatsEnSessio() {
    	String path="/contenido/tramite/vuds/buscarCodis";
    	
    	//peticio sense codis en sessio
    	MockHttpServletRequest request=createMockRequest(path);
    	request=createMockRequest(path);
    	request.addParameter("filtre", "permiso");
    	
    	long t1=System.currentTimeMillis();
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        _("msecs = "+(System.currentTimeMillis()-t1));
        
        HttpSession session = request.getSession();
        List<TramiteVuds> codis =(List<TramiteVuds>)session.getAttribute("codis");
        assertNotNull(codis);
    	

    	//peticio amb codis en sessio
    	request.setParameter("filtre", "permiso");
    	t1=System.currentTimeMillis();
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        _("msecs = "+(System.currentTimeMillis()-t1));
        
    }
    
    
    
    public void _testSeleccionarTramit() throws DelegateException {
    	String path="/contenido/tramite/seleccionar";
    	String action="Seleccionar";
    	MockHttpServletRequest request=createMockRequest(path);
   
        //ompleParametresTramit(request);
        request.addParameter("idSelect","394381");
        request.addParameter("action", action);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");

        _(request.getAttribute("idProcedimiento"));
        
        Set<DocumentTramit> docs=(Set<DocumentTramit> )request.getAttribute("formularioOptions");
        _(docs);
        _(docs.size());
        docs=(Set<DocumentTramit> )request.getAttribute("docInformatiuOptions");
        _(docs);
        _(docs.size());

        docs=(Set<DocumentTramit> )request.getAttribute("docPresentarOptions");
        _(docs);
        _(docs.size());
        
        TramiteForm form=(TramiteForm) request.getAttribute("tramiteForm");
        Long id=(Long)form.getMap().get("id");
        assertEquals(394381L,id.longValue());
        

/*        
        verifyForward("success");
        DocumentoForm dform=(DocumentoForm) request.getAttribute("documentoForm");
        List<Traduccion> t=(List<Traduccion>)dform.getMap().get("traducciones");
        TraduccionDocumento td=(TraduccionDocumento)t.get(0);
        assertEquals(titulo,td.getTitulo());
        assertEquals(Document.DOCINFORMATIU,request.getAttribute("tipus"));
*/
     }

    
    //area privada
    
    private void ompleParametresTramit(MockHttpServletRequest request) {
    	  request.addParameter("idProcedimiento","246");
          request.addParameter("textoFechaCaducidad","10/02/2010");	
          request.addParameter("textoFechaPublicacion","10/12/2009");
          request.addParameter("textoFechaActualizacion","11/12/2009");
          request.addParameter("textoFechaCaducidad","10/02/2010");	
          request.addParameter("fase", "Instrucción");
          request.addParameter("idOrganCompetent","1"); //1 = "Govern de les Illes Balears");
          request.addParameter("nomOrganCompetent","Govern de les Illes Balears"); 
          request.addParameter("descTaxa","descripcion taxa"); 
          request.addParameter("formaPagamentTaxa","en efectiu");
          request.addParameter("codiTaxa","123456");
          request.addParameter("versio","1");
          request.addParameter("urlExterna","http://urlExterna");
          request.addParameter("traducciones[0].nombre","test tramite");
          request.addParameter("traducciones[0].documentacion","portar dni");
          request.addParameter("traducciones[0].plazos","3 meses");
	}
    
    private void ompleParametresDocument(MockHttpServletRequest request) {
        request.addParameter("traducciones[0].titulo","nombre doc");
        request.addParameter("traducciones[0].descripcion","descripcion doc ");
	}


	private MockHttpServletRequest createMockRequest(String path) {
    	 setRequestPathInfo(path);
         MockHttpServletRequest request=new MockHttpServletRequest();
         HttpServletRequestWrapper wrapper =new HttpServletRequestWrapper(request);
         wrapper.setAttribute(RequestProcessor.INCLUDE_SERVLET_PATH, path);
         setRequestWrapper(wrapper);
         return request;
	}

	private void _(Object o){System.out.println(o);}
	
}
