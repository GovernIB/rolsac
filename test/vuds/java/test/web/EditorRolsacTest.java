package test.web;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts.Globals;
import org.ibit.rol.sac.model.DocumentTramit;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.api.ITestingEngine;
import net.sourceforge.jwebunit.junit.WebTestCase;
import net.sourceforge.jwebunit.util.TestContext;

public class EditorRolsacTest extends WebTestCase {
	

	String baseEP="http://epreinf45.caib.es:8080/sacback";
	//String baseEndpoint="https://proves.caib.es/sacback";

	String passwd="palma091";

	private void _(Object o){ System.out.println(o); }
	
	@Override
    public void setUp() throws Exception {
        super.setUp();
        
        //Locale es=new Locale("es");
        //getTestContext().setLocale(es);  //no va
        
    	getTestContext().addRequestHeader("Accept-Language","es");  //OK
	}


    //OK
    public void _testLogin() {
    	beginAt(baseEP);
    	login("u92770",passwd);
    }
    
    /**
     * Test: EDITOR omple un nou procediment, selecciona
     * ventanilla unica, el sistema el valida, i l'envia al VUDS.
     * 
     */
 /*
    public void testCreaNouProcedimentVUDS() {
    	beginAt("http://epreinf45.caib.es:8080/sacback");  //per defecte, catala
    	login("u92770",passwd);
    	clickLinkWithExactText("Procedimientos");
    }                           
*/


    //OK 30/12/2009
    public void _test01CreaTramitVUDSConDocPresentarEnProcedimentExistent() {

    	_(getTestContext().getLocale());
    	
    	//selecciona un procediment
    	seleccionaProcediment("246");
    	
    	//inserta un tramit
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	//System.out.println(getPageSource());

    	setWorkingForm("tramiteForm");
    	String nomTramit="tramit de test "+new Date();
    	ompleTramit(nomTramit,true);
        
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        clickButtonWithText("Insertar");

        //aixo torna al formulari procediment
        clickButtonWithText("Cancelar");  

        //aixo torna al tramit
        String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

        setWorkingForm("tramiteForm");
    	assertSelectedOptionEquals("fase", "Instrucción");
    	//@deprecated assertTextFieldEquals("codiTaxa","123456");
    	assertTextFieldEquals("textoFechaCaducidad","10/02/2010");
    	assertTextFieldEquals("textoFechaPublicacion","10/12/2009");
    	assertTextFieldEquals("textoFechaActualizacion","11/12/2009");
    	assertTextFieldEquals("idOrganCompetent","1"); 
    	assertTextFieldEquals("nomOrganCompetent","Govern de les Illes Balears");

        _(getPageSource());

    	//afegeix un document informatiu
        clickLinkWithExactText("Nuevo Documento a Presentar");
    	//clickLinkWithExactText("???en.documentPresentar.nuevo???");  
    	setWorkingForm("documentoForm");
    	assertTextNotPresent("Archivo");
    	
    	String nomDoc="document de test "+new Date();
    	ompleDocument(nomDoc);
    	
    	
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        clickButtonWithText("Insertar");  //aixo ens torna
        System.out.println(getPageSource());
    	assertTextPresent(nomDoc);
    	
    	
    	//aixo torna al formulari
        xpath="id('ccontingut')/div[4]/form/div/div[contains(.,'"+nomDoc+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
        System.out.println(getPageSource());

    	setWorkingForm("documentoForm");
    	assertTextFieldEquals("traducciones[0].titulo", nomDoc);
    	assertTextNotPresent("Archivo");
    }


