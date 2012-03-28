<%@page import="es.caib.loginModule.client.SeyconPrincipal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>

<%
SeyconPrincipal seyconPrincipal = SeyconPrincipal.getCurrent();
String nomComplet = seyconPrincipal.getFullName();
//<c:out value="${pageContext.session.remoteUser}"/>
%>

<div id="cap">
    <div id="cap_contingut">
        <div class="logos">
            <div class="caib_logo"></div>
        </div>
        <div class="aplicacio">
            <span class="nom">Rolsac</span> <span class="versio">1.1</span>
        </div>
        <div class="usuari">        
            <p>
                <spring:message code="cap.usuari" />: <strong>[<%=nomComplet%>]</strong> <a href="#" class="btn personalitzar" title="<spring:message code='cap.personalitza_aplicacio'/>"><rol:printRol/></a>
            </p>
        </div>
        <div id="tancarAplicacio">
            <a href="javascript:;" aria-haspopup="true"><span><span><spring:message code='cap.tancar_intranet'/></span></span></a>                             
        </div>
        <!-- dsanchez: Hay que mover el botón otra capa --> 
        <!--
        <div>
            <a id="cap_pestanya" href="javascript:;" title="Amagar capçalera"></a>      
        </div> 
        -->
    </div>
</div>