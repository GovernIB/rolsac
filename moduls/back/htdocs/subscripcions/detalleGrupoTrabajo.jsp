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
	<script type="text/javascript" src="moduls/funcions.js"></script>
</head>

<body>

	<!-- molla pa -->
	<ul id="mollapa">
		<li><a href="<%=context%>/subscripcions/index_inicio.do"><bean:message  bundle="subscripcions" key="op.7" /></a></li>
		<li><bean:message  bundle="subscripcions" key="menu.configuracion" /></li>
		<li><a href="<%=context%>/subscripcions/trabajos.do"><bean:message  bundle="subscripcions" key="trabajo.lista" /></a></li>
		<li><a href="<%=context%>/subscripcions/trabajoEdita.do?id=<bean:write name="grupoTrabajoForm" property="idtrabajo" />"><bean:message  bundle="subscripcions" key="trabajo.trabajo" /></a></li>
	    <logic:present name="grupoTrabajoForm" property="id">
       		<li class="pagActual"><bean:message  bundle="subscripcions" key="trabajo.lingrupos" /></li>
	    </logic:present>		
	    <logic:notPresent name="grupoTrabajoForm" property="id">
	         <li class="pagActual"><bean:message  bundle="subscripcions" key="trabajo.grupo.alta" /></li>
	    </logic:notPresent>				
	</ul>
	
	<!-- titol pagina -->
	<h1><bean:message  bundle="subscripcions" key="trabajo.grupo.datos" />:  
		<span>
		    <logic:present name="grupoTrabajoForm" property="id">
	         	<bean:message  bundle="subscripcions" key="trabajo.grupo.modificacion" />
		    </logic:present>		
		    <logic:notPresent name="grupoTrabajoForm" property="id">
		        <bean:message  bundle="subscripcions" key="trabajo.grupo.alta" />
		    </logic:notPresent>				
		</span>
	</h1>

	<!-- botonera -->
	<div id="botonera">
		<button type="button" title="<bean:message  bundle="subscripcions" key="op.15" />" onclick="submitForm();"><img src="../img/botons/guardar.gif" alt="<bean:message  bundle="subscripcions" key="op.15" />" /> &nbsp;<bean:message  bundle="subscripcions" key="op.15" /></button>
	</div>	

	<div style="font-weight:bold; color:#FF4400;">
		<html:errors/>
	</div>

<html:form action="/subscripcions/grupoTrabajoEdita.do" styleId="accTrabajo">

     <html:hidden name="grupoTrabajoForm" property="idtrabajo" />
     
     <logic:present name="grupoTrabajoForm" property="id">
	    <input type="hidden" name="modificagrupo" value="Grabar">
		<html:hidden name="grupoTrabajoForm" property="id" />
	</logic:present>
	<logic:notPresent name="grupoTrabajoForm" property="id">
		<input type="hidden" name="anyadegrupo" value="Crear">
	</logic:notPresent>
     
	<!-- las tablas están entre divs por un bug del FireFox -->
	<div id="formulario">
	
		<table cellpadding="0" cellspacing="0" class="edicio">
		<tr>
			<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.grupo" /></td>
			<td>
				<html:select property="id">
			       <html:option value=""><bean:message  bundle="subscripcions" key="trabajo.grupo.selecgrupo" /></html:option>
				   <html:options collection="grupos" labelProperty="traduccion.nombre" property="id"/>
			    </html:select>
			</td>
		</tr>
		</table>
	
</div>


</html:form>

</body>
</html>


<script>


function submitForm(){
	var accForm = document.getElementById('accTrabajo');
	accForm.submit();
}

var accForm = document.getElementById('accTrabajo');
	accForm.id.value="";

</script>

