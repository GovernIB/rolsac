
jQuery(document).ready(function() {
	// elements
	escriptori_documents_elm_lopd = jQuery("#escriptori_documents_lopd");
    moduls_elm_lopd = escriptori_detall_elm.find("div.modul");
    modul_documents_elm_lopd = jQuery("div#modulDocumentsLopd:first");

	ModulDocumentsLopd = new CModulDocumentsLopd();
	ModulDocumentsLopd.iniciar();

    EscriptoriPareLopd = new CEscriptoriPareLopd();
    EscriptoriPareLopd.iniciar();

    // datos traductor
	CAMPOS_TRADUCTOR_DOCUMENTO_LOPD = [];
	DATOS_TRADUCIDOS_DOCUMENTO_LOPD = [];
});

// Lista ordenable para elimiar/ordenar docs en la pantalla "padre"
function CEscriptoriPareLopd() {

	this.extend = ListaOrdenable;
	this.extend();

    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "documents",
        nodoOrigen: modul_documents_elm_lopd.find(".listaOrdenable"),
        nodoDestino: "", // documents_seleccionats_elm.find(".listaOrdenable"),
        atributos: ["id", "nombre", "orden", "idMainItem", "idRelatedItem"],	// Campos que queremos que aparezcan en las listas.
        multilang: true
    };

	var that = this;

	this.iniciar = function() {

		// botons
		modul_documents_elm_lopd.find("a.gestiona").bind("click", function() {
			if (modul_documents_elm_lopd.find(".listaOrdenable ul").size() > 0 && modul_documents_elm_lopd.find(".listaOrdenable ul")[0].childNodes.length > 0) {

				var procId = $("#item_id").val();
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
				$.ajax({
					type: "GET",
					url: pagCarregarDocLopd,
					data: "id=" + procId,
					dataType: "json",
					error: function() {
						// Missatge.cancelar();
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						Error.llansar();
					},
					success: function(data) {
						Missatge.cancelar();
						if (data.id > 0) {
							ModulDocumentsLopd.pintar(data.document);
						} else if (data.id == -1) {
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
							Error.llansar();
						} else if (data.id < -1) {
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
							Error.llansar();
						}
					}
				});

			} else {
				ModulDocumentsLopd.nou(false);
			}
		});

		documents_seleccionats_elm = escriptori_documents_elm_lopd.find("div.escriptori_items_seleccionats:first");

		escriptori_documents_elm_lopd.find("div.botonera").each(function() {
			botonera_elm = $(this);
		});

		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);

	};

	this.gestiona = function() {

		lis_size = modul_documents_elm_lopd.find('div.cajaIdioma:first li').length;

		if (lis_size > 0) {
			EscriptoriEdifici.contaSeleccionats();
		} else {
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaEdificisSeleccionats + ".");
			edificis_seleccionats_elm.find(".listaOrdenable").html("");
		}

		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_edificis_elm.fadeIn(300);
		});

	};

	this.eliminaItem = function(item) {

		var id = jQuery(item).find("input." + that.configuracion.nombre + "_id:first").val();

		jQuery(that.configuracion.nodoOrigen).find("input[name=" + that.configuracion.nombre + "_id_" + id + "]").parents("li").remove();

		$('#modulo_documents_modificado').val("1");

	};

    /**
	 * Agrega o actualiza un item en la lista de origen.
	 * @return boolean Devuelve false si el item se agraga a la lista, true si lo actualiza.
	 */
	this.agregaActualizaItem = function(item) {

		var listas = that.configuracion.nodoOrigen;
		var tamLista = listas.first().find("ul:first").find("li").size();
		var actualizar = false;

		if (tamLista == 0) {
			listas.html("<ul></ul>");
		} else {
			listas.first().find("input." + that.configuracion.nombre + "_id").each(function() {
				if (jQuery(this).val() == item.id) {
					actualizar = true;
				}
			});
		}

		if (actualizar) {

			for (var i in idiomas) {

				var idioma = idiomas[i];

				listas.find("input." + that.configuracion.nombre + "_nombre_" + idioma + "[name$=_" + item.id + "]").each(function() {
                    var $docInput = jQuery(this);
                    var $docSpan = $docInput.next();
                    if (item["nombre"][idioma] != undefined && item["nombre"][idioma] != "") {
                    	$docInput.val(item["nombre"][idioma]);
                    	$docSpan.text(item["nombre"][idioma]);
                    } else {
                    	$docInput.val(item["nombre"][idioma]);
                    	$docSpan.html("&nbsp;");
                    }
                });

			}

		} else {

            if (typeof item["orden"] != "number") {
                item["orden"] = that.obtenerUltimoOrden(listas, tamLista) + 1;
            }

			listas.each(function(){
                var lista = jQuery(this);
                var idioma = that.getIdiomaActivo(lista);
				var htmlCode = that.getHtmlItem(item, true, idioma);
				lista.find("ul").append(htmlCode);
			});

            ModulDocumentsLopd.inicializarDocuments();

		}

		ModulDocumentsLopd.modificado(false);

		return actualizar;

	};

	this.obtenerUltimoOrden = function(listas, tamLista) {

		var ultimoOrden = -1;

		if (tamLista > 0) {
			ultimoOrden = parseInt(listas.first().find("li:last input." + that.configuracion.nombre + "_orden").val());
		}

		return ultimoOrden;

	};

};

