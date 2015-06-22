<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_perfils.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_seccions_perfils_gestor.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_seccions_arbre_perfils.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ajax.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_perfils_gestor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_seccions_perfils_gestor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_seccions_arbre.js'/>"></script>


<script type="text/javascript">
    var pagLlistat = '<c:url value="/perfilsGestor/llistat.do" />';
    var pagDetall = '<c:url value="/perfilsGestor/pagDetall.do" />';
    var pagGuardar = '<c:url value="/perfilsGestor/guardar.do" />';
    var pagEsborrar = '<c:url value="/perfilsGestor/esborrarPerfil.do" />';
    var pagDetallProcediment = '<c:url value="/seccions/seccio.do" />';
    var pagLlistatSeccions = '<c:url value="/seccions/llistat.do" />';
    var pagTraduirFetsVitals = '<c:url value="/fetsVitals/traduir.do" />';
    var modulos = '<c:url value="/perfilsGestor/modulos.do" />';
    var pagSeccions = '<c:url value="/perfilsGestor/arbreSeccions.do" />';
    var pagArbreSeccions = '<c:url value="/perfilsGestor/arbreSeccions.do" />';
    var pagGuardaSeccions = '<c:url value="/perfilsGestor/guardaSeccions.do" />';
    
    //texts
    var txt_per = "<spring:message code='txt.per'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.usuari_esborrat_correcte'/>";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtSi = "<spring:message code='txt.si'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtLlistaItem = "<spring:message code='txt.perfil'/>";
    var txtLlistaItems = "<spring:message code='txt.perfils'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtMostremAl = " <spring:message code='txt.a_la'/> ";
    var txtHiHa = "<spring:message code='txt.hi_ha'/>";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenats = "<spring:message code='txt.ordenats'/>";
    var txtOrdenades = "<spring:message code='txt.ordenades'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtPer = "<spring:message code='txt.per'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtFamilia = "<spring:message code='txt.familia'/>";
    var txtFechaActualizacion = "<spring:message code='camp.dataActualitzacio'/>";
    var txtSeleccionada = "<spring:message code='txt.seleccionada'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtTe = "<spring:message code='txt.te'/>";
    var txtDescendents = "<spring:message code='txt.descendents'/>";

    // modul seccions
    var txtSeccio = "<spring:message code='perfil_gestor.formulari.seccio'/>"; 
    var txtLaSeccio = "<spring:message code='txt.la_seccio'/>";
    var txtSeccions = "<spring:message code='perfil_gestor.formulari.seccions'/>";
    var txtSeccionsFilles = "<spring:message code='perfil_gestor.formulari.seccions.filles'/>"; 
    var txtNoHiHaSeccions = txtNoHiHa + " " + txtSeccions;
    var txtNoHiHaSeccionsSeleccionades = txtNoHiHa + " " + txtSeccions + " " + txtSeleccionades.toLowerCase();
    var txtNoHiHaSeccionsSeleccionadesFilles = txtLaSeccio + " " + txtSeleccionada.toLowerCase() + " " +  txtNo.toLowerCase() +" " + txtTe + " " + txtDescendents;
    var txtAfegirSeccionsFilles = "<spring:message code='perfil_gestor.formulari.seccions.afegir_filles'/>";

    

 // modul ua arbre
    var txtArrel = "<spring:message code='txt.arrel'/>";
    var txtNodesFills = "<spring:message code='txt.nodes_fills.titol'/>";
    var txtCarregantArrel = "<spring:message code='txt.carregant_node_arrel'/> " + txtEspere;
    var txtCarregantNodes = txtCarregant + " <spring:message code='txt.nodes_fills_dot'/> " + txtEspere;
    
    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtOrdre = "<spring:message code='camp.ordre'/>";
    var txtPujar = "<spring:message code='txt.pujar'/>";
    var txtCodiEstandard = "<spring:message code='camp.codi.estandard'/>";
    

    //paginacio
    var txtTrobat = "<spring:message code='txt.sha_trobat'/>";
    var txtSeguents = "<spring:message code='txt.seguents'/>";
    var txtAnteriors = "<spring:message code='txt.anteriors'/>";
    var txtInici = "<spring:message code='txt.inici'/>";
    var txtFinal = "<spring:message code='txt.final'/>";
    var txtPagines = "<spring:message code='txt.pagines'/>";
    var txtCercant = "<spring:message code='txt.cercant'/>";
    var txtCercantAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
    var txtCercantSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
    var txtCercantElements = txtCercant + " <spring:message code='txt.elements'/>" + ". " + txtEspere;

    //detall
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_de_la'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "<spring:message code='txt.detall_de_la.titol'/> " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquesta'/> " + txtLlistaItem.toLowerCase() + "?";
    var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
    var txtMostra = "<spring:message code='txt.mostra'/>";
    var txtAmaga = "<spring:message code='txt.amaga'/>";
    var txtCaducat = "<spring:message code='txt.caducat'/>";
    var txtCaducitat = "<spring:message code='txt.caducitat'/>";
    var txtPublicacio = "<spring:message code='boto.publicacio'/>";

    //idioma
    var txtDesplega = "<spring:message code='txt.desplega'/>";
    var txtPlega = "<spring:message code='txt.plega'/>";

    var txtElimina = "<spring:message code='txt.elimina'/>";
    
    // modul procediments
    var txtProcediment = "<spring:message code='txt.procediment'/>";
    var txtProcediments = "<spring:message code='txt.procediments'/>";
    var txtNoHiHaProcediments = txtNoHiHa + " " + txtProcediments.toLowerCase();
    var txtSeleccionat = "<spring:message code='txt.seleccionat'/>";
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtNoHiHaProcedimentsSeleccionats = txtNoHiHa + " " + txtProcediments.toLowerCase() + " " + txtSeleccionats.toLowerCase();

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript">
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
    var txtAnyMal = "<spring:message code='txt.any_mal'/>";
    var txtMesMal = "<spring:message code='txt.mes_mal'/>";
    var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
    var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";

    // dades formularios
    var FormulariDades = [
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_codi_estandard",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 230,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": "<spring:message code='perfil_gestor.formulari.codi_estandard.obligatori'/>",
                    "tipus": "<spring:message code='perfil_gestor.formulari.codi_estandard.no_nomes_numeros'/>"
                }
        },
        {
        "modo": "individual",
	    "etiqueta": "id",
	    "etiquetaValor": "item_nom_" + '<c:out value="${idiomaVal}"/>',
	    "obligatori": "si",
	    "tipus": "alfanumeric",
	    "caracters":
	        {
	            "maxim": 230,
	            "mostrar": "no",
	            "abreviat": "no"
	        },
	    "error":
	        {
	            "obligatori": "<spring:message code='agrupacioFV.formulari.nom.obligatori'/>",
	            "tipus": "<spring:message code='agrupacioFV.formulari.nom.no_nomes_numeros'/>"
	        }
	}
    ];
