// TM SIA

$(document).ready(function() {
	// elements
	inicializarBtn();
	
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

	//escriptori_detall_elm = $("#escriptori_detall");

	// INICIEM
	Llistat = new CLlistat();
   
	//LLAMAMOS AL LISTADO DE JOBS.
	Llistat.inicializarJobs();
	
});


function inicializarBtn() {
	
	jQuery("#btnContinuar").unbind("click").bind("click", function() {
		
        var $btn = jQuery(this);
        var url;        

		if ($btn.hasClass('unitatOrganica')) {
            url = pagEnviarTodo;
		} 
        
        $.ajax({
            type: "POST",
            url: url, 
            dataType: "json",
            error: function() {					
                // missatge
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                
            },
            success: function(data) {
            	 if (!data.error) {
                  	Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEnviantDades});			
                  } else {
                      Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});					
                  }
            }
        });
	});
	
	jQuery("#btnCerrarJobs").unbind("click").bind("click", function() {
		var $btn = jQuery(this);
        var url;        

		if ($btn.hasClass('unitatOrganica')) {
            url = pagCerrarJobs;
		} 
        
        $.ajax({
            type: "POST",
            url: url, 
            dataType: "json",
            error: function() {					
                // missatge
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                
            },
            success: function(data) {
            	 if (!data.error) {
                  	Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtCerrantJobs});			
                  } else {
                      Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});					
                  }
            }
        });
	});
	
	
	    
	jQuery('#close').unbind("click").bind("click", function(){
	        $('.popup').fadeOut('slow');
	        $('.popup-overlay').fadeOut('slow');
	        $("#item_texto").val("");
	        return false;
	});
	
}

function incluirSaltosLinea(descripcion) {
	 while (descripcion.indexOf("<br />") != -1) {
		 descripcion = descripcion.replace("<br />","\n");
	 }
	 while (descripcion.indexOf("<br/>") != -1) {
		 descripcion = descripcion.replace("<br/>","\n");
	 }
	 while (descripcion.indexOf("<br>") != -1) {
		 descripcion = descripcion.replace("<br>","\n");
	 }
	 while (descripcion.indexOf("<br >") != -1) {
		 descripcion = descripcion.replace("<br >","\n");
	 }
	 return descripcion;
}

function pintarPopUp(descripcion) {
	 $('.popup').fadeIn('slow');
     $('.popup-overlay').fadeIn('slow');
     $('.popup-overlay').height($(window).height());
	 $("#item_texto").val(incluirSaltosLinea (descripcion) );
     return false;
}
function inicializarBtn2() {
	
	jQuery("#btnContinuar2").unbind("click").bind("click", function() {
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
        
        var $btn = jQuery(this);
        var url;        

		if ($btn.hasClass('unitatOrganica')) {
            url = pagEnviarPendientes;
		} 
        
		$.ajax({
            type: "POST",
            url: url, 
            dataType: "json",
            error: function(data) {					
                setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: "'+txtAjaxError+'", text: "<p> + '+txtIntenteho+' + </p>"})', 400);
                
            },
            success: function(data) {
                if (!data.error) {
                    setTimeout('Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: "'+txtEnviantDades+'"})', 400);
                } else {
                    setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: "'+data.error+'"})', 400);
                }
            }
        });
	});
}

// idioma
var pag_idioma = $("html").attr("lang");

// minim cercador
var numCercadorMinim = 0;

// paginacio
var paginacio_marge = 4;

// Cllistat
var claseCllistat;

