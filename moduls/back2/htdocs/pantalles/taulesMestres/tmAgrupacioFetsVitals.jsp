<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_agrupacio_FV.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_agrupacio_FV.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_fet_vital.js'/>"></script>
<script type="text/javascript">
    var pagLlistat = '<c:url value="/agrupacioFetsVitals/llistat.do" />';
    var pagDetall = '<c:url value="/agrupacioFetsVitals/pagDetall.do" />';
    var pagGuardar = '<c:url value="/agrupacioFetsVitals/guardar.do" />';
    var pagEsborrar = '<c:url value="/agrupacioFetsVitals/esborrarAgrupacioFetsVitals.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.agrupacioFV'/>";
    var txtLlistaItems = "<spring:message code='txt.agrupacionsFV'/>";
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

    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtDescripcio = "<spring:message code='camp.descripcio'/>";

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

    var txtFetVital = "<spring:message code='fetVital.fet_vital'/>";
    var txtFetsVitals = "<spring:message code='fetVital.fets_vitals'/>";
    var txtNoHiHaFetVital = txtNoHiHa + " " + txtFetVital.toLowerCase();
    var txtNoHiHaFetsVitals = txtNoHiHa + " " + txtFetsVitals.toLowerCase();
    var txtSeleccionada =  "<spring:message code='txt.seleccionat'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionats'/>";
    var txtNoHiHaFetVitalSeleccionada = txtNoHiHa + " " + txtFetVital.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaFetsVitalsSeleccionades = txtNoHiHa + " " + txtFetsVitals.toLowerCase() + " " + txtSeleccionades.toLowerCase();

    var errorFetVital= "<spring:message code='fetVital.escull_fet_vital'/>";
  
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
		            "obligatori": "<spring:message code='agrupacioFV.formulari.nom.obligatori'/>",
		            "tipus": "<spring:message code='agrupacioFV.formulari.nom.no_nomes_numeros'/>"
		        }
		},
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
                    "obligatori": "<spring:message code='agrupacioFV.formulari.codi_estandard.obligatori'/>",
                    "tipus": "<spring:message code='agrupacioFV.formulari.codi_estandard.no_nomes_numeros'/>"
                }
        },{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_public_objectiu",
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
                    "obligatori": "<spring:message code='agrupacioFV.formulari.public_objectiu.obligatori'/>",
                    "tipus": "<spring:message code='agrupacioFV.formulari.public_objectiu.no_nomes_numeros'/>"
                }
        }
    ];
</script>

