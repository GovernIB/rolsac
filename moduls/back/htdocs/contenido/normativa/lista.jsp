<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc">
    <logic:present name="local">
        <h1><bean:message key="normativa.local.lista" /></h1>
        <h2><bean:message key="normativa.local.datos" /></h2>
    </logic:present>
    <logic:present name="externa">
        <h1><bean:message key="normativa.externa.lista" /></h1>
        <h2><bean:message key="normativa.externa.datos" /></h2>
    </logic:present>
</div>
<br />

<logic:empty name="listaNormativas">
    <div class="bloc">
        <br /><h2><bean:message key="normativa.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="listaNormativas">
    <div class="bloc">
        <logic:iterate id="normativa" name="listaNormativas">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="normativa" property="traduccion.titulo" />
                </div>
                <div class="botoneraconsulta1">
                    <logic:present name="local">
                        <html:form action="/contenido/normativa/local/seleccionar" styleId="normativaForm">
                            <input type="hidden" name="idSelect" value='<bean:write name="normativa" property="id" />' />
                            <html:submit property="action">
                                <bean:message key="boton.seleccionar" />
                            </html:submit>
                        </html:form>
                    </logic:present>
                    <logic:present name="externa">
                        <html:form action="/contenido/normativa/externa/seleccionar" styleId="normativaForm">
                            <input type="hidden" name="idSelect" value='<bean:write name="normativa" property="id" />' />
                            <html:submit property="action">
                                <bean:message key="boton.seleccionar" />
                            </html:submit>
                        </html:form>
                    </logic:present>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>
<br />
<center>
    <logic:present name="local">
        [<html:link page="/contenido/normativa/local.do" ><bean:message key="boton.volver" /></html:link>]
    </logic:present>
    <logic:present name="externa">
        [<html:link page="/contenido/normativa/externa.do" ><bean:message key="boton.volver" /></html:link>]
    </logic:present>
</center>
