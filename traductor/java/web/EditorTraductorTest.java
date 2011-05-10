package web;

import net.sourceforge.jwebunit.junit.WebTestCase;


public class EditorTraductorTest extends WebTestCase {
	
	@Override
    public void setUp() throws Exception {
        super.setUp();
       	beginAt("http://epreinf45.caib.es:8080/sacback");  //per defecte, catala
    	login("u92770","palma091");

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


    public void _testLogin() {
    	beginAt("http://epreinf45.caib.es:8080/sacback");
    	login("u92770","palma092");
    }

    /*
     *
     *<form name="procedimientoForm" method="post" action="/sacback/contenido/procedimiento/editar.do" id="procedimientoForm"> 
        
       <div id="capes"> 
        <div id="capa0" class="capa"> 
            <div class="pestanyes"> 
                    <div class="tab">Catalán</div> 
                    <div class="notab"><a href="javascript:activarCapa(1, 4)" class="notabb">Castellano</a></div> 
                    <div class="notab"><a href="javascript:activarCapa(2, 4)" class="notabb">Inglés</a></div> 
                    <div class="notab"><a href="javascript:activarCapa(3, 4)" class="notabb">Alemán</a></div> 
            </div> 
            <div class="bloc"> 
                <div class="component"> 
                    <div class="etiqueta">Nombre</div> 
                    <input type="text" name="traducciones[0].nombre" maxlength="256" tabindex="121" value="" class="btext" /> 
                </div> 
                <div class="component"> 
                    <div class="etiqueta">Objeto<br /><br /><br /></div> 
                    <textarea name="traducciones[0].resumen" tabindex="18" cols="60" rows="3"></textarea> 
                </div>
                ... 
            </div> 
        </div> 
    
        <div id="capa1" class="capa"> 
            <div class="pestanyes"> 
                    <div class="notab"><a href="javascript:activarCapa(0, 4)" class="notabb">Catalán</a></div> 
                    <div class="tab">Castellano</div> 
                    <div class="notab"><a href="javascript:activarCapa(2, 4)" class="notabb">Inglés</a></div> 
                    <div class="notab"><a href="javascript:activarCapa(3, 4)" class="notabb">Alemán</a></div> 
            </div> 
            <div class="bloc"> 
                <div class="component"> 
                    <div class="etiqueta">Nombre</div> 
                    <input type="text" name="traducciones[1].nombre" maxlength="256" tabindex="121" value="" class="btext" /> 
                </div> 
                <div class="component"> 
                    <div class="etiqueta">Objeto<br /><br /><br /></div> 
                    <textarea name="traducciones[1].resumen" tabindex="18" cols="60" rows="3"></textarea> 
                </div>
                ... 
     *   
     */
    public void testTradueixNouProcediment_TexteCatala() {
     	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	
    	setTextField("traducciones[0].nombre", "sol.licitud");
    	clickButtonWithText("Traduir");
    	assertTextFieldEquals("traducciones[0].nombre", "sol.licitud");
    	
    	//clickLinkWithExactText("Castellano");
    	assertTextFieldEquals("traducciones[1].nombre", "solicitud");
    }
    
    public void testTradueixNouProcediment_TexteCatala_2() {
     	clickLinkWithExactText("Procedimientos");
    	setWorkingForm("procedimientoForm");
    	
    	setTextField("traducciones[0].nombre", "ajut");
    	clickButtonWithText("Traduir");
    	assertTextFieldEquals("traducciones[0].nombre", "ajut");
    	
    	//clickLinkWithExactText("Castellano");
    	assertTextFieldEquals("traducciones[1].nombre", "ayuda");
    }


    public void _testTradueixProcedimentExistent_TexteCatala() {
    	beginAt("http://epreinf45.caib.es:8080/sacback/contenido/procedimiento/editar.do?action=Seleccionar&idSelect=390655");
    	login("u92770","palma092");
    	System.out.println(getPageSource());
    	clickButtonWithText("Traduir");
    	
    }

	private void dump() {    	System.out.println(getPageSource()); }

}
