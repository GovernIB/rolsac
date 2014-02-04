<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>
<link href='<c:url value="/css/fitxes.css"/>' rel="stylesheet" type="text/css" media="screen" />    
<link href='<c:url value="/css/modul_seccions_ua.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_ua_arbre.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_seccions_arbre.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_enllassos.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/css/modul_documents.css"/>" rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/jquery-ui-timepicker-addon.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/fitxes.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-timepicker-addon.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_ua_arbre.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_seccions_arbre.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_seccions_ua.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_fetsVitals.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_public_objectiu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_enllassos.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_auditories.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>

<script type="text/javascript">
   // accesos
   var pagLlistat = '<c:url value="/fitxainf/llistat.do" />';
   var pagDetall = '<c:url value="/fitxainf/pagDetall.do" />';
   var pagGuardar = '<c:url value="/fitxainf/guardar.do" />';
   var pagSeccions = '<c:url value="/fitxainf/seccions.do" />';
   var pagUnitats = '<c:url value="/fitxainf/unitats.do" />';
   var pagEsborrar = '<c:url value="/fitxainf/esborrarFitxa.do" />';
   var pagGuardarDoc = '<c:url value="/documents/guardarDocument.do" />';
   var pagCarregarDoc = '<c:url value="/documents/carregarDocument.do" />';
   var pagAuditories = '<c:url value="/auditories/llistat.do" />';
   var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
   var pagTraduir = '<c:url value="/fitxainf/traduir.do" />';
   var pagTraduirDocument = '<c:url value="/documents/traduir.do" />';
   var modulos = '<c:url value="/fitxainf/modulos.do" />';

   var urlPrevisualizarFicha = '<c:out value="${urlPrevisualitzacio}"/>';
   
   // texts   
   var txtEspere = "<spring:message code='txt.esperi'/>";
   var txtCarregant = "<spring:message code='txt.carregant'/>";
   var txtSi = "<spring:message code='txt.si'/>";
   var txtNo = "<spring:message code='txt.no'/>";
   var txtTrobats = "<spring:message code='txt.trobades'/>";
   var txtLlistaItem = "<spring:message code='txt.fitxa'/>";
   var txtLlistaItems = "<spring:message code='txt.fitxes'/>";
   var txtAdministrativa = "<spring:message code='txt.administrativa'/>";
   var txtAdministratives = "<spring:message code='txt.administratives'/>";
   var txtData = "<spring:message code='txt.data'/>";
   var txtPublicacio = "<spring:message code='txt.publicacio'/>";
   var txtCaducitat = "<spring:message code='txt.caducitat'/>";
   var txtMostrem = "<spring:message code='txt.mostrem'/> <spring:message code='txt.de_la'/> ";
   var txtMostremAl = "<spring:message code='txt.a_la'/>";
   var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
   var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
   var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
   var txtOrdenats = "<spring:message code='txt.ordenats'/>";
   var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
   var txtDescendentment = "<spring:message code='txt.descendentment'/>";
   var txtPer = "<spring:message code='txt.per'/>";
   var txtEsborrarCorrecte = "<spring:message code='txt.fitxa_borrat'/>";
   // taula
   var txtNom = "<spring:message code='txt.nom'/>";
   var txtPare = "<spring:message code='txt.pare'/>";
   var txtCercant = "<spring:message code='txt.cercant'/>";
   var txtCercantLlistat = txtCercant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
   var txtFechaModificacion = "<spring:message code='camp.dataActualitzacio'/>";
   // paginacio
   
   var txtTrobat = "<spring:message code='txt.sha_trobat'/>";
   var txtSeguents = "<spring:message code='txt.seguents'/>";
   var txtAnteriors = "<spring:message code='txt.anteriors'/>";
   var txtInici = "<spring:message code='txt.inici'/>";
   var txtFinal = "<spring:message code='txt.final'/>";
   var txtPagines = "<spring:message code='txt.pagines'/>";
   var txtCercant = "<spring:message code='txt.cercant'/>";
      
   var txtCercantElements = txtCercant + " " + txtLlistaItems;
   var txtCercantLlistatAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
   var txtCercantLlistatSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
   var txtCercantAnteriors = txtCercantLlistatAnteriors;
   var txtCercantSeguents = txtCercantLlistatSeguents;
   
   // detall
   var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_de_la'/> "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
   var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
   var txtDetallTitol = "<spring:message code='txt.detall_de_la.titol'/> " + txtLlistaItem.toLowerCase();
   var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
   var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
   var txtMostra = "<spring:message code='boto.mostra'/>";
   var txtAmaga = "<spring:message code='txt.amaga'/>";
   var txtCaducat = "<spring:message code='txt.caducat'/>";
   var txtImmediat = "<spring:message code='txt.immediat'/>";
   // idioma
   var txtDesplega = "<spring:message code='txt.desplega'/>";
   var txtPlega = "<spring:message code='txt.plega'/>";
   // fotos
   var txtImatge = "<spring:message code='txt.imatge'/>";
   var txtFoto = "<spring:message code='txt.foto'/>";
   var txtFotos = "<spring:message code='txt.fotos'/>";
   var txtFotoPetita = txtFoto + " <spring:message code='txt.petita'/>";
   var txtFotoGran = txtFoto + " <spring:message code='txt.gran'/>";
   var txtLleva = "<spring:message code='txt.lleva'/>";
   var txtInclou = "<spring:message code='txt.inclou'/>";
   var txtElimina = "<spring:message code='txt.elimina'/>";
   var txtNoHiHaFotos = txtNoHiHa + " " + txtFotos.toLowerCase() + " <spring:message code='txt.associades'/>";
   // docs
   var txtNom = "<spring:message code='txt.nom'/>";
   var txtArxiu = "<spring:message code='txt.arxiu'/>"
   // enllasos
   var txtAdresa = "<spring:message code='txt.adresa'/>";
   // moduls
   var txtHiHa = "<spring:message code='txt.hi_ha'/>";
   var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
   var txtSeleccionat = "<spring:message code='txt.seleccionat'/>";
   var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
   // modul documents
   var txtDocument = "<spring:message code='txt.document'/>";
   var txtDocuments = "<spring:message code='txt.documents'/>";
   var txtNoHiHaDocuments = txtNoHiHa + " " + txtDocuments;
   var txtNoHiHaDocumentsSeleccionats = txtNoHiHaDocuments + " " + txtSeleccionats.toLowerCase();
   // modul materies
   var txtMateria = "<spring:message code='txt.materia'/>";
   var txtMateries = "<spring:message code='txt.materies'/>";
   var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
   var txtNoHiHaMateriesSeleccionades = "<spring:message code='txt.noHiHaMateriaRelacionada'/>";
   
   // modul fets vitals
   var txtFet = "<spring:message code='txt.fet_vital'/>";
   var txtFets = "<spring:message code='txt.fets_vitals'/>";
   var txtNoHiHaFets = txtNoHiHa + " " + txtFets;
   // modul public objectiu
   var txtPublic = "<spring:message code='txt.public_objectiu'/>";
   var txtPublics = "<spring:message code='txt.publics_objectiu'/>";
   var txtNoHiHaPublics = txtNoHiHa + " " + txtPublics;
   var txtNoHiHaPublicsSeleccionats = txtNoHiHa + " " + txtPublics.toLowerCase() + " " + txtSeleccionats.toLowerCase();
   
   // modul seccio/ua
   var txtSeccioUA = "<spring:message code='txt.seccio_ua'/>";
   var txtSeccionsUA = "<spring:message code='txt.seccions_ua'/>";
   var txtNoHiHaSeccioUA = "<spring:message code='txt.no_hi_ha_cap'/> " + txtSeccioUA.toLowerCase();
   var txtLaSeccio = "<spring:message code='txt.la_seccio'/>";
   var txtAmbLaUnitat = "<spring:message code='txt.amb_la_unitat'/>";
   // modul ua arbre
   var txtArrel = "<spring:message code='txt.arrel'/>";
   var txtNodesFills = "<spring:message code='txt.nodes_fills.titol'/>";
   var txtCarregantArrel = "<spring:message code='txt.carregant_node_arrel'/> " + txtEspere;
   var txtCarregantNodes = txtCarregant + " <spring:message code='txt.nodes_fills_dot'/> " + txtEspere;
   // enllassos
   var txtEnllas = "<spring:message code='txt.enllas'/>";
   var txtEnllassos = "<spring:message code='txt.enllassos'/>";
   var txtNoHiHaEnllassos = "<spring:message code='txt.no_hi_ha_cap'/> " + txtEnllas.toLowerCase();
   var txtNoHiHaEnllassosSeleccionats = "<spring:message code='txt.no_hi_ha'/> " + txtEnllassos.toLowerCase() + " " + txtSeleccionats.toLowerCase();
   var txtErrorRelacioBuida = "Les relacions han de contenir una secci� i una UA obligatoriament. Per favor, revisi la relaci� que vol establir.";
   
   // suggeriments
   var suggeriments = "";

