<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_idiomes.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/tm_idiomes.js'/>"></script>
<script type="text/javascript">
    var pagLlistat = '<c:url value="/idiomes/llistat.do" />';
    var pagDetall = '<c:url value="/idiomes/pagDetall.do" />';
    var pagGuardar = '<c:url value="/idiomes/guardar.do" />';
    var pagEsborrar = '<c:url value="/idiomes/esborrarIdioma.do" />';
    var pagReordenar = '<c:url value="/idiomes/reordenarIdiomes.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.idioma'/>";
    var txtLlistaItems = "<spring:message code='txt.idioma'/>";
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
    var txtNombreObligatorio = "<spring:message code='personal.formulari.nom.obligatori'/>";
    var txtNombreNoSoloNumeros = "<spring:message code='personal.formulari.nom.no_nomes_numeros'/>";

    // dades formularios
    var FormulariDades = [
        // Lang
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_lang",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 2,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": "<spring:message code='idioma.formulari.error.lang.obligatori'/>"
                }
        },

        // Codi estandar
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_codi_estandard",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 128,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": "<spring:message code='idioma.formulari.error.codi.obligatori'/>"
                }
        },

        // Nom
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_nom",
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

        // Codi traductor
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_lang_traductor",
            "obligatori": "si",
            "tipus": "alfanumeric",         
            "error":
                {
                    "obligatori": "<spring:message code='idioma.formulari.error.traductor.obligatori'/>"
                }
        }   
    ];
</script>

<div id="escriptori_contingut"> <%-- style="display:none/block" --%>
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcions nuevo">
            <a id="btnNuevaFicha" href="javascript:;" class="btn nou">
                <span><span><spring:message code='idioma.crear_nou_idioma'/></span></span>
            </a>
        </li>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='idioma.carregant_llistat_idiomes'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />
        </div>
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="false">
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

                            <div class="fila">
                                <div class="element t50p">
                                    <div class="etiqueta">
                                        <label for="item_lang"><spring:message code='idioma.formulari.lang'/></label>
                                    </div>
                                    <div class="control">
                                        <input id="item_lang" name="item_lang" type="text" class="nou" />
                                    </div>
                                </div>
                            </div>

                            <div class="fila">
                                <div class="element t50p">
                                    <div class="etiqueta">
                                        <label for="item_codi_estandard"><spring:message code='idioma.formulari.codi_estandard'/></label>
                                    </div>
                                    <div class="control">
                                        <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                    </div>
                                </div>
                            </div>

                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="item_nom"><spring:message code='idioma.formulari.nom'/></label>
                                    </div>
                                    <div class="control">
                                        <input id="item_nom" name="item_nom" type="text" class="nou" />
                                    </div>
                                </div>
                            </div>

                            <div class="fila">
                                <div class="element t99p">
                                    <div class="etiqueta">
                                        <label for="item_lang_traductor"><spring:message code='idioma.formulari.lang_traductor'/></label>
                                    </div>
                                    <div class="control">
                                        <textarea id="item_lang_traductor" name="item_lang_traductor" cols="70" rows="3" class="nou"></textarea>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- /fila -->
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