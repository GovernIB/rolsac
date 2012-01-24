<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/cataleg_procediments.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_documents.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_normativa.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_tramits.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/seekAttention.min.jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.maskedinput-1.2.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/autoresize.jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/procediments.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_normativa.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_tramits.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/catalegProcediments/llistat.do" />';
    var pagDetall = '<c:url value="/catalegProcediments/pagDetall.do" />';
    var pagGuardar = '<c:url value="/catalegProcediments/guardar.do" />';
    var pagEsborrar = '<c:url value="/catalegProcediments/esborrarProcediment.do" />';
    var seccioNormatives = '<c:url value="/catalegProcediments/cercarNormatives.do" />';
    var pagGuardarDoc = '<c:url value="/documents/guardarDocument.do" />';
    var pagCarregarDoc = '<c:url value="/documents/carregarDocument.do" />';
    
    //texts
    var txtEsborrarCorrecte = "<spring:message code='txt.procediment_esborrat_correcte'/>";
    var txtEsborrarIncorrecte = "";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtLlistaItem = "<spring:message code='txt.procediment'/>";
    var txtLlistaItems = "<spring:message code='txt.procediments'/>";
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
    var txtCaducitat = "<spring:message code='txt.caducitat'/>";
    var txtFamilia = "Familia";
    var txtFechaActualizacion = "Data d\'actualitzaci�";
    
    var txtHiHa = "<spring:message code='txt.hi_ha'/>";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
    
    var txtDocument = "<spring:message code='txt.document'/>";
    var txtDocuments = "<spring:message code='txt.documents'/>";
    var txtNoHiHaDocuments = txtNoHiHa + " " + txtDocuments;
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtNoHiHaDocumentsSeleccionats = txtNoHiHaDocuments + " " + txtSeleccionats.toLowerCase();
    //var txtTramitNouProcediment = "Per a gestionar els tr�mits has de guardar el procediment";
    var txtNoHiHaTramitsSeleccionats = "No hay tramites seleccionados";
    
    var txtMateria = "<spring:message code='txt.materia'/>";
    var txtMateries = "<spring:message code='txt.materies'/>";
    var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
    var txtNoHiHaMateriesSeleccionades = "No hi ha materies seleccionades";
    
    var txtTramit = "Tramit";
    var txtTramits = "Tramits";
    
    var txtNormativa = "<spring:message code='quadreControl.normativa'/>";
    var txtNormatives = "<spring:message code='quadreControl.normatives'/>";
    var txtNoHiHaNormativa = txtNoHiHa + " " + txtNormativa.toLowerCase();
    var txtNoHiHaNormatives = txtNoHiHa + " " + txtNormatives.toLowerCase();
    var txtSeleccionada =  "<spring:message code='txt.seleccionada'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaNormativaSeleccionada = txtNoHiHa + " " + txtNormativa.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaNormativesSeleccionades = txtNoHiHa + " " + txtNormatives.toLowerCase() + " " + txtSeleccionades.toLowerCase();
    var txtNumero = "<spring:message code='camp.numero'/>";
    
    var txtImmediat = "<spring:message code='txt.immediat'/>";
    var txtPublicacio = "<spring:message code='boto.publicacio'/>";
    
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
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript">
<!--
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
    var txtAnyMal = "<spring:message code='txt.any_mal'/>";
    var txtMesMal = "<spring:message code='txt.mes_mal'/>";
    var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
    var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";
    var txtNombreObligatorio = "<spring:message code='personal.formulari.nom.obligatori'/>";    
    var txtNombreNoSoloNumeros = "<spring:message code='personal.formulari.nom.no_nomes_numeros'/>";

    // dades formularios
    var FormulariDades = [
        // Nombre (Catalan)
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
                    "obligatori": txtNombreObligatorio,
                    "tipus": txtNombreNoSoloNumeros
                }
        },
        
        // Estado
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_estat",
            "obligatori": "si",
            "tipus": "numeric",
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.estat.obligatori'/>"
                }
        },
        
        // Forma de iniciaci�n
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_iniciacio",
            "obligatori": "si",
            "tipus": "numeric",         
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.formaIniciacio.obligatori'/>"
                }
        },
        
        // Familia
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_familia",
            "obligatori": "si",
            "tipus": "numeric",         
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.familia.obligatori'/>"
                }
        },

        // Plazo m�ximo para resoluci�n (Catal�n)
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_resolucio_ca",
            "obligatori": "si",
            "tipus": "alfanumeric",
            /*"caracters":
                {
                    "mostrar": "no",
                    "abreviat": "no"
                },*/
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.terminiMaximResolucio.obligatori'/>",
                    "tipus": "<spring:message code='proc.formulari.error.terminiMaximResolucio.no_nomes_numeros'/>"
                }
        },
        
        // Plazo m�ximo para la notificaci�n (Catal�n)
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_notificacio_ca",
            "obligatori": "si",
            "tipus": "alfanumeric",
            /*"caracters":
                {
                    "mostrar": "no",
                    "abreviat": "no"
                },*/
            "error":
                {
                	"obligatori": "<spring:message code='proc.formulari.error.terminiMaximNotificacio.obligatori'/>",
                    "tipus": "<spring:message code='proc.formulari.error.terminiMaximNotificacio.no_nomes_numeros'/>"
                }
        },
        
        // Silencio administrativo
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_silenci_ca",
            "obligatori": "si",
            "tipus": "alfanumeric",
            /*"caracters":
                {
                    "mostrar": "no",
                    "abreviat": "no"
                },*/
            "error":
                {
                	"obligatori": "<spring:message code='proc.formulari.error.silenciAdministratiu.obligatori'/>",
                    "tipus": "<spring:message code='proc.formulari.error.silenciAdministratiu.no_nomes_numeros'/>"
                }
        }
    ];
    
    var FormulariTramits = [
		{		    
		}   
    ];
