package org.ibit.rol.sac.back.action.contenido.tramite.vuds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.LookupDispatchAction;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import es.caib.vuds.VentanillaUnica;
import es.map.vuds.si.service.webservice.TramiteVuds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para buscar fichas
 *
 * @struts.action
 *  name="BusquedaCodisVudsForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/tramite/vuds/buscarCodis"  
 *
 * @struts.action-forward
 *  name="success" path=".contenido.tramite.vuds.popupCodisVuds"  
 *  
 * @struts.action-forward
 *  name="fail" path=".contenido.tramite.vuds.popupCodisVuds"
 *  
 */
public class BuscarCodisVudsAction extends Action{
    protected static Log log = LogFactory.getLog(BuscarCodisVudsAction.class);
    
    DestinatarioDelegate destinatarioDelegate;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.info("Entrem en llistar codis vuds");
        
        List<TramiteVuds> codis=new ArrayList<TramiteVuds>();
        
        codis=(List<TramiteVuds>)request.getSession().getAttribute("codisTram");
        String ep=obtenirEndpoint();
        if(null==ep) {
    		String msg="Error: No està definit el destinatari VUDS, o no té la URL definida.";
    		log.error(msg);
    		request.setAttribute("alert", msg);
    		return mapping.findForward("fail");
        }
        if(null==codis) {
        	VentanillaUnica vuds=new VentanillaUnica(ep);

        	log.info(System.getProperty("java.class.path"));
        	try {
        		codis=vuds.cargarCodisVuds("es");
        		request.getSession().setAttribute("codisTram", codis);
        	}
        	catch (WSInvocatorException  e) {
        		String msg="Error: No es pot contactar amb el servidor: "+ep+" "+e;
        		log.error(msg);
        		request.setAttribute("alert", msg);
        		return mapping.findForward("fail");
        	}
        }
        log.info("num de codis descarregats = "+codis.size());
        
        String desc=request.getParameter("filtre");
        
        request.setAttribute("codisTram",filtrar(codis,desc));
        
        return mapping.findForward("success");
    }
 
    private List<TramiteVuds> filtrar(List<TramiteVuds> codis, String desc) {
    	List<TramiteVuds> filtre= new ArrayList<TramiteVuds>();
    
    	//no hi ha cap filtre
    	if(null==desc) return codis;
    	
    	//filtrar per codi
    	for(TramiteVuds t:codis) { if(t.getIdTramiteVuds().equalsIgnoreCase(desc)) filtre.add(t); }
    	if(0!=filtre.size()) return filtre;
    	
    	//filtrar per descripcio
    	String regex="(?i).*"+desc+".*";
    	for(TramiteVuds t:codis) { if(t.getDescripcionTramiteVuds().matches(regex)) filtre.add(t); }
    	
    	return filtre;
    }

	
    private String obtenirEndpoint() throws DelegateException {
        destinatarioDelegate = null==destinatarioDelegate? DelegateUtil.getDestinatarioDelegate():destinatarioDelegate;
        List<Destinatario>destins = destinatarioDelegate.listarDestinatarios();
        String endpoint=null;
        for(Destinatario d : destins) if("VUDS".equalsIgnoreCase(d.getNombre())) {endpoint=d.getEndpoint(); break; }
    	return endpoint;
    }
    
    public DestinatarioDelegate getDestinatarioDelegate() {
		return destinatarioDelegate;
	}

	public void setDestinatarioDelegate(DestinatarioDelegate destinatarioDelegate) {
		this.destinatarioDelegate = destinatarioDelegate;
	}
}
