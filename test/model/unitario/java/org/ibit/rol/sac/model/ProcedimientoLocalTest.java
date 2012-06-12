package org.ibit.rol.sac.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcedimientoLocalTest {

		@Before
		public void setup() {
	    	System.setProperty("es.caib.rolsac.idiomaDefault", "ca");
		}
		
		@After
		public void tearDown() {
	    	System.setProperty("es.caib.rolsac.idiomaDefault", "");
		}
		
	
		@Test
		public void testToStringQuanProcedimientoNoEstaTraduit() {
			ProcedimientoLocal proc = new ProcedimientoLocal();
			proc.toString();
			System.out.println(proc.toString());
		}
		
		@Test
		public void testToStringQuanProcedimientoSiEstaTraduit() {
			ProcedimientoLocal proc = new ProcedimientoLocal();
			TraduccionProcedimientoLocal traduccion=new TraduccionProcedimientoLocal();
			traduccion.setNombre("nombre del proc");
			proc.setTraduccion("ca", traduccion);
			System.out.println(proc.toString());
		}
}
