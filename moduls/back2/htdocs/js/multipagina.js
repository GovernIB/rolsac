/*
 datos{
	total: Nº total de elementos
	itemsPorPagina: Elementos por página
	paginaActual: Nº de la página actual
	funcionPagina: String con la función a la que se le pasará la nueva página de la forma "funcionPagina(n)"
	}
*/
function Multipagina(datos) {
		
	var totalItems;
	var itemsPorPagina;
	var paginaActual = 0;
	var funcionPagina;
	var isUltimaPagina = false;
	
	this.cambiaItemsPorPagina = function(num){
		
		pag_Res = num;		
		eval(funcionPagina+'(1)');
		
	}
			
	
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
		var margenPaginas = 2;
		
		if( paginasNum > 1 ){
			
			// Calculamos la "ventana de páginas"
			var primeraPagVentana = paginaActual - margenPaginas;
			var ultimaPagVentana = paginaActual + margenPaginas;
			
			var extraDerecha = ultimaPagVentana - paginasNum + 1;
			var extraIzquierda = -(primeraPagVentana);
			
			if( extraDerecha > 0 ){
				primeraPagVentana -= extraDerecha;
			}else if( extraIzquierda > 0 ){
				ultimaPagVentana += extraIzquierda;
			}
			
			if( paginaActual > 0 ){
				
				// Primera página
				html += '<a class="inicio" onclick="'+funcionPagina+'(1);" href="javascript:void(0)" title="'+txtInicio+'"><span></span></a>';
				
				// Retroceder una página
				html += '<a class="retroceder" onclick="'+funcionPagina+'('+(paginaActual)+');" href="javascript:void(0)" title="'+txtAnterior+'"><span></span></a>';
				
			}
			
			for ( var i = 0 ; i < paginasNum ; i++ ) {
				
				if ( i >= primeraPagVentana && i <= ultimaPagVentana )
					html += ( i == paginaActual ) ? '<strong>'+( i+1 )+'</strong>' : '<a class="pagina" onclick="'+funcionPagina+'('+ ( i+1 ) +')"  href="javascript:void(0);">' + ( i+1 ) + '</a>';
				
			}	
			
			
			html += '<form action="" onsubmit="'+funcionPagina+'(jQuery(this).find(\'input\').val());return false;"><input type="text" name="multipagina" value="" /></form>';
			
			
			// Última página
			html += '<span class="de">' + txtDe + '</span><a class="ultimo" onclick="'+funcionPagina+'('+paginasNum+');'+'" href="javascript:void(0)">'+paginasNum+'</a>';
			
			
			// Página siguiente
			if ( !isUltimaPagina )
				html += '<a class="siguiente" onclick="'+funcionPagina+'('+(paginaActual+2)+')'+'" href="javascript:void(0)" title="'+txtSiguiente+'"></a>';
			
			
			html = '<div class="paginacio" role="">'+html+'</div>';
			
		}
		
		return html;	
		
	}
	

	this.init(datos);	
}