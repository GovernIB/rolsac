// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
				
	escriptori_detall_elm = $("#escriptori_detall");
	escriptori_previsualitza_elm = $("#escriptori_previsualitza");
			
	// css
	//opcions_elm.find("li").css({"border-radius": "1em 1em 0 0", "-moz-border-radius": "1em 1em 0 0", "-webkit-border-radius": "1em 1em 0 0"}).end().css("background-image", "-moz-linear-gradient(0% 50% 270deg, #fff, #f0f0f0)");

	escriptori_detall_elm.find("div.botonera").each(function() {
		botonera_elm = $(this);
		// dsanchez: Ya no hay "rabillos".
		/*if (botonera_elm.hasClass("dalt")) {
			botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		} else {
			botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		}*/
	});
	
	$('textarea.rich').tinymce({
		// Location of TinyMCE script
		script_url : tinyMceUrl,

		// General options
		theme : "advanced",
		plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",
		
		// Theme options
		theme_advanced_buttons1 : "bold,italic,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,bullist,numlist,|,link,unlink,|,undo,redo,|,cleanup,code",
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_buttons4 : "",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,
		theme_advanced_resize_horizontal: false
	});
	
	// aria
	/*
	cap_elm.find("div.aplicacio strong").attr('role', 'banner').end().find("ul.opcions").attr('role', 'navigation');
	menu_elm.attr('role', 'complementary').attr('aria-live', 'polite').attr('aria-atomic', 'true').attr('aria-relevant', 'text additions');
	continguts_elm.attr('role', 'content');
	escriptori_elm.attr('aria-live', 'polite').attr('aria-atomic', 'true').attr('aria-relevant', 'text additions');
	escriptori_detall_elm.attr('aria-hidden', 'true').attr('aria-disabled', 'true').attr('aria-live', 'polite').attr('aria-atomic', 'true').attr('aria-relevant', 'text additions');
	*/
	
	// INICIEM
	Detall.iniciar();
	
});

// items array
var Items_arr = new Array();

