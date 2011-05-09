<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
        return validateTipuaForm(form);
    }
// -->
</script>
<script type="text/javascript">
<!--
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
        <logic:notPresent name="tipuaForm" property="id">
            <bean:message key="tipua.alta" />
        </logic:notPresent>
        <logic:present name="tipuaForm" property="id">
            <bean:message key="tipua.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="tipua.datos" />
    </h2>
</div>

<br/>

<html:errors/>

<html:form action="/organigrama/tipua/editar" styleId="tipuaForm">

    <html:hidden property="id" />
	
	<div class="bloc">
        <div class="component">
        	<div class="etiqueta"><bean:message key="tipua.codiestandar" /></div>
            <html:text styleClass="btext" property="codigoEstandar" maxlength="256" tabindex="1" />
        </div>
    </div>
    <br />
	
    <div id="capes">
        <bean:size id="capes" name="tipuaForm" property="traducciones"/>
        <logic:iterate id="traducciones" name="tipuaForm" property="traducciones" indexId="i" >
            <div id="capa<%=i%>" class="capa">
                <div  class="pestanyes">
		            <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                        <% if (i.intValue() == j.intValue()) { %>
                        <div class="tab"><bean:message name="lang" /></div>
                        <% } else { %>
                        <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                        <% } %>
                    </logic:iterate>
		        </div>
                <div class="bloc">
                    <div class="component">
                        <div class="etiqueta"><bean:message key="tipua.tipo" /></div>
                        <html:text styleClass="btext" property="tipo" name="traducciones" indexed="true" />
                    </div>
                    <div class="component">
                        <div class="etiqueta"><bean:message key="tipua.cargom" /></div>
                        <html:text styleClass="btext" property="cargoM" name="traducciones" indexed="true" />
                    </div>
                    <div class="component">
                        <div class="etiqueta"><bean:message key="tipua.tratamientom" /></div>
                        <html:text styleClass="btext" property="tratamientoM" name="traducciones" indexed="true" />
                    </div>
                    <div class="component">
                        <div class="etiqueta"><bean:message key="tipua.cargof" /></div>
                        <html:text styleClass="btext" property="cargoF" name="traducciones" indexed="true" />
                    </div>
                    <div class="component">
                        <div class="etiqueta"><bean:message key="tipua.tratamientof" /></div>
                        <html:text styleClass="btext" property="tratamientoF" name="traducciones" indexed="true" />
                    </div>
                </div>
            </div>
        </logic:iterate>
    </div>
    <br />

    <div class="botonera">
         <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);">
            <logic:notPresent name="tipuaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="tipuaForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
         </html:submit>
         <html:reset styleClass="esquerra"><bean:message key="boton.reiniciar" /></html:reset>
         <html:cancel styleClass="dreta" ><bean:message key="boton.cancelar" /></html:cancel>
         <logic:present name="tipuaForm" property="id">
            <html:submit styleClass="dreta" property="action" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
         </logic:present>
         <input type="hidden" name="action" />
     </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="tipuaForm"
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
