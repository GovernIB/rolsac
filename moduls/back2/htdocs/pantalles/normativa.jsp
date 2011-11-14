<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<link href='<c:url value="/css/normativa.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_afectacions.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_procediments.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_traspas.css"/>' rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/normativa.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_afectacions.js'/>"></script>

    <script type="text/javascript">
    <!--
        // pagines  
        var pagLlistat = '<c:url value="/normativa/llistat.do" />';
        var pagDetall = '<c:url value="/normativa/pagDetall.do" />';
        var pagGuardar = '<c:url value="/normativa/guardar.do" />';
        var pagEliminar = '<c:url value="/normativa/eliminar.do" />';
        
        var pagArrel = '<c:url value="/" />';
        
        var pagBOIB = "json/boibsJSON.php";
        var pagNormativa = '<c:url value="/normativa/cercarNormatives.do" />';
        var pagProcediments = "json/procedimentsJSON.php";
        
        var idUaActual = '<c:out value="${idUA}" />';
        var nomUaActual = '<c:out value="${nomUA}" />';        
        
        var txtSenseArxiu = "<spring:message code='txt.sense_arxiu'/>";
        
        // texts
        var txtEspere = "<spring:message code='txt.esperi'/>";
        var txtCarregant = "<spring:message code='txt.carregant'/>";
        var txtSi = "<spring:message code='txt.si'/>";
        var txtNo = "<spring:message code='txt.no'/>";
        var txtTrobats = "<spring:message code='txt.trobats'/>";
        var txtTrobades = "<spring:message code='txt.trobades'/>";
        var txtLlistaItem = "<spring:message code='txt.normativa'/>";
        var txtLlistaItems = "<spring:message code='txt.normatives'/>";
        var txtData = "<spring:message code='txt.data'/>";
        var txtDataButlleti = "<spring:message code='camp.data_butlleti'/>";
        var txtPublicacio = "<spring:message code='boto.publicacio'/>";
        var txtCaducitat = "<spring:message code='txt.caducitat'/>";
        var txtMostrem = "<spring:message code='txt.mostrem'/>";
        var txtDeLa = "<spring:message code='txt.de_la'/>";
        var txtMostremAl = " <spring:message code='txt.a_la'/> ";
        var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
        var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
        var txtCarregantLlistat = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;        
        var txtOrdenats = "<spring:message code='txt.ordenats'/>";
        var txtOrdenades = "<spring:message code='txt.ordenades'/>";
        var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
        var txtDescendentment = "<spring:message code='txt.descendentment'/>";
        var txtPer = "<spring:message code='txt.per'/>";
        // taula

        var txtNumero = "<spring:message code='camp.numero'/>";
        var txtTipus = "<spring:message code='camp.tipus'/>";
        var txtTipologia = "<spring:message code='camp.tipologia'/>";
        var txtData = "<spring:message code='camp.data'/>";
        var txtLocal = "<spring:message code='txt.local'/>";
        var txtExterna = "<spring:message code='txt.externa'/>";
        var txtBOIB = "BOIB";
        
        
        var txtCercant = "<spring:message code='txt.cercant'/>";
        var txtCercantLlistat = txtCercant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
        // paginacio
        var txtTrobat = "<spring:message code='txt.sha_trobat'/>";
        var txtSeguents = "<spring:message code='txt.seguents'/>";
        var txtAnteriors = "<spring:message code='txt.anteriors'/>";
        var txtInici = "<spring:message code='txt.inici'/>";
        var txtFinal = "<spring:message code='txt.final'/>";
        var txtPagines = "<spring:message code='txt.pagines'/>";
        var txtCercantElements = txtCercant + " " + txtLlistaItems;
        var txtCercantLlistatAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantLlistatSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        
        var txtCercantAnteriors = txtCercantLlistatAnteriors;
        var txtCercantSeguents = txtCercantLlistatSeguents;
        
        // detall
        var txtCarregantDetall = txtCarregant + " <spring:message code='txt.detall_de_la'/> " + txtLlistaItem.toLowerCase() + ". " + txtEspere;
        var txtNouTitol = "<spring:message code='txt.nova'/> " + txtLlistaItem.toLowerCase();
        var txtDetallTitol = "<spring:message code='txt.detall_de_la.titol'/> " + txtLlistaItem.toLowerCase();
        var txtItemEliminar = "<spring:message code='txt.segur_eliminar_aquest'/> " + txtLlistaItem.toLowerCase() + "?";
        var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
        var txtMostra = "<spring:message code='txt.mostra'/>";
        var txtAmaga = "<spring:message code='txt.amaga'/>";
        var txtCaducat = "<spring:message code='txt.caducat'/>";
        var txtImmediat = "<spring:message code='txt.inmediat'/>";
        // idioma
        var txtDesplega = "<spring:message code='txt.desplega'/>";
        var txtPlega = "<spring:message code='txt.plega'/>";
        //fotos
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
        var txtArxiu = "<spring:message code='txt.arxiu'/>";
        // enllasos
        var txtAdresa = "<spring:message code='txt.adresa'/>";
        // moduls
        var txtHiHa = "<spring:message code='txt.hi_ha'/>";
        var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
        var txtItems = "<spring:message code='txt.items'/>";
        var txtCarregantItems = txtCarregant + " " + txtItems.toLowerCase();
        var txtCercantItems = txtCercant + " " + txtItems.toLowerCase();
        var txtCercantItemsAnteriors = txtCercant + " " + txtItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantItemsSeguents = txtCercant + " " + txtItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        // modul afectacions
        var txtAfectacio = "<spring:message code='txt.afectacio'/>";
        var txtAfectacions = "<spring:message code='txt.afectacions'/>";
        var txtNoHiHaAfectacions = txtNoHiHa + " " + txtAfectacions.toLowerCase();
        var txtSeleccionada = "<spring:message code='txt.seleccionada'/>";
        var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
        var txtNoHiHaAfectacionsSeleccionats = txtNoHiHa + " " + txtAfectacions.toLowerCase() + " " + txtSeleccionades.toLowerCase();
        var txtNormativa = "<spring:message code='txt.normativa'/>";
        var txtNormatives = "<spring:message code='txt.normatives'/>";
        var txtAmbLaNorma = "<spring:message code='txt.amb_la_norma'/>";
        // modul BOIB
        var txtRegistre = "<spring:message code='txt.registre'/>";
        var txtNoHiHaNormativaBOIB = txtNoHiHa + " " + "<spring:message code='txt.normativa_boib'/>";
        
        var txtNoHiHaNormatives = txtNoHiHa + " " + txtNormatives;
        
        var Afectacions_arr = [];
        <c:forEach items="${llistaTipusAfectacio}" var="tipus">
            Afectacions_arr.push({id : '<c:out value="${tipus.id}" />', nom : '<c:out value="${tipus.nom}" />'});        
        </c:forEach>
        
                
        // modul procediments
        var txtProcediment = "<spring:message code='txt.procediment'/>";
        var txtProcediments = "<spring:message code='txt.procediments'/>";
        var txtNoHiHaProcediments = txtNoHiHa + " " + txtProcediments.toLowerCase();
        var txtSeleccionat = "<spring:message code='txt.seleccionat'/>";
        var txtSeleccionats = "<spring:message code='txt.seleccionats'/>";
        var txtNoHiHaProcedimentsSeleccionats = txtNoHiHa + " " + txtProcediments.toLowerCase() + " " + txtSeleccionats.toLowerCase();        
        
        var txtNormativaLocal = "<spring:message code='txt.normativa_local'/>";
        var txtNormativaExterna = "<spring:message code='txt.normativa_externa'/>";
        
        // suggeriments
        var suggeriments = [
            {
                etiqueta: "id",
                etiquetaValor: "cerca_titol",
                pagina: "json/normativesJSON_consulta.php"
            }
        ];
    -->
    </script>
    
    <script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
    <script type="text/javascript">
    <!--
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
                "etiquetaValor": "item_titol_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 250,
                        "mostrar": "si",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "obligatori": "<spring:message code='normativa.formulari.titol_ca.obligatori'/>",
                        "tipus": "<spring:message code='normativa.formulari.titol_ca.no_nomes_numeros'/>"
                    }
            },           
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_inicial_ca",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_inicial_ca.tipus'/>"
                    }
            },    
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_inicial_es",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_inicial_es.tipus'/>"
                    }
            },  
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_inicial_en",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_inicial_en.tipus'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_inicial_de",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_inicial_de.tipus'/>"
                    }
            },             
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_inicial_fr",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_inicial_fr.tipus'/>"
                    }
            },   
            
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_final_ca",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_final_ca.tipus'/>"
                    }
            },    
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_final_es",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_final_es.tipus'/>"
                    }
            },  
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_final_en",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_final_en.tipus'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_final_de",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_final_de.tipus'/>"
                    }
            },             
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_final_fr",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_final_fr.tipus'/>"
                    }
            },            
            
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_numero",
                "obligatori": "si",
                "tipus": "numeric",
                "error":
                    {
                        "obligatori": "<spring:message code='normativa.formulari.numero.obligatori'/>",
                        "tipus": "<spring:message code='normativa.formulari.numero.tipus'/>"
                    }
            },       
            
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_data",
                "obligatori": "no",
                "tipus": "data",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.data.tipus'/>"
                    }
            },    
            
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_data_butlleti",
                "obligatori": "no",
                "tipus": "data",
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.data_butlleti.tipus'/>"
                    }
            }           

        ];
    -->
    </script>

