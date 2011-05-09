package test.integracion.model.testsuite.ws;


import java.io.IOException;

import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;

import test.integracion.model.common.ModelTestCase;


public class ProcedimientoTransferibleTest extends ModelTestCase {
	
	
	public void testJbossServiceXMLcontienePropiedadUrl() throws IOException {
		String s=leerJbossServiceXMLComoString();
		assertTrue(0<s.indexOf(ProcedimientoTransferible.URL_PROC));
	}
	
	
}
