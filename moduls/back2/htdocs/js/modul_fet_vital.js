// MODUL FET VITAL 

$(document).ready(function() {	
	
	// elements
//	escriptori_unitatsAdministratives_elm = jQuery("#escriptori_unitatsAdministratives");
    modul_fetVital_elm = jQuery("div.modulFetVital:first");

	ModulFetVital = new CModulFetVital();
	
	if (modul_fetVital_elm.size() == 1) {
		ModulFetVital.iniciar();
	}
	
});


function CModulFetVital(){
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	
	this.iniciar = function() {

//        unitatsAdministratives_llistat_elm = escriptori_unitatsAdministratives_elm.find("div.escriptori_items_llistat:first");
//		unitatsAdministratives_cercador_elm = escriptori_unitatsAdministratives_elm.find("div.escriptori_items_cercador:first");
//		unitatsAdministratives_seleccionades_elm = escriptori_unitatsAdministratives_elm.find("div.escriptori_items_seleccionats:first");
//		
//		unitatsAdministratives_dades_elm = unitatsAdministratives_llistat_elm.find("div.dades:first");
//		
//		pagPagina_unitatAdministrativa_elm = unitatsAdministratives_llistat_elm.find("input.pagPagina:first");
//		ordreTipus_unitatAdministrativa_elm = unitatsAdministratives_llistat_elm.find("input.ordreTipus:first");
//		ordreCamp_unitatAdministrativa_elm = unitatsAdministratives_llistat_elm.find("input.ordreCamp:first");
//		
//		escriptori_unitatsAdministratives_elm.find("div.botonera").each(function() {
//			botonera_elm = $(this);		
//		}); 
				
		// unitatsAdministratives_llistat_elm.add(unitatsAdministratives_seleccionades_elm);							
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "fetVital",
			nodoOrigen: modul_fetVital_elm.find(".listaOrdenable"),
			nodoDestino: modul_fetVital_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
	}
			
	this.nuevo = function() {       
		fetVitals_seleccionats_elm = escriptori_detall_elm.find("div.modulFetVital div.seleccionats");
		fetVitals_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFetVital + ".");
	}	
		
	this.contaSeleccionats = function() {		
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
					Detall.modificado();
				}
			}).css({cursor:"move"});

		}
	}
	
	
	this.inicializarFetsVitals = function(listaFetsVitals) {		
		if (typeof listaFetsVitals != 'undefined' && listaFetsVitals != null && listaFetsVitals.length > 0) {
			modul_fetVital_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaFetsVitals, true);
		}
		that.contaSeleccionats();
		
		modul_fetVital_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();
			Detall.modificado();
		});
	}
	
	// Devuelve un string con el formato fetsVitals=n1,n2,...,nm on n son codis de fetsVitals.
	this.listaFetsVitals = function (){
		var listaFetsVitals = "";
		
		modul_fetVital_elm.find("div.listaOrdenable input.fetVital_id").each(function() {
			listaFetsVitals += jQuery(this).val() + ",";
		});
		
		if (listaFetsVitals[listaFetsVitals.length-1] == ","){
			listaFetsVitals = listaFetsVitals.slice(0, -1);
		}
		
		return listaFetsVitals;
	}
};
