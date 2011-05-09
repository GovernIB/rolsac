<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
        <h1><bean:message key="auditoria.lista" /></h1>
        <h2><bean:message key="auditoria.ua" /></h2>
</div>
<br />
<%session.setAttribute("action_path_key",null);%>
<logic:empty name="listaAuditorias">
    <div class="bloc">
        <br /><h2><bean:message key="auditoria.vacio" /></h2><br />
    </div>
</logic:empty>
<logic:notEmpty name="listaAuditorias">
    <div class="bloc">
        <h2><bean:write name="unidad" property="traduccion.nombre"/></h2>
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

<logic:notEmpty name="listaProcedimientos">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="auditoria.procedimiento" />
        </div>
        <logic:iterate id="procedimiento" name="listaProcedimientos">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="procedimiento" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                     <bean:define id="idProc" name="procedimiento" property="id"/>
                     <html:form action="/auditoria/procedimiento">
                        <html:hidden property="idProc" value="<%=idProc.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<logic:notEmpty name="listaNormativas">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="auditoria.normativa" />
        </div>
        <logic:iterate id="normativa" name="listaNormativas">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="normativa" property="traduccion.titulo" />
                </div>
                <div class="botoneraconsulta1">
                    <bean:define id="idNorm" name="normativa" property="id"/>
                     <html:form action="/auditoria/normativa">
                        <html:hidden property="idNorm" value="<%=idNorm.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<logic:notEmpty name="listaFichas">
    <br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="auditoria.ficha" />
        </div>
        <logic:iterate id="ficha" name="listaFichas">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="ficha" property="traduccion.titulo" />
                </div>
                <div class="botoneraconsulta1">
                    <bean:define id="idFicha" name="ficha" property="id"/>
                     <html:form action="/auditoria/ficha">
                        <html:hidden property="idFicha" value="<%=idFicha.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>
