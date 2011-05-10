<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%--
    listaAntecesores - lista de unidades administrativas padre
    listaUAs - lista de unidades administrativas hijo
--%>
<bean:define id="idPadre" name="id"/>
<html:xhtml/>

<script type="text/javascript">
<!--
    function seleccionar(ua){
        url = '<html:rewrite page="/auditoria/seleccionar.do" />?espera=si&idSelect=' + ua;
        document.location = url;
    }
-->
</script>

<div class="bloc">
	<h1><bean:message key="navges.inicio" /></h1>
	<h2><bean:message key="navges.arbol" /></h2>
</div>
<br />

<p>
     <html:link action="/auditoria/navges/arbol">
        <bean:message key="navges.raiz" />
     </html:link>
     <logic:present name="listaAntecesores">
         <bean:size id="sAntecesores" name="listaAntecesores" />
         <logic:iterate id="antecesor" name="listaAntecesores" indexId="iAntecesores">
                &raquo;
                <logic:equal name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                    <bean:define id="aux"><bean:message key="boton.seleccionar" /></bean:define>
        	    <html:link action='<%="/auditoria/seleccionar?espera=si"%>'
                               paramId="idSelect"
                               paramName="antecesor"
                               paramProperty="id">
                    <b><bean:write name="antecesor" property="traduccion.nombre" /></b>
                    </html:link>
                </logic:equal>
                <logic:notEqual name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                    <html:link action="/auditoria/navges/arbol.do"
                               paramId="idUA"
                               paramName="antecesor"
                               paramProperty="id">
                    <bean:write name="antecesor" property="traduccion.nombre" />
                    </html:link>
                </logic:notEqual>
         </logic:iterate>
    </logic:present>
</p>

<br />

<logic:empty name="listaUAs">
    <div class="bloc">
        <br /><h2><bean:message key="ua.vacioh" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaUAs" >
    <div class="bloc">
    <bean:size id="uaOptions" name="listaUAs" />
        <logic:iterate  id="ua" name="listaUAs" indexId="inUA">
            <bean:define id="idActual" name="ua" property="id"/>
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=inUA.intValue() + 1%>
                    </div>
                    <div class="textgros1">
                        <html:link action="/auditoria/navges/arbol"
                                   paramId="idUA"
                                   paramName="ua"
                                   paramProperty="id">
                        <bean:write name="ua" property="traduccion.nombre" />
                        </html:link>
                    </div>
                </div>
                <div class="bototext">
                    <input type="button" name="select" onclick="seleccionar('<bean:write name="ua" property="id"/>')" value='<bean:message key="boton.seleccionar" />' />
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />


<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>