// MODUL ENLLAÇOS

$(document).ready(function() {	
	
	// elements
	modul_enllassos_elm = jQuery("div.modulEnllassos:first");
	resultats_elm = jQuery("#resultats");
	resultats_actiu_elm = resultats_elm.find("div.actiu:first");
	escriptori_enllassos_elm = jQuery("#escriptori_enllassos");	
	cercador_elm = jQuery("#cercador");
	//escriptori_enllassos_idiomes = escriptori_enllassos_elm.find("div.idiomes");
	
	ModulEnllas = new CModulEnllas();
	EscriptoriEnllas = new CEscriptoriEnllas();
	//Llistat = EscriptoriEnllas;
	
	multipagina = new Multipagina();
	
	if (modul_enllassos_elm.size() == 1) {
		ModulEnllas.iniciar();
		EscriptoriEnllas.iniciar();
	}
	
	// Evento para el botón de volver al detalle
	jQuery("#btnVolver_enllassos").bind("click", function() {
		EscriptoriEnllas.torna();
	});
	jQuery("#btnLimpiarCampos").bind("click", function() {
		EscriptoriEnllas.limpia();
	});
	jQuery("#btnGuardar_enllassos").bind("click", function() {
		EscriptoriEnllas.finalizar();
	});
	jQuery("#btnInsertarEnllas").bind("click", function() {
		EscriptoriEnllas.guardar();
	});
	
});

