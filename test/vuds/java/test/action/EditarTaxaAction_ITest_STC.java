package test.action;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.action.RequestProcessor;
import org.easymock.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TaxaForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;

import es.caib.persistence.vuds.GestorWebserviceBeanServiceStub.Formulario;
import es.caib.persistence.vuds.GestorWebserviceBeanServiceStub.Normativa;

import servletunit.struts.MockStrutsTestCase;


/**
 * INFO: utiliza el sac-back-messages.properties por defecto (o sea "en");
 * @author u92770
 *
 */

public class EditarTaxaAction_ITest_STC extends MockStrutsTestCase {

	public EditarTaxaAction_ITest_STC(String s) {
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

    
    //OK 10feb2010
    public void _test01SeleccionarTaxa() throws DelegateException {
    	String path="/contenido/taxa/seleccionar";
    	String action="Seleccionar";
    	MockHttpServletRequest request=createMockRequest(path);
   
        //ompleParametresTramit(request);
    	String taxId="395816";		//procID = 246
        request.addParameter("idSelect",taxId);
        request.addParameter("action", action);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");

        
        TaxaForm form=(TaxaForm) request.getAttribute("taxaForm");
        List<TraduccionTaxa> tt=(List<TraduccionTaxa>)form.get("traducciones");
        assertEquals("taxa de prova",tt.get(0).getDescripcio()); 
        
     }


    //OK 10feb2010
    public void test02InsertarTaxa() throws DelegateException {
    	
    	//1. escriure taxa
    	String path="/contenido/taxa/editar";
    	MockHttpServletRequest request=createMockRequest(path);
    	String action="Insertar";
    	request.addParameter("action", action);
    	
    	String validacio="1";
    	String descr="taxa de prova desde action test";
    	String codi="COD002";
    	String fpag="visa";
    	String engDesc="english desc";
    	
        request.addParameter("validacio",validacio);
        request.addParameter("traducciones[0].descripcio",descr);
        request.addParameter("traducciones[0].codificacio",codi);
        request.addParameter("traducciones[0].formaPagament",fpag);
        
        request.addParameter("traducciones[1].descripcio",engDesc);
        request.addParameter("traducciones[1].codificacio",codi);
        request.addParameter("traducciones[1].formaPagament",fpag);

        
    	String tramId="394519";		//procID = 246
        request.addParameter("idTramite",tramId);
        
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("tramite");

        TaxaForm dform= (TaxaForm)request.getAttribute("taxaForm");
        assertNotNull(dform.get("id"));
        
        String taxId=Long.toString((Long)dform.get("id"));
        
        _(taxId);

        // 2. llegir taxa
    	path="/contenido/taxa/seleccionar";
    	action="Seleccionar";
    	request=createMockRequest(path);

        request.addParameter("idSelect",taxId);
        request.addParameter("action", action);
        
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");

        
        TaxaForm form=(TaxaForm) request.getAttribute("taxaForm");
        List<TraduccionTaxa> tt=(List<TraduccionTaxa>)form.get("traducciones");
        assertEquals(descr, tt.get(0).getDescripcio()); 
        assertEquals(engDesc, tt.get(1).getDescripcio());

     }


    
    
    //----------------------------- area privada
    
    private void ompleParametresTramit(MockHttpServletRequest request) {
    	  request.addParameter("idProcedimiento","246");
          request.addParameter("textoFechaCaducidad","10/02/2010");	
          request.addParameter("textoFechaPublicacion","10/12/2009");
          request.addParameter("textoFechaActualizacion","11/12/2009");
          request.addParameter("textoFechaCaducidad","10/02/2010");	
          request.addParameter("fase", "Instrucci�n");
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
