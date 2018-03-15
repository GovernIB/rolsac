/**
 * Clase para manejar listas ordenables.
 */
function ListaOrdenable() {
	
	var params;
	
	// Activa mensajes de debug.
	var debug = false;
		
	/**
	 * Obtiene el idioma activo (solo en modo multi-idioma)
	 */
	this.getIdiomaActivo = function( $lista ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.getIdiomaActivo");
		
		// Obtenemos el idioma activo.
		var clases = $lista.parents(".cajaIdioma").attr("class");
		clases = clases.split(" ");
		for ( var i = 0; i < clases.length; i++ ) {
			if ( clases[i].length == 2 ) {
				return clases[i];
			}
		}
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.getIdiomaActivo");
		
	};
	
	// XXX amartin: este método al final tendrá que borrarse si todos los módulos laterales se guardan vía AJAX,
	// ya que se encarga de marcar en azul el botón "GUARDA!" de la vista de detalle de un registro, dando a entender
	// que se ha modificado el detalle cuando sólo se ha modificado algo en el módulo lateral.
	this.modificado = function() {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.modificado");
		
	    if ( Detall ) {
	        Detall.modificado(true);
	    }
	    
	    if (debug)
			console.log("Saliendo de ListaOrdenable.modificado");
	    
	};
	
	/**
	 * Replica la ordenación de la lista actual al resto de listas (solo en modo multi-idioma).
	 */
	var actualizaOrden = function( claves, tipo ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.actualizaOrden");
		
		var id;		
		var datos;
		var nodo;
		
		// Para poder controlar las modificaciones en los modulos de los documentos, ya que no se tenia pensado
		if ($('#modulo_documents_modificado') != "undefined") {
            $('#modulo_documents_modificado').val("1");
        }
		
		if ( tipo == "origen" ) {
			nodo = params.nodoOrigen;
		} else {
			nodo = params.nodoDestino;
		}
		
		jQuery(nodo).each(function() {
			
			datos = [];
			
			jQuery(this).find("li").each(function() {				
				id = jQuery(this).find( "input." + params.nombre + "_id" ).val();
				datos[id] = jQuery(this).html();
			});
			
			jQuery(this).find("li").each(function(i) {			
				jQuery(this).html( datos[claves[i]] );
			});
			
		});
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.actualizaOrden");
		
	};
		
	/**
	 * Obtiene el html de un item de la lista.
	 */
	this.getHtmlItem = function( item, btnEliminar, idioma ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.getHtmlItem");
		
		var fontcolor = ""; //Parametro extra para marcar con rojo valores no validos.
		if (item["color"] != undefined && item["color"] != '') {
			fontcolor = "style=\"color:" + item["color"]+"\"";
		}
		
		var sufijoIdioma = "";
		var idiomaAtributo = "";
		var partesAtributo;
        		
		if ( idioma ) {
			sufijoIdioma += "_" + idioma;
		}
        
		var html = "<li element-id='" + item.id + "' main-item-id='" + item.idMainItem + "' related-item-id='" + item.idRelatedItem + "'>";
		html += '<div class="' + params.nombre + '">';
			
		for ( var i = 0; i < params.atributos.length; i++ ) {
			
			atributo = params.atributos[i];
			
			if ( item[atributo] != undefined ) {
			
				if ( params.multilang && ( typeof item[atributo] == "object" ) ) {						
					valor = item[atributo][idioma];
				} else {										
					valor = item[atributo];												
				}
				
			} else {
				valor = 0;
			}
											
			switch ( atributo ) {
			
				case "id":
					html += "<input class=\"" + params.nombre + "_" + atributo + "\" id=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
					break;
					
				case "nombre":
                case "nom":
                	// Dentro del atributo value del elemento <input> sustituimos entidades HTML del valor usando $("<div/>").html(valor).text()
                	// Visto en http://stackoverflow.com/questions/1147359/how-to-decode-html-entities-using-jquery
					html += "<input class=\"" + params.nombre + "_" + atributo + sufijoIdioma + "\" id=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" value=\"" + $("<div/>").html(valor).text() + "\" type=\"hidden\" />";
					
					if (jQuery.trim(valor) == "")
						valor = "&nbsp;";
					
					if (params.nodoDestino != null && params.nodoDestino.length > 0 && params.nodoDestino[0].id != null && params.nodoDestino[0].id == 'listaNormativasDeProcedimientos') {
						html += "<span class=\"" + params.nombre + "\" "+fontcolor+"><a href='../normativa/normativa.do?itemId="+item.id+"' target='_blank' "+fontcolor+">" + valor + "</a></span>";
					} else {
						html += "<span class=\"" + params.nombre + "\" "+fontcolor+">" + valor + "</span>";
					}
					break;			

				case "url":
					html += "<input class=\"" + params.nombre + "_" + atributo + sufijoIdioma + "\" id=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
					break;

				case "orden":                        
					html += "<input class=\"" + params.nombre + "_" + atributo + "\" id=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";                        
					break;															
					
				default:
					html += "<input class=\"" + params.nombre + "_" + atributo + sufijoIdioma + "\" id=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" name=\"" + params.nombre + "_" + atributo + sufijoIdioma + "_" + item.id + "\" value=\"" + valor + "\" type=\"hidden\" />";
			
			}
		
		}
		
		if ( btnEliminar ) {
			html += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
		}
		
		html += "</div>";
		html += "</li>";
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.getHtmlItem");
		
		return html;
		
	};
	
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
	this.configurar = function( _params ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.configurar");
		
		params = _params;
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.configurar");
		
	};
	
	/**
	 * Devuelve los parametros de configuracion.
	 */
	this.getConfiguracion = function() {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.getConfiguracion");
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.getConfiguracion");
		
		return params;
		
	};
	
	/**
	 * Rellena los inputs "orden" con el orden correspondiente, tanto en la lista origen como en destino.
	 */
	this.calculaOrden = function( ui, tipo ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.calculaOrden");
		
		var claves = [];
		var ordenAnterior, ordenNuevo;
		
		if ( params.multilang ) {
			
			id = jQuery( ui.item ).find( "input." + params.nombre + "_id" ).val();
			
			jQuery( ui.item ).parents(".seleccionat").find("li").each(function(i) {				
				id = jQuery(this).find("input." + params.nombre + "_id" ).val();
				claves.push( id );
			});
			
			actualizaOrden( claves, tipo );
			
		}
		
		jQuery(params.nodoDestino).each(function() {						
			jQuery(this).find("li").each(function(i) {				
				jQuery(this).find( "input." + params.nombre + "_orden" ).val(i + 1);
			});			
		});				
				
		jQuery(params.nodoOrigen).each(function() {
			jQuery(this).find("li").each(function(i) {
				jQuery(this).find( "input." + params.nombre + "_orden" ).val(i + 1);
			});
		});
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.calculaOrden");
				
	};
	
	/**
	 * Elimina un item de la lista.
	 */ 
	this.eliminaItem = function( item ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.eliminaItem");
		
		var id = jQuery(item).find("input." + params.nombre + "_id:first").val();						
		jQuery(params.nodoDestino).find("input[name=" + params.nombre + "_id_" + id + "]").parents("li").remove();
		
		// TODO amartin: esto no ha de hacerse si el guardado del módulo lateral es es vía AJAX.
		// this.modificado();
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.eliminaItem");
		
	};

	/**
	 * Agrega un item a la lista.
	 *
	 * @return boolean Devuelve true si el item no se encontraba ya en la lista.
	 */
	this.agregaItem = function( item ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.agregaItem");
		
		var _this = this;
		var tamLista = jQuery(params.nodoDestino).filter(":first").find("li").size();		
		var itemYaExiste = false;
		var html;		
		var idioma;
						                                
		if ( tamLista == 0) {
						
			jQuery(params.nodoDestino).html("<ul class='guarda-ajax'></ul>");
			
		} else {
			
			jQuery(params.nodoDestino).find("input." + params.nombre + "_id").each(function() {				
				
				if ( jQuery(this).val() == item.id ) {
					itemYaExiste = true;
				}			
				
			});			
		}
		
		if ( !itemYaExiste ) {
							
			if ( params.multilang ) {
		
				jQuery( params.nodoDestino ).each(function() {
					idioma = _this.getIdiomaActivo( jQuery(this) );
					html = _this.getHtmlItem( item, true, idioma );					
					jQuery(this).find("ul").append(html);					
				});		
				
			} else {			
							
				html = _this.getHtmlItem( item, true );				
				jQuery( params.nodoDestino ).find("ul").append(html);			
				
			}
			
			if (debug)
				console.log("Saliendo de ListaOrdenable.agregaItem");
			
			return true;
			
		}
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.agregaItem");
		
		return false;
		
	};
	
	/**
	 * Carga un array de items en la lista.
	 */
	this.agregaItems = function( lista, btnEliminar ) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.agregaItems");
		
        var _this = this;
		var item, idioma;		
		var eliminar;
		
		if (typeof btnEliminar != "undefined") {
			eliminar = Boolean(btnEliminar);
		} else {
			eliminar = false;
		}
		
		if ( params.multilang ) {
		
			jQuery(params.nodoOrigen).each( function() {
				idioma = _this.getIdiomaActivo( jQuery(this) );
				
				html = "<ul class='guarda-ajax'>";
				for ( i in lista ) {
					html += _this.getHtmlItem( lista[i], eliminar, idioma );
				}
				html += "</ul>";
				
				jQuery(this).html(html);
			});
			
		} else {
		
			html = "<ul class='guarda-ajax'>";
			
			for ( i in lista ) {						
				html += _this.getHtmlItem( lista[i], eliminar );
			}
			
			html += "</ul>";
			
			jQuery(params.nodoOrigen).html(html);
		
		}
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.agregaItems");
		
	};
	
	/**
	 * Copia los datos de la lista origen a la de destino.
	 */
	this.copiaInicial = function() {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.copiaInicial");
		
		var i;
		var html;
		var idioma;
		var clases;
		
		var _this = this;
		
		if ( params.multilang ) {			
		
			jQuery(params.nodoOrigen).each(function() {
								
				idioma = _this.getIdiomaActivo( jQuery(this) );								
				
				html = "<ul class='guarda-ajax'>";
				
				jQuery(this).find("li").each(function() {
					
					var li_elm = jQuery(this);
					var item = [];
					var atributo;
							
					for ( i = 0; i < params.atributos.length; i++ ) {
						
						atributo = params.atributos[i];
						
						// id y orden no pueden ser multiidioma.
						if ( atributo == "id" || atributo == "orden" ) {
						
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
							
						} else {
						
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo + "_" + idioma ).val();
							
						}
						
					}
								
					html += _this.getHtmlItem( item, true, idioma );
					
				});			
				
				html += "</ul>";
				
				jQuery(params.nodoDestino).each(function() {
					if ( jQuery(this).parent(".cajaIdioma").hasClass(idioma) ) {					
						jQuery(this).html(html);
					}
				});
							
			});
					
		} else {
		
			html = "<ul class='guarda-ajax'>";
		
			jQuery(params.nodoOrigen).find("li").each(function() {			
				var li_elm = jQuery(this);			
				var item = [];
				var atributo;
				
				for ( i = 0; i < params.atributos.length; i++ ) {
					atributo = params.atributos[i];
					item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
				}
							
				html += _this.getHtmlItem( item, true );
			});
			
			html += "</ul>";
													
			jQuery(params.nodoDestino).html(html);
			
		}
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.copiaInicial");
		
	};
	
	/**
	 * Copia los datos de la lista destino a la de origen.
	 * btnEliminar es un booleano para indicar si ha de ponerse un boton para eliminar (cruz roja). Es optativo.
	 */
	this.copiaFinal = function(btnEliminar) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.copiaFinal");
		
		var _this = this;
		
		var numItems;
		var idioma;
		var i;
		var html;
		
		var eliminar = typeof btnEliminar != 'undefined' && new Boolean(btnEliminar); 
		
		if ( params.multilang ) {
		
			jQuery(params.nodoDestino).each(function(){
			
				numItems = 0;
			
				html = "<ul class='guarda-ajax'>";
		
				idioma = _this.getIdiomaActivo( jQuery(this) );
				
				jQuery(this).find("li").each(function() {
					
					var li_elm = jQuery(this);
					var item = [];				
					var atributo;
								
					for ( i = 0; i < params.atributos.length; i++ ) {
						
						atributo = params.atributos[i];
						
						if ( atributo == "id" || atributo == "orden" ){
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
						} else {						
							item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo + "_" + idioma ).val();
						}
						
					}			
					
					html += _this.getHtmlItem(item, eliminar, idioma);
					
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
		
		} else {
		
			numItems = 0;
		
			html = "<ul class='guarda-ajax'>";
		
			jQuery(params.nodoDestino).find("li").each(function(i) {
				
				var li_elm = jQuery(this);
				var item = [];
				var atributo;
							
				for ( i = 0; i < params.atributos.length; i++ ) {
					atributo = params.atributos[i];
					item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
				}			
				
				html += _this.getHtmlItem(item, eliminar);
				
				numItems++;
				
			});
			
			html += "</ul>";
			
			jQuery(params.nodoOrigen).html(html);
	
		}
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.copiaFinal");
		
		return numItems;
		
	};
	
	/**
	 * btnEliminar es un booleano para indicar si ha de ponerse un boton para eliminar (cruz roja). Es optativo.
	 */
	this.finalizar = function(btnEliminar) {
		
		if (debug)
			console.log("Entrando en ListaOrdenable.finalizar");
		
		if (typeof btnEliminar != 'undefined') {
			return this.copiaFinal(btnEliminar);
		} else {
			return this.copiaFinal();
		}
		
		if (debug)
			console.log("Saliendo de ListaOrdenable.finalizar");
		
	};
	
}