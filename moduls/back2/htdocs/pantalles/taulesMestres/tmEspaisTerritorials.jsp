<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />
<link href='<c:url value="/css/tm_espai_territorial.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/comuns.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_espai_territorial.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_espais.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/espaisTerritorials/llistat.do" />';
    var pagDetall = '<c:url value="/espaisTerritorials/pagDetall.do" />';
    var pagGuardar = '<c:url value="/espaisTerritorials/guardar.do" />';
    var pagEsborrar = '<c:url value="/espaisTerritorials/esborrarEspaiTerritorial.do" />';
    var pagBreadcrumb = '<c:url value="/espaisTerritorials/espaiTerritorialBreadcrumb.do" />';

    //texts
    var txtTria = "<spring:message code='camp.tria.opcio'/>";
    var txt_per = "<spring:message code='txt.per'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.espai_esborrat_correcte'/>";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtSi = "<spring:message code='txt.si'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtLlistaItem = "<spring:message code='txt.espai'/>";
    var txtLlistaItems = "<spring:message code='txt.espais'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtMostremAl = " <spring:message code='txt.a_la'/> ";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenats = "<spring:message code='txt.ordenats'/>";
    var txtOrdenades = "<spring:message code='txt.ordenades'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtPer = "<spring:message code='txt.per'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();

    var txtEspai = "<spring:message code='txt.espai'/>";
    var txtEspais = "<spring:message code='txt.espais'/>";
    var txtNoHiHaEspais = txtNoHiHa + " " + txtEspais;
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaEspaisSeleccionats= txtNoHiHaEspais + " " + txtSeleccionades.toLowerCase();
    var txtEspaiArrel = "<spring:message code='espai.arrel'/>";
    var txtArrel = "<spring:message code='txt.arrel'/>";
    
    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtDescripcio = "<spring:message code='camp.descripcio'/>";
    var txtCodiEstandar = "<spring:message code='camp.codiEstandard'/>";

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
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_de_l'/>"+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "<spring:message code='txt.detall_de_la.titol'/> " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
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

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript">
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";

    // dades formularios
    var FormulariDades = [
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_nom_ca",
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
                    "obligatori": "<spring:message code='espai.formulari.nom.obligatori'/>",
                    "tipus": "<spring:message code='espai.formulari.nom.no_nomes_numeros'/>"
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
                <span><span><spring:message code='espai.crea_nou_espai'/></span></span>
            </a>
        </li>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='espai.carregant_llistat_espais'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />
        </div>
        <%-- Mientras no haya metodos en el EJB para buscar con parametros, no habra buscador.
        <div class="resultats C">
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2>Cercador</h2>                 
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_nom"><spring:message code='camp.nom'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_nom" name="cerca_nom" type="text" maxlength="250" class="nom" />
                                </div>
                            </div>
                        </div>
                        <div class="botonera">
                            <div class="boton btnGenerico">
                              <a id="btnLimpiarForm" href="javascript:void(0)" class="btn borrar"><span><span><spring:message code='boto.borrar'/></span></span></a>
                            </div>
                            <div class="boton btnGenerico">
                             <a id="btnBuscarForm" href="javascript:;" class="btn consulta"><span><span><spring:message code='boto.cercar'/></span></span></a>
                            </div>
                        </div>
                </div>
            </div>
            <div class="dades"></div>               
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nom" class="ordreCamp" />
        </div>
        --%>      
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="false" method="post">
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
                                <li class="idioma"><a href="javascript:;" class="ca"><spring:message code='txt.idioma.ca'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="es"><spring:message code='txt.idioma.es'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="en"><spring:message code='txt.idioma.en'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="de"><spring:message code='txt.idioma.de'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="fr"><spring:message code='txt.idioma.fr'/></a></li>
                                <li class="traduix btnGenerico">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
                            <div class="idiomes">
                                <!-- ca -->
                                <div class="idioma ca">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_ca"><spring:message code='camp.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_ca" name="item_nom_ca" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                                                        
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_pare"><spring:message code='camp.codiPare'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_pare" name="item_pare" type="text" readonly="readonly" />
                                                <input id="item_codi_pare" name="item_codi_pare" type="hidden" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador" class="fila">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreET('<c:url value="/pantalles/popArbreET.do"/>', 'popET', 'item_id', 'item_codi_pare', 'item_pare');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarETPare'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_coordenades"><spring:message code='camp.coordenades'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_coordenades" name="item_coordenades" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>                    
                                    
                                </div>
                                <!-- /ca -->
                                <!-- es -->
                                <div class="idioma es">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_es"><spring:message code='camp.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_es" name="item_nom_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_pare_es"><spring:message code='camp.codiPare'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_pare_es" name="item_pare" type="text" readonly="readonly" />                                                
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador" class="fila">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreET('<c:url value="/pantalles/popArbreET.do"/>', 'popET', 'item_id', 'item_codi_pare', 'item_pare_es');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarETPare'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_coordenades_es"><spring:message code='camp.coordenades'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_coordenades_es" name="item_coordenades_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>  
                                    
                                </div>
                                <!-- /es -->
                                <!-- en -->
                                <div class="idioma en">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_en"><spring:message code='camp.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_en" name="item_nom_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_pare_en"><spring:message code='camp.codiPare'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_pare_en" name="item_pare_en" type="text" readonly="readonly" />                                                
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador" class="fila">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreET('<c:url value="/pantalles/popArbreET.do"/>', 'popET', 'item_id', 'item_codi_pare', 'item_pare_en');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarETPare'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_coordenades_en"><spring:message code='camp.coordenades'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_coordenades_en" name="item_coordenades_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <!-- /en -->
                                <!-- de -->
                                <div class="idioma de">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_de"><spring:message code='camp.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_de" name="item_nom_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_pare_de"><spring:message code='camp.codiPare'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_pare_de" name="item_pare_de" type="text" readonly="readonly" />                                                
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador" class="fila">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreET('<c:url value="/pantalles/popArbreET.do"/>', 'popET', 'item_id', 'item_codi_pare', 'item_pare_de');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarETPare'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_coordenades_de"><spring:message code='camp.coordenades'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_coordenades_de" name="item_coordenades_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <!-- /de -->
                                <!-- fr -->
                                <div class="idioma fr">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_fr"><spring:message code='camp.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_fr" name="item_nom_fr" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_pare_fr"><spring:message code='camp.codiPare'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_pare_fr" name="item_pare_fr" type="text" readonly="readonly" />                                                
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador" class="fila">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreET('<c:url value="/pantalles/popArbreET.do"/>', 'popET', 'item_id', 'item_codi_pare', 'item_pare_fr');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarETPare'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_coordenades_fr"><spring:message code='camp.coordenades'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_coordenades_fr" name="item_coordenades" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <!-- /fr -->
                            </div>
                        </div>
                        <!-- /fila -->
                    </div>                          
                </fieldset>                 
            </div>
            <!-- /modul -->
            
        <!-- modul -->
        <%--<div class="modul">
            <fieldset>
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='txt.dades'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t99p">
                            <div class="etiqueta">
                                <label for="item_pare"><spring:message code='camp.codiPare'/></label>
                            </div>
							<div class="control">
                                <input id="item_pare" name="item_pare" type="text" readonly="readonly" />
                                <input id="item_codi_pare" name="item_codi_pare" type="hidden" />
							</div>
						</div>
					</div>
					<!-- Botonera -->
					<div id="cercador" class="fila">
						<div class="botonera" style="margin-top: 0px; float:left;">
							<div class="boton btnGenerico" style="margin-left: 0px;">
								<a href="javascript:carregarArbreET('<c:url value="/pantalles/popArbreET.do"/>', 'popET', 'item_id', 'item_codi_pare', 'item_pare');" class="btn consulta">
									<span><span><spring:message code='boto.canviarETPare'/></span></span>
								</a>
							</div>
						</div>
					</div>
					<!-- /Botonera -->
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t99p">
                            <div class="etiqueta">
                                <label for="item_coordenades"><spring:message code='camp.coordenades'/></label>
                            </div>
                            <div class="control">
                                <input id="item_coordenades" name="item_coordenades" type="text" class="nou" />
                            </div>
                        </div>
                    </div>                    
                </div>
            </fieldset>
        </div>--%>
        <!-- /modul -->
        
        <!-- modul -->
        <div class="modul">
            <fieldset>
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend>Multimedia</legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p campoImagen">
                            <div class="thumbnail"></div>
                            <div class="etiqueta"><label for="item_mapa"><spring:message code='camp.mapa'/></label></div>
                            <div class="control archivo">
                                <div id="grup_item_mapa" class="grup_arxiu_actual">
                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                    <a href="#" target="_blank"></a>
                                    <input type="checkbox" name="item_mapa_delete" id="item_mapa_delete" value="1"/>
                                    <label for="item_mapa_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                </div>
                            </div>
                        </div>
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_mapa"><spring:message code='camp.arxiu_nou'/></label></div>
                            <div class="control">
                                <input id="item_mapa" name="item_mapa" type="file" class="nou" />
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p campoImagen">
                            <div class="thumbnail"></div>
                            <div class="etiqueta"><label for="item_logo"><spring:message code='camp.logo'/></label></div>
                            <div class="control archivo">
                                <div id="grup_item_logo" class="grup_arxiu_actual">
                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                    <a href="#" target="_blank"></a>
                                    <input type="checkbox" name="item_logo_delete" id="item_logo_delete" value="1"/>
                                    <label for="item_logo_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                </div>
                            </div>
                        </div>
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_logo"><spring:message code='camp.arxiu_nou'/></label></div>
                            <div class="control">
                                <input id="item_logo" name="item_logo" type="file" class="nou" />
                            </div>
                        </div>
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
            <!-- modul -->
            <div class="modul" id="modul_espais_relacionats">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='espai.espais_relacionats'/></legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulEspais -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulEspais">
                            <div class="seleccionats">
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaEspaisRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixEspai'/></span></span></a>
                                </div>
                            </div>
                        </div>
                        <!-- /modulEspais -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
        </div>
        <!-- /modulLateral -->
    </form>
</div>