</script>
   
   
<script type="text/javascript">
   //validacio
   var txtSeccUa = "<spring:message code='fitxes.missatge.es_necessari'/>";
   var txtMaxim = "<spring:message code='txt.maxim'/>";
   var txtMax = "<spring:message code='txt.max'/>";
   var txtCaracters = "<spring:message code='txt.caracters'/>";
   var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
   var txtAnyMal = "<spring:message code='txt.any_mal'/>";
   var txtMesMal = "<spring:message code='txt.mes_mal'/>";
   var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
   var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";
   
   // dades formularis
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
                   "obligatori": "<spring:message code='fitxes.missatge.titol.obligatori'/>",
                   "tipus": "<spring:message code='fitxes.missatge.titol.tipus'/>"
               }
       },
       {
           "modo": "individual",
           "etiqueta": "id",
           "etiquetaValor": "item_estat",
           "obligatori": "si",
           "tipus": "numeric",
           "error":
               {
                   "obligatori": "<spring:message code='fitxes.missatge.estat.obligatori'/>"                   
               }
       } 
   ];

    var FormulariEnllassos = [
		{
		    "modo": "individual",
		    "etiqueta": "id",
		    "etiquetaValor": "enllas_titol_" + '<c:out value="${idiomaVal}"/>',
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
	           		"obligatori": "<spring:message code='fitxes.missatge.titol.enllas.obligatori'/>",
		            "tipus": "<spring:message code='fitxes.missatge.titol.enllas.tipus'/>"                   
		        }
		}   
    ];
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
            <li id="btnNuevaFicha" class="opcions nuevo"><a href="javascript:;" class="btn nou"><span><span><spring:message code='fitxes.crea.nova'/></span></span></a></li>
        </c:if>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='fitxes.llistat.carregant_dades'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="id" class="ordreCamp" />
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
                        <h2><spring:message code='fitxes.llistat.cercador'/></h2>
                        <div class="fila">
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_codi"><spring:message code='fitxes.llistat.codi'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_codi" name="cerca_codi" type="text" />
                                </div>
                            </div>
                            <div class="element t75">
                                <div class="etiqueta">
                                    <label for="cerca_textes"><spring:message code='fitxes.llistat.textes'/></label>
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
                        <%--
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_estat"><spring:message code='fitxes.llistat.estat'/></label>
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
                         --%>
                         <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_publicObjectiu"><spring:message code='fitxes.llistat.public_objectiu'/></label>
                                </div>
                                <div class="control select">
                                    <select id="cerca_publicObjectiu" name="cerca_publicObjectiu" class="publicObjectiu">
                                        <option value=""><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${llistaPublicsObjectiu}" var="publicObjectiu">
                                            <option value='<c:out value="${publicObjectiu.id}" />'><c:out value="${publicObjectiu.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>                         
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_materia"><spring:message code='fitxes.llistat.materia'/></label>
                                </div>
                                <div class="control select">
                                    <select id="cerca_materia" name="cerca_materia" class="materia">
                                        <option value=""><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${llistaMateries}" var="materia">
                                            <option value='<c:out value="${materia.id}" />'><c:out value="${materia.nom}" /></option>
                                        </c:forEach>
                                    </select>               
                                </div>
                            </div>                        
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_fetVital"><spring:message code='fitxes.llistat.fet_vital'/></label>
                                </div>
                                <div class="control select">
                                    <select id="cerca_fetVital" name="cerca_fetVital" class="fetVital">
                                        <option value=""><spring:message code='camp.tria.opcio'/></option>
                                        <c:forEach items="${llistaFetsVitals}" var="fetVital">
                                            <option value='<c:out value="${fetVital.id}" />'><c:out value="${fetVital.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>                        
                        <%--
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_url"><spring:message code='fitxes.llistat.url_forum'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_url" name="cerca_url" type="text"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_responsable"><spring:message code='fitxes.llistat.responsable'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_responsable" name="cerca_responsable" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_fechaCaducidad"><spring:message code='fitxes.llistat.data.caducitat'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_fechaCaducidad" name="cerca_fechaCaducidad" type="text" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_fechaPublicacion"><spring:message code='fitxes.llistat.data.publicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_fechaPublicacion" name="cerca_fechaPublicacion" type="text" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_fechaActualizacion"><spring:message code='fitxes.llistat.data.actualitzacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_fechaActualizacion" name="cerca_fechaActualizacion" type="text" readonly="readonly"/>
                                </div>
                            </div>                            
                        </div>
                        --%>
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
<!-- escriptori_detall -->
<div id="escriptori_detall" class="escriptori_detall">
    <form id="formGuardar" action="" method="post">
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <h2><spring:message code='fitxes.formulari.detall'/></h2>
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
        <!-- modulPrincipal -->
        <div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend>Dades</legend>
                    <div class="modul_continguts mostrat">
                        <!-- fila -->
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
							<ul class="idiomes">							
								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<li class="idioma"><a href="javascript:;" class="<c:out value='${lang}'/>">
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
								</a></li>								
								</c:forEach>
								
                                <li class="traduix btnGenerico" id="botoTraduirFitxa">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='boto.traduix'/></span></span></a>
                                </li>								
							</ul>		
							
                            <div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value="${lang}"/>">
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_titol_<c:out value="${lang}"/>"><spring:message code='fitxes.formulari.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_titol_<c:out value="${lang}"/>" name="item_titol_<c:out value="${lang}"/>" type="text"
                                                    class="nou" />
                                            </div>
                                        </div>
                                        <div id="caja_item_clave_primaria" class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria"><spring:message code='camp.clau_primaria'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria" name="item_clave_primaria" type="text" readonly="readonly"/>
                                            </div>                                            
                                        </div>
                                    </div>
									
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_curta_<c:out value="${lang}"/>"><spring:message code='fitxes.formulari.descripcio.abreviada'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_curta_<c:out value="${lang}"/>" name="item_des_curta_<c:out value="${lang}"/>" cols="70" rows="5" style="width: 100%" class="rich basic nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
									
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_des_llarga_<c:out value="${lang}"/>"><spring:message code='fitxes.formulari.descripcio.extensa'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="item_des_llarga_<c:out value="${lang}"/>" name="item_des_llarga_<c:out value="${lang}"/>" cols="70" rows="10" style="width: 100%" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_url_<c:out value="${lang}"/>"><spring:message code='fitxes.formulari.adresa_web_externa'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_url_<c:out value="${lang}"/>" name="item_url_<c:out value="${lang}"/>" type="text"
                                                    class="nou" />
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
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul amagat"><spring:message code='boto.mostra'/></a>
                    <legend><spring:message code='fitxes.formulari.multimedia'/></legend>
                    <div class="modul_continguts">
                        <!-- fila -->
                        <div class="fila">
	                        <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
	                            <div class="etiqueta"><label for="item_icona"><spring:message code='fitxes.formulari.icona'/></label></div>
	                            <div class="control archivo">   
	                            	<div id="grup_item_icona" class="file">
	                            		<span><spring:message code='txt.no_arxiu_assignat'/></span>
	                            		<a href="#" target="_blank"></a>
	                            		<input type="checkbox" name="item_icona_delete" id="item_icona_delete" value="1"/>
	                            		<label for="item_icona_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
	                            	</div>
	                            </div>
	                        </div>    
	                        
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_icona"><spring:message code='fitxes.formulari.icona'/></label></div>
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
	                            <div class="etiqueta">
                                    <label for="item_banner"><spring:message code='fitxes.formulari.banner'/></label>
                                </div>
	                            <div class="control archivo">   
	                            	<div id="grup_item_banner" class="file">
	                            		<span><spring:message code='txt.no_arxiu_assignat'/></span>
	                            		<a href="#" target="_blank"></a>
	                            		<input type="checkbox" name="item_banner_delete" id="item_banner_delete" value="1"/>
	                            		<label for="item_banner_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
	                            	</div>
	                            </div>
	                        </div>    
	                        
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_banner"><spring:message code='fitxes.formulari.banner'/></label></div>
	                            <div class="control">                                      		
	                                <input id="item_banner" name="item_banner" type="file" class="nou" />
	                            </div>
	                        </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        
                        <!-- fila -->
                        <div class="fila">
	                        <div class="element t50p campoImagen">
                                <div class="thumbnail"></div>
	                            <div class="etiqueta"><label for="item_imatge"><spring:message code='fitxes.formulari.imatge'/></label></div>
	                            <div class="control archivo">   
	                            	<div id="grup_item_imatge" class="file">
	                            		<span><spring:message code='txt.no_arxiu_assignat'/></span>
	                            		<a href="#" target="_blank"></a>
	                            		<input type="checkbox" name="item_imatge_delete" id="item_imatge_delete" value="1"/>
	                            		<label for="item_imatge_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
	                            	</div>
	                            </div>
	                        </div>
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_imatge"><spring:message code='fitxes.formulari.imatge'/></label></div>
	                            <div class="control">                                      		
	                                <input id="item_imatge" name="item_imatge" type="file" class="nou" />
	                            </div>
	                        </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_youtube"><spring:message code='fitxes.formulari.adresa_youtube'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_youtube" name="item_youtube" type="text"
                                        class="nou" />
                                </div>
                            </div>
                        
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_forum"><spring:message code='fitxes.formulari.enllas_forum'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_forum" name="item_forum" type="text" class="nou" />
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
                    <a class="modul amagat"><spring:message code='boto.mostra'/></a>
                    <legend><spring:message code='txt.INFORMADOR'/></legend>
                    <div class="modul_continguts">
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_responsable"><spring:message code='fitxes.formulari.responsable'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_responsable" name="item_responsable" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t99p">
                                <div class="etiqueta">
                                    <label for="item_notes"><spring:message code='fitxes.formulari.notes'/></label>
                                </div>
                                <div class="control">
                                    <textarea id="item_notes" name="item_notes" cols="50" rows="7" class="nou"></textarea>
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
                    <div class="modul_continguts mostrat">
                    <%-- 
                        <div class="fila">
                            <img src="/sacback2/quadreControl/grafica.do?tipoOperacion=1&id=1" width="728px" />
                        </div>
                    --%>
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
		  <c:if test="${rolAdmin}">
	        <!-- modul -->
	        <div id="modulAuditories" class="modul auditorias">                
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.AUDITORIES'/></legend>
                    <div class="modul_continguts mostrat">
                       <p class="executant"><spring:message code='txt.carregant'/></p>
                     <%--
                        <table>
                            <thead>
                                <th class="usuario"><div>USUARI</div></th>
                                <th class="fecha"><div>DATA</div></th>
                                <th class="operacion"><div>OPERACI�</div></th>
                            </thead>                    
                            <tbody>
                                <tr>
                                    <td class="usuario"><div>rsanz</div></td>
                                    <td class="fecha"><div>16/01/2012</div></td>
                                    <td class="operacion"><div>Modificaci�</div></td>
                                </tr>
                                <tr>
                                    <td class="usuario"><div>jfernandez</div></td>
                                    <td class="fecha"><div>16/01/2012</div></td>
                                    <td class="operacion"><div>Modificaci�</div></td>
                                </tr>
                                <tr>
                                    <td class="usuario"><div>flopez</div></td>
                                    <td class="fecha"><div>16/01/2012</div></td>
                                    <td class="operacion"><div>Alta</div></td>
                                </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </fieldset>
	        </div>
	        <!-- /modul -->
      </c:if>
        </div>
        <!-- /modulPrincipal -->        
        <!-- modulLateral -->
        <div class="modulLateral">
            <!-- modul -->
            <div class="modul publicacio">
                <fieldset>
                    <legend><spring:message code='txt.PUBLICACIO'/></legend>
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
                                    <label for="item_data_publicacio"><spring:message code='fitxes.formulari.data.publicacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_publicacio" name="item_data_publicacio" type="text" class="nou" />
                                </div>
                            </div>
                            <div class="element right">
                                <div class="etiqueta">
                                    <label for="item_data_caducitat"><spring:message code='fitxes.formulari.data.caducitat'/></label>
                                </div>
                                <div class="control">
                                    <input id="item_data_caducitat" name="item_data_caducitat" type="text" class="nou" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- botonera baix -->                                                                                                  
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
                              <li class="btnPrevisualizar par">
                                  <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span><spring:message code='boto.previsualitza'/></span></span></a>
                              </li>                              
                          </ul>
                        </div>                                                
                        <!-- /botonera baix -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
			<!-- modul -->
            <div class="modul">
            	<input type="hidden" id="llistaPublicObjectiu" name="publicsObjectiu" value=""/>
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='fitxes.public_objectiu'/> *</legend>
                    <div class="modul_continguts mostrat">                    
                        <!-- modulPublicObjectiu -->
                        <div class="ModulPublicObjectiu selectorChecks">
                        
                            <input type="hidden" name="modul_public_modificat" value="0" />
                        
                            <div class="seleccionats">
                                <p class="info"><spring:message code='fitxes.no_hi_ha_public_objectiu' /></p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_public_objectiu'/></span></span></a>
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
                        <!-- /modulPublicObjectiu-->                        
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
                                    
            <!-- modul -->
            <div class="modul invisible">
            	<input type="hidden" id="llistaMateries" name="materies" value=""/>
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='fitxes.materies_relacionades'/></legend>
                    <div class="modul_continguts mostrat">                    
                        <!-- modulMateries -->
                        <div class="modulMateries selectorChecks">
                        
                            <input type="hidden" name="modulo_materias_modificado" value="0">
                        
                            <div class="seleccionats">
                                <p class="info"><spring:message code='fitxes.no_hi_ha_materies'/></p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_materies'/></span></span></a>
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
                                        <a class="btn finalitza" href="javascript:;"><span><span><spring:message code='boto.finalitza'/></span></span></a>
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
            <div class="modul invisible">
            	<input type="hidden" id="llistaFetsVitals" name="fetsVitals" value=""/>
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='fitxes.fets_vitals'/></legend>
                    <div class="modul_continguts mostrat">                    
                        <!-- modulFetsVitals -->
                        <div class="modulFetsVitals selectorChecks">
                        
                            <input type="hidden" name="modulo_hechos_modificado" value="0" />
                        
                            <div class="seleccionats">
                                <p class="info"><spring:message code='fitxes.no_hi_ha_fets_vitals'/></p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_fets_vitals'/></span></span></a>
                                </div>
                            </div>
                            <div class="llistat">
                                <ul>
									<c:forEach items="${llistaFetsVitals}" var="fetVital" varStatus="i">
                                        <c:choose>
                                            <c:when test="${(i.count) % 2 == 0}">
                                                <li class="par">
                                            </c:when>
                                            <c:otherwise>
                                               <li class="impar">
                                            </c:otherwise>
                                        </c:choose>                                     
                                          <label><span><c:out value="${fetVital.nom}" /></span><input type="checkbox" value="<c:out value='${fetVital.id}' />" /></label>
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
                        <!-- /modulFetsVitals -->                        
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul invisible" id="modul_documents">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='document.documentsRelacionats'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulDocuments -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulDocuments multilang">
                            <input id="modulo_documents_modificado" type="hidden" name="modulo_documents_modificado" value="0" />
                            <ul class="idiomes">
                                <li class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</li>
								
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
    								<c:if test="${loop.first}">
    								    <li class="<c:out value='${lang}'/> seleccionat">
    								</c:if>
    								
    								<c:if test="${!loop.first}">
    								    <li class="<c:out value='${lang}'/>">
    								</c:if>
    								
    								<c:choose>
    									<c:when test="${lang eq 'ca'}">	
    										<spring:message code='txt.idioma.ca_abr'/>
    									</c:when>
    									
    									<c:when test="${lang eq 'es'}">	
    										<spring:message code='txt.idioma.es_abr'/>
    									</c:when>
    									
    									<c:when test="${lang eq 'en'}">	
    										<spring:message code='txt.idioma.en_abr'/>
    									</c:when>
    									
    									<c:when test="${lang eq 'fr'}">	
    										<spring:message code='txt.idioma.fr_abr'/>
    									</c:when>
    									
    									<c:when test="${lang eq 'de'}">	
    										<spring:message code='txt.idioma.de_abr'/>
    									</c:when>
    								</c:choose>
    								</li>
								</c:forEach> 															
                            </ul>

                            <div class="seleccionats">
                                <%-- dsanchez: multiidioma --%>
                                <c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.first}">
                                          <div class="seleccionat cajaIdioma <c:out value='${lang}'/>">                             
                                        </c:when>
                                        <c:when test="${!loop.first}">
                                          <div class="<c:out value='${lang}'/> cajaIdioma">                             
                                        </c:when>                                   
                                    </c:choose>
                                        <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                        <div class="listaOrdenable"></div>                          
                                    </div>                                  
                                </c:forEach>                                
                                
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixDocument'/></span></span></a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulDocuments -->                                 
                    </div>    
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul">
            	<input type="hidden" id="llistaSeccions" name="seccUA" value=""/>
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend>
                        <spring:message code='fitxes.relacio_seccio'/><abbr title="<spring:message code='fitxes.relacio_seccio.ua'/>"><spring:message code='fitxes.relacio_seccio.ua_abr'/></abbr>
                    </legend>
                    <div class="modul_continguts mostrat">
                        <!-- modulSeccionsUA -->
                        <div class="modulSeccionsUA">
                        
                            <input type="hidden" name="modulo_seccionesua_modificado" value="0">
                        
                            <div class="seleccionats">
                                <p class="info"><spring:message code='fitxes.no_hi_ha_seccions_ua'/></p>
                                <div class="listaOrdenable"></div>
                                <div class="botonera">
                                    <div class="btnGenerico">
                                        <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_seccions'/></span></span></a>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                        <!-- /modulSeccionsUA -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul invisible">                     
                <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='fitxes.enllasos_relacionats'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulEnllacos -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <div class="modulEnllassos multilang">
                            
                            <input type="hidden" name="modulo_enlaces_modificado" value="0"/>
                        
                            <ul class="idiomes">
								<li class="introIdiomas"><spring:message code='txt.idioma.idioma'/></li>
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">								
									<c:if test="${loop.first}">
									   <li class="<c:out value='${lang}'/> seleccionat">
									</c:if>
									<c:if test="${!loop.first}">
									   <li class="<c:out value='${lang}'/>">									
									</c:if>									
									<c:choose>
										<c:when test="${lang eq 'ca'}">
											<spring:message code='txt.idioma.ca_abr'/>
										</c:when>
										
										<c:when test="${lang eq 'es'}">
											<spring:message code='txt.idioma.es_abr'/>
										</c:when>
										
										<c:when test="${lang eq 'en'}">
											<spring:message code='txt.idioma.en_abr'/>
										</c:when>
										
										<c:when test="${lang eq 'fr'}">
											<spring:message code='txt.idioma.fr_abr'/>
										</c:when>
										
										<c:when test="${lang eq 'de'}">
											<spring:message code='txt.idioma.de_abr'/>
										</c:when>										
									</c:choose>									
									</li>
								</c:forEach>							
                            </ul>
                            <div class="seleccionats">
							
								<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
									<c:choose>
										<c:when test="${loop.first}">
										  <div class="seleccionat cajaIdioma <c:out value='${lang}'/>">								
										</c:when>
										
										<c:when test="${!loop.first}">
										  <div class="<c:out value='${lang}'/> cajaIdioma">								
										</c:when>									
									</c:choose>
											<p class="info"><spring:message code='fitxes.no_hi_ha_enllasos'/></p>
											<div class="listaOrdenable"></div>							
										</div>									
								</c:forEach>
								
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_enllasos'/></span></span></a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulEnllacos -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->     
        </div>
        <!-- /modulLateral -->    
    </form>
