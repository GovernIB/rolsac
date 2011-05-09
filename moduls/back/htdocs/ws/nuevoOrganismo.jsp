<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>

<div class="bloc">
    <h1>
        <bean:message key="organismo.alta" />
    </h1>
</div>

<br />

<html:errors/>

<html:form action="/ws/organismo/rellenar" styleId="organismoForm">

    <html:hidden property="clave" />

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.title" /></div>
            <html:select styleClass="btext" property="idUnidad" tabindex="1">
                <html:option value="" key="select.defecto" />
                <html:options collection="unidades" property="id" labelProperty="traduccion.nombre" />
            </html:select>
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" onclick="return validateOrganismoForm(this.form);" tabindex="100">
            <bean:message key="boton.seleccionar" />
        </html:submit>
        <html:reset styleClass="esquerra"tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="organismoForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>
