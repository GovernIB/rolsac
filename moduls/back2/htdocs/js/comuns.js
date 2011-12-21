// CERRAR Y MENSAJES

$(document).ready(function(){
	
	$(window).bind('resize',function(e){
	    if ($('.falsePopUp').length > 0) {
	        $('.falsePopUp').css('left',$(window).width() / 2 - 275);
	    }
	});

	idiomas = ["ca", "es", "en", "fr", "de"];
	
	// elements
	contenidor_elm = $("#contenidor");
	cap_elm = $("#cap");
	cap_contingut_elm = $("#cap_contingut");
	menu_contingut_elm = $("#menu_contingut");
	mollaPa_elm = $("#mollaPa");
	mollaPa_contingut_elm = $("#mollaPa_contingut");
	continguts_elm = $("#continguts");
	suggeriment_elm = $("#suggeriment");
	peu_elm = $("#peu");
	
	// aria
	cap_elm.find("div.aplicacio strong").attr('role', 'banner').end().find("ul.opcions").attr('role', 'navigation');
	//menu_contingut_elm.find("a, span.text").css({"border-radius": ".5em", "-moz-border-radius": ".5em", "-webkit-border-radius": ".5em"});
	
	// dsanchez: Ahora todos los submenús tienen "colita".
	//menu_contingut_elm.find("li.seleccionat").append("<span class=\"cueta\">&nbsp;</span>");
	
	continguts_elm.attr('role', 'content');
	peu_elm.attr('role', 'contentinfo');
	
	// Cap tancar
	Cap.iniciar();
	
	// Tancar Aplicacio
	Aplicacio.iniciar();
	
	// fons
	Fons.iniciar();
	
	// atencio
	Atencio.activar();
	
	// suggeriments
	Suggeriment.iniciar();
	
	// ie6 form:onFocus
	if ($.browser.msie) {
		Formulari_focus.activar();
	}
	
	// enllaços
	As.iniciar("body");
	F5.activar();
	
	// molla pa
	MollaPa.iniciar();
	
	// taules mestre
	TaulesMestre.iniciar();
	
});

var TaulesMestre = {
	iniciar: function() {
		
		if ($("#taules_mestre").size() != 0) {
			
			taules_mestre_btn = $("#taules_mestre");
			taulesMestre_elm = $("#taulesMestre");
			
			//taulesMestre_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"}).css({"-webkit-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "-moz-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "box-shadow": "0 .5em 1.5em rgb(100,100,100)"})
			
			//taulesMestre_elm.find("span.titol:first").css({"border-radius": "1em 1em 0 0", "-moz-border-radius": "1em 1em 0 0", "-webkit-border-radius": "1em 1em 0 0"})
			
			taules_mestre_btn.bind("click",TaulesMestre.llansar);
			
		}
		
	},
	llansar: function() {
		
		taules_mestre_btn.unbind("click",TaulesMestre.llansar);
		
		/*elm_pos_T = taules_mestre_btn.offset().top;
		elm_pos_L = taules_mestre_btn.offset().left;*/
		// dsanchez: Restamos 3 píxels por cuestiones de diseño.
		elm_H = taules_mestre_btn.outerHeight()-5;
		
		//taulesMestre_elm.css({ top: elm_pos_T + elm_H + "px", left: elm_pos_L + "px" }).slideToggle(300, function() {
		taulesMestre_elm.css({ top: elm_H + "px" }).slideToggle(300, function() {
		
			taules_mestre_btn.bind("click",TaulesMestre.llansar);
			
			if (taulesMestre_elm.css("display") == "block") {
				$(window).one("click",TaulesMestre.tancar);
			}
			
		});
		
	},
	tancar : function() {
		
		taulesMestre_elm.slideUp(300);
		
	}
};


var pare_anterior_ID = 0;

