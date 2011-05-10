package org.ibit.rol.sac.back.action.organigrama.unidad;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.micromodel.Microsite;
import org.ibit.rol.sac.micropersistence.delegate.DelegateException;
import org.ibit.rol.sac.micropersistence.delegate.MicrositeDelegate;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 
 * Action para editar una Unidad Administrativa.
 *
 * @struts.action
 *  name="unidadForm"
 *  scope="request"
 *  validate="true"
 *  path="/organigrama/unidad/editar"
 *  input=".organigrama.unidad.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="unidadForm"
 *  scope="request"
 *  validate="false"
 *  path="/organigrama/unidad/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".organigrama.unidad.form"
 *
 * @struts.action-forward
 *  name="cancel" path=".organigrama.navges"
 *
 * @struts.action-forward
 *  name="lista" path=".organigrama.unidad.lista"
 *
 */
public class EditarUnidadAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarUnidadAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("ua.edificios", "edificio");
        map.put("ua.materias", "materia");
        map.put("ua.relacion.seccion", "ficha");
        map.put("ua.usuarios", "usuario");
        map.put("ua.relacion.procedimientos", "procedimiento");
        map.put("ua.relacion.procedimientos2", "procedimiento2");
        map.put("ua.relacion.procedimientos3", "procedimiento3");
        return map;
    }

	  private static class TreeSeccionComparator implements Comparator {
		    public int compare(Object element1, Object element2) {
		    	String lower1 =	 element1.toString();
		    	String lower2 =	 element2.toString();
		    	lower1 = lower1.substring(lower1.indexOf("#")+1,lower1.length());
		    	lower2 = lower2.substring(lower2.indexOf("#")+1,lower2.length());	
		      return lower1.compareTo(lower2);
		    }
	 }    
	  
	  /**
	   * Ordena un treemap segun el key
	   *
	   */
	  private TreeMap ordenartreeseccion(TreeMap treeseccion) {

		  TreeMap newtreesecciones = new TreeMap( new TreeSeccionComparator() );
		 
		    for( Iterator it = treeseccion.keySet().iterator(); it.hasNext(); )
		    {
		    	String key = (String)it.next();
		    	newtreesecciones.put(key,treeseccion.get(key));
		    }
		    
		    return newtreesecciones;
		  	
	  }	  
    
    
    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();

        UnidadAdministrativa unidad;
        if (dForm.get("id") != null) {
            unidad = unidadDelegate.consultarUnidadAdministrativa((Long)dForm.get("id"));
        } else {
            unidad = new UnidadAdministrativa();
        }
        VOUtils.populate(unidad, dForm);

        FormFile fotopfile = (FormFile) dForm.get("fotopfile");
        if (archivoValido(fotopfile)) {
            //borrarArchivo(unidad.getFotop());
            unidad.setFotop(populateArchivo(unidad.getFotop(), fotopfile));
        } else if (((Boolean) dForm.get("borrarfotop")).booleanValue()) {
            //borrarArchivo(unidad.getFotop());
            unidad.setFotop(null);
        }

        if (unidad.getFotop() != null) {
            dForm.set("nombrefotop", unidad.getFotop().getNombre());
        } else {
            dForm.set("nombrefotop", null);
        }


        FormFile fotogfile = (FormFile) dForm.get("fotogfile");
        if (archivoValido(fotogfile)) {
            //borrarArchivo(unidad.getFotog());
            unidad.setFotog(populateArchivo(unidad.getFotog(), fotogfile));
        } else if (((Boolean) dForm.get("borrarfotog")).booleanValue()) {
            //borrarArchivo(unidad.getFotog());
            unidad.setFotog(null);
        }

        if (unidad.getFotog() != null) {
            dForm.set("nombrefotog", unidad.getFotog().getNombre());
        } else {
            dForm.set("nombrefotog", null);
        }

        if(mostrarLogosUA()){
            FormFile logohfile = (FormFile) dForm.get("logohfile");
            if (archivoValido(logohfile)) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogoh(populateArchivo(unidad.getLogoh(), logohfile));
            } else if (((Boolean) dForm.get("borrarlogoh")).booleanValue()) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogoh(null);
            }

            if (unidad.getLogoh() != null) {
                dForm.set("nombrelogoh", unidad.getLogoh().getNombre());
            } else {
                dForm.set("nombrelogoh", null);
            }

            FormFile logovfile = (FormFile) dForm.get("logovfile");
            if (archivoValido(logovfile)) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogov(populateArchivo(unidad.getLogov(), logovfile));
            } else if (((Boolean) dForm.get("borrarlogov")).booleanValue()) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogov(null);
            }

            if (unidad.getLogov() != null) {
                dForm.set("nombrelogov", unidad.getLogov().getNombre());
            } else {
                dForm.set("nombrelogov", null);
            }

            FormFile logosfile = (FormFile) dForm.get("logosfile");
            if (archivoValido(logosfile)) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogos(populateArchivo(unidad.getLogos(), logosfile));
            } else if (((Boolean) dForm.get("borrarlogos")).booleanValue()) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogos(null);
            }

            if (unidad.getLogos() != null) {
                dForm.set("nombrelogos", unidad.getLogos().getNombre());
            } else {
                dForm.set("nombrelogos", null);
            }

            FormFile logotfile = (FormFile) dForm.get("logotfile");
            if (archivoValido(logotfile)) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogot(populateArchivo(unidad.getLogot(), logotfile));
            } else if (((Boolean) dForm.get("borrarlogot")).booleanValue()) {
                //borrarArchivo(unidad.getFotog());
                unidad.setLogot(null);
            }

            if (unidad.getLogot() != null) {
                dForm.set("nombrelogot", unidad.getLogot().getNombre());
            } else {
                dForm.set("nombrelogot", null);
            }
            request.setAttribute("mostrarLogos" , "mostrar");
        }

        if(mostrarFotosTipoUA()){
            request.setAttribute("mostrarFotosTipo" , "mostrar");
        }
        
        Long idTratamiento = (Long) dForm.get("idTratamiento");
        TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
        Tratamiento tratamiento = tratamientoDelegate.obtenerTratamiento(idTratamiento);
        unidad.setTratamiento(tratamiento);

        Long idEspacioTerrit = (Long) dForm.get("idEspacioTerrit");
        if(idEspacioTerrit != null){
            EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
            EspacioTerritorial espacioTerritorial = espacioDelegate.obtenerEspacioTerritorial(idEspacioTerrit);
            unidad.setEspacioTerrit(espacioTerritorial);
        }

        Long idPadre = (Long) dForm.get("idPadre");
        if (idPadre != null) {
            UnidadAdministrativa padre = unidadDelegate.obtenerUnidadAdministrativa(idPadre);
            //unidad.setPadre(padre);
            request.setAttribute("padre", padre);
        }
        
        if (dForm.get("id") != null) {
            unidadDelegate.actualizarUnidadAdministrativa(unidad, idPadre);
            request.setAttribute("alert", "confirmacion.modificacion");
            request.setAttribute("edificioOptions", unidad.getEdificios());
            request.setAttribute("unidadesmateriasOptions", unidad.getUnidadesMaterias());
            request.setAttribute("usuarioOptions", unidad.getUsuarios());
            
            //request.setAttribute("fichaUAOptions", unidad.getFichasUA());
            TreeMap treesecciones = (TreeMap)unidad.getMapSeccionFichasUA();
            TreeMap treeSortSecciones = ordenartreeseccion(treesecciones);
            request.setAttribute("fichaUAOptions", treeSortSecciones);
        } else {
        	/*
        	if (idPadre == null) {
                unidadDelegate.crearUnidadAdministrativaRaiz(unidad);
            } else {
                unidadDelegate.crearUnidadAdministrativa(unidad, idPadre);
            }
            */
        	Long idunidad;
            if (idPadre == null) {
            	idunidad=unidadDelegate.crearUnidadAdministrativaRaiz(unidad);
            } else {
            	idunidad=unidadDelegate.crearUnidadAdministrativa(unidad, idPadre);
            }
            unidad.setId(idunidad);
        	request.setAttribute("alert", "confirmacion.alta");
        }
        
        dForm.set("id", unidad.getId());
        log.info("Creado/Actualizado " + unidad.getId());

        return mapping.findForward("success");
    }

    /**
     *  Descripción: Action que se ejecuta al eliminar una UA. En esta Accion se controla que la UA no tenga Microsites relacionados,
     *  se comprueba que la UA no tenga elmentos relacionados, y finalmente si no tiene elementos se elimina la UA.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return actionforward
     */
    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws SecurityException,Exception {

        log.info("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();

        Long id = (Long) dForm.get("id");
        
        if (!hayMicrositesUA(id)) {
        
        	UnidadAdministrativa ua = unidadDelegate.consultarUnidadAdministrativa(id);
        	
        	//Validamos que se pueda eliminar la UA. Se podra elimnar si no tiene elementos relacionados. Ha excepción de usuarios y edificios.
        	ActionErrors errores =  validarEliminacionUA(ua,unidadDelegate);
        	if(!errores.isEmpty()){
        		saveErrors(request, errores);
        		request.setAttribute("idUA", id);
        		log.error("No se puede elimar la UA. La UA tiene elementos relacionados ");
        		return dispatchMethod(mapping, form, request, response, "seleccionar");
        		//return mapping.findForward("success");
	        }
	
        	unidadDelegate.eliminarUaSinRelaciones(id);

        	dForm.reset(mapping, request);
	        request.setAttribute("alert", "confirmacion.baja");
	        log.info("Eliminada Unidad Administrativa: " + id);

        } else {
	        request.setAttribute("alert", "microsites.ua.asociados");
	        log.info("No se ha eliminado Unidad Administrativa: " + id + " . Causa: Tiene asociado algún microsite");
        	
        }
        return mapping.findForward("cancel");
    }

    /**
     * Descripción: Método que valida si la UA puede ser eliminada.
     * 
     * @author Indra
     * @param  ua  Unidad administrativa
     * @param  unidadDelegate  Delegado de la Unidad administrativa
     * @return Devuelve un objeto actionerrors, si el objeto contiene errores la UnidadAdministrativa no podra ser borrada
     */
    private ActionErrors validarEliminacionUA(UnidadAdministrativa ua, UnidadAdministrativaDelegate unidadDelegate) {
    	
    	ActionErrors errores = new ActionErrors();
    	String ids ="";
    	boolean boolProcedIsEmpty =ua.getProcedimientos().isEmpty();
    	
    	// Comprobar si el usuari puede eliminar UA
    	try{
    		if (!ua.isRaiz()) { 
    			if (!unidadDelegate.autorizarEliminarUA(ua.getPadre().getId()))
    				errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.permisosAntecesorUA"));
    		} else {
    			if (!unidadDelegate.autorizarEliminarUA(ua.getId()))
    				errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.permisosUA"));
    		}
    		
    	}catch(Exception e){
    		errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.permisos"));
    		return errores;  
    	}

    	//Compronbar si la UA tiene elementos relacionados
        if(!ua.getHijos().isEmpty()){
        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneHijos"));
        }else if(!ua.getFichasUA().isEmpty()){
        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneFichas"));
        }else if(!ua.getPersonal().isEmpty()){
        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tienePersonal"));
        }else if(!ua.getUnidadesMaterias().isEmpty()){
        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneUnidadesMaterias"));
        }else if(!boolProcedIsEmpty || !ua.getNormativas().isEmpty() ){
       	
        	List <Long> idsList=new ArrayList<Long>();

        	if(!boolProcedIsEmpty)
        		idsList = ua.getIdsProcedimientos();
        	else
        		idsList = ua.getIdsNormativas();
        	    	
        	Iterator<Long> iter = idsList.iterator();
        	int count = 0;
        	while(iter.hasNext()){
        		Long id = iter.next();
        		if (ids.equals("")){
          			ids = id.toString();
          			count++;
        		}else{
          			if ((count % 10) == 0)
          				ids = ids + ",<BR> " + id.toString();
          			else
          				ids = ids + ", " + id.toString();
          			
          			count++;
          		}
        	}
        	
        	if(!boolProcedIsEmpty)
        		errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneProcedimientos",ids));
        	else
        		errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneNormativas",ids));
        	
        }

        return errores;
    }
    
    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();

        Long id = null;

        if (request.getParameter("idUA") != null)
            id = new Long(request.getParameter("idUA"));

        if (request.getAttribute("idUA") != null)
            id = (Long) request.getAttribute("idUA");

        if (id != null){
            UnidadAdministrativa uni = unidadDelegate.consultarUnidadAdministrativa(id);
            VOUtils.describe(dForm, uni);
            //dForm.set("estadovalidacion", new Integer(uni.getValidacion()));

            if (uni.getTratamiento() != null) {
                dForm.set("idTratamiento", uni.getTratamiento().getId());
            }
            
            if (uni.getEspacioTerrit() != null) {
            	dForm.set("idEspacioTerrit",uni.getEspacioTerrit().getId());
            }
            
            if (uni.getPadre() != null) {
                dForm.set("idPadre", uni.getPadre().getId());
                request.setAttribute("padre", uni.getPadre());
            } else {
                dForm.set("idPadre", null);
                request.removeAttribute("padre");
            }

                if (uni.getFotop() != null) {
                    dForm.set("nombrefotop", uni.getFotop().getNombre());
                }
                if (uni.getFotog() != null) {
                    dForm.set("nombrefotog", uni.getFotog().getNombre());
                }
            if(mostrarLogosUA()){
                if (uni.getLogoh() != null) {
                    dForm.set("nombrelogoh", uni.getLogoh().getNombre());
                }
                if (uni.getLogov() != null) {
                    dForm.set("nombrelogov", uni.getLogov().getNombre());
                }
                if (uni.getLogos() != null) {
                    dForm.set("nombrelogos", uni.getLogos().getNombre());
                }
                if (uni.getLogot() != null) {
                    dForm.set("nombrelogot", uni.getLogot().getNombre());
                }

                request.setAttribute("mostrarLogos" , "mostrar");
            }

            if(mostrarFotosTipoUA()){
                request.setAttribute("mostrarFotosTipo" , "mostrar");
            }

            request.setAttribute("edificioOptions", uni.getEdificios());
            log.info("edificioOptions ->"+Arrays.toString(uni.getEdificios().toArray()));

            request.setAttribute("unidadesmateriasOptions", uni.getUnidadesMaterias());
            request.setAttribute("usuarioOptions", uni.getUsuarios());
            //request.setAttribute("fichaUAOptions", unidad.getFichasUA());
            TreeMap treesecciones = (TreeMap)uni.getMapSeccionFichasUA();
            TreeMap treeSortSecciones = ordenartreeseccion(treesecciones);
            request.setAttribute("fichaUAOptions", treeSortSecciones);
            
            ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
            List listaProcedimientos;
            
            if (uni.getPadre() == null) // es el govern, ( su padre es nulo)
            {
            	 
            	request.setAttribute("conse",1 );
                listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,1);
            }
            else // la unidad tiene padre distinto de nulo.
            {
            	
            	UnidadAdministrativa elpadre = uni.getPadre();
            	//Listamos los procedimiento asociados a la UA.
            	// vemos si es a nievel conselleria o si es  a nivel interno.
            	if (elpadre.getPadre()==null) //nivel conselleria
            	{
            		request.setAttribute("conse",1 );
            		listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,1);
            	}
            	else
            	{
            		// analizamos el padre del padre para ver si es nivel direccion o servicio
            		UnidadAdministrativa padrepadre = uni.getPadre().getPadre();
            		if (padrepadre.getPadre()==null)
            		{
            			request.setAttribute("conse",2 );
            			listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,2);
            		}
            		else
            		{
            			request.setAttribute("conse",3 );
            			listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,3);
            		}	
             	  
            	}
            }


            request.setAttribute("procedimientoOptions",listaProcedimientos );   
            
        } else {
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }


    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en busqueda");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        Map paramMap = new HashMap();
        Map tradMap = new HashMap();

        Iterator itTrad = ((ArrayList) dForm.get("traducciones")).iterator();
        Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

        // Parámetros generales
        paramMap.put("claveHita", dForm.get("claveHita"));
        paramMap.put("dominio", dForm.get("dominio"));

        if (request.isUserInRole("sacoper")){
            paramMap.put("validacion", "");
        } else {
            paramMap.put("validacion", dForm.get("validacion"));
        }

        paramMap.put("responsable", dForm.get("responsable").toString().toUpperCase());
        paramMap.put("telefono", dForm.get("telefono"));
        paramMap.put("fax", dForm.get("fax"));
        paramMap.put("email", dForm.get("email"));

        if (dForm.get("idPadre") != null) {
            paramMap.put("padre.id", dForm.get("idPadre"));
        }

        Object sexoResponsable = dForm.get("sexoResponsable");
        if (sexoResponsable != null) {
            paramMap.put("sexoResponsable", sexoResponsable);
        }

        // Parámetros traducibles
        tradMap.put("idioma", idiomaDelegate.lenguajePorDefecto());
        while (itLang.hasNext()){
            TraduccionUA tUnidad = (TraduccionUA) itTrad.next();
            int x = 0;
            x += tUnidad.getNombre().length();
            x += tUnidad.getPresentacion().length();
            x += tUnidad.getAbreviatura().length();
            if (x > 0){
                tradMap.put("idioma", itLang.next().toString());
                tradMap.put("nombre", tUnidad.getNombre().toUpperCase());
                tradMap.put("presentacion", tUnidad.getPresentacion().toUpperCase());
                tradMap.put("abreviatura", tUnidad.getAbreviatura().toUpperCase());
                break;
            } else {
                itLang.next();
            }
        }

        List listaUnidades = unidadDelegate.buscarUnidadesAdministrativas(paramMap, tradMap);

        if (listaUnidades.size() == 1) {
            request.setAttribute("idUA", ((UnidadAdministrativa) listaUnidades.get(0)).getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        } else {
            request.setAttribute("listaUAs", listaUnidades);
        }

        return mapping.findForward("lista");
    }

    public ActionForward edificio(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en edificio");
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();

        if (request.getParameter("unidad") != null && request.getParameter("edificio") != null){
            Long idUnidad = new Long(request.getParameter("unidad"));
            Long idEdificio = new Long(request.getParameter("edificio"));

            if ("alta".equals(request.getParameter("operacion")))
                unidadDelegate.anyadirEdificio(idEdificio, idUnidad);
            if ("baja".equals(request.getParameter("operacion")))
                unidadDelegate.eliminarEdificio(idEdificio, idUnidad);

            request.setAttribute("idUA", idUnidad);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
    }

    /*public ActionForward materia(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en materia");
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();

        if (request.getParameter("unidad") != null && request.getParameter("materia") != null){
            Long idUnidad = new Long(request.getParameter("unidad"));
            Long idMateria = new Long(request.getParameter("materia"));

            if ("alta".equals(request.getParameter("operacion")))
                unidadDelegate.anyadirMateria(idMateria, idUnidad);
            if ("baja".equals(request.getParameter("operacion")))
                unidadDelegate.eliminarMateria(idMateria, idUnidad);

            request.setAttribute("idUA", idUnidad);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
    }*/

    public ActionForward usuario(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en usuario");

        if (request.getParameter("unidad") != null && request.getParameter("usuario") != null){
            Long idUnidad = new Long(request.getParameter("unidad"));
            Long idUsuario = new Long(request.getParameter("usuario"));

            UsuarioDelegate delegate = DelegateUtil.getUsuarioDelegate();

            if ("alta".equals(request.getParameter("operacion")))
                delegate.asignarUnidad(idUsuario, idUnidad);

            if ("baja".equals(request.getParameter("operacion")))
                delegate.desasignarUnidad(idUsuario, idUnidad);

            request.setAttribute("idUA", idUnidad);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
    }

    public ActionForward ficha(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en ficha");
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        Long idUA = new Long(request.getParameter("ua"));

        if ("alta".equals(request.getParameter("operacion"))) {

            Long idSeccion = new Long(request.getParameter("seccion"));
            Long idFicha = new Long(request.getParameter("ficha"));
            fichaDelegate.crearFichaUA(idUA, idSeccion, idFicha);

        } else if ("baja".equals(request.getParameter("operacion"))) {

            Long idFichaUA = new Long(request.getParameter("fichaUA"));
            fichaDelegate.borrarFichaUA(idFichaUA);

        } else if ("subir".equals(request.getParameter("operacion"))) {

            Long idFichaUA = new Long(request.getParameter("fichaUA"));
            fichaDelegate.subirFichaUA(idFichaUA);

        } else if ("actualizar_orden".equals(request.getParameter("operacion"))) {

    		Enumeration params = request.getParameterNames();
			Map valores = request.getParameterMap();
            fichaDelegate.actualizarOrdenFichasUA(params, valores );

        }else {
            return mapping.findForward("cancel");
        }

        request.setAttribute("idUA", idUA);
        return dispatchMethod(mapping, form, request, response, "seleccionar");
    }

    public ActionForward procedimiento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
  
			 
			 ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			
			 Long idUnidad = new Long(request.getParameter("ua"));
			
			if ("actualizar_orden".equals(request.getParameter("operacion")))
			{
				HashMap params = new HashMap();
	            Enumeration names = request.getParameterNames();
	            while(names.hasMoreElements())
	            {
	               final String name = (String) names.nextElement();
	               final String vals[] = request.getParameterValues(name);
	               params.put(name, vals);
	            }
	             procedimientoDelegate.actualizarOrdenPros(params);				
	        }else {
	            return mapping.findForward("cancel");
	        }

			 
			    request.setAttribute("idUA", idUnidad);
	            return dispatchMethod(mapping, form, request, response, "seleccionar");
	}
    public ActionForward procedimiento2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
  
			 
			 ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			
			 Long idUnidad = new Long(request.getParameter("ua"));
			
			if ("actualizar_orden".equals(request.getParameter("operacion")))
			{
				HashMap params = new HashMap();
	            Enumeration names = request.getParameterNames();
	            while(names.hasMoreElements())
	            {
	               final String name = (String) names.nextElement();
	               final String vals[] = request.getParameterValues(name);
	               params.put(name, vals);
	            }
	             procedimientoDelegate.actualizarOrdenPros2(params);				
	        }else {
	            return mapping.findForward("cancel");
	        }

			 
			    request.setAttribute("idUA", idUnidad);
	            return dispatchMethod(mapping, form, request, response, "seleccionar");
	}
    public ActionForward procedimiento3(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
  
			 
			 ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			
			 Long idUnidad = new Long(request.getParameter("ua"));
			
			if ("actualizar_orden".equals(request.getParameter("operacion")))
			{
				HashMap params = new HashMap();
	            Enumeration names = request.getParameterNames();
	            while(names.hasMoreElements())
	            {
	               final String name = (String) names.nextElement();
	               final String vals[] = request.getParameterValues(name);
	               params.put(name, vals);
	            }
	             procedimientoDelegate.actualizarOrdenPros3(params);				
	        }else {
	            return mapping.findForward("cancel");
	        }

			 
			    request.setAttribute("idUA", idUnidad);
	            return dispatchMethod(mapping, form, request, response, "seleccionar");
	}
    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        dForm.reset(mapping,request);

        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }
    
    /**
     * Método que comprueba si hay microsites para una Unidad Orgánica
     * @param idua identificador de la unidad organica
     * @return boolean
     */
    private boolean hayMicrositesUA(Long idua){
    	boolean retorno=false;
    	
    	try {
	    	String value = System.getProperty("es.caib.rolsac.microsites");
	    	
	    	if ((value == null) || value.equals("N")) {
	    		retorno=false;
	    	} else {
	            retorno = tieneMicrosites(idua);
	    	}    	
		} catch (Exception e) {
			retorno=true; //para evitar inconsistencias
			log.warn("No se ha podido obtener el listado de microsites. Error=" + e.getMessage());
		}
    	
    	
    	return retorno;
    }


	private boolean tieneMicrosites(Long idua) throws Exception {
		boolean retorno = false;
		MicrositeDelegate micro = org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getMicrositeDelegate();
		List micros = micro.listarMicrosites();
		           
		Iterator iter = micros.iterator();
		while (iter.hasNext()) 
		{
			Microsite mic = (Microsite) iter.next();
			if (mic.getUnidadAdministrativa()==idua.intValue()) {
	        			retorno=true;
				break;
			}
		}
		return retorno;
	}

     /**
     * Método que comprueba si hay que mostrar los logos
     *
     * @return boolean
     */
    private boolean mostrarLogosUA(){
       
        String value = System.getProperty("es.caib.rolsac.logos");
        return !((value == null) || value.equals("N"));
    }

    /**
     * Método que comprueba si hay que mostrar las fotos Tipo
     *
     * @return boolean
     */
    private boolean mostrarFotosTipoUA(){

        String value = System.getProperty("es.caib.rolsac.fotostipo");
        return !((value == null) || value.equals("N"));
    }

}