-->
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
            <li id="btnNuevaFicha" class="opcions nuevo"><a href="javascript:;" class="btn nou"><span><span><spring:message code='proc.crea.nou'/></span></span></a></li>
        </c:if>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='proc.executant'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" /> <input
                type="hidden" value="DESC" class="ordreTipus" /> <input
                type="hidden" value="nom" class="ordreCamp" />
        </div>
        <div class="resultats C">
            <!-- cercador -->
            <div id="cercador">
                <div id="cercador_contingut">
                    <div class="opcionesBusqueda">
                        <h2>OPCIONS DE CERCA</h2>
                        <div class="fila">
                            <div class="element checkbox">                                
                                <label for="cerca_uaFilles"><spring:message code='camp.inclouUAFilles'/></label>                                                                
                                <input id="cerca_uaFilles" type="checkbox" name="cerca_uaFilles" value="1" />
                                <%--<select id="cerca_uaFilles" name="cerca_uaFilles" class="t8">
                                    <option value="0" selected="selected"><spring:message code='txt.no'/></option>
                                    <option value="1"><spring:message code='txt.si'/></option>
                                </select>--%>                                
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element checkbox">                                
                                <label for="cerca_uaMeves"><spring:message code='camp.cerca_totes_unitats'/></label>                                
                                <input id="cerca_uaMeves" name="cerca_uaMeves" type="checkbox" value="1"/>
                            </div>
                        </div>
                        <div class="element>                        
                            <label for="visibilitat">Visibilitat</label>                            
                            <select id="visibilitat" name="visibilitat">
                                <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                
                            </select>
                        </div>                        
                    </div>
                    <div class="busquedaBasica">
                        <h2><spring:message code='tab.cercador'/></h2>
                        <div class="fila">
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_codi"><spring:message code='txt.codi'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_codi" name="cerca_codi" type="text" />
                                </div>
                            </div>
                            <div class="element t75">
                                <div class="etiqueta">
                                    <label for="cerca_textes"><spring:message code='camp.textes'/></label>
                                </div>
                                <div class="control">                                    
                                    <input id="cerca_textes" name="cerca_textes" type="text"/>
                                </div>                                
                            </div>
                        </div>
                    </div>                    
                    <div class="busquedaAvanzada">
                        <h2>CERCADOR AVAN�AT</h2>
                        <div class="fila">                                                                                    
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_tramit"><spring:message code='camp.identificadorTramit'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_tramit" name="cerca_tramit" type="text"/>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_estat"><spring:message code='camp.estat'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_estat" name="cerca_estat" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="1"><spring:message code='txt.validacio.publica'/></option>
                                        <option value="2"><spring:message code='txt.validacio.interna'/></option>
                                        <option value="3"><spring:message code='txt.validacio.reserva'/></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_materia">Mat�ria</label>
                                </div>
                                <div class="control">
                                    <select id="cerca_materia" name="cerca_materia" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                        
                                    </select>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_fet_vital">Fet Vital</label>
                                </div>
                                <div class="control">
                                    <select id="cerca_fet_vital" name="cerca_fet_vital" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                        
                                    </select>
                                </div>
                            </div>
                            <div class="element t50">
                                <div class="etiqueta">
                                    <label for="cerca_familia"><spring:message code='camp.familia'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_familia" name="cerca_familia">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${families}" var="familia">
                                            <option value="<c:out value="${familia.id}"/>"><c:out value="${familia.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_iniciacio"><spring:message code='camp.iniciacio'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_iniciacio" name="cerca_iniciacio">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${iniciacions}" var="iniciacio">
                                            <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_indicador"><spring:message code='camp.fiViaAdministrativa'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_indicador" name="cerca_indicador" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="0">No</option>
                                        <option value="1">S�</option>
                                    </select>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_finestreta"><spring:message code='camp.finestraUnica'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_finestreta" name="cerca_finestreta" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="0">No</option>
                                        <option value="1">S�</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                        <%--
                        <div class="fila">                                                                                
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_versio"><spring:message code='camp.versioTramit'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_versio" name="cerca_versio" type="text"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_url"><spring:message code='camp.urlTramitExtern'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_url" name="cerca_url" type="text"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_taxa"><spring:message code='camp.taxa'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_taxa" name="cerca_taxa" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="0">No</option>
                                        <option value="1">S�</option>
                                    </select>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_responsable"><spring:message code='unitatadm.formulari.responsable'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_responsable" name="cerca_responsable" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_fechaCaducidad"><spring:message code='camp.dataCaducitat'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_fechaCaducidad" name="cerca_fechaCaducidad" type="text" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_fechaPublicacion"><spring:message code='camp.dataPublicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_fechaPublicacion" name="cerca_fechaPublicacion" type="text" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_fechaActualizacion"><spring:message code='camp.dataActualitzacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_fechaActualizacion" name="cerca_fechaActualizacion" type="text" readonly="readonly"/>
                                </div>
                            </div>                            
                        </div>
                        --%>                                                
                        <div class="fila">                            
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
    <div id="escriptori_detall" class="escriptori_detall">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <h2><spring:message code='txt.detallProcediment'/></h2>
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">                
        
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <!--<a class="modul mostrat"><spring:message code='txt.amaga'/></a>-->
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
                                                <label for="item_codigo_pro_ca">Codi</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_pro_ca" name="item_codigo_pro_ca" type="text" value=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_ca"><spring:message code='camp.nomProcediment'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_ca" name="item_nom_ca" type="text" class="nou" />
                                            </div>
                                        </div>
                                        <div id="caja_item_clave_primaria" class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria">Clave primaria</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria" name="item_clave_primaria" type="text" class="nou" readonly="readonly" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_ca"><spring:message code='camp.objecte'/></label>
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
                                                <label for="item_destinataris_ca"><spring:message code='camp.destinataris'/></label>
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
                                                <label for="item_requisits_ca"><spring:message code='camp.requisits'/></label>
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
                                                <label for="item_resolucio_ca"><spring:message code='camp.terminiMaximResolucio'/></label>
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
                                                <label for="item_notificacio_ca"><spring:message code='camp.terminiMaximNotificacio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_ca" name="item_notificacio_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element organoCompetente">
                                            <div class="etiqueta">
                                                <label for="item_organ"><spring:message code='camp.organCompetent'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_organ_id" name="item_organ_id" type="hidden" />
                                                <div class="campo">
                                                    <input id="item_organ" name="item_organ" type="text" class="nou" readonly="true" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
                                                        <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_organ_id', 'item_organ');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
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
                                    </div>
                                    
                                    <div class="fila">                                        
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_iniciacio"><spring:message code='camp.formaIniciacio'/></label>
                                            </div>
                                            <div class="control select">
                                                <select id="item_iniciacio" name="item_iniciacio" class="nou">
                                                    <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                                    <c:forEach items="${iniciacions}" var="iniciacio">
                                                        <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_ca"><spring:message code='camp.silenciAdministratiu'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_ca" name="item_silenci_ca"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                                                        
                                    <div class="fila">
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_fi_vida_administrativa" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_fi_vida_administrativa"><spring:message code='camp.fiViaAdministrativa'/></label>
                                            </div>                                            
                                        </div>
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_taxa" name="item_taxa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_taxa"><spring:message code='camp.taxa'/></label>
                                            </div>                                            
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_ca"><spring:message code='camp.observacions'/></label>
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
                                        <div class="element t50p">
                                            <div class="etiqueta">                                            
                                                <label for="item_codigo_pro_es">Codi</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_pro_es" name="item_codigo_pro_es" type="text" value=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_es"><spring:message code='camp.nomProcediment'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_es" name="item_nom_es" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_es">Clave primaria</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_es" name="item_clave_primaria_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_es"><spring:message code='camp.objecte'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_es" name="item_objecte_es"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_es"><spring:message code='camp.destinataris'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_es"
                                                    name="item_destinataris_es" cols="50" rows="2"
                                                    class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_es"><spring:message code='camp.requisits'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_es" name="item_requisits_es"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_es"><spring:message code='camp.terminiMaximResolucio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_es" name="item_resolucio_es"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_es"><spring:message code='camp.terminiMaximNotificacio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_es" name="item_notificacio_es"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element organoCompetente">
                                            <div class="etiqueta">
                                                <label for="item_organ_es"><spring:message code='camp.organCompetent'/></label>
                                            </div>
                                            <div class="control">                                                
                                                <div class="campo">
                                                    <input id="item_organ_es" name="item_organ_es" type="text" class="nou" readonly="true" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
                                                        <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_organ_id', 'item_organ_es');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                        </a>
                                                    </div>
                                                    <div class="boton btnGenerico">                                    
                                                        <a href="javascript:EliminaArbreUA('item_organ_es', 'item_organ_id');" class="btn borrar">
                                                            <span><span><spring:message code='boto.borrar'/></span></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>                                            
                                        </div>
                                    </div>
                                    <div class="fila">                                        
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_iniciacio_es"><spring:message code='camp.formaIniciacio'/></label>
                                            </div>
                                            <div class="control select">
                                                <select id="item_iniciacio_es" name="item_iniciacio_es" class="nou">
                                                    <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                                    <c:forEach items="${iniciacions}" var="iniciacio">
                                                        <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_es"><spring:message code='camp.silenciAdministratiu'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_es" name="item_silenci_es"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_fi_vida_administrativa_es" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_fi_vida_administrativa_es"><spring:message code='camp.fiViaAdministrativa'/></label>
                                            </div>                                            
                                        </div>
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_taxa_es" name="item_taxa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_taxa_es"><spring:message code='camp.taxa'/></label>
                                            </div>                                            
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_es"><spring:message code='camp.observacions'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_es"
                                                    name="item_observacions_es" cols="50" rows="2"
                                                    class="nou"></textarea>
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
                                                <label for="item_codigo_pro_en">Codi</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_pro_en" name="item_codigo_pro_en" type="text" value=""/>
                                            </div>
                                        </div>
                                    </div>  
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_en"><spring:message code='camp.nomProcediment'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_en" name="item_nom_en" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_en">Clave primaria</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_en" name="item_clave_primaria_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_en"><spring:message code='camp.objecte'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_en" name="item_objecte_en"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_en"><spring:message code='camp.destinataris'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_en"
                                                    name="item_destinataris_en" cols="50" rows="2"
                                                    class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_en"><spring:message code='camp.requisits'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_en" name="item_requisits_en"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_en"><spring:message code='camp.terminiMaximResolucio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_en" name="item_resolucio_en"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_en"><spring:message code='camp.terminiMaximNotificacio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_en" name="item_notificacio_en"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element organoCompetente">
                                            <div class="etiqueta">
                                                <label for="item_organ_en"><spring:message code='camp.organCompetent'/></label>
                                            </div>
                                            <div class="control">                                                
                                                <div class="campo">
                                                    <input id="item_organ_en" name="item_organ_es" type="text" class="nou" readonly="true" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
                                                        <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_organ_id', 'item_organ_en');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                        </a>
                                                    </div>
                                                    <div class="boton btnGenerico">                                    
                                                        <a href="javascript:EliminaArbreUA('item_organ_en', 'item_organ_id');" class="btn borrar">
                                                            <span><span><spring:message code='boto.borrar'/></span></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>                                            
                                        </div>
                                    </div>
                                    <div class="fila">                                        
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_iniciacio_en"><spring:message code='camp.formaIniciacio'/></label>
                                            </div>
                                            <div class="control select">
                                                <select id="item_iniciacio_en" name="item_iniciacio_en" class="nou">
                                                    <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                                    <c:forEach items="${iniciacions}" var="iniciacio">
                                                        <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_en"><spring:message code='camp.silenciAdministratiu'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_en" name="item_silenci_en"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_fi_vida_administrativa_en" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_fi_vida_administrativa_en"><spring:message code='camp.fiViaAdministrativa'/></label>
                                            </div>                                            
                                        </div>
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_taxa_en" name="item_taxa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_taxa_en"><spring:message code='camp.taxa'/></label>
                                            </div>                                            
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_en"><spring:message code='camp.observacions'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_en"
                                                    name="item_observacions_en" cols="50" rows="2"
                                                    class="nou"></textarea>
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
                                                <label for="item_codigo_pro_de">Codi</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_pro_de" name="item_codigo_pro_de" type="text" value=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_de"><spring:message code='camp.nomProcediment'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_de" name="item_nom_de" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_de">Clave primaria</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_de" name="item_clave_primaria_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_de"><spring:message code='camp.objecte'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_de" name="item_objecte_de"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_de"><spring:message code='camp.destinataris'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_de"
                                                    name="item_destinataris_de" cols="50" rows="2"
                                                    class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_de"><spring:message code='camp.requisits'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_de" name="item_requisits_de"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_de"><spring:message code='camp.terminiMaximResolucio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_de" name="item_resolucio_de"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_de"><spring:message code='camp.terminiMaximNotificacio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_de" name="item_notificacio_de"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element organoCompetente">
                                            <div class="etiqueta">
                                                <label for="item_organ_de"><spring:message code='camp.organCompetent'/></label>
                                            </div>
                                            <div class="control">                                                
                                                <div class="campo">
                                                    <input id="item_organ_de" name="item_organ_es" type="text" class="nou" readonly="true" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
                                                        <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_organ_id', 'item_organ_de');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                        </a>
                                                    </div>
                                                    <div class="boton btnGenerico">                                    
                                                        <a href="javascript:EliminaArbreUA('item_organ_de', 'item_organ_id');" class="btn borrar">
                                                            <span><span><spring:message code='boto.borrar'/></span></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>                                            
                                        </div>
                                    </div>
                                    <div class="fila">                                        
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_iniciacio_de"><spring:message code='camp.formaIniciacio'/></label>
                                            </div>
                                            <div class="control select">
                                                <select id="item_iniciacio_de" name="item_iniciacio_de" class="nou">
                                                    <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                                    <c:forEach items="${iniciacions}" var="iniciacio">
                                                        <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_de"><spring:message code='camp.silenciAdministratiu'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_de" name="item_silenci_de"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="fila">
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_fi_vida_administrativa_de" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_fi_vida_administrativa_de"><spring:message code='camp.fiViaAdministrativa'/></label>
                                            </div>                                            
                                        </div>
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_taxa_de" name="item_taxa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_taxa_de"><spring:message code='camp.taxa'/></label>
                                            </div>                                            
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_de"><spring:message code='camp.observacions'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_de"
                                                    name="item_observacions_de" cols="50" rows="2"
                                                    class="nou"></textarea>
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
                                                <label for="item_codigo_pro_fr">Codi</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_pro_fr" name="item_codigo_pro_fr" type="text" value=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_fr"><spring:message code='camp.nomProcediment'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_fr" name="item_nom_fr" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_fr">Clave primaria</label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_fr" name="item_clave_primaria_fr" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_fr"><spring:message code='camp.objecte'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_fr" name="item_objecte_fr"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_fr"><spring:message code='camp.destinataris'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_fr"
                                                    name="item_destinataris_fr" cols="50" rows="2"
                                                    class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisits_fr"><spring:message code='camp.requisits'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisits_fr" name="item_requisits_fr"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_fr"><spring:message code='camp.terminiMaximResolucio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_fr" name="item_resolucio_fr"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_fr"><spring:message code='camp.terminiMaximNotificacio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_fr" name="item_notificacio_fr"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element organoCompetente">
                                            <div class="etiqueta">
                                                <label for="item_organ_fr"><spring:message code='camp.organCompetent'/></label>
                                            </div>
                                            <div class="control">                                                
                                                <div class="campo">
                                                    <input id="item_organ_fr" name="item_organ_fr" type="text" class="nou" readonly="true" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
                                                        <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_organ_id', 'item_organ_fr');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                        </a>
                                                    </div>
                                                    <div class="boton btnGenerico">                                    
                                                        <a href="javascript:EliminaArbreUA('item_organ_fr', 'item_organ_id');" class="btn borrar">
                                                            <span><span><spring:message code='boto.borrar'/></span></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>                                            
                                        </div>
                                    </div>
                                    <div class="fila">                                        
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_iniciacio_fr"><spring:message code='camp.formaIniciacio'/></label>
                                            </div>
                                            <div class="control select">
                                                <select id="item_iniciacio_fr" name="item_iniciacio_fr" class="nou">
                                                    <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                                    <c:forEach items="${iniciacions}" var="iniciacio">
                                                        <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_silenci_fr"><spring:message code='camp.silenciAdministratiu'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_fr" name="item_silenci_fr"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_fi_vida_administrativa_fr" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_fi_vida_administrativa_fr"><spring:message code='camp.fiViaAdministrativa'/></label>
                                            </div>                                            
                                        </div>
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_taxa_fr" name="item_taxa" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_taxa_fr"><spring:message code='camp.taxa'/></label>
                                            </div>                                            
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_fr"><spring:message code='camp.observacions'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_fr"
                                                    name="item_observacions_fr" cols="50" rows="2"
                                                    class="nou"></textarea>
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
                    <legend>CANALS DE PRESENTACI�</legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_presentacio">Presentaci�</label>
                                </div>
                                <div class="control">
                                    <input id="item_presentacio" name="item_presentacio" type="text" class="nou" />
                                </div>
                            </div>                            
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_lloc">Lloc</label>
                                </div>
                                <div class="control">
                                    <input id="item_lloc" name="item_lloc" type="text" class="nou" />
                                </div>
                            </div>                            
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_url"><spring:message code='camp.urlTramitExtern'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_url" name="item_url" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_tramite"><spring:message code='camp.idTramit'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_tramite" name="item_tramite" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_version"><spring:message code='camp.versioTramit'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_version" name="item_version" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend>INFORMADORS</legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_responsable"><spring:message code='unitatadm.formulari.responsable'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_responsable" name="item_responsable" type="text" class="nou" />
                                </div>
                            </div>                            
                        </div>                                               
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_notes"><spring:message code='camp.notesInformadors'/></label>
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
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend>ESTAD�STIQUES</legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <img src="/sacback2/quadreControl/grafica.do?tipoOperacion=1&id=1" width="728px" />
                        </div>
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul auditorias">                
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend>AUDITORIES</legend>
                <div class="modul_continguts mostrat">
                    <table>
                        <thead>
                            <th class="usuario"><div>USUARIO</div></th>
                            <th class="fecha"><div>FECHA</div></th>
                            <th class="operacion"><div>OPERACION</div></th>
                        </thead>                    
                        <tbody>
                            <tr>
                                <td class="usuario"><div>rsanz</div></td>
                                <td class="fecha"><div>16/01/2012</div></td>
                                <td class="operacion"><div>Modificat</div></td>
                            </tr>
                            <tr>
                                <td class="usuario"><div>jfernandez</div></td>
                                <td class="fecha"><div>16/01/2012</div></td>
                                <td class="operacion"><div>Modificat</div></td>
                            </tr>
                            <tr>
                                <td class="usuario"><div>flopez</div></td>
                                <td class="fecha"><div>16/01/2012</div></td>
                                <td class="operacion"><div>Insertat</div></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <%--
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='unitatadm.formulari.responsable'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_codi"><spring:message code='camp.codi'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_codi" name="item_codi" type="text" class="nou" />
                                </div>
                            </div>
                            <!--<div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_iniciacio"><spring:message code='camp.formaIniciacio'/></label>
                                </div>
                                <div class="control select">
                                    <select id="item_iniciacio" name="item_iniciacio" class="nou">
                                        <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                        <c:forEach items="${iniciacions}" var="iniciacio">
                                            <option value="<c:out value="${iniciacio.id}"/>"><c:out value="${iniciacio.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>-->
                        </div>
                        <div class="fila">
                            <!--<div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_url"><spring:message code='camp.urlTramitExtern'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_url" name="item_url" type="text" class="nou" />
                                </div>
                            </div>-->
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_familia"><spring:message code='camp.familia'/></label>
                                </div>
                                <div class="control select">
                                    <select id="item_familia" name="item_familia" class="nou">
                                        <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                        <c:forEach items="${families}" var="familia">
                                            <option value="<c:out value="${familia.id}"/>"><c:out value="${familia.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!--<div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_tramite"><spring:message code='camp.idTramit'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_tramite" name="item_tramite" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_version"><spring:message code='camp.versioTramit'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_version" name="item_version" type="text" class="nou" />
                                </div>
                            </div>
                        </div>-->
                        <div class="fila">
                            <!--<div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_organ"><spring:message code='camp.organCompetent'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_organ" name="item_organ" type="text" class="nou" readonly="true" />
                                    <input id="item_organ_id" name="item_organ_id" type="hidden" />
                                </div>
                            </div>-->
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_responsable"><spring:message code='unitatadm.formulari.responsable'/></label>
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
                                        <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_organ_id', 'item_organ');" class="btn consulta">                                           
                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
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
                                    <label for="item_finestreta_unica"><spring:message code='camp.finestretaUnicaDirectiva'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_finestreta_unica" name="item_finestreta_unica" type="checkbox" class="nou" />
                                </div>
                            </div>
                        </div>
                        <!--<div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_fi_vida_administrativa"><spring:message code='camp.fiViaAdministrativa'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_fi_vida_administrativa" name="item_fi_vida_administrativa" type="checkbox" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_taxa"><spring:message code='camp.taxa'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_taxa" name="item_taxa" type="checkbox" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_notes"><spring:message code='camp.notesInformadors'/></label>
                                </div>
                                <div class="control">
                                    <textarea id="item_notes" name="item_notes" cols="50" rows="2"
                                        class="nou"></textarea>
                                </div>
                            </div>
                        </div>-->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->                        
            --%>
            
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
                        <!-- fila -->
                        <div class="fila">
                            <div class="element left">
                                <div class="etiqueta">
                                    <label for="item_estat"><spring:message code='camp.estat'/></label>
                                </div>
                                <div class="control">
                                    <select id="item_estat" name="item_estat">
                                        <c:set var="rolSuper"><rol:userIsSuper/></c:set>
                                        <c:choose>
                                           <c:when test="${rolSuper}" >
                                               <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                               <option value="1" selected="selected"><spring:message code='txt.validacio.publica'/></option>
                                               <option value="2"><spring:message code='txt.validacio.interna'/></option>
                                               <option value="3"><spring:message code='txt.validacio.reserva'/></option>                                                                                   
                                           </c:when>
                                           <c:otherwise>
                                               <option value="2" selected="selected"><spring:message code='txt.validacio.interna'/></option>
                                           </c:otherwise>
                                       </c:choose>
                                    </select>
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_actualitzacio"><spring:message code='camp.dataActualitzacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_actualitzacio" name="item_data_actualitzacio" type="text" class="nou" readonly="readonly" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element left">
                                <div class="etiqueta">
                                    <label for="item_data_publicacio"><spring:message code='camp.dataPublicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacio" name="item_data_publicacio" type="text" class="nou" readonly="readonly" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_caducitat"><spring:message code='camp.dataCaducitat'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_caducitat" name="item_data_caducitat" type="text" class="nou" readonly="readonly" />
                                </div>
                            </div>
                        </div>                        
                        <!-- /fila -->
                        <!-- botonera dalt -->
                        <div class="botonera dalt">
                          <ul>
                              <li class="btnVolver impar">
                                  <a id="btnVolver" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
                              </li>
                              <li class="btnPrevisualizar par">
                                  <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span><spring:message code='boto.previsualitza'/></span></span></a>
                              </li>
                          </ul>
                        </div>
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->            
            <div id="modul_tramits" class="modul destacado">                               
                <div class="dec1"></div>
                <div class="dec2"></div>
                
                <div class="interior">                    
                    <fieldset>                                  
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                        <legend>TR�MITS</legend>                               
                        <div class="modul_continguts mostrat">                                  
                            <!-- modulNormatives -->
                            <div class="modulTramits">
                                <div class="seleccionats">                                
                                    <div class="seleccionat">
                                        <p class="info">No hay tramites seleccionados</p>
                                        <div class="listaOrdenable"></div>
                                    </div>
                                    <div class="btnGenerico">
                                        <a class="btn gestiona" href="javascript:;"><span><span>Gestiona tr�mites</span></span></a>
                                    </div>
                                </div>                                  
                            </div>
                            <!-- /modulNormatives -->                                 
                        </div>                              
                    </fieldset>                     
                </div>
            </div>            
            <!-- /modul -->  
            
            
            <div class="modul">
                <fieldset>                    
                    <legend><spring:message code='camp.familia'/></legend>                               
                    <div class="element">                        
                        <div class="control select">
                            <select id="item_familia" name="item_familia" class="nou">
                                <option value="" selected="selected"><spring:message code='camp.cap'/></option>
                                <c:forEach items="${families}" var="familia">
                                    <option value="<c:out value="${familia.id}"/>"><c:out value="${familia.nom}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </fieldset>
            </div>
            
            <!-- modul -->
            <div class="modul" id="modul_documents">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='document.documentsRelacionats'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulDocuments -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulDocuments multilang">                            
                            <ul class="idiomes">
                                <li class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</li>
                                <li class="ca seleccionat"><spring:message code='txt.idioma.ca_abr'/></li>
                                <li class="es"><spring:message code='txt.idioma.es_abr'/></li>
                                <li class="en"><spring:message code='txt.idioma.en_abr'/></li>
                                <li class="de"><spring:message code='txt.idioma.de_abr'/></li>
                                <li class="fr"><spring:message code='txt.idioma.fr_abr'/></li>
                            </ul>
                            
                            <div class="seleccionats">
                                <%-- dsanchez: multiidioma --%>
                                <div class="seleccionat cajaIdioma ca">
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="es cajaIdioma">
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="en cajaIdioma">
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                <div class="de cajaIdioma">
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                <div class="fr cajaIdioma">
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixDocument'/></span></span></a>
                                </div>
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
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='unitatadm.formulari.materies'/></legend>
                    <div class="modul_continguts mostrat">
                        
                        <!-- modulMateries -->
                        <div class="modulMateries selectorChecks">
                            <div class="seleccionats">
                                <p class="info"><spring:message code='unitatadm.formulari.materies.noInfo'/>.</p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='unitatadm.formulari.materies.gestiona'/></span></span></a>
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
                                        <a class="btn finalitza" href="javascript:;"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                                    </div>
                                    <div class="btnGenerico">
                                        <a href="javascript:;" class="cancela"><span><span><spring:message code='boto.cancela'/></span></span></a>
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
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='txt.normativaRelacionada'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulNormatives -->
                        <div class="modulNormatives">
                            <div class="seleccionats">
                                <%-- dsanchez: un solo idioma --%>
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaNormativaRelacionada'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestionaNormatives'/></span></span></a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulNormatives -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->  
        </div>
        <!-- /modulLateral -->
    </div>
    <!-- /escriptori_detall -->
