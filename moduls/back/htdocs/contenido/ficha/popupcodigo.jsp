<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html locale="true" xhtml="true">
<head>
   <title>SAC Ficha - POPUP</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />

   <script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
   <script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
   <script type="text/javascript">
       <!--
       function tancar(){
            id = document.forms[0].idSelect.value;
            opener.mostrarFicha(id);
            window.close();
       }
     -->
   </script>
</head>

<body>
    <div id="organigrama">
        <div id="capsalera">
            <h1><bean:message key="ficha.busqueda" /></h1>
        </div>

    </div>
    <br />
    <form >
        <div class="bloc">
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.id" /></div>
                <input type="text" name="idSelect" class="ctext" />
            </div>
        </div>
        <center>
        <div class="botonera">
            <input type="button" value="<bean:message key="boton.seleccionar" />" onclick="tancar();" />
        </div>
        </center>
    </form>
</body>
</html:html>