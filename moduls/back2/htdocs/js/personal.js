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

	// INICIEM	
	Error = new CError();
	
	Llistat.iniciar();	
	Detall.iniciar();	
	//Docs.iniciar();
	
	//$.suggeriments();	
});

// idioma
var pag_idioma = $("html").attr("lang");

// minim cercador
var numCercadorMinim = 0;

// llistat
var itemID_ultim = 0;
var Llistat = {	

	iniciar: function() {	
		this.extend = ListadoBase;
		this.extend();
		
		Llistat.carregar({});		
	},
	
	// Método llamado al terminar de cargar el listado por ajax.
	finCargaListado: function( opcions, data ){			
		
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
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + txtAla + " " + resultatFinal + txt_ordenacio + ".</p>";

			// De momento no habra ordenacion.
//						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
//						codi_cap2 = "<div class=\"th codi" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCodi + "</a></div>";
//						codi_cap3 = "<div class=\"th ua" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtUA + "</a></div>";
//						codi_cap4 = "<div class=\"th email" + ordre_c4 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtEmail + "</a></div>";
//						codi_cap5 = "<div class=\"th epui" + ordre_c5 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtEpui + "</a></div>";
			
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th codi" + ordre_c2 + "\" role=\"columnheader\">" + txtCodi + "</div>";
			codi_cap3 = "<div class=\"th ua" + ordre_c3 + "\" role=\"columnheader\">" + txtUA + "</div>";
			codi_cap4 = "<div class=\"th email" + ordre_c4 + "\" role=\"columnheader\">" + txtEmail + "</div>";
			codi_cap5 = "<div class=\"th epui" + ordre_c5 + "\" role=\"columnheader\">" + txtEpui + "</div>";
			
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
				funcionPagina: "Llistat.cambiaPagina",
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
			
				// events
				//escriptori_contingut_elm.bind("click",Llistat.llansar);
				
				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					cercador_elm.find("input, select").removeAttr("disabled");
				}
				
			});
		});
	},
	carregar: function(opcions) {	
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;
		
		dataVars = "";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			
			/*pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;*/
			
			// cercador
			dataVars_cercador = "&nom=" + $("#cerca_nom").val();
			dataVars_cercador += "&username=" + $("#cerca_codi").val();
			dataVars_cercador += "&idUA=" + $("#cerca_ua_id").val(); //TODO: no te sentit cercar per nom de UA, sino per codi
			dataVars_cercador += "&funcions=" + $("#cerca_funcions").val();
			dataVars_cercador += "&carrec=" + $("#cerca_carrec").val();
			dataVars_cercador += "&email=" + $("#cerca_email").val();
			dataVars_cercador += "&epui=" + $("#cerca_epui").val();
			dataVars_cercador += "&nlpui=" + $("#cerca_nlpui").val();
			dataVars_cercador += "&epri=" + $("#cerca_epri").val();
			dataVars_cercador += "&nlpri=" + $("#cerca_nlpri").val();
			dataVars_cercador += "&em=" + $("#cerca_em").val();
			dataVars_cercador += "&nlm=" + $("#cerca_nlm").val();
		
		} else {
			
			/*pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;*/
			
			// cercador
			
			dataVars_cercador = "&idUA=" + $("#cerca_ua_id").val();//Siempre habra una UA
			
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
		//pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_elm.val(),10);		
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();
			
		// ordre
		/*ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();*/
		ordre_Tipus = "";
		ordre_Camp = "";
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax		
		if ( ( modoListado && !Llistat.cacheDatosListado ) || modoBuscador ){
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
	},
									
	llansar: function(e) {					
		/*elm = $(e.target);
				
		if (elm.is("A")) {
			// desactivem taula
			escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
			
			// cercador
			resultats_actiu_elm = resultats_elm.find("div.actiu:first");			
									
			// llancem
			pare_elm = elm.parent();
			
			if (pare_elm.hasClass("th")) {
									
				// ordenacio
				if (pare_elm.hasClass("ASC")) {
					ordreTipus_elm.val("DESC");
				} else if (pare_elm.hasClass("DESC")) {
					ordreTipus_elm.val("ASC");
				} else {
					pare_class = pare_elm.attr("class");
					c = pare_class.substr(pare_class.indexOf(" ")+1);
					ordreCamp_elm.val(c);
				}
				
				resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");
				
				// animacio
				resultats_dades_elm.fadeOut(300, function() {
					// pintem
					codi_ordre = "<p class=\"executant\">" + txtCarregantItems + "</p>";
					resultats_dades_elm.html(codi_ordre).fadeIn(300, function() {
						
						if (resultats_actiu_elm.hasClass("C")) {
							Llistat.carregar({cercador: "si"});
						} else {
							Llistat.carregar({});
						}
						
					});
				});
							
			} 
		}*/
	},
			
	anar: function(enlace_html) {

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
		
	},
};

