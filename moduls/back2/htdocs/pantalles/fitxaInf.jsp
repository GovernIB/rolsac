<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <link href='<c:url value="/css/fitxes.css"/>' rel="stylesheet" type="text/css" media="screen" />    
    <link href='<c:url value="/css/modul_seccions_ua.css"/>' rel="stylesheet" type="text/css" media="screen" />
    <link href='<c:url value="/css/modul_ua_arbre.css"/>' rel="stylesheet" type="text/css" media="screen" />
    <link href='<c:url value="/css/modul_seccions_arbre.css"/>' rel="stylesheet" type="text/css" media="screen" />
    <link href='<c:url value="/css/modul_enllassos.css"/>' rel="stylesheet" type="text/css" media="screen" />
    <link href='<c:url value="/css/jquery-ui-timepicker-addon.css"/>' rel="stylesheet" type="text/css" media="screen" />
    
    <script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/fitxes.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui-timepicker-addon.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/modul_ua_arbre.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_seccions_arbre.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_seccions_ua.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_fetsVitals.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_enllassos.js'/>"></script>

    <script type="text/javascript">
    // accesos
    var pagLlistat = '<c:url value="/fitxainf/llistat.do" />';
    var pagDetall = '<c:url value="/fitxainf/pagDetall.do" />';
    var pagGuardar = '<c:url value="/fitxainf/guardar.do" />';
    var pagSeccions = '<c:url value="/fitxainf/seccions.do" />';
    var pagUnitats = '<c:url value="/fitxainf/unitats.do" />';
    var pagEsborrar = '<c:url value="/fitxainf/esborrarFitxa.do" />';

    // texts
    var txtEspere = "Espere un moment, si us plau.";
    var txtCarregant = "Carregant";
    var txtSi = "Sí";
    var txtNo = "No";
    var txtTrobats = "Trobades";
    var txtLlistaItem = "Fitxa";
    var txtLlistaItems = "Fitxes";
    var txtAdministrativa = "Administrativa";
    var txtAdministratives = "Administratives";
    var txtData = "Data";
    var txtPublicacio = "Publicació";
    var txtCaducitat = "Caducitat";
    var txtMostrem = "Mostrem de la ";
    var txtMostremAl = " a la ";
    var txtNoHiHa = "No hi ha";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenats = "ordenats";
    var txtAscendentment = "ascendentment";
    var txtDescendentment = "descendentment";
    var txtPer = "per";
    var txtEsborrarCorrecte = "La Fitxa s'ha esborrat correctament";
    // taula
    var pag_Res = 5;
    var txtNom = "Nom";
    var txtPare = "Pare";
    var txtCercant = "Cercant";
    var txtCercantLlistat = txtCercant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    // paginacio
    var txtTrobat = "S'ha trobat";
    var txtSeguents = "Següents";
    var txtAnteriors = "Anteriors";
    var txtInici = "Inici";
    var txtFinal = "Final";
    var txtPagines = "Pàgines";
    var txtCercantElements = txtCercant + " " + txtLlistaItems;
    var txtCercantLlistatAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
    var txtCercantLlistatSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
    var txtCercantAnteriors = txtCercantLlistatAnteriors;
    var txtCercantSeguents = txtCercantLlistatSeguents;
    
    // detall
    var txtCarregantDetall = txtCarregant + " detall de la "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "Nova " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "Detall de la " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "Està segur de voler eliminar aquesta " + txtLlistaItem.toLowerCase() + "?";
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
    var txtAdresa = "Adreça web";
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
    var txtMateria = "matèria";
    var txtMateries = "matèries";
    var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
    // modul fets vitals
    var txtFet = "fet vital";
    var txtFets = "fets vitals";
    var txtNoHiHaFets = txtNoHiHa + " " + txtFets;
    // modul seccio/ua
    var txtSeccioUA = "Secció/UA";
    var txtSeccionsUA = "Seccions/UA";
    var txtNoHiHaSeccioUA = "No hi ha cap " + txtSeccioUA.toLowerCase();
    var txtLaSeccio = "La secció";
    var txtAmbLaUnitat = "amb la unitat";
    // modul ua arbre
    var txtArrel = "Arrel";
    var txtNodesFills = "Nodes fills";
    var txtCarregantArrel = "Carregant node arrel de l'arbre. " + txtEspere;
    var txtCarregantNodes = txtCarregant + " nodes fills. " + txtEspere;
	// enllassos
	var txtEnllas = "Enllaç";
    var txtEnllassos = "Enllassos";
    var txtNoHiHaEnllassos = "No hi ha cap " + txtEnllas.toLowerCase();
    var txtNoHiHaEnllassosSeleccionats = "No hi ha " + txtEnllassos.toLowerCase() + " " + txtSeleccionats.toLowerCase();
    
    // error conexio
    var txtCalcularTotal = "Calcular el total";
    var txtAjaxError = "S'ha produït un error de conexió.";
    var txtIntenteho = "Intente-ho de nou en uns minuts.";
    var txtConexionIntentar = "Intentar conexió en";
    var txtSegon = "segon";
    var txtSegons = "segons";
    var txtConectar = "Conectar ara";
    var txtFuncions = "Les funcions de l'aplicació s'han deshabilitat";
    var txtFuncionsFins = "fins que no es restableixi la comunicació";
    
    // suggeriments
    var suggeriments = "";

    </script>
    
    
    <script type="text/javascript">
    //validacio
    var txtSeccUa = "Es necessari definir una relació Fitxa -Secció -  UA";
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
    var txtAnyMal = "<spring:message code='txt.any_mal'/>";
    var txtMesMal = "<spring:message code='txt.mes_mal'/>";
    var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
    var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";
    
    // dades formularis
    var FormulariDades = [
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_ca",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "si",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": "El camp 'Títol de la fitxa en l'idioma català' és obligatori",
                    "tipus": "El camp 'Títol de la fitxa en l'idioma català' no pot estar compost només de números"
                }
        },
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_es",
            "obligatori": "no",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "si",
                    "abreviat": "no"
                },
            "error":
                {
                    "tipus": "El camp 'Títol de la fitxa en l'idioma castellà' no pot estar compost només de números"
                }
        },
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_en",
            "obligatori": "no",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "si",
                    "abreviat": "no"
                },
            "error":
                {
                    "tipus": "El camp 'Títol de la fitxa en l'idioma anglés' no pot estar compost només de números"
                }
        },
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_de",
            "obligatori": "no",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "si",
                    "abreviat": "no"
                },
            "error":
                {
                    "tipus": "El camp 'Títol de la fitxa en l'idioma alemany' no pot estar compost només de números"
                }
        },
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_fr",
            "obligatori": "no",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "si",
                    "abreviat": "no"
                },
            "error":
                {
                    "tipus": "El camp 'Títol de la fitxa en l'idioma francés' no pot estar compost només de números"
                }
        },
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_estat",
            "obligatori": "si",
            "tipus": "numeric",
            "error":
                {
                    "obligatori": "El camp 'Estat' es obligatori"                   
                }
        } 
    ];

    var FormulariEnllassos = [
		{
		    "modo": "individual",
		    "etiqueta": "id",
		    "etiquetaValor": "enllas_titol_ca",
		    "obligatori": "si",
		    "tipus": "alfanumeric",
		    "caracters":
            {
                "maxim": 250,
                "mostrar": "si",
                "abreviat": "no"
            },
		    "error":
		        {
            		"obligatori": "El camp 'Títol de la fitxa en l'idioma català' és obligatori",
		            "tipus": "El camp 'Títol de la fitxa en l'idioma català' no pot estar compost només de números"                   
		        }
		}   
	];
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
            <li id="btnNuevaFicha" class="opcions nuevo"><a href="javascript:;" class="btn nou"><span><span>Crea  una nova fitxa</span></span></a></li>
        </c:if>
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
                                <label for="cerca_materia">Matèria</label>
                            </div>
                            <div class="control select">
                                <select id="cerca_materia" name="cerca_materia" class="materia">
                                    <option value="">Tria una opci&oacute;</option>
                                    <c:forEach items="${llistaMateries}" var="materia">
                                        <option value='<c:out value="${materia.id}" />'><c:out value="${materia.nom}" /></option>
                                    </c:forEach>
                                </select>               
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
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_url">URL Fórum</label>
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
                                <input id="cerca_fechaCaducidad" name="cerca_fechaCaducidad" type="text" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_fechaPublicacion">Data publicaci&oacute;</label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaPublicacion" name="cerca_fechaPublicacion" type="text" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_fechaActualizacion">Data actualitzaci&oacute;</label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaActualizacion" name="cerca_fechaActualizacion" type="text" readonly="readonly"/>
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
<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="false">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <h2>Detall de la fitxa</h2>
        <p>Recorde que les dades amb asterisc (<span class="obligatori">*</span>) són obligatòries.</p>            
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">
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
                                <li class="idioma"><a href="javascript:;" class="ca">Català</a></li>
                                <li class="idioma"><a href="javascript:;" class="es">Español</a></li>
                                <li class="idioma"><a href="javascript:;" class="en">English</a></li>
                                <li class="idioma"><a href="javascript:;" class="de">Deutsch</a></li>
                                <li class="idioma"><a href="javascript:;" class="fr">Français</a></li>
                                
                                <li class="traduix btnGenerico">
                                    <a href="javascript:;" class="btn traduix"><span><span>Traduïx</span></span></a>
                                </li>
                            </ul>
                            <div class="idiomes">
                                <!-- ca -->
                                <div class="idioma ca">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_ca">Títol de la fitxa</label>
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
                                                <label for="item_des_curta_ca">Descripció abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_ca" name="item_des_curta_ca" cols="70" rows="5" style="width: 100%" class="rich basic nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_ca">Descripció extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_ca" name="item_des_llarga_ca" cols="70" rows="10" style="width: 100%" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_ca">Adreça web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_ca" name="item_url_ca" type="text"
                                                    class="nou" />
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
                                                <label for="item_titol_es">Título de la ficha</label>
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
                                                <label for="item_des_curta_es">Descripción abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_es" name="item_des_curta_es" cols="70" rows="5" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_es">Descripción extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_es" name="item_des_llarga_es" cols="70" rows="10" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_es">Dirección web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_es" name="item_url_es" type="text"
                                                    class="nou" />
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
                                </div>
                                <!-- /en -->
                                <!-- de -->
                                <div class="idioma de">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_de">Títol de la fitxa</label>
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
                                                <label for="item_des_curta_de">Descripció abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_de" name="item_des_curta_de" cols="70" rows="5" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_de">Descripció extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_de" name="item_des_llarga_de" cols="70" rows="10" style="width: 100%" class="rich nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_de">Adreça web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_de" name="item_url_de" type="text"
                                                    class="nou" />
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
                                                <label for="item_titol_fr">Títol de la fitxa</label>
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
                                                <label for="item_des_curta_fr">Descripció abreviada</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_fr" name="item_des_curta_fr" cols="70" rows="5" style="width: 100%" class="rich basic nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_fr">Descripció extensa</label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_fr" name="item_des_llarga_fr" cols="70" rows="10" style="width: 100%" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_fr">Adreça web externa</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_fr" name="item_url_fr" type="text"
                                                    class="nou" />
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
                    <a class="modul amagat">Mostra</a>
                    <legend>Multimèdia</legend>
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
                                    <label for="item_youtube">Adreça video YouTube</label>
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
                                    <label for="item_forum">Enllaç fòrum / Línia de debat</label>
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
        <div class="modulLateral">
            <? if ($_SESSION['rolsac_rol'] != "RSC_OPERADOR") { ?>
            <!-- modul -->
            <div class="modul publicacio">
                <fieldset>
                    <legend>Publicació</legend>
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
                                    <label for="item_data_publicacio">Data publicació</label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacio" name="item_data_publicacio"                                        
                                        type="text" <%-- value="Immediat" --%> class="nou" readonly="readonly" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_caducitat">Data caducitat</label>
                                </div>
                                <div class="control">
                                    <input id="item_data_caducitat" name="item_data_caducitat"
                                        type="text" class="nou" readonly="readonly" />
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
                        <div class="modulDocuments multilang">
                            <ul class="idiomes">
                                <li class="ca seleccionat">ca</li>
                                <li class="es">es</li>
                                <li class="en">en</li>
                                <li class="de">de</li>
                                <li class="fr">fr</li>
                            </ul>
                            <div class="seleccionats">
                                <div class="ca seleccionat cajaIdioma">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="es cajaIdioma">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="en cajaIdioma">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="de cajaIdioma">
                                    <p class="info">No hi ha documents.</p>
                                </div>
                                <div class="fr cajaIdioma">
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
                    <legend>Matèries relacionades</legend>
                    <div class="modul_continguts mostrat">                    
                        <!-- modulMateries -->
                        <div class="modulMateries selectorChecks">
                            <div class="seleccionats">
                                <p class="info">No hi ha matèries.</p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona matèries</span></span></a>
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
                                        <a href="javascript:;" class="cancela"><span><span>Cancel·la</span></span></a>
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
									<c:forEach items="${llistaFetsVitals}" var="fetVital" varStatus="i">
                                        <c:choose>
                                            <c:when test="${(i.count) % 2 == 0}">
                                                <li class="par">
                                            </c:when>
                                            <c:otherwise>
                                               <li class="impar">
                                            </c:otherwise>
                                        </c:choose>                                     
                                          <label><span><c:out value="${fetVital.nom}" /></span><input type="checkbox" value="<c:out value='${fetVital.id}' />" /></label>
                                        </li>                                                                                                               
                                    </c:forEach>
                                </ul>
                                <div class="botonera">
                                    <div class="btnGenerico">
                                        <a class="btn finalitza" href="javascript:;"><span><span>Finalitza</span></span></a>
                                    </div>
                                    <div class="btnGenerico">
                                        <a href="javascript:;" class="cancela"><span><span>Cancel·la</span></span></a>
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
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend>
                        Relació: Secció - <abbr title="Unitat Administrativa">UA</abbr>
                    </legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulSeccionsUA -->
                        <div class="modulSeccionsUA">
                            <div class="seleccionats">
                                <p class="info">No hi ha seccions amb Unitat Administrativa.</p>
                                <div class="listaOrdenable"></div>
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
            <!-- modul -->
            <div class="modul">                     
                <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend>Enllaços relacionats</legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulEnllaços -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulEnllassos multilang">
                            <ul class="idiomes">
                            	<li class="introIdiomas">Idioma:</li>
                                <li class="ca">ca</li>
                                <li class="es">es</li>
                                <li class="en">en</li>
                                <li class="de">de</li>
                                <li class="fr">fr</li>
                            </ul>                            
                            <div class="seleccionats">
                                <%-- dsanchez: multiidioma --%>
                                <div class="seleccionat cajaIdioma ca">
                                    <p class="info">No hi ha enllaços</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="es cajaIdioma">
                                    <p class="info">No hi ha enllaços</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="en cajaIdioma">
                                    <p class="info">No hi ha enllaços</p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                <div class="de cajaIdioma">
                                    <p class="info">No hi ha enllaços</p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                <div class="fr cajaIdioma">
                                    <p class="info">No hi ha enllaços</p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona enllaços</span></span></a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulEdificis -->                                 
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
	<ul id="opcions">
		<li class="opcio actiu">Gestió de la relació entre les Seccions i les Unitats
        Administratives</li>
	</ul>
    <!-- llistat -->
    <div id="resultats" class="escriptori_items_llistat">
        <div class="escriptori_selector_seccions">
            <h3>Seccions</h3>
            <div class="escriptori_seccions_arbre"></div>
        </div>
        <div class="escriptori_selector_uas">
            <h3>Unitats Administratives</h3>
            <div class="escriptori_ua_arbre"></div>
        </div>
        <div id="cercador">
        	<div class="botonera">
				<div class="boton btnGenerico btnVolverDetalle">
					<a class="btn torna" href="javascript:;">
						<span><span>Torna al detall</span></span>
					</a>
				</div>
			</div>
		</div>
    </div>
    <!-- /llistat -->
    <!-- seleccionats -->
    
    <div class="modulLateral escriptori_items_seleccionats">
		<div class="modul">
			<div class="interior">
				<div class="seleccionats">
					<div class="seleccionat">
						<p class="info">No hi ha cap secció/UA.</p>
						<div class="listaOrdenable"></div>
					</div>
					<div class="botonera">
						<p class="botonera btnGenerico">
							<a id="btnInsertar" class="btn inserta important" href="javascript:;">
								<span><span>Inserta</span></span>
							</a>							
						</p>
						<p class="botonera btnGenerico">
							<a id="btnFinalizar" class="btn finalitza important" href="javascript:;">
								<span><span>Finalitza</span></span>
							</a>
						</p>
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /escriptori_seccions_ua -->
<!-- escriptori_enllassos -->
<div id="escriptori_enllassos">
   <ul id="opcions">
        <li class="opcio actiu">Administració de enllaços</li>                                 
    </ul>    
    <form id="formEnllassos" class="grupoModulosFormulario">
    	<input id="id_enllas_actual" type="hidden"/>
	    <div class="modul">
	        <fieldset>
		        <!-- fila -->
		        <div class="fila">
		            <p class="introIdiomas">Idioma:</p>
		            <ul class="idiomes">
		                <li class="idioma"><a href="javascript:;" class="ca">Català</a></li>
		                <li class="idioma"><a href="javascript:;" class="es">Español</a></li>
		                <li class="idioma"><a href="javascript:;" class="en">English</a></li>
		                <li class="idioma"><a href="javascript:;" class="de">Deutsch</a></li>
		                <li class="idioma"><a href="javascript:;" class="fr">Français</a></li>                                
		            </ul>
		            <div class="idiomes">
		                <!-- ca -->
		                <div class="idioma ca">
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_titol_ca">Títol</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_titol_ca" name="item_titol_ca" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_url_ca">Adreça web</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_url_ca" name="item_url_ca" type="text"
		                                    class="nou" />
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
		                                <label for="enllas_titol_es">Título</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_titol_es" name="item_titol_es" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>                                    
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_url_es">Dirección web</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_url_es" name="item_url_es" type="text"
		                                    class="nou" />
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
		                                <label for="enllas_titol_en">Headline</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_titol_en" name="item_titol_en" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>                                    
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_url_en">Web adress</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_url_en" name="item_url_en" type="text"
		                                    class="nou" />
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
		                                <label for="enllas_titol_de">Títol</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_titol_de" name="item_titol_de" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>                                    
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_url_de">Adreça web externa</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_url_de" name="item_url_de" type="text"
		                                    class="nou" />
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
		                                <label for="enllas_titol_fr">Títol</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_titol_fr" name="item_titol_fr" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>                                    
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_url_fr">Adreça web</label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_url_fr" name="item_url_fr" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <!-- /fr -->
		            </div>
		        </div>
		        <div id="cercador">
			        <div class="botonera">
						<div class="boton btnGenerico">
							<a id="btnLimpiarCampos" class="btn borrar" href="javascript:;">
								<span><span>Borrar</span></span>
							</a>
						</div>
						<div class="boton btnGenerico">
							<a id="btnInsertarEnllas" class="btn inserta important" href="javascript:;">
								<span><span>Inserta</span></span>
							</a>							
						</div>
						<div id="btnVolverDetalle" class="boton btnGenerico">
							<a class="btn torna" href="javascript:;">
								<span><span>Torna al detall</span></span>
							</a>
						</div>
					</div>
				</div>
		        <!-- /fila -->
			</fieldset>
		</div>
	</form>
    <div class="modulLateral escriptori_items_seleccionats">
        <div class="modul">
            <div class="interior multilang">            	
                <ul class="idiomes">
                	<li class="introIdiomas">Idioma:</li>
                    <li class="ca">ca</li>
                    <li class="es">es</li>
                    <li class="en">en</li>
                    <li class="de">de</li>
                    <li class="fr">fr</li>
                </ul>
                <div class="seleccionats">
                    <div class="seleccionat cajaIdioma ca">
                        <p class="info">No hi ha enllaços</p>
                        <div class="listaOrdenable"></div>
                    </div>
                    <div class="es cajaIdioma">
                        <p class="info">No hi ha enllaços</p>
                        <div class="listaOrdenable"></div>
                    </div>
                    <div class="en cajaIdioma">
                        <p class="info">No hi ha enllaços</p>
                        <div class="listaOrdenable"></div>
                    </div>                                
                    <div class="de cajaIdioma">
                        <p class="info">No hi ha enllaços</p>
                        <div class="listaOrdenable"></div>
                    </div>                                
                    <div class="fr cajaIdioma">
                        <p class="info">No hi ha enllaços</p>
                        <div class="listaOrdenable"></div>                      
                    </div>                    
                    <p class="botonera btnGenerico">
                        <a id="btnFinalitzar" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                    </p>                                    
                </div>                                  
            </div>
        </div>
    </div>
    <!-- seleccionats -->
</div>                           

<!-- /escriptori_enllassos -->