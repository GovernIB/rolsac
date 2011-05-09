<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>

<html:xhtml/>
<div class="bloc">
        <h1><bean:message key="historico.lista" /></h1>
</div>
<br />

<logic:empty name="listaHistoricos">
    <div class="bloc">
        <br /><h2><bean:message key="auditoria.vacio" /></h2><br />
    </div>
</logic:empty>
<logic:notEmpty name="listaHistoricos">
    <div class="bloc">
        <logic:iterate id="historico" name="listaHistoricos">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="historico" property="nombre" />
                </div>
                <div class="botoneraconsulta1">
                <bean:define id="idHistorico" name="historico" property="id"/>
                    <html:form action="/auditoria/historico" >
                        <html:hidden property="idSelect" value="<%=idHistorico.toString()%>" />
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
