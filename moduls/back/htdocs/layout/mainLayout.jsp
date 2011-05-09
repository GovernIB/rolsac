<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:html locale="true" xhtml="true">
<head>
   <title>SAC</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleA.css"/>' type="text/css" />
</head>
<body>
    <div class="identificacio" id="cidentificacio"><tiles:insert attribute="identificacion" /></div>
    <div class="menu" id="cmenu"><tiles:insert attribute="menu" /></div>
    <div id="ccontingut"><tiles:insert attribute="contenido" /></div>
</body>
</html:html>
