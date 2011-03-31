package org.ibit.rol.sac.back.action.archivo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;

/**
 * @struts.action
 *  path="/documento/archivo"
 *  scope="request"
 *  validate="false"
 */
public class DocumentoArchivoAction extends ArchivoAction {
    public Archivo obtenerArchivo(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {

        //obtener archivo concreto con el delegate
        
        String lang = request.getParameter("lang");

        if(null!=request.getParameter("idDocumento")) {
        	Long idDoc = new Long(request.getParameter("idDocumento"));
        	DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
        return docDelegate.obtenerArchivoDocumento(idDoc, lang, false);
    }

        if(null!=request.getParameter("idDocumentTramit")) {
        	Long idDoc = new Long(request.getParameter("idDocumentTramit"));
        	TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
        	DocumentTramit docTramit = tramDelegate.obtenirDocument(idDoc);
        	TraduccionDocumento tradoc = (TraduccionDocumento)docTramit.getTraduccion(lang);
        	return tradoc.getArchivo();
        }
        
        return null;
    }

    public boolean forzarDownload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request) throws Exception {
        return true;
    }
}
