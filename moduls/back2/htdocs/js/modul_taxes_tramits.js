$(document).ready(function() {
	// elements
	escriptori_taxes_tramits_elm = $("#escriptori_taxes_tramits");
    moduls_elm = escriptori_tramits_elm.find("div.modul");
    modul_taxes_tramits_elm = $("div.modulTaxesTramits:first");
    
	ModulTaxesTramit = new CModulTaxesTramit();
    ModulTaxesTramit.iniciar();
    
    EscriptoriPareTramitTaxa = new CEscriptoriPareTramitTaxa();
    EscriptoriPareTramitTaxa.iniciar();    
});

// Lista ordenable para eliminar/ordenar forms en la pantalla "padre"
function CEscriptoriPareTramitTaxa(){
	this.extend = ListaOrdenable;
	this.extend();		
    
    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "taxesTramit",
        nodoOrigen: modul_taxes_tramits_elm.find(".listaOrdenable"),
        nodoDestino: "", 
        atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
        multilang: true
    }
	
	var that = this;
	
	this.iniciar = function() {		
		
		// botons
		modul_taxes_tramits_elm.find("a.gestiona").bind("click", function() { ModulTaxesTramit.nou(false); } );			
		formularis_tramits_seleccionats_elm = escriptori_taxes_tramits_elm.find("div.escriptori_items_seleccionats:first");
		
		escriptori_taxes_tramits_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);
	}	

	this.gestiona = function() {
		lis_size = modul_taxes_tramits_elm.find('div.cajaIdioma:first li').length;
		if (lis_size > 0) {
			EscriptoriEdifici.contaSeleccionats();
		} else {
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaTaxesSeleccionades + ".");			
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
    	escriptori_taxes_tramits_elm.find("#btnGuardar").unbind("click").bind("click",function(){this.guarda_upload();}).parent().removeClass("off");
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
            
            ModulTaxesTramit.inicializarTaxes();
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
function CModulTaxesTramit(){
	this.extend = DetallBase;
	if (typeof FormulariDadesTaxaTramit != 'undefined') {
		this.extend(true, FormulariDadesTaxaTramit,{
		    btnVolver: "btnVolver_taxes_tramit" 
		});
	} else {
		this.extend(true, null);
	}

	var that = this;
	
	this.iniciar = function() {			
        // botons        
        //$("#btnVolver_taxes_tramit").bind("click", that.torna);

        // El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario.
    	$("#formGuardarTaxaTramit input, #formGuardarTaxaTramit textarea").bind("change", function(){that.modificado();});
    	
		// idioma
		if (escriptori_taxes_tramits_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_taxes_tramits_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_taxes_tramits_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_taxes_tramits_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
		}
		
		// Redifinimos el m�todo que guarda porque en este caso tambi�n hacemos un upload de archivos.
		this.guarda = this.guarda_upload;
	}
	
	
	this.vuelve = function () {
		escriptori_taxes_tramits_elm.fadeOut(300, function() {
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
        var tramitId = $("#idTramit");         
        tramitId.val($("#id_tramit_actual").val());

		//Enviamos el formulario mediante el m�todo ajaxSubmit del plugin $.form
		$("#formGuardarTaxaTramit").ajaxSubmit({			
			url: pagGuardarTaxaTramit,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
				Llistat.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {                   
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtTaxaCreadaCorrecte});
                    
					var nom = new Object();
                    escriptori_taxes_tramits_elm.find("input[id^='taxa_tramit_codi_']").each(function (index) {
						var $titolTaxa = $(this);
						var idioma = $titolTaxa.attr('id').split('_')[3];
						nom[idioma] = $titolTaxa.val();
					});
					
					var taxaItem = new Object();
					taxaItem['id'] = data.id;
					taxaItem['nom'] = nom;
					EscriptoriPareTramitTaxa.agregaActualizaItem(taxaItem);

					that.vuelve();
				}
			}

		});
        
		return false;
	}
	
	this.modificado = function(){
		// Habilitamos el bot�n de guardar.
		$("#btnGuardar_taxes_tramit").unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
	}
	
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
	}
			
	this.nou = function(edicion) {
		
		$("#tramitId").attr("value", $("#id_tramit_actual").val());		
		
		// El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario.
		$("#btnGuardar_taxes_tramit").parent().addClass("off");
        
		if (!edicion) {
            $("#taxaTramitId").val("");
            for (var i in idiomas) {
                var idioma = idiomas[i];                
                $("#taxa_tramit_codi_" + idioma + ", #taxa_tramit_descripcio_" + idioma + ", #taxa_tramit_forma_pagament_" + idioma).each(limpiarCampo);
            }
		}
		
		escriptori_tramits_elm.fadeOut(300, function() {
			escriptori_taxes_tramits_elm.fadeIn(300);
		});
		
		this.actualizaEventos();
	}			
	
	this.pintar = function(dades) {		
		dada_node = dades;
		
		$("#taxaTramitId").val(dada_node.item_id);

		// Bloque de pestanyas de idiomas.
		for (var i in idiomas) {
			var idioma = idiomas[i];
            
			$("#taxa_tramit_codi_" + idioma).val(printStringFromNull(dada_node["idioma_codificacio_" + idioma]));
			$("#taxa_tramit_descripcio_" + idioma).val(printStringFromNull(dada_node["idioma_descripcio_" + idioma]));
			$("#taxa_tramit_forma_pagament_" + idioma).val(printStringFromNull(dada_node["idioma_forma_pagament_" + idioma]));					
		}		
		// Fin bloque de pestanyas de idiomas
		
        // Mostrar la pantalla de edicion de documento
		that.nou(true);
	}
	
	this.contaSeleccionats = function() {		
		var seleccionats_val = modul_taxes_tramits_elm.find(".seleccionat").find("li").size();
		var info_elms = modul_taxes_tramits_elm.find("p.info");
		
		if (seleccionats_val == 0) {
			info_elms.text(txtNoHiHaTaxesSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elms.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtTaxa.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elms.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtTaxes.toLowerCase() + "</strong>.");						
		}
	}
	
	this.inicializarTaxes = function(listaTaxes) {
		
		if (  $("#item_taxa").attr("checked") == undefined ) {
			$("#modul_taxes_tramits").hide();
			return;
		} else 
			$("#modul_taxes_tramits").show();
				
		if (typeof listaTaxes != 'undefined' && listaTaxes != null) {
            modul_taxes_tramits_elm.find(".listaOrdenable").empty();		
			EscriptoriPareTramitTaxa.agregaItems(listaTaxes, true);
        }
        
        // Editar el documento al hacer click sobre el.
        modul_taxes_tramits_elm.find('div.taxesTramit').each(function() {
            $(this).unbind("click").bind("click", function() {
                var taxaTramitId = $(this).find("input.taxesTramit_id").val();
                var tramitId  = $("#id_tramit_actual").val();
                
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
                $.ajax({
                    type: "POST",
                    url: pagCarregarTaxaTramit,
                    data: "id=" + taxaTramitId + "&idTramit=" + tramitId,
                    dataType: "json",
                    error: function() {
                        // Missatge.cancelar();
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                        Error.llansar();
                    },
                    success: function(data) {
                        Missatge.cancelar();
                        if (data.id > 0) {
                            that.pintar(data.taxa);
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
		
		modul_taxes_tramits_elm.find(".listaOrdenable ul").sortable({ 
			axis: 'y', 
			update: function(event,ui){
				EscriptoriPareTramitTaxa.calculaOrden(ui,"origen");
				EscriptoriPareTramitTaxa.habilitarBotonGuardar();
			}
		}).css({cursor:"move"});
		
		modul_taxes_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = $(this).parents("li:first");
			EscriptoriPareTramitTaxa.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
	}
	
	// Devuelve un string con el formato taxesTramit=n1,n2,...,nm donde n son codigos de formularios de un tr�mite.
	this.listarTasas = function (){
		var listaTasas = "taxesTramit=";
		var separador = "";
		
		modul_taxes_tramits_elm.find(".ca div.listaOrdenable input.taxesTramit_id").each(function() {
			listaTasas += separador + $(this).val();
			separador = ",";
		});
		
		return listaTasas;
	}	
	
};