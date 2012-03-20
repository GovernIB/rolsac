// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
	
	multipagina = new Multipagina();
	
	pagPagina_llistat_elm = resultats_llistat_elm.find("input.pagPagina");
	ordreTipus_llistat_elm = resultats_llistat_elm.find("input.ordreTipus");
	ordreCamp_llistat_elm = resultats_llistat_elm.find("input.ordreCamp");
	
	resultats_cercador_elm = resultats_elm.find("div.C");
	cercador_elm = $("#cercador_contingut");
	
	pagPagina_cercador_elm = resultats_cercador_elm.find("input.pagPagina");
	ordreTipus_cercador_elm = resultats_cercador_elm.find("input.ordreTipus");
	ordreCamp_cercador_elm = resultats_cercador_elm.find("input.ordreCamp");
	
	escriptori_detall_elm = $("#escriptori_detall");
	//escriptori_previsualitza_elm = $("#escriptori_previsualitza");	
	
	Error = new CError();
	Llistat = new CLlistat();	
	Detall = new CDetall();
	Auditoria = new ModulAuditories();
	
	Detall.iniciar();
    // Mostrar detall?
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	}
    Llistat.iniciar();
    // Cercador.iniciar();
	
	$("#item_ua_id").change( function() {
		if ($(this).val() != "") {
			$("#tipoNormativa").text(txtNormativaLocal);
		} else {
			$("#tipoNormativa").text(txtNormativaExterna);
		}
	});

	//$.suggeriments();
});


// idioma
var pag_idioma = $("html").attr("lang");

var Cercador = {
	iniciar: function() {		
	}
};

// minim cercador
var numCercadorMinim = 0;

// paginacio
var paginacio_marge = 4;

// llistat
var itemID_ultim = 0;

