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
var context = '\<%=request.getContextPath()%>';
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateUsuarioForm(form);
    }
    
    function popupUA() {
		obrir(context+"/organigrama/unidad/poparbol.do?idUA=0&action=&ficha=true", "Relacionar", 538, 440);
	}

	function actualizaUA(id) {
		
		<logic:present name="usuarioForm" property="id">
			document.location = context+"/sistema/usuario/editar.do?action=<bean:message key="usuario.relacion.unidades" />&unidad=" + id + "&usuario=" + <bean:write name="usuarioForm" property="id"/> + "&operacion=alta";
	    </logic:present>		
	}
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="usuarioForm" property="id">
            <bean:message key="usuario.alta" />
        </logic:notPresent>
        <logic:present name="usuarioForm" property="id">
            <bean:message key="usuario.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="usuario.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/usuario/editar" styleId="usuarioForm">
    <logic:present name="usuarioForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="usuario.usuario" /></div>
            <html:text styleClass="btext" property="username" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="usuario.password" /></div>
            <html:password styleClass="btext" property="password" maxlength="50" tabindex="2" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="usuario.nombre" /></div>
            <html:text styleClass="btext" property="nombre" maxlength="256" tabindex="4" />
        </div>
        
        <div class="component">
             <div class="etiqueta"><bean:message key="usuario.email" /></div> 
            <html:text styleClass="ctext" property="email" size="43" maxlength="256" tabindex="11" /> 
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="usuario.observaciones" /><br /><br /><br /></div>
            <html:textarea property="observaciones" tabindex="5" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="usuario.perfil" /></div>
            <html:select property="perfil" tabindex="6" >
                <html:option value="" key="select.defecto" />
                <html:option value="sacadmin" key="perfil.sacadmin" />
                <html:option value="sacsuper" key="perfil.sacsuper" />
                <html:option value="sacoper" key="perfil.sacoper" />
                <html:option value="sacinfo" key="perfil.sacinfo" />
            </html:select>
        </div>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="usuarioForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="usuarioForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:notPresent name="usuarioForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" >
                <bean:message key="boton.busqueda" />
            </html:submit>
        </logic:notPresent>
        <logic:present name="usuarioForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="usuarioForm"
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

<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>
<logic:present name="usuarioForm" property="id">

    <br /><br />

    <!-- Unidades -->
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="usuario.relacion.unidades.titulo" />
        </div>
        <logic:empty name="unidadOptions">
            <br /><h2><bean:message key="usuario.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="unidadOptions">
            <logic:iterate id="unidad" name="unidadOptions">
            <div class="component">
                <div class="textconsulta1">
	                <html:link action='<%="/organigrama/unidad/seleccionar?action=" + etiquetaSelect%>'
                          paramId="idUA" paramName="unidad" paramProperty="id">
    	                <bean:write name="unidad" property="traduccion.nombre" />
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/usuario/editar" >
                        <input type="hidden" name="unidad" value='<bean:write name="unidad" property="id" />' />
                        <input type="hidden" name="usuario" value='<bean:write name="usuarioForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="usuario.relacion.unidades" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript:popupUA();" ><bean:message key="boton.nuevo" /></a>]
    </center>
    <!-- /Unidades -->

    <br /><br />

</logic:present>


