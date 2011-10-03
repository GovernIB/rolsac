<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/cataleg_procediments.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_documents.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_normativa.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/seekAttention.min.jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.maskedinput-1.2.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/autoresize.jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/procediments.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_tramits.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_normativa.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/catalegProcediments/llistat.htm" />';
    var pagDetall = '<c:url value="/catalegProcediments/pagDetall.htm" />';
    var pagGuardar = '<c:url value="/catalegProcediments/guardar.htm" />';
    var pagEsborrar = '<c:url value="/catalegProcediments/esborrarProcediment.htm" />';
    
    //texts
    var txtEsborrarCorrecte = "<spring:message code='txt.procediment_esborrat_correcte'/>";
    var txtEsborrarIncorrecte = "";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtLlistaItem = "Procediment";
    var txtLlistaItems = "Procediments";
    var txtPer = "<spring:message code='txt.per'/>";
    var txtDel = "<spring:message code='txt.del'/>";
    var txtAl = "<spring:message code='txt.al'/>";
    var txtOrdenats = "<spring:message code='txt.ordenats'/>";
    var txtOrdenades = "<spring:message code='txt.ordenades'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtMostremAl = " <spring:message code='txt.a_la'/> ";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtLlistaItems = "<spring:message code='txt.procediments'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_del'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "<spring:message code='txt.detall_de_la.titol'/> " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
    var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
    var txtMostra = "<spring:message code='txt.mostra'/>";
    var txtAmaga = "<spring:message code='txt.amaga'/>";
    var txtCaducat = "<spring:message code='txt.caducat'/>";
    var txtCaducitat = "Caducitat";
    
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
    
    var txtDocument = "document";
    var txtDocuments = "documents";
    var txtNoHiHaDocuments = txtNoHiHa + " " + txtDocuments;
    var txtTramitNouProcediment = "Per a gestionar els tràmits has de guardar el procediment";
    
    var txtMateria = "matèria";
    var txtMateries = "matèries";
    var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
    
    var txtNormativa = "Normativa";
    var txtNormatives = "Normatives";
    var txtNoHiHaNormativa = txtNoHiHa + " " + txtNormativa.toLowerCase();
    var txtSeleccionada = "Seleccionada";
    var txtSeleccionades = "Seleccionades";
    var txtNoHiHaNormativaSeleccionada = txtNoHiHa + " " + txtNormativa.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNumero = "Número";
    
    var txtImmediat = ""; //"Immediat"; // Comentado por si la funcionalidad fuese necesaria en el futuro (en el procediments.js se usa esta variable).
    var txtPublicacio = "Publicació";
    
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
</script>

