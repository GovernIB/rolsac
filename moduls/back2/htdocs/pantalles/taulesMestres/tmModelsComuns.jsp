<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />
<!-- <link href='<c:url value="/css/tm_materia.css"/>' rel="stylesheet" type="text/css" media="screen" /> -->
<link href='<c:url value="/css/tm_models_comuns.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_models_comuns.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/modelsComuns/llistat.do" />';
    var pagDetall = '<c:url value="/modelsComuns/pagDetall.do" />';
    var pagGuardar = '<c:url value="/modelsComuns/guardar.do" />';
    var pagEsborrar = '<c:url value="/modelsComuns/esborrar.do" />';
    var pagReordenar  = '<c:url value="/modelsComuns/reordenarModelsComuns.do" />';
    var pagTraduirModelsComuns = '<c:url value="/modelsComuns/traduir.do" />';

    //texts
    var txtTria = "<spring:message code='camp.tria.opcio'/>";
    var txt_per = "<spring:message code='txt.per'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.model_comu_esborrat_correcte'/>";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtSi = "<spring:message code='txt.si'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtLlistaItem = "<spring:message code='txt.model_comu'/>";
    var txtLlistaItems = "<spring:message code='txt.models_comuns'/>";
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
    var txtNou = "<spring:message code='txt.nou'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtDescripcio = "<spring:message code='camp.descripcio'/>";
    var txtOrdre = "<spring:message code='camp.ordre'/>";

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
    var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_del'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
    var txtNouTitol = "<spring:message code='txt.nou'/> " + txtLlistaItem.toLowerCase();
    var txtDetallTitol = "<spring:message code='txt.detall_del.titol'/> " + txtLlistaItem.toLowerCase();
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
    var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
    var txtMostra = "<spring:message code='txt.mostra'/>";
    var txtAmaga = "<spring:message code='txt.amaga'/>";

    //idioma
    var txtDesplega = "<spring:message code='txt.desplega'/>";
    var txtPlega = "<spring:message code='txt.plega'/>";

    var txtElimina = "<spring:message code='txt.elimina'/>";

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript">
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";

    // dades formularios
    var FormulariDades = [
        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_titol_" + '<c:out value="${idiomaVal}"/>',
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
                    "obligatori": "<spring:message code='models_comuns.formulari.titol.obligatori'/>",
                    "tipus": "<spring:message code='models_comuns.formulari.titol.no_nomes_numeros'/>"
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
                <span><span><spring:message code='models_comuns.crea_nou_model'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='models_comuns.carregant_llistat_model'/></p>                           
            </div>                          
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />                            
        </div>
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="" method="POST">
     	<input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <div id="modulFormularis" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.models_comuns'/></legend>
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
								
								<li class="traduix btnGenerico" id="botoTraduirModelsComuns">
								    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
								</li>
							</ul>

                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value='${lang}'/>">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_titol_<c:out value='${lang}'/>"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_<c:out value='${lang}'/>" name="item_titol_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_<c:out value='${lang}'/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_<c:out value='${lang}'/>" name="item_descripcio_<c:out value='${lang}'/>" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_model_<c:out value='${lang}'/>"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_model_<c:out value='${lang}'/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_model_<c:out value='${lang}'/>_delete" id="item_model_arxiu_<c:out value='${lang}'/>_delete" value="1"/>
                                                    <label for="item_model_<c:out value='${lang}'/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_model_<c:out value='${lang}'/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_model_<c:out value='${lang}'/>" name="item_model_<c:out value='${lang}'/>" type="file" class="nou" />
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