</script>

<div id="escriptori_contingut"> <%-- style="display:none/block" --%>
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <%-- Mientras no haya metodos en el EJB para buscar con parametros, no habra buscador.
        <li class="opcio C">
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        --%>
        <li class="opcions nuevo">
            <a id="btnNuevaFicha" href="javascript:;" class="btn nou">
                <span><span><spring:message code='perfil.crea_nou_perfil_gestor'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='perfil.carregant_llistat_perfil'/></p>                           
            </div>                          
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nom" class="ordreCamp" />                            
        </div>   
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="" method="post">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>            
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">          
            <!-- modul -->
            <div class="modul">                 
                <fieldset>                              
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                         
                    <legend><spring:message code='txt.dades'/></legend>                          
                    <div class="modul_continguts mostrat">                              
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>
                            <ul class="idiomes">
                                <c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <li class="idioma">
                                        <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
                                    </li>
                                </c:forEach>

                                <c:if test="${traductorActivo}">
	                                <li class="traduix btnGenerico" id="botoTraduirPerfil">
	                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
	                                </li>
                                </c:if>
                            </ul>

                            <c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								

							<c:if test="${loop.first}">
							<div class="idiomes">		
							</c:if>	

                                <div class="idioma <c:out value="${lang}"/>">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard"><spring:message code='perfil.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_<c:out value="${lang}"/>"><spring:message code='perfil.formulari.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_<c:out value="${lang}"/>" name="item_nom_<c:out value="${lang}"/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_<c:out value="${lang}"/>"><spring:message code='perfil.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_<c:out value="${lang}"/>" name="item_descripcio_<c:out value="${lang}"/>" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
								
							<c:if test="${loop.last}">
							</div>					
							</c:if>
							
                            </c:forEach>								
                            
                        </div>
                        <!-- /fila -->
                    </div>                          
                </fieldset>                 
            </div>
            <!-- /modul -->
        </div>             
        <!-- /modulPrincipal -->   
		
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
                                    <a id="btnVolver" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                                </li>
                                <li class="btnGuardar par">
                                    <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                                </li>
                                <li class="e btnEliminar impar">
                                    <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
                                </li>
                            </ul>
                        </div>
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- /modul -->
            <div class="modul invisible" id="modul_seccions">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='perfil_gestor.formulari.seccions'/></legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulSeccions -->
                        <div class="modulSeccions">
                            <div class="seleccionats">
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='perfil_gestor.formulari.seccions.noInfo'/></p>
                                    <div class="listaOrdenable"></div> 
                                </div>
                            </div>
                            <p class="btnGenerico">
                                <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_seccions'/></span></span></a>
                            </p>
                        </div>
                        <!-- /modulSeccions -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
        </div>
        <!-- /modulLateral -->
    </form>
