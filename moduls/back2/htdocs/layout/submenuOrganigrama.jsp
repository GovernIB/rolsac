<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                    <li><a href="<c:url value="/quadreControl/quadreControl.htm"/>"><spring:message code="submenu.quadre_control" /></a></li>
                </c:otherwise>
            </c:choose>
			
			<c:choose>
                <c:when test="${submenu_seleccionado==1}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.cataleg_procediments" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/catalegProcediments/catalegProcediments.htm"/>"><spring:message code="submenu.cataleg_procediments" /></a></li>
                </c:otherwise>
            </c:choose>
            
			<c:choose>
                <c:when test="${submenu_seleccionado==2}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.unitatAdm" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/unitatadm/unitatadm.htm"/>"><spring:message code="submenu.unitatAdm" /></a></li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${submenu_seleccionado==3}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.fitxes_informatives" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="#"><spring:message code="submenu.fitxes_informatives" /></a></li>
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
                    <li><a href="<c:url value="/normativa/normativa.htm"/>"><spring:message code="submenu.normativa" /></a></li>
                </c:otherwise>
            </c:choose>
            
            <c:choose>
                <c:when test="${submenu_seleccionado==5}">
                    <li class="seleccionat">
                        <span><spring:message code="submenu.personal" /></span>
                        <span class="actual"></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/personal/personal.htm"/>"><spring:message code="submenu.personal" /></a></li>
                </c:otherwise>
            </c:choose>
		</ul>			
	</div>		
</div>
