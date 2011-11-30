<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<div id="menu">

    <!-- Ejemplo para consultar el permiso para saber si mostrar opciones del menu o no -->
    <!-- p>sacsystem: <%= request.isUserInRole("sacsystem")%></p -->


 	<c:set var="rolSystem"><rol:userIsSystem/></c:set>
 	<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>

	<div id="menu_contingut">			
		<ul>
            <c:choose>
                <c:when test="${menu==0}">
                    <li class="seleccionat">
                        <span class="text"><spring:message code="menu.organigrama" /></span>
                        <span class="cueta"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="<c:url value="/quadreControl/quadreControl.do"/>"><spring:message code="menu.organigrama" /></a>
                        <span class="cueta"></span>
                    </li>
                </c:otherwise>
            </c:choose>
		    <c:if test="${rolSystem || rolAdmin}" >	
			    <c:choose>
	                <c:when test="${menu==1}">                                        
	                    <li class="seleccionat desplegable">
	                </c:when>
	                <c:otherwise>
	                    <li class="desplegable">
	                </c:otherwise>
	            </c:choose>
				<a id="taules_mestre" href="javascript:;"><spring:message code="menu.administracio" /></a>
				<span class="cueta"></span>		
				<ul id="taulesMestre">
					<li>
						<span class="titol"><spring:message code="menu.sistema_generals" /></span>
						<ul>
							<li>
								<a href="<c:url value="/agrupacioMateries/llistat.do"/>"><spring:message code="menu.agrupacio_materies" /></a>
							</li>
							<li>
								<a href="<c:url value="/materies/llistat.do"/>"><spring:message code="menu.materies" /></a>
							</li>
							<li>
								<a href="<c:url value="/fetsVitals/llistat.do"/>"><spring:message code="menu.fets_vitals" /></a>
							</li>
							<li>
								<a href="<c:url value="/agrupacioFetsVitals/llistat.do"/>"><spring:message code="menu.agrupacio_fets_vitals" /></a>
							</li>
							<li>
								<a href="<c:url value="/publicObjectiu/llistat.do"/>"><spring:message code="menu.public_objectiu" /></a>
							</li>
							<li>
								<a href="<c:url value="/espaisTerritorials/llistat.do"/>"><spring:message code="menu.espais_territorials" /></a>
							</li>
							<li>
								<a href="<c:url value="/perfils/llistat.do"/>"><spring:message code="menu.perfils" /></a>
							</li>
							<li>
								<a href="<c:url value="/destinataris/llistat.do"/>"><spring:message code="menu.destinatari" /></a>
							</li>
							<li>
								<a href="<c:url value="/administracioRemota/llistat.do"/>"><spring:message code="menu.administracio_remota" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.procediments" /></span>
						<ul>
							<li>
								<a href="<c:url value="/familia/llistat.do"/>"><spring:message code="menu.familia" /></a>
							</li>
							<li>
								<a href="<c:url value="/tipusIniciacio/llistat.do"/>"><spring:message code="menu.tipus_iniciacio" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.unitat_organica" /></span>
						<ul>
							<li>
								<a href="<c:url value="/tipusUnitat/llistat.do"/>"><spring:message code="menu.tipus_unitat" /></a>
							</li>
							<li>
								<a href="<c:url value="/edifici/llistat.do"/>"><spring:message code="menu.edificis" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.fitxes_informatives" /></span>
						<ul>
							<li>
								<a href="<c:url value="/seccions/llistat.do"/>"><spring:message code="menu.seccions" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.normativa" /></span>
						<ul>
							<li>
								<a href="<c:url value="/tipusNormatives/tipusNormatives.do"/>"><spring:message code="menu.tipus_normatives" /></a>
							</li>
							<li>
								<a href="<c:url value="/tipusAfectacio/llistat.do"/>"><spring:message code="menu.tipus_afectacio" /></a>
							</li>
							<li>
								<a href="<c:url value="/butlletinsOficials/llistat.do"/>"><spring:message code="menu.butlletins_oficials" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.index" /></span>
						<ul>
							<li>
								<a href="<c:url value="/index/index.do"/>"><spring:message code="menu.reiniciar_index" /></a>
							</li>
						</ul>
					</li>
				</ul>
										
					
				<c:choose>
	                <c:when test="${menu==2}">
	                    <li class="seleccionat">
	                        <span class="text"><spring:message code="menu.usuaris" /></span>
	                        <span class="cueta"></span>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li>
	                        <a href="<c:url value="/usuaris/llistat.do"/>"><spring:message code="menu.usuaris" /></a>
	                        <span class="cueta"></span>
	                    </li>
	                </c:otherwise>
	            </c:choose>
					
				<%--				
				<c:choose>
	                <c:when test="${menu==3}">
	                    <li class="seleccionat">
	                        <span class="text"><spring:message code="menu.administracio" /></span>
	                        <span class="cueta"></span>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li>
	                        <a href="<c:url value="/administracio/llistat.do"/>"><spring:message code="menu.administracio" /></a>
	                        <span class="cueta"></span>
	                    </li>
	                </c:otherwise>
	            </c:choose>
	            --%>
			</c:if>	       
		</ul>
		<div id="cap_pestanya">
            <a href="javascript:void(0)" title="<spring:message code="cap.amagar_capsalera" />"></a>      
        </div>			
	</div>		
</div>