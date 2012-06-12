// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	graficaPlanificacio_elm = $("#graficaPlanificacio");
	
	nombreContinguts_elm = $("#nombreContinguts");
	fillsContinguts_elm = $("#fillsContinguts");
			
	// css
	escriptori_contingut_elm.find("div.modul").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
	
	// aria
	/*
	cap_elm.find("div.aplicacio strong").attr('role', 'banner').end().find("ul.opcions").attr('role', 'navigation');
	menu_elm.attr('role', 'complementary').attr('aria-live', 'polite').attr('aria-atomic', 'true').attr('aria-relevant', 'text additions');
	continguts_elm.attr('role', 'content');
	escriptori_elm.attr('aria-live', 'polite').attr('aria-atomic', 'true').attr('aria-relevant', 'text additions');
	escriptori_detall_elm.attr('aria-hidden', 'true').attr('aria-disabled', 'true').attr('aria-live', 'polite').attr('aria-atomic', 'true').attr('aria-relevant', 'text additions');
	*/
	
	// INICIEM
	//Inici.llansar(); // TODO: descomentar esta línea para la palntalla de control cuando proceda.

});

var Inici = {
	llansar: function() {
		
		// variables
		dataVars = "";
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagInici,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				// missatge
				//Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				//Error.llansar();
				
			},
			success: function(data) {
				
				// estat json
				json_estat = data.estat
				json_mode = (json_estat == "CORRECTE") ? "correcte" : (json_estat == "WARNING") ? "atencio" : (json_estat == "ERROR") ? "error" : "fatal";
				if (json_estat == "FATAL") {
					
					Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.missatge, funcio: Aplicacio.tancar});
					$("#contenidor").html("");
					return false;
					
				} else if (json_estat == "ERROR") {
					
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + data.missatge + "</p>"});
					// error
					Error.llansar();
					
				} else {
					
					data_json = data.json.data;
					
					if (typeof data_json.planificacio != "undefined") {
						Inici.planificacio(data_json.planificacio);
					} else {
						graficaPlanificacio_elm.remove();
					}
					
					if (typeof data_json.grafica != "undefined") {
						Inici.grafica(data_json.grafica);
					} else {
						$("#graficaResum").remove();
					}
					
					if (typeof data_json.modificacions != "undefined") {
						Inici.modificacions(data_json.modificacions);
					} else {
						$("#darreresModificacions").remove();
					}
					
					if (typeof data_json.nombre != "undefined") {
						Inici.nombre(data_json.nombre);
					} else {
						$("#nombreContinguts").remove();
					}
					
					// mostrem
					$("#carregantDades").slideUp(300, function() {
						$("#iniciDades").slideDown(300, function() {
							As.iniciar("#iniciDades");
						});
					});
					
				}
			}
		});
		
	},
	planificacio: function(dades_planificacio) {
		
		// codi planificacio
		codi_planificacio = "<div class=\"planificacio\">";
		
		codi_planificacio += "<div class=\"dades\">";
				
		codi_planificacio += "<ul class=\"tipus\">";
		codi_planificacio += "<li>" + txtProcediments + "</li>";
		codi_planificacio += "<li>" + txtNormativa + "</li>";
		codi_planificacio += "<li>" + txtFitxes + "</li>";
		codi_planificacio += "</ul>";
		
		codi_planificacio += "<ul class=\"dades\"></ul>";
				
		codi_planificacio += "</div>";
				
		codi_planificacio += "<div class=\"faldo\">";
				
		codi_planificacio += "<ul class=\"dades\"></ul>";
				
		codi_planificacio += "</div>";
			
		codi_planificacio += "</div>";
		
		graficaPlanificacio_elm.append(codi_planificacio);
		
		// vars
		planificacio_dades_elm = graficaPlanificacio_elm.find("div.dades ul.dades");
		codi_planificacio_faldo = "";
		
		$(dades_planificacio).each(function(i) {
			
			dada_elm = this
			
			// codi columnes
			codi_boto_P = "";
			codi_boto_N = "";
			codi_boto_F = "";
			
			if (typeof dada_elm.info != "undefined") {
				
				if (dada_elm.info.tipus == "P") {
					class_caducat = (dada_elm.info.accio == "C") ? " procediment_caducat" : "";
					codi_boto_P += "<a href=\"javascript:;\" class=\"btn procediment" + class_caducat + "\"><span><span>&nbsp;</span></span></a>";
				}
				
				if (dada_elm.info.tipus == "N") {
					codi_boto_N += "<a href=\"javascript:;\" class=\"btn normativa\"><span><span>&nbsp;</span></span></a>";
				}
				
				if (dada_elm.info.tipus == "F") {
					class_caducat = (dada_elm.info.accio == "C") ? " fitxa_caducat" : "";
					codi_boto_F += "<a href=\"javascript:;\" class=\"btn fitxa" + class_caducat + "\"><span><span>&nbsp;</span></span></a>";
				}
			
			}
			
			codi_planificacio_columnes = "<div class=\"P\">" + codi_boto_P + "</div>";
			codi_planificacio_columnes += "<div class=\"N\">" + codi_boto_N + "</div>";
			codi_planificacio_columnes += "<div class=\"F\">" + codi_boto_F + "</div>";
			
			planificacio_dades_elm.append("<li></li>");
			
			//li_last_elm = planificacio_dades_elm.find("li:last");
			if (typeof dada_elm.info != "undefined") {
				planificacio_dades_elm.find("li:last").data( { tipus: dada_elm.info.tipus, id: dada_elm.info.id, titol: dada_elm.info.titol, accio: dada_elm.info.accio } ).append(codi_planificacio_columnes);
			} else {
				planificacio_dades_elm.find("li:last").append(codi_planificacio_columnes);
			}
			// codi faldo
			dada_data = dada_elm.data;
			if (dada_data.indexOf("/") != -1) {
				data_elm = dada_data.split("/");
				codi_planificacio_faldo += "<li><span>" + data_elm[0] + "<br />" + mesosNom[parseInt(data_elm[1],10)] + "<br />" + data_elm[2] + "</span></li>";
			} else {
				codi_planificacio_faldo += (dada_data == "avui") ? "<li><span class=\"avui\">" + txtAvui + "</span></li>" : "<li><span class=\"avui\">" + txtDema + "</span></li>";
			}
			
		});
		
		graficaPlanificacio_elm.find("div.faldo ul.dades").append(codi_planificacio_faldo);
		
		GraficaPlanificacio.iniciar();
		
	},
	grafica: function(graficaDades) {
	
		// vars
		valor_max = 0;
		valor_mitja = 0;
		codi_grafica_columnes = "";
		codi_grafica_faldo = "";
		
		$(graficaDades).each(function(i) {
			
			// valor max i mitja
			dada_elm = this
			dada_P_elm = dada_elm.P;
			dada_N_elm = dada_elm.N;
			dada_F_elm = dada_elm.F;
			
			valor_max = (dada_P_elm > valor_max) ? dada_P_elm : valor_max;
			valor_max = (dada_N_elm > valor_max) ? dada_N_elm : valor_max;
			valor_max = (dada_F_elm > valor_max) ? dada_F_elm : valor_max;
			
			// codi columnes
			codi_grafica_columnes += "<li>";
			codi_grafica_columnes += "<div class=\"P\" title=\"" + txtProcediments +" = " + dada_P_elm + "\">";
			valor_P = 10 - ((dada_P_elm*10)/valor_max);
			codi_grafica_columnes += "<span style=\"height: " + valor_P + "em;\"></span>";
			codi_grafica_columnes += "P";
			codi_grafica_columnes += "</div>";
			codi_grafica_columnes += "<div class=\"N\" title=\"" + txtNormativa +" = " + dada_N_elm + "\">";
			valor_N = 10 - ((dada_N_elm*10)/valor_max);
			codi_grafica_columnes += "<span style=\"height: " + valor_N + "em;\"></span>";
			codi_grafica_columnes += "N";
			codi_grafica_columnes += "</div>";
			codi_grafica_columnes += "<div class=\"F\" title=\"" + txtFitxes + " = " + dada_F_elm + "\">";
			valor_F = 10 - ((dada_F_elm*10)/valor_max);
			codi_grafica_columnes += "<span style=\"height: " + valor_F + "em;\"></span>";
			codi_grafica_columnes += "F";
			codi_grafica_columnes += "</div>";
			codi_grafica_columnes += "</li>";
			
			// codi faldo
			dada_data = dada_elm.data;
			if (dada_data.indexOf("/") != -1) {
				data_elm = dada_data.split("/");
				codi_grafica_faldo += "<li><span>" + data_elm[0] + "<br />" + mesosNom[parseInt(data_elm[1],10)] + "<br />" + data_elm[2] + "</span></li>";
			} else {
				codi_grafica_faldo += "<li><span class=\"avui\">" + txtAvui + "</span></li>";
			}
			
		});
		valor_mitja = parseFloat(valor_max/2);
		
		// codi grafica
		codi_grafica = "<div class=\"grafica\">";
		
		codi_grafica += "<div class=\"dades\">";
				
		codi_grafica += "<ul class=\"num\">";
		codi_grafica += "<li>" + valor_max + "</li>";
		codi_grafica += "<li>" + valor_mitja + "</li>";
		codi_grafica += "</ul>";
		
		codi_grafica += "<ul class=\"dades\">";
		codi_grafica += codi_grafica_columnes;
		codi_grafica += "</ul>";
				
		codi_grafica += "</div>";
				
		codi_grafica += "<div class=\"faldo\">";
				
		codi_grafica += "<ul class=\"dades\">";
		codi_grafica += codi_grafica_faldo;
		codi_grafica += "</ul>";
				
		codi_grafica += "</div>";
			
		codi_grafica += "</div>";
		
		$("#graficaResum").append(codi_grafica);
		
	},
	modificacions: function(data) {
		
		data_mod = "<ul>";
		
		$(data).each(function(i) {
			
			dada_elm = this
			
			tipus_val = dada_elm.tipus;
			
			if (tipus_val == "P") {
				tipus_class = "procediment";
				tipus_text = txtProcediment;
				tipus_url = pagProcediments;
			} else if (tipus_val == "N") {
				tipus_class = "normativa";
				tipus_text = txtNormativa;
				tipus_url = pagNormativa;
			} else {
				tipus_class = "fitxa";
				tipus_text = txtFitxa;
				tipus_url = pagFitxes;
			}
			
			if (dada_elm.estat == "C") {
				tipus_class = tipus_class + "_caducat";
			}
			
			data_mod += "<li class=\"" + tipus_class + "\">";
			data_mod += "<span class=\"tipus\">" + tipus_text + "</span>. <span class=\"data\">" + dada_elm.data + " - " + dada_elm.hora + " h.</span>";
			data_mod += "<a href=\"" + tipus_url + "?/" + dada_elm.id + "\">" + dada_elm.titol + "</a>";
			data_mod += "</li>";
		
		});
		
		data_mod += "</ul>";
		
		$("#darreresModificacions").append(data_mod);
		
	},
	nombre: function(data) {
		
		codi_taula = "<table>";
		codi_taula += "<thead>";
		codi_taula += "<tr>";
		codi_taula += "<th class=\"tipus\">" + txtTipus + "</th>";
		codi_taula += "<th>" + txtActius + "</th>";
		codi_taula += "<th>" + txtCaducats + "</th>";
		codi_taula += "<th>" + txtTotal + "</th>";
		codi_taula += "</tr>";
		codi_taula += "</thead>";
		codi_taula += "<tbody>";
		codi_taula += "<tr>";
		codi_taula += "<th><a href=\"" + pagProcediments + "\">" + txtProcediments + "</a></th>";
		codi_taula += "<td>" + (parseInt(data.procediment.total, 10) - parseInt(data.procediment.caducats, 10)) + "</td>";
		codi_taula += "<td>" + data.procediment.caducats + "</td>";
		codi_taula += "<td class=\"total\">" + data.procediment.total + "</td>";
		codi_taula += "</tr>";
		codi_taula += "<tr>";
		codi_taula += "<th><a href=\"" + pagNormativa + "\">" + txtNormativa + "</a></th>";
		codi_taula += "<td>" + (parseInt(data.normativa.total, 10) - parseInt(data.normativa.caducats, 10)) + "</td>";
		codi_taula += "<td>" + data.normativa.caducats + "</td>";
		codi_taula += "<td class=\"total\">" + data.normativa.total + "</td>";
		codi_taula += "</tr>";
		codi_taula += "<tr>";
		codi_taula += "<th><a href=\"" + pagFitxes + "\">" + txtFitxes + "</a></th>";
		codi_taula += "<td>" + (parseInt(data.fitxa.total, 10) - parseInt(data.fitxa.caducats, 10)) + "</td>";
		codi_taula += "<td>" + data.fitxa.caducats + "</td>";
		codi_taula += "<td class=\"total\">" + data.fitxa.total + "</td>";
		codi_taula += "</tr>";
		codi_taula += "</tbody>";
		codi_taula += "</table>";
		
		$("#nombreContinguts").append(codi_taula);
		
	}
};

