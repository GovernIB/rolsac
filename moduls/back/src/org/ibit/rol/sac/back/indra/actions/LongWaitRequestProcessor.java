package org.ibit.rol.sac.back.indra.actions;

import java.util.Enumeration;

import org.apache.struts.action.*;

public class LongWaitRequestProcessor extends BackRequestProcessor {
	
	protected boolean processValidate(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response,
			ActionForm form,
			ActionMapping mapping)
	throws java.io.IOException, javax.servlet.ServletException {
		
		
		if (request.getParameter("espera") == null) {
			return super.processValidate(request, response, form, mapping);
		} else if (request.getParameter("espera").equals("si")) {
			
			String htmlCancel = ""+ request.getParameter("org.apache.struts.taglib.html.CANCEL");
			//en el caso que pulsen cancelar y no espera=si no interesa que se active la session
			if (!htmlCancel.equals("null")) {
				return super.processValidate(request, response, form, mapping);
			}else{
				
				javax.servlet.http.HttpSession session = request.getSession(true);
				
				if (session.getAttribute("action_path_key") == null) {
					return super.processValidate(request,response, form, mapping);
				} else {
					// Retrieve the saved form, it's not null, that means the previous request was
					// sent via POST and we'll need this form because the current one has had
					// all its values reset to null
					ActionForm nonResetForm = (ActionForm)session.getAttribute("current_form_key");
					if (nonResetForm != null) {
						return super.processValidate(request, response, nonResetForm, mapping);
					} else {
						return super.processValidate(request, response, form, mapping);
					}
				}
			}
		}else {
			
			javax.servlet.http.HttpSession session = request.getSession(true);
			ActionForm nonResetForm = (ActionForm)session.getAttribute("current_form_key");
			
			if (nonResetForm != null) {
				String mappingName = mapping.getName(); 
				return super.processValidate(request, response, nonResetForm, mapping);
			} else {
				return super.processValidate(request, response, form, mapping);
			}
	
		}
	}
	
	protected ActionForward processActionPerform(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response,
			Action action,
			ActionForm form,
			ActionMapping mapping) throws
			java.io.IOException, javax.servlet.ServletException {
		
		
		if (request.getParameter("espera") == null) {
			return super.processActionPerform(request, response, action, form, mapping);

		} else if (request.getParameter("espera").equals("si") ) {	
			
			String htmlCancel = ""+ request.getParameter("org.apache.struts.taglib.html.CANCEL");
			//en el caso que pulsen cancelar y no espera=si no interesa que se active la session
			if (!htmlCancel.equals("null")) {
				return super.processActionPerform(request, response, action, form, mapping);
			}else{

				javax.servlet.http.HttpSession session = request.getSession(true);
				if (session.getAttribute("action_path_key") == null) {
							
					ForwardActionBean b = new ForwardActionBean();

					String parametros = "";
					
					Enumeration enumera = request.getParameterNames();
					boolean mas = enumera.hasMoreElements();
					
					String requestUri = request.getRequestURI();
					if	((requestUri.indexOf("procedimiento") != -1
							|| requestUri.indexOf("ficha")!= -1 
							|| requestUri.indexOf("normativa")!= -1
							|| requestUri.indexOf("tramite")!= -1) && requestUri.indexOf("editar.do") != -1){
					
						if (mas) parametros="?";
						while (mas){
							String name = (String)enumera.nextElement();
							String valor = request.getParameter(name);
									
							if (name.equals("action") || name.equals("idSeccion") ||name.equals("id") || name.equals("espera") ||
							name.equals("idProcedimiento")) 
							{
										
								if (name.equals("espera")) parametros+=name+"="+"no"; 
								else parametros+=name+"="+valor;
										
								if (mas) parametros+="&";
							}
							mas = enumera.hasMoreElements();
						}
						parametros = parametros.substring(0, parametros.length()-1);
												
					} else {
							
							if (mas) parametros="?";
							while (mas)	{
								String name = (String)enumera.nextElement();
								String valor = request.getParameter(name);
								parametros+=name+"="+valor;
								mas = enumera.hasMoreElements();
								if (mas) parametros+="&";
							}
					}	
					
					b.setActionPath(request.getRequestURI()+parametros);
					session.setAttribute("action_path_key", b);

					if (request.getMethod().equalsIgnoreCase("POST")) {
							session.setAttribute("current_form_key",form);
							
							//Si existe el objeto traductor en contexto y no está como atributo de sesión lo cargamos
							if (getServletContext().getAttribute("traductor") != null 
									& session.getAttribute("traductor") == null)
									session.setAttribute("traductor", getServletContext().getAttribute("traductor"));
					}
					
					return mapping.findForward("pleaseWait");
		
				} 
			}
		} else  {
			javax.servlet.http.HttpSession session = request.getSession(true);
			
			ActionForm nonResetForm = (ActionForm)session.getAttribute("current_form_key");
			
			if (nonResetForm != null) {
				session.setAttribute("current_form_key", null);
				return super.processActionPerform(request, response, action, nonResetForm, mapping);
			} else {
				return super.processActionPerform(request, response, action, form, mapping);
			}
		}

		return super.processActionPerform(request, response, action, form, mapping);
		
		}
		
	}