<input type="hidden" id="rolusuario" value="<rol:printRol/>"/>

<div id="escriptori_contingut">
    <ul id="opcions">
        <li class="opcio L actiu">          
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcio C">            
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        <c:if test="${idUA > 0}">
            <li class="opcions nuevo">
                <a id="btnNuevaFicha" href="javascript:;" class="btn nou"><span><span><spring:message code='normativa.crea_nova_normativa'/></span></span></a>
            </li>
        </c:if>
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">
            <div class="dades">
                <p class="executant"><spring:message code='normativa.carregant_llistat_normativa'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="data" class="ordreCamp" />
        </div>
        <div class="resultats C">
            <div id="cercador">
                <div id="cercador_contingut">
                    <h2><spring:message code='tab.cercador'/></h2>
                    
                    <input id="cerca_ua_id" name="cerca_ua_id" type="hidden" value='<c:out value="${idUA}" />'/>
                    
                    <div class="fila">
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_numero"><spring:message code='camp.numero'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_numero" name="cerca_numero" type="text" />
                            </div>                          
                        </div>
                        
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_validacio"><spring:message code='camp.validacio'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_validacio" name="cerca_validacio">
                                
                                    <c:set var="rolSuper"><rol:userIsSuper/></c:set>
                                    <c:choose>
                                        <c:when test="${rolSuper}" >
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
                        
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_tipus_normativa"><spring:message code='camp.tipus_normativa'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_tipus_normativa" name="cerca_tipus_normativa">
                                    <option value=""><spring:message code='txt.tots'/></option>
                                    <c:forEach items="${llistaTipusNormativa}" var="tipus">                                     
                                        <option value='<c:out value="${tipus.id}" />'><c:out value="${tipus.nom}" /></option>
                                    </c:forEach>
                                </select>                               
                            </div>                          
                        </div>
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_butlleti"><spring:message code='camp.butlleti'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_butlleti" name="cerca_butlleti">
                                    <option value=""><spring:message code='txt.tots'/></option>
                                    <c:forEach items="${llistaButlletins}" var="butlleti">                                      
                                        <option value='<c:out value="${butlleti.id}" />'><c:out value="${butlleti.nom}" /></option>
                                    </c:forEach>
                                </select>                               
                            </div>                          
                        </div>                                              
                    </div>      
                    
                    <div class="fila">
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_registre"><spring:message code='camp.registre'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_registre" name="cerca_registre" type="text" />
                            </div>                          
                        </div>  
                        
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_llei"><spring:message code='camp.llei'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_llei" name="cerca_llei" type="text" />
                            </div>                          
                        </div>      
                        
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_data"><spring:message code='camp.data'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_data" name="cerca_data" type="text" class="data" />
                            </div>
                        </div>          
                        
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_data_butlleti"><spring:message code='camp.data_butlleti'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_data_butlleti" name="cerca_data_butlleti" type="text" class="data" />
                            </div>
                        </div>
                    </div>          
                    
                    <div class="fila">                        
                        <div class="element t21">
                            <div class="etiqueta">
                                <label for="cerca_text"><spring:message code='camp.text'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_text" name="cerca_text" type="text" maxlength="250" class="text" />
                            </div>
                        </div>                      
                    </div>
                    
                    <div class="fila">
                        <div class="element t18">
                            <input type="checkbox" id="cerca_totes_unitats" name="cerca_totes_unitats"/>
                            <label for="cerca_totes_unitats" class="checkbox"><spring:message code='camp.cerca_totes_unitats'/></label>                     
                        </div>

                        <div class="element t18">                           
                            <input type="checkbox" id="cerca_externes" name="cerca_externes" />
                            <label for="cerca_externes" class="checkbox"><spring:message code='camp.cerca_externes'/></label>
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
            <!-- /cercador -->
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="data" class="ordreCamp" />
        </div>
    </div>
