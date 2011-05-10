<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="tipo.lista" /></h1>
    <h2><bean:message key="tipo.datos" /></h2>
</div>
<br />

<logic:empty name="tipoOptions">
    <div class="bloc">
        <br /><h2><bean:message key="tipo.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="tipoOptions">
    <div class="bloc">
        <logic:iterate id="tipo" name="tipoOptions">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="tipo" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/tipo/seleccionar" styleId="tipoForm">
                        <input type="hidden" name="idTipo" value='<bean:write name="tipo" property="id" />' />
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
[<html:link page="/sistema/tipo/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
