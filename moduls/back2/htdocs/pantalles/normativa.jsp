<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>

<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>

<link rel="stylesheet" type="text/css" href='<c:url value="/css/normativa.css"/>'  media="screen" />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/modul_afectacions.css"/>'  media="screen" />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/modul_documents.css"/>' media="screen" />

<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ajax.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/normativa.js?versio=${rolsac_einarevision}' />"></script>
<script type="text/javascript" src="<c:url value='/js/modul_afectacions.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_auditories.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_documents.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_unitats_adminstratives.js'/>"></script>

<script type="text/javascript">
<!--
	var permisoGestionNormativas=<c:out value="${permisoGestionNormativas}" />;
	// pagines  
	var pagLlistat = '<c:url value="/normativa/llistat.do" />';
	var pagExportar = '<c:url value="/normativa/exportar.do" />';
	var pagDetall = '<c:url value="/normativa/pagDetall.do" />';
	var pagGuardar = '<c:url value="/normativa/guardar.do" />';
	var pagEliminar = '<c:url value="/normativa/eliminar.do" />';
	var pagCercaBoib = '<c:url value="/normativa/cercaBoib.do" />';
	var pagDetallBoib = '<c:url value="/normativa/detallBoib.do" />';
	var pagAuditories = '<c:url value="/auditories/llistat.do" />';
	var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
	var pagArrel = '<c:url value="/" />';
 	var pagNormativa = '<c:url value="/normativa/cercarNormatives.do" />';
 	var pagTraduirNormativa = '<c:url value="/normativa/traduir.do" />';
 	var pagCarregarDoc = '<c:url value="/normativa/carregarDocument.do" />';  
 	var modulos = '<c:url value="/normativa/modulos.do" />';
	var pagGuardarDoc = '<c:url value="/normativa/guardarDocument.do" />';
      
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
	var txtData = "<spring:message code='txt.dataNorma'/>";
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
	var txtProcessant = "<spring:message code='txt.processant'/>";
	var txtDocument = "<spring:message code='txt.document'/>";    
    var txtDocuments = "<spring:message code='txt.documents'/>";
    var txtNormativaValida= "<spring:message code='camp.norm.valid.valid'/>";
    var txtNormativaNoValida= "<spring:message code='camp.norm.valid.invalid'/>";
    var txtNormativaSinvalorar = "<spring:message code='camp.norm.valid.sinvalorar'/>";
    
    var txtDocument = "<spring:message code='txt.document'/>";    
    var txtDocuments = "<spring:message code='txt.documents'/>";
    var txtFormulari = "<spring:message code='txt.formulari'/>";
    var txtFormularis = "<spring:message code='txt.formularis'/>";
    var txtTaxa = "<spring:message code='txt.taxa'/>";
    var txtTaxes = "<spring:message code='txt.taxes'/>";
    var txtNoHiHaDocuments = txtNoHiHa + " " + txtDocuments;
    var txtNoHiHaTaxes = txtNoHiHa + " " + txtTaxes;
    var txtNoHiHaDocumentsSeleccionats = txtNoHiHaDocuments + " " + txtSeleccionats.toLowerCase();
    var txtNoHiHaTaxesSeleccionades = txtNoHiHaTaxes + " " + txtSeleccionades.toLowerCase();
    var txtErrorTamanyoFitxer ="<spring:message code='error.fitxer.tamany_nom'/>";
	
	// taula
	var txtNumero = "<spring:message code='camp.numero'/>";
    var txtNumBoletin = "<spring:message code='camp.nombreButlleti'/>";
    var txtTipus = "<spring:message code='camp.tipus'/>";
    var txtTipoBoletin = "<spring:message code='camp.tipusButlleti'/>";
    var txtBoletin = "<spring:message code='txt.butlleti'/>";
    var txtTipologia = "<spring:message code='camp.tipologia'/>";
    var txtTipologiaNorma = "<spring:message code='camp.tipologiaNorma'/>";
    var txtNumNorma = "<spring:message code='txt.numNorma'/>";
    var txtFechaAprobacio = "<spring:message code='txt.dataAprovacio'/>";
    var txtFechaBoletin = "<spring:message code='camp.dataButlleti'/>";
    var txtLocal = "<spring:message code='txt.local'/>";
    var txtExterna = "<spring:message code='txt.externa'/>";
    var txtBOIB = "<spring:message code='txt.boib'/>";
    var txtNumRegistro = "<spring:message code='camp.registre'/>";
    
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
    
	 // Textes mòdul UAs
    var txtUnitatAdministrativa = "<spring:message code='unitatAdministrativa.ua'/>";
    var txtUnitatsAdministratives = "<spring:message code='unitatAdministrativa.uas'/>";
    var txtNoHiHaUA = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase();
    var txtNoHiHaUAs = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase();
    var txtSeleccionada =  "<spring:message code='txt.seleccionada'/>";
    var txtSeleccionades = "<spring:message code='txt.seleccionades'/>";
    var txtNoHiHaUASeleccionada = txtNoHiHa + " " + txtUnitatAdministrativa.toLowerCase() + " " + txtSeleccionada.toLowerCase();
    var txtNoHiHaUAsSeleccionades = txtNoHiHa + " " + txtUnitatsAdministratives.toLowerCase() + " " + txtSeleccionades.toLowerCase();

   
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
    
    var txtIntroduceBoibOFecha = "<spring:message code='normativa.formulari.traspas_eboib.introduce_boib_o_fecha'/>";
    
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
    
	// modul serveis
    var txtServei = "<spring:message code='txt.servei'/>";
    var txtServeis = "<spring:message code='txt.serveis'/>";
    var txtNoHiHaServeis = txtNoHiHa + " " + txtServeis.toLowerCase();
    var txtNoHiHaServeisSeleccionats = txtNoHiHa + " " + txtServeis.toLowerCase() + " " + txtSeleccionats.toLowerCase();        
    
    var txtColUsuario = "<spring:message code='txt.auditoria.usu'/>";
    var txtColNombre = "<spring:message code='txt.auditoria.nombre'/>";
    var txtColFecha = "<spring:message code='txt.auditoria.fecha'/>";
    var txtColOperacion = "<spring:message code='txt.auditoria.operacion'/>";
    
    // suggeriments
    /*var suggeriments = [
        {
            etiqueta: "id",
            etiquetaValor: "cerca_titol",
            pagina: "json/normativesJSON_consulta.php"
        }
    ];*/
