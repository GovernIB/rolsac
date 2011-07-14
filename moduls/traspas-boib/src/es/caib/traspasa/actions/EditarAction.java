package es.caib.traspasa.actions;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
//import org.ibit.rol.sac.back.action.contenido.normativa.EditarLocalAction;
/*import org.ibit.rol.sac.back.form.NormativaForm;
import org.ibit.rol.sac.back.utils.VOUtils;*/

import org.ibit.rol.sac.persistence.delegate.*;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.TraduccionNormativa;
import es.caib.traspasa.bean.TrMensaAvisoBean;
import es.caib.traspasa.actionsforms.NormativaForm;
import es.caib.traspasa.util.VOUtils;


public class EditarAction extends BaseDispatchAction
{

   protected Map getKeyMethodMap() {
        return null;
    }


  /**
   * This is the main action called from the Struts framework.
   * @param mapping The ActionMapping used to select this instance.
   * @param form The optional ActionForm bean for this request.
   * @param request The HTTP Request we are processing.
   * @param response The HTTP Response we are processing.
   * @throws javax.servlet.ServletException
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
   
  // contenido/normativa/local/editar
  // org.ibit.rol.sac.back.action.contenido.normativa.EditarLocalAction

  TrMensaAvisoBean mensaaviso = new TrMensaAvisoBean();
  try {

      //EditarLocalAction actionremoto = new EditarLocalAction(); //obtener la clase que va a realizar la insercion
      ActionForward retornoaction = editar(mapping, form, request, response); //pasar TODOS los parametros para la insercion

      mensaaviso.setCabecera("INSERCIÓ REALITZADA AMB ÈXIT");
      request.setAttribute("APPTRS_aviso",mensaaviso);

      return mapping.findForward(retornoaction.getName());

  } catch (Exception e) {
    mensaaviso.setCabecera("ERROR EN LA INSERCIÓ");
    mensaaviso.setSubcabecera("S'ha produit un error a la cridadaa a org.ibit.rol.sac.back.action.contenido.normativa.EditarLocalAction");
    mensaaviso.setDescripcion("Causes desconegudes");
    request.setAttribute("APPTRS_aviso",mensaaviso);

    return mapping.findForward("forwerror");
  }


  }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        NormativaForm dForm = (NormativaForm) form;
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
        BoletinDelegate boletinDelegate = DelegateUtil.getBoletinDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        NormativaLocal normativa = new NormativaLocal();
        VOUtils.populate(normativa, dForm);

        NormativaLocal normativaOld = null;
        boolean modificant = dForm.get("id") != null;
        if (modificant){
            //Recuperamos los datos de la normativa local
            normativaOld = (NormativaLocal)normativaDelegate.obtenerNormativa((Long)dForm.get("id"));
            normativa.setAfectadas(normativaOld.getAfectadas());
            normativa.setAfectantes(normativaOld.getAfectantes());
            normativa.setProcedimientos(normativaOld.getProcedimientos());
        }

        Iterator aux =  Arrays.asList((FormFile[]) dForm.get("ficheros")).iterator();
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();

        while (aux.hasNext()){
            log.debug("Entro en el while de ficheros");
            FormFile fichero = (FormFile) aux.next();
            String idioma = (String) lang.next();
            TraduccionNormativa traduccion = (TraduccionNormativa) normativa.getTraduccion(idioma);
            if (archivoValido(fichero)){
                traduccion.setArchivo(populateArchivo(traduccion.getArchivo(), fichero));
                normativa.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarfichero_" + idioma) != null){
                traduccion.setArchivo(null);
                normativa.setTraduccion(idioma, traduccion);
            } else if (modificant){
                TraduccionNormativa traduccionOld = (TraduccionNormativa) normativaOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getArchivo() != null)
                    traduccion.setArchivo(traduccionOld.getArchivo());
            }
        }

        normativa.setFecha(dForm.getFecha());
        normativa.setFechaBoletin(dForm.getFechaBoletin());
        if (dForm.get("valorNumero") != null)
            normativa.setNumero(((Long) dForm.get("valorNumero")).longValue());
        if (dForm.get("valorRegistro") != null)
            normativa.setRegistro(((Long) dForm.get("valorRegistro")).longValue());
        if (dForm.get("idTipo") != null)
            normativa.setTipo(tipoNormativaDelegate.obtenerTipoNormativa((Long) dForm.get("idTipo")));
        if (dForm.get("idBoletin") != null)
            normativa.setBoletin(boletinDelegate.obtenerBoletin((Long) dForm.get("idBoletin")));

        Long idUA = (Long) dForm.get("idUA");
        normativaDelegate.grabarNormativaLocal(normativa, idUA);

        if(modificant){
            log.debug("Entro en el if de confirmacion de modificacion");
            request.setAttribute("alert", "confirmacion.modificacion");
            request.setAttribute("afectacionOptions", normativa.getAfectadas());
            request.setAttribute("procedimientoOptions", normativa.getProcedimientos());
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }

        dForm.set("id", normativa.getId());
        log.debug("Creat/Actualitzat " + normativa.getId());

        return mapping.findForward("success");
    }
}