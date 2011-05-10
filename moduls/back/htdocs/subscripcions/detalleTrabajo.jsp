<%@ page contentType="text/html; charset=ISO-8859-1" import="org.ibit.rol.sac.model.EnvioSuscripcion" %>
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
		<li><a href="<%=context%>/subscripcions/trabajos.do"><bean:message  bundle="subscripcions" key="trabajo.lista" /></a></li>
	    <logic:present name="trabajoForm" property="id">
         		<li class="pagActual"><bean:write name="trabajoForm" property="titulo"/></li>
	    </logic:present>		
	    <logic:notPresent name="trabajoForm" property="id">
	         <li class="pagActual"><bean:message  bundle="subscripcions" key="trabajo.alta" /></li>
	    </logic:notPresent>				
	</ul>
	<!-- titol pagina -->
	<h1><img src="../img/titulos/agenda.gif" alt="<bean:message  bundle="subscripcions" key="trabajo.trabajo" />" />
	<bean:message  bundle="subscripcions" key="trabajo.trabajo" />:  
		<span>
		    <logic:present name="trabajoForm" property="id">
	         	<bean:write name="trabajoForm" property="titulo"/>
		    </logic:present>		
		    <logic:notPresent name="trabajoForm" property="id">
		        <bean:message  bundle="subscripcions" key="trabajo.alta" />
		    </logic:notPresent>				
		</span>
	</h1>
	
	
	
	
	<logic:notEqual name="tamano" value="0">
		
		<!-- botonera -->
		<div id="botonera">
			<logic:present name="trabajoForm" property="id">
				<span class="grup">
					<button type="button" title="<bean:message  bundle="subscripcions" key="op.12" />" onclick="previsualizar();"><img src="../img/botons/previsualitzar.gif" alt="<bean:message  bundle="subscripcions" key="op.12" />" /></button>
				</span>	
			</logic:present>	
			<button type="button" title="<bean:message  bundle="subscripcions" key="op.15" />" onclick="submitForm();"><img src="../img/botons/guardar.gif" alt="<bean:message  bundle="subscripcions" key="op.15" />" /> &nbsp;<bean:message  bundle="subscripcions" key="op.15" /></button>
		</div>
	
	
		<div style="font-weight:bold; color:#FF4400;">
		<html:errors/>
		</div>
	
		<html:form action="/subscripcions/trabajoEdita.do" enctype="multipart/form-data"  styleId="accFormulario" >
		     
			<logic:present name="trabajoForm" property="id">
			    <input type="hidden" name="modifica" value="Grabar">
				<html:hidden property="id" />
			</logic:present>
			<logic:notPresent name="trabajoForm" property="id">
				<input type="hidden" name="anyade" value="Crear">
			</logic:notPresent>        
			
			<div id="formulario">
				<!-- las tablas están entre divs por un bug del FireFox -->
					<table cellpadding="0" cellspacing="0" class="edicio">
			
					<tr class="par">
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.titulo" /></td>
						<td colspan="3">
						    <logic:equal name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
							<strong><bean:write name="trabajoForm" property="titulo"  /><strong>
				            </logic:equal>
						    <logic:notEqual name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
							<html:text property="titulo" maxlength="100"  size="100"/>
				            </logic:notEqual>
						</td>
					</tr>
					<tr>
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.fechaEnvio" /></td>
						<td>
						    <logic:equal name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
								<strong><bean:write name="trabajoForm" property="fechaEnvio"  /><strong>
				            </logic:equal>
						    <logic:notEqual name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
								<html:text property="fechaEnvio" maxlength="16" />(dd/mm/yyyy hh:mm)
				            </logic:notEqual>
						</td>
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.fechaEnviado" /></td>
						<td>
							<strong><bean:write name="trabajoForm" property="fechaEnviado"  /><strong>
						</td>
					</tr>
					<tr class="par">
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.asunto" /></td>
						<td colspan="3">
						    <logic:equal name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
								<strong><bean:write name="trabajoForm" property="asunto"  /><strong>
				            </logic:equal>
						    <logic:notEqual name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
								<html:textarea property="asunto"  rows="2" cols="100" />
				            </logic:notEqual>
						</td>
					</tr>
					<tr>
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.estado" /></td>
						<td>
			                <html:select property="estado">
			                	<html:option value=""><bean:message  bundle="subscripcions" key="trabajo.selecestado" /></html:option>
				                <html:options collection="estados" labelProperty="value" property="key"/>
			    	        </html:select>
						</td>
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.canal" /></td>
						<td>
						    <logic:equal name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
								<logic:equal name="trabajoForm" property="canalEnvio" value="<%=EnvioSuscripcion.CANAL_MENSAJE%>">
				                    <strong><bean:message  bundle="subscripcions" key="trabajo.canal.mensaje" /></strong>
					            </logic:equal>
					            <logic:equal name="trabajoForm" property="canalEnvio" value="<%=EnvioSuscripcion.CANAL_SMS%>">
				                    <strong><bean:message  bundle="subscripcions" key="trabajo.canal.sms" /></strong>
					            </logic:equal>
					            <html:hidden property="canalEnvio" />
				            </logic:equal>
						    <logic:notEqual name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
				                <html:select property="canalEnvio">
				                	<html:option value=""><bean:message  bundle="subscripcions" key="trabajo.seleccanal" /></html:option>
					                <html:options collection="canales" labelProperty="value" property="key"/>
				    	        </html:select>
				            </logic:notEqual>
						</td>
					</tr>
					<tr class="par">
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.tipo" /></td>
						<td colspan="3">
						    <logic:equal name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
							<logic:equal name="trabajoForm" property="tipo" value="<%=EnvioSuscripcion.TIPO_BOLETIN%>">
							<strong><bean:message  bundle="subscripcions" key='trabajo.tipo.boletin' /></strong>
					            </logic:equal>
					            <logic:equal name="trabajoForm" property="tipo" value="<%=EnvioSuscripcion.TIPO_ALERTAS%>">
				                    <strong><bean:message  bundle="subscripcions" key="trabajo.tipo.alertas" /></strong>
					            </logic:equal>
					            <logic:equal name="trabajoForm" property="tipo" value="<%=EnvioSuscripcion.TIPO_ESTUDIOS%>">
				                    <strong><bean:message  bundle="subscripcions" key="trabajo.tipo.estudios" /></strong>
					            </logic:equal>
					            <html:hidden property="tipo" />
				            </logic:equal>
						    <logic:notEqual name="trabajoForm" property="estado" value="E">
				                <html:select property="tipo">
				                	<html:option value=""><bean:message  bundle="subscripcions" key="trabajo.selectipo" /></html:option>
					                <html:options collection="tipos" labelProperty="value" property="key"/>
				    	        </html:select>
				            </logic:notEqual>
						</td>
					</tr>
					<!--  
					<tr>
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.seccion" /></td>
						<td colspan="3">
							<html:hidden property="idSeccion"/>
							<html:text property="seccion"  size="60" />
							<button type="button" title="<bean:message  bundle="subscripcions" key="boton.seleccionar" />" onclick="abrirSeccion();"><img src="../img/botons/seleccionar.gif" alt="<bean:message  bundle="subscripcions" key="boton.seleccionar" />" /></button>
						</td>
					</tr>
					-->
					<tr>
						<td class="etiqueta"><bean:message  bundle="subscripcions" key="trabajo.activo" /></td>
						<td colspan="3">
							<label><html:radio property="activo" value="S" />&nbsp;Sí</label>&nbsp;&nbsp;&nbsp;<label><html:radio property="activo" value="N" />&nbsp;No</label>
						</td>
					
				</table>
					
				<!-- ****************************** -->
				<!-- Lista de Grupos -->
				<!-- ****************************** -->
	
	
	
				<div style="background:#FFFAF5; margin:40px 50px 40px 50px;">
				<h1>
				<span>
				    <bean:message  bundle="subscripcions" key="trabajo.grupo" />				
				</span>
				</h1>

				<logic:notEmpty name="grupos">
	
				<table id="llistatcoses" cellpadding="0" cellspacing="0" class="llistat">
				<thead>
					<tr>
						<th >
							<html:multibox property="seleccionados" styleClass="radio" onclick="marcatodos(this);">-1</html:multibox> 
					   		
						&nbsp;</th>
						<th><bean:message  bundle="subscripcions" key="trabajo.grupo" /></th>
					</tr>
				</thead>
				<tbody>

				<logic:iterate id="i" name="grupos" indexId="indice">
			    	<tr class="<%=((indice.intValue()%2==0) ? "par" : "")%>">
			    		<td >
						<html:multibox property="seleccionados" styleClass="radio"> 
					   			<bean:write name="i" property="id"/>
		    				</html:multibox>
						</td>
			   	    	<td>
		   			    <logic:notEmpty name="i" property="traduccion.nombre">
							<bean:write name="i" property="traduccion.nombre" />
						</logic:notEmpty>
						<logic:empty name="i" property="traduccion.nombre">
							[<bean:message  bundle="subscripcions" key="formu.noetiq" />]
						</logic:empty>
   	    				</td>
					</tr>
			    	</logic:iterate>
	
					</tbody>
				</table>
				
				</logic:notEmpty>
				
				</div>
					
			</div>
			
			
		</html:form>
	</logic:notEqual>

