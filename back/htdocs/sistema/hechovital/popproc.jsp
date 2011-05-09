<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html locale="true" xhtml="true">
<head>
   <title>SAC Ficha - Materia</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        function relaciona(){
            id = document.forms[0].procedimiento.options[document.forms[0].procedimiento.selectedIndex].value;
            nombre = document.forms[0].procedimiento.options[document.forms[0].procedimiento.selectedIndex].text;
            opener.asignarProcedimiento(id);
            window.close();
        }
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
            <h1><bean:message key="procedimiento.lista" /></h1>
    </div>
    <br />
    <logic:empty name="procedimientoOptions">
        <center>
            <br /><bean:message key="procedimiento.vacio" /></h2><br />
            <div class="botonera">
    </logic:empty>

    <form>
    <logic:notEmpty name="procedimientoOptions">
            <div class="bloc">
            <div class="component">
                <div class="etiqueta">
                    <bean:message key="procedimiento.titulo" />
                </div>
                <select name="procedimiento" >
                    <logic:iterate id="option" name="procedimientoOptions" >
                        <option value='<bean:write name="option" property="id" />'>
                           <logic:notEmpty name="option" property="traduccion">
	                         	<bean:write name="option" property="traduccion.nombre" />
                            </logic:notEmpty>
                            <logic:empty name="option" property="traduccion">
                            	<bean:message key="ua.procedimiento.noTraduccion" />
                            </logic:empty>
                        </option>
                    </logic:iterate>
                </select>
            </div>
            </div>
        <center>
        <div class="botonera">
            <input type="button" value="<bean:message key="boton.relacionar" />" onclick="relaciona()" />
    </logic:notEmpty>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </div>
    </center>
    </form>

</div>
</body>
</html:html>
