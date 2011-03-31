<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="edificio.lista" /></h1>
    <h2><bean:message key="edificio.datos" /></h2>
</div>
<br />

<logic:empty name="listaEdificios">
    <div class="bloc">
        <br /><h2><bean:message key="edificio.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaEdificios">
    <div class="bloc">
        <logic:iterate id="edificio" name="listaEdificios">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="edificio" property="direccion" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/edificio/seleccionar" styleId="edificioForm">
                        <input type="hidden" name="idSelect" value='<bean:write name="edificio" property="id" />' />
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
[<html:link page="/organigrama/edificio/form.do" ><bean:message key="boton.volver" /></html:link>]
</center>