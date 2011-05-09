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
    function validar(form) {
       return validateUnidadMateriaForm(form);
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

<%String idUnidad = null;%>
<logic:notPresent name="idUnidad" >
    <bean:parameter id="aux" name="idUnidad"  />
    <%idUnidad = aux;%>
</logic:notPresent>
<logic:present name="idUnidad">
    <bean:define id="aux" name="idUnidad" type="java.lang.Long" />
    <%idUnidad = aux.toString();%>
</logic:present>

<div class="bloc">
    <h1>
        <logic:notPresent name="unidadMateriaForm" property="id">
            <bean:message key="unimat.alta" />
        </logic:notPresent>
        <logic:present name="unidadMateriaForm" property="id">
            <bean:message key="unimat.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="unimat.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/organigrama/unimat/editar" styleId="unidadMateriaForm" method="POST" >
    <logic:present name="unidadMateriaForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <input type="hidden" name="idUnidad" value="<%=idUnidad%>" />
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="unimat.materia" /></div>
            <html:select property="idMateria" tabindex="1">
                <html:option value="" >- <bean:message key="materia.lista"/> -</html:option>
                <html:options collection="materiaOptions" property="id" labelProperty="traduccion.nombre" />
            </html:select>
        </div>
    </div>

    <br/>
    <br/>

    <div id="capes">
        <bean:size id="capes" name="unidadMateriaForm" property="traducciones"/>
        <logic:iterate id="traducciones" name="unidadMateriaForm" property="traducciones" indexId="i" >
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
                        <div class="etiqueta"><bean:message key="unimat.url" /></div>
                        <html:text styleClass="btext" property="urlUnidadMateria" name="traducciones" indexed="true" />
                    </div>
                </div>
            </div>
        </logic:iterate>
    </div>
    <br />

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);">
            <logic:notPresent name="unidadMateriaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="unidadMateriaForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra"><bean:message key="boton.reiniciar" /></html:reset>
      <%--  <logic:present name="unidadMateriaForm" property="id">
            <html:submit property="action" styleClass="dreta"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>--%>
        <html:submit styleClass="dreta" property="action"><bean:message key="boton.cancelar" /></html:submit>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="unidadMateriaForm"
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
