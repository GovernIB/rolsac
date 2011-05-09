<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="materia.lista" /></h1>
    <h2><bean:message key="materia.datos" /></h2>
</div>
<br />

<logic:empty name="materiaOptions">
    <div class="bloc">
        <br /><h2><bean:message key="materia.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="materiaOptions">
    <div class="bloc">
        <logic:iterate id="materia" name="materiaOptions">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="materia" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/materia/seleccionar" styleId="materiaForm">
                        <input type="hidden" name="idSelect" value='<bean:write name="materia" property="id" />' />
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
[<html:link page="/sistema/materia/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
