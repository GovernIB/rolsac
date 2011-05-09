<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<!-- (PORMAD) -->
<div class="bloc">
    <h1><bean:message key="publico.lista" /></h1>
    <h2><bean:message key="publico.datos" /></h2>
</div>
<br />

<logic:empty name="publicoOptions">
    <div class="bloc">
        <br /><h2><bean:message key="publico.vacio" /></h2><br />
    </div>
</logic:empty>

<logic:notEmpty name="publicoOptions" >
    <bean:size id="publicosize" name="publicoOptions" />
    <div class="bloc">
        <logic:iterate  id="publico" name="publicoOptions" indexId="ixPubl">
            <bean:define id="idActual" name="publico" property="id" type="java.lang.Long" />
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=ixPubl.intValue() + 1%>
                    </div>
                    <div class="textgros2" >
                        <bean:write name="publico" property="traduccion.titulo" />
                    </div>
                </div>
                <div class="botoneraconsulta2">
                    <html:form action="/sistema/publico/seleccionar" >
                        <html:hidden property="idSelect" value="<%=idActual.toString()%>" />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
                        <logic:notEqual name="ixPubl" value="0">
                            <html:submit property="action">
                                <bean:message key="boton.subir" />
                            </html:submit>
                        </logic:notEqual>
                    </html:form>
                </div>
            </div>
        </logic:iterate>
    </div>
</logic:notEmpty>

<br />

<center>
[<html:link page="/sistema/publico/form.do" ><bean:message key="boton.nuevo" /></html:link>]
</center>
