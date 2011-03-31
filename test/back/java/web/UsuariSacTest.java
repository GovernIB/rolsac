package web;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class UsuariSacTest extends WebTestCase {
	
	@Override
    public void setUp() throws Exception {
        super.setUp();
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
    
    public void testLiteralsTerminiNotificacio_rolsac_catala() {
    	beginAt("http://epreinf45.caib.es:8080/sacback");  //per defecte, catala
    	login("u92770","palma092");
    	clickLinkWithExactText("Procedimientos");
    	assertTextInElement("capa0", "Termini màxim per a la resolució");
    }
    
	public void testLiteralsTerminiNotificacio_webcaib_catala() {
    	beginAt("http://epreinf45.caib.es:8080/govern/sac/visor_proc.do?codi=390741&lang=ca&coduo=390419");
		assertTextInElement("fitxaProcediment", "Termini màxim per a la resolució");
	}

}
