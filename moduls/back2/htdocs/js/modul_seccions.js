// MODUL PROCEDIMENTS

$(document).ready(function() {
	
	// elements
	modul_seccions_elm = $("div.modulSeccions:first");
	escriptori_seccions_elm = $("#escriptori_seccions");
	
	if (modul_seccions_elm.size() == 1) {
		
		// INICIEM
		ModulSeccio.iniciar();
		
	}
	
});

var ModulSeccio = {
	iniciar: function() {
		
		seccions_llistat_elm = escriptori_seccions_elm.find("div.escriptori_items_llistat:first");
		seccions_cercador_elm = escriptori_seccions_elm.find("div.escriptori_items_cercador:first");
		seccions_seleccionats_elm = escriptori_seccions_elm.find("div.escriptori_items_seleccionats:first");
		
		seccions_dades_elm = seccions_llistat_elm.find("div.dades:first");
		
		pagPagina_seccio_elm = seccions_llistat_elm.find("input.pagPagina:first");
		ordreTipus_seccio_elm = seccions_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_seccio_elm = seccions_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_seccions_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
			if (botonera_elm.hasClass("dalt")) {
				botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			} else {
				botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			}
		});
		
		seccions_llistat_elm.add(seccions_seleccionats_elm).css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		seccions_cercador_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// botonera seleccionats
		seccions_seleccionats_elm.find("p.botonera").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// enllaços
		modul_seccions_elm.bind("click", ModulSeccio.cerca);
		
	},
	cerca: function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			seccio_ID = elm.find("input").val();
			
			// select cercador
			codi_select_seccions = "<select id=\"cerca_fixta_seccio\" name=\"cerca_fixta_seccio\">";
			modul_seccions_elm.find("a").each(function() {
				a_node = $(this);
				a_node_val = a_node.find("input").val();
				codi_selected = (a_node_val == seccio_ID) ? " selected=\"selected\"" : "";
				codi_select_seccions += "<option value=\"" + a_node_val + "\"" + codi_selected + ">" + a_node.text() + "</value>";
			});
			codi_select_seccions += "</select>";
			
			seccions_llistat_elm.find("label.cerca_fixta_seccio").attr("label","cerca_fixta_seccio").parents("div.element:first").find("div.control:first").html(codi_select_seccions);
			
			// animacio
			escriptori_detall_elm.fadeOut(300, function() {
				
				seccions_cercador_elm.find("input, select").attr("disabled", "disabled");
				
				escriptori_seccions_elm.fadeIn(300, function() {
					
					codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
					seccions_dades_elm.html(codi_cercant);
					
					EscriptoriSeccio.carregar({});
					
					// activar
					//escriptori_seccions_elm.bind("click",EscriptoriSeccio.llansar);
					
				});
				
			});
		
		}
		
	}
};

