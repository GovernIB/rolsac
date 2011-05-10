<%@ page import="java.util.Arrays,
                 java.lang.reflect.Array"%>
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="organismo.lista" /></h1>
</div>
<br />

<html:messages id="message" header="messages.header" footer="messages.footer" message="true">
  <li><bean:write name="message"/></li>
</html:messages>

<logic:empty name="organismos">
    <div class="bloc">
        <br /><h2><bean:message key="organismo.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="organismos">
    <div class="bloc">
        <logic:iterate id="organ" name="organismos" type="org.ibit.rol.sac.integracion.uddi.Organismo">
        <div class="component">
            <div class="textconsulta2">
                <bean:write name="organ" property="nombre" />
            </div>
            <div class="botoneraconsulta2">
                <html:form action="/ws/organismo/borrar" styleId="especificacionForm">
                    <html:hidden property="clave" value="<%=organ.getClave()%>" />
                    <html:submit><bean:message key="boton.eliminar" /></html:submit>
                </html:form>
                <html:form action="/ws/organismo/mostrar" styleId="especificacionForm" method="get">
                    <html:hidden property="clave" value="<%=organ.getClave()%>" />
                    <html:submit><bean:message key="boton.seleccionar" /></html:submit>
                </html:form>
            </div>
        </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<br />

<center>
[<html:link action="/ws/organismo/nuevo" ><bean:message key="boton.nuevo" /></html:link>]
</center>
