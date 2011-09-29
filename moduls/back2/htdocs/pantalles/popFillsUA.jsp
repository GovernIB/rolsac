<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>

<tiles:useAttribute name="padreActual" />
<tiles:useAttribute name="nivel" classname="java.lang.Integer" />
<tiles:useAttribute name="inicial" classname="java.lang.Long" />
<bean:define id="tieneHijos" name="tieneHijos" type="java.util.List" />
<logic:iterate id="hijo" name="<%=padreActual.toString()%>" indexId="idx" >
    <bean:define id="idActual" name="hijo" property="id" type="java.lang.Long"/>
    <bean:define id="actual" name="hijo" property="id" type="java.lang.Long" />
    <%if (!idActual.equals(inicial)){%>
        <logic:present name="<%=actual.toString()%>">
            ex.push(new init_ex(<%=nivel%>,true,"<bean:write name='hijo' property='traduccion.nombre' />",true,"<bean:write name='hijo' property='id' />"));
        </logic:present>
        <logic:notPresent name="<%=actual.toString()%>">
            <% if (tieneHijos.contains(actual)) { %>
                ex.push(new init_ex(<%=nivel%>,false,"<bean:write name='hijo' property='traduccion.nombre' />",true,"<bean:write name='hijo' property='id' />"));
            <% } else { %>
                ex.push(new init_ex(<%=nivel%>,false,"<bean:write name='hijo' property='traduccion.nombre' />",false,"<bean:write name='hijo' property='id' />"));
            <% } %>
        </logic:notPresent>
        <logic:notEmpty name="<%=actual.toString()%>">
            <tiles:insert page="//pantalles/popFillsUA.jsp" flush="false" >
                <tiles:put name="padreActual" value="<%=actual.toString()%>" />
                <tiles:put name="inicial" value="<%=inicial%>" />
                <tiles:put name="nivel" value="<%=new Integer(nivel.intValue() + 1)%>" />
            </tiles:insert>
        </logic:notEmpty>
    <%
    }
    %>
</logic:iterate>
--%>
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
				<c:param name = "inicial" value = "${inicial}" />
			</c:import>
		</c:if>
	</c:if>	
</c:forEach>
