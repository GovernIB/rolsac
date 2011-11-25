<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p id="index"><spring:message code='index.reinicar_index'/></p>
<div id="escriptori_index">
	<!-- inici dades -->
	<div style="display: block;" id="dadesIndex">
		<!-- index -->
	 	<div id="index" class="modul m100">
	 		<div id="opcionsindex">
				<div class="opcionsindex icona actiu" id="divUnitatOrganica" style="display: block;">
					<spring:message code='index.missatge.unitat_organica'/>
		 			<a href="javascript:;" class="btnIndex unitatOrganica "><span><span><spring:message code='boto.continuar'/></span></span></a>
		 			<%--
		 			<div class="botoneraIndex">
						<div class="btnGenericoIndex">
						 
					    </div>
					</div>
					--%>	
				</div> 
				
				<div class="opcionsindex icona" style="display: none;" id="divFitxa">
					<spring:message code='index.missatge.fitxa_informativa'/>
					<a href="javascript:;" class="btnIndex fitxa "><span><span><spring:message code='boto.continuar'/></span></span></a>
		 			<%--
		 			<div class="botoneraIndex">
						<p class="btnGenericoIndex">
						   
					    </p>
					</div>
					--%>
				</div>
				
				<div class="opcionsindex icona" style="display: none;" id="divNormativa">
					<spring:message code='index.missatge.normativa'/>
					<a href="javascript:;" class="btnIndex normativa "><span><span><spring:message code='boto.continuar'/></span></span></a>
		 			<%-- 
		 			<div class="botoneraIndex">
						<div class="btnGenericoIndex">
						  
					    </div>
					</div>
					--%>
				</div>
				
				<div class="opcionsindex icona" style="display: none;" id="divProcediment">
					<spring:message code='index.missatge.procediment'/>
					<a href="javascript:;" class="btnIndex procediment "><span><span><spring:message code='boto.continuar'/></span></span></a>
		 			<%-- 
		 			<div class="botoneraIndex">
						<div class="btnGenericoIndex">
						   
					    </div>
					</div>
					--%>
				</div>
			</div>
	 	</div>
	 	<!-- /index -->
	</div> 
</div>