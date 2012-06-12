/**
 * Clase que implementa la lógica del módulo de estadísticas
 * Todos los parametros son opcionales.
 * @param idModuloEstadisticas = Id del div HTML de estadísticas
 */
function ModulEstadistiques(idModuloEstadisticas){

	if (typeof idModuloEstadisticas == "undefined" || idModuloEstadisticas == "") idModuloEstadisticas = "#modulEstadistiques";

	// Anadir los "#" por comodidad.
	var ID_MARK = "#";
	if (idModuloEstadisticas.substring(0,1) != ID_MARK) idModuloEstadisticas = ID_MARK + idOpciones;
	
	var that = this;
	
	// Atributo que contiene el id del elemento que se está visualizando en la ficha.
	this.itemID;
	
	var moduloEstadisticas_elm = jQuery(idModuloEstadisticas);
	
	// Realizar una búsqueda
	this.pinta = function(tipusEstadistica, itemID){		
			
		if (!itemID) {
			return;
		}
		// variables
		var dataVars = "tipus=modulo&tipusModul=" + tipusEstadistica + "&id=" + itemID;
					
		var codi_stats = '<div class="fila">';
		codi_stats +='<img src="' + pagEstadistiques + '?' + dataVars + '" width="728px" />';
		codi_stats +='</div>';

		moduloEstadisticas_elm.find("div.modul_continguts:first").html(codi_stats);
		moduloEstadisticas_elm.show();

	}
	
	
	
}
