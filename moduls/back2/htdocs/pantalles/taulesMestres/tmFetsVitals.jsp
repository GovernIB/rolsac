<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_fets_vitals.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_procediments.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_fets_vitals.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_procediments.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/fetsVitals/llistat.do" />';
    var pagDetall = '<c:url value="/fetsVitals/pagDetall.do" />';
    var pagGuardar = '<c:url value="/fetsVitals/guardar.do" />';
    var pagEsborrar = '<c:url value="/fetsVitals/esborrarFetsVitals.do" />';
    var pagPujar  = '<c:url value="/fetsVitals/pujarFetsVitals.do" />';
    var pagDetallProcediment = '<c:url value="/catalegProcediments/catalegProcediments.do" />';
    var pagLlistatProcediments = '<c:url value="/catalegProcediments/llistat.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.fet_vital'/>";
    var txtLlistaItems = "<spring:message code='txt.fets_vitals'/>";
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
    var txtNoHiHaProcedimentsSeleccionats = txtNoHiHa + " " + txtProcediments.toLowerCase() + " " + txtSeleccionats.toLowerCase();

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
                    "obligatori": "<spring:message code='fetVital.formulari.nom.obligatori'/>",
                    "tipus": "<spring:message code='fetVital.formulari.nom.no_nomes_numeros'/>"
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
                <span><span><spring:message code='fetVital.crea_nou_fet_vital'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='fetVital.carregant_llistat_fet_vital'/></p>                           
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
							
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
							
							<c:if test="${loop.first}">
                            <ul class="idiomes">
							</c:if>
							
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

										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.fr'/>							
										</c:when>

										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.de'/>
										</c:when>									
									</c:choose>								
									</a>
								</li>																
							<c:if test="${loop.last}">
                            </ul>					
							</c:if>							
							</c:forEach>
							                                                            
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
							
							<c:if test="${loop.first}">
							<div class="idiomes">		
							</c:if>	
							
                                <div class="idioma <c:out value="${lang}"/>">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard"><spring:message code='publicObjectiu.formulari.codi_estandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
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
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_descripcio_<c:out value="${lang}"/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_descripcio_<c:out value="${lang}"/>" name="item_descripcio_<c:out value="${lang}"/>" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_paraules_clau_<c:out value="${lang}"/>"><spring:message code='camp.paraules_clau'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_paraules_clau_<c:out value="${lang}"/>" name="item_paraules_clau_<c:out value="${lang}"/>" cols="50" rows="4" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucio_<c:out value="${lang}"/>"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucio_<c:out value="${lang}"/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucio_<c:out value="${lang}"/>_delete" id="item_distribucio_<c:out value="${lang}"/>_delete" value="1"/>
                                                    <label for="item_distribucio_<c:out value="${lang}"/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucio_<c:out value="${lang}"/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucio_<c:out value="${lang}"/>" name="item_distribucio_<c:out value="${lang}"/>" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_<c:out value="${lang}"/>"><spring:message code='camp.normativa'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_normativa_<c:out value="${lang}"/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_normativa_<c:out value="${lang}"/>_delete" id="item_normativa_<c:out value="${lang}"/>_delete" value="1"/>
                                                    <label for="item_normativa_<c:out value="${lang}"/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_normativa_<c:out value="${lang}"/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_normativa_<c:out value="${lang}"/>" name="item_normativa_<c:out value="${lang}"/>" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="fila">
									
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contingut_<c:out value="${lang}"/>"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_contingut_<c:out value="${lang}"/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contingut_<c:out value="${lang}"/>_delete" id="item_contingut_<c:out value="${lang}"/>_delete" value="1"/>
                                                    <label for="item_contingut_<c:out value="${lang}"/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contingut_<c:out value="${lang}"/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contingut_<c:out value="${lang}"/>" name="item_contingut_<c:out value="${lang}"/>" type="file" class="nou" />
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
			
			<!-- modul -->
			<div class="modul">
				<fieldset>
					<a class="modul mostrat"><spring:message code='txt.amaga'/></a>
					<legend><spring:message code='fitxes.formulari.multimedia'/></legend>
					<div class="modul_continguts mostrat">                    
					
						<!-- fila -->
						<div class="fila">
							<div class="element t50p campoImagen">
								<div class="thumbnail"></div>
								<div class="etiqueta"><label for="item_foto"><spring:message code='camp.foto'/></label></div>
								<div class="control archivo">   
									<div id="grup_item_foto" class="grup_arxiu_actual">
										<span><spring:message code='txt.no_arxiu_assignat'/></span>
										<a href="#" target="_blank"></a>
										<input type="checkbox" name="item_foto_delete" id="item_foto_delete" value="1"/>
										<label for="item_foto_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
									</div>
								</div>
							</div>    
							<div class="element t50p">
								<div class="etiqueta"><label for="item_foto"><spring:message code='camp.arxiu_nou'/></label></div>
								<div class="control">                                           
									<input id="item_foto" name="item_foto" type="file" class="nou" />
								</div>
							</div> 
						</div>
						<!-- /fila -->
						
						<!-- fila -->
						<div class="fila">
							<div class="element t50p campoImagen">
								<div class="thumbnail"></div>
								<div class="etiqueta"><label for="item_icona"><spring:message code='camp.icona'/></label></div>
								<div class="control archivo">   
									<div id="grup_item_icona" class="grup_arxiu_actual">
										<span><spring:message code='txt.no_arxiu_assignat'/></span>
										<a href="#" target="_blank"></a>
										<input type="checkbox" name="item_icona_delete" id="item_icona_delete" value="1"/>
										<label for="item_icona_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
									</div>
								</div>
							</div>    
							<div class="element t50p">
								<div class="etiqueta"><label for="item_icona"><spring:message code='camp.arxiu_nou'/></label></div>
								<div class="control">                                           
									<input id="item_icona" name="item_icona" type="file" class="nou" />
								</div>
							</div> 
						</div>
						<!-- /fila -->
						
						<!-- fila -->
						<div class="fila">
							<div class="element t50p campoImagen">
								<div class="thumbnail"></div>
								<div class="etiqueta"><label for="item_icona_gran"><spring:message code='camp.icona_gran'/></label></div>
								<div class="control archivo">   
									<div id="grup_item_icona_gran" class="grup_arxiu_actual">
										<span><spring:message code='txt.no_arxiu_assignat'/></span>
										<a href="#" target="_blank"></a>
										<input type="checkbox" name="item_icona_gran_delete" id="item_icona_gran_delete" value="1"/>
										<label for="item_icona_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
									</div>
								</div>
							</div>    
							<div class="element t50p">
								<div class="etiqueta"><label for="item_icona_gran"><spring:message code='camp.arxiu_nou'/></label></div>
								<div class="control">                                           
									<input id="item_icona_gran" name="item_icona_gran" type="file" class="nou" />
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
            <!-- /modul -->
            <div class="modul" id="modul_procediments">
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
<div id="escriptori_procediments">
    <ul id="opcions_procediment" class="opcions">
        <li class="opcio C actiu"><spring:message code='txt.gestiona'/></li>                                 
    </ul>
    
    <div id="resultats_procediments" class="escriptori_items_llistat">            
        <div class="resultats C actiu" style="display: block;">
            <div id="cercador_procediments" class="escriptori_items_cercador"> 
                <div id="cercador_procediments_contingut">
                    <div class="fila">
                            <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_textes"><spring:message code='camp.textes'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_textes" name="cerca_textes" type="text"/>
                            </div>
                        </div>
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_codi"><spring:message code='txt.codi'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_codi" name="cerca_codi" type="text" />
                            </div>
                        </div>
                        <div class="element t15">
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
                        <div class="element t15">
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
                        <div class="element t15">
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
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_tramit"><spring:message code='camp.identificadorTramit'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_tramit" name="cerca_tramit" type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_versio"><spring:message code='camp.versioTramit'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_versio" name="cerca_versio" type="text"/>
                            </div>
                        </div>
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_url"><spring:message code='camp.urlTramitExtern'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_url" name="cerca_url" type="text"/>
                            </div>
                        </div>
                        <div class="element t15">
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
                    </div>
                    <div class="fila">  
                        <div class="element t15">
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
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_taxa"><spring:message code='camp.taxa'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_taxa" name="cerca_taxa" class="t8">
                                    <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                    <option value="0"><spring:message code='txt.no'/></option>
                                    <option value="1"><spring:message code='txt.si'/></option>
                                </select>
                            </div>
                        </div>
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_responsable"><spring:message code='unitatadm.formulari.responsable'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_responsable" name="cerca_responsable" type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_fechaCaducidad"><spring:message code='camp.dataCaducitat'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaCaducidad" name="cerca_fechaCaducidad" type="text" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_fechaPublicacion"><spring:message code='camp.dataPublicacio'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaPublicacion" name="cerca_fechaPublicacion" type="text" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_fechaActualizacion"><spring:message code='camp.dataActualitzacio'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_fechaActualizacion" name="cerca_fechaActualizacion" type="text" readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                    <div class="fila">
                        <div class="element t15">
                            <div class="etiqueta">
                                <label for="cerca_uaFilles"><spring:message code='camp.inclouUAFilles'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_uaFilles" name="cerca_uaFilles" class="t8">
                                    <option value="0" selected="selected"><spring:message code='txt.no'/></option>
                                    <option value="1"><spring:message code='txt.si'/></option>
                                </select>
                            </div>
                        </div>
                        <div class="element t30">
                            <div class="control">
                                <input id="cerca_uaMeves" name="cerca_uaMeves" type="checkbox" value="1"/> <label for="cerca_uaMeves" class="etiqueta"><spring:message code='camp.cerca_totes_unitats'/></label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="botonera">
                        <div class="boton btnGenerico"><a id="btnLimpiarForm_procediment" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnBuscarForm_procediment" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnVolverDetalle_procediment" class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
                    </div>
                </div>
            </div>   
            <div class="dades"></div>                       
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="id" class="ordreCamp" />             
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
                        <a id="btnFinalizar_procediment" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                    </p>                                    
                </div>                                  
            </div>
        </div>
    </div>
    <!-- seleccionats -->
</div>
<!-- /escriptori_procediments -->