</div>
<!-- /escriptori_contingut -->
<!-- escriptori_detall -->
<div id="escriptori_detall" class="escriptori_detall"> <%--La linia de style ja ve al CSS  - display: block/none; --%>
    <form id="formGuardar" action="" method="POST">
    <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
    <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>
           
    <p id="tipoNormativa"></p>
            
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
                        <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</p>
                        <ul class="idiomes">
                            <li class="idioma"><a href="javascript:;" class="ca"><spring:message code='txt.idioma.ca'/></a></li>
                            <li class="idioma"><a href="javascript:;" class="es"><spring:message code='txt.idioma.es'/></a></li>
                            <li class="idioma"><a href="javascript:;" class="en"><spring:message code='txt.idioma.en'/></a></li>
                            <li class="idioma"><a href="javascript:;" class="de"><spring:message code='txt.idioma.de'/></a></li>
                            <li class="idioma"><a href="javascript:;" class="fr"><spring:message code='txt.idioma.fr'/></a></li>
                            
                            <li class="traduix btnGenerico">
                                <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                            </li>
                            <%--
                            <li class="desplegar">
                                <a href="javascript:;" class="desplegar">Desplega</a>
                            </li>
                             --%>
                        </ul>
                        <div class="idiomes">
                            <!-- ca -->
                            <div class="idioma ca">
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_titol_ca"><spring:message code='camp.titol_normativa'/></label></div>
                                        <div class="control">
                                            <input id="item_titol_ca" name="item_titol_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_ca"><spring:message code='camp.enllas'/></label></div>
                                        <div class="control">
                                            <input id="item_enllas_ca" name="item_enllas_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_ca"><spring:message code='camp.apartat'/></label></div>
                                        <div class="control">
                                            <input id="item_apartat_ca" name="item_apartat_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_ca"><spring:message code='camp.pagina_inicial'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_ca" name="item_pagina_inicial_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_ca"><spring:message code='camp.pagina_final'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_ca" name="item_pagina_final_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_ca"><spring:message code='camp.responsable'/></label></div>
                                        <div class="control">
                                            <input id="item_responsable_ca" name="item_responsable_ca" type="text" class="nou" />
                                        </div>
                                    </div>                                    
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_ca"><spring:message code='camp.arxiu'/></label></div>
                                        <div class="control archivo">   
                                            <div id="grup_arxiu_actual_ca" class="grup_arxiu_actual">
                                                <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                <a href="#" target="_blank"></a>
                                                <input type="checkbox" name="item_arxiu_ca_delete" id="item_arxiu_ca_delete" value="1"/>
                                                <label for="item_arxiu_ca_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                            </div>
                                        </div>
                                    </div>    
                                    
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_ca"><spring:message code='camp.arxiu_nou'/></label></div>
                                        <div class="control">                                           
                                            <input id="item_arxiu_ca" name="item_arxiu_ca" type="file" class="nou" />
                                        </div>
                                    </div>                                                                                      
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_ca"><spring:message code='camp.observacions'/></label></div>
                                        <div class="control">
                                            <textarea id="item_des_curta_ca" name="item_des_curta_ca" cols="70" rows="3" class="nou"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /ca -->
                            <!-- es -->
                            <div class="idioma es">
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_titol_es"><spring:message code='camp.titol_normativa'/></label></div>
                                        <div class="control">
                                            <input id="item_titol_es" name="item_titol_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_es"><spring:message code='camp.enllas'/></label></div>
                                        <div class="control">
                                            <input id="item_enllas_es" name="item_enllas_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_es"><spring:message code='camp.apartat'/></label></div>
                                        <div class="control">
                                            <input id="item_apartat_es" name="item_apartat_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_es"><spring:message code='camp.pagina_inicial'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_es" name="item_pagina_inicial_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_es"><spring:message code='camp.pagina_final'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_es" name="item_pagina_final_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_es"><spring:message code='camp.responsable'/></label></div>
                                        <div class="control">
                                            <input id="item_responsable_es" name="item_responsable_es" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_es"><spring:message code='camp.arxiu'/></label></div>
                                        <div class="control">
                                            <div id="grup_arxiu_actual_es" class="grup_arxiu_actual">
                                                <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                <a href="#" target="_blank"></a>
                                                <input type="checkbox" name="item_arxiu_es_delete" id="item_arxiu_es_delete" value="1"/>
                                                <label for="item_arxiu_es_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                            </div>
                                        </div>
                                    </div>    
                                    
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_es"><spring:message code='camp.arxiu_nou'/></label></div>
                                        <div class="control">                                           
                                            <input id="item_arxiu_es" name="item_arxiu_es" type="file" class="nou" />
                                        </div>
                                    </div>                                                                                      
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_es"><spring:message code='camp.observacions'/></label></div>
                                        <div class="control">
                                            <textarea id="item_des_curta_es" name="item_des_curta_es" cols="70" rows="3" class="nou"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /es -->
                            <!-- en -->
                            <div class="idioma en">
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_titol_en"><spring:message code='camp.titol_normativa'/></label></div>
                                        <div class="control">
                                            <input id="item_titol_en" name="item_titol_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_en"><spring:message code='camp.enllas'/></label></div>
                                        <div class="control">
                                            <input id="item_enllas_en" name="item_enllas_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_en"><spring:message code='camp.apartat'/></label></div>
                                        <div class="control">
                                            <input id="item_apartat_en" name="item_apartat_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_en"><spring:message code='camp.pagina_inicial'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_en" name="item_pagina_inicial_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_en"><spring:message code='camp.pagina_final'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_en" name="item_pagina_final_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_en"><spring:message code='camp.responsable'/></label></div>
                                        <div class="control">
                                            <input id="item_responsable_en" name="item_responsable_en" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_en"><spring:message code='camp.arxiu'/></label></div>
                                        <div class="control">
                                            <div id="grup_arxiu_actual_en" class="grup_arxiu_actual">
                                                <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                <a href="#" target="_blank"></a>
                                                <input type="checkbox" name="item_arxiu_en_delete" id="item_arxiu_en_delete" value="1"/>
                                                <label for="item_arxiu_en_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                            </div>
                                        </div>
                                    </div>    
                                    
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_en"><spring:message code='camp.arxiu_nou'/></label></div>
                                        <div class="control">                                           
                                            <input id="item_arxiu_en" name="item_arxiu_en" type="file" class="nou" />
                                        </div>
                                    </div>                                                                                      
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_en"><spring:message code='camp.observacions'/></label></div>
                                        <div class="control">
                                            <textarea id="item_des_curta_en" name="item_des_curta_en" cols="70" rows="3" class="nou"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /en -->
                            <!-- de -->
                            <div class="idioma de">
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_titol_de"><spring:message code='camp.titol_normativa'/></label></div>
                                        <div class="control">
                                            <input id="item_titol_de" name="item_titol_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_de"><spring:message code='camp.enllas'/></label></div>
                                        <div class="control">
                                            <input id="item_enllas_de" name="item_enllas_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_de"><spring:message code='camp.apartat'/></label></div>
                                        <div class="control">
                                            <input id="item_apartat_de" name="item_apartat_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_de"><spring:message code='camp.pagina_inicial'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_de" name="item_pagina_inicial_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_de"><spring:message code='camp.pagina_final'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_de" name="item_pagina_final_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_de"><spring:message code='camp.responsable'/></label></div>
                                        <div class="control">
                                            <input id="item_responsable_de" name="item_responsable_de" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_de"><spring:message code='camp.arxiu'/></label></div>
                                        <div class="control">
                                            <div id="grup_arxiu_actual_de" class="grup_arxiu_actual">
                                                <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                <a href="#" target="_blank"></a>
                                                <input type="checkbox" name="item_arxiu_de_delete" id="item_arxiu_de_delete" value="1"/>
                                                <label for="item_arxiu_de_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                            </div>
                                        </div>
                                    </div>    
                                    
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_de"><spring:message code='camp.arxiu_nou'/></label></div>
                                        <div class="control">                                           
                                            <input id="item_arxiu_de" name="item_arxiu_de" type="file" class="nou" />
                                        </div>
                                    </div>                                                                                      
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_de"><spring:message code='camp.observacions'/></label></div>
                                        <div class="control">
                                            <textarea id="item_des_curta_de" name="item_des_curta_de" cols="70" rows="3" class="nou"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /de -->
                            <!-- fr -->
                            <div class="idioma fr">
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_titol_fr"><spring:message code='camp.titol_normativa'/></label></div>
                                        <div class="control">
                                            <input id="item_titol_fr" name="item_titol_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_fr"><spring:message code='camp.enllas'/></label></div>
                                        <div class="control">
                                            <input id="item_enllas_fr" name="item_enllas_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_fr"><spring:message code='camp.apartat'/></label></div>
                                        <div class="control">
                                            <input id="item_apartat_fr" name="item_apartat_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_fr"><spring:message code='camp.pagina_inicial'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_fr" name="item_pagina_inicial_fr" type="text" class="nou" />
                                        </div>

                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_fr"><spring:message code='camp.pagina_final'/></label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_fr" name="item_pagina_final_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_fr"><spring:message code='camp.responsable'/></label></div>
                                        <div class="control">
                                            <input id="item_responsable_fr" name="item_responsable_fr" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_fr"><spring:message code='camp.arxiu'/></label></div>
                                        <div class="control">
                                            <div id="grup_arxiu_actual_fr" class="grup_arxiu_actual">
                                                <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                <a href="#" target="_blank"></a>
                                                <input type="checkbox" name="item_arxiu_fr_delete" id="item_arxiu_fr_delete" value="1"/>
                                                <label for="item_arxiu_fr_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                            </div>
                                        </div>
                                    </div>    
                                    
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_arxiu_fr"><spring:message code='camp.arxiu_nou'/></label></div>
                                        <div class="control">                                           
                                            <input id="item_arxiu_fr" name="item_arxiu_fr" type="file" class="nou" />
                                        </div>
                                    </div>                                                                                      
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_fr"><spring:message code='camp.observacions'/></label></div>
                                        <div class="control">
                                            <textarea id="item_des_curta_fr" name="item_des_curta_fr" cols="70" rows="3" class="nou"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /fr -->
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
                <legend><spring:message code='txt.gestio'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_numero"><spring:message code='camp.numero'/></label></div>
                            <div class="control">
                                <input id="item_numero" name="item_numero" type="text" class="nou" />
                            </div>
                        </div>
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_data"><spring:message code='camp.data'/></label></div>
                            <div class="control">
                                <input id="item_data" name="item_data" type="text" class="data nou" />
                            </div>
                        </div>  
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_registre"><spring:message code='camp.registre'/></label></div>
                            <div class="control">
                                <input id="item_registre" name="item_registre" type="text" class="nou" />
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_butlleti_id"><spring:message code='camp.butlleti'/></label></div>
                            <div class="control select">
                                <select id="item_butlleti_id" name="item_butlleti_id" class="nou">
                                    <option value=""><spring:message code='txt.no_definit'/></option>
                                    <c:forEach items="${llistaButlletins}" var="butlleti">                                      
                                        <option value='<c:out value="${butlleti.id}" />'><c:out value="${butlleti.nom}" /></option>
                                    </c:forEach>
                                </select>       
                            </div>       
                        </div>
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_data_butlleti"><spring:message code='camp.data_butlleti'/></label></div>
                            <div class="control">
                                <input id="item_data_butlleti" name="item_data_butlleti" type="text" class="data nou" />
                            </div>
                        </div>
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_tipus"><spring:message code='camp.tipus_normativa'/></label></div>
                            <div class="control select">
                                <select id="item_tipus" name="item_tipus" class="nou">
                                    <option value=""><spring:message code='txt.no_definit'/></option>
                                    <c:forEach items="${llistaTipusNormativa}" var="tipus">                                     
                                        <option value='<c:out value="${tipus.id}" />'><c:out value="${tipus.nom}" /></option>
                                    </c:forEach>
                                </select>
                                
                            </div>
                        </div>                      
                    </div>
                    <!-- /fila -->
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p">                          
                            <div class="etiqueta"><label for="item_ua_nom"><spring:message code='camp.unitat_administrativa'/></label></div>
                            <div class="control">
                                <input id="item_ua_id" name="item_ua_id" type="hidden" />
                                <input id="item_ua_nom" name="item_ua_nom" type="text" readonly="readonly"/>
                            </div>
                        </div>
                                            
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_llei"><spring:message code='camp.llei'/></label></div>
                            <div class="control">
                                <input id="item_llei" name="item_llei" type="text" class="nou" />
                            </div>
                        </div>                  
                    </div>
                    <!-- /fila -->
                    
                    <!-- Botonera -->
                    <div id="botoneraCambioUA">
                        <div class="botonera" style="margin-top: 0px; float:left;">
                            <div class="boton btnGenerico" style="margin-left: 0px;">
                            	<a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_ua_id', 'item_ua_nom');" class="btn consulta">
                                <%-- <a href="javascript:ArbreUA('item_ua_nom', 'item_ua_id');" class="btn consulta"> --%>
                                <span><span><spring:message code='boto.canviarUA'/></span></span>
                                </a>
                            </div>
                            <div id="botonBorrarUA" class="boton btnGenerico">
                                <a href="javascript:EliminaArbreUA('item_ua_nom', 'item_ua_id');" class="btn borrar">
                                <span><span><spring:message code='boto.borrar'/></span></span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- /Botonera -->    
                                      
                </div>
            </fieldset>
        </div>
        <!-- /modul -->
    </div>
    <!-- /modulPrincipal -->

    <!-- modulLateral -->
    <div class="modulLateral">
        <%
        //TODO pintar si es OPERADOR
        //if ($_SESSION['rolsac_rol'] != "RSC_OPERADOR") {
        %>
        <!-- modul -->
        <div class="modul publicacio">
            <fieldset>
                <legend><spring:message code='txt.publicacio'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila publicacion_2campos">
                        <!-- >div class="element left">
                            <div class="etiqueta">
                                <label for="item_estat">Estat</label>
                            </div>
                            <div class="control">
                                <select id="item_estat" name="item_estat">
                                    <option value="E" selected="selected">Esborrany</option>
                                    <option value="R">Real</option>
                                </select>
                            </div>
                        </div-->
                        <div class="element right">
                            <div class="etiqueta">
                                <label for="item_validacio"><spring:message code='camp.validacio'/></label>
                            </div>
                            <div class="control">
                                <select id="item_validacio" name="item_validacio">
                                
                                    <c:set var="rolSuper"><rol:userIsSuper/></c:set>
                                    <c:choose>
                                        <c:when test="${rolSuper}" >
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
                    <div class="fila publicacion_2campos">
                        <!-- div class="element left">
                            <div class="etiqueta">
                                <label for="item_data_publicacio">Data publicació</label>
                            </div>
                            <div class="control">
                                <input id="item_data_publicacio" name="item_data_publicacio" type="text" class="nou" />
                            </div>
                        </div>
                        <div class="element right">
                            <div class="etiqueta">
                                <label for="item_data_caducitat">Data caducitat</label>
                            </div>
                            <div class="control">
                                <input id="item_data_caducitat" name="item_data_caducitat" type="text" class="nou" />
                            </div>
                        </div-->
                    </div>
                    <div class="clear"></div>
                    <!-- /fila -->
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
                                                 
                          
                          <!-- li class="btnPrevisualizar par">
                              <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span><spring:message code='boto.previsualitza'/></span></span></a>
                          </li-->
                      </ul>
                    </div>
                    <!-- /botonera dalt -->
                </div>
            </fieldset>
        </div>
        <!-- /modul -->
        <%
        //}
        %>

        <!-- modul -->      
        <input type="hidden" id="item_tipologia" name="item_tipologia" />
        
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
                    </div>
                    <!-- /modulProcediments -->
                </div>
            </fieldset>
        </div>
        <!-- /modul -->
        <!-- modul -->
        <div class="modul" id="modul_afectacions">
            <input type="hidden" id="afectaciones" name="afectaciones" value=""/>
            <fieldset>
                <a class="modul amagat"><spring:message code='txt.mostra'/></a>
                <legend><spring:message code='txt.afectacions_relacionades'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- modulAfectacions -->
                    <div class="modulAfectacions">
                        <div class="seleccionats">
                            <div class="seleccionat">
                                <p class="info"><spring:message code='txt.no_afectacions'/></p>
                                <div class="listaOrdenable"></div>                                                         
                            </div>
                            <p class="btnGenerico">
                                <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.gestiona_afectacions'/></span></span></a>
                            </p>

                        </div>
                    </div>
                    <!-- /modulAfectacions -->
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
<!-- div id="escriptori_previsualitza">
    <h2><spring:message code='txt.previsualitzant_normativa'/></h2>
    <p>
        <a href="javascript:;" class="btn torna dePrevisualitzar"><span><span><spring:message code='boto.torna'/></span>
        </span>
        </a>
    </p>
    <div class="previsualitzacio">
        <iframe frameborder="0" scrolling="auto"></iframe>
    </div>
