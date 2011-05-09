<%@ page language="java"%>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>

<logic:present name="contenidoHtml">
<bean:write name="contenidoHtml" filter="false"/>
</logic:present>
<logic:notPresent name="contenidoHtml">
Error
</logic:notPresent>