</form>

<!-- escriptori_previsualitza -->
<div id="escriptori_previsualitza">
    <h2><spring:message code='txt.previsualitzantProcediment'/></h2>
    <div class="boton btnGenerico clear">
        <a href="javascript:;" class="btn torna dePrevisualitzar">
            <span><span><spring:message code='boto.torna'/></span></span>
        </a>
    </div>
    <div class="previsualitzacio">
        <iframe frameborder="0" scrolling="auto"></iframe>
    </div>
</div>
<!-- /escriptori_previsualitza -->


<!-- escriptori_documents -->
<div id="escriptori_documents" class="escriptori_detall">
    <script type="text/javascript">
        var txtTituloObligatorio = "<spring:message code='personal.formulari_document.titol.obligatori'/>";    
        var txtTituloNoSoloNumeros = "<spring:message code='personal.formulari_document.titol.no_nomes_numeros'/>";

        // dades formularis
        var FormulariDadesDoc = [
            { // Titol (Catala)
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "doc_titol_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters": {
                    "maxim": 230,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error":{
                    "obligatori": txtTituloObligatorio,
                    "tipus": txtTituloNoSoloNumeros
                }
            }
        ];
    </script>
    <form id="formGuardarDoc" action="" method="POST">
        <input type="hidden" name="docId" id="docId" />
        <input type="hidden" name="procId" id="procId" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <!--div id="modulPrincipal" class="grupoModulosFormulario"-->                    
        <div id="modulDocuments" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.documents'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>
                            <ul class="idiomes">
                                <li class="idioma"><a href="javascript:;" class="ca"><spring:message code='txt.idioma.ca'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="es"><spring:message code='txt.idioma.es'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="en"><spring:message code='txt.idioma.en'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="de"><spring:message code='txt.idioma.de'/></a></li>
                                <li class="idioma"><a href="javascript:;" class="fr"><spring:message code='txt.idioma.fr'/></a></li>
                            </ul>
                            <div class="idiomes">
                                <!-- ca -->
                                <div class="idioma ca">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_titol_ca"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_titol_ca" name="doc_titol_ca" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_descripcio_ca"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_descripcio_ca" name="doc_descripcio_ca" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_ca"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_ca" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_arxiu_ca_delete" id="doc_arxiu_ca_delete" value="1"/>
                                                    <label for="doc_arxiu_ca_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_ca"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_arxiu_ca" name="doc_arxiu_ca" type="file" class="nou" />
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
                                                <label for="doc_titol_es"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_titol_es" name="doc_titol_es" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_descripcio_es"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_descripcio_es" name="doc_descripcio_es" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_es"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_es" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_arxiu_es_delete" id="doc_arxiu_es_delete" value="1"/>
                                                    <label for="doc_arxiu_es_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_es"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_arxiu_es" name="doc_arxiu_es" type="file" class="nou" />
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
                                                <label for="doc_titol_en"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_titol_en" name="doc_titol_en" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_descripcio_en"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_descripcio_en" name="doc_descripcio_en" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_en"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_en" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_arxiu_en_delete" id="doc_arxiu_en_delete" value="1"/>
                                                    <label for="doc_arxiu_doc_en_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_en"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_arxiu_en" name="doc_arxiu_en" type="file" class="nou" />
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
                                                <label for="doc_titol_de"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_titol_de" name="doc_titol_de" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_descripcio_de"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_descripcio_de" name="doc_descripcio_de" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_doc_de"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_de" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_arxiu_de_delete" id="doc_arxiu_de_delete" value="1"/>
                                                    <label for="doc_arxiu_de_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_de"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_arxiu_de" name="doc_arxiu_de" type="file" class="nou" />
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
                                                <label for="doc_titol_fr"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_titol_fr" name="doc_titol_fr" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_descripcio_fr"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_descripcio_fr" name="doc_descripcio_fr" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_fr"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_fr" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_arxiu_fr_delete" id="doc_arxiu_fr_delete" value="1"/>
                                                    <label for="doc_arxiu_fr_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_fr"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_arxiu_fr" name="doc_arxiu_fr" type="file" class="nou" />
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
                                  <a id="btnVolver_documents" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_documents" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar_documents" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
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
<!-- escriptori_documents -->


