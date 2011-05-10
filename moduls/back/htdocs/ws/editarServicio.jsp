<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>

<div class="bloc">
    <h1>
        <logic:notPresent name="servicioForm" property="clave">
            <bean:message key="servicio.alta" />
        </logic:notPresent>
        <logic:present name="servicioForm" property="clave">
            <bean:message key="servicio.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="servicio.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/ws/servicio/guardar" styleId="servicioForm">

    <html:hidden property="clave" />

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="servicio.nombre" /></div>
            <html:text styleClass="btext" property="nombre"  maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="servicio.descripcion" /><br /><br /><br /></div>
            <html:textarea property="descripcion" tabindex="2" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="servicio.puntoAcceso" /></div>
            <html:text styleClass="btext" property="puntoAcceso" maxlength="512" tabindex="3" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="servicio.organismo" /></div>
            <html:select styleClass="btext" property="claveOrganismo" tabindex="4">
                <html:option value="" key="select.defecto" />
                <html:options collection="organismos" property="clave" labelProperty="nombre" />
            </html:select>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="servicio.especificacion" /></div>
            <html:select styleClass="btext" property="claveEspecificacion" tabindex="5">
                <html:option value="" key="select.defecto" />
                <html:options collection="especificaciones" property="clave" labelProperty="nombre" />
            </html:select>
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" onclick="return validateServicioForm(this.form);" tabindex="100">
            <logic:empty name="servicioForm" property="clave">
                <bean:message key="boton.insertar" />
            </logic:empty>
            <logic:notEmpty name="servicioForm" property="clave">
                <bean:message key="boton.modificar" />
            </logic:notEmpty>
        </html:submit>
        <html:reset styleClass="esquerra"tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="servicioForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>
