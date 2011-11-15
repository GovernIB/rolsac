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
	}
	
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
	}
													
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
		
	}
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
		$("#item_codi").mask("u999999");
		$("#item_epui, #item_epri, #item_em").mask("99999");
		$("#item_nlpui, #item_nlpri, #item_nlm").mask("999999999");
		
	}
		
	this.nou = function() {
		
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
	}
			
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
		
	}
	
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