function CModulEnllas() {
	
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
    
    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = modul_enllassos_elm.find('input[name="modulo_enlaces_modificado"]');
	
	this.iniciar = function() {
		
		DebugJS.debug("Entrando en CModulEnllas.iniciar");
				                        
		enllassos_seleccionats_elm = escriptori_enllassos_elm.find("div.escriptori_items_seleccionats:first");
		escriptori_enllassos_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});

		// idioma
		
		if (escriptori_enllassos_elm.find("div.idiomes").size() != 0) {
	        // Esconder todos menos el primero
			escriptori_enllassos_elm.find('div.idioma').slice(1).hide();
	        
			var ul_idiomes_elm = escriptori_enllassos_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
	        a_primer_elm.parent().addClass("seleccionat");
	        
	        var a_primer_elm_class = a_primer_elm.attr("class");
	        var a_primer_elm_text = a_primer_elm.text();
	        
	        a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
	        
	        var div_idiomes_elm = escriptori_enllassos_elm.find("div.idiomes:first");
	        div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");	        	        
	        ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': true, 'idPare':'#escriptori_enllassos'}, Detall.idioma);
	    }
				
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "enllas",
			nodoOrigen: modul_enllassos_elm.find(".listaOrdenable"),
			nodoDestino: enllassos_seleccionats_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "url", "orden", "idMainItem", "idRelatedItem"], // Campos que queremos que aparezcan en las listas.
			multilang: true
		});
		
		// one al botó de gestionar
		modul_enllassos_elm.find("a.gestiona").one("click", function() { ModulEnllas.gestiona(); });
		
		DebugJS.debug("Saliendo de CModulEnllas.iniciar");
		
	};

	this.gestiona = function() {
		
		DebugJS.debug("Entrando en CModulEnllas.gestiona");
				
		EscriptoriEnllas.limpia();				
		
		lis_size = modul_enllassos_elm.find("li").size();
		
		if (lis_size > 0) {
		
			this.copiaInicial();
															
			EscriptoriEnllas.contaSeleccionats();
			
		} else {
			
			enllassos_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaEnllassosSeleccionats + ".");			
			enllassos_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_enllassos_elm.fadeIn(300);			
		});
		
		this.deshabilitarBotonGuardar();
		
		DebugJS.debug("Saliendo de CModulEnllas.gestiona");
		
	};
	
    this.modificado = function() {
    	
		DebugJS.debug("Entrando en CModulEnllas.modificado");
    	
        $moduloModificado.val(1);
        
		DebugJS.debug("Saliendo de CModulEnllas.modificado");
        
    };
    
	this.nuevo = function() {
		
		DebugJS.debug("Entrando en CModulEnllas.nuevo");
		
		modul_enllassos_seleccionats_elm = escriptori_detall_elm.find("div.modulEnllassos div.seleccionats");
		modul_enllassos_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaEnllassos + ".");
		
		DebugJS.debug("Saliendo de CModulEnllas.nuevo");
		
	};

	this.cargarEnlaces = function(dades) {
		
		DebugJS.debug("Entrando en CModulEnllas.cargarEnlaces");
		
        // Iniciamos el campo de control de cambios 0.
        $moduloModificado.val(0);
        
		en_seleccionats_elm = escriptori_detall_elm.find("div.modulEnllassos div.seleccionats");
		en_llistat_elm = escriptori_detall_elm.find("div.modulEnllassos div.llistat");
		enllassos_nodes = dades.enllassos;
		enllassos_nodes_size = enllassos_nodes.length;
								
		if (enllassos_nodes_size == 0) {
			
			en_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaEnllassos + ".");
			
		} else {
		
			var itemsLista = [];
			var i = 0;

			jQuery(enllassos_nodes).each(function() {
				
				enllas_node = this;

				// dsanchez: Creamos la lista de elementos iniciales.
				itemsLista.push({
					id: enllas_node.id, 
					// Para listas multi-idioma pasar un objeto con los strings de cada idioma, en lugar de un solo string.
					nombre:{					
						es: enllas_node.traduccionMap.es == null ? "" : enllas_node.traduccionMap.es.titulo, 
						en: enllas_node.traduccionMap.en == null ? "" : enllas_node.traduccionMap.en.titulo, 
						ca: enllas_node.traduccionMap.ca == null ? "" : enllas_node.traduccionMap.ca.titulo, 
						de: enllas_node.traduccionMap.de == null ? "" : enllas_node.traduccionMap.de.titulo, 
						fr: enllas_node.traduccionMap.fr == null ? "" : enllas_node.traduccionMap.fr.titulo								
					},
					url:{							
						es: enllas_node.traduccionMap.es == null ? "" : enllas_node.traduccionMap.es.enlace,
						en: enllas_node.traduccionMap.en == null ? "" : enllas_node.traduccionMap.en.enlace, 
						ca: enllas_node.traduccionMap.ca == null ? "" : enllas_node.traduccionMap.ca.enlace, 
						de: enllas_node.traduccionMap.de == null ? "" : enllas_node.traduccionMap.de.enlace, 
						fr: enllas_node.traduccionMap.fr == null ? "" : enllas_node.traduccionMap.fr.enlace							
					},
					orden: enllas_node.orden,
					idMainItem: enllas_node.idMainItem,
					idRelatedItem: enllas_node.idRelatedItem
				});
				
			});		

			ModulEnllas.agregaItems(itemsLista);
			
			txt_enllassos = (enllassos_nodes_size == 1) ? txtEnllas : txtEnllassos;
			en_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + enllassos_nodes_size + " " + txt_enllassos + "</strong>.");
			
		}
		
		DebugJS.debug("Saliendo de CModulEnllas.cargarEnlaces");
		
	};
	
	this.botonGuardar = jQuery("#btnGuardar_enllassos");
	
	this.existeBotonGuardar = function() {
		
		DebugJS.debug("Entrando en CModulEnllas.existeBotonGuardar");
		
		DebugJS.debug("Saliendo de CModulEnllas.existeBotonGuardar");
		
		return (this.botonGuardar.length > 0);
		
	};
	
	this.habilitarBotonGuardar = function() {
		
		DebugJS.debug("Entrando en CModulEnllas.habilitarBotonGuardar");
		
		if (this.existeBotonGuardar() && this.botonGuardar.parent().hasClass("off")) {
    		this.botonGuardar.parent().removeClass("off");
    	}
		
		DebugJS.debug("Saliendo de CModulEnllas.habilitarBotonGuardar");
		
    };
    
    this.deshabilitarBotonGuardar = function() {
    	
    	DebugJS.debug("Entrando en CModulEnllas.deshabilitarBotonGuardar");
    	
    	if (this.existeBotonGuardar() && !this.botonGuardar.parent().hasClass("off")) {
    		this.botonGuardar.parent().addClass("off");
    	}
    	
    	DebugJS.debug("Saliendo de CModulEnllas.deshabilitarBotonGuardar");
    	
    };
    
    this._eliminaItem = this.eliminaItem;
	
	this.eliminaItem = function( item ) {
		
		DebugJS.debug("Entrando en CModulEnllas.eliminaItem");

		that._eliminaItem(item);

		if (this.existeBotonGuardar()) {
			this.habilitarBotonGuardar();
		}
		
		DebugJS.debug("Saliendo de CModulEnllas.eliminaItem");
		
	};
	
	this._agregaItem = this.agregaItem;
	
	this.agregaItem = function( item ) {
		
		DebugJS.debug("Entrando en CModulEnllas.agregaItem");
		
		that._agregaItem(item);

		if (this.existeBotonGuardar()) {
			this.habilitarBotonGuardar();
		}
		
		DebugJS.debug("Saliendo de CModulEnllas.agregaItem");
		
	};
	
};

