<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="perfil.lista" /></h1>
    <h2><bean:message key="perfil.datos" /></h2>
</div>
<br />

<logic:empty name="perfilOptions">
    <div class="bloc">
        <br /><h2><bean:message key="perfil.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="perfilOptions">
    <div class="bloc">
        <logic:iterate id="perfil" name="perfilOptions">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="perfil" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/perfil/seleccionar" styleId="perfilForm">
                        <input type="hidden" name="idPerfil" value='<bean:write name="perfil" property="id" />' />
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
[<html:link page="/sistema/perfil/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
