<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
   function seleccionar(mat){
        url = '<html:rewrite page="/estadisticasmateria/seleccionar.do" />?espera=si&idSelect=' + mat;
        document.location = url;
    }

// -->
</script>

<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>

<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="estadisticas.materias" /></h1>
</div>

<br />

<logic:notEmpty name="listaMats">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="estadisticas.materia" />
        </div>
        <logic:iterate id="mat" name="listaMats">
            <div class="component">
                <div class="textconsulta1">
                	<html:link action='<%="/sistema/materia/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="mat" paramProperty="id">
                    	<bean:write name="mat" property="traduccion.nombre" />
                	</html:link>
                </div>
                
                <div class="botoneraconsulta1">
                    <input type="button" name="select" onclick="seleccionar(<bean:write name="mat" property="id" />)" value="<bean:message key="boton.seleccionar" />" />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>