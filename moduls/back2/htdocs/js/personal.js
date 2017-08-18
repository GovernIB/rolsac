// PERSONAL

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

	// INICIEM	
	Error = new CError();
	Llistat = new CLlistat();
	Detall = new CDetall();
	
//	Llistat.iniciar();	
//	Detall.iniciar();
	
	// Mostrar llistat o detall
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	} else {
        Detall.iniciar();
		// Cercador.iniciar();
	}
    Llistat.iniciar();
    
});

// idioma
var pag_idioma = $("html").attr("lang");

// minim cercador
var numCercadorMinim = 0;

// llistat
var itemID_ultim = 0;
function CLlistat(){	
	this.extend = ListadoBase;
	this.extend();

	this.iniciar = function() {		
		Llistat.carregar({});		
	};
	
	// Exporta la búsqueda
	this.exporta = function(opcions) {	
		this.carregar ({cercador: "si", exportar: "si"});
	};
	
	
	// Método llamado al terminar de cargar el listado por ajax.
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
			ordre_c1 = (ordre_C == "nombre") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "username") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "uniAdmin") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "email") ? " " + ordre_T : "";
			ordre_c5 = (ordre_C == "extensionPublica") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";
                
				if (ordre_C == "nombre") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "username") {
					txt_per = txtCodi;
				} else if (ordre_C == "uniAdmin") {
					txt_per = txtUA;
				} else if (ordre_C == "email") {
					txt_per = txtEmail;
				} else if (ordre_C == "extensionPublica") {
					txt_per = txtEpui;
				} else{
                    txt_per = "XX";
                }
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";

			// De momento no habra ordenacion.
