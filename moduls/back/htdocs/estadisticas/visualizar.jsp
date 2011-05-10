<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function abrirProcedimiento(idProc){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idProcedimiento=" + idProc, "<bean:message key='boton.relacionar' />", 538, 300);
    }

    function abrirNormativa(idNorm){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idNormativa=" + idNorm, "<bean:message key='boton.relacionar' />", 538, 300);
    }

    function abrirFicha(idFic){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idFicha=" + idFic, "<bean:message key='boton.relacionar' />", 538, 300);
    }

    function abrirMateria(idMat){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idMateria=" + idMat, "<bean:message key='boton.relacionar' />", 538, 400);
    }

    function abrirFichaPorUA(idFic, idUA){
       obrir("<html:rewrite page='/estadisticas/popventana.do'/>?idFicha=" + idFic + "&idFicUA=" + idUA, "<bean:message key='boton.relacionar' />", 538, 300);
    }

// -->
</script>
<bean:define id="idUA" name="idUAdmin" ></bean:define>
<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>

<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="estadisticas.titulo" /></h1>
    <h2><bean:message key="estadisticas.valor" /></h2>
</div>

<br />

<%session.setAttribute("action_path_key",null);%>

<logic:notEmpty name="idUAdmin">
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="estadisticas.ua" />
        </div>
        <br />
        <center>
            <html:img page="/estadisticas/graficoua.do"
                      paramId="id"
                      paramName="idUA" />
        </center>
        <br />
    </div>
</logic:notEmpty>

<logic:empty name="idUAdmin">
    <div class="bloc">
        <bean:message key="estadisticas.valor" />
    </div>
</logic:empty>


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


<logic:notEmpty name="listaNormativas">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="estadisticas.normativa" />
        </div>
        <logic:iterate id="normativa" name="listaNormativas">
            <div class="component">
                <div class="textconsulta1">
                    <logic:equal name="normativa" property="class" value="class org.ibit.rol.sac.model.NormativaLocal" >
                        <html:link action='<%="/contenido/normativa/local/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="normativa"paramProperty="id">
                            <bean:write name="normativa" property="traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                    <logic:equal name="normativa" property="class" value="class org.ibit.rol.sac.model.NormativaExterna" >
                        <html:link action='<%="/contenido/normativa/externa/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="normativa"paramProperty="id">
                            <bean:write name="normativa" property="traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                </div>
                <div class="botoneraconsulta1">
                    <input type="button" onclick="abrirNormativa(<bean:write name="normativa" property="id" />)" value="<bean:message key="boton.seleccionar" />" />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>


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
                    <input type="button" onclick="abrirFichaPorUA(<bean:write name="ficha" property="id" />, <bean:write name="idUA" />)" value="<bean:message key="estadisticas.porua" />" />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

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
                           paramId="idSelect" paramName="mat" paramProperty="materia.id">
                    	<bean:write name="mat" property="materia.traduccion.nombre" />
                	</html:link>
                </div>
                <div class="botoneraconsulta1">
                    <input type="button" onclick="abrirMateria(<bean:write name="mat" property="materia.id" />)" value="<bean:message key="boton.seleccionar" />" />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>