function CEscriptoriEnllas() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListaOrdenable;
	this.extend();
	
	var that = this;
	
	var formulariComprovarEnllassos;
	
	this.iniciar = function() {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.iniciar");
		
		formulariComprovarEnllassos = new FormulariComprovar(FormulariEnllassos);
		formulariComprovarEnllassos.iniciar();
		
		DebugJS.debug("Saliendo de CEscriptoriEnllas.iniciar");
		
	};
		
	this.guardar = function() {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.guardar");
		
		//Validam el formulari d'enllassos
		formulariComprovarEnllassos.llansar();
		
		if (!formulariComprovarEnllassos.formComprovacio) {				
			return false;
		} 
				
		//Si el camp id_enllas_actual del jsp principal te valor, aleshores s'esta editant un enllas
		var idEnllas = $("#id_enllas_actual").val();
			
		if (idEnllas != "") {
								
			escriptori_enllassos_elm.find("#enllas_nombre_ca_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_titol_ca").val());
			escriptori_enllassos_elm.find("#enllas_url_ca_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_url_ca").val());
			escriptori_enllassos_elm.find("#enllas_nombre_ca_" + idEnllas).siblings("span.enllas").html(escriptori_enllassos_elm.find("#enllas_titol_ca").val());
			
			escriptori_enllassos_elm.find("#enllas_nombre_es_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_titol_es").val());
			escriptori_enllassos_elm.find("#enllas_url_es_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_url_es").val());
			escriptori_enllassos_elm.find("#enllas_nombre_es_" + idEnllas).siblings("span.enllas").html(escriptori_enllassos_elm.find("#enllas_titol_es").val());
			
			escriptori_enllassos_elm.find("#enllas_nombre_en_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_titol_en").val());
			escriptori_enllassos_elm.find("#enllas_url_en_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_url_en").val());
			escriptori_enllassos_elm.find("#enllas_nombre_en_" + idEnllas).siblings("span.enllas").html(escriptori_enllassos_elm.find("#enllas_titol_en").val());
			
			escriptori_enllassos_elm.find("#enllas_nombre_de_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_titol_de").val());
			escriptori_enllassos_elm.find("#enllas_url_de_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_url_de").val());
			escriptori_enllassos_elm.find("#enllas_nombre_de_" + idEnllas).siblings("span.enllas").html(escriptori_enllassos_elm.find("#enllas_titol_de").val());
			
			escriptori_enllassos_elm.find("#enllas_nombre_fr_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_titol_fr").val());
			escriptori_enllassos_elm.find("#enllas_url_fr_" + idEnllas).val(escriptori_enllassos_elm.find("#enllas_url_fr").val());
			escriptori_enllassos_elm.find("#enllas_nombre_fr_" + idEnllas).siblings("span.enllas").html(escriptori_enllassos_elm.find("#enllas_titol_fr").val());
			
		} else {
		
			var max_ordre = parseInt(enllassos_seleccionats_elm.find(".listaOrdenable li:last input.enllas_orden").val());
			
			//En cas de llista buïda
			max_ordre = (isNaN(max_ordre)) ? 0 : max_ordre + 1 ; 		
			
			//Els elements nous reben un id provisional. 						
			var idiomes = escriptori_enllassos_elm.find("div.idiomes");
			
			var timestamp = new Date().getTime().toString();
			var idTemporal = 't' + timestamp.substring(timestamp.length - 6);
			
			var item = {
				id: idTemporal,
				nombre: {
					ca: idiomes.find("#enllas_titol_ca").val(),
					es: idiomes.find("#enllas_titol_es").val(),
					en: idiomes.find("#enllas_titol_en").val(),
					de: idiomes.find("#enllas_titol_de").val(),
					fr: idiomes.find("#enllas_titol_fr").val()
				},
				url: {
					ca: idiomes.find("#enllas_url_ca").val(),
					es: idiomes.find("#enllas_url_es").val(),
					en: idiomes.find("#enllas_url_en").val(),
					de: idiomes.find("#enllas_url_de").val(),
					fr: idiomes.find("#enllas_url_fr").val()
				},
				orden: max_ordre,
				idMainItem: $('#item_id').val(),
				idRelatedItem: idTemporal
			};		
		
			if (ModulEnllas.agregaItem(item)) {
				EscriptoriEnllas.contaSeleccionats();
			}
			
		}
		
		EscriptoriEnllas.limpia();
		
		DebugJS.debug("Saliendo de CEscriptoriEnllas.guardar");
		
	};
	
	this.finalizar = function() {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.finalizar");

		nombre_llistat = ModulEnllas.finalizar();
		
		codi_enllassos_txt = (nombre_llistat == 1) ? txtEnllas : txtEnllassos;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaEnllassos + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_enllassos_txt.toLowerCase() + "</strong>.";
		
		modul_enllassos_elm.find("p.info").html(codi_info);		
		
		if (nombre_llistat > 1) {			
			modul_enllassos_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulEnllas.calculaOrden(ui,"origen");
					EscriptoriEnllas.contaSeleccionats();
				}
			}).css({cursor:"move"});
		}
        
        // Marcamos el módulo como modificado.
        ModulEnllas.modificado();
		
		this.torna();
		
		DebugJS.debug("Saliendo de CEscriptoriEnllas.finalizar");
		
	};
	
	this.torna = function() {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.torna");
		
		// animacio
		escriptori_enllassos_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_enllassos_elm.find("a.gestiona").one("click", function() { ModulEnllas.gestiona(); });
			});
			
		});
		
		DebugJS.debug("Saliendo de CEscriptoriEnllas.torna");
		
	};
	
	this.limpia = function() {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.limpia");
		
        jQuery('#formEnllassos :input').each(limpiarCampo);
        $("#id_enllas_actual").val(""); //Se neteja manualment ja que limpiarCampo no afecta els input hidden
        
        DebugJS.debug("Saliendo de CEscriptoriEnllas.limpia");
        
	};

	this.contaSeleccionats = function() {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.contaSeleccionats");
		
		seleccionats_val = enllassos_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = enllassos_seleccionats_elm.find("p.info");
		
		if (seleccionats_val == 0) {
			
			enllassos_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaEnllassosSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtEnllas.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtEnllassos.toLowerCase() + "</strong>.");						
			enllassos_seleccionats_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulEnllas.calculaOrden(ui,"destino");
					EscriptoriEnllas.contaSeleccionats();
				}
			}).css({cursor:"move"});
			
		}
		
		//Boto eliminar de cada fila del manteniment
		enllassos_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {				
			var itemLista = jQuery(this).parents("li:first");
			ModulEnllas.eliminaItem(itemLista);
			EscriptoriEnllas.contaSeleccionats();
		});
		
		enllassos_seleccionats_elm.find(".listaOrdenable span.enllas").unbind("click").bind("click", function() {				
			var idItem = jQuery(this).siblings("input.enllas_id").val();
			EscriptoriEnllas.editar(idItem);
		});
		
		DebugJS.debug("Saliendo de CEscriptoriEnllas.contaSeleccionats");
		
	};
	
	//Edicio de un enllas ja existent
	this.editar = function (idItem) {
		
		DebugJS.debug("Entrando en CEscriptoriEnllas.editar");
		
		//Amb el id del enllas (ja sigui temporal o definitiu) es possible recuperar tots els elements del DOM
		
		$("#id_enllas_actual").val(idItem);
		
		escriptori_enllassos_elm.find("#enllas_titol_ca").val(escriptori_enllassos_elm.find("#enllas_nombre_ca_" + idItem).val());
		escriptori_enllassos_elm.find("#enllas_url_ca").val(escriptori_enllassos_elm.find("#enllas_url_ca_" + idItem).val());
		
		escriptori_enllassos_elm.find("#enllas_titol_es").val(escriptori_enllassos_elm.find("#enllas_nombre_es_" + idItem).val());
		escriptori_enllassos_elm.find("#enllas_url_es").val(escriptori_enllassos_elm.find("#enllas_url_es_" + idItem).val());
		
		escriptori_enllassos_elm.find("#enllas_titol_en").val(escriptori_enllassos_elm.find("#enllas_nombre_en_" + idItem).val());
		escriptori_enllassos_elm.find("#enllas_url_en").val(escriptori_enllassos_elm.find("#enllas_url_en_" + idItem).val());
		
		escriptori_enllassos_elm.find("#enllas_titol_de").val(escriptori_enllassos_elm.find("#enllas_nombre_de_" + idItem).val());
		escriptori_enllassos_elm.find("#enllas_url_de").val(escriptori_enllassos_elm.find("#enllas_url_de_" + idItem).val());
		
		escriptori_enllassos_elm.find("#enllas_titol_fr").val(escriptori_enllassos_elm.find("#enllas_nombre_fr_" + idItem).val());
		escriptori_enllassos_elm.find("#enllas_url_fr").val(escriptori_enllassos_elm.find("#enllas_url_fr_" + idItem).val());
		
		DebugJS.debug("Saliendo de CEscriptoriEnllas.editar");
		
	};

};

