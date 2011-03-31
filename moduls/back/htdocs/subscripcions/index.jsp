<%@ page language="java"%>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<% String context=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Subscripcions</title>
	<link href="<%=context%>/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<a href="../main.do" id="volverROLSAC"><bean:message  bundle="subscripcions" key="menu.volver.rolsac" /></a>
	
	<div id="cUsuari"><bean:message  bundle="subscripcions" key="identificacion.usuario" />: <strong><bean:write name="username" ignore="true" /></strong> - <bean:message  bundle="subscripcions" key="identificacion.rol" />: <bean:write name="rolenamestxt" ignore="true" /></div>
	<h1 id="cTitulo">Subscripcions: <bean:write name="tituloSuscripcion" ignore="true"/></h1>
	<table id="cMenu" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td><a href="#" onmouseup="voreMenu(this, event, 'mTrabajos');"><bean:message  bundle="subscripcions" key="menu.trabajos" /></a></td>
			<td><a href="#" onmouseup="voreMenu(this, event, 'mRolsac');"><bean:message  bundle="subscripcions" key="menu.rolsac" /></a></td>
			<td><a href="#" onmouseup="voreMenu(this, event, 'mSuscriptors');"><bean:message  bundle="subscripcions" key="menu.suscriptores" /></a></td>			
		</tr>
	</tbody>
	</table>
	<div id="mTrabajos" class="submenu">
		<p><bean:message  bundle="subscripcions" key="menu.trabajos" /></p>
			<a href="<%=context%>/subscripcions/trabajos.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.trabajos.envios" /></a>
	</div>	
	<div id="mRolsac" class="submenu">
		<p><bean:message  bundle="subscripcions" key="menu.rolsac" /></p>
			<a href="<%=context%>/contenido/ficha/form.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.rolsac.fichas" /></a>
			<a href="<%=context%>/organigrama/unidad/editar.do?action=Seleccionar&amp;idUA=<bean:write name="MVS_suscripcion" property="unidadAdministrativa"/>&amp;espera=si"  target="escritori"><bean:message  bundle="subscripcions" key="menu.rolsac.seccion" /></a>						
			<a href="<bean:write name="servidorBoletin" ignore="true"/>/govern/boletin.do?coduo=<bean:write name="MVS_suscripcion" property="unidadAdministrativa"/>&amp;logo=<bean:write name="MVS_suscripcion" property="urlLogo"/>&amp;titulo=<bean:write name="MVS_suscripcion" property="titulo"/>"  target="escritori"><bean:message  bundle="subscripcions" key="menu.rolsac.boletin" /></a>
	</div>
	<div id="mSuscriptors" class="submenu">
		<p><bean:message  bundle="subscripcions" key="menu.suscriptores" /></p>
			<a href="<%=context%>/subscripcions/suscriptores.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.suscriptores.suscripciones" /></a>
			<a href="<%=context%>/subscripcions/grupos.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.suscriptores.grupos" /></a>			
	</div>
	
	<logic:notPresent name="MVS_index_con_info">
		<logic:present name="MVS_pagina_inicio">
			<iframe id="escritori" name="escritori" src="<bean:write name="MVS_pagina_inicio" />" frameborder="0" scrolling="auto"></iframe>
		</logic:present>
		<logic:notPresent name="MVS_pagina_inicio">
			<iframe id="escritori" name="escritori" src="index_inicio.do" frameborder="0" scrolling="auto"></iframe>
		</logic:notPresent>
	</logic:notPresent>
	
	<logic:present name="MVS_index_con_info">
		<br/>
		<br/>
		<div class="encabezado"><bean:message  bundle="subscripcions" key="menu.mensaje" />. </div>
	
		<div class="alerta" style="font-weight:bold; color:#FF1111;">
			<html:messages id="message" message="true"  bundle="subscripcions">
			<%= message %><br/>
			</html:messages>		
		</div>
		<iframe id="escritori" name="escritori" frameborder="0" scrolling="auto"></iframe>
	</logic:present>
	
	
</body>
</html>
