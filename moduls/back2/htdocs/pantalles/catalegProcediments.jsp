<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/cataleg_procediments.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_documents.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_normativa.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_tramits.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />

<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/autoresize.jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/procediments.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents_tramits.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents_requerits.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_public_objectiu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_normativa.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_tramits.js'/>"></script>
<!--<script type="text/javascript" src="<c:url value='/js/modul_fetsVitals.js'/>"></script>-->
<script type="text/javascript" src="<c:url value='/js/modul_fetsVitals_procediments.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_taxes_tramits.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_formularis_tramits.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_auditories.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>

<script type="text/javascript">

    var idUAMollapa = '<c:out value="${idUA}"/>';
    var nomUAMollapa = '<c:out value="${nomUA}"/>';

    var pagLlistat = '<c:url value="/catalegProcediments/llistat.do" />';
    var pagDetall = '<c:url value="/catalegProcediments/pagDetall.do" />';
    var pagGuardar = '<c:url value="/catalegProcediments/guardar.do" />';    
    var pagEsborrar = '<c:url value="/catalegProcediments/esborrarProcediment.do" />';
    var seccioNormatives = '<c:url value="/catalegProcediments/cercarNormatives.do" />';
    var pagDetallTramit = '<c:url value="/tramit/carregarTramit.do" />';
    var pagGuardarTramit = '<c:url value="/tramit/guardarTramit.do" />';
    var pagEsborrarTramit = '<c:url value="/tramit/esborrarTramit.do" />';
    var pagGuardarDoc = '<c:url value="/documents/guardarDocument.do" />';
    var pagCarregarDoc = '<c:url value="/documents/carregarDocument.do" />';    
    var pagGuardarDocTramit = '<c:url value="/documentsTramit/guardarDocumentTramit.do" />';
    var pagCarregarDocTramit = '<c:url value="/documentsTramit/carregarDocumentTramit.do" />';
    var pagGuardarDocRequerit = '<c:url value="/documentsRequerits/guardarDocumentRequerit.do" />';
    var pagCarregarDocRequerit = '<c:url value="/documentsRequerits/carregarDocumentRequerit.do" />';
    var pagExcepcioDocRequerit = '<c:url value="/documentsRequerits/recuperaExcepcionsDocumentacio.do" />';
    var pagGuardarTaxaTramit = '<c:url value="/taxa/guardarTaxa.do" />';
    var pagCarregarTaxaTramit = '<c:url value="/taxa/carregarTaxaTramit.do" />';
    var pagAuditories = '<c:url value="/auditories/llistat.do" />';
    var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
    var pagTraduir = '<c:url value="/catalegProcediments/traduir.do" />';
    var pagTraduirTramit = '<c:url value="/tramit/traduir.do" />';
    var pagTraduirDocument = '<c:url value="/documents/traduir.do" />';
    var pagTraduirDocumentTramit = '<c:url value="/documentsTramit/traduir.do" />';
    var pagListarHechosVitales = '<c:url value="/catalegProcediments/listarHechosVitales.do" />';
    var urlPrevisualizarProcedimiento = '<c:out value="${urlPrevisualitzacio}"/>';

    //texts
    var txtEsborrarCorrecte = "<spring:message code='txt.procediment_esborrat_correcte'/>";
    var txtEsborrarTramitCorrecte = "<spring:message code='txt.tramit_esborrat_correcte'/>";
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
    var txtTramit = "<spring:message code='txt.tramit'/>";
    var txtTramitEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " +  txtTramit.toLowerCase() + "?";
    var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
    var txtMostra = "<spring:message code='txt.mostra'/>";
    var txtAmaga = "<spring:message code='txt.amaga'/>";
    var txtCaducat = "<spring:message code='txt.caducat'/>";
    var txtCaducitat = "<spring:message code='txt.caducitat'/>";
    var txtFamilia = "<spring:message code='txt.familia'/>";
    var txtFechaActualizacion = "<spring:message code='camp.dataActualitzacio'/>";
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtSeleccionat = "<spring:message code='txt.seleccionat'/>";

    var txtHiHa = "<spring:message code='txt.hi_ha'/>";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtNoHiHaFets = "<spring:message code='txt.noHiHaFetsVitals'/>";
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();

    var txtFet = "<spring:message code='txt.fet_vital'/>";
    var txtFets = "<spring:message code='txt.fets_vitals'/>";
    var txtNoHiHaFets = txtNoHiHa + " " + txtFets;
    var txtNoHiHaFetsSeleccionats = txtNoHiHaFets + " " + txtSeleccionats.toLowerCase();
    
    var txtDocument = "<spring:message code='txt.document'/>";    
    var txtDocuments = "<spring:message code='txt.documents'/>";
    var txtFormulari = "<spring:message code='txt.formulari'/>";
    var txtFormularis = "<spring:message code='txt.formularis'/>";
    var txtTaxa = "<spring:message code='txt.taxa'/>";
    var txtTaxes = "<spring:message code='txt.taxes'/>";
    var txtNoHiHaDocuments = txtNoHiHa + " " + txtDocuments;
    var txtNoHiHaTaxes = txtNoHiHa + " " + txtTaxes;
    var txtNoHiHaDocumentsSeleccionats = txtNoHiHaDocuments + " " + txtSeleccionats.toLowerCase();
    var txtNoHiHaTaxesSeleccionades = txtNoHiHaTaxes + " " + txtSeleccionades.toLowerCase();
    
    var txtTramitNouProcediment = "<spring:message code='txt.gestionar_tramits_guardar_procediment'/>";
    var txtTramit = "<spring:message code='txt.tramit'/>";
    var txtTramits = "<spring:message code='txt.tramits'/>";
    var txtTramitCreatCorrecte = "<spring:message code='txt.tramit_creat_correcte'/>";
    var txtTramitModificatCorrecte = "<spring:message code='txt.tramit_modificat_correcte'/>";
    
    var txtTaxaCreadaCorrecte = "<spring:message code='txt.taxa_creada_correcte'/>";
    var txtTaxaModificadaCorrecte = "<spring:message code='txt.taxa_modificada_correcte'/>";    
    
    var txtNoHiHaTramitsSeleccionats = txtNoHiHa + " " + txtTramits.toLowerCase() + " " + txtSeleccionats.toLowerCase();
    
    var txtMateria = "<spring:message code='txt.materia'/>";
    var txtMateries = "<spring:message code='txt.materies'/>";
    var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
    var txtNoHiHaMateriaSeleccionada = txtNoHiHa + " " + txtMateria.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaMateriesSeleccionades = txtNoHiHa + " " + txtMateries.toLowerCase() + " " + txtSeleccionades.toLowerCase();

 	// modul public objectiu
    var txtPublic = "<spring:message code='txt.public_objectiu'/>";
    var txtPublics = "<spring:message code='txt.publics_objectiu'/>";
    var txtNoHiHaPublics = txtNoHiHa + " " + txtPublics;
    var txtNoHiHaPublicsSeleccionats = txtNoHiHa + " " + txtPublics.toLowerCase() + " " + txtSeleccionats.toLowerCase();
    
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
    var txtValidacionObligatorio = "<spring:message code='personal.formulari.validacio.tramit.obligatori'/>";    
    var txtNombreNoSoloNumeros = "<spring:message code='personal.formulari.nom.no_nomes_numeros'/>";
    var txtOrganoObligatorio = "<spring:message code='personal.formulari.organ.obligatori'/>";
    var txtOrganoResponsableObligatorio = "<spring:message code='personal.formulari.organ.responsable.obligatori'/>";

    // dades formularios
    var FormulariDades = [
        // Nombre (Catalan)
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
        
        // Forma de iniciaci?n
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

        // Plazo m?ximo para resoluci?n (Catal?n)
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_resolucio_" + '<c:out value="${idiomaVal}"/>',
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
        
        // Plazo m?ximo para la notificaci?n (Catal?n)
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_notificacio_" + '<c:out value="${idiomaVal}"/>',
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
        
        // Organisme responsable 
        {   
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_organ_responsable_id",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": txtOrganoResponsableObligatorio
                }           
        },
        
        // Organ competent per resoldre 
        {   
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_organ_id",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": txtOrganoObligatorio
                }           
        },
        
        // Silencio administrativo
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_silenci_" + '<c:out value="${idiomaVal}"/>',
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
			// Nom del tr?mit 
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_nom_tramit_" + '<c:out value="${idiomaVal}"/>',
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
						
        // Identificador tr?mit telem?tic
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_version_tramit",
            "obligatori": "no",
            "tipus": "numeric",         
            "error":
                {
                    "tipus": "<spring:message code='tram.formulari.error.versio.nomes_numeros'/>"
                }
        },
        
        //Validaci?
		{	
			// Nom del tr?mit 
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_validacio_tramit",
            "obligatori": "si",
            "error":
                {
                    "obligatori": txtValidacionObligatorio
                }			
		},
		
		// Organ competent per tramitar 
		{	
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "tramits_item_organ_id",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 250,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": txtOrganoObligatorio
                }			
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
                type="hidden" value="id" class="ordreCamp" />
        </div>
        <div class="resultats C">
            <!-- cercador -->
            <div id="cercador">
                <div id="cercador_contingut">
                    <div class="opcionesBusqueda">
                        <h2><spring:message code='txt.OPCIONS_CERCA'/></h2>
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
                        <div class="element">                        
                            <label for="cerca_estat"><spring:message code='txt.visibilitat'/></label>                            
                            <select id="cerca_estat" name="cerca_estat" class="t8">
                                <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                <%--<option value="1"><spring:message code='txt.validacio.publica'/></option>
                                <option value="2"><spring:message code='txt.validacio.interna'/></option>
                                <option value="3"><spring:message code='txt.validacio.reserva'/></option>--%>
                                <option value="1"><spring:message code='txt.validacio.visible'/></option>
                                <option value="2"><spring:message code='txt.validacio.noVisible'/></option>
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
                        <h2><spring:message code='txt.CERCADOR_AVANCAT'/></h2>
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
                                    <label for="enPlazo"><spring:message code='camp.cerca_en_plazo'/></label>
                                </div>
                                <div class="control">
                                    <select id="enPlazo" name="enPlazo" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="0"><spring:message code='txt.no'/></option>
                                        <option value="1"><spring:message code='txt.si'/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="telematico"><spring:message code='camp.cerca_telematico'/></label>
                                </div>
                                <div class="control">
                                    <select id="telematico" name="telematico" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="0"><spring:message code='txt.no'/></option>
                                        <option value="1"><spring:message code='txt.si'/></option>
                                    </select>
                                </div>
                            </div>
                            <%--
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
                             --%>
                        </div>
                        <div class="fila">
                        	<div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_publicObjectiu"><spring:message code='camp.publicObjectiu'/></label>
                                </div>
                                <div class="control select">
                                    <select id="cerca_publicObjectiu" name="cerca_publicObjectiu" class="publicObjectiu">
                                        <option value=""><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${llistaPublicsObjectiu}" var="publicObjectiu">
                                            <option value='<c:out value="${publicObjectiu.id}" />'><c:out value="${publicObjectiu.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_materia"><spring:message code='fitxes.llistat.materia'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_materia" name="cerca_materia" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                        
                                        <c:forEach items="${llistaMateries}" var="materia">
                                            <option value="<c:out value="${materia.id}"/>"><c:out value="${materia.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_fet_vital"><spring:message code='fitxes.llistat.fet_vital'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_fet_vital" name="cerca_fet_vital" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                        
                                        <c:forEach items="${llistaFetsVitals}" var="fetVital">
                                            <option value="<c:out value="${fetVital.id}"/>"><c:out value="${fetVital.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
							<div class="element t25">
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
                                        <option value="0"><spring:message code='txt.no'/></option>
                                        <option value="1"><spring:message code='txt.si'/></option>
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
                                        <option value="0"><spring:message code='txt.no'/></option>
                                        <option value="1"><spring:message code='txt.si'/></option>
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
                                        <option value="1">S?</option>
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
            <input type="hidden" value="id" class="ordreCamp" />
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
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirProcediment">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>                            
                            
                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
                                <!-- Camps per cada idioma -->
                                <div class="idioma <c:out value="${lang}" />">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">                                            
                                                <label for="item_codigo_pro"><spring:message code='txt.codi'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_pro" name="item_codigo_pro" type="text" value=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_<c:out value="${lang}" />"><spring:message code='camp.nomProcediment'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_<c:out value="${lang}" />" name="item_nom_<c:out value="${lang}" />" type="text" class="nou" />
                                            </div>
                                        </div>
                                        <div id="caja_item_clave_primaria" class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria"><spring:message code='camp.clau_primaria'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria" name="item_clave_primaria" type="text" class="nou" readonly="readonly" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_objecte_<c:out value="${lang}" />"><spring:message code='camp.objecte'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objecte_<c:out value="${lang}" />" name="item_objecte_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resultat_<c:out value="${lang}" />"><spring:message code='camp.resultat'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resultat_<c:out value="${lang}" />" name="item_resultat_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_destinataris_<c:out value="${lang}" />"><spring:message code='camp.destinataris'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_destinataris_<c:out value="${lang}" />"
                                                    name="item_destinataris_<c:out value="${lang}" />" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_resolucio_<c:out value="${lang}" />"><spring:message code='camp.terminiMaximResolucio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_resolucio_<c:out value="${lang}" />" name="item_resolucio_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_notificacio_<c:out value="${lang}" />"><spring:message code='camp.terminiMaximNotificacio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_notificacio_<c:out value="${lang}" />" name="item_notificacio_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element organoResponsable">
                                            <div class="etiqueta">
                                                <label for="item_organ_responsable"><spring:message code='camp.organResponsable'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_organ_responsable_id" name="item_organ_responsable_id" type="hidden" />
                                                <div class="campo">
                                                    <input id="item_organ_responsable" name="item_organ_responsable" type="text" class="nou" readonly="readonly" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">                        
                                                        <a href="javascript:carregarArbreUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','item_organ_responsable_id', 'item_organ_responsable');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                        </a>
                                                    </div>
                                                    <div class="boton btnGenerico">                                    
                                                        <a href="javascript:EliminaArbreUA('item_organ_responsable', 'item_organ_responsable_id');" class="btn borrar">
                                                            <span><span><spring:message code='boto.borrar'/></span></span>
                                                        </a>
                                                    </div>
                                                </div>
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
                                                    <input id="item_organ" name="item_organ" type="text" class="nou" readonly="readonly" />                                                
                                                </div>
                                                <div class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
														<a href="javascript:carregarArbreTotesUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','item_organ_id', 'item_organ');" class="btn consulta">
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
                                                <label for="item_silenci_<c:out value="${lang}" />"><spring:message code='camp.silenciAdministratiu'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_silenci_<c:out value="${lang}" />" name="item_silenci_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                                                        
                                    <div class="fila">
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_fi_vida_administrativa" name="item_fi_vida_administrativa" type="checkbox" value="on" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_fi_vida_administrativa"><spring:message code='camp.fiViaAdministrativa'/></label>
                                            </div>                                            
                                        </div>
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_taxa" name="item_taxa" type="checkbox" value="on" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_taxa"><spring:message code='camp.taxa'/></label>
                                            </div>                                            
                                        </div>                      
                                        <div class="element checkbox">
                                            <div class="control">
                                                <input id="item_finestreta_unica" name="item_finestreta_unica" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_finestreta_unica"><spring:message code='camp.finestretaUnica'/></label>
                                            </div>                                            
                                        </div>                  
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_observacions_<c:out value="${lang}" />"><spring:message code='camp.observacions'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_observacions_<c:out value="${lang}" />"
                                                    name="item_observacions_<c:out value="${lang}" />" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>								
                                <!-- /ca -->
								</c:forEach>
                            </div>
                        </div>
                        <!-- /fila -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            			
			<!-- modul >			
			<div class="modul">
				<fieldset>
					<a class="modul mostrat"><spring:message code='txt.amaga'/></a>
					<legend><spring:message code='txt.CANALS_PRESENTACIO'/>/</legend>					
					<div class="modul_continguts mostrat">
						<div class="fila">
							<div class="element t99p multilang">
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">						
									<div class='campoIdioma <c:out value="${lang}"/>'>
										<div class="etiqueta">
											<label for='item_presentacio_<c:out value="${lang}"/>'>												
												<c:choose>
													<c:when test="${lang eq 'ca'}">
														<spring:message code='camp.presentacio'/> (CA)
													</c:when>
													
													<c:when test="${lang eq 'es'}">
														<spring:message code='camp.presentacio'/> (ES)
													</c:when>
													
													<c:when test="${lang eq 'en'}">
														<spring:message code='camp.presentacio'/> (EN)
													</c:when>										

													<c:when test="${lang eq 'fr'}">
														<spring:message code='camp.presentacio'/> (FR)
													</c:when>										

													<c:when test="${lang eq 'de'}">
														<spring:message code='camp.presentacio'/> (DE)
													</c:when>														
												</c:choose>												
											</label>
										</div>									
										<div class="control">
											<input id='item_presentacio_<c:out value="${lang}"/>' name='item_presentacio_<c:out value="${lang}"/>' type="text" class="nou" />
										</div>									
									</div>												
							</c:forEach>						
							</div>
						</div>
						
						<div class="fila">
							<div class="element t50p multilang">						
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">							
                                <div class='campoIdioma <c:out value="${lang}"/>'>
                                    <div class="etiqueta">
                                        <label for='item_lloc_<c:out value="${lang}"/>'>										
											<c:choose>
												<c:when test="${lang eq 'ca'}">
													<spring:message code='camp.lloc'/> (CA)
												</c:when>
												
												<c:when test="${lang eq 'es'}">
													<spring:message code='camp.lloc'/> (ES)
												</c:when>
												
												<c:when test="${lang eq 'en'}">
													<spring:message code='camp.lloc'/> (EN)
												</c:when>										

												<c:when test="${lang eq 'fr'}">
													<spring:message code='camp.lloc'/> (FR)
												</c:when>										

												<c:when test="${lang eq 'de'}">
													<spring:message code='camp.lloc'/> (DE)
												</c:when>														
											</c:choose>
										</label>
                                    </div>
                                    <div class="control">
                                        <input id='item_lloc_<c:out value="${lang}"/>' name='item_lloc_<c:out value="${lang}"/>' type="text" class="nou" />
                                    </div>
                                </div>																					
							</c:forEach>
								<div class="element t50p">
									<div class="etiqueta">
										<label for="item_url"><spring:message code='camp.urlTramitExtern'/></label>
									</div>
									<div class="control">
										<input id="item_url" name="item_url" type="text" class="nou" />
									</div>
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
                    <legend><spring:message code='txt.INFORMADORS'/></legend>
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
            <div id="modulEstadistiques" class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.ESTADISTIQUES'/></legend> 
                    <div class="modul_continguts mostrat">
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
		  <c:if test="${rolAdmin}">
	        <!-- modul -->
	        <div id="modulAuditories" class="modul auditorias">                
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.AUDITORIES'/></legend>
                    <div class="modul_continguts mostrat">
                       <p class="executant"><spring:message code='txt.carregant'/></p>
                    </div>
                </fieldset>
	        </div>            
	        <!-- /modul -->
	      </c:if>
            
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
                        </div>
                        <!-- /fila -->
						
                        <!-- fila -->
                        <div class="fila">
                            <div class="element left">
                                <div class="etiqueta">
                                    <label for="item_data_publicacio"><spring:message code='camp.dataPublicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacio" name="item_data_publicacio" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_caducitat"><spring:message code='camp.dataCaducitat'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_caducitat" name="item_data_caducitat" type="text" class="nou" />
                                </div>
                            </div>
                        </div>                        
                        <!-- /fila -->
						
                        <!-- botonera dalt -->
                        <div class="botonera dalt">
                          <ul>
                              <li class="btnVolver impar">
                                  <a id="btnVolver" href="javascript:;" class="btn torna btnVolver"><span><span><spring:message code='boto.torna'/></span></span></a>
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
                        
            <!-- /modul Public Objectiu -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='unitatadm.formulari.publics'/> *</legend>
                    <div class="modul_continguts mostrat">
                        
                        <!-- modulPublicObjectiu -->
                        <div class="ModulPublicObjectiu selectorChecks">
                        
                            <input name="modul_public_modificat" type="hidden" value="0" />
                        
                            <div class="seleccionats">
                                <p class="info"><spring:message code='unitatadm.formulari.publics.noInfo'/>.</p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='unitatadm.formulari.publics.gestiona'/></span></span></a>
                                </div>
                            </div>
                            <div class="llistat">
                                <ul>
									<c:forEach items="${llistaPublicsObjectiu}" var="publicObjectiu" varStatus="i">
                                        <c:choose>
                                            <c:when test="${(i.count) % 2 == 0}">
                                                <li class="par">
                                            </c:when>
                                            <c:otherwise>
                                               <li class="impar">
                                            </c:otherwise>
                                        </c:choose>                                     
                                          <label><span><c:out value="${publicObjectiu.nom}" /></span><input type="checkbox" value="<c:out value='${publicObjectiu.id}' />" /></label>
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
                        <!-- /modulPublicObjectiu -->
                    </div>
                </fieldset>
            </div>
            <!-- modul -->   
                     
            <div id="modul_tramits" class="modul destacado">                               
                <div class="dec1"></div>
                <div class="dec2"></div>
                
                <div class="interior">                    
                    <fieldset>                                  
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                        <legend><spring:message code='txt.TRAMITS'/></legend>                               
                        <div class="modul_continguts mostrat">                                  
                            <!-- modulTramits -->
                            <div class="modulTramits">
                                <div class="seleccionats">                                
                                    <div class="seleccionat">
                                        <p class="info"><spring:message code='txt.noHiHaTramitsRelacionats'/></p>
                                        <div class="listaOrdenable"></div>
                                    </div>
                                    <div class="btnGenerico">
                                        <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='txt.gestiona_tramits'/></span></span></a>
                                    </div>
                                </div>                                  
                            </div>
                            <!-- /modulTramits -->                                 
                        </div>                              
                    </fieldset>                     
                </div>
            </div>            
            <!-- /modul -->              
            
            <div class="modul">
                <fieldset>                    
                    <legend><spring:message code='camp.familia'/></legend>                               
                    <div class="element">     
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
                            <input id="modulo_documents_modificado" type="hidden" name="modulo_documents_modificado" value="0" />
                            <ul class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
									<c:choose>
										<c:when test="${lang eq 'ca'}">
										<li class="ca seleccionat"><spring:message code='txt.idioma.ca_abr'/></li>
										</c:when>										
										<c:when test="${lang eq 'es'}">
										<li class="es"><spring:message code='txt.idioma.es_abr'/></li>
										</c:when>										
										<c:when test="${lang eq 'en'}">
										<li class="en"><spring:message code='txt.idioma.en_abr'/></li>
										</c:when>											
										<c:when test="${lang eq 'fr'}">
										<li class="fr"><spring:message code='txt.idioma.fr_abr'/></li>
										</c:when>																					
										<c:when test="${lang eq 'de'}">
										<li class="de"><spring:message code='txt.idioma.de_abr'/></li>
										</c:when>																															
									</c:choose>
								</c:forEach>
                            </ul>
                            
                            <div class="seleccionats">								
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
                                <c:if test="${loop.first}">
								<div class="seleccionat cajaIdioma <c:out value="${lang}"/>">
								</c:if>
                                <c:if test="${!loop.first}">
								<div class="<c:out value="${lang}"/> cajaIdioma">
								</c:if>								
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>								
								</c:forEach>							                                
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
                        
                            <input name="modulo_materias_modificado" type="hidden" value="0" />
                        
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
                        
                            <input name="modulo_normativas_modificado" type="hidden" value="0" />
                        
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
            
            <!-- modul -->
            <div class="modul" id="fetsVitals">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='fitxes.fets_vitals'/></legend>
                    <div class="modul_continguts mostrat">                    
                        <!-- modulFetsVitals -->
                        <div class="modulFetsVitals selectorChecks">
                        
                            <input name="modulo_hechos_modificado" type="hidden" value="0" />
                        
                            <div class="seleccionats">
                                <p class="info"><spring:message code='fitxes.no_hi_ha_fets_vitals'/></p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_fets_vitals'/></span></span></a>
                                </div>
                            </div>
                            <div class="llistat">
                                <ul></ul>
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
                        <!-- /modulFetsVitals -->                        
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
                "etiquetaValor": "doc_titol_" + '<c:out value="${idiomaVal}"/>',
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
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirDocument">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
									
							<div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<div class='idioma <c:out value="${lang}"/>'>
									<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label for='doc_titol_<c:out value="${lang}"/>'><spring:message code='camp.titol'/></label>
											</div>
											<div class="control">
												<input id='doc_titol_<c:out value="${lang}"/>' name='doc_titol_<c:out value="${lang}"/>' type='text' class='nou'/>
											</div>
										</div>
									</div>

                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for='doc_descripcio_<c:out value="${lang}"/>'><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id='doc_descripcio_<c:out value="${lang}"/>' name='doc_descripcio_<c:out value="${lang}"/>' cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>	
									
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for='doc_arxiu_<c:out value="${lang}"/>'><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id='grup_arxiu_actual_doc_<c:out value="${lang}"/>' class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name='doc_arxiu_<c:out value="${lang}"/>_delete' id='doc_arxiu_<c:out value="${lang}"/>_delete' value="1"/>
                                                    <label for='doc_arxiu_<c:out value="${lang}"/>_delete' class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for='doc_arxiu_<c:out value="${lang}"/>'><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id='doc_arxiu_<c:out value="${lang}"/>' name='doc_arxiu_<c:out value="${lang}"/>' type="file" class="nou" />
                                            </div>
                                        </div>                                                                                      
                                    </div>									
								</div>								
								</c:forEach>								
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
    <h2><spring:message code='txt.detallTramit'/></h2>
    <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        
    <form id="formTramits">
		<div class="escriptori_detall" id="escriptori_detall" style="display: block;">
			<div class="grupoModulosFormulario modulPrincipal" id="modulPrincipal">    	
		    	<input id="id_tramit_actual" name="id_tramit_actual" type="hidden"/>        
		    	<input id="id_procediment_tramit" name="id_procediment_tramit" type="hidden"/>
		    	
			    <!-- titol  -->
	    		<div class="modul" id="titolEdicioTramit">        		
					<p class="titol"><spring:message code='txt.edicio.tramit'/></p>
					<p class="titolProcediment" id="nom_procediment_tramit"></p>				        
	    		</div>
	    		<!-- fi titol  -->
		    			    	
			    <div class="modul">
			        <fieldset>
						<legend><spring:message code='txt.dades'/></legend>			
				        <!-- fila -->
				        <div class="fila">		            
				            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>

                            <ul class="idiomes">                            
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirTramit">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
							
				            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">

				                <div class='idioma <c:out value="${lang}"/>'>		
								
									<div class="fila">
										<div class="element t75p">
											<div class="etiqueta">
												<label for='item_nom_tramit_<c:out value="${lang}"/>'><spring:message code='camp.nomTramit'/></label>
											</div>
											<div class="control">
												<input id='item_nom_tramit_<c:out value="${lang}"/>' name='item_nom_tramit_<c:out value="${lang}"/>' type="text" class="nou" />
											</div>
										</div>
									</div>						
								
									<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label for='item_descripcio_tramit_<c:out value="${lang}"/>'><spring:message code='camp.descripcio'/></label>
											</div>
											<div class="control">
												<textarea id='item_descripcio_tramit_<c:out value="${lang}"/>' name='item_descripcio_tramit_<c:out value="${lang}"/>'
													cols="50" rows="2" class="nou"></textarea>
											</div>
										</div>
									</div>
									
									<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label for='item_requisits_tramit_<c:out value="${lang}"/>'><spring:message code='camp.requisits'/></label>
											</div>
											<div class="control">
												<textarea id='item_requisits_tramit_<c:out value="${lang}"/>' name='item_requisits_tramit_<c:out value="${lang}"/>'
													cols="50" rows="2" class="nou"></textarea>
											</div>
										</div>
									</div>							
											
									<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label for='item_documentacio_tramit_<c:out value="${lang}"/>'><spring:message code='camp.documentacio'/></label>
											</div>
											<div class="control">
												<textarea id='item_documentacio_tramit_<c:out value="${lang}"/>' name='item_documentacio_tramit_<c:out value="${lang}"/>'
													cols="50" rows="2" class="nou"></textarea>
											</div>
										</div>
									</div>
									
									<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label for='item_termini_tramit_<c:out value="${lang}"/>'><spring:message code='camp.terminiMaximPresentacio'/></label>
											</div>
											<div class="control">
												<textarea id='item_termini_tramit_<c:out value="${lang}"/>' name='item_termini_tramit_<c:out value="${lang}"/>'
													cols="50" rows="2" class="nou"></textarea>
											</div>
										</div>
									</div>	
		
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for='item_lloc_tramit_<c:out value="${lang}"/>'><spring:message code='camp.lloc'/></label>
                                            </div>
                                                <div class="campo">                                           
                                                    <input id='item_lloc_tramit_<c:out value="${lang}"/>' name='item_lloc_tramit_<c:out value="${lang}"/>' type="text" class="nou" />
                                                </div>
                                        </div>
                                    </div>
                            
		                            <div class="fila">
		                                <div class="element organoCompetente">
		                                    <div class="etiqueta">
		                                        <label for="tramits_item_organ"><spring:message code='camp.organCompetentTramit'/></label>
		                                    </div>
		                                    <div class="control">
		                                        <input id="tramits_item_organ_id" name="tramits_item_organ_id" type="hidden" />
		                                        <div class="campo">                                           
		                                            <input id='tramits_item_organ_<c:out value="${lang}"/>' name='tramits_item_organ_<c:out value="${lang}"/>' type="text" class="nou" readonly="readonly" />
		                                        </div>
		                                        <div class="botones">
		                                            <div class="btnCambiar boton btnGenerico">
		                                                <a href="javascript:carregarArbreTotesUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','tramits_item_organ_id', 'tramits_item_organ_<c:out value="${lang}"/>');" class="btn consulta">		                                                		                                                
		                                                    <span><span><spring:message code='boto.canviarOrgan'/></span></span>
		                                                </a>
		                                            </div>
		                                            <div class="boton btnGenerico">                                    
		                                                <a href="javascript:EliminaArbreUA('tramits_item_organ_<c:out value="${lang}"/>', 'tramits_item_organ_id');" class="btn borrar">
		                                                    <span><span><spring:message code='boto.borrar'/></span></span>
		                                                </a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>                                
		                            </div>		                            
				                </div>
								</c:forEach>
				        	</div>	
				        </div>	        
				        <!-- /fila -->
					</fieldset>
				</div>
		
		        <!-- modul -->
		        <div class="modul">
		            <fieldset>
		                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
		                <legend><spring:message code='camp.canalsPresentacio'/></legend>
		                <div class="modul_continguts mostrat">
							<div class="fila">		                    
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="item_url_tramit"><spring:message code='camp.urlTramitExtern'/></label>
		                            </div>
		                            <div class="control">
		                                <input id="item_url_tramit" name="item_url_tramit" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>
		                    
		                    <div class="fila">
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="item_tramite_tramit"><spring:message code='camp.idTramit'/></label>
		                            </div>
		                            <div class="control">
		                                <input id="item_tramite_tramit" name="item_tramite_tramit" type="text" class="nou" />
		                            </div>
		                        </div>
		                        
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="item_version_tramit"><spring:message code='camp.versioTramit'/></label>
		                            </div>
		                            <div class="control">
		                                <input id="item_version_tramit" name="item_version_tramit" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>		                    
		                </div>
		            </fieldset>
		        </div>
		        <!-- /modul -->		
		        
		        <!-- modul -->
		        <div class="modul modulFinestretaUnica">
		            <fieldset>
		                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
		                <legend><spring:message code='camp.finestretaUnica'/></legend>
		                <div class="modul_continguts mostrat">		                    
		                    <div class="fila">
		                        <div class="element t50p">											                       
		                            <div class="etiqueta">
		                                <label for="item_codivuds_tramit"><spring:message code='camp.codiVUDS'/></label>
		                            </div>
		                            <div class="control">
		                            	<input id="item_id_codivuds_tramit" name="item_id_codivuds_tramit" type="hidden" />
		                            	<div class="campo">
		                                	<input id="item_codivuds_tramit" name="item_codivuds_tramit" type="text" class="nou" readonly="readonly" />
		                                </div>
                                        <div class="botones">
                                            <div class="btnCambiar boton btnGenerico">
                                                <a href="#" class="btn consulta">
                                                    <span><span><spring:message code='boto.seleccionarVUDS'/></span></span>
                                                </a>
                                            </div>
                                        </div>		                                
		                            </div>
		                        </div>
		                        
		                        <div class="element t50p">
		                            <div class="etiqueta">
		                                <label for="tramit_item_data_vuds"><spring:message code='camp.dataEnviamentVUDS'/></label>
		                            </div>		                            
		                            <div class="control">
		                                <input id="tramit_item_data_vuds" name="tramit_item_data_vuds" type="text" class="nou" />
		                            </div>
		                        </div>
		                    </div>		                    
		                </div>
		            </fieldset>
		        </div>
		        <!-- /modul -->		        		               
		                		               
	    	</div>
	    	
		    <!-- Men? de publicaci?n -->
		    <div class="modulLateral menuPublicacion">
		        <div class="modul publicacio">
		            <fieldset>
		                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
		                <legend>Publicaci?</legend>
		                <div class="modul_continguts mostrat">
		                    <!-- fila -->
		                    <div class="fila">
		                        <div class="element left">
		                            <div class="etiqueta">
		                                <label for="item_validacio_tramit"><spring:message code='txt.validacio'/></label>
		                            </div>
		                            <div class="control">
		                                <select name="item_validacio_tramit" id="item_validacio_tramit">                                                                
		                                   <option selected="selected" value=""><spring:message code='txt.validacio.tria'/></option>
		                                   <option selected="selected" value="1"><spring:message code='txt.validacio.publica'/></option>
		                                   <option value="2"><spring:message code='txt.validacio.interna'/></option>
		                                   <option value="3"><spring:message code='txt.validacio.reserva'/></option>                               
		                                </select>
		                            </div>
		                        </div>
		                        
		                        <div class="element right">
		                            <div class="etiqueta">
		                                <label for="item_moment_tramit"><spring:message code='txt.moment'/></label>
		                            </div>
		                            <div class="control">  
		                                <select name="item_moment_tramit" id="item_moment_tramit">                                                                
		                                   <option selected="selected" value="1"><spring:message code='txt.moment.inicialitzacio'/></option>
		                                   <option value="2"><spring:message code='txt.moment.instruccio'/></option>
		                                   <option value="3"><spring:message code='txt.moment.finalitzacio'/></option>                               
		                                </select>
		                            </div>
		                        </div>		                        
		                    </div>
		                    <!-- /fila -->
		                    <!-- fila -->
		                    <div class="fila">
                                <div class="element left">
                                    <div class="etiqueta">
                                        <label for="tramit_item_data_publicacio"><spring:message code='camp.dataPublicacio'/></label>
                                    </div>
                                    <div class="control">
                                        <input type="text" class="nou" name="tramit_item_data_publicacio" id="tramit_item_data_publicacio">
                                    </div>
                                </div>
                                <div class="element right">
                                    <div class="etiqueta">
                                        <label for="tramit_item_data_caducitat"><spring:message code='camp.dataCaducitat'/></label>
                                    </div>
                                    <div class="control">
                                        <input type="text" class="nou" name="tramit_item_data_caducitat" id="tramit_item_data_caducitat">
                                    </div>
                                </div>
                            </div>
                            <div class="fila">
                                <div class="element left">
                                    <div class="etiqueta">
                                        <label for="tramit_item_data_inici"><spring:message code='camp.dataInici'/></label>
                                    </div>
                                    <div class="control">
                                        <input type="text" class="nou" name="tramit_item_data_inici" id="tramit_item_data_inici">
                                    </div>
                                </div>
                                <div class="element right">
                                    <div class="etiqueta">
                                        <label for="tramit_item_data_tancament"><spring:message code='camp.dataTancament'/></label>
                                    </div>
                                    <div class="control">
                                        <input type="text" class="nou" name="tramit_item_data_tancament" id="tramit_item_data_tancament">
                                    </div>
                                </div>
                            </div>                        
		                    <!-- /fila -->
		                    <!-- botonera dalt -->
		                    <div class="botonera dalt">
		                      <ul>
		                          <li class="btnVolver impar">
		                              <a href="javascript:;"><span><span><spring:message code='boto.torna'/></span></span></a>
		                          </li>
		                          <li class="btnGuardar par">
		                              <a id="btnGuardarTraimt" class="btnGuardar" href="javascript:;"><span><span><spring:message code='boto.guarda'/></span></span></a>
		                          </li>
		                          <li style="" class="btnEliminar impar">
		                              <a class="btnEliminar" href="javascript:;"><span><span><spring:message code='boto.elimina'/></span></span></a>
		                          </li>                          
		                      </ul>
		                    </div>
		                    <!-- /botonera dalt -->
		                </div>
		            </fieldset>
		        </div>			        		         
		        
		        <!--  Tasas -->
		        <div class="modul" id="modul_taxes_tramits" style="display:none;">
		        	<fieldset>
		        		<a class="modul mostrat"><spring:message code='txt.amaga'/></a>
		        		<legend><spring:message code='tramit.taxes'/></legend>
		        		<div class="modul_continguts mostrat">
			        		<!-- modulTaxes -->
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop"> 
							<c:if test="${loop.first}">								
							<div class="modulTaxesTramits multilang">			        									
								<div class="seleccionats">							
									<div class="seleccionat cajaIdioma <c:out value="${lang}"/>">
							</c:if>
							
							<c:if test="${!loop.first}">
									<div class="<c:out value="${lang}"/> cajaIdioma">
							</c:if>
	                                    <p class="info"><spring:message code='txt.noHiHaTaxesRelacionades'/>.</p>
	                                    <div class="listaOrdenable"></div>																	
									</div>										
							<c:if test="${loop.last}">
	                                <div class="btnGenerico">
	                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixTaxa'/></span></span></a>
	                                </div>
								</div>
							</div>
							</c:if>
							</c:forEach>
		        		</div>
		        	</fieldset>
		        </div>

	            <!-- Formularios -->
	            <div class="modul" id="modul_formularis_tramits">
	                <fieldset>
	                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
	                    <legend><spring:message code='document.formularis'/></legend>                               
	                    <div class="modul_continguts mostrat">                                  
	                        <!-- modulFormularis -->
	                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
	                        <div class="modulFormularisTramit multilang">                            
	                            <ul class="idiomes" style="display:none;">                                								
									<c:forEach items="${idiomes_aplicacio}" var="lang">
									<li class='<c:out value="${lang}"/>'>
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca_abr'/>
										</c:when>

										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es_abr'/>
										</c:when>

										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en_abr'/>
										</c:when>

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de_abr'/>
										</c:when>

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr_abr'/>
										</c:when>										
									</c:choose>
									</li>
									</c:forEach>
	                            </ul>
	                            
								<div class="seleccionats">
									<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
									<c:if test="${loop.first}">
	                                <div class='seleccionat cajaIdioma <c:out value="${lang}"/>'>
									</c:if>
									<c:if test="${!loop.first}">
									<div class='<c:out value="${lang}"/> cajaIdioma'>
									</c:if>
	                                    <p class="info"><spring:message code='txt.noHiHaFormularisRelacionats'/>.</p>
	                                    <div class="listaOrdenable"></div>
	                                </div>								
									</c:forEach>
									
	                                <div class="btnGenerico">
	                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixFormulari'/></span></span></a>
	                                </div>								
								</div>								
	                        </div>
	                        <!-- /modulFormularis -->                                 
	                    </div>    
	                </fieldset>
	            </div>
	            <!-- /modul -->

	            <!-- Documentos -->
	            <div class="modul" id="modul_documents_tramits">
	                <fieldset>
	                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
	                    <legend><spring:message code='document.documentacioInformativa'/></legend>                               
	                    <div class="modul_continguts mostrat">                                  
	                        <!-- modulDocuments -->
	                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
	                        <div class="modulDocumentsTramit multilang">                            
	                            <ul class="idiomes" style="display:none;">                                
									<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
									<c:if test="${loop.first}">
									<li class='
									<c:out value="${lang}"/> seleccionat'>
									</c:if>									
									<c:if test="${!loop.first}">
									<li class='<c:out value="${lang}"/>'>
									</c:if>

									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca_abr'/>
										</c:when>

										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es_abr'/>
										</c:when>

										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en_abr'/>
										</c:when>

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de_abr'/>
										</c:when>

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr_abr'/>
										</c:when>										
									</c:choose>
									
									<spring:message code='txt.idioma.ca_abr'/>
									</li>
									</c:forEach>
	                            </ul>
	                            
		                            <div class="seleccionats">	                                
										<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">									
										<c:if test="${loop.first}">
		                                <div class='seleccionat cajaIdioma <c:out value="${lang}"/>'>
										</c:if>									
										<c:if test="${!loop.first}">
										<div class='<c:out value="${lang}"/> cajaIdioma'>
										</c:if>										
		                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
		                                    <div class="listaOrdenable"></div>									
									</div>																		
									</c:forEach>									
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
		        <!-- Documentacio Requerida -->
		        <div class="modul" id="modul_documents_requerits">
	                <fieldset>
	                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
	                    <legend><spring:message code='document.documentacioRequerida'/></legend>                               
	                    <div class="modul_continguts mostrat">                                  
	                        <!-- modulDocuments -->
	                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
	                        <div class="modulDocumentsRequerits multilang">                            
								<ul class="idiomes" style="display:none;">                                
									<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
									<c:if test="${loop.first}">
									<li class='
									<c:out value="${lang}"/> seleccionat'>
									</c:if>									
									<c:if test="${!loop.first}">
									<li class='<c:out value="${lang}"/>'>
									</c:if>

									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca_abr'/>
										</c:when>

										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es_abr'/>
										</c:when>

										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en_abr'/>
										</c:when>

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de_abr'/>
										</c:when>

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr_abr'/>
										</c:when>										
									</c:choose>
									
									<spring:message code='txt.idioma.ca_abr'/>
									</li>
									</c:forEach>
	                            </ul>
	                            
                            	<div class="seleccionats">	                                
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">									
									<c:if test="${loop.first}">
	                                <div class='seleccionat cajaIdioma <c:out value="${lang}"/>'>
									</c:if>									
									<c:if test="${!loop.first}">
									<div class='<c:out value="${lang}"/> cajaIdioma'>
									</c:if>										
	                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
	                                    <div class="listaOrdenable"></div>									
									</div>																		
								</c:forEach>									
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixDocument'/></span></span></a>
                                </div>                                  
	                        </div>
	                        <!-- /modulDocuments -->                                 
	                    </div>    
	                </fieldset>
	            </div>
		        <!-- /modul -->	     
		    </div>
		    <!-- /Men? de publicaci?n -->		    		                   
	</div>    
	</form>    
