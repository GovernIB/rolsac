<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript" src="<c:url value='/js/tm_index.js'/>"></script>
<link href="<c:url value='/css/tm_index.css'/>" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">

	// url
    var pagIndexUnitatOrganica = '<c:url value="/index/reiniciarUnitatOrganica.do" />';
    var pagIndexFitxa = '<c:url value="/index/reiniciarFitxes.do" />';
    var pagIndexNormativa = '<c:url value="/index/reiniciarNormatives.do" />';
    var pagIndexProcediment = '<c:url value="/index/reiniciarProcediments.do" />';
    var txtAmaga = "";
    var txtEnviantDades="<spring:message code='index.missatge.enviant_dades'/>" ;

</script>

<div id="submenu">
	<div id="submenu_contingut">
		<ul>
	        <li class="seleccionat">
	            <span><spring:message code="submenu.index" /></span>
	            <span class="actual"></span>
	        </li>
        </ul>
        <ul id="opcionsIndex">
	        <li class="primer opcio actiu" id="liUnitatOrganica" >
	            <a href="javascript:;" id="tabUnitatOraganica"><spring:message code="submenu.index.unitats_organiques" /></a>	            
	        </li>
	        <li class="opcio " id="liFitxa">
	        	<a href="javascript:;" id="tabFitxa"><spring:message code="submenu.index.fitxes_informatives" /></a>
	        </li>
	        <li class="opcio " id="liNormativa">
	        	<a href="javascript:;" id="tabNormativa"><spring:message code="submenu.index.normatives" /></a>
	        </li>
	        <li class="opcio" id="liProcediment">
	        	<a href="javascript:;" id="tabProcediment"><spring:message code="submenu.index.procediments" /></a>
	        </li>
	        
        </ul>				
	</div>		
</div>