</body>
</html>



<script type="text/javascript">
var context = '\<%=request.getContextPath()%>';
var uriBorrar=context+"/subscripcions/trabajoEdita.do?modifica=null&anyade=null&borrar";
var uriAnyadir=context+"/subscripcions/trabajoEdita.do?modifica=null&anyade=null&AnyadeGrupos";
var alert1="<bean:message  bundle="subscripcions" key="trabajo.grupalert1"/>";
var alert2="<bean:message  bundle="subscripcions" key="trabajo.grupalert2"/>";
var alert3="<bean:message  bundle="subscripcions" key="trabajo.grupalert3"/>";



var alertFechaNoValida="<bean:message  bundle="subscripcions" key="mensa.trabajo.errorfecha"/>";


	function submitForm(){
		var accForm = document.getElementById('accFormulario');
		var fecha = document.getElementById('fechaEnvio');		
		
		<logic:equal name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
			accForm.submit();
	    </logic:equal>
	    <logic:notEqual name="trabajoForm" property="estado" value="<%=EnvioSuscripcion.EJECUTADO%>">
			if (comprobarFormato(fecha)){
				accForm.submit();
			}else{alert(alertFechaNoValida);}
        </logic:notEqual>
	}
	
	function previsualizar() {
		<logic:present name="trabajoForm" property="id">
			abrirWindow(context+'/subscripcions/visualizaTrabajo.do?id=<bean:write name="trabajoForm" property="id"/>');
		</logic:present>	
	}

 	function abrirSeccion() {
        poprealcion = obrir("../sistema/seccion/arbol.do?idSeccion=0&action=&ficha=true", "<bean:message  bundle="subscripcions" key='boton.seleccionar'/>", 538, 440);
    }
    function obrir(url, name, x, y) {
   		nombre = window.open(url, name, 'scrollbars=no, resizable=yes, width=' + x + ',height=' + y);
   		return nombre;
	}
	
	function actualizaSeccion(id, nombre){
            document.forms[0].idSeccion.value = id;
            document.forms[0].seccion.value = nombre;
        }


    var Rcajatemp;
    function Rpopupurl(obj) {
    	Rcajatemp=document.trabajoForm[obj];
		window.open(context+'/subscripcions/recursos.do','recursos','scrollbars=yes,width=700,height=400');
    }
	
	function Rmeterurl(laurl) {
		Rcajatemp.value=laurl;
	}
	
	function nuevoGrupo(idtrabajo) {
		document.location.href=context+"/subscripcions/grupoTrabajoEdita.do?idtrabajo="+idtrabajo;
}

