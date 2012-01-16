package test.action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.RequestProcessor;
import org.easymock.EasyMock;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.model.DocumentTramit;
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

public class BuscadorProcedimientos_ITest_STC extends MockStrutsTestCase {

	public BuscadorProcedimientos_ITest_STC(String s) {
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
	 * Necesita definir en struts-config.xml el processor:
	 * <set-property property="processorClass" value="org.ibit.rol.sac.back.action.CustomRequestProcessor"/>
	 * para que no valide la operacion busqueda.
	 */

	//OK
	public void _test01BusquedaProcedimientos_PRO() throws DelegateException {
		String path="/contenido/procedimiento/editar";
		String action="B�squeda";

		//buscar PRO
		MockHttpServletRequest request=createMockRequest(path);
		request.addParameter("action", action);

		request.addParameter("signatura", "PRO");

		actionPerform(); 
		assertValidacioFormOK(request);

		//_(request.getAttribute(Globals.ERROR_KEY));

		verifyForward("lista");  //    <action path="/contenido/procedimiento/editar" 
		//	<forward name="lista" path="/contenido.procedimiento.lista" redirect="false"/>

		List<ProcedimientoLocal> trobats=(List<ProcedimientoLocal>)request.getAttribute("listaProcedimientos");
		assertNotNull(trobats.size());
		_(trobats.size());
		for(ProcedimientoLocal p:trobats) {
			_("["+p.getSignatura()+"]");
			assertTrue(p.getSignatura().trim().equalsIgnoreCase("PRO"));
		}

	}


	
	/*
	 * assumeixo que hi ha 1 mes de un proc que compleix pro + ua
	 */
	public void test02BusquedaProcedimientos_PRO_UA() throws DelegateException {
		String path="/contenido/procedimiento/editar";
		String action="B�squeda";
		MockHttpServletRequest request=createMockRequest(path);
		request.addParameter("action", action);
		
		request.addParameter("signatura", "PRO");
		request.addParameter("idUA", "1");

		actionPerform(); 
		assertValidacioFormOK(request);

		//_(request.getAttribute(Globals.ERROR_KEY));

		verifyForward("lista");  //    <action path="/contenido/procedimiento/editar" 
		//	<forward name="lista" path="/contenido.procedimiento.lista" redirect="false"/>

		List<ProcedimientoLocal> trobats=(List<ProcedimientoLocal>)request.getAttribute("listaProcedimientos");
		assertNotNull(trobats.size());
		_(trobats.size());
		for(ProcedimientoLocal p:trobats) {
			_("["+p.getSignatura()+"]");
			_(p.getUnidadAdministrativa());
			assertTrue(p.getSignatura().trim().equalsIgnoreCase("PRO"));
		}

	}

	public void test03BusquedaProcedimientos_PRO_nombre() throws DelegateException {
		String path="/contenido/procedimiento/editar";
		String action="B�squeda";
		MockHttpServletRequest request=createMockRequest(path);
		request.addParameter("action", action);

		request.addParameter("signatura", "PRO");
		request.addParameter("traducciones[0].nombre", "test");

		actionPerform(); 
		assertValidacioFormOK(request);

		//_(request.getAttribute(Globals.ERROR_KEY));

		verifyForward("lista");  //    <action path="/contenido/procedimiento/editar" 
		//	<forward name="lista" path="/contenido.procedimiento.lista" redirect="false"/>

		List<ProcedimientoLocal> trobats=(List<ProcedimientoLocal>)request.getAttribute("listaProcedimientos");
		assertNotNull(trobats.size());
		_(trobats.size());
		for(ProcedimientoLocal p:trobats) {
			_("["+p.getSignatura()+"]");
			_(p.getUnidadAdministrativa());
			assertTrue(p.getSignatura().trim().equalsIgnoreCase("PRO"));
		}

	}
	


	public void test04BusquedaProcedimientos_UA() throws DelegateException {
		String path="/contenido/procedimiento/editar";
		String action="B�squeda";
		MockHttpServletRequest request=createMockRequest(path);
		request.addParameter("action", action);
		
		request.addParameter("idUA", "1");

		actionPerform(); 
		assertValidacioFormOK(request);

		//_(request.getAttribute(Globals.ERROR_KEY));

		verifyForward("lista");  //    <action path="/contenido/procedimiento/editar" 
		//	<forward name="lista" path="/contenido.procedimiento.lista" redirect="false"/>

		List<ProcedimientoLocal> trobats=(List<ProcedimientoLocal>)request.getAttribute("listaProcedimientos");
		assertNotNull(trobats.size());
		_(trobats.size());
		for(ProcedimientoLocal p:trobats) {
			_("["+p.getSignatura()+"]");
			_(p.getUnidadAdministrativa());
			assertEquals(1, (long)p.getUnidadAdministrativa().getId());
		}

	}

	
	//area privada

	private void assertValidacioFormOK(MockHttpServletRequest request) {
		ActionErrors errors= (ActionErrors)request.getAttribute("org.apache.struts.action.ERROR");
		if(null!=errors) {
			Iterator<ActionError> it=errors.get();
			while(it.hasNext())  _(Arrays.toString(it.next().getValues()));
			fail();
		}
	}

	/*
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
	 */



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