function CListaMultiidiomaEnlaces() {
	
	this.extend = ListaMultiidioma;
	this.extend();
	
	var that = this;
	
	// Método sobreescrito para obtener qué datos guardar en este caso.
	this.getFilters = function(elements, id) {
		
		DebugJS.debug("Entrando en CListaMultiidiomaEnlaces.getFilters");
		
		// XXX amartin: importante. Este listado ha de coincidir con los valores devueltos por DelegateUtil.getIdiomaDelegate().listarLenguajes().
		var langs = new Array("ca", "es", "en", "de", "fr");
		var lista = new Array();
				
		if (elements.length > 0) {
			
			// Lista de <li>.
			elements.each(function() {
						
				var item = new Object();
				
				item.id = $(this).attr('element-id');
				item.orden = $(this).find('#enllas_orden_' + item.id).val();
				item.idMainItem = id;
				item.idRelatedItem = item.id;
				
				// Creamos objeto de mapa de traducciones.
				item.traduccionesEnlace = new Object();
				
				// Creamos objeto de idioma asociado a cada mapa de traducciones.
				for (var i = 0; i < langs.length; i++) {
									
					item.traduccionesEnlace[langs[i]] = new Object();
					
					// Asociamos valores.
					item.traduccionesEnlace[langs[i]].titulo = escriptori_enllassos_elm.find('#enllas_nombre_' + langs[i] + '_' + item.id).val();
					item.traduccionesEnlace[langs[i]].enlace = escriptori_enllassos_elm.find('#enllas_url_' + langs[i] + '_' + item.id).val();
					
				}
				
				lista.push(item);
								
			});
						
		}
		
		// Estructura JSON que emula un objeto de la clase EnlacesFichaDTO.
		var filters = new Object();
		filters.id = id;
		filters.listaEnlaces = lista;
				
		DebugJS.debug("Saliendo de CListaMultiidiomaEnlaces.getFilters");
		
		return filters;
		
	};
	
};
