package org.ibit.rol.sac.back.action.contenido.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.tools.ant.taskdefs.Sleep;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

public abstract class RelacionarMateriaAction extends BaseAction {

	protected static Log log = LogFactory.getLog(RelacionarMateriaAction.class);

	public ActionForward relacionarMateria(BaseDispatchAction action, ActionParameters params)
			throws DelegateException, Exception {
				log.info("Entramos en relacionar materia");
			
				if (faltanParametres(params))
					return forwardError(params);
			
				if (esAfegirMateria(params))
					afegirMateria(params);
				
				if (esEliminarMateria(params))
					eliminarMateria(params);
			
				return dispatchSeleccionar(action, params);
			    
			}

	private void eliminarMateria(ActionParameters actionParams) throws DelegateException {
		desrelacionarMateria(actionParams);
		if(informacionNoEstaClassificada(actionParams)) {
			dormir();  //dormir un temps perque enviaments al Portal del Actualitzador son asincrons 
			afegirMateriaSinClassificar(actionParams);
		}
	}


	private void afegirMateria(ActionParameters actionParams) throws DelegateException {
		if(informacionNoEstaClassificada(actionParams)) {
			eliminarMateriaSinClassificar(actionParams);
			dormir(); //dormir un temps perque enviaments al Portal del Actualitzador son asincrons
		}
		relacionarMateria(actionParams);
	}

	private void dormir() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	protected abstract boolean informacionNoEstaClassificada(ActionParameters params) throws DelegateException;

	protected abstract void desrelacionarMateria(ActionParameters actionParams) throws DelegateException;
	
	protected abstract void relacionarMateria(ActionParameters params) throws DelegateException;

	protected abstract void eliminarMateriaSinClassificar(ActionParameters params) throws DelegateException;
	
	protected abstract void afegirMateriaSinClassificar(ActionParameters actionParams) throws DelegateException;
	
	protected abstract boolean faltanParametres(ActionParameters params) ;

	protected abstract Long obtenirIdInformacion(ActionParameters params);
	
	protected Long obtenirIdMateriaSinClassificar() throws DelegateException {
		Materia materia = getMateriaDelegate().obtenerMateriaCE(Materia.CE_SENSECLASSIFICAR);
		return materia.getId();
	}


	protected Long obtenirIdMateria(ActionParameters params) {
		return new Long(params.getParameter("materia"));
	}

	private boolean esEliminarMateria(ActionParameters params) {
		return "baja".equals(params.getParameter("operacion"));
	}

	private boolean esAfegirMateria(ActionParameters params) {
		return "alta".equals(params.getParameter("operacion"));
	}


	protected ActionForward dispatchSeleccionar(BaseDispatchAction action,
			ActionParameters params) throws Exception {
		params.setAttribute("idSelect", obtenirIdInformacion(params));
		return action.dispatchMethod(params, "seleccionar");
	}

	//getters and setters

	MateriaDelegate materiaDelegate;

	public MateriaDelegate getMateriaDelegate() {
		if(null==materiaDelegate) materiaDelegate = DelegateUtil.getMateriaDelegate();
		return materiaDelegate;
	}

	public void setMateriaDelegate(MateriaDelegate materiaDelegate) {
		this.materiaDelegate = materiaDelegate;
	}


}