var MollaPa = {
	iniciar: function() {
		ua_codi = "<ul id=\"uas\"></ul>";
		mollaPa_elm.append(ua_codi);
		uas_elm = $("#uas");			
		
		//mollaPa_contingut_elm.bind("click",MollaPa.llansar);		
		mollaPa_elm.find(".uaHijas a").bind("click",MollaPa.despliegaUnidades);
		
		$('#mollapaHome').bind("click", function() {			
            document.location.href = cambioMollaPa + "?redirectTo=" + document.location.href.split('?', 1)[0];
		});
	},			
	
	despliegaUnidades: function(e){
	//llansar: function(e) {

		elm = $(e.target);
				
		//if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
									
		btn_elm = elm.parents("a.btn:first");
		
		if (btn_elm.hasClass("uaFilles")) {
											
			pare_ID = btn_elm.parents("ul.molla:first").find("input:last").val();
											
			if (pare_ID == pare_anterior_ID) {
				
				uas_elm.slideDown(300, function() {
					$(window).bind("click",MollaPa.amagar);
				});
				
			} else {
												
				uas_elm.html("<li><span class=\"carregant\">" + txtCarregantMollaFills + "</span></li>")
				
				a_T = elm.position().top;
				//a_L = elm.position().left;
				a_L = elm.parents("li").position().left;
				a_H = elm.outerHeight();
				
				uas_elm.css({ top: (a_T + a_H) + "px", left: a_L + "px" }).fadeIn(300, function() {					
					
					dataVars = "id=" + pare_ID;
			
					// ajax
					$.ajax({
						type: "POST",
						url: pagMollaPa,
						data: dataVars,
						dataType: "json",
						error: function() {
							
							// missatge
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
							// error
							Error.llansar();
							
						},
						success: function(data) {								
							error = false;
							error_msg = "";
							nodes_codi = "";								
							
							$(data).each(function() {
								node_ua = this;
								if (node_ua.id < 0) {
									error = true;
									error_msg = node_ua.nom;
								} else {
									nodes_codi += "<li><a href=\"" + cambioMollaPa;
									nodes_codi += "?ua=" + node_ua.id;
									nodes_codi += "&redirectTo=" + document.location.href.split('?', 1)[0];
									nodes_codi += "\" class=\"n\">";
									nodes_codi += node_ua.nom + "</a></li>";
								}
							});
							
							uas_elm.slideUp(300, function() {
								pare_anterior_ID = pare_ID;
								$(this).html(nodes_codi).slideDown(300, function() {
									$(window).bind("click",MollaPa.amagar);
									As.iniciar("#uas");
								});
							});
							
							if (error) {
								Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: error_msg});
							}
						}
					});				
				});			
			}			
		}
		//}
	},
	amagar: function(e) {
		elm = $(e.target);
		if (!elm.hasClass("n")) {
			$("#uas").fadeOut(300, function() {
				$(window).unbind("click",MollaPa.amagar);
			});
		}
	}
};

var a_enllas = false;
var As = {
	iniciar: function(elm) {
		$(elm + " a").each(function(i) {
			a_elm = $(this);
			if (a_elm.attr("href") != "#" && a_elm.attr("href") != "javascript:;") {
				a_elm.bind("click",As.llansar);
			}
		});
	},
	llansar: function() {
		a_enllas = true;
	}
};

f5_polsat = false;
var F5 = {
	activar: function() {
		$(document).bind("keydown",F5.llansar).bind("keyup",F5.desllansar);
	},
	llansar: function(e) {
		if (e.keyCode == 116) {
			f5_polsat = true;
		}
	},
	desllansar: function() {
		f5_polsat = false;
	}
};

var Formulari_focus = {
	activar: function() {
		$("input, textarea, select").bind("focus",function() {
			$(this).addClass("focus");
		}).bind("blur",function() {
			$(this).removeClass("focus");
		});
	}
};

var Suggeriment = {
	iniciar: function() {
		
		$("#suggeriment_contingut").css({"border-radius": "2em", "-moz-border-radius": "2em", "-webkit-border-radius": "2em"});
		suggeriment_elm.find("a.tancar").bind("click",Suggeriment.tancar);
		
	},
	tancar: function() {
		
		suggeriment_elm.animate({
			duration: "slow", height: "0px", opacity: "0"
			}, 300, function() {
				$(this).remove();
			});
		
	}
};

var Cap = {
	iniciar: function() {
		$("#cap_pestanya").bind("click",Cap.llansar);
	},
	llansar: function() {
	
		jQuery("#cap_contingut").slideToggle(300);				
		
	}/*,
	ajax: function(valor) {
		
		// variables
		dataVars = "valor=" + valor;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagCap,
			data: dataVars,
			dataType: "json",
			error: function() {
			},
			success: function(data) {
			}
		});
	}*/
};

var eixir = false;

var Aplicacio = {
	iniciar: function() {
		// aria y evento
		$("#tancarAplicacio").attr('aria-haspopup', 'true').click(Aplicacio.llansar);
	},
	llansar: function() {
		Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTancarAplicacio, funcio: Aplicacio.tancar});
		eixir = true;
	},
	tancar: function() {
		window.location.href = pagTancarAplicacio;
	}
};

var Atencio = {
	activar: function() {
		$("a.marcador").bind("click",Atencio.llansar);
	},
	llansar: function(e) {
		elm_id = $(this).attr("id");
		elm_id_arr = elm_id.split("_");
		contenidor_elm.find('a.' + elm_id_arr[2]).seekAttention({ paddingTop: 5, paddingBottom: 8, paddingLeft: 5, paddingRight: 5 });
	}
};

