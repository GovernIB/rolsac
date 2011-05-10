package org.ibit.rol.sac.back.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

public class MateriasUtils {
	
	
	public static List<Materia> listarMateriasFiltrandoSinClasificar(Set<Materia> materias) {
		return listarMateriasFiltrandoSinClasificar(new ArrayList<Materia>(materias));
	}
	
	public static List<Materia> listarMateriasFiltrandoSinClasificar(List<Materia> materias) {
		List<Materia> materiasFiltradas = new ArrayList<Materia>();
		
		for (Materia materia : materias) {
			if(Materia.CE_SENSECLASSIFICAR.equals(materia.getCodigoEstandar())) 
				continue;
			materiasFiltradas.add(materia);
		}
		
		return materiasFiltradas;
	}
	

}
