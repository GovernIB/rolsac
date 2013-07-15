jQuery(document).ready(function(){
	LANG_TRADUCTOR = "ca";
	
	jQuery("#btnVolver").bind("click",function(){Detall.torna();});
	jQuery("#btnEliminar").bind("click",function(){Detall.eliminar();});
	jQuery("#btnPrevisualizar").bind("click",function(){Detall.previsualitza();});

	// El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario (ver DetallBase.modificado)
	jQuery("#btnGuardar").parent().addClass("off");
	//jQuery("#btnGuardar").bind("click",function(){Detall.guarda();});

	jQuery("#formGuardar input,#formGuardar select,#formGuardar textarea").bind("change",function(){Detall.modificado();});

	if( jQuery("textarea.rich").tinymce != undefined ){
		$('textarea.rich').tinymce({
			// Location of TinyMCE script
			script_url : tinyMceUrl,

			// General options
			theme : "advanced",
			plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

			// Theme options
			theme_advanced_buttons1 : "bold,italic,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,bullist,numlist,|,link,unlink,|,undo,redo,|,cleanup,code,removeformat",
			theme_advanced_buttons2 : "",
			theme_advanced_buttons3 : "",
			theme_advanced_buttons4 : "",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "bottom",
			theme_advanced_resizing : true,
			theme_advanced_resize_horizontal: false,
			
			// Todos los caracteres (que hagan falta) se convertirán en entidades numéricas.
			// Documentación: http://www.tinymce.com/wiki.php/Configuration:entity_encoding
			entity_encoding : "numeric",

			onchange_callback: function(){Detall.modificado();}
		});
	}

	var Error = new CError();
	
});

/**
 * @param boolean soloFicha: Indica si es un asociado a un listado, por defecto no.
 * @param array reglasFormulario: Lista de reglas a validar para guardar el formulario, por defecto FormulariDades.
 * @param object identificadores: Identificadores de botones de acci�n, etc.
 */
