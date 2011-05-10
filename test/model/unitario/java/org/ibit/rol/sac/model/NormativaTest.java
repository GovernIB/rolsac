package org.ibit.rol.sac.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NormativaTest {

	@Test
	public void areEquals() {
		Normativa m1 = new Normativa();
		m1.setId(1L);

		Normativa m2 = new Normativa();
		m2.setId(2L);
		
		Normativa m3 = new Normativa();
		m3.setId(1L);
		
		assertEquals(m1,m1);
		assertNotSame(m1,m2);
		assertEquals(m1,m3);
		
	}
}
