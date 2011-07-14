package org.ibit.rol.sac.back.action.sistema.materia;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.MateriaForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.UnidadMateria;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Action para editar una Materia. (PORMAD)
 *
 * @struts.action
 *  name="materiaForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/materia/editar"
 *  input=".sistema.materia.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="materiaForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/materia/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.materia.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/matfamper.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.materia.lista"
 *
 */
public class EditarMateriaAction extends BaseDispatchAction {
    protected static Log log = LogFactory.getLog(EditarMateriaAction.class);

    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
    	log.debug("Entramos en editar");
    	IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
    	MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
    	
        MateriaForm dForm = (MateriaForm) form;
        Materia materia = new Materia();
        
        VOUtils.populate(materia, dForm);
        
        Materia materiaOld = null;
        boolean modificant = dForm.get("id") != null;
        if (modificant){
            //Recuperamos los datos de la materia
        	materiaOld = (Materia)materiaDelegate.obtenerMateria((Long)dForm.get("id"));
        }

        //tractam tots els fitxers associats a les traduccions
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();
        Iterator distribs =  Arrays.asList((FormFile[]) dForm.get("distribs")).iterator();
        Iterator normats =  Arrays.asList((FormFile[]) dForm.get("normats")).iterator();
        Iterator contens =  Arrays.asList((FormFile[]) dForm.get("contens")).iterator();
        //iteram per tots els idiomes
        while (lang.hasNext()){
            log.debug("Entro en el while de ficheros");
            FormFile distribComp = (FormFile) distribs.next();
            FormFile normativa = (FormFile) normats.next();
            FormFile contenido = (FormFile) contens.next();
            String idioma = (String) lang.next();
            TraduccionMateria traduccion = (TraduccionMateria) materia.getTraduccion(idioma);
            // tractam distribucióCompetencial
            if (archivoValido(distribComp)){
                traduccion.setDistribComp((populateArchivo(traduccion.getDistribComp(), distribComp)));
                materia.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrardistrib_" + idioma) != null){
                traduccion.setDistribComp(null);
                materia.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionMateria traduccionOld = (TraduccionMateria) materiaOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getDistribComp() != null)
                    traduccion.setDistribComp(traduccionOld.getDistribComp());
            }
            // tractam normativa aplicada
            if (archivoValido(normativa)){
                traduccion.setNormativa((populateArchivo(traduccion.getNormativa(), normativa)));
                materia.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarnormat_" + idioma) != null){
                traduccion.setNormativa(null);
                materia.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionMateria traduccionOld = (TraduccionMateria) materiaOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getNormativa() != null)
                    traduccion.setNormativa(traduccionOld.getNormativa());
            }
            // tractam contenido
            if (archivoValido(contenido)){
                traduccion.setContenido((populateArchivo(traduccion.getContenido(), contenido)));
                materia.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarconten_" + idioma) != null){
                traduccion.setContenido(null);
                materia.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionMateria traduccionOld = (TraduccionMateria) materiaOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getContenido() != null)
                    traduccion.setContenido(traduccionOld.getContenido());
            }
        }

        FormFile fotofile = (FormFile) dForm.get("fotofile");
        if (archivoValido(fotofile)) {
            materia.setFoto(populateArchivo(materia.getFoto(), fotofile));
        } else if (((Boolean) dForm.get("borrarfoto")).booleanValue()) {
            log.debug("entram borrarfoto");
            materia.setFoto(null);
        }else if (modificant){
            if(materiaOld.getFoto()!=null){
                materia.setFoto(materiaOld.getFoto());
            }
        }
        if (materia.getFoto() != null) {
            dForm.set("nombrefoto", materia.getFoto().getNombre());
        } else {
            dForm.set("nombrefoto", null);
        }

        FormFile iconofile = (FormFile) dForm.get("iconofile");
        if (archivoValido(iconofile)) {
            materia.setIcono(populateArchivo(materia.getIcono(), iconofile));
        } else if (((Boolean) dForm.get("borraricono")).booleanValue()) {
            log.debug("Entram borraricono");
            materia.setIcono(null);
        } else if (modificant){
            if(materiaOld.getIcono()!=null){
                materia.setIcono(materiaOld.getIcono());
            }
        }
        if (materia.getIcono() != null) {
            dForm.set("nombreicono", materia.getIcono().getNombre());
        } else {
            dForm.set("nombreicono", null);
        }

        FormFile iconogfile = (FormFile) dForm.get("iconogfile");
        if (archivoValido(iconogfile)) {
            materia.setIconoGrande(populateArchivo(materia.getIconoGrande(), iconogfile));
        } else if (((Boolean) dForm.get("borrariconog")).booleanValue()) {
            log.debug("entram borrariconogrande");
            materia.setIconoGrande(null);
        } else if (modificant){
            if(materiaOld.getIconoGrande()!=null){
                materia.setIconoGrande(materiaOld.getIconoGrande());
            }
        }
        if (materia.getIconoGrande() != null) {
            dForm.set("nombreiconog", materia.getIconoGrande().getNombre());
        } else {
            dForm.set("nombreiconog", null);
        }
        
        if (modificant){
            // ACTUALIZAMOS LA UA SELECCIONADA COMO UA PRINCIPAL
            Long uaprincipal=(Long)dForm.get("idUAPrincipal");
            materia.setUnidadesmaterias( setUAPrincipal(materiaOld.getUnidadesmaterias(), uaprincipal)  );        	
        }
            
        materiaDelegate.grabarMateria(materia);
        if(modificant){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", materia.getId());
        
        materia = materiaDelegate.obtenerMateria((Long) dForm.get("id"));
        request.setAttribute("iconosMateria", materia.getIconos());
        request.setAttribute("listaUAsMateria", materia.getUnidadesmaterias());
        log.debug("Creado/Actualizado " + materia.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

        Long id = (Long) dForm.get("id");

        if (materiaDelegate.tieneProcedimientosOFichas(id)){
            request.setAttribute("alert", "materia.relacion");
            request.setAttribute("idSelect", id);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        } else {
            log.debug("Eliminada Materia: " + id);
            materiaDelegate.borrarMateria(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        }

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            Materia materia = materiaDelegate.obtenerMateria(id);
            
            request.setAttribute("listaUAsMateria", materia.getUnidadesmaterias());
            dForm.set("idUAPrincipal", getUAPrincipal(materia.getUnidadesmaterias()));
            
            VOUtils.describe(dForm, materia);
            request.setAttribute("iconosMateria", materia.getIconos());

            if (materia.getFoto() != null) {
                dForm.set("nombrefoto", materia.getFoto().getNombre());
            }

            if (materia.getIcono() != null) {
                dForm.set("nombreicono", materia.getIcono().getNombre());
            }

            if (materia.getIconoGrande() != null) {
                dForm.set("nombreiconog", materia.getIconoGrande().getNombre());
            }

            return mapping.findForward("success");

        }



        return mapping.findForward("fail");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        dForm.reset(mapping, request);
        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

    // Dado un set devolvemos el id de la principal
    private Long getUAPrincipal(Set uas) {
    	
    	Iterator it=uas.iterator();
    	
    	while (it.hasNext()) {
    		UnidadMateria unimat= (UnidadMateria)it.next();
    		if (unimat.getUnidadPrincipal()!=null && unimat.getUnidadPrincipal().equals("S"))
    			return unimat.getId(); 
    	}
    	
    	return null;
    	
    }
    
    // Dado un set lo actualizamos con la principal seleccionada
    private Set setUAPrincipal(Set uas, Long id) {
    	
    	Iterator it=uas.iterator();
    	
    	if (id==null) return uas;
    	
    	while (it.hasNext()) {
    		UnidadMateria unimat= (UnidadMateria)it.next();
    		if (unimat.getId().longValue()==id.longValue()) {
    			unimat.setUnidadPrincipal("S");
    		}
    		else {
    			unimat.setUnidadPrincipal("N");
    		}
    	}
    	
    	return uas;

    }
    
}
