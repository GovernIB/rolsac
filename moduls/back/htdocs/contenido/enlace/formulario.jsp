<%@ page language="java" %>
<%@ page import="org.apache.struts.Globals, org.ibit.rol.sac.model.Validacion" %>
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
       return validateEnlaceForm(form);
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
        <logic:notPresent name="enlaceForm" property="id">
            <bean:message key="enlace.alta" />
        </logic:notPresent>
        <logic:present name="enlaceForm" property="id">
            <bean:message key="enlace.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="enlace.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/contenido/enlace/editar" styleId="enlaceForm" method="POST">
    <logic:present name="enlaceForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <logic:present parameter="idProcedimiento">
        <bean:parameter id="idProcedimiento" name="idProcedimiento" value="0" />
        <input type="hidden" name="idProcedimiento" value="<%=idProcedimiento%>" />
    </logic:present>

    <logic:present parameter="idFicha">
        <bean:parameter id="idFicha" name="idFicha" value="0" />
        <input type="hidden" name="idFicha" value="<%=idFicha%>" />
    </logic:present>

    <div id="capes">
    <bean:size id="capes" name="enlaceForm" property="traducciones" />
    <logic:iterate id="traducciones" name="enlaceForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa">
			<% String idioma = ""; %>        
            <div class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) { 
                    	idioma = (String) lang;%>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                    <% } %>
                </logic:iterate>
            </div>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="enlace.titulo" /></div>
                    <html:text styleClass="btext" name="traducciones" property="titulo" indexed="true" tabindex="2"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="enlace.enlace" /></div>
                    <html:text styleClass="btext" name="traducciones" property="enlace" indexed="true" tabindex="2"  maxlength="512" />
                </div>
   			 </div>
	    <br />
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100">
            <logic:notPresent name="enlaceForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="enlaceForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101"><bean:message key="boton.reiniciar" /></html:reset>
        <html:submit styleClass="dreta" property="action" tabindex="103"><bean:message key="boton.cancelar" /></html:submit>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="enlaceForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

