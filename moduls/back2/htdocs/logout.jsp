<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head>
   <title><spring:message code='aplicacio.logout.sesio_finalitzada'/></title>
   <meta http-equiv="Content-type" content='text/html; charset="ISO-8859-1"' />
</head>
<body class="ventana">
	<center>
	    <h1><spring:message code='aplicacio.logout.sesio_finalitzada'/></h1>
	    <a href="<c:url value="/"/>">[<spring:message code='aplicacio.tornar'/>]</a>
	</center>
</body>
</html>