<!-- escriptori_normatives -->
<div id="escriptori_normatives">
   <ul id="opcions_normativa" class="opcions">
        <li class="opcio C actiu"><spring:message code='txt.gestiona'/></li>                                 
    </ul>
    
    <div id="resultats_normativa" class="escriptori_items_llistat">            
        <div class="resultats C actiu" style="display: block;">
            <div id="cercador_normativa" class="escriptori_items_cercador"> 
                <div id="cercador_normativa_contingut">
                    <div class="fila">                                  
                        <div class="element t26">                               
                            <div class="etiqueta"><label for="cerca_normativa_titol"><spring:message code='camp.titol'/></label></div>
                            <div class="control">
                                <input id="cerca_normativa_titol" name="cerca_normativa_titol" type="text" class="titol" />
                            </div>                                  
                        </div>                              
                        <div class="element t12">                                
                            <div class="etiqueta"><label for="cerca_normativa_data"><spring:message code='txt.data'/></label></div>
                            <div class="control">
                                <input id="cerca_normativa_data" name="cerca_normativa_data" type="text" class="data" />
                            </div>                                  
                        </div>                              
                        <div class="element t12">                               
                            <div class="etiqueta"><label for="cerca_normativa_data_butlleti"><spring:message code='txt.dataButlleti'/></label></div>
                            <div class="control">
                                <input id="cerca_normativa_data_butlleti" name="cerca_normativa_data_butlleti" type="text" class="data_butlleti" />
                            </div>                                  
                        </div>                              
                    </div>
                    
                    <div class="botonera">
                        <div class="boton btnGenerico"><a id="btnLimpiarForm_normativa" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnBuscarForm_normativa" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnVolverDetalle_normativa" class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
                    </div>
                    
                </div>
            </div>   
            <div class="dades"></div>                       
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="titol" class="ordreCamp" />             
        </div>
    </div>        
    
    <div class="modulLateral escriptori_items_seleccionats">
        <div class="modul">
            <div class="interior">
                <div class="seleccionats">
                    <div class="seleccionat">
                        <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                        <div class="listaOrdenable"></div>
                    </div>
                    <p class="botonera btnGenerico">
                        <a id="btnFinalizar_normativa" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                    </p>                                    
                </div>                                  
            </div>
        </div>
    </div>
    <!-- seleccionats -->
