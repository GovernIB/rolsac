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
	<link href="<%=context%>/css/estils.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/funciones.js"></script>
</head>

<body>	
	<logic:present name="MVS_suscripcion">
	
		<!-- molla pa -->
		<ul id="mollapa">
			<li class="pagActual"><bean:message  bundle="subscripcions" key="op.7" /></li>
		</ul>
		<!-- titol pagina -->
		<h1><bean:message  bundle="subscripcions" key="op.7" />.</span></h1>
		<!-- continguts -->
		<div id="pagInici">
			<div class="columna">
				<h2><bean:message  bundle="subscripcions" key="menu.trabajos" /></h2>
				<ul>
					<li><a href="<%=context%>/subscripcions/trabajos.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.trabajos.envios" /></a></li>
				</ul>
				<h2><bean:message  bundle="subscripcions" key="menu.rolsac" /></h2>
				<ul>
					<li><a href="<%=context%>/contenido/ficha/form.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.rolsac.fichas" /></a></li>
					<li><a href="<%=context%>/organigrama/unidad/editar.do?action=Seleccionar&amp;idUA=8&amp;espera=si"  target="escritori"><bean:message  bundle="subscripcions" key="menu.rolsac.seccion" /></a></li>					
					<li><a href="<bean:write name="servidorBoletin" ignore="true"/>/govern/boletin.do?coduo=<bean:write name="MVS_suscripcion" property="unidadAdministrativa"/>&amp;logo=<bean:write name="MVS_suscripcion" property="urlLogo"/>&amp;titulo=<bean:write name="MVS_suscripcion" property="titulo"/>"  target="escritori"><bean:message  bundle="subscripcions" key="menu.rolsac.boletin" /></a></li>					
				</ul>
				<h2><bean:message  bundle="subscripcions" key="menu.suscriptores" /></h2>
				<ul>
					<li><a href="<%=context%>/subscripcions/suscriptores.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.suscriptores.suscripciones" /></a></li>
					<li><a href="<%=context%>/subscripcions/grupos.do"  target="escritori"><bean:message  bundle="subscripcions" key="menu.suscriptores.grupos" /></a></li>
				</ul>								
			</div>
		</div>
	</logic:present>
	
	<logic:notPresent name="MVS_suscripcion">
		<!-- molla pa -->
		<ul id="mollapa">
			<li class="pagActual"><bean:message  bundle="subscripcions" key="op.7" /></a></li>
		</ul>
		<!-- titol pagina -->
		<h1><bean:message  bundle="subscripcions" key="menu.mensaje" />. </h1>
			<div class="alerta" style="font-weight:bold; color:#FF1111;">
				<bean:message  bundle="subscripcions" key="index.inicio.nosuscripcion" /> <br/><br/>
				<em><a href="../back.do" target="_parent"><bean:message  bundle="subscripcions" key="menu.volver.rolsac" /></a></em>
			</div>
	</logic:notPresent>	
	
	
</body>
</html>
