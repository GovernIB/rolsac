<%@ page language="java" %>
<%@ page import="org.apache.struts.Globals,
                 org.ibit.rol.sac.model.Validacion" %>
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

	function inicioTraduccion(){
	    return confirm("<bean:message key='traduccion.inicio' />");
	}

	function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateFichaForm(form);
    }

    // Llamada al popup para seleccionar la fecha de un campo
    function abrirCalendario(field) {
        obrirFixa("<html:rewrite page='/moduls/calendario.do'/>?field=" + field);
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
      window.open("<logic:present name="host"><bean:write name="host" /></logic:present>/govern/sac/fitxa.do?previ=s&lang="+idi+"&codi="+id, 'ficha', 'scrollbars=yes, resizable=yes, top=100,left=200, width=800,height=600');
    }
       // Llamada para el popup de calendario para fijar la fecha
    function seleccionaDia(data, field) {
 
    	if (field=="textoFechaPublicacion") {
   		// Para el campo fecha de publicacion le aniado la hora local    	
			var ahora = new Date();
//			var hora = ahora.getHours();
//			var minuto = ahora.getMinutes();
//			var segundo = ahora.getSeconds();
			var hora = 0;
			var minuto = 0;
			var segundo = 0;						
			if (hora<10) hora = "0" + hora;
			if (minuto<10) minuto = "0" + minuto;
			if (segundo<10) segundo = "0" + segundo;
    		document.forms[0].textoFechaPublicacion.value = data  + " " + hora + ":" + minuto + ":" + segundo;
    	}
        else if (field=="textoFechaCaducidad") {
   		// Para el campo textoFechaCaducidad le aniado la hora local
			var ahora = new Date();
//			var hora = ahora.getHours();
//			var minuto = ahora.getMinutes();
//			var segundo = ahora.getSeconds();
			var hora = 23;
			var minuto = 59;
			var segundo = 59;			
			if (hora<10) hora = "0" + hora;
			if (minuto<10) minuto = "0" + minuto;
			if (segundo<10) segundo = "0" + segundo;
    		document.forms[0].textoFechaCaducidad.value = data  + " " + hora + ":" + minuto + ":" + segundo;
    	}
        else{
        	eval("document.forms[0]." + field + ".value=data");
	    }
	}

	// Llamada para el popup de microsites (MODIFICADO INDRA)
	function abrirDato(opcion) {
		var filtro = document.getElementById('titulo').value;
        obrir("<html:rewrite page='/contenido/normativa/popup.do'/>?opcion=" + opcion + "&filtro=" + filtro, "<bean:message key='boton.seleccionar' />", 538, 140);
    }

	// Fija la url del microsite (MODIFICADO INDRA)
    function actualizaDato(id, nombre, portalUrl) {
    	var url = portalUrl+"/sacmicrofront/index.do?mkey="+id;
    	eval("document.getElementById('titulo').value=nombre");
        eval("document.getElementById('url').value=url");
    }

    // Funci�n para abrir la lista de fichasUA para relacion
    function altaFichaUA(){
        obrir("<html:rewrite page='/contenido/ficha/popFichaUA.do'/>", "<bean:message key='boton.seleccionar' />", 538, 175);
    }

    // Funci�n para abrir la lista de materias para busqueda o relacion
    function listaMaterias(opcion){
        if (opcion == "busqueda"){
            obrir("<html:rewrite page='/contenido/ficha/popmateria.do?accion=busqueda'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }
        if (opcion == "relaciona"){
            obrir("<html:rewrite page='/contenido/ficha/popmateria.do?accion=relaciona'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }
    }
    
    // Funci�n para abrir la lista de hechosVitales para busqueda o relacion
    function listaHechoVital(opcion){
        if (opcion == "busqueda"){
            obrir("<html:rewrite page='/contenido/ficha/pophechovital.do?accion=busqueda'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }
        if (opcion == "relaciona"){
            obrir("<html:rewrite page='/contenido/ficha/pophechovital.do?accion=relaciona'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }
    }

    function busquedaHuerfanas(id){
        accion = '<bean:message key='boton.busqueda.huerfanas' />';
        document.location = "<html:rewrite page='/contenido/ficha/seleccionar.do'/>?action=" + accion;
    }

    function formCodigo(){
        obrir("<html:rewrite page='/contenido/ficha/popupcodigo.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    function mostrarFicha(id){
        accion = '<bean:message key="boton.seleccionar" />';
        document.location = "<html:rewrite page='/contenido/ficha/editar.do'/>?action=" + accion + "&idSelect="+ id;
    }

    function buscarPorMateria(id){
        accion = '<bean:message key="boton.busqueda" />';
        document.location = "<html:rewrite page='/contenido/ficha/seleccionar.do'/>?action=" + accion + "&materia=" + id;
    }
    
    function buscarPorHechoVital(id){
        accion = '<bean:message key="boton.busqueda" />';
        document.location = "<html:rewrite page='/contenido/ficha/seleccionar.do'/>?action=" + accion + "&hechovital=" + id;
    }

    <logic:present name="fichaForm" property="id">
 
        function asignarMateria(idMateria){
            accion = '<bean:message key="ficha.relacion.materias" />';
            idFicha = '<bean:write name="fichaForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/ficha/editar.do'/>?action=" + accion + "&ficha=" + idFicha + "&materia=" + idMateria + "&operacion=alta";
        }
        
        function asignarHechoVital(idHechovital){
            accion = '<bean:message key="ficha.relacion.hechovital" />';
            idFicha = '<bean:write name="fichaForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/ficha/editar.do'/>?action=" + accion + "&ficha=" + idFicha + "&hechovital=" + idHechovital + "&operacion=alta";
        }

        function asignarFichaUA(idSeccion, idUA){
            accion = '<bean:message key="ficha.relacion.ua" />';
            idFicha = '<bean:write name="fichaForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/ficha/editar.do'/>?action=" + accion + "&ficha=" + idFicha + "&seccion=" + idSeccion + "&ua=" + idUA;
        }

    </logic:present>

	
	// Funcio per mostrar una capa i ocultar la resta. REDEFINIDA PARA EL TINYMCE
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
                //capeta.style.visibility="visible";
                capeta.style.display='block';
            } else {
                //capeta.style.visibility="hidden";
                capeta.style.display='none';
            }
        }
	}	
	
	function abrirForoTema() {
		
		obrir("/gforumback/popupForos.do", "Seleccionar", 538, 440);
			
	}
// -->	
</script>

	<!-- tinyMCE -->
	<script language="javascript" type="text/javascript" src="../../tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
	<script language="javascript" type="text/javascript">
	
		tinyMCE.init({
			mode : "textareas", 
			editor_selector : "mceSimple",
			theme : "advanced",
			plugins : "advlink",
			theme_advanced_buttons1 : "bold,italic,underline,separator,link,unlink,forecolor,removeformat,cleanup,code",
			theme_advanced_buttons2 : "",		
			theme_advanced_buttons3 : "",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_path_location : "bottom",
			verify_html : true,
			accessibility_warnings : true,	
			invalid_elements : "p", 
			force_br_newlines : true,
			theme_advanced_resizing : false,			
			language: "ca"	
		});
		
		tinyMCE.init({
			mode : "textareas", 
			editor_selector : "mceAdvanced",
			theme : "advanced",
			plugins : "advlink",
			theme_advanced_buttons1 : "bold,italic,underline,separator,justifyleft,justifycenter,justifyright,justifyfull,bullist,numlist,separator,outdent,indent,separator,link,unlink,forecolor,removeformat,cleanup,code",
			theme_advanced_buttons2 : "",		
			theme_advanced_buttons3 : "",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_path_location : "bottom",
			verify_html : true,
			accessibility_warnings : true,
			force_br_newlines : false,
			theme_advanced_resizing : false,			
			language: "ca"	
		});		
	</script>
	<!-- /tinyMCE -->

	<style type="text/css">
	<!--
		.capa { position:static !important; visibility:inherit !important; }
		.tiny { display:block; float:none; width:580px; text-align:center; }
	-->
	</style>

<div class="bloc">
    <h1>
        <logic:notPresent name="fichaForm" property="id">
            <bean:message key="ficha.alta" />
        </logic:notPresent>
        <logic:present name="fichaForm" property="id">
            <bean:message key="ficha.modificacion" />
        </logic:present>
    </h1>
    <h2>
    <bean:message key="ficha.datos" />
    <logic:present name="fichaForm" property="id">
       &nbsp;(<bean:message key="ficha.id" />:<bean:write name="fichaForm" property="id" />)
    </logic:present>    
    </h2>
</div>

<br />

<html:errors/>
<% String context=request.getContextPath();%>

<%session.setAttribute("action_path_key",null);%>
<html:form action="/contenido/ficha/editar" styleId="fichaForm" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="idio" value="ca" id="idio" />
	
	<logic:present name="fichaForm" property="id">
	<input type="hidden" name="espera" value="si" id="espera" />
	</logic:present>
	<input type="hidden" name="langTrad" value="ca" id="langTrad" />


    
    <logic:present name="fichaForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <logic:present name="idSeccion">
        <input type="hidden" name="idSeccion" value='<bean:write name="idSeccion" />' />
    </logic:present>
    <logic:present parameter="idSeccion">
        <bean:parameter id="idSeccion" name="idSeccion" />
        <input type="hidden" name="idSeccion" value='<bean:write name="idSeccion" />' />
    </logic:present>

    <div class="bloc">
        <logic:notPresent role="sacoper" >
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.validacion" /></div>
                <html:select property="validacion" tabindex="1">
                    <html:option value="" key="select.defecto" />
                    <html:option value="1" key="validacion.publica" />
                    <html:option value="2" key="validacion.interna" />
                    <html:option value="3" key="validacion.historico" />
                </html:select>
            </div>
        </logic:notPresent>
        <logic:present role="sacoper">
            <logic:present name="fichaForm" property="id">
                <html:hidden property="validacion"/>
            </logic:present>
            <logic:notPresent name="fichaForm" property="id">
                <html:hidden property="validacion" value="2" />
            </logic:notPresent>
        </logic:present>
        <div class="component">
			<div class="etiqueta"><bean:message key="ficha.fechaPublicacion" /></div>
			<html:text styleClass="ctext" property="textoFechaPublicacion" maxlength="19" tabindex="2" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaPublicacion')" tabindex="3"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>        
        <div class="component">
			<div class="etiqueta"><bean:message key="ficha.fechaCaducidad" /></div>
			<html:text styleClass="ctext" property="textoFechaCaducidad" maxlength="19" tabindex="2" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaCaducidad')" tabindex="3"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="ficha.fechaActualizacion" /></div>
			<html:text styleClass="ctext" property="textoFechaActualizacion" maxlength="10" tabindex="2" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaActualizacion')" tabindex="3"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>		
        <logic:notEmpty name="fichaForm" property="nombreicono">
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.icono" /></div>
                <bean:define id="imgurl"><html:rewrite page="/ficha/icono.do" paramId="idFicha" paramName="fichaForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="fichaForm" property="nombreicono" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombreicono" /> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borraricono"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <logic:empty name="fichaForm" property="nombreicono">        
	        <div class="component">
	            <div class="etiqueta"><bean:message key="ficha.icono" /> <bean:message key="ficha.maxfilesize"/></div>
	            <html:file styleClass="bfile" property="fileicono" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
	        </div>
        </logic:empty>        
        <logic:notEmpty name="fichaForm" property="nombrebanner">
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.banner" /></div>
                <bean:define id="imgurl"><html:rewrite page="/ficha/baner.do" paramId="idFicha" paramName="fichaForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="fichaForm" property="nombrebanner" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrebanner" /> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarbanner"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <logic:empty name="fichaForm" property="nombrebanner">        
	        <div class="component">
	            <div class="etiqueta"><bean:message key="ficha.banner" /> <bean:message key="ficha.maxfilesize"/></div>
	            <html:file styleClass="bfile" property="filebanner" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="5" />
	        </div>
        </logic:empty>        
        <logic:notEmpty name="fichaForm" property="nombreimagen">
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.imagen" /></div>
                <bean:define id="imgurl"><html:rewrite page="/ficha/imagen.do" paramId="idFicha" paramName="fichaForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="fichaForm" property="nombreimagen" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombreimagen" /> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarimagen"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <logic:empty name="fichaForm" property="nombreimagen">        
	        <div class="component">
	            <div class="etiqueta"><bean:message key="ficha.imagen" /> <bean:message key="ficha.maxfilesize"/></div>
	            <html:file styleClass="bfile" property="fileimagen" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="6" />
	        </div>
        </logic:empty>        
        <div class="component">
             <div class="etiqueta"><bean:message key="ficha.urlVideo" /></div> 
            <html:text styleClass="ctext" property="urlVideo" size="43" maxlength="1000" tabindex="8" /> 
        </div> 

        <logic:notEmpty name="mostrarForo">

        <div class="component">
    	    <div class="etiqueta"><bean:message key="ficha.urlForo" /></div> 
			<html:hidden styleId="foro_tema" property="foro_tema"/>
	        <html:text styleClass="ctext" styleId="urlForo" property="urlForo" size="20" maxlength="500" tabindex="8" /> 
	        <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirForoTema()" tabindex="3"><bean:message key="boton.seleccionar" /></html:button>
            </div>
        </div>         
 
        </logic:notEmpty>
        
        <div class="component">
			<div class="etiqueta"><bean:message key="ficha.info" /></div>
			<html:textarea property="info" rows="3" cols="60" tabindex="10" />
		</div>
 
 		<div class="component">
             <div class="etiqueta"><bean:message key="ficha.responsable" /></div> 
            <html:text styleClass="ctext" property="responsable" size="43" maxlength="256" tabindex="11" /> 
        </div>
        
        <div class="etiqueta">    
	       <logic:present name="fichaForm" property="id">
   			 <a href="javascript:obrirProce('<bean:write name="fichaForm" property="id"   />')" class="ctext" onfocus="this.blur()"> previsualizar contenido</a>
	      </logic:present>
		</div>                      	        
        <div class="component">
        </div>
    </div>


    <br />

    <div id="capes">
    <bean:size id="capes" name="fichaForm" property="traducciones" />
    <logic:iterate id="traducciones" name="fichaForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa" style="<%=(i.intValue()==0)?"display:block;":"display:none;" %>">
            <div class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) { %>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a>
                    <bean:define id="langTrad"><%=j%></bean:define>
                    </div>
                    <% } %>
                </logic:iterate>
            </div>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="ficha.titulo" /></div>
                    <html:text styleClass="btext" styleId="titulo" name="traducciones" property="titulo" indexed="true" tabindex="7"  maxlength="256" />
                </div>
                
                
				<!-- tiny  -->
				<div>
					<div class="etiqueta tiny"><bean:message key="ficha.descabr" /></div>
					<!--<html:textarea name="traducciones" property="descAbr" indexed="true" rows="5" cols="60" tabindex="8"  style="width:585px; height:120px;" />-->
					<textarea class="mceSimple" name="traducciones[<%=i%>].descAbr" tabindex="8" cols="60" rows="5" style="width:585px; height:120px;"><bean:write name="traducciones" property="descAbr"/></textarea>
				</div>
				<div>
					<div class="etiqueta tiny"><bean:message key="ficha.descripcion" /></div>
                    			<!--<html:textarea name="traducciones" property="descripcion" indexed="true" rows="6" cols="60" tabindex="9"  style="width:585px; height:170px;" />-->
                    			<textarea class="mceAdvanced" name="traducciones[<%=i%>].descripcion" tabindex="8" cols="60" rows="5" style="width:585px; height:120px;"><bean:write name="traducciones" property="descripcion"/></textarea>
				</div>
				<!-- /tiny  -->                
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="ficha.url" /></div>
                    <html:text styleClass="ctext" name="traducciones" styleId="url" property="url" indexed="true"  tabindex="10"  maxlength="512"/>
                    <% String microsites = System.getProperty("es.caib.rolsac.microsites"); 
					   if (microsites == null) microsites = "N" ;
					   if (!microsites.equals("N")) {
					%>
                    <div class="botoneraconsulta1">
                		<html:button property="boton" onclick="abrirDato('Micro')"><bean:message key="boton.seleccionar" /></html:button>
            		</div>
            		<% } %>
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
         <logic:present name="traductor">
	         <logic:present name="fichaForm" property="id">
	         <html:submit styleClass="esquerra" property="action" tabindex="100" onclick="return inicioTraduccion();">
	                	<bean:message key="boton.traducir" />
	         </html:submit>
	         </logic:present>
         </logic:present>
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100">
            <logic:notPresent name="fichaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="fichaForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        
        <html:reset styleClass="esquerra" tabindex="101"><bean:message key="boton.reiniciar" /></html:reset>
        <logic:notPresent name="idSeccion">
            <html:cancel styleClass="dreta" tabindex="103"><bean:message key="boton.cancelar" /></html:cancel>
        </logic:notPresent>
        <logic:present name="idSeccion">
            <html:submit styleClass="dreta" tabindex="103" property="action"><bean:message key="boton.cancelar" /></html:submit>
        </logic:present>
        <logic:notPresent name="fichaForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" >
                <bean:message key="boton.busqueda" />
            </html:submit>
        </logic:notPresent>
        <logic:present name="fichaForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" onclick="return baja();">
                <bean:message key="boton.eliminar" />
            </html:submit>
        </logic:present>
    </div>
    
</html:form>


<center>
    <logic:notPresent name="fichaForm" property="id">
        [<a href="javascript: listaMaterias('busqueda')" ><bean:message key="ficha.busqueda.materia" /></a>]
        [<a href="javascript: listaHechoVital('busqueda')" ><bean:message key="ficha.busqueda.hechovital" /></a>]
        [<a href="javascript: formCodigo()" ><bean:message key="ficha.seleccionar.codigo" /></a>]
        [<a href="javascript: busquedaHuerfanas()" ><bean:message key="ficha.busqueda.huerfana" /></a>]
    </logic:notPresent>

</center>
<!-- XAPU�A -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="fichaForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>
<logic:present name="fichaForm" property="id">

    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ficha.relacion.documentos" />
        </div>
        <logic:empty name="documentoOptions">
            <br /><h2><bean:message key="ficha.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="documentoOptions">
	        <html:form action="/contenido/ficha/editar" >
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
                	<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="<%=context%>/contenido/documento/seleccionar.do?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="documento" property="id" />&idFicha=<bean:write name="fichaForm" property="id" />&titol=documento.modificacion";' />                	
                	<input type="button" value="<bean:message key="boton.eliminar" />" name="<bean:message key="boton.eliminar" />" onclick='document.location.href="<%=context%>/contenido/documento/seleccionar.do?action=<bean:message key="boton.eliminar" />&idSelect=<bean:write name="documento" property="id" />&idFicha=<bean:write name="fichaForm" property="id" />";' />                	
                </div>
            </div>
             </logic:notEmpty>
            </logic:iterate>
			<input type="hidden" name="ficha" value='<bean:write name="fichaForm" property="id" />' />
			<input type="hidden" name="action" value='<bean:message key="ficha.relacion.documentos" />' />
			<input type="hidden" name="operacion" value='actualizar_orden' />                   	
			<html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
			</html:form>                          
        </logic:notEmpty>
    </div><br />
    <center>  
		[<a href="javascript: obrirConfirmant('<%=context%>/contenido/documento/form.do?idFicha=<bean:write name="fichaForm" property="id" />&titol=documento.alta','Si ha realizado cambios y no los guarda antes de asignar los documentos, se perder�n. �Quiere continuar con la inserci�n de documentos?')" ><bean:message key="boton.nuevo" /></a>]
    </center>

    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ficha.relacion.enlaces" />
        </div>
        <logic:empty name="enlaceOptions">
            <br /><h2><bean:message key="ficha.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="enlaceOptions">
	        <html:form action="/contenido/ficha/editar" >
            <logic:iterate id="enlace" name="enlaceOptions">
            <logic:notEmpty name="enlace">
            <div class="component">
                <div class="textconsulta2">
                	 <table>
	                     <tr><td>
		                    <input type="text" name="orden_enl<bean:write name="enlace" property="id" />" size="4" maxlength="4" value="<bean:write name="enlace" property="orden" />" />
		                 </td>
		                 <td>   
	                    	<bean:write name="enlace" property="traduccion.titulo" />
	                     </td></tr>
               		 </table> 
                </div>
                <div class="botoneraconsulta2">                
                	<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="<%=context%>/contenido/enlace/seleccionar.do?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="enlace" property="id" />&idFicha=<bean:write name="fichaForm" property="id" />";' />                	
                	<input type="button" value="<bean:message key="boton.eliminar" />" name="<bean:message key="boton.eliminar" />" onclick='document.location.href="<%=context%>/contenido/enlace/seleccionar.do?action=<bean:message key="boton.eliminar" />&idSelect=<bean:write name="enlace" property="id" />&idFicha=<bean:write name="fichaForm" property="id" />";' />                	
                </div>
            </div>
            </logic:notEmpty>
            </logic:iterate>
			<input type="hidden" name="ficha" value='<bean:write name="fichaForm" property="id" />' />
			<input type="hidden" name="action" value='<bean:message key="ficha.relacion.enlaces" />' />
			<input type="hidden" name="operacion" value='actualizar_orden' />                   	
			<html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
			</html:form>                          
        </logic:notEmpty>
    </div><br />
    <center>  
		[<a href="javascript: obrirConfirmant('<%=context%>/contenido/enlace/form.do?idFicha=<bean:write name="fichaForm" property="id" />','Si ha realizado cambios y no los guarda antes de asignar los enlaces, se perder�n. �Quiere continuar con la inserci�n de enlaces?')" ><bean:message key="boton.nuevo" /></a>]
    </center>

    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ficha.relacion.materias" />
        </div>
        <logic:empty name="materiaOptions">
            <br /><h2><bean:message key="ficha.relacion.materias.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="materiaOptions">
            <logic:iterate id="materia" name="materiaOptions">
            <div class="component">
                <div class="textconsulta1">
                <html:link action='<%="/sistema/materia/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="materia"paramProperty="id">
                    <bean:write name="materia" property="traduccion.nombre" />
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/ficha/editar" >
                        <input type="hidden" name="materia" value='<bean:write name="materia" property="id" />' />
                        <input type="hidden" name="ficha" value='<bean:write name="fichaForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="ficha.relacion.materias" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaMaterias('relaciona')" ><bean:message key="boton.nuevo" /></a>]
    </center>

    <br /><br />
    
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ficha.relacion.hechovital" />
        </div>
        <logic:empty name="hechovitalOptions">
            <br /><h2><bean:message key="ficha.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="hechovitalOptions">
            <logic:iterate id="hechovital" name="hechovitalOptions">
            <div class="component">
                <div class="textconsulta1">
                <html:link action='<%="/sistema/hechovital/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="hechovital"paramProperty="id">
                    <bean:write name="hechovital" property="traduccion.nombre" />
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/ficha/editar" >
                        <input type="hidden" name="hechovital" value='<bean:write name="hechovital" property="id" />' />
                        <input type="hidden" name="ficha" value='<bean:write name="fichaForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="ficha.relacion.hechovital" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaHechoVital('relaciona')" ><bean:message key="boton.nuevo" /></a>]
    </center>

    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ficha.relacion.ua" />
        </div>
        <logic:empty name="fichaUAOptions">
            <br /><h2><bean:message key="ficha.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="fichaUAOptions">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit1" style="background-color: #d8dfe7">
                        <bean:message key="seccion.title" />
                    </div>
                    <div class="textgros5" style="background-color: #d8dfe7">
                        <bean:message key="ua.title" />
                    </div>
                </div>
                <div class="botoneraconsulta1"></div>
            </div>

            <logic:iterate id="fichaUA" name="fichaUAOptions">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit1">&nbsp;
                        <logic:present name="fichaUA" property="seccion">
                        <html:link action='<%="/sistema/seccion/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="fichaUA"paramProperty="seccion.id">
                            <bean:write name="fichaUA" property="seccion.traduccion.nombre" />
                        </html:link>
                        </logic:present>
                    </div>
                    <div class="textgros5">&nbsp;
                        <logic:present name="fichaUA" property="unidadAdministrativa">
                        <html:link action='<%="/organigrama/unidad/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idUA" paramName="fichaUA"paramProperty="unidadAdministrativa.id">
                            <bean:write name="fichaUA" property="unidadAdministrativa.traduccion.nombre" />
                        </html:link>
                        </logic:present>
                    </div>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/ficha/editar" >
                        <input type="hidden" name="fichaUA" value='<bean:write name="fichaUA" property="id" />' />
                        <input type="hidden" name="ficha" value='<bean:write name="fichaForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="ficha.relacion.ua" />' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: altaFichaUA()" ><bean:message key="boton.nuevo" /></a>]
    </center>

    <br /><br />

    <tiles:insert definition=".include.comentarios">
        <tiles:put name="idRel" beanName="fichaForm" beanProperty="id"/>
        <tiles:put name="tipo" value="ficha"/>
    </tiles:insert>

    <br /><br />

</logic:present>

<script type="text/javascript">
<!--
	<logic:present name="alert_ico">
	alert("<bean:message name='alert_ico' />");
	</logic:present>
	
	<logic:present name="alert_ban">
	alert("<bean:message name='alert_ban' />");
	</logic:present>
	
	<logic:present name="alert_img">
	alert("<bean:message name='alert_img' />");
	</logic:present>
	
	<logic:present name="alert">
	   	window.setTimeout("handleAlert()",1000)
	</logic:present>
-->
</script>
