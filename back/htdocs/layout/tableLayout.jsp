<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:html locale="true" xhtml="true">
<head>
   <title>SAC</title>
   <meta http-equiv="Content-type" content='text/html; charset="ISO-8859-1"' />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleOld.css"/>' type="text/css" />
</head>
<body>
    <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td id="identificacion" width="20%"><tiles:insert attribute="identificacion" /></td>
            <td rowspan="2" width="80%" id="contenido" class="contenido"><center><tiles:insert attribute="contenido" /></center></td>
        </tr>
        <tr>
            <td id="menu" width="20%" class="menu"><tiles:insert attribute="menu" /></td>
        </tr>
    </table>
</body>
</html:html>