-->
</script>
      
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
                    "obligatori": "<spring:message code='normativa.formulari.titol.obligatori'/> " + '<c:out value="${idiomaVal}"/>',
                    "tipus": "<spring:message code='normativa.formulari.titol_ca.no_nomes_numeros'/>"
                }
        },

        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_enllac_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "no",
            "tipus": "alfanumeric",
            "caracters":
            {
                "maxim": 480,
                "mostrar": "no",
                "abreviat": "no"
            }
        },

       

        {
            "modo": "individual",
            "etiqueta": "id",
            "etiquetaValor": "item_responsable_" + '<c:out value="${idiomaVal}"/>',
            "obligatori": "no",
            "tipus": "alfanumeric",
            "caracters":
            {
                "maxim": 480,
                "mostrar": "no",
                "abreviat": "no"
            }
        },
           {
               "modo": "individual",
               "etiqueta": "id",
               "etiquetaValor": "item_data_norma",
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
   				"obligatori": "<spring:message code='normativa.formulari.error.dataaprobacio.obligatori'/>"
   			  }
              },
              
           
           
           
           
          {
              "modo": "individual",
              "etiqueta": "id",
              "etiquetaValor": "item_llei",
              "obligatori": "no",
              "tipus": "alfanumeric",
              "caracters":
              {
                  "maxim": 230,
                  "mostrar": "no",
                  "abreviat": "no"
              }
          },

          {
              "modo": "individual",
              "etiqueta": "id",
              "etiquetaValor": "item_validacio",
              "obligatori": "si",
              "tipus": "numeric",
              "caracters":
              {
                  "maxim": 10,
                  "mostrar": "no",
                  "abreviat": "no"
              },
              "error":
                  {
                      "obligatori": "<spring:message code='normativa.formulari.registre.estat'/>"
                  }
          },
          {
              "modo": "individual",
              "etiqueta": "id",
              "etiquetaValor": "item_tipus",
              "obligatori": "si",
              "tipus": "numeric",
              "error":
               {
                      "obligatori": "<spring:message code='normativa.formulari.tipus.obligatori'/> "
               }
          },
          // Boletin
          {
              "modo": "individual",
              "etiqueta": "id",
              "etiquetaValor": "item_butlleti_id",
              "obligatori": "si",
              "tipus": "alfanumeric",
              "error":
                  {
                  	"obligatori": "<spring:message code='normativa.formulari.error.bulleti.obligatori'/>"
                  }
          },
		    // Enlace
			  {
				  "modo": "individual",
				  "etiqueta": "id",
				  "etiquetaValor": "item_enllac_" + '<c:out value="${idiomaVal}"/>',
				  "obligatori": "si",
				  "tipus": "alfanumeric",
				  "error":
					  {
						"obligatori": "<spring:message code='normativa.formulari.error.enllac.obligatori'/>"
					  }
			  }
		
         
      ];
      
      <c:if test="${traspasboib == 'Y'}">
      
       // Validación del formulario de búsqueda de Traspaso EBOIB
       var FormularioBusquedaTB = [{
           "modo": "individual",
           "etiqueta": "id",
           "etiquetaValor": "numeroboletinTB",
           "obligatori": "no",
           "caracters":
               {
                   "maxim": 50,
                   "mostrar": "no",
                   "abreviat": "no"
               },
           "error":
               {
                   "obligatori": "",
                   "tipus": "<spring:message code='normativa.formulari.traspas_eboib.campo_boletin_numero'/>"
               }
           
           },{
           
           "modo": "individual",
           "etiqueta": "id",
           "etiquetaValor": "numeroregistroTB",
           "obligatori": "no",
           "tipus": "numeric",
           "caracters":
               {
                   "maxim": 50,
                   "mostrar": "no",
                   "abreviat": "no"
               },
           "error":
               {
                   "obligatori": "",
                   "tipus": "<spring:message code='normativa.formulari.traspas_eboib.campo_registro_numero'/>"
               }
           }
           
          ];
      
      </c:if>
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
		<li class="opcio nuevo"><!--  que es L C ... -->
			<a id="tabTraspasBoib" href="javascript:;"><spring:message code='normativa.traspas.boib'/></a>
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
            <input type="hidden" value="id" class="ordreCamp" />
        </div>
		<div class="resultats C">
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
                                <label for="cerca_totes_unitats"><spring:message code='camp.cerca_totes_unitats'/></label>                                
                                <input id="cerca_totes_unitats" name="cerca_totes_unitats" type="checkbox" value="1"/>
                            </div>
                        </div>
                        <div class="fila">
                            <div class="element">
                                <label for="cerca_validacio">Visibilitat</label>
                                <select id="cerca_validacio" name="cerca_validacio">
                                    <c:set var="rolSuper"><rol:userIsSuper/></c:set>
                                    <c:choose>
                                        <c:when test="${rolSuper}" >
                                            <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                
                                            <option value="1"><spring:message code='txt.validacio.vigente'/></option>
                                            <option value="2"><spring:message code='txt.validacio.derogada'/></option>
                                                                                                                      
                                        </c:when>
                                        <c:otherwise>
                                            <option value="1" selected="selected"><spring:message code='txt.validacio.vigente'/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>                        
                        </div>
                    </div>
                       
                    <div class="busquedaBasica">
                        <h2><spring:message code='tab.cercador'/></h2>
                    
                        <input id="cerca_ua_id" name="cerca_ua_id" type="hidden" value='<c:out value="${idUA}" />'/>
                    
                        <div class="fila">
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_codi">Codi</label>
                                </div>
                                <div class="control">
                                    <input id="cerca_codi" name="cerca_codi" type="text" />
                                </div>                          
                            </div>
                            
                            <div class="element t75">
                                <div class="etiqueta">
                                    <label for="cerca_text"><spring:message code='camp.text'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_text" name="cerca_text" type="text" maxlength="250" />
                                </div>
                            </div>
                        </div>
                    </div>
                        
                    <div class="busquedaAvanzada">
                        <h2><spring:message code='txt.cercador.avansat'/></h2>
                        
                        <div class="fila">
                           
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_num_normativa"><spring:message code='txt.numNorma'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_num_normativa" placeholder="NNNNN/YYYY" name="cerca_num_normativa" type="text" />
                                </div>                          
                            </div>
                            
                             
                            <div class="element t50">
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
                                                        
                        </div>      
                    
                        <div class="fila">
                        
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_data_butlleti"><spring:message code='camp.data_butlleti'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_data_butlleti" name="cerca_data_butlleti" type="text" class="data" />
                                </div>
                            </div>
                            
                            <div class="element t50">
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
                                                                                     
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_data_aprovacio"><spring:message code='txt.dataAprovacio'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_data_aprovacio" name="cerca_data_aprovacio" type="text" class="data" />
                                </div>
                            </div>          
                                 
                                 
                            <div class="element t50">
                                <div class="etiqueta">
                                    <label for="cerca_invalids"><spring:message code='camp.norm.valid.titulo'/></label>
                                </div>
                                <div class="control">
                                	<select id="cerca_invalids" name="cerca_invalids">
                                			 <option value=""><spring:message code='txt.tots'/></option>
                                			 <option value="1"><spring:message code='camp.norm.valid.valids'/></option>
                                			 <option value="0"><spring:message code='camp.norm.valid.invalids'/></option>                                			 
                                	</select>                               
                                </div>                          
                            </div>                               
                        </div>

                        <%--
                        <div class="fila">                        
                            <div class="element t21">
                                <div class="etiqueta">
                                    <label for="cerca_llei"><spring:message code='camp.llei'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_llei" name="cerca_llei" type="text" />
                                </div>                          
                            </div>      
                        </div>
                        --%>
                        
                        <div class="botonera">
                        	<div class="boton btnGenerico">
                                <a id="btnExportar" href="javascript:void(0)" class="btn exportar"><span><span>Exportar</span></span></a>
                            </div>
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
            <!-- /cercador -->
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="id" class="ordreCamp" />
        </div>
		

        <div class="resultats TL">
            <div class="dades">
                <p class="executant"><spring:message code='normativa.carregant_llistat_normativa'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="id" class="ordreCamp" />
        </div>
        <div class="resultats T">
		
            <div id="cercadorTB">
                <div id="cercadorTB_contingut">
                    <div class="busquedaBasica">
                        <h2><spring:message code='tab.cercador'/></h2>
                    
                        <div class="fila">
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="numeroboletinTB"><spring:message code='camp.n_butlleti'/></label>
                                </div>
                                <div class="control">
                                    <input id="numeroboletinTB" placeholder="NNN/YYYY" name="numeroboletinTB" type="text" />
                                </div>                          
                            </div>
                            
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="numeroregistroTB"><spring:message code='camp.n_registre'/></label>
                                </div>
                                <div class="control">
                                    <input id="numeroregistroTB" placeholder="NNNNN" name="numeroregistroTB" type="text" maxlength="250" />
                                </div>
                            </div>

                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="fechaTB"><spring:message code='camp.dataButlleti'/></label>
                                </div>
                                <div class="control">
                                    <input id="fechaTB" name="fechaTB" type="text" class="data" />
                                </div>
                            </div>
                        	<div class="botonera">
	                            <div class="boton btnGenerico">
	                              <a id="btnLimpiarFormTB" href="javascript:void(0)" class="btn borrar"><span><span><spring:message code='boto.borrar'/></span></span></a>
	                            </div>
	                            <div class="boton btnGenerico">
	                             <a id="btnBuscarFormTB" href="javascript:;" class="btn consulta"><span><span><spring:message code='boto.cercar'/></span></span></a>
	                            </div>
	                        </div>

                        </div>
                    </div>
                                        
                </div>
				<div class="dades"></div>
				<input type="hidden" value="0" class="pagPagina" /> 
				<input type="hidden" value="DESC" class="ordreTipus" /> 
				<input type="hidden" value="id" class="ordreCamp" />
            </div> 
            
        </div>
	

 </div>

