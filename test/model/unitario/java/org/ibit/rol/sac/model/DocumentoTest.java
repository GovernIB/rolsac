package org.ibit.rol.sac.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DocumentoTest {

	@Test
	public void imprimeStringDocumentoSinTraduccionNiActuacion()  {
		Documento doc = new Documento();
		String str = doc.toString();
		assertTrue(!str.contains("ficha"));
		assertTrue(!str.contains("procedimiento"));
	}

	@Test
	public void imprimeStringDocumentoSinTraduccionConFicha()  {
		Documento doc = new Documento();
		doc.setFicha(new Ficha(123L));
		String str = doc.toString();
		assertTrue(str.contains("ficha"));
		assertTrue(!str.contains("procedimiento"));
	}

	
	@Test
	public void imprimeStringDocumentoSinTraduccionConProcedimiento()  {
		Documento doc = new Documento();
		doc.setProcedimiento(new ProcedimientoLocal(123L));
		String str = doc.toString();
		assertTrue(str.contains("procedimiento"));
		assertTrue(!str.contains("ficha"));
	}

	
}
