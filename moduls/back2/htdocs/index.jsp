<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Tipus Unitats Administratives - ROLSAC - Govern de les Illes Balears</title>
    <link href="<c:url value='/img/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
    <link href="<c:url value='/css/comuns.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/comuns_amplaria1.css'/>" rel="stylesheet" type="text/css" media="screen" />  
    <link href="<c:url value='/css/titol.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/menu.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/submenu.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/jquery-ui.css'/>" rel="stylesheet" type="text/css"/> 
    <script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/seekAttention.min.jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.maskedinput-1.2.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/comuns.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/inici.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/error.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/listado_base.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/detall_base.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/multipagina.js'/>"></script>    
    <jsp:include page="layout/variablesGlobalsJavascript.jsp" flush="true"/>    
</head>
    <body>
        <div id="contenidor">       
            <jsp:include page="layout/cap.jsp" flush="true"/>
            <c:choose>
                <c:when test="${error=='permisos'}">
                    <p><spring:message code="error.permisos.accedir"/></p>
                </c:when>
                <c:when test="${error=='altres'}">
                    <p><spring:message code="error.altres"/></p>
                </c:when>
                <c:otherwise>
                    <jsp:include page="layout/menu.jsp" flush="true"/>
                    <jsp:include page="layout/mollapa.jsp" flush="true"/>
                    <c:import url="${submenu}" /> <!-- jsp:include page="layout/submenu.jsp" flush="true"/-->
                    <div role="content" id="continguts">
                        <jsp:include page="layout/titol.jsp" flush="true"/>
                        <div id="escriptori">               
                            <c:import url="${escriptori}" />                                                    
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <jsp:include page="layout/peu.jsp" flush="true"/>               
        </div>
    </body>
</html>