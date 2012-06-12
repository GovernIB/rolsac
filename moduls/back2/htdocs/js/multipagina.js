/*
 datos{
	total: N� total de elementos
	itemsPorPagina: Elementos por p�gina
	paginaActual: N� de la p�gina actual
	funcionPagina: String con la funci�n a la que se le pasar� la nueva p�gina de la forma "funcionPagina(n)"
	}
*/
function Multipagina(datos){		
	var totalItems;
	var itemsPorPagina;
	var paginaActual = 0;
	var funcionPagina
			
	this.getPaginaActual = function(){
		return paginaActual;
	}
	
	this.setPaginaActual = function(n){		
		paginaActual = n;
	}
		
	// Establece los datos iniciales
	this.init = function(datos){
		
		paginaActual = 0;
		
		if( datos ){
			totalItems = datos.total;	
			itemsPorPagina = datos.itemsPorPagina;
			paginaActual = datos.paginaActual;
			funcionPagina = datos.funcionPagina;
		}
	}
						
	// Devuelve el c�digo HTML del multip�gina.
	this.getHtml = function(){
		var html = "";
		var codi_paginas = "";
		var paginasNum = Math.ceil(totalItems/itemsPorPagina);					
		var paginas = [];
		
		if( paginasNum > 1 ){
		
			for ( var i=0; i < paginasNum; i++ ) {		
				paginas.push( ( i == paginaActual ) ? "<strong>"+( i+1 )+"</strong>" : "<a href=\"javascript:;\" onclick=\""+funcionPagina+"("+ ( i+1 ) +")\">" + ( i+1 ) + "</a>" );
			}	
			html += paginas.join(" ");			
			html = '<p class="paginacio" role="">'+html+'</p>';
		}
		
		return html;		
	}	

	this.init(datos);	
}