<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_administracio_remota.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/tm_administracio_remota.js'/>"></script>
<script type="text/javascript">
    var pagLlistat = '<c:url value="/administracioRemota/llistat.do" />';
    var pagDetall = '<c:url value="/administracioRemota/pagDetall.do" />';
    var pagGuardar = '<c:url value="/administracioRemota/guardar.do" />';
    var pagEsborrar = '<c:url value="/administracioRemota/esborrarAdministracioRemota.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.administracio_remota'/>";
    var txtLlistaItems = "<spring:message code='txt.administracions_remotes'/>";
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

    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtEnllas = "<spring:message code='camp.enllas'/>";

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
                    "obligatori": "<spring:message code='administracioRemota.formulari.nom.obligatori'/>",
                    "tipus": "<spring:message code='administracioRemota.formulari.nom.no_nomes_numeros'/>"
                }
        },{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_idRemot",
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
                    "obligatori": "<spring:message code='administracioRemota.formulari.idRemot.obligatori'/>",
                    "tipus": "<spring:message code='administracioRemota.formulari.idRemot.no_nomes_numeros'/>"
                }
        },{
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_responsable",
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
                    "obligatori": "<spring:message code='administracioRemota.formulari.responsable.obligatori'/>",
                    "tipus": "<spring:message code='administracioRemota.formulari.responsable.no_nomes_numeros'/>"
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
                <span><span><spring:message code='administracioRemota.crea_nova_administracioRemota'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='administracioRemota.carregant_llistat_administracioRemota'/></p>                           
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
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_codi_estandart"><spring:message code='administracioRemota.formulari.codi_estandart'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_codi_estandart" name="item_codi_estandart" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_nom"><spring:message code='administracioRemota.formulari.nom'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_nom" name="item_nom" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
	                        <div class="element t99p">                                      
		                        <div class="etiqueta"><label for="item_espai_territorial"><spring:message code='administracioRemota.formulari.espai_territorial'/></label></div>
		                        <div class="control select">
		                            <!-- <input id="item_espai_territorial" name="item_espai_territorial" type="text" /-->
		                            <select id="item_espai_territorial" name="item_espai_territorial">
		                                <option value=""><spring:message code='txt.escolliu_opcio'/></option>
		                                <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
		                                    <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
		                                </c:forEach>
		                            </select>
		                        </div>
	                        </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_responsable"><spring:message code='administracioRemota.formulari.responsable'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_responsable" name="item_responsable" type="text" class="nou" />
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
                    <legend>Multimedia</legend>
                    <div class="modul_continguts mostrat">                        
                        <!-- fila -->
	                    <div class="fila">
	                        <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
	                            <div class="etiqueta"><label for="item_logo_petit"><spring:message code='administracioRemota.formulari.logo_petit'/></label></div>
	                            <div class="control archivo">   
	                                <div id="grup_item_logo_petit" class="file grup_arxiu_actual">
	                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
	                                    <a href="#" target="_blank"></a>
	                                    <input type="checkbox" name="item_logo_petit_delete" id="item_logo_petit_delete" value="1"/>
	                                    <label for="item_logo_petit_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
	                                </div>
	                            </div>
	                        </div>    	                        	                        
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_logo_petit"><spring:message code='administracioRemota.formulari.logo_petit'/></label></div>
	                            <div class="control">
	                                <input id="item_logo_petit" name="item_logo_petit" type="file" class="nou" />
	                            </div>
	                        </div>                                                                                      
	                    </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
                                <div class="etiqueta"><label for="item_logo_gran"><spring:message code='administracioRemota.formulari.logo_gran'/></label></div>
                                <div class="control archivo">   
                                    <div id="grup_item_logo_gran" class="file grup_arxiu_actual">
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_logo_gran_delete" id="item_logo_gran_delete" value="1"/>
                                        <label for="item_logo_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>
                            </div>    
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_gran"><spring:message code='administracioRemota.formulari.logo_gran'/></label></div>
                                <div class="control">
                                    <input id="item_logo_gran" name="item_logo_gran" type="file" class="nou" />
                                </div>
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
                    <legend><spring:message code='txt.connexio'/></legend>
                    <div class="modul_continguts mostrat">                        
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_idRemot"><spring:message code='administracioRemota.formulari.idRemot'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_idRemot" name="item_idRemot" type="text" class="nou" />
                                </div>
                            </div>
                        </div>             
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_profunditat"><spring:message code='administracioRemota.formulari.profunditat'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_profunditat" name="item_profunditat" type="text" class="nou" />
                                </div>
                            </div>
                        </div>                                    
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_endPoint"><spring:message code='administracioRemota.formulari.endPoint'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_endPoint" name="item_endPoint" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_versio_ws"><spring:message code='administracioRemota.formulari.versio_ws'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_versio_ws" name="item_versio_ws" type="text" class="nou" />
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