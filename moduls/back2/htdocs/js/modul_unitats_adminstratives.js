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


function CModulUnitatAdministrativa(){
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	
	this.iniciar = function() {

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
				
		// unitatsAdministratives_llistat_elm.add(unitatsAdministratives_seleccionades_elm);							
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "unitatAdministrativa",
			nodoOrigen: modul_unitatsAdministratives_elm.find(".listaOrdenable"),
			nodoDestino: modul_unitatsAdministratives_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
	}	
			
	this.nuevo = function() {       
		unitatAdm_seleccionats_elm = escriptori_detall_elm.find("div.modulUnitatAdministratives div.seleccionats");
		unitatAdm_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaUA + ".");
	}	
		
	this.contaSeleccionats = function() {		
		seleccionats_val = modul_unitatsAdministratives_elm.find(".seleccionat").find("li").size();
		info_elm = modul_unitatsAdministratives_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaUAsSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtUnitatAdministrativa.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtUnitatsAdministratives.toLowerCase() + "</strong>.");						
		}
	}
	
	
	this.inicializarUnidadesAdministrativas = function(listaUnidadesAdministrativas) {
		if (typeof listaUnidadesAdministrativas != 'undefined' && listaUnidadesAdministrativas != null && listaUnidadesAdministrativas.length > 0) {
			modul_unitatsAdministratives_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaUnidadesAdministrativas, true);
		}
		that.contaSeleccionats();
		
		modul_unitatsAdministratives_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			Detall.modificado();
		});
	}
	
	// Devuelve un string con el formato unitatsAdministratives=n1,n2,...,nm donde n son codigos de unitatAdministrativas.
	this.listaUnidadesAdministrativas = function (){
		var listaUnidadesAdministrativas = "";
		
		modul_unitatsAdministratives_elm.find("div.listaOrdenable input.unitatAdministrativa_id").each(function() {
			listaUnidadesAdministrativas += jQuery(this).val() + ",";
		});
		
		if (listaUnidadesAdministrativas[listaUnidadesAdministrativas.length-1] == ","){
			listaUnidadesAdministrativas = listaUnidadesAdministrativas.slice(0, -1);
		}
		
		return listaUnidadesAdministrativas;
	}
};