<div id="escriptori_contingut">
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <%-- 
        <li class="opcio C">
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        --%>
        <li class="opcions nuevo">
            <a id="btnNuevaFicha" href="javascript:;" class="btn nou">
                <span><span><spring:message code='agrupacioFV.crea_nova_agrupacioFV'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='agrupacioFV.carregant_llistat_agrupacioFV'/></p>                           
            </div>                          
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />                            
        </div>
        <%--
        <div class="resultats C">
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2><spring:message code='tab.cercador'/></h2>                 
                        <div class="fila">
                            <div class="element t50">
                                <div class="etiqueta"><label for="cerca_texte"><spring:message code='agrupacioFV.cerca.texte'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_texte" name="cerca_texte" type="text" maxlength="250" />
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
    <form id="formGuardar" action="false">
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
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard"><spring:message code='agrupacioFV.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">                              
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_ca"><spring:message code='agrupacioFV.formulari.nom'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_nom_ca" name="item_nom_ca" type="text" class="nou" />
                                            </div>                                      
                                        </div>                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_ca"><spring:message code='agrupacioFV.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_ca" name="item_descripcio_ca" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_ca"><spring:message code='agrupacioFV.formulari.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_ca" name="item_paraules_clau_ca" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /ca -->
                                <!-- es -->
                                <div class="idioma es">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard_es"><spring:message code='agrupacioFV.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_es" name="item_codi_estandard_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">                              
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_es"><spring:message code='agrupacioFV.formulari.nom'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_nom_es" name="item_nom_es" type="text" class="nou" />
                                            </div>                                      
                                        </div>                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_es"><spring:message code='agrupacioFV.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_es" name="item_descripcio_es" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_es"><spring:message code='agrupacioFV.formulari.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_es" name="item_paraules_clau_es" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /es -->
                                <!-- en -->
                                <div class="idioma en">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard_en"><spring:message code='agrupacioFV.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_en" name="item_codi_estandard_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">                              
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_en"><spring:message code='agrupacioFV.formulari.nom'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_nom_en" name="item_nom_en" type="text" class="nou" />
                                            </div>                                      
                                        </div>                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_en"><spring:message code='agrupacioFV.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_en" name="item_descripcio_en" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_en"><spring:message code='agrupacioFV.formulari.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_en" name="item_paraules_clau_en" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /en -->
                                <!-- de -->
                                <div class="idioma de">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard_de"><spring:message code='agrupacioFV.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_de" name="item_codi_estandard_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">                              
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_de"><spring:message code='agrupacioFV.formulari.nom'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_nom_de" name="item_nom_de" type="text" class="nou" />
                                            </div>                                      
                                        </div>                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_de"><spring:message code='agrupacioFV.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_de" name="item_descripcio_de" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_de"><spring:message code='agrupacioFV.formulari.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_de" name="item_paraules_clau_de" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /de -->
                                <!-- fr -->
                                <div class="idioma fr">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard_fr"><spring:message code='agrupacioFV.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_fr" name="item_codi_estandard_fr" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">                              
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_fr"><spring:message code='agrupacioFV.formulari.nom'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_nom_fr" name="item_nom_fr" type="text" class="nou" />
                                            </div>                                      
                                        </div>                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_fr"><spring:message code='agrupacioFV.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_fr" name="item_descripcio_fr" cols="50" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_fr"><spring:message code='agrupacioFV.formulari.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_fr" name="item_paraules_clau_fr" cols="50" rows="3" class="nou"></textarea>
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
              <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='fitxes.formulari.multimedia'/></legend>
                    <div class="modul_continguts mostrat">                        
                        
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_foto"><spring:message code='agrupacioFV.formulari.foto'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_foto" class="file grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_foto_delete" id="item_foto_delete" value="1"/>
                                        <label for="item_foto_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>                                
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_foto"><spring:message code='agrupacioFV.formulari.foto'/></label></div>
                                <div class="control">
                                    <input id="item_foto" name="item_foto" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_icona"><spring:message code='agrupacioFV.formulari.icona'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_icona" class="file grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_icona_delete" id="item_icona_delete" value="1"/>
                                        <label for="item_icona_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                                                        
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_icona"><spring:message code='agrupacioFV.formulari.icona'/></label></div>
                                <div class="control">
                                    <input id="item_icona" name="item_icona" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_icona_gran"><spring:message code='agrupacioFV.formulari.foto_gran'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_icona_gran" class="file grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_icona_gran_delete" id="item_icona_gran_delete" value="1"/>
                                        <label for="item_icona_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>                                
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_icona_gran"><spring:message code='agrupacioFV.formulari.foto_gran'/></label></div>
                                <div class="control">
                                    <input id="item_icona_gran" name="item_icona_gran" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                        </div>
                        <!-- /fila -->
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- PopFetsVitals -->
            <div id="popFetVital" class="modul">
                <div class="fila">
                    <div class="element ">                                      
	                    <div class="etiqueta"><label for="item_fetVital_relacionat"><spring:message code='agrupacioFV.formulari.fet_vital'/></label></div>
	                    <div class="control select">
			                 <select id="item_fetVital_relacionat" name="item_fetVital_relacionat">
			                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
			                    <c:forEach items="${llistaFets}" var="fetVital">
			                        <option value='<c:out value="${fetVital.id}" />'><c:out value="${fetVital.nom}" /></option>
			                    </c:forEach>
			                </select>
	                    </div>
	                </div>
                    <div class="botonera">
	                    <div class="etiqueta">
                            <label for="item_">&nbsp;</label>
                        </div>
                        <span class="btnGenerico fetVitals">
                            <a href="javascript:;" class="btn afegeixFetVital" id="addFetVital" >
                             <span><span><spring:message code='boto.afegeixFetsVitals'/></span></span>
                            </a>
                        </span>
                        <span class="btnGenerico fetVitals ">
                            <a href="javascript:;" class="btn tanca" id="tancaFetVital" >
                             <span><span><spring:message code='boto.tancar'/></span></span>
                            </a>
                        </span>
                    </div>
                </div>
			</div>
			<!-- PopFetsVitals -->
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
            <div class="modul" id="modul_fetsVitals">
                <input type="hidden" id="llistaFetsVitals" name="fetsVitals" value=""/>                     
                <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='fetVital.fets_vitals_relacionats'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulFetVital -->
                        <div class="modulFetVital">
                            <div class="seleccionats">
                                <%-- dsanchez: un solo idioma --%>
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaFetVitalRelacionat'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="boton btnGenerico" style="margin-left: 0px;">
                                    <a href="javascript:;" class="btn consulta" id="afegeixFetVital">
                                        <span><span><spring:message code='boto.afegeixFetVital'/></span></span>
                                    </a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulFetVital -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->  
            
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='agrupacioFV.formulari.public_objectiu'/></legend>
                    <div class="modul_continguts mostrat">
                                                
                        <select id="item_public_objectiu" name="item_public_objectiu">
                            <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                            <c:forEach items="${llistaPublicObjectiu}" var="publicObjectiu">
                                <option value='<c:out value="${publicObjectiu.id}" />'><c:out value="${publicObjectiu.nom}" /></option>
                            </c:forEach>
                        </select>
                        
                    </div>
                </fieldset>
            </div>
        </div>
        <!-- /modulLateral -->
    </form>
</div>