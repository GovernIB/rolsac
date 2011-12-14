<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_edifici.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/tm_edifici.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_unitats_adminstratives.js'/>"></script>
<script type="text/javascript">
    var pagLlistat = '<c:url value="/edifici/llistat.do" />';
    var pagDetall = '<c:url value="/edifici/pagDetall.do" />';
    var pagGuardar = '<c:url value="/edifici/guardar.do" />';
    var pagEsborrar = '<c:url value="/edifici/esborrarEdifici.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.edifici'/>";
    var txtLlistaItems = "<spring:message code='txt.edificis'/>";
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

    var txtUnitatAdministrativa = "<spring:message code='unitatAdministrativa.ua'/>";
	var txtUnitatsAdministratives = "<spring:message code='unitatAdministrativa.uas'/>";
	var txtNoHiHaUA = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase();
    var txtNoHiHaUAs = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase();
    var txtSeleccionada =  "<spring:message code='txt.seleccionada'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaUASeleccionada = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaUAsSeleccionades = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase() + " " + txtSeleccionades.toLowerCase();
    
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
            "etiquetaValor": "item_direccio",
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
                    "obligatori": "<spring:message code='edifici.formulari.direccio.obligatori'/>",
                    "tipus": "<spring:message code='edifici.formulari.direccio.no_nomes_numeros'/>"
                }
        },{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_descripcio_ca",
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
                    "obligatori": "<spring:message code='edifici.formulari.descripcio.obligatori'/>",
                    "tipus": "<spring:message code='edifici.formulari.descripcio.no_nomes_numeros'/>"
                }
        }
    ];
</script>

