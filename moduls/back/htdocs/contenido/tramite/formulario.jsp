<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ page import="org.apache.struts.Globals,
                 org.ibit.rol.sac.model.Validacion" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<%String idProcedimiento = null;%>
<logic:notPresent name="idProcedimiento" >
    <bean:parameter id="aux" name="idProcedimiento" value="0" />
    <%idProcedimiento = aux;%>
</logic:notPresent>
<logic:present name="idProcedimiento">
    <bean:define id="aux" name="idProcedimiento" type="java.lang.Long" />
    <%idProcedimiento = aux.toString();%>
</logic:present>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--

<logic:present name="alert">
              function handleAlert() {
					 alert("<bean:message name='alert' />");
				}
 </logic:present>

<logic:present name="alertVuds"> 
             function handleAlertVuds() {
				alert("<bean:write name='alertVuds' />");
            }
</logic:present>

	function inicioTraduccion(){
	    return confirm('<bean:message key="traduccion.inicio" />');
	}

function validar(form) {
   return validateTramiteForm(form);
}


	// Funcio per mostrar una capa i ocultar la resta
	function activarCapa(capa, n) {
        for (i = 0; i < n; i++) {
            capeta = document.getElementById("capa" + i);
            if (i == capa) {
                capeta.style.visibility="visible";
            } else {
                capeta.style.visibility="hidden";
            }
        }
	}

    function baja(){
        return confirm('<bean:message key="alerta.baja" />');
    }
