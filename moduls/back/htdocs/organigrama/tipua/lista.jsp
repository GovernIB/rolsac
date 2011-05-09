<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>


<div class="bloc">
	<h1><bean:message key="tipua.lista" /></h1>
	<h2><bean:message key="tipua.datos" /></h2>
</div>
<br />


<logic:empty name="listaTipuas">
    <bean:message key="tipua.vacio" />
</logic:empty>

<logic:notEmpty name="listaTipuas">
    <div class="bloc">
        <logic:iterate id="tipua" name="listaTipuas">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="tipua" property="traduccion.tipo"  />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/tipua/editar" styleId="tipuaForm">
                        <input type="hidden" name="idTipua" value='<bean:write name="tipua" property="id" />' />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<br/>
<br/>