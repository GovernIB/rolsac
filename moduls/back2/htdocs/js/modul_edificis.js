// MODUL NORMATIVA

$(document).ready(function() {
	
	// elements
	modul_edificis_elm = $("div.modulEdificis:first");
	escriptori_edificis_elm = $("#escriptori_edificis");
	
	if (modul_edificis_elm.size() == 1) {
		
		// INICIEM
		ModulEdifici.iniciar();
		
	}
	
});

//paginacio
var paginacio_marge = 4;

var ModulEdifici = {
	iniciar: function() {
		
		edificis_llistat_elm = escriptori_edificis_elm.find("div.escriptori_items_llistat:first");
		edificis_cercador_elm = escriptori_edificis_elm.find("div.escriptori_items_cercador:first");
		edificis_seleccionats_elm = escriptori_edificis_elm.find("div.escriptori_items_seleccionats:first");
		
		edificis_dades_elm = edificis_llistat_elm.find("div.dades:first");
		
		pagPagina_edifici_elm = edificis_llistat_elm.find("input.pagPagina:first");
		ordreTipus_edifici_elm = edificis_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_edifici_elm = edificis_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_edificis_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
			/*if (botonera_elm.hasClass("dalt")) {
				botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			} else {
				botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			}*/
		});
		
		//edificis_llistat_elm.add(edificis_seleccionats_elm).css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		edificis_llistat_elm.add(edificis_seleccionats_elm);
		
		//edificis_cercador_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// botonera seleccionats
		//edificis_seleccionats_elm.find("p.botonera").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// one al bot� de gestionar
		modul_edificis_elm.find("a.gestiona").one("click", ModulEdifici.gestiona);
		
	},
	gestiona: function() {
		
		lis_size = modul_edificis_elm.find("li").size();
		
		if (lis_size > 0) {
			
			codi_seleccionat = "<ul>";
			
			modul_edificis_elm.find("li").each(function() {
				
				li_elm = $(this);
				
				codi_seleccionat += "<li>";
				codi_seleccionat += "<div class=\"procediment\">";
				codi_seleccionat += "<input type=\"hidden\" value=\"" + li_elm.find("input").val() + "\" />";
				codi_seleccionat += "<span class=\"procediment\">" + li_elm.text() + "</span>";
				codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
				codi_seleccionat += "</div>";
				codi_seleccionat += "</li>";
			
			});
			
			codi_seleccionat += "</ul>";
			
			edificis_seleccionats_elm.find("ul").remove().end().append(codi_seleccionat);
			
			EscriptoriEdifici.contaSeleccionats();
			
		} else {
			
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaEdificisSeleccionats + ".");
			
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			escriptori_edificis_elm.fadeIn(300, function() {
				// activar
				escriptori_edificis_elm.bind("click",EscriptoriEdifici.llansar);
			});
			
		});
		
	}
};

