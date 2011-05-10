<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<% String nombreEspacio = null;%>
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
       return validateEspacioForm(form);
    }

    <logic:notEmpty name="espacioForm">
        function cambiaPadre(){
            obrir("<html:rewrite page='/sistema/espacio/arbol.do'/>?idEspacio=<bean:write name="espacioForm" property="id" />&action=", "<bean:message key='boton.seleccionar' />", 538, 440);
        }
    </logic:notEmpty>

    function updatePadre(id, nombre) {
        if (id == 0){
            document.forms[0].idPadre.value = "";
        } else {
            document.forms[0].idPadre.value = id;
        }
        document.forms[0].nombrePadre.value = nombre;
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
    <logic:notPresent name="espacioForm" property="id">
        <h1><bean:message key="espacio.alta" /></h1>
    </logic:notPresent>
    <logic:present name="espacioForm" property="id">
        <h1><bean:message key="espacio.modificacion" /></h1>
    </logic:present>
    <h2><bean:message key="espacio.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/espacio/editar" styleId="espacioForm" enctype="multipart/form-data">
    <html:hidden property="id" />
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="espacio.padre" /></div>
            <logic:notEmpty name="idPadre">
                <bean:define id="idp" name="idPadre" type="java.lang.Long" />
                <html:hidden property="idPadre" value="<%=idp.toString()%>" />
            </logic:notEmpty>
            <logic:empty name="idPadre">
                <html:hidden property="idPadre" />
            </logic:empty>
            <logic:present name="padre">
                <input type="text" class="ctext" id="nombrePadre" readonly="1" value='<bean:write name="padre" property="traduccion.nombre" />' />
            </logic:present>
            <logic:notPresent name="padre">
                <input type="text" class="ctext" id="nombrePadre" readonly="1" value='- <bean:message key="espacio.raiz" /> -' />
            </logic:notPresent>
            <logic:notEmpty name="espacioForm" property="id" >
                <div class="botoneraconsulta1">
                    <input type="button" onclick="cambiaPadre()" value="<bean:message key='boton.modificar' />" tabindex="1"/>
                </div>
            </logic:notEmpty>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="espacio.coordenadas"/></div>
             <html:text styleClass="btext" property="coordenadas" maxlength="100" tabindex="1" />
        </div>
        
        <logic:notEmpty name="espacioForm" property="nombremapa">
        <div class="component">
            <div class="etiqueta"><bean:message key="espacio.mapa" /></div>
            <bean:define id="imgurl"><html:rewrite page="/espacio/mapa.do" paramId="idEspacio" paramName="espacioForm" paramProperty="id" /></bean:define>
            <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="espacioForm" property="nombremapa" /></a>

            <div class="checkconsulta1">
                <html:checkbox property="borrarmapa"><bean:message key="boton.eliminar" /></html:checkbox>
            </div>
        </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="espacio.mapa" /></div>
            <html:file styleClass="bfile" property="mapafile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>
        
        <logic:notEmpty name="espacioForm" property="nombrelogo">
        <div class="component">
            <div class="etiqueta"><bean:message key="espacio.logo" /></div>
            <bean:define id="imgurl"><html:rewrite page="/espacio/logo.do" paramId="idEspacio" paramName="espacioForm" paramProperty="id" /></bean:define>
            <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="espacioForm" property="nombrelogo" /></a>

            <div class="checkconsulta1">
                <html:checkbox property="borrarlogo"><bean:message key="boton.eliminar" /></html:checkbox>
            </div>
        </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="espacio.logo" /></div>
            <html:file styleClass="bfile" property="logofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>
    </div>

    <br /><br />

    <div id="capes">
    <bean:size id="capes" name="espacioForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="espacioForm" property="traducciones" indexId="i" >
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
                    <div class="etiqueta"><bean:message key="espacio.nombre" /></div>
                    <logic:notEmpty name="espacioForm" property="id" >
                        <logic:equal name="i" value="0">
                            <bean:define id="nombreAux" name="traducciones" property="nombre" type="java.lang.String" />
                            <% nombreEspacio = nombreAux; %>
                        </logic:equal>
                    </logic:notEmpty>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" maxlength="256" tabindex="3" />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="espacioForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="espacioForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="espacioForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>


</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="espacioForm"
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
