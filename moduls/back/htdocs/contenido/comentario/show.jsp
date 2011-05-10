<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<jsp:useBean id="comentario" type="org.ibit.rol.sac.model.Comentario" scope="request"/>
<html:html locale="true" xhtml="true">
<head>
   <title>Comentario</title>
   <meta content="text/html; charset=ISO-8859-15" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
        <h1><bean:write name="comentario" property="titulo" /></h1>
    </div>
    <br />

    <div class="bloc">
        <p><bean:write name="comentario" property="contenido" /></p><br />
        <em>
            <bean:write name="comentario" property="autor" />,
            <bean:write name="comentario" property="fecha" format="dd/MM/yyyy HH:mm" />
        </em>
    </div>
    <center>
    <div class="botonera">
        <button type="button" onclick="window.close();"><bean:message key="boton.cerrar" /></button>
    </div>
    </center>
</div>
</body>
</html:html>
