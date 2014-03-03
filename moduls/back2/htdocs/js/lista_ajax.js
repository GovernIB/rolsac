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
			error: function() {

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

	}
	
};

/** @style: .lista-simple*/
function ListaSimple() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.prototype = new ListaAjax();
	
	var that = this;
	
	this.guardar = function(element, url, id) {
		
		if (debug)
			console.log("Entrando en ListaSimple.guardar");
		
		console.log("element");
		console.log(element);
		
		var filters = this.getFilters(element, id);
		
		that.prototype.guardar(url, filters);
		
		if (debug)
			console.log("Saliendo de ListaSimple.guardar");
		
	}
	
	this.getFilters = function(element, id) {
		
		if (debug)
			console.log("Entrando en ListaSimple.getFilters");
		
		console.log("element");
		console.log(element);
		
		var lista = new Array();
		var filters = "id=" + id;
		
		if (element.length > 0) {
			
			element.each(function() {
				
				var value = $(this).attr('related-item-id');
				lista.push(value);
				
			});
			
			filters += "&elementos=" + lista;
			
		}
		
		if (debug)
			console.log("Saliendo de ListaSimple.getFilters");
		
		return filters;
		
	}
	
};

/** @style: .lista-compleja*/
function ListaCompleja() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.prototype = new ListaAjax();
	
	var that = this;
	
	this.guardar = function(selector) {
		
		if (debug)
			console.log("Entrando en ListaCompleja.guardar");
		
		that.prototype.guardar(selector, getFilters(selector));
		
		if (debug)
			console.log("Saliendo de ListaCompleja.guardar");
		
	}
	
	this.getFilters = function(selector) {
		
		if (debug)
			console.log("Entrando en ListaCompleja.getFilters");
		
		//TODO:
		
		if (debug)
			console.log("Saliendo de ListaCompleja.getFilters");
		
	}
	
};