function CLlistat(){	
	this.extend = ListadoBase;		
	this.extend();

	this.iniciar = function() {
		$("#cerca_data").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_data_butlleti").datepicker({ dateFormat: 'dd/mm/yy' });				
		$("#fechaTB").datepicker({ dateFormat: 'dd/mm/yy' });
        
		Llistat.carregar({});
	}
	
	this.finCargaListado = function( opcions, data ){
		// total
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			// minim per cercador
			if (resultats_total > numCercadorMinim) {
				opcions_elm.find("li.C").animate({
					duration: "slow", width: 'show'
					}, 300);
			}
			
			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}						
			
			resultatInici = ((pag_Pag*pag_Res)+1);
			resultatFinal = ((pag_Pag*pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag*pag_Res) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "id") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "numero") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "tipo") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "unidadAdministrativa.id") ? " " + ordre_T : "";
			ordre_c5 = (ordre_C == "fecha") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "id") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "numero") {
					txt_per = txtNumero;
				} else if (ordre_C == "tipo") {
					txt_per = txtTipus;
				} else {
					txt_per = txtData;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + " " + txtDeLa + " " + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

			//De momento no habrá ordenación
			/*
			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th numero" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtNumero + "</a></div>";
			codi_cap3 = "<div class=\"th tipus" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtTipus + "</a></div>";
			codi_cap4 = "<div class=\"th data" + ordre_c4 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtData + "</a></div>";
			*/
            
			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\"><a class=\"id\" href=\"javascript:void(0)\">" + txtLlistaItem + "</a></div>";
            codi_cap2 = "<div class=\"th tipologia" + ordre_c4 + "\" role=\"columnheader\"><a class=\"unidadAdministrativa.id\" href=\"javascript:void(0)\">" + txtTipologiaNorma + "</a></div>";
			codi_cap3 = "<div class=\"th numero" + ordre_c2 + "\" role=\"columnheader\"><a class=\"numero\" href=\"javascript:void(0)\">" + txtNumBoletin + "</a></div>";
			codi_cap4 = "<div class=\"th tipus" + ordre_c3 + "\" role=\"columnheader\"><a class=\"tipo\" href=\"javascript:void(0)\">" + txtTipoBoletin + "</a></div>";			
			codi_cap5 = "<div class=\"th fecha" + ordre_c5 + "\" role=\"columnheader\"><a class=\"fecha\" href=\"javascript:void(0)\">" + txtFechaBoletin + "</a></div>";						
			
			
			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3 + codi_cap4 + codi_cap5;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				caducat_titol_class = (dada_node.caducat) ? " normativa" : " normativaCaducada";
				
				codi_taula += '<div class="tr' + parClass + '" role="row">';
				
				codi_taula += '<div class="td titol ' + caducat_titol_class + ' role="gridcell">';
				codi_taula += '<input type="hidden" value="' + dada_node.id + '" class="id" />';
				codi_taula += '<span class="id">'+ dada_node.id +'</span><a id="normativa_'+dada_node.id+'" href="javascript:void(0);" class="titol">' + dada_node.titulo + '</a>';
				codi_taula += "</div>";
				
                codi_taula += "<div class=\"td tipologia\" role=\"gridcell\">" + dada_node.tipologia + "</div>";
				codi_taula += "<div class=\"td numero\" role=\"gridcell\">" + dada_node.numero + "</div>";
				codi_taula += "<div class=\"td tipus\" role=\"gridcell\">" + dada_node.tipo + "</div>";				
				codi_taula += "<div class=\"td data\" role=\"gridcell\">" + dada_node.fecha_boletin + "</div>";
				
				codi_taula += "</div>";
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Instanciamos el navegador multipágina.					
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "Llistat.cambiaPagina"
			});					
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.ficha(this);});
                
                // Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
                jQuery("#resultats .table .th a").unbind("click").click(function(){
                    Llistat.ordena(this,opcions);
                });
							
				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					cercador_elm.find("input, select").removeAttr("disabled");
				}
				
			});
		});
	}
	
	this.carregar = function(opcions) {
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)		
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;
		
		dataVars = "";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			
			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;
			
			// cercador
			dataVars_cercador = "&id=" + $("#cerca_codi").val();
			dataVars_cercador += "&numero=" + $("#cerca_numero").val();
			dataVars_cercador += "&tipus=" + $("#cerca_tipus_normativa").val();
			dataVars_cercador += "&butlleti=" + $("#cerca_butlleti").val();
			dataVars_cercador += "&registre=" + $("#cerca_registre").val();
			//dataVars_cercador += "&llei=" + $("#cerca_llei").val();
			dataVars_cercador += "&data=" + $("#cerca_data").val();			
			dataVars_cercador += "&text=" + $("#cerca_text").val();
			dataVars_cercador += "&data_butlleti=" + $("#cerca_data_butlleti").val();			
			dataVars_cercador += "&validacio=" + $("#cerca_validacio").val();			
			dataVars_cercador += "&totesUnitats=" + $("#cerca_totes_unitats").is(':checked');
			dataVars_cercador += "&uaFilles=" + $("#cerca_uaFilles").is(':checked');
			dataVars_cercador += "&cercaExternes=" + $("#cerca_externes").is(':checked');			
			dataVars_cercador += "&idUA=" + $("#cerca_ua_id").val();
		
		} else {
			
			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;
			
			// cercador			
			dataVars_cercador = "&idUA=" + $("#cerca_ua_id").val();
			
		}
			
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();
			
		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
										
		// ajax		
		if ( ( modoListado && !Llistat.cacheDatosListado ) || modoBuscador ){
			$.ajax({
				type: "POST",
				url: pagLlistat,
				data: dataVars,
				dataType: "json",
				error: function() {
					
					if (!a_enllas) {
						// missatge
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						// error
						Error.llansar();
					}
					
				},
				success: function(data) {				
					Llistat.finCargaListado(opcions,data);
					
					if( modoListado ){											
						Llistat.cacheDatosListado = data;
					}
				}
			});
		}else{
			Llistat.finCargaListado(opcions,Llistat.cacheDatosListado);
		}
	
	}
	
	this.busca = function(){

		cercador_elm.find("input, select").attr("disabled", "disabled");
		
		resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");
		
		// animacio
		resultats_dades_elm.fadeOut(300, function() {
			// pintem
			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				Llistat.carregar({cercador: "si"});
				
			});
		});
	}
	
	// FUNCIONALITAT TRASPAS BOIB

	var that = this;
	jQuery("#tabTraspasBoib").bind("click",function(){that.tabTraspasBoib();});
	jQuery("#btnBuscarFormTB").bind("click",function(){that.buscaTB();});
	jQuery("#btnLimpiarFormTB").bind("click",function(){that.limpiaTB();});
	
	var cercador_traspas_elm = jQuery("#cercadorTB");
	
	// Cambia a la pestaña del buscador.
	this.tabTraspasBoib = function(){
		jQuery("#opcions .actiu").removeClass("actiu");
		jQuery("#tabTraspasBoib").parent().addClass("actiu");
		
		opcio_unitat = "T";
		
		// resultats
		resultats_elm.find("div.actiu").slideUp(300,function() {
			jQuery(this).removeClass("actiu");
			resultats_elm.find("div."+opcio_unitat).slideDown(300,function() {
				jQuery(this).addClass("actiu");
				
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");								
			});
		});
	}

	// Limpia el formulario de búsqueda.
	this.limpiaTB = function(){
        jQuery('#cercadorTB_contingut :input').each(limpiarCampo);
	}

	this.buscaTB = function(){

		multipagina.setPaginaActual(0);
		cercador_traspas_elm.find("input, select").attr("disabled", "disabled");
		
		resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");
		
		// animacio
		resultats_dades_elm.fadeOut(300, function() {
			// pintem
			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				Llistat.carregarTB({});
				
			});
		});
	}
	
	this.carregarTB = function(opcions) {

		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)		
		
		dataVars = "";
		
		pagPagina_elm = pagPagina_cercador_elm;
		ordreTipus_elm = ordreTipus_cercador_elm;
		ordreCamp_elm = ordreCamp_cercador_elm;
		
		// cercador
		dataVars_cercador = "&numeroboletin=" + $("#numeroboletinTB").val();
		dataVars_cercador += "&numeroregistro=" + $("#numeroregistroTB").val();
		dataVars_cercador += "&fecha=" + $("#fechaTB").val();			
			
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();
			
		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
										
		// ajax		
		$.ajax({
			type: "POST",
			url: pagCercaBoib,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					// error
					Error.llansar();
				}
				
			},
			success: function(data) {				
				Llistat.finCargaListadoTB(opcions,data);
			}
		});
	
	}

	// Cambia de página.
	this.cambiaPaginaTB = function( pag ){
		multipagina.setPaginaActual(pag-1);
		pag_Pag = pag;
		this.anar(pag, that.carregarTB);
	}
		
	
	
	this.finCargaListadoTB = function( opcions, data ){
		// total
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			// minim per cercador
			if (resultats_total > numCercadorMinim) {
				opcions_elm.find("li.C").animate({
					duration: "slow", width: 'show'
					}, 300);
			}
			
			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}						
			
			resultatInici = ((pag_Pag*pag_Res)+1);
			resultatFinal = ((pag_Pag*pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag*pag_Res) + pag_Res;
			
			//TODO: ordenació
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "titol") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "registre") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "butlleti") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "fecha") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "id") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "numero") {
					txt_per = txtNumero;
				} else if (ordre_C == "tipo") {
					txt_per = txtTipus;
				} else {
					txt_per = txtData;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + " " + txtDeLa + " " + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

			/* per ara no hi ha ordenació
			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\"><a class=\"id\" href=\"javascript:void(0)\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th registre" + ordre_c2 + "\" role=\"columnheader\"><a class=\"tipo\" href=\"javascript:void(0)\">" + txtNumRegistro + "</a></div>";			
			codi_cap3 = "<div class=\"th numero" + ordre_c3 + "\" role=\"columnheader\"><a class=\"numero\" href=\"javascript:void(0)\">" + txtNumBoletin + "</a></div>";
			codi_cap4 = "<div class=\"th fecha" + ordre_c4 + "\" role=\"columnheader\"><a class=\"fecha\" href=\"javascript:void(0)\">" + txtFechaBoletin + "</a></div>";
			*/						
			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th registre" + ordre_c2 + "\" role=\"columnheader\">" + txtNumRegistro + "</div>";			
			codi_cap3 = "<div class=\"th numero" + ordre_c3 + "\" role=\"columnheader\">" + txtNumBoletin + "</div>";
			codi_cap4 = "<div class=\"th fecha" + ordre_c4 + "\" role=\"columnheader\">" + txtFechaBoletin + "</div>";
			
			
			// codi taula
			codi_taula = "<div class=\"table llistat llistattrasllat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3 + codi_cap4;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				caducat_titol_class = (dada_node.caducat) ? " normativa" : " normativaCaducada";
				
				codi_taula += '<div class="tr' + parClass + '" role="row">';
				
				codi_taula += '<div class="td titol ' + caducat_titol_class + ' role="gridcell">';
				codi_taula += '<input type="hidden" value="' + dada_node.numero + 'X' + dada_node.registro + '" class="id" />';
				codi_taula += '<a id="normativa_'+dada_node.numero + 'X' + dada_node.registro+'" href="javascript:void(0);" class="titol">' + dada_node.titulo + '</a>';
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td registre\" role=\"gridcell\">" + dada_node.registro + "</div>";
				codi_taula += "<div class=\"td numero\" role=\"gridcell\">" + dada_node.numero + "</div>";
				codi_taula += "<div class=\"td data\" role=\"gridcell\">" + dada_node.fecha_boletin + "</div>";
				
				codi_taula += "</div>";
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Instanciamos el navegador multipágina.					
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "Llistat.cambiaPaginaTB"
			});					
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.fichaTB(this);});
                /* TODO por ahora no hay ordenación
                // Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
                jQuery("#resultats .table .th a").unbind("click").click(function(){
                    Llistat.ordena(this,opcions);
                });
                */

				// cercador
				cercador_traspas_elm.find("input, select").removeAttr("disabled");
				
			});
		});
	}
	
	/**
	 * Carga la ficha de un item del listado.
	 * @param link Objeto <A> sobre el que se realizó la acción.
	 */
	this.fichaTB = function( link ){
		// Obtenemos el id del item a partir del id del enlace.
		itemID = jQuery(link).attr("id").split("_")[1];
		Detall.carregarTB(itemID);
		
		//itemID_ultim = itemID;
		//this.itemID = itemID;
	}
	
	
};

