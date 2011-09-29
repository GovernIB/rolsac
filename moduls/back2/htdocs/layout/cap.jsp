<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
String[] roles = new String[]{"RSC_ADMIN","RSC_SYSTEM","RSC_SUPER","RSC_OPER"};
java.util.List<String> rolenames = new java.util.ArrayList<String>(roles.length);
if (request.getRemoteUser() != null) {            
	 for (int i = 0; i < roles.length; i++) {
        if (request.isUserInRole(roles[i])) {
            rolenames.add(roles[i]);
        }
    }
}
%>
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
                <spring:message code="cap.usuari" />: <strong>[<%=request.getRemoteUser() %>]</strong> <a href="" class="btn personalitzar" title="<spring:message code='cap.personalitza_aplicacio'/>"><%=rolenames%></a>                
            </p>
        </div>
        <div id="tancarAplicacio">
            <a href="javascript:;" aria-haspopup="true"><span><span><spring:message code='cap.tancar_aplicacio'/></span></span></a>                             
        </div>
        <!-- dsanchez: Hay que mover el botón otra capa --> 
        <!--
        <div>
            <a id="cap_pestanya" href="javascript:;" title="Amagar capçalera"></a>      
        </div> 
        -->
    </div>
</div>
