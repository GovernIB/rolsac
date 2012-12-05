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
								<a href="<c:url value="/agrupacioMateries/agrupacioMateries.do"/>"><spring:message code="menu.agrupacio_materies" /></a>
							</li>
							<li>
								<a href="<c:url value="/materies/materia.do"/>"><spring:message code="menu.materies" /></a>
							</li>
							<li>
								<a href="<c:url value="/fetsVitals/fetsVitals.do"/>"><spring:message code="menu.fets_vitals" /></a>
							</li>
							<li>
								<a href="<c:url value="/agrupacioFetsVitals/agrupacioFetsVitals.do"/>"><spring:message code="menu.agrupacio_fets_vitals" /></a>
							</li>
							<li>
								<a href="<c:url value="/publicObjectiu/publicObjectiu.do"/>"><spring:message code="menu.public_objectiu" /></a>
							</li>
							<li>
								<a href="<c:url value="/espaisTerritorials/espaiTerritorial.do"/>"><spring:message code="menu.espais_territorials" /></a>
							</li>
							<li>
								<a href="<c:url value="/perfils/perfils.do"/>"><spring:message code="menu.perfils" /></a>
							</li>
							<li>
								<a href="<c:url value="/destinataris/destinataris.do"/>"><spring:message code="menu.destinatari" /></a>
							</li>
							<li>
								<a href="<c:url value="/administracioRemota/administracioRemota.do"/>"><spring:message code="menu.administracio_remota" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.procediments" /></span>
						<ul>
							<li>
								<a href="<c:url value="/familia/familia.do"/>"><spring:message code="menu.familia" /></a>
							</li>
							<li>
								<a href="<c:url value="/tipusIniciacio/tipusIniciacions.do"/>"><spring:message code="menu.tipus_iniciacio" /></a>
							</li>
							<li>
								<a href="<c:url value="/catalegDocuments/catalegDocuments.do"/>"><spring:message code="menu.cataleg_documents" /></a>
							</li>
							<li>
								<a href="<c:url value="/excepcioDocumentacio/excepcioDocumentacio.do"/>"><spring:message code="menu.excepcio_documentacio" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.unitat_organica" /></span>
						<ul>
							<li>
								<a href="<c:url value="/tipusUnitat/tipusUnitat.do"/>"><spring:message code="menu.tipus_unitat" /></a>
							</li>
							<li>
								<a href="<c:url value="/edifici/edifici.do"/>"><spring:message code="menu.edificis" /></a>
							</li>
						</ul>
					</li>
					<li>
						<span class="titol"><spring:message code="menu.fitxes_informatives" /></span>
						<ul>
							<li>
								<a href="<c:url value="/seccions/seccio.do"/>"><spring:message code="menu.seccions" /></a>
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
								<a href="<c:url value="/tipusAfectacio/tipusAfectacions.do"/>"><spring:message code="menu.tipus_afectacio" /></a>
							</li>
							<li>
								<a href="<c:url value="/butlletinsOficials/butlletins.do"/>"><spring:message code="menu.butlletins_oficials" /></a>
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
	                        <a href="<c:url value="/usuaris/usuaris.do"/>"><spring:message code="menu.usuaris" /></a>
	                        <span class="cueta"></span>
	                    </li>
	                </c:otherwise>
	            </c:choose>
			</c:if>	       
		</ul>
		<div id="cap_pestanya">
            <a href="javascript:void(0)" title="<spring:message code="cap.amagar_capsalera" />"></a>      
        </div>			
	</div>		
</div>