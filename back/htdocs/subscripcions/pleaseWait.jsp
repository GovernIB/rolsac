<%@ page language="java"%>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<% String context=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	  <META http-equiv="CACHE-CONTROL" content="NO-CACHE">  <!-- For HTTP 1.1 -->
	  <META http-equiv="PRAGMA" content="NO-CACHE">         <!-- For HTTP 1.0 -->
	  <META http-equiv="refresh" content="0; URL=<bean:write name="action_path_key" property="actionPath"/>">	
	<title>Gestor Microsites</title>
	<link href="<%=context%>/css/estils.css" rel="stylesheet" type="text/css" />
</head>

<body onload="javascript:window.focus();">	
	<!-- molla pa -->
	<ul id="mollapa">
		<li><a href="/subscripcions/index_inicio.do"><bean:message  bundle="subscripcions" key="op.7" /></a></li>
		<li class="pagActual"><bean:message  bundle="subscripcions" key="menu.mensaje" />.</li>
	</ul>
	<!-- titol pagina -->
	<h1><bean:message  bundle="subscripcions" key="pleasewait.espere" />........ </h1>
	
	  <table cellpadding="10">
	        <tr>
	           <td align="center"><h2><bean:message  bundle="subscripcions" key="pleasewait.peticion" />...</h2></td>     
	        </tr>
	        <tr>
	        	<td align="center"><h2><bean:message  bundle="subscripcions" key="pleasewait.favor" /></h2></td>
	        </tr>
	  </table>


</body>
</html>

