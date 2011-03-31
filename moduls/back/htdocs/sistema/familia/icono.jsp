<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function validar(form) {
       return validateIconoFamiliaForm(form);
    }
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="iconoFamiliaForm" property="id">
            <bean:message key="icofam.alta" />
        </logic:notPresent>
        <logic:present name="iconoFamiliaForm" property="id">
            <bean:message key="icofam.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="icofam.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/icofam" styleId="iconoFamiliaForm" method="POST" enctype="multipart/form-data">
    <logic:present name="iconoFamiliaForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <bean:parameter id="idFamilia" name="idFamilia" />
    <html:hidden property="idFamilia" value="<%=idFamilia%>"/>
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="icofam.perfil" /></div>
            <html:select property="idPerfil" tabindex="1">
                <html:option value="" >- <bean:message key="perfil.lista"/> -</html:option>
                <html:options collection="perfilOptions" property="id" labelProperty="traduccion.nombre" />
            </html:select>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="icofam.archivo" /></div>
            <html:file styleClass="file" styleClass="bfile" property="file" tabindex="2" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt"/>
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);">
            <logic:notPresent name="iconoFamiliaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="iconoFamiliaForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra"><bean:message key="boton.reiniciar" /></html:reset>
        <logic:present name="iconoFamiliaForm" property="id">
            <html:submit property="action" styleClass="dreta"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
        <html:cancel styleClass="dreta" ><bean:message key="boton.cancelar" /></html:cancel>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="iconoFamiliaForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>
