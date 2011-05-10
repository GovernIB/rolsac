<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html>
<head>
  <!-- The following two lines are required, they instruct the browser to not cache the wait page. -->
  <!-- This way we can better guarantee that when the user hits the 'Back' button, they don't get the wait page instead of the form. -->
  <META http-equiv="CACHE-CONTROL" content="NO-CACHE">  <!-- For HTTP 1.1 -->
  <META http-equiv="PRAGMA" content="NO-CACHE">         <!-- For HTTP 1.0 -->
  <META http-equiv="refresh" content="0; URL=<bean:write name="action_path_key" property="actionPath"/>">
  <title>Espere, por favor</title>
</head>

<body onload="javascript:window.focus();">
	<br />
	<br />
  <table cellpadding="10">
        <tr>
           <td align="center">
           <h4><bean:message key="pagina.espera.mensaje1"/></h4>
           <h4><bean:message key="pagina.espera.mensaje2"/></h4>
           </td>     
        </tr>
  </table>
</body>

</html:html>