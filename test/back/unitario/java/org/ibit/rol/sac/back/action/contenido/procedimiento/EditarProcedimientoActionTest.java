package org.ibit.rol.sac.back.action.contenido.procedimiento;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;
import org.easymock.EasyMock;

import org.apache.struts.action.RequestProcessor;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.negocio.GrabadorProcedimiento;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;

import es.caib.test.common.LogSpy;

import servletunit.struts.MockStrutsTestCase;
import test.unitario.back.mock.MockProcedimientoDelegate;
import test.unitario.persistence.mock.MockMateriaDelegate;


/**
 * INFO: utiliza el sac-back-messages.properties por defecto (o sea "en");
 * @author u92770
 *
 */

public class EditarProcedimientoActionTest extends MockStrutsTestCase {

    LogSpy spy = new LogSpy();

	
	public EditarProcedimientoActionTest(String s) {
		super(s);
		}
	
	public void setUp() { try {
		super.setUp();
        setInitParameter("validating","false");
    	establecerMockIdiomaDelegate();
    	System.setProperty("es.caib.rolsac.idiomaDefault", "ca");

        spy = new LogSpy();
        SeleccionarProcedimientoAction.log = spy;
  

	} catch (Exception e) {
		e.printStackTrace();
	} }

	private void establecerMockIdiomaDelegate() throws DelegateException {
		IdiomaDelegate idiomaDelegate = EasyMock.createMock(IdiomaDelegate.class);
    	String[] langs={"ca","es","en","de"};
		EasyMock.expect(idiomaDelegate.listarLenguajes()).andReturn(Arrays.asList(langs)).times(6);
		EasyMock.replay(idiomaDelegate);
    	DocumentoForm.idiomaDelegate = idiomaDelegate;
    	
    	TraDynaValidatorForm.idiomaDelegate = idiomaDelegate;
	}

