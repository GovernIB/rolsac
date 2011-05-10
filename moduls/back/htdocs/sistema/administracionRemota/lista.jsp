<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<!-- (PORMAD) -->
<script src="<html:rewrite page='/moduls/xmlhttp.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
	var estado='<bean:write name="running"/>';

    function recargarPagina(){
        url = "<html:rewrite page="/sistema/administracionRemota/lista.do" />";
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
				var timer = setTimeout(preguntarEstado, 1000);
	        } else {
			    alert('Se genero un error:\n' + xmlhttp.statusText);
			}
		}
	}
	
	preguntarEstado();
	
// -->
</script>

<div class="bloc">
    <h1><bean:message key="administracionRemota.lista" /></h1>
    <h2><bean:message key="administracionRemota.datos" /></h2>
</div>
<br />

<logic:empty name="listaAdministracionRemota">
    <div class="bloc">
        <br /><h2><bean:message key="administracionRemota.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaAdministracionRemota">
    <div class="bloc">
        <logic:iterate id="administracionRemota" name="listaAdministracionRemota">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="administracionRemota" property="nombre" />
                     <logic:notEqual name="running" value="parado">
	                	<logic:equal value="<%= request.getAttribute("idRunning").toString() %>"  name="administracionRemota" property="id">
		                	<logic:equal value="error"  name="running">
		                		<html:image src="../../img/error.gif" />
		                	</logic:equal>
		                	<logic:notEqual value="error"  name="running">
		                		<html:image src="../../img/small-loader.gif" />
		                	</logic:notEqual>
	                	</logic:equal>
                	</logic:notEqual>
                </div>
                <div class="botoneraconsulta1">
	                <html:form action="/sistema/administracionRemota/seleccionar" styleId="administracionRemotaForm">
	                    <input type="hidden" name="idSelect" value='<bean:write name="administracionRemota" property="id" />' />
	                    <html:submit property="action">
	                        <bean:message key="boton.seleccionar" />
	                    </html:submit>
	                </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />
<center>
[<html:link page="/sistema/administracionRemota/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
