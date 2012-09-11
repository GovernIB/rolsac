<%@  page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--

<logic:present name="alert">
   function handleAlert() {
	 alert("<bean:message name='alert' />");
   }
 </logic:present>

    function handleBaja() {
  	 alert("<bean:message key='alerta.baja' />");
   }
 
    function baja(){
	       	window.setTimeout("handleBaja()",1000)
        //return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateProcedimientoForm(form);
    }

    // Llamada al popup para seleccionar la fecha de un campo
    function abrirCalendario(field) {
        obrirFixa("<html:rewrite page='/moduls/calendario.do'/>?field=" + field);
    }

    // Llamada para el popup de calendario para fijar la fecha
    function seleccionaDia(data, field) {
        eval("document.forms[0]." + field + ".value=data");
    }

    function abrirDato(opcion) {
        obrir("<html:rewrite page='/contenido/procedimiento/popup.do'/>?opcion=" + opcion, "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    function actualizaDato(id, nombre, field) {
        eval("document.forms[0].id" + field + ".value=id");
        eval("document.forms[0].nombre" + field + ".value=nombre");
    }


    
    function obrirProce(idproced)
     {
     
      var idi= document.getElementById("idio").value;
    
      if (idi== "0") {idi="ca";}
      if (idi== "1") {idi="es";}
      if (idi== "2") {idi="en";}
      if (idi== "3") {idi="de";}
      if (idi== "4") {idi="fr";}
      var id = idproced
      window.open("<logic:present name="host"><bean:write name="host" /></logic:present>/govern/sac/visor_proc.do?previ=s&lang="+idi+"&codi="+id, 'procedimiento', 'scrollbars=yes, resizable=yes, top=100,left=200, width=800,height=600');
     }

	var classeUA=1;
	
	
    function abrirUAResponsable() {
		classeUA=1;
        poprealcion = obrirScroll("<html:rewrite page='/organigrama/unidad/poparbol.do'/>?idUA=0&action=&", "<bean:message key='boton.seleccionar' />", 538, 440);
    }


    function abrirUAResolver() {
		classeUA=2;
        poprealcion = obrirScroll("<html:rewrite page='/organigrama/unidad/poparbol.do'/>?idUA=0&action=&padres", "<bean:message key='boton.seleccionar' />", 538, 440);
    }
	
    function actualizaUA(id, nombre) {
		if(classeUA==1) {
        eval("document.forms[0].idUA.value=id");
        eval("document.forms[0].nombreUA.value=nombre");
		}
		if(classeUA==2) {
        eval("document.forms[0].idUAResol.value=id");
        eval("document.forms[0].nombreUAResol.value=nombre");
		}
    }

    // Función para abrir la lista de opciones para busqueda o relacion
    function listaOpciones(opcion, dato){
        if (opcion == "busqueda"){
            obrir("<html:rewrite page='/contenido/procedimiento/popup.do?accion=busqueda&opcion='/>" + dato, "<bean:message key='boton.seleccionar' />", 538, 140);
        }
        if (opcion == "relaciona"){
            obrir("<html:rewrite page='/contenido/procedimiento/popup.do?accion=relaciona&opcion='/>" + dato, "<bean:message key='boton.seleccionar' />", 538, 140);
        }
    }

    function buscarPorOpcion(id, dato){
        accion = '<bean:message key='boton.busqueda' />';
        document.location = "<html:rewrite page='/contenido/procedimiento/editar.do'/>?action=" + accion + "&dato=" + dato +"&id=" + id;
    }

    function popupFormNormativas(){
       obrirScroll("<html:rewrite page='/contenido/procedimiento/popupnormativas.do'/>","", 538, 340);
    }

     function formCodigo(){
        obrir("<html:rewrite page='/contenido/procedimiento/popupcodigo.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
    }
 
    function mostrarProcedimiento(id){
        accion = '<bean:message key='boton.seleccionar' />';
        document.location = "<html:rewrite page='/contenido/procedimiento/editar.do'/>?action=" + accion + "&idSelect="+ id;
    }

    <logic:present name="procedimientoForm" property="id">
        function asignarOpcion(id, dato){
            accion = "";
            if (dato == "materia") accion = '<bean:message key="procedimiento.relacion.materias" />';
            if (dato == "normativa") accion = '<bean:message key="procedimiento.relacion.normativas" />';
            if (dato == "tramite") accion = '<bean:message key="procedimiento.relacion.tramites" />';
            idProcedimiento = '<bean:write name="procedimientoForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/procedimiento/editar.do'/>?action=" + accion + "&procedimiento=" + idProcedimiento + "&" + dato + "=" + id + "&operacion=alta";
        }
    </logic:present>


     <logic:present name="procedimientoForm" property="id">
        function asignarNormativa(id, nombre){
            accion = '<bean:message key="procedimiento.relacion.normativas" />';
            idProcedimiento = '<bean:write name="procedimientoForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/procedimiento/editar.do'/>?action=" + accion + "&procedimiento=" + idProcedimiento + "&normativa=" + id + "&operacion=alta";
        }
    </logic:present>


	// Funcio per mostrar una capa i ocultar la resta
	function activarCapa(capa, n) {
	 
	document.getElementById("idio").value=capa;

    if (capa== 0) {document.getElementById("langTrad").value="ca";}
    if (capa== 1) {document.getElementById("langTrad").value="es";}
    if (capa== 2) {document.getElementById("langTrad").value="en";}
    if (capa== 3) {document.getElementById("langTrad").value="de";}
    if (capa== 4) {document.getElementById("langTrad").value="fr";}
        for (i = 0; i < n; i++) {
            capeta = document.getElementById("capa" + i);
            if (i == capa) {
                capeta.style.visibility="visible";
            } else {
                capeta.style.visibility="hidden";
            }
        }
	}

	<logic:present name="traductor">

	function inicioTraduccion(){
	    return confirm("<bean:message key='traduccion.inicio' />");
	}
	
    function validar(form) {
		var validado = false;

		validado = validateProcedimientoForm(form);
		if (validado == true){
       		if(document.getElementById("checkValidar").checked==false){
				var msg = "<bean:message key='procedimiento.aviso.validar.campos'/>";
				alert(msg);
				validado = false;
	        }
		}
		
    	return validado;
    }

	function unCheckValidar(validacion, campo){
		
		if (validacion== 1){
			if(document.getElementById("checkValidar").checked==true){
				document.getElementById("checkValidar").checked=false;	

				var p = document.getElementById(campo).value;
				var msg = "<bean:message key='procedimiento.aviso.validar.campo' arg0='"+ p +"'/>";
				alert(msg);
				
			}
		}
	}

	</logic:present>


	<logic:notPresent name="traductor">
	
	function validar(form) {
        return validateProcedimientoForm(form);
     }

	</logic:notPresent>
	
// -->
</script>


<div class="bloc">
    <h1>
        <logic:notPresent name="procedimientoForm" property="id">
            <bean:message key="procedimiento.alta" />
        </logic:notPresent>
        <logic:present name="procedimientoForm" property="id">
            <bean:message key="procedimiento.modificacion" />
        </logic:present>
    </h1>
	<h2>
		<bean:message key="procedimiento.datos" />
        <logic:present name="procedimientoForm" property="id">
           &nbsp;(<bean:message key="procedimiento.id" />:<bean:write name="procedimientoForm" property="id" />)
        </logic:present>
	</h2>
</div>

<br />
<html:errors/>
 <%session.setAttribute("action_path_key",null);%>
<% String context=request.getContextPath();%>
<html:form action="/contenido/procedimiento/editar" styleId="procedimientoForm" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="idio" id="idio" value="ca">
    
   <logic:present name="procedimientoForm" property="id">
   <input type="hidden" name="espera" value="si" id="espera" />
   </logic:present>
    
    <input type="hidden" name="langTrad" id="langTrad" value="ca">
    <logic:present name="procedimientoForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <div class="bloc">
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.signatura" /></div>
			<html:text styleClass="ctext" property="signatura" maxlength="256" tabindex="1" />
		</div>
        <logic:notPresent role="sacoper" >
            <div class="component">
                <div class="etiqueta"><bean:message key="procedimiento.validacion" /></div>
                <html:select property="validacion" tabindex="3">
                    <html:option value="" key="select.defecto" />
                    <html:option value="1" key="validacion.publica" />
                    <html:option value="2" key="validacion.interna" />
                    <html:option value="3" key="validacion.reserva" />
                </html:select>
            </div>
        </logic:notPresent>
        <logic:present role="sacoper">
            <logic:present name="procedimientoForm" property="id">
                <html:hidden property="validacion" />
            </logic:present>
            <logic:notPresent name="procedimientoForm" property="id">
                <html:hidden property="validacion" value="2"/>
            </logic:notPresent>
        </logic:present>
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.fechaCaducidad" /></div>
			<html:text styleClass="ctext" property="textoFechaCaducidad" maxlength="15" tabindex="4" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaCaducidad')" tabindex="5"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.fechaPublicacion" /></div>
			<html:text styleClass="ctext" property="textoFechaPublicacion" maxlength="15" tabindex="6" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaPublicacion')" tabindex="7"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.fechaActualizacion" /></div>
			<html:text styleClass="ctext" property="textoFechaActualizacion" maxlength="15" tabindex="8" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaActualizacion')" tabindex="9"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.ua.responsable" /></div>
            <html:hidden property="idUA" />
            <html:text styleClass="ctext" property="nombreUA" readonly="true" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirUAResponsable()" tabindex="10"><bean:message key="boton.seleccionar" /></html:button>
			</div>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="procedimiento.ua.resolver" /></div>
            <html:hidden property="idUAResol" />
            <html:text styleClass="ctext" property="nombreUAResol" readonly="true" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirUAResolver()" tabindex="10"><bean:message key="boton.seleccionar" /></html:button>
            </div>
        </div>
        
        <div class="component">
            <div class="etiqueta"><bean:message key="procedimiento.familia" /></div>
            <html:hidden property="idFamilia" />
            <html:text styleClass="ctext" property="nombreFamilia" readonly="true" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirDato('Familia')" tabindex="11"><bean:message key="boton.seleccionar" /></html:button>
            </div>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="procedimiento.iniciacion" /></div>
            <html:hidden property="idIniciacion" />
            <html:text styleClass="ctext" property="nombreIniciacion" readonly="true" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirDato('Iniciacion')" tabindex="12"><bean:message key="boton.seleccionar" /></html:button>
            </div>
        </div>

        <logic:notEmpty name="mostrarTramite">
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.identificadorsistra" /></div>
			<html:text styleClass="ctext" property="tramite" maxlength="256" tabindex="13"  />
		</div>        
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.version" /></div>
			<html:text styleClass="ctext" property="version" maxlength="256" tabindex="14" />
		</div>        
        </logic:notEmpty>
        <div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.urlexterna" /></div>
			<html:text styleClass="ctext" property="url" maxlength="1024" tabindex="15"  />
		</div>   
		<div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.indicador" /> </div>
			<input type="checkbox"  class="ctext"  name="indicador" tabindex="16"
			 <logic:equal name="procedimientoForm" property="indicador" value="1">
				 checked
            		 </logic:equal>
			  >
		  
		<div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.ventana" /> </div>
			<input type="checkbox"  class="ctext"  name="ventana" tabindex="17"
			 <logic:equal name="procedimientoForm" property="ventana" value="1">
				 checked
            		 </logic:equal>
			  >
		</div>  
		<div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.taxa" /> </div>
			<input type="checkbox"  class="ctext"  name="taxa" tabindex="18"
			 <logic:equal name="procedimientoForm" property="taxa" value="on">
				 checked
            		 </logic:equal>
			  >
		</div>  
		
		<div class="component">
			<div class="etiqueta"><bean:message key="procedimiento.info" /><br/><br/><br/></div>
				<html:textarea property="info" rows="3" cols="60" tabindex="19" />
			</div>
    	</div>
		<div class="component">
             <div class="etiqueta"><bean:message key="procedimiento.responsable" /></div> 
            <html:text styleClass="ctext" property="responsable" size="43" maxlength="256" tabindex="19" /> 
        </div>
		<div class="etiqueta">    
	      <logic:present name="procedimientoForm" property="id">
                <logic:present name="host">
                    <logic:notEmpty name="host">
   		 <a href="javascript:obrirProce('<bean:write name="procedimientoForm" property="id"   />')" class="ctext" onfocus="this.blur()"> previsualizar contenido</a>
                    </logic:notEmpty>
	      </logic:present>
		      </logic:present>
		</div>       
	</div>  
    <br />

    <div id="capes">
    <bean:size id="capes" name="procedimientoForm" property="traducciones" />
    <logic:iterate id="traducciones" name="procedimientoForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa">
            <div class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) { %>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                    <% } %>
                </logic:iterate>
            </div>
            
            <logic:notPresent name="traductor">
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="121"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.resumen" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="resumen" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.resultat" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="resultat" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.destinatarios" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="destinatarios" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.requisitos" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="requisitos" indexed="true" rows="3" cols="60" tabindex="18" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.plazos" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="plazos" indexed="true" rows="3" cols="60" tabindex="18" styleClass="ctextAreaReadOnly" readonly="true" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.resolucion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="resolucion" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.notificacion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="notificacion" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.lugar" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="lugar" indexed="true" rows="3" cols="60" tabindex="18"   styleClass="ctextAreaReadOnly" readonly="true"/>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.silencio" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="silencio" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.observaciones" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="observaciones" indexed="true" rows="3" cols="60" tabindex="18"  />
                </div>
            </div>
			</logic:notPresent>
            
            <logic:present name="traductor">
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.nombre"/></div>
                     <input type="hidden" name="msgNombre" id="msgNombre" value="<bean:message key="procedimiento.nombre"/>">
                	  <logic:equal name="i" value="0">
                	  		<html:text styleClass="btext" onchange="unCheckValidar('1','msgNombre')" name="traducciones" property="nombre" indexed="true" tabindex="121"  maxlength="256" />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:text styleClass="btext" onchange="unCheckValidar('1','msgNombre')" name="traducciones" property="nombre" indexed="true" tabindex="121"  maxlength="256" />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="121"  maxlength="256" />
                	  		</logic:notEqual>
                	  </logic:notEqual>  
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.resumen" /><br /><br /><br /></div>
                    <input type="hidden" name="msgResumen" id="msgResumen" value="<bean:message key="procedimiento.resumen"/>">
                     <logic:equal name="i" value="0">
                	  		<html:textarea onchange="unCheckValidar('1','msgResumen')" name="traducciones" property="resumen" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:textarea onchange="unCheckValidar('1','msgResumen')" name="traducciones" property="resumen" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="resumen" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>
                </div>
				<div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.resultat" /><br /><br /><br /></div>
					<input type="hidden" name="msgResultat" id="msgResultat" value="<bean:message key="procedimiento.resultat"/>">
                      <logic:equal name="i" value="0">
                	  		<html:textarea onchange="unCheckValidar('1','msgResultat')" name="traducciones" property="resultat" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:textarea onchange="unCheckValidar('1','msgResultat')" name="traducciones" property="resultat" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="resultat" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>
					
					
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.destinatarios" /><br /><br /><br /></div>
                     <input type="hidden" name="msgDestinatarios" id="msgDestinatarios" value="<bean:message key="procedimiento.destinatarios"/>">
                      <logic:equal name="i" value="0">
                	  		<html:textarea onchange="unCheckValidar('1','msgDestinatarios')" name="traducciones" property="destinatarios" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:textarea onchange="unCheckValidar('1','msgDestinatarios')" name="traducciones" property="destinatarios" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="destinatarios" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>
                </div>
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.requisitos" /><br /><br /><br /></div>
                    <input type="hidden" name="msgRequisitos" id="msgRequisitos" value="<bean:message key="procedimiento.requisitos"/>">
                      <logic:equal name="i" value="0">
                	  		<html:textarea onchange="unCheckValidar('1','msgRequisitos')" name="traducciones" property="requisitos" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:textarea onchange="unCheckValidar('1','msgRequisitos')" name="traducciones" property="requisitos" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="requisitos" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>
                </div>
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.plazos" /><br /><br /><br /></div>
                    <input type="hidden" name="msgPlazos" id="msgPlazos" value="<bean:message key="procedimiento.plazos"/>"> 
                     <logic:equal name="i" value="0">
                	  		<html:textarea onchange="unCheckValidar('1','msgPlazos')"  name="traducciones" property="plazos" indexed="true" rows="3" cols="60" tabindex="18" styleClass="ctextAreaReadOnly" readonly="true" />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:textarea onchange="unCheckValidar('1','msgPlazos')"  name="traducciones" property="plazos" indexed="true" rows="3" cols="60" tabindex="18" styleClass="ctextAreaReadOnly" readonly="true" />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="plazos" indexed="true" rows="3" cols="60" tabindex="18" styleClass="ctextAreaReadOnly" readonly="true" />
                	  		</logic:notEqual>
                	  </logic:notEqual>
                </div>
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.resolucion" /><br /><br /><br /></div>
                      <input type="hidden" name="msgResolu" id="msgResolu" value="<bean:message key="procedimiento.resolucion"/>"> 
                      <logic:equal name="i" value="0">
                	  		<html:textarea onchange="unCheckValidar('1','msgResolu')" name="traducciones" property="resolucion" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			<html:textarea onchange="unCheckValidar('1','msgResolu')" name="traducciones" property="resolucion" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="resolucion" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>
                </div>
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.notificacion" /><br /><br /><br /></div>
                    <input type="hidden" name="msgNotif" id="msgNotif" value="<bean:message key="procedimiento.notificacion"/>">
                      <logic:equal name="i" value="0">
                	 		<html:textarea onchange="unCheckValidar('1','msgNotif')" name="traducciones" property="notificacion" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			 <html:textarea onchange="unCheckValidar('1','msgNotif')" name="traducciones" property="notificacion" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="notificacion" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>                    
                </div>
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.lugar" /><br /><br /><br /></div>
                    <input type="hidden" name="msgLugar" id="msgLugar" value="<bean:message key="procedimiento.lugar"/>">
                     <logic:equal name="i" value="0">
                	 		<html:textarea onchange="unCheckValidar('1','msgLugar')" name="traducciones" property="lugar" indexed="true" rows="3" cols="60" tabindex="18"  styleClass="ctextAreaReadOnly" readonly="true" />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			 <html:textarea onchange="unCheckValidar('1','msgLugar')" name="traducciones" property="lugar" indexed="true" rows="3" cols="60" tabindex="18"   styleClass="ctextAreaReadOnly" readonly="true"/>
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="lugar" indexed="true" rows="3" cols="60" tabindex="18"   styleClass="ctextAreaReadOnly" readonly="true"/>
                	  		</logic:notEqual>
                	  </logic:notEqual> 
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.silencio" /><br /><br /><br /></div>
                    <input type="hidden" name="msgSilenc" id="msgSilenc" value="<bean:message key="procedimiento.silencio"/>">
                      <logic:equal name="i" value="0">
                	 		<html:textarea onchange="unCheckValidar('1','msgSilenc')" name="traducciones" property="silencio" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			 <html:textarea onchange="unCheckValidar('1','msgSilenc')" name="traducciones" property="silencio" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="silencio" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual> 
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="procedimiento.observaciones" /><br /><br /><br /></div>
                    <input type="hidden" name="msgObserv" id="msgObserv" value="<bean:message key="procedimiento.observaciones"/>">
                      <logic:equal name="i" value="0">
                	 		 <html:textarea onchange="unCheckValidar('1','msgObserv')" name="traducciones" property="observaciones" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  </logic:equal>
                	  <logic:notEqual name="i" value="0">
                	  		<logic:equal name="i" value="1">
                	  			  <html:textarea onchange="unCheckValidar('1','msgObserv')" name="traducciones" property="observaciones" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:equal>
                	  		<logic:notEqual name="i" value="1">
                	  			<html:textarea name="traducciones" property="observaciones" indexed="true" rows="3" cols="60" tabindex="18"  />
                	  		</logic:notEqual>
                	  </logic:notEqual>
                </div>
            </div>
            </logic:present>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        
          <logic:present name="traductor">
	        <logic:present name="procedimientoForm" property="id">
		        <html:submit styleClass="esquerra" property="action" tabindex="100" onclick="return inicioTraduccion();">
		                <bean:message key="boton.traducir" />
		         </html:submit>
	        </logic:present>
          </logic:present>
	       
        <html:submit styleClass="esquerra" property="action" tabindex="100" onclick="return validar(this.form);">
            <logic:notPresent name="procedimientoForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="procedimientoForm" property="id">
                <bean:message key="boton.modificar" />
             </logic:present>
         </html:submit>
         
         <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
         <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
         <logic:notPresent name="procedimientoForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" ><bean:message key="boton.busqueda" /></html:submit>
         </logic:notPresent>
         <logic:present name="procedimientoForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
         </logic:present>
    </div>
 	
</html:form>

	   <logic:present name="traductor">
		   <logic:present name="procedimientoForm" property="id">
		        <logic:present name="booleanCheckValidar">
		   			<input type="checkbox" name="checkValidar" id="checkValidar" value="on" /><bean:message key="check.validado" />
		   		</logic:present>
		   		<logic:notPresent name="booleanCheckValidar">
		   			<input type="checkbox" name="checkValidar" id="checkValidar" value="on" checked="checked" /><bean:message key="check.validado" />
		   		</logic:notPresent>
		   </logic:present>
	   </logic:present>


<logic:notPresent name="fichaForm" property="id">
    <center>
        [<a href="javascript: formCodigo()" ><bean:message key="ficha.seleccionar.codigo" /></a>]
    </center>
</logic:notPresent>

<!-- XAPUÇA -->

<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="procedimientoForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>
<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>
<logic:present name="procedimientoForm" property="id">

    <br /><br />

    <!-- Tramites relacionados -->
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="procedimiento.relacion.tramites" />
        </div>
        <logic:empty name="tramiteOptions">
            <br /><h2><bean:message key="procedimiento.relacion.tramites.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="tramiteOptions">
		  <html:form action="/contenido/procedimiento/editar" >        <!--  action para mapear la clase java -->
            <logic:iterate id="tramite" name="tramiteOptions">
            <div class="component">
                <div class="textconsulta2">
				     <table>
	                     <tr><td>
		                    <input type="text" name="orden<bean:write name="tramite" property="id" />" size="4" maxlength="4" value="<bean:write name="tramite" property="orden" />" />
		                 </td>
		                 <td>   
	                    	<bean:write name="tramite" property="traduccion.nombre" />
	                     </td></tr>
               		 </table> 
                </div>
                <div class="botoneraconsulta2">
                	<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="<%=context%>/contenido/tramite/seleccionar.do?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="tramite" property="id" />&titol=tramite.modificacion";' />
                	<input type="button" value="<bean:message key="boton.eliminar" />" name="<bean:message key="boton.eliminar" />" onclick='document.location.href="<%=context%>/contenido/tramite/seleccionar.do?action=<bean:message key="boton.eliminar" />&idProcedimiento=<bean:write name="procedimientoForm" property="id" />&idSelect=<bean:write name="tramite" property="id" />";' />
                </div>
            </div>
            </logic:iterate>
			<input type="hidden" name="procedimiento" value='<bean:write name="procedimientoForm" property="id" />' />
			<input type="hidden" name="action" value='<bean:message key="procedimiento.relacion.tramites" />' />  <!--  action para mapear la operacion -->
			<input type="hidden" name="operacion" value='actualizar_orden' />                   	
			<html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
			</html:form>             
        </logic:notEmpty>
    </div><br />
    <center>

		<script type="text/javascript">
		
			//funcio per reemplaçar totes les ocurrencies del caracter (')
			function reemplazar(str) {
				str=str.replace(/'/g,"\\'");  
				return str;
			}
		</script>

		[<a href='javascript:location.href=reemplazar("<%=context%>/contenido/tramite/crear.do?action=Crear&idProcedimiento=<bean:write name="procedimientoForm" property="id"/>&idUA=<bean:write name="procedimientoForm" property="idUA"/>&nombreUA=<bean:write name="procedimientoForm" property="nombreUA"/>")'>
		<bean:message key="boton.nuevo" /></a>]
	
    
    </center>
    <!-- /Tramites relacionados -->

    <br /><br />

    <!-- Documentos relacionados -->
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="procedimiento.relacion.documentos" />
        </div>
        <logic:empty name="documentoOptions">
            <br /><h2><bean:message key="procedimiento.relacion.docs.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="documentoOptions">
	        <html:form action="/contenido/procedimiento/editar" >        <!--  action para mapear la clase java -->
            <logic:iterate id="documento" name="documentoOptions">
            <logic:notEmpty name="documento">
            <div class="component">
                <div class="textconsulta2">
                	 <table>
	                     <tr><td>
		                    <input type="text" name="orden_doc<bean:write name="documento" property="id" />" size="4" maxlength="4" value="<bean:write name="documento" property="orden" />" />
		                 </td>
		                 <td>   
	                    	<bean:write name="documento" property="traduccion.titulo" />
	                     </td></tr>
               		 </table> 
                </div>
                <div class="botoneraconsulta2">
                	<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="<%=context%>/contenido/documento/seleccionar.do?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="documento" property="id" />&idProcedimiento=<bean:write name="procedimientoForm" property="id" />&titol=documento.modificacion";' />                	
                	<input type="button" value="<bean:message key="boton.eliminar" />" name="<bean:message key="boton.eliminar" />" onclick='document.location.href="<%=context%>/contenido/documento/seleccionar.do?action=<bean:message key="boton.eliminar" />&idSelect=<bean:write name="documento" property="id" />&idProcedimiento=<bean:write name="procedimientoForm" property="id" />";' />                	
                </div>
            </div>
            </logic:notEmpty>
            </logic:iterate>
			<input type="hidden" name="procedimiento" value='<bean:write name="procedimientoForm" property="id" />' />
			<input type="hidden" name="action" value='<bean:message key="procedimiento.relacion.documentos" />' />  <!--  action para mapear la operacion -->
			<input type="hidden" name="operacion" value='actualizar_orden' />                   	
			<html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
			</html:form>             
        </logic:notEmpty>
    </div><br />
    <center>  
		[<a href="javascript: obrirConfirmant('<%=context%>/contenido/documento/form.do?idProcedimiento=<bean:write name="procedimientoForm" property="id" />&titol=documento.alta','Si ha realizado cambios y no los guarda antes de asignar los documentos, se perderán. ¿Quiere continuar con la inserción de documentos?')" ><bean:message key="boton.nuevo" /></a>]    
    </center>
    <!-- /Documentos relacionados -->

    <br /><br />

    <!-- Materias relacionadas -->
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="procedimiento.relacion.materias.titulo" />
        </div>
        <logic:empty name="materiaOptions">
            <br /><h2><bean:message key="procedimiento.relacion.materias.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="materiaOptions">
            <logic:iterate id="materia" name="materiaOptions">
            <div class="component">
                <div class="textconsulta1">
                <html:link action='<%="/sistema/materia/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="materia" paramProperty="id">
                    <bean:write name="materia" property="traduccion.nombre" />
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/procedimiento/editar" >
                        <input type="hidden" name="materia" value='<bean:write name="materia" property="id" />' />
                        <input type="hidden" name="procedimiento" value='<bean:write name="procedimientoForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="procedimiento.relacion.materias" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaOpciones('relaciona', 'materia')" ><bean:message key="boton.nuevo" /></a>]
    </center>
    <!-- /Materias relacionadas -->

    <br /><br />

    <!-- Normativas relacionadas -->
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="procedimiento.relacion.normativas" />
        </div>
        <logic:empty name="normativaOptions">
            <br /><h2><bean:message key="procedimiento.relacion.normativas.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="normativaOptions">
            <logic:iterate id="normativa" name="normativaOptions">
            <div class="component">
                <div class="textconsulta1">
                    <logic:equal name="normativa" property="class" value="class org.ibit.rol.sac.model.NormativaLocal" >
                        <html:link action='<%="/contenido/normativa/local/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="normativa"paramProperty="id">
                            <bean:write name="normativa" property="traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                    <logic:equal name="normativa" property="class" value="class org.ibit.rol.sac.model.NormativaExterna" >
                        <html:link action='<%="/contenido/normativa/externa/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="normativa"paramProperty="id">
                            <bean:write name="normativa" property="traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                     <logic:equal name="normativa" property="class" value="class org.ibit.rol.sac.model.NormativaExternaRemota" >
                        <html:link action='<%="/contenido/normativa/externa/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="normativa"paramProperty="id">
                            <bean:write name="normativa" property="traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                    
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/procedimiento/editar" >
                        <input type="hidden" name="normativa" value='<bean:write name="normativa" property="id" />' />
                        <input type="hidden" name="procedimiento" value='<bean:write name="procedimientoForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="procedimiento.relacion.normativas" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
        [<a href="javascript: popupFormNormativas()" ><bean:message key="boton.nuevo" /></a>]

    </center>
    <!-- /Normativas relacionadas -->

    <br /><br />

    <tiles:insert definition=".include.comentarios">
        <tiles:put name="idRel" beanName="procedimientoForm" beanProperty="id"/>
        <tiles:put name="tipo" value="procedimiento"/>
    </tiles:insert>

    <br /><br />
    
</logic:present>


<logic:present name="alert">
    <script type="text/javascript">
<!--
	window.setTimeout("handleAlert()",1000)
-->
	</script>
 </logic:present>

