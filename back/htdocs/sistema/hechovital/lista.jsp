<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="hechovital.lista" /></h1>
    <h2><bean:message key="hechovital.datos" /></h2>
</div>
<br />

<logic:empty name="listaHechos">
    <div class="bloc">
        <br /><h2><bean:message key="hechovital.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaHechos" >
    <bean:size id="hechoOptions" name="listaHechos" />
    <div class="bloc">
        <logic:iterate  id="hecho" name="listaHechos" indexId="ixHecho">
            <bean:define id="idActual" name="hecho" property="id" type="java.lang.Long" />
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=ixHecho.intValue() + 1%>
                    </div>
                    <div class="textgros2" >
                        <bean:write name="hecho" property="traduccion.nombre" />
                    </div>
                </div>
                <div class="botoneraconsulta2">
                    <html:form action="/sistema/hechovital/seleccionar" >
                        <html:hidden property="idSelect" value="<%=idActual.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                        <logic:notEqual name="ixHecho" value="0">
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
[<html:link page="/sistema/hechovital/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
