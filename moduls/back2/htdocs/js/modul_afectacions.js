// MODUL NORMATIVA

$(document).ready(function() {
	
	// elements
	modul_afectacions_elm = $("div.modulAfectacions");
	escriptori_afectacions_elm = $("#escriptori_afectacions");
	
	if (modul_afectacions_elm.size() == 1) {
		
		// INICIEM
		ModulAfectacions.iniciar();
		
	}
	
	multipagina_afec = new Multipagina();
	
});

var ModulAfectacions = {
	iniciar: function() {
		
		afectacions_llistat_elm = escriptori_afectacions_elm.find("div.escriptori_items_llistat:first");
		afectacions_cercador_elm = escriptori_afectacions_elm.find("div.escriptori_items_cercador:first");
		afectacions_seleccionats_elm = escriptori_afectacions_elm.find("div.escriptori_items_seleccionats:first");
		
		afectacions_dades_elm = afectacions_llistat_elm.find("div.dades:first");
		
		pagPagina_afec_elm = afectacions_llistat_elm.find("input.pagPagina:first");
		ordreTipus_afec_elm = afectacions_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_afec_elm = afectacions_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_afectacions_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
			if (botonera_elm.hasClass("dalt")) {
				botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			} else {
				botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			}
		});
		
		afectacions_llistat_elm.add(afectacions_seleccionats_elm).css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		afectacions_cercador_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// botonera seleccionats
		afectacions_seleccionats_elm.find("p.botonera").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});

		// one al botÃ³ de gestionar
		modul_afectacions_elm.find("a.gestiona").one("click", ModulAfectacions.gestiona);
		
		$("#afec_cerca_data").mask("99/99/9999");
		$("#afec_cerca_data_butlleti").mask("99/99/9999");
		
	},
	gestiona: function() {
		
		lis_size = modul_afectacions_elm.find("li").size();
		
		if (lis_size > 0) {
			
			codi_seleccionat = "<ul>";
			
			modul_afectacions_elm.find("li").each(function() {
				
				li_elm = $(this);
				
				codi_seleccionat += "<li>";
				codi_seleccionat += "<div class=\"afectacio\">";
				codi_seleccionat += "<input type=\"hidden\" value=\"" + li_elm.find("input.norma").val() + "\" class=\"norma\" />";
				codi_seleccionat += "<span class=\"afectacio\">";
				codi_seleccionat += "<select>";
				afectacio_ID = li_elm.find("input.afectacio").val();
				$(Afectacions_arr).each(function(i) {
					codi_selected = (this.id == afectacio_ID) ? "selected=\"selected\"" : "";
					codi_seleccionat += "<option value=\"" + this.id + "\" " + codi_selected + ">" + this.nom + "</option>";
				});
				codi_seleccionat += "</select>";
				codi_seleccionat += "<br />";
				codi_seleccionat += ", " + txtAmbLaNorma + " <em>" + li_elm.find("em").text() + "</em>";
				codi_seleccionat += "</span>";
				codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
				codi_seleccionat += "</div>";
				codi_seleccionat += "</li>";
			
			});
			
			codi_seleccionat += "</ul>";
			
			afectacions_seleccionats_elm.find("ul").remove().end().append(codi_seleccionat);
			
			EscriptoriAfectacions.contaSeleccionats();
			
			//Si cambia el valor del tipo de afectación activar el botón guardar de la pantalla de detalle
			$("#escriptori_afectacions span.afectacio select").one("change", Detall.modificado);
			
		} else {
			
			afectacions_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaAfectacionsSeleccionats + ".");
			
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			escriptori_afectacions_elm.fadeIn(300, function() {
				// activar
				escriptori_afectacions_elm.bind("click",EscriptoriAfectacions.llansar);
			});
			
		});
		
	}
};