var GraficaPlanificacio = {
	iniciar: function() {
		
		$("<div id=\"planificacio_detall\">").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"}).css({"-webkit-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "-moz-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "box-shadow": "0 .5em 1.5em rgb(100,100,100)"}).appendTo("#graficaPlanificacio");
		planificacio_detall_elm = $("#planificacio_detall");
		
		graficaPlanificacio_elm.bind("click", GraficaPlanificacio.detall);
		
	},
	detall: function(e) {
		elm = $(e.target);
		
		if (elm.is("A") && elm.hasClass("tancar")) {
			
			planificacio_detall_elm.fadeOut(200);
			
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
		
			li_elm = elm.parents("li:first");
			
			// valors
			elm_tipus_val = li_elm.data().tipus;
			elm_id_val = li_elm.data().id;
			elm_titol_val = li_elm.data().titol;
			elm_accio_val = li_elm.data().accio;
			
			// posicio
			elm_T = elm.offset().top;
			elm_L = elm.offset().left;
			elm_H = elm.outerHeight();
			elm_W = elm.outerWidth(true);
			
			graficaPlanificacio_W = graficaPlanificacio_elm.outerWidth(true);
			
			pos_L = ((elm_L + elm_W) > (graficaPlanificacio_W - 60)) ? graficaPlanificacio_W - elm_W - 60 : elm_L;
			
			// codi
			codi_plan_detall = "<a href=\"javascript:;\" class=\"tancar\">tancar</a>";
			codi_plan_detall += "<h3>" + txtDetallElement + "</h3>";
			
			txt_article = (elm_tipus_val == "P") ? txtAquest : txtAquesta;
			txt_elm = (elm_tipus_val == "P") ? txtProcediment : (elm_tipus_val == "F") ? txtFitxa : txtNormativa;
			url_elm = (elm_tipus_val == "P") ? pagProcediments : (elm_tipus_val == "F") ? pagFitxes : pagNormativa;
			txt_accio = (elm_accio_val == "C") ? txtCaducada : txtPublicada;
			
			codi_plan_detall += "<p>" + txt_article + " <em>" + txt_elm + "</em> " + txtAnomenada + " <a href=\"" + url_elm + "?/" + elm_id_val + "\">" + elm_titol_val + "</a> " + txtPassara + " <strong>" + txt_accio + "</strong>.</p>";
			
			planificacio_detall_elm.fadeOut(200, function() {
				
				planificacio_detall_elm.css( { top: (elm_T + elm_H) + "px", left: pos_L + "px" } ).html(codi_plan_detall).fadeIn(200, function() { As.iniciar("#planificacio_detall"); });
				
			});
		
		}
		
	}
};
