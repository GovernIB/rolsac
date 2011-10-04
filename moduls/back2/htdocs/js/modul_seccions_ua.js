// MODUL NORMATIVA

$(document).ready(function() {
	
	// elements
	modul_seccions_ua_elm = $("div.modulSeccionsUA");
	escriptori_seccions_ua_elm = $("#escriptori_seccions_ua");
	
	if (modul_seccions_ua_elm.size() == 1) {
		
		// INICIEM
		ModulSeccionsUA.iniciar();
		
	}
	
});

var ModulSeccionsUA = {
	iniciar: function() {
		
		seccions_ua_llistat_elm = escriptori_seccions_ua_elm.find("div.escriptori_items_llistat:first");
		seccions_ua_seleccionats_elm = escriptori_seccions_ua_elm.find("div.escriptori_items_seleccionats:first");
		
		seccions_ua_dades_elm = seccions_ua_llistat_elm.find("div.dades:first");
				
		escriptori_seccions_ua_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
			if (botonera_elm.hasClass("dalt")) {
				botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			} else {
				botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			}
		});
		
		seccions_ua_llistat_elm.add(seccions_ua_seleccionats_elm).css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// botonera seleccionats
		seccions_ua_seleccionats_elm.find("p.botonera").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		
		// one al botó de gestionar
		modul_seccions_ua_elm.find("a.gestiona").one("click", ModulSeccionsUA.gestiona);
		
	},
	gestiona: function() {
		
		lis_size = modul_seccions_ua_elm.find("li").size();
		
		if (lis_size > 0) {
			
			ua_seleccionada_elm = mollaPa_contingut_elm.find("li.seleccionat input").val();
			
			codi_seleccionat = "<ul>";
			
			modul_seccions_ua_elm.find("li").each(function() {
				
				li_elm = $(this);
				
				ua_val = li_elm.find("input.ua:first").val();
				
				codi_seleccionat += "<li>";
				codi_seleccionat += "<div class=\"se_ua\">";
				codi_seleccionat += "<input type=\"hidden\" value=\"" + li_elm.find("input.seccio:first").val() + "\" class=\"seccio\" />";
				codi_seleccionat += "<input type=\"hidden\" value=\"" + ua_val + "\" class=\"ua\" />";
				codi_seleccionat += "<span class=\"se_ua\">";
				codi_seleccionat += "<span class=\"seccio\">" + li_elm.find("em.seccio:first").text() + "</span>, " + txtAmbLaUnitat + " <span class=\"ua\">" + li_elm.find("em.ua:first").text() + "</span>";
				codi_seleccionat += "</span>";
				codi_seleccionat += (ua_val == ua_seleccionada_elm) ? "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>" : "&nbsp;";
				codi_seleccionat += "</div>";
				codi_seleccionat += "</li>";
			
			});
			
			codi_seleccionat += "</ul>";
			
			seccions_ua_seleccionats_elm.find("ul").remove().end().append(codi_seleccionat);
			
			EscriptoriSeccionsUA.contaSeleccionats();
			
		} else {
			
			seccions_ua_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaSeccioUA + ".");
			
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			escriptori_seccions_ua_elm.fadeIn(300, function() {
				
				// tamanys moduls
				
				// modul seccions
				secc_nivell_ample = escriptori_seccions_arbre_elm.width();
				secc_nivell_ample_em = Math.round(parseFloat($(secc_nivell_ample).toEm()));
				secc_nivell_UL = secc_arbre_elm.find("ul:last");
				secc_nivell_UL_LIs = parseInt(secc_nivell_UL.find("li").size(),10);
				secc_nivell_seguent_alt = secc_nivell_UL.innerHeight() + secc_nivell_UL_LIs;
				secc_arbre_nivells_elm.css({ height: $(secc_nivell_seguent_alt).toEm() });
				
				// modul ua
				/*
				ua_nivell_ample = escriptori_ua_arbre_elm.width();
				ua_nivell_ample_em = Math.round(parseFloat($(ua_nivell_ample).toEm())) + 1;
				ua_nivell_UL = ua_arbre_elm.find("ul:last");
				ua_nivell_UL_LIs = parseInt(ua_nivell_UL.find("li").size(),10);
				ua_nivell_seguent_alt = ua_nivell_UL.innerHeight() + ua_nivell_UL_LIs;
				ua_arbre_nivells_elm.css({ height: $(ua_nivell_seguent_alt).toEm() });
				*/
				// activar
				escriptori_seccions_ua_elm.bind("click",EscriptoriSeccionsUA.llansar);
			});
			
		});
		
	}
};

