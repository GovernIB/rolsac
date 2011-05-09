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
	<script type="text/javascript" src="js/jsListados.js"></script>
	<script type="text/javascript" src="moduls/funcions.js"></script>
</head>

<body>
	<!-- molla pa -->
	<ul id="mollapa">
		<li><a href="<%=context%>/subscripcions/index_inicio.do"><bean:message  bundle="subscripcions" key="op.7" /></a></li>
		<li><bean:message  bundle="subscripcions" key="menu.configuracion" /></li>
		<li class="pagActual"><bean:message  bundle="subscripcions" key="menu.grupos" /></li>
	</ul>
	<!-- titol pagina -->
	<h1><img src="../img/titulos/agenda.gif" alt="<bean:message  bundle="subscripcions" key="menu.grupos" />" />
	<bean:message  bundle="subscripcions" key="menu.grupos" />. <span><bean:message  bundle="subscripcions" key="grupo.lista" /></span></h1>

<logic:equal name="parametros_pagina" property="nreg" value="0">

		<div id="botonera">
			<span class="grup">
				<button type="button" title="<bean:message  bundle="subscripcions" key="grupo.crear" />" onclick="crear();"><img src="../img/botons/nou.gif" alt="<bean:message  bundle="subscripcions" key="grupo.crear" />" /></button> 
			</span>
		</div>

		<div class="alerta" style="font-weight:bold; color:#FF1111;">	
			<p>
				<em><strong><bean:message  bundle="subscripcions" key="grupo.vacio" /><strong></em><br/><br/>
			</p>		
		</div>			


		<br/>&nbsp;&nbsp;<html:link href="<%=context%>/subscripcions/index_inicio.do"><bean:message  bundle="subscripcions" key="op.7" /></html:link>

		<html:form action="/subscripcions/gruposAcc.do" styleId="accFormularioLista">
		  <html:hidden property="accion" />
		</html:form>
		
</logic:equal>

<logic:notEqual name="parametros_pagina" property="nreg" value="0">  

	<html:form action="/subscripcions/grupos.do"  styleId="accFormSearch">
		<html:hidden property="ordenacion" />
		<div id="botonera">
			<span class="grup">
				<button type="button" title="<bean:message  bundle="subscripcions" key="grupo.crear" />" onclick="crear();"><img src="../img/botons/nou.gif" alt="<bean:message  bundle="subscripcions" key="grupo.crear" />" /></button> 
				<button type="button" title="<bean:message  bundle="subscripcions" key="op.6" />" onclick="editar();"><img src="../img/menu/editar.gif" alt="<bean:message  bundle="subscripcions" key="op.6" />" /></button> 
				<button type="button" title="<bean:message  bundle="subscripcions" key="op.2" />" onclick="borravarios();"><img src="../img/botons/borrar.gif" alt="<bean:message  bundle="subscripcions" key="op.2" />" /></button>
			</span>
			<span class="grup">
				<html:text property="filtro" size="10"/> 
				<button type="button" title="<bean:message  bundle="subscripcions" key="op.3" />" onclick="submitFormBuscar();"><img src="../img/botons/seleccionar.gif" alt="<bean:message  bundle="subscripcions" key="op.3" />" /></button> 
			</span>
		</div>		
		</html:form>

		<p><bean:message  bundle="subscripcions" key="grupo.dobleclic" />.</p>
		<p><bean:message  bundle="subscripcions" key="encontrados" /> <strong><bean:write name="parametros_pagina" property="nreg" /> <bean:message  bundle="subscripcions" key="grupo.plural" /></strong>. <bean:message  bundle="subscripcions" key="mostrados" /> <strong><bean:write name="parametros_pagina" property="cursor" /> <bean:message  bundle="subscripcions" key="pagina.al" /> <bean:write name="parametros_pagina" property="cursor_final" /></strong>.</p>