var teclat_especials = false;
var Teclat = {
	activar: function() {
		
		$(document).bind("keydown",Teclat.llansar).bind("keyup",Teclat.up);
		
	},
	desactivar: function() {
		
		$(document).unbind("keydown",Teclat.llansar).unbind("keyup",Teclat.up);
		
	},
	aplicacio: function() {
		
		aplicacio_nodes = $("body").find("#missatge, #missatge a");
		
		aplicacio_nodes_size = aplicacio_nodes.size();
		aplicacio_node = 0;
		
		Teclat.activar();
		
	},
	llansar: function(e) {
		elm = $(e.target);
		if (!elm.is("INPUT") && !elm.is("TEXTAREA") && !teclat_especials) {
			
			if (e.keyCode == 16 || e.keyCode == 17 || e.keyCode == 18) {
				// 16 -> Shift, 17 -> Ctrl, 18 -> Alt
				teclat_especials = true;
			}
			
			// 9 -> Tab
			if (e.keyCode == 9) {
				
				aplicacio_node = aplicacio_node + 1;
				if (aplicacio_nodes_size == aplicacio_node) {
					aplicacio_node = 0;
				}
				
				aplicacio_nodes.eq(aplicacio_node).focus();
				
				e.preventDefault();
				
			}
			
		}
		
	},
	up: function(e) {
		
		if (e.keyCode == 16 || e.keyCode == 17 || e.keyCode == 18) {
			teclat_especials = false;
		}
		
	}
};

var esIE6 = false;

/*
llansar
- tipus: missatge, alerta, confirmacio
- modo: executant, atencio, correcte, error, informacion
- titol: text
- text: text con etiquetas HTML
- fundit: si/no
- funcio: function() { } // funcio ejecutada al acceptar
- funcioAlMostrar: function() { } // funcio ejecutada al mostrar
*/
var Missatge = {
	llansar: function(opcions) {
		
		// fons
		Tamanys.iniciar();
		Fons.mostrar();
		// crear
		if ($("#missatge").size() === 0) {
			$("<div>").attr("id","missatge").attr({"tabindex": "-1", "role": "alertdialog"}).css({"-webkit-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "-moz-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "box-shadow": "0 .5em 1.5em rgb(100,100,100)"}).appendTo("body");
		}
		missatge_elm = $("#missatge");
		// fundit
		if (typeof opcions.fundit != "undefined" && opcions.fundit == "si") {
			// teclat
			if (typeof Teclat != "undefined") {
				// teclat
				Teclat.desactivar();
			}
			missatge_elm.fadeOut(300, function() {
				Missatge.pintar(opcions);
			});
		} else {
			Missatge.pintar(opcions);
		}
	},
	pintar: function(opcions) {
		
		// pintar
		codi_missatge = "<p class=\"titol\">" + opcions.titol + "</p>";
		if (typeof opcions.text != "undefined" && opcions.text != "") {
			codi_missatge += opcions.text;
		}
		if (opcions.tipus == "alerta" || opcions.tipus == "confirmacio") {
			codi_missatge += "<div class=\"botonera\">";
			//codi_missatge += "<button id=\"missatge_acceptar\" type=\"button\">" + txtAccepta + "</button>";
			codi_missatge += "<a id=\"missatge_acceptar\" href=\"javascript:;\" class=\"btn acceptar important\"><span><span>" + txtAccepta + "</span></span></a>";
			if (opcions.tipus == "confirmacio") {
				codi_missatge += ", o <a id=\"missatge_cancelar\" href=\"javascript:;\">" + txtCancela + "</a>";
			}
			codi_missatge += "</div>";
		}
		missatge_elm.html(codi_missatge).attr("class",opcions.modo);
		if (typeof opcions.funcio != "undefined" && opcions.funcio != "") {
			$("#missatge_acceptar").bind("click",opcions.funcio);
			if (opcions.tipus == "confirmacio") {
				$("#missatge_cancelar").bind("click",Missatge.cancelar);
			}
		} else {
			$("#missatge_acceptar").bind("click",Missatge.cancelar);
		}
		// posicion
		capa_W = missatge_elm.outerWidth();
		capa_H = missatge_elm.outerHeight();
		esIE6 = ($.browser.msie && $.browser.version < 7) ? true : false;
		if (!esIE6) {
			missatge_elm.css({top: (finestra_H-capa_H)/2+"px", left: (finestra_W-capa_W)/2+"px"});
		} else {
			missatge_ie6 = true;
			missatge_elm.css({position: "absolute", top: $(document).scrollTop()+((finestra_H-capa_H)/2)+"px", left: (finestra_W-capa_W)/2+"px"});
			$(window).bind("scroll",Missatge.scrollear);
			Selects.esconder();
		}
		// mostrar
		missatge_elm.fadeIn(300, function() {
			missatge_elm.focus();
			// funcio al mostrar el missatge
			if (typeof opcions.funcioAlMostrar != "undefined" && opcions.funcioAlMostrar != "") {
				opcions.funcioAlMostrar();
			}
			// posicionar
			$(window).bind("resize",Missatge.posicionar);
			// teclat
			if (typeof Teclat != "undefined") {
				// teclat
				Teclat.aplicacio();
			}
		});
	},
	cancelar: function() {
		missatge_elm.fadeOut(300, function() {
			missatge_elm.remove();
			Fons.esconder();
		});
		exit = false;
		if (esIE6) {
			$(window).unbind("scroll",Missatge.scrollear);
			esIE6 = false;
			Selects.mostrar();
		}
		// teclat
		if (typeof Teclat != "undefined") {
			// teclat
			Teclat.desactivar();
		}
	},
	scrollear: function() {
		$("#missatge").css("top",$(document).scrollTop()+((finestra_H-capa_H)/2)+"px");
	},
	posicionar: function() {
		if ($("#missatge").size() !== 0) {
			Tamanys.iniciar();
			Fons.mostrar();
			// posicion
			capa_W = missatge_elm.outerWidth();
			capa_H = missatge_elm.outerHeight();
			top_val = (!esIE6) ? (finestra_H-capa_H)/2+"px" : $(document).scrollTop()+((finestra_H-capa_H)/2)+"px";
			left_val = (finestra_W-capa_W)/2+"px";
			missatge_elm.animate({
				duration: "slow", top: top_val, left: left_val
				}, 300);
		}
	}
};

