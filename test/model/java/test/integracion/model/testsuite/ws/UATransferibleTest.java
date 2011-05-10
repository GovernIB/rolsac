package test.integracion.model.testsuite.ws;


import java.io.IOException;

import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;

import test.integracion.model.common.ModelTestCase;


public class UATransferibleTest extends ModelTestCase {
	
	
		public void testJbossServiceXMLcontienePropiedadUrl() throws IOException {
			String s=leerJbossServiceXMLComoString();
			assertTrue(0<s.indexOf(UnidadAdministrativaTransferible.URL_UA));
	}
}
