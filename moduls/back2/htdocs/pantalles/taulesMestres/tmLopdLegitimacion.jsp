<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_lopd.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ajax.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_lopd.js'/>"></script>

    <script type="text/javascript">
    	var pagLlistat = '<c:url value="/lopd/llistatLopdLegitimacion.do" />';
        var pagDetall = '<c:url value="/lopd/pagDetall.do" />';
        var pagGuardar = '<c:url value="/lopd/guardar.do" />';
        var pagEsborrar = '<c:url value="/lopd/borrar.do" />';
        var pagTraduirLopd = '<c:url value="/lopd/traduir.do" />';

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
        var txtLlistaItem = "<spring:message code='txt.lopd_legitimacion'/>";
        var txtLlistaItems = "<spring:message code='txt.lopd_legitimacions'/>";
        var txtMostrem = "<spring:message code='txt.mostrem'/>";
        var txtMostremAl = " <spring:message code='txt.a_la'/> ";
        var txtHiHa = "<spring:message code='txt.hi_ha'/>";
        var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
        var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
        var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
        var txtOrdenats = "<spring:message code='txt.ordenats'/>";
        var txtOrdenades = "<spring:message code='txt.ordenades'/>";
        var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
        var txtDescendentment = "<spring:message code='txt.descendentment'/>";
        var txtPer = "<spring:message code='txt.per'/>";
        var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
        var txtFamilia = "<spring:message code='txt.familia'/>";
        var txtFechaActualizacion = "<spring:message code='camp.dataActualitzacio'/>";
        var txtPorDefecto = "<spring:message code='lopdLegitimacion.formulari.porDefecto'/>";

        //taula
        var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
        var txtCodi = "<spring:message code='txt.codi'/>";
        var txtOrdre = "<spring:message code='camp.ordre'/>";
        var txtPujar = "<spring:message code='txt.pujar'/>";
        var txtCodiEstandar = "<spring:message code='camp.codi.estandard'/>";


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

        // modul procediments
        var txtProcediment = "<spring:message code='txt.procediment'/>";
        var txtProcediments = "<spring:message code='txt.procediments'/>";
        var txtNoHiHaProcediments = txtNoHiHa + " " + txtProcediments.toLowerCase();
        var txtSeleccionat = "<spring:message code='txt.seleccionat'/>";
        var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";

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
                "etiquetaValor": "item_nombre_" + '<c:out value="${idiomaVal}"/>',
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
                        "obligatori": "<spring:message code='lopdLegitimacion.formulari.nom.obligatori'/>",
                        "tipus": "<spring:message code='lopdLegitimacion.formulari.nom.no_nomes_numeros'/>"
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
                    <span><span><spring:message code='lopdLegitimacion.crea_nou_lopd_legitimacion'/></span></span>
                </a>
            </li>
        </ul>
        <div id="resultats">
            <div class="resultats L actiu">
                <div class="dades">
                    <p class="executant"><spring:message code='lopdLegitimacion.carregant_llistat_lopd_legitimacion'/></p>
                </div>
                <input type="hidden" value="0" class="pagPagina" />
                <input type="hidden" value="DESC" class="ordreTipus" />
                <input type="hidden" value="identificador" class="ordreCamp" />
            </div>
        </div>
    </div>

    <div id="escriptori_detall" class="escriptori_detall">
        <form id="formGuardar" action="" method="post">
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
                                    <c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                        <li class="idioma">
                                            <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${traductorActivo}">
    	                                <li class="traduix btnGenerico" id="botoTraduirLopdLegitimacion">
    	                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
    	                                </li>
                                    </c:if>
                                </ul>

                                <c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">

    							<c:if test="${loop.first}">
    							<div class="idiomes">
    							</c:if>

                                    <div class="idioma <c:out value="${lang}"/>">
                                        <div class="fila">
                                            <div class="element t50p">
                                                <div class="etiqueta">
                                                    <label for="item_identificador"><spring:message code='lopdLegitimacion.formulari.identificador'/></label>
                                                </div>
                                                <div class="control">
                                                    <input id="item_identificador" name="item_identificador" type="text" class="nou" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="fila">
                                            <div class="element t99p">
                                                <div class="etiqueta">
                                                    <label for="item_nombre_<c:out value="${lang}"/>"><spring:message code='camp.nom'/></label>
                                                </div>
                                                <div class="control">
                                                    <input id="item_nombre_<c:out value="${lang}"/>" name="item_nombre_<c:out value="${lang}"/>" type="text" class="nou" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="fila">
                                            <div class="element t99p">
                                                <div class="etiqueta">
                                                    <label for="item_por_defecto"><spring:message code='lopdLegitimacion.formulari.porDefecto'/></label>
                                                </div>
                                                <div class="control">
													<input id="item_por_defecto" type="checkbox" name="item_por_defecto" value="1" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

    							<c:if test="${loop.last}">
    							</div>
    							</c:if>

                                </c:forEach>

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
                <!-- /modul -->
                <div class="modul invisible" id="modul_procediments">
                    <fieldset>
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                        <legend><spring:message code='txt.procediments_relacionats'/></legend>
                        <div class="modul_continguts mostrat">
                            <!-- modulProcediments -->
                            <div class="modulProcediments">
                                <div class="seleccionats">
                                    <div class="seleccionat">
                                        <p class="info"><spring:message code='txt.no_procediments'/></p>
                                        <div class="listaOrdenable"></div>
                                    </div>
                                </div>
                                <p class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_procediments'/></span></span></a>
                                </p>
                            </div>
                            <!-- /modulProcediments -->
                        </div>
                    </fieldset>
                </div>
                <!-- /modul -->
            </div>
            <!-- /modulLateral -->
        </form>
    </div>



    <!-- escriptori_procediments -->
    <div id="escriptori_lopd_legitimacion">

        <div id="resultats_lopd_legitimacions" class="escriptori_items_llistat">
            <div class="resultats C actiu" style="display: block;">
                <div class="dades"></div>
                <input type="hidden" value="0" class="pagPagina" />
                <input type="hidden" value="DESC" class="ordreTipus" />
                <input type="hidden" value="id" class="ordreCamp" />
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
    								  <a id="btnVolver_lopd_legitimacion" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
    							  </li>
    							  <li class="btnGuardar par">
                                  <a id="btnGuardar_lopd_legitimacion" href="javascript:;" class="btn guarda important lista-simple-procedimientos"
                                      action="/rolsacback/lopd/guardar.do"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
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

    </div>
    <!-- /escriptori_procediments -->