</div>                           
<!-- /escriptori_tramits -->

<!-- escriptori_taxes_tramits -->
<div id="escriptori_taxes_tramits" class="escriptori_detall">
	<script type="text/javascript">
    	var txtCodigoTasaTramiteObligatorio = "<spring:message code='personal.formulari_taxes_tramit.codi.obligatori'/>";    
    	var txtFormaPagoTasaObligatorio = "<spring:message code='personal.formulari_taxes_tramit.pagament.obligatori'/>";

        // dades formularis
        var FormulariDadesTaxaTramit = [
            { // Codi (Catala)
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "taxa_tramit_codi_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters": {
                    "maxim": 256,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error": {
                    "obligatori": txtCodigoTasaTramiteObligatorio,
                    "tipus": txtTituloNoSoloNumeros
                }
            },
            
            { // Forma de pagament (Catal?)
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "taxa_tramit_forma_pagament_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters": {
                    "maxim": 4000,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error": {
                    "obligatori": txtFormaPagoTasaObligatorio,
                    "tipus": txtTituloNoSoloNumeros
                }
            }
            
        ];
	</script>

    <form id="formGuardarTaxaTramit" action="" method="POST">
        <input type="hidden" name="idTramit" id="idTramit" />
        <input type="hidden" name="taxaTramitId" id="taxaTramitId" />        
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->                            
        <div id="modulTaxes" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='tramit.taxes'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>
							
                            <ul class="idiomes">                            
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirProcediment">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>                            

                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang"> 
                                <!-- ca -->
                                <div class="idioma <c:out value='${lang}'/>">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="taxa_tramit_codi_<c:out value='${lang}'/>"><spring:message code='camp.codi'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="taxa_tramit_codi_<c:out value='${lang}'/>" name="taxa_tramit_codi_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="taxa_tramit_descripcio_<c:out value='${lang}'/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="taxa_tramit_descripcio_<c:out value='${lang}'/>" name="taxa_tramit_descripcio_<c:out value='${lang}'/>" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="taxa_tramit_forma_pagament_<c:out value='${lang}'/>"><spring:message code='camp.formaPagament'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="taxa_tramit_forma_pagament_<c:out value='${lang}'/>" name="taxa_tramit_forma_pagament_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
								</c:forEach>
							</div>
                        <!-- /fila -->
						</div>
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
                                  <a id="btnVolver_taxes_tramit" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_taxes_tramit" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar_taxes_tramit" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
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
<!-- /escriptori_taxes_tramits -->

