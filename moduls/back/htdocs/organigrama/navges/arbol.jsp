<%@ page import="org.ibit.rol.sac.model.UnidadAdministrativaRemota" %>
<%@ page import="org.ibit.rol.sac.model.UnidadAdministrativa" %>
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

<div class="bloc">
	<h1><bean:message key="navges.inicio" /></h1>
	<h2><bean:message key="navges.arbol" /></h2>
</div>
<br />

<p>
    <html:link action="/organigrama/navges">
        <bean:message key="navges.raiz" />
    </html:link>
     <logic:present name="listaAntecesores">
        <bean:size id="sAntecesores" name="listaAntecesores" />
        <logic:iterate id="antecesor" name="listaAntecesores" indexId="iAntecesores">
            &raquo;

            <logic:equal name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                <bean:define id="aux"><bean:message key="boton.seleccionar" /></bean:define>
                <bean:define id="idUA" name="antecesor" property="id"/>
						<% 					
						    java.util.HashMap params = new java.util.HashMap();
							params.put("idUA", idUA);
							params.put("espera","si");
							pageContext.setAttribute("paramsName", params);
						%>			
                        <html:link action='<%="/organigrama/unidad/editar?action=" + aux%>'
                                   name="paramsName"
                                   scope="page">
                        <b><bean:write name="antecesor" property="traduccion.nombre" /></b>
                        </html:link>
                
                <!--  
                <html:link action='<%="/organigrama/unidad/editar?action=" + aux%>'
                           paramId="idUA"
                           paramName="antecesor"
                           paramProperty="id">
                <b><bean:write name="antecesor" property="traduccion.nombre" /></b>
                </html:link>
                -->
            </logic:equal>
            <logic:notEqual name="iAntecesores" value="<%=Integer.toString(sAntecesores.intValue() - 1)%>">
                <html:link action="/organigrama/navges.do"
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
            <% if (ua instanceof UnidadAdministrativaRemota) {%>
                <div class="component">
                    <div class="textseparat">
                        <div class="textpetit">
                            <%=inUA.intValue() + 1%>
                        </div>
                        <div class="textgros1">
                            <html:link action="/organigrama/navges"
                                       paramId="idUA"
                                       paramName="ua"
                                       paramProperty="id">
                            <bean:write name="ua" property="traduccion.nombre" />&nbsp;(<bean:message key="ua.id" />:<bean:write name="ua" property="id" />)&nbsp;<b><bean:message key="ua.remota"/></b>
                            </html:link>
                        </div>
                    </div>
                    <div class="botoneraconsulta1">
                        <html:form action="/organigrama/unidad/editar">
                            <html:hidden property="idUA" value="<%=idActual.toString()%>" />
                            <html:hidden property="espera" value="si" />
                            <html:submit property="action">
                                <bean:message key="boton.seleccionar" />
                            </html:submit>
                        </html:form>
                    </div>
                </div>
            <% }else {%>
                <div class="component">
                    <div class="textseparat">
                        <div class="textpetit">
                            <%=inUA.intValue() + 1%>
                        </div>
                        <div class="textgros1">
                            <html:link action="/organigrama/navges"
                                       paramId="idUA"
                                       paramName="ua"
                                       paramProperty="id">
                            <bean:write name="ua" property="traduccion.nombre" />&nbsp;(<bean:message key="ua.id" />:<bean:write name="ua" property="id" />)
                            </html:link>
                        </div>
                    </div>
                    <div class="botoneraconsulta1">
                        <html:form action="/organigrama/unidad/editar">
                            <html:hidden property="idUA" value="<%=idActual.toString()%>" />
                            <html:hidden property="espera" value="si" />
                            <html:submit property="action">
                                <bean:message key="boton.seleccionar" />
                            </html:submit>
                        </html:form>
                    </div>
                </div>
            <% }%>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />

<center>
<logic:present role="sacsystem">
[<html:link page="/organigrama/unidad/form.do" paramId="idPadre" paramName="id">
    <bean:message key="boton.nuevo" />
</html:link>]
</logic:present>
<logic:notPresent role="sacsystem">
    <logic:notEmpty name="idPadre">
    [<html:link page="/organigrama/unidad/form.do" paramId="idPadre" paramName="id">
        <bean:message key="boton.nuevo" />
    </html:link>]
    </logic:notEmpty>
</logic:notPresent>
</center>


<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>