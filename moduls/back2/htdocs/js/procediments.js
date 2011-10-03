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
	escriptori_previsualitza_elm = $("#escriptori_previsualitza");
	
	// Asociamos los evento a los botones.
	jQuery("#btnNuevaFicha").bind("click",function(){Llistat.nuevaFicha();});
	jQuery("#tabListado").bind("click",function(){Llistat.tabListado();});	
	jQuery("#tabBuscador").bind("click",function(){Llistat.tabBuscador();});		
	jQuery("#btnBuscarForm").bind("click",function(){Llistat.busca();});
	jQuery("#btnLimpiarForm").bind("click",function(){Llistat.limpia();});	
	
	jQuery("#btnVolver").bind("click",function(){Detall.torna();});
	jQuery("#btnGuardar").bind("click",function(){Detall.guarda();});
	jQuery("#btnEliminar").bind("click",function(){Detall.eliminar();});
	jQuery("#btnPrevisualizar").bind("click",function(){Detall.previsualitza();});
	
						
	// INICIEM
	
	// es un detall?
	window_href = window.location.href;
	if (window_href.indexOf('?/') != -1) {
		
		var vars = [], hash;
		var hashes = window.location.href.slice(window_href.indexOf('?/') + 2).split('&');
		var hashes_size = hashes.length;
		
		if (hashes_size > 0) {
		
			for(var i = 0; i < hashes_size; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			
			Detall.carregar(vars[0]);
			
		}
	
	}
	
	Llistat.iniciar();
	Cercador.iniciar();
	Detall.iniciar();	
	//Docs.iniciar();
	
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
var Llistat = {
	iniciar: function() {
		this.extend = ListadoBase;
		this.extend();
	
		$("#cerca_fechaCaducidad").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaPublicacion").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaActualizacion").datepicker({ dateFormat: 'dd/mm/yy' });

		Llistat.carregar({});
	},
	
	finCargaListado: function(opcions,data){
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
			ordre_c1 = (ordre_C == "nom") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "publicacio") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "caducitat") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "nom") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "publicacio") {
					txt_per = txtPublicacio;
				} else {
					txt_per = txtCaducitat;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDel + " " + resultatInici + " " + txtAl + " " + resultatFinal + txt_ordenacio + ".</p>";
			
			// De momento no habra ordenacion.
//						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
//						codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
//						codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
			
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\">" + txtPublicacio + "</div>";
			codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\">" + txtCaducitat + "</div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				caducat_nom_class = (dada_node.caducat == "S") ? " nomCaducat" : "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td nom" + caducat_nom_class + "\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"procediment_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = (dada_node.caducat == "S") ? " caducat" : "";
				codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.publicacio) + "</div>";
				codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.caducitat) + "</div>";
				
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
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.ficha(this);});
			
				// events
				if (escriptori_contingut_elm.css("display") != "none") {
					escriptori_contingut_elm.bind("click",Llistat.llansar);
				}
				
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
			
			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;
			
			// cercador
			//dataVars_cercador = "&nom=" + $("#cerca_nom").val();
			//dataVars_cercador += "&codi=" + $("#cerca_codi").val();
			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&estat=" + $("#cerca_estat").val();
			dataVars_cercador += "&familia=" + $("#cerca_familia").val();
			dataVars_cercador += "&iniciacio=" + $("#cerca_iniciacio").val();
			dataVars_cercador += "&tramit=" + $("#cerca_tramit").val();
			dataVars_cercador += "&versio=" + $("#cerca_versio").val();
			dataVars_cercador += "&url=" + $("#cerca_url").val();
			dataVars_cercador += "&indicador=" + $("#cerca_indicador").val();
			dataVars_cercador += "&finestreta=" + $("#cerca_finestreta").val();
			dataVars_cercador += "&taxa=" + $("#cerca_taxa").val();
			dataVars_cercador += "&responsable=" + $("#cerca_responsable").val();
			dataVars_cercador += "&fechaCaducidad=" + $("#cerca_fechaCaducidad").val();
			dataVars_cercador += "&fechaPublicacion=" + $("#cerca_fechaPublicacion").val();
			dataVars_cercador += "&fechaActualizacion=" + $("#cerca_fechaActualizacion").val();
			dataVars_cercador += "&uaFilles=" + $("#cerca_uaFilles").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
			var uaMevesVal = $("#cerca_uaMeves").attr('checked') ? 1 : 0;
			dataVars_cercador += "&uaMeves=" + uaMevesVal;

		} else {

			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;
			
			// cercador
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
		//pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_elm.val(),10);
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
	
	},
	llansar: function(e) {
		/*elm = $(e.target);
		
		if (elm.is("A")) {
			
			// desactivem taula
			escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
			
			// cercador
			resultats_actiu_elm = resultats_elm.find("div.actiu:first");
			if (resultats_actiu_elm.hasClass("C")) {
				
				pagPagina_elm = pagPagina_cercador_elm;
				ordreTipus_elm = ordreTipus_cercador_elm;
				ordreCamp_elm = ordreCamp_cercador_elm;
			
			} else {
				
				pagPagina_elm = pagPagina_llistat_elm;
				ordreTipus_elm = ordreTipus_llistat_elm;
				ordreCamp_elm = ordreCamp_llistat_elm;
				
			}

			// llancem
			pare_elm = elm.parent();
			
			if (pare_elm.is("LI") && pare_elm.hasClass("opcio")) {
				
				// opcions pestanya
				Llistat.opcions(elm);
				
			} else if (pare_elm.hasClass("th")) {
				
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
					codi_ordre = "<p class=\"executant\">" + txtCarregantPro + "</p>";
					resultats_dades_elm.html(codi_ordre).fadeIn(300, function() {
						
						if (resultats_actiu_elm.hasClass("C")) {
							Llistat.carregar({cercador: "si"});
						} else {
							Llistat.carregar({});
						}
						
					});
				});
				
			} else if (pare_elm.is("P") && pare_elm.attr("class") == "paginacio") {
				
				pag_Pag = pagPagina_elm.val();
				enlace_html = elm.html();
				Llistat.anar(enlace_html);
				
			} else if (elm.hasClass("nom") && pare_elm.hasClass("nom")) {
				
				itemID = pare_elm.find("input.id").val();				
				Detall.carregar(itemID);
				itemID_ultim = itemID;
				
			}
			
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_btn = elm.parents("a.btn:first");
			
			if (elm.parents("a.btn:first").hasClass("nou")) {
			
				// desactivem taula
				escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
				
				Detall.nou();
			
			} else if (a_btn.hasClass("consulta")) {javascript:;
				
				// desactivem taula
				escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
				cercador_elm.find("input, select").attr("disabled", "disabled");
				
				resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");
				
				// animacio
				resultats_dades_elm.fadeOut(300, function() {
					// pintem
					codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
					resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {
					
						// events taula
						Llistat.carregar({cercador: "si"});
						
					});
				});				
				
			} else if (a_btn.hasClass("borrar")) {
				// netejar els camps editables del cercador
				$('#cercador_contingut :input').each(limpiarCampo);
			}
			
		}*/
		
	},
	/*anar: function(enlace_html) {
		
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
				pagPagina_elm.val(num-1);
				// llancem!
				
				if (resultats_actiu_elm.hasClass("C")) {
					Llistat.carregar({pagina: num-1, cercador: "si"});
				} else {
					Llistat.carregar({pagina: num-1});
				}
				
			});
		});
		
	},*/
	/*opcions: function(elm) {
		// estils
		opcions_elm.find("li.actiu").html("<a href=\"javascript:;\">" + opcions_elm.find("li.actiu").html() + "</a>").removeClass("actiu");
		// opcio
		li_pare = elm.parent();
		if (li_pare.hasClass("L")) {
			opcio_unitat = "L";
		} else if (li_pare.hasClass("C")) {
			opcio_unitat = "C";
		}
		li_pare.html(elm.html()).addClass("actiu");
		// resultats
		resultats_elm.find("div.actiu").slideUp(300,function() {
			$(this).removeClass("actiu");
			resultats_elm.find("div."+opcio_unitat).slideDown(300,function() {
				$(this).addClass("actiu");
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");
				escriptori_contingut_elm.bind("click",Llistat.llansar);
			});
		});
	}*/
};

