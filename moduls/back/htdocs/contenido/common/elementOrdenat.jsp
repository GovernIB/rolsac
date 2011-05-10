	<%@ page language="java" %>
	<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
	<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
	<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
	<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
	<html:xhtml/>


	<div class="bloc">
			<div class="titolbloc">
				<bean:message key="<%= request.getParameter("titol") %>" />
			</div>
			<logic:empty name="<%= request.getParameter("options") %>">
				<br /><h2><bean:message key="<%= request.getParameter("empty") %>" /></h2><br />
			</logic:empty>
			<logic:notEmpty name="<%= request.getParameter("options") %>">
				<html:form action="<%= request.getParameter("action") %>" >        
				<logic:iterate id="element" name="<%= request.getParameter("options") %>">
				<logic:notEmpty name="element">
				<div class="component">
					<div class="textconsulta2">
						 <table> 
							 <tr><td>
								<input type="text" name="orden_doc<bean:write name="element" property="id" />" size="4" maxlength="4" value="<bean:write name="element" property="orden" />" />
							 </td>
							 <td>   
								<bean:write name="element" property="<%= request.getParameter("campTitol") %>" />
							 </td></tr>
						 </table> 
					</div>
					<div class="botoneraconsulta2">
						<!--  la url aqui nomes es fa servir per apuntar a la clase Action, pero qui diu que s'executa es el param 'action'   -->
						<logic:present parameter="paramsNouElement">
							<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="/sacback<%= request.getParameter("urlBotonera") %>?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="element" property="id" />&<%= request.getParameter("containerFormId") %>=<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" /><%= request.getParameter("paramsNouElement") %>&titol=<%= request.getParameter("modifElement") %>";' />                	
							<input type="button" value="<bean:message key="boton.eliminar" />"    name="<bean:message key="boton.eliminar" />"    onclick='document.location.href="/sacback<%= request.getParameter("urlBotonera") %>?action=<bean:message key="boton.eliminar" />&idSelect=<bean:write name="element" property="id" />&<%= request.getParameter("containerFormId") %>=<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" /><%= request.getParameter("paramsNouElement") %>";' />
						</logic:present>
						<logic:notPresent parameter="paramsNouElement">
							<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="/sacback<%= request.getParameter("urlBotonera") %>?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="element" property="id" />&<%= request.getParameter("containerFormId") %>=<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" />&titol=<%= request.getParameter("modifElement") %>";' />                	
							<input type="button" value="<bean:message key="boton.eliminar" />"    name="<bean:message key="boton.eliminar" />"    onclick='document.location.href="/sacback<%= request.getParameter("urlBotonera") %>?action=<bean:message key="boton.eliminar" />&idSelect=<bean:write name="element" property="id" />&<%= request.getParameter("containerFormId") %>=<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" />";' />                	
						</logic:notPresent>
						                	
					</div>
				</div>
				</logic:notEmpty>
				</logic:iterate> 
				<input type="hidden" name="<%= request.getParameter("containerFormId") %>" value='<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" />' />
				<input type="hidden" name="action" value='<bean:message key="<%= request.getParameter("relacioElements") %>" />' />
				
				<input type="hidden" name="operacion" value='actualizar_orden' />               
				<html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
				</html:form>             
			</logic:notEmpty>
		</div><br />
		<!-- Alta document -->
		<center>  
		<logic:present parameter="paramsNouElement">
				[<a href="/sacback/contenido/<%= request.getParameter("formName") %>/form.do?<%= request.getParameter("containerFormId") %>=<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" /><%= request.getParameter("paramsNouElement") %>&titol=<%= request.getParameter("altaElement") %>" ><bean:message key="<%= request.getParameter("texteNouElement") %>" /></a>]    
		</logic:present>
		<logic:notPresent parameter="paramsNouElement">
				[<a href="/sacback/contenido/<%= request.getParameter("formName") %>/form.do?<%= request.getParameter("containerFormId") %>=<bean:write name="<%= request.getParameter("containerFormName") %>" property="id" />&titol=<%= request.getParameter("altaElement") %>" ><bean:message key="<%= request.getParameter("texteNouElement") %>" /></a>]    
		</logic:notPresent>
		
		</center>

