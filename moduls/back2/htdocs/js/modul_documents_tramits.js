// MODUL DOCUMENTS TRAMITS

$(document).ready(function() {
	// elements
	escriptori_documents_tramits_elm = $("#escriptori_documents_tramits");
    moduls_elm = escriptori_tramits_elm.find("div.modul");
    modul_documents_tramits_elm = $("div.modulDocumentsTramit:first");
    
	ModulDocumentsTramit = new CModulDocumentsTramit();
    ModulDocumentsTramit.iniciar();
    
    EscriptoriPareTramit = new CEscriptoriPareTramit();
    EscriptoriPareTramit.iniciar();        
});

// Lista ordenable para eliminar/ordenar docs en la pantalla "padre"
function CEscriptoriPareTramit(){
	this.extend = ListaOrdenable;
	this.extend();		
    
    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "documentsTramit",
        nodoOrigen: modul_documents_tramits_elm.find(".listaOrdenable"),
        nodoDestino: "", // documents_seleccionats_elm.find(".listaOrdenable"),
        atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
        multilang: true
    }
	
	var that = this;
	
	this.iniciar = function() {
				
		// botons
		modul_documents_tramits_elm.find("a.gestiona").bind("click", function() { ModulDocumentsTramit.nou(false); } );
		
		documents_tramits_seleccionats_elm = escriptori_documents_tramits_elm.find("div.escriptori_items_seleccionats:first");
		
		escriptori_documents_tramits_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);
	}	

	this.gestiona = function() {
		lis_size = modul_documents_tramits_elm.find('div.cajaIdioma:first li').length;
		if (lis_size > 0) {
			EscriptoriEdifici.contaSeleccionats();
		} else {
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaEdificisSeleccionats + ".");			
			edificis_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		escriptori_tramits_elm.fadeOut(300, function() {			
			escriptori_edificis_elm.fadeIn(300);			
		});
	}
	
	this.eliminaItem = function(item) {
		var id = $(item).find("input." + that.configuracion.nombre + "_id:first").val();						
		$(that.configuracion.nodoOrigen).find("input[name=" + that.configuracion.nombre + "_id_" + id + "]").parents("li").remove();
		that.habilitarBotonGuardar();
	}
    
    this.habilitarBotonGuardar = function() {
    	escriptori_documents_tramits_elm.find("#btnGuardar").unbind("click").bind("click",function(){this.guarda_upload();}).parent().removeClass("off");
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
				if ($(this).val() == item.id) {
					actualizar = true;
				}			
			});			
		}
		
		if (actualizar) {
			for (var i in idiomas) {
				var idioma = idiomas[i];
				listas.find("input." + that.configuracion.nombre + "_nombre_" + idioma + "[name$=_" + item.id + "]").each(function() {
                    var $docInput = $(this);
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
                var lista = $(this);
                var idioma = that.getIdiomaActivo(lista);
				var htmlCode = that.getHtmlItem(item, true, idioma);
				lista.find("ul").append(htmlCode);					
			});
            
            ModulDocumentsTramit.inicializarDocuments();
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
function CModulDocumentsTramit(){
	this.extend = DetallBase;
	if (typeof FormulariDadesDocTramit != 'undefined') {
		this.extend(true, FormulariDadesDocTramit);
	} else {
		this.extend(true, null);
	}

	var that = this;
	
	this.iniciar = function() {			
        // botons        
        $("#btnVolver_documents_tramit").bind("click", that.torna);

        // El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario.
    	$("#formGuardarDocTramit input, #formGuardarDocTramit select, #formGuardarDocTramit textarea").bind("change", function(){that.modificado();});
    	
		// idioma
		if (escriptori_documents_tramits_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_documents_tramits_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_documents_tramits_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_documents_tramits_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
		}
		
		// Redifinimos el método que guarda porque en este caso también hacemos un upload de archivos.
		this.guarda = this.guarda_upload;
	}
	
	
	this.torna = function () {
		escriptori_documents_tramits_elm.fadeOut(300, function() {
			escriptori_tramits_elm.fadeIn(300);
	    });
	}
		
	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {
        // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }
        
        // Coger el id del trámite
        var tramitId = $("#tramitIddoc");         
        tramitId.val($("#id_tramit_actual").val());

		//Enviamos el formulario mediante el método ajaxSubmit del plugin $.form
		$("#formGuardarDocTramit").ajaxSubmit({			
			url: pagGuardarDocTramit,
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
                    escriptori_documents_tramits_elm.find("input[id^='doc_tramit_titol_']").each(function (index) {
						var $titolDoc = $(this);
						var idioma = $titolDoc.attr('id').split('_')[3];
						nom[idioma] = $titolDoc.val();
					});
					
					var docItem = new Object();
					docItem['id'] = data.id;
					docItem['nom'] = nom;
					EscriptoriPareTramit.agregaActualizaItem(docItem);

					that.torna();
				}
			}

		});
        
		return false;
	}
		
	this.modificado = function(){
		// Habilitamos el botón de guardar.
		$("#btnGuardar_documents_tramit").unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
	}
		
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
	}
			
	this.nou = function(edicion) {
		
		$("#tramitId").attr("value", $("#id_tramit_actual").val());		
		$("#procId").attr("value", $("#id_procediment_tramit").val());
		
		// El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario.
		$("#btnGuardar_documents_tramit").parent().addClass("off");
        
		if (!edicion) {
            $("#docTramitId").val("");
            for (var i in idiomas) {
                var idioma = idiomas[i];                
                $("#doc_tramit_descripcio_" + idioma + ", #doc_tramit_titol_" + idioma + ", #doc_tramit_arxiu_" + idioma).each(limpiarCampo);

                //Limpiar campos de fichero
            	$("#grup_arxiu_actual_doc_tramit_" + idioma + " span").show();
            	$("#grup_arxiu_actual_doc_tramit_" + idioma + " input").hide();
            	$("#grup_arxiu_actual_doc_tramit_" + idioma + " label.eliminar").hide();
            	$("#grup_arxiu_actual_doc_tramit_" + idioma + " a").hide().attr("href","").empty();
            	
            	var thumbnail = $("#grup_arxiu_actual_doc_tramit_" + idioma).closest(".fila").find(".thumbnail");
            	
            	if (thumbnail.size() > 0) 
            		thumbnail.children().remove();
            }
		}
		
		escriptori_tramits_elm.fadeOut(300, function() {
			escriptori_documents_tramits_elm.fadeIn(300);
		});
		
		this.actualizaEventos();
	}		
	
	
	this.pintar = function(dades) {		
		dada_node = dades;
		
		$("#docTramitId").val(dada_node.item_id);

		// Bloque de pestanyas de idiomas.
		for (var i in idiomas) {
			var idioma = idiomas[i];
            
			$("#doc_tramit_titol_" + idioma).val(printStringFromNull(dada_node["idioma_titol_" + idioma]));
			$("#doc_tramit_descripcio_" + idioma).val(printStringFromNull(dada_node["idioma_descripcio_" + idioma]));
			
			// archivos
			$("#doc_tramit_arxiu_" + idioma).val("");
			$("#grup_arxiu_actual_doc_tramit_" + idioma + " input").removeAttr("checked");
			var anchors = $("#grup_arxiu_actual_doc_tramit_" + idioma + " a");

            var enllasArxiu = dada_node["idioma_enllas_arxiu_" + idioma];
			if (typeof enllasArxiu != "undefined" && enllasArxiu != "") {
				anchors.attr("href", pagArrel + dada_node["idioma_enllas_arxiu_" + idioma]);
				anchors.text(dada_node["idioma_nom_arxiu_" + idioma]);
				anchors.show();
				$("#grup_arxiu_actual_doc_tramit_" + idioma + " span").hide();
				$("#grup_arxiu_actual_doc_tramit_" + idioma + " input").show();
				$("#grup_arxiu_actual_doc_tramit_" + idioma + " label.eliminar").show();
			} else {
                $("#grup_arxiu_actual_doc_tramit_" + idioma + " span").show();
				$("#grup_arxiu_actual_doc_tramit_" + idioma + " input").hide();
				$("#grup_arxiu_actual_doc_tramit_" + idioma + " label.eliminar").hide();
                anchors.hide();
            }
		}
		// Fin bloque de pestanyas de idiomas
		
        // Mostrar la pantalla de edicion de documento
		that.nou(true);
	}
	
	this.contaSeleccionats = function() {		
		var seleccionats_val = modul_documents_tramits_elm.find(".seleccionat").find("li").size();
		var info_elms = modul_documents_tramits_elm.find("p.info");
		
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
            modul_documents_tramits_elm.find(".listaOrdenable").empty();		
			EscriptoriPareTramit.agregaItems(listaDocuments, true);
        }
				       
        // Editar el documento al hacer click sobre el.
        modul_documents_tramits_elm.find('div.documentsTramit').each(function() {
            $(this).unbind("click").bind("click", function() {
                var docTramitId = $(this).find("input.documentsTramit_id").val();
                var tramitId  = $("#id_tramit_actual").val();
                
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
                $.ajax({
                    type: "GET",
                    url: pagCarregarDocTramit,
                    data: "id=" + docTramitId + "&idTramit=" + tramitId,
                    dataType: "json",
                    error: function() {
                        // Missatge.cancelar();
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                        Error.llansar();
                    },
                    success: function(data) {
                        Missatge.cancelar();
                        if (data.id > 0) {
                            that.pintar(data.documentTramit);
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
		
		modul_documents_tramits_elm.find(".listaOrdenable ul").sortable({ 
			axis: 'y', 
			update: function(event,ui){
				EscriptoriPareTramit.calculaOrden(ui,"origen");
				EscriptoriPareTramit.habilitarBotonGuardar();
			}
		}).css({cursor:"move"});
				
		modul_documents_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = $(this).parents("li:first");
			EscriptoriPareTramit.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
	}
	
	// Devuelve un string con el formato documentsTramit=n1,n2,...,nm donde n son codigos de documentos de un trámite.
	this.listarDocumentos = function (){
		var listaDocumentos = "documentsTramit=";
		var separador = "";
		
		modul_documents_tramits_elm.find(".ca div.listaOrdenable input.documentsTramit_id").each(function() {
			listaDocumentos += separador + $(this).val();
			separador = ",";
		});
		
		return listaDocumentos;
	}	
	
};