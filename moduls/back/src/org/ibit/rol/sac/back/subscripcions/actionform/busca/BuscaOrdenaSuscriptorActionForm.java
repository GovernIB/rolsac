package org.ibit.rol.sac.back.subscripcions.actionform.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;


/**
 * Clase que extiende de BuscaOrdenaActionForm para que se mantengan
 * los valores de consulta y ordenación en todos los mantenimientos
 * 

 * 
 */
public class BuscaOrdenaSuscriptorActionForm extends BuscaOrdenaActionForm
{
	
	private String filtroGrupo;    
    private List grupos;
    
    
    public String getFiltroGrupo() {
		return filtroGrupo;
	}

	public void setFiltroGrupo(String filtroGrupo) {
		this.filtroGrupo = filtroGrupo;
	}

	public void setGrupos(List grup){
    	this.grupos=grup;
    }
    
    public BuscaOrdenaSuscriptorActionForm()   {  
    }
    
    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest)
    {
    	GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
    	try {
			httpServletRequest.setAttribute("grupos",grupoDelegate.listarCombo(((TipoSuscripcion)httpServletRequest.getSession().getAttribute("MVS_suscripcion")).getId()));
		} catch (DelegateException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
        return null;
    }

}