// items array
var Items_arr = new Array();

// detall
function CDetall(){
	this.extend = DetallBase;
	this.extend();
	
	var that = this;
	
	this.tipusAuditoria = 'normativa';
	
	this.iniciar = function() {			
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;
		
		jQuery("#item_data_norma, #item_data_norma_ca, #item_data_norma_es, #item_data_norma_en, #item_data_norma_de, #item_data_norma_fr, #item_data_butlleti").datepicker({ dateFormat: 'dd/mm/yy' });
				
		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			$('div.idioma:gt(0)').hide();			
			
			ul_idiomes_elm = escriptori_detall_elm.find("ul.idiomes:first");

			a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			a_primer_elm_class = a_primer_elm.attr("class");
			a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			div_idiomes_elm = escriptori_detall_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			
			ul_idiomes_elm.bind("click",function(e){Detall.idioma(e);});			

            // Solo mostramos los idiomas activos para los campos multi-idioma.
            escriptori_detall_elm.find(".element.multilang .campoIdioma").hide();            
            escriptori_detall_elm.find(".element.multilang .campoIdioma:first-child").show().addClass("seleccionat");            
			
		}
		
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").change(function(){
            jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").val( jQuery(this).val() );
        });        
        jQuery("#item_tipus,#item_tipus_es,#item_tipus_en,#item_tipus_de,#item_tipus_fr").change(function(){
            jQuery("#item_tipus,#item_tipus_es,#item_tipus_en,#item_tipus_de,#item_tipus_fr").val( jQuery(this).val() );
        });        
        jQuery("#item_num_norma,#item_num_norma_es,#item_num_norma_en,#item_num_norma_de,#item_num_norma_fr").change(function(){
            jQuery("#item_num_norma,#item_num_norma_es,#item_num_norma_en,#item_num_norma_de,#item_num_norma_fr").val( jQuery(this).val() );
        });        
        jQuery("#item_data_norma,#item_data_norma_es,#item_data_norma_en,#item_data_norma_de,#item_data_norma_fr").change(function(){
            jQuery("#item_data_norma,#item_data_norma_es,#item_data_norma_en,#item_data_norma_de,#item_data_norma_fr").val( jQuery(this).val() );
        });
        
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
		
		// altres moduls
		modulAfectacions_pare_elm = $("#modulLateral div.modulAfectacions").parents("div.modul:first");
		modulProcediments_pare_elm = $("#modulLateral div.modulProcediments").parents("div.modul:first");
        
        // esborrar arxiu (reemplasar <input type="file"> per un de nou perque a IE nomes es de lectura)
        jQuery('a.esborraArxiu').click().unbind("click").click(function(event){
            event.preventDefault();
            var input = $(this).parent().prev().find('input[type=file]')
            var inputId = input.attr('id');
            input.replaceWith('<input id="' + inputId + '" name="' + inputId + '" type="file" class="nou" />');
        });
	},
	

	//Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
		
	    // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }
		
		//Preparar los datos de afectaciones relacionadas.
		//Crear un JSON con la lista de afectaciones.
		/*
		var listaAfectaciones = "{\"listaAfectaciones\" : [";
		var sep = "";
		$("div.modulAfectacions").find("li").each(function() {
			var li_elm = $(this);
			var idNormaAfectada = li_elm.find("input.norma").val();
			var idTipoAfectacion = li_elm.find("input.afectacio").val();
					
			listaAfectaciones += sep + "{ \"afectacioId\" : " + idTipoAfectacion + ", \"normaId\" : " + idNormaAfectada+ ", \"normaNom\" : \"\", \"afectacioNom\" : \"\" } ";
			sep=",";
		});
		listaAfectaciones += "]}";
		
		$("#afectaciones").val(listaAfectaciones);
		*/
		
		$("#afectaciones").val( ModulAfectacions.jsonAfectacions() );
		

		//Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
		$("#formGuardar").ajaxSubmit({			
			url: pagGuardar,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
							
				Llistat.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
//					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
					Detall.recarregar(data.id);
				}					
			}
								
		});
		return false;		
		
	},
	
	this.tipologia = function(e) {
		
		if ($(this).val() == "B") {
			modulAfectacions_pare_elm.fadeOut(300);
			modulProcediments_pare_elm.fadeOut(300);
		} else {
			modulAfectacions_pare_elm.fadeIn(300);
			modulProcediments_pare_elm.fadeIn(300);
		}
		
	}
	
	this.dataPublicacio = function(e) {		
//		if ($(this).val() == "") {
//			$(this).val(txtImmediat);
//		}		
	}
	
	this.nou = function() {
		
		//Anular id
		$("#item_id").val("");
		
		//Ocultar botones
		$("#modulLateral li.btnEliminar").hide();
		
		//Ocultar campos clave primaria		
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		
		//Mostrar botones cambio de UA / borrar UA.
		$("#botoneraCambioUA").show();
		$("#botonBorrarUA a").show();		

		//Borrar valores de los campos
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou, div.fila select.nou, div.modulDocuments input.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		//Establecer UA por defecto
		$("#item_ua_id").val(idUaActual);
		$("#item_ua_nom").val(nomUaActual);
		
		//Poner tipo Normativa local por defecto
		$("#tipoNormativa").text(txtNormativaLocal);
		
		//Establecer Validación por defecto si el usuario es operador
		if ( $("#rolusuario").val() == "RSC_OPER" ) {
			$("#item_validacio").val(2);
		}
		
		//Resetear upload de archivos			
		for (var i in idiomas) {
			$("#grup_arxiu_actual_" + idiomas[i] + " span").show();
			$("#grup_arxiu_actual_" + idiomas[i] + " input").hide();
			$("#grup_arxiu_actual_" + idiomas[i] + " label.eliminar").hide();
			$("#grup_arxiu_actual_" + idiomas[i] + " a").hide();	
		}
		
		//Ocultar paneles
		$("#modul_procediments, #modul_afectacions").hide();
				
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);		

		
		pro_seleccionats_elm = escriptori_detall_elm.find("div.modulAfectacions div.seleccionats");
		pro_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaAfectacions + ".");
		
		afecta_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.seleccionats");
		afecta_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaProcediments + ".");
				
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
				
		$("#modulLateral p.baix:first").removeClass("iPublicat");
		

		modulAfectacions_pare_elm.show();
		modulProcediments_pare_elm.show();
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();
	}
	
	this.pintar = function(dades) {	
		
		$("#modul_procediments, #modul_afectacions").show();
		
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").show();
		
		$("#modulLateral li.btnEliminar").show();
		$("#modulLateral li.btnEliminar").css("visibility", "visible");
		
		dada_node = dades;
		
		$("#modulLateral p.baix:first").addClass("iPublicat");
							
		$("#item_id").val(dada_node.id);
		$("#item_tipologia").val(dada_node.tipologia);
		$("#item_validacio").val(dada_node.validacio);
		
				
		for (var i in idiomas) {		
			var idioma = idiomas[i];
			
			$("#item_titol_" + idioma).val(nn(dada_node["idioma_" + idioma + "_titol"]));
			$("#item_enllas_" + idioma).val(nn(dada_node["idioma_" + idioma + "_enllac"]));
			$("#item_apartat_" + idioma).val(nn(dada_node["idioma_" + idioma + "_apartat"]));
			$("#item_pagina_inicial_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagini"]));
			$("#item_pagina_final_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagfin"]));
			$("#item_responsable_" + idioma).val(nn(dada_node["idioma_" + idioma + "_responsable"]));
			//Campos comentados en Back2
			//$("#item_des_curta_" + idioma).val(nn(dada_node["idioma_" + idioma + "_observacions"]));
			
			$("#item_arxiu_" + idioma).val("");
			$("#grup_arxiu_actual_" + idioma + " input").removeAttr("checked");
			if (dada_node["idioma_" + idioma + "_enllas_arxiu"]) {
				$("#grup_arxiu_actual_" + idioma + " a").show();					
				$("#grup_arxiu_actual_" + idioma + " a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_arxiu_actual_" + idioma + " a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_arxiu_actual_" + idioma + " span").hide();
				$("#grup_arxiu_actual_" + idioma + " input").show();
				$("#grup_arxiu_actual_" + idioma + " label.eliminar").show();
							
			} else {
				$("#grup_arxiu_actual_" + idioma + " span").show();
				$("#grup_arxiu_actual_" + idioma + " input").hide();
				$("#grup_arxiu_actual_" + idioma + " label.eliminar").hide();
				$("#grup_arxiu_actual_" + idioma + " a").hide();			
			}						
		}
		
		$("#item_clave_primaria").val(nn(dada_node.id));
        $("#item_clave_primaria").change();
		
		$("#item_numero").val(nn(dada_node.numero));
		$("#item_butlleti_id").val(nn(dada_node.butlleti_id));
		$("#item_butlleti").val(nn(dada_node.butlleti));
		$("#item_registre").val(nn(dada_node.registre));
		$("#item_llei").val(nn(dada_node.llei));
		
		$("#item_data_butlleti").val(nn(dada_node.data_butlleti));
		
		$("#item_data_norma").val(nn(dada_node.data_norma));
		$("#item_data_norma").change();
		
		$("#item_tipus").val(nn(dada_node.tipus));
        $("#item_tipus").change();
		
        $("#item_num_norma").val(nn(dada_node.num_norma));
        $("#item_num_norma").change();
        
		$("#item_ua_id").val(nn(dada_node.idUA));
		$("#item_ua_nom").val(nn(dada_node.nomUA));
		
		if (dada_node.tipus == "B") {
			
			modulAfectacions_pare_elm.hide();
			modulProcediments_pare_elm.hide();
		
		} else {
			
			modulAfectacions_pare_elm.show();
			modulProcediments_pare_elm.show();
			
			// afectacions
			ModulAfectacions.inicializarAfectacions(dada_node.afectacions);
			
			// procediments
			pro_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.listaOrdenable");
			pro_nodes = dada_node.procediments;
			pro_nodes_size = pro_nodes.length;
			
			if (pro_nodes_size == 0) {				
				escriptori_detall_elm.find("div.modulProcediments p.info").text(txtNoHiHaProcediments + ".");
				pro_seleccionats_elm.html("");				
			} else {
				codi_pro = "<ul>";
				$(pro_nodes).each(function() {
					pro_node = this;
					codi_pro += "<li><input type=\"hidden\" value=\"" + pro_node.id + "\" />" + pro_node.nombre + "</li>";
				});
				codi_pro += "</ul>";
				txt_procediments = (pro_nodes_size == 1) ? txtProcediment : txtProcediments;
				//pro_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + pro_nodes_size + " " + txt_procediments + "</strong>.").after(codi_pro);
				escriptori_detall_elm.find("div.modulProcediments p.info").html(txtHiHa + " <strong>" + pro_nodes_size + " " + txt_procediments + "</strong>.");
				pro_seleccionats_elm.html(codi_pro);
				if (pro_nodes_size > 1) {
					//pro_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(../img/cursor/grabbing.cur), move' }).find("li").css("cursor","url(../img/cursor/grab.cur), move");
				}
			}
		
		}

		
		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				
				// array
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				
				escriptori_detall_elm.fadeIn(300);
											
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);
			});
		
		}
		
		//Mostrar / ocultar campo de responsable y botonera cambio UA en normativa local/externa
		if ("E" == $("#item_tipologia").val()) {
			$("#tipoNormativa").text(txtNormativaExterna);
			$("#botoneraCambioUA").hide();						
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").show();
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").parent().parent().show();
		} else {
			$("#tipoNormativa").text(txtNormativaLocal);
			$("#botoneraCambioUA").show();
			$("#botonBorrarUA a").hide();
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").hide();
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").parent().parent().hide();
		}		
		
	}
	
	this.elimina = function() {
												
		dataVars = "accio=eliminar&id=" + Llistat.itemID;
				
		// ajax
		$.ajax({
			type: "POST",
			url: pagEliminar,
			data: dataVars,
			dataType: "json",
			beforeSubmit : function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},			
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {	
				Llistat.anulaCache();				
				if (data.id > -1) {		
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
					Detall.array({id: data.id, accio: "elimina"});
					Detall.recarregar();
				} else {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				}
			}
		});			
	}
	
	//Métodos para traspaso BOIB

	this.carregarTB = function(boibID){
		//Cargamos los datos de un edicto del boib en una ficha vacía de normativa nueva

		escriptori_contingut_elm.fadeOut(300, function() {

			codi_carregant = "<div id=\"carregantDetall\"><p class=\"executant\">" + txtCarregantDetall + "</p></div>";
			escriptori_elm.append(codi_carregant).slideDown(300, function() {

				dataVars = "accio=carregar" + "&id=" + boibID;

				// ajax
				$.ajax({
					type: "POST",
					url: pagDetallBoib,
					data: dataVars,
					dataType: "json",
					error: function() {
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					},
					success: function(data) {
						if (typeof data.error != 'undefined') {
							$("#carregantDetall").fadeOut(300, function() {
								$(this).remove();
								escriptori_contingut_elm.fadeIn(300);
							});
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.error + "</p>"});
						} else {
							Detall.pintarTB(data);
						}
					}
				});
			});
		});

		this.actualizaEventos();
	}
	
	this.pintarTB = function(dades) {	
		
		Detall.nou();		

		//Rellena el formulario con los datos de un elemento BOIB
		dada_node = dades;
		
		$("#item_validacio").val(dada_node.validacio);

		for (var i in idiomas) {		
			var idioma = idiomas[i];
			
			$("#item_titol_" + idioma).val(nn(dada_node["idioma_" + idioma + "_titol"]));
			$("#item_enllas_" + idioma).val(nn(dada_node["idioma_" + idioma + "_enllac"]));
			$("#item_apartat_" + idioma).val(nn(dada_node["idioma_" + idioma + "_apartat"]));
			$("#item_pagina_inicial_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagini"]));
			$("#item_pagina_final_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagfin"]));
			$("#item_responsable_" + idioma).val(nn(dada_node["idioma_" + idioma + "_responsable"]));
			//Campos comentados en Back2
			////$("#item_des_curta_" + idioma).val(nn(dada_node["idioma_" + idioma + "_observacions"]));
			
			$("#grup_arxiu_actual_" + idioma + " span").show();
			$("#grup_arxiu_actual_" + idioma + " input").hide();
			$("#grup_arxiu_actual_" + idioma + " label.eliminar").hide();
			$("#grup_arxiu_actual_" + idioma + " a").hide();			
						
		}
		
		$("#item_numero").val(nn(dada_node.numero));
		$("#item_butlleti_id").val(nn(dada_node.butlleti_id));
		$("#item_butlleti").val(nn(dada_node.butlleti));
		$("#item_registre").val(nn(dada_node.registre));
		$("#item_data_butlleti").val(nn(dada_node.data_butlleti));
		
		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				
				// array
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				
				escriptori_detall_elm.fadeIn(300);
											
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);
			});
		
		}
		
	}
	
	
	
};


