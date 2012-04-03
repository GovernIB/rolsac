package es.caib.traspasa.actions;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import java.io.IOException;
import javax.servlet.ServletException;

import es.caib.traspasa.actionsforms.SearchnormativaActionForm;
import es.caib.traspasa.util.BdSearchnormativa;
import es.caib.traspasa.util.SearchNormativa;

public class SearchnormativaAction extends Action {

    
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
  
   System.out.println("ENTRAMOS EN ACTION. COMIENZA EL PROCESO");
    //recogemos el formulario
    SearchnormativaActionForm f = (SearchnormativaActionForm) form;
    SearchNormativa bdcons;
    
    String traspasboibMode = System.getProperty("es.caib.rolsac.traspasboib.mode");
    if (traspasboibMode == null || !traspasboibMode.equals("eBoib")) {
    	traspasboibMode = "boib";
    }
    if (traspasboibMode.equalsIgnoreCase("eboib")) {
        bdcons = new EBoibSearchNormativa(f);
    } else {
        bdcons = new BdSearchnormativa(f);
    }
    
    
    String parametro = "" + request.getParameter("trsid");
    System.out.println("PARAMETRO  "+ parametro);
    
    //lo de la X es una pequeña comprobacion.
    if ((!parametro.equals("null")) && (parametro.indexOf("X")!=-1)) 
      bdcons.makeSearchFromBoibRegistro(parametro);
    else
      bdcons.makeSearch();
      
    request.setAttribute("APPTRS_aviso",bdcons.getMensajeavisobean());
    System.out.println("bdcons.getNumeroNormativas() EN ACTION " + bdcons.getNumeroNormativas());
    
    if ((bdcons.getNumeroNormativas().equals("-1")) || (bdcons.getNumeroNormativas().equals("0"))) {
    	return mapping.findForward("successError");
    }
    
    if (bdcons.getNumeroNormativas().equals("1") && bdcons.getNormativabean() != null) {
      request.setAttribute("APPTRS_normativa",bdcons.getNormativabean());
      return mapping.findForward("successOne");
    } else {
      request.setAttribute("APPTRS_listanormativa", bdcons.getListadonormativas());
      request.setAttribute("APPTRS_normativa",bdcons.getNormativabean());
      return mapping.findForward("successVarios");
    }
    
  }
}