// Creacion/edicion de docs
function CModulDocumentsLopd() {

	this.extend = DetallBase;

	if (typeof FormulariDadesDocLopd != 'undefined') {

		this.extend(true, FormulariDadesDocLopd,{
		    form: "formGuardarDocLopd",
		    btnGuardar: "btnGuardar_documents_lopd",
		    btnVolver: "btnVolver_documents_lopd"
		});

	} else {

		this.extend(true, null);

	}

	var that = this;

	this.iniciar = function() {

    	// boton de traducir
        jQuery("#botoTraduirDocumentLopd").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });

		// idioma
		if (escriptori_documents_elm_lopd.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_documents_elm_lopd.find('div.idioma').slice(1).hide();

			var ul_idiomes_elm = escriptori_documents_elm_lopd.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");

			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();

			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");

			var div_idiomes_elm = escriptori_documents_elm_lopd.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
		}

		// Redifinimos el método que guarda porque en este caso también hacemos un upload de archivos.
		this.guarda = this.guarda_upload;

	};

	this.traduirWrapper = function () {
		that.traduir(pagTraduirDocument, CAMPOS_TRADUCTOR_DOCUMENTO_LOPD, DATOS_TRADUCIDOS_DOCUMENTO_LOPD);
	};

	this.vuelve = function () {

	    if ( this.cambiosSinGuardar() ) {

            Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtAvisoCambiosSinGuardar, funcio: function() {

                escriptori_documents_elm_lopd.fadeOut(300, function() {
                    escriptori_detall_elm.fadeIn(300);
                });

                that.modificado(false);

                Missatge.cancelar();

            }});

        } else {

            escriptori_documents_elm_lopd.fadeOut(300, function() {
                escriptori_detall_elm.fadeIn(300);
            });

            this.modificado(false);

        }

	};

	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {

        // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }

        // Coger el id del procedimiento o de la ficha (depende del mantenimiento/jsp en el que estemos).
        // XXX amartin: añadir más casos aquí si este módulo se añade a otros mantenimientos.
        var procId = jQuery("#procedimientoId");
        if (procId.length > 0) {
        	procId.val(jQuery("#item_id").val());
        } else {
        	jQuery("#fitxaId").val(jQuery("#item_id").val());
        }

		// Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
		$("#formGuardarDocLopd").ajaxSubmit({
			url: pagGuardarDocLopd,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {

				Llistat.cacheDatosListado = null;

				if (data.id < 0) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});

				} else {

					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});

					var nom = new Object();
					var nomca = "";
					var nomes = "";

					if(jQuery(escriptori_documents_elm_lopd.find("[id^='doc_arxiu_lop_es_delete']")[0]).attr('checked') != 'checked') {
						nomes = jQuery(escriptori_documents_elm_lopd.find("[id^='doc_arxiu_es']")[0]).val().split('\\').pop();
						if (nomes == "" &&  escriptori_documents_elm_lopd.find("[id^='grup_arxiu_actual_doc_lopd_es'] a").length == 1) {
							nomes = escriptori_documents_elm_lopd.find("[id^='grup_arxiu_actual_doc_lopd_es'] a ")[0].innerHTML;
						}
					}

					if(jQuery(escriptori_documents_elm_lopd.find("[id^='doc_arxiu_lop_ca_delete']")[0]).attr('checked') != 'checked') {
						nomca = jQuery(escriptori_documents_elm_lopd.find("[id^='doc_arxiu_ca']")[0]).val().split('\\').pop();

						if (nomca == "" &&  escriptori_documents_elm_lopd.find("[id^='grup_arxiu_actual_doc_lopd_ca'] a").length == 1) {
							nomca = escriptori_documents_elm_lopd.find("[id^='grup_arxiu_actual_doc_lopd_ca'] a ")[0].innerHTML;
						}
					}

					nom['es'] = nomes;
					nom['ca'] = nomca;

					var docItem = new Object();
					docItem['id'] = data.id;
					docItem['nombre'] = nom;
					docItem['idMainItem'] = $('#item_id').val();
					docItem['idRelatedItem'] = data.id;

					EscriptoriPareLopd.agregaActualizaItem(docItem);

					$('#modulo_documents_modificado').val("1");

					that.modificado(false);

					that.vuelve();

				}

			}

		});

		return false;

	};

	this.dataPublicacio = function(e) {
		if (jQuery(this).val() == "") {
			jQuery(this).val(txtImmediat);
		}
	};

	this.limpia = function() {
		for (var i in idiomas) {

            var idioma = idiomas[i];
            jQuery("#doc_arxiu_lop_" + idioma).each(limpiarCampo);
            limpiarArchivoMultiidioma("arxiu_actual_doc_lopd", idioma);

        }
		$("#modul_documents_lopd").addClass("invisible");
	};

	this.nou = function(edicion) {

		if (!edicion) {

            jQuery("#docId").val("");

            for (var i in idiomas) {

                var idioma = idiomas[i];
                jQuery("#doc_arxiu_lop_" + idioma).each(limpiarCampo);
                limpiarArchivoMultiidioma("arxiu_actual_doc_lopd", idioma);

            }

		}

		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_documents_elm_lopd.fadeIn(300);
		});

		this.modificado(false);

		this.actualizaEventos();

	};

	this.pintar = function(dades) {

		dada_node = dades;

		jQuery("#docId").val(dada_node.item_id);



		// Bloque de pestanyas de idiomas.
		for (var i in idiomas) {

			var idioma = idiomas[i];

			// archivos
			$("#modulDocumentsLopd #doc_arxiu_" + idioma).val("");
			$("#grup_arxiu_actual_doc_lopd_" + idioma + " input").removeAttr("checked");

			var anchors = $("#grup_arxiu_actual_doc_lopd_" + idioma + " a");
            var enllasArxiu = dada_node["idioma_enllas_arxiu_" + idioma];

			if (typeof enllasArxiu != "undefined" && enllasArxiu != "") {

				anchors.attr("href", pagArrel + dada_node["idioma_enllas_arxiu_" + idioma]);
				anchors.text(dada_node["idioma_nom_arxiu_" + idioma]);
				anchors.show();

				$("#grup_arxiu_actual_doc_lopd_" + idioma + " span").hide();
				$("#grup_arxiu_actual_doc_lopd_" + idioma + " input").show();
				$("#grup_arxiu_actual_doc_lopd_" + idioma + " label.eliminar").show();

			} else {

                $("#grup_arxiu_actual_doc_lopd_" + idioma + " span").show();
				$("#grup_arxiu_actual_doc_lopd_" + idioma + " input").hide();
				$("#grup_arxiu_actual_doc_lopd_" + idioma + " label.eliminar").hide();

                anchors.hide();

            }

		}
		// Fin bloque de pestanyas de idiomas

        // Mostrar la pantalla de edicion de documento
		that.nou(true);

	};

	this.contaSeleccionats = function() {

		var seleccionats_val = modul_documents_elm_lopd.find(".seleccionat").find("li").size();
		var info_elms = modul_documents_elm_lopd.find("p.info");

		if (seleccionats_val == 0) {
			info_elms.text(txtNoHiHaDocumentsSeleccionats+ ".");
		} else if (seleccionats_val == 1) {
			info_elms.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtDocument.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elms.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtDocuments.toLowerCase() + "</strong>.");
		}

	};

	this.inicializarDocuments = function(listaDocuments, permiteGuardar) {

		if (typeof listaDocuments != 'undefined' && listaDocuments != null) {
            modul_documents_elm_lopd.find(".listaOrdenable").empty();
            var mostrarBtnEliminar = true;
            if (permiteGuardar != null && permiteGuardar != undefined && permiteGuardar == 'N') {
            	mostrarBtnEliminar = false;
            }
			EscriptoriPareLopd.agregaItems(listaDocuments, mostrarBtnEliminar);
        }

        // Editar el documento al hacer click sobre el.
        this.editarBotonEdicion();

		that.contaSeleccionats();

		modul_documents_elm_lopd.find(".listaOrdenable ul").sortable({
			axis: 'y',
			update: function(event,ui){
				EscriptoriPareLopd.calculaOrden(ui,"origen");
				ModulDocumentsLopd.habilitarBotonGuardar();
				that.activarEventoBotonBorrar(); //El evento sobre el boton de borrar
				that.editarBotonEdicion(); //El evento al click para editar
			}
		}).css({cursor:"move"});

		this.activarEventoBotonBorrar();


	};
	this.editarBotonEdicion = function () {
		modul_documents_elm_lopd.find('div.documents').each(function() {

		            $(this).unbind("click").bind("click", function() {

		                //var docId = $(this).find("input.documents_id").val();
		            	//Como solo hay uno , se carga a partir del id de proc
		                var procId = $("#item_id").val();
		                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		                $.ajax({
		                    type: "GET",
		                    url: pagCarregarDocLopd,
		                    data: "id=" + procId,
		                    dataType: "json",
		                    error: function() {
		                        // Missatge.cancelar();
		                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
		                        Error.llansar();
		                    },
		                    success: function(data) {
		                        Missatge.cancelar();
		                        if (data.id > 0) {
		                            that.pintar(data.document);
		                        } else if (data.id == -1) {
		                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
		                            Error.llansar();
		                        } else if (data.id < -1) {
		                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
		                            Error.llansar();
		                        }
		                    }
		                });

		            });

		        });
	};
	this.activarEventoBotonBorrar = function () {
		modul_documents_elm_lopd.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {

			var itemLista = jQuery(this).parents("li:first");
			EscriptoriPareLopd.eliminaItem(itemLista);
			that.contaSeleccionats();

			// Si hay botón de guardado, hay que marcar la página como modificada.
			// Si no, el guardado se hace vía botón "Finalizar".
			if (that.existeBotonGuardar()) {
				Detall.modificado(true);
				that.habilitarBotonGuardar();
			}

		});
	};

	this.botonGuardar = jQuery("#btnGuardar_documentos_lopd");

	this.existeBotonGuardar = function() {
		return (this.botonGuardar.length > 0);
	};

	this.habilitarBotonGuardar = function() {
		if (this.existeBotonGuardar()) {
			this.botonGuardar.show(500);
	        Detall.modificado();
		}
    };

    this.deshabilitarBotonGuardar = function() {
    	if (this.existeBotonGuardar()) {
    		this.botonGuardar.css("display", "none");
    	}
    };

};

