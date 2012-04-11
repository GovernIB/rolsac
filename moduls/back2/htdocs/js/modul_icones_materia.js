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
	var params = {
		nombre: "iconaMateria",
		nodoOrigen: modul_iconesMateria_elm.find(".listaOrdenable"),
		nodoDestino: modul_iconesMateria_elm.find(".listaOrdenable"),
		atributos: ["id", "nombre", "url"],  // Campos que queremos que aparezcan en las listas.
		multilang: false
	};
		
	this.iniciar = function() {
		
		// Configuramos la lista ordenable.
		this.configurar(params);
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
	
	/**
	* Versión sobreescrita para obtener el html de un item de la lista.
	*/
	this.getHtmlItem = function( item, btnEliminar, idioma ){
		
		var html = "<li>";
		html += '<div class="'+ params.nombre + '">';
		
		for( var i=0; i < params.atributos.length; i++ ){
			atributo = params.atributos[i];
			if( item[atributo] != undefined ){
				valor = item[atributo];
			} else {
				valor = 0;
			}
			
			switch( atributo ) {
			
				case "id":
					html += "<input class=\"" + params.nombre + "_" + atributo + "\" id=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
					break;
				case "url":
					html += "<div class='thumbnail'>" +
							"<a href=\"" + pagArrel + valor + "\">" +
								"<img width='50px' height='50px' title=\"" + $.trim(item["nombre"])  + "\" class=\"" + params.nombre + "_" + atributo + "\" id=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" src=\"" + pagArrel + valor + "\" />" +
							"</a>" + 
							"</div>";
					break;
				case "orden":
					html += "<input class=\"" + params.nombre + "_" + atributo + "\" id=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
					break;
				default:
					//html += "<input class=\"" + params.nombre + "_" + atributo + "\" id=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";				
			}
		}
		
		if( btnEliminar )
			html += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
		
		html += "</div>";
		html += "</li>";
		
		return html;
		
	}
	
	this.inicializarIconesMateria = function(listaIconesMateria) {
		if (typeof listaIconesMateria != 'undefined' && listaIconesMateria != null && listaIconesMateria.length > 0) {
			modul_iconesMateria_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaIconesMateria);
		}
		that.contaSeleccionats();		
	}
	
};
