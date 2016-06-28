jQuery(document).ready(function() {
	
	LANG_TRADUCTOR = "ca";

	//jQuery("#btnVolver").bind("click",function(){Detall.torna();});
	jQuery("#btnEliminar").bind("click",function(){Detall.eliminar();});
	jQuery("#btnPrevisualizar").bind("click",function(){Detall.previsualitza();});

	// El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario (ver DetallBase.modificado)
	//jQuery("#btnGuardar").parent().addClass("off");
	//jQuery("#btnGuardar").bind("click",function(){Detall.guarda();});

	//jQuery("#formGuardar input,#formGuardar select,#formGuardar textarea").bind("change",function(){DebugJS.debug("mod 1");Detall.modificado();});

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

	var that = this;
	var soloFicha = soloFicha || false;

	var ids = {btnGuardar: "btnGuardar", btnVolver: "btnVolver", form: "formGuardar"};

	if (identificadores) {

		ids.btnGuardar = identificadores.btnGuardar || ids.btnGuardar;
		ids.btnVolver = identificadores.btnVolver || ids.btnVolver;
		ids.form = identificadores.form || ids.form;

	}

	// Evento del botón volver
	//jQuery("#"+ids.btnVolver).bind("click",function(){DebugJS.debug("Click torna");Detall.torna();});
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
		
		DebugJS.debug("Entrando en DetallBase.actualizaEventos");

		// Asociamos los eventos a los botones de plegar y desplegar.
		jQuery("#continguts a.mostrat, #continguts a.amagat").unbind("click").bind("click", function() {

			if (jQuery(this).hasClass("mostrat"))
				jQuery(this).removeClass("mostrat").addClass("amagat").text(txtAmaga);

			else
				jQuery(this).removeClass("amagat").addClass("mostrat").text(txtMostra);


			jQuery(this).siblings("div.modul_continguts").slideToggle(300);

		});
		
		DebugJS.debug("Saliendo de DetallBase.actualizaEventos");
		
	};

	this.cambiosSinGuardar = function() {
		
		DebugJS.debug("Entrando en DetallBase.cambiosSinGuardar");
		
		DebugJS.debug("Saliendo de DetallBase.cambiosSinGuardar");
		
		return !jQuery("#" + ids.btnGuardar).parent().hasClass("off");
		
	};

	this.formulariValid = function() {
		
		DebugJS.debug("Entrando en DetallBase.formulariValid");

		formulariComprovar.llansar();
		
		DebugJS.debug("Saliendo de DetallBase.formulariValid");
		
		return formulariComprovar.formComprovacio;

	};

	this.guardaGenerico = function(dataVars) {
		
		DebugJS.debug("Entrando en DetallBase.guardaGenerico");

		if (!that.formulariValid()) {
			DebugJS.debug("Saliendo de DetallBase.guardaGenerico");
			return false;
		}

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
		
		DebugJS.debug("Saliendo de DetallBase.guardaGenerico");

	};

	this.guarda = function(dataVars) {
		
		DebugJS.debug("Entrando en DetallBase.guarda");
		
		that.guardaGenerico(dataVars);
		
		DebugJS.debug("Saliendo de DetallBase.guarda");
		
	};

	this.modificado = function(marcar) {
		
		DebugJS.debug("Entrando en DetallBase.modificado");

		if( typeof(marcar) == "undefined" )
			marcar = true;
		
		DebugJS.debug("\tCon atributo 'marcar' = " + marcar);

		// Actualizamos la variable global para controlar si hay cambios sin guardar en los formularios.
		CambiosSinGuardar(ids.form, marcar);

		// Habilitamos el botón de guardar.
		jQuery("#"+ids.btnGuardar).unbind("click").bind("click", function() { that.guarda(); } ).parent().toggleClass("off", !marcar);
		
		DebugJS.debug("Saliendo de DetallBase.modificado");

	};

	this.publica = function() {
		
		DebugJS.debug("Entrando en DetallBase.publica");

		jQuery("#item_data_publicacio").val(txtImmediat);
		jQuery("#item_data_caducitat").val("");
		
		this.guarda();
		
		DebugJS.debug("Saliendo de DetallBase.publica");

	};

	/**
	 * Ocultamos el formulario y volvemos a mostrar el contenido general.  
	 */
	this.cierra = function() {
		
		DebugJS.debug("Entrando en DetallBase.cierra");

		this.modificado(false);

		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_contingut_elm.fadeIn(300);
		});
		
		DebugJS.debug("Saliendo de DetallBase.cierra");

	};

	/**
	 * Vuelve de la ficha al listado.
	 */
	this.vuelve = function() {
		
		DebugJS.debug("Entrando en DetallBase.vuelve");

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
		
		DebugJS.debug("Saliendo de DetallBase.vuelve");

	};

	this.ocultarModulos = function(selector) {
		
		DebugJS.debug("Entrando en DetallBase.ocultarModulos");

		if ( !selector.hasClass("publicacio") )
			selector.addClass("invisible");
		
		DebugJS.debug("Saliendo de DetallBase.ocultarModulos");

	};

	/**
	 * Inicia la eliminación de un item confirmando la operación.
	 */
	this.eliminar = function() {
		
		DebugJS.debug("Entrando en DetallBase.eliminar");

		Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtItemEliminar, funcio: function() {
			that.elimina();
		}});
		
		DebugJS.debug("Saliendo de DetallBase.eliminar");

	};

	this.carregar = function(itemID) {
		
		DebugJS.debug("Entrando en DetallBase.carregar");

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
		
		DebugJS.debug("Saliendo de DetallBase.carregar");

	};

	this.recarregar = function(itemId) {
		
		DebugJS.debug("Entrando en DetallBase.recarregar");

		var url = location.protocol + "//" + location.host + location.pathname;

		var itemId = parseInt(itemId);

		if (!isNaN(itemId) && itemId > 0)
			url += "?itemId=" + itemId;

		this.modificado(false);

		location = url;
		
		DebugJS.debug("Saliendo de DetallBase.recarregar");

	};

	/**
	 * @param opcions: id (num); accio (guardar, elimina); dades: json
	 */
	this.array = function(opcions) {
		
		DebugJS.debug("Entrando en DetallBase.array");

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
		
		DebugJS.debug("Saliendo de DetallBase.array");
		
	};

	this.idioma = function(e) {
		
		DebugJS.debug("Entrando en DetallBase.idioma");

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
						
						// Si no hay hermanos que cumplan la condición, es que se ha seleccionado el mismo idioma
						// que el idioma principal en algún otro escritorio del mantenimiento, así que volvemos a mostrar
						// el anterior elemento afectado.
						DebugJS.debug('Mostrando $this.siblings("div.' + a_clicat_class + '")');
						DebugJS.debug($this.siblings("div." + a_clicat_class));
						
						if ($this.siblings("div." + a_clicat_class).length > 0) {
						
							$this.removeClass("seleccionat");
							$this.hide();
						
							$this.siblings("div." + a_clicat_class).slideDown(300, function() {
								jQuery(this).addClass("seleccionat");
							});
							
						} else {
							
							// Volvemos a añadir la clase seleccionat y a mostrar el objeto, por si se
							// ha ocultado previamente desde el menú principal de selección de idioma
							// (el menú de selección de idioma del mantenimiento, no el de selección de
							// idioma de un escritorio asociado).
							$this.addClass("seleccionat");
							$this.show();
							
						}

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
		
		DebugJS.debug("Saliendo de DetallBase.idioma");

	};

	this.previsualitza = function() {
		
		DebugJS.debug("Entrando en DetallBase.previsualitza");

		var url = this.urlPrevisualizar;
		var idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
		var id = escriptori_detall_elm.find("#item_id").val();
		
		url += "?lang=" + idiomaSeleccionat + "&codi=" + id + "&previ=s";
		
		var ancho = 1024;
		var alto = 768;
		window.open(url, 'ventanaPrevisualizar', "width=" + ancho + ", height=" + alto);
		
		DebugJS.debug("Saliendo de DetallBase.previsualitza");

	};

	this.traduir = function (url, inputs, datos) {
		
		DebugJS.debug("Entrando en DetallBase.traduir");

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
			if(typeof texto != 'undefined'){				
				texto = texto.replace(/%/g, "&#37;");
			}else{
				texto = "";
			}
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
					
					// Si todo ha ido bien, marcamos el detalle como modificado.
					that.modificado(true);

					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtTraduccioCorrecta});
					
				}
				
			}
		});
		
		DebugJS.debug("Saliendo de DetallBase.traduir");

	};

	this.cargarModulos = function() {
		
		DebugJS.debug("Entrando en DetallBase.cargarModulos");
		if (typeof modulos != 'undefined'){
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
		}
		DebugJS.debug("Saliendo de DetallBase.cargarModulos");

	};

}