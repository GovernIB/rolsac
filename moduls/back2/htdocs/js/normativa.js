//TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	//#421 para comprobar que no tiene la longitud del nombre demasiado largo.
	jQuery("input:file").change(function(e) {
		  if (e.target.files.length > 0) {
		  var file = e.target.files[0];
			if (file != null && file.name != null) {
				if (file.name.length >= 128) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + txtErrorTamanyoFitxer + "</p>"});
				}
			}
		  }
	});
	
	
	// Listener para guardado de módulo vía AJAX.
	jQuery(".lista-simple-afectaciones").click(function() {
				
		var elements = $('#escriptori_afectacions .escriptori_items_seleccionats .seleccionats').find('li');
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleAfectaciones.guardar(elements, url, id);
		
	});
	
	// Listener para guardado de módulos laterales vía AJAX.
	jQuery(".lista-simple-uasMateria").click(function() {
		
		var element = $(this).parent().parent().find("li");
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleUAsMateria.guardar(element, url, id);
		
	});
	
	//Para el reordenamiento de documentos.
	jQuery(".lista-simple-documentos").click(function() {
		
		var elements = $(this).parent().parent().find("div.cajaIdioma.ca li"); // Con esto obtenemos los <li> que cuelgan de <div class="cajaIdioma ca">
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleDocumentos.guardar(elements, url, id);
	
	});
	
	ListaSimpleAfectaciones = new CListaSimpleAfectaciones();
	ListaSimpleUAsMateria = new CListaSimpleUAs();
	ListaSimpleDocumentos = new CListaSimpleDocumentos();
    
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

	Error = new CError();
	Llistat = new CLlistat();	
	Detall = new CDetall();
	Auditoria = new ModulAuditories();
	Estadistica = new ModulEstadistiques();

	Detall.iniciar();

	// Mostrar detall
	var itemACarregar = itemAEditar();

	if (itemACarregar > 0)
		Detall.carregar(itemACarregar);

	Llistat.iniciar();

	// datos traductor
	CAMPOS_TRADUCTOR_NORMATIVA = ["item_titol_"];
	DATOS_TRADUCIDOS_NORMATIVA = ["titulo"];
	
	// Listener para guardado de módulo vía AJAX.
	/*
	jQuery(".gestionaBOIB").click(function() {
				
		// resultats
		jQuery("div#escriptori_detall").slideUp(300, function() {

			jQuery("div.modulBOIB").slideDown(300, function() {

				resultats_actiu_elm = resultats_elm.find("div.actiu:first");	
			});

		});
		
	});*/
	
	jQuery("#btnVolverTB").click(function() {
		
		// resultats
		jQuery("div.modulBOIB").slideUp(300, function() {

			jQuery("div#escriptori_detall").slideDown(300, function() {	
			});

		});
		
	});
	
	
	
	/*
	jQuery( "#item_butlleti_id" ).change(function() {
		var str = jQuery( "#item_butlleti_id option:selected" ).text();
	    if (str.trim() == "BOIB") {
	    	jQuery(".gestionaBOIB").show();
	    } else {
	    	jQuery(".gestionaBOIB").hide();
	    }
	});*/

});

//idioma
var pag_idioma = $("html").attr("lang");

var Cercador = {iniciar: function() {}};

//minim cercador
var numCercadorMinim = 0;

//paginacio
var paginacio_marge = 4;

//llistat
var itemID_ultim = 0;

