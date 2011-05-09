/**
 * User: jhorrach
 * Date: Mar 9, 2004
 * Time: 1:50:49 PM
 */
package org.ibit.rol.sac.back.action.contenido.tramite.document;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.TraduccionDocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.persistence.delegate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para editar un Documento
 *
 * @struts.action
 *  name="documentoForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/tramite/document/editar"
 *  input=".contenido.documento.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="documentoForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/tramite/document/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.documento.form"
 *
 * @struts.action-forward
 *  name="tramite" path="/contenido/tramite/seleccionar.do"
 *
 */
public class EditarDocumentTramitAction extends BaseDispatchAction{

	public EditarDocumentTramitAction() {
		// TODO Auto-generated constructor stub
	}
	
    protected static Log log = LogFactory.getLog(EditarDocumentTramitAction.class);

    // u92770[enric]. fem 'static' aquestes propietats pq el IoC del sping no funciona aqui perque hi ha un
    // LongWaitRequestProcessor que crea la instancia (i no es fan les conexions)
    
    IdiomaDelegate idiomaDelegate;
    TramiteDelegate tramiteDelegate;
    
    public IdiomaDelegate getIdiomaDelegate() {
		return idiomaDelegate;
	}

	public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
		this.idiomaDelegate = idiomaDelegate;
	}

	public TramiteDelegate getTramiteDelegate() {
		return tramiteDelegate;
	}

	public void setTramiteDelegate(TramiteDelegate tramiteDelegate) {
		this.tramiteDelegate = tramiteDelegate;
	}

	protected Map getKeyMethodMap(){
        Map map = new HashMap();
        map.put("boton.insertar","editar");
        map.put("boton.modificar","editar");
        map.put("boton.seleccionar","seleccionar");
        map.put("boton.eliminar","eliminar");
        map.put("boton.cancelar","cancelar");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception{

        log.info("entramos en editar");
        DocumentoForm dForm = (DocumentoForm)form;
        IdiomaDelegate idiomaDelegate = null==this.idiomaDelegate?  DelegateUtil.getIdiomaDelegate() : this.idiomaDelegate;        
        TramiteDelegate tramDelegate = null==this.tramiteDelegate? DelegateUtil.getTramiteDelegate() : this.tramiteDelegate;

        Long id=((Long)dForm.get("id"));
        int tipus= new Integer(request.getParameter("tipus"));
        Long tid = new Long(request.getParameter("idTramite"));
        log.info("tid="+tid);


        boolean modificant=id==null?false:true;

        DocumentTramit documentTramit=null;
        
        if (modificant){
        	documentTramit=tramDelegate.obtenirDocument(id);
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
        	documentTramit=new DocumentTramit();;
            documentTramit.setTipus(tipus);
            request.setAttribute("alert", "confirmacion.alta");
        }

        VOUtils.populate(documentTramit, dForm, idiomaDelegate);
       
        //u92770[enric] - es checkeja id a 0 pq en junit usa un classloader diferent que el de jboss.
        if(null!=documentTramit.getId() && 0==documentTramit.getId()) documentTramit.setId(null);    
      
        
        //FIXME  u92770[enric] en el seguent bucle pot haber-hi un BUG. Es l'ordre dels langs iterator,
        //el mateix que fichers[]? Si no es així, hi ha un BUG.   

        //tractam els fitxers associats a les traduccions
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();
        Iterator fichers =  Arrays.asList((FormFile[]) dForm.get("fichers")).iterator();
        //iteram per tots els idiomes
        while (lang.hasNext()){
            log.info("Entro en el while de ficheros");
            FormFile fichero = (FormFile) fichers.next();
            String idioma = (String) lang.next();
            TraduccionDocumento traduccion = (TraduccionDocumento) documentTramit.getTraduccion(idioma);
            // tractam fichero
            if (archivoValido(fichero)){
                traduccion.setArchivo((populateArchivo(traduccion.getArchivo(), fichero)));
                documentTramit.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarfichero_" + idioma) != null){
                traduccion.setArchivo(null);
                documentTramit.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	DocumentTramit documentoOld = tramDelegate.obtenirDocument(id);
            	TraduccionDocumento traduccionOld = (TraduccionDocumento) documentoOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getArchivo() != null)
                    traduccion.setArchivo(traduccionOld.getArchivo());
            }
        }
        
        Long docId=tramDelegate.grabarDocument(documentTramit, tid);
        log.info("docId="+docId);
        request.setAttribute("idSelect", tid);  //ens redirigim al tramite
        request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
        return mapping.findForward("tramite");

    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        log.info(idiomaDelegate);
        IdiomaDelegate idiomaDelegate = null==this.idiomaDelegate?  DelegateUtil.getIdiomaDelegate() : this.idiomaDelegate;        
        TramiteDelegate tramDelegate = null==this.tramiteDelegate? DelegateUtil.getTramiteDelegate() : this.tramiteDelegate;

        log.info(idiomaDelegate);
		Long docId=null;
        if(null!=request.getParameter("idSelect"))
        	docId = Long.valueOf(request.getParameter("idSelect"));
        else if(null!=request.getAttribute("idSelect"))
        	docId = (Long)request.getAttribute("idSelect");
        if (docId != null){
            log.info("idSelect="+docId);
            DocumentTramit documentTramit = tramDelegate.obtenirDocument(docId);
            log.info(documentTramit);
            log.info(request.getParameterMap());
            VOUtils.describe(dForm, documentTramit,idiomaDelegate);
            
//FIXME - enric - error: fichers[0] no es propietat. cal aquest bucle?
//             Iterator<String> lang = (Iterator<String>)idiomaDelegate.listarLenguajes().iterator();
//            int i=0;
//            while (lang.hasNext()){
//            	TraduccionDocumento traduccion = (TraduccionDocumento) documentTramit.getTraduccion(lang.next());
//            	if(null!=traduccion.getArchivo())
//            	dForm.set("fichers["+ i++ +"]",traduccion.getArchivo().getNombre());
//            }

        } else {
            log.info("failed");
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
        TramiteDelegate tramDelegate = null==this.tramiteDelegate? DelegateUtil.getTramiteDelegate() : this.tramiteDelegate;
        if (request.getParameter("idSelect") != null){
            Long id = new Long(request.getParameter("idSelect"));
            tramDelegate.borrarDocument(id);
            TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
            dForm.reset(mapping, request);

            
            log.info("Eliminado Documento: " + id);

            id= new Long(request.getParameter("idTramite"));
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
            return mapping.findForward("tramite");
        }
        return mapping.findForward("fail");

    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

        if (request.getParameter("idTramite") != null){
            Long id = new Long(request.getParameter("idTramite"));
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
            return mapping.findForward("tramite");
        }
        return mapping.findForward("fail");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

}
