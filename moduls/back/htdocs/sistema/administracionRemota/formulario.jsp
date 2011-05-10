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
<script src="<html:rewrite page='/moduls/xmlhttp.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateAdministracionRemotaForm(form);
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
	<logic:present name="administracionRemotaForm" property="id">
        
		var estado='<bean:write name="running"/>';
	
	    function recargarPagina(){
	        url = '<html:rewrite page="/sistema/administracionRemota/seleccionar.do?&action=Seleccionar&idSelect=" />';
	        url+= '<bean:write name="administracionRemotaForm" property="id"/>';
	        document.location.href = url;
	    }
	
	    function preguntarEstado() {
	        url = "<html:rewrite page='/sistema/administracionRemota/estado.do' />";
	        params = "estado=" + estado;
        	asyncPost(url, params,recogerEstado);
	    }
	
		function recogerEstado() {
		    if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
				    var Temp =  new Function("return "+ (xmlhttp.responseText))();
					if(estado!=Temp.etiqueta){
						recargarPagina();
					}           
					var timer = setTimeout(preguntarEstado, 5000);
		        } else {
				    alert('Se genero un error:\n' + xmlhttp.statusText);
				}
			}
		}
		
		preguntarEstado();
		
	</logic:present>
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="administracionRemotaForm" property="id">
            <bean:message key="administracionRemota.alta" />
        </logic:notPresent>
        <logic:present name="administracionRemotaForm" property="id">
            <bean:message key="administracionRemota.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="administracionRemota.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/administracionRemota/editar" styleId="administracionRemotaForm" enctype="multipart/form-data">
    <logic:present name="administracionRemotaForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
    	<div class="component">
        	<div class="etiqueta"><bean:message key="administracionRemota.nombre" /></div>
            <html:text styleClass="btext" property="nombre" maxlength="256" tabindex="1" />
        </div>
    	<div class="component">
        	<div class="etiqueta"><bean:message key="administracionRemota.endpoint" /></div>
            <html:text styleClass="btext" property="endpoint" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
        	<div class="etiqueta"><bean:message key="administracionRemota.profundidad" /></div>
            <html:text styleClass="ctext" property="profundidad" maxlength="256" tabindex="2" />
        </div>
        <div class="component">
        	<div class="etiqueta"><bean:message key="administracionRemota.codigoEstandarUA" /></div>
            <html:text styleClass="ctext" property="codigoEstandarUA" maxlength="256" tabindex="2" />
        </div>
        
        <logic:notEmpty name="administracionRemotaForm" property="nombrelogop">
        <div class="component">
            <div class="etiqueta"><bean:message key="administracionRemota.logop" /></div>
            <bean:define id="imgurl"><html:rewrite page="/administracionRemota/logop.do" paramId="idAdmin" paramName="administracionRemotaForm" paramProperty="id" /></bean:define>
            <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="administracionRemotaForm" property="nombrelogop" /></a>

            <div class="checkconsulta1">
                <html:checkbox property="borrarlogop"><bean:message key="boton.eliminar" /></html:checkbox>
            </div>
        </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="administracionRemota.logop" /></div>
            <html:file styleClass="bfile" property="logopfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>
        
        <logic:notEmpty name="administracionRemotaForm" property="nombrelogog">
        <div class="component">
            <div class="etiqueta"><bean:message key="administracionRemota.logog" /></div>
            <bean:define id="imgurl"><html:rewrite page="/administracionRemota/logog.do" paramId="idAdmin" paramName="administracionRemotaForm" paramProperty="id" /></bean:define>
            <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="administracionRemotaForm" property="nombrelogog" /></a>

            <div class="checkconsulta1">
                <html:checkbox property="borrarlogog"><bean:message key="boton.eliminar" /></html:checkbox>
            </div>
        </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="administracionRemota.logog" /></div>
            <html:file styleClass="bfile" property="logogfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>
        
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.espacio" /></div>
            <html:select styleClass="btext" property="idEspacioTerrit" tabindex="13">
                <html:option value="" key="select.defecto" />
                <html:options collection="listaEspacios" property="id" labelProperty="traduccion.nombre" />
            </html:select>
        </div>
        <div class="component">
        	<div class="etiqueta"><bean:message key="administracionRemota.idRemoto" /></div>
            <html:text styleClass="ctext" property="idRemoto" maxlength="256" tabindex="2" />
        </div>
       <div class="component">
             <div class="etiqueta"><bean:message key="administracionRemota.responsable" /></div> 
            <html:text styleClass="ctext" property="responsable" size="43" maxlength="256" tabindex="11" /> 
       </div>
       <div class="component">
             <div class="etiqueta"><bean:message key="administracionRemota.version" /></div> 
            <html:text styleClass="ctext" property="version" size="43" maxlength="256" tabindex="11" /> 
       </div>
    </div>
    <br/>
    <br/>
    <logic:notEqual value="<%= request.getAttribute("idRunning").toString() %>"  name="administracionRemotaForm" property="id">
	    <div class="botonera">
	        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
	            <logic:notPresent name="administracionRemotaForm" property="id">
	                <bean:message key="boton.insertar" />
	            </logic:notPresent>
	            <logic:present name="administracionRemotaForm" property="id">
	                <bean:message key="boton.modificar" />
	            </logic:present>
	        </html:submit>
	        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
	        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
	        <logic:present name="administracionRemotaForm" property="id">
	            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
	        </logic:present>
	    </div>
	    <logic:present name="administracionRemotaForm" property="id">
	    	<br/>
	    	<logic:equal name="running" value="parado">
			    <div class="botonera">
			    	<logic:notPresent name="admBacia">
					    <html:submit property="action" styleClass="esquerra" tabindex="102"><bean:message key="boton.baja" /></html:submit>
				    </logic:notPresent>
			    	<logic:present name="admBacia">
					    <html:submit property="action" styleClass="esquerra" tabindex="102"><bean:message key="boton.alta" /></html:submit>
				    </logic:present>
			    </div>
		    </logic:equal>
		    	
	        <logic:equal value="eject" name="running">
		        <div class="bloc">
		        	<h2>
		        	<html:image src="../../img/small-loader.gif" />
		        	<bean:message key="administracionRemota.sinc.enProgreso" />
		        	</h2>
				</div>
	        </logic:equal>
		        
	        <logic:equal value="error" name="running">
	        	<div class="bloc">
		        	<h2>
		        	<html:image src="../../img/error.gif" />
		        	<bean:message key="administracionRemota.sinc.error" />
		        	</h2>
	        	</div>
	        </logic:equal>
	    </logic:present>
	</logic:notEqual>
	<logic:equal value="<%= request.getAttribute("idRunning").toString() %>"  name="administracionRemotaForm" property="id">
    	<div class="bloc">
	    	
	        <logic:equal value="eject" name="running">
	        	<h1>
	        	<html:image src="../../img/small-loader.gif" />
	        	<logic:equal value="alta" name="runtipo">
		        	<bean:message key="administracionRemota.sinc.procesandoAlta" />
		        </logic:equal>
		        <logic:equal value="baja" name="runtipo">
		        	<bean:message key="administracionRemota.sinc.procesandoBaja" />
		        </logic:equal>
		        </h1>
	        </logic:equal>
	        <logic:equal value="error" name="running">
	        	<h1>
		        	<bean:message key="administracionRemota.sinc.error" />
	        	</h1>
		        <h2>
		        	<bean:write name="runerror" />
	        	</h2>
	        	<html:submit property="action" tabindex="102"><bean:message key="boton.clean" /></html:submit>
	        	<html:image src="../../img/error.gif" />
	        </logic:equal>
        </div>
	</logic:equal>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="administracionRemotaForm"
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
