<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href='<c:url value="/css/normativa.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_afectacions.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_procediments.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_traspas.css"/>' rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/normativa.js'/>"></script>

    <script type="text/javascript">
    <!--
        // pagines  
        var pagLlistat = '<c:url value="/normativa/llistat.htm" />';
        var pagDetall = '<c:url value="/normativa/pagDetall.htm" />';
        var pagGuardar = '<c:url value="/normativa/guardar.htm" />';
        var pagEliminar = '<c:url value="/normativa/eliminar.htm" />';
        
        var pagBOIB = "json/boibsJSON.php";
        var pagNormativa = "json/normativesJSON.php";
        var pagProcediments = "json/procedimentsJSON.php";
        
        var idUaActual = '<c:out value="${idUA}" />';
        var nomUaActual = '<c:out value="${nomUA}" />';        
        
        // eixir aplicacio
        var txtEixirAplicacio = "Per raons de seguretat, és preferible que abandone l'aplicació fent clic al botó Tanca l'aplicació";
        // missatge
        var txtAccepta = "Accepta";
        var txtCancela = "Cancel·la";
        var txtTancarAplicacio = "Està segur de voler abandonar l'aplicació?";
        // cap
        var txtAmagar = "Amagar";
        var txtAmagarCap = txtAmagar + " capçalera";
        var txtMostrar = "Mostrar";
        var txtMostrarCap = txtMostrar + " capçalera";
        // molla pa
        var txtCarregantMollaFills = "Carregant unitats filles...";
        // texts
        var txtEspere = "Espere un moment, si us plau.";
        var txtCarregant = "Carregant";
        var txtSi = "Sí";
        var txtNo = "No";
        var txtTrobats = "Trobades";
        var txtLlistaItem = "Normativa";
        var txtLlistaItems = "Normatives";
        var txtData = "Data";
        var txtPublicacio = "Publicació";
        var txtCaducitat = "Caducitat";
        var txtMostrem = "Mostrem de la ";
        var txtMostremAl = " a la ";
        var txtNoHiHa = "No hi ha";
        var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
        var txtCarregantLlistat = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
        var txtOrdenats = "ordenats";
        var txtAscendentment = "ascendentment";
        var txtDescendentment = "descendentment";
        var txtPer = "per";
        // taula

        var txtNumero = "Número";
        var txtTipus = "Tipus";
        var txtTipologia = "Tipologia";
        var txtData = "Data";
        var txtLocal = "Local";
        var txtExterna = "Externa";
        var txtBOIB = "BOIB";
        
        
        var txtCercant = "Cercant";
        var txtCercantLlistat = txtCercant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
        // paginacio
        var txtTrobat = "S'ha trobat";
        var txtSeguents = "Següents";
        var txtAnteriors = "Anteriors";
        var txtInici = "Inici";
        var txtFinal = "Final";
        var txtPagines = "Pàgines";
        var txtCercantElements = txtCercant + " " + txtLlistaItems;
        var txtCercantLlistatAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantLlistatSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        
        var txtCercantAnteriors = txtCercantLlistatAnteriors;
        var txtCercantSeguents = txtCercantLlistatSeguents;
        
        // detall
        var txtCarregantDetall = txtCarregant + " detall de la "+ txtLlistaItem.toLowerCase() + ". " + txtEspere;
        var txtNouTitol = "Nova " + txtLlistaItem.toLowerCase();
        var txtDetallTitol = "Detall de la " + txtLlistaItem.toLowerCase();
        var txtItemEliminar = "Està segur de voler eliminar aquesta " + txtLlistaItem.toLowerCase() + "?";
        var txtEnviantDades = "Enviant dades al servidor. " + txtEspere;
        var txtMostra = "Mostra";
        var txtAmaga = "Amaga";
        var txtCaducat = "Caducada";
        var txtImmediat = "Immediat";
        // idioma
        var txtDesplega = "Desplega";
        var txtPlega = "Plega";
        // fotos
        var txtImatge = "Imatge";
        var txtFoto = "Foto";
        var txtFotos = "Fotos";
        var txtFotoPetita = txtFoto + " petita";
        var txtFotoGran = txtFoto + " gran";
        var txtLleva = "Lleva";
        var txtInclou = "Inclou";
        var txtElimina = "Elimina";
        var txtNoHiHaFotos = txtNoHiHa + " " + txtFotos.toLowerCase() + " associades";
        // docs
        var txtNom = "Nom";
        var txtArxiu = "Arxiu"
        // enllasos
        var txtAdresa = "Adreça web";
        // moduls
        var txtHiHa = "Hi ha";
        var txtNoHiHa = "No hi ha";
        var txtItems = "Ítems";
        var txtCarregantItems = "Carregant " + txtItems.toLowerCase();
        var txtCercantItems = "Cercant " + txtItems.toLowerCase();
        var txtCercantItemsAnteriors = "Cercant " + txtItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantItemsSeguents = "Cercant " + txtItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        // modul afectacions
        var txtAfectacio = "Afectació";
        var txtAfectacions = "Afectacions";
        var txtNoHiHaAfectacions = txtNoHiHa + " " + txtAfectacions.toLowerCase();
        var txtSeleccionada = "Seleccionada";
        var txtSeleccionades = "Seleccionades";
        var txtNoHiHaAfectacionsSeleccionats = "No hi ha " + txtAfectacions.toLowerCase() + " " + txtSeleccionades.toLowerCase();
        var txtNormativa = "Normativa";
        var txtNormatives = "Normatives";
        var txtAmbLaNorma = "amb la norma";
        // modul BOIB
        var txtRegistre = "Registre";
        var txtNoHiHaNormativaBOIB = txtNoHiHa + " normativa BOIB";
        
        /*
        <?
        // afectacions
        $query_afectacions = "SELECT * ";
        $query_afectacions .= "FROM afectacio";
        $afectacions = mysql_query($query_afectacions, $rolsac) or die(mysql_error());
        $row_afectacions = mysql_fetch_assoc($afectacions);
        $totalRows_afectacions = mysql_num_rows($afectacions);
        $Afectacions_arr = "var Afectacions_arr = [";
        $i = 0;
        if ($totalRows_afectacions > 0) {
            do {
                if ($i > 0) { $Afectacions_arr .= ","; }
                $Afectacions_arr .= "{";
                $Afectacions_arr .= "\"id\": ".$row_afectacions['afectacio_id'].",";
                $Afectacions_arr .= "\"nom\": \"".utf8_encode($row_afectacions['afectacio_nom'."_".$_SESSION['rolsac_idioma']])."\"";
                $Afectacions_arr .= "}";
                $i++;
            } while ($row_afectacions = mysql_fetch_assoc($afectacions));
        }
        mysql_free_result($afectacions);
        $Afectacions_arr .= "];";
        echo $Afectacions_arr;
        ?>
        */
        
        // modul procediments
        var txtProcediment = "Procediment";
        var txtProcediments = "Procediments";
        var txtNoHiHaProcediments = "No hi ha " + txtProcediments.toLowerCase();
        var txtSeleccionat = "Seleccionat";
        var txtSeleccionats = "Seleccionats";
        var txtNoHiHaProcedimentsSeleccionats = "No hi ha " + txtProcediments.toLowerCase() + " " + txtSeleccionats.toLowerCase();
        var txtPublicacio = "Publicació";
        var txtCaducitat = "Caducitat";
        
        // error conexio
        var txtCalcularTotal = "Calcular el total";
        var txtAjaxError = "S'ha produït un error de conexió.";
        var txtIntenteho = "Intente-ho de nou en uns minuts.";
        var txtConexionIntentar = "Intentar conexió en";
        var txtSegon = "segon";
        var txtSegons = "segons";
        var txtConectar = "Conectar ara";
        var txtFuncions = "Les funcions de l'aplicació s'han deshabilitat";
        var txtFuncionsFins = "fins que no es restableixi la comunicació";
        
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
                "etiquetaValor": "item_numero",
                "obligatori": "si",
                "tipus": "numeric",
                "error":
                    {
                        "obligatori": "<spring:message code='normativa.formulari.numero.obligatori'/>"
                    }
            },          

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
                    <h2>Cercador</h2>
                    
                    <div class="fila">
                        <div class="element t10">
                            <div class="etiqueta">
                                <label for="cerca_numero"><spring:message code='camp.numero'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_numero" name="cerca_numero" type="text" />
                            </div>                          
                        </div>
                        
                        <div class="element t16">
                            <div class="etiqueta">
                                <label for="cerca_validacio"><spring:message code='camp.validacio'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_validacio" name="cerca_validacio">
                                    <option value="1" selected="selected">Pública</option>
                                    <option value="2">Interna</option>
                                    <option value="3">Reserva</option>
                                </select>
                            </div>
                        </div>                          
                        
                        <div class="element t29">
                            <div class="etiqueta">
                                <label for="cerca_tipus_normativa"><spring:message code='camp.tipus_normativa'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_tipus_normativa" name="cerca_tipus_normativa">
                                    <option value="">--Tots--</option>
                                    <c:forEach items="${llistaTipusNormativa}" var="tipus">                                     
                                        <option value='<c:out value="${tipus.id}" />'><c:out value="${tipus.nom}" /></option>
                                    </c:forEach>
                                </select>                               
                            </div>                          
                        </div>
                        <div class="element t16">
                            <div class="etiqueta">
                                <label for="cerca_butlleti"><spring:message code='camp.butlleti'/></label>
                            </div>
                            <div class="control">
                                <select id="cerca_butlleti" name="cerca_butlleti">
                                    <option value="">--Tots--</option>
                                    <c:forEach items="${llistaButlletins}" var="butlleti">                                      
                                        <option value='<c:out value="${butlleti.id}" />'><c:out value="${butlleti.nom}" /></option>
                                    </c:forEach>
                                </select>                               
                            </div>                          
                        </div>                                              
                    </div>      
                    
                    <div class="fila">
                        <div class="element t10">
                            <div class="etiqueta">
                                <label for="cerca_registre"><spring:message code='camp.registre'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_registre" name="cerca_registre" type="text" />
                            </div>                          
                        </div>  
                        
                        <div class="element t10">
                            <div class="etiqueta">
                                <label for="cerca_llei"><spring:message code='camp.llei'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_llei" name="cerca_llei" type="text" />
                            </div>                          
                        </div>      
                        
                        <div class="element t10">
                            <div class="etiqueta">
                                <label for="cerca_data"><spring:message code='camp.data'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_data" name="cerca_data" type="text" class="data" />
                            </div>
                        </div>          
                        
                        <div class="element t10">
                            <div class="etiqueta">
                                <label for="cerca_data_butlleti"><spring:message code='camp.data_butlleti'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_data_butlleti" name="cerca_data_butlleti" type="text" class="data" />
                            </div>
                        </div>
                    </div>          
                    
                    <div class="fila">
                        <!-- div class="element t29">
                            <div class="etiqueta">
                                <label for="cerca_titol"><spring:message code='camp.titol_normativa'/></label>
                            </div>
                            <div class="control">
                                <input id="cerca_titol" name="cerca_titol" type="text" size="50" maxlength="250" class="titol" />
                            </div>
                        </div -->
                        
                        <div class="element t30">
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
<div id="escriptori_detall"> <%--La linia de style ja ve al CSS  - display: block/none; --%>
    <form id="formGuardar" action="false">
    <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
    <p>Recordi que les dades amb asterisc (<span class="obligatori">*</span>) són obligatòries.</p>
        
    <%--
    <div class="botonera dalt">
        <ul>
            <li class="btnVolver">
                <a href="javascript:;" class="btn torna"><span><span>Torna</span></span></a>
            </li>
            <li class="btnGuardar">
                <a href="javascript:;" class="btn guarda important"><span><span>Guarda</span></span></a>
            </li>           
            <li class="e btnEliminar">
                <a href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
            </li>
        </ul>
    </div>
     --%>
    
    <!-- modulPrincipal -->
    <div id="modulPrincipal" class="grupoModulosFormulario">        
        <!-- modul -->
        <div class="modul">
            <fieldset>
                <a class="modul mostrat">Amaga</a>
                <legend>Dades</legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <p class="introIdiomas">Idioma:</p>
                        <ul class="idiomes">
                            <li class="idioma"><a href="javascript:;" class="ca">Català</a></li>
                            <li class="idioma"><a href="javascript:;" class="es">Español</a></li>
                            <li class="idioma"><a href="javascript:;" class="en">English</a></li>
                            <li class="idioma"><a href="javascript:;" class="de">Deutsch</a></li>
                            <li class="idioma"><a href="javascript:;" class="fr">Français</a></li>
                            
                            <li class="traduix btnGenerico">
                                <a href="javascript:;" class="btn traduix"><span><span>Traduïx</span></span></a>
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
                                        <div class="etiqueta"><label for="item_titol_ca">Títol de la normativa</label></div>
                                        <div class="control">
                                            <input id="item_titol_ca" name="item_titol_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_ca">Enllaç</label></div>
                                        <div class="control">
                                            <input id="item_enllas_ca" name="item_enllas_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_ca">Apartat</label></div>
                                        <div class="control">
                                            <input id="item_apartat_ca" name="item_apartat_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_ca">Pàgina inicial</label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_ca" name="item_pagina_inicial_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_ca">Pàgina final</label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_ca" name="item_pagina_final_ca" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_ca">Responsable</label></div>
                                        <div class="control">
                                            <input id="item_responsable_ca" name="item_responsable_ca" type="text" class="nou" />
                                        </div>
                                    </div>                                    
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_arxiu_ca">Arxiu</label></div>
                                        <div class="control">
                                            <input id="item_arxiu_ca" name="item_arxiu_ca" type="file" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_ca">Observacions</label></div>
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
                                        <div class="etiqueta"><label for="item_titol_es">Títol de la normativa</label></div>
                                        <div class="control">
                                            <input id="item_titol_es" name="item_titol_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_es">Enllaç</label></div>
                                        <div class="control">
                                            <input id="item_enllas_es" name="item_enllas_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_es">Apartat</label></div>
                                        <div class="control">
                                            <input id="item_apartat_es" name="item_apartat_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_es">Pàgina inicial</label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_es" name="item_pagina_inicial_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_es">Pàgina final</label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_es" name="item_pagina_final_es" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_es">Responsable</label></div>
                                        <div class="control">
                                            <input id="item_responsable_es" name="item_responsable_es" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_arxiu_es">Arxiu</label></div>
                                        <div class="control">
                                            <input id="item_arxiu_es" name="item_arxiu_es" type="file" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_es">Observacions</label></div>
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
                                        <div class="etiqueta"><label for="item_titol_en">Títol de la normativa</label></div>
                                        <div class="control">
                                            <input id="item_titol_en" name="item_titol_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_en">Enllaç</label></div>
                                        <div class="control">
                                            <input id="item_enllas_en" name="item_enllas_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_en">Apartat</label></div>
                                        <div class="control">
                                            <input id="item_apartat_en" name="item_apartat_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_en">Pàgina inicial</label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_en" name="item_pagina_inicial_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_en">Pàgina final</label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_en" name="item_pagina_final_en" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_en">Responsable</label></div>
                                        <div class="control">
                                            <input id="item_responsable_en" name="item_responsable_en" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_arxiu_en">Arxiu</label></div>
                                        <div class="control">
                                            <input id="item_arxiu_en" name="item_arxiu_en" type="file" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_en">Observacions</label></div>
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
                                        <div class="etiqueta"><label for="item_titol_de">Títol de la normativa</label></div>
                                        <div class="control">
                                            <input id="item_titol_de" name="item_titol_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_de">Enllaç</label></div>
                                        <div class="control">
                                            <input id="item_enllas_de" name="item_enllas_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_de">Apartat</label></div>
                                        <div class="control">
                                            <input id="item_apartat_de" name="item_apartat_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_de">Pàgina inicial</label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_de" name="item_pagina_inicial_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_de">Pàgina final</label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_de" name="item_pagina_final_de" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_de">Responsable</label></div>
                                        <div class="control">
                                            <input id="item_responsable_de" name="item_responsable_de" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_arxiu_de">Arxiu</label></div>
                                        <div class="control">
                                            <input id="item_arxiu_de" name="item_arxiu_de" type="file" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_de">Observacions</label></div>
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
                                        <div class="etiqueta"><label for="item_titol_fr">Títol de la normativa</label></div>
                                        <div class="control">
                                            <input id="item_titol_fr" name="item_titol_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_enllas_fr">Enllaç</label></div>
                                        <div class="control">
                                            <input id="item_enllas_fr" name="item_enllas_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_apartat_fr">Apartat</label></div>
                                        <div class="control">
                                            <input id="item_apartat_fr" name="item_apartat_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_inicial_fr">Pàgina inicial</label></div>
                                        <div class="control">
                                            <input id="item_pagina_inicial_fr" name="item_pagina_inicial_fr" type="text" class="nou" />
                                        </div>

                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_pagina_final_fr">Pàgina final</label></div>
                                        <div class="control">
                                            <input id="item_pagina_final_fr" name="item_pagina_final_fr" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t50p">
                                        <div class="etiqueta"><label for="item_responsable_fr">Responsable</label></div>
                                        <div class="control">
                                            <input id="item_responsable_fr" name="item_responsable_fr" type="text" class="nou" />
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_arxiu_fr">Arxiu</label></div>
                                        <div class="control">
                                            <input id="item_arxiu_fr" name="item_arxiu_fr" type="file" class="nou" />
                                        </div>
                                    </div>
                                </div>
                                <div class="fila">
                                    <div class="element t99p">
                                        <div class="etiqueta"><label for="item_des_curta_fr">Observacions</label></div>
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
                <a class="modul mostrat">Amaga</a>
                <legend>Gestió</legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_numero">Número</label></div>
                            <div class="control">
                                <input id="item_numero" name="item_numero" type="text" class="nou" />
                            </div>
                        </div>
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_data">Data</label></div>
                            <div class="control">
                                <input id="item_data" name="item_data" type="text" class="data nou" />
                            </div>
                        </div>  
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_registre">Registre</label></div>
                            <div class="control">
                                <input id="item_registre" name="item_registre" type="text" class="nou" />
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_butlleti_id">Butlletí</label></div>
                            <!-- div class="control">
                                <input id="item_butlleti_id" name="item_butlleti_id" type="hidden" />
                                <input id="item_butlleti" name="item_butlleti" type="text" class="nou" />
                            </div-->
                            <div class="control">
                                <select id="item_butlleti_id" name="item_butlleti_id" class="nou">
                                    <option value="">- -No definit --</option>
                                    <c:forEach items="${llistaButlletins}" var="butlleti">                                      
                                        <option value='<c:out value="${butlleti.id}" />'><c:out value="${butlleti.nom}" /></option>
                                    </c:forEach>
                                </select>       
                            </div>                      
                        </div>
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_data_butlleti">Data del butlletí</label></div>
                            <div class="control">
                                <input id="item_data_butlleti" name="item_data_butlleti" type="text" class="nou" />
                            </div>
                        </div>
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_tipus">Tipus de normativa</label></div>
                            <div class="control">
                                <select id="item_tipus" name="item_tipus" class="nou">
                                    <option value="">-- No definit --</option>
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
                            <div class="etiqueta"><label for="item_ua_nom">Unitat Administrativa</label></div>
                            <div class="control">
                                <input id="item_ua_id" name="item_ua_id" type="hidden" />
                                <input id="item_ua_nom" name="item_ua_nom" type="text" />
                            </div>
                        </div>
                                            
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_llei">Llei</label></div>
                            <div class="control">
                                <input id="item_llei" name="item_llei" type="text" class="nou" />
                            </div>
                        </div>                  
                    </div>
                    <!-- /fila -->
                    
                    <!-- Botonera -->
                    <div id="cercador">
                        <div class="botonera" style="margin-top: 0px; float:left;">
                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                <a href="javascript:ArbreUA('item_ua_nom', 'item_ua_id');" class="btn consulta">
                                <span><span>Canviar UA</span></span>
                                </a>
                            </div>
                            <div class="boton btnGenerico">
                                <a href="javascript:EliminaArbreUA('item_ua_nom', 'item_ua_id');" class="btn borrar">
                                <span><span>Borrar</span></span>
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
                <legend>Publicació</legend>
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
                                <label for="item_validacio">Validació</label>
                            </div>
                            <div class="control">
                                <select id="item_validacio" name="item_validacio">
                                    <option value="1" selected="selected">Pública</option>
                                    <option value="2">Interna</option>
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
                              <a id="btnVolver" href="javascript:;" class="btn torna"><span><span>Torna</span></span></a>
                          </li>

                          <li class="btnGuardar par">
                              <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span>Guarda!</span></span></a>
                          </li>                                                    
                          <li class="btnEliminar impar" style="display:none;">

                              <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span>Elimina</span></span></a>
                          </li>
                          <li class="btnPrevisualizar par">
                              <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span>Previsualitza</span></span></a>
                          </li>
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
        <!-- div class="modul">
            <fieldset>
                <a class="modul mostrat">Amaga</a>
                <legend>Tipologia</legend>
                <div class="modul_continguts mostrat">                    
                    <div class="modulTraspas">                        
                        <div class="fila">
                            <div class="element">
                                <div class="etiqueta">
                                    <label for="item_tipologia">Tipus de norma</label>
                                </div>
                                <div class="control">
                                    <select id="item_tipologia" name="item_tipologia">
                                        <option value="L" selected="selected">Local</option>
                                        <option value="E">Externa</option>
                                        <option value="B">BOIB</option>
                                    </select> 
                                    <a id="gestioTraspas" class="btn gestiona" href="javascript:;"><span><span>Gestiona traspàs</span></span></a>
                                </div>
                            </div>
                        </div>                        
                    </div>                    
                </div>
            </fieldset>
        </div-->
        <!-- /modul -->
        <!-- modul -->
        
        <input type="hidden" id="item_tipologia" name="item_tipologia" />
        
        <div class="modul">
            <fieldset>
                <a class="modul mostrat">Amaga</a>
                <legend>Procediments relacionats</legend>
                <div class="modul_continguts mostrat">
                    <!-- modulProcediments -->
                    <div class="modulProcediments">
                        <div class="seleccionats">
                            <p class="info">No hi ha procediments.</p>
                            <p class="btnGenerico">
                                <a class="btn gestiona" href="javascript:;"><span><span>Gestiona procediments</span></span></a>
                            </p>
                        </div>
                    </div>
                    <!-- /modulProcediments -->
                </div>
            </fieldset>
        </div>
        <!-- /modul -->
        <!-- modul -->
        <div class="modul">
            <fieldset>
                <a class="modul amagat">Mostra</a>
                <legend>Afectacions relacionades</legend>
                <div class="modul_continguts">
                    <!-- modulAfectacions -->
                    <div class="modulAfectacions">
                        <div class="seleccionats">
                            <p class="info">No hi ha afectacions.</p>
                            <p class="btnGenerico">
                                <a class="btn gestiona" href="javascript:;"><span><span>Gestiona afectacions</span></span></a>
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
    <%--
    <div class="botonera baix">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al llistat</span>
                </span>
            </a></li>
            <li class="e"><a href="javascript:;" class="btn elimina"><span><span>Elimina</span>
                </span>
            </a></li>
        </ul>
    </div>
    --%>
    
    </form>