</div>
<!-- /escriptori_contingut -->
<!-- escriptori_detall -->
<div id="escriptori_detall" class="escriptori_detall"> <%--La linia de style ja ve al CSS  - display: block/none; --%>
    <form id="formGuardar" action="" method="POST" enctype="multipart/form-data">
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
							<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
							    <li class="idioma">
							        <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
							    </li>
							</c:forEach>

							<c:if test="${traductorActivo}">
								<li class="traduix btnGenerico" id="botoTraduirNormativa">
								    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
								</li>
							</c:if>
                        </ul>
                        <div class="idiomes">					
							<c:forEach items="${idiomes_aplicacio}" var="lang">
                            <div class="idioma <c:out value='${lang}'/>">
                                <div class="fila">
                                    <div class="element t75p">
                                        <div class="etiqueta"><label for="item_titol_<c:out value="${lang}"/>"><spring:message code='camp.titol_normativa'/></label></div>
                                        <div class="control">
                                            <input id="item_titol_<c:out value="${lang}"/>" name="item_titol_<c:out value="${lang}"/>" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div id="caja_item_clave_primaria" class="element t25p">
                                        <div class="etiqueta"><label for="item_clave_primaria"><spring:message code='camp.clau_primaria'/></label></div>
                                        <div class="control">
                                            <input id="item_clave_primaria" name="item_clave_primaria" type="text" class="nou" readonly="readonly"/>
                                        </div>
                                    </div>
                                </div>
                             </div>	
                             </c:forEach>	
                                <div class="fila">
                                    <div class="element t25p">
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
                                    <div class="element t25p">
                                        <div class="etiqueta"><label><spring:message code='camp.norm.valid.titulo'/></label></div>
                                        <div class="control">
                                        	<input id="normativa_datos_validos" type="text" class="nou"  readonly="readonly"></input>
                                        </div>
                                    </div>
									<div class="element t25p">
                                        <div class="etiqueta"><label for="item_num_norma"><spring:message code='txt.numNorma'/></label></div>
                                        <div class="control">
                                            <input id="item_num_norma" placeholder="NNNNN/YYYY" name="item_num_norma" type="text" class="nou" />
                                        </div>
                                    </div>
                                    <div class="element t25p">
                                        <div class="etiqueta"><label for="item_data_norma"><spring:message code='txt.dataAprovacio'/></label></div>
                                        <div class="control">
                                            <input id="item_data_norma" name="item_data_norma" type="text" class="nou" />
                                        </div>
                                    </div>
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
                <legend><spring:message code='txt.DADES_PUBLICACION'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_butlleti_id"><spring:message code='camp.butlleti'/></label></div>
                            <div class="control select">
                                <select id="item_butlleti_id" name="item_butlleti_id" class="nou" >
                                    <option value=""><spring:message code='txt.no_definit'/></option>
                                    <c:forEach items="${llistaButlletins}" var="butlleti">                                      
                                        <option value='<c:out value="${butlleti.id}" />'><c:out value="${butlleti.nom}" /></option>
                                    </c:forEach>
                                </select>       
                            </div>       
                        </div>
						<div class="element t25p">
							<div class="etiqueta"><label for="item_data_butlleti"><spring:message code='txt.dataButlleti'/></label></div>
							<div class="control">
								<input id="item_data_butlleti" name="item_data_butlleti" type="text" class="nou" />
							</div>
						</div>  
						<div class="element t25p multilang">
							<div class="etiqueta"><label for="item_numero"><spring:message code='camp.n_butlleti'/></label></div>
							<div class="control">
								<input id="item_numero" name="item_numero" type="text" class="nou" />
							</div>
						</div>  
						<!-- 
						<div class="element t25p">
							<div class="etiqueta"><label></label></div>
							<div class="btnGenerico">
									<a class="btn gestionaBOIB" href="javascript:;" style="display:none"><span><span><spring:message code='normativa.traspas.boib'/></span></span></a>
								</div> 
						</div>-->
						  
                    </div>
                    <!-- /fila -->
					
					<!-- fila -->
					<div class="fila">                        
						<div class="element t99p multilang">
							<c:forEach items="${idiomes_aplicacio}" var="lang">
								<div class="campoIdioma <c:out value="${lang}"/>">
									<div class="etiqueta"><label for="item_enllac_<c:out value="${lang}"/>"><spring:message code='camp.enllac_norma'/></label></div>
									<div class="control">
										<input id="item_enllac_<c:out value="${lang}"/>" name="item_enllac_<c:out value="${lang}"/>" type="text" class="nou" />
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
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='txt.INFORMADOR'/></legend>
                <div class="modul_continguts mostrat">
                    <div class="fila">                        
                        <div class="element t99p multilang">						
							<c:forEach items="${idiomes_aplicacio}" var="lang">
							<div class="campoIdioma <c:out value="${lang}"/>">
	                            <div class="etiqueta"><label for="item_responsable_<c:out value="${lang}"/>"><spring:message code='camp.responsable'/></label></div>
	                            <div class="control">
	                                <input id="item_responsable_<c:out value="${lang}"/>" name="item_responsable_<c:out value="${lang}"/>" type="text" class="nou" />
	                            </div>								
							</div>
							</c:forEach>							
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
						<img src="/rolsacback/quadreControl/grafica.do?tipoOperacion=1&id=1" width="728px" />
					</div>
				--%>
				</div>
			</fieldset>
		</div>
		<!-- /modul -->
        
	
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
                                	 <option value="1" selected="selected"><spring:message code='txt.validacio.vigente'/></option>
                                     <option value="2"><spring:message code='txt.validacio.derogada'/></option>     
                                     
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                    <!-- fila -->
                    <div class="fila publicacion_2campos">
                        <!-- div class="element left">
                            <div class="etiqueta">
                                <label for="item_data_publicacio">Data publicaci�</label>
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
        <div class="modul invisible" id="modulDocumentNormativa">
             <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='proc.documents.relacionats'/> </legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulDocuments -->
                        <div class="modulDocuments multilang">
                            <input id="modulo_documents_modificado" type="hidden" name="modulo_documents_modificado" value="0" />
                            <ul class="idiomes">
								<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <c:if test="${loop.first}">
                                        <li class="<c:out value="${llengua.lang}" /> seleccionat">
                                    </c:if>
                                    <c:if test="${!loop.first}">
                                        <li class="<c:out value='${llengua.lang}'/>">
                                    </c:if>
                                    <c:out value="${llengua.lang}" />
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
                                    <p class="info"><spring:message code='txt.noHiHaDocumentsRelacionats'/>.</p>
                                    <div class="listaOrdenable"></div>
                                </div>								
								</c:forEach>							                                
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='boto.afegeixDocument'/></span></span></a>
                                </div>
                                <p style="clear: both; margin-bottom: 10px;"/><!-- Separador -->
	                            <div class="btnGenerico">
	                                <a id="btnGuardar_documentos" href="javascript:;" class="btn guarda important lista-simple-documentos" style="display: none"
	                            			action="<c:url value="/normativa/guardarDocumentosRelacionados.do" />">
	                           			<span><span><spring:message code='boto.guarda'/></span></span>
	                            	</a>
	                            </div>
                            </div>                            
                        <!-- /modulDocuments -->                                 
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
                                    <a href="javascript:carregarModulArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA', undefined,'S');" class="btn consulta">
                                        <span><span><spring:message code='boto.afegeixUnitatAdminsitrativa'/></span></span>
                                    </a>
                                </div>
                                <p style="clear: both; margin-bottom: 10px;"/><!-- Separador -->
	                            <div class="btnGenerico">
	                                <a id="btnGuardar_modul_unitatsAdministratives" href="javascript:;" class="btn guarda important lista-simple-uasMateria" style="display: none" 
	                            			action="<c:url value="/normativa/guardarUnidadesRelacionadas.do" />">
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
       
        <!-- modul -->      
        <input type="hidden" id="item_tipologia" name="item_tipologia" />
        
        <div class="modul invisible" id="modul_procediments">
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
        
        <div class="modul invisible" id="modul_serveis">
            <fieldset>
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='txt.serveis_relacionats'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- modulProcediments -->
                    <div class="modulServicios">
                        <div class="seleccionats">
                            <div class="seleccionat">
                                <p class="info"><spring:message code='txt.no_serveis'/></p>
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
        <div class="modul invisible" id="modul_afectacions">
            <input type="hidden" id="afectaciones" name="afectaciones" value=""/>
            <fieldset>
                <a class="modul amagat"><spring:message code='txt.mostra'/></a>
                <legend><spring:message code='txt.afectacions_relacionades'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- modulAfectacions -->
                    <div class="modulAfectacions">
                    
                        <input name="modulo_afectaciones_modificado" value="0" type="hidden"/>
                    
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


	<div class="modulBOIB" class="escriptori_detall" style="display:none">
		<div id="cercadorTB">
			<div id="cercadorTB_contingut">
				<div class="busquedaBasica">
					<h2><spring:message code='tab.cercador'/></h2>
				
					<div class="fila">
						<div class="element t21">
							<div class="etiqueta">
								<label for="numeroboletinTB"><spring:message code='camp.n_butlleti'/></label>
							</div>
							<div class="control">
								<input id="numeroboletinTB" placeholder="NNN/YYYY" name="numeroboletinTB" type="text" />
							</div>                          
						</div>
						
						<div class="element t21">
							<div class="etiqueta">
								<label for="numeroregistroTB"><spring:message code='camp.n_registre'/></label>
							</div>
							<div class="control">
								<input id="numeroregistroTB" placeholder="NNNNN" name="numeroregistroTB" type="text" maxlength="250" />
							</div>
						</div>

						<div class="element t21">
							<div class="etiqueta">
								<label for="fechaTB"><spring:message code='txt.dataButlleti'/></label>
							</div>
							<div class="control">
								<input id="fechaTB" name="fechaTB" type="text" class="data" />
							</div>
						</div>
						<div class="botonera">
							<div class="boton btnGenerico">
							  <a id="btnLimpiarFormTB" href="javascript:void(0)" class="btn borrar"><span><span><spring:message code='boto.borrar'/></span></span></a>
							</div>
							<div class="boton btnGenerico">
							 <a id="btnBuscarFormTB" href="javascript:;" class="btn consulta"><span><span><spring:message code='boto.cercar'/></span></span></a>
							</div>
							<div class="boton btnGenerico">
								<a id="btnVolverTB" href="javascript:;" class="btn torna"><span><span><spring:message code='aplicacio.tornar'/></span></span></a>
							</div>
							
						</div>

					</div>
				</div>
									
			</div>
			<div id="resultatsTB" class="dades"></div>
		</div>
	</div>

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
        <input type="hidden" name="procId" id="procId" />
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
								<c:forEach items="${idiomasListado}" var="llengua" varStatus="loop">
                                    <li class="idioma">
                                        <a href="javascript:;" class='<c:out value="${llengua.lang}"/>'><c:out value="${llengua.nombre}" /></a>
                                    </li>
                                </c:forEach>
								
                                <c:if test="${traductorActivo}">
	                                <li class="traduix btnGenerico" id="botoTraduirDocument">
	                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
	                                </li>
                                </c:if>
                            </ul>
									
							<div class="idiomes">
								<c:forEach items="${idiomes_aplicacio}" var="lang">
								<div class='idioma <c:out value="${lang}"/>'>
									<div class="fila">
										<div class="element t99p">
											<div class="etiqueta">
												<label for='doc_titol_<c:out value="${lang}"/>'><spring:message code='camp.titol'/></label>
											</div>
											<div class="control">
												<input id='doc_titol_<c:out value="${lang}"/>' name='doc_titol_<c:out value="${lang}"/>' type='text' class='nou'/>
											</div>
										</div>
									</div>
									
									<div class="fila">
                                        <div class="element t99p">
											<div class="etiqueta">
												<label for='doc_url_<c:out value="${lang}"/>'><spring:message code='camp.url'/></label>
											</div>
											<div class="control">
												<input id='doc_url_<c:out value="${lang}"/>' name='doc_url_<c:out value="${lang}"/>' type='text' class='nou'/>
											</div>
										</div>
                                    </div>

                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for='doc_descripcio_<c:out value="${lang}"/>'><spring:message code='camp.descripcio'/></label>
                                            </div>
                                            <div class="control">
                                                <textarea id='doc_descripcio_<c:out value="${lang}"/>' name='doc_descripcio_<c:out value="${lang}"/>' cols="50" rows="2" class="nou"></textarea>
                                            </div>
                                        </div>
                                    </div>	
									
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for='doc_arxiu_<c:out value="${lang}"/>'><spring:message code='camp.arxiu'/></label></div>
                                            <div class="control archivo">   
                                                <div id='grup_arxiu_actual_doc_<c:out value="${lang}"/>' class="grup_arxiu_actual">
                                                    <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                                    <a href="#" target="_blank"></a>
                                                    <input type="checkbox" name='doc_arxiu_<c:out value="${lang}"/>_delete' id='doc_arxiu_<c:out value="${lang}"/>_delete' value="1"/>
                                                    <label for='doc_arxiu_<c:out value="${lang}"/>_delete' class="eliminar"><spring:message code='boto.elimina'/></label>
                                                </div>
                                            </div>
                                        </div>    
                                        
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for='doc_arxiu_<c:out value="${lang}"/>'><spring:message code='camp.arxiu_nou'/></label></div>
                                            <div class="control">                                           
                                                <input id='doc_arxiu_<c:out value="${lang}"/>' name='doc_arxiu_<c:out value="${lang}"/>' type="file" class="nou" />
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

