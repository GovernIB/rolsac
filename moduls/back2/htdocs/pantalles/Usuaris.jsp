<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/usuaris.css"/>" media="screen" />
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
<script type="text/javascript" src="<c:url value='/js/usuaris.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ajax.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_perfils_gestor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_unitats_adminstratives.js'/>"></script>

<script type="text/javascript">

    var pagLlistat = '<c:url value="/usuaris/llistat.do" />';
    var pagDetall = '<c:url value="/usuaris/pagDetall.do" />';
    var pagGuardar = '<c:url value="/usuaris/guardar.do" />';
    var pagEsborrar = '<c:url value="/usuaris/esborrarUsuari.do" />';
    var modulos = '<c:url value="/usuaris/modulos.do" />';

    //texts
    var txtEsborrarCorrecte = "<spring:message code='txt.usuari_esborrat_correcte'/>";
    var txtEsborrarIncorrecte = "";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtLlistaItem = "<spring:message code='txt.usuari'/>";
    var txtLlistaItems = "<spring:message code='txt.usuaris'/>";
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
    var txtLlistaItems = "<spring:message code='txt.usuaris'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_del'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "<spring:message code='txt.nou'/> " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "<spring:message code='txt.detall_de_la.titol'/> " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
    var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
    var txtMostra = "<spring:message code='txt.mostra'/>";
    var txtAmaga = "<spring:message code='txt.amaga'/>";

    var txtHiHa = "<spring:message code='txt.hi_ha'/>";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();

    var txtUnitatAdministrativa = "<spring:message code='unitatAdministrativa.ua'/>";
    var txtUnitatsAdministratives = "<spring:message code='unitatAdministrativa.uas'/>";
    var txtNoHiHaUA = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase();
    var txtNoHiHaUAs = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase();
    var txtSeleccionada =  "<spring:message code='txt.seleccionada'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaUASeleccionada = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaUAsSeleccionades = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase() + " " + txtSeleccionades.toLowerCase();

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

    var txtNom = "<spring:message code='txt.nom'/>";
    var txtUsername = "<spring:message code='txt.usuari'/>";
    var txtPerfil = "<spring:message code='txt.perfil'/>";
    var txtEmail = "<spring:message code='txt.email'/>";

    // modul Perfils Gestor
    var txtPerfilGestor = "<spring:message code='txt.perfil_gestor'/>";
    var txtPerfilsGestor = "<spring:message code='txt.perfils_gestor'/>";
    var txtNoHiHaPerfilsGestor = txtNoHiHa + " " + txtPerfilsGestor;
    var txtNoHiHaPerfilsGestorSeleccionats = "<spring:message code='txt.noHiHaPerfilsGestorRelacionats'/>";

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript">
<!--
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
    var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";
    var txtNombreObligatorio = "<spring:message code='personal.formulari.nom.obligatori'/>";
    var txtNombreNoSoloNumeros = "<spring:message code='personal.formulari.nom.no_nomes_numeros'/>";
    var txtUsernameObligatorio = "<spring:message code='usuari.formulari.username.obligatori'/>";
    var txtPasswordObligatorio = "<spring:message code='usuari.formulari.password.obligatori'/>";
    var txtPerfilObligatorio = "<spring:message code='usuari.formulari.perfil.obligatori'/>";
    var gestionNormativasPorDefecto =<c:out value="${valorDefectoGestionNormativas}" />;

    // dades formularis
    var FormulariDades = [
        // username
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_username",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 100,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": txtUsernameObligatorio,
                }
        },
        // password
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_password",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 100,
                    "mostrar": "no",
                    "abreviat": "no"
                },
            "error":
                {
                    "obligatori": txtPasswordObligatorio,
                }
        },
         // nom
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
        // perfil
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_perfil",
            "obligatori": "si",
            "tipus": "alfanumeric",
            "error":
                {
                    "obligatori": txtPerfilObligatorio
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
        <li id="btnNuevaFicha" class="opcions nuevo">
            <a href="javascript:;" class="btn nou"><span><span><spring:message code='usuari.crea.nou'/></span></span></a>
        </li>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='usuari.executant'/></p>
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
                        <div class="element t30">
                            <div class="etiqueta">
                                <label for="cerca_username"><spring:message code='camp.username'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_username" name="cerca_username" type="text"/>
                            </div>
                        </div>
                        <div class="element t30">
                            <div class="etiqueta">
                                <label for="cerca_nom"><spring:message code='camp.nom'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_nom" name="cerca_nom" type="text" />
                            </div>
                        </div>
                        <div class="element t30">
                            <div class="etiqueta">
                                <label for="cerca_email"><spring:message code='camp.email'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_email" name="cerca_email" type="text" />
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t30">
                            <div class="etiqueta">
                                <label for="cerca_perfil"><spring:message code='camp.rol'/></label>
                            </div>
							<div class="control">
							    <select id="cerca_perfil" name="cerca_perfil" class="t30">
							        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
							        <option value="sacadmin"><spring:message code='usuari.sacadmin'/></option>
							        <option value="sacsuper"><spring:message code='usuari.sacsuper'/></option>
							        <option value="sacoper"><spring:message code='usuari.sacoper'/></option>
							        <option value="sacinfo"><spring:message code='usuari.sacinfo'/></option>
							    </select>
                            </div>
                        </div>
                        <div class="element t30">
		        			<div class="etiqueta">
                                <label for="cerca_perfilGestor"><spring:message code='camp.perfil_gestor'/></label>
                            </div>
                            <div class="control select">
                                <select id="cerca_perfilGestor" name="cerca_perfilGestor" class="t30">
                                    <option value=""><spring:message code='camp.tria.opcio'/></option>
                                    <c:forEach items="${llistaPerfilsGestor}" var="perfilGes">
                                        <option value='<c:out value="${perfilGes.id}" />'><c:out value="${perfilGes.nom}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="element t30">
                            <div class="etiqueta">
                                <label for="cerca_unitat_administrativa_nom"><spring:message code='camp.unitat_administrativa'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_unitat_administrativa_nom" name="cerca_unitat_administrativa_nom" type="text"
                                		onfocus="carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA', 'cerca_unitat_administrativa_id', 'cerca_unitat_administrativa_nom')" />
                                <input id="cerca_unitat_administrativa_id" name="cerca_unitat_administrativa_id" type="hidden" />
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t30">
                            <div class="etiqueta">
                                <label for="cerca_observacions"><spring:message code='camp.observacions'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_observacions" name="cerca_observacions" type="text" />
                            </div>
                        </div>
                    </div>
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
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_username"><spring:message code='camp.username'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_username" name="item_username" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_password"><spring:message code='camp.password'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_password" name="item_password" type="password" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_nom"><spring:message code='camp.nom'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_nom" name="item_nom" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_email"><spring:message code='camp.email'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_email" name="item_email" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_perfil"><spring:message code='camp.rol'/></label>
                                </div>
                                <div class="control select">
                                    <select id="item_perfil" name="item_perfil" class="nou">
	                                    <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
	                                    <option value="sacadmin"><spring:message code='usuari.sacadmin'/></option>
	                                    <option value="sacsuper"><spring:message code='usuari.sacsuper'/></option>
	                                    <option value="sacoper"><spring:message code='usuari.sacoper'/></option>
	                                    <option value="sacinfo"><spring:message code='usuari.sacinfo'/></option>
	                                </select>
                                </div>
                            </div>
                            <div class="element t25p">
								<div class="etiqueta">
									<label for="item_check_permis_modificacio_normativa"><spring:message
											code='camp.permisModificacioNormativa' /></label>
								</div>
								<div class="control">
									<input id="item_check_permis_modificacio_normativa"
										name="item_check_permis_modificacio_normativa" type="checkbox"
										class="nou" />
								</div>
							 </div>
							 <div class="element t25p">
								<div class="etiqueta">
									<label for="item_check_gestion_comunes"><spring:message
											code='camp.permisModificacionComunes' /></label>
								</div>
								<div class="control">
									<input id="item_check_gestion_comunes"
										name="item_check_gestion_comunes" type="checkbox"
										class="nou" />
								</div>
							 </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_observacions"><spring:message code='camp.observacions'/></label>
                                </div>
                                <div class="control">
                                    <textarea id="item_observacions" name="item_observacions" cols="50" rows="10" class="nou"></textarea>
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
                              <li class="btnEliminar impar" style="display:none;">
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
            <div class="modul invisible" id="modul_perfils_gestor">
            	<input type="hidden" id="llistaPerfilsGestor" name="materies" value=""/>
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='seccio.perfils_gestor_relacionats'/></legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulPerfilsGestor -->
                        <div class="modulPerfilsGestor selectorChecks">

                         	<input type="hidden" name="modulo_perfils_gestor_modificado" value="0">

                            <div class="seleccionats">
                                <p class="info"><spring:message code='seccio.no_hi_ha_perfils_gestor'/></p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_perfils_gestor'/></span></span></a>
                                </div>
                            </div>
                            <div class="llistat">
                                <ul>
                                    <c:forEach items="${llistaPerfilsGestor}" var="perfil" varStatus="i">
                                        <c:choose>
                                            <c:when test="${(i.count) % 2 == 0}">
                                                <li class="par">
                                            </c:when>
                                            <c:otherwise>
                                               <li class="impar">
                                            </c:otherwise>
                                        </c:choose>
                                          <label><span><c:out value="${perfil.nom}" /></span><input type="checkbox" value="<c:out value='${perfil.id}' />" /></label>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="botonera">
                                    <div class="btnGenerico">
                                        <a id="btnFinalizar_perfils_gestor" class="btn finalitza" href="javascript:;"
                                        		action="<c:url value="/usuaris/guardarPerfilsGestorRelacionats.do" />">
                                        	<span><span><spring:message code='boto.finalitza'/></span></span>
                                       	</a>
                                    </div>
                                    <div class="btnGenerico">
                                        <a href="javascript:;" class="cancela"><span><span><spring:message code='boto.cancela'/></span></span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /modulPerfilsGestor -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul invisible" id="modul_unitats_administratives">
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
                                <p style="clear: both; margin-bottom: 10px;"/><!-- Separador -->
	                            <div class="btnGenerico">
	                                <a id="btnGuardar_modul_unitatsAdministratives" href="javascript:;" class="btn guarda important lista-simple-uasUsuario" style="display: none"
	                            			action="<c:url value="/usuaris/guardarUnidadesRelacionadas.do" />">
	                           			<span><span><spring:message code='boto.guarda'/></span></span>
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
    </div>
    <!-- /escriptori_detall -->
</form>