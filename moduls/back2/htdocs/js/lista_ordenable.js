/**
 * Clase para manejar listas ordenables.
 */
function ListaOrdenable(){
	var params;
		
	/**
	 * Obtiene el idioma activo (solo en modo multi-idioma)
	 */
	var getIdiomaActivo = function( $lista ){	
		// Obtenemos el idioma activo.
		var clases = $lista.parents(".cajaIdioma").attr("class");
		clases = clases.split(" ");
		for( var i=0; i<clases.length; i++ ){
			if( clases[i].length == 2 ){
				return clases[i];
			}
		}
	}
	
	/**
	 * Replica la ordenación de la lista actual al resto de listas (solo en modo multi-idioma).
	 */
	var actualizaOrden = function( claves, tipo ){		
		var id;		
		var datos;
		var nodo;
					
		if( tipo == "origen" ){
			nodo = params.nodoOrigen;
		}else{
			nodo = params.nodoDestino;
		}
		
		jQuery(nodo).each(function(){						
			datos = [];
			
			jQuery(this).find("li").each(function() {				
				id = jQuery(this).find( "input." + params.nombre + "_id" ).val();
				datos[id] = jQuery(this).html();
			});
			
			jQuery(this).find("li").each(function(i) {			
				jQuery(this).html( datos[claves[i]] );
			});
		}); 
		
	}
		
	/**
	 * Obtiene el html de un item de la lista.
	 */
	this.getHtmlItem = function( item, btnEliminar, idioma ){
		var sufijoIdioma = "";
		var idiomaAtributo = "";
		var partesAtributo;
		
		if( idioma ){
			sufijoIdioma += "_"+idioma;
		}
	
		var html = "<li>";
			html += '<div class="'+params.nombre+'">';
			
			for( var i=0; i < params.atributos.length; i++ ){
				atributo = params.atributos[i];
				
				if( item[atributo] != undefined ){
				
					if( params.multilang && ( typeof item[atributo] == "object" ) ){						
						valor = item[atributo][idioma];					
					}else{										
						valor = item[atributo];												
					}
					
				}else{
					valor = 0;
				}
												
				switch( atributo ){
					case "id":
						html += "<input class=\"" + params.nombre + "_" + atributo + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
						break;
						
					case "nombre":
						html += "<input class=\"" + params.nombre + "_" + atributo + sufijoIdioma + "\" name=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
						html += "<span class=\"" + params.nombre + "\">" + valor + "</span>";
						break;
						
					case "orden":
						html += "<input class=\"" + params.nombre + "_" + atributo + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
						break;															
						
					default:
						html += "<input class=\"" + params.nombre + "_" + atributo + sufijoIdioma + "\" name=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
				}
			}
			
			if( btnEliminar ){
				html += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
			}
			
			html += "</div>";
		html += "</li>";
		
		return html;
	}
	
	/**
	 * Establece los parámetros de configuración.
	 * _params = {
	 *		nombre: "etiqueta",
     *		nodoOrigen: modul_edificis_elm.find(".listaOrdenable"), hace referencia a la lista "final" en la que se graban los datos definitivos para guardar.
	 *		nodoDestino: edificis_seleccionats_elm.find(".listaOrdenable"), hace referencia a la lista "intermedia" que el usuario modifica.
	 *		atributos: ["id", "nombre", "orden", "..."], los campos "id" y "nombre" deberían aparecer siempre.
	 *	    multilang: true | false // Especifica si la lista es multiidioma.
	 * }
 	 */
	this.configurar = function( _params ){
		params = _params;
	}
	
	/**
	 * Rellena los inputs "orden" con el orden correspondiente, tanto en la lista origen como en destino.
	 */
	this.calculaOrden = function( ui, tipo ){
		var claves = [];
		var ordenAnterior, ordenNuevo;
		
		if( params.multilang ){
			
			id = jQuery( ui.item ).find( "input."+params.nombre+"_id" ).val();
			
			jQuery( ui.item ).parents(".seleccionat").find("li").each(function(i){				
				id = jQuery(this).find("input."+params.nombre+"_id" ).val();
				claves.push( id );
			});
			
			actualizaOrden( claves, tipo );
			
		}
		
		jQuery(params.nodoDestino).each(function(){						
			jQuery(this).find("li").each(function(i) {				
				jQuery(this).find( "input." + params.nombre + "_orden" ).val(i);			
			});			
		});				
				
		jQuery(params.nodoOrigen).each(function(){
			jQuery(this).find("li").each(function(i) {
				jQuery(this).find( "input." + params.nombre + "_orden" ).val(i);			
			});
		});
				
	}
	
	/**
	 * Elimina un item de la lista.
	 */ 
	this.eliminaItem = function( item ){		
		var id = jQuery(item).find("input." + params.nombre + "_id:first").val();						
		jQuery(params.nodoDestino).find("input[name=" + params.nombre + "_id_" + id + "]").parents("li").remove();				
	}
		
	/**
	 * Agrega un item a la lista.
	 *
	 * @return boolean Devuelve true si el item no se encontraba ya en la lista.
	 */
	this.agregaItem = function( item ){
		var tamLista = jQuery(params.nodoDestino).filter(":first").find("li").size();		
		var itemYaExiste = false;
		var html;		
		var idioma;
						
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
							
			if( params.multilang ){
		
				jQuery( params.nodoDestino ).each(function(){
					idioma = getIdiomaActivo( jQuery(this) );
					html = this.getHtmlItem( item, true, idioma );					
					jQuery(this).find("ul").append(html);					
				});		
				
			}else{			
							
				html = this.getHtmlItem( item, true );				
				jQuery( params.nodoDestino ).find("ul").append(html);			
				
			}
			
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * Carga un array de items en la lista.
	 */
	this.agregaItems = function( lista ){
		var item, idioma;		
		
		if( params.multilang ){
		
			jQuery(params.nodoOrigen).each( function(){
				idioma = getIdiomaActivo( jQuery(this) );
				
				html = "<ul>";
				for( i in lista ){				
					html += this.getHtmlItem( lista[i], false, idioma );
				}
				html += "</ul>";
				
				jQuery(this).html(html);
			});
			
		}else{
		
			html = "<ul>";		
			for( i in lista ){						
				html += this.getHtmlItem( lista[i], false );
			}			
			html += "</ul>";
			
			jQuery(params.nodoOrigen).html(html);
		
		}
	}
	
	/**
	 * Copia los datos de la lista origen a la de destino.
	 */
	this.copiaInicial = function(){		
		var i;
		var html;
		var idioma;
		var clases;
		
		var _this = this;
		
		if( params.multilang ){			
		
			jQuery(params.nodoOrigen).each(function(){
								
				idioma = getIdiomaActivo( jQuery(this) );								
				
				html = "<ul>";
				
				jQuery(this).find("li").each(function(){
					var li_elm = jQuery(this);
					var item = [];
					var atributo;
							
					for( i=0; i<params.atributos.length; i++ ){
						atributo = params.atributos[i];
						
						// id y orden no pueden ser multiidioma.
						if( atributo == "id" || atributo == "orden" ){
						
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
							
						}else{
						
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo + "_" + idioma ).val();
							
						}
					}
								
					html += _this.getHtmlItem( item, true, idioma );
				});			
				
				html += "</ul>";
				
				jQuery(params.nodoDestino).each(function(){
					if( jQuery(this).parent(".cajaIdioma").hasClass(idioma) ){					
						jQuery(this).html(html);
					}
				});
							
			});
					
		}else{
		
			html = "<ul>";
		
			jQuery(params.nodoOrigen).find("li").each(function() {			
				var li_elm = jQuery(this);			
				var item = [];
				var atributo;
						
				for( i=0; i<params.atributos.length; i++ ){
					atributo = params.atributos[i];
					item[atributo] = li_elm.find( "input."+params.nombre+"_"+atributo ).val();
				}
							
				html += _this.getHtmlItem( item, true );
			});
			
			html += "</ul>";
													
			jQuery(params.nodoDestino).html(html);
			
		}		
	}
	
	/**
	 * Copia los datos de la lista destino a la de origen.
	 */
	this.copiaFinal = function(){
		var numItems;
		var idioma;
		var i;
		var html;
		
		var _this = this;
		
		if( params.multilang ){
		
			jQuery(params.nodoDestino).each(function(){
			
				numItems = 0;
			
				html = "<ul>";
		
				idioma = getIdiomaActivo( jQuery(this) );
				
				jQuery(this).find("li").each(function(){
					var li_elm = jQuery(this);
					var item = [];				
					var atributo;
								
					for( i=0; i < params.atributos.length; i++ ){
						atributo = params.atributos[i];
						
						if( atributo == "id" || atributo == "orden" ){

							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
							
						}else{						
						
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo + "_" + idioma ).val();
							
						}
					}			
					
					html += _this.getHtmlItem( item, false, idioma );
					
					numItems++;
				});
				
				html += "</ul>";
				
				// Escribimos la lista
				jQuery(params.nodoOrigen).each(function(){
					if( jQuery(this).parent(".cajaIdioma").hasClass(idioma) ){
						jQuery(this).html(html);						
					}
				});
				
			});
		
		}else{
		
			numItems = 0;
		
			html = "<ul>";
		
			jQuery(params.nodoDestino).find("li").each(function(i) {								
				var li_elm = jQuery(this);
				var item = [];
				var atributo;
							
				for( i=0; i < params.atributos.length; i++ ){
					atributo = params.atributos[i];
					item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
				}			
				
				html += _this.getHtmlItem( item, false );
				
				numItems++;
			});
			
			html += "</ul>";
			
			jQuery(params.nodoOrigen).html(html);
	
		}				
		
		return numItems;
	}
	
	this.finalizar = function(){		
		return this.copiaFinal();		
	}
}