<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />
<link href='<c:url value="/css/tm_materia.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_materia.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_icones.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/materies/llistat.do" />';
    var pagDetall = '<c:url value="/materies/pagDetall.do" />';
    var pagGuardar = '<c:url value="/materies/guardar.do" />';
    var pagEsborrar = '<c:url value="/materies/esborrarMateria.do" />';
    var pagCarregarIcona = '<c:url value="/iconesMateria/carregarIcona.do" />';
    var pagGuardarIcona = '<c:url value="/iconesMateria/guardarIcona.do" />';

    //texts
    var txtTria = "<spring:message code='camp.tria.opcio'/>";
    var txt_per = "<spring:message code='txt.per'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.materia_esborrada_correcte'/>";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtSi = "<spring:message code='txt.si'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtLlistaItem = "<spring:message code='txt.materia'/>";
    var txtLlistaItems = "<spring:message code='txt.materies'/>";
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

    var txtIcona = "<spring:message code='txt.icona'/>";
    var txtIcones = "<spring:message code='txt.icones'/>";
    var txtNoHiHaIcones = txtNoHiHa + " " + txtIcones;
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaIconesSeleccionades= txtNoHiHaIcones + " " + txtSeleccionades.toLowerCase();
    
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
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_de_la'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
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
        /*{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_codi_hita",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 100,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": "<spring:message code='materia.formulari.codi_hita.obligatori'/>",
                    "tipus": "<spring:message code='materia.formulari.codi_hita.no_nomes_numeros'/>"
                }
        },*/
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
                    "obligatori": "<spring:message code='materia.formulari.nom.obligatori'/>",
                    "tipus": "<spring:message code='materia.formulari.nom.no_nomes_numeros'/>"
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
                <span><span><spring:message code='materia.crea_nova_materia'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='materia.carregant_llistat_materia'/></p>                           
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
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- fila -->
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
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_ca"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_ca" name="item_descripcio_ca" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_ca"><spring:message code='camp.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_ca" name="item_paraules_clau_ca" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_ca"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucion_ca" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucion_ca_delete" id="item_distribucion_ca_delete" value="1"/>
                                                    <label for="item_distribucion_ca_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_ca"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucion_ca" name="item_distribucion_ca" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_ca"><spring:message code='camp.normativa'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_normativa_ca" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_normativa_ca_delete" id="item_normativa_ca_delete" value="1"/>
                                                    <label for="item_normativa_ca_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_ca"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_normativa_ca" name="item_normativa_ca" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_ca"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_contenido_ca" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contenido_ca_delete" id="item_contenido_ca_delete" value="1"/>
                                                    <label for="item_contenido_ca_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_ca"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contenido_ca" name="item_contenido_ca" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                </div>
                                <!-- /ca -->
                                <!-- es -->
                                <div class="idioma es">
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard_es"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_es" name="item_codi_estandard_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- fila -->
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
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_es"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_es" name="item_descripcio_es" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_es"><spring:message code='camp.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_es" name="item_paraules_clau_es" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_es"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucion_es" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucion_es_delete" id="item_distribucion_es_delete" value="1"/>
                                                    <label for="item_distribucion_es_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_es"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucion_es" name="item_distribucion_es" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_es"><spring:message code='camp.normativa'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_normativa_es" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_normativa_es_delete" id="item_normativa_es_delete" value="1"/>
                                                    <label for="item_normativa_es_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_es"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_normativa_es" name="item_normativa_es" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_es"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_contenido_es" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contenido_es_delete" id="item_contenido_es_delete" value="1"/>
                                                    <label for="item_contenido_es_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_es"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contenido_es" name="item_contenido_es" type="file" class="nou" />
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
                                                <label for="item_codi_estandard_en"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_en" name="item_codi_estandard_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>                                    
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
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_en"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_en" name="item_descripcio_en" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_en"><spring:message code='camp.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_en" name="item_paraules_clau_en" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_en"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucion_en" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucion_en_delete" id="item_distribucion_en_delete" value="1"/>
                                                    <label for="item_distribucion_en_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_en"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucion_en" name="item_distribucion_en" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_en"><spring:message code='camp.normativa'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_normativa_en" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_normativa_en_delete" id="item_normativa_en_delete" value="1"/>
                                                    <label for="item_normativa_en_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_en"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_normativa_en" name="item_normativa_en" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_en"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_contenido_en" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contenido_en_delete" id="item_contenido_en_delete" value="1"/>
                                                    <label for="item_contenido_en_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_en"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contenido_en" name="item_contenido_en" type="file" class="nou" />
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
                                                <label for="item_codi_estandard_de"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_de" name="item_codi_estandard_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>                                    
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
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_de"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_de" name="item_descripcio_de" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_de"><spring:message code='camp.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_de" name="item_paraules_clau_de" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_de"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucion_de" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucion_de_delete" id="item_distribucion_de_delete" value="1"/>
                                                    <label for="item_distribucion_de_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_de"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucion_de" name="item_distribucion_de" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_de"><spring:message code='camp.normativa'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_normativa_de" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_normativa_de_delete" id="item_normativa_de_delete" value="1"/>
                                                    <label for="item_normativa_de_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_de"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_normativa_de" name="item_normativa_de" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_de"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_contenido_de" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contenido_de_delete" id="item_contenido_de_delete" value="1"/>
                                                    <label for="item_contenido_de_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_de"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contenido_de" name="item_contenido_de" type="file" class="nou" />
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
                                                <label for="item_codi_estandard_fr"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard_fr" name="item_codi_estandard_fr" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>                                    
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
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_fr"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_fr" name="item_descripcio_fr" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_fr"><spring:message code='camp.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_fr" name="item_paraules_clau_fr" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_fr"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucion_fr" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucion_fr_delete" id="item_distribucion_fr_delete" value="1"/>
                                                    <label for="item_distribucion_fr_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_fr"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucion_fr" name="item_distribucion_fr" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_fr"><spring:message code='camp.normativa'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_normativa_fr" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_normativa_fr_delete" id="item_normativa_fr_delete" value="1"/>
                                                    <label for="item_normativa_fr_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_fr"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_normativa_fr" name="item_normativa_fr" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_fr"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_contenido_fr" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contenido_fr_delete" id="item_contenido_fr_delete" value="1"/>
                                                    <label for="item_contenido_fr_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_fr"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contenido_fr" name="item_contenido_fr" type="file" class="nou" />
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
                    <legend><spring:message code='txt.dades'/></legend>
                    <div class="modul_continguts mostrat">
                        <%--
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_codi_hita"><spring:message code='camp.codiHita'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_codi_hita" name="item_codi_hita" type="text" class="nou" />
                                </div>
                            </div>
                        </div>                    
                        --%>                        
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_ua_principal"><spring:message code='camp.uaPrincipal'/></label>
                                </div>
                                <div class="control select">
                                    <select id="item_ua_principal" name="item_ua_principal" class="nou">
                                        <option value="0" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_destacada"><spring:message code='camp.destacada'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_destacada" name="item_destacada" type="checkbox" class="nou" />
                                </div>
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
                    <legend>MULTIMÈDIA</legend>
                    <div class="modul_continguts mostrat">                        
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_foto"><spring:message code='camp.foto'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_foto" class="grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_foto_delete" id="item_foto_delete" value="1"/>
                                        <label for="item_foto_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_foto"><spring:message code='camp.arxiu_nou'/></label></div>
                                <div class="control">                                           
                                    <input id="item_foto" name="item_foto" type="file" class="nou" />
                                </div>
                            </div> 
                        </div>                        
                                                
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_icona"><spring:message code='camp.icona'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_icona" class="grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_icona_delete" id="item_icona_delete" value="1"/>
                                        <label for="item_icona_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_icona"><spring:message code='camp.arxiu_nou'/></label></div>
                                <div class="control">                                           
                                    <input id="item_icona" name="item_icona" type="file" class="nou" />
                                </div>
                            </div> 
                        </div>
                        
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_icona_gran"><spring:message code='camp.icona_gran'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_icona_gran" class="grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_icona_gran_delete" id="item_icona_gran_delete" value="1"/>
                                        <label for="item_icona_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_icona_gran"><spring:message code='camp.arxiu_nou'/></label></div>
                                <div class="control">                                           
                                    <input id="item_icona_gran" name="item_icona_gran" type="file" class="nou" />
                                </div>
                            </div> 
                        </div>
                                                
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
            <div class="modul" id="modul_icones">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='materia.icones_perfil'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulIcones -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulIcones">                            
                            <div class="seleccionats">
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaIcones'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixIcona'/></span></span></a>
                                </div>
                            </div>
                        </div>
                        <!-- /modulIcones -->                                 
                    </div>    
                </fieldset>
            </div>
            <!-- /modul -->
        </div>
        <!-- /modulLateral -->
    </form>