function DetallBase( soloFicha, reglasFormulario, identificadores ){
	var that = this;
	var soloFicha = soloFicha || false;
	var ids = {
		btnGuardar: "btnGuardar",
		form: "formGuardar"
	};
	
	if( identificadores ){
		
		ids.btnGuardar = identificadores.btnGuardar || "btnGuardar";
		ids.form = identificadores.form || "formGuardar";

	}
	
	jQuery("#"+ids.btnGuardar).parent().addClass("off");
	jQuery("#"+ids.form+" input,#"+ids.form+" select,#"+ids.form+" textarea").bind("change",function(){that.modificado();});
    
    this.idiomas = ["es","ca","en","de","fr"];
    //this.idiomas = idiomas; 

	// Url de la previsualizacion (tiene que sobreescribirse al extender la clase).
	this.urlPrevisualizar = null;
	
	// Tipo de auditoria
	this.tipusAuditoria = null;

	// Tipo de estad�stica
	this.tipusEstadistica = null;

	// Preparar reglas de validacion del formulario.
	if (typeof reglasFormulario == 'undefined' && typeof FormulariDades != 'undefined') {
		reglasFormulario = FormulariDades;
	}
	var formulariComprovar = new FormulariComprovar(reglasFormulario);
	formulariComprovar.iniciar();


	this.actualizaEventos = function(){

		// Asociamos los eventos a los botones de plegar y desplegar.
		jQuery("#continguts a.mostrat, #continguts a.amagat").unbind("click").bind("click",function(){
			if( jQuery(this).hasClass("mostrat") ){
				jQuery(this).removeClass("mostrat").addClass("amagat").text(txtAmaga);
			}else{
				jQuery(this).removeClass("amagat").addClass("mostrat").text(txtMostra);
			}
			jQuery(this).siblings("div.modul_continguts").slideToggle(300);
		});
	}

	this.formulariValid = function () {
		formulariComprovar.llansar();
		return formulariComprovar.formComprovacio;
	}

	this.guardaGenerico = function(dataVars) {

		// Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }

		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		dataForm = $("#"+ids.form).serialize();

		if (typeof dataVars != 'undefined' && dataVars.length > 0) {
			dataForm += "&" + dataVars;
		}
     
		// ajax
		$.ajax({
			type: "POST",
			url: pagGuardar,
			data: dataForm,
			dataType: "json",
			error: function() {
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {

				Llistat.cacheDatosListado = null;

				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					if( !soloFicha ){
						Detall.recarregar(data.id);
					}
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
				}

			}
		});
	}
	
	this.guarda = function (dataVars) {
		that.guardaGenerico(dataVars);
	}

	this.modificado = function(){
		// Habilitamos el bot�n de guardar.
		jQuery("#"+ids.btnGuardar).unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
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
	 * Inicia la eliminaci�n de un item confirmando la operaci�n.
	 */
	this.eliminar = function() {
		Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtItemEliminar, funcio: function() {
			that.elimina();
		}});
	}

	this.carregar = function(itemID){

		// Deshabilitamos inicialmente el bot�n de guardar.
		jQuery("#"+ids.btnGuardar).unbind("click").parent().removeClass("off").addClass("off");

		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").show();

		if (itemID == undefined){
			itemID = $("#item_id").val();
			Llistat.itemID = itemID;
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
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					},
					success: function(data) {
						if (typeof data.error != 'undefined') {
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.error + "</p>"});
						} else {
							that.pintar(data);
							if (that.tipusAuditoria != null && typeof Auditoria.busca != 'undefined') { 
								//Existe auditoria para el detalle y se ha cargado el objeto de auditor�as
								Auditoria.busca(that.tipusAuditoria, itemID);
							}
							if (that.tipusEstadistica != null && typeof Estadistica.pinta != 'undefined') { 
								//Existe auditoria para el detalle y se ha cargado el objeto de auditor�as
								Estadistica.pinta(that.tipusEstadistica, itemID);
							}
						}
					}
				});
			});
		});

		this.actualizaEventos();
	}

	this.recarregar = function(itemId) {
        var url = location.protocol + "//" + location.host + location.pathname;
        
        var itemId = parseInt(itemId);
        if (!isNaN(itemId) && itemId > 0) {
            url += "?itemId=" + itemId;
        }
        
        location = url;
    
		// // animacio
		// escriptori_detall_elm.fadeOut(300, function() {

			// div_L_elm = resultats_elm.find("div.L:first");
			// carregant_codi = "<p class=\"executant\">" + txtCarregantItems + "</p>";
			// div_L_elm.find("div.dades").html(carregant_codi);

			// escriptori_contingut_elm.fadeIn(300, function() {

				// // cercador o llistat?
				// if (opcions_elm.find("li.actiu").hasClass("L")) {
					// Llistat.carregar({});
				// } else {
					// jQuery("#opcions .actiu").removeClass("actiu");
					// jQuery("#tabListado").parent().addClass("actiu");

					// // resultats
					// resultats_elm.find("div.actiu:first").removeClass("actiu").slideUp(300,function() {
						// $(this).find("div.dades").html("");

						// div_L_elm.slideDown(300,function() {
							// $(this).addClass("actiu");
							// Llistat.carregar({});
						// });
						// Detall.carregar(itemId);
					// });
				// }
			// });
		// });
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
		
    	var elm = $(e.target);

		if (elm.is("A")) {
        
            // Determinar si hay que cambiar los modulos laterales muldiidioma (por defecto se cambian).
            var cambiarModulosLaterales;
            if (typeof e.data != 'undefined' && typeof e.data.actualizarIdiomasModulosLaterales != 'undefined') {
                cambiarModulosLaterales = Boolean(e.data.actualizarIdiomasModulosLaterales);
            } else {
                cambiarModulosLaterales = true;
            }
        
			var ul_idiomes_elm = elm.parent().parent();
                                    
            ul_idiomes_elm.unbind("click");

            var div_idiomes_elm = ul_idiomes_elm.next();

			if (!elm.hasClass("desplegar")) {

				var a_clicat_class = elm.attr("class");

				div_idiomes_elm.find("div.seleccionat").slideUp(300, function() {

					$(this).removeClass("seleccionat");
					var span_primer_elm = ul_idiomes_elm.find("span:first");
					var span_primer_elm_class = span_primer_elm.attr("class");
					var span_primer_elm_text = span_primer_elm.text();
					span_primer_elm.parent().removeClass("seleccionat").html("<a href=\"javascript:;\" class=\"" + span_primer_elm_class + "\">" + span_primer_elm_text + "</a>");

					var elm_text = elm.text();
					elm.parent().addClass("seleccionat").html("<span class=\"" + a_clicat_class + "\">" + elm_text + "</span>");

					div_idiomes_elm.find("div." + a_clicat_class).slideDown(300, function() {
						$(this).addClass("seleccionat");
						ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': cambiarModulosLaterales}, that.idioma);
					});

				});
				
				// Si hay más de una sección de idiomas, ocultarlo todo y mostrar el
				// DIV con el idioma solicitado.
				if (escriptori_detall_elm.find("div.idiomes").size() > 1) {
					
					$('div.idiomes:gt(0) div.idioma').hide();
					$('div.idiomes:gt(0) div.idioma.' + a_clicat_class).show();
					
				}

                // Actualizamos los campos multi-idioma independientes.
                escriptori_detall_elm.find(".element.multilang .campoIdioma").hide();
                escriptori_detall_elm.find(".element.multilang ." + a_clicat_class).show();

                if (cambiarModulosLaterales) {
                	
                    var modulos = jQuery(".modulLateral .modul .multilang");
                    modulos.find("li.seleccionat:first").removeClass("seleccionat");
                    modulos.find("li." + a_clicat_class).addClass("seleccionat");
                    
                    modulos.find("div.seleccionats div.seleccionat").slideUp(300,function() {
                        var $this = jQuery(this);
                        $this.removeClass("seleccionat");
                        $this.hide();
                        $this.siblings("div." + a_clicat_class).slideDown(300,function() {
                            jQuery(this).addClass("seleccionat");
                        });
                    });
                    
				}

			} else {

				if (!elm.hasClass("on")) {

					ul_idiomes_elm.find("li:not(.desplegar)").css("display","none");
					
					div_idiomes_elm.find("div.idioma").each(function(i) {
						var text_idioma = ul_idiomes_elm.find("li:eq(" + i + ")").text();
						var div_idioma_elm = $(this);
						if (i >= 1) {
							div_idioma_elm.css("border-top",".2em solid #ffecd9");
						}
						div_idioma_elm.prepend("<h3>" + text_idioma + "</h3>").slideDown(300);
					});
					
					elm.addClass("on").text(txtPlega);

				} else {

					div_idiomes_elm.find("div.idioma").each(function(i) {
						var div_idioma_elm = $(this);
						if (i >= 1) {
							div_idioma_elm.css("border-top","");
						}
						div_idioma_elm.find("h3:first").remove().end().slideUp(300);
					});
					
					elm.removeClass("on").text(txtDesplega);
					ul_idiomes_elm.find("li:not(.desplegar)").css("display","block");

				}

				ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': cambiarModulosLaterales}, that.idioma);
				
			}
			
		}
		
	}

	this.previsualitza = function() {
		var url = this.urlPrevisualizar;
		escriptori_detall_elm.fadeOut(300, function() {
			var idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			var id = escriptori_detall_elm.find("#item_id").val();

			url += "?lang=" + idiomaSeleccionat + "&codi=" + id + "&previ=s";

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
	
	this.traduir = function (url, inputs, datos) {
		
        Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
        
        var dataVars = "";
        for (var i in inputs) {
            var campo = inputs[i] + LANG_TRADUCTOR;
            var and = (i == 0) ? "" : "&";
            // Escapamos cada valor de los campos a traducir con encodeURIComponent(),
            // ya que si el texto contenía alguna "&", se consideraría ya otro campo en la llamada AJAX.
            // Luego tocará decodificar cada campo en el servlet que reciba la petición.
            var texto = jQuery("#" + campo).val();
            // TinyMCE, aún forzando a que escape a entidades numéricas, no contempla el caso del tanto por ciento %.
            // Toca hacerlo de forma explícita antes de codificar completamente la cadena con encodeURIComponent().
            texto = texto.replace(/%/g, "&#37;")
            dataVars += and + campo + "=" + encodeURIComponent( texto );
        }
                
        $.ajax({
            type: "POST",
            url: url,
            data: dataVars,
            dataType: "json",
            error: function() {
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
            },
            success: function(data) {
                if (typeof data.error != "undefined") {
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: data.error});
                } else {
                    for (var i in data.traduccions) {
                        var traduccio = data.traduccions[i].traduccio;
                        var lang = data.traduccions[i].lang;
                        for (var j in inputs) {
                            var campo = inputs[j] + lang;
                            var valor = traduccio[datos[j]] || "";
                            jQuery("#" + campo).val(valor);
                        }
                    }
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtTraduccioCorrecta});
                }
            }
        });
	}
}