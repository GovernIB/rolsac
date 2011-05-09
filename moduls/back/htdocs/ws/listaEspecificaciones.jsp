<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>

<div class="bloc">
    <h1><bean:message key="especificacion.lista" /></h1>
</div>

<br />

<html:messages id="message" header="messages.header" footer="messages.footer" message="true">
  <li><bean:write name="message"/></li>
</html:messages>

<logic:empty name="especificaciones">
    <div class="bloc">
        <br /><h2><bean:message key="especificacion.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="especificaciones">
    <div class="bloc">
        <logic:iterate id="espec" name="especificaciones" type="org.ibit.rol.sac.integracion.uddi.Especificacion">
        <div class="component">
            <div class="textconsulta2">
                <bean:write name="espec" property="nombre" />
            </div>
            <div class="botoneraconsulta2">
                <html:form action="/ws/especificacion/borrar" styleId="especificacionForm">
                    <html:hidden property="clave" value="<%=espec.getClave()%>" />
                    <html:submit><bean:message key="boton.eliminar" /></html:submit>
                </html:form>
                <html:form action="/ws/especificacion/mostrar" styleId="especificacionForm" method="get">
                    <html:hidden property="clave" value="<%=espec.getClave()%>" />
                    <html:submit><bean:message key="boton.seleccionar" /></html:submit>
                </html:form>
            </div>
        </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<br />

<center>
[<html:link action="/ws/especificacion/editar" ><bean:message key="boton.nuevo" /></html:link>]
</center>