var EscriptoriAfectacions = {
	carregar: function(opcions) {
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		
		dataVars = "";
		
		// cercador
		/*
		dataVars_cercador = "&titol=" + $("#cerca_normativa_titol").val();
		dataVars_cercador += "&codi=" + $("#cerca_normativa_codi").val();
		dataVars_cercador += "&noRelacionades=" + $("#cerca_normativa_no_relacionades").val();
		dataVars_cercador += "&idUA=" + $("#cerca_ua_id").val();
		*/
		
		// cercador
		dataVars_cercador = "";		
		dataVars_cercador += "&data=" + $("#afec_cerca_data").val();			
		dataVars_cercador += "&titol=" + $("#afec_cerca_normativa_titol").val();
		dataVars_cercador += "&data_butlleti=" + $("#afec_cerca_data_butlleti").val();			
		
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_afec_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_afec_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_afec_elm.val(),10);		
			
		// ordre
		ordre_Tipus = ordreTipus_afec_elm.val();
		ordre_Camp = ordreCamp_afec_elm.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagNormativa,
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
				*/
					// total
					//resultats_total = parseInt(data.json.data.total,10);
					resultats_total = data.total;
					
					if (resultats_total > 0) {
						
						txtT = (resultats_total > 1) ? txtNormatives : txtNormativa;
						
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
						ordre_c1 = (ordre_C == "titol") ? " " + ordre_T : "";
						ordre_c2 = (ordre_C == "numero") ? " " + ordre_T : "";
						ordre_c3 = (ordre_C == "data") ? " " + ordre_T : "";
						
						txt_ordenacio = "";
						
						if (resultats_total > 1) {
						
							txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
							
							if (ordre_C == "titol") {
								txt_per = txtNormativa;
							} else if (ordre_C == "numero") {
								txt_per = txtNumero;
							} else {
								txt_per = txtData;
							}
							
							txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
						
						}
						
						codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
						
						/*
						codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtNormativa + "</a></div>";
						codi_cap2 = "<div class=\"th numero" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtNumero + "</a></div>";
						codi_cap3 = "<div class=\"th data" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtData + "</a></div>";
						*/
						
						codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\">" + txtNormativa + "</div>";
						codi_cap2 = "<div class=\"th numero" + ordre_c2 + "\" role=\"columnheader\">" + txtNumero + "</div>";
						codi_cap3 = "<div class=\"th data" + ordre_c3 + "\" role=\"columnheader\">" + txtData + "</div>";						
						
						// codi taula
						codi_taula = "<div class=\"table llistat normativa\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
						
						// codi cap + cuerpo
						codi_taula += "<div class=\"thead\">";
						codi_taula += "<div class=\"tr\" role=\"rowheader\">";
						codi_taula += codi_cap1 + codi_cap2 + codi_cap3;
						codi_taula += "</div>";
						codi_taula += "</div>";
						codi_taula += "<div class=\"tbody\">";
						
						// codi cuerpo
						//$(data.json.data.nodes).each(function(i) {
						$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
							dada_node = this;
							parClass = (i%2) ? " par": "";
							
							codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
							
							codi_taula += "<div class=\"td titol\" role=\"gridcell\">";
							codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
							codi_taula += "<a href=\"javascript:;\" class=\"titol\">" + dada_node.titulo + "</a>";
							codi_taula += "</div>";
							
							codi_taula += "<div class=\"td numero\" role=\"gridcell\">" + dada_node.numero + "</div>";
							codi_taula += "<div class=\"td data\" role=\"gridcell\">" + dada_node.fecha + "</div>";
							
							codi_taula += "</div>";
						});
						
						codi_taula += "</div>";
						codi_taula += "</div>";
						
						if($.browser.opera) {
							escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
						}
						
						
						// Instanciamos el navegador multipágina.					
						multipagina_afec.init({
							total: resultats_total,
							itemsPorPagina: pag_Res,
							paginaActual: pag_Pag,
							funcionPagina: "EscriptoriAfectacions.anar"
						});					
						
						codi_navegacio = multipagina_afec.getHtml();						
						
						// codi final
						codi_final = codi_totals + codi_taula + codi_navegacio;
					
					} else {
						
						// no hi ha items
						codi_final = "<p class=\"noItems\">" + txtNoHiHaNormativa + ".</p>";
						
					}
					
					// animacio
					afectacions_dades_elm.fadeOut(300, function() {
						// pintem
						afectacions_dades_elm.html(codi_final).fadeIn(300, function() {
						
							// events
							escriptori_afectacions_elm.bind("click",EscriptoriAfectacions.llansar);
							
							// cercador
							afectacions_cercador_elm.find("input, select").removeAttr("disabled");
							
						});
					});
					
					// missatge?
					if (data.missatge) {
						Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.missatge});
					}
					
				//}
			
			}
		});
	
	},
	llansar: function(e) {
		
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			escriptori_afectacions_elm.unbind("click",EscriptoriAfectacions.llansar);
				
			// llancem
			pare_elm = elm.parent();
			
			if (pare_elm.is("LI") && pare_elm.hasClass("opcio")) {
				
				// opcions pestanya
				EscriptoriAfectacions.opcions(elm);
				
			} else if (pare_elm.hasClass("th")) {
				
				// ordenacio
				if (pare_elm.hasClass("ASC")) {
					ordreTipus_afec_elm.val("DESC");
				} else if (pare_elm.hasClass("DESC")) {
					ordreTipus_afec_elm.val("ASC");
				} else {
					pare_class = pare_elm.attr("class");
					c = pare_class.substr(pare_class.indexOf(" ")+1);
					ordreCamp_afec_elm.val(c);
				}
				
				// animacio
				afectacions_dades_elm.fadeOut(300, function() {
					// pintem
					codi_ordre = "<p class=\"executant\">" + txtCarregantItems + "</p>";
					afectacions_dades_elm.html(codi_ordre).fadeIn(300, function() {
						
						EscriptoriAfectacions.carregar({});
						
					});
				});
				
			} else if (pare_elm.is("P") && pare_elm.attr("class") == "paginacio") {
				
				pag_Pag = pagPagina_afec_elm.val();
				enlace_html = elm.html();
				EscriptoriAfectacions.anar(enlace_html);
				
			} else if (elm.hasClass("titol")) {
				
				norma_id = elm.parent().find("input.id").val();
				norma_titol = elm.html();
				
				lis_size = afectacions_seleccionats_elm.find("li").size();
				
				norma_esta = false;
				
				if (lis_size == 0) {
					
					$("<ul>").appendTo(afectacions_seleccionats_elm);
					
				} else {
					
					afectacions_seleccionats_elm.find("input").each(function() {
						
						if ($(this).val() == norma_id) {
							norma_esta = true;
						}
					
					});
					
				}
				
				if (!norma_esta) {
					
					codi_seleccionat = "<li>";
					codi_seleccionat += "<div class=\"afectacio\">";
					codi_seleccionat += "<input type=\"hidden\" value=\"" + norma_id + "\" class=\"norma\" />";
					codi_seleccionat += "<span class=\"afectacio\">";
					codi_seleccionat += "<select>";
					$(Afectacions_arr).each(function(i) {
						codi_selected = (i == 0) ? "selected=\"selected\"" : "";
						codi_seleccionat += "<option value=\"" + this.id + "\" " + codi_selected + ">" + this.nom + "</option>";
					});
					codi_seleccionat += "</select>";
					codi_seleccionat += "<br />";
					codi_seleccionat += ", " + txtAmbLaNorma + " <em>" + norma_titol + "</em>";
					codi_seleccionat += "</span>";
					codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
					codi_seleccionat += "</div>";
					codi_seleccionat += "</li>";
					
					afectacions_seleccionats_elm.find("ul").append(codi_seleccionat);
					
					EscriptoriAfectacions.contaSeleccionats();
					
					Detall.modificado();
				
				}
				
				escriptori_afectacions_elm.bind("click",EscriptoriAfectacions.llansar);
				
			}
			
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a.btn:first");
			
			if (a_elm.hasClass("torna")) {
				
				escriptori_afectacions_elm.unbind("click",EscriptoriAfectacions.llansar);
				
				EscriptoriAfectacions.torna();
				
			} else if (a_elm.hasClass("elimina")) {
				
				a_elm.parents("li:first").remove();
				
				EscriptoriAfectacions.contaSeleccionats();
				
				Detall.modificado();
				
			} else if (a_elm.hasClass("finalitza")) {
				
				escriptori_afectacions_elm.unbind("click",EscriptoriAfectacions.llansar);
				
				nombre_llistat = 0;
				
				codi_llistat = "<ul>";
				
				afectacions_seleccionats_elm.find("li").each(function(i) {
				
					li_elm = $(this);
					afectacio_select = li_elm.find("select");
					afectacio_select_id = afectacio_select.val();
					afectacio_select_nom = afectacio_select.find("option:selected").text();
					
					codi_llistat += "<li>";
					codi_llistat += "<input type=\"hidden\" value=\"" + afectacio_select_id + "\" class=\"afectacio\" />";
					codi_llistat += "<input type=\"hidden\" value=\"" + li_elm.find("input.norma").val() + "\" class=\"norma\" />";
					codi_llistat += afectacio_select_nom + ", " + txtAmbLaNorma + "  <em>" + li_elm.find("em").text() + "</em>";
					codi_llistat += "</li>";
					
					nombre_llistat++;
					
				});
				
				codi_llistat += "</ul>";
				
				codi_afectacions_txt = (nombre_llistat == 1) ? txtNormativa : txtNormatives;
				codi_info = (nombre_llistat == 0) ? txtNoHiHaAfectacions + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_afectacions_txt.toLowerCase() + "</strong>.";
				
				modul_afectacions_elm.find("ul").remove().end().find("p.info").html(codi_info).after(codi_llistat);
				/*
				if (nombre_llistat > 1) {
					modul_afectacions_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
				}
				*/
				EscriptoriAfectacions.torna();
				
			} else if (a_elm.hasClass("consulta")) {								
				
				// desactivem taula
				escriptori_afectacions_elm.unbind("click",EscriptoriAfectacions.llansar);
				
				afectacions_cercador_elm.find("input, select").attr("disabled", "disabled");
				
				// animacio
				afectacions_dades_elm.fadeOut(300, function() {
					// pintem
					codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
					afectacions_dades_elm.html(codi_cercant).fadeIn(300, function() {
					
						pagPagina_afec_elm.val(0);
						// events taula
						EscriptoriAfectacions.carregar({});
						
					});
				});
				
			}
			
		}
		
	},
	anar: function(enlace_html) {
		
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
		txt = (num <= pag_Pag) ? txtCercantItemsAnteriors : txtCercantItemsAnteriors;
		afectacions_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			afectacions_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_afec_elm.val(num-1);
				
				// llancem!
				EscriptoriAfectacions.carregar({pagina: num-1});
				
			});
		});
		
	},
	torna: function() {
		
		// animacio
		escriptori_afectacions_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_afectacions_elm.find("a.gestiona").one("click", ModulAfectacions.gestiona);
			});
			
		});
		
	},
	contaSeleccionats: function() {
		
		seleccionats_val = afectacions_seleccionats_elm.find("li").size();
		info_elm = afectacions_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			afectacions_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaAfectacionsSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtAfectacio.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtAfectacions.toLowerCase() + "</strong>.");
			//afectacions_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			
		}
		
	}
};
