package org.ibit.rol.sac.back.action.contenido.tramite.vuds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.LookupDispatchAction;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import org.ibit.rol.sac.persistence.remote.vuds.TramiteValidado;
import org.ibit.rol.sac.persistence.remote.vuds.VentanillaUnica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para buscar fichas
 *
 * @struts.action
 *  name="codisVudsForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/tramite/vuds/previsualitzar"  
 *
 * @struts.action-forward
 *  name="success" path=".contenido.tramite.vuds.popupVisorVuds"  
 */
public class PrevisualitzarVudsAction extends Action{
    protected static Log log = LogFactory.getLog(PrevisualitzarVudsAction.class);

    TramiteDelegate tramiteDelegate;

    ProcedimientoDelegate procDelegate; //ho necessitem per llegir les normatives
    
    public ProcedimientoDelegate getProcDelegate() {
		return procDelegate;
	}

	public void setProcDelegate(ProcedimientoDelegate procDelegate) {
		this.procDelegate = procDelegate;
	}

	public TramiteDelegate getTramiteDelegate() {
		return tramiteDelegate;
	}

	public void setTramiteDelegate(TramiteDelegate tramiteDelegate) {
		this.tramiteDelegate = tramiteDelegate;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.debug("Entrem en previsualitzar tramit vuds");
        
        TramiteDelegate tramiteDelegate = null!=this.tramiteDelegate? this.tramiteDelegate: DelegateUtil.getTramiteDelegate();  //u92770[enric]
        ProcedimientoDelegate procDelegate = null!=this.procDelegate? this.procDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]
        TramiteForm dForm = (TramiteForm)form;
        long tid=Long.parseLong(request.getParameter("tid"));
        Tramite t_rolsac = tramiteDelegate.obtenerTramite(tid);
        ProcedimientoLocal p_rolsac=procDelegate.obtenerProcedimiento(t_rolsac.getProcedimiento().getId());
        t_rolsac.setProcedimiento(p_rolsac);
        t_rolsac.setOperativa(Tramite.Operativa.MODIFICA);

        String idioma="es";
        
        //obtener Archivos asociados a los formularios
        Set<DocumentTramit> forms = t_rolsac.getFormularios();
        for(Iterator<DocumentTramit> it = forms.iterator(); it.hasNext(); ) {
        	DocumentTramit dt =it.next();
        	DocumentTramit dta = tramiteDelegate.obtenirDocument(dt.getId());
        	dt.setArchivo(dta.getArchivo());
        }
        
        VentanillaUnica vuds=new VentanillaUnica();
        //es.map.vuds.si.service.webservice.Tramite t_vuds= vuds.tramitRolsacl2TramiteVUDS(t_rolsac, idioma);
        TramiteValidado traval = vuds.validarTramiteVuds(t_rolsac, idioma);
        es.map.vuds.si.service.webservice.Tramite t_vuds= traval.getTramite();
        
        request.setAttribute("sinTraducir",traval.getSinTraducir());
        
        request.setAttribute("tipoRegistro", t_vuds.getTipoRegistro());
        request.setAttribute("areaTramitadora", t_vuds.getAreaTramitadora());
        request.setAttribute("organismoCompetente", t_vuds.getOrganismoCompetente());
        request.setAttribute("canalTramitacion", t_vuds.getCanalTramitacion());
//FIXME temporal        request.setAttribute("codigoIdentificador", t_vuds.getCodigoIdentificador());
        request.setAttribute("denominacionTramite", t_vuds.getDenominacionTramite());
        request.setAttribute("descripcionTramite", t_vuds.getDescripcionTramite());
        request.setAttribute("formaIniciacion", t_vuds.getFormaIniciacion());
        request.setAttribute("observaciones", t_vuds.getObservaciones());
        request.setAttribute("plazosLegales", t_vuds.getPlazosLegales());
        request.setAttribute("tasa", t_vuds.getTasa());
        request.setAttribute("tiempoResolucion", t_vuds.getTiempoResolucion());
        request.setAttribute("tipoRegistro", t_vuds.getTipoRegistro());
        request.setAttribute("tipologia", t_vuds.getTipologia());
        request.setAttribute("tramiteVuds", t_vuds.getTramiteVuds());
        request.setAttribute("enlaceConsulta", t_vuds.getEnlaceConsulta());
        request.setAttribute("resultado", t_vuds.getResultado()[0]);
        request.setAttribute("formularios", t_vuds.getFormulario());
        request.setAttribute("requisitos", t_vuds.getRequisitosPrevios());
        request.setAttribute("documentos", t_vuds.getDocumento());
        request.setAttribute("normativa", t_vuds.getNormativa());
        
        return mapping.findForward("success");

    }

  
}