</div>
    

    
<!-- escriptori_seccions -->
<div id="escriptori_seccions">
    <ul id="opcions" class="opcions">
        <li class="opcio C actiu"><spring:message code='perfil_gestor.formulari.seccions.gestio'/></li>                                 
    </ul>
    
    <div id="resultats" class="escriptori_items_llistat">  
        <div class="escriptori_selector_seccions">
            <h3><spring:message code='fitxes.seccions'/></h3>
            <div class="escriptori_seccions_arbre"></div>
        </div>          
        <div class="resultats C actiu" style="display: block;">
            <div id="cercador"> 
				<div class="botonera">
					<div class="boton btnGenerico">
						<a id="btnInsertar" class="btn inserta" href="javascript:;">
							<span><span><spring:message code='boto.inserta'/></span></span>
						</a>							
					</div>
					<div class="boton btnGenerico">
						<a id="btnInsertarDesc" class="btn insertaDesc" href="javascript:;">
							<span><span><spring:message code='boto.insertaDesc'/></span></span>
						</a>							
					</div>
				</div>
            </div>           
        </div>
    </div>

    <!-- modulLateral -->
	<div class="escriptori_detall">
		<div class="modulLateral">
			<!-- modul -->
			<div class="modul publicacio">
				<fieldset>
					<a class="modul mostrat"><spring:message code='txt.amaga'/></a>
					<legend><spring:message code='txt.accions'/></legend>
					<div class="modul_continguts mostrat">
						<!-- botonera dalt -->
						<div class="botonera dalt">
						  <ul>
							  <li class="btnVolver impar">
								  <a id="btnVolver_seccions" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
							  </li>
							  <li class="btnGuardar par">
                              <a id="btnGuardar_seccions" href="javascript:;" class="btn guarda important lista-simple-secciones" 
                                  action="/rolsacback/perfilsGestor/guardarSeccions.do"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
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
    
    <div class="modulLateral escriptori_items_seleccionats">
        <div class="modul">
            <div class="interior">
                <div class="seleccionats">
                    <div class="seleccionat">
                        <p class="info"><spring:message code='perfil_gestor.formulari.seccions.noInfo'/></p>
                        <div class="listaOrdenable"></div>
                    </div>
                </div>                                  
            </div>
        </div>
    </div>
    <!-- seleccionats -->
</div>
<!-- /escriptori_seccions -->
