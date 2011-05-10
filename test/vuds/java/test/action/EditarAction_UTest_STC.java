package test.action;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.action.RequestProcessor;
import org.springframework.mock.web.MockHttpServletRequest;

import servletunit.struts.Common;
import servletunit.struts.MockStrutsTestCase;


public class EditarAction_UTest_STC extends MockStrutsTestCase {

	public EditarAction_UTest_STC(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public void setUp() { try {
		super.setUp();
        setInitParameter("validating","false");
        
        //_(Thread.currentThread().getContextClassLoader());

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
    public void testConsultarCodisVuds() {
    	String path="/contenido/tramite/formulario.jsp";
    	MockHttpServletRequest request=createMockRequest(path);
    	
    }
    
    public void testInsertarTramite() {
    	String path="/contenido/tramite/editar";
    	MockHttpServletRequest request=createMockRequest(path);
   
        ompleTramit(request);
        request.addParameter("action", "Insertar");
          
        actionPerform();
        
        assertNotNull(request.getAttribute("idSelect"));
        assertNotNull(request.getAttribute("idProcedimiento"));
        
        //verifyForward("success");
        verifyForwardPath("/layout.do");

     }

    private void ompleTramit(MockHttpServletRequest request) {
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
