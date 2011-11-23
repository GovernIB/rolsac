<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

	<link href="<c:url value='/css/quadre_control.css'/>" rel="stylesheet" type="text/css" media="screen" />
	<p id="quadreControl"><spring:message code='quadreControl.grafica_resum'/></p>
	<!-- /mostrat tots els nodes -->
	<c:if test="${not empty idUA}">
		<div id="totsElements" class="check">
			<c:if test="${not empty allUA }">
				<!-- dentro -->
				<c:set value="checked=checked" var="checked" />
			</c:if>
			<input type="checkbox" name="allUA" id="allUA" value="S" <c:out value="${checked}" /> />
			<span><spring:message code='quadreControl.tots_nodes'/></span>
	    </div>
    </c:if>
	<!-- /mostrat tots els nodes -->
	<!-- escriptori_contingut -->
	<div id="escriptori_contingut">
					
		<div style="display: none;" id="carregantDades"><spring:message code='quadreControl.carregant_dades'/></div>
					
		<!-- inici dades -->
		<div style="display: block;" id="iniciDades"> 
			<!-- darreres modificacions -->
			<div id="darreresModificacions" class="modul m50">
				<h2><spring:message code='quadreControl.darreres_modificacions'/></h2>
				<c:choose>
					<c:when test="${not empty darreresModificacions}">
						<ul>
							<c:forEach items="${darreresModificacions}" var="modificacio">
								<c:choose>
									<c:when test="${modificacio.value.class.name == 'org.ibit.rol.sac.model.HistoricoFicha'}">
										<c:choose>
											<c:when test="${modificacio.value.ficha.fechaActualizacion < dataActual}">
												<li class="fitxa_caducat">
											</c:when>
											<c:otherwise>
												<li class="fitxa">
											</c:otherwise>
										</c:choose>
										<span class="tipus"><spring:message code='quadreControl.fitxa'/></span>. 
										<span class="data"><c:out value="${modificacio.key}" /> h.</span>
										<a href="<c:url value="/fitxainf/fitxainf.do" />?itemId=<c:out value="${modificacio.value.ficha.id }" />">(<c:out value="${modificacio.value.ficha.id }" />) <c:out value="${modificacio.value.nombre }" /></a></li>
									</c:when>
									<c:when test="${modificacio.value.class.name == 'org.ibit.rol.sac.model.HistoricoProcedimiento'}">
										<c:choose>
											<c:when test="${modificacio.value.procedimiento.fechaActualizacion < dataActual}">
												<li class="procediment_caducat">
											</c:when>
											<c:otherwise>
												<li class="procediment">
											</c:otherwise>
										</c:choose>
										<span class="tipus"><spring:message code='quadreControl.procediment'/></span>. 
										<span class="data"><c:out value="${modificacio.key}" /> h.</span>
										<a href="<c:url value="/catalegProcediments/catalegProcediments.do" />?itemId=<c:out value="${modificacio.value.procedimiento.id }" />">(<c:out value="${modificacio.value.procedimiento.id }" />) <c:out value="${modificacio.value.nombre }" /></a></li>
									</c:when>
									<c:when test="${modificacio.value.class.name == 'org.ibit.rol.sac.model.HistoricoNormativa'}">
										<li class="normativa"><span class="tipus"><spring:message code='quadreControl.normativa'/></span>. 
										<span class="data"><c:out value="${modificacio.key}" /> h.</span>
										<a href="<c:url value="/normativa/normativa.do" />?itemId=<c:out value="${modificacio.value.normativa.id }" />">(<c:out value="${modificacio.value.normativa.id }" />) <c:out value="${modificacio.value.nombre }" /></a></li>
									</c:when>
								</c:choose>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<spring:message code='quadreControl.no_darreres_modificacions'/>
					</c:otherwise>
				</c:choose>
				
			</div>
			<!-- /darreres modificacions -->
				
			<!-- continguts -->
			<div id="nombreContinguts" class="modul m50">	
				<h2><spring:message code='quadreControl.nombre_continguts'/></h2>
				<table>
					<thead>
						<tr>
							<th class="tipus"><spring:message code='quadreControl.tipus'/></th>
							<th><spring:message code='quadreControl.tipus.actius'/></th>
							<th><spring:message code='quadreControl.tipus.caducats'/></th>
							<th><spring:message code='quadreControl.tipus.total'/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><a href="<c:url value="/catalegProcediments/catalegProcediments.do"/>"><spring:message code='quadreControl.procediments'/></a></th>
							<td><c:out value="${procedimentActiu}" /></td>
							<td><c:out value="${procedimentCaducat}" /></td>
							<td class="total"><c:out value="${procedimentTotal}" /></td>
						</tr>
						<tr>
							<th><a href="<c:url value="/normativa/normativa.do"/>"><spring:message code='quadreControl.normatives'/></a></th>
							<td><c:out value="${normativaActiva}" /></td>
							<td>0</td>
							<td class="total"><c:out value="${normativaActiva}" /></td>
						</tr>
						<tr>
							<th><a href="<c:url value="/fitxainf/fitxainf.do"/>"><spring:message code='quadreControl.fitxes'/></a></th>
							<td><c:out value="${fitxaActiva}" /></td>
							<td><c:out value="${fitxaCaducada}" /></td>
							<td class="total"><c:out value="${fitxaTotal}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /continguts -->
			
			<%-- Recorrer tot arbre UA --%>
	 		<c:if test="${not empty allUA }">
	 			<c:set value="&allUA=S" var="totUA" />
	 		</c:if>
	 		<%-- /Recorrer tot arbre UA --%>
	 		
	 		<!-- grafica estadistiques UA -->
	 		<div id="graficaEstadistiques" class="modul m100">
	 			<h2><spring:message code='quadreControl.grafica.estadistica_ua'/><c:out value="${nomUA }" /></h2>
	 			<c:choose>
	 				<c:when test="${idUA > 0}">
	 					<img alt="" src="<c:url value="/quadreControl/grafica.do?tipoOperacion=1&id=${idUA }${totUA }"/>" border="0" />
	 				</c:when>
	 				<c:otherwise>
	 					<spring:message code='quadreControl.grafica.escull_ua'/>
	 				</c:otherwise>
	 			</c:choose>
	 		</div>
	 		<!-- /grafica estadistiques UA -->	

			<!-- grafica resum -->
	 		<div id="graficaResum" class="modul m100">
	 			<h2><spring:message code='quadreControl.grafica.resum'/></h2>
	 			<c:choose>
	 				<c:when test="${idUA > 0}">
	 					<ul id="opcions">
					        <li class="opcio actiu" id="liAlta">
					            <a href="javascript:void(0)" id="tabAlta">Altes</a>
					        </li>
					        <%--
					        <li class="opcio" id="liBaixa">
					            <a href="javascript:;" id="tabBaixa">Baixes</a>
					        </li>
					        --%>
				            <li class="opcio" id="liModificacio">
				            	<a href="javascript:;" id="tabModificacio">Modificacions</a>	
				            </li>
				            
					    </ul>
					    <div id="resultats">
					        <div class="resultats actiu" id="divAlta" style="display: block;">
				            	<img alt="" src="<c:url value="/quadreControl/grafica.do?tipoOperacion=2&id=${idUA }${totUA }"/>" border="0" />			 				
					        </div>
					        <%--
					        <div class="resultats " style="display: none;" id="divBaixa">
					        	<img alt="" src="<c:url value="/quadreControl/grafica.do?tipoOperacion=3&id=${idUA }${totUA }"/>" border="0" />
					        </div>
					        --%> 
					        <div class="resultats " style="display: none;" id="divModificacio">
					        	<img alt="" src="<c:url value="/quadreControl/grafica.do?tipoOperacion=4&id=${idUA }${totUA }"/>" border="0" />
					        </div>
					        
					    </div>	
	 				</c:when>
	 				<c:otherwise>
	 					<spring:message code='quadreControl.grafica.escull_ua'/>
	 				</c:otherwise>
	 			</c:choose>
	 		</div>
	 		<!-- /grafica resum-->

		</div>
		<!-- /inici dades -->
				
	</div>
	<!-- /escriptori_contingut -->
    
	<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/quadre_control.js'/>"></script>
    
    <script type="text/javascript">
           
        // texts
        var txtEspere = "<spring:message code='txt.esperi'/>";
        var txtCarregant = "<spring:message code='txt.carregant'/>";
        var txtSi = "<spring:message code='txt.si'/>";
        var txtNo = "<spring:message code='txt.no'/>";
       
    </script>