function anyadeGrupos() {

	<logic:present name="trabajoForm" property="id">
	    
	    if (!confirm(alert3))	return;
		var accFormLista = document.getElementById('accFormulario');
		accFormLista.action=uriAnyadir;
		accFormLista.submit();
		
	</logic:present>  
}

function borraGrupos() {
var nselec=0;

	<logic:present name="trabajoForm" property="id">
		var accFormLista = document.getElementById('accFormulario');
		var nselec=0;
		
	    if (accFormLista.seleccionados.length==undefined) {
	        if (accFormLista.seleccionados.checked) nselec=1;
	    } else {
	        for (var i=0;i<accFormLista.seleccionados.length;i++)
	            if (accFormLista.seleccionados[i].checked) nselec++;
	    }
		    
	    if (nselec==0) {
            alert (alert1);
            return;
	    }
		    
	    if (!confirm(alert2))	return;
		
		accFormLista.action=uriBorrar;
		accFormLista.submit();
	</logic:present>  
}

function actualizaGruposSeleccionados()
{
	desmarcatodos();
	var accFormLista = document.getElementById('accFormulario');
	<logic:iterate id="i" name="trabajoForm" property="lineasgrupo" indexId="indice">
	        for (var i=0;i<accFormLista.seleccionados.length;i++)
	        {
	            if (accFormLista.seleccionados[i].value == <bean:write name="i" property="id"/>)
	            { 
	            	accFormLista.seleccionados[i].checked = 'checked';
	            	accFormLista.seleccionados[i].parentNode.parentNode.className = 'marcado';
	            }
	        }
	</logic:iterate>

}