// llistat
var itemID_ultim = 0;
function CLlistat(){
	this.extend = ListadoBase;
	this.extend();

	var that = this;
	

	// Cambia a la pestaña del buscador.
	this.tabBuscador = function() {
		jQuery("li.opcio, li.actiu").removeClass("actiu");
		jQuery("#tabBuscador").parent().addClass("actiu");
		
		opcio_unitat = "C";
		
		//that.carregar({});
		claseCllistat = that;
		
		// resultats
		resultats_elm.find("div.actiu").slideUp(300, function() {			
			jQuery(this).removeClass("actiu");
			resultats_elm.find("div."+opcio_unitat).slideDown(300, function() {
				
				jQuery(this).addClass("actiu");
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");
				claseCllistat.carregar({});
               
			});
			
		});
		
		
	};
	
	

	this.finCargaListado = function(opcions,data) {
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
			ordre_c1 = (ordre_C == "ordre") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "codiEstandar") ? " " + ordre_T : "";
			

			

			if (resultats_total > 1) {

				txt_ordenats = (ordre_T == "DESC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				var txt_per = txtOrdre;
				

			}
			var width10="10"; //Porcentaje del width de cada columna
			var width20="20";

			codi_totals = "<p class=\"info\" style = \"float:left\">" + txtTrobades + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ".";			
			codi_totals += "<div class=\"btnGenerico\" style = \"float:left\"><a href=\"javascript:;\" style=\"width:120px\" id = \"btnContinuar2\" class=\"btn unitatOrganica\"><span>"+txtBotonEnvio+"</span></a></div>";
			
			codi_totals += "</p>";

			codi_cap1 = "<div class=\"th id" + ordre_c1 + "\" role=\"columnheader\"  style=\"width:"+width10+ "%\">" + txtId + "</div>";
			codi_cap2 = "<div class=\"th desc" + ordre_c2 + "\" role=\"columnheader\" style=\"width:"+width10+ "%\">" + txtTipo + "</div>";
			codi_cap3 = "<div class=\"th desc" + ordre_c2 + "\" role=\"columnheader\" style=\"width:"+width10+ "%\">" + txtIdElemento + "</div>";
			codi_cap4 = "<div class=\"th desc" + ordre_c2 + "\" role=\"columnheader\" style=\"width:"+width10+ "%\">" + txtEstado + "</div>";
			codi_cap5 = "<div class=\"th desc" + ordre_c2 + "\" role=\"columnheader\" style=\"width:"+width10+ "%\">" + txtFalta + "</div>";
			codi_cap6 = "<div class=\"th desc" + ordre_c2 + "\" role=\"columnheader\" style=\"width:"+width10+ "%\">" + txtFenvio + "</div>";
			codi_cap7 = "<div class=\"th " + ordre_c2 + "\" role=\"columnheader\" style=\"width:"+width20+ "%\">" + txtMensaje + "</div>";
			

			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\" style=\"clear:both\">";

			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3 + codi_cap4 + codi_cap5 + codi_cap6 + codi_cap7;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			 
			
			// codi cuerpo
			var txtSiaDescripcionTipo="";
			$(data.nodes).each(function(i) {
				
				dada_node = this;
				
				if (dada_node.tipo == "PROC") {
					txtSiaDescripcionTipo = txtSiaDescripcionTipoPro;
				} else if(dada_node.tipo == "UA"){
					txtSiaDescripcionTipo = txtSiaDescripcionTipoUna;
				} else if(dada_node.tipo == "NORM"){
					txtSiaDescripcionTipo = txtSiaDescripcionTipoNor;
				}
				
				parClass = (i%2) ? " par": "";

				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";

				codi_taula += "<div class=\"td id\" role=\"gridcell\" style=\"width:"+width10+"%\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<span class=\"perfil\" >" + (printStringFromNull(dada_node.id, txtSinValor)) + "</span>";				
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td\" role=\"gridcell\" style=\"width:"+width10+"%\">";				
				codi_taula += printStringFromNull(txtSiaDescripcionTipo, txtSinValor);
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td\" role=\"gridcell\" style=\"width:"+width10+"%\">";				
				codi_taula += printStringFromNull(dada_node.idElemento, txtSinValor);
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td\" role=\"gridcell\" style=\"width:"+width10+"%\">";				
				if (dada_node.estado == 0) {
					codi_taula += "Espera";
				}else if (dada_node.estado == 1) {
					codi_taula += "Correcto";
				}  else if (dada_node.estado == 2) {
					codi_taula += "Incorrecto";
				} else {
					codi_taula += " Desconocido ";
				}
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td\" role=\"gridcell\" style=\"width:"+width10+"%\">";
				codi_taula += "<span class=\"falta\" >" + Llistat.getFechaString(dada_node.fecAlta) + "</span>";	
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td\" role=\"gridcell\" style=\"width:"+width10+"%\">";
				codi_taula += "<span class=\"fenvio\">" + Llistat.getFechaString(dada_node.fecIdx) + "</span>";	
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td\" role=\"gridcell\" style=\"width:"+width20+"%\">";				
				codi_taula += printStringFromNull(dada_node.mensaje, txtSinValor);
				codi_taula += "</div>";
				
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
				funcionPagina: "Llistat.cambiaPagina"
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

				// Asociamos el evento onclick a los elementos de la lista para
				// poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a.editarIdioma").unbind("click").bind("click",function(){Llistat.ficha(this);});
				inicializarBtn2();

				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					cercador_elm.find("input, select").removeAttr("disabled");
				}

				jQuery("#resultats .llistat .tbody select.ordenacion").unbind("change").bind("change",function(){
					var itemID = jQuery(this).attr("id").split("_")[1];
					var orden = jQuery(this).val();

					// Obtenemos el valor del orden anterior para saber en qué dirección reordenar los elementos
					var ordenAnterior = jQuery("#idioma_" + itemID).prev().html()-1;

					var dataVars = "id=" + itemID+"&orden="+orden + "&ordenAnterior=" + ordenAnterior;

					$.ajax({
						type: "POST",
						url: pagReordenar,
						data: dataVars,
						dataType: "json",
						error: function() {
							if (!a_enllas) {
								// missatge
								Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
							}
						},
						success: function(data) {
							that.anulaCache();
							that.carregar({});
						}
					});
				});
			});
		});
		
	}
	
	this.carregar = function(opcions) {
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC,
		// DESC), ordreCamp (tipus, carrec, tractament)

		dataVars = "";

		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;

			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
		} else {
			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;

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
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();

		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();

		// variables
		dataVars += "pagPag=" + pag_Pag + "&pagRes=" + pag_Res + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;		

		// ajax
		$.ajax({
			type: "POST",
			url: pagLlistat,
			data: dataVars,
			dataType: "json",
			error: function() {
				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				}
			},
			success: function(data) {				
				Llistat.finCargaListado(opcions,data);
			}
		});
	};
	
	/** Devuelve la fecha en formato string DD/MM/YYYY HH:MM:SS **/
	this.getFechaString = function(ifecha) {
		var retorno = "";
		if (ifecha != null) {
			var fecha = new Date(ifecha);
			retorno = this.getLPAD(fecha.getMonth()+1 , 2)+"/"+
				this.getLPAD(fecha.getDate(), 2)+"/"+
				this.getLPAD(fecha.getFullYear(), 4)+" "+
				this.getLPAD(fecha.getHours(), 2)+":"+
				this.getLPAD(fecha.getMinutes(), 2)+":"+
				this.getLPAD(fecha.getSeconds(), 2);
		}
		
		return retorno;
	};
	
	/** Funcion LPDA. **/
	this.getLPAD = function(itexto, longitud) {
		var texto = itexto;
		while (texto.length < longitud) {
			texto = "0"+texto;
		}
		return texto;
	};
	
	/** Obtiene el texto borrando el null. **/
	this.getTexto = function(itexto) {
		var texto = "";
		if (itexto != null && itexto != "null") {
			texto = itexto;
		}
		return texto;
	};
	
	this.finCargaListadoJob = function(opcions,data) {
		
		var contenido = "";
		var width="10"; //Porcentaje del width de cada columna
		var width20="20"; 
		var width40="40";
		
		if (data.nodes.length == 0) {
			contenido = "No hi ha dades";
		} else {
			contenido +="<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\" style=\"clear:both;\">";
			///HEAD
			contenido += "<div class=\"thead\">";
			contenido += "<div class=\"tr\" role=\"rowheader\">";
			contenido += "<div class=\"th \" role=\"columnheader\" style=\"width:"+width+"%\">ID</div>";
			contenido += "<div class=\"th \" role=\"columnheader\" style=\"width:"+width+"%\">Estat</div>";
			contenido += "<div class=\"th \" role=\"columnheader\" style=\"width:"+width+"%\">Data inici</div>";
			contenido += "<div class=\"th \" role=\"columnheader\" style=\"width:"+width+"%\">Data fi</div>";
			contenido += "<div class=\"th \" role=\"columnheader\" style=\"width:"+width40+"%\">Descripció abreviada</div>";
			contenido += "<div class=\"th \" role=\"columnheader\" style=\"width:"+width+"%;align-items: center;\">Descripció</div>";
			contenido += "</div>";
			contenido += "</div>";
			contenido += "<div class=\"tbody\">";
			
			
			contenido +="<div class=\"tbody\">";
		
			//Bucle sobre los nodoes.
			for(var i=0; i<data.nodes.length; i++) {
				var elemento = data.nodes[i]; 
				
				
				parClass = (i%2) ? " par": "";
				
				//Si tiene estado 3 o -1 se marca como erroneo
				if(elemento.estado == 3 || elemento.estado == -1){
					parClass =  " errorEstado";
				}
				contenido += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				contenido += "<div class=\"td id\" role=\"gridcell\" style=\"width:"+width+"%\">";
				
				contenido += "<span class=\"id\">" + elemento.id + "</span>";				
				contenido += "</div>";			
	
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width+"%\">";
				var descEstado="";
				if (elemento.estado == 0) { 
					descEstado = txtEstadoCreado;	
				} else if (elemento.estado == 1){
					descEstado = txtEstadoEje;
				} else if(elemento.estado == 2){
					descEstado = txtEstadoEnviado;
				} else if(elemento.estado == 3){
					descEstado = txtEstadoEnvError;
				}else if(elemento.estado == -1){
					descEstado = txtEstadoError;
				}
				
				contenido += "<span class=\"fecha\">"+descEstado+"</span>";
				contenido += "</div>";			
				
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width+"%\">";
				contenido += "<span class=\"fecha\">" + Llistat.getFechaString(elemento.fechaIni) + "</span>";	
				contenido += "</div>";
				
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width+"%\">";
				contenido += "<span class=\"fecha\">" + Llistat.getFechaString(elemento.fechaFin) + "</span>";	
				contenido += "</div>";			
				
				contenido += "<div class=\"td fechaFicha\" role=\"gridcell\" style=\"width:"+width40+"%\;text-align: left;\">";
				contenido += "<span class=\"descripcion\" >" + elemento.descBreve + "</span>";
				contenido += "</div>";
				
				contenido += "<div class=\"td fechaNormativa\" role=\"gridcell\" style=\"width:"+width+"%;align-items: center;\">";
				contenido += "<div class=\"btnGenerico\" style = \"float:left\"><a href=\"javascript:;\" style=\"width:100px;\" id = \"botonInfo\" class=\"btn unitatOrganica\" onclick=\"pintarPopUp('"+ elemento.descripcion +"')\"><span>"+txtBotonInfo+"</span></a></div>";

				//FIN ROW
				contenido += "</div>";
				
				
			}
			
			//FIN TBODY
			contenido += "</div>";
			
			//FIN TABLE
			contenido += "</div>";
		}
		
		
		$(".dadesJob").html(contenido);
	};
	
	this.inicializarJobs = function() {
		dataVars = "";
    
		// ajax
		$.ajax({
			type: "POST",
			url: pagLlistatJob,
			data: dataVars,
			dataType: "json",
			error: function() {
				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				}
			},
			success: function(data) {				
				Llistat.finCargaListadoJob(opcions,data);
			}
		});
	}
};



