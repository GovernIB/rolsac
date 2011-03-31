<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--

    function abrirFicha(idFic){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idFicha=" + idFic, "<bean:message key='boton.relacionar' />", 538, 300);
    }

    function abrirProcedimiento(idProc){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idProcedimiento=" + idProc, "<bean:message key='boton.relacionar' />", 538, 300);
    }

    function abrirFichaPorMateria(idFic, idMat){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idFicha=" + idFic + "&idFicMat=" + idMat, "<bean:message key='boton.relacionar' />", 538, 300);
    }


// -->
</script>
<bean:define id="idMat" name="idMateria" ></bean:define>
<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>

<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="estadisticas.titulo" /></h1>
    <h2><bean:message key="estadisticas.valor" /></h2>
</div>

<br />

<%session.setAttribute("action_path_key",null);%>

<logic:notEmpty name="idMateria">
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="estadisticas.materia" />
        </div>
        <br />
        <center>
            <html:img page="/estadisticas/graficomat.do"
                      paramId="id"
                      paramName="idMat" />
        </center>
        <br />
    </div>
</logic:notEmpty>

<logic:empty name="idMateria">
    <div class="bloc">
        <bean:message key="estadisticas.materia" />
    </div>
</logic:empty>

<logic:notEmpty name="listaFichas">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="estadisticas.ficha" />
        </div>
        <logic:iterate id="ficha" name="listaFichas">
            <div class="component">
                <div class="textconsulta2">
                <html:link action='<%="/contenido/ficha/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="ficha"paramProperty="id">
                    	<bean:write name="ficha" property="traduccion.titulo" />
                </html:link>
                </div>
                <div class="botoneraconsulta2">
                    <input type="button" onclick="abrirFicha(<bean:write name="ficha" property="id" />)" value="<bean:message key="boton.general" />" />
                    <input type="button" onclick="abrirFichaPorMateria(<bean:write name="ficha" property="id" />, <bean:write name="idMat" />)" value="<bean:message key="estadisticas.pormateria" />" />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<logic:notEmpty name="listaProcedimientos">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="estadisticas.procedimiento" />
        </div>
        <logic:iterate id="procedimiento" name="listaProcedimientos">
            <div class="component">
                <div class="textconsulta1">
                <html:link action='<%="/contenido/procedimiento/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="procedimiento"paramProperty="id">
                    <bean:write name="procedimiento" property="traduccion.nombre" />
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <input type="button" onclick="abrirProcedimiento(<bean:write name="procedimiento" property="id"/>)" value="<bean:message key="boton.seleccionar" />" />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>