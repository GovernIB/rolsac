<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<div id="submenu">
	<div id="submenu_contingut">
		<ul>
		    <c:choose>
                <c:when test="${submenu_seleccionado==0}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.quadre_control" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/quadreControl/quadreControl.do"/>"><spring:message code="submenu.quadre_control" /></a></li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${submenu_seleccionado==1}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.unitatAdm" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/unitatadm/unitatadm.do"/>"><spring:message code="submenu.unitatAdm" /></a></li>
                </c:otherwise>
            </c:choose>
            			
			<c:choose>
                <c:when test="${submenu_seleccionado==2}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.cataleg_procediments" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/catalegProcediments/catalegProcediments.do"/>"><spring:message code="submenu.cataleg_procediments" /></a></li>
                </c:otherwise>
            </c:choose>
            <!-- 
            <c:choose>
                <c:when test="${submenu_seleccionado==6}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.cataleg_serveis" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/catalegServeis/catalegServeis.do"/>"><spring:message code="submenu.cataleg_serveis" /></a></li>
                </c:otherwise>
            </c:choose>-->
            <c:choose>
                <c:when test="${submenu_seleccionado==3}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.fitxes_informatives" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/fitxainf/fitxainf.do"/>"><spring:message code="submenu.fitxes_informatives" /></a></li>
                </c:otherwise>
            </c:choose>
            
            <c:choose>
                <c:when test="${submenu_seleccionado==4}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.normativa" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/normativa/normativa.do"/>"><spring:message code="submenu.normativa" /></a></li>
                </c:otherwise>
            </c:choose>
            
            <c:set var="mostrarPersonal"><rol:userIsSuper/></c:set>
            <c:if test="${mostrarPersonal}" >
	            <c:choose>
	                <c:when test="${submenu_seleccionado==5}">
	                    <li class="seleccionat">
	                        <span><spring:message code="submenu.personal" /></span>
	                        <span class="actual"></span>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li><a href="<c:url value="/personal/personal.do"/>"><spring:message code="submenu.personal" /></a></li>
	                </c:otherwise>
	            </c:choose>
            </c:if>
		</ul>			
	</div>		
</div>
