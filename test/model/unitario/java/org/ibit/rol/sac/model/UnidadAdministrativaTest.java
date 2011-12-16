package org.ibit.rol.sac.model;

import org.junit.Test;
import static org.junit.Assert.*;


public class UnidadAdministrativaTest {

	
	@Test
	public void compruebaEquals() {

		//comprueba que son iguales
		UnidadAdministrativa ua1 =new UnidadAdministrativa(100L);
		UnidadAdministrativa ua2 =new UnidadAdministrativa(100L);
		assertEquals(ua1, ua2);
		
		//comprueba que son diferentes
		ua1 =new UnidadAdministrativa(100L);
		ua2 =new UnidadAdministrativa(222L);
		assertNotSame(ua1, ua2);

	}
	
	
	@Test
	public void compruebaCamposTraducibles() {
		TraduccionUA tradua = new TraduccionUA();
		tradua.setCvResponsable("mi curriculum");
		
	}
	
}
