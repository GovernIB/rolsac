<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<!-- (PORMAD) -->
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateDestinatarioForm(form);
    }

	// Funcio per mostrar una capa i ocultar la resta
	function activarCapa(capa, n) {
        for (i = 0; i < n; i++) {
            capeta = document.getElementById("capa" + i);
            if (i == capa) {
                capeta.style.visibility="visible";
            } else {
                capeta.style.visibility="hidden";
            }
        }
	}
	
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="destinatarioForm" property="id">
            <bean:message key="destinatario.alta" />
        </logic:notPresent>
        <logic:present name="destinatarioForm" property="id">
            <bean:message key="destinatario.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="destinatario.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/destinatario/editar" styleId="destinatarioForm">
    <logic:present name="destinatarioForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
    	<div class="component">
        	<div class="etiqueta"><bean:message key="destinatario.nombre" /></div>
            <html:text styleClass="btext" property="nombre" maxlength="256" tabindex="1" />
        </div>
    	<div class="component">
        	<div class="etiqueta"><bean:message key="destinatario.endpoint" /></div>
            <html:text styleClass="btext" property="endpoint" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
        	<div class="etiqueta"><bean:message key="destinatario.idRemoto" /></div>
            <html:text styleClass="btext" property="idRemoto" maxlength="256" tabindex="2" />
        </div>
        <div class="component">
        	<div class="etiqueta"><bean:message key="destinatario.email" /></div>
            <html:text styleClass="btext" property="email" maxlength="256" tabindex="2" />
        </div>
    </div>
    <br/>
    <br/>
    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="destinatarioForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="destinatarioForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="destinatarioForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="destinatarioForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
