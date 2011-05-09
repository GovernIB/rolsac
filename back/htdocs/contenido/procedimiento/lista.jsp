<%@ page import="org.ibit.rol.sac.model.Procedimiento" %>
<%@ page import="org.ibit.rol.sac.model.ProcedimientoRemoto" %>
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<% String context=request.getContextPath();%>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="procedimiento.lista" /></h1>
    <h2><bean:message key="procedimiento.datos" /></h2>
</div>
<br />

<logic:empty name="listaProcedimientos">
    <div class="bloc">
        <br /><h2><bean:message key="procedimiento.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaProcedimientos">
    <div class="bloc">
        <logic:iterate id="procedimiento" name="listaProcedimientos">
            <% if (procedimiento instanceof ProcedimientoRemoto) {%>
                <div class="component">
                    <div class="textconsulta1">
                    
  	                    <logic:equal name="procedimiento" property="validacion" value="1">
                             <logic:present name="procedimiento" property="fechaCaducidad">
			                	<bean:define id="caduca" name="procedimiento" property="fechaCaducidad" type="java.util.Date"/>
			                	<%	if ((new java.util.Date().getTime() > caduca.getTime())) { %>
			                		<img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>"/>&nbsp;
			                	<% } else { %>
			                		<img src="<%=context%>/img/fichaP.gif" alt="<bean:message key="validacion.publica"/>"/>&nbsp;		                            		
								<% } %>
							</logic:present>
							<logic:notPresent name="procedimiento" property="fechaCaducidad">
								<img src="<%=context%>/img/fichaP.gif" alt="<bean:message key="validacion.publica"/>"/>&nbsp;		
							</logic:notPresent>
                        </logic:equal>
	                    
	                    <logic:notEqual name="procedimiento" property="validacion" value="1">
                            <img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>"/>&nbsp;
                        </logic:notEqual>
                    
                        <bean:write name="procedimiento" property="traduccion.nombre" />&nbsp;(<bean:message key="procedimiento.id" />:<bean:write name="procedimiento" property="id" />)&nbsp;<b><bean:message key="procedimiento.remoto"/></b>
                    </div>
                    <div class="botoneraconsulta1">
                        <html:form action="/contenido/procedimiento/seleccionar" styleId="procedimientoForm">
                            <input type="hidden" name="idSelect" value='<bean:write name="procedimiento" property="id" />' />
                            <html:submit property="action">
                                <bean:message key="boton.seleccionar" />
                            </html:submit>
                        </html:form>
                    </div>
                </div>
            <% }else {%>
                <div class="component">
                <div class="textconsulta1">
                
                    <logic:equal name="procedimiento" property="validacion" value="1">
                            <logic:present name="procedimiento" property="fechaCaducidad">
		                	<bean:define id="caduca" name="procedimiento" property="fechaCaducidad" type="java.util.Date"/>
		                	<%	if ((new java.util.Date().getTime() > caduca.getTime())) { %>
		                		<img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>"/>&nbsp;
		                	<% } else { %>
		                		<img src="<%=context%>/img/fichaP.gif" alt="<bean:message key="validacion.publica"/>"/>&nbsp;		                            		
							<% } %>
						</logic:present>
						<logic:notPresent name="procedimiento" property="fechaCaducidad">
							<img src="<%=context%>/img/fichaP.gif" alt="<bean:message key="validacion.publica"/>"/>&nbsp;		
						</logic:notPresent>
                    </logic:equal>
                    
                    <logic:notEqual name="procedimiento" property="validacion" value="1">
	                    <img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>"/>&nbsp;
                    </logic:notEqual>

                    <logic:notEmpty name="procedimiento" property="traduccion">
                        <bean:write name="procedimiento" property="traduccion.nombre" />&nbsp;(<bean:message key="procedimiento.id" />:<bean:write name="procedimiento" property="id" />)
                    </logic:notEmpty>
                    <logic:empty name="procedimiento" property="traduccion">
                        <bean:write name="procedimiento" property="id" /> - <bean:message key="ua.procedimiento.noTraduccion" />
                    </logic:empty>


                    <%--<bean:write name="procedimiento" property="traduccion.nombre" />&nbsp;(<bean:message key="procedimiento.id" />:<bean:write name="procedimiento" property="id" />)--%>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/procedimiento/seleccionar" styleId="procedimientoForm">
                        <input type="hidden" name="idSelect" value='<bean:write name="procedimiento" property="id" />' />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
            <% } %>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />
<center>
    [<html:link page="/contenido/procedimiento/form.do" ><bean:message key="boton.volver" /></html:link>]
</center>
