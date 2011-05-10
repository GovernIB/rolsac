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
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateFormularioForm(form);
    }
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="formularioForm" property="id">
            <bean:message key="formulario.alta" />
        </logic:notPresent>
        <logic:present name="formularioForm" property="id">
            <bean:message key="formulario.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="formulario.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/contenido/formulario/editar" styleId="formularioForm" enctype="multipart/form-data" >
    <logic:present name="formularioForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <logic:present parameter="idTramite">
        <bean:parameter id="idTramite" name="idTramite" value="0" />
        <input type="hidden" name="idTramite" value="<%=idTramite%>" />
    </logic:present>

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="formulario.nombre" /></div>
            <html:text styleClass="btext" property="nombre"  maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="formulario.url" /></div>
            <html:text styleClass="btext" property="url" maxlength="512" tabindex="2" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="formulario.url.manual" /></div>
            <html:text styleClass="btext" property="urlManual" maxlength="512" tabindex="3" />
        </div>
        <logic:notEmpty name="formularioForm" property="nombreFichero">
            <div class="component">
                <div class="etiqueta"><bean:message key="formulario.archivo" /></div>
                <html:link action="/formulario/archivo" paramId="idFormulario" paramName="formularioForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="formularioForm" property="nombreFichero" /></html:link>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombreFichero" /> --%>
                <div class="checkconsulta1">
                    <input type="checkbox" name="borrarfichero" value="true" /><bean:message key="boton.eliminar" />
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="formulario.archivo" /></div>
            <html:file styleClass="bfile" property="fichero" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>
        <logic:notEmpty name="formularioForm" property="nombreManual">
            <div class="component">
                <div class="etiqueta"><bean:message key="formulario.archivo.manual" /></div>
                <html:link action="/formulario/manual" paramId="idFormulario" paramName="formularioForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="formularioForm" property="nombreManual" /></html:link>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombreManual" /> --%>
                <div class="checkconsulta1">
                    <input type="checkbox" name="borrarmanual" value="true" /><bean:message key="boton.eliminar" />
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="formulario.archivo.manual" /></div>
            <html:file styleClass="bfile" property="ficheroManual" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="5" />
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="formularioForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="formularioForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra"tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:submit styleClass="dreta" tabindex="103" property="action"><bean:message key="boton.cancelar" /></html:submit>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="formularioForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