var EscriptoriSeccionsUA = {
	llansar: function(e) {
		
		elm = $(e.target);
		
		if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a.btn:first");
			
			if (a_elm.hasClass("torna")) {
				
				escriptori_seccions_ua_elm.unbind("click",EscriptoriSeccionsUA.llansar);
				
				EscriptoriSeccionsUA.torna();
				
			} else if (a_elm.hasClass("elimina")) {
				
				a_elm.parents("li:first").remove();
				
				EscriptoriSeccionsUA.contaSeleccionats();
				
				
			} else if (a_elm.hasClass("finalitza")) {
				
				escriptori_seccions_ua_elm.unbind("click",EscriptoriSeccionsUA.llansar);
				
				nombre_llistat = 0;
				
				codi_llistat = "<ul>";
				
				seccions_ua_seleccionats_elm.find("li").each(function(i) {
				
					li_elm = $(this);
					input_elm = li_elm.find("input");
					codi_llistat += "<li>";
					codi_llistat += "<input type=\"hidden\" value=\"" + li_elm.find("input.seccio").val() + "\" class=\"seccio\" />";
					codi_llistat += "<input type=\"hidden\" value=\"" + li_elm.find("input.ua").val() + "\" class=\"ua\" />";
					codi_llistat += txtLaSeccio + " <em class=\"seccio\">" + li_elm.find("span.seccio").text() + "</em>, " + txtAmbLaUnitat + " <em class=\"ua\">" + li_elm.find("span.ua").text() + "</em>";
					codi_llistat += "</li>";
					nombre_llistat++;
					
				});
				
				codi_llistat += "</ul>";
				
				codi_seccions_txt = (nombre_llistat == 1) ? txtSeccioUA : txtSeccionsUA;
				codi_info = (nombre_llistat == 0) ? txtNoHiHaSeccioUA + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_seccions_txt.toLowerCase() + "</strong>.";
				
				modul_seccions_ua_elm.find("ul").remove().end().find("p.info").html(codi_info).after(codi_llistat);
				
				if (nombre_llistat > 1) {
					modul_seccions_ua_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
				}
				
				EscriptoriSeccionsUA.torna();
				
			}
			
		}
		
	},
	torna: function() {
		
		// animacio
		escriptori_seccions_ua_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_ua_elm.find("a.gestiona").one("click", ModulSeccionsUA.gestiona);
			});
			
		});
		
	},
	contaSeleccionats: function() {
		
		seleccionats_val = seccions_ua_seleccionats_elm.find("li").size();
		info_elm = seccions_ua_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			seccions_ua_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaSeccioUA + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtSeccioUA.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtSeccionsUA.toLowerCase() + "</strong>.");
			seccions_ua_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			
		}
		
	},
	afegir: function(dades) {
		
		seccio_id = dades.id;
		seccio_titol = dades.nom;
		
		lis_size = seccions_ua_seleccionats_elm.find("li").size();
		
		seccio_esta = false;
		
		if (lis_size == 0) {
			
			$("<ul>").appendTo(seccions_ua_seleccionats_elm);
			
		} else {
			
			seccions_ua_seleccionats_elm.find("input").each(function() {
				
				if ($(this).val() == seccio_id) {
					seccio_esta = true;
				}
			
			});
			
		}
		
		if (!seccio_esta) {
			
			li_mollaPa_seleccionat_elm = mollaPa_contingut_elm.find("li.seleccionat");
			
			ua_text = li_mollaPa_seleccionat_elm.text();
			ua_text = ua_text.substr(ua_text.indexOf(">")+2);
			
			codi_seleccionat = "<li>";
			codi_seleccionat += "<div class=\"se_ua\">";
			codi_seleccionat += "<input type=\"hidden\" value=\"" + seccio_id + "\" class=\"seccio\" />";
			codi_seleccionat += "<input type=\"hidden\" value=\"" + li_mollaPa_seleccionat_elm.find("input").val() + "\" class=\"ua\" />";
			codi_seleccionat += "<span class=\"se_ua\">";
			codi_seleccionat += "<span class=\"seccio\">" + seccio_titol + "</span>, " + txtAmbLaUnitat + " <span class=\"ua\">" + ua_text + "</span>";
			codi_seleccionat += "</span>";
			codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
			codi_seleccionat += "</div>";
			codi_seleccionat += "</li>";
			
			seccions_ua_seleccionats_elm.find("ul").append(codi_seleccionat);
			
			EscriptoriSeccionsUA.contaSeleccionats();
		
		}
		
	}
};
