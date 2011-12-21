// MODUL DOCUMENTS

jQuery(document).ready(function() {
	// elements
	escriptori_documents_elm = jQuery("#escriptori_documents");
    moduls_elm = escriptori_detall_elm.find("div.modul");
    modul_documents_elm = jQuery("div.modulDocuments:first");
    
	ModulDocuments = new CModulDocuments();
    ModulDocuments.iniciar();
    
    EscriptoriPare = new CEscriptoriPare();
    EscriptoriPare.iniciar();
});


// Lista ordenable para elimiar/ordenar docs en la pantalla "padre"
function CEscriptoriPare(){
	this.extend = ListaOrdenable;
	this.extend();		
    
    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "documents",
        nodoOrigen: modul_documents_elm.find(".listaOrdenable"),
        nodoDestino: "", // documents_seleccionats_elm.find(".listaOrdenable"),
        atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
        multilang: true
    }
	
	var that = this;
	
	this.iniciar = function() {
		// botons
		modul_documents_elm.find("a.gestiona").bind("click", function(){ModulDocuments.nou(false);} );
		
		documents_seleccionats_elm = escriptori_documents_elm.find("div.escriptori_items_seleccionats:first");
		
		escriptori_documents_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);
	}	

	this.gestiona = function() {
		lis_size = modul_documents_elm.find('div.cajaIdioma:first li').length;
		if (lis_size > 0) {
			EscriptoriEdifici.contaSeleccionats();
		} else {
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaEdificisSeleccionats + ".");			
			edificis_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_edificis_elm.fadeIn(300);			
		});
	}
	
	this.eliminaItem = function(item) {
		var id = jQuery(item).find("input." + that.configuracion.nombre + "_id:first").val();						
		jQuery(that.configuracion.nodoOrigen).find("input[name=" + that.configuracion.nombre + "_id_" + id + "]").parents("li").remove();
		that.habilitarBotonGuardar();
	}
    
    this.habilitarBotonGuardar = function() {
        jQuery("#btnGuardar").unbind("click").bind("click",function(){Detall.guarda();}).parent().removeClass("off");
    }
    
    
    /**
	 * Agrega o actualiza un item en la lista de origen.
	 * @return boolean Devuelve false si el item se agraga a la lista, true si lo actualiza.
	 */
	this.agregaActualizaItem = function(item){
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
                    $docInput.val(item["nombre"][idioma]);
                    $docSpan.text(item["nombre"][idioma]);
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
            
            ModulDocuments.inicializarDocuments();
		}
		
		that.habilitarBotonGuardar();
		
		return actualizar;
	}
	

	this.obtenerUltimoOrden = function(listas, tamLista) {
		var ultimoOrden = -1;
		if (tamLista > 0) {
			ultimoOrden = parseInt(listas.first().find("li:last input." + that.configuracion.nombre + "_orden").val());  
		}
		return ultimoOrden;
	}
};