// detall
var Detall = {
	iniciar: function() {
		
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
		//moduls_elm.css({"border-radius": "1em 1em 0 0", "-moz-border-radius": "1em 1em 0 0", "-webkit-border-radius": "1em 1em 0 0"});
		
		// modul publicacio
		//$("#modulLateral div.publicacio p.botonera").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});

		// carregar
		Detall.carregar();
		
	},
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
	llansar: function(e) {
		
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
			
			if (a_elm.hasClass("guarda")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.guarda();
				
			} else if (a_elm.hasClass("previsualitza")) {
				
				escriptori_detall_elm.unbind("click", Detall.llansar);
				
				Detall.previsualitza();
				
			}
		
		}
		
	},
	carregar: function(itemID) {
			
		if (itemID == undefined) 
		{
			itemID = $("#item_id").val();
		}				
		
		escriptori_detall_elm.find("a.elimina").show();
				
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
					Detall.pintar(data);
				/*	
					// missatge?
					if (data.json.missatge != "") {
						Missatge.llansar({tipus: "alerta", modo: json_mode, fundit: "si", titol: data.json.missatge});
					}
					*/
			//	}
			}
		});
		
	},
	pintar: function(dades) {
		
		dada_node = dades;
		if (dada_node.id != -1){							
								
			$("#item_id").val(dada_node.id);
			
			//Bloque de pestanyas de idiomas
			
			$("#item_nom_ca").val(printStringFromNull(dada_node.ca.nombre));
			$("#item_presentacio_ca").val(printStringFromNull(dada_node.ca.presentacion));
			$("#item_abreviatura_ca").val(printStringFromNull(dada_node.ca.abreviatura));

			$("#item_nom_es").val(printStringFromNull(dada_node.es.nombre));
			$("#item_presentacio_es").val(printStringFromNull(dada_node.es.presentacion));
			$("#item_abreviatura_es").val(printStringFromNull(dada_node.es.abreviatura));
			
			$("#item_nom_en").val(printStringFromNull(dada_node.en.nombre));
			$("#item_presentacio_en").val(printStringFromNull(dada_node.en.presentacion));
			$("#item_abreviatura_en").val(printStringFromNull(dada_node.en.abreviatura));
			
			$("#item_nom_de").val(printStringFromNull(dada_node.de.nombre));
			$("#item_presentacio_de").val(printStringFromNull(dada_node.de.presentacion));
			$("#item_abreviatura_de").val(printStringFromNull(dada_node.de.abreviatura));
			$("#item_url_de").val(printStringFromNull(dada_node.de.url));
			
			$("#item_nom_fr").val(printStringFromNull(dada_node.fr.nombre));
			$("#item_presentacio_fr").val(printStringFromNull(dada_node.fr.presentacion));
			$("#item_abreviatura_fr").val(printStringFromNull(dada_node.fr.abreviatura));
			
			//Configuracion / gestion
			
			$("#item_clau_hita").val(dada_node.item_clau_hita);
			$("#item_codi_estandar").val(dada_node.item_codi_estandar);
			$("#item_domini").val(dada_node.item_domini);
			marcarOpcionSelect("item_validacio",dada_node.item_validacio);
			//$("#item_validacio").val(dada_node.item_validacio);
			$("#item_telefon").val(dada_node.item_telefon);
			$("#item_fax").val(dada_node.item_fax);
			$("#item_email").val(dada_node.item_email);
			marcarOpcionSelect("item_espai_territorial",dada_node.item_espai_territorial);
			//$("#item_espai_territorial").val(dada_node.item_espai_territorial);
			$("#item_pare_id").val(dada_node.pareId);
			$("#item_pare").val(dada_node.pareNom);
			
			//Responsable			
			$("#item_responsable").val(dada_node.item_responsable);	
			marcarOpcionSelect("item_responsable_sexe",dada_node.item_responsable_sexe);
			//$("#item_responsable_sexe").val(dada_node.item_responsable_sexe);
			
			//TODO: se genera un error 1000 al asignar con val un archivo al input. Se comenta hasta poder solucionarlo
			$("#item_responsable_foto_petita_nom").val(dada_node.item_responsable_foto_petita);			
			$("#item_responsable_foto_gran_nom").val(dada_node.item_responsable_foto_gran);			
			$("#item_tractament").val(dada_node.item_tractament).attr('selected',true);			
			
			//Logotipos
			$("#item_logo_horizontal_nom").val(dada_node.item_logo_horizontal);
			$("#item_logo_vertical_nom").val(dada_node.item_logo_vertical);
			$("#item_logo_salutacio_horizontal_nom").val(dada_node.item_logo_salutacio_horizontal);
			$("#item_logo_salutacio_vertical_nom").val(dada_node.item_logo_salutacio_vertical);		
			
			
			//Fitxes de la portada web

			$("#item_nivell_1").val(dada_node.item_nivell_1);
			$("#item_nivell_2").val(dada_node.item_nivell_2);
			$("#item_nivell_3").val(dada_node.item_nivell_3);
			$("#item_nivell_4").val(dada_node.item_nivell_4);		
			
			
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
			
			//Edificis
			
			edi_seleccionats_elm = escriptori_detall_elm.find("div.modulEdificis div.seleccionats");
			edi_llistat_elm = escriptori_detall_elm.find("div.modulEdificis div.llistat");
			edificis_nodes = dada_node.edificis;
			edificis_nodes_size = edificis_nodes.length;
			
			edi_llistat_elm.find("input").removeAttr("checked");
			
			if (edificis_nodes_size == 0) {
				edi_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaEdificis + ".");
			} else {
				codi_edificis = "<ul>";
				$(edificis_nodes).each(function() {
					edifici_node = this;
					codi_edificis += "<li><input type=\"hidden\" value=\"" + edifici_node.id + "\" />" + edifici_node.nom + "</li>";
					edi_llistat_elm.find("input[value=" + edifici_node.id + "]").attr("checked","checked");
				});
				codi_edificis += "<ul>";
				txt_edificis = (edificis_nodes_size == 1) ? txtEdifici : txtEdificis;
				edi_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + edificis_nodes_size + " " + txt_edificis + "</strong>.").after(codi_edificis);
			}			
			
			/*
			// seccions
			secc_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccions div.seleccionats");
			secc_nodes = dada_node.seccions;
			secc_nodes_size = secc_nodes.length;
			
			if (secc_nodes_size == 0) {
				secc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccions + ".");
			} else {
				codi_seccions = "<ul>";
				$(secc_nodes).each(function() {
					secc_node = this;
					codi_seccions += "<li><a href=\"javascript:;\"><input type=\"hidden\" value=\"" + secc_node.id + "\" />" + secc_node.nom + "</a></li>";
				});
				codi_seccions += "<ul>";
				txt_seccions = (materes_nodes_size == 1) ? txtSeccio : txtSeccions;
				secc_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + secc_nodes_size + " " + txt_seccions + "</strong>.").after(codi_seccions);
			}
			*/
		} else {
			
			$("#escriptori_detall").html("<p class=\"noUnitat\">Es necessari triar primer una unitat administrativa</p>");			
			
		}
			
		// mostrem
		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				
				// array
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				
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
	torna: function() {
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			escriptori_contingut_elm.fadeIn(300, function() {
				// activar
				escriptori_contingut_elm.bind("click",Llistat.llansar);
			});
			
		});
		
	},
	eliminar: function() {
		
		// missatge
		Missatge.llansar({tipus: "confirmacion", modo: "atencio", fundit: "si", titol: txtEdificiEliminar, funcio : Detall.elimina});
		
	},
	elimina: function() {
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		
		tipusUnitat_ID = $("#tipusUnitat_id").val();
		
		dataVars = "accio=eliminar&id=" + tipusUnitat_ID;
				
		// ajax
		$.ajax({
			type: "POST",
			url: pagUnitat,
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
					
					// missatge?
					*/
					Missatge.cancelar();
					
					if (data.id > -1) {
						Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					}
					
					// array
					Detall.array({id: dada_node.id, accio: "elimina"});
					
					// recarregar
					Detall.recarregar();
					
				}
			//}
		});
		
		//Missatge.cancelar();
		
	},
	guarda: function() {
		
		// form comprobar
		FormulariComprovar.llansar();
		
		if (!formComprovacio) {
			return false;
		}
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		/*
		tipusUnitat_ID = $("#tipusUnitat_id").val();
		
		// json
		tipusUnitat_json = "{";
		
		tipusUnitat_json += "\"id\": \"" + tipusUnitat_ID + "\",";
		tipusUnitat_json += "\"codi\": \"" + $("#tipusUnitat_codi_estandar").val() + "\",";
		
		tipusUnitat_json += "\"idioma\": [";
		
		idioma_nodes = $("div.idiomes div.idioma");
		idioma_nodes_size = idioma_nodes.size();
		
		$(idioma_nodes).each(function(i) {
			
			idioma_nodo = $(this);
			
			tipusUnitat_json += "{";
				
			idioma_class = idioma_nodo.attr("class");
			idioma_codi = idioma_class.substr(idioma_class.indexOf(" ")+1,2);
			
			tipusUnitat_json += "\"" + idioma_codi + "\": [{";
			
			input_nodes = idioma_nodo.find("input");
			input_nodes_size = input_nodes.size();
			
			$(input_nodes).each(function(j) {
			
				idioma_node = $(this);
			
				tipusUnitat_json += "\"" + idioma_node.attr("id") + "\": \"" + idioma_node.val() + "\"";
				
				if (j != input_nodes_size-1) {
					tipusUnitat_json += ",";
				}
			
			});
			
			tipusUnitat_json += "}]";
			
			tipusUnitat_json += "}";
			
			if (i != idioma_nodes_size-1) {
				tipusUnitat_json += ",";
			}
		
		});
		
		tipusUnitat_json += "]";
		
		tipusUnitat_json += "}";
		
		//alert(tipusUnitat_json);
		//return false;
		
		accio = (tipusUnitat_ID == "") ? "guardar" : "modificar";
		
		dataVars = "accio=" + accio + "&dades=" + tipusUnitat_json;
		*/
		
		dataVars = $('#guardarUnitatAdministrativa').serialize();
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
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					Detall.recarregar();
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
				}
			}
		});
		
	},
	recarregar: function() {
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			div_L_elm = resultats_elm.find("div.L:first");
			carregant_codi = "<p class=\"executant\">" + txtCarregantUnitats + "</p>";
			div_L_elm.find("div.dades").html(carregant_codi);
			
			escriptori_contingut_elm.fadeIn(300, function() {
				
				// cercador o llistat?
				if (opcions_elm.find("li.actiu").hasClass("L")) {
					
					Unitats.carregar({});
					
				} else {
					
					L_elm = opcions_elm.find("li.L:first");
					
					// estils
					opcions_elm.find("li.actiu:first").html("<a href=\"javascript:;\">" + opcions_elm.find("li.actiu").html() + "</a>").removeClass("actiu");
					// opcio
					L_elm.html(L_elm.find("a:first").html()).addClass("actiu");
					
					// resultats
					resultats_elm.find("div.actiu:first").removeClass("actiu").slideUp(300,function() {
																															 
						$(this).find("div.dades").html("");
						
						div_L_elm.slideDown(300,function() {
							
							$(this).addClass("actiu");
							Unitats.carregar({});
							
						});
						
					});
					
				}
				
			});
			
		});
		
	},
	previsualitza: function() {
		
		escriptori_detall_elm.fadeOut(300, function() {
			
			fitxa_idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			fitxa_ID = escriptori_detall_elm.find("#item_id").val();
			
			previsualitza_url = "http://www.caib.es/govern/organigrama/area.do?lang=" + fitxa_idiomaSeleccionat + "&coduo=6"; //+ fitxa_ID;
			
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
		
	},
	array: function(opcions) {
		
		//opcions -> id (num); accio (guardar, eliminar); dades: json
		
		if (opcions.accio == "guarda") {
			
			Items_arr.push({id: opcions.id, dades: opcions.dades});
			
		} else if (opcions.accio == "elimina") {
			
			id_eliminat = 0;
			
			for (i=0; i<Items_arr.length; i++) {
				if (opcions.id == Items_arr[i].id) {
					id_eliminat = i;
				}
			}
			
			delete Items_arr[id_eliminat];
			
		}
		
	}
};











var Error = {
	llansar: function() {
		if (escriptori_detall_elm.css("display") != "none") {
			escriptori_detall_elm.attr('aria-hidden', 'true').attr('aria-disabled', 'true').fadeOut(300);
		}
		escriptori_elm.fadeOut(300, function() {
			segundos = 60;
			conex = setInterval("Error.conexion()",1000);
			codi = "<div id=\"error\">";
			codi += "<h1>" + txtAjaxError + "</h1>";
			codi += "<p><strong>" + txtFuncions + "</strong> " + txtFuncionsFins + ".</p>";
			codi += "<p>" + txtConexionIntentar + " <span id=\"temps\">" + segundos + " " + txtSegons + "</span>.</p>";
			codi += "<p><a onclick=\"Error.reiniciar();\">" + txtConectar + "</a></p>";
			codi += "</div>";
			// mostrem
			escriptori_elm.attr('aria-hidden', 'false').attr('aria-disabled', 'false').html(codi).fadeIn(300);
		});
	},
	conexion: function() {
		segundos--;
		if (segundos == 0) {
			Error.reiniciar();
		} else if (segundos == 1) {
			$("#temps").html(segundos + " " + txtSegon);
		} else {
			$("#temps").html(segundos + " " + txtSegons);
		}
	},
	reiniciar: function() {
		escriptori_elm.fadeOut(300, function() {
			if (conex) { clearInterval(conex); }
			// escriptori, carregant
			codi = "<p class=\"executant\">" + txtCargandoEntidades + "</p>";
			escriptori_elm.html(codi).fadeIn(300, function() {
				// INICIAMOS
				Entidades.carregar({entidad:entidad_ID});
			});
		});
	}
};

function EliminaArbreUA()
{
	$("#id_item_pare").val("");
	$("#item_pare").val("");
}

function posarValorsInput(idInput, valor)
{
	$(idInput).val(valor);
}
