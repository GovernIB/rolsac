package test.action;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.action.RequestProcessor;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TaxaForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;

import es.caib.persistence.vuds.GestorWebserviceBeanServiceStub.Formulario;
import es.caib.persistence.vuds.GestorWebserviceBeanServiceStub.Normativa;

import servletunit.struts.MockStrutsTestCase;
import test.mock.MockUnidadAdministrativaDelegate;


/**
 * INFO: utiliza el sac-back-messages.properties por defecto (o sea "en");
 * @author u92770
 *
 */

public class PopArbolAction_ITest_STC extends MockStrutsTestCase {

	public PopArbolAction_ITest_STC(String s) {
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
     * test: usuari RSANZ a de veure en org.resolver q l'arrel es conselleria turisme 
     * @throws DelegateException
     */
    public void _test01RaizArbolSinPadre() throws DelegateException {
    	String path="/organigrama/unidad/poparbol.do";
    	MockHttpServletRequest request=createMockRequest(path);
   
    	MockUnidadAdministrativaDelegate.userID="rsanz";
   	
        request.addParameter("idUA","0");
        request.addParameter("action", "");
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
        List<UnidadAdministrativa> raices = (List<UnidadAdministrativa>)request.getAttribute("raizOptions");
        
        _(raices);
        assertEquals(1,raices.size());
        
        assertEquals(10L, (long)raices.get(0).getId());
        
     }

    /**
     * test: usuari RSANZ a de veure en organism .responsable q l'arrel es govern illes balears 
     * @throws DelegateException
     */
    public void _test02RaizArbolConPadre() throws DelegateException {
    	String path="/organigrama/unidad/poparbol.do";
    	MockHttpServletRequest request=createMockRequest(path);
   
    	MockUnidadAdministrativaDelegate.userID="rsanz";
   	
        request.addParameter("idUA","0");
        request.addParameter("padres", "");
        request.addParameter("action", "");
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
        List<UnidadAdministrativa> raices = (List<UnidadAdministrativa>)request.getAttribute("raizOptions");
        
        _(raices);
        assertEquals(1,raices.size());
        
        assertEquals(1L, (long)raices.get(0).getId());
        
     }

    
    //
    public void test03ExpandirArbolConPadre() throws DelegateException {
    	String path="/organigrama/unidad/poparbol.do";
    	MockHttpServletRequest request=createMockRequest(path);
   
    	MockUnidadAdministrativaDelegate.userID="rsanz";

    	
        request.addParameter("idUA","0");
        request.addParameter("padres","");
        request.addParameter("action","Expandir");
        request.addParameter("idSelect","1");
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");

        List<UnidadAdministrativa> raices = (List<UnidadAdministrativa>)request.getAttribute("tieneHijos");
        
        _(raices);

        
     }

    
    //----------------------------- area privada
    
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
          request.addParameter("traducciones[0].plazos","3 meses");
          request.addParameter("codiVuds","C0002");

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