// items array
var Items_arr = new Array();

// detall
var Detall = {
	iniciar: function() {	
		this.extend = DetallBase;
		this.extend();
		
		// dates
		//$("#item_data_publicacio, #item_data_caducitat").mask("99/99/9999").datepicker({ altField: '#actualDate' });
		$("#item_data_caducitat").datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });
		$("#item_data_publicacio").bind("blur",Detall.dataPublicacio).datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });

		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			$('div.idioma:gt(0)').hide();
			
			ul_idiomes_elm = escriptori_detall_elm.find("ul.idiomes:first");
			
			//ul_idiomes_elm.find("li").css({"border-radius": "1em 1em 0 0", "-moz-border-radius": "1em 1em 0 0", "-webkit-border-radius": "1em 1em 0 0"}).end().css("background-image", "-moz-linear-gradient(0% 50% 270deg, #fff6ee, #ffddbc)");
			
			a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			a_primer_elm_class = a_primer_elm.attr("class");
			a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			div_idiomes_elm = escriptori_detall_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click",Detall.idioma);
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");
		moduls_elm.css({"border-radius": "1em 1em 0 0", "-moz-border-radius": "1em 1em 0 0", "-webkit-border-radius": "1em 1em 0 0"});
		
		// modul publicacio
		$("#modulLateral div.publicacio p.botonera").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
	},
	dataPublicacio: function(e) {
		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
		
	},
	/*idioma: function(e) {
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
				
				if (typeof modul_documents_elm != "undefined") {
					modul_documents_idiomes_elm.find("li.seleccionat:first").removeClass("seleccionat");
					modul_documents_idiomes_elm.find("li." + a_clicat_class).addClass("seleccionat");
					
					documents_seleccionats_elm.find("div.seleccionat").slideUp(300, function() {
						$(this).removeClass("seleccionat");
						documents_seleccionats_elm.find("div." + a_clicat_class).slideDown(300, function() {
							$(this).addClass("seleccionat");
						});
					});
					
				}
				
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
		
	},*/
	
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
		
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a:first");
			
			if (a_elm.hasClass("torna")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.torna();
				
			} else if (a_elm.hasClass("guarda")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.guarda();
				
			} else if (a_elm.hasClass("previsualitza")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.previsualitza();
				
			} else if (a_elm.hasClass("elimina")) {
					
				// missatge
				Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtItemEliminar, funcio: function() { Detall.elimina(); }});
				
			} else if (a_elm.hasClass("publica")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				$("#item_data_publicacio").val(txtImmediat);
				$("#item_data_caducitat").val("");
				
				Detall.guarda();
				
			}
		
		}*/
		
	},
	nou: function() {
		
		//escriptori_detall_elm.find("a.elimina, a.previsualitza").hide().end().find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
        escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");
		
		tra_seleccionats_elm = escriptori_detall_elm.find("div.modulTramits div.seleccionats");
		tra_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtTramitNouProcediment + ".").end().find("p.gestiona").hide();
		
		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		
		norma_seleccionats_elm = escriptori_detall_elm.find("div.modulNormativa div.seleccionats");
		norma_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaNormativa + ".");
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
		$("#item_data_publicacio").val(txtImmediat);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		
        $("#modulLateral li.btnEliminar").hide();
        
		escriptori_contingut_elm.fadeOut(300, function() {
			$("#item_id").val("");
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				escriptori_detall_elm.bind("click", Detall.llansar);
				itemID_ultim = 0;
			});
		});
		
	},
	carregar: function(itemID) {
		
		escriptori_detall_elm.find("a.elimina").show();
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
		escriptori_contingut_elm.fadeOut(300, function() {
			
			codi_carregant = "<div id=\"carregantDetall\"><p class=\"executant\">" + txtCarregantDetall + "</p></div>";
			escriptori_elm.append(codi_carregant).slideDown(300, function() {
				
				dataVars = "accio=carregar" + "&id=" + itemID;
				
				// ajax
				$.ajax({
					type: "POST",
					url: pagDetall,
					data: dataVars,
					dataType: "json",
					error: function() {
						
						// missatge
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						// error
						Error.llansar();
						
					},
					success: function(data) {
						// estat json
						/*
						json_estat = data.json.estat;
						json_mode = (json_estat == "CORRECTE") ? "correcte" : (json_estat == "WARNING") ? "atencio" : (json_estat == "ERROR") ? "error" : "fatal";
						if (json_estat == "FATAL") {
							
							Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge, funcio: function() { document.location = pagTancarAplicacio; }});
							$("#contenidor").html("");
							return false;
						
						} else if (json_estat == "ERROR") {
							
							// missatge
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + data.json.missatge + "</p>"});
							// error
							Error.llansar();
						
						} else {
							*/
							Detall.pintar(data);
							/*
							// missatge?
							if (data.json.missatge != "") {
								Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge});
							}
						}
						*/
					}
				});
			
			});
			
		});
		
	},
	pintar: function(dades) {
		
		escriptori_detall_elm.find("a.elimina, a.previsualitza").show().end().find("h2:first").text(txtDetallTitol);
		
		dada_node = dades;

		$("#item_id").val(dada_node.item_id);
		
		// Bloque de pestanyas de idiomas
		$("#item_nom_ca").val(printStringFromNull(dada_node.ca.nombre));
		$("#item_objecte_ca").val(printStringFromNull(dada_node.ca.resumen));
		$("#item_destinataris_ca").val(printStringFromNull(dada_node.ca.destinatarios));
		$("#item_requisits_ca").val(printStringFromNull(dada_node.ca.requisitos));
		$("#item_presentacio_ca").val(printStringFromNull(dada_node.ca.plazos));
		$("#item_resolucio_ca").val(printStringFromNull(dada_node.ca.resolucion));
		$("#item_notificacio_ca").val(printStringFromNull(dada_node.ca.notificacion));
		$("#item_lloc_ca").val(printStringFromNull(dada_node.ca.lugar));
		$("#item_silenci_ca").val(printStringFromNull(dada_node.ca.silencio));
		$("#item_observacions_ca").val(printStringFromNull(dada_node.ca.observaciones));
		
		$("#item_nom_es").val(printStringFromNull(dada_node.es.nombre));
		$("#item_objecte_es").val(printStringFromNull(dada_node.es.resumen));
		$("#item_destinataris_es").val(printStringFromNull(dada_node.es.destinatarios));
		$("#item_requisits_es").val(printStringFromNull(dada_node.es.requisitos));
		$("#item_presentacio_es").val(printStringFromNull(dada_node.es.plazos));
		$("#item_resolucio_es").val(printStringFromNull(dada_node.es.resolucion));
		$("#item_notificacio_es").val(printStringFromNull(dada_node.es.notificacion));
		$("#item_lloc_es").val(printStringFromNull(dada_node.es.lugar));
		$("#item_silenci_es").val(printStringFromNull(dada_node.es.silencio));
		$("#item_observacions_es").val(printStringFromNull(dada_node.es.observaciones));
		
		$("#item_nom_en").val(printStringFromNull(dada_node.en.nombre));
		$("#item_objecte_en").val(printStringFromNull(dada_node.en.resumen));
		$("#item_destinataris_en").val(printStringFromNull(dada_node.en.destinatarios));
		$("#item_requisits_en").val(printStringFromNull(dada_node.en.requisitos));
		$("#item_presentacio_en").val(printStringFromNull(dada_node.en.plazos));
		$("#item_resolucio_en").val(printStringFromNull(dada_node.en.resolucion));
		$("#item_notificacio_en").val(printStringFromNull(dada_node.en.notificacion));
		$("#item_lloc_en").val(printStringFromNull(dada_node.en.lugar));
		$("#item_silenci_en").val(printStringFromNull(dada_node.en.silencio));
		$("#item_observacions_en").val(printStringFromNull(dada_node.en.observaciones));
		
		$("#item_nom_de").val(printStringFromNull(dada_node.de.nombre));
		$("#item_objecte_de").val(printStringFromNull(dada_node.de.resumen));
		$("#item_destinataris_de").val(printStringFromNull(dada_node.de.destinatarios));
		$("#item_requisits_de").val(printStringFromNull(dada_node.de.requisitos));
		$("#item_presentacio_de").val(printStringFromNull(dada_node.de.plazos));
		$("#item_resolucio_de").val(printStringFromNull(dada_node.de.resolucion));
		$("#item_notificacio_de").val(printStringFromNull(dada_node.de.notificacion));
		$("#item_lloc_de").val(printStringFromNull(dada_node.de.lugar));
		$("#item_silenci_de").val(printStringFromNull(dada_node.de.silencio));
		$("#item_observacions_de").val(printStringFromNull(dada_node.de.observaciones));
		
		$("#item_nom_fr").val(printStringFromNull(dada_node.fr.nombre));
		$("#item_objecte_fr").val(printStringFromNull(dada_node.fr.resumen));
		$("#item_destinataris_fr").val(printStringFromNull(dada_node.fr.destinatarios));
		$("#item_requisits_fr").val(printStringFromNull(dada_node.fr.requisitos));
		$("#item_presentacio_fr").val(printStringFromNull(dada_node.fr.plazos));
		$("#item_resolucio_fr").val(printStringFromNull(dada_node.fr.resolucion));
		$("#item_notificacio_fr").val(printStringFromNull(dada_node.fr.notificacion));
		$("#item_lloc_fr").val(printStringFromNull(dada_node.fr.lugar));
		$("#item_silenci_fr").val(printStringFromNull(dada_node.fr.silencio));
		$("#item_observacions_fr").val(printStringFromNull(dada_node.fr.observaciones));
		// Fin bloque de pestanyas de idiomas
		
		
		marcarOpcionSelect("item_estat", dada_node.item_estat);

		/*
		if (dada_node.caducat == "S") {
			escriptori_detall_elm.find("h2:first").append(", <span class=\"caducat\">" + txtCaducat.toLowerCase() + "</span>");
			$("#modulLateral p.baix:first").removeClass("iPublicat").addClass("iCaducat");
		} else {
			escriptori_detall_elm.find("h2:first span.caducat").remove();
			$("#modulLateral p.baix:first").removeClass("iCaducat").addClass("iPublicat");
		}
		*/
		
		$("#item_data_publicacio").val(dada_node.item_data_publicacio);

		$("#item_data_caducitat").val(dada_node.item_data_caducitat);
		
		$("#item_codi").val(dada_node.item_codi);
		
		if (dada_node.item_iniciacio != undefined) {
			$("#item_iniciacio").val(dada_node.item_iniciacio);
		}
		
		if (dada_node.item_organ_id != undefined) {
			$("#item_organ_id").val(dada_node.item_organ_id);
			$("#item_organ").val(dada_node.item_organ_nom);
		}
		
		if (dada_node.item_familia != undefined) {
			$("#item_familia").val(dada_node.item_familia);
		}
		
		if (dada_node.item_familia_id != undefined) {
			$("#item_familia").val(dada_node.item_familia_nom);
		}
		
		$("#item_tramite").val(dada_node.item_tramite);
		
		if (dada_node.item_version != undefined) {
			$("#item_version").val(dada_node.item_version);
		}
		
		$("#item_url").val(dada_node.item_url);
		
		if (dada_node.item_fi_vida_administrativa != undefined) {
			$('#item_fi_vida_administrativa').attr('checked', dada_node.item_fi_vida_administrativa);
		}
		
		if (dada_node.item_finestreta_unica != undefined) {
			$('#item_finestreta_unica').attr('checked', dada_node.item_finestreta_unica);
		}
		
		if (dada_node.item_taxa != undefined) {
			$('#item_taxa').attr('checked', dada_node.item_taxa);
		}
		
		$("#item_notes").val(dada_node.item_notes);
/*		
		// documents
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_nodes = dada_node.documents;
		doc_nodes_size = doc_nodes.length;
		
		if (doc_nodes_size == 0) {
			doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");
		} else {
			
			num_ca = 0;
			num_es = 0;
			num_en = 0;
			num_de = 0;
			num_fr = 0;
			
			codi_doc_ca = "<ul>";
			codi_doc_es = "<ul>";
			codi_doc_en = "<ul>";
			codi_doc_de = "<ul>";
			codi_doc_fr = "<ul>";
			
			$(doc_nodes).each(function() {
				doc_node = this;
				
				codi_doc = "<li>";
				codi_doc += "<div>";
				codi_doc += "<span class=\"doc\"><input type=\"hidden\" value=\"" + doc_node.id + "\" /><a href=\"#\">" + doc_node.nom + "</a></span>";
				codi_doc += "<a class=\"btn lleva\" href=\"javascript:;\"><span><span>" + txtLleva + "</span></span></a>";
				codi_doc += "</div>";
				codi_doc += "</li>";
				
				if (doc_node.idioma == "ca") {
					codi_doc_ca += codi_doc;
					num_ca++;
				} else if (doc_node.idioma == "es") {
					codi_doc_es += codi_doc;
					num_es++;
				} else if (doc_node.idioma == "en") {
					codi_doc_en += codi_doc;
					num_en++;
				} else if (doc_node.idioma == "de") {
					codi_doc_de += codi_doc;
					num_de++;
				} else if (doc_node.idioma == "fr") {
					codi_doc_fr += codi_doc;
					num_fr++;
				}
				
			});
			
			codi_doc_ca += "</ul>";
			codi_doc_es += "</ul>";
			codi_doc_en += "</ul>";
			codi_doc_de += "</ul>";
			codi_doc_fr += "</ul>";
			
			if (num_ca == 0) {
				doc_seleccionats_elm.find("div.ca ul").remove().end().find("div.ca p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_ca == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.ca ul").remove().end().find("div.ca p.info").html(txtHiHa + " <strong>" + num_ca + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_ca);
			}
			
			if (num_es == 0) {
				doc_seleccionats_elm.find("div.es ul").remove().end().find("div.es p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_es == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.es ul").remove().end().find("div.es p.info").html(txtHiHa + " <strong>" + num_es + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_es);
			}
			
			if (num_en == 0) {
				doc_seleccionats_elm.find("div.en ul").remove().end().find("div.en p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_en == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.en ul").remove().end().find("div.en p.info").html(txtHiHa + " <strong>" + num_en + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_en);
			}
			
			if (num_de == 0) {
				doc_seleccionats_elm.find("div.de ul").remove().end().find("div.de p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_de == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.de ul").remove().end().find("div.de p.info").html(txtHiHa + " <strong>" + num_de + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_de);
			}
			
			if (num_fr == 0) {
				doc_seleccionats_elm.find("div.fr ul").remove().end().find("div.fr p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_de == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.fr ul").remove().end().find("div.fr p.info").html(txtHiHa + " <strong>" + num_fr + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_fr);
			}
			
			if (doc_nodes_size > 1) {
				doc_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			}
		}
		
		// tramits
		tra_seleccionats_elm = escriptori_detall_elm.find("div.modulTramits div.seleccionats");
		tra_nodes = dada_node.tramits;
		tra_nodes_size = tra_nodes.length;
		
		if (tra_nodes_size == 0) {
			tra_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaTramits + ".").end().find("p.gestiona").show();
		} else {
			codi_tramits = "<ul>";
			$(tra_nodes).each(function() {
				tramit_node = this;
				codi_tramits += "<li><input type=\"hidden\" value=\"" + tramit_node.id + "\" />" + tramit_node.nom + "</li>";
			});
			codi_tramits += "<ul>";
			txt_tramits = (tra_nodes_size == 1) ? txtTramit : txtTramits;
			tra_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + tra_nodes_size + " " + txt_tramits.toLowerCase() + "</strong>.").after(codi_tramits).end().find("p.gestiona").show();
			if (tra_nodes_size > 1) {
				tra_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			}
		}
		
		// materies
		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
		materies_nodes = dada_node.materies;
		materes_nodes_size = materies_nodes.length;
		
		mat_llistat_elm.find("input").removeAttr("checked");
		
		if (materes_nodes_size == 0) {
			mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		} else {
			codi_materies = "<ul>";
			$(materies_nodes).each(function() {
				materia_node = this;
				codi_materies += "<li><input type=\"hidden\" value=\"" + materia_node.id + "\" />" + materia_node.nom + "</li>";
				mat_llistat_elm.find("input[value=" + materia_node.id + "]").attr("checked","checked");
			});
			codi_materies += "<ul>";
			txt_materies = (materes_nodes_size == 1) ? txtMateria : txtMateries;
			mat_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + materes_nodes_size + " " + txt_materies + "</strong>.").after(codi_materies);
		}
		
		// normatives
		norma_seleccionats_elm = escriptori_detall_elm.find("div.modulNormativa div.seleccionats");
		normatives_nodes = dada_node.normatives;
		normatives_nodes_size = normatives_nodes.length;
		
		if (normatives_nodes_size == 0) {
			norma_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		} else {
			codi_normatives = "<ul>";
			$(normatives_nodes).each(function() {
				normativa_node = this;
				codi_normatives += "<li><input type=\"hidden\" value=\"" + normativa_node.id + "\" />" + normativa_node.nom + "</li>";
			});
			codi_normatives += "<ul>";
			txt_materies = (normatives_nodes_size == 1) ? txtNormativa : txtNormatives;
			norma_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + normatives_nodes_size + " " + txt_materies.toLowerCase() + "</strong>.").after(codi_normatives);
			if (normatives_nodes_size > 1) {
				norma_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			}
		}
*/		
		
        // mostrem
        
        $("#modulLateral li.btnEliminar").show();
        
		if ($("#carregantDetall").size() > 0) {
			$("#carregantDetall").fadeOut(300, function() {
				$(this).remove();
				
				// array
				Detall.array({id: dada_node.item_id, accio: "guarda", dades: dada_node});

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
		
		item_ID = $("#item_id").val();
		
		dataVars = "id=" + item_ID;
				
		// ajax
		$.ajax({
			type: "POST",
			url: pagEsborrar,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {

				Llistat.anulaCache();
			
				Missatge.cancelar();
				
				if (data.id > 0) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermissos});
				} else if (data.id == -2){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
								
				// array
				Detall.array({id: dada_node.item_id, accio: "elimina"});
				
				// recarregar
				Detall.recarregar();

			}
		});
	},
	/*guarda: function() {
		
		// form comprobar
//		FormulariComprovar.llansar();
//		
//		if (!formComprovacio) {
//			return false;
//		}
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		*/
		/*
		//item_ID = $("#tipusUnitat_id").val();
		item_ID = $("#item_id").val();
		
		// json
		json_str = "{";
		
		json_str += "\"id\": \"" + item_ID + "\",";
		
		json_str += "\"publicacio\": \"" + $("#item_data_publicacio").val() + "\",";
		json_str += "\"caducitat\": \"" + $("#item_data_caducitat").val() + "\",";
		json_str += "\"validacio\": \"" + $("#item_validacio").val() + "\",";
		
		json_str += "\"codi\": \"" + $("#item_codi").val() + "\",";
		json_str += "\"organ\": \"" + $("#item_organ").val() + "\",";
		json_str += "\"familia\": \"" + $("#item_familia").val() + "\",";
		json_str += "\"iniciacio\": \"" + $("#item_iniciacio").val() + "\",";
		
		
		json_str += "\"notes\": \"" + $("#item_notes").val() + "\",";
		
		json_str += "\"idioma\": [";
		
		idioma_nodes = $("div.idiomes div.idioma");
		idioma_nodes_size = idioma_nodes.size();
		
		$(idioma_nodes).each(function(i) {
			
			idioma_nodo = $(this);
			
			json_str += "{";
				
			idioma_class = idioma_nodo.attr("class");
			idioma_codi = idioma_class.substr(idioma_class.indexOf(" ")+1,2);
			
			json_str += "\"" + idioma_codi + "\": [{";
			
			input_nodes = idioma_nodo.find("input");
			input_nodes_size = input_nodes.size();
			
			$(input_nodes).each(function(j) {
			
				idioma_node = $(this);
			
				json_str += "\"" + idioma_node.attr("id") + "\": \"" + idioma_node.val() + "\"";
				
				if (j != input_nodes_size-1) {
					json_str += ",";
				}
			
			});
			
			json_str += "}]";
			
			json_str += "}";
			
			if (i != idioma_nodes_size-1) {
				json_str += ",";
			}
		
		});
		
		json_str += "]";
		
		json_str += "}";
		
		//alert(json_str);
		//return false;
		
		accio = (item_ID == "") ? "guardar" : "modificar";
		
		dataVars = "accio=" + accio + "&dades=" + json_str;
		*/
		/*
		dataVars = $('#guardarProcediment').serialize();
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagGuardar,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {
				*/
				/*
				// estat json
				json_estat = data.json.estat;
				json_mode = (json_estat == "CORRECTE") ? "correcte" : (json_estat == "WARNING") ? "atencio" : (json_estat == "ERROR") ? "error" : "fatal";
				if (json_estat == "FATAL") {
					
					Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge, funcio: function() { document.location = pagTancarAplicacio; }});
					$("#contenidor").html("");
					return false;
				
				} else if (json_estat == "ERROR") {
					
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + data.json.missatge + "</p>"});
					// error
					Error.llansar();
				
				} else {
					
					// missatge?
					if (data.json.missatge != "") {
						Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge});
					}
					
					// recarregar
					Detall.recarregar();
					
				}
				*/
				/*
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					Detall.recarregar();
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
				}
			}
		});
		
	},
	*/
	
	previsualitza: function() {
		
		escriptori_detall_elm.fadeOut(300, function() {
			
			fitxa_idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			fitxa_ID = escriptori_detall_elm.find("#item_id").val();
			
			previsualitza_url = "http://www.caib.es/govern/sac/visor_proc.do?lang=" + fitxa_idiomaSeleccionat + "&codi=611701"; //+ fitxa_ID;
			
			escriptori_previsualitza_elm.find("iframe").attr("src", previsualitza_url).end().fadeIn(300, function() {
			
				$(this).find("a.dePrevisualitzar").one("click", Detall.previsualitzaTorna);
			
			});
		
		});
		
	},
	previsualitzaTorna: function() {
		
		escriptori_previsualitza_elm.fadeOut(300, function() {
		
			escriptori_detall_elm.fadeIn(300, function() {
				escriptori_detall_elm.bind("click", Detall.llansar);
			});
		
		});
		
	}	
};

