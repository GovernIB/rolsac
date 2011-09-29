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

import org.apache.struts.action.RequestProcessor;
import org.apache.struts.upload.FormFile;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.micromodel.Traduccion;
import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.sun.mail.iap.Response;

import servletunit.struts.Common;
import servletunit.struts.MockStrutsTestCase;


public class EditarDocumentTramitAction_ITest_STC extends MockStrutsTestCase {

	public EditarDocumentTramitAction_ITest_STC(String s) {
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
     *  Necessita tenir el servidor vuds web proves funcionant
     */
    public void _testConsultarCodisVuds() {
    	String path="/contenido/tramite/formulario.jsp";
    	MockHttpServletRequest request=createMockRequest(path);
    	
    }

    //OK 22.12.09
    public void _testVerificarFormulariDocumentInformatiu() throws DelegateException {
        setInitParameter("validating","true");
    	String idTramite="394564";
    	//pas 1. afegir document
    	String path="/contenido/tramite/document/editar";
    	String action="Insertar";
    	MockHttpServletRequest request=createMockRequest(path);
   
        //ompleParametresTramit(request);
        request.addParameter("idTramite",idTramite);
        request.addParameter("action", action);
        request.addParameter("tipus", new Integer(Document.DOCINFORMATIU).toString());
        String titulo="titulo doc";
        //request.addParameter("traducciones[0].titulo",titulo);
        
        actionPerform();
        verifyNoActionErrors();
        
        Long id=(Long)request.getAttribute("idSelect");
        assertNotNull(id);
        action=(String)request.getAttribute("action");
        assertEquals("Seleccionar",action);
        verifyForward("tramite");
    }

    
    //OK 22.12.09   11/05/2010
    public void _testAfegirDocumentInformatiu() throws DelegateException {

    	//pas 1. afegir document
    	String path="/contenido/tramite/document/editar";
    	MockHttpServletRequest request=createMockRequest(path);

    	String idTramite="394564";
    	String action="Insertar";
    	
        //ompleParametresTramit(request);
        request.addParameter("idTramite",idTramite);
        request.addParameter("action", action);
        request.addParameter("tipus", new Integer(Document.DOCINFORMATIU).toString());
        String titulo="titulo doc";
        request.addParameter("traducciones[0].titulo",titulo);
        //FormFile fitxer = new Form
        
        actionPerform();
        
        verifyNoActionErrors();
        
        verifyForward("tramite");

// No funciona el pas 2, pq editar no posa en el request el idDoc (es redirigeix al tramit) 
//        //pas 2. seleccionar el  document creat
//        request=createMockRequest(path);
//        Long idDoc=(Long)request.getAttribute(?);
//        assertNotNull(idDoc);
//        request.addParameter("idSelect",Long.toString(idDoc));
//        action=(String)request.getAttribute("action");
//        assertEquals("Seleccionar",action);
//        verifyForward("tramite");
//        actionPerform();
//        verifyForward("success");

//	documentoForm?  editar redirige al tramiteForm ...
//        DocumentoForm dform=(DocumentoForm) request.getAttribute("documentoForm");
//        List<Traduccion> t=(List<Traduccion>)dform.getMap().get("traducciones");
//        TraduccionDocumento td=(TraduccionDocumento)t.get(0);
//        assertEquals(titulo,td.getTitulo());
//        assertEquals(Integer.toString(Document.DOCINFORMATIU),request.getParameter("tipus"));
//        assertEquals(idTramite,request.getParameter("idTramite"));


    	
    	Long tid=(Long)request.getAttribute("idSelect");
    	_(tid);

        //pas 2. verificar q document s'ha afegit al tramit
    	path="/contenido/tramite/seleccionar";
    	request=createMockRequest(path);

    	action="Seleccionar";
    	
        request.addParameter("idSelect",tid.toString());
        request.addParameter("action", action);
        
        
    	actionPerform();
    	
        Set<DocumentTramit> docs= (Set<DocumentTramit> )request.getAttribute("docInformatiuOptions");
    
        boolean found=false;
        for(DocumentTramit d : docs) {
        	TraduccionDocumento traduccion = (TraduccionDocumento)d.getTraduccion("ca"); 
        	if(traduccion.getTitulo().equals(titulo)){found=true;break;}
        }
        assertTrue(found);
    }

    
    //OK 22.12.09
    public void testModificarDocInformatiu() throws DelegateException {
    	
    	//pas 1. afegir document
    	String path="/contenido/tramite/document/editar";
    	MockHttpServletRequest request=createMockRequest(path);

    	String idTramite="394564";
    	String idDoc="398100";
        String titulo="titulo doc";
    	String action="Modificar";
    	
        //ompleParametresTramit(request);
        request.addParameter("idTramite",idTramite);
        request.addParameter("action", action);
        request.addParameter("id",idDoc);
        request.addParameter("tipus", new Integer(Document.DOCINFORMATIU).toString());
        request.addParameter("traducciones[0].titulo",titulo);
        //FormFile fitxer = new FormFile()
        
        actionPerform();
        
        assertNull(request.getAttribute("org.apache.struts.action.EXCEPTION"));
        verifyNoActionErrors();  //verifica org.apache.struts.action.ERROR

     	
    	Long tid=(Long)request.getAttribute("idSelect");
    	_(tid);

        //pas 2. verificar q document s'ha afegit al tramit
    	path="/contenido/tramite/seleccionar";
    	request=createMockRequest(path);

    	action="Seleccionar";
    	
        request.addParameter("idSelect",tid.toString());
        request.addParameter("action", action);
        
        
    	actionPerform();
    	
        Set<DocumentTramit> docs= (Set<DocumentTramit> )request.getAttribute("docInformatiuOptions");
    
        _("docs informatius size="+docs.size());
        boolean found=false;
        for(DocumentTramit d : docs) {
        	TraduccionDocumento traduccion = (TraduccionDocumento)d.getTraduccion("ca");
        	if(d.getId().toString().equals(idDoc)&&traduccion.getTitulo().equals(titulo)) {found=true;break;}
        }
        assertTrue(found);
        
     }

    
    public void _testSeleccionarFormulari() {
        String path="/contenido/tramite/document/seleccionar";
    	String action="Seleccionar";
    	MockHttpServletRequest request=createMockRequest(path);
    	
        request.addParameter("idSelect","394863");// "394882");
        request.addParameter("action", action);
        request.addParameter("tipus", new Integer(Document.FORMULARI).toString());
        actionPerform();
        
        verifyForward("success");
        DocumentoForm dform=(DocumentoForm) request.getAttribute("documentoForm");
        List<Traduccion> t=(List<Traduccion>)dform.getMap().get("traducciones");
        TraduccionDocumento td=(TraduccionDocumento)t.get(0);
        assertNotNull(td.getTitulo());
        _(td.getTitulo());
        _("tipus="+request.getParameter("tipus"));
        assertEquals(Document.FORMULARI,request.getParameter("tipus"));

        
    }
    
    
    
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
