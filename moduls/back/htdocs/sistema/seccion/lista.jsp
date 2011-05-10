<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<bean:define id="idPadre" name="idSeccion"/>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="seccion.lista" /></h1>
    <h2><bean:message key="seccion.datos" /></h2>
</div>
<br />


<p>
     <html:link action="/sistema/seccion/lista">
        Raiz
     </html:link>
     <logic:present name="listaAntecesores">
         <bean:size id="sAntecesores" name="listaAntecesores" />
         <logic:iterate id="antecesor" name="listaAntecesores" indexId="iAntecesores">
                &raquo;
                <logic:equal name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                    <bean:define id="aux"><bean:message key="boton.seleccionar" /></bean:define>
                    <html:link action='<%="/sistema/seccion/seleccionar?action=" + aux%>'
                               paramId="idSelect"
                               paramName="antecesor"
                               paramProperty="id">
                    <b><bean:write name="antecesor" property="traduccion.nombre" /></b>
                    </html:link>
                </logic:equal>
                <logic:notEqual name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                    <html:link action="/sistema/seccion/lista.do"
                               paramId="idSeccion"
                               paramName="antecesor"
                               paramProperty="id">
                    <bean:write name="antecesor" property="traduccion.nombre" />
                    </html:link>
                </logic:notEqual>
         </logic:iterate>
     </logic:present>
</p>
<br />


<logic:empty name="listaSecciones">
    <div class="bloc">
        <br /><h2><bean:message key="seccion.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaSecciones" >
    <div class="bloc">
    <bean:size id="seccionOptions" name="listaSecciones" />
        <logic:iterate  id="seccion" name="listaSecciones" indexId="inSeccion">
            <bean:define id="idActual" name="seccion" property="id"/>
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=inSeccion.intValue() + 1%>
                    </div>
                    <div class="textgros2">
                        <html:link action="/sistema/seccion/lista"
                                   paramId="idSeccion"
                                   paramName="seccion"
                                   paramProperty="id">
                        <bean:write name="seccion" property="traduccion.nombre" />
                        </html:link>
                    </div>
                </div>
                <div class="botoneraconsulta2">
                    <html:form action="/sistema/seccion/seleccionar">
                        <html:hidden property="idSelect" value="<%=idActual.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                        <logic:notEqual name="inSeccion" value="0">
                            <html:hidden property="idSubir" value="<%=idActual.toString()%>" />
                            <html:hidden property="idSeccion" value="<%=idPadre.toString()%>" />
                            <html:submit property="action">
                                <bean:message key="boton.subir" />
                            </html:submit>
                        </logic:notEqual>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />
<center>
[<html:link page="/sistema/seccion/form.do" paramId="idPadre" paramName="idSeccion">
    <bean:message key="boton.nuevo" />
</html:link>]
</center>


<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