<html:form action="/subscripcions/gruposAcc.do"  styleId="accFormularioLista">
  <table cellpadding="0" cellspacing="0" class="llistat">
  <thead>
	<tr>
		<th class="check">&nbsp;</th>
		<th width="40%">
            <bean:message  bundle="subscripcions" key="grupo.eticolumna1" />&nbsp;
            <html:link href="javascript:ordenar('Atrad.nombre');">
                <logic:equal name="BuscaOrdenaGrupoActionForm" property="ordenacion" value="Atrad.nombre">
                    <img src="../img/iconos/orden_ascendente_on.gif" alt='<bean:message  bundle="subscripcions" key="op.4"/>'>
                </logic:equal>
                <logic:notEqual name="BuscaOrdenaGrupoActionForm" property="ordenacion" value="Atrad.nombre">
                    <img src="../img/iconos/orden_ascendente_off.gif" alt='<bean:message  bundle="subscripcions" key="op.4"/>'>
                </logic:notEqual>
            </html:link>
            <html:link href="javascript:ordenar('Dtrad.nombre');">
                <logic:equal name="BuscaOrdenaGrupoActionForm" property="ordenacion" value="Dtrad.nombre">
                    <img src="../img/iconos/orden_descendente_on.gif" alt='<bean:message  bundle="subscripcions" key="op.5"/>'>
                </logic:equal>
                <logic:notEqual name="BuscaOrdenaGrupoActionForm" property="ordenacion" value="Dtrad.nombre">
                    <img src="../img/iconos/orden_descendente_off.gif" alt='<bean:message  bundle="subscripcions" key="op.5"/>'>
                </logic:notEqual>            
            </html:link>
        </th>

	</tr>
	</thead>
			<tfoot>
				<tr>
					<td colspan="5">

       <logic:present name="parametros_pagina" property="inicio">
            &lt;&lt;<html:link action="/subscripcions/grupos.do" paramId="pagina" paramName="parametros_pagina" paramProperty="inicio"><bean:message  bundle="subscripcions" key="op.7" /></html:link>&nbsp;&nbsp;
        </logic:present>
        <logic:present name="parametros_pagina" property="anterior">
            &lt;<html:link action="/subscripcions/grupos.do" paramId="pagina" paramName="parametros_pagina" paramProperty="anterior"><bean:message  bundle="subscripcions" key="op.8" /></html:link>&nbsp;&nbsp;      
        </logic:present>
        - <bean:write name="parametros_pagina" property="cursor" /> <bean:message  bundle="subscripcions" key="pagina.al" /> <bean:write name="parametros_pagina" property="cursor_final" /> <bean:message  bundle="subscripcions" key="pagina.de" /> <bean:write name="parametros_pagina" property="nreg" /> -  
        <logic:present name="parametros_pagina" property="siguiente">
            <html:link action="/subscripcions/grupos.do" paramId="pagina" paramName="parametros_pagina" paramProperty="siguiente"><bean:message  bundle="subscripcions" key="op.9" /></html:link>&gt;&nbsp;&nbsp;      
        </logic:present>
        <logic:present name="parametros_pagina" property="final">
            <html:link action="/subscripcions/grupos.do" paramId="pagina" paramName="parametros_pagina" paramProperty="final"><bean:message  bundle="subscripcions" key="op.10" /></html:link>&gt;&gt;&nbsp;&nbsp;      
        </logic:present>					
					
					</td>
				</tr>
			</tfoot>
			<tbody>
	    <logic:iterate id="i" name="listado" indexId="indice">
	      <tr class="<%=((indice.intValue()%2==0) ? "par" : "")%>">
	      <td class="check">
	        <html:multibox property="seleccionados" styleClass="radio"> 
	            <bean:write name="i" property="id"/>
	        </html:multibox>
	      </td>
	      <td>
			<bean:write name="i" property="traduccion.nombre" />
	      </td>
	    </tr>
	    </logic:iterate>								
</tbody>
  </table>
  
   
  <html:hidden property="accion" />

</html:form>

</logic:notEqual>

</body>
</html>

<script>
<!--
var context = '\<%=request.getContextPath()%>';
var uriEdicion=context+"/subscripcions/grupoEdita.do?id=";

var alert1="<bean:message  bundle="subscripcions" key="grupo.alert1"/>";
var alert2="<bean:message  bundle="subscripcions" key="grupo.alert2"/>";
-->
</script>


