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
	
	var params = {
		nombre: "iconaFamilia",
		nodoOrigen: modul_iconesFamilia_elm.find(".listaOrdenable"),
		nodoDestino: modul_iconesFamilia_elm.find(".listaOrdenable"),
		atributos: ["id", "nombre", "url"], // Campos que queremos que aparezcan en las listas.
		multilang: false
	};
	
	this.iniciar = function() {
		
		// Configuramos la lista ordenable.
		this.configurar(params);
	}	
			
	this.nuevo = function() {       
		fetVitals_seleccionats_elm = escriptori_detall_elm.find("#modul_iconesFamilia div.seleccionats");
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
	
	this.inicializarIconesFamilia = function(listaIconesFamilia) {
		if (typeof listaIconesFamilia != 'undefined' && listaIconesFamilia != null && listaIconesFamilia.length > 0) {
			modul_iconesFamilia_elm.find(".listaOrdenable").empty();
			that.agregaItems(listaIconesFamilia);
		}
		that.contaSeleccionats();
	}
	
};
