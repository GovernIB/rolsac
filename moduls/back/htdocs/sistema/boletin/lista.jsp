<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <h1><bean:message key="boletin.lista" /></h1>
    <h2><bean:message key="boletin.datos" /></h2>
</div>
<br />

<logic:empty name="boletinOptions">
    <div class="bloc">
        <br /><h2><bean:message key="boletin.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="boletinOptions">
    <div class="bloc">
        <logic:iterate id="boletin" name="boletinOptions">
        <div class="component">
            <div class="textconsulta1">
                <bean:write name="boletin" property="nombre" />
            </div>
            <div class="botoneraconsulta1">
                <html:form action="/sistema/boletin/seleccionar" styleId="boletinForm">
                    <input type="hidden" name="idBoletin" value='<bean:write name="boletin" property="id" />' />
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
[<html:link page="/sistema/boletin/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
