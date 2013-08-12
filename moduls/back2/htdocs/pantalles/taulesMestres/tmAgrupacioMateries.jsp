<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />
<link href='<c:url value="/css/tm_agrupacio_M.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_agrupacio_M.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/agrupacioMateries/llistat.do" />';
    var pagDetall = '<c:url value="/agrupacioMateries/pagDetall.do" />';
    var pagGuardar = '<c:url value="/agrupacioMateries/guardar.do" />';
    var pagEsborrar = '<c:url value="/agrupacioMateries/esborrarAgrupacioMateries.do" />';

    //texts
    var txtTria = "<spring:message code='camp.tria.opcio'/>";
    var txt_per = "<spring:message code='txt.per'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.materia_esborrada_correcte'/>";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtSi = "<spring:message code='txt.si'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtLlistaItem = "<spring:message code='txt.agrupacio'/>";
    var txtLlistaItems = "<spring:message code='txt.agrupacions'/>";
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

    var txtMateria = "<spring:message code='txt.materia'/>";
    var txtMateries = "<spring:message code='txt.materies'/>";
    var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaMateriesSeleccionades = txtNoHiHaMateries + " " + txtSeleccionades.toLowerCase();
    
    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtDescripcio = "<spring:message code='camp.descripcio'/>";
    var txtCodiEstandar = "<spring:message code='camp.codiEstandard'/>";

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
    var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
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
    var txtEdita = "<spring:message code='txt.edita'/>";
	
    var errorMateria= "<spring:message code='materia.escull_materia'/>";
    
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
            "etiquetaValor": "item_seccio",
            "obligatori": "si",
            /*"tipus": "alfanumeric",
            "caracters":
                {
                    "maxim": 100,
                    "mostrar": "no",
                    "abreviat": "no"
                },*/
            "error":
                {
                    "obligatori": "<spring:message code='agrupacioM.formulari.codi_seccio'/>"
                    //"tipus": "<spring:message code='materia.formulari.codi_hita.no_nomes_numeros'/>"
                }
        },
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
                    "obligatori": "<spring:message code='materia.formulari.nom.obligatori'/>",
                    "tipus": "<spring:message code='materia.formulari.nom.no_nomes_numeros'/>"
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
                <span><span><spring:message code='agrupacioM.crea_nova_agrupacio'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='materia.carregant_llistat_materia'/></p>                           
            </div>                          
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />                            
        </div>
        <%-- Mientras no haya metodos en el EJB para buscar con parametros, no habra buscador.
        <div class="resultats C">
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2>Cercador</h2>                 
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_nom"><spring:message code='camp.nom'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_nom" name="cerca_nom" type="text" maxlength="250" class="nom" />
                                </div>
                            </div>
                        </div>
                        <div class="botonera">
                            <div class="boton btnGenerico">
                              <a id="btnLimpiarForm" href="javascript:void(0)" class="btn borrar"><span><span><spring:message code='boto.borrar'/></span></span></a>
                            </div>
                            <div class="boton btnGenerico">
                             <a id="btnBuscarForm" href="javascript:;" class="btn consulta"><span><span><spring:message code='boto.cercar'/></span></span></a>
                            </div>
                        </div>
                </div>
            </div>
            <div class="dades"></div>               
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nom" class="ordreCamp" />
        </div>
        --%>      
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="false" method="post">
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
								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<li class="idioma">
								<a href="javascript:;" class="<c:out value="${lang}"/>">
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
								</c:choose>
								</a>
								</li>
								</c:forEach>
                            </ul>
							
                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<div class="idioma <c:out value='${lang}'/>">			
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_<c:out value="${lang}"/>"><spring:message code='camp.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_<c:out value="${lang}"/>" name="item_nom_<c:out value="${lang}"/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>                                    
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_seccio"><spring:message code='camp.seccio'/></label>
                                            </div>
                                            <div class="control select">
                                                <!-- <input id="item_espai_territorial" name="item_espai_territorial" type="text" /-->
                                                <select id="item_seccio" name="item_seccio">
                                                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                    <c:forEach items="${llistaSeccio}" var="seccio">
                                                        <option value='<c:out value="${seccio.id}" />'><c:out value="${seccio.nom}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /fila -->								
								</div>
								</c:forEach>								
                            </div>
                        </div>
                        <!-- /fila -->
                    </div>                          
                </fieldset>                 
            </div>
            <!-- /modul -->
                
		<!-- PopMateria -->
		<div id="popMateria" class="modul">
			<div class="fila">
				<div class="element ">
					<div class="etiqueta"><label for="item_materia_relacionada"><spring:message code='agrupacioM.formulari.materia'/></label></div>
				<div class="control select">
					<select id="item_materia_relacionada" name="item_materia_relacionada">
						<option value=""><spring:message code='txt.escolliu_opcio'/></option>
						<c:forEach items="${llistaMateries}" var="materia">
							<option value='<c:out value="${materia.id}" />'><c:out value="${materia.nom}" /></option>
						</c:forEach>
					</select>
				</div>
				</div>
				<div class="botonera">
					<div class="etiqueta">
						<label for="item_">&nbsp;</label>
					</div>
					<span class="btnGenerico materies">
						<a href="javascript:;" class="btn afegeixMateria" id="addMateria" >
							<span><span><spring:message code='boto.afegeixMateries'/></span></span>
						</a>
					</span>
					<span class="btnGenerico materies">
						<a href="javascript:;" class="btn tanca" id="tancaMateria" >
							<span><span><spring:message code='boto.tancar'/></span></span>
						</a>
					</span>
				</div>
			</div>
		</div>
		<!-- PopMateries -->
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
            <!-- modul -->
            <div class="modul" id="modul_materies">
                <input type="hidden" id="llistaMateries" name="materies" value=""/>                     
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='materia.materies_relacionades'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulMateries -->
                        <div class="modulMateries">
                            <div class="seleccionats">
                                <%-- dsanchez: un solo idioma --%>
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaMateriaRelacionada'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="boton btnGenerico" style="margin-left: 0px;">
                                    <a href="javascript:;" class="btn consulta" id="afegeixMateria">
                                        <span><span><spring:message code='boto.afegeixMateria'/></span></span>
                                    </a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulMateria -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->
        </div>
        <!-- /modulLateral -->
    </form>
</div>