<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<!-- (PORMAD) -->
<tiles:insert definition=".sistema.hechovital.lista" /><br />
<tiles:insert definition=".sistema.agrupacion.lista" /><br />
<tiles:insert definition=".sistema.publico.lista" /><br />

<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>