<!-- escriptori_afectacions -->
<div id="escriptori_afectacions">
    
    <ul id="opcions_afectacions" class="opcions">
        <li class="opcio C actiu">Gestiona</li>                               
    </ul>    
    
    <div id="resultats_afectacions" class="escriptori_items_llistat mh150px">            
        <div class="resultats C actiu" style="display: block;">
            <div id="cercador_afectacions" class="escriptori_items_cercador"> 
                <div id="cercador_afectacions_contingut">
                    <div class="fila"> 
                        <div class="element t26">
                            <div class="etiqueta">
                                <label for="afec_cerca_normativa_titol"><spring:message code='camp.titol_afectacio'/></label>
                            </div>
                            <div class="control">
                                <input id="afec_cerca_normativa_titol" name="afec_cerca_normativa_titol" type="text" class="titol" />
                            </div>
                        </div>
                         <div class="element t12">
                             <div class="etiqueta">
                                 <label for="afec_cerca_data"><spring:message code='txt.dataNorma'/></label>
                             </div>
                             <div class="control">
                                 <input id="afec_cerca_data" name="afec_cerca_data" type="text" class="data"/>
                             </div>
                         </div>          
                         
                         <div class="element t12">
                             <div class="etiqueta">
                                 <label for="afec_cerca_data_butlleti"><spring:message code='camp.data_butlleti'/></label>
                             </div>
                             <div class="control">
                                 <input id="afec_cerca_data_butlleti" name="afec_cerca_data_butlleti" type="text" class="data"/>
                             </div>
                         </div>
                    </div>
           
                    <div class="botonera">
                        <div class="boton btnGenerico"><a id="btnLimpiarForm_afectacions" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                        <div class="boton btnGenerico"><a id="btnBuscarForm_afectacions" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
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
    
    <!-- modulLateral -->
	<div class="escriptori_detall">
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
								  <a id="btnVolver_afectacions" href="javascript:;" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
							  </li>
							  <li class="btnGuardar par">
                              <a id="btnGuardar_afectacions" href="javascript:;" class="btn guarda finalitza important lista-simple-afectaciones" 
                                  action="<c:url value="/normativa/guardarAfectaciones.do" />"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
							  </li>
						  </ul>
						</div>
						<!-- /botonera dalt -->
					</div>
				</fieldset>
			</div>
			<!-- /modul -->  
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
                </div>
            </div>
        </div>        
    </div>
    <!-- seleccionats -->
    
</div>
<!-- /escriptori_afectacions -->