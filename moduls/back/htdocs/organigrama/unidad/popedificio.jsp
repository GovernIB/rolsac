<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:html locale="true" xhtml="true">
<head>
   <title>SAC Unidad - Edificio</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        function relaciona(){
            id = document.forms[0].edificio.options[document.forms[0].edificio.selectedIndex].value;
            opener.asignarEdificio(id);
            window.close();
        }
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
            <h1><bean:message key="edificio.lista" /></h1>
    </div>
    <br />

    <logic:empty name="edificioOptions">
            <br /><h2><bean:message key="edificio.vacio" /></h2><br />
            <div class="botonera">
    </logic:empty>

    <form>

    <logic:notEmpty name="edificioOptions">
            <div class="bloc">
            <div class="component">
                <div class="etiqueta">
                    <bean:message key="edificio.direccion" />
                </div>
                <select name="edificio" >
                    <logic:iterate id="option" name="edificioOptions" >
                        <option value='<bean:write name="option" property="id" />'>
                            <bean:write name="option" property="direccion" />
                        </option>
                    </logic:iterate>
                </select>
            </div>
            </div>

        <div class="botonera">
            <input type="button" value="<bean:message key="boton.relacionar" />" onclick="relaciona()" />
    </logic:notEmpty>

        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </div>

    </form>

</div>
</body>
</html:html>