function CListaMultiidiomaDocumentos() {

	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaMultiidioma;
	this.extend();

	var that = this;

	// Método sobreescrito para obtener qué datos guardar en este caso.
	this.getFilters = function(elements, id) {

		if (debug)
			console.log("Entrando en CListaMultiidiomaDocumentos.getFilters");

		var lista = new Array();
		var filters = "id=" + id;

		if (elements.length > 0) {

			// Lista de <li>.
			elements.each(function() {

				if (elements.length > 0) {

					elements.each(function() {

						var value = $(this).attr('related-item-id');
						lista.push(value);

					});

					filters += "&elementos=" + lista;

				}

			});

		}

		if (debug)
			console.log("Saliendo de CListaMultiidiomaDocumentos.getFilters");

		return filters;

	};

};

/**
 * (amartin) Explicación de extensión de clase:
 *
 * Extendemos la clase para que, tras el guardado, se oculte el botón de guardado del módulo lateral.
 * Al marcar un elemento para ser borrado o al reordenar alguno, aparecerá el botón de guardar.
 * Al realizar la acción de guardado, el botón de guardar desaparecerá.
 */
function CListaSimpleDocumentosLopd() {

	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaSimple;
	this.extend();

	var that = this;

	this._guardar = this.guardar;

	this.guardar = function(element, url, id) {

		if (debug)
			console.log("Entrando en CListaSimpleDocumentos.guardar");

		that._guardar(element, url, id);

		if (typeof ModulDocumentsLopd != 'undefined') {
			ModulDocumentsLopd.deshabilitarBotonGuardar();
		}

		Detall.modificado(false);

		if (debug) {
			console.log("Entrando en CListaSimpleDocumentosLopd.guardar");
		}
	};

};