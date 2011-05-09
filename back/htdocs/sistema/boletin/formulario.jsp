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
       return validateBoletinForm(form);
    }
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="boletinForm" property="id">
            <bean:message key="boletin.alta" />
        </logic:notPresent>
        <logic:present name="boletinForm" property="id">
            <bean:message key="boletin.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="boletin.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/boletin/editar" styleId="boletinForm">
    <logic:present name="boletinForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="boletin.enlace" /></div>
            <html:text styleClass="btext" property="enlace" maxlength="512" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="boletin.nombre" /></div>
            <html:text styleClass="btext" property="nombre"  maxlength="256" tabindex="2" />
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="boletinForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="boletinForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra"tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="boletinForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="boletinForm"
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
