package org.ibit.rol.sac.back.subscripcions.action.edita;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.subscripcions.actionform.formulario.suscriptorForm;
import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.model.SuscriptorClave;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.SuscriptorClaveDelegate;
import org.ibit.rol.sac.persistence.delegate.SuscriptorDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;



/**
 * Acción que se ejecuta desde el lario de detalle
 * 
 * Autor: Fausto Navarro
 * Copyright INDRA 2006
 * 
 */
public class suscriptoresEditaAction extends BaseAction 
{
    /**
     * This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @return 
     */
	
	protected static Log log = LogFactory.getLog(gruposEditaAction.class);
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	SuscriptorDelegate delegate = DelegateUtil.getSuscriptorDelegate();
    	Suscriptor suscriptor = null;
    	UsuarioDelegate usuarioDelegate = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
    	suscriptorForm f = (suscriptorForm) form;

    	
    	TipoSuscripcion tipo = (TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion");
    
    	String pModifica="" + request.getParameter("modifica");
    	String pAnyade="" + request.getParameter("anyade");
    	
    	if ( (!pModifica.equals("null")) || (!pAnyade.equals("null")) ) { 

        	if (f.get("id") == null) {
        		suscriptor = new Suscriptor();
        		suscriptor.setTipoSuscripcion(tipo);
        		
        		//comprobamos si existe ya en la BD
        		boolean res = delegate.existeSuscriptor((String) f.get("email"),tipo.getId());
        		if (res){
        	       	addMessage(request, "error.suscriptor.existe");
        	       	return mapping.findForward("info");
        		}
        		
            } else {  // Es modificacion
            	suscriptor = delegate.obtenerSuscriptor((Long)f.get("id"));
            	//************COMPROBACION DE IDES*************
            	/*if (grupo.getTipoSuscripcion().getId().longValue()!= tipo.getId().longValue())
            	{
            		addMessage(request, "peticion.error");
                    return mapping.findForward("info");
            	}
            	request.setAttribute("identificador",((TraduccionGrupoSuscripcion)grupo.getTraduccion()).getNombre());
            	*/
            	//*********************************************
            }
   		//Usuario  usu = (Usuario) request.getSession().getAttribute("MVS_usuario");
        suscriptor.setTipoSuscripcion(tipo);//getTipoSuscripcion().setId(tipo.getId());
        //        suscriptor.setIdentificador((String) f.get("identificador"));
        
        suscriptor.setApellido1((String) f.get("apellido1"));
        suscriptor.setApellido2((String) f.get("apellido2"));
        suscriptor.setNombre((String) f.get("nombre"));                
        suscriptor.setSms((String) f.get("sms"));
        String g =(String) f.get("telefono");
        suscriptor.setTelefono(g);
        suscriptor.setTipo((String) f.get("tipo"));
        suscriptor.setNombreEntidad((String) f.get("nombreEntidad"));
        suscriptor.setNombreArea((String) f.get("nombreArea"));
        suscriptor.setDepartamentoEntidad((String) f.get("departamentoEntidad"));
        suscriptor.setCargo((String) f.get("cargo"));
        suscriptor.setBoletin((String) f.get("boletin"));
        suscriptor.setAlertas((String) f.get("alertas"));
        suscriptor.setEstudios((String) f.get("estudios"));

        if (!pAnyade.equals("null"))
        {
        	suscriptor.setEmail((String) f.get("email"));
        	Usuario  usu = (Usuario) request.getSession().getAttribute("MVS_usuario");
        	suscriptor.setTipoSuscripcion(tipo);
        	suscriptor.setUsuarioAlta(usu.getId());
        	suscriptor.setFechaAlta(new Date());
        	suscriptor.setOrigen("M");
        }
        // Establezco el objeto Grupo
        GrupoSuscripcion grupo=null;
        GrupoSuscripcionDelegate bdGrupo = DelegateUtil.getGrupoSuscripcionDelegate();
        if (f.get("id") == null)  	grupo = bdGrupo.obtenerGrupo((Long)f.get("grupo"));
        else 			        	grupo = suscriptor.getGrupo();
        grupo.setId((Long)f.get("grupo"));
        suscriptor.setGrupo(grupo);
        
        String antes;
        
        if (suscriptor.getEstado() == null)
        	antes = new String("B"); // Para que no trate la baja al ser nuevo
        else
        	antes = new String(suscriptor.getEstado());
        
        String despues = new String((String) f.get("estado"));
        
        if ( !antes.equals("B") && despues.equals("B")) 
        {
        		SuscriptorClaveDelegate bdsuscriptorClave = DelegateUtil.getSuscriptorClaveDelegate();
        		suscriptor.setFechaBaja(new Date());
                String mail = suscriptor.getEmail();
           		bdsuscriptorClave.borrarSuscriptorClave(bdsuscriptorClave.recuperarSuscriptorClave(tipo.getId(), mail));
        }
        
        	
        suscriptor.setEstado((String) f.get("estado"));

        
        delegate.grabarSuscriptor(suscriptor);	
        
        if (!pAnyade.equals("null"))
        {
        	
        	// Incluimos el registro de la clave
        	
        	SuscriptorClaveDelegate delegateClave = DelegateUtil.getSuscriptorClaveDelegate();
        	SuscriptorClave suscriptorClave = new SuscriptorClave();
        	suscriptorClave.setTipoSuscripcion(tipo);
        	suscriptorClave.setEmail(suscriptor.getEmail());
        	delegateClave.grabarSuscriptorClave(suscriptorClave);
        
        }
       	//grupo.setFecgrupopo(f.getFechaGrupo());
       	//grupo.setAsunto((String) f.get("asunto"));
       	//String estado = (String) f.get("estado");
       	/*if(estado.equals(EnvioSuscripcion.PENDIENTE) && !estado.equals(envio.getEstado()))
       	{
       		if(envio.getGrupos().size() == 0)
       		{
       			addMessage(request, "grupo.nogrupos");
       	   		addMessage(request, "mensa.editargrupo", "" + envio.getId().longValue());
       			return mapping.findForward("info");
       		}

       	}*/
       	
       	//envio.setEstado(estado);
       	////envio.setContenidoHtml((String) f.get("contenidoHtml"));
       	//String titulo = (String) f.get("titulo");
       	
       	//envio.setTitulo(titulo);
       	
       	
       	/*
       	envio.setContenidoHtml(getContenido((Long) f.get("idSeccion"),envio.getAsunto(),servidor + rb.getString("url.boletin")));
       	envio.setSeccion((Long) f.get("idSeccion"));
       	*/
       	/*
       	if(estado.equals(EnvioSuscripcion.GENERACION))
       	{
           	ResourceBundle rb =	ResourceBundle.getBundle("sac-subscripcions-messages");
           	
           	String servidor ="http://"+request.getServerName()+":"+request.getServerPort();
       		String contenidoHtml = getContenido(new Long(1),envio.getAsunto(),servidor + rb.getString("url.boletin"));
       		contenidoHtml = StringUtil.replace(contenidoHtml,"[#TITULO_BOLETIN#]",titulo);
       		contenidoHtml = StringUtil.replace(contenidoHtml,"[#URL_LOGO#]",tipo.getUrlLogo());
       		contenidoHtml = StringUtil.replace(contenidoHtml,"[#TITULO_SUSCRIPCION#]",tipo.getTitulo());
       		
       		UnidadAdministrativaDelegate uaDelegate = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate();
       		UnidadAdministrativa ua = uaDelegate.obtenerUnidadAdministrativa(tipo.getUnidadAdministrativa());
       		TraduccionUA tradUA = (TraduccionUA) ua.getTraduccion();
       		contenidoHtml = StringUtil.replace(contenidoHtml,"[#UA#]",tradUA.getNombre());
       		envio.setContenidoHtml(contenidoHtml);
       	}
       	
       	envio.setCanalEnvio((String) f.get("canalEnvio"));
       	envio.setTipo((String) f.get("tipo"));
    	if(request.getParameter("anyade")!=null) {
    		envio.setFechaAlta(new Date());
    		envio.setUsuarioAlta(usu.getId());
    	}
    	if(request.getParameter("modifica")!=null) {
    		envio.setFechaModificacion(new Date());
    		envio.setUsuarioModificacion(usu.getId());
    	}
    	String activo = (String)f.get("activo");
    	if(activo.equals("N"))
    		envio.setFechaBaja(new Date());
    	else
    		envio.setFechaBaja(null);

       	delegate.grabarEnvio(envio);

       	//log.debug("Creado/Actualizado " + age.getId());
      	*/
       	if(request.getParameter("anyade")!=null) 
       		addMessage(request, "mensa.nuevosuscriptor");
       	if(request.getParameter("modifica")!=null)	
       		addMessage(request, "mensa.modifsuscriptor");	
       
   		addMessage(request, "mensa.editarsuscriptor", "" + suscriptor.getId().longValue());
       	addMessage(request, "mensa.listasuscriptor");
       	
       	return mapping.findForward("info");
               
       }
    	
        //********************************************************
        //********************** EDITAMOS ************************
        //********************************************************
        if (request.getParameter("id")!=null) {     
            Long id = new Long(""+request.getParameter("id"));

            if (id != null){

                /*if (delegate.checkTipo(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId(),id)) {
                	addMessage(request, "info.seguridad");
                	return mapping.findForward("info");
                }
            	*/
            	suscriptor = delegate.obtenerSuscriptor(id);
            	//request.setAttribute("identificador",((TraduccionGrupoSuscripcion)grupo.getTraduccion()).getNombre());
            	//************COMPROBACION DE IDES*************
            	if (suscriptor.getTipoSuscripcion().getId().longValue()!=((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId().longValue())
            	{
            		addMessage(request, "peticion.error");
                    return mapping.findForward("info");
            	}
            	
            	//*********************************************
      	 //f.set("identificador", (String)grupo.getIdentificador());
            	suscriptorForm fdet=(suscriptorForm) form;
                
                fdet.set("id", id);
            	
                fdet.setFechaAlta(suscriptor.getFechaAlta());
                fdet.setFechaModificacion(suscriptor.getFechaModificacion());
                fdet.setFechaBaja(suscriptor.getFechaBaja());
                fdet.set("apellido1",suscriptor.getApellido1());
                fdet.set("apellido2",suscriptor.getApellido2());
                fdet.set("nombre",suscriptor.getNombre());                
                fdet.set("email",suscriptor.getEmail());                
                fdet.set("usuarioAlta",suscriptor.getUsuarioAlta());                
                fdet.set("sms",suscriptor.getSms());
                fdet.set("telefono",suscriptor.getTelefono());
                fdet.set("tipo",suscriptor.getTipo());
                fdet.set("nombreEntidad",suscriptor.getNombreEntidad());
                fdet.set("nombreArea",suscriptor.getNombreArea());
                fdet.set("departamentoEntidad",suscriptor.getDepartamentoEntidad());
                fdet.set("cargo",suscriptor.getCargo());
                fdet.set("boletin",suscriptor.getBoletin());
                fdet.set("alertas",suscriptor.getAlertas());
                fdet.set("estudios",suscriptor.getEstudios());
                fdet.set("estado",suscriptor.getEstado());
                if ("M".equals(suscriptor.getOrigen()))
                	fdet.set("origen","Manual");
                else
                	fdet.set("origen","Telemàtic");
                fdet.set("referenciaTramite",suscriptor.getReferenciaTramite());        	    
                fdet.set("grupo",suscriptor.getGrupo().getId());
                
            	GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
        		request.setAttribute("grupos",grupoDelegate.listarCombo(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId()));
                
                                
                /*
                
                
                fdet.set("titulo",envio.getTitulo());
                fdet.set("contenidoHtml",envio.getContenidoHtml());
                if(envio.getFechaBaja() == null)
                	fdet.set("activo" , "S");
                else
                	fdet.set("activo" , "N");

                
                
                //SeccionDelegate seccionDelegate = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getSeccionDelegate();
                //Seccion seccion  = seccionDelegate.obtenerSeccion(envio.getSeccion());
                //TraduccionSeccion tradSec = (TraduccionSeccion) seccion.getTraduccion();
                //fdet.set("idSeccion",envio.getSeccion());
                //fdet.set("seccion",tradSec.getNombre());
                
                
                
                fdet.set("idSeccion",new Long(1));
                fdet.set("seccion","DESHABILITADO MANTENIMIENTO");
                
                fdet.set("canalEnvio",envio.getCanalEnvio());
                fdet.set("tipo",envio.getTipo());
                
                if((envio.getUsuarioAlta() != null) && envio.getUsuarioAlta().intValue() != 0)
                {
                    Usuario usuAlta = usuarioDelegate.obtenerUsuario(envio.getUsuarioAlta());
                    fdet.set("usuarioAlta",usuAlta.getNombre());
                }
                else
                {
                	fdet.set("usuarioAlta","");
                }

                if((envio.getUsuarioModificacion() != null) && envio.getUsuarioModificacion().intValue() != 0)
                {
                    Usuario usuModif = usuarioDelegate.obtenerUsuario(envio.getUsuarioModificacion());
                    fdet.set("usuarioModificacion",usuModif.getNombre());
                }
                else
                {
                	fdet.set("usuarioModificacion","");
                }


                //VOUtils.describe(fdet, age);  // bean --> form

            	Iterator it = envio.getGrupos().iterator();	
            	ArrayList grupos= new ArrayList();
            	while (it.hasNext()) {
            		grupos.add((GrupoSuscripcion)it.next());
            	}
                fdet.set("lineasgrupo",grupos);
                
           */ 

                
             request.setAttribute("estados",Suscripcionback.ESTADOS_SUSCRIPCION);
             request.setAttribute("tipos",Suscripcionback.TIPO_ENTIDAD);
           	 return mapping.findForward("detalle");


} else {
            	addMessage(request, "info.noposible");
            	return mapping.findForward("info");
            }
            
        
            
/*			request.setAttribute("canales",Suscripcionback.CANALES);
			request.setAttribute("estados",Suscripcionback.ESTADOS_SUSCRIPCION);
			request.setAttribute("tipos",Suscripcionback.TIPO_ENVIO);
		
            return mapping.findForward("detalle");

        }

        addMessage(request, "peticion.error");
        return mapping.findForward("info");
    }
   */ 
    /*private String getContenido(Long idSeccion, String titulo, String path) throws MalformedURLException, IOException
    {
    	ClientHttpRequest client = new ClientHttpRequest(new URL(path));
    	client.setParameter("idSeccion", idSeccion);
    	client.setParameter("titulo", titulo);
    	InputStream is = client.post();
    	return copyInputStream(is);
    }
    */
    

/*
	private String copyInputStream(InputStream in)
	  throws IOException
	  {
	    byte[] buffer = new byte[1024];
	    int len;

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    while((len = in.read(buffer)) >= 0)
	    {
	      baos.write(buffer, 0, len);
	    }
	    
	    String cadena = new String(baos.toByteArray());
	    //System.out.println("Cadena:" + cadena);
	    baos.close();

	    in.close();
	    return cadena;
	  }*/
}
        return mapping.findForward("info");
        }
    
}


