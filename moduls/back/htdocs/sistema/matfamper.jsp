<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>

<tiles:insert definition=".sistema.agrupacionm.lista" /><br />
<tiles:insert definition=".sistema.materia.lista" /><br />
<tiles:insert definition=".sistema.familia.lista" /><br />
<tiles:insert definition=".sistema.perfil.lista" /><br />

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
