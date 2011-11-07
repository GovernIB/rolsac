<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

	<link href="<c:url value='/css/quadre_control.css'/>" rel="stylesheet" type="text/css" media="screen" />
	<p id="quadreControl"><spring:message code='quadreControl.grafica_resum'/></p>
	

	<!-- escriptori_contingut -->
	<div id="escriptori_contingut">
					
		<div style="display: none;" id="carregantDades"><spring:message code='quadreControl.carregant_dades'/></div>
					
		<!-- inici dades -->
		<div style="display: block;" id="iniciDades"> 
			<!-- darreres modificacions -->
			<div style="-moz-border-radius: 1em 1em 1em 1em;" id="darreresModificacions" class="modul m50">
				<h2><spring:message code='quadreControl.darreres_modificacions'/></h2>
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
								<span class="tipus"><spring:message code='quadreControl.fitxa'/></span>. <span class="data"><c:out value="${modificacio.key}" /> h.</span><a href="<c:out value="${modificacio.value.id }" />">(<c:out value="${modificacio.value.id }" />) <c:out value="${modificacio.value.nombre }" /></a></li>
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
								<span class="tipus"><spring:message code='quadreControl.procediment'/></span>. <span class="data"><c:out value="${modificacio.key}" /> h.</span><a href="<c:out value="${modificacio.value.id }" />">(<c:out value="${modificacio.value.id }" />) <c:out value="${modificacio.value.nombre }" /></a></li>
							</c:when>
							<c:when test="${modificacio.value.class.name == 'org.ibit.rol.sac.model.HistoricoNormativa'}">
								<li class="normativa"><span class="tipus"><spring:message code='quadreControl.normativa'/></span>. <span class="data"><c:out value="${modificacio.key}" /> h.</span><a href="<c:out value="${modificacio.value.id }" />">(<c:out value="${modificacio.value.id }" />) <c:out value="${modificacio.value.nombre }" /></a></li>
							</c:when>
						</c:choose>
					</c:forEach>

					<!-- 
					<li class="procediment"><span class="tipus">Procediment</span>. <span class="data">25/05/2010 - 10:30 h.</span><a href="http://maq-rolsac.at4.net/procediments.php?/5">(5) Procediment exemple</a></li>
					<li class="fitxa"><span class="tipus">Fitxa informativa</span>. <span class="data">24/05/2010 - 15:22 h.</span><a href="http://maq-rolsac.at4.net/fitxes.php?/4">(4) Títol de la fitxa exemple</a></li>
					<li class="normativa"><span class="tipus">Normativa</span>. <span class="data">07/02/2010 - 18:31 h.</span><a href="http://maq-rolsac.at4.net/normativa.php?/10">(10) Títol exemple de la Normativa</a></li>
					<li class="procediment_caducat"><span class="tipus">Procediment</span>. <span class="data">16/03/2010 - 22:09 h.</span><a href="http://maq-rolsac.at4.net/procediments.php?/4">(4) Procediment exemple</a></li>
					<li class="fitxa_caducat"><span class="tipus">Fitxa informativa</span>. <span class="data">02/10/2009 - 09:47 h.</span><a href="http://maq-rolsac.at4.net/fitxes.php?/5">(5) Títol de la fitxa exemple caducada</a></li>
					 -->
				</ul>
			</div>
			<!-- /darreres modificacions -->
				
			<!-- continguts -->
			<div style="-moz-border-radius: 1em 1em 1em 1em;" id="nombreContinguts" class="modul m50">	
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
			<%-- 			
			<!-- grafica planificacio -->
			<div style="-moz-border-radius: 1em 1em 1em 1em;" id="graficaPlanificacio" class="modul">
							
				<h2>Planificació</h2>
			<div class="planificacio">
				<div class="dades">
					<ul class="tipus"><li>Procediments</li><li>Normativa</li><li>Fitxes informatives</li></ul>
					<ul class="dades"><li>
					<div class="P"></div><div class="N"></div><div class="F"><a href="javascript:;" class="btn fitxa fitxa_caducat"><span><span>&nbsp;</span></span></a></div></li><li><div class="P"></div><div class="N"></div><div class="F"></div></li><li><div class="P"><a href="javascript:;" class="btn procediment"><span><span>&nbsp;</span></span></a></div><div class="N"></div><div class="F"></div></li><li><div class="P"></div><div class="N"></div><div class="F"><a href="javascript:;" class="btn fitxa"><span><span>&nbsp;</span></span></a></div></li><li><div class="P"></div><div class="N"></div><div class="F"></div></li><li><div class="P"></div><div class="N"></div><div class="F"></div></li><li><div class="P"></div><div class="N"><a href="javascript:;" class="btn normativa"><span><span>&nbsp;</span></span></a></div><div class="F"></div></li><li><div class="P"></div><div class="N"></div><div class="F"></div></li><li><div class="P"><a href="javascript:;" class="btn procediment procediment_caducat"><span><span>&nbsp;</span></span></a></div><div class="N"></div><div class="F"></div></li></ul></div><div class="faldo"><ul class="dades"><li><span class="avui">Avuí</span></li><li><span class="avui">Demà</span></li><li><span>14<br>Agost<br>2011</span></li><li><span>15<br>Agost<br>2011</span></li><li><span>16<br>Agost<br>2011</span></li><li><span>17<br>Agost<br>2011</span></li><li><span>18<br>Agost<br>2011</span></li><li><span>19<br>Agost<br>2011</span></li><li><span>20<br>Agost<br>2011</span></li></ul></div></div><div style="-moz-border-radius: 1em 1em 1em 1em; -moz-box-shadow: 0pt 0.5em 1.5em rgb(100, 100, 100);" id="planificacio_detall"></div></div>
			<!-- grafica planificacio -->
						
			<!-- grafica resum -->
			<div style="-moz-border-radius: 1em 1em 1em 1em;" id="graficaResum" class="modul">
				<h2>Gràfica resum última setmana</h2>
				<ul class="opcions">
					<li class="seleccionat">
						<span class="text">Diari</span>
					</li>
					<li>
						<a href="javascript:;">Mensual</a>
					</li>
					<li>
						<a href="javascript:;">Anual</a>
					</li>
				</ul>
				<div class="grafica"><div class="dades"><ul class="num"><li>10</li><li>5</li></ul><ul class="dades"><li><div class="P" title="Procediments = 10"><span style="height: 0em;"></span>P</div><div class="N" title="Normativa = 2"><span style="height: 8em;"></span>N</div><div class="F" title="Fitxes informatives = 3"><span style="height: 7em;"></span>F</div></li><li><div class="P" title="Procediments = 10"><span style="height: 0em;"></span>P</div><div class="N" title="Normativa = 2"><span style="height: 8em;"></span>N</div><div class="F" title="Fitxes informatives = 3"><span style="height: 7em;"></span>F</div></li><li><div class="P" title="Procediments = 5"><span style="height: 5em;"></span>P</div><div class="N" title="Normativa = 3"><span style="height: 7em;"></span>N</div><div class="F" title="Fitxes informatives = 7"><span style="height: 3em;"></span>F</div></li><li><div class="P" title="Procediments = 4"><span style="height: 6em;"></span>P</div><div class="N" title="Normativa = 0"><span style="height: 10em;"></span>N</div><div class="F" title="Fitxes informatives = 10"><span style="height: 0em;"></span>F</div></li><li><div class="P" title="Procediments = 2"><span style="height: 8em;"></span>P</div><div class="N" title="Normativa = 2"><span style="height: 8em;"></span>N</div><div class="F" title="Fitxes informatives = 3"><span style="height: 7em;"></span>F</div></li><li><div class="P" title="Procediments = 8"><span style="height: 2em;"></span>P</div><div class="N" title="Normativa = 6"><span style="height: 4em;"></span>N</div><div class="F" title="Fitxes informatives = 1"><span style="height: 9em;"></span>F</div></li><li><div class="P" title="Procediments = 1"><span style="height: 9em;"></span>P</div><div class="N" title="Normativa = 7"><span style="height: 3em;"></span>N</div><div class="F" title="Fitxes informatives = 6"><span style="height: 4em;"></span>F</div></li></ul></div><div class="faldo"><ul class="dades"><li><span class="avui">Avuí</span></li><li><span>11<br>Agost<br>2011</span></li><li><span>10<br>Agost<br>2011</span></li><li><span>09<br>Agost<br>2011</span></li><li><span>08<br>Agost<br>2011</span></li><li><span>07<br>Agost<br>2011</span></li><li><span>06<br>Agost<br>2011</span></li></ul></div></div></div>
			<!-- /grafica resum -->
			--%>
		</div>
		<!-- /inici dades -->
				
	</div>
	<!-- /escriptori_contingut -->
    
	<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
    
    <script type="text/javascript">
           
        // texts
        var txtEspere = "<spring:message code='txt.esperi'/>";
        var txtCarregant = "<spring:message code='txt.carregant'/>";
        var txtSi = "<spring:message code='txt.si'/>";
        var txtNo = "<spring:message code='txt.no'/>";
       
    </script>