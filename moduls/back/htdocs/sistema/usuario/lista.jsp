<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="usuario.lista" /></h1>
    <h2><bean:message key="usuario.datos" /></h2>
</div>
<br />

<logic:empty name="listaUsuarios">
    <div class="bloc">
        <br /><h2><bean:message key="usuario.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaUsuarios">
    <div class="bloc">
        <div class="component">
            <div class="textseparat">
                <div class="textpetit1" style="background-color: #d8dfe7">
                    <bean:message key="usuario.usuario" />
                </div>
                <div class="textgros5" style="background-color: #d8dfe7">
                    <bean:message key="usuario.nombre" />
                </div>
            </div>
            <div class="botoneraconsulta1"></div>
        </div>
        <logic:iterate id="usuario" name="listaUsuarios">
            <div class="component">
            <div class="textseparat">
                <div class="textpetit1">
                    <bean:write name="usuario" property="username" />
                </div>
                <div class="textgros5">
                    <bean:write name="usuario" property="nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/usuario/seleccionar" styleId="usuarioForm">
                        <input type="hidden" name="idUsuario" value='<bean:write name="usuario" property="id" />' />
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
[<html:link page="/sistema/usuario/form.do" ><bean:message key="boton.volver" /></html:link>]
</center>
