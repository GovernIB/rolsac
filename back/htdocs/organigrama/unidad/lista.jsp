<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<div class="bloc">
    <h1><bean:message key="ua.lista" /></h1>
    <h2><bean:message key="ua.datos" /></h2>
</div>
<br />

<logic:empty name="listaUAs">
    <div class="bloc">
        <br /><h2><bean:message key="ua.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaUAs">
    <div class="bloc">
        <logic:iterate id="ua" name="listaUAs">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="ua" property="traduccion.nombre" />&nbsp;(<bean:message key="ua.id" />:<bean:write name="ua" property="id" />)
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/unidad/editar" styleId="unidadForm">
                        <input type="hidden" name="idUA" value='<bean:write name="ua" property="id" />' />
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
[<html:link page="/organigrama/unidad/form.do" ><bean:message key="boton.volver" /></html:link>]
</center>
