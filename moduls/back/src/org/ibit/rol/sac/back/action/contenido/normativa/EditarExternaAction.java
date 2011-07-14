/**
 * User: jhorrach
 * Date: Feb 25, 2004
 * Time: 12:01:49 PM
 */
package org.ibit.rol.sac.back.action.contenido.normativa;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.FichaForm;
import org.ibit.rol.sac.back.form.NormativaForm;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.delegate.*;

import es.indra.rol.sac.integracion.traductor.Traductor;

/**
 * Action para editar una Normativa Externa.
 *
 * @struts.action
 *  name="normativaForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/normativa/externa/editar"
 *  input=".contenido.normativa.externa"
 *  parameter="action"
 *
 * @struts.action
 *  name="normativaForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/normativa/externa/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.normativa.externa"
 *
 * @struts.action-forward
 *  name="cancel" path="/contenido/normativa/externa.do"
 *
 * @struts.action-forward
 *  name="lista" path=".contenido.normativa.lista"
 *
 * @struts.action-forward
 *  name="eliminar" path=".contenido.normativa.baja"
 */
public class EditarExternaAction  extends BaseDispatchAction {

    protected static final Log log = LogFactory.getLog(EditarExternaAction.class);
    private static final String TIPO_NORM = "externa";

    protected Map<String,String> getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.traducir", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.busqueda.norelac", "norelacionadas");
        map.put("normativa.relacion.procedimientos", "procedimiento");
        map.put("normativa.relacion.afectaciones", "afectacion");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");

        NormativaForm dForm = (NormativaForm) form;
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
        BoletinDelegate boletinDelegate = DelegateUtil.getBoletinDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
        NormativaExterna normativa = new NormativaExterna();
        NormativaExterna normativaOld;

        try {        
        
        if ((Long) dForm.get("id") == null) {
        	normativaOld = null;
        	if (!normativaDelegate.autorizaCrearNormativa((Integer) dForm.get("validacion")))
        		throw new SecurityException("Aviso: No tiene privilegios para crear una normativa pública");
        } else { 
        	normativaOld = (NormativaExterna)normativaDelegate.obtenerNormativa((Long) dForm.get("id"));
        	if (!normativaDelegate.autorizaModificarNormativa(normativaOld.getId()))
        		throw new SecurityException("Aviso: No tiene privilegios para modificar la normativa");
        	normativa.setAfectadas(normativaOld.getAfectadas());
            normativa.setProcedimientos(normativaOld.getProcedimientos());
        }        
	
        VOUtils.populate(normativa, dForm);

        Iterator aux =  Arrays.asList((FormFile[]) dForm.get("ficheros")).iterator();
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();

        while (aux.hasNext()){
            FormFile fichero = (FormFile) aux.next();
            String idioma = (String) lang.next();
            TraduccionNormativaExterna  traduccion = (TraduccionNormativaExterna) normativa.getTraduccion(idioma);
            if (archivoValido(fichero)){
                traduccion.setArchivo(populateArchivo(traduccion.getArchivo(), fichero));
                normativa.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarfichero_" + idioma) != null){
                traduccion.setArchivo(null);
                normativa.setTraduccion(idioma, traduccion);
            } else if (dForm.get("id") != null) {
                TraduccionNormativaExterna traduccionOld = (TraduccionNormativaExterna) normativaOld.getTraduccion(idioma);
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

        request.setAttribute("afectacionOptions", normativa.getAfectadas());
        request.setAttribute("procedimientoOptions", normativa.getProcedimientos());
        
        
       //     if (request.getParameter("action").equals(getResources(request).getMessage(request.getLocale(), "boton.traducir"))) traducir(request,dForm);
       //      else {
            	 normativaDelegate.grabarNormativaExterna(normativa);
            
	            if(dForm.get("id") != null) request.setAttribute("alert", "confirmacion.modificacion");
	              else request.setAttribute("alert", "confirmacion.alta");
       //     }
        
        dForm.set("id", normativa.getId());
        request.setAttribute("normativaForm", dForm);    
        log.debug("Creat/Actualitzat " + normativa.getId());

        return mapping.findForward("success");
        
        } catch (SecurityException e) {
	    	
            if ((Long) dForm.get("id") == null) request.setAttribute("alert", "normativa.aviso.privilegio.insertar");
            	else request.setAttribute("alert", "normativa.aviso.privilegio.modificar");

        	request.setAttribute("idSelect", normativa.getId());
        	return dispatchMethod(mapping, form, request, response, "seleccionar");
	        
	    }
    }

    /**
     * Método que traduce un formulario de Normativa Externa
     * @author	Indra
     * @param request	petición de usuario
     * @param dForm		formulario dinámico enviado por el usuario
     * @throws Exception
     */
    private void traducir (HttpServletRequest request, NormativaForm dForm) throws Exception  {	

		Traductor traductor = (Traductor) request.getSession().getAttribute("traductor");
		String idiomaOrigen = "ca";
    	
        TraduccionNormativa normOrigen = (TraduccionNormativa)dForm.get("traducciones", 0);        	
    	
        Iterator itTradNorms = ((ArrayList) dForm.get("traducciones")).iterator();                
        Iterator<String> itLang = traductor.getListLang().iterator(); 
        
        while (itLang.hasNext()){
            
        	TraduccionNormativa normDesti = (TraduccionNormativa) itTradNorms.next();
        	String idiomaDesti = itLang.next();
        	
        	if (!idiomaOrigen.equals(idiomaDesti)) 
        	{
        		traductor.setDirTraduccio(idiomaOrigen, idiomaDesti);
        		
        		if (traductor.traducir(normOrigen, normDesti))  request.setAttribute("alert", "traduccion.confirmacion"); 
        		else {
        			request.setAttribute("alert", "traduccion.error");
        			break;
        		}
        	}
        }
           
       log.debug("Traducción Normativa Externa - Id: " + (Long) dForm.get("id"));
    	
    }
    
    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        NormativaForm dForm = (NormativaForm) form;
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

        Long id = (Long) dForm.get("id");

        if(normativaDelegate.autorizaModificarNormativa(id)) {
	        normativaDelegate.borrarNormativa(id);
	        log.debug("Eliminada Normativa Externa: " + id);
	        request.setAttribute("alert", "confirmacion.baja");
	        dForm.reset(mapping, request);
	        request.setAttribute("normativaForm", dForm);
	        return mapping.findForward("cancel");
        } else {    	
	        request.setAttribute("alert", "normativa.aviso.privilegio.eliminar");
	        request.setAttribute("idSelect", id);
	        return dispatchMethod(mapping, form, request, response, "seleccionar");
        }
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        NormativaForm dForm = (NormativaForm) form;
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            Normativa normativa = normativaDelegate.obtenerNormativa(id);
            if(normativa instanceof NormativaLocal){
                return mapping.findForward("fail");
            }
            NormativaExterna normativaExterna = (NormativaExterna)normativa;
            VOUtils.describe(dForm, normativaExterna);

            dForm.setFecha(normativaExterna.getFecha());
            dForm.setFechaBoletin(normativaExterna.getFechaBoletin());
            
            if (normativaExterna.getNumero() != null)
            dForm.set("valorNumero", normativaExterna.getNumero());
            
            if (normativaExterna.getRegistro() != null)
            dForm.set("valorRegistro", normativaExterna.getRegistro());

            if (normativaExterna.getBoletin() != null){
                dForm.set("idBoletin", normativaExterna.getBoletin().getId());
                dForm.set("nombreBoletin", normativaExterna.getBoletin().getNombre());
            }

            if (normativaExterna.getTipo() != null){
                dForm.set("idTipo", normativaExterna.getTipo().getId());
                dForm.set("nombreTipo", ((TraduccionTipo)normativaExterna.getTipo().getTraduccion()).getNombre());
            }

            request.setAttribute("afectacionOptions", normativaExterna.getAfectadas());
            request.setAttribute("procedimientoOptions", normativaExterna.getProcedimientos());

        }

        return mapping.findForward("success");
    }

    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en busqueda");
        NormativaForm dForm = (NormativaForm)form;
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        List normativas;

        if (dForm.get("idTipo") != null){
            Long idTipo = (Long) dForm.get("idTipo");
            normativas = normativaDelegate.buscarNormativasTipo(idTipo, TIPO_NORM);

        } else if (dForm.get("idBoletin") != null){
            Long idBoletin = (Long) dForm.get("idBoletin");
            normativas = normativaDelegate.buscarNormativasBoletin(idBoletin, TIPO_NORM);

        } else {

            Map paramMap = new HashMap();
            Map tradMap = new HashMap();

            Iterator itTrad = ((ArrayList) dForm.get("traducciones")).iterator();
            Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

            // Parámetros generales
            paramMap.put("numero", dForm.get("valorNumero"));
            paramMap.put("registro", dForm.get("valorRegistro"));

            if (request.isUserInRole("sacoper")){
                //paramMap.put("validacion", Validacion.INTERNA);
            	paramMap.put("validacion", "");
            } else {
                paramMap.put("validacion", dForm.get("validacion"));
            }

            paramMap.put("fecha", dForm.getFecha());
            paramMap.put("fechaBoletin", dForm.getFechaBoletin());

            // Parámetros traducibles
            tradMap.put("idioma", idiomaDelegate.lenguajePorDefecto());

            while (itLang.hasNext()){
                TraduccionNormativaExterna tNormativa = (TraduccionNormativaExterna) itTrad.next();
                int x = 0;
                x += tNormativa.getTitulo().length();
                x += tNormativa.getApartado().length();
                x += tNormativa.getEnlace().length();
                  if(tNormativa.getPaginaInicial() != null){
                    x += tNormativa.getPaginaInicial().toString().length();
                }
                if(tNormativa.getPaginaFinal() != null){
                    x += tNormativa.getPaginaFinal().toString().length();
                }
                x += tNormativa.getObservaciones().length();
                if (x > 0){
                    tradMap.put("idioma", itLang.next().toString());
                    tradMap.put("titulo", tNormativa.getTitulo().toUpperCase());
                    tradMap.put("apartado", tNormativa.getApartado().toUpperCase());
                    tradMap.put("enlace", tNormativa.getEnlace().toUpperCase());
                    tradMap.put("responsable", tNormativa.getResponsable().toUpperCase());
                    if(tNormativa.getPaginaInicial() != null){
                        tradMap.put("paginaInicial", tNormativa.getPaginaInicial());
                    }
                    if(tNormativa.getPaginaFinal() != null){
                        tradMap.put("paginaFinal", tNormativa.getPaginaFinal());
                    }
                    tradMap.put("observaciones", tNormativa.getObservaciones().toUpperCase());
                    break;
                } else {
                    itLang.next();
                }
            }

            normativas = normativaDelegate.buscarNormativas(paramMap, tradMap, TIPO_NORM);
        }

        if (normativas.size() == 1) {
            request.setAttribute("idSelect", ((Normativa) normativas.get(0)).getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        } else {
            request.setAttribute(TIPO_NORM, "true");
            request.setAttribute("listaNormativas", normativas);
        }

        return mapping.findForward("lista");

    }

    public ActionForward norelacionadas(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

           log.debug("Entramos en norelacionadas");
           NormativaDelegate normaDelegate = DelegateUtil.getNormativaDelegate();

           List normativas = normaDelegate.buscarNormativasNoRelacionadas(TIPO_NORM);
           if (normativas.size() == 1) {
               request.setAttribute("idSelect", ((Normativa) normativas.get(0)).getId());
               return dispatchMethod(mapping, form, request, response, "seleccionar");

           } else {
               request.setAttribute(TIPO_NORM, "true");
               request.setAttribute("listaNormativas", normativas);
           }

            return mapping.findForward("lista");
    }

    public ActionForward procedimiento(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en procedimiento");
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

        if (request.getParameter("normativa") != null && request.getParameter("procedimiento") != null){
            Long idNormativa = new Long(request.getParameter("normativa"));
            Long idProcedimiento = new Long(request.getParameter("procedimiento"));

            if ("alta".equals(request.getParameter("operacion")))
                normativaDelegate.anyadirProcedimiento(idProcedimiento, idNormativa);
            if ("baja".equals(request.getParameter("operacion")))
                normativaDelegate.eliminarProcedimiento(idProcedimiento, idNormativa);

            request.setAttribute("idSelect", idNormativa);
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        }

        return mapping.findForward("cancel");
    }

    public ActionForward afectacion(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en afectacion");
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

        if (request.getParameter("normativa1") != null
            && request.getParameter("normativa2") != null
            && request.getParameter("tafectacion") != null){
            Long idNormativa1 = new Long(request.getParameter("normativa1"));
            Long idNormativa2 = new Long(request.getParameter("normativa2"));
            Long idTafectacion = new Long(request.getParameter("tafectacion"));

            if ("alta".equals(request.getParameter("operacion")))
                normativaDelegate.anyadirAfectacion(idNormativa2, idTafectacion, idNormativa1);
            if ("baja".equals(request.getParameter("operacion")))
                normativaDelegate.eliminarAfectacion(idNormativa1, idTafectacion, idNormativa2);

            request.setAttribute("idSelect", idNormativa1);
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        }

        return mapping.findForward("cancel");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        NormativaForm dForm = (NormativaForm)form;
        dForm.reset(mapping, request);

        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

}