</div-->
<!-- /escriptori_previsualitza -->
<!-- escriptori_traspas -->
<!-- div id="escriptori_traspas">
    <h2>Gestió del traspàs del BOIB</h2>
    <div class="botonera dalt">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
    <p>Use el cercador per a trobar la norma que dessitja traspassar.
        Després simplement premi al títol de la mateixa per a importar totes
        les dades.</p>

    <div class="escriptori_items_llistat">

        <div class="escriptori_items_cercador">
            <h3>Cercador</h3>
            <div class="fila">
                <div class="element t18">
                    <div class="etiqueta">
                        <label for="cerca_boib_titol">Títol del butlletí</label>
                    </div>
                    <div class="control">
                        <input id="cerca_boib_titol" name="cerca_boib_titol" type="text"
                            maxlength="100" class="titol" />
                    </div>
                </div>
                <div class="element t10">
                    <div class="etiqueta">
                        <label for="cerca_boib_numero">Número del butlletí</label>
                    </div>
                    <div class="control">
                        <input id="cerca_boib_numero" name="cerca_boib_numero" type="text"
                            maxlength="10" class="numero" />
                    </div>
                </div>
                <div class="element t10">
                    <div class="etiqueta">
                        <label for="cerca_boib_registre">Número de Registre</label>
                    </div>
                    <div class="control">
                        <input id="cerca_boib_registre" name="cerca_boib_registre"
                            type="text" maxlength="10" class="registre" />
                    </div>
                </div>
                <div class="element t10">
                    <div class="etiqueta">
                        <label for="cerca_boib_data">Data del butlletí</label>
                    </div>
                    <div class="control">
                        <input id="cerca_boib_data" name="cerca_boib_data" type="text"
                            class="data" />
                    </div>
                </div>
                
                
            </div>
            <div class="botonera">
                <a href="javascript:;" class="btn consulta"><span><span>Cerca!</span>
                </span>
                </a>
            </div>
        </div>

        <div class="dades"></div>
        <input type="hidden" value="0" class="pagPagina" /> <input
            type="hidden" value="DESC" class="ordreTipus" /> <input
            type="hidden" value="data" class="ordreCamp" />
    </div>

    <div class="botonera baix">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