var EscriptoriSeccio = {
	carregar: function(opcions) {
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		
		dataVars = "";
		
		// cercador
		dataVars_cercador = "&titol=" + $("#cerca_fixta_titol").val();
		dataVars_cercador += "&codi=" + $("#cerca_fixta_codi").val();
		dataVars_cercador += "&seccio=" + $("#cerca_fixta_seccio").val();
			
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_seccio_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_seccio_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_seccio_elm.val(),10);
			
		// ordre
		ordre_Tipus = ordreTipus_seccio_elm.val();
		ordre_Camp = ordreCamp_seccio_elm.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagFitxes,
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
					
					// total
					resultats_total = parseInt(data.json.data.total,10);
					
					if (resultats_total > 0) {
						
						txtT = (resultats_total > 1) ? txtFitxes : txtFitxa;
						
						resultatInici = ((pag_Pag*pag_Res)+1);
						resultatFinal = ((pag_Pag*pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag*pag_Res) + pag_Res;
						
						// ordenacio
						ordre_T = ordre_Tipus;
						ordre_C = ordre_Camp;
						ordre_c1 = (ordre_C == "fitxa") ? " " + ordre_T : "";
						ordre_c2 = (ordre_C == "ordre") ? " " + ordre_T : "";
						ordre_c3 = (ordre_C == "publicacio") ? " " + ordre_T : "";
						ordre_c4 = (ordre_C == "caducitat") ? " " + ordre_T : "";
						
						txt_ordenacio = "";
						
						if (resultats_total > 1) {
						
							txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
							
							if (ordre_C == "fitxa") {
								txt_per = txtFitxa;
							} else if (ordre_C == "ordre") {
								txt_per = txtOrdre;
							} else if (ordre_C == "publicacio") {
								txt_per = txtPublicacio;
							} else {
								txt_per = txtCaducitat;
							}
							
							txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
						
						}
						
						codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

						codi_cap1 = "<div class=\"th fitxa" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtFitxa + "</a></div>";
						codi_cap2 = "<div class=\"th ordre" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtOrdre + "</a></div>";
						codi_cap3 = "<div class=\"th publicacio" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
						codi_cap4 = "<div class=\"th caducitat" + ordre_c4 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
						
						// codi taula
						codi_taula = "<div class=\"table llistat fitxes\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
						
						// codi cap + cuerpo
						codi_taula += "<div class=\"thead\">";
						codi_taula += "<div class=\"tr\" role=\"rowheader\">";
						codi_taula += codi_cap1 + codi_cap2 + codi_cap3 + codi_cap4;
						codi_taula += "</div>";
						codi_taula += "</div>";
						codi_taula += "<div class=\"tbody\">";
						
						// codi cuerpo
						$(data.json.data.nodes).each(function(i) {
							dada_node = this;
							parClass = (i%2) ? " par": "";
							
							codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
							
							codi_taula += "<div class=\"td fitxa\" role=\"gridcell\">";
							codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
							codi_taula += "<a href=\"javascript:;\" class=\"fitxa\">" + dada_node.titol + "</a>";
							codi_taula += "</div>";
							
							codi_taula += "<div class=\"td ordre\" role=\"gridcell\"><input type=\"text\" value=\"" + dada_node.ordre + "\" size=\"3\" maxlength=\"3\" class=\"id\" /></div>";
							
							caducat_class = (dada_node.caducat == "S") ? " caducat" : "";
							codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + dada_node.publicacio + "</div>";
							codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + dada_node.caducitat + "</div>";
							
							codi_taula += "</div>";
						});
						
						codi_taula += "</div>";
						codi_taula += "</div>";
						
						if($.browser.opera) {
							escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
						}
						
						// paginacio
						if (resultats_total > (pag_Res * (pag_Pag+1)) || pag_Pag > 0) {
							// inici, final
							inici_esta = false;
							final_esta = false;
							// anterior
							codi_anteriors = "";
							if (pag_Pag > 0) {
								codi_anteriors = "&lt; <a href=\"javascript:;\" class=\"anteriors\">" + txtAnteriors + "</a> - ";
							}
							// num pagines
							codi_paginas = "";
							paginasNum = Math.ceil(resultats_total/pag_Res);
							if (pag_Pag - paginacio_marge >= 0) {
								codi_paginas += "... ";
							}
							for (i=0; i<paginasNum; i++) {
								if (i > pag_Pag - paginacio_marge && i < pag_Pag + paginacio_marge) {
									codi_paginas += (i == pag_Pag) ? (i+1) : "<a href=\"javascript:;\">" + (i+1) + "</a>";
									codi_paginas += (i == paginasNum-1) ? "" : ", ";
									if (i+1 == 1) {
										inici_esta = true;
									}
									if (i+1 == paginasNum) {
										final_esta = true;
									}
								}
							}
							if (pag_Pag + paginacio_marge < paginasNum) {
								codi_paginas += " ...";
							}
							codi_paginas_todo = "[ " + txtPagines + ": " + codi_paginas + " ]";
							// siguiente
							codi_seguents = "";
							if (resultats_total > (pag_Res * (pag_Pag+1))) {
								codi_seguents = " - <a href=\"javascript:;\" class=\"seguents\">" + txtSeguents + "</a> &gt;";
							}
							// inici
							if (!inici_esta) {
								codi_anteriors = "&lt; <a href=\"javascript:;\" class=\"inici\">" + txtInici + "</a> - " + codi_anteriors;
							}
							// final
							if (!final_esta) {
								codi_seguents = codi_seguents + " - <a href=\"javascript:;\" class=\"final\">" + txtFinal + "</a> &gt;";
							}
							// pintamos
							codi_navegacio = "<p class=\"paginacio\" role=\"navigation\">" + codi_anteriors + codi_paginas_todo + codi_seguents + "</p>";
						} else {
							codi_navegacio = "";
						}
						
						// codi final
						codi_final = codi_totals + codi_taula + codi_navegacio;
					
					} else {
						
						// no hi ha items
						codi_final = "<p class=\"noItems\">" + txtNoHiHaFitxes + ".</p>";
						
					}
					
					// animacio
					seccions_dades_elm.fadeOut(300, function() {
						// pintem
						seccions_dades_elm.html(codi_final).fadeIn(300, function() {
						
							// events
							escriptori_seccions_elm.bind("click",EscriptoriSeccio.llansar);
							
							// cercador
							seccions_cercador_elm.find("input, select").removeAttr("disabled");
							
						});
					});
					
					// missatge?
					if (data.json.missatge != "") {
						Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge});
					}
					
				}
			
			}
		});
	
	},
	llansar: function(e) {
		
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			escriptori_seccions_elm.unbind("click",EscriptoriSeccio.llansar);
				
			// llancem
			pare_elm = elm.parent();
			
			if (pare_elm.is("LI") && pare_elm.hasClass("opcio")) {
				
				// opcions pestanya
				EscriptoriSeccio.opcions(elm);
				
			} else if (pare_elm.hasClass("th")) {
				
				// ordenacio
				if (pare_elm.hasClass("ASC")) {
					ordreTipus_seccio_elm.val("DESC");
				} else if (pare_elm.hasClass("DESC")) {
					ordreTipus_seccio_elm.val("ASC");
				} else {
					pare_class = pare_elm.attr("class");
					c = pare_class.substr(pare_class.indexOf(" ")+1);
					ordreCamp_seccio_elm.val(c);
				}
				
				// animacio
				seccions_dades_elm.fadeOut(300, function() {
					// pintem
					codi_ordre = "<p class=\"executant\">" + txtCarregantItems + "</p>";
					seccions_dades_elm.html(codi_ordre).fadeIn(300, function() {
						
						EscriptoriSeccio.carregar({});
						
					});
				});
				
			} else if (pare_elm.is("P") && pare_elm.attr("class") == "paginacio") {
				
				pag_Pag = pagPagina_seccio_elm.val();
				enlace_html = elm.html();
				EscriptoriSeccio.anar(enlace_html);
				
			} else if (elm.hasClass("nom")) {
				
				procediment_id = elm.parent().find("input.id").val();
				procediment_titol = elm.html();
				
				lis_size = seccions_seleccionats_elm.find("li").size();
				
				procediment_esta = false;
				
				if (lis_size == 0) {
					
					$("<ul>").appendTo(seccions_seleccionats_elm);
					
				} else {
					
					seccions_seleccionats_elm.find("input").each(function() {
						
						if ($(this).val() == procediment_id) {
							procediment_esta = true;
						}
					
					});
					
				}
				
				if (!procediment_esta) {
				
					codi_seleccionat = "<li>";
					codi_seleccionat += "<div class=\"procediment\">";
					codi_seleccionat += "<input type=\"hidden\" value=\"" + procediment_id + "\" />";
					codi_seleccionat += "<span class=\"procediment\">" + procediment_titol + "</span>";
					codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
					codi_seleccionat += "</div>";
					codi_seleccionat += "</li>";
					
					seccions_seleccionats_elm.find("ul").append(codi_seleccionat);
					
					EscriptoriSeccio.contaSeleccionats();
				
				}
				
				escriptori_seccions_elm.bind("click",EscriptoriSeccio.llansar);
				
			}
			
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a.btn:first");
			
			if (a_elm.hasClass("torna")) {
				
				escriptori_seccions_elm.unbind("click",EscriptoriSeccio.llansar);
				
				EscriptoriSeccio.torna();
				
			} else if (a_elm.hasClass("elimina")) {
				
				a_elm.parents("li:first").remove();
				
				EscriptoriSeccio.contaSeleccionats();
				
				
			} else if (a_elm.hasClass("finalitza")) {
				
				escriptori_seccions_elm.unbind("click",EscriptoriSeccio.llansar);
				
				nombre_llistat = 0;
				
				codi_llistat = "<ul>";
				
				seccions_seleccionats_elm.find("li").each(function(i) {
				
					li_elm = $(this);
					input_elm = li_elm.find("input");
					codi_llistat += "<li><input type=\"hidden\" value=\"" + li_elm.find("input").val() + "\" />" + li_elm.find("span.procediment").text() + "</li>";
					nombre_llistat++;
					
				});
				
				codi_llistat += "</ul>";
				
				codi_seccions_txt = (nombre_llistat == 1) ? txtProcediment : txtProcediments;
				codi_info = (nombre_llistat == 0) ? txtNoHiHaNormatives + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_seccions_txt.toLowerCase() + "</strong>.";
				
				modul_seccions_elm.find("ul").remove().end().find("p.info").html(codi_info).after(codi_llistat);
				
				if (nombre_llistat > 1) {
					modul_seccions_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
				}
				
				EscriptoriSeccio.torna();
				
			} else if (a_elm.hasClass("consulta")) {
				
				// desactivem taula
				escriptori_seccions_elm.unbind("click",EscriptoriSeccio.llansar);
				
				seccions_cercador_elm.find("input, select").attr("disabled", "disabled");
				
				// animacio
				seccions_dades_elm.fadeOut(300, function() {
					// pintem
					codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
					seccions_dades_elm.html(codi_cercant).fadeIn(300, function() {
					
						// events taula
						EscriptoriSeccio.carregar({});
						
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
		seccions_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			seccions_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_seccio_elm.val(num-1);
				
				// llancem!
				EscriptoriSeccio.carregar({pagina: num-1});
				
			});
		});
		
	},
	torna: function() {
		
		// animacio
		escriptori_seccions_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_elm.find("a.gestiona").one("click", ModulSeccio.gestiona);
			});
			
		});
		
	},
	contaSeleccionats: function() {
		
		seleccionats_val = seccions_seleccionats_elm.find("li").size();
		info_elm = seccions_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			seccions_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaProcedimentsSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtProcediment.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtProcediments.toLowerCase() + "</strong>.");
			seccions_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			
		}
		
	}
};
