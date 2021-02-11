<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>

<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/cataleg_serveis.css?versio=${rolsac_einarevision}"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_documents.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/modul_normativa.css"/>" media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />

<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/autoresize.jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-timepicker-addon.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/serveis.js?versio=${rolsac_einarevision}' />"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ajax.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents_lopd.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_public_objectiu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_normativa.js?versio=${rolsac_einarevision}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_fetsVitals_serveis.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_auditories.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/buscador_servicios.js'/>"></script>

<script type="text/javascript">

	var comunActivo = '<c:out value="${comunes}"/>';
	var comunActivoTramite = '<c:out value="${comunes}"/>';
	var comunUA = '<c:out value="${comunesUA}"/>';
	var comunUAESP = '<c:out value="${comunesUAESP}"/>';
	var idUAMollapa = '<c:out value="${idUA}"/>';
    var nomUAMollapa = '<c:out value="${nomUA}"/>';
    var idiomas = '<c:out value="${idiomasListado}"/>';
    var desactivarMensajeComun = false;
    var lopdFinalidad = '<c:out value="${lopdFinalidad}"/>';
	var lopdFinalidadESP = '<c:out value="${lopdFinalidadESP}"/>';
	var lopdDestinatario = '<c:out value="${lopdDestinatario}"/>';
	var lopdDestinatarioESP = '<c:out value="${lopdDestinatarioESP}"/>';
	var lopdDerechos = '<c:out value="${lopdDerechos}"/>';
	var lopdDerechosESP = '<c:out value="${lopdDerechosESP}"/>';
	var lopdResponsableComun = '<c:out value="${lopdResponsableComun}"/>';
	var lopdResponsableComunESP = '<c:out value="${lopdResponsableComunESP}"/>';
	var lopdResponsableNOComun = '';
	var lopdResponsableNOComunESP = '';
	var lopd_legitimacion_pordefecto =  '<c:out value="${lopdLegitimacionPorDefecto}"/>';
	var lopdPlantilla = '<c:out value="${lopdPlantilla}"/>';
	var lopdPlantillaESP = '<c:out value="${lopdPlantillaESP}"/>';

    var pagLlistat = '<c:url value="/catalegServeis/llistat.do" />';
    var pagExportar = '<c:url value="/catalegServeis/exportar.do" />';
	var pagDetall = '<c:url value="/catalegServeis/pagDetall.do" />';
    var pagGuardar = '<c:url value="/catalegServeis/guardar.do" />';
    var pagEsborrar = '<c:url value="/catalegServeis/esborrarServei.do" />';
    var seccioNormatives = '<c:url value="/normativa/cercarNormativesProcServ.do" />';
    var pagGuardarDoc = '<c:url value="/catalegServeis/guardarDocument.do" />';
    var pagGuardarDocLopd = '<c:url value="/catalegServeis/guardarDocumentLopd.do" />';
    var pagCarregarDoc = '<c:url value="/catalegServeis/carregarDocument.do" />';
    var pagCarregarDocLopd = '<c:url value="/catalegServeis/carregarDocumentLopd.do" />';
    var pagAuditories = '<c:url value="/auditories/llistat.do" />';
    var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
    var pagTraduir = '<c:url value="/catalegServeis/traduir.do" />';
    var pagTraduirDocument = '<c:url value="/documents/traduir.do" />';
    var pagListarHechosVitales = '<c:url value="/catalegServeis/listarHechosVitales.do" />';
    var pagLopdResponsable = '<c:url value="/catalegServeis/getLopdResponsable.do" />';
    var pagNormativaVigentes = '<c:url value="/catalegServeis/checkNormativaVigente.do" />';
    var urlPrevisualizarServicio = '<c:out value="${urlPrevisualitzacio}"/>';
    var modulos = '<c:url value="/catalegServeis/modulos.do" />';

    //texts
    var txtEsborrarCorrecte = "<spring:message code='txt.servei_esborrat_correcte'/>";
    var txtEsborrarIncorrecte = "";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtLlistaItem = "<spring:message code='txt.servei'/>";
    var txtLlistaItems = "<spring:message code='txt.serveis'/>";
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
    var txtLlistaItems = "<spring:message code='txt.serveis'/>";
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
    //var txtFamilia = "<spring:message code='txt.familia'/>";
    var txtFamilia = "<spring:message code='camp.tipo.servicio'/>";
    var txtFechaActualizacion = "<spring:message code='camp.dataActualitzacio'/>";
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtSeleccionat = "<spring:message code='txt.seleccionat'/>";
	var txtProcessant = "<spring:message code='txt.processant'/>";

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
    var txtErrorTamanyoFitxer ="<spring:message code='error.fitxer.tamany_nom'/>";

    var txtTaxaCreadaCorrecte = "<spring:message code='txt.taxa_creada_correcte'/>";
    var txtTaxaModificadaCorrecte = "<spring:message code='txt.taxa_modificada_correcte'/>";

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
    var txtNormativaDerogadaTitol= "<spring:message code='serv.error.normativa.derogadas.titulo'/>";
    var txtNormativaDerogada= "<spring:message code='serv.error.normativa.derogadas'/>";

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

    var txtColUsuario = "<spring:message code='txt.auditoria.usu'/>";
    var txtColNombre = "<spring:message code='txt.auditoria.nombre'/>";
    var txtColFecha = "<spring:message code='txt.auditoria.fecha'/>";
    var txtColOperacion = "<spring:message code='txt.auditoria.operacion'/>";

    var txtEstadoSiaB 	= "<spring:message code='txt.sia.estado.B'/>";
    var txtEstadoSiaA 	= "<spring:message code='txt.sia.estado.A'/>";
    var txtEstadoSiaRC 	= "<spring:message code='txt.sia.estado.RC'/>";
    var txtEstadoSiaM 	= "<spring:message code='txt.sia.estado.M'/>";
    var txtSI 			= "<spring:message code='txt.si'/>";
	var txtNO 			= "<spring:message code='txt.no'/>";
	var txtComun 		= "<spring:message code='camp.tipo.comun'/>";


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
    var txtRequisitsObligatori = "<spring:message code= 'tramit.formulari.requisits.obligatori'/>";
    var txtTerminiObligatori   = "<spring:message code= 'tramit.formulari.termini.obligatori'/>";
    var txtModelObligatori   = "<spring:message code= 'tramit.formulari.models.obligatori'/>";
    var txtCanalObligatori   = "<spring:message code= 'tramit.formulari.canal.obligatori'/>";
    var txtDataPublicacioObligatori = "<spring:message code='serv.formulari.error.fechapublicacion.obligatori'/>";
    var txtDataIniciObligatori = "<spring:message code='serv.formulari.error.datainici.obligatori'/>";
    var txtDataPublicacioPosterior = "<spring:message code='serv.formulari.error.fechapublicacion.posterior'/>";
    var txtDataIniciPosterior = "<spring:message code='serv.formulari.error.datainici.posterior'/>";

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
                    "tipus": "<spring:message code='personal.formulari_document.titol.no_nomes_numeros'/>"
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
                    "obligatori": "<spring:message code='serv.formulari.error.estat.obligatori'/>"
                }
        },

        // Servei Responsable
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_servei_responsable_id",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='serv.formulari.error.servei.responsable.obligatori'/>"
                }
        },

     // Objecte
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_objeto_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='serv.formulari.error.objecte.obligatori'/>"
                }
        },




        // Responsable
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_responsable_nombre",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='serv.formulari.error.responsable.obligatori'/>"
                }
        },

        // Destinataris
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_destinatarios_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='serv.formulari.error.destinataris.obligatori'/>"
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

        // Organisme responsable
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_organ_instructor_id",
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


        // Fecha publicacion
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_data_publicacion",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": txtDataPublicacioObligatori
                }
        },
        // LOPD Responsable
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_lopd_responsable",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.lopdresponsable.obligatori'/>"
                }
        },

        // LOPD Legitmacion
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_lopd_legitimacion",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.lopdlegitimacion.obligatori'/>"
                }
        },

        // LOPD Finalitat
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor":  "item_lopd_finalidad_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.lopdfinalidad.obligatori'/>"
                }
        },

        // LOPD Finalitat
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor":  "item_lopd_destinatario_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.lopddestintario.obligatori'/>"
                }
        },


        // LOPD Finalitat
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor":  "item_lopd_derechos_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": "<spring:message code='proc.formulari.error.lopdderechos.obligatori'/>"
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
            <li id="btnNuevaFicha" class="opcions nuevo">
            	<a href="javascript:;" class="btn nou"><span><span><spring:message code='serv.crea.nou'/></span></span></a>
            </li>
        </c:if>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='serv.executant'/></p>
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
                                    <label for="cerca_sia"><spring:message code='camp.codi_sia'/></label>
                                </div>
                                <div class="control">
                                	<input id="cerca_sia" name="cerca_sia" type="text"/>
                                </div>
                            </div>
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_bolcat_sia"><spring:message code='camp.bolcat_sia'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_bolcat_sia" name="cerca_bolcat_sia" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="-1"><spring:message code='camp.cap'/></option>
                                        <option value="A"><spring:message code='txt.sia.estado.A'/></option>
                                        <option value="B"><spring:message code='txt.sia.estado.B'/></option>
                                    </select>
                                </div>
                            </div>

                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_publicObjectiu"><spring:message code='camp.publicObjectiu'/></label>
                                </div>
                                <div class="control select">
                                    <select id="cerca_publicObjectiu" name="cerca_publicObjectiu" class="publicObjectiu">
                                        <option value=""><spring:message code='camp.tria.opcio'/></option>
                                        <option value="-1"><spring:message code='camp.cap'/></option>
                                        <c:forEach items="${llistaPublicsObjectiu}" var="publicObjectiu">
                                            <option value='<c:out value="${publicObjectiu.id}" />'><c:out value="${publicObjectiu.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                        </div>


                        <div class="fila">

                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_materia"><spring:message code='fitxes.llistat.materia'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_materia" name="cerca_materia" class="t8">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <option value="-1"><spring:message code='camp.cap'/></option>
                                        <c:forEach items="${llistaMateries}" var="materia">
                                            <option value="<c:out value="${materia.id}"/>"><c:out value="${materia.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
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
                                    <label for="cerca_plataforma"><spring:message code='camp.cerca_plataforma'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_plataforma" name="cerca_plataforma">
                                        <option value=""><spring:message code='camp.cap'/></option>
                                        <c:forEach items="${llistaPlataformas}" var="plataforma">
                                            <option value='<c:out value="${plataforma.id}" />'><c:out value="${plataforma.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                        </div>

                        <div class="fila">

							<div id="divComun" class="element t25">

                            	<c:if test="${comunes}">
	                                <div class="etiqueta">
	                                    <label for="cerca_comun"><spring:message code='camp.cerca_comun'/></label>
	                                </div>
	                                <div class="control">
	                                    <select id="cerca_comun" name="cerca_comun" class="t8">
	                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
	                                        <option value="0"><spring:message code='txt.no'/></option>
	                                        <option value="1"><spring:message code='txt.si'/></option>
	                                    </select>
	                                </div>
                                </c:if>
                            </div>

                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_plataforma"><spring:message code='camp.cerca_plataforma'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_plataforma" name="cerca_plataforma">
                                        <option value=""><spring:message code='camp.cap'/></option>
                                        <c:forEach items="${llistaPlataformas}" var="plataforma">
                                            <option value='<c:out value="${plataforma.id}" />'><c:out value="${plataforma.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                        </div>

						<div class="fila">
                            <div class="botonera noClear">
								 <div class="boton btnGenerico">
                                  <a id="btnExportar" href="javascript:void(0)" class="btn exportar"><span><span>Exportar</span></span></a>
                                </div>
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
        <h2><spring:message code='txt.detallServei'/></h2>
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
                                <c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <li class="idioma">
                                        <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
                                    </li>
                                </c:forEach>

                                <c:if test="${traductorActivo}">
	                                <li class="traduix btnGenerico" id="botoTraduirServei">
	                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
	                                </li>
                                </c:if>
                            </ul>

                            <div id="modul_continguts_idiomas" class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
                                <!-- Camps per cada idioma -->
                                <div class="idioma <c:out value="${lang}" />">
                                    <div class="fila">
                                        <div class="element t45p">
                                            <div class="etiqueta">
                                                <label for="item_codigo_servicio"><spring:message code='txt.codi'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_servicio" name="item_codigo_servicio" type="text" value=""/>
                                            </div>
                                        </div>
                                        <div id="caja_item_codigo_sia" class="element t20p">
                                            <div class="etiqueta">
                                                <label for="item_codigo_sia"><spring:message code='camp.codi_sia'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codigo_sia" name="item_codigo_sia" type="text" class="nou" readonly="readonly" />
                                            </div>
                                        </div>
                                        <div id="caja_item_estado_sia" class="element t15p">
                                            <div class="etiqueta">
                                                <label for="item_estado_sia"><spring:message code='camp.codi_sia_estat'/></label>
                                            </div>
                                            <div class="control">
                                           		  <input id="item_estado_sia" name="item_estado_sia" type="text" class="nou" readonly="readonly" />

                                            </div>
                                        </div>
                                        <div id="caja_item_fecha_sia" class="element t20p">
                                            <div class="etiqueta">
                                                <label for="item_fecha_sia"><spring:message code='camp.codi_sia_data'/></label>
                                            </div>
                                            <div class="control">
                                           		  <input id="item_fecha_sia" name="item_fecha_sia" type="text" class="nou" readonly="readonly" />

                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_<c:out value="${lang}" />"><spring:message code='camp.nomServei'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_<c:out value="${lang}" />" name="item_nom_<c:out value="${lang}" />" type="text" class="nou inputGris" />
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
                                                <label for="item_objeto_<c:out value="${lang}" />"><spring:message code='camp.objecte'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_objeto_<c:out value="${lang}" />" name="item_objeto_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila" >
                                         <div class="element t99p">
                                             <div class="etiqueta">
                                               <label for="item_destinatarios_<c:out value="${lang}" />"><spring:message code='camp.destinataris'/></label>
                                             </div>
                                             <div class="control">
                                                <textarea id="item_destinatarios_<c:out value="${lang}" />"
                                                    name="item_destinatarios_<c:out value="${lang}" />" cols="50" rows="2" class="nou"></textarea>
                                             </div>
                                         </div>
                                     </div>
 									<div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_requisitos_<c:out value="${lang}" />"><spring:message code='camp.requisits'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_requisitos_<c:out value="${lang}" />" name="item_requisitos_<c:out value="${lang}" />"
                                                    cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="fila">
                                        <div id="divComun2" class="element checkbox">
                                            <div class="control">
                                                <input id="item_comun" name="item_comun" type="checkbox" class="nou" />
                                            </div>
                                            <div class="etiqueta">
                                                <label for="item_comun"><spring:message code='camp.servicio.comun'/></label>
                                            </div>
                                        </div>
                                    </div>

                                     <div class="fila">
                                        <div class="element organoResponsable">
                                            <div class="etiqueta">
                                                <label for="item_organ_instructor"><spring:message code='camp.organResponsable'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_organ_instructor_id" name="item_organ_instructor_id" type="hidden" />
                                                <div class="campo">
                                               		<c:if test="${lang == 'es'}">
				                                        <input id="item_organ_comun" name="item_organ_comun" value="<c:out value="${comunesUAESP}"/>" type="text" class="nou" readonly="readonly" />
                                                	</c:if>
				                                    <c:if test="${lang != 'es'}">
				                                        <input id="item_organ_comun" name="item_organ_comun" value="<c:out value="${comunesUA}"/>" type="text" class="nou" readonly="readonly" />
                                                	</c:if>
				                                    <input id="item_organ_instructor" name="item_organ_instructor" type="text" class="nou" readonly="readonly" />
                                                </div>
                                                <div id="item_organ_botones" class="botones">
                                                    <div class="btnCambiar boton btnGenerico">
                                                        <a href="javascript:carregarArbreUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','item_organ_instructor_id', 'item_organ_instructor');" class="btn consulta">
                                                            <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                                        </a>
                                                    </div>
                                                    <div class="boton btnGenerico">
                                                        <a href="javascript:EliminaArbreUA('item_organ_instructor', 'item_organ_instructor_id');" class="btn borrar">
                                                            <span><span><spring:message code='boto.esborrar'/></span></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

									<!--    #349 -->
                                     <div class="fila">
				                            <div class="element t99p">
				                                <div class="etiqueta">
				                                    <label for="item_observaciones_<c:out value="${lang}" />"><spring:message code='camp.observacions'/></label>
				                                </div>
				                                <div class="control">
				                                    <textarea id="item_observaciones_<c:out value="${lang}" />"
				                                        name="item_observaciones_<c:out value="${lang}" />" cols="50" rows="2" class="nou"></textarea>
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

            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='serv.dades.contacte'/></legend>
                    <div class="modul_continguts mostrat">
                    <div class="fila">
                          <div class="element organoResponsable">
                              <div class="etiqueta">
                                  <label for="item_servei_responsable"><spring:message code='serv.servei.responsable'/></label>
                              </div>
                              <div class="control">
                                  <input id="item_servei_responsable_id" name="item_servei_responsable_id" type="hidden" />
                                  <div class="campo t50p">
                                      <input id="item_servei_responsable" name="item_servei_responsable" type="text" class="nou inputGris" readonly="readonly" />
                                  </div>
                                  <div class="botones">
                                      <div class="btnCambiar boton btnGenerico">
                                          <a href="javascript:carregarArbreUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','item_servei_responsable_id', 'item_servei_responsable');" class="btn consulta">
                                              <span><span><spring:message code='boto.canviarOrgan'/></span></span>
                                          </a>
                                      </div>
                                      <div class="boton btnGenerico">
                                          <a href="javascript:EliminaArbreUA('item_servei_responsable', 'item_servei_responsable_id');" class="btn borrar">
                                              <span><span><spring:message code='boto.esborrar'/></span></span>
                                          </a>
                                      </div>
                                  </div>
                              </div>
                          </div>
                    </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_responsable_nombre"><spring:message code='camp.responsable.servei'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_responsable_nombre" name="item_responsable_nombre" type="text" class="nou inputGris" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_email"><spring:message code='serv.formulari.email'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_email" name="item_email" type="text" class="inputGris" />
                                </div>

                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_telefon"><spring:message code='serv.formulari.telefon'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_telefon" name="item_telefon" type="text" class="inputGris" />
                                </div>

                            </div>
                        </div>

                    </div>
                </fieldset>
            </div>
            <!-- /modul -->


            <!-- modul tramit -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='serv.dades.tramit'/></legend>
                    <div class="modul_tramit mostrat idiomes">
						<div class="fila">
							<div class="element t20p">
								<div class="etiqueta">
									<label for="item_check_tramit_presencial"><spring:message
											code='camp.tramitPresencial' /></label>
								</div>
								<div class="control">
									<input id="item_check_tramit_presencial"
										name="item_check_tramit_presencial" type="checkbox"
										class="nou" />
								</div>
							</div>
							<div class="element t20p">
								<div class="etiqueta">
									<label for="item_check_tramit_telematico"><spring:message
											code='camp.tramitTelematico' /></label>
								</div>
								<div class="control">
									<input id="item_check_tramit_telematico"
										name="item_check_tramit_telematico" type="checkbox"
										class="nou" />
								</div>
							</div>
							<div class="element t20p">
								<div class="etiqueta">
									<label for="item_check_tramit_telefonico"><spring:message
											code='camp.tramitTelefonico' /></label>
								</div>
								<div class="control">
									<input id="item_check_tramit_telefonico"
										name="item_check_tramit_telefonico" type="checkbox"
										class="nou" />
								</div>
							</div>
						</div>
						<div class="fila">
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
                            	<!-- Camps per cada idioma -->
		                        <div class="idioma <c:out value="${lang}" />">
		                            <div class="element t99p">
		                                <div class="etiqueta">
		                                    <label for="item_tramite_url_<c:out value="${lang}" />"><spring:message code='serv.tramit.url'/></label>
		                                </div>
		                                <div class="control">
		                                    <input id="item_tramite_url_<c:out value="${lang}" />" name="item_tramite_url_<c:out value="${lang}" />" type="text" class="nou inputGris" />
		                                </div>
		                            </div>
		                         </div>
		                 	</c:forEach>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_tramite_id"><spring:message code='serv.tramit.id'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_tramite_id" name="item_tramite_id" type="text" class="inputGris" />
                                </div>

                            </div>
                            <div class="element t25p">
                                <div class="etiqueta">
                                    <label for="item_tramite_version"><spring:message code='serv.tramit.versio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_tramite_version" name="item_tramite_version" type="text" class="inputGris" />
                                </div>

                            </div>

                            <div class="element t25p">
	                            <div class="etiqueta">
	                                <label for="item_plataforma"><spring:message code='camp.plataforma'/></label>
	                            </div>
	                            <div class="control">
	                            	<select id="item_plataforma" name="item_plataforma">
                                   		<option value=""><spring:message code='camp.tria.opcio'/></option>
                                       	<c:forEach items="${llistaPlataformas}" var="plataforma">
                                           	<option value='<c:out value="${plataforma.id}" />'><c:out value="${plataforma.nom}" /></option>
                                       	</c:forEach>
                                       </select>
	                            </div>
	                        </div>
                        </div>

						<div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="item_parametros"><spring:message code='camp.parametros'/></label>
		                            </div>
		                            <div class="control">
		                                <input id="item_parametros" name="item_parametros" type="text" class="nou" />
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
		                <legend><spring:message code='proc.dades.lopd.titulo'/></legend>
		                <div class="modul_continguts mostrat">

							<!-- fila -->
							<div class="fila">
								<div class="element t99p">
									 <div class="etiqueta">
										  <label for="item_lopd_legitimacion"><spring:message code='proc.dades.lopd.responsable'/></label>
									  </div>
									  <div class="control">
									  		<input id="item_lopd_responsable" name="item_lopd_responsable" type="text" class="nou soloLectura" readonly="readonly" />
									  </div>
								</div>
							</div>


		                  	<!-- fila -->
							<div class="fila">
								<div class="element t99p multilang">
									<c:forEach items="${idiomes_aplicacio}" var="lang">
										<div class="campoIdioma <c:out value="${lang}"/>">
											<div class="etiqueta"><label for="item_lopd_finalidad_<c:out value="${lang}"/>"><spring:message code='proc.dades.lopd.finalidad'/></label></div>
											<div class="control">
												 <textarea id="item_lopd_finalidad_<c:out value="${lang}" />"
				                                        name="item_lopd_finalidad_<c:out value="${lang}" />" cols="50" rows="2" class="nou"></textarea>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

		                    <!-- fila -->
		                    <div class="fila">
		                    	<div class="element t49p">
									  <div class="etiqueta">
										  <label for="item_lopd_legitimacion"><spring:message code='proc.dades.lopd.legitimacion'/></label>
									  </div>
									  <div class="control select">
                                            <select id="item_lopd_legitimacion" name="item_lopd_legitimacion">
                                          	<c:forEach items="${llistaLopdLegitimacion}" var="legit">
                                    			<option value='<c:out value="${legit.id}" />'><c:out value="${legit.nom}" /></option>
                               				 </c:forEach>
                           				 </select>
                                       </div>
								</div>
		                    </div>



							<!-- fila -->
							<div class="fila">
								<div class="element t99p multilang">
									<c:forEach items="${idiomes_aplicacio}" var="lang">
										<div class="campoIdioma <c:out value="${lang}"/>">
											<div class="etiqueta"><label for="item_lopd_destinatario_<c:out value="${lang}"/>"><spring:message code='proc.dades.lopd.destinatario'/></label></div>
											<div class="control">
												<textarea id="item_lopd_destinatario_<c:out value="${lang}" />"
				                                        name="item_lopd_destinatario_<c:out value="${lang}" />" cols="50" rows="2" class="nou"></textarea>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<!-- fila -->
							<div class="fila">
								<div class="element t99p multilang">
									<c:forEach items="${idiomes_aplicacio}" var="lang">
										<div class="campoIdioma <c:out value="${lang}"/>">
											<div class="etiqueta"><label for="item_lopd_derechos_<c:out value="${lang}"/>"><spring:message code='proc.dades.lopd.derechos'/></label></div>
											<div class="control">
												<textarea id="item_lopd_derechos_<c:out value="${lang}" />"
				                                        name="item_lopd_derechos_<c:out value="${lang}" />" cols="50" rows="2" class="nou soloLectura" readonly="readonly"></textarea>
											</div>
										</div>
									</c:forEach>
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

	        <div id="modulAuditories" class="modul auditorias">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.AUDITORIES'/></legend>
                    <div class="modul_continguts mostrat">
                       <p class="executant"><spring:message code='txt.carregant'/></p>
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
                                    <label for="item_data_publicacion"><spring:message code='camp.dataPublicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacion" name="item_data_publicacion" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_despublicacion"><spring:message code='camp.dataDespublicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_despublicacion" name="item_data_despublicacion" type="text" class="nou" />
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
                              <li id="liEnvioSiaNoActivo" class="btnPrevisualizar par">
	                                 <a id="btnEnvioSiaNoActivo" href="javascript:;" class="btn previsualitza"><span><span><spring:message code='boto.envio_sia_no_activo'/></span></span></a>
	                          </li>
                          </ul>
                        </div>
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->


            <!-- modul Public Objectiu -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='unitatadm.formulari.publics'/> *</legend>
                    <div class="modul_continguts mostrat">

                        <!-- modulPublicObjectiu -->
                        <div class="ModulPublicObjectiu selectorChecks">

                            <input name="modul_public_modificat" type="hidden" value="0" />
                            <input type="hidden" name="modul_public_intern" value='<c:out value="${publicObjectiuIntern}" />' />

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
           <!-- /modul -->



            <!-- modul taxa -->
            <div class="modul taxa">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.taxa'/></legend>
                    <div class="modul_taxa mostrat">

                        <!-- fila -->
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_tasa_url"><spring:message code='camp.url'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_tasa_url" name="item_tasa_url" placeholder="http://URL.es" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->

            <!-- modul materies-->
            <div class="modul invisible" id="modul_materies">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='unitatadm.formulari.materies'/> <span class="obligatori">*</span></legend>
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
                                        <a id="btnFinalizar_materias" class="btn finalitza" href="javascript:;"
                                        		action="<c:url value="/catalegServeis/guardarMaterias.do" />">
                                        	<span><span><spring:message code='boto.finalitza'/></span></span>
                                       	</a>
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
            <div class="modul invisible" id="modul_normatives">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.normativaServeiRelacionada'/></legend>
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
              <div class="modul invisible" id="modul_documents">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='serv.documents.relacionats'/> </legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulDocuments -->
                        <div class="modulDocuments multilang">
                            <input id="modulo_documents_modificado" type="hidden" name="modulo_documents_modificado" value="0" />
                            <ul class="idiomes">
								<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <c:if test="${loop.first}">
                                        <li class="<c:out value="${llengua.lang}" /> seleccionat">
                                    </c:if>
                                    <c:if test="${!loop.first}">
                                        <li class="<c:out value='${llengua.lang}'/>">
                                    </c:if>
                                    <c:out value="${llengua.lang}" />
                                    </li>
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
                                <p style="clear: both; margin-bottom: 10px;"/><!-- Separador -->
	                            <div class="btnGenerico">
	                                <a id="btnGuardar_documentos" href="javascript:;" class="btn guarda important lista-simple-documentos" style="display: none"
	                            			action="<c:url value="/catalegServeis/guardarDocumentosRelacionados.do" />">
	                           			<span><span><spring:message code='boto.guarda'/></span></span>
	                            	</a>
	                            </div>
                            </div>
                        <!-- /modulDocuments -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->

             <!-- modul -->
              <div class="modul invisible" id="modul_documents_lopd">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='proc.documents.lopd'/> </legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulDocuments -->
                        <div id="modulDocumentsLopd" class="modulDocumentsLopd multilang">
                            <input id="modulo_documents_modificado_lopd" type="hidden" name="modulo_documents_modificado_lopd" value="0" />
                            <ul class="idiomes">
								<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <c:if test="${loop.first}">
                                        <li class="<c:out value="${llengua.lang}" /> seleccionat">
                                    </c:if>
                                    <c:if test="${!loop.first}">
                                        <li class="<c:out value='${llengua.lang}'/>">
                                    </c:if>
                                    <c:out value="${llengua.lang}" />
                                    </li>
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
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestionDocument'/></span></span></a>
                                </div>
                                <p style="clear: both; margin-bottom: 10px;"/><!-- Separador -->
	                            <div class="btnGenerico">
	                                <a id="btnGuardar_documentos_lopd" href="javascript:;" class="btn guarda important lista-simple-documentos-lopd" style="display: none"
	                            			action="<c:url value="/catalegServeis/guardarDocumentosRelacionadosLopd.do" />">
	                           			<span><span><spring:message code='boto.guarda'/></span></span>
	                            	</a>
	                            </div>
                            </div>
                        <!-- /modulDocuments -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->

            <!-- modul -->
            <div style="display: none">
            <div class="modul invisible" id="fetsVitals" >
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='fitxes.fets_vitals'/></legend>
                    <div class="modul_continguts mostrat" id="modul_fetsVitals">
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
                                        <a id="btnFinalizar_hechosVitales" href="javascript:;" class="btn finalitza"
                                        		action="<c:url value="/catalegServeis/guardarHechosVitales.do" />">
                                        	<span><span><spring:message code='boto.finalitza'/></span></span>
                                       	</a>
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
            </div>
            <!-- /modul -->

    </div>
    <!-- /escriptori_detall -->
