<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- CSS -->
<link href='<c:url value="/css/tm_sia.css"/>' rel="stylesheet" type="text/css" media="screen" />

<!-- VARIABLES -->
<script type="text/javascript">
	
	//Variable basica
	var pag_idioma = $("html").attr("lang");

	//Eventos a .DO
    var pagLlistat  = '<c:url value="/sia/llistatSIAUA.do" />';
	var pagGuardar  = '<c:url value="/sia/grabarSiaUA.do" />';
    var pagDetall   = '<c:url value="/sia/pagDetall.do" />';
    var pagEsborrar = '<c:url value="/sia/borrarSiaUA.do" />';
	
	//textListaFilaSuperior
	var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtDel = "<spring:message code='txt.del'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtOrdenats = "<spring:message code='txt.ordenats'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtPer = "<spring:message code='txt.per'/>";
    
	//Campos de la lista.
    var txtId = "<spring:message code='txt.sia.id'/>";
    var txtIdUA = "<spring:message code='txt.sia.idUA'/>";
    var txtUA = "<spring:message code='txt.sia.siaUA'/>";
    var txtUser = "<spring:message code='txt.sia.usuario'/>";
    var txtPass = "<spring:message code='txt.sia.contra'/>";
    
    //Botones
    var txtBotonInfo = "<spring:message code='boto.info'/>";
    
	//Mensajes de error para el formulario
	var txtUsuarioObligatorio = "Usuario obligatorio";
	var txtContrasenyaObligatorio = "Contraseña obligatoria";
	var txtUAObligatorio = "Unitat administrativa obligatoria";
	var txtCampObligatori ="Campo obligatorio"; //Obligatorio para formulari.js
	var txtLlistaItem = "SIA UA";
	var txtEspere = "<spring:message code='txt.esperi'/>";
	var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_de_la'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtIntenteho = "<spring:message code='error.conexio.intentar_de_nou'/>";
    var txtAjaxError = "<spring:message code='error.conexio.ajax'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.usuari_esborrat_correcte'/>";
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
    
	 var FormulariDades = [
       // Usuario
       {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_usuario",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error": { "obligatori": "<spring:message code='proc.formulari.error.estat.obligatori'/>" }
        },
		
		// Contrasenya
		{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_contrasenya",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error": { "obligatori": "<spring:message code='proc.formulari.error.estat.obligatori'/>" }
        },
		
		// UA
		{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_ua_id",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error": {   "obligatori": "<spring:message code='proc.formulari.error.estat.obligatori'/>"}
        },
		
		// UA NOMBRE
		{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_ua",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error": { "obligatori": "<spring:message code='proc.formulari.error.estat.obligatori'/>" }
        }];

</script>
<script type="text/javascript" src="<c:url value='/js/detall_base_n.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_sia_ua.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/listado_base_n.js'/>"></script>
<jsp:include page="../../layout/variablesGlobalsJavascript.jsp" flush="true"/> 
<script type="text/javascript"> 
	var lista;
</script>

<div id="escriptori_contingut"> 
	
	<!-- TABS -->
	<ul id="opcions">
 	   <li id="tabListado" class="opcio L actiu" data-div="">
            <a href="javascript:void(0)"><spring:message code='sia.listado'/></a>
       </li>
	   <li id="tabNuevaFicha" class="opcions nuevo"><a id="btnNuevaFicha" href="javascript:;" class="btn nou"><span><span>Crear nuevo SIA Unitat Administrativa</span></span></a></li>        
    </ul>
	
	<!-- DIV DE ABAJO CON LOS RESULTADOS QUE TENDRÍAN BACKGROUND BLANCO Y ENCIMA EL TAB. -->
    <div id="resultats" class="resultats L actiu">
		<!-- DIV LISTA -->
        <div id="divListado" class="actiu">   
          <div class="tabBlanco">            
          	<div class="dadesLista">
                <p class="executant"><spring:message code='index.carregant_llistat_index'/></p>
            </div>
          	<br /><br /><br />
			<!-- Div del formulario de búsqueda para el listado. -->
            <div id="formularioBusqueda">
				<input id="pagPag"     		type="hidden" value="0" /> 
				<input id="pagRes"     		type="hidden" value="10" />
				<input id="ordreTipus" 		type="hidden" value="ASC" /> 
				<input id="ordreCamp"  		type="hidden" value="id"  />	
				<input id="ordreCampNombre" type="hidden" value="id"  />	
            </div> 
          </div>            
        </div>
        
	</div>
