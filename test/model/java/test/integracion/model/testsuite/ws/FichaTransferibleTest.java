package test.integracion.model.testsuite.ws;


import java.io.IOException;

import org.ibit.rol.sac.model.ws.FichaTransferible;

import test.integracion.model.common.ModelTestCase;


public class FichaTransferibleTest extends ModelTestCase {
	
	public void testJbossServiceXMLcontienePropiedadUrl() throws IOException {
		String s=leerJbossServiceXMLComoString();
		assertTrue(0<s.indexOf(FichaTransferible.URL_FICHA));
	}
	
}
