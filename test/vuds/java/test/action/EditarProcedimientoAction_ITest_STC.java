package test.action;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.action.RequestProcessor;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;

import servletunit.struts.MockStrutsTestCase;


/**
 * INFO: utiliza el sac-back-messages.properties por defecto (o sea "en");
 * @author u92770
 *
 */

public class EditarProcedimientoAction_ITest_STC extends MockStrutsTestCase {

	public EditarProcedimientoAction_ITest_STC(String s) {
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

    	TraDynaValidatorForm.idiomaDelegate = idiomaDelegate;


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
        request.addParameter("idSelect","394381");
        request.addParameter("action", action);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        /*
        <action path="/contenido/procedimiento/editar" 
      		input=".contenido.procedimiento.form" name="procedimientoForm"
       		parameter="action" 
       		scope="request"
       		type="org.ibit.rol.sac.back.action.contenido.procedimiento.EditarProcedimientoAction" validate="true">
       	<forward name="success" path="/contenido.procedimiento.form" redirect="false"/>
         */

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

    //OK 12/03/2010   12/05/2010
    public void _test02AfegirProcediment() throws DelegateException {
    	String path="/contenido/procedimiento/editar";
    	String action="Insertar";
    	MockHttpServletRequest request=createMockRequest(path);
        request.addParameter("action", action);
   
    	ompleParametresProcediment(request);
   
    	//parametres testejats.
        String taxa="on";
        String resultado="obtencion del certificado";
        String idUAResp="1";
        String nombreUAResp="Govern de les Illes Balears";
        String tramiteTel="codigo1";
        String version="1";
        String url="url1";
        
        request.addParameter("idUA", idUAResp);
        request.addParameter("nombreUA",nombreUAResp);        
        request.addParameter("taxa",taxa);
        request.addParameter("resultat",resultado);
        request.addParameter("tramite", tramiteTel);
        request.addParameter("version",version);        
        request.addParameter("url",url);
        
        
        
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");

        
        ProcedimientoForm dform=(ProcedimientoForm)request.getAttribute("procedimientoForm");
        assertNotNull(dform.get("idUA"));
        assertNotNull(dform.get("nombreUA"));
        
        assertEquals(1L,dform.get("idUA"));
        assertEquals(nombreUAResp,dform.get("nombreUA"));
        assertEquals(resultado,dform.get("resultat"));
        assertEquals(taxa,dform.get("taxa"));
        assertEquals(tramiteTel,dform.get("tramite"));
        assertEquals(version,dform.get("version").toString());
        assertEquals(url,dform.get("url"));
        
        
     }


    //OK 27/07/2010
    //-Des.caib.rolsac.idiomaDefault=ca
    public void test03SeleccionarProcediment() throws DelegateException {
    	String path="/contenido/procedimiento/editar";
    	String action="Seleccionar";
    	MockHttpServletRequest request=createMockRequest(path);
   
        //ompleParametresTramit(request);
        request.addParameter("idSelect","247");
        request.addParameter("action", action);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        /*
        <action path="/contenido/procedimiento/editar" 
      		input=".contenido.procedimiento.form" name="procedimientoForm"
       		parameter="action" 
       		scope="request"
       		type="org.ibit.rol.sac.back.action.contenido.procedimiento.EditarProcedimientoAction" validate="true">
       	<forward name="success" path="/contenido.procedimiento.form" redirect="false"/>
         */

        _(request.getAttribute("procedimientoForm"));
        
        List<Documento> docs=(List<Documento> )request.getAttribute("documentoOptions");
        _(docs);
        _(docs.size());
        

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
    
    private void ompleParametresProcediment(MockHttpServletRequest request) {
    	  request.addParameter("validacion","1");
          request.addParameter("textoFechaCaducidad","10/02/2010");	
          request.addParameter("textoFechaPublicacion","10/12/2009");
          request.addParameter("textoFechaActualizacion","11/12/2009");
          request.addParameter("textoFechaCaducidad","10/02/2010");	
          request.addParameter("idUA","1"); 
          //request.addParameter("nomOrganCompetent","Govern de les Illes Balears"); 
          request.addParameter("traducciones[0].nombre","test procediment");
          request.addParameter("traducciones[0].documentacion","portar dni");
          //request.addParameter("resultado", "obtencion del certificado");
          //request.addParameter("taxa", "on");
          request.addParameter("idUAResp", "1");
          request.addParameter("nombreUAResp","Govern de les Illes Balears");
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