</div>
	
	<!-- FORMULARIO CON EL CONTENIDO DE UN SIA UA -->
	<form id="formGuardar" action="false">
		
		<!-- escriptori_detall -->
		<div id="escriptori_detall" class="escriptori_detall">
			<input id="item_id" name="item_id" type="hidden" value="" class="nou" />
			<h2><spring:message code='txt.detallProcediment'/></h2>
			<p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
			
			<!-- modulPrincipal -->
			<div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">                
						
				<!-- modul -->
				<div class="modul">
					<fieldset>
						<a class="modul mostrat"><spring:message code='txt.amaga'/></a>
						<legend>DATOS</legend>
						<div class="modul_continguts mostrat" style="margin-top:20px">
							<!-- fila -->
							<div class="fila">
								<div class="element t99p">
									<div class="etiqueta">
										<label for="item_ua">Unitat Administrativa</label>
									</div>                                          
								</div>
							</div>
							
							<!-- fila -->
							<div class="fila">
								<div class="element t50p">
									<div class="control">
										<input id="item_ua_id" name="item_ua_id" type="hidden" />
										<div class="campo">
											<input id="item_ua" name="item_ua" type="text" class="nou" readonly="readonly" />
										</div>
									</div>
								</div>
								<div class="element t50p">
								
										<div class="botones">
											<div class="btnCambiar boton btnGenerico">
												<a href="javascript:carregarArbreTotesUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','item_ua_id', 'item_ua');" class="btn consulta">
													<span><span><spring:message code='boto.canviarOrgan'/></span></span>
												</a>
											</div>
											<div class="boton btnGenerico" style="margin-left:20px">                                    
												<a href="javascript:EliminaArbreUA('item_ua', 'item_ua_id');" class="btn borrar">
													<span><span><spring:message code='boto.esborrar'/></span></span>
												</a>
											</div>
										</div>
									</div>  
								</div>
							<div class="fila"> 
								<div class="element t50p">
									<div class="etiqueta">                                            
										<label for="item_usuario">Usuario</label>
									</div>
									<div class="control">
										<input id="item_usuario" name="item_usuario" type="text" value=""/>
									</div>
								</div>
								<div class="element t50p">
									<div class="etiqueta">
										<label for="item_contrasenya">Contrasenya</label>
									</div>
									<div class="control">
										<input id="item_contrasenya" name="item_contrasenya" type="text" value="" />
									</div>
								</div>
							</div>                        
						 </div> 
					</fieldset>
				</div> 
			</div>
			
			 <!-- modulLateral -->
			<div class="modulLateral">
				<!-- modul -->
				<div class="modul publicacio">
					<fieldset>
						<a class="modul mostrat"><spring:message code='txt.amaga'/></a>
						<legend><spring:message code='txt.publicacio'/></legend>
						<div class="modul_continguts mostrat">
							
							<!-- botonera dalt -->
							<div class="botonera dalt">
							  <ul>
								  <li class="btnVolver impar">
									  <a id="btnVolver" href="javascript:;" class="btn torna btnVolver"><span><span><spring:message code='boto.torna'/></span></span></a>
								  </li>
								  <li class="btnGuardar par">
									  <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
								  </li>
								  <li class="btnEliminar impar" style="display:none;">
									  <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
								  </li>									 
							  </ul>
							</div>
							<!-- /botonera dalt -->
						</div>
					</fieldset>
				</div>
				<!-- /modul -->
			</div>
		</div>
	</form>
	<!-- /FORMULARIO -->