	//OK 15/12/2009  29/12/2009  30/12/2009   23/04/2009   paspre
	public void test02CreaTramitVUDSConDocInformatiuEnProcedimentExistent() {
		
		//selecciona un procediment
		seleccionaProcediment("246");
		
		//inserta un tramit
		clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
		//System.out.println(getPageSource());
	
		setWorkingForm("tramiteForm");
		String nomTramit="tramit de test "+new Date();
		ompleTramit(nomTramit,true);
	    
	    setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
	    //System.out.println(getPageSource());
	    clickButtonWithText("Insertar");
	
	    //aixo torna al formulari procediment
	    clickButtonWithText("Cancelar");  
	
	    //aixo torna al tramit
	    String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
	    clickElementByXPath(xpath);
	
	    setWorkingForm("tramiteForm");
		assertSelectedOptionEquals("fase", "Instrucción");
		assertTextFieldEquals("textoFechaCaducidad","10/02/2010");
		assertTextFieldEquals("textoFechaPublicacion","10/12/2009");
		assertTextFieldEquals("textoFechaActualizacion","11/12/2009");
		assertTextFieldEquals("idOrganCompetent","1"); 
		assertTextFieldEquals("nomOrganCompetent","Govern de les Illes Balears");
	
	
		//_viewSource();
		//center[1] -> enllaç a nova taxa
		//center[2] -> enllaç a nou formularis
		//center[3] -> enllaç a nou docs. infor
		
		//afegeix un document informatiu
		String href=getElementAttributeByXPath("id('ccontingut')/center[3]/a", "href");
		assertTrue(href.contains("tipus=0"));
		String[] tokens=href.split("[=&?]");
		String idTramite=null;
		for(int i=0;i<tokens.length;i++) if("idTramite".equals(tokens[i])) {idTramite=tokens[i+1];break;} 
		
		clickLinkWithExactText("Nuevo Documento Informativo");
	
		setWorkingForm("documentoForm");
		assertHiddenFieldPresent("tipus", "0");
		assertHiddenFieldPresent("idTramite", idTramite);
		assertTextPresent("Archivo");
	
		_("idTramite="+idTramite);
		String nomDoc="document de test "+new Date();
		ompleDocument(nomDoc);
		
		
	    setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
	    clickButtonWithText("Insertar");  //aixo ens torna al tramit
	    _(getPageSource());
		assertTextPresent(nomDoc);
		
		
		//aixo torna al formulari del document
		//id('ccontingut')/div[4] -> docs informatius
		
	    xpath="id('ccontingut')/div[4]/form/div/div[contains(.,'"+nomDoc+"')]/..//input[@value='Seleccionar']";
	    clickElementByXPath(xpath);
	
		setWorkingForm("documentoForm");
		assertTextFieldEquals("traducciones[0].titulo", nomDoc);
		assertTextPresent("Archivo");
	}

/*
    //OK 30/12/2009
    public void _test03CreaTramitVUDSConRequisitEnProcedimentExistent() {
    	
    	//selecciona un procediment
    	seleccionaProcediment("246");
    	
    	//inserta un tramit
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	//System.out.println(getPageSource());

    	setWorkingForm("tramiteForm");
    	String nomTramit="tramit de test "+new Date();
    	ompleTramit(nomTramit,true);
        
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        System.out.println(getPageSource());
        clickButtonWithText("Insertar");

        //aixo torna al formulari procediment
        clickButtonWithText("Cancelar");  

        //aixo torna al tramit
        String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

        setWorkingForm("tramiteForm");
    	assertSelectedOptionEquals("fase", "Instrucción");
    	assertTextFieldEquals("codiTaxa","123456");
    	assertTextFieldEquals("textoFechaCaducidad","10/02/2010");
    	assertTextFieldEquals("textoFechaPublicacion","10/12/2009");
    	assertTextFieldEquals("textoFechaActualizacion","11/12/2009");
    	assertTextFieldEquals("idOrganCompetent","1"); 
    	assertTextFieldEquals("nomOrganCompetent","Govern de les Illes Balears");

    	
    	//afegeix un tramit
    	String href=getElementAttributeByXPath("id('ccontingut')/center[4]/a", "href");
    	assertTrue(href.contains("tipus="+Integer.toString(DocumentTramit.REQUISIT)));
    	String[] tokens=href.split("[=&?]");
    	String idTramite=null;
    	for(int i=0;i<tokens.length;i++) if("idTramite".equals(tokens[i])) {idTramite=tokens[i+1];break;} 
    	
    	clickLinkWithExactText("Nuevo Requisito");

    	_(getPageSource());
    	setWorkingForm("documentoForm");
    	assertHiddenFieldPresent("tipus", Integer.toString(DocumentTramit.REQUISIT));
    	assertHiddenFieldPresent("idTramite", idTramite);
    	assertTextNotPresent("Archivo");
    	_("idTramite="+idTramite);
    	String nomDoc="document de test "+new Date();
    	ompleDocument(nomDoc);
    	
    	
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        clickButtonWithText("Insertar");  //aixo ens torna al tramit
        _(getPageSource());
    	assertTextPresent(nomDoc);
    	
    	
    	//aixo torna al document
        xpath="id('ccontingut')/div[5]/form/div/div[contains(.,'"+nomDoc+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
        System.out.println(getPageSource());

    	setWorkingForm("documentoForm");
    	assertTextFieldEquals("traducciones[0].titulo", nomDoc);
    	assertTextNotPresent("Archivo");
    }
*/
    //OK 30/12/2009
    public void _test04CreaTramitVUDSConFormulariEnProcedimentExistent() {
    	
    	//selecciona un procediment
    	seleccionaProcediment("246");
    	
    	//inserta un tramit
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	//System.out.println(getPageSource());

    	setWorkingForm("tramiteForm");
    	String nomTramit="tramit de test "+new Date();
    	ompleTramit(nomTramit,true);
        
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        System.out.println(getPageSource());
        clickButtonWithText("Insertar");

        //aixo torna al formulari procediment
        clickButtonWithText("Cancelar");  

        //aixo torna al tramit
        String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

        setWorkingForm("tramiteForm");
    	assertSelectedOptionEquals("fase", "Instrucción");
    	assertTextFieldEquals("codiTaxa","123456");
    	assertTextFieldEquals("textoFechaCaducidad","10/02/2010");
    	assertTextFieldEquals("textoFechaPublicacion","10/12/2009");
    	assertTextFieldEquals("textoFechaActualizacion","11/12/2009");
    	assertTextFieldEquals("idOrganCompetent","1"); 
    	assertTextFieldEquals("nomOrganCompetent","Govern de les Illes Balears");

    	
    	//afegeix un formulari
    	String href=getElementAttributeByXPath("id('ccontingut')/center[1]/a", "href");
    	assertTrue(href.contains("tipus=1"));
    	String[] tokens=href.split("[=&?]");
    	String idTramite=null;
    	for(int i=0;i<tokens.length;i++) if("idTramite".equals(tokens[i])) {idTramite=tokens[i+1];break;} 
    	
    	clickLinkWithExactText("Nuevo Formulario");

    	_(getPageSource());
    	setWorkingForm("documentoForm");
    	assertHiddenFieldPresent("tipus", "1");
    	assertHiddenFieldPresent("idTramite", idTramite);
    	assertTextPresent("Archivo");

    	_("idTramite="+idTramite);
    	String nomDoc="document de test "+new Date();
    	ompleDocument(nomDoc);
    	
    	
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        clickButtonWithText("Insertar");  //aixo ens torna al tramit
        _(getPageSource());
    	assertTextPresent(nomDoc);
    	
    	
    	//aixo torna al formulari
        xpath="id('ccontingut')/div[2]/form/div/div[contains(.,'"+nomDoc+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
        System.out.println(getPageSource());

    	setWorkingForm("documentoForm");
    	assertTextFieldEquals("traducciones[0].titulo", nomDoc);	
    	assertTextPresent("Archivo");

    }

    
    public void _testSeleccionarProcedimentNoMostraCampsEliminats() {
    	seleccionaProcediment("246");
    	assertTextNotPresent("Identificador Trámite");
    	assertTextNotPresent("Version Trámite");
    	assertTextNotPresent("Lugar");
    	assertTextNotPresent("Requisitos");
    }

    
    //OK 16/12/2009
    public void _test05SeleccionarProcedimentMostraCampsEliminatsComReadOnly() {
    	seleccionaProcediment("246");
    	
    	assertTextPresent("Identificador Trámite");
    	String xpath="id('procedimientoForm')/div[1]/div[9]/input";
    	IElement e=getElementByXPath(xpath);
    	assertEquals("readonly",e.getAttribute("readonly"));
    	assertEquals("ctextReadOnly",e.getAttribute("class"));
    	
    	assertTextPresent("Version Trámite");
    	xpath="id('procedimientoForm')/div[1]/div[10]/input";
    	e=getElementByXPath(xpath);
    	assertEquals("readonly",e.getAttribute("readonly"));
    	assertEquals("ctextReadOnly",e.getAttribute("class"));

    	assertTextPresent("URL Trámite Externo");
    	xpath="id('procedimientoForm')/div[1]/div[11]/input";
    	e=getElementByXPath(xpath);
    	assertEquals("readonly",e.getAttribute("readonly"));
    	assertEquals("ctextReadOnly",e.getAttribute("class"));

    	assertTextPresent("Requisitos");
    	xpath="id('capa0')/div[2]/div[4]/textarea";
    	e=getElementByXPath(xpath);
    	assertEquals("readonly",e.getAttribute("readonly"));
    	assertEquals("ctextAreaReadOnly",e.getAttribute("class"));
    	
    	assertTextPresent("Lugar");
    	xpath="id('capa0')/div[2]/div[8]/textarea";
    	e=getElementByXPath(xpath);
    	assertEquals("readonly",e.getAttribute("readonly"));
    	assertEquals("ctextAreaReadOnly",e.getAttribute("class"));
    	

    }