var EscriptoriEdifici = {
	carregar: function(opcions) {
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		
		dataVars = "";
		
		// cercador
		dataVars_cercador = "&adresa=" + $("#cerca_edificis_adresa").val();
		dataVars_cercador += "&cp=" + $("#cerca_edificis_cp").val();
		dataVars_cercador += "&poblacio=" + $("#cerca_edificis_poblacio").val();
			
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_edifici_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_edifici_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_edifici_elm.val(),10);
			
		// ordre
		ordre_Tipus = ordreTipus_edifici_elm.val();
		ordre_Camp = ordreCamp_edifici_elm.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagEdificis,
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
		/*		json_estat = data.json.estat;
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
					resultats_total = parseInt(data.total,10);
					
					if (resultats_total > 0) {
						
						txtT = (resultats_total > 1) ? txtEdificis : txtEdifici;
						
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
						ordre_c1 = (ordre_C == "adresa") ? " " + ordre_T : "";
						ordre_c2 = (ordre_C == "cp") ? " " + ordre_T : "";
						ordre_c3 = (ordre_C == "poblacio") ? " " + ordre_T : "";
						
						txt_ordenacio = "";
						
						if (resultats_total > 1) {
						
							txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
							
							if (ordre_C == "adresa") {
								txt_per = txtAdresa;
							} else if (ordre_C == "cp") {
								txt_per = txtCodiPostal;
							} else {
								txt_per = txtPoblacio;
							}
							
							txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
						
						}
						
						codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

						/* Se desactiva la ordenacion
						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtAdresa + "</a></div>";
						codi_cap2 = "<div class=\"th cp" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCodiPostal + "</a></div>";
						codi_cap3 = "<div class=\"th poblacio" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPoblacio + "</a></div>";
						*/
						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtAdresa + "</a></div>";
						codi_cap2 = "<div class=\"th cp" + ordre_c2 + "\" role=\"columnheader\">" + txtCodiPostal + "</a></div>";
						codi_cap3 = "<div class=\"th poblacio" + ordre_c3 + "\" role=\"columnheader\">" + txtPoblacio + "</a></div>";
						
						// codi taula
						codi_taula = "<div class=\"table llistat edificis\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
						
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
							
							codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
							
							codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
							codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
							codi_taula += "<a href=\"javascript:;\" class=\"nom\">" + dada_node.direccion + "</a>";
							codi_taula += "</div>";
							
							codi_taula += "<div class=\"td cp\" role=\"gridcell\">" + dada_node.codigoPostal + "</div>";
							codi_taula += "<div class=\"td poblacio\" role=\"gridcell\">" + dada_node.poblacion + "</div>";
							
							codi_taula += "</div>";
						});
						
						codi_taula += "</div>";
						codi_taula += "</div>";
						
						if($.browser.opera) {
							escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
						}
						
						// paginacio
						if (ultimaPag > 0) {
							// inici, final
							inici_esta = false;
							final_esta = false;
							// anterior
							codi_anteriors = "";
							if (pag_Pag > 0) {
								codi_anteriors = " <a href=\"javascript:;\" class=\"anteriors\">" + txtAnteriors + "</a> - ";
							}
							// num pagines
							codi_paginas = "";
							paginasNum = Math.ceil(resultats_total/pag_Res);
							if (pag_Pag - paginacio_marge >= 0) {
								codi_paginas += "... ";
							}
							for (i=0; i<paginasNum; i++) {
								if (i > pag_Pag - paginacio_marge && i < pag_Pag + paginacio_marge) {
									codi_paginas += (i == pag_Pag) ? "<strong>"+(i+1)+"</strong>" : "<a href=\"javascript:;\">" + (i+1) + "</a>";
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
								codi_seguents = " - <a href=\"javascript:;\" class=\"seguents\">" + txtSeguents + "</a>";
							}
							// inici
							if (!inici_esta) {
								codi_anteriors = "<a href=\"javascript:;\" class=\"inici\">" + txtInici + "</a> - " + codi_anteriors;
							}
							// final
							if (!final_esta) {
								codi_seguents = codi_seguents + " - <a href=\"javascript:;\" class=\"final\">" + txtFinal + "</a>";
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
						codi_final = "<p class=\"noItems\">" + txtNoHiHaEdificis + ".</p>";
						
					}
					
					// animacio
					edificis_dades_elm.fadeOut(300, function() {
						// pintem
						edificis_dades_elm.html(codi_final).fadeIn(300, function() {
						
							// events
							escriptori_edificis_elm.bind("click",EscriptoriEdifici.llansar);
							
							// cercador
							edificis_cercador_elm.find("input, select").removeAttr("disabled");
							
						});
					});
				/*	
					// missatge?
					if (data.json.missatge != "") {
						Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge});
					}
				*/	
				}
			
			//}
		});
	
	},
	llansar: function(e) {
		
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			escriptori_edificis_elm.unbind("click",EscriptoriEdifici.llansar);
				
			// llancem
			pare_elm = elm.parent();
			
			if (pare_elm.is("LI") && pare_elm.hasClass("opcio")) {
				
				// opcions pestanya
				EscriptoriEdifici.opcions(elm);
				
			} else if (pare_elm.hasClass("th")) {
				
				// ordenacio
				if (pare_elm.hasClass("ASC")) {
					ordreTipus_edifici_elm.val("DESC");
				} else if (pare_elm.hasClass("DESC")) {
					ordreTipus_edifici_elm.val("ASC");
				} else {
					pare_class = pare_elm.attr("class");
					c = pare_class.substr(pare_class.indexOf(" ")+1);
					ordreCamp_edifici_elm.val(c);
				}
				
				// animacio
				edificis_dades_elm.fadeOut(300, function() {
					// pintem
					codi_ordre = "<p class=\"executant\">" + txtCarregantItems + "</p>";
					edificis_dades_elm.html(codi_ordre).fadeIn(300, function() {
						
						EscriptoriEdifici.carregar({});
						
					});
				});
				
			} else if (pare_elm.is("P") && pare_elm.attr("class") == "paginacio") {
				
				pag_Pag = pagPagina_edifici_elm.val();
				enlace_html = elm.html();
				EscriptoriEdifici.anar(enlace_html);
				
			} else if (elm.hasClass("nom")) {
				
				procediment_id = elm.parent().find("input.id").val();
				procediment_titol = elm.html();
				
				lis_size = edificis_seleccionats_elm.find("li").size();
				
				procediment_esta = false;
				
				if (lis_size == 0) {
					
					$("<ul>").appendTo(edificis_seleccionats_elm);
					
				} else {
					
					edificis_seleccionats_elm.find("input").each(function() {
						
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
					
					edificis_seleccionats_elm.find("ul").append(codi_seleccionat);
					
					EscriptoriEdifici.contaSeleccionats();
				
				}
				
				escriptori_edificis_elm.bind("click",EscriptoriEdifici.llansar);
				
			}
			
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a.btn:first");
			
			if (a_elm.hasClass("torna")) {
				
				escriptori_edificis_elm.unbind("click",EscriptoriEdifici.llansar);
				
				EscriptoriEdifici.torna();
				
			} else if (a_elm.hasClass("elimina")) {
				
				a_elm.parents("li:first").remove();
				
				EscriptoriEdifici.contaSeleccionats();
				
				
			} else if (a_elm.hasClass("finalitza")) {
				
				escriptori_edificis_elm.unbind("click",EscriptoriEdifici.llansar);
				
				nombre_llistat = 0;
				
				codi_llistat = "<ul>";
				
				edificis_seleccionats_elm.find("li").each(function(i) {
				
					li_elm = $(this);
					input_elm = li_elm.find("input");
					codi_llistat += "<li><input type=\"hidden\" value=\"" + li_elm.find("input").val() + "\" />" + li_elm.find("span.procediment").text() + "</li>";
					nombre_llistat++;
					
				});
				
				codi_llistat += "</ul>";
				
				codi_edificis_txt = (nombre_llistat == 1) ? txtEdifici : txtEdificis;
				codi_info = (nombre_llistat == 0) ? txtNoHiHaEdificis + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_edificis_txt.toLowerCase() + "</strong>.";
				
				modul_edificis_elm.find("ul").remove().end().find("p.info").html(codi_info).after(codi_llistat);
				
				if (nombre_llistat > 1) {
					modul_edificis_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
				}
				
				EscriptoriEdifici.torna();
				
			} else if (a_elm.hasClass("consulta")) {
				
				// desactivem taula
				escriptori_edificis_elm.unbind("click",EscriptoriEdifici.llansar);
				
				edificis_cercador_elm.find("input, select").attr("disabled", "disabled");
				
				// animacio
				edificis_dades_elm.fadeOut(300, function() {
					// pintem
					codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
					edificis_dades_elm.html(codi_cercant).fadeIn(300, function() {
					
						// events taula
						pagPagina_edifici_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera p�gina.
						EscriptoriEdifici.carregar({});
						
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
		edificis_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			edificis_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_edifici_elm.val(num-1);
				
				// llancem!
				EscriptoriEdifici.carregar({pagina: num-1});
				
			});
		});
		
	},
	torna: function() {
		
		// animacio
		escriptori_edificis_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_edificis_elm.find("a.gestiona").one("click", ModulEdifici.gestiona);
			});
			
		});
		
	},
	contaSeleccionats: function() {
		
		seleccionats_val = edificis_seleccionats_elm.find("li").size();
		info_elm = edificis_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			edificis_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaEdificisSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtEdifici.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtEdificis.toLowerCase() + "</strong>.");
			edificis_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			
		}
		
	}
};
