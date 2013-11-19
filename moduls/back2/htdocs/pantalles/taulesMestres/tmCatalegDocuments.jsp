<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_cataleg_documents.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tm_cataleg_documents.js'/>"></script>

<script type="text/javascript">
    var pagLlistat = '<c:url value="/catalegDocuments/llistat.do" />';
    var pagDetall = '<c:url value="/catalegDocuments/pagDetall.do" />';
    var pagGuardar = '<c:url value="/catalegDocuments/guardar.do" />';
    var pagEsborrar = '<c:url value="/catalegDocuments/esborrarDocumentCataleg.do" />';
    var pagTraduirCatalegDocuments = '<c:url value="/catalegDocuments/traduir.do" />';

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
    var txtLlistaItem = "<spring:message code='txt.cataleg_document'/>";
    var txtLlistaItems = "<spring:message code='txt.cataleg_documents'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtMostremAl = " <spring:message code='txt.a_la'/> ";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtHiHa = "<spring:message code='txt.hi_ha'/>";
    var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenats = "<spring:message code='txt.ordenats'/>";
    var txtOrdenades = "<spring:message code='txt.ordenades'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtPer = "<spring:message code='txt.per'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>"; 

    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtDescripcio = "<spring:message code='camp.descripcio'/>";

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

 	// moduls
 	var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtItems = "Ítems";
    var txtCercantItems = "Cercant " + txtItems.toLowerCase();
    var txtCercantItemsAnteriors = "Cercant " + txtItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
    var txtCercantItemsSeguents = "Cercant " + txtItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
    

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
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
                    "obligatori": "<spring:message code='catdoc.formulari.nom.obligatori'/>",
                    "tipus": "<spring:message code='catdoc.formulari.nom.no_nomes_numeros'/>"
                }
        }
    ];
</script>

<div id="escriptori_contingut"> <%-- style="display:none/block" --%>
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcio C">
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        <li class="opcions nuevo">
            <a id="btnNuevaFicha" href="javascript:;" class="btn nou">
                <span><span><spring:message code='catdoc.crea_nou_document'/></span></span>
            </a>
        </li>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='catdoc.carregant_llistat_cataleg'/></p>                           
            </div>                          
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />                            
        </div>
		<div class="resultats C">
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2><spring:message code='tab.cercador'/></h2>                 
                        <div class="fila">
                            <div class="element t75">
                                <div class="etiqueta"><label for="cerca_textes"><spring:message code='catdoc.formulari.textes'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_textes" name="cerca_textes" type="text" maxlength="250" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t30"">
                                <div class="etiqueta"><label for="cerca_admresp"><spring:message code='catdoc.formulari.administracio_responsable'/></label></div>
                                <div class="control">                           
                                    <select id="cerca_admresp" class="nou" name="cerca_admresp">
			                                <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
			                                <option value="1"><spring:message code='catdoc.formulari.administracio_estatal.valor'/></option>
			                                <option value="2"><spring:message code='catdoc.formulari.administracio_autonomica.valor'/></option>
			                                <option value="3"><spring:message code='catdoc.formulari.administracio_local.valor'/></option>                                                                                   
			                        </select>
                                </div>
                            </div>
                            <div class="element t16">
                            </div>
                            <div class="element t30"">
                                <div class="etiqueta"><label for="cerca_excepcio"><spring:message code='catdoc.formulari.causes_excepcio'/></label></div>
                                <div class="control">                           
                                   <select id="cerca_excepcio" name="cerca_excepcio" class="nou">
			                       	    	<option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
	                                    	<c:forEach items="${excepcions}" var="excepcio">
	                                          	<option value="<c:out value="${excepcio.id}"/>"><c:out value="${excepcio.nom}"/></option>
	                                       	</c:forEach>
			                       </select>
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
    </div>
</div>

<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="false">
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
                            <ul class="idiomes">
                                <c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop" >
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
                                
                                <li class="traduix btnGenerico" id="botoTraduirCatalegDocuments">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
                            
                            <c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
							<c:if test="${loop.first}">
                            <div class="idiomes">
							</c:if>
                                <div class="idioma <c:out value="${lang}"/>">
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_nom_<c:out value="${lang}"/>"><spring:message code='catdoc.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_<c:out value="${lang}"/>" name="item_nom_<c:out value="${lang}"/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_descripcio_<c:out value="${lang}"/>"><spring:message code='catdoc.formulari.descripcio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_descripcio_<c:out value="${lang}"/>" name="item_descripcio_<c:out value="${lang}"/>" cols="70" rows="3" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
			                  			<div class="element t50p">
			                            	 <div class="etiqueta">
			                                	 <label for="item_admpresp"><spring:message code='catdoc.formulari.administracio_responsable'/></label>
			                             	</div>
			                             	<div class="control select">
			                                	 <select id="item_admresp" class="nou" name="item_admresp">
			                                    	<option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
			                                    	<option value="1"><spring:message code='catdoc.formulari.administracio_estatal.valor'/></option>
			                                    	<option value="2"><spring:message code='catdoc.formulari.administracio_autonomica.valor'/></option>
			                                 		<option value="3"><spring:message code='catdoc.formulari.administracio_local.valor'/></option>                                                                                   
			                                 	</select>
			                             	</div>
			                         	</div> 
			                         	<div class="element t50p">
			                            	 <div class="etiqueta">
			                                	 <label for="item_excepcio"><spring:message code='catdoc.formulari.causes_excepcio'/></label>
			                             	</div>
			                             	<div class="control select">
			                                	<select id="item_excepcio" name="item_excepcio" class="nou">
			                                    	<option value="" selected="selected"><spring:message code='camp.cap'/></option>
			                                        	<c:forEach items="${excepcions}" var="excepcio">
			                                            	<option value="<c:out value="${excepcio.id}"/>"><c:out value="${excepcio.nom}"/></option>
			                                           	</c:forEach>
			                                  	</select>
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
        </div>
        <!-- /modulLateral -->
    </form>
</div>