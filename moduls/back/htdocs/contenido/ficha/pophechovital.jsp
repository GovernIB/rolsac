<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<bean:parameter id="accion" name="accion" />
<html:html locale="true" xhtml="true">
<head>
   <title>SAC Ficha - HechoVital</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        function busca(){
            id = document.forms[0].hechovital.options[document.forms[0].hechovital.selectedIndex].value;
            nombre = document.forms[0].hechovital.options[document.forms[0].hechovital.selectedIndex].text;
            opener.buscarPorHechoVital(id);
            window.close();
        }

        function relaciona(){
            id = document.forms[0].hechovital.options[document.forms[0].hechovital.selectedIndex].value;
            nombre = document.forms[0].hechovital.options[document.forms[0].hechovital.selectedIndex].text;
            opener.asignarHechoVital(id);
            window.close();
        }
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
            <h1><bean:message key="hechovital.lista" /></h1>
    </div>
    <br />
    <logic:empty name="listaHechos">
        <center>
            <br /><bean:message key="hechovital.vacio" /></h2><br />
            <div class="botonera">
    </logic:empty>

    <form>
    <logic:notEmpty name="listaHechos">
            <div class="bloc">
            <div class="component">
                <div class="etiqueta">
                    <bean:message key="hechovital.nombre" />
                </div>
                <select name="hechovital" >
                    <logic:iterate id="option" name="listaHechos" >
                        <option value='<bean:write name="option" property="id" />'>
                            <bean:write name="option" property="traduccion.nombre" />
                        </option>
                    </logic:iterate>
                </select>
            </div>
            </div>
        <center>
        <div class="botonera">
            <logic:equal name="accion" value="busqueda">
                <input type="button" value="<bean:message key="boton.busqueda" />" onclick="busca()" />
            </logic:equal>
            <logic:equal name="accion" value="relaciona">
                <input type="button" value="<bean:message key="boton.relacionar" />" onclick="relaciona()" />
            </logic:equal>
    </logic:notEmpty>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </div>
    </center>
    </form>

</div>
</body>
</html:html>
