<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
        <h1><bean:message key="auditoria.lista" /></h1>
</div>
<br />
<logic:empty name="listaAuditorias">
    <div class="bloc">
        <br /><h2><bean:message key="auditoria.vacio" /></h2><br />
    </div>
</logic:empty>
<logic:notEmpty name="listaAuditorias">
    <div class="bloc">
        <logic:notEmpty name="ficha" >
              <h2><bean:write name="ficha" property="traduccion.titulo"/></h2>
        </logic:notEmpty>
        <logic:notEmpty name="procedimiento" >
            <h2><bean:write name="procedimiento" property="traduccion.nombre"/></h2>
        </logic:notEmpty>
        <logic:notEmpty name="normativa" >
                <h2><bean:write name="normativa" property="traduccion.titulo"/></h2>
        </logic:notEmpty>
        <div class="component">
            <div class="textseparat">
                <div class="texte1parts" style="background-color: #d8dfe7">
                    <bean:message key="auditoria.usuario.titulo" />
                </div>
                <div class="texte2parts" style="background-color: #d8dfe7">
                    <bean:message key="auditoria.fecha.titulo" />
                </div>
                <div class="texte3parts" style="background-color: #d8dfe7">
                    <bean:message key="auditoria.operacion.titulo" />
                </div>
            </div>
            <div class="botoneraconsulta1"></div>
        </div>
        <logic:iterate id="auditoria" name="listaAuditorias">
			<div class="component">
				<div class="textseparat">
                <bean:define id="codigo" name="auditoria" property="codigoOperacion"/>
                   <% String operacio = "op."+codigo;%>
					<div class="texte1parts"><bean:write name="auditoria" property="usuario" /></div>
					<div class="texte2parts"><bean:write name="auditoria" property="fecha" formatKey="date.short.format"/></div>
					<div class="texte3parts"><bean:message key="<%=operacio%>"/></div>
				</div>
			</div>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />