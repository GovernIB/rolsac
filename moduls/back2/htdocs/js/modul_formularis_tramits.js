$(document).ready(function() {
	// elements
	escriptori_formularis_tramits_elm = $("#escriptori_formularis_tramits");
    moduls_elm = escriptori_tramits_elm.find("div.modul");
    modul_formularis_tramits_elm = $("div.modulFormularisTramit:first");
    
	ModulFormularisTramit = new CModulFormularisTramit();
    ModulFormularisTramit.iniciar();
    
    EscriptoriPareTramitForm = new CEscriptoriPareTramitForm();
    EscriptoriPareTramitForm.iniciar();
});

// Lista ordenable para eliminar/ordenar forms en la pantalla "padre"
function CEscriptoriPareTramitForm(){
	this.extend = ListaOrdenable;
	this.extend();		
    
    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "formularisTramit",
        nodoOrigen: modul_formularis_tramits_elm.find(".listaOrdenable"),
        nodoDestino: "", // documents_seleccionats_elm.find(".listaOrdenable"),
        atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
        multilang: true
    }
	
	var that = this;
	
	this.iniciar = function() {
				
		// botons
		modul_formularis_tramits_elm.find("a.gestiona").bind("click", function() { ModulFormularisTramit.nou(false); } );
		
		formularis_tramits_seleccionats_elm = escriptori_formularis_tramits_elm.find("div.escriptori_items_seleccionats:first");
		
		escriptori_formularis_tramits_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);
	}	

	this.gestiona = function() {
		lis_size = modul_formularis_tramits_elm.find('div.cajaIdioma:first li').length;
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
        //$("#btnGuardar").unbind("click").bind("click",function(){Detall.guarda();}).parent().removeClass("off");
    	escriptori_formularis_tramits_elm.find("#btnGuardar").unbind("click").bind("click",function(){this.guarda_upload();}).parent().removeClass("off");
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
                    var $formInput = $(this);
                    var $formSpan = $formInput.next();
                    $formInput.val(item["nombre"][idioma]);
                    $formSpan.text(item["nombre"][idioma]);
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
            
            ModulFormularisTramit.inicializarFormularis();
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

// Creacion/edicion de forms
function CModulFormularisTramit(){
	this.extend = DetallBase;
	if (typeof FormulariDadesFormTramit != 'undefined') {
		this.extend(true, FormulariDadesFormTramit);
	} else {
		this.extend(true, null);
	}

	var that = this;
	
	this.iniciar = function() {			
        // botons        
        $("#btnVolver_formularis_tramit").bind("click", that.torna);

        // El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario.
    	$("#formGuardarFormTramit input, #formGuardarFormTramit select, #formGuardarFormTramit textarea").bind("change", function(){that.modificado();});
    	
		// idioma
		if (escriptori_formularis_tramits_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_formularis_tramits_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_formularis_tramits_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_formularis_tramits_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
		}
		
		// Redifinimos el m�todo que guarda porque en este caso tambi�n hacemos un upload de archivos.
		this.guarda = this.guarda_upload;
	}
	
	
	this.torna = function () {
		escriptori_formularis_tramits_elm.fadeOut(300, function() {
			escriptori_tramits_elm.fadeIn(300);
	    });
	}
		
	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {
        // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }
        
        // Coger el id del tr�mite
        var tramitId = $("#tramitIdform");         
        tramitId.val($("#id_tramit_actual").val());

		//Enviamos el formulario mediante el m�todo ajaxSubmit del plugin $.form
		$("#formGuardarFormTramit").ajaxSubmit({			
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
                    escriptori_formularis_tramits_elm.find("input[id^='form_tramit_titol_']").each(function (index) {
						var $titolDoc = $(this);
						var idioma = $titolDoc.attr('id').split('_')[3];
						nom[idioma] = $titolDoc.val();
					});
					
					var docItem = new Object();
					docItem['id'] = data.id;
					docItem['nom'] = nom;
					EscriptoriPareTramitForm.agregaActualizaItem(docItem);

					that.torna();
				}
			}

		});
        
		return false;
	}
	
	this.modificado = function(){
		// Habilitamos el bot�n de guardar.
		$("#btnGuardar_formularis_tramit").unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
	}
	
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
	}
			
	this.nou = function(edicion) {
		
		$("#tramitId").attr("value", $("#id_tramit_actual").val());		
		$("#procId").attr("value", $("#id_procediment_tramit").val());
		
		// El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario.
		$("#btnGuardar_formularis_tramit").parent().addClass("off");
        
		if (!edicion) {
            $("#formTramitId").val("");
            for (var i in idiomas) {
                var idioma = idiomas[i];                
                $("#form_tramit_descripcio_" + idioma + ", #form_tramit_titol_" + idioma + ", #form_tramit_arxiu_" + idioma).each(limpiarCampo);
                
                limpiarArchivoMultiidioma("arxiu_actual_form", idiomas[i]);

            }
		}
		
		escriptori_tramits_elm.fadeOut(300, function() {
			escriptori_formularis_tramits_elm.fadeIn(300);
		});
		
		this.actualizaEventos();
	}			
	
	this.pintar = function(dades) {		
		dada_node = dades;
		
		$("#formTramitId").val(dada_node.item_id);

		// Bloque de pestanyas de idiomas.
		for (var i in idiomas) {
			var idioma = idiomas[i];
            
			$("#form_tramit_titol_" + idioma).val(printStringFromNull(dada_node["idioma_titol_" + idioma]));
			$("#form_tramit_descripcio_" + idioma).val(printStringFromNull(dada_node["idioma_descripcio_" + idioma]));
			
			// archivos
			$("#form_tramit_arxiu_" + idioma).val("");
			$("#grup_arxiu_actual_form_tramit_" + idioma + " input").removeAttr("checked");
			var anchors = $("#grup_arxiu_actual_form_tramit_" + idioma + " a");

            var enllasArxiu = dada_node["idioma_enllas_arxiu_" + idioma];
			if (typeof enllasArxiu != "undefined" && enllasArxiu != "") {
				anchors.attr("href", pagArrel + dada_node["idioma_enllas_arxiu_" + idioma]);
				anchors.text(dada_node["idioma_nom_arxiu_" + idioma]);
				anchors.show();
				$("#grup_arxiu_actual_form_tramit_" + idioma + " span").hide();
				$("#grup_arxiu_actual_form_tramit_" + idioma + " input").show();
				$("#grup_arxiu_actual_form_tramit_" + idioma + " label.eliminar").show();
			} else {
                $("#grup_arxiu_actual_form_tramit_" + idioma + " span").show();
				$("#grup_arxiu_actual_form_tramit_" + idioma + " input").hide();
				$("#grup_arxiu_actual_form_tramit_" + idioma + " label.eliminar").hide();
                anchors.hide();
            }
		}
		// Fin bloque de pestanyas de idiomas
        // Mostrar la pantalla de edicion de documento
		that.nou(true);
	}
	
	this.contaSeleccionats = function() {		
		var seleccionats_val = modul_formularis_tramits_elm.find(".seleccionat").find("li").size();
		var info_elms = modul_formularis_tramits_elm.find("p.info");
		
		if (seleccionats_val == 0) {
			info_elms.text(txtNoHiHaDocumentsSeleccionats+ ".");
		} else if (seleccionats_val == 1) {
			info_elms.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtFormulari.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elms.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtFormularis.toLowerCase() + "</strong>.");						
		}
	}
	
	
	this.inicializarFormularis = function(listaFormularis) {
		if (typeof listaFormularis != 'undefined' && listaFormularis != null) {
            modul_formularis_tramits_elm.find(".listaOrdenable").empty();		
			EscriptoriPareTramitForm.agregaItems(listaFormularis, true);
        }
        
        // Editar el documento al hacer click sobre el.
        modul_formularis_tramits_elm.find('div.formularisTramit').each(function() {
            $(this).unbind("click").bind("click", function() {
                var docTramitId = $(this).find("input.formularisTramit_id").val();
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
		
		modul_formularis_tramits_elm.find(".listaOrdenable ul").sortable({ 
			axis: 'y', 
			update: function(event,ui){
				EscriptoriPareTramitForm.calculaOrden(ui,"origen");
				EscriptoriPareTramitForm.habilitarBotonGuardar();
			}
		}).css({cursor:"move"});
		
		modul_formularis_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = $(this).parents("li:first");
			EscriptoriPareTramitForm.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
	}
	
	// Devuelve un string con el formato formularisTramit=n1,n2,...,nm donde n son codigos de formularios de un tr�mite.
	this.listarFormularios = function (){
		var listaFormularios = "formularisTramit=";
		var separador = "";
		
		modul_formularis_tramits_elm.find(".ca div.listaOrdenable input.formularisTramit_id").each(function() {
			listaFormularios += separador + $(this).val();
			separador = ",";
		});
		
		return listaFormularios;
	}	
	
};