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
       return validatePerfilForm(form);
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
        <logic:notPresent name="perfilForm" property="id">
            <bean:message key="perfil.alta" />
        </logic:notPresent>
        <logic:present name="perfilForm" property="id">
            <bean:message key="perfil.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="perfil.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/perfil/editar" styleId="perfilForm">
    <logic:present name="perfilForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="perfil.codigo" /></div>
            <html:text styleClass="btext" property="codigoEstandard" maxlength="10" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="perfil.iconografia" /></div>
            <html:text styleClass="btext" property="pathIconografia" maxlength="512" tabindex="2" />
        </div>
    </div>
    <br />

    <div id="capes">
    <bean:size id="capes" name="perfilForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="perfilForm" property="traducciones" indexId="i" >
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
                    <div class="etiqueta"><bean:message key="perfil.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="3" maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="perfil.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" rows="3" cols="60" tabindex="4" />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="perfilForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="perfilForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="perfilForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="perfilForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="perfilForm" property="id">
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
                        <bean:write name="icofam" property="icono.nombre" />
                    </div>
                    <div class="botoneraconsulta1">
                    </div>
                </div>
            </logic:iterate>
        </logic:notEmpty>
    </div>
    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="materia.iconos" />
        </div>
        <logic:empty name="iconosMateria">
            <br /><h2><bean:message key="icomat.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="iconosMateria">
            <logic:iterate id="icomat" name="iconosMateria">
                <div class="component">
                    <div class="textconsulta1">
                        <bean:write name="icomat" property="icono.nombre" />
                    </div>
                    <div class="botoneraconsulta1">
                    </div>
                </div>
            </logic:iterate>
        </logic:notEmpty>
    </div>
    <br /><br />

</logic:present>

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