</div>
<!-- /escriptori_detall -->
<!-- escriptori_previsualitza -->
<div id="escriptori_previsualitza">
    <h2>Previsualitzant la normativa</h2>
    <p>
        <a href="javascript:;" class="btn torna dePrevisualitzar"><span><span>Torna</span>
        </span>
        </a>
    </p>
    <div class="previsualitzacio">
        <iframe frameborder="0" scrolling="auto"></iframe>
    </div>
</div>
<!-- /escriptori_previsualitza -->
<!-- escriptori_traspas -->
<div id="escriptori_traspas">
    <h2>Gestió del traspàs del BOIB</h2>
    <div class="botonera dalt">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
    <p>Use el cercador per a trobar la norma que dessitja traspassar.
        Després simplement premi al títol de la mateixa per a importar totes
        les dades.</p>
    <!-- llistat -->
    <div class="escriptori_items_llistat">
        <!-- cercador -->
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
                
                <input id="cerca_ua_id" name="cerca_ua_id" type="hidden" value='<c:out value="${idUA}" />'/>
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
            type="hidden" value="data" class="ordreCamp" />
    </div>
    <!-- /llistat -->
    <div class="botonera baix">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
</div>
<!-- /escriptori_traspas -->
<!-- escriptori_afectacions -->
<div id="escriptori_afectacions">
    <h2>Gestió de l'afectació relacionada</h2>
    <div class="botonera dalt">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
        </ul>
    </div>
    <!-- llistat -->
    <div class="escriptori_items_llistat">
        <!-- cercador -->
        <div class="escriptori_items_cercador">
            <h3>Cercador</h3>
            <div class="fila">
                <div class="element t15">
                    <div class="etiqueta">
                        <label for="cerca_normativa_titol">Títol de la normativa</label>
                    </div>
                    <div class="control">
                        <input id="cerca_normativa_titol" name="cerca_normativa_titol"
                            type="text" class="titol" />
                    </div>
                </div>
                <div class="element t7">
                    <div class="etiqueta">
                        <label for="cerca_normativa_codi">Codi</label>
                    </div>
                    <div class="control">
                        <input id="cerca_normativa_codi" name="cerca_normativa_codi"
                            type="text" class="codi" />
                    </div>
                </div>
                <div class="element t18">
                    <div class="etiqueta">
                        <label for="cerca_normativa_no_relacionades">Cerca per
                            normativa no relacionada</label>
                    </div>
                    <div class="control">
                        <select id="cerca_normativa_no_relacionades"
                            name="cerca_normativa_no_relacionades" class="t8">
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
            type="hidden" value="data" class="ordreCamp" />
    </div>
    <!-- /llistat -->
    <!-- seleccionats -->
    <div class="escriptori_items_seleccionats">
        <h3>Afectacions amb la norma seleccionada</h3>
        <p class="botonera">
            <a href="javascript:;" class="btn finalitza important"><span><span>Finalitza</span>
            </span>
            </a>
        </p>
        <div class="dades">
            <p class="info">No hi ha cap afectació.</p>
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
<!-- /escriptori_afectacions -->
<!-- escriptori_procediments -->
<div id="escriptori_procediments">
    <h2>Gestió dels procediments relacionats</h2>
    <div class="botonera dalt">
        <ul>
            <li><a href="javascript:;" class="btn torna"><span><span>Torna
                            al detall</span>
                </span>
            </a></li>
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