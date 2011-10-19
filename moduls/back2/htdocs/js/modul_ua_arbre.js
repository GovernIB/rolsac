// MÒDUL UA ARBRE

$(document).ready(function() {
	
	UA_Arbre.iniciar();
	
});

var ua_arbre_arr = new Array();
var ua_arbre_elm, ua_arbre_mollaPa_elm, ua_arbre_nivells_elm, ua_arbre_nivells_contingut_elm;

var UA_Arbre = {
	iniciar: function() {
		
		ua_arbre_codi = "<p class=\"executant\">" + txtCarregantArrel + ".</p>";
		
		escriptori_ua_arbre_elm = $("div.escriptori_ua_arbre:first");
		escriptori_ua_arbre_elm.html(ua_arbre_codi);
		
		UA_Arbre.carregar({ nivell: 0 });
		
	},
	llansar: function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			if (elm.hasClass("selecciona")) {
				
				ua_arbre_elm.unbind("click", UA_Arbre.llansar);
				
				$("div.ua_arbre a.selecciona").removeClass("triat");
				
				elm.addClass("triat");
				
				ua_arbre_elm.bind("click", UA_Arbre.llansar);
				
			} else if (elm.hasClass("molla") && elm.parent().is("LI")) {
				
				ua_arbre_elm.unbind("click", UA_Arbre.llansar);
				
				pare_elm = elm.parent();
				pare_class = pare_elm.attr("class");
				ua_nivell_val = parseInt(pare_class.substr(1),10);
				
				nivell_index = pare_elm.index();
				
				pos_left = (ua_nivell_ample_em * nivell_index);
				
				ua_nivell_UL = nivell_alt = ua_arbre_elm.find("ul.n"+ua_nivell_val+":first");
				ua_nivell_UL_LIs = parseInt(ua_nivell_UL.find("li").size(),10);
				nivell_alt = ua_nivell_UL.innerHeight() + ua_nivell_UL_LIs;
				
				ua_arbre_mollaPa_elm.animate({
					opacity: "0"
				}, 300, function() {
					
					ua_arbre_mollaPa_elm.find("li:gt("+nivell_index+")").remove();
					
					ua_arbre_mollaPa_elm.find("li.n" + nivell_index).html(ua_arbre_mollaPa_elm.find("li.n" + ua_nivell_val + " a").text()).end().animate({
						opacity: "1"
					}, 300, function() {
						ua_arbre_elm.bind("click",UA_Arbre.llansar);
					});
					
				});
				
				ua_arbre_nivells_contingut_elm.animate({
					left: "-" + pos_left + "em"
				}, 300, function() {
					ua_arbre_nivells_contingut_elm.find("ul:gt("+nivell_index+")").remove();
				});
				
				ua_arbre_nivells_elm.animate({
					height: $(nivell_alt).toEm()
				}, 300);
				
			} else if (elm.hasClass("fills")){			
			
			ua_arbre_elm.unbind("click", UA_Arbre.llansar);
			
			ua_nivell_pare_DIV = elm.parents("div:first");
			ua_nivell_pare_UL = ua_nivell_pare_DIV.parents("ul.nivell:first");
			ua_nivell_pare_UL_class = ua_nivell_pare_UL.attr("class");
			ua_nivell_val = parseInt(ua_nivell_pare_UL_class.substr(ua_nivell_pare_UL_class.indexOf(" ")+2),10);
			ua_nivell_seguent_val = ua_nivell_val+1;
			ua_nivell_ID = ua_nivell_pare_DIV.find("input:first").val();
			ua_nivell_nom = ua_nivell_pare_DIV.find("a.selecciona:first").text();
			
			arbre_arr_esta = false;
			codi_ua_arbre = "<ul class=\"nivell n" + ua_nivell_seguent_val + "\">";
			$(ua_arbre_arr).each(function(i) {
				arbre_arr_node = this;
				if (arbre_arr_node.pare == ua_nivell_ID) {
					arbre_arr_esta = true;
					codi_ua_arbre += "<li>";
					codi_ua_arbre += "<div>";
					codi_ua_arbre += "<input type=\"hidden\" value=\"" + arbre_arr_node.id + "\" class=\"id\"/>";
					codi_ua_arbre += "<a class=\"selecciona\" href=\"javascript:;\">" + arbre_arr_node.nom + "</a>";
					if (arbre_arr_node.fills == true) {						
						codi_ua_arbre += "<a class=\"btn fills\" href=\"javascript:;\"></a>";
					}
					codi_ua_arbre += "</div>";
					codi_ua_arbre += "</li>";
				}
			});
			codi_ua_arbre += "</ul>";
			
			if (arbre_arr_esta) {
				UA_Arbre.pintar({ codi: codi_ua_arbre });
			} else {
				
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantNodes });
				
				UA_Arbre.carregar({ nivell: (ua_nivell_val+1), pare: ua_nivell_ID });
			}
			
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
			url: pagUnitats,
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
					
					data_unitat = data.llistaUnitats;
					
					codi_ua_arbre = "<ul class=\"nivell n" + opcions.nivell + "\">";
					$(data_unitat).each(function(i) {
						
						data_unitat_node = this;
						
						codi_ua_arbre += "<li>";
						codi_ua_arbre += "<div>";
						codi_ua_arbre += "<input type=\"hidden\" value=\"" + data_unitat_node.id + "\" class=\"id\"/>";
						codi_ua_arbre += "<a class=\"selecciona\" href=\"javascript:;\">" + data_unitat_node.nom + "</a>";
						if (data_unitat_node.filles == true) {							
							codi_ua_arbre += "<a class=\"btn fills\" href=\"javascript:;\"></a>";
						}
						codi_ua_arbre += "</div>";
						codi_ua_arbre += "</li>";
						
						// array
						arbre_node = { id: data_unitat_node.id, nom: data_unitat_node.nom, pare: data_unitat_node.pare, fills: data_unitat_node.filles };
						ua_arbre_arr.push(arbre_node);
						
					});
					codi_ua_arbre += "</ul>";
					
					if ($("#missatge").size() != 0) {
						Missatge.cancelar();
					}
					
					UA_Arbre.pintar({ codi: codi_ua_arbre, nivell: opcions.nivell });
					

			}
		});
		
	},
	pintar: function(opcions) {
		
		// pintar
		if (opcions.nivell == 0) {
			
			codi_ua_arbre_0 = "<div class=\"ua_arbre\">";
			codi_ua_arbre_0 += "<ul class=\"mollaPa\">";
			codi_ua_arbre_0 += "<li class=\"n0\">";
			codi_ua_arbre_0 += txtArrel;
			codi_ua_arbre_0 += "</li>";
			codi_ua_arbre_0 += "</ul>";
			codi_ua_arbre_0 += "<div class=\"nivells\">";
			codi_ua_arbre_0 += "<div class=\"nivells_contingut\">";
			codi_ua_arbre_0 += opcions.codi;
			codi_ua_arbre_0 += "</div>";
			codi_ua_arbre_0 += "</div>";
			
			escriptori_ua_arbre_elm.slideUp(300, function() {
				$(this).html(codi_ua_arbre_0).slideDown(300, function() {
					ua_arbre_elm = $("div.ua_arbre:first");
					ua_arbre_mollaPa_elm = ua_arbre_elm.find("ul.mollaPa:first");
					ua_arbre_nivells_elm = ua_arbre_elm.find("div.nivells:first");
					ua_arbre_nivells_contingut_elm = ua_arbre_elm.find("div.nivells_contingut:first");
					ua_arbre_elm.bind("click", UA_Arbre.llansar);
					
					ua_nivell_UL = ua_arbre_elm.find("ul.n0:first");
					ua_nivell_UL_LIs = parseInt(ua_nivell_UL.find("li").size(),10);
					ua_nivell_seguent_alt = ua_nivell_UL.innerHeight() + ua_nivell_UL_LIs;
					
					ua_arbre_nivells_elm.animate({
						height: $(ua_nivell_seguent_alt).toEm()
					}, 300);
			
				});
			});
			
		} else {
		
			ua_arbre_nivells_contingut_elm.append(opcions.codi);
		
			ua_arbre_mollaPa_elm.animate({
				opacity: "0"
			}, 300, function() {
				ua_arbre_mollaPa_elm.append("<li class=\"n" + ua_nivell_seguent_val + "\">" + ua_nivell_nom + "</li>");
				ua_arbre_mollaPa_elm.find("li.n" + ua_nivell_val).html("<a class=\"molla\">" + ua_arbre_mollaPa_elm.find("li.n" + ua_nivell_val).text() + "</a>").end().animate({
					opacity: "1"
				}, 300, function() {
					ua_arbre_elm.bind("click", UA_Arbre.llansar);
				});
			});
			
			pos_left = (ua_nivell_ample_em);// + 0.3;
			ua_nivell_UL = ua_arbre_elm.find("ul.n"+ua_nivell_seguent_val+":first");
			ua_nivell_UL_LIs = parseInt(ua_nivell_UL.find("li").size(),10);
			ua_nivell_seguent_alt = ua_nivell_UL.innerHeight() + ua_nivell_UL_LIs;
			
			ua_arbre_nivells_contingut_elm.animate({
				left: "-=" + pos_left + "em"
			}, 300);
			
			ua_arbre_nivells_elm.animate({
				height: $(ua_nivell_seguent_alt).toEm()
			}, 300);
			
		}
		
	}
};
