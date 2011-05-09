<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="familia.lista" /></h2>
    <h2><bean:message key="familia.datos" /></h2>
</div>
<br />

<logic:empty name="familiaOptions">
    <div class="bloc">
        <br /><h2><bean:message key="familia.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="familiaOptions">
    <div class="bloc">
        <logic:iterate id="familia" name="familiaOptions">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="familia" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/familia/seleccionar" styleId="familiaForm">
                        <input type="hidden" name="idSelect" value='<bean:write name="familia" property="id" />' />
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
[<html:link page="/sistema/familia/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
