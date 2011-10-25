package org.ibit.rol.sac.back.negocio;

import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.model.Classificable;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

public abstract class GrabadorBase {

	private MateriaDelegate materiaDelegate;

	protected Materia buscarMateriaSinEspecificar() throws DelegateException {
		return getMateriaDelegate().obtenerMateriaCE(Materia.CE_SENSECLASSIFICAR);
	}

	public MateriaDelegate getMateriaDelegate() {
		if(null==materiaDelegate) materiaDelegate=DelegateUtil.getMateriaDelegate();
		return materiaDelegate;
	}

	public void setMateriaDelegate(MateriaDelegate materiaDelegate) {
		this.materiaDelegate = materiaDelegate;
	}


	protected void anadirMateriaSinEspecificar(Classificable materias) throws DelegateException {
		Materia sinEspecificar = buscarMateriaSinEspecificar();
		materias.addMateria(sinEspecificar);
	}
	
	public <T extends Classificable> T guardar(TraDynaValidatorForm dForm, T t) throws DelegateException {
		if(!t.tieneMaterias()) 
			anadirMateriaSinEspecificar(t);
		return grabar(dForm, t);
	}
	
	public abstract <T extends Classificable> T grabar (TraDynaValidatorForm dForm, T t) throws DelegateException;
	

}