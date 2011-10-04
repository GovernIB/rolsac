// MÒDUL UA ARBRE

$(document).ready(function() {
	
	Seccions_Arbre.iniciar();
	
});

var secc_arbre_arr = new Array();
var secc_arbre_elm, secc_arbre_mollaPa_elm, secc_arbre_nivells_elm, secc_arbre_nivells_contingut_elm;

var Seccions_Arbre = {
	iniciar: function() {
		
		ua_arbre_codi = "<p class=\"executant\">" + txtCarregantArrel + ".</p>";
		
		escriptori_seccions_arbre_elm = $("div.escriptori_seccions_arbre:first");
		escriptori_seccions_arbre_elm.html(ua_arbre_codi);
		//secc_nivell_ample = escriptori_seccions_arbre_elm.width();
		//secc_nivell_ample_em = Math.round(parseFloat($(secc_nivell_ample).toEm()));
		
		Seccions_Arbre.carregar({ nivell: 0 });
		
	},
	llansar: function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			if (elm.hasClass("selecciona")) {
				
				secc_arbre_elm.unbind("click", Seccions_Arbre.llansar);
				
				secc_pare_div_elm = elm.parents("div:first");
				
				secc_ID = secc_pare_div_elm.find("input.id").val();
				secc_NOM = secc_pare_div_elm.find("a.selecciona").text();
				
				EscriptoriSeccionsUA.afegir({ tipus: "seccio", id: secc_ID, nom: secc_NOM });
				
				secc_arbre_elm.bind("click", Seccions_Arbre.llansar);
				
			} else if (elm.hasClass("molla") && elm.parent().is("LI")) {
				
				secc_arbre_elm.unbind("click", Seccions_Arbre.llansar);
				
				secc_pare_elm = elm.parent();
				secc_pare_class = secc_pare_elm.attr("class");
				secc_nivell_val = parseInt(secc_pare_class.substr(1),10);
				
				secc_nivell_index = secc_pare_elm.index();
				
				secc_pos_left = (secc_nivell_ample_em * secc_nivell_index);
				
				secc_nivell_UL = secc_nivell_alt = secc_arbre_elm.find("ul.n"+secc_nivell_val+":first");
				secc_nivell_UL_LIs = parseInt(secc_nivell_UL.find("li").size(),10);
				secc_nivell_alt = secc_nivell_UL.innerHeight() + secc_nivell_UL_LIs;
				
				secc_arbre_mollaPa_elm.animate({
					opacity: "0"
				}, 300, function() {
					
					secc_arbre_mollaPa_elm.find("li:gt("+secc_nivell_index+")").remove();
					
					secc_arbre_mollaPa_elm.find("li.n" + secc_nivell_index).html(secc_arbre_mollaPa_elm.find("li.n" + secc_nivell_val + " a").text()).end().animate({
						opacity: "1"
					}, 300, function() {
						secc_arbre_elm.bind("click",Seccions_Arbre.llansar);
					});
					
				});
				
				secc_arbre_nivells_contingut_elm.animate({
					left: "-" + secc_pos_left + "em"
				}, 300, function() {
					secc_arbre_nivells_contingut_elm.find("ul:gt("+secc_nivell_index+")").remove();
				});
				
				secc_arbre_nivells_elm.animate({
					height: $(secc_nivell_alt).toEm()
				}, 300);
				
			}
			
		} else if (elm.is("SPAN") && elm.parents("a.btn:first").hasClass("fills")) {
			
			secc_arbre_elm.unbind("click", Seccions_Arbre.llansar);
			
			secc_nivell_pare_DIV = elm.parents("div:first");
			secc_nivell_pare_UL = secc_nivell_pare_DIV.parents("ul.nivell:first");
			secc_nivell_pare_UL_class = secc_nivell_pare_UL.attr("class");
			secc_nivell_val = parseInt(secc_nivell_pare_UL_class.substr(secc_nivell_pare_UL_class.indexOf(" ")+2),10);
			secc_nivell_seguent_val = secc_nivell_val+1;
			secc_nivell_ID = secc_nivell_pare_DIV.find("input:first").val();
			secc_nivell_NOM = secc_nivell_pare_DIV.find("a.selecciona:first").text();
			
			secc_arbre_arr_esta = false;
			codi_secc_arbre = "<ul class=\"nivell n" + secc_nivell_seguent_val + "\">";
			$(secc_arbre_arr).each(function(i) {
				arbre_arr_node = this;
				if (arbre_arr_node.pare == secc_nivell_ID) {
					secc_arbre_arr_esta = true;
					codi_secc_arbre += "<li>";
					codi_secc_arbre += "<div>";
					codi_secc_arbre += "<input type=\"hidden\" value=\"" + arbre_arr_node.id + "\" class=\"id\"/>";
					codi_secc_arbre += "<a class=\"selecciona\" href=\"javascript:;\">" + arbre_arr_node.nom + "</a>";
					if (arbre_arr_node.fills == "S") {
						codi_secc_arbre += "<a class=\"btn fills\" href=\"javascript:;\"><span><span>" + txtNodesFills + "</span></span></a>";
					}
					codi_secc_arbre += "</div>";
					codi_secc_arbre += "</li>";
				}
			});
			codi_secc_arbre += "</ul>";
			
			if (secc_arbre_arr_esta) {
				Seccions_Arbre.pintar({ codi: codi_secc_arbre });
			} else {
				
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantNodes });
				
				Seccions_Arbre.carregar({ nivell: (secc_nivell_val+1), pare: secc_nivell_ID });
			}
			
		}
		
	},
	carregar: function(opcions) {
		
		// opcions -> nivell: int
		
		// variables
		dataVars = "";
		
		if (typeof opcions.pare != "undefined") {
			dataVars += "pare=" + opcions.pare;
		}
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagSeccions,
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
					
					data_seccio = data.json.data.seccions;
					
					codi_secc_arbre = "<ul class=\"nivell n" + opcions.nivell + "\">";
					$(data_seccio).each(function(i) {
						
						data_seccio_node = this;
						
						codi_secc_arbre += "<li>";
						codi_secc_arbre += "<div>";
						codi_secc_arbre += "<input type=\"hidden\" value=\"" + data_seccio_node.id + "\" class=\"id\"/>";
						codi_secc_arbre += "<a class=\"selecciona\" href=\"javascript:;\">" + data_seccio_node.nom + "</a>";
						if (data_seccio_node.filles == "S") {
							codi_secc_arbre += "<a class=\"btn fills\" href=\"javascript:;\"><span><span>" + txtNodesFills + "</span></span></a>";
						}
						codi_secc_arbre += "</div>";
						codi_secc_arbre += "</li>";
						
						// array
						arbre_node = { id: data_seccio_node.id, nom: data_seccio_node.nom, pare: data_seccio_node.pare, fills: data_seccio_node.filles };
						secc_arbre_arr.push(arbre_node);
						
					});
					codi_secc_arbre += "</ul>";
					
					if ($("#missatge").size() != 0) {
						Missatge.cancelar();
					}
					
					Seccions_Arbre.pintar({ codi: codi_secc_arbre, nivell: opcions.nivell });
					
				}
			}
		});
		
	},
	pintar: function(opcions) {
		
		// pintar
		if (opcions.nivell == 0) {
			
			codi_secc_arbre_0 = "<div class=\"seccions_arbre\">";
			codi_secc_arbre_0 += "<ul class=\"mollaPa\">";
			codi_secc_arbre_0 += "<li class=\"n0\">";
			codi_secc_arbre_0 += txtArrel;
			codi_secc_arbre_0 += "</li>";
			codi_secc_arbre_0 += "</ul>";
			codi_secc_arbre_0 += "<div class=\"nivells\">";
			codi_secc_arbre_0 += "<div class=\"nivells_contingut\">";
			codi_secc_arbre_0 += opcions.codi;
			codi_secc_arbre_0 += "</div>";
			codi_secc_arbre_0 += "</div>";
			
			escriptori_seccions_arbre_elm.slideUp(300, function() {
				$(this).html(codi_secc_arbre_0).slideDown(300, function() {
					secc_arbre_elm = $("div.seccions_arbre:first");
					secc_arbre_mollaPa_elm = secc_arbre_elm.find("ul.mollaPa:first");
					secc_arbre_nivells_elm = secc_arbre_elm.find("div.nivells:first");
					secc_arbre_nivells_contingut_elm = secc_arbre_elm.find("div.nivells_contingut:first");
					secc_arbre_elm.bind("click", Seccions_Arbre.llansar);
					
					secc_nivell_UL = secc_arbre_elm.find("ul.n0:first");
					secc_nivell_UL_LIs = parseInt(secc_nivell_UL.find("li").size(),10);
					secc_nivell_seguent_alt = secc_nivell_UL.innerHeight() + secc_nivell_UL_LIs;
					
					secc_arbre_nivells_elm.animate({
						height: $(secc_nivell_seguent_alt).toEm()
					}, 300);
					
				});
			});
			
		} else {
		
			secc_arbre_nivells_contingut_elm.append(opcions.codi);
		
			secc_arbre_mollaPa_elm.animate({
				opacity: "0"
			}, 300, function() {
				secc_arbre_mollaPa_elm.append("<li class=\"n" + secc_nivell_seguent_val + "\">" + secc_nivell_NOM + "</li>");
				secc_arbre_mollaPa_elm.find("li.n" + secc_nivell_val).html("<a class=\"molla\">" + secc_arbre_mollaPa_elm.find("li.n" + secc_nivell_val).text() + "</a>").end().animate({
					opacity: "1"
				}, 300, function() {
					secc_arbre_elm.bind("click", Seccions_Arbre.llansar);
				});
			});
			
			secc_pos_left = (secc_nivell_ample_em);// + 0.3;
			secc_nivell_UL = secc_arbre_elm.find("ul.n"+secc_nivell_seguent_val+":first");
			secc_nivell_UL_LIs = parseInt(secc_nivell_UL.find("li").size(),10);
			secc_nivell_seguent_alt = secc_nivell_UL.innerHeight() + secc_nivell_UL_LIs;
			
			secc_arbre_nivells_contingut_elm.animate({
				left: "-=" + secc_pos_left + "em"
			}, 300);
			
			secc_arbre_nivells_elm.animate({
				height: $(secc_nivell_seguent_alt).toEm()
			}, 300);
			
		}
		
	}
};