</div-->
<!-- /escriptori_traspas -->

<!-- escriptori_afectacions -->
<div id="escriptori_afectacions">
    
    <ul id="opcions_afectacions" class="opcions">
        <li class="opcio C actiu">Gestiona</li>                               
    </ul>    
    
    <div id="resultats_afectacions" class="escriptori_items_llistat">            
        <div class="resultats C actiu" style="display: block;">
            <div id="cercador_afectacions" class="escriptori_items_cercador"> 
                <div id="cercador_afectacions_contingut">
                    <div class="fila"> 
                        <div class="element t26">
                            <div class="etiqueta">
                                <label for="afec_cerca_normativa_titol"><spring:message code='camp.titol_normativa'/></label>
                            </div>
                            <div class="control">
                                <input id="afec_cerca_normativa_titol" name="afec_cerca_normativa_titol" type="text" class="titol" />
                            </div>
                        </div>
                         <div class="element t12">
                             <div class="etiqueta">
                                 <label for="afec_cerca_data"><spring:message code='camp.data'/></label>
                             </div>
                             <div class="control">
                                 <input id="afec_cerca_data" name="afec_cerca_data" type="text" class="data" />
                             </div>
                         </div>          
                         
                         <div class="element t12">
                             <div class="etiqueta">
                                 <label for="afec_cerca_data_butlleti"><spring:message code='camp.data_butlleti'/></label>
                             </div>
                             <div class="control">
                                 <input id="afec_cerca_data_butlleti" name="afec_cerca_data_butlleti" type="text" class="data" />
                             </div>
                         </div>
                    </div>
           
                    <div class="botonera">
                        <div class="boton btnGenerico"><a id="btnLimpiarForm_afectacions" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnBuscarForm_afectacions" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnVolverDetalle_afectacions" class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
                    </div>  

                </div>
                                          
            </div>
            <!-- /cercador -->
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="data" class="ordreCamp" />
        </div>
    </div>

    <div class="modulLateral escriptori_items_seleccionats">
        <div class="modul">
            <div class="interior">
                <div class="seleccionats">
                    <div class="seleccionat">
                        <p class="info"><spring:message code='txt.no_afectacions'/></p>     
                        <div class="listaOrdenable"></div>              
                    </div>
                    <p class="botonera btnGenerico">
                        <a id="btnFinalizar_afectacions" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                    </p>                                    
                </div>
            </div>
        </div>        
    </div>
    <!-- seleccionats -->