// detall array
var Items_arr = new Array();


// detall
var Detall = {
	iniciar: function() {		
		// Extendemos de la clase base;
		this.extend = DetallBase;
		this.extend();
		
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
		$("#item_codi").mask("u999999");
		$("#item_epui, #item_epri, #item_em").mask("99999");
		$("#item_nlpui, #item_nlpri, #item_nlm").mask("999999999");
		
	},
	
	/*
	// dsanchez: En Personal no hay idiomas.	
	idioma: function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			ul_idiomes_elm.unbind("click",Detall.idioma);
			
			if (!elm.hasClass("desplegar")) {
				
				a_clicat_class = elm.attr("class");
				
				div_idiomes_elm.find("div.seleccionat").slideUp(300, function() {
					
					$(this).removeClass("seleccionat");
					span_primer_elm = ul_idiomes_elm.find("span:first");
					span_primer_elm_class = span_primer_elm.attr("class");
					span_primer_elm_text = span_primer_elm.text();
					span_primer_elm.parent().removeClass("seleccionat").html("<a href=\"javascript:;\" class=\"" + span_primer_elm_class + "\">" + span_primer_elm_text + "</a>");
					
					elm_text = elm.text();
					elm.parent().addClass("seleccionat").html("<span class=\"" + a_clicat_class + "\">" + elm_text + "</span>");
					
					div_idiomes_elm.find("div." + a_clicat_class).slideDown(300, function() {
						$(this).addClass("seleccionat");
						ul_idiomes_elm.bind("click",Detall.idioma);
					});
				
				});
				
			} else{
				
				if (!elm.hasClass("on")) {
				
					ul_idiomes_elm.find("li:not(.desplegar)").css("display","none");
					div_idiomes_elm.find("div.idioma").each(function(i) {
						text_idioma = ul_idiomes_elm.find("li:eq(" + i + ")").text();
						div_idioma_elm = $(this);
						if (i >= 1) {
							div_idioma_elm.css("border-top",".2em solid #ffecd9");
						}
						div_idioma_elm.prepend("<h3>" + text_idioma + "</h3>").slideDown(300);
					});
					elm.addClass("on").text(txtPlega);
				
				} else {
					
					div_idiomes_elm.find("div.idioma").each(function(i) {
						div_idioma_elm = $(this);
						if (i >= 1) {
							div_idioma_elm.css("border-top","");
						}
						div_idioma_elm.find("h3:first").remove().end().slideUp(300);
					});
					elm.removeClass("on").text(txtDesplega);
					ul_idiomes_elm.find("li:not(.desplegar)").css("display","block");
					
				}
				
				ul_idiomes_elm.bind("click",Detall.idioma);
			
			}
			
		}
		
	},
	*/
	llansar: function(e) {	
		
		/*
		elm = $(e.target);
		
		if (elm.is("A") && elm.hasClass("modul")) {
			
			escriptori_detall_elm.unbind("click", Detall.llansar);
			
			modul_continguts_elm = elm.parent().find("div.modul_continguts:first");
			
			if (elm.hasClass("amagat")) {
				modul_continguts_elm.slideDown(300, function() {
					elm.addClass("mostrat").removeClass("amagat").text(txtAmaga);
					escriptori_detall_elm.bind("click", Detall.llansar);
				});
			} else {
				modul_continguts_elm.slideUp(300, function() {
					$(this).removeClass("mostrat");
					elm.addClass("amagat").removeClass("mostrat").text(txtMostra);
					escriptori_detall_elm.bind("click", Detall.llansar);
				});
			}
			
		} else if (elm.is("SPAN") && elm.parents("a:first").hasClass("btn")) {
			
			a_elm = elm.parents("a:first");
			
			if (a_elm.hasClass("torna")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.torna();
				
			} else if (a_elm.hasClass("guarda")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.guarda();
				
			} else if (a_elm.hasClass("elimina")) {
					
				// missatge
				Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtItemEliminar, funcio: function() { Detall.elimina(); }});
				
			}
			
		}*/
		
	},
	nou: function() {
		
		//escriptori_detall_elm.find("a.elimina").hide().end().find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);		
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				escriptori_detall_elm.bind("click", Detall.llansar);
				itemID_ultim = 0;
			});
		});
		
	},
			
	pintar: function(dades) {
		
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
				
				escriptori_detall_elm.fadeIn(300, function() {
					// activar
					escriptori_detall_elm.bind("click", Detall.llansar);
				});
											
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300, function() {
					// activar
					escriptori_detall_elm.bind("click", Detall.llansar);
				});
			});
		
		}
		
	},	
	elimina: function() {
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
																		
		dataVars = "accio=elimina" + "&id=" + Llistat.itemID;
				
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
			
				Missatge.cancelar();
				
				if (data.id > -1) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});					
				}
								
				// array
				Detall.array({id: pro_node.id, accio: "elimina"});
				// recarregar
				Detall.recarregar();
				
			}			
		});		
	}
	
};

