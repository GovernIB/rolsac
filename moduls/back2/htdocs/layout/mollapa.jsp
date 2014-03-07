<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="ua" uri="/WEB-INF/ua.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="mollaPa">
	<div id="mollaPa_contingut">
		<ul class="molla">    		
    		<li class="ua inicio">
    			<div>
    				<a data-clave_ua_padre="" id="mollapaHome" href="javascript:void(0)"><spring:message code="mollapa.inici" /></a>
				</div>
			</li>
				
            <ua:crearMollapa/>
    		
    		<%--
    		<!-- dsanchez: MAQUETACI�N EST�TICA -->
    		
    		<li class="ua">
    			<div>
	    			<a class="ua" href="/rolsacback/unidadAdministrativa/cambiarUA.do?ua=1&redirectTo=http://caibrolsac.in.at4.net:8080/rolsacback/quadreControl/quadreControl.do">Govern de les Illes Balears</a>
	    			<a class="desplegar" href="javascript:void(0)"></a>
    			</div>
	    	</li>

			<!-- dsanchez: El campo data-clave_ua_padre debe contener la clave de la UA padre, en este ejemplo "Govern de les Illes Balears" (1) -->
                     	    	
	    	<li class="ua seleccionat" data-clave_ua_padre="1">
	    		<div>
			 		<a class="ua" href="/rolsacback/unidadAdministrativa/cambiarUA.do?ua=2&redirectTo=http://caibrolsac.in.at4.net:8080/rolsacback/quadreControl/quadreControl.do">Presid�ncia</a>
			 		<a class="desplegar" href="javascript:void(0)"></a>
		 		</div>
    		</li> 

			<!-- dsanchez: El campo data-clave_ua_padre debe contener la clave de la UA padre, 
	    			 en este ejemplo "Presid�ncia" (2)-->		    	
	    	<li class="uaHijas" data-clave_ua_padre="2">
	    		<div>
		    		<a class="btn uaFilles" href="javascript:void(0)">Unitats filles</a>
	    		</div>
    		</li>

		    <!-- /MAQUETACI�N EST�TICA -->
		    --%>
		    
		</ul>								
	</div>	
</div>