var Fons = {
	iniciar: function() {
		$("<div>").attr("id","fons").appendTo("body");
		fons_elm = $("#fons");
		$(window).bind("resize",Fons.redimensionar);
	},
	mostrar: function() {
		fons_W = (finestra_W > contenidor_W) ? finestra_W : contenidor_W;
		fons_H = (finestra_H > contenidor_H) ? finestra_H : contenidor_H;
		fons_elm.css({display: "block", width: fons_W+"px", height: fons_H+"px", opacity: 0.5});
	},
	esconder: function() {
		fons_elm.hide();
	},
	redimensionar: function() {
		if (fons_elm.css("display") != "none") {
			Tamanys.iniciar();
			fons_W = (finestra_W > contenidor_W) ? finestra_W : contenidor_W;
			fons_H = (finestra_H > contenidor_H) ? finestra_H : contenidor_H;
			fons_elm.css({width: fons_W+"px", height: fons_H+"px"});
		}
	}
};

var Tamanys = {
	iniciar: function() {
		finestra_W = $(window).width();
		finestra_H = $(window).height();
		contenidor_W = contenidor_elm.outerWidth();
		contenidor_H = contenidor_elm.outerHeight();
		return finestra_W, finestra_H, contenidor_W, contenidor_H;
	}
};

var Selects = {
	esconder: function() {
		contenidor_elm.find("select").css("display","none");
	},
	mostrar: function() {
		contenidor_elm.find("select").css("display","inline");
	}
};

//window.onbeforeunload = function(e) {
//	if (!eixir && !a_enllas && !f5_polsat && !$.browser.msie) {
//		var e = e || window.event;
//		if (e) {
//			e.returnValue = txtEixirAplicacio;
//		}
//		return txtEixirAplicacio;
//	}
//};

function diesEnMes(mes, any) {
  return new Date(any || new Date().getFullYear(), mes, 0).getDate();
}

function esAnyDeTraspas(any) {
  return new Date(any, 0, 366).getFullYear() == any;
}

