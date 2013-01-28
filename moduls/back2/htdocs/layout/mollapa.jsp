<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="ua" uri="/WEB-INF/ua.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="mollaPa">
	<div id="mollaPa_contingut">
		<ul class="molla">    		
    		<li><a id="mollapaHome" href="javascript:void(0)"><spring:message code="mollapa.inici" /></a></li>
    		
    		<%--
    		<!-- dsanchez: MAQUETACIÓN ESTÁTICA -->
    		<li class="ua">
		    	<a href="javascript:void(0)">Govern de les Illes Balears</a>
	    	</li>
	    	
	    	<li class="ua seleccionat">
	    		<!-- dsanchez: El campo data-clave_ua_padre debe contener la clave de la UA padre, 
	    			 en este ejemplo "Govern de les Illes Balears" (1) -->
	    		<a data-clave_ua_padre="1" href="javascript:void(0)">Presidència</a>
    		</li> 
		    	
	    	<li class="uaHijas">
	    		<!-- dsanchez: El campo data-clave_ua_padre debe contener la clave de la UA padre, 
	    			 en este ejemplo "Presidència" (2)-->
	    		<a data-clave_ua_padre="2" class="btn uaFilles" href="javascript:void(0)">Unitats filles</a>
    		</li>
		    <!-- /MAQUETACIÓN ESTÁTICA -->
		    --%>
    		
		    <ua:crearMollapa/>
		</ul>								
	</div>	
</div>
