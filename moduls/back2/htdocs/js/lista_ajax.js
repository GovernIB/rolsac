function ListaAjax() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.guardar = function(url, filters) {
		
		if (debug)
			console.log("Entrando en ListaAjax.guardar");

		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: filters,
			beforeSend: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				
				if (debug) {
					console.log("textStatus: " + textStatus);
					console.log("errorThrown: " + errorThrown);
				}

				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					// error
					Error.llansar();
				}
				
			},
			success: function(data) {				
				Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});			
			}
		});
		
		if (debug)
			console.log("Saliendo de ListaAjax.guardar");

	};
	
	this.guardarMultiidioma = function(url, filters) {
		
		if (debug)
			console.log("Entrando en ListaAjax.guardarMultiidioma");

		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: JSON.stringify(filters),						// XXX amartin: muy importante usar JSON.stringify() para que los parámetros se envíen correctamente.
			contentType: 'application/json; charset=UTF-8',
			headers: { 
		        'Accept': 'application/json' 					// XXX amartin: necesario para que el controller de SPRING acepte la petición con datos enviados en formato JSON.
		    },
		    beforeSend: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				
				if (debug) {
					console.log("textStatus: " + textStatus);
					console.log("errorThrown: " + errorThrown);
				}

				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					// error
					Error.llansar();
				}
				
			},
			success: function(data) {				
				Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});			
			}
		});
		
		if (debug)
			console.log("Saliendo de ListaAjax.guardarMultiidioma");

	};
	
};

function ListaSimple() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.prototype = new ListaAjax();
	
	var that = this;
	
	this.guardar = function(elements, url, id) {
		
		if (debug)
			console.log("Entrando en ListaSimple.guardar");
		
		var filters = this.getFilters(elements, id);
		
		that.prototype.guardar(url, filters);
		
		if (debug)
			console.log("Saliendo de ListaSimple.guardar");
		
	};
	
	this.getFilters = function(elements, id) {
		
		if (debug)
			console.log("Entrando en ListaSimple.getFilters");
		
		var lista = new Array();
		var filters = "id=" + id;
		
		if (elements.length > 0) {
			
			elements.each(function() {
				
				var value = $(this).attr('related-item-id');
				lista.push(value);
				
			});
			
			filters += "&elementos=" + lista;
			
		}
		
		if (debug)
			console.log("Saliendo de ListaSimple.getFilters");
		
		return filters;
		
	};
	
};

function ListaMultiidioma() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.prototype = new ListaAjax();
		
	var that = this;
	
	this.guardar = function(elements, url, id) {
		
		if (debug)
			console.log("Entrando en ListaMultiidioma.guardar");
		
		var filters = this.getFilters(elements, id);
		
		that.prototype.guardarMultiidioma(url, filters);
		
		if (debug)
			console.log("Saliendo de ListaMultiidioma.guardar");
		
	};
	
	// XXX amartin: este método ha de reescribirse en cada módulo donde sea necesario, extendiendo esta clase.
	this.getFilters = function(elements, id) {
		
		var filters = new Object();
		
		return filters; 
		
	};
		
};