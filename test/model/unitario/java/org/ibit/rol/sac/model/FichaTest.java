package org.ibit.rol.sac.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;


public class FichaTest {

	@Test
	public void printFicha() {
		Ficha ficha = crearFichaConUnaMateria(123L, Materia.CE_SENSECLASSIFICAR);
		
		System.out.println(ficha);
		
	} 
	
	private Ficha crearFicha(Long id) {
		Ficha ficha=new Ficha(id);
		ficha.setResponsable("yo mismo");
	
		return ficha;
	}

	private Ficha crearFichaConUnaMateria(long id, String ceMateria) {
		Ficha ficha = crearFicha(id);
		return relacionaMateriaConFicha(ceMateria,ficha);
		
	}

	private Ficha relacionaMateriaConFicha(String ceMateria, Ficha ficha) {
		Materia materia=new Materia();
		materia.setCodigoEstandar(ceMateria);
		materia.setId(obtenerIdMateria(ceMateria));
		Set<Materia> materias = new HashSet<Materia>();
		materias.add(materia);
		ficha.setMaterias(materias);
		return ficha;
	}
	
	
	private Long obtenerIdMateria(String ce)  {
		return 3L;
	}
}
