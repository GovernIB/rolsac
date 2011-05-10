<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<% String context=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Suscripciones</title>
	<link href="<%=context%>/css/estils.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/funciones.js"></script>
	<script type="text/javascript" src="moduls/funcions.js"></script>
	<script type="text/javascript" src="js/jsListados.js"></script>	
</head>

<body>


	<!-- molla pa -->
	<ul id="mollapa">
		<li><a href="<%=context%>/subscripcions/index_inicio.do"><bean:message bundle="subscripcions" key="op.7"  /></a></li>
		<li><bean:message  bundle="subscripcions" key="menu.configuracion"   /></li>
		<li><a href="<%=context%>/subscripcions/grupos.do"><bean:message  bundle="subscripcions" key="grupo.lista"  /></a></li>		
	    <logic:present name="grupoForm" property="id">
         		<li class="pagActual"><bean:write name="grupoForm" property="traducciones[0].nombre" ignore="true" /></li>
	    </logic:present>		
	    
	    <logic:notPresent name="grupoForm" property="id">
	         <li class="pagActual"><bean:message  bundle="subscripcions" key="grupo.alta"  /></li>
	    </logic:notPresent>				
		
	</ul>
	<!-- titol pagina -->
	<h1><img src="../img/titulos/agenda.gif" alt="<bean:message  bundle="subscripcions" key="menu.grupos" />" />
	<bean:message  bundle="subscripcions" key="grupo.grupo" />:</h1>
		<!-- botonera -->
		<div id="botonera">
			<button type="button" title="<bean:message  bundle="subscripcions" key="op.15" />" onclick="submitForm();"><img src="../img/botons/guardar.gif" alt="<bean:message  bundle="subscripcions" key="op.15" />" /> &nbsp;<bean:message  bundle="subscripcions" key="op.15" /></button>
		</div>
	
	
		<div style="font-weight:bold; color:#FF4400;">
		<html:errors/>
		</div>
	
		<html:form action="/subscripcions/grupoEdita.do" enctype="multipart/form-data"  styleId="accFormulario" >
		     
			<logic:present name="grupoForm" property="id">
			    <input type="hidden" name="modifica" value="Grabar">
				<html:hidden property="id" />
			</logic:present>
			<logic:notPresent name="grupoForm" property="id">
				<input type="hidden" name="anyade" value="Crear">
			</logic:notPresent>        
			
			<div id="formulario">
				<!-- las tablas están entre divs por un bug del FireFox -->
				<table cellpadding="0" cellspacing="0" class="edicio">
			
			<tr>

				<td class="etiqueta"><bean:message  bundle="subscripcions" key="grupo.identificador" /></td>
				<td>
					<html:text property="identificador" maxlength="10" size="10"/>
				</td>
				<td class="etiqueta">&nbsp;</td>
				<td  class="etiqueta">&nbsp;</td>
			</tr>
				<tr>
				<td colspan="4">
					<ul id="submenu">
						<logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
							<li<%=(j.intValue()==0?" class='selec'":"")%>><a href="#" onclick="mostrarForm(this);"><bean:message name="lang" /></a></li>
				        </logic:iterate>
					</ul>    
				
				    <logic:iterate id="traducciones" name="grupoForm" property="traducciones" indexId="i" >
					<div id="capa_tabla<%=i%>" class="capaFormIdioma" style="<%=(i.intValue()==0?"display:true;":"display:none;")%>">
					
						<table cellpadding="0" cellspacing="0" class="tablaEdicio">
						<tr>
							<td class="etiqueta"><bean:message  bundle="subscripcions" key="grupo.titulo" />:</td>
							<td><html:text property="nombre" name="traducciones" size="100" maxlength="256" indexed="true" /></td>
						</tr>
						</table>
					</div>
				    </logic:iterate>
				</td>
				</tr>
					
				</table>
					
				
				
					
			</div>
			
		</html:form>

</body>
</html>

<script type="text/javascript">
/*

var uriBorrar="grupoEdita.do?modifica=null&anyade=null&borrar";
*/

	function submitForm(){
		var accForm = document.getElementById('accFormulario');
		accForm.submit();
	}
 
</script>

