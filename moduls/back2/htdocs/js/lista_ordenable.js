/**
 *
 */
function ListaOrdenable(){
	var params;
		
	var getHtmlItem = function( item, btnEliminar ){
		var html = "<li>";
			html += '<div class="'+params.nombre+'">';
			
			for( var i=0; i < params.atributos.length; i++ ){
				atributo = params.atributos[i];
				
				if( item[atributo] != undefined ){
					valor = item[atributo];
				}else{
					valor = 0;
				}
								
				switch( atributo ){
					case "id":
						html += "<input class=\"" + params.nombre + "_" + atributo + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
						break;
						
					case "nombre":
						html += "<input class=\"" + params.nombre + "_" + atributo + "\" name=\"" + params.nombre + "_"+atributo+"_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
						html += "<span class=\"" + params.nombre + "\">" + valor + "</span>";
						break;
						
					case "orden":
						html += "<input class=\"" + params.nombre + "_" + atributo + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
						break;
						
					default:
						html += "<input class=\"" + params.nombre + "_"+atributo+"\" name=\""+params.nombre+"_"+atributo+"_"+item.id+"\" value=\""+valor+"\" type=\"hidden\" />";
				}
			}			
			
			if( btnEliminar ){
				html += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
			}
			
			html += "</div>";
		html += "</li>";
		
		return html;
	}
	
	this.configurar = function( _params ){
		params = _params;
	}
	
	/**
	 * Rellena los inputs "orden" con el orden correspondiente, tanto en la lista origen como en destino.
	 */
	this.calculaOrden = function(){
						
		jQuery(params.nodoDestino).find("li").each(function(i) {
			jQuery(this).find( "input." + params.nombre + "_orden" ).val(i);			
		});
		jQuery(params.nodoOrigen).find("li").each(function(i) {
			jQuery(this).find( "input." + params.nombre + "_orden" ).val(i);			
		});
		
	}
		
	/**
	 * Agrega un item a la lista.
	 *
	 * @return boolean Devuelve true si el item no se encontraba ya en la lista.
	 */
	this.agregaItem = function( item ){
		var tamLista = jQuery(params.nodoDestino).find("li").size();		
		var itemYaExiste = false;
		var html;
		
		if ( tamLista == 0) {
						
			jQuery(params.nodoDestino).html("<ul></ul>");
			
		} else {
			
			jQuery(params.nodoDestino).find("input."+params.nombre+"_id").each(function() {				
				
				if ( jQuery(this).val() == item.id ) {
					itemYaExiste = true;
				}			
				
			});			
		}
				
		if ( !itemYaExiste ){
		
			html = getHtmlItem( item, true );
			jQuery(params.nodoDestino).find("ul").append(html);			
			return true;
						
		}		
		
		return false;
		
	}
	
	/**
	 * Carga un array de items en la lista.
	 */
	this.agregaItems = function( lista ){
		var id, nombre, i, atributo, valor, item;		
		
		html = "<ul>";
		
		for( i in lista ){						
			html += getHtmlItem( lista[i], false );
		}		
		
		html += "</ul>";		
		
		jQuery(params.nodoOrigen).html(html);
	}
	
	/**
	 * Copia los datos de la lista origen a la de destino.
	 */
	this.copiaInicial = function(){
			
		var html = "<ul>";
			
		jQuery(params.nodoOrigen).find("li").each(function() {			
			var li_elm = jQuery(this);
			var i;
			var item = [];
			var atributo;			
					
			for( i=0; i<params.atributos.length; i++ ){
				atributo = params.atributos[i];
				item[atributo] = li_elm.find( "input."+params.nombre+"_"+atributo ).val();
			}
						
			html += getHtmlItem( item, true );
		});
		
		html += "</ul>";
							
		jQuery(params.nodoDestino).html(html);
	}
	
	/**
	 * Copia los datos de la lista destino a la de origen.
	 */
	this.copiaFinal = function(){
		var numItems = 0;
				
		var html = "<ul>";
		
		jQuery(params.nodoDestino).find("li").each(function(i) {								
			var li_elm = jQuery(this);
			var item = [];
			var i;
			var atributo;
						
			for( i=0; i < params.atributos.length; i++ ){
				atributo = params.atributos[i];
				item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
			}			
			
			html += getHtmlItem( item, false );
			
			numItems++;
		});
	
		html += "</ul>";
		
		jQuery(params.nodoOrigen).html(html);
		
		return numItems;
	}
	
	this.finalizar = function(){
		this.calculaOrden();
		return this.copiaFinal();		
	}
}