/*
    function validar(form) {
       return validateProcedimientoForm(form);
    }
*/
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

    function abrirUA() {
        poprealcion = obrir("<html:rewrite page='/organigrama/unidad/poparbol.do'/>?idUA=0&action=&", "<bean:message key='boton.seleccionar' />", 538, 440);
    }


    
    function obrirTramit(tid)
     {
      window.open("<html:rewrite page='/contenido/tramite/vuds/previsualitzar.do'/>?tid="+tid, 'tramite_Vuds', 'scrollbars=yes, resizable=yes, top=100,left=200, width=800,height=600');
     }
    
    function actualizaUA(id, nombre) {
        eval("document.forms[0].idOrganCompetent.value=id");
        eval("document.forms[0].nomOrganCompetent.value=nombre");
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


	

    function obrirCodisVuds() {
        obrirScroll("<html:rewrite page='/contenido/tramite/vuds/popupCodisVuds.jsp'/>", "<bean:message key='boton.seleccionar' />", 750, 440);
    }

	function actualitzaCodiVuds(id, nom) {
        eval("document.forms[0].codiVuds.value=id");
        eval("document.forms[0].descCodiVuds.value=nom");
    }
    
	function actualitzaOrganCompetent(id, nom) {
       eval("document.forms[0].idOrganCompetent.value=id");
        eval("document.forms[0].nomOrganCompetent.value=nom");
    }
    
	
// -->
</script>

<div class="bloc">
    <h1>
        <logic:notPresent name="tramiteForm" property="id">
            <bean:message key="tramite.alta" />
        </logic:notPresent>
        <logic:present name="tramiteForm" property="id">
            <bean:message key="tramite.modificacion" />
        </logic:present>
    </h1>
    <logic:present name="vuds">
    <h2><bean:message key="tramite.vuds.datos" /></h2>
    </logic:present>
    <logic:notPresent name="vuds">
    <h2><bean:message key="tramite.datos" /></h2>
    </logic:notPresent>
</div>


<br />

<html:errors/>

<%session.setAttribute("action_path_key",null);%>
<html:form action="/contenido/tramite/editar" styleId="tramiteForm" method="POST">
    <logic:present name="tramiteForm" property="id">
	<input type="hidden" name="espera" value="si" id="espera" />
	</logic:present>
	<input type="hidden" name="langTrad" value="ca" id="langTrad" />
    <logic:present name="tramiteForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <input type="hidden" name="idProcedimiento" value="<%=idProcedimiento%>" />
    
		
    <div class="bloc">
		<div class="component">
            <div class="etiqueta"><bean:message key="tramite.organCompetent" /></div>
            <html:hidden property="idOrganCompetent" />
            <html:text styleClass="ctext" property="nomOrganCompetent" readonly="true" />
            <div class="botoneraconsulta4">
                <html:button property="boton" onclick="abrirUA()" tabindex="10"><bean:message key="boton.seleccionar" /></html:button>
            </div>
			
			<logic:present name="idUA">

			<script type="text/javascript">
					actualitzaOrganCompetent('<bean:write name="idUA"/>',"<bean:write name='nombreUA'  filter='false'/>");
				</script>
			</logic:present>
			
			
        </div>		
       <div class="component">
            <div class="etiqueta"><bean:message key="tramite.estado" /></div>
            <html:select property="fase" tabindex="11">
                <html:option value="1" key="tramite.iniciacion" />
                <html:option value="2" key="tramite.instruccion" />
                <html:option value="3" key="tramite.finalizacion" />
            </html:select>
       </div>

		
<br>    
<fieldset>
<legend>
<bean:message key="tramite.fieldset.publicacio" />
</legend>
       <div class="component">
                <div class="etiqueta"><bean:message key="tramite.validacio" /></div>
                <html:select property="validacio" tabindex="12">
                    <html:option value="" key="select.defecto" />
                    <html:option value="1" key="tramite.validacio.publica" />
                    <html:option value="2" key="tramite.validacio.interna" />
                    <html:option value="3" key="tramite.validacio.reserva" />
                </html:select>
       </div>
       <div class="component">
			<div class="etiqueta"><bean:message key="tramite.fechaCaducidad" /></div>
			<html:text styleClass="ctext" property="textoFechaCaducidad" maxlength="15" tabindex="13" />
            <div class="botoneraconsulta4">
                <html:button property="boton" onclick="abrirCalendario('textoFechaCaducidad')" tabindex="14"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
		<div class="component">
			<div class="etiqueta"><bean:message key="tramite.fechaPublicacion" /></div>
			<html:text styleClass="ctext" property="textoFechaPublicacion" maxlength="15" tabindex="15" />
            <div class="botoneraconsulta4">
                <html:button property="boton" onclick="abrirCalendario('textoFechaPublicacion')" tabindex="16"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="tramite.fechaActualizacion" /></div>
			<html:text styleClass="ctext" property="textoFechaActualizacion" maxlength="15" tabindex="17" />
            <div class="botoneraconsulta4">
                <html:button property="boton" onclick="abrirCalendario('textoFechaActualizacion')" tabindex="18"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
</fieldset>
<br>


<br>
<fieldset>
<legend>
<bean:message key="tramite.fieldset.telematic" />
</legend>
		 <div class="component">
            <div class="etiqueta"><bean:message key="tramite.idTraTel" /></div>
            <html:text styleClass="ctext" property="idTraTel" tabindex="19"/>
        </div>
        
        <div class="component">
            <div class="etiqueta"><bean:message key="tramite.versio" /></div>
            <html:text styleClass="ctext" property="versio" tabindex="20"/>
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="tramite.urlExterna" /></div>
            <html:text styleClass="ctext" property="urlExterna" tabindex="21" />
        </div>

</fieldset>

<br>

<logic:present name="vuds">

<fieldset>
<legend>
<bean:message key="tramite.fieldset.ventanilla" />
</legend>
        <div class="component">
          <div class="etiqueta"><bean:message key="tramite.codiVuds" /></div>
            <html:hidden property="codiVuds" />
            <html:text styleClass="ctext" property="descCodiVuds"  />
            <div class="botoneraconsulta4">
                <html:button property="boton" onclick="obrirCodisVuds()" tabindex="1"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>


		 <div class="component">
            <div class="etiqueta"><bean:message key="tramite.dataActualitzacioVuds" /></div>
            <html:text styleClass="ctext" property="dataActualitzacioVuds" tabindex="19"/>
        </div>
        
        <div class="etiqueta" style="width: 240px;">
	   		 <!-- a href="javascript:obrirTramit('<bean:write name="tramiteForm" property="id"   />')" class="ctext" onfocus="this.blur()"> <bean:message key="tramite.vuds.previ" /></a-->
        </div>

</fieldset>
</logic:present>

<br>
		        
</div>

    <br />

    <div id="capes">
    <bean:size id="capes" name="tramiteForm" property="traducciones" />
    <logic:iterate id="traducciones" name="tramiteForm" property="traducciones" indexId="i" >
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
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="tramite.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="22"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="tramite.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" rows="3" cols="60" tabindex="23"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="tramite.documentacion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="documentacion" indexed="true" rows="3" cols="60" tabindex="24" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="tramite.requisit" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="requisits" indexed="true" rows="3" cols="60" tabindex="25"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="tramite.plazos" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="plazos" indexed="true" rows="3" cols="60" tabindex="26"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="tramite.lugar" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="lugar" indexed="true" rows="3" cols="60" tabindex="27"  />
                </div>
                
            </div>
        </div>
    </logic:iterate>
    </div>  

    <div class="botonera">
         <logic:present name="traductor">
	         <logic:present name="tramiteForm" property="id">
		         <html:submit styleClass="esquerra" property="action" tabindex="100" onclick="return inicioTraduccion();">
		                	<bean:message key="boton.traducir" />
		         </html:submit>
	         </logic:present>
         </logic:present>
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="28">
            <logic:notPresent name="tramiteForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="tramiteForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101"><bean:message key="boton.reiniciar" /></html:reset>
        <html:submit styleClass="dreta" property="action" tabindex="103"><bean:message key="boton.cancelar" /></html:submit>
    </div>
    </html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="tramiteForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="tramiteForm" property="id">

<logic:present name="taxa">
 	<br /><br />

    
    <!-- Taxes relacionades -->
       <jsp:include page="../common/elementOrdenat.jsp">
    	<jsp:param name="titol" value="taxa.lista"/>
    	<jsp:param name="options" value="taxesOptions"/>
    	<jsp:param name="empty" value="taxa.vacio"/>
    	<jsp:param name="campTitol" value="traduccion.codificacio"/>
    	<jsp:param name="action" value="/contenido/tramite/editar"/>
    	<jsp:param name="urlBotonera" value="/contenido/taxa/seleccionar.do"/>
		<jsp:param name="relacioElements" value="tramite.relacion.taxes"/>
    	<jsp:param name="containerFormId" value="idTramite"/>
    	<jsp:param name="containerFormName" value="tramiteForm"/>
		<jsp:param name="formName" value="taxa"/>
    	<jsp:param name="texteNouElement" value="taxa.nuevo"/>
    	<jsp:param name="altaElement" value="taxa.alta"/>
    	<jsp:param name="modifElement" value="taxa.modificacion"/>
    </jsp:include>
    
</logic:present>
    
    <br /><br />
    
    <!-- Formularios relacionados -->
       <jsp:include page="../common/elementOrdenat.jsp">
    	<jsp:param name="titol" value="formulario.lista"/>
    	<jsp:param name="options" value="formularioOptions"/>
    	<jsp:param name="empty" value="formulario.vacio"/>
    	<jsp:param name="campTitol" value="traduccion.titulo"/>
    	<jsp:param name="action" value="/contenido/tramite/editar"/>
    	<jsp:param name="urlBotonera" value="/contenido/tramite/document/seleccionar.do"/>
		<jsp:param name="relacioElements" value="tramite.relacion.documents"/>
		<jsp:param name="containerFormId" value="idTramite"/>
		<jsp:param name="containerFormName" value="tramiteForm"/>
		<jsp:param name="formName" value="documento"/>
		<jsp:param name="paramsNouElement" value="&tipus=1"/>
		<jsp:param name="texteNouElement" value="formulario.nuevo"/>
    	<jsp:param name="altaElement" value="formulario.alta"/>
    	<jsp:param name="modifElement" value="formulario.modificacion"/>
    </jsp:include>

    <br /><br />
   
     
    <!-- Documents informatius -->
    <jsp:include page="../common/elementOrdenat.jsp">
    	<jsp:param name="titol" value="tramite.relacion.documentsInformatius"/>
    	<jsp:param name="options" value="docInformatiuOptions"/>
    	<jsp:param name="empty" value="tramite.relacion.docsInformatius.vacio"/>
    	<jsp:param name="campTitol" value="traduccion.titulo"/>
    	<jsp:param name="action" value="/contenido/tramite/editar"/>
    	<jsp:param name="urlBotonera" value="/contenido/tramite/document/seleccionar.do"/>
		<jsp:param name="relacioElements" value="tramite.relacion.documents"/>
		<jsp:param name="containerFormId" value="idTramite"/>
    	<jsp:param name="containerFormName" value="tramiteForm"/>
		<jsp:param name="formName" value="documento"/>
		<jsp:param name="paramsNouElement" value="&tipus=0"/>
    	<jsp:param name="texteNouElement" value="documentInformatiu.nuevo"/>
    	<jsp:param name="altaElement" value="docInformatiu.alta"/>
    	<jsp:param name="modifElement" value="docInformatiu.modificacion"/>
    </jsp:include>
   
    <br /><br />
   

</logic:present>   


<!-- ejaen@dgtic. Els alerts es posen en una funcio que es crida un cop carregada la pagina.
					això es fa per tal de poder fer tests amb Selenium, doncs no suporta
					alerts generats durant el load de la pagina.
					  
 -->   
<logic:present name="alertVuds">
	<script type="text/javascript">
<!--
	window.setTimeout("handleAlertVuds()",1000)
-->
	</script>
 </logic:present>


<logic:present name="alert">
    <script type="text/javascript">
<!--
	window.setTimeout("handleAlert()",1000)
-->
	</script>
 </logic:present>

