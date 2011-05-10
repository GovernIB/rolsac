<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<bean:define id="idPadre" name="idEspacio"/>
<html:xhtml/>
<!-- (PORMAD) -->
<div class="bloc">
    <h1><bean:message key="espacio.lista" /></h1>
    <h2><bean:message key="espacio.datos" /></h2>
</div>
<br />


<p>
     <html:link action="/sistema/espacio/lista">
        Raiz
     </html:link>
     <logic:present name="listaAntecesores">
         <bean:size id="sAntecesores" name="listaAntecesores" />
         <logic:iterate id="antecesor" name="listaAntecesores" indexId="iAntecesores">
                &raquo;
                <logic:equal name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                    <bean:define id="aux"><bean:message key="boton.seleccionar" /></bean:define>
                    <html:link action='<%="/sistema/espacio/seleccionar?action=" + aux%>'
                               paramId="idSelect"
                               paramName="antecesor"
                               paramProperty="id">
                    <b><bean:write name="antecesor" property="traduccion.nombre" /></b>
                    </html:link>
                </logic:equal>
                <logic:notEqual name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                    <html:link action="/sistema/espacio/lista.do"
                               paramId="idEspacio"
                               paramName="antecesor"
                               paramProperty="id">
                    <bean:write name="antecesor" property="traduccion.nombre" />
                    </html:link>
                </logic:notEqual>
         </logic:iterate>
     </logic:present>
</p>
<br />


<logic:empty name="listaEspacios">
    <div class="bloc">
        <br /><h2><bean:message key="espacio.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaEspacios" >
    <div class="bloc">
    <bean:size id="espacioOptions" name="listaEspacios" />
        <logic:iterate  id="espacio" name="listaEspacios" indexId="inEspacio">
            <bean:define id="idActual" name="espacio" property="id"/>
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=inEspacio.intValue() + 1%>
                    </div>
                    <div class="textgros2">
                        <html:link action="/sistema/espacio/lista"
                                   paramId="idEspacio"
                                   paramName="espacio"
                                   paramProperty="id">
                        <bean:write name="espacio" property="traduccion.nombre" />
                        </html:link>
                    </div>
                </div>
                <div class="botoneraconsulta2">
                    <html:form action="/sistema/espacio/seleccionar">
                        <html:hidden property="idSelect" value="<%=idActual.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />
<center>
[<html:link page="/sistema/espacio/form.do" paramId="idPadre" paramName="idEspacio">
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
