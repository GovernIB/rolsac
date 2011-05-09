package org.ibit.rol.sac.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MateriaTest {

	@Test
	public void areEquals() {
		Materia m1 = new Materia();
		m1.setId(1L);

		Materia m2 = new Materia();
		m2.setId(2L);
		
		Materia m3 = new Materia();
		m3.setId(1L);
		
		Normativa n1=new Normativa();
		n1.setId(1L);
		
		verifyReflexivo(m1);
		verifyEqualsDifferentId(m1, m2);
		verifyEqualsSameId(m1, m3);
		verifyEqualsDifferentTypes(m1, n1);
		verifyEqualsWithOneIdNull();
		verifyEqualsWithOneIdNull();
		
	}

	private void verifyEqualsDifferentId(Materia m1, Materia m2) {
		assertNotSame(m1,m2);
	}

	private void verifyEqualsDifferentTypes(Materia m1, Normativa n1) {
		assertNotSame(m1,n1);
		assertFalse(m1.equals(n1));

	}

	private void verifyEqualsSameId(Materia m1, Materia m3) {
		assertEquals(m1,m3);
	}

	private void verifyReflexivo(Materia m1) {
		assertEquals(m1,m1);
	}

	private void verifyEqualsWithOneIdNull() {
		Materia m4=new Materia();
		m4.setId(1L);
		Materia m5=new Materia();
		assertFalse(m4.equals(m5));
		assertFalse(m5.equals(m4));
	}
}
