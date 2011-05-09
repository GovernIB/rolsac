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
        <logic:notPresent name="especificacionForm" property="clave">
            <bean:message key="especificacion.alta" />
        </logic:notPresent>
        <logic:present name="especificacionForm" property="clave">
            <bean:message key="especificacion.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="especificacion.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/ws/especificacion/guardar" styleId="especificacionForm">

    <html:hidden property="clave" />

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="especificacion.nombre" /></div>
            <html:text styleClass="btext" property="nombre"  maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="especificacion.descripcion" /><br /><br /><br /></div>
            <html:textarea property="descripcion" tabindex="2" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="especificacion.urlDocumento" /></div>
            <html:text styleClass="btext" property="urlDocumento" maxlength="512" tabindex="3" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="especificacion.descripcionDocumento" /><br /><br /><br /></div>
            <html:textarea property="descripcionDocumento" tabindex="4" />
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" onclick="return validateEspecificacionForm(this.form);" tabindex="100">
            <logic:empty name="especificacionForm" property="clave">
                <bean:message key="boton.insertar" />
            </logic:empty>
            <logic:notEmpty name="especificacionForm" property="clave">
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
    formName="especificacionForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>