<div id="escriptori_contingut"> <%-- style="display:none/block" --%>
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcio C">
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        <li class="opcions nuevo">
            <a id="btnNuevaFicha" href="javascript:;" class="btn nou">
                <span><span><spring:message code='edifici.crea_nou_edifici'/></span></span>
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
        <div class="resultats C">
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2><spring:message code='tab.cercador'/></h2>                 
                        <div class="fila">
                            <div class="element t50">
                                <div class="etiqueta"><label for="cerca_descripcio"><spring:message code='edifici.formulari.descripcio'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_descripcio" name="cerca_descripcio" type="text" maxlength="250" />
                                </div>
                            </div>
                            <div class="element t50">
                                <div class="etiqueta"><label for="cerca_direccio"><spring:message code='edifici.formulari.direccio'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_direccio" name="cerca_direccio" type="text" maxlength="250" />
                                </div>
                            </div>
                        </div>
                        <div class="filla">
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_codiPostal"><spring:message code='edifici.formulari.codi_postal'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_codiPostal" name="cerca_codiPostal" type="text" maxlength="250" />
                                </div>    
                            </div>
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_poblacio"><spring:message code='edifici.formulari.poblacio'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_poblacio" name="cerca_poblacio" type="text" maxlength="250" />
                                </div>    
                            </div>
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_telefon"><spring:message code='edifici.formulari.telefon'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_telefon" name="cerca_telefon" type="text" maxlength="250" />
                                </div>    
                            </div>
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_fax"><spring:message code='edifici.formulari.fax'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_fax" name="cerca_fax" type="text" maxlength="250" />
                                </div>    
                            </div>
                        </div>
                        <div class="filla">
                            <div class="element t50">
                                <div class="etiqueta"><label for="cerca_email"><spring:message code='edifici.formulari.email'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_email" name="cerca_email" type="text" maxlength="250" />
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
                                                <label for="item_descripcio_ca"><spring:message code='edifici.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_descripcio_ca" name="item_descripcio_ca" type="text" class="nou" />
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
                                                <label for="item_descripcio_es"><spring:message code='edifici.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_descripcio_es" name="item_descripcio_es" type="text" class="nou" />
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
                                                <label for="item_descripcio_en"><spring:message code='edifici.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_descripcio_en" name="item_descripcio_en" type="text" class="nou" />
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
                                                <label for="item_descripcio_de"><spring:message code='edifici.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_descripcio_de" name="item_descripcio_de" type="text" class="nou" />
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
                                                <label for="item_descripcio_fr"><spring:message code='edifici.formulari.descripcio'/></label>
                                            </div>
                                            <div class="control">                           
                                                <input id="item_descripcio_fr" name="item_descripcio_fr" type="text" class="nou" />
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
                                    <label for="item_direccio"><spring:message code='edifici.formulari.direccio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_direccio" name="item_direccio" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_codi_postal"><spring:message code='edifici.formulari.codi_postal'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_codi_postal" name="item_codi_postal" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_poblacio"><spring:message code='edifici.formulari.poblacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_poblacio" name="item_poblacio" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50">
                                <div class="etiqueta">
                                    <label for="item_latitud"><spring:message code='edifici.formulari.coordenades'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_latitud" name="item_latitud" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50">
                                <div class="etiqueta">
                                    <label for="item_longitud"><spring:message code='edifici.formulari.coordenades'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_longitud" name="item_longitud" type="text" class="nou" />
                                </div>
                            </div>
                        	<div class="element t50">
                        		<div class="etiqueta">
                                    <label for="item_longitud">&nbsp;</label>
                                </div>
			                    <span class="btnGenerico">
			                        <a href="javascript:;" class="btn coordenades"><span><span><spring:message code='boto.coordenades'/></span></span></a>
			                    </span>
                        	</div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_telefon"><spring:message code='edifici.formulari.telefon'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_telefon" name="item_telefon" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_fax"><spring:message code='edifici.formulari.fax'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_fax" name="item_fax" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_email"><spring:message code='edifici.formulari.email'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_email" name="item_email" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <!-- fila -->
	                    <div class="fila">
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_foto_petita"><spring:message code='edifici.formulari.foto_petita'/></label></div>
	                            <div class="control archivo">   
	                                <div id="grup_item_foto_petita" class="file grup_arxiu_actual">
	                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
	                                    <a href="#" target="_blank"></a>
	                                    <input type="checkbox" name="item_foto_petita_delete" id="item_foto_petita_delete" value="1"/>
	                                    <label for="item_foto_petita_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
	                                </div>
	                            </div>
	                        </div>    
	                        
	                        
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_foto_petita"><spring:message code='edifici.formulari.foto_petita'/></label></div>
	                            <div class="control">
	                                <input id="item_foto_petita" name="item_foto_petita" type="file" class="nou" />
	                            </div>
	                        </div>                                                                                      
	                    </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_foto_gran"><spring:message code='edifici.formulari.foto_gran'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_foto_gran" class="file grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_foto_gran_delete" id="item_foto_gran_delete" value="1"/>
                                        <label for="item_foto_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                            
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_foto_gran"><spring:message code='edifici.formulari.foto_gran'/></label></div>
                                <div class="control">
                                    <input id="item_foto_gran" name="item_foto_gran" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_planol"><spring:message code='edifici.formulari.planol'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_planol" class="file grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_planol_delete" id="item_planol_delete" value="1"/>
                                        <label for="item_planol_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                            
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_planol"><spring:message code='edifici.formulari.planol'/></label></div>
                                <div class="control">
                                    <input id="item_planol" name="item_planol" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                        </div>
                        <!-- /fila -->
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
            <div class="modul">
            	<input type="hidden" id="llistaUnitatsAdministratives" name="unitatsAdministratives" value=""/>                     
                <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='unitatAdministrativa.uaRelacionats'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulUnitatAdministrativa -->
                        <div class="modulUnitatAdministratives">
                            <div class="seleccionats">
                                <%-- dsanchez: un solo idioma --%>
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaUnitatAdministrativaRelacionada'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="boton btnGenerico" style="margin-left: 0px;">
                                    <a href="javascript:carregarModulArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA');" class="btn consulta">
                                    	<span><span><spring:message code='boto.afegeixUnitatAdminsitrativa'/></span></span>
                                    </a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulUnitatAdministrativa -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->  
        </div>
        <!-- /modulLateral -->
    </form>
</div>