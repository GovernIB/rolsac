<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="<c:url value='/css/tm_index.css'/>" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">

	// url
    var txtAmaga = '<spring:message code="txt.amaga" />';
    var txtEnviantDades="<spring:message code='index.missatge.enviant_dades'/>" ;

</script>

<div id="submenu">
	<div id="submenu_contingut">
		<ul>
	        <li class="seleccionat">
	            <span><spring:message code="menu.lopd" /></span>
	            <span class="actual"></span>
	        </li>
        </ul>

	</div>
</div>