<!-- escriptori_formularis_tramits -->
<div id="escriptori_formularis_tramits" class="escriptori_detall">
    <script type="text/javascript">
        var txtTituloFormTramiteObligatorio = "<spring:message code='personal.formulari_document_tramit.titol.obligatori'/>";    
        var txtTituloNoSoloNumeros = "<spring:message code='personal.formulari_document_tramit.titol.no_nomes_numeros'/>";
               
        // dades formularis
        var FormulariDadesFormTramit = [
            { // Titol (Catala)
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "form_tramit_titol_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters": {
                    "maxim": 230,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error": {
                    "obligatori": txtTituloFormTramiteObligatorio,
                    "tipus": txtTituloNoSoloNumeros
                }
            }
        ];
    </script>
    
    <form id="formGuardarFormTramit" action="" method="POST">
        <input type="hidden" name="formTramitId" id="formTramitId" />
        <input type="hidden" name="tramitIdform" id="tramitIdform" />        
        <input type="hidden" name="tipDoc" id="tipDoc" value="1" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <!--div id="modulPrincipal" class="grupoModulosFormulario"-->                    
        <div id="modulFormularis" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.formularis'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>

                            <ul class="idiomes">                            
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirFormulariTramit">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>

                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value='${lang}'/>">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="form_tramit_titol_<c:out value='${lang}'/>"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="form_tramit_titol_<c:out value='${lang}'/>" name="form_tramit_titol_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="form_tramit_descripcio_<c:out value='${lang}'/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="form_tramit_descripcio_<c:out value='${lang}'/>" name="form_tramit_descripcio_<c:out value='${lang}'/>" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="form_tramit_arxiu_<c:out value='${lang}'/>"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_form_tramit_<c:out value='${lang}'/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="form_tramit_arxiu_<c:out value='${lang}'/>_delete" id="form_tramit_arxiu_<c:out value='${lang}'/>_delete" value="1"/>
                                                    <label for="form_tramit_arxiu_<c:out value='${lang}'/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="form_tramit_arxiu_<c:out value='${lang}'/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="form_tramit_arxiu_<c:out value='${lang}'/>" name="form_tramit_arxiu_<c:out value='${lang}'/>" type="file" class="nou" />
                                            </div>
                                        </div>                                                                                      
                                    </div>
                                </div>
								</c:forEach>
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
                                  <a id="btnVolver_formularis_tramit" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_formularis_tramit" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar_formularis_tramit" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
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
<!-- escriptori_formularis_tramits -->