</div>
<!-- /escriptori_afectacions -->
<!-- escriptori_procediments -->
<%--
<div id="escriptori_procediments">
    <h2>Gestió dels procediments relacionats</h2>
    <div class="botonera dalt">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna al detall</span></span></a></li>
        </ul>
    </div>
    <!-- llistat -->
    <div class="escriptori_items_llistat">
        <!-- cercador -->
        <div class="escriptori_items_cercador">
            <h3>Cercador</h3>
            <div class="fila">
                <div class="element t12">
                    <div class="etiqueta">
                        <label for="cerca_procediment_nom">Nom procediment</label>
                    </div>
                    <div class="control">
                        <input id="cerca_procediment_nom" name="cerca_procediment_nom"
                            type="text" class="nom" />
                    </div>
                </div>
                <div class="element t7">
                    <div class="etiqueta">
                        <label for="cerca_procediment_codi">Codi</label>
                    </div>
                    <div class="control">
                        <input id="cerca_procediment_codi" name="cerca_procediment_codi"
                            type="text" />
                    </div>
                </div>
                <div class="element t12">
                    <div class="etiqueta">
                        <label for="cerca_procediment_finestreta">Inclou
                            finestreta única</label>
                    </div>
                    <div class="control">
                        <select id="cerca_procediment_finestreta"
                            name="cerca_procediment_finestreta" class="t8">
                            <option value="0" selected="selected">No</option>
                            <option value="1">Sí</option>
                        </select>
                    </div>
                </div>
                <div class="element t8">
                    <div class="etiqueta">
                        <label for="cerca_procediment_uaFilles">Inclou UA filles</label>
                    </div>
                    <div class="control">
                        <select id="cerca_procediment_uaFilles"
                            name="cerca_procediment_uaFilles" class="t8">
                            <option value="0" selected="selected">No</option>
                            <option value="1">Sí</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="botonera">
                <a href="javascript:;" class="btn consulta"><span><span>Cerca!</span>
                </span>
                </a>
            </div>
        </div>
        <!-- /cercador -->
        <div class="dades"></div>
        <input type="hidden" value="0" class="pagPagina" /> <input
            type="hidden" value="DESC" class="ordreTipus" /> <input
            type="hidden" value="publicacio" class="ordreCamp" />
    </div>
    <!-- /llistat -->
    <!-- seleccionats -->
    <div class="escriptori_items_seleccionats">
        <h3>Procediments seleccionats</h3>
        <p class="botonera">
            <a href="javascript:;" class="btn finalitza important"><span><span>Finalitza</span>
            </span>
            </a>
        </p>
        <div class="dades">
            <p class="info">No hi ha cap procediment.</p>
        </div>
    </div>
    <!-- seleccionats -->
    <div class="botonera baix">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
</div>
--%>