    //OK 13/01/2010
    public void _test07CancelaDocumentAPresentarEnTramitExistent() {
    	
    	//seleccciona procediment
    	seleccionaProcediment("246");
    	
    	//Seleccciona tramit existent segons el seu nom
    	String nomTramit="tramit de test Tue Dec 29 11:51:22 CET 2009";
    	String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

    	//selecciona document a presentar ja existent 
        xpath="id('ccontingut')/div[4]/form/div[1]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
        assertTextPresent("Modificación Documento a Presentar");
        
        //cancel.la document
    	setWorkingForm("documentoForm");
    	submit("action","Cancelar");  //aixo torna al tramit
    	assertTextPresent("Modificación Trámite");

    	
    	//selecciona formulario ja existent 
        xpath="id('ccontingut')/div[2]/form/div[1]//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
        assertTextPresent("Modificación Formulario");
        
        //cancel.la document
    	setWorkingForm("documentoForm");
    	submit("action","Cancelar");  //aixo torna al tramit
    	assertTextPresent("Modificación Trámite");
    	
    }

    //OK 14/01/2010
    public void _test08ModificarCodiVUDSTramitExistent() {
    	//seleccciona procediment
    	seleccionaProcediment("246");
    	
    	//Seleccciona tramit existent segons el seu nom
    	String nomTramit="tramit de test Tue Dec 29 11:51:22 CET 2009";
    	String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

        //modifica codi vuds
        String codi="3";
        String desc="desc codi 0003";
        setWorkingForm("tramiteForm");
        setHiddenField("codiVuds", codi);
        setTextField("descCodiVuds", desc );
        
        //guardar modificacio
        setExpectedJavaScriptAlert("Se ha realizado la modificación correctamente");
        System.out.println(getPageSource());
        clickButtonWithText("Modificar");
        
        //comprobar tramit modificat
    	seleccionaProcediment("246");
    	
    	//Seleccciona tramit existent segons el seu nom
        clickElementByXPath(xpath);

        setWorkingForm("tramiteForm");
        assertHiddenFieldPresent("codiVuds", codi);
        assertTextFieldEquals("descCodiVuds", desc);
        
    }
    