function marcatodos(obj) {
 		
	var inputs = document.getElementById('llistatcoses').getElementsByTagName('input');

	for(var i =0;i< inputs.length;i++) {
		
  		if (inputs[i].type =='checkbox' && inputs[i].value != '-1' ) {
  			if (obj.checked)
  			{
  			 	inputs[i].checked=true;
  			 	inputs[i].parentNode.parentNode.className = 'marcado';
  			 }
  			else
  			{
  			 	inputs[i].checked=false;
  			 	inputs[i].parentNode.parentNode.className = '';
  			 }
  		}
  		
   	}
	
}

function desmarcatodos() {
 		
	var inputs = document.getElementById('llistatcoses').getElementsByTagName('input');

	for(var i =0;i< inputs.length;i++) {
		
  		if (inputs[i].type =='checkbox') {
  			 	inputs[i].checked=false;
  			 	inputs[i].parentNode.parentNode.className = '';
  		}
  		
   	}
	
}

actualizaGruposSeleccionados();


function comprobarFormato(fecha){
	if (fecha.value.length <10 ){return false;}
	
    if (fecha != undefined && fecha.value != "" ){
        if (!/^\d{2}\/\d{2}\/\d{4} \d{2}:\d{2}$/.test(fecha.value)){return false;}
        
        var dia  =  parseInt(fecha.value.substring(0,2),10);
        var mes  =  parseInt(fecha.value.substring(3,5),10);
        var anio =  parseInt(fecha.value.substring(6,10),10);
        
        var horas =  parseInt(fecha.value.substring(11,13),10);
        var minutos =  parseInt(fecha.value.substring(14),10);
        
	    switch(mes){
	        case 1:
	        case 3:
	        case 5:
	        case 7:
	        case 8: 
	        case 10:
	        case 12:
	            numDias=31;
	            break;
	        case 4: case 6: case 9: case 11:
	            numDias=30;
	            break;
	        case 2:
	            if (comprobarSiBisiesto(anio)){ numDias=29 }else{ numDias=28};
	            break;
	        default:
	            return false;
	    }
 
        if (dia>numDias || dia==0){return false;}
        if (horas>23){return false;}
        if (minutos>59){return false;}
        
        return true;
    }
}
 
function comprobarSiBisiesto(anio){
	if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) {return true;}
	else {return false;}
}

</script>

