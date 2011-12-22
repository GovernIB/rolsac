<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_public_objectiu.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/tm_public_objectiu.js'/>"></script>
<script type="text/javascript">
    var pagLlistat = '<c:url value="/publicObjectiu/llistat.do" />';
    var pagDetall = '<c:url value="/publicObjectiu/pagDetall.do" />';
    var pagGuardar = '<c:url value="/publicObjectiu/guardar.do" />';
    var pagEsborrar = '<c:url value="/publicObjectiu/esborrarPublicObjectiu.do" />';
    var pagPujar  = '<c:url value="/publicObjectiu/pujarPublicObjectiu.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.publicObjectiu'/>";
    var txtLlistaItems = "<spring:message code='txt.publicsObjectiu'/>";
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
    var txtOrdre = "<spring:message code='camp.ordre'/>";
    var txtPujar = "<spring:message code='txt.pujar'/>";
    

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
                    "obligatori": "<spring:message code='publicObjectiu.formulari.codi_estandard.obligatori'/>",
                    "tipus": "<spring:message code='publicObjectiu.formulari.codi_estandard.no_nomes_numeros'/>"
                }
        },{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_ca",
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
                    "obligatori": "<spring:message code='publicObjectiu.formulari.titol.obligatori'/>",
                    "tipus": "<spring:message code='publicObjectiu.formulari.titol.no_nomes_numeros'/>"
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
                <span><span><spring:message code='publicObjectiu.crea_nou_public_objectiu'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='edifici.carregant_llistat_edifici'/></p>                           
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
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_ca"><spring:message code='publicObjectiu.formulari.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_ca" name="item_titol_ca" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_ca"><spring:message code='publicObjectiu.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_ca" name="item_descripcio_ca" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_paraules_clau_ca"><spring:message code='publicObjectiu.formulari.paraules_clau'/></label></div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_ca" name="item_paraules_clau_ca" cols="70" rows="3" class="nou"></textarea>
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
                                                <label for="item_titol_es"><spring:message code='publicObjectiu.formulari.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_es" name="item_titol_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_es"><spring:message code='publicObjectiu.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_es" name="item_descripcio_es" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_paraules_clau_es"><spring:message code='publicObjectiu.formulari.paraules_clau'/></label></div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_es" name="item_paraules_clau_es" cols="70" rows="3" class="nou"></textarea>
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
                                                <label for="item_titol_en"><spring:message code='publicObjectiu.formulari.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_en" name="item_titol_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_en"><spring:message code='publicObjectiu.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_en" name="item_descripcio_en" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_paraules_clau_en"><spring:message code='publicObjectiu.formulari.paraules_clau'/></label></div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_en" name="item_paraules_clau_en" cols="70" rows="3" class="nou"></textarea>
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
                                                <label for="item_titol_de"><spring:message code='publicObjectiu.formulari.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_de" name="item_titol_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_de"><spring:message code='publicObjectiu.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_de" name="item_descripcio_de" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_paraules_clau_de"><spring:message code='publicObjectiu.formulari.paraules_clau'/></label></div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_de" name="item_paraules_clau_de" cols="70" rows="3" class="nou"></textarea>
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
                                                <label for="item_titol_fr"><spring:message code='publicObjectiu.formulari.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_fr" name="item_titol_fr" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_fr"><spring:message code='publicObjectiu.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_fr" name="item_descripcio_fr" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_paraules_clau_fr"><spring:message code='publicObjectiu.formulari.paraules_clau'/></label></div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_fr" name="item_paraules_clau_fr" cols="70" rows="3" class="nou"></textarea>
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
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_codi_estandard"><spring:message code='publicObjectiu.formulari.codi_estandard'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
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
        </div>
        <!-- /modulLateral -->
    </form>
</div>