    //OK 18/01/2010
    public void _test09CrearTramitAmbOrganismeCompetentPerDefecte() {
    	//seleccciona procediment
    	seleccionaProcediment("246");

    	//llegir org.competent del procediment 
    	String ua=getElementTextByXPath("id('procedimientoForm')/div[1]/div[6]/input[2]");  //camp organo competente para resolver

    	
    	ua="DirecciÃ³ General d\'OrdenaciÃ³ i PlanificaciÃ³";
    	
    	//inserta un tramit
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	//System.out.println(getPageSource());

    	//llegir org.competent del tramit
    	//setWorkingForm("tramiteForm");
    	//ua=getElementTextByXPath("id('tramiteForm')/div[1]/div[2]/input[2]");  //camp organo competente para resolver
    	assertTextPresent(ua);
    	_(ua);
    	
    	String nomTramit="tramit de test "+new Date();
    	ompleTramit(nomTramit,false);
        
    	//llegir org.competent del tramit
    	
    	setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        clickButtonWithText("Insertar");

  
    	
    }
    

    //OK 18/01/2010
    public void _test10ComprovaCampsAgrupatsEnTramit()  {
    	//seleccciona procediment
    	seleccionaProcediment("246");
    	
    	//Seleccciona tramit existent segons el seu nom
    	String nomTramit="tramit de test Tue Dec 29 11:51:22 CET 2009";
    	String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
	
        assertTextPresent("Publicación Web");
        assertTextPresent("Tasa");
        assertTextPresent("Trámite Telemático");
    }
    
    //OK 18/01/2010
    /**
     * tests associats: STC:test04ReordenarFormularisTramit() DAO: testReordenarFormularisTramit(), testReordenarDocsPresentar()
     */
    public void _test11ReordenarFormularisEnTramitExistent() {
    	//seleccciona procediment
    	seleccionaProcediment("246");
    	
    	//Seleccciona tramit existent segons el seu nom
    	String nomTramit="tramit de test Tue Dec 29 11:51:22 CET 2009";
    	String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
        setWorkingForm("tramiteForm",1);
        
        setHiddenField("action", "Llistat de formularis");
        
        //recuperar elements ordenats
        xpath="id('ccontingut')/div[2]/form/div/div[1]/table/tbody/tr/td[1]/input";
        List<IElement> elems = getElementsByXPath(xpath);
        assertTrue(isSorted(elems));

        submit();  //Reordenar

        //comprobar la ordenacio despres
        elems = getElementsByXPath(xpath);
        assertTrue(isSorted(elems));
    }


