<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui.css"/>" />
<link href='<c:url value="/css/tm_materia.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ajax.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_materia.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_icones.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_unitats_adminstratives.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/materies/llistat.do" />';
    var pagDetall = '<c:url value="/materies/pagDetall.do" />';
    var pagGuardar = '<c:url value="/materies/guardar.do" />';
    var pagEsborrar = '<c:url value="/materies/esborrarMateria.do" />';
    var pagCarregarIcona = '<c:url value="/iconesMateria/carregarIcona.do" />';
    var pagGuardarIcona = '<c:url value="/iconesMateria/guardarIcona.do" />';
    var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
    var pagTraduirMateria = '<c:url value="/materies/traduir.do" />';
    var modulos = '<c:url value="/materies/modulos.do" />'; 

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
    var txtLlistaItem = "<spring:message code='txt.materia'/>";
    var txtLlistaItems = "<spring:message code='txt.materies'/>";
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

    var txtIcona = "<spring:message code='txt.icona'/>";
    var txtIcones = "<spring:message code='txt.icones'/>";
    var txtNoHiHaIcones = txtNoHiHa + " " + txtIcones;
    var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaIconesSeleccionades= txtNoHiHaIcones + " " + txtSeleccionades.toLowerCase();
    
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

    // Textes mòdul UAs
    var txtUnitatAdministrativa = "<spring:message code='unitatAdministrativa.ua'/>";
    var txtUnitatsAdministratives = "<spring:message code='unitatAdministrativa.uas'/>";
    var txtNoHiHaUA = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase();
    var txtNoHiHaUAs = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase();
    var txtSeleccionada =  "<spring:message code='txt.seleccionada'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaUASeleccionada = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaUAsSeleccionades = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase() + " " + txtSeleccionades.toLowerCase();

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

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript">
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";

    // dades formularios
    var FormulariDades = [
        /*{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_codi_hita",
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
                    "obligatori": "<spring:message code='materia.formulari.codi_hita.obligatori'/>",
                    "tipus": "<spring:message code='materia.formulari.codi_hita.no_nomes_numeros'/>"
                }
        },*/
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
                <span><span><spring:message code='materia.crea_nova_materia'/></span></span>
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
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="false" method="post">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>            
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">          
            <!-- modul -->
            <div id="modul_detall_materies" class="modul">                 
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

							    <li class="traduix btnGenerico" id="botoTraduirMateria">
							        <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
							    </li>
							</ul>

							<div class="idiomes">                                
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
								<div class="idioma <c:out value="${lang}"/>">
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_codi_estandard"><spring:message code='camp.codiEstandard'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_codi_estandard" name="item_codi_estandard" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- fila -->
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
                                            <div class="etiqueta"><label for="item_distribucion_<c:out value="${lang}"/>"><spring:message code='camp.distribucio'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_item_distribucion_<c:out value="${lang}"/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_distribucion_<c:out value="${lang}"/>_delete" id="item_distribucion_<c:out value="${lang}"/>_delete" value="1"/>
                                                    <label for="item_distribucion_<c:out value="${lang}"/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_distribucion_<c:out value="${lang}"/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_distribucion_<c:out value="${lang}"/>" name="item_distribucion_<c:out value="${lang}"/>" type="file" class="nou" />
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
                                            <div class="etiqueta"><label for="item_contenido_<c:out value="${lang}"/>"><spring:message code='camp.contingut'/></label></div>
                                            <div class="control archivo">
                                                <div id="grup_item_contenido_<c:out value="${lang}"/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="item_contenido_<c:out value="${lang}"/>_delete" id="item_contenido_<c:out value="${lang}"/>_delete" value="1"/>
                                                    <label for="item_contenido_<c:out value="${lang}"/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_contenido_<c:out value="${lang}"/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="item_contenido_<c:out value="${lang}"/>" name="item_contenido_<c:out value="${lang}"/>" type="file" class="nou" />
                                            </div>
                                        </div>    
                                    </div>
                                    
                                    <!-- fila -->
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta">
                                                <label for="item_destacada"><spring:message code='camp.destacada'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_destacada" name="item_destacada" type="checkbox" class="nou" />
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
                    
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.MULTIMEDIA'/></legend>
                    <div class="modul_continguts mostrat">                        
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
                                                
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->

            <!-- modul -->
            <div id="modulEstadistiques" class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.ESTADISTIQUES'/></legend> 
                    <div class="modul_continguts mostrat"></div>
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
            <!-- modul -->
            <div class="modul invisible" id="modul_icones">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='materia.icones_perfil'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulIcones -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulIcones">                            
                            <div class="seleccionats">
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='txt.noHiHaIcones'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixIcona'/></span></span></a>
                                </div>
                            </div>
                        </div>
                        <!-- /modulIcones -->                                 
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
	                                <a id="btnGuardar_unidades_administrativas" href="javascript:;" class="btn guarda important lista-simple" 
	                            			action="<c:url value="/materies/guardarUnidadesRelacionadas.do" />">
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
    </form>
</div>

<!-- escriptori_icones -->
<div id="escriptori_icones" class="escriptori_detall">
    <script type="text/javascript">
        // dades formularis
        var FormulariDadesIcona = [                      
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "icona_perfil",
                "obligatori": "si",
                "tipus": "numeric",
                "error":{
                    "obligatori": "<spring:message code='icona.error.perfil_obligatori'/>"
                }
            }
        ];
    </script>
    <form id="formGuardarIcona" action="" method="POST">
        <input type="hidden" name="iconaId" id="iconaId" />
        <input type="hidden" name="materiaId" id="materiaId" />
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <!--div id="modulPrincipal" class="grupoModulosFormulario"-->                    
        <div id="modulIcones" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.icones'/></legend>
                    <div class="modul_continguts mostrat">
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="icona_perfil"><spring:message code='camp.perfil'/></label>
                                </div>
                                <div class="control select">
                                    <select id="icona_perfil" name="icona_perfil" class="nou">
                                        <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${perfils}" var="perfil">
                                            <option value="<c:out value="${perfil.id}"/>"><c:out value="${perfil.nom}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t50p campoImagen">
                            	<div class="thumbnail"></div>
                                <div class="etiqueta"><label for="icona_arxiu"><spring:message code='camp.arxiu'/> *</label></div>
                                <div class="control archivo">   
                                    <!-- <div id="grup_arxiu_actual_icona" class="grup_arxiu_actual"> -->
                                     <div id="grup_icona_arxiu" class="grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <!-- input type="checkbox" name="icona_arxiu_delete" id="icona_arxiu_delete" value="1"/>
                                        <label for="icona_arxiu_delete" class="eliminar"><spring:message code='boto.elimina'/></label -->
                                    </div>
                                </div>
                            </div>    
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="icona_arxiu"><spring:message code='camp.arxiu_nou'/></label></div>
                                <div class="control">                                           
                                    <input id="icona_arxiu" name="icona_arxiu" type="file" class="nou" />
                                </div>
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
                                  <a id="btnVolver_icones" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
                              </li>
                              <li class="btnGuardar par">
                                  <a id="btnGuardar_icones" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
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
<!-- escriptori_icones -->