// Creacion/edicion de docs
function CModulDocuments(){
	this.extend = DetallBase;
	if (typeof FormulariDadesDoc != 'undefined') {
		this.extend(true, FormulariDadesDoc);
	} else {
		this.extend(true, null);
	}

	var that = this;
	
	this.iniciar = function() {			
        // botons        
        jQuery("#btnVolver_documents").bind("click", that.torna);

        // El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario.
    	jQuery("#formGuardarDoc input, #formGuardarDoc select, #formGuardarDoc textarea").bind("change", function(){that.modificado();});
    	
		// idioma
		if (escriptori_documents_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_documents_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_documents_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_documents_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
		}
		
		// Redifinimos el método que guarda porque en este caso también hacemos un upload de archivos.
		this.guarda = this.guarda_upload;
	}
	
	
	this.torna = function () {
		escriptori_documents_elm.fadeOut(300, function() {
	        escriptori_detall_elm.fadeIn(300);
	    });
	}
	
	
	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {
        // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }
        
        // Coger el id del procedimiento o de la ficha (depende del mantenimiento/jsp en el que estemos).
        var procId = jQuery("#procId");
        if (procId.length > 0) {
        	procId.val(jQuery("#item_id").val());
        } else {
        	jQuery("#fitxaId").val(jQuery("#item_id").val());
        }

		//Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
		$("#formGuardarDoc").ajaxSubmit({			
			url: pagGuardarDoc,
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
                    escriptori_documents_elm.find("input[id^='doc_titol_']").each(function (index) {
						var $titolDoc = jQuery(this);
						var idioma = $titolDoc.attr('id').split('_')[2];
						nom[idioma] = $titolDoc.val();
					});
					
					var docItem = new Object();
					docItem['id'] = data.id;
					docItem['nombre'] = nom;
					EscriptoriPare.agregaActualizaItem(docItem);

					that.torna();
				}
			}

		});
        
		return false;
	}
	
	
	this.modificado = function(){
		// Habilitamos el botón de guardar.
		jQuery("#btnGuardar_documents").unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
	}
	
	
	this.dataPublicacio = function(e) {		
		if (jQuery(this).val() == "") {
			jQuery(this).val(txtImmediat);
		}
	}
		
	
	this.nou = function(edicion) {
		// El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario.
		jQuery("#btnGuardar_documents").parent().addClass("off");
        
		if (!edicion) {
            jQuery("#docId").val("");
            for (var i in idiomas) {
                var idioma = idiomas[i];
                jQuery("#doc_descripcio_" + idioma, "#doc_titol_" + idioma, "#doc_arxiu_" + idioma).each(limpiarCampo);
                jQuery("#grup_arxiu_actual_doc_" + idioma + " span").show();
				jQuery("#grup_arxiu_actual_doc_" + idioma + " input").hide();
				jQuery("#grup_arxiu_actual_doc_" + idioma + " label.eliminar").hide();
                jQuery("#grup_arxiu_actual_doc_" + idioma + " a").hide();
            }
		}
		
		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_documents_elm.fadeIn(300);
		});
		
		this.actualizaEventos();
	}		
	
	
	this.pintar = function(dades) {		
		dada_node = dades;
		
		jQuery("#docId").val(dada_node.item_id);

		// Bloque de pestanyas de idiomas.
		for (var i in idiomas) {
			var idioma = idiomas[i];
            
			jQuery("#doc_titol_" + idioma).val(printStringFromNull(dada_node["idioma_titol_" + idioma]));
			jQuery("#doc_descripcio_" + idioma).val(printStringFromNull(dada_node["idioma_descripcio_" + idioma]));
			
			// archivos
			$("#doc_arxiu_" + idioma).val("");
			$("#grup_arxiu_actual_doc_" + idioma + " input").removeAttr("checked");
			var anchors = $("#grup_arxiu_actual_doc_" + idioma + " a");

            var enllasArxiu = dada_node["idioma_enllas_arxiu_" + idioma];
			if (typeof enllasArxiu != "undefined" && enllasArxiu != "") {
				anchors.attr("href", pagArrel + dada_node["idioma_enllas_arxiu_" + idioma]);
				anchors.text(dada_node["idioma_nom_arxiu_" + idioma]);
				anchors.show();
				$("#grup_arxiu_actual_doc_" + idioma + " span").hide();
				$("#grup_arxiu_actual_doc_" + idioma + " input").show();
				$("#grup_arxiu_actual_doc_" + idioma + " label.eliminar").show();
			} else {
                $("#grup_arxiu_actual_doc_" + idioma + " span").show();
				$("#grup_arxiu_actual_doc_" + idioma + " input").hide();
				$("#grup_arxiu_actual_doc_" + idioma + " label.eliminar").hide();
                anchors.hide();
            }
		}
		// Fin bloque de pestanyas de idiomas
		
        // Mostrar la pantalla de edicion de documento
		that.nou(true);
	}
	
	this.contaSeleccionats = function() {		
		var seleccionats_val = modul_documents_elm.find(".seleccionat").find("li").size();
		var info_elms = modul_documents_elm.find("p.info");
		
		if (seleccionats_val == 0) {
			info_elms.text(txtNoHiHaDocumentsSeleccionats+ ".");
		} else if (seleccionats_val == 1) {
			info_elms.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtDocument.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elms.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtDocuments.toLowerCase() + "</strong>.");						
		}
	}
	
	
	this.inicializarDocuments = function(listaDocuments) {
		if (typeof listaDocuments != 'undefined' && listaDocuments != null) {
            modul_documents_elm.find(".listaOrdenable").empty();		
			EscriptoriPare.agregaItems(listaDocuments, true);
        }
        
        // Editar el documento al hacer click sobre el.
        modul_documents_elm.find('div.documents').each(function() {
            $(this).unbind("click").bind("click", function() {
                var docId = $(this).find("input.documents_id").val();
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
                $.ajax({
                    type: "GET",
                    url: pagCarregarDoc,
                    data: "id=" + docId,
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
                        } else if (data.id == -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                        } else if (data.id < -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                        }
                    }
                });
            });
        });
		
		that.contaSeleccionats();
		
		modul_documents_elm.find(".listaOrdenable ul").sortable({ 
			axis: 'y', 
			update: function(event,ui){
				EscriptoriPare.calculaOrden(ui,"origen");
				EscriptoriPare.habilitarBotonGuardar();
			}
		}).css({cursor:"move"});
		
		modul_documents_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			EscriptoriPare.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
	}
};