//						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
//						codi_cap2 = "<div class=\"th codi" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCodi + "</a></div>";
//						codi_cap3 = "<div class=\"th ua" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtUA + "</a></div>";
//						codi_cap4 = "<div class=\"th email" + ordre_c4 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtEmail + "</a></div>";
//						codi_cap5 = "<div class=\"th epui" + ordre_c5 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtEpui + "</a></div>";
			
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a class=\"nombre\" href=\"javascript:void(0)\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th codi" + ordre_c2 + "\" role=\"columnheader\"><a class=\"username\" href=\"javascript:void(0)\">" + txtCodi + "</a></div>";
			codi_cap3 = "<div class=\"th ua" + ordre_c3 + "\" role=\"columnheader\"><a class=\"unidadAdministrativa.id\" href=\"javascript:void(0)\">" + txtUA + "</a></div>";
  			codi_cap4 = "<div class=\"th email" + ordre_c4 + "\" role=\"columnheader\"><a class=\"email\" href=\"javascript:void(0)\">" + txtEmail + "</a></div>";
			codi_cap5 = "<div class=\"th epui" + ordre_c5 + "\" role=\"columnheader\"><a class=\"extensionPublica\" href=\"javascript:void(0)\">" + txtEpui + "</a></div>";
			
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
			//$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
			$(data.nodes).each( function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"itemPersona_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td codi\" role=\"gridcell\">" + printStringFromNull(dada_node.username) + "</div>";
				codi_taula += "<div class=\"td ua\" role=\"gridcell\">" + printStringFromNull(dada_node.uniAdmin) + "</div>";
				codi_taula += "<div class=\"td email\" role=\"gridcell\">" + printStringFromNull(dada_node.email) + "</div>";
				codi_taula += "<div class=\"td epui\" role=\"gridcell\">" + printStringFromNull(dada_node.extensionPublica) + "</div>";
				
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
			codi_final = "<p class=\"noItems\">" + txtNoHiHaItems + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem						
			dades_elm.html(codi_final).fadeIn(300, function() {
			
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
	};
	
	this.carregar = function(opcions) {	
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;
		var modoExportar = (typeof opcions.exportar != "undefined" && opcions.exportar == "si");
		
		dataVars = "";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			
			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;
			
			// cercador
			var uaMevesVal = $("#cerca_uaMeves").is(':checked') ? 1 : 0;
			var uaFillesVal = $("#cerca_uaFilles").is(':checked') ? 1 : 0;
			dataVars_cercador = "&cerca_codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&cerca_text=" + $("#cerca_text").val();
			dataVars_cercador += "&uaMeves=" + uaMevesVal;
			dataVars_cercador += "&uaFilles=" + uaFillesVal;
		
		} else {
			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;
			
			dataVars_cercador = "";
			
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
		var dataVarsExportar = dataVars + "pagPag=0&pagRes=999999&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		dataVars += "pagPag=" + pag_Pag + "&pagRes=" + pag_Res + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		 if ( modoExportar ) { 
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
				
		 } else if ( modoListado || modoBuscador ) {
			$.ajax({
				type: "POST",
				url: pagLlistatPersonal,
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
	};
													
	this.anar = function(enlace_html) {

		resultats_actiu_elm = resultats_elm.find("div.actiu:first");
		
		if (isNaN(parseInt(enlace_html,10))) {
			if (elm.hasClass("inici")) {
				num = 1;
			} else if (elm.hasClass("anteriors")) {
				num = parseInt(pag_Pag,10);
			} else if (elm.hasClass("final")) {
				num = paginasNum;
			} else {
				num = parseInt(pag_Pag,10)+2;
			}
		} else {
			num = parseInt(enlace_html,10);
		}
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantAnteriors : txtCercantSeguents;
		resultats_dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		resultats_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			resultats_dades_elm.html(codi_anar).fadeIn(300, function() {
				
				//pagPagina_elm.val(num-1);
				
				// llancem!
												
				if (resultats_actiu_elm.hasClass("C")) {
					Llistat.carregar({pagina: num-1, cercador: "si"});
				} else {
					Llistat.carregar({pagina: num-1});
				}
				
			});
		});
		
	};
};

// detall array
var Items_arr = new Array();

// detall
function CDetall(){
	// Extendemos de la clase base;
	this.extend = DetallBase;
	this.extend();
	
	this.iniciar = function() {			
		// dates
		$("#procediment_data_publicacio, #procediment_data_caducitat, #procediment_data_actualitzacio").mask("99/99/9999");
		
		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			ul_idiomes_elm = escriptori_detall_elm.find("ul.idiomes:first");
									
			a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			a_primer_elm_class = a_primer_elm.attr("class");
			a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			div_idiomes_elm = escriptori_detall_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			//ul_idiomes_elm.bind("click",Detall.idioma);
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
		
		// màscares camps
		//$("#item_codi").mask("999999");
		$("#item_epui, #item_epri, #item_em").mask("99999");
		$("#item_nlpui, #item_nlpri, #item_nlm").mask("999999999");
		
	};
		
	this.nou = function() {
		
		$("#item_id").val("");
		
		//escriptori_detall_elm.find("a.elimina").hide().end().find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);		
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {				
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();
		
		this.modificado(false);
	};
			
	this.pintar = function(dades) {
		
		escriptori_detall_elm.find("a.elimina").show().end().find("h2:first").text(txtDetallTitol);
		
		pro_node = dades;
							
		$("#item_id").val(pro_node.id);
		
		$("#item_nom").val(pro_node.nom);
		$("#item_codi").val(pro_node.codi);
		$("#item_ua").val(pro_node.ua);
		$("#item_ua_id").val(pro_node.uaId);
		$("#item_funcions").val(pro_node.funcions);
		$("#item_carrec").val(pro_node.carrec);
		
		$("#item_email").val(pro_node.email);
		$("#item_epui").val(pro_node.extensioPublicaIntranet);
		$("#item_nlpui").val(pro_node.numeroLlargPublicIntranet);
		$("#item_epri").val(pro_node.extensioPrivadaIntranet);
		$("#item_nlpri").val(pro_node.numeroLlargPrivatIntranet);
		$("#item_em").val(pro_node.extensioMobil);
		$("#item_nlm").val(pro_node.extensioLlargMobil);
		
		// mostrem
		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				
				// array
				Detall.array({id: pro_node.id, accio: "guarda", dades: pro_node});
				
				escriptori_detall_elm.fadeIn(300);
											
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);				
			});
		
		}
		
		this.modificado(false);
		
	};
	
	this.elimina = function() {
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		
		//dataVars = "accio=elimina" + "&id=" + Llistat.itemID;
		item_ID = $("#item_id").val();
		dataVars = "id=" + item_ID;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagEsborrar,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.cancelar();
			
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {
				Llistat.anulaCache();
				if (data.id > -1) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					Detall.array({id: pro_node.id, accio: "elimina"});
					Detall.recarregar();
				} else {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}			
		});		
	};
	
};