<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<c:set var="id_collection" value="id_${param.padreActual}" scope="page" />
<c:set var="collection" value="${requestScope[id_collection]}" />

 
<c:forEach items="${collection}" var="hijo">
	<c:set var="idActual" value="${hijo.id}" />
	<c:set var="actual" value="${hijo.id}" />
	
	<c:set var="id_collFill" value="id_${actual}" scope="page" />
	<c:set var="collFill" value="${requestScope[id_collFill]}" />
				
				
	<c:if test="${param.inicial != idActual}">
		<c:choose>
	    	<c:when test="${collFill != null}">
	    		ex.push(new init_ex("<c:out value='${param.nivel}' />",true,"<c:out value='${hijo.traduccion.nombre}' />",true,"<c:out value='${hijo.id}' />"));
	    	</c:when>
		    <c:otherwise>
		    	<c:set var="auxIdH" value="" />
				<c:set var="auxNombreH" value="" />
				<c:set var="hayHijosH" value="no" />
	    		<c:forEach items="${tieneHijos}" var="hijo1">
	    			<c:if test="${hijo1 == actual}" >
		    			<c:set var="auxIdH" value="${hijo.id}"  />
						<c:set var="auxNombreH" value="${hijo.traduccion.nombre}" />		
						<c:set var="hayHijosH" value="si" />		
	    			</c:if>
	    		</c:forEach>
	    		<c:choose>
   					<c:when test="${hayHijosH == 'si'}">
   						ex.push(new init_ex("<c:out value='${param.nivel}' />",false,"<c:out value='${auxNombreH}' />",true,"<c:out value='${auxIdH}' />"));
				    </c:when>
				    <c:otherwise>
				    	ex.push(new init_ex("<c:out value='${param.nivel}' />",false,"<c:out value='${hijo.traduccion.nombre}' />",false,"<c:out value='${hijo.id}' />"));
				    </c:otherwise>
				</c:choose>
		    </c:otherwise>
		</c:choose>
		<c:if test="${not empty collFill}" >
			<c:import url = "/pantalles/popFillsUA.jsp">
				<c:param name = "padreActual" value = "${actual}" />
				<c:param name = "nivel" value = "${param.nivel + 1}" />
				<c:param name = "inicial" value = "${param.inicial}" />
			</c:import>
		</c:if>
	</c:if>	
</c:forEach>
