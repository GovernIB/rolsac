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
       return validatePersonalForm(form);
    }
    
    function abrirUA() {
        poprealcion = obrir("<html:rewrite page='/organigrama/unidad/poparbol.do'/>?idUA=0&action=&", "<bean:message key='boton.seleccionar' />", 538, 440);
    }

    function actualizaUA(id, nombre) {
        eval("document.forms[0].idUA.value=id");
        eval("document.forms[0].nombreUA.value=nombre");
    }
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="personalForm" property="id">
            <bean:message key="personal.alta" />
        </logic:notPresent>
        <logic:present name="personalForm" property="id">
            <bean:message key="personal.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="personal.datos" />
    </h2>
</div>


<br />

<html:errors/>

<html:form action="/organigrama/guia/editar" styleId="personalForm">

    <logic:present name="personalForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.username" /></div>
            <html:text styleClass="btext" property="username" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.nombre" /></div>
            <html:text styleClass="btext" property="nombre" maxlength="50" tabindex="2" />
        </div>


        <div class="component">
            <div class="etiqueta"><bean:message key="normativa.ua" /></div>
            
			<logic:present name="idUA">
            	<bean:define id="idu" name="idUA" type="java.lang.Long" />
                <html:hidden property="idUA" value="<%=idu.toString()%>" />
               	<bean:define id="txtUA" name="Uatexto" type="java.lang.String" />
           		<html:text styleClass="ctext" property="nombreUA" styleId="nombreUA" readonly="readonly" value="<%=txtUA%>"/>
            </logic:present>
            
            <logic:notPresent name="idUA">
				<html:hidden property="idUA" />
    	        <html:text styleClass="ctext" property="nombreUA" readonly="true" />
            </logic:notPresent>
            
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirUA()" tabindex="5"><bean:message key="boton.seleccionar" /></html:button>
            </div>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.funciones" /></div>
            <html:text styleClass="btext" property="funciones" maxlength="256" tabindex="3" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.cargo" /></div>
            <html:text styleClass="btext" property="cargo" maxlength="256" tabindex="4" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.email" /></div>
            <html:text styleClass="btext" property="email" maxlength="256" tabindex="5" />
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="personal.extensionPublica" /></div>
            <html:text styleClass="btext" property="extensionPublica" maxlength="64" tabindex="6" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.numeroLargoPublico" /></div>
            <html:text styleClass="btext" property="numeroLargoPublico" maxlength="64" tabindex="7" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.extensionPrivada" /></div>
            <html:text styleClass="btext" property="extensionPrivada" maxlength="64" tabindex="8" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.numeroLargoPrivado" /></div>
            <html:text styleClass="btext" property="numeroLargoPrivado" maxlength="64" tabindex="9" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.extensionMovil" /></div>
            <html:text styleClass="btext" property="extensionMovil" maxlength="64" tabindex="10" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="personal.numeroLargoMovil" /></div>
            <html:text styleClass="btext" property="numeroLargoMovil" maxlength="64" tabindex="11" />
        </div>
    </div>

    <div class="botonera">
         <html:submit styleClass="esquerra" property="action" tabindex="100" onclick="return validar(this.form);">
            <logic:notPresent name="personalForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="personalForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
         </html:submit>
         <html:reset styleClass="esquerra" tabindex="101"><bean:message key="boton.reiniciar" /></html:reset>
         <html:cancel styleClass="dreta" tabindex="103"><bean:message key="boton.cancelar" /></html:cancel>
         <logic:notPresent name="personalForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" >
                <bean:message key="boton.busqueda" />
            </html:submit>
		 </logic:notPresent>
         
         <logic:present name="personalForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
         </logic:present>
     </div>

</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="personalForm"
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