</form></div>


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
								<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <li class="idioma">
                                        <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
                                    </li>
                                </c:forEach>

                                <c:if test="${traductorActivo}">
	                                <li class="traduix btnGenerico" id="botoTraduirDocument">
	                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
	                                </li>
                                </c:if>
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



<!-- escriptori_documents -->
<div id="escriptori_documents_lopd" class="escriptori_detall">
    <script type="text/javascript">
        /*var txtTituloObligatorio = "<spring:message code='personal.formulari_document.titol.obligatori'/>";
        var txtTituloNoSoloNumeros = "<spring:message code='personal.formulari_document.titol.no_nomes_numeros'/>"; */

        // dades formularis
        var FormulariDadesDocLopd = [
            /*{ // Titol (Catala)
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
            }*/
        ];
    </script>
<form id="formGuardarDocLopd" action="" method="POST">
        <input type="hidden" name="docId" id="docId" />
        <input type="hidden" name="procedimientoId" id="procedimientoId" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <!--div id="modulPrincipal" class="grupoModulosFormulario"-->
        <div id="modulDocumentsLopd" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.documents'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>

                            <ul class="idiomes">
								<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <li class="idioma">
                                        <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
                                    </li>
                                </c:forEach>

                                <c:if test="${traductorActivo}">
	                                <li class="traduix btnGenerico" id="botoTraduirDocument">
	                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
	                                </li>
                                </c:if>
                            </ul>

							<div class="idiomes">
								<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label><spring:message code='camp.descargarPlantilla'/>: </label>
												<a href="<c:out value="${lopdPlantilla}"/>" target="_blank" style="margin-left:5px"><span><spring:message code='txt.idioma.ca'/></span></a>
	                                    		<a href="<c:out value="${lopdPlantillaESP}"/>" target="_blank" style="margin-left:5px"><span><spring:message code='txt.idioma.es'/></span></a>
											</div>
											<div class="control">

											</div>
										</div>
								</div>

								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<div class='idioma <c:out value="${lang}"/>'>

									 <!--
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
                                    </div>  -->

                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for='doc_arxiu_lop_<c:out value="${lang}"/>'><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">
                                                <div id='grup_arxiu_actual_doc_lopd_<c:out value="${lang}"/>' class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name='doc_arxiu_lop_<c:out value="${lang}"/>_delete' id='doc_arxiu_lop_<c:out value="${lang}"/>_delete' value="1"/>
                                                    <label for='doc_arxiu_lop_<c:out value="${lang}"/>_delete' class="eliminar"><spring:message code='boto.elimina'/></label>
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
                                  <a id="btnVolver_documents_lopd" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_documents_lopd" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnEliminar impar" style="display:none;">
                                  <a id="btnEliminar_documents_lopd" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
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

