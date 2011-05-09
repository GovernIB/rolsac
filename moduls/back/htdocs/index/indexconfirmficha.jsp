<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    // Función para redireccionar al action de reindexar fichas
    function reiniciarIndex(){
       document.location="<html:rewrite page='/index/ficha/reiniciar.do'/>";
    }
// -->
</script>
<div class="bloc">
    <h1>
        <bean:message key="index.reiniciar" />
    </h1>
</div>
<br/>
<div><center><bean:message key="index.confirmacio.ficha"/></center></div>
<div class="component">
    <div class="botoneraconsulta1">
        <html:button property="boton" onclick="reiniciarIndex()" ><bean:message key="boton.continuar" /></html:button>
    </div>
</div>
