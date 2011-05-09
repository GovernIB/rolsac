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
        <logic:empty name="organismoForm" property="clave">
            <bean:message key="organismo.alta" />
        </logic:empty>
        <logic:notEmpty name="organismoForm" property="clave">
            <bean:message key="organismo.modificacion" />
        </logic:notEmpty>
    </h1>
    <h2>
        <bean:message key="organismo.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/ws/organismo/guardar" styleId="organismoForm">

    <html:hidden property="clave" />
    <html:hidden property="idUnidad" />

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="organismo.nombre" /></div>
            <html:text styleClass="btext" property="nombre"  maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="organismo.descripcion" /><br /><br /><br /></div>
            <html:textarea property="descripcion" tabindex="2" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="organismo.nivel" /></div>
            <html:select styleClass="btext" property="nivel" tabindex="3">
                <html:option value="" key="select.defecto" />
                <html:option value="1" />
                <html:option value="2" />
                <html:option value="3" />
                <html:option value="4" />
            </html:select>
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="organismo.responsable" /></div>
            <html:text styleClass="btext" property="responsable" maxlength="256" tabindex="4" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="organismo.telefono" /></div>
            <html:text styleClass="btext" property="telefono" maxlength="256" tabindex="5" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="organismo.email" /></div>
            <html:text styleClass="btext" property="email" maxlength="256" tabindex="6" />
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" onclick="return validateOrganismoForm(this.form);" tabindex="100">
            <logic:empty name="organismoForm" property="clave">
                <bean:message key="boton.insertar" />
            </logic:empty>
            <logic:notEmpty name="organismoForm" property="clave">
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
    formName="organismoForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>
