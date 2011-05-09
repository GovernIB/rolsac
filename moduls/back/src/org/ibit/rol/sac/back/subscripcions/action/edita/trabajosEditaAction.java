package org.ibit.rol.sac.back.subscripcions.action.edita;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EnvioSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.back.subscripcions.actionform.formulario.trabajoForm;
import org.ibit.rol.sac.back.subscripcions.utils.ClientHttpRequest;
import org.ibit.rol.sac.back.subscripcions.utils.StringUtil;





/**
 * Acción que se ejecuta desde el formulario de detalle
 * 
 * Autor: Fausto Navarro
 * Copyright INDRA 2006
 * 
 */
public class trabajosEditaAction extends BaseAction 
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
	
	protected static Log log = LogFactory.getLog(trabajosEditaAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EnvioSuscripcionDelegate delegate = DelegateUtil.getEnvioSuscripcionDelegate();
		EnvioSuscripcion envio = null;
		UsuarioDelegate usuarioDelegate = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
		trabajoForm f = (trabajoForm) form;
		
		TipoSuscripcion tipo = (TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion");
		
		String pModifica="" + request.getParameter("modifica");
		String pAnyade="" + request.getParameter("anyade");
		
		GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
		
		request.setAttribute("grupos",grupoDelegate.listarCombo(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId()));
		
		
		if ( (!pModifica.equals("null")) || (!pAnyade.equals("null")) ) { 
			
			if (f.get("id") == null) {
				envio = new EnvioSuscripcion();
				//envio.setTipoSuscripcion(tipo);
			} else {  // Es modificacion
				envio = delegate.obtenerEnvio((Long)f.get("id"));
				//************COMPROBACION DE IDES*************
				if (envio.getTipoSuscripcion().getId().longValue()!= tipo.getId().longValue())
				{
					addMessage(request, "peticion.error");
					return mapping.findForward("info");
				}
				
				//*********************************************
			}
			Usuario  usu = (Usuario) request.getSession().getAttribute("MVS_usuario");
			GrupoSuscripcionDelegate delegateG = DelegateUtil.getGrupoSuscripcionDelegate();
			delegateG.init(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId());
			
			String[] grupos = (String[])f.get("seleccionados");
			
			HashSet gEnvio = new HashSet();
			for(int i=0 ; i<grupos.length; i++)
			{
				if(grupos[i].equals("-1")) continue;
				Long idG = Long.valueOf(grupos[i]);
				GrupoSuscripcion g = new GrupoSuscripcion();
				g.setId(Long.valueOf(grupos[i]));
				gEnvio.add(g);
			}
			envio.setGrupos(gEnvio);
			
			
			
			envio.setTipoSuscripcion(tipo);//getTipoSuscripcion().setId(tipo.getId());
			envio.setFechaEnvio(f.getFechaEnvio());
			envio.setAsunto((String) f.get("asunto"));
			String estado = (String) f.get("estado");
			if(estado.equals(EnvioSuscripcion.PENDIENTE) && !estado.equals(envio.getEstado()))
			{
				if(envio.getGrupos().size() == 0)
				{
					addMessage(request, "trabajo.nogrupos");
					addMessage(request, "mensa.editartrabajo", "" + envio.getId().longValue());
					return mapping.findForward("info");
				}
				
			}
			
			envio.setEstado(estado);
			//envio.setContenidoHtml((String) f.get("contenidoHtml"));
			String titulo = (String) f.get("titulo");
			
			envio.setTitulo(titulo);
			
			
			/*
			 envio.setContenidoHtml(getContenido((Long) f.get("idSeccion"),envio.getAsunto(),servidor + rb.getString("url.boletin")));
			 envio.setSeccion((Long) f.get("idSeccion"));
			 */
			
			if(estado.equals(EnvioSuscripcion.GENERACION))
			{
				ResourceBundle rb =	ResourceBundle.getBundle("sac-subscripcions-messages");
				
				String servidor = "";
				String servidorPuerto = System.getProperty("es.indra.caib.rolsac.servidorPuerto");
				if ((servidorPuerto != null) && !servidorPuerto.equals(""))
				{
					servidor = "http://"+ servidorPuerto;
				}
				else
				{
					String value = System.getProperty("es.indra.caib.rolsac.oficina");
					if ((value == null) || value.equals("N"))
						servidor = "http://"+ Suscripcionback.RESOURCE_HOST;
					else            	
						servidor = "http://"+request.getServerName()+":"+request.getServerPort();           	
				}
				
				String path = servidor + rb.getString("url.boletin");
				path = path + "?coduo=" + tipo.getUnidadAdministrativa().toString();
				
				String contenidoHtml = getContenido(path);
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
			
			//log.info("Creado/Actualizado " + age.getId());
			
			if(request.getParameter("anyade")!=null) 
				addMessage(request, "mensa.nuevotrabajo");
			if(request.getParameter("modifica")!=null)	
				addMessage(request, "mensa.modiftrabajo");	
			
			addMessage(request, "mensa.editartrabajo", "" + envio.getId().longValue());
			addMessage(request, "mensa.listatrabajo");
			
			return mapping.findForward("info");
			
		}
		
		//********************************************************
		//*********** BORRAMOS LINEAS DEL FORMULARIO *************
		//********************************************************
		if(request.getParameter("borrar")!=null) {
//			String[] grupos = getGrupos((String[])f.get("seleccionados"));
			String[] grupos = (String[])f.get("seleccionados");
			delegate.eliminarGrupos(grupos , (Long)f.get("id"));
			
			addMessage(request, "mensa.listatrabajogruposborrados");
			addMessage(request, "mensa.listatrabajos");
			addMessage(request, "mensa.editartrabajo", ""+f.get("id"));
			
			return mapping.findForward("info");             
		}
		//********************************************************
		//*********** AÑADIMOS TODS LOS GRUPOS AL ENVIO**********
		//********************************************************
		
		if(request.getParameter("AnyadeGrupos")!=null) {
			
			GrupoSuscripcionDelegate delegateG = DelegateUtil.getGrupoSuscripcionDelegate();
			delegateG.init(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId());
			
			// No liste solo 10, sino todos
			delegateG.setTampagina(9999);
			List lista=delegateG.listarGrupos();
			delegate.anyadirAllGrupos(lista, (Long)f.get("id"));
			
			addMessage(request, "mensa.listatrabajogruposanyadidos");
			addMessage(request, "mensa.listatrabajos");
			addMessage(request, "mensa.editartrabajo", ""+f.get("id"));
			
			return mapping.findForward("info");             
		}
		
		
		//********************************************************
		//********************** EDITAMOS ************************
		//********************************************************
		if (request.getParameter("id")!=null) {     
			Long id = new Long(""+request.getParameter("id"));
			
			if (id != null){
				
				if (delegate.checkTipo(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId(),id)) {
					addMessage(request, "info.seguridad");
					return mapping.findForward("info");
				}
				
				envio = delegate.obtenerEnvio(id);
				//************COMPROBACION DE IDES*************
				if (envio.getTipoSuscripcion().getId().longValue()!=((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId().longValue())
				{
					addMessage(request, "peticion.error");
					return mapping.findForward("info");
				}
				
				//*********************************************
				trabajoForm fdet=(trabajoForm) form;
				
				fdet.set("id", id);
				fdet.setFechaEnvio(envio.getFechaEnvio());
				fdet.setFechaEnviado(envio.getFechaEnviado());
				fdet.setFechaAlta(envio.getFechaAlta());
				fdet.setFechaModificacion(envio.getFechaModificacion());
				fdet.setFechaBaja(envio.getFechaBaja());
				fdet.set("estado",envio.getEstado());
				fdet.set("asunto",envio.getAsunto());
				fdet.set("titulo",envio.getTitulo());
				fdet.set("contenidoHtml",envio.getContenidoHtml());
				if(envio.getFechaBaja() == null)
					fdet.set("activo" , "S");
				else
					fdet.set("activo" , "N");
				
				/*
				 
				 SeccionDelegate seccionDelegate = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getSeccionDelegate();
				 Seccion seccion  = seccionDelegate.obtenerSeccion(envio.getSeccion());
				 TraduccionSeccion tradSec = (TraduccionSeccion) seccion.getTraduccion();
				 fdet.set("idSeccion",envio.getSeccion());
				 fdet.set("seccion",tradSec.getNombre());
				 */
				
				
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

				
				
			} else {
				addMessage(request, "info.noposible");
				return mapping.findForward("info");
			}
			
			
			
			request.setAttribute("canales",Suscripcionback.CANALES);
			request.setAttribute("estados",Suscripcionback.ESTADOS_ENVIO);
			request.setAttribute("tipos",Suscripcionback.TIPO_ENVIO);
			
			return mapping.findForward("detalle");
			
		}
		
		addMessage(request, "peticion.error");
		return mapping.findForward("info");
	}
	
	private String getContenido(String path) throws MalformedURLException, IOException
	{
		ClientHttpRequest client = new ClientHttpRequest(new URL(path));
		//   	client.setParameter("coduo", coduo);   Parece que no se pasan correctamente los parametros y los incluimos en la URL
		InputStream is = client.post();
		return copyInputStream(is);
	}
	
	
	
	
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
	}
	

}

