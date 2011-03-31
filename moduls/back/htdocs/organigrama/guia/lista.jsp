<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
 
<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>

<div class="bloc">
	<h1><bean:message key="guia.lista" /></h1>
	<h2><bean:message key="guia.datos" /></h2>
</div>
<br />

<logic:empty name="listaGuia">
<div class="bloc"> 
    <br /><h2>
     
        <bean:message key="guia.vacio" />
     
    </h2><br />
</div>
</logic:empty>

<logic:notEmpty name="listaGuia">
    <div class="bloc">
        <logic:iterate id="guia" name="listaGuia">
            <div class="component">
                
                <div class="textseparat">
                    <div class="textgros6">&nbsp;
                        <bean:write name="guia" property="nombre"  />
                    </div>
                    <div class="textpetit2">&nbsp;
                    	
                    	<logic:present name="guia" property="unidadAdministrativa">
                        <html:link action='<%="/organigrama/unidad/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idUA" paramName="guia" paramProperty="unidadAdministrativa.id">
                            <bean:write name="guia" property="unidadAdministrativa.traduccion.nombre" />
                        </html:link>
                        </logic:present>
                        
                    </div>
                </div>
                
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/guia/editar" styleId="personalForm2">
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
[<html:link page="/organigrama/guia/form.do" ><bean:message key="boton.volver" /></html:link>]
</center>