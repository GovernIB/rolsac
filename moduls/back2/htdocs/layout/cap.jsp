<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>

<div id="cap">
    <div id="cap_contingut">
        <div class="logos">
<%    
String logourl = System.getProperty("entitat.logourl");
String style = "";
if (logourl != null) {
  style = "style=\"background:transparent url(" + logourl + ") 0 0 no-repeat;\"";
}
%>
            <div class="caib_logo" <%=style%>></div>
        </div>
        <div class="aplicacio">
            <span class="nom"><spring:message code='aplicacio.nom'/></span><span class="versio">1.3</span>
        </div>
        <div class="usuari">        
            <p>
                <spring:message code="cap.usuari" />: <strong>[<c:out value="${capNomLlinatges}"/>]</strong> <a href="#" class="btn personalitzar" title="<spring:message code='cap.personalitza_aplicacio'/>"><rol:printRol/></a>
            </p>
            <p>
                <spring:message code='aplicacio.nom'/> <c:out value="${rolsac_einaversion}"/><a href="<bean:write name="rolsac_urlrevision" ignore="true"/>" target="_blank"><bean:write name="rolsac_einarevision" ignore="true"/></a>
			</p>
        </div>
        <div id="tancarAplicacio">
            <a href="javascript:;" aria-haspopup="true"><span><span><spring:message code='cap.sortir'/></span></span></a>                             
        </div>
        <!-- dsanchez: Hay que mover el bot�n otra capa --> 
        <!--
        <div>
            <a id="cap_pestanya" href="javascript:;" title="Amagar cap�alera"></a>      
        </div> 
        -->
    </div>
</div>