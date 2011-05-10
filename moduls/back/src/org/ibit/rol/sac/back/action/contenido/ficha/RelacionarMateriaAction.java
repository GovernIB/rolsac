package org.ibit.rol.sac.back.action.contenido.ficha;

import org.ibit.rol.sac.back.action.contenido.common.ActionParameters;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;

public class RelacionarMateriaAction extends org.ibit.rol.sac.back.action.contenido.common.RelacionarMateriaAction {
	
	
	@Override
	protected Long obtenirIdInformacion(ActionParameters params) {
		return new Long(params.getParameter("ficha"));
	}


	protected boolean faltanParametres(ActionParameters params) {
		return params.getParameter("ficha") == null || params.getParameter("materia") == null;
	}


	protected void desrelacionarMateria(ActionParameters actionParams) throws DelegateException {
		getFichaDelegate().eliminarMateria(obtenirIdMateria(actionParams), obtenirIdInformacion(actionParams));
	}
 
	protected void relacionarMateria(ActionParameters params) throws DelegateException {
		getFichaDelegate().anyadirMateria(obtenirIdMateria(params),obtenirIdInformacion(params));
	}

	protected void eliminarMateriaSinClassificar(ActionParameters params) throws DelegateException {
		getFichaDelegate().eliminarMateria(obtenirIdMateriaSinClassificar(),obtenirIdInformacion(params));
	}
	
	protected void afegirMateriaSinClassificar(ActionParameters actionParams) throws DelegateException {
		getFichaDelegate().anyadirMateria(obtenirIdMateriaSinClassificar(),obtenirIdInformacion(actionParams));
	}

	protected boolean informacionNoEstaClassificada(ActionParameters params) throws DelegateException {
		Ficha ficha = getFichaDelegate().obtenerFicha(obtenirIdInformacion(params));
		return !ficha.estaClasificado();
	}
	
	private FichaDelegate fichaDelegate;

	public void setFichaDelegate(FichaDelegate fichaDelegate) {
		this.fichaDelegate = fichaDelegate;
	}

	public FichaDelegate getFichaDelegate() {
    	if(null==fichaDelegate) 
    		fichaDelegate=DelegateUtil.getFichaDelegate();
		return fichaDelegate;
	}


	
}