<div id="escriptori_contingut">
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcio C">
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        <c:if test="${idUA > 0}">
            <li id="btnNuevaFicha" class="opcions nuevo"><a href="javascript:;" class="btn nou"><span><span>Crea un nou procediment</span></span></a></li>
        </c:if>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant">Carregant catàleg de procediments. Espere un moment, si us plau.</p>
            </div>
            <input type="hidden" value="0" class="pagPagina" /> <input
                type="hidden" value="DESC" class="ordreTipus" /> <input
                type="hidden" value="publicacio" class="ordreCamp" />
        </div>
        <div class="resultats C">
            <!-- cercador -->
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2>Cercador</h2>
                    <div class="fila">
                        <!-- div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_nom">Nom procediment</label>
                            </div>
                            <div class="control">
                                <input id="cerca_nom" name="cerca_nom" type="text" class="nom" />
                            </div>
                        </div-->
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_textes">Textes</label>
                            </div>
                            <div class="control">
                                <input id="cerca_textes" name="cerca_textes" type="text"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_codi">Codi</label>
                            </div>
                            <div class="control">
                                <input id="cerca_codi" name="cerca_codi" type="text" />
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_estat">Estat</label>
                            </div>
                            <div class="control">
                                <select id="cerca_estat" name="cerca_estat" class="t8">
                                    <option value="" selected="selected">Tria una opci&oacute;</option>
                                    <option value="1">P&uacute;blica</option>
                                    <option value="2">Interna</option>
                                    <option value="3">Reserva</option>
                                </select>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_familia">Familia</label>
                            </div>
                            <div class="control">
                                <select id="cerca_familia" name="cerca_familia">
                                    <option value="" selected="selected">Tria una opció</option>
                                    <c:forEach items="${families}" var="familia">
                                        <option value="<c:out value="${familia.id}"/>"><c:out value="${familia.nom}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div> 
                    <div class="fila">
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_iniciacio">Iniciacio</label>
                            </div>
                            <div class="control">
                                <select id="cerca_iniciacio" name="cerca_iniciacio">
                                    <option value="" selected="selected">Tria una opci&oacute;</option>
                                    <c:forEach items="${iniciacions}" var="iniciacio">
                                        <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_tramit">Identificador tr&agrave;mit</label>
                            </div>
                            <div class="control">
                                <input id="cerca_tramit" name="cerca_tramit" type="text"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_versio">Versi&oacute; tr&agrave;mit</label>
                            </div>
                            <div class="control">
                                <input id="cerca_versio" name="cerca_versio" type="text"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_url">URL tr&agrave;mit extern</label>
                            </div>
                            <div class="control">
                                <input id="cerca_url" name="cerca_url" type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_indicador">Fi de la via administrativa</label>
                            </div>
                            <div class="control">
                                <select id="cerca_indicador" name="cerca_indicador" class="t8">
                                    <option value="" selected="selected">Tria una opci&oacute;</option>
                                    <option value="0">No</option>
                                    <option value="1">Sí</option>
                                </select>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_finestreta">Inclou finestreta única</label>
                            </div>
                            <div class="control">
                                <select id="cerca_finestreta" name="cerca_finestreta" class="t8">
                                    <option value="" selected="selected">Tria una opci&oacute;</option>
                                    <option value="0">No</option>
                                    <option value="1">Sí</option>
                                </select>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_taxa">Taxa</label>
                            </div>
                            <div class="control">
                                <select id="cerca_taxa" name="cerca_taxa" class="t8">
                                    <option value="" selected="selected">Tria una opci&oacute;</option>
                                    <option value="0">No</option>
                                    <option value="1">Sí</option>
                                </select>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_responsable">Responsable</label>
                            </div>
                            <div class="control">
                                <input id="cerca_responsable" name="cerca_responsable" type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_fechaCaducidad">Data caducitat</label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaCaducidad" name="cerca_fechaCaducidad" type="text"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_fechaPublicacion">Data publicaci&oacute;</label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaPublicacion" name="cerca_fechaPublicacion" type="text"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_fechaActualizacion">Data actualitzaci&oacute;</label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaActualizacion" name="cerca_fechaActualizacion" type="text"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_uaFilles">Inclou UA filles</label>
                            </div>
                            <div class="control">
                                <select id="cerca_uaFilles" name="cerca_uaFilles" class="t8">
                                    <option value="0" selected="selected">No</option>
                                    <option value="1">Sí</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t30">
                            <div class="control">
                                <input id="cerca_uaMeves" name="cerca_uaMeves" type="checkbox" value="1"/> <label for="cerca_uaMeves" class="etiqueta">Busca a totes les meves unitats org&agrave;iques</label>
                            </div>
                        </div>
                        <div class="botonera noClear">
                            <div class="boton btnGenerico">
                              <a id="btnLimpiarForm" href="javascript:void(0)" class="btn borrar"><span><span><spring:message code='boto.borrar'/></span></span></a>
                            </div>
                            <div class="boton btnGenerico">
                             <a id="btnBuscarForm" href="javascript:;" class="btn consulta"><span><span><spring:message code='boto.cercar'/></span></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /cercador -->
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="publicacio" class="ordreCamp" />
        </div>
    </div>