/*
// documents
var Docs = {
	iniciar: function() {				
		docs_elm = $("div.documents");
		docs_table_existix = false;
		
		docs_elm.bind("click",Docs.llansar);
		
		codi_table_docs = "<div class=\"table docs\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
		codi_table_docs += "<div class=\"thead\">";
		codi_table_docs += "<div role=\"rowheader\" class=\"tr\">";
		codi_table_docs += "<div role=\"columnheader\" class=\"th doc\">" + txtDoc + "</div>";
		codi_table_docs += "<div role=\"columnheader\" class=\"th opcions\"></div>";
		codi_table_docs += "</div>";
		codi_table_docs += "</div>";
		codi_table_docs += "<div class=\"tbody\">";
		codi_table_docs += "</div>";
		codi_table_docs += "</div>";
		
		return codi_table_docs;
		
	},
	llansar: function(e) {
		elm = $(e.target);
		
		if (elm.is("A") || (elm.is("SPAN") && !elm.hasClass("doc"))) {
			
			docs_elm.unbind("click",Docs.llansar);
			
			A_elm = elm.parents("a:first");
			doc_elm = elm.parents("div.documents:first");
			
			if (A_elm.hasClass("afegeix")) {
				
				if (doc_elm.find("div.table").size() == 0) {
			
					elm.parents("p:first").before(codi_table_docs);
					
					docs_table_elm = doc_elm.find("div.table:first");
					docs_table_tbody_elm = docs_table_elm.find("div.tbody");
					
					docs_table_existix = true;
					
				}
				
				tr_num = docs_table_tbody_elm.find("div.tr").size();
				
				par_class = (tr_num%2) ? " par" : "";
				
				codi_docs = "<div role=\"row\" class=\"tr nou" + par_class + "\">";
				codi_docs += "<div role=\"gridcell\" class=\"td doc\">";
				codi_docs += "<input name=\"doc_ca\" type=\"file\" />";
				codi_docs += "</div>";
				codi_docs += "<div role=\"gridcell\" class=\"td opcions\">";
				codi_docs += "<a href=\"javascript:;\" class=\"btn lleva\"><span><span>" + txtLleva + "</span></span></a>";
				codi_docs += "</div>";
				codi_docs += "</div>";
				
				docs_table_tbody_elm.append(codi_docs);
				docs_table_tbody_elm.find("div.tr:last").slideDown(300,function() {
					$(this).removeClass("nou");
				});
				
			} else if (A_elm.hasClass("lleva")) {
				
				tr_elm = elm.parents("div.tr:first");
				
				tr_elm.slideUp(300,function() {
					$(this).remove();
					
					tr_num = docs_table_tbody_elm.find("div.tr").size();
					if (tr_num == 0) {
						docs_table_elm.slideUp(300,function() {
							$(this).remove();
							docs_table_existix = false;
						});
					}
					
				});
				
			} else if (A_elm.hasClass("elimina") || A_elm.hasClass("inclou")) {
				
				if (A_elm.hasClass("inclou")) {
					A_elm.removeClass("inclou").addClass("elimina").html("<span><span>" + txtElimina + "</span></span>");
					A_elm.parents("div.tr:first").find("span.doc").removeClass("elimina");
				} else {
					A_elm.removeClass("elimina").addClass("inclou").html("<span><span>" + txtInclou + "</span></span>");
					A_elm.parents("div.tr:first").find("span.doc").addClass("elimina");
				}
				
			}
			
			docs_elm.bind("click",Docs.llansar);
			
		}
		
	}
};*/