function CLlistat() {

	this.extend = ListadoBase;		
	this.extend();

	var formulariComprovarTB = null;

	// Exporta la búsqueda
	this.exporta = function(opcions) {	
		this.carregar ({cercador: "si", exportar: "si"});
	};
	
	this.iniciar = function() {

		$("#cerca_data").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_data_butlleti").datepicker({ dateFormat: 'dd/mm/yy' });				
		$("#cerca_data_aprovacio").datepicker({ dateFormat: 'dd/mm/yy' });				
		$("#fechaTB").datepicker({ dateFormat: 'dd/mm/yy' });

		Llistat.carregar({});

		if (typeof FormularioBusquedaTB === "undefined")		
			FormularioBusquedaTB = "";

		formulariComprovarTB = new FormulariComprovar(FormularioBusquedaTB);
		formulariComprovarTB.iniciar();

	};
	
	/** Obtiene el texto quitando el null **/
	this.obtenerString = function(texto) {
		if (texto == null) {
			return "";
		} else {
			return texto;
		}
	};

	this.finCargaListado = function( opcions, data ) {

		resultats_total = parseInt(data.total, 10);

		if (resultats_total > 0) {

			if (resultats_total > numCercadorMinim)
				opcions_elm.find("li.C").animate({duration: "slow", width: 'show'}, 300);

			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;

			if (resultats_total % pag_Res > 0)
				ultimaPag++;

			if (pag_Pag > ultimaPag)
				pag_Pag = ultimaPag;

			resultatInici = ((pag_Pag * pag_Res) + 1);
			resultatFinal = ((pag_Pag * pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag * pag_Res) + pag_Res;

			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "id") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "numero") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "tipo") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "numNormativa") ? " " + ordre_T : "";
			ordre_c5 = (ordre_C == "fechaBoletin") ? " " + ordre_T : "";

			txt_ordenacio = "";

			if (resultats_total > 1) {

				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";

				if (ordre_C == "id")
					txt_per = txtLlistaItem;

				else if (ordre_C == "numNormativa")
					txt_per = txtNumNorma;

				else if (ordre_C == "tipo")
					txt_per = txtTipologia;

				else if (ordre_C == "fechaBoletin")
					txt_per = txtFechaAprobacio;

				else
					txt_per = txtTipologiaNorma;

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + " " + txtDeLa + " " + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina(); 
			codi_totals += "</p>";

			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\"><a class=\"id\" href=\"javascript:void(0)\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th tipologia" + ordre_c4 + "\" role=\"columnheader\"><a class=\"numNormativa\" href=\"javascript:void(0)\">" + txtNumNorma + "</a></div>";	
			codi_cap3 = "<div class=\"th numero" + ordre_c3 + "\" role=\"columnheader\"><a class=\"tipo\" href=\"javascript:void(0)\">" + txtBoletin + "</a></div>";
			codi_cap4 = "<div class=\"th tipus" + ordre_c4 + "\" role=\"columnheader\">" + txtTipologiaNorma + "</div>";			
			codi_cap5 = "<div class=\"th fecha" + ordre_c5 + "\" role=\"columnheader\"><a class=\"fechaBoletin\" href=\"javascript:void(0)\">" + txtFechaAprobacio + "</a></div>";						

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
			$(data.nodes).each(function(i) {

				dada_node = this;
				parClass = (i%2) ? " par": "";
				caducat_titol_class = (dada_node.vigente) ? " normativa" : " normativaCaducada";

				var fontcolor = ""; //Parametro extra para marcar con rojo valores no validos.
				if (dada_node.color != undefined && dada_node.color != '') {
						fontcolor = "style=\"color:" + dada_node.color+"\"";
				}
				
				codi_taula += '<div class="tr' + parClass + '" role="row">';

				codi_taula += '<div class="td titol ' + caducat_titol_class + ' role="gridcell">';
				codi_taula += '<input type="hidden" value="' + dada_node.id + '" class="id" />';
				codi_taula += '<span class="id">'+ dada_node.id +'</span><a id="normativa_'+dada_node.id+'" href="javascript:void(0);" class="titol" '+fontcolor+'>' + dada_node.titulo + '</a>';
				codi_taula += "</div>";

				codi_taula += "<div class=\"td tipologia\" role=\"gridcell\">" +  Llistat.obtenerString(dada_node.numNormativa) + "</div>";
				codi_taula += "<div class=\"td numero\" role=\"gridcell\">" + Llistat.obtenerString(dada_node.boletin) + "</div>";
				codi_taula += "<div class=\"td tipus\" role=\"gridcell\">" + Llistat.obtenerString(dada_node.tipo) + "</div>";				
				codi_taula += "<div class=\"td data\" role=\"gridcell\">" + Llistat.obtenerString(dada_node.fecha) + "</div>";

				codi_taula += "</div>";

			});

			codi_taula += "</div>";
			codi_taula += "</div>";

			if ($.browser.opera)
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");


			multipagina.init({ // Instanciamos el navegador multipágina.
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

			dades_elm.html(codi_final).fadeIn(300, function() { // pintem

				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click", function() { Llistat.ficha(this); });

				// Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
				jQuery("#resultats .table .th a").unbind("click").click(function() {
					Llistat.ordena(this, opcions);
				});

				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si")
					cercador_elm.find("input, select").removeAttr("disabled");

			});

		});

	};

	

	this.carregar = function(opcions) {

		var modoExportar = (typeof opcions.exportar != "undefined" && opcions.exportar == "si");
		
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
			dataVars_cercador += "&data=" + $("#cerca_data").val();			
			dataVars_cercador += "&text=" + $("#cerca_text").val();
			dataVars_cercador += "&data_butlleti=" + $("#cerca_data_butlleti").val();			
			dataVars_cercador += "&validacio=" + $("#cerca_validacio").val();			
			dataVars_cercador += "&totesUnitats=" + $("#cerca_totes_unitats").is(':checked');
			dataVars_cercador += "&uaFilles=" + $("#cerca_uaFilles").is(':checked');
			dataVars_cercador += "&invalids=" + $("#cerca_invalids").val();
			dataVars_cercador += "&idUA=" + $("#cerca_ua_id").val();
			dataVars_cercador += "&numNormativa=" + $("#cerca_num_normativa").val();
			dataVars_cercador += "&dataAprovacio=" + $("#cerca_data_aprovacio").val();
			dataVars_cercador += "&desactivarUA=true";
			
			
		} else {

			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;

			// cercador			
			dataVars_cercador = "&idUA=" + $("#cerca_ua_id").val();

		}

		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined")
			ordreTipus_elm.val(opcions.ordreTipus);

		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined")
			ordreCamp_elm.val(opcions.ordreCamp);

		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();

		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();

		// variables
		var dataVarsExportar = dataVars + "pagPag=0&pagRes=99999&&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		dataVars += "pagPag=" + pag_Pag + "&pagRes=" + pag_Res + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;		

		if (modoExportar) {
			Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtProcessant});
			var xhr = new XMLHttpRequest();
			xhr.open('POST', pagExportar, true);
			xhr.responseType = 'arraybuffer';
			xhr.onload = function () {
				Missatge.cancelar();
				if (this.status === 200) {
					var filename = "";
					var disposition = xhr.getResponseHeader('Content-Disposition');
					if (disposition && disposition.indexOf('attachment') !== -1) {
						var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
						var matches = filenameRegex.exec(disposition);
						if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
					}
					var type = xhr.getResponseHeader('Content-Type');

					var blob = new Blob([this.response], { type: type });
					if (typeof window.navigator.msSaveBlob !== 'undefined') {
						// IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
						window.navigator.msSaveBlob(blob, filename);
					} else {
						var URL = window.URL || window.webkitURL;
						var downloadUrl = URL.createObjectURL(blob);

						if (filename) {
							// use HTML5 a[download] attribute to specify filename
							var a = document.createElement("a");
							// safari doesn't support this yet
							if (typeof a.download === 'undefined') {
								window.location = downloadUrl;
							} else {
								a.href = downloadUrl;
								a.download = filename;
								document.body.appendChild(a);
								a.click();
							}
						} else {
							window.location = downloadUrl;
						}

						setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
					}
				}
			};
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.send(dataVarsExportar);
			
		} else {
			// ajax				
			$.ajax({
				type: "POST",
				url: pagLlistat,
				data: dataVars,
				dataType: "json",
				error: function() {
	
					if (!a_enllas) {
	
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						Error.llansar();
	
					}
	
				},
				success: function(data) {				
					Llistat.finCargaListado(opcions,data);					
				}
			});
		}
	};

	this.busca = function() {

		cercador_elm.find("input, select").attr("disabled", "disabled");

		resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");

		// animacio
		resultats_dades_elm.fadeOut(300, function() { // pintem

			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {

				// events taula
				pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				Llistat.carregar({cercador: "si"});

			});

		});

	};

	// FUNCIONALITAT TRASPAS BOIB

	var that = this;
	jQuery("#tabTraspasBoib").bind("click",function(){that.tabTraspasBoib();});
	jQuery("#btnBuscarFormTB").bind("click",function(){that.buscaTB();});
	jQuery("#btnLimpiarFormTB").bind("click",function(){that.limpiaTB();});

	var cercador_traspas_elm = jQuery("#cercadorTB");

	// Cambia a la pestaña del buscador.
	this.tabTraspasBoib = function() {

		jQuery("#opcions .actiu").removeClass("actiu");
		jQuery("#tabTraspasBoib").parent().addClass("actiu");

		opcio_unitat = "T";

		// resultats
		resultats_elm.find("div.actiu").slideUp(300, function() {

			jQuery(this).removeClass("actiu");
			resultats_elm.find("div."+opcio_unitat).slideDown(300, function() {

				jQuery(this).addClass("actiu");
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");

			});

		});

	};

	// Limpia el formulario de búsqueda.
	this.limpiaTB = function() {
		jQuery('#cercadorTB_contingut :input').each(limpiarCampo);
	};

	this.buscaTB = function() {

		// Se ha de rellenar el campo boletín o fecha.
		if ( jQuery("#numeroboletinTB").val() == "" && jQuery("#fechaTB").val() == "" ) {

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtIntroduceBoibOFecha});

			return;

		}

		formulariComprovarTB.llansar();        
		if ( !formulariComprovarTB.formComprovacio ) {
			return;
		}        

		multipagina.setPaginaActual(0);
		cercador_traspas_elm.find("input, select").attr("disabled", "disabled");

		resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");

		// animacio
		jQuery("#cercadorTB").find(".dades").fadeOut(300, function() { // pintem

			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			jQuery("#cercadorTB").find(".dades").html(codi_cercant).fadeIn(300, function() { // events taula

				pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				Llistat.carregarTB({});

			});
		});


	};
	
	//Métodos para traspaso BOIB
	this.carregarTBaNormativa = function(boibID) {
		
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
							
							Detall.nou(); 
							Detall.pintarTB(data);
							
						}
					}
					
				});
				
			});
			
		});

		//this.actualizaEventos();
		
	};

	this.carregarTB = function(opcions) {

		dataVars = "";

		pagPagina_elm = pagPagina_cercador_elm;
		ordreTipus_elm = ordreTipus_cercador_elm;
		ordreCamp_elm = ordreCamp_cercador_elm;

		// cercador
		dataVars_cercador = "&numeroboletin=" + $("#numeroboletinTB").val();
		dataVars_cercador += "&numeroregistro=" + $("#numeroregistroTB").val();
		dataVars_cercador += "&fecha=" + $("#fechaTB").val();			

		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined")
			ordreTipus_elm.val(opcions.ordreTipus);

		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined")
			ordreCamp_elm.val(opcions.ordreCamp);

		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag, 10) : multipagina.getPaginaActual();

		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();

		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;

		$.ajax({
			type: "POST",
			url: pagCercaBoib,
			data: dataVars,
			dataType: "json",
			error: function() {

				if (!a_enllas) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					Error.llansar();

				}

			},
			success: function(data) {
				if (data.errorMessage && data.errorMessage != '') {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + data.errorMessage + "</p>"});
					Error.llansar();
				} else {
					Llistat.finCargaListadoTB(opcions,data);
				}
			}
		});

	};

	// Cambia de página.
	this.cambiaPaginaTB = function( pag ) {
		
		multipagina.setPaginaActual(pag - 1);
		pag_Pag = pag;
		this.anar(pag, that.carregarTB);
		
	};

	this.finCargaListadoTB = function( opcions, data ) {
		
		resultats_total = parseInt(data.total,10);

		if (resultats_total > 0) {

			if (resultats_total > numCercadorMinim) // minim per cercador
				opcions_elm.find("li.C").animate({duration: "slow", width: 'show'}, 300);

			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;

			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0)
				ultimaPag++;

			if (pag_Pag > ultimaPag)
				pag_Pag = ultimaPag;

			resultatInici = ((pag_Pag * pag_Res) + 1);
			resultatFinal = ((pag_Pag * pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag * pag_Res) + pag_Res;

			//TODO: ordenació
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "titol") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "registre") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "butlleti") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "fechaBoletin") ? " " + ordre_T : "";

			txt_ordenacio = "";

			if (resultats_total > 1) {

				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";

				if (ordre_C == "id")
					txt_per = txtLlistaItem;
				
				else if (ordre_C == "numero")
					txt_per = txtNumero;
				
				else if (ordre_C == "tipo")
					txt_per = txtTipologia;
				
				else if (ordre_C == "fechaBoletin")
					txt_per = txtData;
				
				else
					txt_per = txtTipologia;

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + " " + txtDeLa + " " + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th registre" + ordre_c2 + "\" role=\"columnheader\">" + txtNumRegistro + "</div>";			
			codi_cap3 = "<div class=\"th numero" + ordre_c3 + "\" role=\"columnheader\">" + txtNumBoletin + "</div>";
			codi_cap4 = "<div class=\"th fechaBoletin" + ordre_c4 + "\" role=\"columnheader\">" + txtFechaBoletin + "</div>";


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
				caducat_titol_class = (dada_node.vigente) ? " normativa" : " normativaCaducada";

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

			if ($.browser.opera)
				escriptori_contingut_elm.find("div.table:first").css("font-size", ".85em");

			multipagina.init({ // Instanciamos el navegador multipágina.
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
		jQuery("#cercadorTB").find(".dades").fadeOut(300, function() {
			jQuery("#cercadorTB").find(".dades").html(codi_final).fadeIn(300, function() { // pintem
				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
				jQuery("#cercadorTB").find(".dades").find(".llistat .tbody a").unbind("click").bind("click", function() { 
					Llistat.fichaTB(this); 
				});

				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					cercador_elm.find("input, select").removeAttr("disabled");
				}
				
			});

		});
		
		if (data.errorMessage) {
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.errorMessage + "</p>"});
		}
	};
	
	/**
	 * Carga la ficha de un item del listado.
	 * @param link Objeto <A> sobre el que se realizó la acción.
	 */
	this.fichaTB = function( link ) {
		
		// Obtenemos el id del item a partir del id del enlace.
		itemID = jQuery(link).attr("id").split("_")[1];
		//Detall.carregarTB(itemID);
		this.carregarTBaNormativa(itemID);
	};

};