<!-- escriptori_documents_tramits -->
<div id="escriptori_documents_tramits" class="escriptori_detall">
    <script type="text/javascript">
        var txtTituloDocTramiteObligatorio = "<spring:message code='personal.formulari_document_tramit.titol.obligatori'/>";    
        var txtTituloNoSoloNumeros = "<spring:message code='personal.formulari_document_tramit.titol.no_nomes_numeros'/>";
               
        // dades formularis
        var FormulariDadesDocTramit = [
            { // Titol (Catala)
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "doc_tramit_titol_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters": {
                    "maxim": 230,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error": {
                    "obligatori": txtTituloDocTramiteObligatorio,
                    "tipus": txtTituloNoSoloNumeros
                }
            }
        ];
    </script>
    
    <form id="formGuardarDocTramit" action="" method="POST">
        <input type="hidden" name="docTramitId" id="docTramitId" />
        <input type="hidden" name="tramitIddoc" id="tramitIddoc" />        
        <input type="hidden" name="tipDoc" id="tipDoc" value="0" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
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
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirDocumentTramit">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>

                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value='${lang}'/>">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_tramit_titol_<c:out value='${lang}'/>"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_tramit_titol_<c:out value='${lang}'/>" name="doc_tramit_titol_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_tramit_descripcio_<c:out value='${lang}'/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_tramit_descripcio_<c:out value='${lang}'/>" name="doc_tramit_descripcio_<c:out value='${lang}'/>" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_tramit_arxiu_<c:out value='${lang}'/>"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_tramit_<c:out value='${lang}'/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_tramit_arxiu_<c:out value='${lang}'/>_delete" id="doc_tramit_arxiu_<c:out value='${lang}'/>_delete" value="1"/>
                                                    <label for="doc_tramit_arxiu_<c:out value='${lang}'/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_tramit_arxiu_<c:out value='${lang}'/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_tramit_arxiu_<c:out value='${lang}'/>" name="doc_tramit_arxiu_<c:out value='${lang}'/>" type="file" class="nou" />
                                            </div>
                                        </div>                                                                                      
                                    </div>
                                </div>
								</c:forEach>                                
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
                                  <a id="btnVolver_documents_tramit" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_documents_tramit" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar_documents_tramit" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
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
<!-- escriptori_documents_tramits -->

