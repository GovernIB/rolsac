<%@ page import="org.ibit.rol.sac.model.FichaRemota"%>
<%@ page import="org.ibit.rol.sac.model.Ficha"%>
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean"%>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic"%>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html"%>
<% String context=request.getContextPath();%>
<html:xhtml />
<div class="bloc">
<h1><bean:message key="ficha.lista" /></h1>
<h2><bean:message key="ficha.datos" /></h2>
</div>
<br />

<logic:empty name="listaFichas">
	<div class="bloc"><br />
	<h2><bean:message key="ficha.vacio" /></h2>
	<br />
	</div>
</logic:empty>

<logic:notEmpty name="listaFichas">
	<div class="bloc">
	<logic:iterate id="ficha" name="listaFichas">
		<div class="component">
			<div class="textconsulta1">
				<table>
					<tr>
						<%String pub = "S";%>
						<td><logic:equal name="ficha" property="validacion" value="1">
							<logic:present name="ficha" property="fechaCaducidad">
								<bean:define id="caduca" name="ficha" property="fechaCaducidad"	type="java.util.Date" />
								<%if ((new java.util.Date().getTime() > caduca.getTime())) {
										pub = "N";
									} else {
										pub = "S";
									}%>
							</logic:present>
							<logic:present name="ficha" property="fechaPublicacion">
								<bean:define id="publica" name="ficha" property="fechaPublicacion" type="java.util.Date" />
								<%if ((new java.util.Date().getTime() > publica.getTime()) && pub.equals("S")) {
										pub = "S";
									} else {
										pub = "N";
									}%>
							</logic:present>
							<%if (pub.equals("N")) {%>
								<img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>" />&nbsp;
							<%} else {%>
								<img src="<%=context%>/img/fichaP.gif" alt="<bean:message key="validacion.publica"/>" />&nbsp;
							<%}	%>
							</logic:equal> 
							<logic:notEqual name="ficha" property="validacion" value="1">
								<img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>" />&nbsp;
							</logic:notEqual>
						</td>
						<td>
							<bean:write name="ficha" property="traduccion.titulo" />&nbsp;(<bean:message key="ficha.id" />:<bean:write name="ficha" property="id" />) <%if (ficha instanceof FichaRemota) {%>&nbsp;<b><bean:message key="ficha.remota" /></b> <%}%>
						</td>
					</tr>
				</table>
			</div>
			<div class="botoneraconsulta1">
				<html:form action="/contenido/ficha/seleccionar" styleId="fichaForm">
				<input type="hidden" name="idSelect" value='<bean:write name="ficha" property="id" />' />
				<html:submit property="action">
					<bean:message key="boton.seleccionar" />
				</html:submit>
				</html:form>
			</div>
		</div>
	</logic:iterate></div>
</logic:notEmpty>
<br />
<center>[<html:link page="/contenido/ficha/form.do">
	<bean:message key="boton.volver" />
</html:link>]</center>
