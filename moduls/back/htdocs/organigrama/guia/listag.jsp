<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<br />
<br />

<div class="bloc">
	<h1><bean:message key="guia.lista" /></h1>
	<h2><bean:message key="guia.datos" /></h2>
</div>
<br />

<logic:empty name="listaGuia">
<div class="bloc">
    <br /><h2>
    <logic:present name="idUA">
        <bean:message key="guia.vacio" />
    </logic:present>
    <logic:notPresent name="idUA">
        <bean:message key="guia.otraua" />
    </logic:notPresent>
    </h2><br />
</div>
</logic:empty>

<logic:notEmpty name="listaGuia">
    <div class="bloc">
        <logic:iterate id="guia" name="listaGuia">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="guia" property="nombre"  />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/guia/editar" styleId="personalForm">
                        <input type="hidden" name="idPersonal" value='<bean:write name="guia" property="id" />' />
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
<logic:present name="idUA">
[<html:link page="/organigrama/guia/form.do" paramId="idUA" paramName="idUA">
    <bean:message key="boton.nuevo" />
</html:link>]
</logic:present>
</center>