// suggeriments
( function($) {
	
	var suggeriments_resultats_elm;
	
	$.suggeriments = function() {
		
		$(suggeriments).each( function() {
			dada = this;
			$("#" + dada.etiquetaValor).bind("keyup",$.suggeriments.cercar).bind("blur",$.suggeriments.eixir);
		});
		
		$("<ul id=\"suggeriments_resultats\">").css({"-webkit-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "-moz-box-shadow": "0 .5em 1.5em rgb(100,100,100)", "box-shadow": "0 .5em 1.5em rgb(100,100,100)"}).appendTo("body");
		suggeriments_resultats_elm = $("#suggeriments_resultats");

	};
	
	var cercador_activat, pagina_dades, tamany;
	
	$.suggeriments.cercar = function(e) {
		
		cercador_activat = true;
		pagina_dades = false;
		tamany = 0;
		
		input_cerca_elm = $(this);
		input_cerca_text = input_cerca_elm.val();
		input_cerca_clase = input_cerca_elm.attr("class");
		if (input_cerca_clase.indexOf(" ") != -1) {
			input_cerca_clase = input_cerca_clase.substr(0,input_cerca_clase.indexOf(" "));
		}
		
		input_cerca_text_real = (input_cerca_text.indexOf("_") != -1) ? input_cerca_text.substr(0,input_cerca_text.indexOf("_")) : input_cerca_text;
		
		$(suggeriments).each( function() {
			dada = this;
			if (input_cerca_elm.attr("id") == dada.etiquetaValor) {
				pagina_ajax = dada.pagina;
				if (typeof dada.dades != "undefined") {
					pagina_dades = dada.dades;
				}
				tamany = (typeof dada.tamany != "undefined") ? parseInt(dada.tamany,10) : 0;
			}
		});
		
		if (input_cerca_text != "" && input_cerca_text_real.length > tamany && ((e.keyCode >= 48 && e.keyCode <= 90) || e.keyCode == 8)) {
		
			// variables
			dataVars = "text=" + input_cerca_text_real + "&clase=" + input_cerca_clase;
			
			// ajax
			$.ajax({
				type: "POST",
				url: pagina_ajax,
				data: dataVars,
				dataType: "json",
				error: function() {
					// error cap
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
						
						// error cap
						
					} else {
						
						input_cerca_elm_T = input_cerca_elm.offset().top;
						input_cerca_elm_L = input_cerca_elm.offset().left;
						input_cerca_elm_H = input_cerca_elm.outerHeight();
						
						// data
						
						cerca_nodes = data.json.data.nodes;
						cerca_nodes_size = cerca_nodes.length;
						
						cerca_codi = "";
						
						if (cerca_nodes_size != 0) {
							
							$(cerca_nodes).each(function(i) {
								cerca_node = this;
								clase = (i == 0) ? " class=\"selec\"": "";
								cerca_codi += "<li" + clase + ">";
								cerca_codi += "<a>";
								
								if (typeof cerca_node.id != "undefined") {
									cerca_codi += "<input type=\"hidden\" value=\"" + cerca_node.id + "\" class=\"id\" />";
								}
								
								cerca_codi += "<input type=\"hidden\" value=\"" + cerca_node.valor + "\" class=\"valor\" />";
								
								if (pagina_dades != false) {
									
									$(pagina_dades).each(function(j) {
										pagina_dada = this;
										cerca_codi += "<input type=\"hidden\" value=\"" + cerca_node.dades[j].valor + "\" class=\"mes " + pagina_dada.camp + "\" />";
									});
									
								}
								
								cerca_codi += cerca_node.html;
								cerca_codi += "</a>";
								cerca_codi += "</li>";
								
							});
							
							if (cercador_activat) {
								suggeriments_resultats_elm.css({display:"block",top:input_cerca_elm_T+input_cerca_elm_H+"px",left:input_cerca_elm_L+"px"}).html(cerca_codi).bind("click",$.suggeriments.marcar);
								$("body").bind("keyup",$.suggeriments.resultats);
							} else {
								$.suggeriments.eixir_llansar();
							}
						
						} else {
							
							$.suggeriments.eixir_llansar();
							
						}
						
						cerca_nodes_selec = 0;
						cercador_activat = false;
						
					}
					
				}
			});
		
		}
		
	};
		
	$.suggeriments.eixir = function() {
		setTimeout("$.suggeriments.eixir_llansar()",150);
	};
	
	$.suggeriments.eixir_llansar = function() {
		suggeriments_resultats_elm.hide();
		cercador_activat = false;
		$("body").unbind("keyup",$.suggeriments.resultats);
		suggeriments_resultats_elm.html("").unbind("click",$.suggeriments.marcar);
	};
	
	$.suggeriments.resultats = function(e) {
		cerca_target_elm = $(e.target);
	
		if (cerca_nodes_size > 1) {
		
			if (e.keyCode == "38") {
				cerca_nodes_selec--;
				cerca_nodes_selec = (cerca_nodes_selec < 0) ? cerca_nodes_size - 1 : cerca_nodes_selec;
			} else if (e.keyCode == "40") {
				cerca_nodes_selec++;
				cerca_nodes_selec = (cerca_nodes_selec > cerca_nodes_size - 1) ? 0 : cerca_nodes_selec;
			}
			
			if (e.keyCode == "38" || e.keyCode == "40") {
				suggeriments_resultats_elm.find("li").removeClass("selec").end().find("li:eq(" + cerca_nodes_selec + ")").addClass("selec");
			}
		
		}
		
		if (cerca_target_elm.is("INPUT") && e.keyCode == "13") {
			li_selec = suggeriments_resultats_elm.find("li.selec:first");
			
			item_ID = li_selec.find("input.id:first").val();
			if (item_ID != "" && !isNaN(item_ID)) {
				
				escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
				Detall.carregar(item_ID);
				
			} else {
			
				input_cerca_elm.val(li_selec.find("input.valor:first").val());
				
				li_selec.find("input.mes").each( function() {
					mes_dada = $(this);
					mes_dada_class = mes_dada.attr("class");
					camp_id = mes_dada_class.substr(mes_dada_class.indexOf(" ")+1);
					$("#" + camp_id).val(mes_dada.val());
				});
			
			}
			
			$.suggeriments.eixir_llansar();
		}
	};
	
	$.suggeriments.marcar = function(e) {
		cerca_target_elm = $(e.target);
	
		if (cerca_target_elm.is("A") || cerca_target_elm.is("STRONG")) {
			if (cerca_target_elm.is("STRONG")) {
				cerca_target_elm = cerca_target_elm.parent();
			}
			
			item_ID = cerca_target_elm.find("input.id:first").val();
			if (item_ID != "" && !isNaN(item_ID)) {
				
				escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
				Detall.carregar(item_ID);
				
			} else {
			
				input_cerca_elm.val(cerca_target_elm.find("input.valor:first").val());
				
				cerca_target_elm.find("input.mes").each( function() {
					mes_dada = $(this);
					mes_dada_class = mes_dada.attr("class");
					camp_id = mes_dada_class.substr(mes_dada_class.indexOf(" ")+1);
					$("#" + camp_id).val(mes_dada.val());
				});
			
			}
			
			$.suggeriments.eixir_llansar();
		}
	};
	
})(jQuery);

// error
var segundosError = 0;
var opcionsError = "";
var conexionError = false;
var Error = {
	iniciar: function(opcions) {
		// opcions -> tipus: (tareas, tarea, avisos, aviso, expedientes) - conexion: (si, no) - desde: (ajax, servidor) - data: (xml)
		
		opcionsError = opcions;
		
		if (conexionError) { clearInterval(conexionError); }
		
		if (opcionsError.desde == "ajax") {
		
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
		
		} else {
			/*
			error_nodo = $(opcionsError.data).find("error");
			codiErr = error_nodo.find('codi').text();
			descripcion = error_nodo.find('descripcion').text();
			descripcion_html = (codiErr == "") ?  "<p>" + descripcion + "</p>" : "<p><span style=\"display:none;\">" + codiErr + " </span>" + descripcion + "</p>";
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxErrorLogic, text: descripcion_html});
			*/
			
			if (opcionsError.json_mode == "fatal") {
			
				Missatge.llansar({tipus: "alerta", modo: opcionsError.json_mode, fundit: "si", titol: opcionsError.missatge, funcio: Aplicacio.tancar});
				$("#contenidor").html("");
				return false;
			
			} else {
				
				Missatge.llansar({tipus: "alerta", modo: opcionsError.json_mode, fundit: "si", titol: txtAjaxError, text: "<p>" + opcionsError.missatge + "</p>"});
				
			}
			
		}
		
		if (opcionsError.conexion == "no") {
			
			if (opcionsError.tipus == "totals") {
				
				numTotal_elm.removeClass("carregant").html("<a href=\"javascript:;\">" + txtCalcularTotal + "</a>");
				
			}
			
			
			
			
			
			if (typeof Avisos != "undefined") {
				if (cercador_avisos_existe) { btnBuscar_avisos_elm.removeAttr("disabled").attr('aria-disabled', 'false'); }
				continguts_avisos_elm.bind("click",Avisos.llansar);
			} else if (typeof Expedientes != "undefined" && typeof Planes == "undefined") {
				
				continguts_exp_elm.find("div.carregant").slideToggle("fast", function() {
					$(this).remove();
					if (cercador_exp_existe) { btnBuscar_exp_elm.removeAttr("disabled").attr('aria-disabled', 'false').text(txtBusca); }
					continguts_exp_elm.bind("click",Expedientes.llansar);
				});
				
				if (continguts_tareas_elm.css("display") == "block") {
					continguts_tareas_elm.bind("click",Tareas.llansar);
				}
			
			} else if (typeof Planes != "undefined") {
				
				div_carregant_elm = continguts_plan_elm.find("div.carregant");
				
				if (div_carregant_elm.css("display") == "block") {
					
					continguts_plan_elm.find("div.carregant").slideToggle("fast", function() {
						$(this).remove();
						if (cercador_plan_existe) { btnBuscar_plan_elm.removeAttr("disabled").attr('aria-disabled', 'false').text(txtBusca); }
						continguts_plan_elm.bind("click",Planes.llansar);
					});
				
				} else {
					
					div_carregant_elm.remove();
					if (cercador_plan_existe) { btnBuscar_plan_elm.removeAttr("disabled").attr('aria-disabled', 'false').text(txtBusca); }
					continguts_plan_elm.bind("click",Planes.llansar);
					
					if (continguts_tareas_elm.css("display") == "block") {
						continguts_tareas_elm.bind("click",Tareas.llansar);
					}
				
				}
				
			} else {
				if (cercador_tareas_existe) { btnBuscar_tareas_elm.removeAttr("disabled").attr('aria-disabled', 'false'); }
				continguts_tareas_elm.bind("click",Tareas.llansar);
			}
			
		} else {
			
			if (opcionsError.tipus == "totals") {
				
				/*
				escriptori_agenda_elm.fadeOut(300, function() {
					segundosError = 60;
					conexionError = setInterval("Error.conexion()",1000);
					codi = "<p class=\"noItems\">" + txtAjaxError + "</p><p>" + txtConexionIntentar + " <span id=\"temps\">" + segundosError + " " + txtSegons + "</span>.</p><p><a onclick=\"Error.reiniciar();\">" + txtConectar + "</a></p>";
					listadoInfo_elm.hide();
					agendaDIV_elm.html(codi).show();
					escriptori_agenda_elm.fadeIn(300);
				});
				*/
				
				numTotal_elm.removeClass("carregant").html("<a href=\"javascript:;\">Calcular totals</a>");
				
			} else {
				
				if (opcionsError.tipus == "tareas") {
					escriptori_base_elm = escriptori_tareas_elm;
				} else if (opcionsError.tipus == "avisos") {
					escriptori_base_elm = escriptori_avisos_elm;
				} else if (opcionsError.tipus == "expedientes") {
					escriptori_base_elm = escriptori_exp_elm;
				} else if (opcionsError.tipus == "planes") {
					escriptori_base_elm = escriptori_plan_elm;
				} else 
				
				escriptori_base_elm.fadeOut(300, function() {
					segundosError = 60;
					conexionError = setInterval("Error.conexion()",1000);
					codi = "<p class=\"noItems\">" + txtAjaxError + "</p><p>" + txtConexionIntentar + " <span id=\"temps\">" + segundosError + " " + txtSegons + "</span>.</p><p><a onclick=\"Error.reiniciar();\">" + txtConectar + "</a></p>";
					escriptori_base_elm.html(codi).fadeIn(300, function() {
						
						if (opcionsError.tipus == "tareas") {
							if (cercador_tareas_existe) { btnBuscar_tareas_elm.removeAttr("disabled").attr('aria-disabled', 'false'); }
							continguts_tareas_elm.bind("click",Tareas.llansar);
						} else if (opcionsError.tipus == "avisos") {
							if (cercador_avisos_existe) { btnBuscar_avisos_elm.removeAttr("disabled").attr('aria-disabled', 'false'); }
							continguts_avisos_elm.bind("click",Avisos.llansar);
						} else if (opcionsError.tipus == "expedientes") {
							if (cercador_exp_existe) { btnBuscar_exp_elm.removeAttr("disabled").attr('aria-disabled', 'false').text(txtBusca); }
							continguts_exp_elm.bind("click",Expedientes.llansar);
						} else if (opcionsError.tipus == "planes") {
							if (cercador_plan_existe) { btnBuscar_plan_elm.removeAttr("disabled").attr('aria-disabled', 'false').text(txtBusca); }
							continguts_plan_elm.bind("click",Planes.llansar);
						}
						
					});
				});
			
			}
			
		}
		
	},
	conexion: function() {
		segundosError--;
		if (segundosError == 0) {
			Error.reiniciar();
		} else if (segundosError == 1) {
			$("#temps").html(segundosError + " " + txtSegon);
		} else {
			$("#temps").html(segundosError + " " + txtSegons);
		}
	},
	reiniciar: function() {
		
		if (opcionsError.tipus == "agenda") {
			
			escriptori_agenda_elm.fadeOut(300, function() {
				if (conexionError) { clearInterval(conexionError); }
				
				listadoInfo_elm.html(txtCargandoCalendario).show();
				agendaDIV_elm.hide();
					
				escriptori_agenda_elm.fadeIn(300, function() {
					Mensual.llansar();
				});
				
			});
			
		} else {
		
			escriptori_base_elm.fadeOut(300, function() {
				if (conexionError) { clearInterval(conexionError); }
				
				if (opcionsError.tipus == "tareas") {
					txtCargandoReinici = txtCargandoTareas;
				} else if (opcionsError.tipus == "avisos") {
					txtCargandoReinici = txtCargandoAvisos;
				} else if (opcionsError.tipus == "expedientes") {
					txtCargandoReinici = txtCargandoExpedientes;
				} else if (opcionsError.tipus == "planes") {
					txtCargandoReinici = txtCargandoPlanes;
				}
				
				codi = "<p class=\"executant\">" + txtCargandoReinici + "</p>";
				escriptori_base_elm.html(codi).fadeIn(300, function() {
					
					if (opcionsError.tipus == "tareas") {
						if (estat_tar == "") { estat = "U"; }
						Tareas.cargar({estat: estat_tar});
					} else if (opcionsError.tipus == "avisos") {
						Avisos.cargar({estat: ""});
					} else if (opcionsError.tipus == "expedientes") {
						Expedientes.cargar({estat: ""});
					} else if (opcionsError.tipus == "planes") {
						Planes.cargar({estat: ""});
					}
					
				});
			});
			
		}
		
	}
};

//Convierte los null en ''
function printStringFromNull(data, defaultValue) {
	if (data == null){
        if (defaultValue != null && defaultValue != undefined){
            return defaultValue;
        }
	    return '';
	}
    return data;
}

//Lanza un popup
function obrir(url, name, x, y) {
   nombre = window.open(url, name, 'scrollbars=no, resizable=yes, width=' + x + ',height=' + y);
   return nombre;
}

//Seleccionar una UA

function ArbreUA(id_input, id_hidden){
    obrir(popupUA + "?idUA=0&idInput=" + id_input + "&idHidden=" + id_hidden, "Arbre de UA", 538, 440);
}

function EliminaArbreUA(id_input, id_hidden){
	$("#" + id_input).val("");
	$("#" + id_hidden).val("");
	$("#" + id_input).change();
	$("#" + id_hidden).change();
}


//Limpiar un input de un formulario excepto los campos hidden, readonly y disabled.
//Se usa como callback de un .each: $('selector').each(limpiarCampo).
function limpiarCampo(index, input){
	if (!input.readOnly && !input.disabled) {
		var type = input.type;
		var tag = input.tagName.toLowerCase();
		if (type == 'text' || type == 'password' || type == 'file' || tag == 'textarea') {
			jQuery(input).val('');
	    } else if (type == 'checkbox' || type == 'radio') {
	    	input.checked = false;
	    } else if (tag == 'select') {
	    	input.selectedIndex = 0;
    	}
	}
}

//Marcar opcion dentro de un select
function marcarOpcionSelect(select, id){
	var combo = document.getElementById(select);
	var cantidad = combo.length;
	for (i = 0; i < cantidad; i++) {

	     if (combo[i].value == id) {
	         combo[i].selected = true;
	     }   
	}
}

//Not null. Si el valor es null devuelve cadena vacía, de lo contrario devuelve el valor
function nn(valor) {
	if (valor == "null" || valor == null)
		return "";
	else
		return valor;
}


function carregarArbreUA (url, idDiv, id_ua, id_ua_texte, llocOnPintar ){
	
	// Aseguram que no estigui creat
	if ($('#' + idDiv).length == 0 ) {
		if (typeof llocOnPintar == 'undefined') {
			$('body').append('<div id="'+ idDiv + '" class="falsePopUp" style="left:'+(($(document).width() / 2) - 275)+'px"><iframe src="' + url + '?idUA=0&idInput='+ id_ua_texte + '&idHidden=' + id_ua +'" style="width:550px; height:450px;" /></div>');
		} else {
			$(llocOnPintar).append('<div id="'+ idDiv + '"><iframe src="' + url + '?idUA=0&idInput='+ id_ua_texte + '&idHidden=' + id_ua +'"  /></div>');
		}
	}
}



function borrarPopUp(idDiv){
	$('#' + idDiv, window.top.document).remove();
}


/*
 * Mira si en el queryString hay un 'itemId' y devuelve su valor. Si no existe devuelve 0. 
 * Usado para determinar si hay que mostrar el listado o la edicion de un elemento al cargar la pagina.
 */ 
function itemAEditar() {
	var queryString = location.search;
	if (queryString.length > 0) {
		queryString = queryString.slice(1); // quitar el '?'
		var params = queryString.split('&'); 
		for (var i=0; i<params.length; i++) {
			var param = params[i].split('=');
			if (param.length > 1 && param[0] == 'itemId') {
				return param[1];
			}
		}
	}
	return 0;
}


function carregarModulArbreUA (url, idDiv, llocOnPintar ){
	
	// Aseguram que no estigui creat
	if ($('#' + idDiv).length == 0 ) {
		if (typeof llocOnPintar == 'undefined') {
			$('body').append('<div id="'+ idDiv + '" class="falsePopUp" style="left:'+(($(document).width() / 2) - 275)+'px"><iframe src="' + url + '?idUA=0" style="width:550px; height:450px;" /></div>');
		} else {
			$(llocOnPintar).append('<div id="'+ idDiv + '"><iframe src="' + url + '?idUA=0"  /></div>');
		}
	}
}

function carregarCoordenades (url, idDiv, idEdifici, latitud, longitud, llocOnPintar ){
	var id = $('#'+ idEdifici).val();
	
	// Aseguram que no estigui creat
	if ($('#' + idDiv).length == 0 ) {
		if (typeof llocOnPintar == 'undefined') {
			$('body').append('<div id="'+ idDiv + '" class="falsePopUp" style="left:'+(($(document).width() / 2) - 380)+'px"><iframe src="' + url + '?id=' + id + '&latitud='+ latitud + '&longitud=' + longitud +'" style="width:760px; height:510px;" /></div>');
		} else {
			$(llocOnPintar).append('<div id="'+ idDiv + '"><iframe src="' + url + '?id=' + id + '&latitud='+ latitud + '&longitud=' + longitud +'"  /></div>');
		}
	}
}