    	/**
	     * test: editor omple un tramit simple i s'envia al servidor de la ventanilla unica
	     */
    
	    //OK 24.02.2010 - 25/01/2010
	   public void _test12CreaProcediment() {
	    	beginAt("http://epreinf45.caib.es:8080/sacback");  //per defecte, catala
	    	login("u92770",passwd);
	    	clickLinkWithExactText("Procedimientos");
	    	setWorkingForm("procedimientoForm");
	    	
	    	String nomProc="procediment de test "+new Date();
	    	boolean vuds=true;
	    	boolean taxa=true;
	    	
	    	String nomUA="Direcció General d'Ordenació i Planificació";
	    	setTextField("traducciones[0].nombre",nomProc);
	    	selectOptionByValue("validacion", "1");
	    	setHiddenField("idUA", "1");
	    	setTextField("nombreUA","Govern de les Illes Balears");
	    	setHiddenField("idUAResol", "49");
	    	setTextField("nombreUAResol",nomUA);
	    	setTextField("resultat", "optim");
	    	if(vuds) checkCheckbox("ventana");
	    	if(taxa) checkCheckbox("taxa");
	    	
	    	setExpectedJavaScriptAlert("Se ha realizado la modificación correctamente");
	    	submit("action","Insertar");

	    	setWorkingForm("procedimientoForm");
	    	assertCheckboxSelected("ventana");
	    	assertCheckboxSelected("taxa");
	    	assertTextFieldEquals("resultat", "optim");
	    	assertTextFieldEquals("idUAResol", "49");
	    	assertTextFieldEquals("nombreUAResol", nomUA);
	    	
	    }

	   
	    public void _test12CreaBorraProcediment() {
	    	String procedimentName="prova test "+new Date();
	    	int before=busquedaProcediments().size();
	    	creaProcediment(procedimentName,false);
	    	int now=busquedaProcediments().size();
	    	assertEquals(now,before+1);

	    	before=now;
	    	borraProcediment(procedimentName);
	    	assertEquals(busquedaProcediments().size()+1, before);
	    }

    
    //OK  17/12/2009
    public void _test06DocumentSelection() {
    	beginAt(baseEP);
    	login("u92770",passwd);
    	
    	//testejar que formulari demana arxiu
    	gotoPage("http://epreinf45.caib.es:8080/sacback/contenido/documento/form.do?idDocInfTramite=394477");
    	assertTextPresent("Archivo");
    	
    	//testejar que formulari no demana arxiu
    	gotoPage("http://epreinf45.caib.es:8080/sacback/contenido/documento/form.do?idDocInfTramite=394477&noFile");
    	assertTextNotPresent("Archivo");

    }
    
