<%@ page contentType="text/html; charset=ISO-8859-1" import="org.ibit.rol.sac.model.Suscriptor" %>
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
		<li><a href="<%=context%>/subscripcions/index_inicio.do"><bean:message  bundle="subscripcions" key="op.7" /></a></li>
		<li><bean:message  bundle="subscripcions" key="menu.configuracion" /></li>
		<li><a href="<%=context%>/subscripcions/suscriptores.do"><bean:message  bundle="subscripcions" key="suscriptor.lista" /></a></li>
	    <logic:present name="suscriptorForm" property="id">
         		<li class="pagActual"><bean:write name="suscriptorForm" property="email"/></li>
	    </logic:present>		
	    <logic:notPresent name="suscriptorForm" property="id">
	         <li class="pagActual"><bean:message  bundle="subscripcions" key="suscriptor.alta" /></li>
	    </logic:notPresent>				
	</ul>
	<!-- titol pagina -->
	<h1><img src="../img/titulos/agenda.gif" alt="<bean:message  bundle="subscripcions" key="suscriptor.suscriptor" />" />
	<bean:message  bundle="subscripcions" key="suscriptor.suscriptor" />:  
		<span>
		    <logic:present name="suscriptorForm" property="id">
	         	<bean:write name="suscriptorForm" property="email"/>
		    </logic:present>		
		</span>
	</h1>
	
	
	
	
	<logic:notEqual name="tamano" value="0">
		
		<!-- botonera -->
	
		<div id="botonera">
			<button type="button" title="<bean:message  bundle="subscripcions" key="op.15" />" onclick="submitForm();"><img src="../img/botons/guardar.gif" alt="<bean:message  bundle="subscripcions" key="op.15" />" /> &nbsp;<bean:message  bundle="subscripcions" key="op.15" /></button>
		</div>
	
		<div style="font-weight:bold; color:#FF4400;">
		<html:errors/>
		</div>
	
		<html:form action="/subscripcions/suscriptorEdita.do" enctype="multipart/form-data"  styleId="accFormulario" >
		     
			<logic:present name="suscriptorForm" property="id">
			    <input type="hidden" name="modifica" value="Grabar">
				<html:hidden property="id" />
			</logic:present>
			<logic:notPresent name="suscriptorForm" property="id">
				<input type="hidden" name="anyade" value="Crear">
			</logic:notPresent>        
			
			<div id="formulario">
				<!-- las tablas están entre divs por un bug del FireFox -->
					<table cellpadding="0" cellspacing="0" class="edicio">
					<tr>
						<td width="10%" class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>																								
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>
						<td width="10%"  class="etiqueta">&nbsp;</td>																								
					</tr>
					
				   <logic:notPresent name="suscriptorForm" property="id">
					<tr  class="par">
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.email" /></td>
						<td colspan="2">
							<html:text property="email" maxlength="100" size="50" />
						</td>
					</logic:notPresent>  
					
					<tr  class="par">
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.nombre" /></td>
						<td colspan="2">
							<html:text property="nombre" maxlength="100" size="30" />
						</td>
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.apellido1" /></td>
						<td colspan="2">
							<html:text property="apellido1" maxlength="100" size="30" />						
						</td>
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.apellido2" /></td>
						<td colspan="2">
							<html:text property="apellido2" maxlength="100" size="30" />
						</td>
					</tr>
					
					<tr>
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.estado" /></td>
						<td colspan="1">
					    <logic:equal name="suscriptorForm" property="estado" value="<%=Suscriptor.TIPO_BAJA%>">
							<strong>Baixa<strong>
			            </logic:equal>
					    <logic:notEqual name="suscriptorForm" property="estado" value="<%=Suscriptor.TIPO_BAJA%>">
			                <html:select property="estado">
			                	<html:option value=""><bean:message  bundle="subscripcions" key="suscriptor.selecestado" /></html:option>
				                <html:options collection="estados" labelProperty="value" property="key"/>
			    	        </html:select>
			    	    </logic:notEqual>    
						</td>
					</tr>
					
					
					<tr class="par">
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.fechaAlta" /></td>
						<td colspan="2">
								<strong><bean:write name="suscriptorForm" property="fechaAlta"  /><strong>
								(<bean:write name="suscriptorForm" property="usuarioAlta"  />)								
						</td>
						<td colspan="2" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.fechaModificacion" /></td>
						<td colspan="1">
							<strong><bean:write name="suscriptorForm" property="fechaModificacion"  /><strong>
						</td>
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.fechaBaja" /></td>
						<td colspan="2">
							<strong><bean:write name="suscriptorForm" property="fechaBaja"  /><strong>
						</td>
					</tr>
					
					<tr>
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.sms" /></td >
						<td colspan="2" ><html:text property="sms" maxlength="100" size="8" /></td>
					
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.grupo" /></td>
						<td colspan="5">
							<html:select property="grupo">
						       <html:option value=""><bean:message  bundle="subscripcions" key="trabajo.grupo.selecgrupo" /></html:option>
							   <html:options collection="grupos" labelProperty="traduccion.nombre" property="id"/>
						    </html:select>
						</td>
					</tr>
					
					<tr>
						<td colspan="1" class="etiqueta">
							<bean:message  bundle="subscripcions" key="suscriptor.telefono" />
						</td >
						<TD colspan="2">
							<html:text property="telefono" maxlength="100" size="8" />
						</td>
					
						<td colspan="6" class="etiqueta">
						&nbsp;
						</td>
					</tr>
					
					
					
					<tr class="par">						
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.tipo" /></td>						
						<td colspan="2">
		                <html:select property="tipo">
			                	<html:option value=""><bean:message  bundle="subscripcions" key="suscriptor.selectipo" /></html:option>
				                <html:options collection="tipos" labelProperty="value" property="key"/>
		    	        </html:select>
		    	        </td>
						<td colspan="1"  class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.nombreEntidad" /></td>
						<td colspan="4" >
							<html:text property="nombreEntidad" maxlength="200" size="50" />
						</td>
		    	        
					</tr>
					<tr>
						<td  colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.nombreArea" /></td>
						<td colspan="2" >
							<html:text property="nombreArea" maxlength="200" size="30" />
						</td>

						<td  colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.departamentoEntidad" /></td>
						<td colspan="2" >
							<html:text property="departamentoEntidad" maxlength="200" size="30" />
						</td>

						<td  colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.cargo" /></td>
						<td colspan="2" >
							<html:text property="cargo" maxlength="200" size="30" />
						</td>
					</tr>
					<tr class="par">
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.boletin" /></td>
						<td colspan="2">
							<label><html:radio property="boletin" value="S" />&nbsp;Sí</label>&nbsp;&nbsp;&nbsp;<label><html:radio property="boletin" value="N" />&nbsp;No</label>
						</td>
					</tr>
					<tr>
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.alertas" /></td>
						<td colspan="2">
							<label><html:radio property="alertas" value="S" />&nbsp;Sí</label>&nbsp;&nbsp;&nbsp;<label><html:radio property="alertas" value="N" />&nbsp;No</label>
						</td>
					<tr class="par">
						<td colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.estudios" /></td>
						<td colspan="2">
							<label><html:radio property="estudios" value="S" />&nbsp;Sí</label>&nbsp;&nbsp;&nbsp;<label><html:radio property="estudios" value="N" />&nbsp;No</label>
						</td>
					</tr>
					<tr>
						<td  colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.origen" /></td>
						<td colspan="1" >
							<strong>
							<span id="inicioDefaultOrigen">
								<bean:write name="suscriptorForm" property="origen"/>
							</span>
							<strong>
						</td>
					</tr>
					<tr class="par">						
						<td  colspan="1" class="etiqueta"><bean:message  bundle="subscripcions" key="suscriptor.referenciaTramite" /></td>
						<td colspan="1" ><strong><bean:write name="suscriptorForm" property="referenciaTramite"  /><strong></td>
					</tr>
				</table>
					
			
		</html:form>
	</logic:notEqual>

