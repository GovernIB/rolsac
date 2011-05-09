package org.ibit.rol.sac.back.action.contenido.ficha;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;

import junit.framework.AssertionFailedError;

import org.apache.struts.action.RequestProcessor;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.FichaForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.negocio.GrabadorProcedimiento;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.mock.web.MockHttpServletRequest;

import es.caib.test.common.LogSpy;

import servletunit.struts.MockStrutsTestCase;
import test.unitario.back.mock.MockFichaDelegate;
import test.unitario.back.mock.MockMateriaDelegate;
import test.unitario.back.mock.MockProcedimientoDelegate;


/**
 * INFO: utiliza el sac-back-messages.properties por defecto (o sea "en");
 * @author u92770
 *
 */

/*
<form-bean name="fichaForm"
    type="org.ibit.rol.sac.back.form.FichaForm"
    className="org.ibit.rol.sac.back.config.TraFormBeanConfig">
    <set-property property="traduccionClassName" value="org.ibit.rol.sac.model.TraduccionFicha"/>
    <form-property name="id" type="java.lang.Long" initial=""/>
    <form-property name="textoFechaPublicacion" type="java.lang.String"/>    
    <form-property name="textoFechaCaducidad" type="java.lang.String"/>
    <form-property name="textoFechaActualizacion" type="java.lang.String"/>    
    <form-property name="urlVideo" type="java.lang.String"/>        
    <form-property name="responsable" type="java.lang.String"/>   
	<form-property name="urlForo" type="java.lang.String"/>
    <form-property name="info" type="java.lang.String" initial=""/>
	<form-property name="foro_tema" type="java.lang.String"/>
    <form-property name="validacion" type="java.lang.Integer" initial=""/>
    <form-property name="fileicono" type="org.apache.struts.upload.FormFile"/>
    <form-property name="borraricono" type="boolean" initial="false" />
    <form-property name="nombreicono" type="java.lang.String" initial=""/>
    <form-property name="filebanner" type="org.apache.struts.upload.FormFile"/>
    <form-property name="borrarbanner" type="boolean" initial="false" />
    <form-property name="nombrebanner" type="java.lang.String" initial=""/>
    <form-property name="fileimagen" type="org.apache.struts.upload.FormFile"/>
    <form-property name="borrarimagen" type="boolean" initial="false" />
    <form-property name="nombreimagen" type="java.lang.String" initial=""/>
    <form-property name="traducciones" type="java.util.ArrayList"/>
</form-bean>
*/


public class EditarFichaActionTest extends MockStrutsTestCase {

    LogSpy spy = new LogSpy();
	private MockHttpServletRequest request;

	
	public EditarFichaActionTest(String s) {
		super(s);
		}
	
