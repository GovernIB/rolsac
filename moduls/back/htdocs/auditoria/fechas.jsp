<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function validar(form) {
       // return validateAuditoriaForm(form);
       return true;
    }

    // Llamada al popup para seleccionar la fecha de un campo
    function abrirCalendario(field) {
        obrirFixa("<html:rewrite page='/moduls/calendario.do'/>?field=" + field);
    }

    // Llamada para el popup de calendario para fijar la fecha
    function seleccionaDia(data, field) {
        eval("document.forms[0]." + field + ".value=data");
    }
//-->
</script>

<div class="bloc">
	<h1><bean:message key="navges.inicio" /></h1>
	<h2><bean:message key="navges.auditoria" /></h2>
</div>
<br />


<html:form action="/auditoria/buscar" styleId="auditoriaForm" enctype="multipart/form-data">
<div class="bloc">
    <div class="component">
			<div class="etiqueta"><bean:message key="auditoria.fechaIni" /></div>
			<html:text styleClass="ctext" property="textoFechaIni" maxlength="15" tabindex="7" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaIni')" tabindex="8"><bean:message key="boton.seleccionar" /></html:button>
            </div>
	</div>
    <div class="component">
			<div class="etiqueta"><bean:message key="auditoria.fechaFin" /></div>
			<html:text styleClass="ctext" property="textoFechaFin" maxlength="15" tabindex="7" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaFin')" tabindex="8"><bean:message key="boton.seleccionar" /></html:button>
            </div>
	</div>
</div>

<div class="botonera">
    <html:submit property="action" styleClass="dreta" tabindex="102" ><bean:message key="boton.busqueda" /></html:submit>
</div>


</html:form>