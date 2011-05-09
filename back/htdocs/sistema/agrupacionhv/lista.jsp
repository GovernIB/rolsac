<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<!-- (PORMAD) -->
<div class="bloc">
    <h1><bean:message key="agrupacion.lista" /></h1>
    <h2><bean:message key="agrupacion.datos" /></h2>
</div>
<br />

<logic:empty name="agrupacionOptions">
    <div class="bloc">
        <br /><h2><bean:message key="agrupacion.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="agrupacionOptions">
    <div class="bloc">
        <logic:iterate id="agrupacion" name="agrupacionOptions">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="agrupacion" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/agrupacion/seleccionar" styleId="agrupacionForm">
                        <input type="hidden" name="idSelect" value='<bean:write name="agrupacion" property="id" />' />
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
[<html:link page="/sistema/agrupacion/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
