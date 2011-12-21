// MODUL ICONES FAMILIA

$(document).ready(function() {	
	
	// elements
    modul_iconesFamilia_elm = jQuery("#modul_iconesFamilia:first");

	ModulIconesFamilia = new CModulIconesFamilia();
	
	//if (modul_iconesFamilia_elm.size() == 1) {
		ModulIconesFamilia.iniciar();
	//}
	
});


function CModulIconesFamilia(){
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	
	this.iniciar = function() {
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "iconaFamilia",
			nodoOrigen: modul_iconesFamilia_elm.find(".listaOrdenable"),
			nodoDestino: modul_iconesFamilia_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
	}	
			
	this.nuevo = function() {       
		fetVitals_seleccionats_elm = escriptori_detall_elm.find("div.modulIconesFamila div.seleccionats");
		fetVitals_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaIconaFamilia + ".");
	}	
		
	this.contaSeleccionats = function() {		
		seleccionats_val = modul_iconesFamilia_elm.find(".seleccionat").find("li").size();
		info_elm = modul_iconesFamilia_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaIconesFamiliaSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtIconaFamilia.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtIconesFamilia.toLowerCase() + "</strong>.");
		}
	}
	
	
	this.inicializarIconesFamilia = function(listaIconesFamilia) {
		if (typeof listaIconesFamilia != 'undefined' && listaIconesFamilia != null && listaIconesFamilia.length > 0) {
			modul_iconesFamilia_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaIconesFamilia);
		}
		that.contaSeleccionats();
	}
	
};
