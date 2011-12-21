// MODUL ICONES

jQuery(document).ready(function() {
	// elements
	escriptori_icones_elm = jQuery("#escriptori_icones");
    moduls_elm = escriptori_detall_elm.find("div.modul");
    modul_icones_elm = jQuery("div.modulIcones:first");
    
	ModulIcones = new CModulIcones();
    ModulIcones.iniciar();
    
    EscriptoriPare = new CEscriptoriPare();
    EscriptoriPare.iniciar();
});


// Lista ordenable para elimiar/ordenar icones en la pantalla "padre"
function CEscriptoriPare(){
	this.extend = ListaOrdenable;
	this.extend();		
    
    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "icones",
        nodoOrigen: modul_icones_elm.find(".listaOrdenable"),
        nodoDestino: "", // icones_seleccionades_elm.find(".listaOrdenable"),
        atributos: ["id", "nombre"],	// Campos que queremos que aparezcan en las listas.
        multilang: false
    }
	
	var that = this;
	
	this.iniciar = function() {
		// botons
		modul_icones_elm.find("a.gestiona").bind("click", function(){ModulIcones.nou(false);} );
		
		icones_seleccionats_elm = escriptori_icones_elm.find("div.escriptori_items_seleccionats:first");
		
		escriptori_icones_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);
	}	

	this.gestiona = function() {
		lis_size = modul_icones_elm.find('div.cajaIdioma:first li').length;
		if (lis_size > 0) {
			EscriptoriEdifici.contaSeleccionats();
		} else {
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaItems + ".");			
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
			listas.find("input." + that.configuracion.nombre + "_nombre[name$=_" + item.id + "]").each(function() {
                var $iconaInput = jQuery(this);
                var $iconaSpan = $iconaInput.next();
                $iconaInput.val(item["nombre"]);
                $iconaSpan.text(item["nombre"]);
            });
		} else {
            if (typeof item["orden"] != "number") {
                item["orden"] = that.obtenerUltimoOrden(listas, tamLista) + 1;
            }
            
			listas.each(function(){
                var lista = jQuery(this);
                // var idioma = that.getIdiomaActivo(lista);
				var htmlCode = that.getHtmlItem(item, true/*, idioma*/);
				lista.find("ul").append(htmlCode);					
			});
            
            ModulIcones.inicializarIcones();
		}
		
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


// Creacion/edicion de icones
function CModulIcones(){
	this.extend = DetallBase;
	if (typeof FormulariDadesIcona != 'undefined') {
		this.extend(true, FormulariDadesIcona);
	} else {
		this.extend(true, null);
	}

	var that = this;
	
	this.iniciar = function() {			
        // botons        
        jQuery("#btnVolver_icones").bind("click", that.torna);

        // El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario.
    	jQuery("#formGuardarIcona input, #formGuardarIcona select, #formGuardarIcona textarea").bind("change", function(){that.modificado();});
    	
		// idioma
		if (escriptori_icones_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_icones_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_icones_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_icones_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
		}
		
		// Redifinimos el método que guarda porque en este caso también hacemos un upload de archivos.
		this.guarda = this.guarda_upload;
	}
	
	
	this.torna = function () {
		escriptori_icones_elm.fadeOut(300, function() {
	        escriptori_detall_elm.fadeIn(300);
	    });
	}
	
	
	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {
        // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }
        
        // Coger el id de la familia o de la materia (depende del mantenimiento/jsp en el que estemos).
        var familiaId = jQuery("#familiaId");
        if (familiaId.length > 0) {
        	familiaId.val(jQuery("#item_id").val());
        } else {
        	jQuery("#materiaId").val(jQuery("#item_id").val());
        }

		//Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
		$("#formGuardarIcona").ajaxSubmit({			
			url: pagGuardarIcona,
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

					var iconaItem = new Object();
					iconaItem['id'] = data.id;
					iconaItem['nombre'] = jQuery('#grup_arxiu_actual_icona a').text();
					EscriptoriPare.agregaActualizaItem(iconaItem);

					that.torna();
				}
			}

		});
        
		return false;
	}
	
	
	this.modificado = function(){
		// Habilitamos el botón de guardar.
		jQuery("#btnGuardar_icones").unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
	}
	
	
	this.nou = function(edicion) {
		// El botón de guardar está inicialmente deshabilitado hasta que se realice un cambio en el formulario.
		jQuery("#btnGuardar_icones").parent().addClass("off");
        
		if (!edicion) {
            jQuery("#iconaId").val("");
            jQuery("#icona_perfil, #icona_arxiu").each(limpiarCampo);
            jQuery("#grup_arxiu_actual_icona span").show();
            jQuery("#grup_arxiu_actual_icona a").hide();
        }
		
		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_icones_elm.fadeIn(300);
		});
		
		this.actualizaEventos();
	}		
	
	
	this.pintar = function(dades) {		
		dada_node = dades;
		
		jQuery("#iconaId").val(dada_node.item_id);
		jQuery("#icona_perfil").val(printStringFromNull(dada_node.perfil));
		
		// archivos
		$("#icona_arxiu").val("");
		$("#grup_arxiu_actual_icona input").removeAttr("checked");
		var anchors = $("#grup_arxiu_actual_icona a");
        var enllasArxiu = dada_node.enllas_arxiu;
		if (typeof enllasArxiu != "undefined" && enllasArxiu != "") {
			anchors.attr("href", pagArrel + enllasArxiu);
			anchors.text(dada_node.nom_arxiu);
			anchors.show();
			$("#grup_arxiu_actual_icona span").hide();
		} else {
            $("#grup_arxiu_actual_icona span").show();
            anchors.hide();
        }
		
        // Mostrar la pantalla de edicion de iconos
		that.nou(true);
	}
	
	this.contaSeleccionats = function() {		
		var seleccionats_val = modul_icones_elm.find(".seleccionat").find("li").size();
		var info_elms = modul_icones_elm.find("p.info");
		
		if (seleccionats_val == 0) {
			info_elms.text(txtNoHiHaIconesSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elms.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtIcona.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elms.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtIcones.toLowerCase() + "</strong>.");						
		}
	}
	
	
	this.inicializarIcones = function(listaIcones) {
		if (typeof listaIcones != 'undefined' && listaIcones != null) {
            modul_icones_elm.find(".listaOrdenable").empty();		
			EscriptoriPare.agregaItems(listaIcones, true);
        }
        
        // Editar el icono al hacer click sobre el.
        modul_icones_elm.find('div.icones').each(function() {
            $(this).unbind("click").bind("click", function() {
                var iconaId = $(this).find("input.icones_id").val();
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
                $.ajax({
                    type: "GET",
                    url: pagCarregarIcona,
                    data: "id=" + iconaId,
                    dataType: "json",
                    error: function() {
                        // Missatge.cancelar();
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                        Error.llansar();
                    },
                    success: function(data) {
                        Missatge.cancelar();
                        if (data.id > 0) {
                            that.pintar(data.icona);
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
		
		modul_icones_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			EscriptoriPare.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
	}
};