<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href='<c:url value="/css/personal.css"/>' rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/personal.js'/>"></script>
<script type="text/javascript">
    var pagLlistatPersonal = '<c:url value="/personal/llistat.do" />';
    var pagDetall = '<c:url value="/personal/pagDetall.do" />';
    var pagGuardar = '<c:url value="/personal/guardar.do" />';
    var pagEsborrar = '<c:url value="/personal/esborrarPersonal.do" />';
    
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
    var txtLlistaItem = "<spring:message code='txt.persona'/>";
    var txtLlistaItems = "<spring:message code='txt.persones'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtMostremAl = " <spring:message code='txt.a_la'/> ";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenades = "<spring:message code='txt.ordenades'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtPer = "<spring:message code='txt.per'/>";

    //taula
    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtUA = "<spring:message code='txt.ua'/>";
    var txtEmail = "<spring:message code='txt.email'/>";
    var txtEpui = "<spring:message code='txt.Epui'/>";

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

    //idioma
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

    //docs
    var txtDoc = "<spring:message code='txt.documents'/>";
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
                        "obligatori": "<spring:message code='personal.formulari.nom.obligatori'/>",
                        "tipus": "<spring:message code='personal.formulari.nom.no_nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_codi",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 10,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "obligatori": "<spring:message code='personal.formulari.codi.obligatori'/>",
                        "tipus": "<spring:message code='personal.formulari.codi.no_nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_ua_id",
                "obligatori": "si",
                "tipus": "numeric",
                "error":
                    {
		            	"obligatori": "<spring:message code='personal.formulari.ua.obligatori'/>",
		                "tipus": "<spring:message code='personal.formulari.ua.nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_funcions",
                "obligatori": "n0",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 230,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.funcions.no_nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_carrec",
                "obligatori": "n0",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim":230,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.carrec.no_nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_email",
                "obligatori": "no",
                "tipus": "email",
                "caracters":
                    {
                        "maxim": 230,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.email.format_no_adecuat'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_epui",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                    {
                        "maxim": 5,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.epui.nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nlpui",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                    {
                        "maxim": 9,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.nlpui.nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_epri",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                    {
                        "maxim": 5,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.epri.nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nlpri",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                    {
                        "maxim": 9,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.nlpri.nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_em",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                    {
                        "maxim": 5,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.em.nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nlm",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                    {
                        "maxim": 9,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "tipus": "<spring:message code='personal.formulari.nlm.nomes_numeros'/>"
                    }
            }
        ];
    -->
    </script>