<!-- escriptori_normatives -->
<div id="escriptori_normatives">

   <ul id="opcions_normativa" class="opcions">
        <li class="opcio C actiu"><spring:message code='normativa.gestiona.titulo'/></li>
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
                            <div class="etiqueta"><label for="cerca_normativa_data"><spring:message code='txt.dataAprovacio'/></label></div>
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
					 <div class="fila">

                            <div class="element t12">
                                <div class="etiqueta">
                                    <label for="cerca_num_normativa"><spring:message code='txt.numNorma'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_num_normativa" placeholder="NNNNN/YYYY" name="cerca_num_normativa" type="text" />
                                </div>
                            </div>


                            <div class="element t12">
                                <div class="etiqueta">
                                    <label for="cerca_tipus_normativa"><spring:message code='camp.tipus_normativa'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_tipus_normativa" name="cerca_tipus_normativa">
                                        <option value=""><spring:message code='txt.tots'/></option>
                                        <c:forEach items="${llistaTipusNormativa}" var="tipus">
                                            <option value='<c:out value="${tipus.id}" />'><c:out value="${tipus.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="element t12">
                                <div class="etiqueta">
                                    <label for="cerca_butlleti"><spring:message code='camp.butlleti'/></label>
                                </div>
                                <div class="control">
                                    <select id="cerca_butlleti" name="cerca_butlleti">
                                        <option value=""><spring:message code='txt.tots'/></option>
                                        <c:forEach items="${llistaButlletins}" var="butlleti">
                                            <option value='<c:out value="${butlleti.id}" />'><c:out value="${butlleti.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                        </div>
				<c:if test="${mensajeInfo != ''}">
					<div class="fila"><c:out value="${mensajeInfo}" /></div>
                	<div class="fila"></div>
                </c:if>
                <div class="botonera">
                        <div class="boton btnGenerico"><a id="btnLimpiarForm_normativa" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnBuscarForm_normativa" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                    </div>

                </div>
            </div>
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="titol" class="ordreCamp" />
        </div>
    </div>

    <!-- modulLateral -->
	<div class="escriptori_detall">
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
								  <a id="btnVolver_normatives" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
							  </li>
							  <li class="btnGuardar par">
                              <a id="btnGuardar_normatives" href="javascript:;" class="btn guarda important lista-simple-normativas"
                                  	action="<c:url value="/catalegServeis/guardarNormativas.do" />"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
							  </li>
						  </ul>
						</div>
						<!-- /botonera dalt -->
					</div>
				</fieldset>
			</div>
			<!-- /modul -->
		</div>
	</div>

    <div class="modulLateral escriptori_items_seleccionats">
        <div class="modul">
            <div class="interior">
                <div class="seleccionats">
                    <div class="seleccionat">
                        <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                        <div id="listaNormativasDeServicios" class="listaOrdenable"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- seleccionats -->

</div>
<!-- /escriptori_normatives -->
