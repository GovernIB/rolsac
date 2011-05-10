package org.ibit.rol.sac.back.negocio;

import java.util.ArrayList;
import java.util.List;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

public class BuscadorMaterias {

	
	public List<Materia> listarTodasMateriasFiltrandoSinClasificar() throws DelegateException {
		return listarMateriasFiltrandoSinClasificar(getMateriaDelegate().listarMaterias());
	}

	public List<Materia> listarMateriasFiltrandoSinClasificar(List<Materia> materias) {
		List<Materia> materiasFiltradas = new ArrayList<Materia>();
		
		for (Materia materia : materias) {
			if(Materia.CE_SENSECLASSIFICAR.equals(materia.getCodigoEstandar())) 
				continue;
			materiasFiltradas.add(materia);
		}
		
		return materiasFiltradas;
	}
	

	//beans & getters
	MateriaDelegate materiaDelegate;

	public MateriaDelegate getMateriaDelegate() {
		if(null==materiaDelegate) 
			materiaDelegate = DelegateUtil.getMateriaDelegate();
		return materiaDelegate;
	}

	public void setMateriaDelegate(MateriaDelegate materiaDelegate) {
		this.materiaDelegate = materiaDelegate;
	}

}