</div>
<!-- /escriptori_detall -->
<!-- escriptori_previsualitza -->
<div id="escriptori_previsualitza">
    <h2><spring:message code='fitxes.previsualitzant'/></h2>
    <div class="boton btnGenerico clear">
        <a href="javascript:;" class="btn torna dePrevisualitzar">
        	<span><span><spring:message code='boto.torna'/></span></span>
        </a>
    </div>
    <div class="previsualitzacio">
        <iframe frameborder="0" scrolling="auto"></iframe>
    </div>
</div>
<!-- /escriptori_previsualitza -->

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
        <input type="hidden" name="fitxaId" id="fitxaId" />
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
								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<li class="idioma"><a href="javascript:;" class="<c:out value='${lang}'/>">
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
								</a></li>
								</c:forEach>
								
								<li class="traduix btnGenerico" id="botoTraduirDocument">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
							
                            <div class="idiomes">
                                <!-- ca -->
								<c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value='${lang}'/>">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_titol_<c:out value='${lang}'/>"><spring:message code='camp.titol'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="doc_titol_<c:out value='${lang}'/>" name="doc_titol_<c:out value='${lang}'/>" type="text" class="nou" />
                                            </div>
                                        </div>
                                    </div>
									
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="doc_descripcio_<c:out value='${lang}'/>"><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id="doc_descripcio_<c:out value='${lang}'/>" name="doc_descripcio_<c:out value='${lang}'/>" cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
									
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_<c:out value='${lang}'/>"><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id="grup_arxiu_actual_doc_<c:out value='${lang}'/>" class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name="doc_arxiu_<c:out value='${lang}'/>_delete" id="doc_arxiu_<c:out value='${lang}'/>_delete" value="1"/>
                                                    <label for="doc_arxiu_<c:out value='${lang}'/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="doc_arxiu_<c:out value='${lang}'/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id="doc_arxiu_<c:out value='${lang}'/>" name="doc_arxiu_<c:out value='${lang}'/>" type="file" class="nou" />
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