</div>
<!-- /escriptori_normatives -->

<!-- escriptori_tramits -->
<div id="escriptori_tramits">   
    <h2><spring:message code='txt.detallProcediment'/></h2>
    <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
    <form id="formTramits" class="grupoModulosFormulario modulPrincipal">
    	<input id="id_tramit_actual" type="hidden"/>        
	    <div class="modul">
	        <fieldset>
		        <!-- fila -->
		        <div class="fila">
		            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
		            <ul class="idiomes">
		                <li class="idioma"><a href="javascript:;" class="ca"><spring:message code='txt.idioma.ca'/></a></li>
		                <li class="idioma"><a href="javascript:;" class="es"><spring:message code='txt.idioma.es'/></a></li>
		                <li class="idioma"><a href="javascript:;" class="en"><spring:message code='txt.idioma.en'/></a></li>
		                <li class="idioma"><a href="javascript:;" class="de"><spring:message code='txt.idioma.de'/></a></li>
		                <li class="idioma"><a href="javascript:;" class="fr"><spring:message code='txt.idioma.fr'/></a></li>                                
		            </ul>
		            <div class="idiomes">
		                <!-- ca -->
		                <div class="idioma ca">
                            <div class="fila">
                                <div class="element organoCompetente">
                                    <div class="etiqueta">
                                        <label for="tramits_item_organ"><spring:message code='camp.organCompetent'/></label>
                                    </div>
                                    <div class="control">
                                        <input id="tramits_item_organ_id" name="tramits_item_organ_id" type="hidden" />
                                        <div class="campo">                                           
                                            <input id="tramits_item_organ" name="tramits_item_organ" type="text" class="nou" readonly="true" />
                                        </div>
                                        <div class="botones">
                                            <div class="btnCambiar boton btnGenerico">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','tramits_item_organ_id', 'tramits_item_organ');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">                                    
                                                <a href="javascript:EliminaArbreUA('tramits_item_organ', 'tramits_item_organ_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                            
                            <div class="fila">
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="tramit_moment">Moment</label>
		                            </div>
		                            <div class="moment control select">
		                                <select id="tramit_moment" name="tramit_item_moment" type="text" class="nou">
                                            <option value="0">[Dinamizar]</option>
                                        </select>
		                            </div>
		                        </div>
		                    </div>
                            
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="tramit_nom_ca">Nom</label>
		                            </div>
		                            <div class="control">
		                                <input id="tramit_nom_ca" name="tramit_item_nom_ca" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_descripcio_ca">Descripci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_descripcio_ca" name="tramit_item_descripcio_ca" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_documentacio_ca">Documentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_documentacio_ca" name="tramit_item_documentacio_ca" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_requisits_ca">Requisits</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_requisits_ca" name="tramit_item_requisits_ca" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_termini_ca">Termini m�xim per a la presentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_termini_ca" name="tramit_item_termini_ca" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_lugar_ca">Lugar</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_lugar_ca" name="tramit_item_lugar_ca" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
		                </div>
		                <!-- /ca -->
		                <!-- es -->
		                <div class="idioma es">
		                    <div class="fila">
		                        <div class="element organoCompetente">
                                    <div class="etiqueta">
                                        <label for="tramits_item_organ"><spring:message code='camp.organCompetent'/></label>
                                    </div>
                                    <div class="control">                                        
                                        <div class="campo">                                           
                                            <input id="tramits_item_organ_es" name="tramits_item_organ_es" type="text" class="nou" readonly="true" />
                                        </div>
                                        <div class="botones">
                                            <div class="btnCambiar boton btnGenerico">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','tramits_item_organ_id', 'tramits_item_organ_es');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">                                    
                                                <a href="javascript:EliminaArbreUA('tramits_item_organ_es', 'tramits_item_organ_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
		                    </div>        
                            
                            <div class="fila">
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="tramit_moment_es">Moment</label>
		                            </div>
		                            <div class="moment control select">
		                                <select id="tramit_moment_es" name="tramit_item_moment_es" type="text" class="nou">
                                            <option value="0">[Dinamizar]</option>
                                        </select>
		                            </div>
		                        </div>
		                    </div>
                            
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="tramit_nom_es">Nom</label>
		                            </div>
		                            <div class="control">
		                                <input id="tramit_nom_es" name="tramit_item_nom_es" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_descripcio_es">Descripci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_descripcio_es" name="tramit_item_descripcio_es" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_documentacio_es">Documentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_documentacio_es" name="tramit_item_documentacio_es" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_requisits_es">Requisits</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_requisits_es" name="tramit_item_requisits_es" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_termini_es">Termini m�xim per a la presentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_termini_es" name="tramit_item_termini_es" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_lugar_es">Lugar</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_lugar_es" name="tramit_item_lugar_es" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
		                </div>
		                <!-- /es -->
		                <!-- en -->
		                <div class="idioma en">
		                    <div class="fila">
		                        <div class="element organoCompetente">
                                    <div class="etiqueta">
                                        <label for="tramits_item_organ_en"><spring:message code='camp.organCompetent'/></label>
                                    </div>
                                    <div class="control">                                        
                                        <div class="campo">                                           
                                            <input id="tramits_item_organ_en" name="tramits_item_organ_en" type="text" class="nou" readonly="true" />
                                        </div>
                                        <div class="botones">
                                            <div class="btnCambiar boton btnGenerico">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','tramits_item_organ_id', 'tramits_item_organ_en');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">                                    
                                                <a href="javascript:EliminaArbreUA('tramits_item_organ_en', 'tramits_item_organ_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>        
		                    </div>        

                            <div class="fila">
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="tramit_moment_en">Moment</label>
		                            </div>
		                            <div class="moment control select">
		                                <select id="tramit_moment_en" name="tramit_item_moment_en" type="text" class="nou">
                                            <option value="0">[Dinamizar]</option>
                                        </select>
		                            </div>
		                        </div>
		                    </div>
                            
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="tramit_nom_en">Nom</label>
		                            </div>
		                            <div class="control">
		                                <input id="tramit_nom_en" name="tramit_item_nom_en" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_descripcio_en">Descripci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_descripcio_en" name="tramit_item_descripcio_en" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_documentacio_en">Documentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_documentacio_en" name="tramit_item_documentacio_en" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_requisits_en">Requisits</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_requisits_en" name="tramit_item_requisits_en" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_termini_en">Termini m�xim per a la presentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_termini_en" name="tramit_item_termini_en" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_lugar_en">Lugar</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_lugar_en" name="tramit_item_lugar_en" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
		                </div>
		                <!-- /en -->
		                <!-- de -->
		                <div class="idioma de">
		                    <div class="fila">
		                        <div class="element organoCompetente">
                                    <div class="etiqueta">
                                        <label for="tramits_item_organ_de"><spring:message code='camp.organCompetent'/></label>
                                    </div>
                                    <div class="control">                                        
                                        <div class="campo">                                           
                                            <input id="tramits_item_organ_de" name="tramits_item_organ_de" type="text" class="nou" readonly="true" />
                                        </div>
                                        <div class="botones">
                                            <div class="btnCambiar boton btnGenerico">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','tramits_item_organ_id', 'tramits_item_organ_de');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">                                    
                                                <a href="javascript:EliminaArbreUA('tramits_item_organ_de', 'tramits_item_organ_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
		                    </div>     

                            <div class="fila">
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="tramit_moment_de">Moment</label>
		                            </div>
		                            <div class="moment control select">
		                                <select id="tramit_moment_de" name="tramit_item_moment_de" type="text" class="nou">
                                            <option value="0">[Dinamizar]</option>
                                        </select>
		                            </div>
		                        </div>
		                    </div>
                            
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="tramit_nom_de">Nom</label>
		                            </div>
		                            <div class="control">
		                                <input id="tramit_nom_de" name="tramit_item_nom_de" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_descripcio_de">Descripci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_descripcio_de" name="tramit_item_descripcio_de" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_documentacio_de">Documentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_documentacio_de" name="tramit_item_documentacio_de" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_requisits_de">Requisits</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_requisits_de" name="tramit_item_requisits_ca" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_termini_de">Termini m�xim per a la presentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_termini_de" name="tramit_item_termini_de" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_lugar_de">Lugar</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_lugar_de" name="tramit_item_lugar_de" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
		                </div>
		                <!-- /de -->
		                <!-- fr -->
		                <div class="idioma fr">
		                    <div class="fila">
		                        <div class="element organoCompetente">
                                    <div class="etiqueta">
                                        <label for="tramits_item_organ_fr"><spring:message code='camp.organCompetent'/></label>
                                    </div>
                                    <div class="control">                                        
                                        <div class="campo">                                           
                                            <input id="tramits_item_organ_fr" name="tramits_item_organ_fr" type="text" class="nou" readonly="true" />
                                        </div>
                                        <div class="botones">
                                            <div class="btnCambiar boton btnGenerico">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','tramits_item_organ_id', 'tramits_item_organ_fr');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">                                    
                                                <a href="javascript:EliminaArbreUA('tramits_item_organ_fr', 'tramits_item_organ_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
		                    </div>                

                            <div class="fila">
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="tramit_moment_fr">Moment</label>
		                            </div>
		                            <div class="moment control select">
		                                <select id="tramit_moment_fr" name="tramit_item_moment_fr" type="text" class="nou">
                                            <option value="0">[Dinamizar]</option>
                                        </select>
		                            </div>
		                        </div>
		                    </div>
                            
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="tramit_nom_fr">Nom</label>
		                            </div>
		                            <div class="control">
		                                <input id="tramit_nom_fr" name="tramit_item_nom_fr" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_descripcio_fr">Descripci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_descripcio_fr" name="tramit_item_descripcio_fr" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_documentacio_fr">Documentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_documentacio_fr" name="tramit_item_documentacio_fr" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_requisits_fr">Requisits</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_requisits_fr" name="tramit_item_requisits_fr" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_termini_fr">Termini m�xim per a la presentaci�</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_termini_fr" name="tramit_item_termini_fr" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="tramit_item_lugar_fr">Lugar</label>
                                    </div>
                                    <div class="control">
                                        <textarea id="tramit_item_lugar_fr" name="tramit_item_lugar_fr" cols="50" rows="2" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>
                            
		                </div>
		                <!-- /fr -->
		            </div>
		        </div>		        
		        <!-- /fila -->
			</fieldset>
		</div>
        
        <!-- modul -->
        <div class="modul">
            <fieldset>
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='txt.gestio'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element codigoTramite">
                            <div class="etiqueta"><label for="tramit_item_codi">Codi tramit</label></div>
                            <div class="control">
                                <input id="tramit_item_codi" name="tramit_item_codi" type="text" class="nou" />
                            </div>
                        </div>                        
                    </div>                    
                    <!-- /fila -->                    
                    
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t99p">
                            <div class="etiqueta"><label for="tramit_item_versio">Versi� tr�mit</label></div>
                            <div class="control">
                                <input id="tramit_item_versio" name="tramit_item_versio" type="text" class="data nou" />
                            </div>
                        </div>                          
                    </div>
                    <!-- /fila -->
                    
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t99p">
                            <div class="etiqueta"><label for="tramit_item_url_externa">URL Externa</label></div>
                            <div class="control">
                                <input id="tramit_item_url_externa" name="tramit_item_url_externa" type="text" class="nou" />
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                                      
                </div>
            </fieldset>
        </div>
        <!-- /modul -->
	</form>
    
    <!-- Men� de publicaci�n -->
    <div class="menuPublicacion">
        <div class="modul publicacio">
            <fieldset>
                <a class="modul mostrat">Amaga</a>
                <legend>Publicaci�</legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element left">
                            <div class="etiqueta">
                                <label for="item_estat">Estat</label>
                            </div>
                            <div class="control">
                                <select name="item_estat" id="item_estat">                                                                
                                   <option selected="selected" value="">Tria una opci�</option>
                                   <option selected="selected" value="1">P�blica</option>
                                   <option value="2">Interna</option>
                                   <option value="3">Reserva</option>                               
                                </select>
                            </div>
                        </div>
                        <div class="element right">
                            <div class="etiqueta">
                                <label for="tramit_item_data_actualitzacio">Data actualitzaci�</label>
                            </div>
                            <div class="control">
                                <input type="text" readonly="readonly" class="nou" name="tramit_item_data_actualitzacio" id="tramit_item_data_actualitzacio">
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                    <!-- fila -->
                    <div class="fila">
                        <div class="element left">
                            <div class="etiqueta">
                                <label for="tramit_item_data_publicacio">Data publicaci�</label>
                            </div>
                            <div class="control">
                                <input type="text" readonly="readonly" class="nou hasDatepicker" name="campo_tramit_item_data_publicacio" id="tramit_item_data_publicacio">
                            </div>
                        </div>
                        <div class="element right">
                            <div class="etiqueta">
                                <label for="tramit_item_data_caducitat">Data caducitat</label>
                            </div>
                            <div class="control">
                                <input type="text" readonly="readonly" class="nou hasDatepicker" name="tramit_item_data_caducitat" id="tramit_item_data_caducitat">
                            </div>
                        </div>
                    </div>                        
                    <!-- /fila -->
                    <!-- botonera dalt -->
                    <div class="botonera dalt">
                      <ul>
                          <li class="btnVolver impar">
                              <a class="btn torna" href="javascript:;" id="btnVolver"><span><span>Torna</span></span></a>
                          </li>
                          <li class="btnGuardar par">
                              <a class="btn guarda important" href="javascript:;" id="btnGuardar"><span><span>Guarda!</span></span></a>
                          </li>
                          <li style="" class="btnEliminar impar">
                              <a class="btn elimina" href="javascript:;" id="btnEliminar"><span><span>Elimina</span></span></a>
                          </li>
                          <!--<li class="btnPrevisualizar par">
                              <a class="btn previsualitza" href="javascript:;" id="btnPrevisualizar"><span><span>Previsualitza</span></span></a>
                          </li>-->
                      </ul>
                    </div>
                    <!-- /botonera dalt -->
                </div>
            </fieldset>
        </div>
    </div>
    <!-- /Men� de publicaci�n -->    
    
</div>                           

<!-- /escriptori_tramits -->