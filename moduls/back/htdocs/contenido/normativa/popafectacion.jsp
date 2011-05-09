<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html locale="true" xhtml="true">
<head>
   <title>SAC Normativa - POPAFECTACION</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
    <script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
   <script type="text/javascript">
   <!--
        function relaciona(){
            tafectacion = document.forms[0].tafectacion.options[document.forms[0].tafectacion.selectedIndex].value;
            normativa = document.forms[0].normativa.value;
            opener.asignarAfectacion(tafectacion, normativa);
            window.close();
        }

        function popBusquedaNormativa(){
           obrirScroll("<html:rewrite page='/contenido/procedimiento/popupnormativas.do'/>","", 538, 340);
        }

        function asignarNormativa(id, nombre){
            document.forms[0].normativa.value = id;
            document.forms[0].normativanom.value = nombre;
        }
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
        <h1><bean:message key="tafectacion.lista" /></h1>
    </div>
    <br />
    <bean:size id="numAfectaciones" name="tafectacionOptions" />
    <bean:size id="numNormativas" name="normativaOptions" />
    <form>

    <% if (numAfectaciones.intValue() == 0 || numNormativas.intValue() == 0){ %>
        <center>
            <br /><h2><bean:message key="normativa.relacion.vacio" /></h2><br />
            <div class="botonera">
    <%  } else { %>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta">
                        <bean:message key="tafectacion" />
                    </div>
                    <select name="tafectacion" >
                        <logic:iterate id="tafectacion" name="tafectacionOptions" >
                            <option value='<bean:write name="tafectacion" property="id" />'>
                                <bean:write name="tafectacion" property="traduccion.nombre" />
                            </option>
                        </logic:iterate>
                    </select>
                </div>
                <input type="hidden" name="normativa" value="" />
                <div class="component">
                    <div class="etiqueta">
                        <bean:message key="normativa" />
                    </div>
                    <input type="text" class="ctext" readonly="readonly" name="normativanom" value='<bean:message key="normativa.selecciona"/>' />
                    <div class="botoneraconsulta1">
                        <input type="button" value="<bean:message key="boton.seleccionar" />" onclick="popBusquedaNormativa()" />
                    </div>
                </div>
            </div>
        <center>
        <div class="botonera">
            <input type="button" value="<bean:message key="boton.seleccionar" />" onclick="relaciona()" />
    <% } %>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </div>
    </center>
    </form>

</div>
</body>
</html:html>
