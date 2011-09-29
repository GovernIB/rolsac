package test.action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.RequestProcessor;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.ws.Actualizador;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.vuds.si.service.webservice.Formulario;
import es.map.vuds.si.service.webservice.TramiteVuds;

import servletunit.struts.MockStrutsTestCase;
import test.mock.MockUnidadAdministrativaDelegate;


/**
 * INFO: utiliza el sac-back-messages.properties por defecto (o sea "en");
 * @author u92770
 *
 */

public class EditarTramiteAction_ITest_STC extends MockStrutsTestCase {

	IdiomaDelegate idiomaDelegate;
	
	public EditarTramiteAction_ITest_STC(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public void setUp() { try {
		super.setUp();
        setInitParameter("validating","false");
        //_(Thread.currentThread().getContextClassLoader());
        
    	idiomaDelegate = EasyMock.createMock(IdiomaDelegate.class);
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

    
    public void _test01SeleccionarTramit() throws DelegateException {
    	String path="/contenido/tramite/seleccionar";
    	String action="Seleccionar";
    	MockHttpServletRequest request=createMockRequest(path);
   
    	
        //ompleParametresTramit(request);
    	String tid="394572";		//procID = 246
        request.addParameter("idSelect",tid);
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
        
        Set<Taxa> taxes =(Set<Taxa> )request.getAttribute("taxesOptions");
        _(taxes);
        _(taxes.size());

        
        TramiteForm form=(TramiteForm) request.getAttribute("tramiteForm");
        Long id=(Long)form.getMap().get("id");
        assertEquals(Long.parseLong(tid),id.longValue());
        

/*        
        verifyForward("success");
        DocumentoForm dform=(DocumentoForm) request.getAttribute("documentoForm");
        List<Traduccion> t=(List<Traduccion>)dform.getMap().get("traducciones");
        TraduccionDocumento td=(TraduccionDocumento)t.get(0);
        assertEquals(titulo,td.getTitulo());
        assertEquals(Document.DOCINFORMATIU,request.getAttribute("tipus"));
*/
     }

    // OK 12/05/2010
    public void _testI16AfegirTramit() throws DelegateException {
    	
    	//ponemos a mano la referencia a idiomaDelegate porque el Form no es una action. 
    	TraDynaValidatorForm.idiomaDelegate = idiomaDelegate;
    	
    	String path="/contenido/tramite/editar";
    	String action="Insertar";
    	MockHttpServletRequest request=createMockRequest(path);

        request.addParameter("action", action);

        String validacio="1";
        String requisits="tenir mes de 18 anys";
        String documentacion="doc1";

        ompleParametresTramit(request);
        
        request.addParameter("validacio",validacio);
        request.addParameter("traducciones[0].requisits",requisits);
        request.addParameter("traducciones[0].documentacion",documentacion);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");

        TramiteForm df = (TramiteForm)request.getAttribute("tramiteForm");
        assertEquals(Integer.parseInt(validacio),df.get("validacio"));
        
        List<TraduccionTramite> t=(List<TraduccionTramite>)df.getMap().get("traducciones");
        TraduccionTramite tt=(TraduccionTramite)t.get(0);
        assertEquals(requisits,tt.getRequisits());
        assertEquals(documentacion,tt.getDocumentacion());
        
        assertEquals("codigo1",df.get("idTraTel"));
        assertEquals("1",df.get("versio").toString());
        assertEquals("http://urlExterna",df.get("urlExterna"));
        

        try {
			Actualizador.getActualizador().join();  //esperem a que termini el thread Actualitzador
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
     }

    /**
     * aquest test agafa per defecte el organ competent x resoldre del procediment.
     * @throws DelegateException
     */
    public void _test03AfegirTramitAmbValorsPerDefecte() throws DelegateException {
    	String procId="246";
    	//1. llegir organ competent x resoldre del proc
    	String path="/contenido/procedimiento/seleccionar";
    	String action="Seleccionar";
    	MockHttpServletRequest request=createMockRequest(path);
    	request.addParameter("action", action);
        request.addParameter("idSelect",procId);
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
        ProcedimientoForm pform=(ProcedimientoForm)request.getAttribute("procedimientoForm");
        
        assertNotNull(pform.get("idUA"));
        assertNotNull("nombreUA");
        
        Long idUA = (Long)pform.get("idUA");
        String nombreUA = (String)pform.get("nombreUA");
        
        _(nombreUA);
        
    	//2. afegir tramit
    	path="/contenido/tramite/editar";
    	action="Crear";
    	request=createMockRequest(path);

        request.addParameter("action", action);
        request.addParameter("idProcedimiento", procId);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");

        assertEquals("on",request.getAttribute("taxa"));
        assertSame(idUA,(Long)request.getAttribute("idUA"));
        assertEquals(nombreUA,request.getAttribute("nombreUA"));
     }

    
    //TODO
    public void _test04ReordenarFormularisProcediment() throws DelegateException {
    	String path="/contenido/procedimiento/editar";
    	MockHttpServletRequest request=createMockRequest(path);
    	String action="Lista de Documentos Relacionados";
    	request.addParameter("action", action);
    	actionPerform();
    	verifyNoActionErrors();
    	verifyForward("success");
    }


    /** 
     * dao tests: testReordenarFormularisTramit(), testReordenarDocsPresentar()
     *  TODO 18/01/2010 
     */
    
    public void _test04ReordenarFormularisTramit() throws DelegateException {
    	String path="/contenido/tramite/editar";
    	MockHttpServletRequest request=createMockRequest(path);
    	request.addParameter("action", "Documentos tramite");
    	request.addParameter("operacion", "actualizar_orden");
    	request.addParameter("idTramite", "394808");
    	ompleParametresTramit(request);

    	//TODO omplir parametres amb els formularis.
    	
    	actionPerform();
    	
    	
    	verifyNoActionErrors();
    	verifyForward("success");
    }
    
    //OK 4feb
    public void _test05previsualitzarTramitVuds() {
    	String path="/contenido/tramite/vuds/previsualitzar";
    	MockHttpServletRequest request=createMockRequest(path);
    	request.addParameter("tid", "394596");
    	actionPerform();
    	verifyForward("success");
    	
    	
    	_(request.getAttribute("areaTramitadora"));
        _(request.getAttribute("canalTramitacion"));
        _(request.getAttribute("codigoIdentificador"));
        _(request.getAttribute("denominacionTramite"));
        _(request.getAttribute("descripcionTramite"));
        _(request.getAttribute("formaIniciacion"));
        _(request.getAttribute("observaciones"));
        _(request.getAttribute("plazosLegales"));
        _(request.getAttribute("tasa"));
        _(request.getAttribute("tiempoResolucion"));
        _(request.getAttribute("tipoRegistro"));
        _(request.getAttribute("tipologia"));
        _(request.getAttribute("idTraTel"));
        _(request.getAttribute("versio"));
        _(request.getAttribute("urlExterna"));
        
        TramiteVuds codivuds= (TramiteVuds)request.getAttribute("tramiteVuds");
        _(codivuds.getIdTramiteVuds());
        _(codivuds.getDescripcionTramiteVuds());
        _(request.getAttribute("enlaceConsulta"));
        _(request.getAttribute("resultado"));
        Formulario[] forms= (Formulario[])request.getAttribute("formularios");
        for(Formulario f:forms) _(f.getUrlDescarga()+" "+f.getDescripcionFormulario()+" "+f.getIdCodificacion());
        _(java.util.Arrays.toString((String[])request.getAttribute("requisitos")));
        _(java.util.Arrays.toString((String[])request.getAttribute("documentos")));
        _(request.getAttribute("normativa"));
        

    }

    public void testH29TramitNou_ValidarFalla() {
        setInitParameter("validating","true");
        TraDynaValidatorForm.idiomaDelegate = idiomaDelegate;
    	
    	String path="/contenido/tramite/editar";
    	String action="Insertar";
    	MockHttpServletRequest request=createMockRequest(path);

        request.addParameter("action", action);

        //String validacio="1";

        ompleParametresTramit(request);
        
        //request.addParameter("validacio",validacio);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        assertValidacioFormOK(request);
     
    	_(request.getAttribute("tramiteForm"));

    }
    
    //area privada
    
    private void ompleParametresTramit(MockHttpServletRequest request) {
    	  request.addParameter("idProcedimiento","247");
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
          request.addParameter("idTraTel","codigo1");
          request.addParameter("versio","1");
          request.addParameter("urlExterna","http://urlExterna");
          //request.addParameter("traducciones[0].nombre","test tramit");
          request.addParameter("traducciones[0].plazos","3 mesos");
          request.addParameter("codiVuds","C0002");

	}
    
    private void ompleParametresDocument(MockHttpServletRequest request) {
        request.addParameter("traducciones[0].titulo","nombre doc");
        request.addParameter("traducciones[0].descripcion","descripcion doc ");
	}


    private void assertValidacioFormOK(MockHttpServletRequest request) {
		ActionErrors errors= (ActionErrors)request.getAttribute("org.apache.struts.action.ERROR");
		if(null!=errors) {
			Iterator<ActionError> it=errors.get();
			while(it.hasNext())  _(Arrays.toString(it.next().getValues()));
			fail();
		}
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
