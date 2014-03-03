jQuery(document).ready(function() {
	
	LANG_TRADUCTOR = "ca";

	//jQuery("#btnVolver").bind("click",function(){Detall.torna();});
	jQuery("#btnEliminar").bind("click",function(){Detall.eliminar();});
	jQuery("#btnPrevisualizar").bind("click",function(){Detall.previsualitza();});

	// El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario (ver DetallBase.modificado)
	//jQuery("#btnGuardar").parent().addClass("off");
	//jQuery("#btnGuardar").bind("click",function(){Detall.guarda();});

	//jQuery("#formGuardar input,#formGuardar select,#formGuardar textarea").bind("change",function(){console.log("mod 1");Detall.modificado();});

	if ( jQuery("textarea.rich").tinymce != undefined ) {

		$('textarea.rich').tinymce({ // Location of TinyMCE script
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

			onchange_callback: function() { Detall.modificado(); }
		});
		
	}

	var Error = new CError();

});

/**
 * @param boolean soloFicha: Indica si es un asociado a un listado, por defecto no.
 * @param array reglasFormulario: Lista de reglas a validar para guardar el formulario, por defecto FormulariDades.
 * @param object identificadores: Identificadores de botones de acci�n, etc.
 */
function DetallBase(soloFicha, reglasFormulario, identificadores) {
	
	// Activa mensajes de debug.
	var debug = false;

	var that = this;
	var soloFicha = soloFicha || false;

	var ids = {btnGuardar: "btnGuardar", btnVolver: "btnVolver", form: "formGuardar"};

	if (identificadores) {

		ids.btnGuardar = identificadores.btnGuardar || ids.btnGuardar;
		ids.btnVolver = identificadores.btnVolver || ids.btnVolver;
		ids.form = identificadores.form || ids.form;

	}

	// Evento del botón volver
	//jQuery("#"+ids.btnVolver).bind("click",function(){console.log("Click torna");Detall.torna();});
	//jQuery("#"+ids.btnVolver).unbind("click").bind("click",function(){that.vuelve();});

	jQuery("#" + ids.btnVolver).bind("click", function() { that.vuelve(); });

	//jQuery("#"+ids.btnGuardar).parent().addClass("off");
	jQuery("#" + ids.form + " input,#" + ids.form + " select,#" + ids.form + " textarea").bind("change", function() { that.modificado(); });

	this.idiomas = ["es","ca","en","de","fr"];

	// Url de la previsualizacion (tiene que sobreescribirse al extender la clase).
	this.urlPrevisualizar = null;

	// Tipo de auditoria
	this.tipusAuditoria = null;

	// Tipo de estadística
	this.tipusEstadistica = null;

	// Preparar reglas de validacion del formulario.
	if (typeof reglasFormulario == 'undefined' && typeof FormulariDades != 'undefined')
		reglasFormulario = FormulariDades;

	var formulariComprovar = new FormulariComprovar(reglasFormulario);
	formulariComprovar.iniciar();

	this.actualizaEventos = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.actualizaEventos");

		// Asociamos los eventos a los botones de plegar y desplegar.
		jQuery("#continguts a.mostrat, #continguts a.amagat").unbind("click").bind("click", function() {

			if (jQuery(this).hasClass("mostrat"))
				jQuery(this).removeClass("mostrat").addClass("amagat").text(txtAmaga);

			else
				jQuery(this).removeClass("amagat").addClass("mostrat").text(txtMostra);


			jQuery(this).siblings("div.modul_continguts").slideToggle(300);

		});
		
		if (debug)
			console.log("Saliendo de DetallBase.actualizaEventos");
		
	}

	this.cambiosSinGuardar = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.cambiosSinGuardar");
		
		if (debug)
			console.log("Saliendo de DetallBase.cambiosSinGuardar");
		
		return !jQuery("#" + ids.btnGuardar).parent().hasClass("off");
	}

	this.formulariValid = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.formulariValid");

		formulariComprovar.llansar();
		
		if (debug)
			console.log("Saliendo de DetallBase.formulariValid");
		
		return formulariComprovar.formComprovacio;

	}

	this.guardaGenerico = function(dataVars) {
		
		if (debug)
			console.log("Entrando en DetallBase.guardaGenerico");

		if (!that.formulariValid())
			return false;

		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		dataForm = $("#" + ids.form).serialize();

		if (typeof dataVars != 'undefined' && dataVars.length > 0)
			dataForm += "&" + dataVars;

		$.ajax({
			type: "POST",
			url: pagGuardar,
			data: dataForm,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {

				Llistat.cacheDatosListado = null;

				if (data.id < 0) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});

				} else {

					if ( !soloFicha )
						Detall.recarregar(data.id);

					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});

				}//End if

			} //Fin success

		});//Fin ajax
		
		if (debug)
			console.log("Saliendo de DetallBase.guardaGenerico");

	}

	this.guarda = function(dataVars) {
		
		if (debug)
			console.log("Entrando en DetallBase.guarda");
		
		that.guardaGenerico(dataVars);
		
		if (debug)
			console.log("Saliendo de DetallBase.guarda");
		
	}

	this.modificado = function(marcar) {
		
		if (debug)
			console.log("Entrando en DetallBase.modificado");

		if( typeof(marcar) == "undefined" )
			marcar = true;

		// Actualizamos la variable global para controlar si hay cambios sin guardar en los formularios.
		CambiosSinGuardar(ids.form, marcar);

		// Habilitamos el botón de guardar.
		jQuery("#"+ids.btnGuardar).unbind("click").bind("click", function() { that.guarda(); } ).parent().toggleClass("off", !marcar);
		
		if (debug)
			console.log("Saliendo de DetallBase.modificado");

	}

	this.publica = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.publica");

		jQuery("#item_data_publicacio").val(txtImmediat);
		jQuery("#item_data_caducitat").val("");
		
		this.guarda();
		
		if (debug)
			console.log("Saliendo de DetallBase.publica");

	}

	/**
	 * Ocultamos el formulario y volvemos a mostrar el contenido general.  
	 */
	this.cierra = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.cierra");

		this.modificado(false);

		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_contingut_elm.fadeIn(300);
		});
		
		if (debug)
			console.log("Saliendo de DetallBase.cierra");

	}

	/**
	 * Vuelve de la ficha al listado.
	 */
	this.vuelve = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.vuelve");

		if ( this.cambiosSinGuardar() ) {

			Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtAvisoCambiosSinGuardar, funcio: function() {

				that.cierra();
				Missatge.cancelar();

			}});

		} else {

			that.cierra();

		}

		$(".modulLateral > div.modul").each(function() {
			that.ocultarModulos($(this));
		});
		
		if (debug)
			console.log("Saliendo de DetallBase.vuelve");

	}

	this.ocultarModulos = function(selector) {
		
		if (debug)
			console.log("Entrando en DetallBase.ocultarModulos");

		if ( !selector.hasClass("publicacio") )
			selector.addClass("invisible");
		
		if (debug)
			console.log("Saliendo de DetallBase.ocultarModulos");

	}

	/**
	 * Inicia la eliminación de un item confirmando la operación.
	 */
	this.eliminar = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.eliminar");

		Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtItemEliminar, funcio: function() {
			that.elimina();
		}});
		
		if (debug)
			console.log("Saliendo de DetallBase.eliminar");

	}

	this.carregar = function(itemID) {
		
		if (debug)
			console.log("Entrando en DetallBase.carregar");

		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").show();

		if (itemID == undefined) {

			itemID = $("#item_id").val();
			Llistat.itemID = itemID;

		}

		escriptori_contingut_elm.fadeOut(300, function() {

			codi_carregant = "<div id=\"carregantDetall\"><p class=\"executant\">" + txtCarregantDetall + "</p></div>";
			escriptori_elm.append(codi_carregant).slideDown(300, function() {

				dataVars = "accio=carregar" + "&id=" + itemID;

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
							that.cargarModulos();

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
		
		if (debug)
			console.log("Saliendo de DetallBase.carregar");

	}

	this.recarregar = function(itemId) {
		
		if (debug)
			console.log("Entrando en DetallBase.recarregar");

		var url = location.protocol + "//" + location.host + location.pathname;

		var itemId = parseInt(itemId);

		if (!isNaN(itemId) && itemId > 0)
			url += "?itemId=" + itemId;

		this.modificado(false);

		location = url;
		
		if (debug)
			console.log("Saliendo de DetallBase.recarregar");

	}

	/**
	 * @param opcions: id (num); accio (guardar, elimina); dades: json
	 */
	this.array = function(opcions) {
		
		if (debug)
			console.log("Entrando en DetallBase.array");

		if (opcions.accio == "guarda") {

			Items_arr.push({id: opcions.id, dades: opcions.dades});

		} else if (opcions.accio == "elimina") {

			id_eliminat = 0;

			for (i = 0; i < Items_arr.length; i++) {

				if (opcions.id == Items_arr[i].id)
					id_eliminat = i;

			}

			Items_arr.splice(id_eliminat,1);

		}
		
		if (debug)
			console.log("Saliendo de DetallBase.array");
		
	}

	this.idioma = function(e) {
		
		if (debug)
			console.log("Entrando en DetallBase.idioma");

		var elm = $(e.target);

		if (elm.is("A")) {

			// Determinar si hay que cambiar los modulos laterales muldiidioma (por defecto se cambian).
			var cambiarModulosLaterales;

			if (typeof e.data != 'undefined' && typeof e.data.actualizarIdiomasModulosLaterales != 'undefined')
				cambiarModulosLaterales = Boolean(e.data.actualizarIdiomasModulosLaterales);

			else
				cambiarModulosLaterales = true;

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

					modulos.find("div.seleccionats div.seleccionat").slideUp(300, function() {

						var $this = jQuery(this);
						$this.removeClass("seleccionat");
						$this.hide();
						$this.siblings("div." + a_clicat_class).slideDown(300, function() {
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

						if (i >= 1)
							div_idioma_elm.css("border-top",".2em solid #ffecd9");

						div_idioma_elm.prepend("<h3>" + text_idioma + "</h3>").slideDown(300);

					});

					elm.addClass("on").text(txtPlega);

				} else {

					div_idiomes_elm.find("div.idioma").each(function(i) {

						var div_idioma_elm = $(this);

						if (i >= 1)
							div_idioma_elm.css("border-top","");

						div_idioma_elm.find("h3:first").remove().end().slideUp(300);

					});

					elm.removeClass("on").text(txtDesplega);
					ul_idiomes_elm.find("li:not(.desplegar)").css("display", "block");

				}

				ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': cambiarModulosLaterales}, that.idioma);

			}

		}
		
		if (debug)
			console.log("Saliendo de DetallBase.idioma");

	}

	this.previsualitza = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.previsualitza");

		var url = this.urlPrevisualizar;
		escriptori_detall_elm.fadeOut(300, function() {

			var idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			var id = escriptori_detall_elm.find("#item_id").val();

			url += "?lang=" + idiomaSeleccionat + "&codi=" + id + "&previ=s";

			escriptori_previsualitza_elm.find("iframe").attr("src", url).end().fadeIn(300, function() {
				$(this).find("a.dePrevisualitzar").one("click", that.previsualitzaTorna);
			});

		});
		
		if (debug)
			console.log("Saliendo de DetallBase.previsualitza");

	}

	this.previsualitzaTorna = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.previsualitzaTorna");

		escriptori_previsualitza_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300);
		});
		
		if (debug)
			console.log("Saliendo de DetallBase.previsualitzaTorna");

	}

	this.traduir = function (url, inputs, datos) {
		
		if (debug)
			console.log("Entrando en DetallBase.traduir");

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
			dataVars += and + campo + "=" + encodeURIComponent(texto);

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
		
		if (debug)
			console.log("Saliendo de DetallBase.traduir");

	}

	this.cargarModulos = function() {
		
		if (debug)
			console.log("Entrando en DetallBase.cargarModulos");

		item_ID = $("#item_id").val();
		dataVars = "id=" + item_ID;

		$.ajax({
			type: "POST",
			url: modulos,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {

				$("div.invisible").each(function() {
					$(this).removeClass("invisible");
				});

				that.pintarModulos(data);

			}

		});
		
		if (debug)
			console.log("Saliendo de DetallBase.cargarModulos");

	}

}