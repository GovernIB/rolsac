// MODUL UNITATS ADMINISTRATIVES

$(document).ready(function() {	
	
	// elements
	escriptori_unitatsAdministratives_elm = jQuery("#escriptori_unitatsAdministratives");
    modul_unitatsAdministratives_elm = jQuery("div.modulUnitatAdministratives:first");

	ModulUnitatAdministrativa = new CModulUnitatAdministrativa();
	
	if (modul_unitatsAdministratives_elm.size() == 1) {
		ModulUnitatAdministrativa.iniciar();
	}
	
});

function CModulUnitatAdministrativa() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	
	this.iniciar = function() {
		
		if (debug)
			console.log("Entrando en CModulUnitatAdministrativa.iniciar");
		
        unitatsAdministratives_llistat_elm = escriptori_unitatsAdministratives_elm.find("div.escriptori_items_llistat:first");
		unitatsAdministratives_cercador_elm = escriptori_unitatsAdministratives_elm.find("div.escriptori_items_cercador:first");
		unitatsAdministratives_seleccionades_elm = escriptori_unitatsAdministratives_elm.find("div.escriptori_items_seleccionats:first");
		
		unitatsAdministratives_dades_elm = unitatsAdministratives_llistat_elm.find("div.dades:first");
		
		pagPagina_unitatAdministrativa_elm = unitatsAdministratives_llistat_elm.find("input.pagPagina:first");
		ordreTipus_unitatAdministrativa_elm = unitatsAdministratives_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_unitatAdministrativa_elm = unitatsAdministratives_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_unitatsAdministratives_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
						
		// Configuramos la lista ordenable.
		params = {
			nombre: "unitatAdministrativa",
			nodoOrigen: modul_unitatsAdministratives_elm.find(".listaOrdenable"),
			nodoDestino: modul_unitatsAdministratives_elm.find(".listaOrdenable"),
			atributos: [			// Campos que queremos que aparezcan en los elementos de las lista ordenable.
	            "id", 
	            "nombre", 
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem"		// Campo necesario para guardado AJAX genérico de módulos laterales.
            ],
			multilang: false
		};
		
		this.configurar(params);
				
		if (debug)
			console.log("Saliendo de CModulUnitatAdministrativa.iniciar");
		
	};
			
	this.nuevo = function() {
		
		if (debug)
			console.log("Entrando en CModulUnitatAdministrativa.nuevo");
		
		unitatAdm_seleccionats_elm = escriptori_detall_elm.find("div.modulUnitatAdministratives div.seleccionats");
		unitatAdm_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaUA + ".");
		
		if (debug)
			console.log("Saliendo de CModulUnitatAdministrativa.nuevo");
		
	};
		
	this.contaSeleccionats = function() {
		
		if (debug)
			console.log("Entrando en CModulUnitatAdministrativa.contaSeleccionats");
		
		seleccionats_val = modul_unitatsAdministratives_elm.find(".seleccionat").find("li").size();
		info_elm = modul_unitatsAdministratives_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaUAsSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtUnitatAdministrativa.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtUnitatsAdministratives.toLowerCase() + "</strong>.");						
		}
		
		if (debug)
			console.log("Saliendo de CModulUnitatAdministrativa.contaSeleccionats");
		
	};
	
	this.inicializarUnidadesAdministrativas = function(listaUnidadesAdministrativas) {
		
		if (debug)
			console.log("Entrando en CModulUnitatAdministrativa.inicializarUnidadesAdministrativas");
		
		if (typeof listaUnidadesAdministrativas != 'undefined' && listaUnidadesAdministrativas != null && listaUnidadesAdministrativas.length > 0) {
			modul_unitatsAdministratives_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaUnidadesAdministrativas, true);
		}
		
		that.contaSeleccionats();
		
		modul_unitatsAdministratives_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
		});
		
		if (debug)
			console.log("Saliendo de CModulUnitatAdministrativa.inicializarUnidadesAdministrativas");
		
	};
	
	// Devuelve un string con el formato unitatsAdministratives=n1,n2,...,nm donde n son codigos de unitatAdministrativas.
	this.listaUnidadesAdministrativas = function () {
		
		if (debug)
			console.log("Entrando en CModulUnitatAdministrativa.listaUnidadesAdministrativas");
		
		var listaUnidadesAdministrativas = "";
		
		modul_unitatsAdministratives_elm.find("div.listaOrdenable input.unitatAdministrativa_id").each(function() {
			listaUnidadesAdministrativas += jQuery(this).val() + ",";
		});
		
		if (listaUnidadesAdministrativas[listaUnidadesAdministrativas.length-1] == ","){
			listaUnidadesAdministrativas = listaUnidadesAdministrativas.slice(0, -1);
		}
		
		if (debug)
			console.log("Saliendo de CModulUnitatAdministrativa.listaUnidadesAdministrativas");
		
		return listaUnidadesAdministrativas;
		
	};
	
	/**
	 * Agrega un item a la lista.
	 * Método sobreescrito del original de ListaOrdenable pero con sólo modificaciones para tener en cuenta si se está o no en el mantenimiento de una materia.
	 * Si es el caso, hay código adicional que añade un radiobutton de UA principal de la materia.
	 *
	 * @return boolean Devuelve true si el item no se encontraba ya en la lista.
	 */
	this.agregaItem = function( item ) {
		
		if (debug)
			console.log("Entrando en CModulUnitatAdministrativa.agregaItem");
				
		var _this = this;
		var tamLista = jQuery(params.nodoDestino).filter(":first").find("li").size();		
		var itemYaExiste = false;
		var html;		
		var idioma;
						                                
		if ( tamLista == 0) {
						
			jQuery(params.nodoDestino).html("<ul></ul>");
			
		} else {
			
			jQuery(params.nodoDestino).find("input." + params.nombre + "_id").each(function() {				
				
				if ( jQuery(this).val() == item.id ) {
					itemYaExiste = true;
				}			
				
			});
			
		}
		
		// Valores necesarios para guardado vía AJAX.
		item.idMainItem = jQuery('#item_id').val();
		item.idRelatedItem = item.id;
		
		if ( !itemYaExiste ) {
							
			if ( params.multilang ) {
		
				jQuery( params.nodoDestino ).each(function() {
					idioma = _this.getIdiomaActivo( jQuery(this) );
					html = _this.getHtmlItem( item, true, idioma );					
					jQuery(this).find("ul").append(html);					
				});		
				
			} else {			
							
				html = _this.getHtmlItem( item, true );				
				jQuery( params.nodoDestino ).find("ul").append(html);
				
				// Añado radio button de UA si estamos en el mantenimiento de Materias.
				if ( document.getElementById('modul_detall_materies') != null ) {
					
					jQuery('#unitatAdministrativa_id_' + item.id).parent().append(
						'<p><input id="item_ua_principal_' + item.id + '" type="radio" value="' + item.id + '" name="item_ua_principal">' +
						'<label for="item_ua_principal_' + item.id + '">Principal?</label></p>'
					);
					
				}
				
			}
			
			if (debug)
				console.log("Saliendo de CModulUnitatAdministrativa.agregaItem");
			
			// Si hay botón de guardado, hay que marcar la página como modificada.
			// Si no, el guardado se hace vía botón "Finalizar".
			if (this.existeBotonGuardar()) {
				Detall.modificado(true);
				this.habilitarBotonGuardar();
			}
						
			return true;
			
		}
		
		if (debug)
			console.log("Saliendo de CModulUnitatAdministrativa.agregaItem");
		
		return false;
		
	};
	
	this.botonGuardar = jQuery("#btnGuardar_modul_unitatsAdministratives");
	
	this.existeBotonGuardar = function() {
		return (this.botonGuardar.length > 0);
	};
	
	this._eliminaItem = this.eliminaItem;
	
	this.eliminaItem = function( item ) {
		
		that._eliminaItem(item);
		
		// Si hay botón de guardado, hay que marcar la página como modificada.
		// Si no, es que el guardado se hace vía botón "Finalizar".
		if (this.existeBotonGuardar()) {
			Detall.modificado(true);
			this.habilitarBotonGuardar();
		}
		
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

/**
 * (amartin) Explicación de extensión de clase:
 * 
 * Necesitamos extender la clase ListaSimple ya que el módulo lateral de unidades administrativas dentro de la gestión de materias 
 * no sólo consta de valor para el registro principal (materia => main-item-id) y para sus N UAs asociadas (ua => related-item-id),
 * sino que también hay un tercer campo, que es el checkbox de UA principal para esa materia. Con la extensión de la clase
 * sobreescribimos el método de obtención de filtros para obtener el dato adicional de UA principal.
 */
function CListaSimpleUAs() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListaSimple;
	this.extend();
	
	var that = this;
	
	this._getFilters = this.getFilters;
	
	this.getFilters = function(elements, id) {
		
		if (debug)
			console.log("Entrando en CListaSimpleUAs.getFilters");
		
		var filters = that._getFilters(elements, id);
		
		if (elements.length > 0) {
			
			elements.each(function() {
				
				// Obtenemos el radiobutton de cada UA y comprobamos si está marcado.
				if ( $(this).find("input[type='radio']").length == 1 ) {
				
					var radio = $(this).find("input[type='radio']");
					
					// Si está seleccionado, lo pasamos por parámetro
					if ( radio.is(':checked') )
						filters += "&itemUAPrincipal=" + radio.val();
				
				}
				
			});
						
		}
				
		if (debug)
			console.log("Saliendo de CListaSimpleUAs.getFilters");
		
		return filters;
				
	};
	
	this._guardar = this.guardar;
	
	this.guardar = function(element, url, id) {
		
		if (debug)
			console.log("Entrando en CListaSimpleUAs.getFilters");
		
		that._guardar(element, url, id);
		
		// XXX amartin: ocultación del botón de guardado tras solicitar guardado AJAX
		// (si el invoker es el guardado de unidades relacionadas con una materia).
		// Ir añadiendo casos aquí.
		var urlGuardarUnidadesMateria = "/materies/guardarUnidadesRelacionadas.do";
		var urlGuardarUnidadesUsuario = "/usuaris/guardarUnidadesRelacionadas.do";
		
		if ( url.indexOf(urlGuardarUnidadesMateria) != -1 
				|| url.indexOf(urlGuardarUnidadesUsuario) != -1 ) {
			
			if (typeof ModulUnitatAdministrativa != 'undefined')
				ModulUnitatAdministrativa.deshabilitarBotonGuardar();
			
		}
		
		Detall.modificado(false);
		
		if (debug)
			console.log("Entrando en CListaSimpleUAs.getFilters");
		
	};
	
};