<div id="escriptori_contingut"> <%-- style="display:none/block" --%>
    <ul id="opcions">
        <li class="opcio L actiu">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
        </li>
        <li class="opcio C"><!-- style="display: list-item" hasta descubrir donde lo pone la maqueta original -->
            <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
        </li>
        <c:if test="${idUA > 0}">
            <li class="opcions nuevo">
                <a id="btnNuevaFicha" href="javascript:;" class="btn nou"><span><span><spring:message code='personal.crea_nova_persona'/></span></span></a>
            </li>
        </c:if>
    </ul>   
    <div id="resultats">
        <div class="resultats L actiu">                         
            <div class="dades">             
                <p class="executant"><spring:message code='personal.carregant_llistat_personal'/></p>                           
            </div>                          
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="nombre" class="ordreCamp" />                            
        </div>
        <div class="resultats C">
            <div id="cercador"> <%-- style="display:none/block" --%>
                <div id="cercador_contingut">
                    <h2>Cercador</h2><%--action="<c:url value="/personal/processaCerca.do"/>" --%>                 
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_nom"><spring:message code='camp.nom'/></label></div>
                                <div class="control">                           
                                    <input id="cerca_nom" name="cerca_nom" type="text" maxlength="250" class="nom" />
                                </div>
                            </div>                                      
                            <div class="element t10">                    
                                <div class="etiqueta"><label for="cerca_codi"><spring:message code='camp.codi'/></label></div>
                                <div class="control">                                   
                                    <input id="cerca_codi" name="cerca_codi" type="text" maxlength="7" class="codi" />
                                </div>                  
                            </div>
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_ua"><spring:message code='camp.unitat_administrativa_competent'/></label></div>
                                <div class="control">
                                    <input id="cerca_ua" name="cerca_ua" type="text" maxlength="250" class="ua" value='<c:out value="${nomUA}" />' readonly="readonly"/>
                                    <input id="cerca_ua_id" name="cerca_ua_id" type="hidden" value='<c:out value="${idUA}" />'/>
                                </div>
                            </div>
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_funcions"><spring:message code='camp.funcions'/></label></div>
                                <div class="control">                                   
                                    <input id="cerca_funcions" name="cerca_funcions" maxlength="250" type="text" />
                                </div>
                            </div>
                            <div class="element t10">
                            <div class="etiqueta"><label for="cerca_carrec"><spring:message code='camp.carrec'/></label></div>
                                <div class="control">                                   
                                    <input id="cerca_carrec" name="cerca_carrec" maxlength="250" type="text" />
                                </div>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element t16">
                                <div class="etiqueta"><label for="cerca_epui"><spring:message code='camp.ext_publica_intra'/></label></div>
                                <div class="control">                                   
                                    <input id="cerca_epui" name="cerca_epui" type="text" maxlength="5" class="epui t8" />
                                </div>
                            </div>
                            <div class="element t16">
                                <div class="etiqueta"><label for="cerca_nlpui"><spring:message code='camp.num_llarg_public_intra'/></label></div>
                                <div class="control">                                   
                                    <input id="cerca_nlpui" name="cerca_nlpui" type="text" maxlength="9" class="nlpui t8" />
                                </div>
                            </div>
                            <div class="element t16">
                                <div class="etiqueta"><label for="cerca_epri"><spring:message code='camp.ext_privada_intra'/></label></div>
                                <div class="control">
                                    <input id="cerca_epri" name="cerca_epri" type="text" maxlength="5" class="epri t8" />
                                </div>      
                            </div>
                            <div class="element t16">
                                <div class="etiqueta"><label for="cerca_nlpri"><spring:message code='camp.num_llarg_provat_intra'/></label></div>
                                    <div class="control">
                                        <input id="cerca_nlpri" name="cerca_nlpri" type="text" maxlength="9" class="nlpri t8" />
                                    </div>
                            </div>
                            <div class="element t16">                   
                                <div class="etiqueta"><label for="cerca_em"><spring:message code='camp.extensio_mobil'/></label></div>
                                <div class="control">
                                    <input id="cerca_em" name="cerca_em" type="text" maxlength="5" class="em t8" />
                                </div>
                            </div>
                            <div class="element t16">
                                <div class="etiqueta"><label for="cerca_nlm"><spring:message code='camp.numero_llarg_mobil'/></label></div>
                                <div class="control">
                                    <input id="cerca_nlm" name="cerca_nlm" type="text" maxlength="9" class="nlm t8" />
                                </div>
                            </div>
                        </div>    
                        <div class="fila">
                            <div class="element t21">
                                <div class="etiqueta"><label for="cerca_email"><spring:message code='camp.email'/></label></div>
                                <div class="control">                                   
                                    <input id="cerca_email" name="cerca_email" type="text" maxlength="250" class="email" />
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
<div id="escriptori_detall" class="escriptori_detall"> <%--La linia de style ja ve al CSS  - display: block/none; --%>
    <form id="formGuardar" action="false">
    
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>            
    
        <input id="item_id" name="item_id" type="hidden" value="" class="nou" />
        <div class="botonera dalt">
            <ul>
                <%-- dsanchez: Al deshabilitar el botón hay que añadir la clase "off" --%>
                <!-- <li class="btnVolver off"> -->
                
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
        
        <div class="grupoModulosFormulario">            
            <!-- modul -->
        <div class="modul">                 
            <fieldset>                              
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                         
                <legend><spring:message code='personal.dades_personals'/></legend>                          
                <div class="modul_continguts mostrat">                              
                <!-- fila -->
                    <div class="fila">                              
                        <div class="element t30p">
                            <div class="etiqueta"><label for="item_nom"><spring:message code='camp.nom'/></label></div>
                            <div class="control">                           
                                <input id="item_nom" name="item_nom" type="text" class="nou" />
                            </div>                                      
                        </div>                                  
                        <div class="element t20p">                                  
                            <div class="etiqueta"><label for="item_codi"><spring:message code='camp.codi'/></label></div>
                            <div class="control">                       
                                <input id="item_codi" name="item_codi" type="text" class="nou t6" />
                            </div>                                      
                        </div>              
                        <div class="element t50p">                                  
                            <div class="etiqueta"><label for="item_carrec"><spring:message code='camp.carrec'/></label></div>
                            <div class="control">
                                <input id="item_carrec" name="item_carrec" type="text" class="nou" />                           
                            </div>                                      
                        </div>                        
                    </div>
                    <!-- /fila -->
                   
                    <!-- fila -->
                    <div class="fila">                              
                        <div class="element t99p">                                  
                            <div class="etiqueta"><label for="item_funcions"><spring:message code='camp.funcions'/></label></div>
                            <div class="control">
                                <input id="item_funcions" name="item_funcions" type="text" class="nou" />                           
                            </div>                                      
                        </div>                                  
                    </div>
                    <!-- /fila -->    
                    
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p">                                      
                           <input id="item_ua_id" name="item_ua_id" type="hidden" class="nou" value='<c:out value="${idUA}"/>'/>                                       
                           <div class="etiqueta"><label for="item_ua"><spring:message code='camp.unitat_administrativa_competent'/></label></div>
                           <div class="control">                           
                               <input id="item_ua" name="item_ua" type="text" readonly="readonly" value='<c:out value="${nomUA}"/>' />
                           </div>                                  
                       </div>        
                       <div class="element t50p">                                      
                       </div>                    
                    </div>                    
                    <!-- /fila -->
                    
                    <!-- fila -->
                    <div class="fila">
                       <div class="element t50p">
                           <div id="cercador">
                               <div class="botonera" style="margin-top: 0px; float:left;">
                                   <div class="boton btnGenerico" style="margin-left: 0px;">
                                   	   <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_ua_id', 'item_ua');" class="btn consulta">
                                       <%--  <a href="javascript:ArbreUA('item_ua', 'item_ua_id');" class="btn consulta"> --%>
                                       <span><span><spring:message code='boto.canviarUA'/></span></span>
                                       </a>
                                   </div>
                                   <div class="boton btnGenerico">
                                       <a href="javascript:EliminaArbreUA('item_ua', 'item_ua_id');" class="btn borrar">
                                       <span><span><spring:message code='boto.borrar'/></span></span>
                                       </a>
                                   </div>
                               </div>
                           </div>
                        </div>
                    </div>
                    <!-- fila -->
                </div>                          
            </fieldset>                 
        </div>
        <!-- /modul -->                 
        <!-- modul -->
        <div class="modul">                 
            <fieldset>                              
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                         
                <legend><spring:message code='personal.contacte'/></legend>                         
                <div class="modul_continguts mostrat">                              
                    <!-- fila -->
                    <div class="fila">                  
                        <div class="element t50p">                                  
                            <div class="etiqueta"><label for="item_email"><spring:message code='camp.email'/></label></div>
                            <div class="control">
                                <input id="item_email" name="item_email" type="text" class="nou" />
                            </div>                                      
                        </div>                                  
                        <div class="element t25p">                                  
                            <div class="etiqueta"><label for="item_epui"><spring:message code='camp.extensio_publica_intranet'/></label></div>
                            <div class="control">
                                <input id="item_epui" name="item_epui" type="text" class="nou t6" />
                            </div>                                      
                        </div>                                  
                        <div class="element t25p">                                  
                            <div class="etiqueta"><label for="item_nlpui"><spring:message code='camp.numero_llarg_public_intranet'/></label></div>
                            <div class="control">                               
                                <input id="item_nlpui" name="item_nlpui" type="text" class="nou t10" />
                            </div>                                      
                        </div>                                  
                    </div>
                    <!-- /fila -->
                                                  
                    <!-- fila -->
                    <div class="fila">                              
                        <div class="element t25p">                                  
                            <div class="etiqueta"><label for="item_epri"><spring:message code='camp.extensio_privada_intranet'/></label></div>
                            <div class="control">
                                <input id="item_epri" name="item_epri" type="text" class="nou t6" />
                            </div>                                      
                        </div>                                  
                        <div class="element t25p">                                  
                            <div class="etiqueta"><label for="item_nlpri"><spring:message code='camp.numero_llarg_provat_intranet'/></label></div>
                            <div class="control">
                                <input id="item_nlpri" name="item_nlpri" type="text" class="nou t10" />
                            </div>                                      
                        </div>                                  
                        <div class="element t25p">                                  
                            <div class="etiqueta"><label for="item_em"><spring:message code='camp.extensio_mobil'/></label></div>
                            <div class="control">
                                <input id="item_em" name="item_em" type="text" class="nou t6" />
                            </div>                                      
                        </div>                                  
                        <div class="element t25p">                                  
                            <div class="etiqueta"><label for="item_nlm"><spring:message code='camp.numero_llarg_mobil'/></label></div>
                            <div class="control">
                                <input id="item_nlm" name="item_nlm" type="text" class="nou t10" />
                            </div>                                      
                        </div>                              
                    </div>
                    <!-- /fila -->                          
                </div>                          
            </fieldset>                 
        </div>
    </form>
</div>