    //TODO
    public void _testSeleccionarProcedimentMostraCamps_es() {
    	seleccionaProcediment("246");
    	assertTextPresent("Taxa");
    	assertTextPresent("Órgano Responsable");
    	assertTextPresent("Resultado");
    	assertTextPresent("Código");
    	assertTextPresent("Validación");
    }

    
    //OK 15/12/2009
    public void _testCreaTramitVUDSConFormularioEnProcedimentExistent() {
    	String nomTramit="tramit de test "+new Date();
    	seleccionaProcediment("246");
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	//System.out.println(getPageSource());

    	setWorkingForm("tramiteForm");
    	ompleTramit(nomTramit,true);
        
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        System.out.println(getPageSource());
        clickButtonWithText("Insertar");

        clickButtonWithText("Cancelar");  //aixo torna al formulari procediment

        //aixo torna al tramit
        String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

        setWorkingForm("tramiteForm");
    	assertSelectedOptionEquals("fase", "Instrucción");
    	assertTextFieldEquals("codiTaxa","123456");
    	assertTextFieldEquals("textoFechaCaducidad","10/02/2010");
    	assertTextFieldEquals("textoFechaPublicacion","10/12/2009");
    	assertTextFieldEquals("textoFechaActualizacion","11/12/2009");
    	assertTextFieldEquals("idOrganCompetent","1"); 
    	assertTextFieldEquals("nomOrganCompetent","Govern de les Illes Balears");

    	_(getPageSource());

    	//afegir formulari
    	clickLinkWithExactText("Nuevo Formulario");
    	setWorkingForm("formularioForm");
    	
    	String nomForm="formulari de test "+new Date();
    	ompleFormulari(nomForm);

        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        clickButtonWithText("Insertar");
    	assertTextPresent(nomForm);
    	
    	//aixo torna al formulari
        xpath="//div[count(*)=0 and contains(.,'"+nomForm+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);
    	setWorkingForm("formularioForm");
    	assertTextFieldEquals("nombre", nomForm);	
    	assertTextFieldEquals("url", "URL1");	

    }

    
    //OK 15/12/2009  17/12/2009
    public void _testCreaTramitVUDSSimpleEnProcedimentExistent() {
    	String nomTramit="tramit de test "+new Date();
    	seleccionaProcediment("246");
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	//System.out.println(getPageSource());

    	setWorkingForm("tramiteForm");
    	ompleTramit(nomTramit,true);
        
        setExpectedJavaScriptAlert("Se ha realizado el alta correctamente");
        System.out.println(getPageSource());
        clickButtonWithText("Insertar");

        clickButtonWithText("Cancelar");  //aixo torna al formulari procediment

        //aixo torna al tramit
        String xpath="//div[count(*)=0 and contains(.,'"+nomTramit+"')]/..//input[@value='Seleccionar']";
        clickElementByXPath(xpath);

        setWorkingForm("tramiteForm");
    	assertSelectedOptionEquals("fase", "Instrucción");
    	assertTextFieldEquals("codiTaxa","123456");
    	assertTextFieldEquals("textoFechaCaducidad","10/02/2010");
    	assertTextFieldEquals("textoFechaPublicacion","10/12/2009");
    	assertTextFieldEquals("textoFechaActualizacion","11/12/2009");
    	assertTextFieldEquals("idOrganCompetent","1"); 
    	assertTextFieldEquals("nomOrganCompetent","Govern de les Illes Balears"); 

/*
        final WebClient webClient = new WebClient();

     // Instead of requesting the page directly we create a WebRequestSettings object
     WebRequestSettings requestSettings = new WebRequestSettings(new URL(uri), HttpMethod.POST);

     // Then we set the request parameters
     List<NameValuePair> postParams=new ArrayList<NameValuePair>();
     postParams.add( new NameValuePair("idProcedimiento","246"));
     postParams.add( new NameValuePair("idSelect","394455"));

     requestSettings.setRequestParameters(  new ArrayList());
     requestSettings.getRequestParameters().add(new NameValuePair("name of value to post", "value"));


     HtmlPage page = webClient.getPage(requestSettings);
*/
        
  //      PostMethod post = new PostMethod(uri);

    }
    
    //OK
    public void _testValidacioFalla_CreaTramitVUDSEnProcedimentExistent() {
    	seleccionaProcediment("246");
    	clickLinkWithText("Nuevo Registro", 0);  //1er enllaç = nuevo tramite
    	System.out.println(getPageSource());
    	setWorkingForm("tramiteForm");
        selectOption("fase", "Instrucción");
        setTextField("textoFechaCaducidad","10/02/2010");	
        setTextField("textoFechaPublicacion","10/12/2009");
        setTextField("textoFechaActualizacion","10/12/2009");
        setTextField("idUA","1"); //1 = "Govern de les Illes Balears");
        setTextField("descTaxa","descripcion taxa"); 
        setTextField("formaPagamentTaxa","en efectiu");
        setTextField("codiTaxa","123456");
        setTextField("versio","1");
        setTextField("urlExterna","http://urlExterna");
        //setTextField("traducciones[0].nombre","camp tramit de proves");  //camp obligatori
        setTextField("traducciones[0].documentacion","portar dni");
        setTextField("traducciones[0].plazos","3 meses");

        setExpectedJavaScriptAlert("El campo Nombre es obligatorio.");
        clickButtonWithText("Insertar");
    }
    
  
    
    //OK
    public void _test13BusquedaProcediments_superUsuari() {
    	List<IElement> procs=busquedaProcediments();    	
    }
    
    
  //OK
    public void _test14BusquedaProcediments_PRO_superUsuari() {
    	beginAt(baseEP);  //per defecte, catala
    	login("u92770",passwd);
    	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	setTextField("signatura","PRO");
    	submit("action","Búsqueda");
    	List<IElement> procs = getElementsByXPath("//div[@id='ccontingut']//div[@class='component']");
    	assertEquals(42,procs.size());
    }

    //OK
    public void _test15BusquedaProcediments_PRO_UA_superUsuari() {
    	beginAt(baseEP);  //per defecte, catala
    	login("u92770",passwd);
    	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	setTextField("signatura","PRO");
        setTextField("idUA","1"); //1 = "Govern de les Illes Balears");
    	submit("action","Búsqueda");
    	List<IElement> procs = getElementsByXPath("//div[@id='ccontingut']//div[@class='component']");
    	assertEquals(2,procs.size());
    }
    
    public void _test16BusquedaProcediments_PRO_rsanz() {
    	beginAt(baseEP);  //per defecte, catala
    	login("rsanz","rsanz");
    	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	setTextField("signatura","PRO");
    	submit("action","Búsqueda");
    	List<IElement> procs = getElementsByXPath("//div[@id='ccontingut']//div[@class='component']");
    	assertEquals(0,procs.size());
    }

    //OK 22abril10
    public void _test17VisualitzaProcediment_122600() {
    	beginAt(baseEP);  //per defecte, catala
    	login("u92770",passwd);
    	seleccionaProcediment("122600");
    	clickLinkWithExactText("previsualizar contenido");
    	gotoWindowByTitle("El Govern");

    	assertTextPresent("Reconeixement d'organitzacions de productors de fruites i hortalisses (OPFH)");
    	assertTextPresent("Direcció General d'Agricultura");  
    }
    
    //FALLA
    public void _test17VisualitzaProcediment_396895() {
    	beginAt(baseEP);  //per defecte, catala
    	login("u92770",passwd);
    	seleccionaProcediment("396895");
    	clickLinkWithExactText("previsualizar contenido");
    	gotoWindowByTitle("El Govern");
  
    }
    
    
    //--------- area privada ------------------------------------------------
    
    
	private boolean isSorted(List<IElement> elems ) {
		boolean sorted=true;
		long old=-1;
		Iterator<IElement> it=elems.iterator();
		while(it.hasNext()) {
			short s1=Short.parseShort(it.next().getAttribute("value"));
			if(old>s1) {sorted=false; break;}
			old=s1;
		}
		return sorted;
	}
	
    private List<IElement> busquedaProcediments() {
    	beginAt(baseEP);  //per defecte, catala
    	login("u92770",passwd);
    	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	submit("action","Búsqueda");
    	return getElementsByXPath("//div[@id='ccontingut']//div[@class='component']");
    	
    }

    
    private void ompleDocument(String nom) {
    	setTextField("traducciones[0].titulo", nom);
    }
    
    private void ompleFormulari(String nomForm) {
        setTextField("nombre", nomForm);	
        setTextField("url", "URL1");	
    }
    
    @Deprecated
    private void ompleTramit(String nomTramit, boolean oc) {
    	selectOption("fase", "Instrucción");
        setTextField("textoFechaCaducidad","10/02/2010");	
        setTextField("textoFechaPublicacion","10/12/2009");
        setTextField("textoFechaActualizacion","11/12/2009");
        if(oc) {
        setTextField("idOrganCompetent","1"); //1 = "Govern de les Illes Balears");
        setTextField("nomOrganCompetent","Govern de les Illes Balears");
        }
        //@deprecated setTextField("descTaxa","descripcion taxa"); 
        //@deprecated setTextField("formaPagamentTaxa","en efectiu");
        //@deprecated   setTextField("codiTaxa","123456");
        setTextField("versio","1");
        setTextField("urlExterna","http://urlExterna");
        setTextField("traducciones[0].nombre",nomTramit);
        setTextField("traducciones[0].documentacion","portar dni");
        setTextField("traducciones[0].plazos","3 meses");
    }
    
    private void seleccionaProcediment(String procid) {
    	String ep=baseEP+ "/contenido/procedimiento/editar.do?action=Seleccionar&idSelect="+procid;
    	System.out.println(ep);
    	beginAt(ep);
    	login("u92770",passwd);
    	setWorkingForm("procedimientoForm");
    	IElement e =getElementByXPath("id('procedimientoForm')/ input[@name='id']");
    	assertEquals(procid,e.getAttribute("value"));
    }
    
    
    private void seleccionaProcedimentByName(String procName) {
     	List<IElement> procs=busquedaProcediments();
    	int i=1;
    	for(IElement c:procs) {
    		if(c.getTextContent().contains(procName)) {
    			//String xp="//div[@id='ccontingut']//div[@class='component']["+i+"]"+
    			String xp="//div[@class='component']["+i+"]"+
    						"/div[@class='botoneraconsulta1']"+
    						"/form[@name='procedimientoForm']"+
    						"/input[@type='submit']";
    			clickElementByXPath(xp);
    			setWorkingForm("procedimientoForm");
    		}
    		else i++;
    	}
    }
    
    //OK 22abr01
    private void creaProcediment(String procName,boolean vuds)  {
    	beginAt("http://epreinf45.caib.es:8080/sacback");  //per defecte, catala
    	login("u92770",passwd);
    	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	selectOptionByValue("validacion", "1");
    	setHiddenField("idUA", "1");
    	setTextField("nombreUA","Govern de les Illes Balears");
    	setTextField("traducciones[0].nombre",procName);

    	IElement elem = getElementByXPath("id('capa0')/div[2]/div[9]/textarea");  //lugar readonly

    	assertEquals("readonly",elem.getAttribute("readonly"));
    	setTextField("traducciones[0].lugar","en un lugar");
    	
    	elem = getElementByXPath("id('capa0')/div[2]/div[6]/textarea");  //plazos readonly
    	assertEquals("readonly",elem.getAttribute("readonly"));

    	String requisit="algun requisit";
    	elem = getElementByXPath("id('capa0')/div[2]/div[5]/textarea");  //requisits no readonly
    	assertNotSame("readonly",elem.getAttribute("readonly"));
    	setTextField("traducciones[0].requisitos",requisit);

    	
    	if(vuds) checkCheckbox("ventana");
    	setExpectedJavaScriptAlert("Se ha realizado la modificación correctamente");
    	submit("action","Insertar");
    	//assertKeyNotPresent("org.apache.struts.action.ERROR");
    	//assertKeyNotPresent(Globals.ERROR_KEY);
    	assertValidacionOK(getServerResponse());
    	
    	seleccionaProcedimentByName(procName);
    	assertTextFieldEquals("traducciones[0].nombre",procName);
    	assertTextFieldEquals("traducciones[0].requisitos",requisit);
    	
    }
    
    private void assertValidacionOK(String page) {
    	boolean errors=page.contains("Se han producido errores");
    	if(errors) _(page);
    	assertFalse(errors);
    }
    
    private void borraProcediment(String procName) {
    	List<IElement> procs=busquedaProcediments();
    	int i=0;
    	int sizeAbans=procs.size();
    	for(IElement c:procs) {
    		if(c.getTextContent().contains(procName)) {
    			
    			String xp="//div[@id='ccontingut']//div[@class='component']["+i+"]"+
    			"/div[@class='botoneraconsulta1']"+
    			"/form[@name='procedimientoForm']"+
    			"/input[@type='submit']";

    			clickElementByXPath(xp);
    			setWorkingForm("procedimientoForm");
    			setExpectedJavaScriptConfirm("¿Desea dar de baja el registro actual?", true);
    			setExpectedJavaScriptAlert("Se ha realizado la baja correctamente");
    			submit("action","Eliminar");
    			break;
    		}
    		else i++;
    	}
    	
    }

    private void login(String user, String pwd) {
        assertTitleEquals("Govern de les Illes Balears");
        setWorkingForm("formUC");
        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", user);
        setTextField("j_password", pwd);
        setTextField("j_method", "U");
        submit();
        assertTitleEquals("SAC");
        assertTextInElement("cidentificacio", user);
    	//assertTextNotInElement("tramitacion", "Compte bloquetjat per raons de seguretat");

    }
    
    private void _viewSource() {
        System.out.println(getPageSource());
    }
    
    /*public void _testBorraProcediment() {
	List<IElement> procs=busquedaProcediments();
	
	int i=0;
	int sizeAbans=procs.size();
	for(IElement c:procs) {
		if(c.getTextContent().contains(procedimentName)) {
			
			String xp="//div[@id='ccontingut']//div[@class='component']["+i+"]"+
			"/div[@class='botoneraconsulta1']"+
			"/form[@name='procedimientoForm']"+
			"/input[@type='submit']";

			//IElement ee=getElementByXPath(xp);
			clickElementByXPath(xp);
			setWorkingForm("procedimientoForm");
			setExpectedJavaScriptConfirm("¿Desea dar de baja el registro actual?", true);
			setExpectedJavaScriptAlert("Se ha realizado la baja correctamente");
			submit("action","Eliminar");
			break;
		}
		else i++;
	}

	assertEquals(busquedaProcediments().size()+1,sizeAbans);
}


public void _testCreaProcediment() {
	int before=busquedaProcediments().size();
	creaProcediment(procedimentName);
	assertEquals(busquedaProcediments().size(),before+1);
}
*/

}
