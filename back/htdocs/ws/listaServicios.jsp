<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>

<div class="bloc">
    <h1><bean:message key="servicio.lista" /></h1>
</div>

<br />

<html:messages id="message" header="messages.header" footer="messages.footer" message="true">
  <li><bean:write name="message"/></li>
</html:messages>

<logic:empty name="servicios">
    <div class="bloc">
        <br /><h2><bean:message key="servicio.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="servicios">
    <div class="bloc">
        <logic:iterate id="serv" name="servicios" type="org.ibit.rol.sac.integracion.uddi.ServicioWeb">
        <div class="component">
            <div class="textconsulta2">
                <bean:write name="serv" property="nombre" />
            </div>
            <div class="botoneraconsulta2">
                <html:form action="/ws/servicio/borrar" styleId="servicioForm">
                    <html:hidden property="clave" value="<%=serv.getClave()%>" />
                    <html:submit><bean:message key="boton.eliminar" /></html:submit>
                </html:form>
                <html:form action="/ws/servicio/mostrar" styleId="servicioForm" method="get">
                    <html:hidden property="clave" value="<%=serv.getClave()%>" />
                    <html:submit><bean:message key="boton.seleccionar" /></html:submit>
                </html:form>
            </div>
        </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<br />

<center>
[<html:link action="/ws/servicio/editar" ><bean:message key="boton.nuevo" /></html:link>]
</center>

