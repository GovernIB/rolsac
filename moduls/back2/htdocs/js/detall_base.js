jQuery(document).ready(function(){
	jQuery("#btnVolver").bind("click",function(){Detall.torna();});
	jQuery("#btnGuardar").bind("click",function(){Detall.guarda();});
	jQuery("#btnEliminar").bind("click",function(){Detall.eliminar();});
	jQuery("#btnPrevisualizar").bind("click",function(){Detall.previsualitza();});
	jQuery("#btnPublicar").bind("click",function(){Detall.publica();});
});

/**
 * @param boolean soloFicha: Indica si es un asociado a un listado, por defecto no.
 */
function DetallBase(soloFicha){
	var that = this;
	var soloFicha = soloFicha || false;

	// Url de la previsualización (tiene que sobreescribirse al extender la clase).
	this.urlPrevisualizar = null;

	this.actualizaEventos = function(){		
						
		// Asociamos los eventos a los botones de plegar y desplegar.
		jQuery("#formGuardar a.mostrat, #formGuardar a.amagat").unbind("click").bind("click",function(){				
			if( jQuery(this).hasClass("mostrat") ){		
				jQuery(this).removeClass("mostrat").addClass("amagat").text(txtAmaga);			
			}else{
				jQuery(this).removeClass("amagat").addClass("mostrat").text(txtMostra);			
			}					
			jQuery(this).siblings("div.modul_continguts").slideToggle(300);
		});
	}
	
	this.guarda = function() {
									
		// Validamos el formulario
		if( typeof FormulariComprovar != "undefined" ){
					
			FormulariComprovar.llansar();
			
			if (!formComprovacio) {				
				return false;
			}
		}
						
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});		
		
		dataVars = $("#formGuardar").serialize();
						
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
			
				Llistat.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					if( !soloFicha ){
						Detall.recarregar();											
					}
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
				}				
				
			}
		});
	}
	
	this.publica = function(){
		jQuery("#item_data_publicacio").val(txtImmediat);
		jQuery("#item_data_caducitat").val("");
		this.guarda();
	}
	
	/**
	 * Vuelve de la ficha al listado.
	 */
	this.torna = function() {		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_contingut_elm.fadeIn(300);			
		});		
	}
	
	/**
	 * Inicia la eliminación de un item confirmando la operación.
	 */
	this.eliminar = function() {
			
		Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtItemEliminar, funcio: function() { 			
			Detall.elimina();
			}
		});
		
	}
	
	this.carregar = function(itemID){
		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").show();				
		
		if (itemID == undefined){
			itemID = $("#item_id").val();
		}						
		
		escriptori_contingut_elm.fadeOut(300, function() {
			
			codi_carregant = "<div id=\"carregantDetall\"><p class=\"executant\">" + txtCarregantDetall + "</p></div>";
			escriptori_elm.append(codi_carregant).slideDown(300, function() {
				
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
						Detall.pintar(data);												
					}
				});			
			});			
		});			
		
		this.actualizaEventos();
	}
	
	this.recarregar = function() {		
			
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			div_L_elm = resultats_elm.find("div.L:first");
			carregant_codi = "<p class=\"executant\">" + txtCarregantItems + "</p>";
			div_L_elm.find("div.dades").html(carregant_codi);
			
			escriptori_contingut_elm.fadeIn(300, function() {
				
				// cercador o llistat?
				if (opcions_elm.find("li.actiu").hasClass("L")) {
					
					Llistat.carregar({});
					
				} else {
					
					jQuery("#opcions .actiu").removeClass("actiu");
					jQuery("#tabListado").parent().addClass("actiu");
															
					// resultats
					resultats_elm.find("div.actiu:first").removeClass("actiu").slideUp(300,function() {
																															 
						$(this).find("div.dades").html("");
						
						div_L_elm.slideDown(300,function() {
							
							$(this).addClass("actiu");
							Llistat.carregar({});
							
						});
						
					});					
				}				
			});			
		});
		
	}
	
	this.previsualitzaTorna = function() {		
		escriptori_previsualitza_elm.fadeOut(300, function() {		
			escriptori_detall_elm.fadeIn(300);		
		});		
	}
	
	/**
	 * @param opcions: id (num); accio (guardar, elimina); dades: json
	 */
	this.array = function(opcions) {
						
		if (opcions.accio == "guarda") {
			
			Items_arr.push({id: opcions.id, dades: opcions.dades});
			
		} else if (opcions.accio == "elimina") {
			
			id_eliminat = 0;
			
			for (i=0; i<Items_arr.length; i++) {
				if (opcions.id == Items_arr[i].id) {
					id_eliminat = i;
				}
			}
			
			//delete Items_arr[id_eliminat];
			Items_arr.splice(id_eliminat,1);
			
		}	
	}
	
	this.idioma = function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			ul_idiomes_elm.unbind("click");
			
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
				
				// Comprovamos si existen documentos relacionados
				if (typeof modul_documents_elm != "undefined") {
					modul_documents_idiomes_elm.find("li.seleccionat:first").removeClass("seleccionat");
					modul_documents_idiomes_elm.find("li." + a_clicat_class).addClass("seleccionat");
					
					documents_seleccionats_elm.find("div.seleccionat").slideUp(300, function() {
						$(this).removeClass("seleccionat");
						documents_seleccionats_elm.find("div." + a_clicat_class).slideDown(300, function() {
							$(this).addClass("seleccionat");
						});
					});
					
				}
				
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
	}
	
	this.previsualitza = function() {
		var url = this.urlPrevisualizar;
		
		escriptori_detall_elm.fadeOut(300, function() {
									
			fitxa_idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			//fitxa_ID = escriptori_detall_elm.find("#item_id").val();
			
			url += "?lang=" + fitxa_idiomaSeleccionat + "&codi=636513"; //+ fitxa_ID;
									
			escriptori_previsualitza_elm.find("iframe").attr("src", url).end().fadeIn(300, function() {			
				$(this).find("a.dePrevisualitzar").one("click", that.previsualitzaTorna);			
			});
		
		});		
	}
	
	this.previsualitzaTorna = function() {		
		escriptori_previsualitza_elm.fadeOut(300, function() {		
			escriptori_detall_elm.fadeIn(300);
		});
	}
}