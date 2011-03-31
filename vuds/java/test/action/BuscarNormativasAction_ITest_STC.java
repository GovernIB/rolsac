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

import es.map.vuds.si.service.webservice.Normativa;

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

public class BuscarNormativasAction_ITest_STC extends MockStrutsTestCase {

	public BuscarNormativasAction_ITest_STC(String s) {
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
    //OK 26.02.2010
    public void _test01ConsultarNormativas_OK() {
    	String path="/contenido/tramite/vuds/buscarNormativas";
    	MockHttpServletRequest request=createMockRequest(path);
    	
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        List<Normativa> codis =(List<Normativa>)request.getAttribute("codis");
        
        assertNotSame(0,codis.size());
        for(Normativa t:codis) {_(t.getIdNormativa()+" "+t.getDescripcionNormativa());}
        
    }
    
    //OK 26.02.2010  
    public void _test01ConsultarNormativasFiltre_OK() {
    	String path="/contenido/tramite/vuds/buscarNormativas";
    	
    	//1. No filtrar
    	MockHttpServletRequest request=createMockRequest(path);
    	//request.addParameter("filtre", "C0001");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        List<Normativa> codis =(List<Normativa>)request.getAttribute("codis");
        assertNotSame(0,codis.size());
        for(Normativa t:codis) {_(t.getIdNormativa()+" "+t.getDescripcionNormativa());}
        
    	//2. Filtrar per codi vàlid
    	request=createMockRequest(path);
    	request.addParameter("filtre", "autorización");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<Normativa>)request.getAttribute("codis");
        assertNotSame(0,codis.size());
        for(Normativa t:codis) {_(t.getIdNormativa()+" "+t.getDescripcionNormativa());}

    	//3. Filtrar per codi invàlid
    	request=createMockRequest(path);
    	request.addParameter("filtre", "M0001");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<Normativa>)request.getAttribute("codis");
        assertSame(0,codis.size());

        
        //4. Filtrar per un desc que retorna almenys 1 codi  
    	request=createMockRequest(path);
    	request.addParameter("filtre", "desc tramit");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<Normativa>)request.getAttribute("codis");
        _(codis.size());
        assertSame(0,codis.size());
        

    }
    
    
    
    /**
     *  Aquest test necessita tenir el servidor vuds web proves APAGAT
     */
    public void _test02ConsultarNormativas_Falla() {
    	String path="/contenido/tramite/vuds/buscarNormativas";
    	MockHttpServletRequest request=createMockRequest(path);
    	
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
    	
        List<Normativa> codis =(List<Normativa>)request.getAttribute("codis");
        
        assertEquals(0,codis.size());
        
        //assertEquals("1111", codis.get(0).getIdNormativas());
        
    }
    
    //OK 26.02.2010
    public void test03ConsultarNormativasCaseInsensitive() {
    	String path="/contenido/tramite/vuds/buscarNormativas";
    	
    	MockHttpServletRequest request=createMockRequest(path);
    	//2. Filtrar per texte minúscules
    	request=createMockRequest(path);
    	request.addParameter("filtre", "autoriza");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        
        List<Normativa> codis =(List<Normativa>)request.getAttribute("codis");
        codis =(List<Normativa>)request.getAttribute("codis");
        int ncodis=codis.size();
        assertNotSame(0,ncodis);
        for(Normativa t:codis) {_(t.getIdNormativa()+" "+t.getDescripcionNormativa());}

    	//2. Filtrar per texte majúscules 
    	request=createMockRequest(path);
    	request.addParameter("filtre", "AUTORIZA");
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
    	
        codis =(List<Normativa>)request.getAttribute("codis");
        assertSame(ncodis,codis.size());

    }

    //OK 26.02.2010
    public void _test04ConsultarNormativasGuardatsEnSessio() {
    	String path="/contenido/tramite/vuds/buscarNormativas";
    	
    	//peticio sense codis en sessio
    	MockHttpServletRequest request=createMockRequest(path);
    	request=createMockRequest(path);
    	request.addParameter("filtre", "autorización");
    	
    	long t1=System.currentTimeMillis();
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        _("msecs = "+(System.currentTimeMillis()-t1));
        
        HttpSession session = request.getSession();
        List<Normativa> codis =(List<Normativa>)session.getAttribute("codis");
        assertNotNull(codis);
    	

    	//peticio amb codis en sessio
    	request.setParameter("filtre", "autorización");
    	t1=System.currentTimeMillis();
        actionPerform();
        verifyNoActionErrors();
        verifyForward("success");
        _("msecs = "+(System.currentTimeMillis()-t1));
        
    }
    
    
    

    
    //area privada
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
