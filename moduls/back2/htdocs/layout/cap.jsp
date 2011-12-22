<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<div id="cap">
    <div id="cap_contingut">
        <div class="logos">
            <div class="caib_logo"></div>
        </div>
        <div class="aplicacio">
            <span class="nom">Rolsac</span> <span class="versio">2.0</span>
        </div>
        <div class="usuari">        
            <p>
                <spring:message code="cap.usuari" />: <strong>[<c:out value="${pageContext.request.remoteUser}"/>]</strong> <a href="#" class="btn personalitzar" title="<spring:message code='cap.personalitza_aplicacio'/>"><rol:printRol/></a>
            </p>
        </div>
        <div id="tancarAplicacio">
            <a href="javascript:;" aria-haspopup="true"><span><span><spring:message code='cap.tancar_aplicacio'/></span></span></a>                             
        </div>
        <!-- dsanchez: Hay que mover el bot�n otra capa --> 
        <!--
        <div>
            <a id="cap_pestanya" href="javascript:;" title="Amagar cap�alera"></a>      
        </div> 
        -->
    </div>
</div>