</div>
<!-- /escriptori_contingut -->

<form id="formGuardar" action="false">
    <!-- escriptori_detall -->
    <div id="escriptori_detall">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <h2>Detall del procediment</h2>
        <p>Recorde que les dades amb asterisc (<span class="obligatori">*</span>) són obligatòries.</p>
        
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario">                    
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Dades</legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <p class="introIdiomas">Idioma:</p>
                            <ul class="idiomes">
                                <li class="idioma"><a href="javascript:;" class="ca">Català</a></li>
                                <li class="idioma"><a href="javascript:;" class="es">Español</a></li>
                                <li class="idioma"><a href="javascript:;" class="en">English</a></li>
                                <li class="idioma"><a href="javascript:;" class="de">Deutsch</a></li>
                                <li class="idioma"><a href="javascript:;" class="fr">Français</a></li>
                                <li class="traduix btnGenerico">
                                    <a href="javascript:;" class="btn traduix"><span><span>Traduïx</span></span></a>
                                </li>
                                <%--
                                dsanchez: Eliminado en el nuevo diseño.
                                <li class="desplegar">
                                    <a href="javascript:;" class="desplegar">Desplega</a>
                                </li>
                                --%>
                            </ul>
                            <div class="idiomes">
                                <!-- ca -->
                                <div class="idioma ca">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_ca">Nom del procediment</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_ca" name="item_nom_ca" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_ca">Objecte</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_ca" name="item_objecte_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_ca">Destinataris</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_ca"
                                                    name="item_destinataris_ca" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_ca">Requisits</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_ca" name="item_requisits_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_presentacio_ca">Presentació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_presentacio_ca" name="item_presentacio_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_ca">Termini màxim per a la resolució</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_ca" name="item_resolucio_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_ca">Termini màxim per a la notificació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_ca" name="item_notificacio_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_lloc_ca">Lloc</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_lloc_ca" name="item_lloc_ca" cols="50"
                                                    rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_ca">Silenci administratiu</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_ca" name="item_silenci_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_ca">Observacions</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_ca"
                                                    name="item_observacions_ca" cols="50" rows="2" class="nou"></textarea>
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
                                                <label for="item_nom_es">Nombre del procedimiento</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_es" name="item_nom_es" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_es">Objeto</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_es" name="item_objecte_es"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_es">Destinatarios</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_es"
                                                    name="item_destinataris_es" cols="50" rows="2"
                                                    class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_es">Requisitos</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_es" name="item_requisits_es"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_presentacio_es">Presentación</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_presentacio_es" name="item_presentacio_es"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_es">Plazo máximo para la
                                                    resolución</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_es" name="item_resolucio_es"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_es">Plazo máximo para la
                                                    notificación</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_es" name="item_notificacio_es"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_lloc_es">Lugar</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_lloc_es" name="item_lloc_es" cols="50"
                                                    rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_es">Silencio administrativo</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_es" name="item_silenci_es"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_es">Observaciones</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_es"
                                                    name="item_observacions_es" cols="50" rows="2"
                                                    class="rich nou"></textarea>
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
                                                <label for="item_nom_en">Nom del procediment</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_en" name="item_nom_en" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_en">Objecte</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_en" name="item_objecte_en"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_en">Destinataris</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_en"
                                                    name="item_destinataris_en" cols="50" rows="2"
                                                    class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_en">Requisits</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_en" name="item_requisits_en"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_presentacio_en">Presentació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_presentacio_en" name="item_presentacio_en"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_en">Termini màxim per a la
                                                    resolució</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_en" name="item_resolucio_en"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_en">Termini màxim per a
                                                    la notificació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_en" name="item_notificacio_en"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_lloc_en">Lloc</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_lloc_en" name="item_lloc_en" cols="50"
                                                    rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_en">Silenci administratiu</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_en" name="item_silenci_en"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_en">Observacions</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_en"
                                                    name="item_observacions_en" cols="50" rows="2"
                                                    class="rich nou"></textarea>
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
                                                <label for="item_nom_de">Nom del procediment</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_de" name="item_nom_de" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_de">Objecte</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_de" name="item_objecte_de"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_de">Destinataris</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_de"
                                                    name="item_destinataris_de" cols="50" rows="2"
                                                    class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_de">Requisits</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_de" name="item_requisits_de"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_presentacio_de">Presentació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_presentacio_de" name="item_presentacio_de"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_de">Termini màxim per a la
                                                    resolució</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_de" name="item_resolucio_de"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_de">Termini màxim per a
                                                    la notificació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_de" name="item_notificacio_de"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_lloc_de">Lloc</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_lloc_de" name="item_lloc_de" cols="50"
                                                    rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_de">Silenci administratiu</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_de" name="item_silenci_de"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_de">Observacions</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_de"
                                                    name="item_observacions_de" cols="50" rows="2"
                                                    class="rich nou"></textarea>
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
                                                <label for="item_nom_fr">Nom del procediment</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_fr" name="item_nom_fr" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_fr">Objecte</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_fr" name="item_objecte_fr"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_fr">Destinataris</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_fr"
                                                    name="item_destinataris_fr" cols="50" rows="2"
                                                    class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_fr">Requisits</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_fr" name="item_requisits_fr"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_presentacio_fr">Presentació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_presentacio_fr" name="item_presentacio_fr"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_fr">Termini màxim per a la
                                                    resolució</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_fr" name="item_resolucio_fr"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_fr">Termini màxim per a
                                                    la notificació</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_fr" name="item_notificacio_fr"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_lloc_fr">Lloc</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_lloc_fr" name="item_lloc_fr" cols="50"
                                                    rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_fr">Silenci administratiu</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_fr" name="item_silenci_fr"
                                                    cols="50" rows="2" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_fr">Observacions</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_fr"
                                                    name="item_observacions_fr" cols="50" rows="2"
                                                    class="rich nou"></textarea>
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
                    <a class="modul mostrat">Amaga</a>
                    <legend>Responsable</legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_codi">Codi</label>
                                </div>
                                <div class="control">
                                    <input id="item_codi" name="item_codi" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_iniciacio">Forma de iniciació</label>
                                </div>
                                <div class="control select">
                                    <select id="item_iniciacio" name="item_iniciacio" class="nou">
                                        <option value="" selected="selected">Cap</option>
                                        <c:forEach items="${iniciacions}" var="iniciacio">
                                            <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_url">URL tr&agrave;mit extern</label>
                                </div>
                                <div class="control">
                                    <input id="item_url" name="item_url" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_familia">Família</label>
                                </div>
                                <div class="control select">
                                    <select id="item_familia" name="item_familia" class="nou">
                                        <option value="" selected="selected">Cap</option>
                                        <c:forEach items="${families}" var="familia">
                                            <option value="<c:out value="${familia.id}"/>"><c:out value="${familia.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_tramite">Id. tr&agrave;mit</label>
                                </div>
                                <div class="control">
                                    <input id="item_tramite" name="item_tramite" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_version">Versi&oacute; tr&agrave;mit</label>
                                </div>
                                <div class="control">
                                    <input id="item_version" name="item_version" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_organ">Òrgan competent per resoldre</label>
                                </div>
                                <div class="control">
                                    <input id="item_organ" name="item_organ" type="text" class="nou" readonly="true" />
                                    <input id="item_organ_id" name="item_organ_id" type="hidden" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_responsable">Responsable</label>
                                </div>
                                <div class="control">
                                    <input id="item_responsable" name="item_responsable" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
	                            <div id="cercador">
	                                <div class="botonera" style="margin-top: 0px; float:left;">
		                                <div class="boton btnGenerico" style="margin-left: 0px;">
		                                    <a href="javascript:ArbreUA('item_organ', 'item_organ_id');" class="btn consulta">
		                                    <span><span>Cambiar Órgan</span></span>
		                                    </a>
		                                </div>
		                                <div class="boton btnGenerico">
		                                    <a href="javascript:EliminaArbreUA('item_organ', 'item_organ_id');" class="btn borrar">
		                                    <span><span><spring:message code='boto.borrar'/></span></span>
		                                    </a>
		                                </div>
								    </div>		                            
								</div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_finestreta_unica">Finestreta Única de la Directiva de Serveis</label>
                                </div>
                                <div class="control">
                                    <input id="item_finestreta_unica" name="item_finestreta_unica" type="checkbox" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_fi_vida_administrativa">Fi de la vida administrativa</label>
                                </div>
                                <div class="control">
                                    <input id="item_fi_vida_administrativa" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_taxa">Taxa</label>
                                </div>
                                <div class="control">
                                    <input id="item_taxa" name="item_taxa" type="checkbox" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_notes">Notes per a informadors</label>
                                </div>
                                <div class="control">
                                    <textarea id="item_notes" name="item_notes" cols="50" rows="2"
                                        class="nou"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
            </form>
            <!-- /modul -->
        </div>
        <!-- /modulPrincipal -->
        <!-- modulLateral -->
        <div id="modulLateral">
            <? if ($_SESSION['rolsac_rol'] != "RSC_OPERADOR") { ?>
            <!-- modul -->
            <div class="modul publicacio">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Publicació</legend>
                    <div class="modul_continguts mostrat">
                        <!-- fila -->
                        <div class="fila publicacion_2campos">
                            <div class="element left">
                                <div class="etiqueta">
                                    <label for="item_estat">Estat</label>
                                </div>
                                <div class="control">
                                    <select id="item_estat" name="item_estat">
                                        <option value="" selected="selected">Tria una opci&oacute;</option>
                                        <option value="1">P&uacute;blica</option>
                                        <option value="2">Interna</option>
                                        <option value="3">Reserva</option>
                                    </select>
                                </div>
                            </div>
                            <div class="element right"></div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila publicacion_2campos">
                            <div class="element left">
                                <div class="etiqueta">
                                    <label for="item_data_publicacio">Data publicació</label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacio" name="item_data_publicacio" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_caducitat">Data caducitat</label>
                                </div>
                                <div class="control">
                                    <input id="item_data_caducitat" name="item_data_caducitat" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <!-- /fila -->
                        <!-- botonera dalt -->
                        <div class="botonera dalt">
                          <ul>
                              <li class="btnGuardar">
                                  <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span>Guarda!</span></span></a>
                              </li>
                              <li class="btnPrevisualizar">
                                  <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span>Previsualitza</span></span></a>
                              </li>
                              <li class="btnVolver">
                                  <a id="btnVolver" href="javascript:;" class="btn torna"><span><span>Torna</span></span></a>
                              </li>
                              <li class="btnEliminar" style="display:none;">
                                  <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
                              </li>
                          </ul>
                        </div>
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <? } ?>
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Documents relacionats</legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulDocuments -->
                        <div class="modulDocuments">
                            <ul class="idiomes">
                                <li class="introIdiomas">Idioma:</li>
                                <li class="ca seleccionat">ca</li>
                                <li class="es">es</li>
                                <li class="en">en</li>
                                <li class="de">de</li>
                                <li class="fr">fr</li>
                            </ul>
                            <div class="seleccionats">
                                <div class="ca seleccionat">
                                    <p class="info">No hi ha documents.</p>
                                    <!--ul>
                                        <li>
                                            <div>
                                                <span class="doc"><a href="#">Nom del document</a>
                                                </span> <a class="btn lleva" href="javascript:;"><span><span>Lleva</span>
                                                </span>
                                                </a>
                                            </div></li>
                                        <li>
                                            <div>
                                                <span class="doc"><input type="file" size="5"
                                                    name="doc_ca">
                                                </span> <a class="btn esborra" href="javascript:;"><span><span>Esborra</span>
                                                </span>
                                                </a>
                                            </div></li>
                                        <li>
                                            <div>
                                                <span class="doc"><input type="file" size="5"
                                                    name="doc_ca">
                                                </span> <a class="btn esborra" href="javascript:;"><span><span>Esborra</span>
                                                </span>
                                                </a>
                                            </div></li>
                                    </ul-->
                                </div>
                                <div class="es">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="en">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="de">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="fr">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                            </div>
                            <p class="btnGenerico">
                                <a href="javascript:;" class="btn traduix"><span><span>Afegeix document</span></span></a>
                            </p>
                        </div>
                        <!-- /modulDocuments -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Tràmits</legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulTramits -->
                        <div class="modulTramits">
                            <div class="seleccionats">
                                <p class="info">No hi ha tràmits.</p>
                                <p class="gestiona btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona tràmits</span></span></a>
                                </p>
                            </div>
                        </div>
                        <!-- /modulTramits -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Matèries relacionades</legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulMateries -->
                        <div class="modulMateries">
                            <div class="seleccionats">
                                <p class="info">No hi ha matèries.</p>
                                <!--
                                                <p class="info">Hi ha <strong>3 matèries</strong>.</p>
                                                
                                                <ul>
                                                    <li>Agricultura</li>
                                                    <li>Canvi climàtic</li>
                                                    <li>Caça</li>
                                                </ul>
                                                -->
                                <p class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona matèries</span></span></a>
                                </p>
                            </div>
                            <div class="llistat">
                                <ul>
                                    <li><label><span>Agricultura</span><input
                                            type="checkbox" value="1" />
                                    </label>
                                    </li>
                                    <li><label><span>Canvi climàtic</span><input
                                            type="checkbox" value="2" />
                                    </label>
                                    </li>
                                    <!--
                                    <li><label><span>Caça</span><input type="checkbox"
                                            value="3" />
                                    </label>
                                    </li>
                                    <li><label><span>Comerç</span><input
                                            type="checkbox" value="4" />
                                    </label>
                                    </li>
                                    <li><label><span>Consum</span><input
                                            type="checkbox" value="5" />
                                    </label>
                                    </li>
                                    <li><label><span>Contractació administrativa</span><input
                                            type="checkbox" value="6" />
                                    </label>
                                    </li>
                                    <li><label><span>Cooperació</span><input
                                            type="checkbox" value="7" />
                                    </label>
                                    </li>
                                    <li><label><span>Cultura</span><input
                                            type="checkbox" value="8" />
                                    </label>
                                    </li>
                                    <li><label><span>Dependència</span><input
                                            type="checkbox" value="9" />
                                    </label>
                                    </li>
                                    <li><label><span>Discapacitat</span><input
                                            type="checkbox" value="10" />
                                    </label>
                                    </li>
                                    <li><label><span>Dona</span><input type="checkbox"
                                            value="11" />
                                    </label>
                                    </li>
                                    <li><label><span>Economia</span><input
                                            type="checkbox" value="12" />
                                    </label>
                                    </li>
                                    <li><label><span>Educació</span><input
                                            type="checkbox" value="13" />
                                    </label>
                                    </li>
                                    <li><label><span>Emergències</span><input
                                            type="checkbox" value="14" />
                                    </label>
                                    </li>
                                    <li><label><span>Emigració</span><input
                                            type="checkbox" value="15" />
                                    </label>
                                    </li>
                                    <li><label><span>Empresa</span><input
                                            type="checkbox" value="16" />
                                    </label>
                                    </li>
                                    <li><label><span>Energia</span><input
                                            type="checkbox" value="17" />
                                    </label>
                                    </li>
                                    <li><label><span>Entitats</span><input
                                            type="checkbox" value="18" />
                                    </label>
                                    </li>
                                    <li><label><span>Esports</span><input
                                            type="checkbox" value="19" />
                                    </label>
                                    </li>
                                    <li><label><span>Europa / Internacional</span><input
                                            type="checkbox" value="20" />
                                    </label>
                                    </li>
                                    <li><label><span>Família</span><input
                                            type="checkbox" value="21" />
                                    </label>
                                    </li>
                                    <li><label><span>Formació ocupacional</span><input
                                            type="checkbox" value="22" />
                                    </label>
                                    </li>
                                    <li><label><span>Gent gran</span><input
                                            type="checkbox" value="23" />
                                    </label>
                                    </li>
                                    <li><label><span>Habitatge</span><input
                                            type="checkbox" value="24" />
                                    </label>
                                    </li>
                                    <li><label><span>Immigració</span><input
                                            type="checkbox" value="25" />
                                    </label>
                                    </li>
                                    <li><label><span>Indústria</span><input
                                            type="checkbox" value="26" />
                                    </label>
                                    </li>
                                    <li><label><span>Infraestructures</span><input
                                            type="checkbox" value="27" />
                                    </label>
                                    </li>
                                    <li><label><span>Infància i menors</span><input
                                            type="checkbox" value="28" />
                                    </label>
                                    </li>
                                    <li><label><span>Innovació i recerca</span><input
                                            type="checkbox" value="29" />
                                    </label>
                                    </li>
                                    <li><label><span>Institucions</span><input
                                            type="checkbox" value="30" />
                                    </label>
                                    </li>
                                    <li><label><span>Joventut</span><input
                                            type="checkbox" value="31" />
                                    </label>
                                    </li>
                                    <li><label><span>Llengua</span><input
                                            type="checkbox" value="32" />
                                    </label>
                                    </li>
                                    <li><label><span>Medi ambient</span><input
                                            type="checkbox" value="33" />
                                    </label>
                                    </li>
                                    <li><label><span>Oposicions i funció pública</span><input
                                            type="checkbox" value="34" />
                                    </label>
                                    </li>
                                    <li><label><span>Participació ciutadana</span><input
                                            type="checkbox" value="35" />
                                    </label>
                                    </li>
                                    <li><label><span>Personal docent</span><input
                                            type="checkbox" value="36" />
                                    </label>
                                    </li>
                                    <li><label><span>Pesca</span><input type="checkbox"
                                            value="37" />
                                    </label>
                                    </li>
                                    <li><label><span>Policia i interior</span><input
                                            type="checkbox" value="38" />
                                    </label>
                                    </li>
                                    <li><label><span>Qualitat</span><input
                                            type="checkbox" value="39" />
                                    </label>
                                    </li>
                                    <li><label><span>Ramaderia</span><input
                                            type="checkbox" value="40" />
                                    </label>
                                    </li>
                                    <li><label><span>Salut</span><input type="checkbox"
                                            value="41" />
                                    </label>
                                    </li>
                                    <li><label><span>Serveis socials</span><input
                                            type="checkbox" value="42" />
                                    </label>
                                    </li>
                                    <li><label><span>Tecnologia i comunicacions</span><input
                                            type="checkbox" value="43" />
                                    </label>
                                    </li>
                                    <li><label><span>Territori</span><input
                                            type="checkbox" value="44" />
                                    </label>
                                    </li>
                                    <li><label><span>Transports i mobilitat</span><input
                                            type="checkbox" value="45" />
                                    </label>
                                    </li>
                                    <li><label><span>Treball i ocupació</span><input
                                            type="checkbox" value="46" />
                                    </label>
                                    </li>
                                    <li><label><span>Tributs i recaptació</span><input
                                            type="checkbox" value="47" />
                                    </label>
                                    </li>
                                    <li><label><span>Turisme i hostaleria</span><input
                                            type="checkbox" value="48" />
                                    </label>
                                    </li>
                                     -->
                                </ul>
                                <p class="btnGenerico">
                                    <a class="btn finalitza" href="javascript:;"><span><span>Finalitza</span></span></a>
                                </p>
                            </div>
                        </div>
                        <!-- /modulMateries -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Normativa relacionada</legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulNormativa -->
                        <div class="modulNormativa">
                            <div class="seleccionats">
                                <p class="info">No hi ha normativa.</p>
                                <p class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona normativa</span></span></a>
                                </p>
                            </div>
                        </div>
                        <!-- /modulNormativa -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
        </div>
        <!-- /modulLateral -->
    </div>
    <!-- /escriptori_detall -->
    <!-- escriptori_previsualitza -->
    <div id="escriptori_previsualitza">
        <h2>Previsualitzant la fitxa</h2>
        <p>
            <a href="javascript:;" class="btn torna dePrevisualitzar"><span><span>Torna</span>
            </span>
            </a>
        </p>
        <div class="previsualitzacio">
            <iframe frameborder="0" scrolling="auto"></iframe>
        </div>
    </div>
    <!-- /escriptori_previsualitza -->
    <!-- escriptori_normativa -->
    <div id="escriptori_normativa">
        <h2>Gestió de la normativa relacionada</h2>
        <div class="botonera dalt">
            <ul>
                <li><a href="javascript:;" class="btn torna"><span><span>Torna
                                al detall</span>
                    </span>
                </a></li>
                <li><a href="javascript:;" class="btn finalitza important"><span><span>Finalitza</span>
                    </span>
                </a></li>
            </ul>
        </div>
        <!-- llistat -->
        <div class="escriptori_items_llistat">
            <!-- cercador -->
            <div class="escriptori_items_cercador">
                <h3>Cercador</h3>
                <div class="fila">
                    <div class="element t15">
                        <div class="etiqueta">
                            <label for="cerca_normativa_titol">Títol de la normativa</label>
                        </div>
                        <div class="control">
                            <input id="cerca_normativa_titol" name="cerca_normativa_titol"
                                type="text" class="titol" />
                        </div>
                    </div>
                    <div class="element t7">
                        <div class="etiqueta">
                            <label for="cerca_normativa_codi">Codi</label>
                        </div>
                        <div class="control">
                            <input id="cerca_normativa_codi" name="cerca_normativa_codi"
                                type="text" class="codi" />
                        </div>
                    </div>
                    <div class="element t18">
                        <div class="etiqueta">
                            <label for="cerca_normativa_no_relacionades">Cerca per
                                normativa no relacionada</label>
                        </div>
                        <div class="control">
                            <select id="cerca_normativa_no_relacionades"
                                name="cerca_normativa_no_relacionades" class="t8">
                                <option value="0" selected="selected">No</option>
                                <option value="1">Sí</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="botonera">
                    <a href="javascript:;" class="btn consulta"><span><span>Cerca!</span>
                    </span>
                    </a>
                </div>
            </div>
            <!-- /cercador -->
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" /> <input
                type="hidden" value="DESC" class="ordreTipus" /> <input
                type="hidden" value="data" class="ordreCamp" />
        </div>
        <!-- /llistat -->
        <!-- seleccionats -->
        <div class="escriptori_items_seleccionats">
            <h3>Normativa seleccionada</h3>
            <div class="dades">
                <p class="info">No hi ha cap normativa.</p>
                <!--
                                <p class="info">Seleccionades <strong>5 normatives</strong>.</p>
                                
                                <ul>
                                    <li>
                                        <div class="norma">
                                            <input type="hidden" value="1" />
                                            <span class="norma">(1) Títol exemple de la Normativa</span>
                                            <a href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="norma">
                                            <input type="hidden" value="2" />
                                            <span class="norma">(2) Títol exemple de la Normativa</span>
                                            <a href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
                                        </div>
                                    </li>
                                </ul>
                                -->
            </div>
    
        </div>
        <!-- seleccionats -->
    </div>
    <!-- /escriptori_normativa -->
</form>