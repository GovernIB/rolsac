// MODUL FET VITAL 

$(document).ready(function() {	
	
	// elements
    modul_fetVital_elm = jQuery("div.modulFetVital:first");

	ModulFetVital = new CModulFetVital();
	
	if (modul_fetVital_elm.size() == 1) {
		ModulFetVital.iniciar();
	}
	
});

function CModulFetVital() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListaOrdenable;
	this.extend();
	
	var that = this;
	
	this.iniciar = function() {
		
		if (debug)
			console.log("Entrando en CModulFetVital.iniciar");
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "fetVital",
			nodoOrigen: modul_fetVital_elm.find(".listaOrdenable"),
			nodoDestino: modul_fetVital_elm.find(".listaOrdenable"),
			atributos: [			// Campos que queremos que aparezcan en los elementos de las lista ordenable.
	            "id", 
	            "nombre", 
	            "orden", 
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem"		// Campo necesario para guardado AJAX genérico de módulos laterales.
            ],
			multilang: false
		});
		
		// Desactivamos que al cambiar un valor en este desplegable la vista se marque como modificada.
		// Esto se hace de forma genérica para elementos de los formularios en detall_base.js, de este modo:
		// 		jQuery("#" + ids.form + " input,#" + ids.form + " select,#" + ids.form + " textarea").bind("change", function() { that.modificado(); });
		jQuery('#item_fetVital_relacionat').unbind('change');
		
		if (debug)
			console.log("Saliendo de CModulFetVital.iniciar");
		
	}
			
	this.nuevo = function() {
		
		if (debug)
			console.log("Entrando en CModulFetVital.nuevo");
		
		fetVitals_seleccionats_elm = escriptori_detall_elm.find("div.modulFetVital div.seleccionats");
		fetVitals_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFetVital + ".");
		
		if (debug)
			console.log("Saliendo de CModulFetVital.nuevo");
		
	}	
		
	this.contaSeleccionats = function() {
		
		if (debug)
			console.log("Entrando en CModulFetVital.contaSeleccionats");
		
		seleccionats_val = modul_fetVital_elm.find(".seleccionat").find("li").size();
		info_elm = modul_fetVital_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			info_elm.text(txtNoHiHaFetsVitalsSeleccionades+ ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtFetVital.toLowerCase() + "</strong>.");
			
		} else if (seleccionats_val > 1) {
			
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtFetsVitals.toLowerCase() + "</strong>.");
									
			modul_fetVital_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui) {
					ModulFetVital.calculaOrden(ui, "destino");
					that.contaSeleccionats();
				}
			}).css({cursor:"move"});

		}
		
		if (debug)
			console.log("Saliendo de CModulFetVital.contaSeleccionats");
		
	}
	
	this.inicializarFetsVitals = function(listaFetsVitals) {
		
		if (debug)
			console.log("Entrando en CModulFetVital.inicializarFetsVitals");
		
		if (typeof listaFetsVitals != 'undefined' && listaFetsVitals != null && listaFetsVitals.length > 0) {
			modul_fetVital_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaFetsVitals, true);
		}
		
		that.contaSeleccionats();
		
		modul_fetVital_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
		
		if (debug)
			console.log("Saliendo de CModulFetVital.inicializarFetsVitals");
		
	}
	
	// Devuelve un string con el formato fetsVitals=n1,n2,...,nm on n son codis de fetsVitals.
	this.listaFetsVitals = function () {
		
		if (debug)
			console.log("Entrando en CModulFetVital.listaFetsVitals");
		
		var listaFetsVitals = "";
		
		modul_fetVital_elm.find("div.listaOrdenable input.fetVital_id").each(function() {
			listaFetsVitals += jQuery(this).val() + ",";
		});
		
		if (listaFetsVitals[listaFetsVitals.length-1] == ","){
			listaFetsVitals = listaFetsVitals.slice(0, -1);
		}
		
		if (debug)
			console.log("Saliendo de CModulFetVital.listaFetsVitals");
		
		return listaFetsVitals;
		
	}
	
};
