<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <link href='<c:url value="/css/fitxes.css"/>' rel="stylesheet" type="text/css" media="screen" />    
    <link href='<c:url value="/css/modul_seccions_ua.css"/>' rel="stylesheet" type="text/css" media="screen" />
    <link href='<c:url value="/css/modul_ua_arbre.css"/>' rel="stylesheet" type="text/css" media="screen" />
    <link href='<c:url value="/css/modul_seccions_arbre.css"/>' rel="stylesheet" type="text/css" media="screen" />
    
    <script type="text/javascript" src="<c:url value='/js/fitxes.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_seccions_ua.js'/>"></script>
    <!-- script type="text/javascript" src="<c:url value='/js/modul_seccions_arbre.js'/>"></script-->
    <script type="text/javascript" src="<c:url value='/js/modul_fetsVitals.js'/>"></script>
    <!--script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script-->

    <script type="text/javascript">
    var pagLlistat = '<c:url value="/fitxainf/llistat.htm" />';
    var pagDetall = '<c:url value="/fitxainf/pagDetall.htm" />';

    // texts
    var txtEspere = "Espere un moment, si us plau.";
    var txtCarregant = "Carregant";
    var txtSi = "S�";
    var txtNo = "No";
    var txtTrobats = "Trobades";
    var txtLlistaItem = "Fitxa";
    var txtLlistaItems = "Fitxes";
    var txtAdministrativa = "Administrativa";
    var txtAdministratives = "Administratives";
    var txtData = "Data";
    var txtPublicacio = "Publicaci�";
    var txtCaducitat = "Caducitat";
    var txtMostrem = "Mostrem de la ";
    var txtMostremAl = " a la ";
    var txtNoHiHa = "No hi ha";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantLlistat = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenats = "ordenats";
    var txtAscendentment = "ascendentment";
    var txtDescendentment = "descendentment";
    var txtPer = "per";

    // taula
    var pag_Res = 5;
    var txtNom = "Nom";
    var txtPare = "Pare";
    var txtCercant = "Cercant";
    var txtCercantLlistat = txtCercant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    // paginacio
    var txtTrobat = "S'ha trobat";
    var txtSeguents = "Seg�ents";
    var txtAnteriors = "Anteriors";
    var txtInici = "Inici";
    var txtFinal = "Final";
    var txtPagines = "P�gines";
    var txtCercantElements = txtCercant + " " + txtLlistaItems;
    var txtCercantLlistatAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
    var txtCercantLlistatSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
    var txtCercantAnteriors = txtCercantLlistatAnteriors;
    var txtCercantSeguents = txtCercantLlistatSeguents;
    
    // detall
    var txtCarregantDetall = txtCarregant + " detall de la "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "Nova " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "Detall de la " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "Est� segur de voler eliminar aquesta " + txtLlistaItem.toLowerCase() + "?";
    var txtEnviantDades = "Enviant dades al servidor. " + txtEspere;
    var txtMostra = "Mostra";
    var txtAmaga = "Amaga";
    var txtCaducat = "Caducada";
    var txtImmediat = "Immediat";
    // idioma
    var txtDesplega = "Desplega";
    var txtPlega = "Plega";
    // fotos
    var txtImatge = "Imatge";
    var txtFoto = "Foto";
    var txtFotos = "Fotos";
    var txtFotoPetita = txtFoto + " petita";
    var txtFotoGran = txtFoto + " gran";
    var txtLleva = "Lleva";
    var txtInclou = "Inclou";
    var txtElimina = "Elimina";
    var txtNoHiHaFotos = txtNoHiHa + " " + txtFotos.toLowerCase() + " associades";
    // docs
    var txtNom = "Nom";
    var txtArxiu = "Arxiu"
    // enllasos
    var txtAdresa = "Adre�a web";
    // moduls
    var txtHiHa = "Hi ha";
    var txtNoHiHa = "No hi ha";
    var txtSeleccionat = "Seleccionat";
    var txtSeleccionats = "Seleccionats";
    // modul documents
    var txtDocument = "document";
    var txtDocuments = "documents";
    var txtNoHiHaDocuments = txtNoHiHa + " " + txtDocuments;
    var txtEsborra = "Esborra";
    var txtInclou = "Inclou";
    var txtLleva = "Lleva";
    // modul materies
    var txtMateria = "mat�ria";
    var txtMateries = "mat�ries";
    var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
    // modul fets vitals
    var txtFet = "fet vital";
    var txtFets = "fets vitals";
    var txtNoHiHaFets = txtNoHiHa + " " + txtFets;
    // modul seccio/ua
    var txtSeccioUA = "Secci�/UA";
    var txtSeccionsUA = "Seccions/UA";
    var txtNoHiHaSeccioUA = "No hi ha cap " + txtSeccioUA.toLowerCase();
    var txtLaSeccio = "La secci�";
    var txtAmbLaUnitat = "amb la unitat";
    // modul ua arbre
    var txtArrel = "Arrel";
    var txtNodesFills = "Nodes fills";
    var txtCarregantArrel = "Carregant node arrel de l'arbre. " + txtEspere;
    var txtCarregantNodes = txtCarregant + " nodes fills. " + txtEspere;
    
    // error conexio
    var txtCalcularTotal = "Calcular el total";
    var txtAjaxError = "S'ha produ�t un error de conexi�.";
    var txtIntenteho = "Intente-ho de nou en uns minuts.";
    var txtConexionIntentar = "Intentar conexi� en";
    var txtSegon = "segon";
    var txtSegons = "segons";
    var txtConectar = "Conectar ara";
    var txtFuncions = "Les funcions de l'aplicaci� s'han deshabilitat";
    var txtFuncionsFins = "fins que no es restableixi la comunicaci�";
    
    // suggeriments
    var suggeriments = "";
    var txtMaxim = "m�xim";
    var txtMax = "m�x.";
    var txtCaracters = "caracters";
    var txtCampObligatori = "Camp obligatori";
    var txtAnyMal = "El format de l'any del camp";
    var txtMesMal = "El format del mes del camp";
    var txtDiaMal = "El format de dia del camp";
    var txtNoEsCorrecte = "no �s correcte. Exemple: 01/01/2010";

    </script>
    <div id="escriptori_contingut"> 
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcio C">
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        <li class="opcions nuevo">          
            <a id="btnNuevaFicha" href="javascript:;" class="btn nou"><span><span>Crea  una nova fitxa</span></span></a>
        </li>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant">Carregant llistat de fitxes. Espere un
                    moment, si us plau.</p>
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
                                <label for="cerca_materia">Mat�ria</label>
                            </div>
                            <div class="control select">
                                <select id="cerca_materia" name="cerca_materia" class="materia">
                                    <option value="">Tria una opci&oacute;</option>
                                    <c:forEach items="${llistaMateries}" var="materia">
                                        <option value='<c:out value="${materia.id}" />'><c:out value="${materia.nom}" /></option>
                                    </c:forEach>
                                </select>               
                                <%--input id="cerca_materia" name="cerca_materia" type="text"
                                    class="materia" /--%>
                            </div>
                        </div>
                    </div> 
                    <div class="fila">
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_fetVital">Fet vital</label>
                            </div>
                            <div class="control select">
                                <select id="cerca_fetVital" name="cerca_fetVital" class="fetVital">
                                    <option value="">Tria una opci&oacute;</option>
                                    <c:forEach items="${llistaFetsVitals}" var="fetVital">
                                        <option value='<c:out value="${fetVital.id}" />'><c:out value="${fetVital.nom}" /></option>
                                    </c:forEach>
                                </select>   
                                <%--input id="cerca_fetVital" name="cerca_fetVital" type="text"
                                    class="fetVital" /--%>
                            </div>
                        </div>
                        <!-- div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_seccio">Secci�</label>
                            </div>
                            <div class="control">
                                <input id="cerca_seccio" name="cerca_seccio" type="text"
                                    class="fetVital" />
                            </div>
                        </div-->
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_url">URL F�rum</label>
                            </div>
                            <div class="control">
                                <input id="cerca_url" name="cerca_url" type="text"/>
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
                                    <option value="1">S�</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t30">
                            <div class="control">
                                <input id="cerca_uaMeves" name="cerca_uaMeves" type="checkbox" value="1"/> <label for="cerca_uaMeves" class="etiqueta">Busca a totes les meves unitats org&agrave;niques</label>
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
<!-- escriptori_detall -->
<div id="escriptori_detall">
    <form id="formGuardar" action="false">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <h2>Detall de la fitxa</h2>
        <p>Recorde que les dades amb asterisc (<span class="obligatori">*</span>) s�n obligat�ries.</p>
            
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Dades</legend>
                    <div class="modul_continguts mostrat">
                        <!-- fila -->
                        <div class="fila">
                            <p class="introIdiomas">Idioma:</p>
                            <ul class="idiomes">
                                <li class="idioma"><a href="javascript:;" class="ca">Catal�</a></li>
                                <li class="idioma"><a href="javascript:;" class="es">Espa�ol</a></li>
                                <li class="idioma"><a href="javascript:;" class="en">English</a></li>
                                <li class="idioma"><a href="javascript:;" class="de">Deutsch</a></li>
                                <li class="idioma"><a href="javascript:;" class="fr">Fran�ais</a></li>
                                
                                <li class="traduix btnGenerico">
                                    <a href="javascript:;" class="btn traduix"><span><span>Tradu�x</span></span></a>
                                </li>
                            </ul>
                            <div class="idiomes">
                                <!-- ca -->
                                <div class="idioma ca">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_ca">T�tol de la fitxa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_ca" name="item_titol_ca" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_curta_ca">Descripci� abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_ca" name="item_des_curta_ca" cols="70" rows="5" style="width: 100%" class="rich basic nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_ca">Descripci� extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_ca" name="item_des_llarga_ca" cols="70" rows="10" style="width: 100%" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_ca">Adre�a web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_ca" name="item_url_ca" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- enllasos -->
                                    <h3>Enlla�os relacionats</h3>
                                    <div class="enllasosRelacionats">
                                        <p class="info">No hi ha enlla�os.</p>
                                        <div class="fila">
                                            <p>
                                                <a href="javascript:;" class="btn afegeix"><span><span>Afegeix
                                                            enlla�</span>
                                                </span>
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <!-- /enllasos -->
                                </div>
                                <!-- /ca -->
                                <!-- es -->
                                <div class="idioma es">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_es">T�tulo de la ficha</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_es" name="item_titol_es" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_curta_es">Descripci�n abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_es" name="item_des_curta_es" cols="70" rows="5" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_es">Descripci�n extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_es" name="item_des_llarga_es" cols="70" rows="10" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_es">Direcci�n web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_es" name="item_url_es" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- enllasos -->
                                    <h3>Enlaces relacionados</h3>
                                    <div class="enllasosRelacionats">
                                        <p class="info">No hay enlaces.</p>
                                        <div class="fila">
                                            <p>
                                                <a href="javascript:;" class="btn afegeix"><span><span>A�ade
                                                            enlace</span>
                                                </span>
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <!-- /enllasos -->
                                </div>
                                <!-- /es -->
                                <!-- en -->
                                <div class="idioma en">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_en">Headline</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_en" name="item_titol_en" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_curta_en">Shorten description</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_en" name="item_des_curta_en" cols="70" rows="5" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_en">Detailed description</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_en" name="item_des_llarga_en" cols="70" rows="10" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_en">Extern web adress</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_en" name="item_url_en" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- enllasos -->
                                    <h3>Enlla�os relacionats</h3>
                                    <div class="enllasosRelacionats">
                                        <p class="info">No hi ha enlla�os.</p>
                                        <div class="fila">
                                            <p>
                                                <a href="javascript:;" class="btn afegeix"><span><span>Afegeix
                                                            enlla�</span>
                                                </span>
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <!-- /enllasos -->
                                </div>
                                <!-- /en -->
                                <!-- de -->
                                <div class="idioma de">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_de">T�tol de la fitxa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_de" name="item_titol_de" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_curta_de">Descripci� abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_de" name="item_des_curta_de" cols="70" rows="5" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_de">Descripci� extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_de" name="item_des_llarga_de" cols="70" rows="10" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_de">Adre�a web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_de" name="item_url_de" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- enllasos -->
                                    <h3>Enlla�os relacionats</h3>
                                    <div class="enllasosRelacionats">
                                        <p class="info">No hi ha enlla�os.</p>
                                        <div class="fila">
                                            <p>
                                                <a href="javascript:;" class="btn afegeix"><span><span>Afegeix
                                                            enlla�</span>
                                                </span>
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <!-- /enllasos -->
                                </div>
                                <!-- /de -->
                                <!-- fr -->
                                <div class="idioma fr">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_fr">T�tol de la fitxa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_fr" name="item_titol_fr" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_curta_fr">Descripci� abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_fr" name="item_des_curta_fr" cols="70" rows="5" style="width: 100%" class="rich basic nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_fr">Descripci� extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_fr" name="item_des_llarga_fr" cols="70" rows="10" style="width: 100%" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_fr">Adre�a web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_fr" name="item_url_fr" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- enllasos -->
                                    <h3>Enlla�os relacionats</h3>
                                    <div class="enllasosRelacionats">
                                        <p class="info">No hi ha enlla�os.</p>
                                        <div class="fila">
                                            <p>
                                                <a href="javascript:;" class="btn afegeix"><span><span>Afegeix
                                                            enlla�</span>
                                                </span>
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <!-- /enllasos -->
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
                    <a class="modul amagat">Mostra</a>
                    <legend>Multim�dia</legend>
                    <div class="modul_continguts">
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_icona">Icona</label>
                                </div>
                                <div class="control">
                                    <input id="item_icona" name="item_icona" type="file" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_banner">Banner</label>
                                </div>
                                <div class="control">
                                    <input id="item_banner" name="item_banner" type="file"
                                        class="nou" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_imatge">Imatge</label>
                                </div>
                                <div class="control">
                                    <input id="item_imatge" name="item_imatge" type="file"
                                        class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_youtube">Adre�a video YouTube</label>
                                </div>
                                <div class="control">
                                    <input id="item_youtube" name="item_youtube" type="text"
                                        class="nou" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_forum">Enlla� f�rum / L�nia de debat</label>
                                </div>
                                <div class="control">
                                    <input id="item_forum" name="item_forum" type="text" class="nou" />
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
                    <a class="modul amagat">Mostra</a>
                    <legend>Responsable</legend>
                    <div class="modul_continguts">
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_notes">Notes</label>
                                </div>
                                <div class="control">
                                    <textarea id="item_notes" name="item_notes" cols="50" rows="7" class="nou"></textarea>
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
        <div id="modulLateral">
            <? if ($_SESSION['rolsac_rol'] != "RSC_OPERADOR") { ?>
            <!-- modul -->
            <div class="modul publicacio">
                <fieldset>
                    <legend>Publicaci�</legend>
                    <div class="modul_continguts mostrat">
                        <!-- fila -->
                        <div class="fila">
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
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element left">
                                <div class="etiqueta">
                                    <label for="item_data_publicacio">Data publicaci�</label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacio" name="item_data_publicacio"
                                        type="text" value="Immediat" class="nou" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_caducitat">Data caducitat</label>
                                </div>
                                <div class="control">
                                    <input id="item_data_caducitat" name="item_data_caducitat"
                                        type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- botonera baix -->                      
                                                                            
                        <div class="botonera dalt">
                          <ul>
                              <li class="btnVolver impar">
                                  <a id="btnVolver" href="javascript:;" class="btn torna"><span><span>Torna</span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span>Guarda!</span></span></a>
                              </li>                                                    
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
                              </li>
                              <li class="btnPrevisualizar par">
                                  <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span>Previsualitza</span></span></a>
                              </li>                              
                          </ul>
                        </div>
                        
                        
                        <!-- /botonera baix -->
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
                                <li class="ca seleccionat">ca</li>
                                <li class="es">es</li>
                                <li class="en">en</li>
                                <li class="de">de</li>
                                <li class="fr">fr</li>
                            </ul>
                            <div class="seleccionats">
                                <div class="ca seleccionat">
                                    <p class="info">No hi ha documents.</p>
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
                                <p class="btnGenerico">
                                    <a class="btn afegeix" href="javascript:;"><span><span>Afegeix document</span></span></a>
                                </p>
                            </div>
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
                    <legend>Mat�ries relacionades</legend>
                    <div class="modul_continguts mostrat">
                    
                        <!-- modulMateries -->
                        <div class="modulMateries selectorChecks">
                            <div class="seleccionats">
                                <p class="info">No hi ha mat�ries.</p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona mat�ries</span></span></a>
                                </div>
                            </div>
                            <div class="llistat">
                                <ul>
                                    <c:forEach items="${llistaMateries}" var="materia" varStatus="i">
                                        <c:choose>
                                            <c:when test="${(i.count) % 2 == 0}">
                                                <li class="par">
                                            </c:when>
                                            <c:otherwise>
                                               <li class="impar">
                                            </c:otherwise>
                                        </c:choose>                                     
                                          <label><span><c:out value="${materia.nom}" /></span><input type="checkbox" value="<c:out value='${materia.id}' />" /></label>
                                        </li>                                                                                                               
                                    </c:forEach>
                                </ul>
                                <div class="botonera">
                                    <div class="btnGenerico">
                                        <a class="btn finalitza" href="javascript:;"><span><span>Finalitza</span></span></a>
                                    </div>
                                    <div class="btnGenerico">
                                        <a href="javascript:;" class="cancela"><span><span>Cancel�la</span></span></a>
                                    </div>
                                </div>
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
                    <legend>Fets vitals relacionats</legend>
                    <div class="modul_continguts mostrat">
                    
                        <!-- modulFetsVitals -->
                        <div class="modulFetsVitals selectorChecks">
                            <div class="seleccionats">
                                <p class="info">No hi ha fets vitals.</p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona fets vitals</span></span></a>
                                </div>
                            </div>
                            <div class="llistat">
                                <ul>
                                    <li><label><span>Fet vital 1</span><input
                                            type="checkbox" value="1" />
                                    </label>
                                    </li>
                                    <li><label><span>Fet vital 2</span><input
                                            type="checkbox" value="2" />
                                    </label>
                                    </li>
                                    <li><label><span>Fet vital 3</span><input
                                            type="checkbox" value="3" />
                                    </label>
                                    </li>
                                    <li><label><span>Fet vital 4</span><input
                                            type="checkbox" value="4" />
                                    </label>
                                    </li>
                                    <li><label><span>Fet vital 5</span><input
                                            type="checkbox" value="5" />
                                    </label>
                                    </li>
                                </ul>
                                <div class="botonera">
                                    <div class="btnGenerico">
                                        <a class="btn finalitza" href="javascript:;"><span><span>Finalitza</span></span></a>
                                    </div>
                                    <div class="btnGenerico">
                                        <a href="javascript:;" class="cancela"><span><span>Cancel�la</span></span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /modulFetsVitals -->
                        
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>
                        Relaci�: Secci� - <abbr title="Unitat Administrativa">UA</abbr>
                    </legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulSeccionsUA -->
                        <div class="modulSeccionsUA">
                            <div class="seleccionats">
                                <p class="info">No hi ha seccions amb Unitat Administrativa.</p>
                                <div class="listaOrdenable">
                                    <ul>
                                        <li><input type="hidden" value="1" class="seccio" /> <input
                                            type="hidden" value="4" class="ua" /> La secci� <em
                                            class="seccio">Correcci� d'errors</em>, amb la UA <em
                                            class="ua">T�tol de la norma</em></li>
                                    </ul>
                                </div>
                                <div class="botonera">
                                    <div class="btnGenerico">
                                        <a class="btn gestiona" href="javascript:;"><span><span>Gestiona seccions</span></span></a>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                        <!-- /modulSeccionsUA -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->     
        </div>
        <!-- /modulLateral -->
    
    </form>
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
<!-- escriptori_seccions_ua -->
<div id="escriptori_seccions_ua">
    <h2>Gesti� de la relaci� entre les Seccions i les Unitats
        Administratives</h2>
    <div class="botonera dalt">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
    <!-- llistat -->
    <div class="escriptori_items_llistat">
        <!-- selector_seccions -->
        <div class="escriptori_selector_seccions">
            <h3>Selector de seccions</h3>
            <div class="escriptori_seccions_arbre"></div>
        </div>
        <!-- /selector_seccions -->
        <!-- selector_uas -->
        <!--
                        <div class="escriptori_selector_uas">
                            <h3>Selector d'Unitats Administratives</h3>
                            <div class="escriptori_ua_arbre"></div>
                        </div>
                        -->
        <!-- /selector_uas -->
    </div>
    <!-- /llistat -->
    <!-- seleccionats -->
    <div class="escriptori_items_seleccionats">
        <h3>Seccions i Unitats seleccionades</h3>
        <p class="botonera">
            <a href="javascript:;" class="btn finalitza important"><span><span>Finalitza</span>
            </span>
            </a>
        </p>
        <div class="dades">
            <p class="info">No hi ha cap secci�/UA.</p>
            <!--
                            <p class="info">Seleccionades <strong>5 seccions/UA</strong>.</p>
                            <ul>
                                <li>
                                    <div class="se_ua">
                                        <input type="hidden" value="1" class="seccio" />
                                        <input type="hidden" value="1" class="ua" />
                                        <span class="se_ua">
                                            <span class="seccio">(1) T�tol de la secci�</span>, amb la unitat <span class="ua">(1) T�tol de la unitat administrativa</span>
                                        </span>
                                        <a href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
                                    </div>
                                </li>
                            </ul>
                            -->
        </div>
    </div>
    <!-- seleccionats -->
    <div class="botonera baix">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
</div>
<!-- /escriptori_seccions_ua -->