<!-- escriptori documents requerits -->
<div id="escriptori_documents_requerits" class="escriptori_detall">
    <script type="text/javascript">
        var txtTituloDocRequeritObligatorio = "<spring:message code='personal.formulari_document_tramit.titol.obligatori'/>";    
        var txtDocCatalegObligatorio = "<spring:message code='personal.formulari_document_tramit.titol.obligatori'/>";
        var txtTituloNoSoloNumeros = "<spring:message code='personal.formulari_document_tramit.titol.no_nomes_numeros'/>";
        var txtTipusDocDocRequeritObligatorio = "<spring:message code='document_requerit.formulari_docreq_tramit.tipus_documentacio.obligatori'/>";
        var txtCatalegDocumentsObligatorio = "<spring:message code='document_requerit.formulari_docreq_tramit.cataleg_document.obligatori'/>";
                       
        // dades formularis
        var FormulariDadesDocRequerit = [
           	// Tipus Documentacio
            { 
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_tipdoc_" + '<c:out value="${idiomaVal}"/>',
                "obligatori": "si",
                "tipus": "numeric",
                "error": {
                    "obligatori": txtTipusDocDocRequeritObligatorio
                }
            }
        ];
    </script>
    <form id="formGuardarDocReq" action="" method="POST">
        <input type="hidden" name="docReqTramitId" id="docReqTramitId" />
        <input type="hidden" name="docReqId" id="docReqId" />          
        <input type="hidden" name="tipDoc" id="tipDoc" value="2" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <!--div id="modulPrincipal" class="grupoModulosFormulario"-->                    
        <div id="modulDocuments" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.documents_requerits'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>
							<ul class="idiomes">                            
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<li class="idioma"><a href="javascript:;" class='<c:out value="${lang}"/>'>								
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en'/>
										</c:when>										

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr'/>
										</c:when>										

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>		
										<c:otherwise></c:otherwise>								
									</c:choose>
									</a></li>									
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirRequerit">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
                            <div class="idiomes">
                            
                            <c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value='${lang}'/>">
                                    <div class="fila">
                                    	 <div class="element t21p">
			                            	 <div class="etiqueta">
			                                	 <label for="item_tipdoc_<c:out value='${lang}'/>"><spring:message code='document_requerit.formulari_docreq_tramit.tipus_documentacio'/></label>
			                             	</div>
			                             	<div class="control select">
			                                	 <select id="item_tipdoc_<c:out value='${lang}'/>" class="nou" name="item_tipdoc_<c:out value='${lang}'/>">
			                                    	<option value="1"><spring:message code='document_requerit.formulari_docreq_tramit.tipus_documentacio.valor_comu'/></option>
			                                    	<option value="2"><spring:message code='document_requerit.formulari_docreq_tramit.tipus_documentacio.valor_especifica'/></option>
			                                 	</select>
			                             	</div>
				                         </div>
                                    </div>
									<div class="fila" id="seccio_doc_cataleg_<c:out value='${lang}'/>">
			                         	<div class="element t50p">
			                            	 <div class="etiqueta">
			                                	 <label for="item_cataleg_<c:out value='${lang}'/>"><spring:message code='camp.cataleg_documents'/>
			                                	 	<c:choose> 
				                                	 <c:when test="${lang eq 'ca'}">
				                                	 	<span class="obligatori">*</span> 
				                                	 </c:when>
				                                	</c:choose>
			                                	 </label>
			                             	</div>
			                             	<div class="control select">
			                                	<select id="item_cataleg_<c:out value='${lang}'/>" name="item_cataleg_<c:out value='${lang}'/>" class="nou">
			                                    	<option value="" selected="selected"><spring:message code='camp.cap'/></option>
			                                        	<c:forEach items="${cataleg}" var="cataleg">
			                                            	<option value="<c:out value="${cataleg.id}"/>"><c:out value="${cataleg.nom}"/></option>
			                                           	</c:forEach>
			                                  	</select>
			                               	</div>
			                         	</div>
			                         </div>                                    
                                    <div class="fila"  id="seccio_doc_especific_<c:out value='${lang}'/>" >
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_requerit_titol_<c:out value='${lang}'/>"><spring:message code='camp.titol'/>
                                                	<c:choose> 
                                                     <c:when test="${lang eq 'ca'}">
				                                	 	<span class="obligatori">*</span> 
				                                	 </c:when>
				                                	</c:choose>
			                                	 </label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_requerit_titol_<c:out value='${lang}'/>" name="doc_requerit_titol_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
									<div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_requerit_descripcio_<c:out value='${lang}'/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_requerit_descripcio_<c:out value='${lang}'/>" name="doc_requerit_descripcio_<c:out value='${lang}'/>" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="fila" id="seccio_excepcions_<c:out value='${lang}'/>">
			                         	<div class="element t50p">
			  	                        	<div class="etiqueta">
			                                	 <label for="item_check_excepcio"></label>
			                             	</div>
                                            <div class="control">
                                                <input id="item_check_excepcio_<c:out value='${lang}'/>" name="item_check_excepcio_<c:out value='${lang}'/>" type="checkbox" value="on" class="nou" />
                                                <label for="item_check_excepcio_<c:out value='${lang}'/>"><spring:message code='camp.excepcio_documentacio'/></label>
                                            </div>                                            
                                        </div>
			                         	<div class="element t50p">
			                            	 <div class="etiqueta">
			                                	 <label for="item_excepcio_<c:out value='${lang}'/>"><spring:message code='catdoc.formulari.causes_excepcio'/> <span class="obligatori"></span></label>
			                             	</div>
			                             	<div class="control select">
			                                	<select id="item_excepcio_<c:out value='${lang}'/>" name="item_excepcio_<c:out value='${lang}'/>" class="nou">
			                                    	<option value="" selected="selected"><spring:message code='camp.cap'/></option>
			                                        	<c:forEach items="${excepcions}" var="excepcio">
			                                            	<option value="<c:out value="${excepcio.id}"/>"><c:out value="${excepcio.nom}"/></option>
			                                           	</c:forEach>
			                                  	</select>
			                               	</div>
			                         	</div>
			                         </div>
                                </div>
								</c:forEach>
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
                                  <a id="btnVolver_documents_requerits" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_documents_requerits" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar_documents_requerits" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
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
<!-- /escriptori documents requerits -->