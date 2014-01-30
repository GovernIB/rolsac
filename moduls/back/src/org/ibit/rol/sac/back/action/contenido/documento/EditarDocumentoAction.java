/**
 * User: jhorrach
 * Date: Mar 9, 2004
 * Time: 1:50:49 PM
 */
package org.ibit.rol.sac.back.action.contenido.documento;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.persistence.delegate.ArchivoDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
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
 *  path="/contenido/documento/editar"
 *  input=".contenido.documento.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="documentoForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/documento/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.documento.form"
 *
 * @struts.action-forward
 *  name="procedimiento" path="/contenido/procedimiento/seleccionar.do"
 *
 * @struts.action-forward
 *  name="ficha" path="/contenido/ficha/seleccionar.do"
 *
 * * @struts.action-forward
 *  name="tramite" path="/contenido/tramite/seleccionar.do"

 *
 */
public class EditarDocumentoAction extends BaseDispatchAction {

	protected static Log log = LogFactory.getLog(EditarDocumentoAction.class);

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
			HttpServletResponse response) throws Exception {

		log.debug("entramos en editar");
		DocumentoForm dForm = (DocumentoForm)form;
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();        
		DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
		ArchivoDelegate archivoDelegate = DelegateUtil.getArchivoDelegate();

		
		boolean antiguo = dForm.get("id") != null;
		Documento documentoOld = null;
		Documento documento = new Documento();
		
		if (antiguo)
			documentoOld = docDelegate.obtenerDocumento((Long)dForm.get("id"));

		VOUtils.populate(documento, dForm); // Se recuperan los valores del formulario

		//tractam els fitxers associats a les traduccions
		Iterator lang = idiomaDelegate.listarLenguajes().iterator();
		Iterator fichers =  Arrays.asList((FormFile[]) dForm.get("fichers")).iterator();
		Vector<Long> ficherosAborrar = new Vector<Long>();

		//iteram per tots els idiomes
		while (lang.hasNext()){
			
			FormFile fichero = (FormFile) fichers.next();
			String idioma = (String) lang.next();
			TraduccionDocumento traDoc = (TraduccionDocumento) documento.getTraduccion(idioma);
			
			if (archivoValido(fichero)) {
				
				if (antiguo) {
				
					TraduccionDocumento traDocOld = (TraduccionDocumento) documentoOld.getTraduccion(idioma);
					
					if (request.getParameter("borrarfichero_" + idioma) != null || traDocOld.getArchivo() != null)
						ficherosAborrar.add(traDocOld.getArchivo().getId());
					
				}
				
				traDoc.setArchivo((populateArchivo(traDoc.getArchivo(), fichero)));
				
			} else if (request.getParameter("borrarfichero_" + idioma) != null && !"".equals(request.getParameter("borrarfichero_" + idioma))) {
				
				// Indicamos a la traducci�n del documento que no va a tener asignado el archivo.
				TraduccionDocumento traDocOld = (TraduccionDocumento) documentoOld.getTraduccion(idioma);
				ficherosAborrar.add( traDocOld.getArchivo().getId() );
				traDoc.setArchivo(null);
				
			} else if (documentoOld != null) {

				TraduccionDocumento traDocOld = (TraduccionDocumento) documentoOld.getTraduccion(idioma);
				
				if ( traDocOld != null )
					traDoc.setArchivo(traDocOld.getArchivo());
				
			}
			
			documento.setTraduccion(idioma, traDoc);
			
		}


		if (dForm.get("id") != null) {
			request.setAttribute("alert", "confirmacion.modificacion");
		} else {
			request.setAttribute("alert", "confirmacion.alta");
		}

		if (request.getParameter("idProcedimiento") != null) {
			Long id = new Long(request.getParameter("idProcedimiento"));
			docDelegate.grabarDocumento(documento, id, null);
			dForm.set("id",documento.getId());
			log.debug("Creat/Actualitzat " + documento.getId());
			request.setAttribute("idSelect", id);
			request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

			return mapping.findForward("procedimiento");
		}

		if (request.getParameter("idFicha") != null) {
			Long id = new Long(request.getParameter("idFicha"));
			docDelegate.grabarDocumento(documento, null, id);

			for (Long idArchivo : ficherosAborrar)
				archivoDelegate.borrarArchivo(idArchivo);


			dForm.set("id",documento.getId());
			log.debug("Creat/Actualitzat " + documento.getId());
			request.setAttribute("idSelect", id);
			request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

			return mapping.findForward("ficha");
			
		}

		/*
        if (request.getParameter("idDocInfTramite") != null){   // param definido en documento/formulario.jsp#form 
            Long id = new Long(request.getParameter("idDocInfTramite"));
            docDelegate.grabarDocumento(documento, null, null, id, null);
            dForm.set("id",documento.getId());
            log.debug("Creat/Actualitzat " + documento.getId());
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("tramite");
        }

        if (request.getParameter("idDocPreTramite") != null){  // param definido en documento/formulario.jsp#form
            Long id = new Long(request.getParameter("idDocPreTramite"));
            docDelegate.grabarDocumento(documento, null, null, null, id);
            dForm.set("id",documento.getId());
            log.debug("Creat/Actualitzat " + documento.getId());
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("tramite");
        }
		 */

		return mapping.findForward("fail");
		
	}

	public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entramos en seleccionar");
		TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
		DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();

		if (request.getParameter("idSelect") != null){
			Long id = new Long(request.getParameter("idSelect"));
			Documento documento = docDelegate.obtenerDocumento(id);
			VOUtils.describe(dForm, documento);
			/* if (documento.getArchivo() != null){
                dForm.set("nombreFichero",documento.getArchivo().getNombre());
            }
			 */
		} else {
			return mapping.findForward("fail");
		}

		return mapping.findForward("success");
		
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entramos en eliminar");
		DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();

		if (request.getParameter("idSelect") != null) {
			
			Long id = new Long(request.getParameter("idSelect"));
			log.debug("Eliminado Documento: " + id);
			docDelegate.borrarDocumento(id);
			TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
			dForm.reset(mapping, request);

			String fwd = null;
			id = null;
			
			if (request.getParameter("idProcedimiento") != null) {
				id = new Long(request.getParameter("idProcedimiento"));
				fwd = "procedimiento";
			}
			else 
				if (request.getParameter("idTramite") != null) {
					id = new Long(request.getParameter("idTramite"));
					fwd = "tramite";
				}
				else
					if (request.getParameter("idFicha") != null) {
						id = new Long(request.getParameter("idFicha"));
						fwd = "ficha";
					}

			request.setAttribute("idSelect", id);
			request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
			
			return mapping.findForward(fwd);
		
		}

		return mapping.findForward("fail");
	
	}

	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		log.debug("Entramos en cancelled");
		TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
		dForm.reset(mapping, request);

		if (request.getParameter("idProcedimiento") != null) {
			Long id = new Long(request.getParameter("idProcedimiento"));
			request.setAttribute("idSelect", id);
			request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

			return mapping.findForward("procedimiento");
		}
		
		if (request.getParameter("idFicha") != null) {
			Long id = new Long(request.getParameter("idFicha"));
			request.setAttribute("idSelect", id);
			request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

			return mapping.findForward("ficha");
		}

		return mapping.findForward("fail");
		
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entramos en unspecified");
		
		return null;
		
	}

}
