<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>


<% String context=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Subscripcions</title>
	<link href="<%=context%>/css/estils.css" rel="stylesheet" type="text/css" />
</head>

<body>	
	<!-- molla pa -->
	<ul id="mollapa">
		<li><a href="<%=context%>/subscripcions/index_inicio.do"><bean:message  bundle="subscripcions" key="op.7" /></a></li>
		<li class="pagActual"><bean:message  bundle="subscripcions" key="menu.mensaje" />.</li>
	</ul>
	<!-- titol pagina -->
	<h1><bean:message  bundle="subscripcions" key="menu.mensaje" />. </h1>
	
		<div class="alerta" style="font-weight:bold; color:#FF1111;">
			<html:messages id="message" message="true"  bundle="subscripcions">
			<%= message %><br/>
			</html:messages>		
		</div>


</body>
</html>