</div>

<!-- escriptori_icones -->
<div id="escriptori_icones" class="escriptori_detall">
    <script type="text/javascript">
        // dades formularis
        var FormulariDadesIcona = [                      
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "icona_perfil",
                "obligatori": "si",
                "tipus": "numeric",
                "error":{
                    "obligatori": "<spring:message code='icona.error.perfil_obligatori'/>"
                }
            }
        ];
    </script>
    <form id="formGuardarIcona" action="" method="POST">
        <input type="hidden" name="iconaId" id="iconaId" />
        <input type="hidden" name="materiaId" id="materiaId" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <!--div id="modulPrincipal" class="grupoModulosFormulario"-->                    
        <div id="modulIcones" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.icones'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="icona_perfil"><spring:message code='camp.perfil'/></label>
                                </div>
                                <div class="control select">
                                    <select id="icona_perfil" name="icona_perfil" class="nou">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${perfils}" var="perfil">
                                            <option value="<c:out value="${perfil.id}"/>"><c:out value="${perfil.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="icona_arxiu"><spring:message code='camp.arxiu'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_arxiu_actual_icona" class="grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <!-- input type="checkbox" name="icona_arxiu_delete" id="icona_arxiu_delete" value="1"/>
                                        <label for="icona_arxiu_delete" class="eliminar"><spring:message code='boto.elimina'/></label -->
                                    </div>
                                </div>
                            </div>    
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="icona_arxiu"><spring:message code='camp.arxiu_nou'/></label></div>
                                <div class="control">                                           
                                    <input id="icona_arxiu" name="icona_arxiu" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                        </div>
                        <!-- /fila -->
                    </div>
                </fieldset>
            </div>
        </div>
        <!-- /modulPrincipal -->
        <!-- modulLateral -->
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
                                  <a id="btnVolver_icones" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_icones" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                          </ul>
                        </div>
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
        </div>
        <!-- /modulLateral -->
    </form>
</div>
<!-- escriptori_icones -->