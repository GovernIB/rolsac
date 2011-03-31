package test.web;

import com.thoughtworks.selenium.*;

import java.util.Date;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class CreaProcSimple_Sel extends TestCase {
	
	Selenium selenium; 
	
	public void setUp() throws Exception {
		//setUp("http://epreinf45.caib.es:8080/sacback", "*iexplore");
		String serverHost="epreinf45", browserStartCommand="*iexplore", 
		//String serverHost="epreinf45", browserStartCommand="*firefox",
		//browserURL="http://epreinf45.caib.es:8080";
		browserURL="https://proves.caib.es";
		int serverPort=5555; 
		
		selenium =new DefaultSelenium(serverHost, serverPort, browserStartCommand, browserURL);
		selenium.addCustomRequestHeader("Accept-Language", "ca");
		selenium.start();

		login();
	}

	private void login() {
		selenium.open("/sacback/");
		selenium.type("//input[@id='j_username' and @name='j_username' and @type='text']", "rsanz");
		selenium.type("//input[@id='j_password' and @name='j_password' and @type='password']", "rsanz");
		selenium.click("formUCboton");
		selenium.waitForPageToLoad("30000");
	}

	
	
	public void _test01login() throws Exception {
		login();
	}
	
	

	public void test02CreaProc() throws Exception {
		String NOM_PROC = "proc test "+new Date();
		selenium.click("link=Procedimientos");
		selenium.waitForPageToLoad("30000");
		selenium.type("signatura", "codi");
		selenium.select("validacion", "label=Público");
		selenium.click("boton");
		selenium.waitForPopUp("Calendario", "30000");
		selenium.selectWindow("name=Calendario");
		selenium.click("link=31");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirCalendario('textoFechaPublicacion')\"]");
		selenium.waitForPopUp("Calendario", "30000");
		selenium.selectWindow("name=Calendario");
		selenium.click("link=25");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirCalendario('textoFechaActualizacion')\"]");
		selenium.waitForPopUp("Calendario", "30000");
		selenium.selectWindow("name=Calendario");
		selenium.click("link=25");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick='abrirUAResponsable()']");
		selenium.waitForPopUp("Seleccionar", "30000");
		selenium.selectWindow("name=Seleccionar");
		selenium.click("link=Conselleria de Turisme");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick='abrirUAResolver()']");
		selenium.waitForPopUp("Seleccionar", "30000");
		selenium.selectWindow("name=Seleccionar");
		selenium.click("link=Govern de les Illes Balears");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirDato('Familia')\"]");
		selenium.waitForPopUp("Seleccionar", "30000");
		selenium.selectWindow("name=Seleccionar");
		selenium.click("//input[@value='Seleccionar']");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirDato('Iniciacion')\"]");
		selenium.waitForPopUp("Seleccionar", "30000");
		selenium.selectWindow("name=Seleccionar");
		selenium.click("//input[@value='Seleccionar']");
		selenium.selectWindow("title=SAC");
		selenium.type("tramite", "id1");
		selenium.type("version", "v1");
		selenium.type("url", "url1");
		selenium.click("indicador");
		selenium.click("ventana");
		selenium.click("taxa");
		selenium.type("traducciones[0].nombre", NOM_PROC);
		selenium.type("traducciones[0].resumen", "objecte");
		selenium.type("traducciones[0].resultat", "resultat");
		selenium.type("traducciones[0].destinatarios", "destinataris");
		selenium.type("traducciones[0].plazos", "presentacio");
		selenium.type("traducciones[0].resolucion", "term resol");
		selenium.type("traducciones[0].notificacion", "term notif");
		selenium.type("traducciones[0].silencio", "silenci");
		selenium.type("traducciones[0].observaciones", "obs");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		selenium.click("org.apache.struts.taglib.html.CANCEL");
		selenium.waitForPageToLoad("30000");
		assertEquals("Dades del Procedimient  (Codi:396663)", selenium.getText("//div[@id='ccontingut']/div[1]/h2"));
	}

	
	public void _test03creaTramEnProcExistent() throws Exception {
		String NOM_TRAMIT = "tramit test "+new Date();
		selenium.click("link=Nou Registre");
		selenium.waitForPageToLoad("30000");
		selenium.click("boton");
		selenium.waitForPopUp("Seleccionar", "30000");
		selenium.selectWindow("name=Seleccionar");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Solicitud de nombramiento de Traductor o Intérprete Jurado (con otros títulos)");
		selenium.selectWindow("title=SAC");
		selenium.select("fase", "label=Instrucció");
		selenium.select("validacio", "label=Pública");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirCalendario('textoFechaCaducidad')\"]");
		selenium.waitForPopUp("Calendario", "30000");
		selenium.selectWindow("name=Calendario");
		selenium.click("link=10");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirCalendario('textoFechaPublicacion')\"]");
		selenium.waitForPopUp("Calendario", "30000");
		selenium.selectWindow("name=Calendario");
		selenium.click("link=18");
		selenium.selectWindow("title=SAC");
		selenium.click("//input[@name='boton' and @value='Seleccionar' and @type='button' and @onclick=\"abrirCalendario('textoFechaActualizacion')\"]");
		selenium.waitForPopUp("Calendario", "30000");
		selenium.selectWindow("name=Calendario");
		selenium.click("link=25");
		selenium.selectWindow("title=SAC");
		selenium.type("traducciones[0].nombre", NOM_TRAMIT);
		selenium.type("traducciones[0].descripcion", "obj");
		selenium.type("traducciones[0].documentacion", "doc");
		selenium.type("traducciones[0].requisits", "requisits");
		selenium.type("traducciones[0].plazos", "plaç presen");
		selenium.type("traducciones[0].lugar", "lloc");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		selenium.selectWindow("name=null");
		selenium.click("//input[@name='action' and @value='Cancelar']");
		assertTrue(selenium.isTextPresent(NOM_TRAMIT));
	}
	
	public void test04selectProc() {
		selectProc("247");
	}
	
	private void selectProc(String pid) {
			selenium.click("link=Procedimientos");
			selenium.waitForPageToLoad("30000");
			selenium.click("link=Seleccionar por código");
			selenium.waitForPopUp("Seleccionar", "30000");
			selenium.selectWindow("name=Seleccionar");
			selenium.type("idSelect", "247");
			selenium.click("//input[@value='Seleccionar']");
			selenium.selectWindow("name=null");
			assertEquals("Dades del Procedimient  (Codi:"+pid+")", selenium.getText("//div[@id='ccontingut']/div[1]/h2"));
	}
	
}