</body>
</html>



<script type="text/javascript">

	



	var alertEmailNoValido="<bean:message  bundle="subscripcions" key="error.suscriptor.mail"/>";
	var alertSubmitForm="<bean:message  bundle="subscripcions" key="error.suscriptor.eleccion"/>";

	function submitForm(){
		var accForm = document.getElementById('accFormulario');
		var op1 = document.forms["accFormulario"].boletin[0].checked;
		var op2 = document.forms["accFormulario"].alertas[0].checked;
		var op3 = document.forms["accFormulario"].estudios[0].checked;
		
		var textEmail = document.getElementById('email');	



		<logic:notPresent name="suscriptorForm" property="id">
			if (op1 || op2 || op3){
				if (textEmail==null){alert(alertEmailNoValido); return;}
				if (/^(.+\@.+\..+)$/.test(textEmail.value)){
					accForm.submit();
				}else{
					alert(alertEmailNoValido);
				}
			}else{
				alert(alertSubmitForm);
			}
		</logic:notPresent>  
		<logic:present name="suscriptorForm" property="id">
			if (op1 || op2 || op3){
				accForm.submit();
			}else{
				alert(alertSubmitForm);
			}
		</logic:present>  			
					
		
	}
	
<%String inicio = (String)request.getSession().getAttribute("inicioDefecto");
if (inicio!= null ){
	if ("1".equals(inicio)){%>
		
		var aux = document.getElementById("inicioDefaultOrigen");
		aux.innerText="Manual";
		document.forms["accFormulario"].boletin[1].checked=true;
		document.forms["accFormulario"].alertas[1].checked=true;
		document.forms["accFormulario"].estudios[1].checked=true;

		var i;

		var selectBox = document.forms["accFormulario"].estado.options;
		for (i=0;i<selectBox.length;i++) {
			if (selectBox[i].value=="A") {break;}
		}
		document.getElementById("estado").selectedIndex = i;
		
		var selectBox = document.forms["accFormulario"].grupo.options;
		for (i=0;i<selectBox.length;i++) {
			if (selectBox[i].value=="0") {break;}
		}
		document.getElementById("grupo").selectedIndex = i;
		
		var selectBox = document.forms["accFormulario"].tipo.options;
		for (i=0;i<selectBox.length;i++) {
			if (selectBox[i].value=="P") {break;}
		}
		document.getElementById("tipo").selectedIndex = i;
				
	<%}	
	request.getSession().removeAttribute("inicioDefecto");
}
%>




	

</script>