//items array
var Items_arr = new Array();

//detall
function CDetall() {
	
	this.extend = DetallBase;
	this.extend();

	var that = this;

	this.tipusAuditoria = 'normativa';
	this.tipusEstadistica = 'normativa';

	this.iniciar = function() {
		
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;

		jQuery("#item_data_norma, #item_data_norma_ca, #item_data_norma_es, #item_data_norma_en, #item_data_norma_de, #item_data_norma_fr, #item_data_butlleti").datepicker({ dateFormat: 'dd/mm/yy' });

		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {

			// Esconder todos menos el primero
			//$('div.idioma:gt(0)').hide();
			$('div.modulPrincipal').find('div.idioma:gt(0)').hide();			

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
		
		jQuery("#item_data_norma,#item_data_norma_es,#item_data_norma_en,#item_data_norma_de,#item_data_norma_fr").change(function(){
			jQuery("#item_data_norma,#item_data_norma_es,#item_data_norma_en,#item_data_norma_de,#item_data_norma_fr").val( jQuery(this).val() );
		});

		// boton de traducir
		jQuery("#botoTraduirNormativa").unbind("click").bind("click", function() {
			Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
		});

		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		

		// altres moduls
		modulAfectacions_pare_elm = $("#modulLateral div.modulAfectacions").parents("div.modul:first");
		modulProcediments_pare_elm = $("#modulLateral div.modulProcediments").parents("div.modul:first");
		modulServeis_pare_elm = $("#modulLateral div.modulServeis").parents("div.modul:first");

		// esborrar arxiu (reemplasar <input type="file"> per un de nou perque a IE nomes es de lectura)
		jQuery('a.esborraArxiu').click().unbind("click").click(function(event) {
			
			event.preventDefault();
			var input = $(this).parent().prev().find('input[type=file]');
			var inputId = input.attr('id');
			input.replaceWith('<input id="' + inputId + '" name="' + inputId + '" type="file" class="nou" />');
			
		});
		
	},

	this.traduirWrapper = function () {
		that.traduir(pagTraduirNormativa, CAMPOS_TRADUCTOR_NORMATIVA, DATOS_TRADUCIDOS_NORMATIVA);
	};

	//Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {

		// Validamos el formulario
		if (!that.formulariValid()) {
			return false;
		}

		//Preparar los datos de afectaciones relacionadas.
		//Crear un JSON con la lista de afectaciones.
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
					
					Detall.recarregar(data.id);
					
				}	
				
			}

		});
		
		return false;		

	};

	this.tipologia = function(e) {

		if ($(this).val() == "B") {
			
			modulAfectacions_pare_elm.fadeOut(300);
			modulProcediments_pare_elm.fadeOut(300);
			modulServeis_pare_elm.fadeOut(300);
			
		} else {
			
			modulAfectacions_pare_elm.fadeIn(300);
			modulProcediments_pare_elm.fadeIn(300);
			modulServeis_pare_elm.fadeIn(300);
			
		}

	};

	this.dataPublicacio = function(e) {		
//		if ($(this).val() == "") {
//		$(this).val(txtImmediat);
//		}		
	};
	
	this.activar = 0;

	this.nou = function() {

		//Anular id
		$("#item_id").val("");

		//Ocultar botones
		$("#modulLateral li.btnEliminar").hide();
		jQuery("#modulAuditories, #modulEstadistiques").hide();

		//Ocultar campos clave primaria		
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();

		//Mostrar botones cambio de UA / borrar UA.
		$("#botoneraCambioUA").show();
		$("#botonBorrarUA a").show();		

		//Borrar valores de los campos
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou, div.fila select.nou, div.modulDocuments input.nou").val("").end().find("h2:first").text(txtNouTitol);

		//Poner tipo Normativa por defecto
		$("#tipoNormativa").text(txtNormativa);
		
		//Resetear upload de archivos			
		for (var i in idiomas) {
			
			$("#grup_arxiu_actual_" + idiomas[i] + " span").show();
			$("#grup_arxiu_actual_" + idiomas[i] + " input").hide();
			$("#grup_arxiu_actual_" + idiomas[i] + " label.eliminar").hide();
			$("#grup_arxiu_actual_" + idiomas[i] + " a").hide();
			
		}

		//Ocultar paneles
		$("#modul_procediments, #modul_serveis, #modul_afectacions, #modulDocumentNormativa, #modul_unitats_administratives").hide();
		jQuery("#modulDocumentNormativa").find(".listaOrdenable").empty();
		jQuery("#modul_unitats_administratives").find(".listaOrdenable").empty();
		jQuery("#modul_procediments").find(".listaOrdenable").empty();
		jQuery("#modul_serveis").find(".listaOrdenable").empty();
		jQuery("#modul_afectacions").find(".listaOrdenable").empty();
		
		
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);		

		pro_seleccionats_elm = escriptori_detall_elm.find("div.modulAfectacions div.seleccionats");
		pro_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaAfectacions + ".");

		afecta_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.seleccionats");
		afecta_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaProcediments + ".");

		servicio_seleccionats_elm = escriptori_detall_elm.find("div.modulServeis div.seleccionats");
		servicio_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaServeis + ".");

		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none")
			suggeriment_elm.slideUp(300);

		$("#modulLateral p.baix:first").removeClass("iPublicat");

		modulAfectacions_pare_elm.show();
		modulProcediments_pare_elm.show();
		modulServeis_pare_elm.show();

		escriptori_contingut_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				itemID_ultim = this.activar;
			});
			
		});
		
		this.actualizaEventos();

		this.modificado(false);
		
		$("#item_validacio").val(1)
	};
	
	this.pintar = function(dades) {

		$("#modul_procediments, #modul_serveis, #modul_afectacions").show();

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
			$("#item_enllac_" + idioma).val(nn(dada_node["idioma_" + idioma + "_enllac"]));
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
		$("#item_num_norma").val(nn(dada_node.numNormativa));
		
		$("#item_numero").val(nn(dada_node.numero));
		$("#item_butlleti_id").val(nn(dada_node.butlleti_id));
		$("#item_butlleti").val(nn(dada_node.butlleti));
		/* Mostraba el botón antiguo de Importar BOIB
		 * if (dada_node.butlleti == "BOIB") {
			$(".gestionaBOIB").show();
		} else {
			$(".gestionaBOIB").hide();
		}*/
		$("#item_registre").val(nn(dada_node.registre));
		$("#item_llei").val(nn(dada_node.llei));

		$("#item_data_butlleti").val(nn(dada_node.data_butlleti));

		$("#item_data_norma").val(nn(dada_node.data_norma));
		$("#item_data_norma").change();

		$("#item_tipus").val(nn(dada_node.tipus));
		$("#item_tipus").change();

		$("#item_ua_id").val(nn(dada_node.idUA));
		$("#item_ua_nom").val(nn(dada_node.nomUA));

		$("#normativa_datos_validos").val(txtNormativaSinvalorar);
		$("#normativa_datos_validos").css( "color", "black" );
		
		if (dada_node.datosValidos == 0) {
			$("#normativa_datos_validos").val(txtNormativaNoValida);
			$("#normativa_datos_validos").css( "color", "red" );
		}
		
		if (dada_node.datosValidos == 1) {
			$("#normativa_datos_validos").val(txtNormativaValida);
		}
		
		if (dada_node.tipus == "B") {

			modulAfectacions_pare_elm.hide();
			modulProcediments_pare_elm.hide();
			modulServeis_pare_elm.hide();

		} else {

			modulAfectacions_pare_elm.show();
			modulProcediments_pare_elm.show();
			modulServeis_pare_elm.show();

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

		//Mostrar campo de responsable y mostrar normativa.
		$("#tipoNormativa").text(txtNormativa);
		$("#item_responsable_ca").show();
		$(".modul_informador div div.campoIdioma.es").hide();
		
		
		ModulDocuments.inicializarDocuments(dades.documents);
		ModulUnitatAdministrativa.inicializarUnidadesAdministrativas(dades.uas);

		// Marcamos el formulario como "no modificado".
		this.modificado(false);		

	};
	
	this.elimina = function() {

		dataVars = "accio=eliminar&id=" + $("#item_id").val(); // Llistat.itemID;

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
		
	};
	
	//Métodos para traspaso BOIB
	this.carregarTB = function(boibID) {
		
		//Cargamos los datos de un edicto del boib en una ficha vacía de normativa nueva
		$(".modulBOIB").fadeOut(300, function() {

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
								$(".modulBOIB").fadeIn(300);
							});
							
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.error + "</p>"});
							
						} else {
							/**
							 * Datos que devuelve:
							 * - butlleti:"BOIB"
							 * - butlleti_id:"1"
							 * - data_butlleti:"07/09/2017"
							 * - idioma_ca_apartat:"Secció I. Disposicions generals"
							 * - idioma_ca_enllac:"http://www.caib.es/eboibfront/ca/2017/10706/598980/aprovacio-definitiva-de-la-modificacio-del-credit-"
							 * - idioma_ca_pagfin:"28561"
							 * - idioma_ca_pagini:"28561"
							 * - idioma_ca_titol:"Aprovació definitiva de la modificació del crèdit extraordinari núm. 13/2017"
							 * - idioma_ca_tipo_publicacion:"anunci licitació"
							 * - idioma_es_apartat:"Sección I. Disposiciones generales"
							 * - idioma_es_enllac:"http://www.caib.es/eboibfront/es/2017/10706/598980/aprobacion-definitiva-de-la-modificacion-de-credit"
							 * - idioma_es_pagfin:"28952"
							 * - idioma_es_pagini:"28952"
							 * - idioma_es_titol:"Aprobación definitiva de la modificación de crédito extraordinario núm. 13/2017"
							 * - idioma_es_tipo_publicacion:"anuncio licitación"
							 * - numero:"2017110"
							 * - registre:"9654"
							 * - validacio:null
							 */
							Detall.pintarTB(data);
							
						}
					}
					
				});
				
			});
			
		});

		this.actualizaEventos();
		
	};
	
	this.pintarTB = function(dades) {

		//No es nuevo, es editar. Detall.nou();		

		//Rellena el formulario con los datos de un elemento BOIB
		dada_node = dades;

		
		
		for (var i in idiomas) {
			
			var idioma = idiomas[i];

			$("#item_titol_" + idioma).val(nn(dada_node["idioma_" + idioma + "_titol"]));
			$("#item_enllac_" + idioma).val(nn(dada_node["idioma_" + idioma + "_enllac"]));
			//$("#item_apartat_" + idioma).val(nn(dada_node["idioma_" + idioma + "_apartat"]));
			//$("#item_pagina_inicial_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagini"]));
			//$("#item_pagina_final_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagfin"]));
			$("#item_responsable_" + idioma).val(nn(dada_node["idioma_" + idioma + "_responsable"]));
			
			//Campos comentados en Back2
			////$("#item_des_curta_" + idioma).val(nn(dada_node["idioma_" + idioma + "_observacions"]));

			//$("#grup_arxiu_actual_" + idioma + " span").show();
			//$("#grup_arxiu_actual_" + idioma + " input").hide();
			//$("#grup_arxiu_actual_" + idioma + " label.eliminar").hide();
			//$("#grup_arxiu_actual_" + idioma + " a").hide();			

		}

		$("#item_numero").val(nn(dada_node.numero));
		$("#item_butlleti_id").val(nn(dada_node.butlleti_id));
		$("#item_butlleti").val(nn(dada_node.butlleti));
		//$("#item_registre").val(nn(dada_node.registre));
		$("#item_data_butlleti").val(nn(dada_node.data_butlleti));
		//$("#item_num_norma").val(nn(dada_node.numNormativa));
		if (dada_node.tipo_normativa_id != '') {
			$("#item_tipus").val(dada_node.tipo_normativa_id);
		}
		
		// resultats
		//jQuery("div.modulBOIB").slideUp(300, function() {
		if ($("#carregantDetall").size() > 0) {

			$("#carregantDetall").fadeOut(300, function() {
				$(this).remove();
				jQuery("div#escriptori_detall").slideDown(300); /*, function() {

					//resultats_actiu_elm = resultats_elm.find("div.actiu:first");	
				});*/

			});
		
		} else {
			$("#carregantDetall").fadeOut(300, function() {
				jQuery("div#escriptori_detall").slideDown(300);
			});
		}
		

	};

	this.pintarModulos = function(dades) {

		// Inicializamos de forma normal, al ser un módulo lateral guardable vía AJAX.
		ModulAfectacions.inicializarAfectacions(dades.afectacions);
		
		// En el caso de los procedimientos no inicializamos de forma normal, ya que no será una lista editable,
		// sino una lista de elementos de "sólo lectura". Los pintamos de forma especial teniendo eso en cuenta.
		pro_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.listaOrdenable");
		pro_nodes = dades.procediments;
		pro_nodes_size = pro_nodes.length;

		if (pro_nodes_size == 0) {
			
			escriptori_detall_elm.find("div.modulProcediments p.info").text(txtNoHiHaProcediments + ".");
			pro_seleccionats_elm.html("");
			
		} else {
			
			codi_pro = "<ul>";
			
			$(pro_nodes).each(function() {
				
				pro_node = this;
				codi_pro += "<li element-id=" + pro_node.id + " modulo-id= '" + pro_node.idModulo + "' ><input type=\"hidden\" value=\"" + pro_node.id + "\" /><a target=\"_blank\" href=\"../catalegProcediments/catalegProcediments.do?itemId="+pro_node.idProcedimiento+"\" >" + pro_node.nombre + "</a></li>";
				
			});
			
			codi_pro += "</ul>";
			txt_procediments = (pro_nodes_size == 1) ? txtProcediment : txtProcediments;
			escriptori_detall_elm.find("div.modulProcediments p.info").html(txtHiHa + " <strong>" + pro_nodes_size + " " + txt_procediments + "</strong>.");
			pro_seleccionats_elm.html(codi_pro);
			
		}
		

		// En el caso de los procedimientos no inicializamos de forma normal, ya que no será una lista editable,
		// sino una lista de elementos de "sólo lectura". Los pintamos de forma especial teniendo eso en cuenta.
		ser_seleccionats_elm = escriptori_detall_elm.find("div.modulServicios div.listaOrdenable");
		ser_nodes = dades.serveis;
		ser_nodes_size = ser_nodes.length;

		if (ser_nodes_size == 0) {
			
			escriptori_detall_elm.find("div.modulServeis p.info").text(txtNoHiHaServeis + ".");
			ser_seleccionats_elm.html("");
			
		} else {
			
			codi_ser = "<ul>";
			
			$(ser_nodes).each(function() {
				
				ser_node = this;
				codi_ser += "<li element-id=" + ser_node.id + " modulo-id= '" + ser_node.idModulo + "' ><input type=\"hidden\" value=\"" + ser_node.id + "\" /><a target=\"_blank\" href=\"../catalegServeis/catalegServeis.do?itemId="+ser_node.idServicio+"\" >" + ser_node.nombre + "</a></li>";
				
			});
			
			codi_ser += "</ul>";
			txt_serveis = (ser_nodes_size == 1) ? txtServei : txtServeis;
			escriptori_detall_elm.find("div.modulServeis p.info").html(txtHiHa + " <strong>" + ser_nodes_size + " " + txt_serveis + "</strong>.");
			ser_seleccionats_elm.html(codi_ser);
			
		}

	};

	this.ocultarModulos = function(selector) {

		if ( !selector.hasClass("publicacio") && !selector.attr("id") == "#modulDocumentNormativa" 
				&& !selector.attr("id") == "modul_procediments" 
				&& !selector.attr("id") == "modul_serveis" 
				&& !selector.attr("id") == "modul_afectacions")
			selector.addClass("invisible");

	};

};