	public void setUp() { try {
		super.setUp();
        setInitParameter("validating","false");
    	establecerMockIdiomaDelegate();
    	System.setProperty("es.caib.rolsac.idiomaDefault", "ca");

        spy = new LogSpy();
        EditarFichaAction.log = spy;
  

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

	class ParametresSeleccionar extends ParametersBase {
		String idSelect;
	}
    
    class AfegirActionParameters extends ParametersBase {
    	String validacion;
    	String traducciones_0_titulo;
    	String traducciones_0_url;

	}
    
    public class ParametresRelacionarMateria extends ParametersBase {
		public String ficha;
		public String materia;
		public String operacion;
	}

	
	public void test01AfegirFichaContieneMateriaSinEspecificarPorDefecto() throws DelegateException {
		
		AfegirActionParameters parametrosAfegir = afegirFicha("http://domini/relativa");
        verificaParamsFormularioRespuesta(parametrosAfegir);
        verificarRespuestaNoTieneMaterias();

        //assertTrue(spy.containsDebugMsg("procId 123 esta clasificado? false"));
        //assertTrue(spy.containsDebugMsg("procId 123 tiene 0 materiaOptions"));
 
	}

	public void test02SeleccionarFichaAmbUnaMateria() {
		
		// el procedimiento que se recupera esta definido en MockProcedimientoDelegate.seleccionar();
		
		conectaFicha(crearFichaConUnaMateria(5L,"Salut"));

    	setConfigFile("/WEB-INF/struts-config.xml");
    	request = createMockRequestWithPath("/contenido/ficha/seleccionar");
    	ParametresSeleccionar params = creaParametrosSeleccionarFicha("5");
    	
    	request = rellenaRequestConParametrosSeleccionar(params);
    	assertEquals("Seleccionar",request.getParameter("action"));
    	
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificarCamposFichaSeleccionada(request);
		verificarRespuestaTieneUnaMateria(request,"Salut");
		
        assertTrue(spy.containsDebugMsg("fichaId 5 esta clasificada? true"));
		
	}




	public void test03RelacionarMateriaEnProcedimentSenseClassificar() {

		conectaFicha(crearFichaConUnaMateria(6L,Materia.CE_SENSECLASSIFICAR));

	   	setConfigFile("/WEB-INF/struts-config.xml");
    	request = createMockRequestWithPath("/contenido/ficha/editar");
    	request = rellenaRequestConParametrosRelacionarMateria("alta","Agricultura" );
    	assertEquals("Materias Relacionadas",request.getParameter("action"));
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
		verificarRespuestaTieneUnaMateria(request,"Agricultura");
		
        assertTrue(spy.containsDebugMsg("fichaId 6 esta clasificada? true"));

	}

	public void test04EliminarMateriaEnProcedimentAmbUnaMateria() {

		conectaFicha(crearFichaConUnaMateria(7L,"Agricultura"));

	   	setConfigFile("/WEB-INF/struts-config.xml");
    	request=createMockRequestWithPath("/contenido/ficha/editar");
    	request = rellenaRequestConParametrosRelacionarMateria("baja","Agricultura" );
    	assertEquals("Materias Relacionadas",request.getParameter("action"));
        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
        
        verificarRespuestaNoTieneMaterias();
	}
	
	
	public void test05VerificarUrlMicrositeAbsolutaQuanUsuariSeleccionaUrlRelativa() throws DelegateException {

		AfegirActionParameters parametrosAfegir = afegirFicha("/relativa");
        verificaParamsFormularioRespuesta(parametrosAfegir);
        verificaUrlMicrositeAbsoluta();
	}
	
	public void test06VerificarUrlMicrositeAbsolutaQuanUsuariSeleccionaUrlAbsoluta() throws DelegateException {
		AfegirActionParameters parametrosAfegir = afegirFicha("http://domini/relativa");
        verificaParamsFormularioRespuesta(parametrosAfegir);
        verificaUrlMicrositeAbsoluta();	
    }
	
	public void test07VerificarUrlMicrositeEstaBuitQuanUsuariNoOmpleUrl() throws DelegateException {
		AfegirActionParameters parametrosAfegir = afegirFicha("");
        verificaParamsFormularioRespuesta(parametrosAfegir);
        verificarCampUrlMicrositeBuit();	
    }


	private AfegirActionParameters afegirFicha(String urlMicrosite) throws AssertionFailedError {
		System.setProperty("es.caib.rolsac.portal.url","http://epreinf45.caib.es:8080");
    	setConfigFile("/WEB-INF/struts-config.xml");
    	request=createMockRequestWithPath("/contenido/ficha/editar");
    	 
		AfegirActionParameters parametrosAfegir = creaParametrosAfegir(urlMicrosite);
		request = rellenaRequestConParametrosAfegir(parametrosAfegir);

        actionPerform(); 
        verifyNoActionErrors();
        verifyForward("success");
		return parametrosAfegir;
	}

	
	private void conectaFicha(Ficha ficha) {
		MockFichaDelegate.ficha= ficha;
		
	}

	private Ficha crearFichaConUnaMateria(long id, String ceMateria) {
		Ficha ficha = crearFicha(id);
		return relacionaMateriaConFicha(ceMateria,ficha);
		
	}

	private Ficha relacionaMateriaConFicha(String ceMateria, Ficha ficha) {
		Materia materia=new Materia();
		materia.setCodigoEstandar(ceMateria);
		materia.setId(obtenerIdMateria(ceMateria));
		Set<Materia> materias = new HashSet<Materia>();
		materias.add(materia);
		ficha.setMaterias(materias);
		return ficha;
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
	
	private Ficha crearFicha(Long id) {
		Ficha ficha=new Ficha(id);
		ficha.setResponsable("yo mismo");
	
		return ficha;
	}
	
	private void verificarRespuestaTieneUnaMateria(MockHttpServletRequest request, String ce) {
		Collection<Materia> materias =(Collection<Materia>)request.getAttribute("materiaOptions");
        assertEquals(1,materias.size());
        assertEquals(ce,materias.iterator().next().getCodigoEstandar());
	}

	private MockHttpServletRequest rellenaRequestConParametrosRelacionarMateria(String operacion, String ce) {
		  ParametresRelacionarMateria params = crearParametrosRelacionarMateria(operacion,ce);
		  request.addParameter("action",params.action);
    	  request.addParameter("ficha",params.ficha);
          request.addParameter("materia",params.materia);			
          request.addParameter("operacion",params.operacion);
          return request;
	}

	private void verificarCamposFichaSeleccionada(MockHttpServletRequest request) {
		ProcedimientoForm dform = (ProcedimientoForm )request.getAttribute("procedimientoForm");
		//assertEquals("yo mismo", dform.get("responsable"));  TODO falta camp responsable en struts-config.xml#formularioForm

	}

	
	private ParametresRelacionarMateria crearParametrosRelacionarMateria(String operacion, String ce) {
		ParametresRelacionarMateria params=new ParametresRelacionarMateria();
		params.action="Materias Relacionadas";
		params.ficha=MockFichaDelegate.ficha.getId().toString();
		params.materia=obtenerIdMateria(ce).toString();
		params.operacion=operacion;
		return params;
	}

	private MockHttpServletRequest rellenaRequestConParametrosSeleccionar(ParametresSeleccionar params) {
			request.addParameter("action", params.action);
			request.addParameter("idSelect",params.idSelect);
			return request;
	}

	private ParametresSeleccionar creaParametrosSeleccionarFicha(String id) {
		ParametresSeleccionar params=new ParametresSeleccionar();
    	params.action="Seleccionar";
		params.idSelect=id;
		return params;
	}

	private void verificarRespuestaNoTieneMaterias() {
		List<Materia> materias =(List<Materia>)request.getAttribute("materiaOptions");
        assertEquals(0,materias.size());
	}
	
    
	private void verificaParamsFormularioRespuesta(AfegirActionParameters params) {
		FichaForm dform = obtenerFormularioFichaDeRespuesta();
        verificarCampoValidacion(params, dform);
	}



	private void verificarCampoValidacion(AfegirActionParameters params, FichaForm dform) {
		assertEquals(Integer.parseInt(params.validacion),dform.get("validacion"));
	}
 
	
	private void verificaUrlMicrositeAbsoluta() {
		FichaForm dform = obtenerFormularioFichaDeRespuesta();
        verificarCampoUrlMicrositeEsAbsoluta(dform);
	}


	private FichaForm obtenerFormularioFichaDeRespuesta() {
		return (FichaForm)request.getAttribute("fichaForm");
	}

	
	private void verificarCampoUrlMicrositeEsAbsoluta(FichaForm dform) {
		String urlMicrosite = obtenerCampoUrlMicrosite(dform);
		assertNotNull(urlMicrosite);
		assertTrue(esUrlAbsoluta(urlMicrosite));
	}
	
	
	private void verificarCampUrlMicrositeBuit() {
		FichaForm dform = obtenerFormularioFichaDeRespuesta();
		assertEquals("", obtenerCampoUrlMicrosite(dform));
	}


	private String obtenerCampoUrlMicrosite(FichaForm dform) {
		return ((TraduccionFicha)dform.getCatala()).getUrl();
	}


	private boolean esUrlAbsoluta(String urlStr) {
		try {
			_(urlStr);
			URL url = new URL(urlStr);
			return null!=url.getProtocol();
		} catch (MalformedURLException e) {
			return false;
		}
	}

    private AfegirActionParameters creaParametrosAfegir(String urlMicrosite) {
    	
    	AfegirActionParameters params = new AfegirActionParameters();
    	params.action="Insertar";
    	params.validacion="0";
    	params.traducciones_0_titulo="ficha prueba";
    	params.traducciones_0_url=urlMicrosite;
    	return params;
	}


    
    private MockHttpServletRequest rellenaRequestConParametrosAfegir(AfegirActionParameters params) {
  	  	  request.addParameter("action",params.action);
    	  request.addParameter("validacion",params.validacion);
    	  request.addParameter("traducciones[0].nombre",params.traducciones_0_titulo);
    	  request.addParameter("traducciones[0].url",params.traducciones_0_url);
    	  return request;
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
