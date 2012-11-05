<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>
<link href='<c:url value="/css/normativa.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_afectacions.css"/>' rel="stylesheet" type="text/css" media="screen" />
<link href='<c:url value="/css/modul_procediments.css"/>' rel="stylesheet" type="text/css" media="screen" />
<%-- obsoleto? borrar
<link href='<c:url value="/css/modul_traspas.css"/>' rel="stylesheet" type="text/css" media="screen" />
 --%>
<script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/normativa.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_afectacions.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_auditories.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>

    <script type="text/javascript">
    <!--
        // pagines  
        var pagLlistat = '<c:url value="/normativa/llistat.do" />';
        var pagDetall = '<c:url value="/normativa/pagDetall.do" />';
        var pagGuardar = '<c:url value="/normativa/guardar.do" />';
        var pagEliminar = '<c:url value="/normativa/eliminar.do" />';
        var pagCercaBoib = '<c:url value="/normativa/cercaBoib.do" />';
        var pagDetallBoib = '<c:url value="/normativa/detallBoib.do" />';
        var pagAuditories = '<c:url value="/auditories/llistat.do" />';
        var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
        var pagArrel = '<c:url value="/" />';        
      	var pagNormativa = '<c:url value="/normativa/cercarNormatives.do" />';             
        
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
        // taula

        var txtNumero = "<spring:message code='camp.numero'/>";
        var txtNumBoletin = "<spring:message code='camp.nombreButlleti'/>";
        var txtTipus = "<spring:message code='camp.tipus'/>";
        var txtTipoBoletin = "<spring:message code='camp.tipusButlleti'/>";
        var txtBoletin = "<spring:message code='txt.butlleti'/>";
        var txtTipologia = "<spring:message code='camp.tipologia'/>";
        var txtTipologiaNorma = "<spring:message code='camp.tipologiaNorma'/>";
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
        
        var txtNormativaLocal = "<spring:message code='txt.normativa_local'/>";
        var txtNormativaExterna = "<spring:message code='txt.normativa_externa'/>";
        
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
                "etiquetaValor": "item_titol_ca",
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
                        "obligatori": "<spring:message code='normativa.formulari.titol_ca.obligatori'/>",
                        "tipus": "<spring:message code='normativa.formulari.titol_ca.no_nomes_numeros'/>"
                    }
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_titol_es",
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
                "etiquetaValor": "item_titol_en",
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
                "etiquetaValor": "item_titol_de",
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
                "etiquetaValor": "item_titol_fr",
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
                "etiquetaValor": "item_enllas_ca",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_enllas_es",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_enllas_en",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_enllas_de",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_enllas_fr",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_apartat_ca",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_apartat_es",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_apartat_en",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_apartat_de",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_apartat_fr",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable_ca",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable_es",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable_en",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable_de",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable_fr",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 480,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
            },
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_pagina_inicial_ca",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
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
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.pagina_final_fr.tipus'/>"
                    }
            },    

            // No traduibles
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_numero",
                "obligatori": "si",
                "tipus": "numeric",
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error":
                    {
                        "obligatori": "<spring:message code='normativa.formulari.numero.obligatori'/>",
                        "tipus": "<spring:message code='normativa.formulari.numero.tipus'/>"
                    }
            },       
            /*
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_data_norma",
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
            },    
            */
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_registre",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                {
                    "maxim": 18,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error":
                    {
                        "tipus": "<spring:message code='normativa.formulari.registre.tipus'/>"
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
                },
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
            }
            
        ];
        
        <c:if test="${traspasboib == 'Y'}">
        
	        // Validaci�n del formulario de b�squeda de Traspaso EBOIB
	        var FormularioBusquedaTB = [{
	            "modo": "individual",
	            "etiqueta": "id",
	            "etiquetaValor": "numeroboletinTB",
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
	            },{
	            "modo": "individual",
	            "etiqueta": "id",
	            "etiquetaValor": "fechaTB",
	            "obligatori": "no",
	            "tipus": "data",
	            "caracters":
	                {
	                    "maxim": 9,
	                    "mostrar": "no",
	                    "abreviat": "no"
	                },
	            "error":
	                {
	                    "obligatori": "",
	                    "tipus": "<spring:message code='normativa.formulari.traspas_eboib.campo_data_formato'/>"
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

        <c:if test="${idUA > 0}">
            <li class="opcions nuevo">
                <a id="btnNuevaFicha" href="javascript:;" class="btn nou"><span><span><spring:message code='normativa.crea_nova_normativa'/></span></span></a>
            </li>
	        <c:if test="${traspasboib == 'Y'}">
	            <li class="opcio nuevo"><!--  que es L C ... -->
	                <a id="tabTraspasBoib" href="javascript:;"><spring:message code='normativa.traspas.boib'/></a>
	            </li>
	        </c:if>
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
                            <div class="element checkbox">                           
                                <input type="checkbox" id="cerca_externes" name="cerca_externes" />
                                <label for="cerca_externes"><spring:message code='camp.cerca_externes'/></label>
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
                                            <option value="1"><spring:message code='txt.validacio.publica'/></option>
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
                            <%--
                            <div class="element t25">
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
                             --%>   

                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_numero"><spring:message code='camp.numero'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_numero" name="cerca_numero" type="text" />
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
                                    <label for="cerca_data_butlleti"><spring:message code='camp.data_butlleti'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_data_butlleti" name="cerca_data_butlleti" type="text" class="data" />
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
                            <div class="element t50">
                                <div class="etiqueta">
                                    <label for="cerca_registre"><spring:message code='camp.registre'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_registre" name="cerca_registre" type="text" />
                                </div>                          
                            </div>  
                                                                                    
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="cerca_data"><spring:message code='txt.dataNorma'/></label>
                                </div>
                                <div class="control">
                                    <input id="cerca_data" name="cerca_data" type="text" class="data" />
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

<c:if test="${traspasboib == 'Y'}">
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
                                    <input id="numeroboletinTB" name="numeroboletinTB" type="text" />
                                </div>                          
                            </div>
                            
                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="numeroregistroTB"><spring:message code='camp.n_registre'/></label>
                                </div>
                                <div class="control">
                                    <input id="numeroregistroTB" name="numeroregistroTB" type="text" maxlength="250" />
                                </div>
                            </div>

                            <div class="element t25">
                                <div class="etiqueta">
                                    <label for="fechaTB"><spring:message code='camp.n_data'/></label>
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
            </div>
            <!-- /cercador -->
            <div class="dades"></div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="id" class="ordreCamp" />
        </div>
	<!-- /traspas_boib -->
</c:if>

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
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
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
                                <div class="fila">
                                    <div class="element t75p">
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
                                        <div class="etiqueta"><label for="item_data_norma"><spring:message code='txt.dataNorma'/></label></div>
                                        <div class="control">
                                            <input id="item_data_norma" name="item_data_norma" type="text" class="nou" />
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
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='txt.DADES_BUTLLETI'/></legend>
                <div class="modul_continguts mostrat">
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p">
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
                            <div class="etiqueta"><label for="item_data_butlleti"><spring:message code='camp.data'/></label></div>
                            <div class="control">
                                <input id="item_data_butlleti" name="item_data_butlleti" type="text" class="nou" />
                            </div>
                        </div>  
                        <div class="element t25p">
                            <div class="etiqueta"><label for="item_numero"><spring:message code='camp.numero'/></label></div>
                            <div class="control">
                                <input id="item_numero" name="item_numero" type="text" class="nou" />
                            </div>
                        </div>                                                
                    </div>
                    <!-- /fila -->
					
                    <!-- fila -->
                    <div class="fila">                        
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_registre"><spring:message code='camp.registre'/></label></div>
                            <div class="control">
                                <input id="item_registre" name="item_registre" type="text" class="nou" />
                            </div>
                        </div>
						
                        <div class="element t50p multilang">
						
							<c:forEach items="${idiomes_aplicacio}" var="lang">
                          	<div class="campoIdioma <c:out value="${lang}"/>">
	                            <div class="etiqueta"><label for="item_apartat_<c:out value="${lang}"/>"><spring:message code='camp.apartat'/></label></div>
	                            <div class="control">
	                                <input id="item_apartat_<c:out value="${lang}"/>" name="item_apartat_<c:out value="${lang}"/>" type="text" class="nou" />
	                            </div>
                            </div>							
							</c:forEach>
						
                        </div>
                    </div>
                    <!-- /fila -->
                    
                    <!-- fila -->
                    <div class="fila">						
                        <div class="element t50p multilang">						
							<c:forEach items="${idiomes_aplicacio}" var="lang">
							<div class="campoIdioma <c:out value="${lang}"/>">
	                            <div class="etiqueta"><label for="item_pagina_inicial_<c:out value="${lang}"/>"><spring:message code='camp.pagina_inicial'/></label></div>
	                            <div class="control">
	                                <input id="item_pagina_inicial_<c:out value="${lang}"/>" name="item_pagina_inicial_<c:out value="${lang}"/>" type="text" class="nou" />
	                            </div>
                            </div>							
							</c:forEach>
                        </div>						
                    </div>
                    <!-- /fila -->
                    
                    <%--
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p">                          
                            <div class="etiqueta"><label for="item_ua_nom"><spring:message code='camp.unitat_administrativa'/></label></div>
                            <div class="control">
                                <input id="item_ua_id" name="item_ua_id" type="hidden" />
                                <input id="item_ua_nom" name="item_ua_nom" type="text" readonly="readonly"/>
                            </div>
                        </div>
                        
                        <!--
                        <div class="element t50p">
                            <div class="etiqueta"><label for="item_llei"><spring:message code='camp.llei'/></label></div>
                            <div class="control">
                                <input id="item_llei" name="item_llei" type="text" class="nou" />
                            </div>
                        </div>                  
                        -->
                        
                    </div>
                    <!-- /fila -->
                    
                    <!-- Botonera -->
                    <div id="botoneraCambioUA">
                        <div class="botonera" style="margin-top: 0px; float:left;">
                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_ua_id', 'item_ua_nom');" class="btn consulta">
                                <!-- <a href="javascript:ArbreUA('item_ua_nom', 'item_ua_id');" class="btn consulta"> -->
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
                    --%>                                     
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
        <div class="modul" id="modulDocumentNormativa">
            <fieldset>
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='document.documentsRelacionats'/></legend>                               
                <div class="modul_continguts mostrat">                                  
                    <!-- modulDocuments -->
                    <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                    <div class="modulDocuments multilang">                            
                        <ul class="idiomes">
						
							<li class="introIdiomas"><spring:message code='txt.idioma.idioma'/>:</li>
							<c:forEach items="${idiomes_aplicacio}" var="lang" varStatus="loop">
							<c:if test="${loop.first}">
							<li class="<c:out value="${lang}"/> seleccionat">
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

								<c:when test="${lang eq 'de'}">
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
							<div class=" <c:out value="${lang}"/> cajaIdioma">
							</c:if>	
							
                                <div class="element enlace">
                                    <div class="etiqueta"><label for="item_enllas_<c:out value="${lang}"/>"><spring:message code='camp.enllas'/></label></div>
                                    <div class="control">
                                        <input id="item_enllas_<c:out value="${lang}"/>" name="item_enllas_<c:out value="${lang}"/>" type="text" class="nou" />
                                    </div>
                                </div>                                                                    
                                <div class="element">
                                    <div class="etiqueta"><label for="item_arxiu_<c:out value="${lang}"/>"><spring:message code='camp.arxiu'/></label></div>
                                    <div class="control archivo">   
                                        <div id="grup_arxiu_actual_<c:out value="${lang}"/>" class="grup_arxiu_actual">
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_arxiu_<c:out value="${lang}"/>_delete" id="item_arxiu_<c:out value="${lang}"/>_delete" value="1"/>
                                            <label for="item_arxiu_<c:out value="${lang}"/>_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>
                                </div>    
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="item_arxiu_<c:out value="${lang}"/>"><spring:message code='camp.arxiu_nou'/></label></div>
                                    <div class="control">                                           
                                        <input id="item_arxiu_<c:out value="${lang}"/>" name="item_arxiu_<c:out value="${lang}"/>" type="file" class="nou" />
                                    </div>
                                    <div class="etiqueta"><a href="#" class="esborraArxiu"><spring:message code='txt.esborra_arxiu'/></a></div>
                                </div>                                                                                                                                                      														
							</div>							
							</c:forEach>                                                        
                        </div>                                  
                    </div>
                    <!-- /modulDocuments -->                                 
                </div>    
            </fieldset>
        </div>
        <!-- /modul -->
        
        <!-- modul -->
        <div class="modul" id="modulRelacioOrganica">
            <fieldset>
                <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                <legend><spring:message code='txt.RELACIO_ORGANICA'/></legend>
                <div class="modul_continguts mostrat">
                    
                    <!-- fila -->
                    <div class="fila">
                        <div class="element t50p">                          
                            <div class="etiqueta"><label for="item_ua_nom"><spring:message code='camp.unitat_administrativa'/></label></div>
                            <div class="control">
                                <input id="item_ua_id" name="item_ua_id" type="hidden" />
                                <input id="item_ua_nom" name="item_ua_nom" type="text" readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                    <!-- /fila -->
                    
                    <!-- Botonera -->
                    <div id="botoneraCambioUA">
                        <div class="botonera">
                            <div class="boton btnGenerico">
                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_ua_id', 'item_ua_nom');" class="btn consulta">                                
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
                        <div class="boton btnGenerico"><a id="btnVolverDetalle_afectacions" class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
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