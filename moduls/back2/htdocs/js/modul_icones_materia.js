// MODUL ICONES MATERIA

$(document).ready(function() {	
	
	// elements
    modul_iconesMateria_elm = jQuery("#modul_iconesMateria:first");

	ModulIconesMateria = new CModulIconesMateria();
	
	//if (modul_iconesMateria_elm.size() == 1) {
		ModulIconesMateria.iniciar();
	//}
	
});


function CModulIconesMateria(){
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	
	this.iniciar = function() {
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "iconaMateria",
			nodoOrigen: modul_iconesMateria_elm.find(".listaOrdenable"),
			nodoDestino: modul_iconesMateria_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
	}	
			
	this.nuevo = function() {       
		fetVitals_seleccionats_elm = escriptori_detall_elm.find("#modul_iconesMateria div.seleccionats");
		fetVitals_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaIconaMateria + ".");
	}	
		
	this.contaSeleccionats = function() {		
		seleccionats_val = modul_iconesMateria_elm.find(".seleccionat").find("li").size();
		info_elm = modul_iconesMateria_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaIconesMateriaSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtIconaMateria.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtIconesMateria.toLowerCase() + "</strong>.");
		}
	}
	
	
	this.inicializarIconesMateria = function(listaIconesMateria) {
		if (typeof listaIconesMateria != 'undefined' && listaIconesMateria != null && listaIconesMateria.length > 0) {
			modul_iconesMateria_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaIconesMateria);
		}
		that.contaSeleccionats();
	}
	
};
