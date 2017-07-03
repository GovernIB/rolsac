/****
 Clase básica de tipo lista, para que se encargue de todo. Tanto de conectar como de actualizar el contenido.
 @created by Indra (slromero)
******/
function detall_lista_n () {
	
	/*** Para mantener compatibilidad con el antiguo. **/
	// Activa mensajes de debug.
	var debug = false;
	//this.extend = ListadoBase;
	//this.extend();
	var that = this;
	/** FIN para mantener la compatibilidad. **/
	
	/** Nombre del objeto lista. **/
	this.nombreLista = "lista";
	/** Identificador del formulario. Dentro tendría que tener todos los inputs que son los parámetros para enviar **/
	this.formulario = "";
	/** URL para pedir info. **/
	this.url = "";
	/** Lista de items. **/
	this.items = new Array();
	/** Lista de cabecera. **/
	this.cabeceras = new Array();
	/** Función para inicializar la clase. **/
	this.inicializar = function  (iFormulario, iUrl, iItems, iCabeceras) {
		this.formulario = iFormulario;
		this.url = iUrl;
		this.items = iItems;
		this.cabeceras = iCabeceras;
	};
	this.Detall = null;
	/** Muestra el formulario para insertar una nueva ficha. **/
	this.nuevaFicha = function() {		
		this.Detall.nou();
	};
	/** Función para actualizar un parámetro del formulario. **/
	this.actualizarParametroFormulario = function (nombre, valor) {
		var inputs = $(this.formulario).find("input");
		for(var i = 0; i < inputs.length; i++) {
			 var input = inputs[i];
				if (input.id == nombre) {
					input.value = valor;
					return;
				}
				
		}
		console.log("Error, no existe el parametro " + nombre);
	};
	this.errorTitulo="Error ajax.";
	this.errorText="<p>Intentar de nuevo más tarde.";
	/** Buscar. **/
	this.buscar = function () {
		var variables = this.obtenerVariables();
		
		// ajax
		$.ajax({
			context: this,
			type: "POST",
			url: this.url,
			data: variables,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: this.errorTitulo, text: this.errorText});
			},
			success: function(data) {				
				this.cargarLista(data);
			}
		});
	};
	/** Obtiene las variables de envío a partir de los inputs que hay que en el formulario. **/
	this.obtenerVariables = function () {
		var inputs = $(this.formulario).find("input");
		var variables = "";
		for(var i = 0; i < inputs.length; i++) {
			var input = inputs[i];
			variables += input.id+"="+input.value+"&";
		}
		if (variables.endsWith("&")) {
			variables = variables.substring(0, variables.length);
		}
		return variables;
	};
	/** Obtiene las variable d envío  **/
	this.getHtmlItemsPagina = function(pag_Res) {
		
		var items = [10,20,50];
		var numTodos = 99999;
		var html = '<span class="itemsPagina">' + txtMostrar + '&nbsp;';
		
		for (var i = 0; i < items.length; i++) {
			
			if ( pag_Res == items[i] )
				html += '<strong>'+items[i]+'</strong>&nbsp;';
			
			else
				html += '<a href="javascript:void(0);" onclick="'+this.nombreLista+'.actualizarParametroFormulario(\'pagRes\','+items[i]+'); '+this.nombreLista+'.buscar();">'+items[i]+'</a>&nbsp;';
			
		}
		
		if ( pag_Res == numTodos )
			html += '<strong>'+ txtTodos +'</strong>';
		
		else
			html += '<a href="javascript:void(0)" onclick="'+this.nombreLista+'.actualizarParametroFormulario(\'pagRes\','+numTodos+'); '+this.nombreLista+'.buscar();">'+ txtTodos +'</a>';
		
		
		html += '&nbsp;'+txtItemsPagina+'</span>';
		
		return html;
		
	};
	/** Cambia la variable de nombre, no se llama a buscar. **/
	this.reordenar = function (campo, nombre) {
		var inputCamp  = $(this.formulario).find("input#ordreCamp");
		var inputCampN = $(this.formulario).find("input#ordreCampNombre");
		var inputAsc   = $(this.formulario).find("input#ordreTipus");
		
		//Si el campo es el mismo, se tiene que cambiar el orden de ascendente a desc o al reves.
		if (inputCamp.val() == campo) {
			if (inputAsc.val() == "ASC") {
				jQuery(inputAsc).val("DESC");
			} else {
				jQuery(inputAsc).val("ASC");
			}
			// Si el campo es el mismo, se pone como campo y en ASCENDENTE.
		} else {
			jQuery(inputCamp).val(campo);
			jQuery(inputCampN).val(nombre);
		    jQuery(inputAsc).val("ASC");
		}
	};
	/** Informació de la cabecera sobre la mini info y la paginación. **/
	this.info_txtTrobats = txtTrobats;
	this.info_txtT = "SIA UAs";
    this.info_txtDel = txtDel;
	this.info_txtMostrem = txtMostrem;
	this.info_txtAl = txtAla;
	this.info_txtAscendentment = txtAscendentment;
	this.info_txtDescendentment = txtDescendentment;
	this.info_txtOrdenats = txtOrdenats;
	
	/**  Obtiene la parte superior con los totales y la paginación. **/
	this.getFilaTotalesConPaginacion = function (data) {
		
		var pagRes = parseInt( $(this.formulario).find("input#pagRes").val() );
		var pagPag = parseInt( $(this.formulario).find("input#pagPag").val() );
		
		var info_resultatInici = (pagRes * pagPag ) + 1;
		var info_resultatFinal = info_resultatInici + pagRes - 1;
		
		var info_txt_ordenacio = ", " + this.info_txtOrdenats + " ";
		var campoNombre = $(this.formulario).find("input#ordreCampNombre").val();
		var ascendente   = $(this.formulario).find("input#ordreTipus").val();
		
		if (ascendente == "ASC") {
			info_txt_ordenacio += this.info_txtAscendentment;
		} else {
			info_txt_ordenacio += this.info_txtDescendentment;
		}
		info_txt_ordenacio += " " + txtPer + " " +campoNombre;
		
		codi_totals = "<p class=\"info\">" + this.info_txtTrobats + " <strong>" + data.total + "</strong> " + this.info_txtT.toLowerCase() + ". " + this.info_txtMostrem + " " + this.info_txtDel + " " + info_resultatInici + " " + this.info_txtAl + " " + info_resultatFinal + info_txt_ordenacio + ".";
		
		codi_totals += this.getHtmlItemsPagina(pagRes);
		codi_totals += "</p>";
		return codi_totals;
	};
	// Atributo que contiene el id del elemento que se está visualizando en la ficha.
	this.itemID;
	/** Muestra la ficha a partir del elemento. **/
	this.ficha = function(elemento) {
		// Obtenemos el id del item a partir del id del enlace.
		itemID = jQuery(elemento).attr("id").split("_")[1];
		this.Detall.carregar(itemID);		
		itemID_ultim = itemID;
		this.itemID = itemID;
	} ;
	/** Devuelve la cabecera HTML según el params this.cabeceras **/
	this.getCabeceraHTML = function() {
		var cabeceraHTML = "<div class=\"thead\">";
		cabeceraHTML += "<div class=\"tr\" role=\"rowheader\">";
		for(var i = 0; i < this.cabeceras.length; i++) {
			var cabecera = this.cabeceras[i];
			var etiquetaA;
			if (cabecera.nombre == null || cabecera.nombre == "") {
				etiquetaA = "";
			} else {
				if (cabecera.eventoActivo) {
					if (cabecera.evento == null) {
						etiquetaA = "<a class=\"th "+cabecera.nombre+"\" onClick=\""+this.nombreLista+".reordenar('"+cabecera.order+"','"+cabecera.nombre+"');"+this.nombreLista+".buscar();\">"+cabecera.nombre+"</a>";
					} else {
						etiquetaA = "<a class=\"th "+cabecera.nombre+"\" onClick=\""+cabecera.evento+"\">"+cabecera.nombre+"</a>";
					}
				} else {
					etiquetaA = "<a class=\"th "+cabecera.nombre+"\">"+cabecera.nombre+"</a>";
				}
			}
			
			cabeceraHTML += "<div class=\"th \" role=\"columnheader\" role=\"columnheader\" style=\"width:"+cabecera.width+"%\">"+etiquetaA+"</div>"				
		}
		cabeceraHTML += "</div>";
		cabeceraHTML += "</div>";
		return cabeceraHTML;
	}
	/** Genera el contenido HTML segun los datos. **/
	this.getContenidoHTML = function (data) {
		var contenidoHTML = "<div class=\"tbody\">";
			//Bucle sobre los nodoes.
			for(var i=0; i<data.nodes.length; i++) {
				var elemento = data.nodes[i]; 
				parClass = (i%2) ? " par": "";
				
				contenidoHTML += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				for(var j = 0 ; j < this.items.length; j++) {
					var item = this.items[j];
					contenidoHTML += "<div class=\"td "+item.nombre+"\" role=\"gridcell\" style=\"width:"+item.width+"%\">";
					if (this.items[j].nombre == "id") {
						contenidoHTML += "<span class=\"td "+item.nombre+"\"><a id=\"siaua_"+elemento[item.nombre]+"\" onclick=\""+this.nombreLista+".ficha(this)\">" + elemento[item.nombre] + "</a></span>";			
					} else {
						if (item.evento == null) {
							contenidoHTML += "<span class=\"td "+item.nombre+"\">" + elemento[item.nombre] + "</span>";				
						} else {
							contenidoHTML += "<span class=\"td "+item.nombre+"\"><a onclick=\""+ elemento.evento +"\"')>" + elemento[item.nombre] + "</a></span>";			
						}
					}
					contenidoHTML += "</div>";	
				}
				
				
				//FIN ROW
				contenidoHTML += "</div>";
				
			}
			
			//FIN TBODY
			contenidoHTML += "</div>";
			return contenidoHTML;
	}
	/** Carga la lista. **/
	this.cargarLista = function(data) {
		
		var contenido = "";
		
		if (data.nodes.length == 0) {
			contenido = "<p class=\"noHiHaDades\">No hi ha dades</p>";
		} else {
			
			///Fila con los totales y la paginación.
			var codigo_totales = this.getFilaTotalesConPaginacion(data);
			contenido += codigo_totales;
			
			contenido +="<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\" style=\"clear:both;\">";
			
			///HEAD
			contenido += this.getCabeceraHTML();
			
			///CONTENIDO
			contenido  += this.getContenidoHTML(data);
			
			//Obtenemos el html del footer
			contenido += this.getHtmlFooterPagina(data, this.nombreLista+".cambiaPagina");
			
			//FIN TABLE
			contenido += "</div>";
		}
		
		
		$(this.divDadesLista).html(contenido);
	};
	/** Indica el div donde tiene que ir la lista. **/
	this.divDadesLista = ".dadesLista";
	/**********************************************************
	 *****												*******
	 *****			FOOTER PAGINACION					*******
	 *****												*******
	 **********************************************************/
	
	/**
	 * Cambia de página.
	 * @param pag
	 */ 
	this.cambiaPagina = function( pag ){
		this.actualizarParametroFormulario('pagPag',pag-1); 
		this.buscar();		
	};
	/** Cambia la página de resultado a la indicada. **/
	this.cambiaItemsPorPagina = function(num){
		
		pag_Res = num;		
		eval(funcionPagina+'(1)');
		
	};
	/** Devuelve el codigo HTML de la paginacion footer.**/
	this.getHtmlFooterPagina = function(data, funcionPagina){
		
		var totalItems = data.total;
		var itemsPorPagina = parseInt( $(this.formulario).find("input#pagRes").val() );
		var paginaActual = parseInt( $(this.formulario).find("input#pagPag").val() );
		
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
			var isUltimaPagina = ( paginaActual + 1 ) == paginasNum;
			if ( !isUltimaPagina )
				html += '<a class="siguiente" onclick="'+funcionPagina+'('+( paginaActual+2)+')'+'" href="javascript:void(0)" title="'+txtSiguiente+'"></a>';
			
			
			html = '<div class="paginacio" role="">'+html+'</div>';
			
		}
		
		return html;	
		
	};
}

/****
 Clase básica de tipo item Lista donde se especifica cada elemento.
 @created by Indra (slromero)
******/
function itemLista () {
	/** Width */	
	var witdh = 10;
	/** Elemento . **/
	var nombreElemento;
	/** Evento. **/
	var evento = null;
	
}

/****
Clase básica de tipo item Lista donde se especifica cada elemento.
@created by Indra (slromero)
******/
function itemCabecera () {
	/** Width */	
	var witdh = 10;
	/** Elemento . **/
	var nombreElemento;
	/** Orden que se debería pasar. **/
	var order;
	/** Indica si se activa el evento. **/
	var eventoActivo = true;
	/** Evento. **/
	var evento = null;
	
}