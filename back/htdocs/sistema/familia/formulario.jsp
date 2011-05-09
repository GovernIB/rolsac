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
       return validateFamiliaForm(form);
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
        <logic:notPresent name="familiaForm" property="id">
            <bean:message key="familia.alta" />
        </logic:notPresent>
        <logic:present name="familiaForm" property="id">
            <bean:message key="familia.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="familia.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/familia/editar" styleId="familiaForm">
    <logic:present name="familiaForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div id="capes">
    <bean:size id="capes" name="familiaForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="familiaForm" property="traducciones" indexId="i" >
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
                    <div class="etiqueta"><bean:message key="familia.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="1" maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="familia.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" rows="3" cols="60" tabindex="2"  />
                </div>

            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="familiaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="familiaForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="familiaForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="familiaForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="familiaForm" property="id">
    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="familia.iconos" />
        </div>
        <logic:empty name="iconosFamilia">
            <br /><h2><bean:message key="icofam.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="iconosFamilia">
            <logic:iterate id="icofam" name="iconosFamilia">
            <div class="component">
                <div class="textconsulta1">
                     <bean:define id="imgurl"><html:rewrite page="/icono/familia.do" paramId="idIcoFam" paramName="icofam" paramProperty="id" /></bean:define>
                     <a href="javascript:obrirImatge('<%=imgurl%>')" onfocus="this.blur()"><bean:write name="icofam" property="icono.nombre" /></a>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/icofam" styleId="iconoFamiliaForm">
                        <input type="hidden" name="id" value='<bean:write name="icofam" property="id" />' />
                        <html:submit property="action">
                            <bean:message key="boton.eliminar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div>
    <br />
    <center>
    [<html:link page="/sistema/familia/icono.do"
               paramId="idFamilia"
               paramName="familiaForm"
               paramProperty="id">
        <bean:message key="boton.nuevo" />
    </html:link>]
    </center>
</logic:present>

<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>