    public void tearDown() { try {
		super.tearDown();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }

   class ParametersBase {
	   String action;
   }

	class ParametresSeleccionarProcediment extends ParametersBase {
		String idSelect;
	}
    
    class AfegirProcedimentoParameters extends ParametersBase {
    	String taxa;
    	String resultat;
    	String idUAResp;
    	String nombreUA;
    	String nombreUAResp;
    	String tramite;
    	String version;
    	String url;
    	String validacion;
    	String textoFechaCaducidad;	
    	String textoFechaPublicacion;
    	String textoFechaActualizacion;
    	String idUA; 
    	String traducciones_0_nombre;
    	String traducciones_0_documentacion;

	}
    
    public class ParametresRelacionarMateria extends ParametersBase {
		public String procedimiento;
		public String materia;
		public String operacion;
	}


	//OK 12/03/2010   12/05/2010  1
    public void test01AfegirProcedimentoOK() throws DelegateException {

    	setConfigFile("/WEB-INF/struts-config.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/editar");
    	AfegirProcedimentoParameters params = creaParametrosAfegirProcediment(); 
    	rellenaParametrosEnRequestAfegirProcediment(params, request);
        
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificaParamsFormularioRespuesta(params, request);
        
        
     }

    
    
	public void test02AfegirProcedimentoFalla() throws DelegateException {
    	setConfigFile("/WEB-INF/struts-config-FAIL.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/editar");

    	AfegirProcedimentoParameters params = creaParametrosAfegirProcediment(); 
    	rellenaParametrosEnRequestAfegirProcediment(params, request);
        
        actionPerform();
        
        verifyForwardPath("/moduls/fail.jsp");
        
     }


	public void test03SeleccionarProcedimentSenseMaterias() {
		
		// el procedimiento que se recupera esta definido en MockProcedimientoDelegate.seleccionar();
		
    	setConfigFile("/WEB-INF/struts-config.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/seleccionar");
    	ParametresSeleccionarProcediment params = creaParametrosSeleccionarProcediment("3");
    	
    	rellenaParametrosEnRequestSeleccionarProcediment(params, request);
    	assertEquals("Seleccionar",request.getParameter("action"));
    	
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificarCamposProcedimientoSeleccionado(request);
		verificarRespuestaNoTieneMaterias(request);

	}





	
	public void test04AfegirProcedimentoContieneMateriaSinEspecificarPorDefecto() throws DelegateException {

          
    	setConfigFile("/WEB-INF/struts-config.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/editar");
    	AfegirProcedimentoParameters params = creaParametrosAfegirProcediment(); 
    	rellenaParametrosEnRequestAfegirProcediment(params, request);
        
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificaParamsFormularioRespuesta(params, request);
        verificarRespuestaNoTieneMaterias(request);

        assertTrue(spy.containsDebugMsg("procId 123 esta clasificado? false"));
        assertTrue(spy.containsDebugMsg("procId 123 tiene 0 materiaOptions"));
 
	}

	public void test05SeleccionarProcedimentAmbUnaMateria() {
		
		// el procedimiento que se recupera esta definido en MockProcedimientoDelegate.seleccionar();
		
		conectaProcedimiento(crearProcedimientoConUnaMateria(5L,"Salut"));

    	setConfigFile("/WEB-INF/struts-config.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/seleccionar");
    	ParametresSeleccionarProcediment params = creaParametrosSeleccionarProcediment("5");
    	
    	rellenaParametrosEnRequestSeleccionarProcediment(params, request);
    	assertEquals("Seleccionar",request.getParameter("action"));
    	
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificarCamposProcedimientoSeleccionado(request);
		verificarRespuestaTieneUnaMateria(request,"Salut");
		
        assertTrue(spy.containsDebugMsg("procId 5 esta clasificado? true"));

	}




	public void test06RelacionarMateriaEnProcedimentSenseClassificar() {

		conectaProcedimiento(crearProcedimientoConUnaMateria(6L,Materia.CE_SENSECLASSIFICAR));

	   	setConfigFile("/WEB-INF/struts-config.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/editar");
    	rellenaParametrosParaRequestRelacionarMateria(request,"alta","Agricultura" );
    	assertEquals("Lista de Materias Relacionadas",request.getParameter("action"));
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
		verificarRespuestaTieneUnaMateria(request,"Agricultura");
		
        assertTrue(spy.containsDebugMsg("procId 6 esta clasificado? true"));

	}

	public void test07EliminarMateriaEnProcedimentAmbUnaMateria() {

		conectaProcedimiento(crearProcedimientoConUnaMateria(7L,"Agricultura"));

	   	setConfigFile("/WEB-INF/struts-config.xml");
    	MockHttpServletRequest request=createMockRequestWithPath("/contenido/procedimiento/editar");
    	rellenaParametrosParaRequestRelacionarMateria(request,"baja", "Agricultura");
    	assertEquals("Lista de Materias Relacionadas",request.getParameter("action"));
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificarRespuestaNoTieneMaterias(request);
	}
	
	
	private void conectaProcedimiento(ProcedimientoLocal procedimiento) {
		MockProcedimientoDelegate.procedimiento = procedimiento;
		
	}

	private ProcedimientoLocal crearProcedimientoConUnaMateria(long procId, String ceMateria) {
		ProcedimientoLocal procedimiento = crearProcedimiento(procId);
		return relacionaMateriaConProcedimiento(ceMateria,procedimiento);
		
	}

	private ProcedimientoLocal relacionaMateriaConProcedimiento(String ceMateria, ProcedimientoLocal procedimiento) {
		Materia materia=new Materia();
		materia.setCodigoEstandar(ceMateria);
		materia.setId(obtenerIdMateria(ceMateria));
		Set<Materia> materias = new HashSet<Materia>();
		materias.add(materia);
		procedimiento.setMaterias(materias);
		return procedimiento;
	}
	
	private Long obtenerIdMateria(String ce)  {
		try {
			MockMateriaDelegate delegate;
			delegate = new MockMateriaDelegate();
			return delegate.obtenerMateriaCE(ce).getId();
			
		} catch (DelegateException e) {
			return -1L;
		}

	}
	
	private ProcedimientoLocal crearProcedimiento(Long id) {
		ProcedimientoLocal procedimiento=new ProcedimientoLocal(id);
		procedimiento.setResponsable("yo mismo");
		procedimiento.setTaxa("on");
		procedimiento.setTramite("codigo1");
		procedimiento.setVersion(1L);
		procedimiento.setUrl("url1");
	
		return procedimiento;
	}
	
	private void verificarRespuestaTieneUnaMateria(MockHttpServletRequest request, String ce) {
		List<Materia> materias =(List<Materia>)request.getAttribute("materiaOptions");
        assertEquals(1,materias.size());
        assertEquals(ce, materias.get(0).getCodigoEstandar());
		
	}

	private void rellenaParametrosParaRequestRelacionarMateria(MockHttpServletRequest request, String operacion, String ce) {
		  ParametresRelacionarMateria params = crearParametrosRelacionarMateria(operacion,ce);
		  request.addParameter("action",params.action);
    	  request.addParameter("procedimiento",params.procedimiento);
          request.addParameter("materia",params.materia);			
          request.addParameter("operacion",params.operacion);
	}

	private void verificarCamposProcedimientoSeleccionado(MockHttpServletRequest request) {
		ProcedimientoForm dform = (ProcedimientoForm )request.getAttribute("procedimientoForm");
		assertEquals("on", dform.get("taxa"));
		//assertEquals("yo mismo", dform.get("responsable"));  TODO falta camp responsable en struts-config.xml#formularioForm
		assertEquals("url1", dform.get("url"));
	}

	
	private ParametresRelacionarMateria crearParametrosRelacionarMateria(String operacion, String ce) {
		ParametresRelacionarMateria params=new ParametresRelacionarMateria();
		params.action="Lista de Materias Relacionadas";
		params.procedimiento=MockProcedimientoDelegate.procedimiento.getId().toString();
		params.materia=obtenerIdMateria(ce).toString();
		params.operacion=operacion;
		return params;
	}

	private void rellenaParametrosEnRequestSeleccionarProcediment(
			ParametresSeleccionarProcediment params,
			MockHttpServletRequest request) {
			request.addParameter("action", params.action);
			request.addParameter("idSelect",params.idSelect);
		
	}

	private ParametresSeleccionarProcediment creaParametrosSeleccionarProcediment(String id) {
		ParametresSeleccionarProcediment params=new ParametresSeleccionarProcediment();
    	params.action="Seleccionar";
		params.idSelect=id;
		return params;
	}

	private void verificarRespuestaNoTieneMaterias(MockHttpServletRequest request) {
		List<Materia> materias =(List<Materia>)request.getAttribute("materiaOptions");
        assertEquals(0,materias.size());
	}
	
    
	private void verificaParamsFormularioRespuesta(AfegirProcedimentoParameters params, MockHttpServletRequest request) {
		 
		ProcedimientoForm dform = obtenerFormularioDeRespuesta(request);
		
		assertNotNull(dform.get("idUA"));
        assertNotNull(dform.get("nombreUA"));
        
        assertEquals(1L,dform.get("idUA"));
        assertEquals(params.nombreUA,dform.get("nombreUA"));
        assertEquals(params.resultat,dform.get("resultat"));
        assertEquals(params.taxa,dform.get("taxa"));
        assertEquals(params.tramite,dform.get("tramite"));
        assertEquals(params.version,dform.get("version").toString());
        assertEquals(params.url,dform.get("url"));
	}
 
	private ProcedimientoForm obtenerFormularioDeRespuesta(MockHttpServletRequest request) {
		ProcedimientoForm dform=(ProcedimientoForm)request.getAttribute("procedimientoForm");
		return dform;
	}



    private AfegirProcedimentoParameters creaParametrosAfegirProcediment() {
    	AfegirProcedimentoParameters params = new AfegirProcedimentoParameters();
    	params.action="Insertar";
    	params.taxa="on";
    	params.resultat="obtencion del certificado";
    	params.idUAResp="1";
    	params.nombreUA="Govern de les Illes Balears";
    	params.nombreUAResp="Govern de les Illes Balears";
    	params.tramite="codigo1";
    	params.version="1";
    	params.url="url1";
    	params.validacion = "1";
    	params.textoFechaCaducidad="10/02/2010";	
    	params.textoFechaPublicacion="10/12/2009";
    	params.textoFechaActualizacion="11/12/2009";
    	params.idUA="1"; 
    	params.traducciones_0_nombre="test procediment";
    	params.traducciones_0_documentacion="portar dni";
    	return params;
	}


    
    private void rellenaParametrosEnRequestAfegirProcediment(AfegirProcedimentoParameters params, MockHttpServletRequest request) {
  	  	  request.addParameter("action",params.action);
    	  request.addParameter("validacion",params.validacion);
          request.addParameter("textoFechaCaducidad",params.textoFechaCaducidad);	
          request.addParameter("textoFechaPublicacion",params.textoFechaPublicacion);
          request.addParameter("textoFechaActualizacion",params.textoFechaActualizacion);
          request.addParameter("textoFechaCaducidad",params.textoFechaCaducidad);
          request.addParameter("idUA",params.idUA);
          request.addParameter("traducciones[0].nombre",params.traducciones_0_nombre);
          request.addParameter("traducciones[0].documentacion",params.traducciones_0_documentacion);
          request.addParameter("idUAResp", params.idUAResp);
          request.addParameter("nombreUAResp", params.nombreUAResp);
          request.addParameter("nombreUA", params.nombreUA);
          request.addParameter("resultat", params.resultat);
          request.addParameter("taxa", params.taxa);
          request.addParameter("tramite", params.tramite);
          request.addParameter("version", params.version);
          request.addParameter("url", params.url);
	}
    


	private MockHttpServletRequest createMockRequestWithPath(String path) {
    	 setRequestPathInfo(path);
         MockHttpServletRequest request=new MockHttpServletRequest();
         HttpServletRequestWrapper wrapper =new HttpServletRequestWrapper(request);
         wrapper.setAttribute(RequestProcessor.INCLUDE_SERVLET_PATH, path);
         setRequestWrapper(wrapper);
         return request;
	}

	private void _(Object o){System.out.println(o);}
	
}