<!-- escriptori_seccions_ua -->
<div id="escriptori_seccions_ua">
	<ul id="opcions">
		<li class="opcio actiu"><spring:message code='fitxes.seccions.gestio'/></li>
	</ul>
    <!-- llistat -->
    <div id="resultats" class="escriptori_items_llistat">
        <div class="escriptori_selector_seccions">
            <h3><spring:message code='fitxes.seccions'/></h3>
            <div class="escriptori_seccions_arbre"></div>
        </div>
        <div class="escriptori_selector_uas">
            <h3><spring:message code='fitxes.seccions.ua'/></h3>
            <div class="escriptori_ua_arbre"></div>
        </div>
        <div id="cercador">
        	<div class="botonera">
				<div class="boton btnGenerico btnVolverDetalle">
					<a class="btn torna" href="javascript:;">
						<span><span><spring:message code='boto.torna_detall'/></span></span>
					</a>
				</div>
			</div>
		</div>
    </div>
    <!-- /llistat -->
    <!-- seleccionats -->
    
    <div class="modulLateral escriptori_items_seleccionats">
		<div class="modul">
			<div class="interior">
				<div class="seleccionats">
					<div class="seleccionat">
						<p class="info"><spring:message code='fitxes.seccions.no_hi_ha'/></p>
						<div class="listaOrdenable"></div>
					</div>
					<div class="botonera">
						<p class="botonera btnGenerico">
							<a id="btnInsertar" class="btn inserta important" href="javascript:;">
								<span><span><spring:message code='boto.inserta'/></span></span>
							</a>							
						</p>
						<p class="botonera btnGenerico">
							<a id="btnFinalizar" class="btn finalitza important" href="javascript:;">
								<span><span><spring:message code='boto.finalitza'/></span></span>
							</a>
						</p>
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /escriptori_seccions_ua -->
<!-- escriptori_enllassos -->
<div id="escriptori_enllassos">
   <ul id="opcions">
        <li class="opcio actiu"><spring:message code='fitxes.enllasos.administracio'/></li>                                 
    </ul>    
    <form id="formEnllassos" class="grupoModulosFormulario">
    	<input id="id_enllas_actual" type="hidden"/>
	    <div class="modul">
	        <fieldset>
		        <!-- fila -->
		        <div class="fila">
		            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
		            <ul class="idiomes">
					
						<c:forEach items="${idiomes_aplicacio}" var="lang">
						<li class="idioma"><a href="javascript:;" class="<c:out value='${lang}'/>">						
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
						</a></li>
						</c:forEach>										
		            </ul>
		            <div class="idiomes">
					
						<c:forEach items="${idiomes_aplicacio}" var="lang">
						<div class="idioma <c:out value='${lang}'/>">
							<div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_titol_<c:out value='${lang}'/>"><spring:message code='fitxes.enllasos.titol'/></label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_titol_<c:out value='${lang}'/>" name="item_titol_<c:out value='${lang}'/>" type="text" 
										class="nou" />
		                            </div>
		                        </div>								
							</div>
							
		                    <div class="fila">
		                        <div class="element t99p">
		                            <div class="etiqueta">
		                                <label for="enllas_url_<c:out value='${lang}'/>"><spring:message code='fitxes.enllasos.url'/></label>
		                            </div>
		                            <div class="control">
		                                <input id="enllas_url_<c:out value='${lang}'/>" name="item_url_<c:out value='${lang}'/>" type="text"
		                                    class="nou" />
		                            </div>
		                        </div>
		                    </div>							
						</div>
						</c:forEach>					
		            </div>
		        </div>
		        <div id="cercador">
			        <div class="botonera">
						<div class="boton btnGenerico">
							<a id="btnLimpiarCampos" class="btn borrar" href="javascript:;">
								<span><span><spring:message code='boto.borrar'/></span></span>
							</a>
						</div>
						<div class="boton btnGenerico">
							<a id="btnInsertarEnllas" class="btn inserta important" href="javascript:;">
								<span><span><spring:message code='boto.inserta'/></span></span>
							</a>							
						</div>
						<div id="btnVolverDetalle" class="boton btnGenerico">
							<a class="btn torna" href="javascript:;">
								<span><span><spring:message code='boto.torna_detall'/></span></span>
							</a>
						</div>
					</div>
				</div>
		        <!-- /fila -->
			</fieldset>
		</div>
	</form>
    <div class="modulLateral escriptori_items_seleccionats">
        <div class="modul">
            <div class="interior multilang">            	
                <ul class="idiomes">
					<li class="introIdiomas"><spring:message code='txt.idioma.idioma'/></li>
					<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">						
						<c:if test="${loop.first}">
						<li class="<c:out value="${lang}"/> seleccionat"><spring:message code='txt.idioma.idioma'/>
						</c:if>
						
						<c:if test="${!loop.first}">
						<li class="<c:out value="${lang}"/>">
						</c:if>
						
						<c:choose>
							<c:when test="${lang eq 'ca'}">
							<spring:message code='txt.idioma.ca_abr'/>
							</c:when>
							
							<c:when test="${lang eq 'es'}">
							<spring:message code='txt.idioma.es_abr'/>
							</c:when>

							<c:when test="${lang eq 'en'}">
							<spring:message code='txt.idioma.en_abr'/>
							</c:when>

							<c:when test="${lang eq 'fr'}">
							<spring:message code='txt.idioma.fr_abr'/>
							</c:when>

							<c:when test="${lang eq 'en'}">
							<spring:message code='txt.idioma.de_abr'/>
							</c:when>							
						</c:choose>
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
					
					<p class="info"><spring:message code='fitxes.no_hi_ha_enllasos'/></p>
					<div class="listaOrdenable"></div>
					</div>					
					</c:forEach>
					                  
                    <p class="botonera btnGenerico">
                        <a id="btnFinalitzar" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                    </p>                                    
                </div>                                  
            </div>
        </div>
    </div>
    <!-- seleccionats -->
</div>                           

<!-- /escriptori_enllassos -->