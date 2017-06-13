// TM SIA
$(document).ready(function() {
	
	//Generamos las listas!
	listaJob = new ListaN(); 
	listaJob.tipo = "JOB";
	listaJob.divDadesLista = ".dadesJob";
	listaJob.nombreLista="listaJob";
	
	listaPendiente = new ListaN();  
	listaPendiente.tipo = "Pendientes";
	listaPendiente.divDadesLista = ".dadesPendientes";
	listaPendiente.nombreLista="listaPendiente";
	
	listaNoCompletos= new ListaN(); 
	listaNoCompletos.tipo = "Pendientes";
	listaNoCompletos.divDadesLista = ".dadesNoCompletas";
	listaNoCompletos.nombreLista="listaNoCompletos";
	
	//Evento sobre los tabs.
	eventoClickTabs();
	
	// elements
	inicializarBtn();
	
	//Inicializamos listas.
	inicializarListaJob();
	inicializarListaPteYnoCompletos();
	
	//Buscar en la primera lista que es la de jobs.
	listaJob.buscar();
	
});

/** Inicializar Lista de Pendientes y No completos. **/
function inicializarListaPteYnoCompletos() {
	//Preparamos la lista de búsqueda de la primera página. 
	//Declaración de items.
	var item1 = new itemLista();item1.nombre = "id";			item1.width=8;	item1.evento=null;
	var item2 = new itemLista();item2.nombre = "tipo";			item2.width=10;	item2.evento=null;
	var item3 = new itemLista();item3.nombre = "idElemento";	item3.width=10;	item3.evento=null;
	//var item4 = new itemLista();item4.nombre = "estado";		item4.width=10;	item4.evento=null;
	var item5 = new itemLista();item5.nombre = "fecAlta";		item5.width=15;	item5.evento=null;
	var item6 = new itemLista();item6.nombre = "fecIdx";		item6.width=15;	item6.evento=null;
	var item7 = new itemLista();item7.nombre = "mensaje";		item7.width=35;	item7.evento=null;
	var items = new Array(item1, item2, item3,  item5, item6, item7);
	
	//Declaramos la cabecera.
	var cabecera1 = new itemCabecera(); cabecera1.nombre = txtId; 			cabecera1.width=10; cabecera1.order ="id";cabecera1.eventoActivo = true;
	var cabecera2 = new itemCabecera(); cabecera2.nombre = txtTipo; 		cabecera2.width=10; cabecera2.order ="tipo";cabecera2.eventoActivo = true;
	var cabecera3 = new itemCabecera(); cabecera3.nombre = txtIdElemento; 	cabecera3.width=10; cabecera3.order ="idElemento";cabecera3.eventoActivo = true;
	//var cabecera4 = new itemCabecera(); cabecera4.nombre = txtEstado; 		cabecera4.width=10; cabecera4.order ="estado";cabecera4.eventoActivo = true;
	var cabecera5 = new itemCabecera(); cabecera5.nombre = txtFalta; 		cabecera5.width=15; cabecera5.order ="fecAlta";cabecera5.eventoActivo = true;
	var cabecera6 = new itemCabecera(); cabecera6.nombre = txtFenvio;		cabecera6.width=15; cabecera6.order ="fecIdx"; cabecera6.eventoActivo = true;
	var cabecera7 = new itemCabecera(); cabecera7.nombre = txtMensaje; 		cabecera7.width=30; cabecera7.order ="mensaje"; cabecera7.eventoActivo = false;
	var cabeceras = new Array(cabecera1, cabecera2,cabecera3,  cabecera5, cabecera6, cabecera7);
	
	var formularioBusquedaPdt = jQuery("#formPendientes");
	//Inicializamos los items de la tab lista.
	listaPendiente.inicializar  (formularioBusquedaPdt, pagLlistat, items, cabeceras);

	var formularioBusquedaNoCompleto = jQuery("#formNoCompletos");
	//Inicializamos los items de la tab lista.
	listaNoCompletos.inicializar  (formularioBusquedaNoCompleto, pagLlistat, items, cabeceras);
} 


/** Inicializar lista jobs. **/
function inicializarListaJob() {
	//Preparamos la lista de búsqueda de la primera página. 
	//Declaración de items.
	var item1 = new itemLista();item1.nombre = "id";			item1.width=8;	item1.evento=null;
	var item2 = new itemLista();item2.nombre = "estat";			item2.width=35;	item2.evento=null;
	var item3 = new itemLista();item3.nombre = "tipo";			item3.width=15;	item3.evento=null;
	var item4 = new itemLista();item4.nombre = "fechaIni";		item4.width=15;	item4.evento=null;
	var item5 = new itemLista();item5.nombre = "fechaFin";		item5.width=20;	item5.evento=null;
	var item6 = new itemLista();item6.nombre = "descBreve";		item6.width=20;	item6.evento=null;
	var item7 = new itemLista();item7.nombre = "descripcion";	item7.width=20;	item7.evento=null;
	var items = new Array(item1, item2, item3, item4, item5, item6, item7);
	
	//Declaramos la cabecera.
	var cabecera1 = new itemCabecera(); cabecera1.nombre = txtId; 		cabecera1.width=10; cabecera1.order ="id";		cabecera1.eventoActivo = true; cabecera1.evento = null;     
	var cabecera2 = new itemCabecera(); cabecera2.nombre = "Estat"; 	cabecera2.width=10; cabecera2.order ="estado";	cabecera2.eventoActivo = true; cabecera2.evento = null;
	var cabecera3 = new itemCabecera(); cabecera3.nombre = txtTipo; 	cabecera3.width=8; cabecera3.order ="tipo";		cabecera3.eventoActivo = false; cabecera3.evento = null;
	var cabecera4 = new itemCabecera(); cabecera4.nombre = "Data inici"; 	cabecera4.width=9; cabecera4.order ="fechaIni";	cabecera4.eventoActivo = true; cabecera4.evento = null;
	var cabecera5 = new itemCabecera(); cabecera5.nombre = "Data fi"; 	cabecera5.width=9; cabecera5.order ="fechaFin";	cabecera5.eventoActivo = true; cabecera5.evento = null;
	var cabecera6 = new itemCabecera(); cabecera6.nombre = "Descripció abreviada"; cabecera6.width=35; cabecera6.order ="descBreve"; cabecera6.eventoActivo = false; 
	var cabeceras = new Array(cabecera1, cabecera2,cabecera3, cabecera4, cabecera5, cabecera6);
	var formularioBusqueda = jQuery("#formJobs");
	//Inicializamos los items de la tab lista.
	listaJob.inicializar  (formularioBusqueda, pagLlistatJob, items, cabeceras);

}

/** Inicializar botones. ***/
function inicializarBtn() {
	
	jQuery("#btnContinuar").unbind("click").bind("click", function() {
		
        $.ajax({
            type: "POST",
            url: pagEnviarTodo, 
            dataType: "json",
            error: function() {					
                // missatge
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                
            },
            success: function(data) {
            	 if (!data.error) {
                  	Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEnviantDades});			
                  } else {
                      Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});					
                  }
            }
        });
	});
	
	jQuery("#btnCerrarJobs").unbind("click").bind("click", function() {
		
        $.ajax({
            type: "POST",
            url: pagCerrarJobs, 
            dataType: "json",
            error: function() {					
                // missatge
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                
            },
            success: function(data) {
            	 if (!data.error) {
                  	Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtCerrantJobs});			
                  } else {
                      Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});					
                  }
            }
        });
	});
	
	
	jQuery("#btnIndexarTiempo").unbind("click").bind("click", function() {
		
        $.ajax({
            type: "POST",
            url: pagSiaTiempo, 
            dataType: "json",
            error: function() {					
                // missatge
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                
            },
            success: function(data) {
            	 if (!data.error) {
                  	Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtTiempoJobs});			
                  } else {
                      Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});					
                  }
            }
        });
	});
	
	jQuery("#btnEnviarPendientes").unbind("click").bind("click", function() {
		
        $.ajax({
            type: "POST",
            url: pagEnviarPendientes, 
            dataType: "json",
            error: function() {					
                // missatge
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                
            },
            success: function(data) {
            	 if (!data.error) {
                  	Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtPendientesMensaje});			
                  } else {
                      Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});					
                  }
            }
        });
	});
	
	
	    
	jQuery('#close').unbind("click").bind("click", function(){
	        $('.popup').fadeOut('slow');
	        $('.popup-overlay').fadeOut('slow');
	        $("#item_texto").val("");
	        return false;
	});
	
}


/** Incluir evento sobre el tab de listado. 
 *  Se presupone que todos los listados cuelgan de divResultats. Además, sólo
 *   ha sido probado cuando NO hay detalle, en este caso, todos son listas.
 *   
 * **/
function eventoClickTabs () {
	generarEventoClickTab("#tabListadoJobs",		"div#divListadoJob"		   , listaJob);
	generarEventoClickTab("#tabListadoPendientes",	"div#divListadoPendientes" , listaPendiente );
	generarEventoClickTab("#tabListadoIncompleto",	"div#divListadoIncompletas", listaNoCompletos);
}	

/**
 * Función que asocia un evento click al tab.
 * 
 * La idea es que cada tab tiene un div asociado, lo que hace en resumen este evento es:
 *  - Mirar que no esté el tab activo.
 *  - Quitar la marca de clase 'actiu' a todos los elementos del tab.
 *  - Asociarle como 'actiu' al tab.
 *  - Generar un evento slideUp (que oculta hacia arriba) al div activo (que será de otro tab).
 *  - Generar un evento slideUp (que muestra hacia abajo) al div asociado al tab del click.
 * @param nombreTab
 * @param nombreDiv
 */
function generarEventoClickTab(nombreTab, nombreDiv, lista) {
	jQuery(nombreTab).click(function() {
 
		//Primero comprobamos que no esté activo.
		if( !jQuery(this).hasClass("actiu") ) {
			
			//Se lo borro a todos y se lo agrego a mi mismo
			jQuery("ul#opcions li").removeClass("actiu");
			jQuery(this).addClass("actiu");
			
			// resultats		
			$("div#resultats").find("div.actiu").slideUp(300, function() {
				
				jQuery(this).removeClass("actiu");
				
				$("div#resultats").find(nombreDiv).slideDown(300, function() {
					
					jQuery(this).addClass("actiu");
					lista.buscar();
				});
				
			});
		}
	});
}

/** Para incluir saltos de linea sustituyendo por su etiqueta br **/
function incluirSaltosLinea(descripcion) {
	 while (descripcion.indexOf("<br />") != -1) {
		 descripcion = descripcion.replace("<br />","\n");
	 }
	 while (descripcion.indexOf("<br/>") != -1) {
		 descripcion = descripcion.replace("<br/>","\n");
	 }
	 while (descripcion.indexOf("<br>") != -1) {
		 descripcion = descripcion.replace("<br>","\n");
	 }
	 while (descripcion.indexOf("<br >") != -1) {
		 descripcion = descripcion.replace("<br >","\n");
	 }
	 while (descripcion.indexOf("</a>") != -1) {
		 descripcion = descripcion.replace("</a>","");
	 }
	 return descripcion;
}

/** Función para pintar popuo. **/
function pintarPopUp(descripcion) {
	 $('.popup').fadeIn('slow');
     $('.popup-overlay').fadeIn('slow');
     $('.popup-overlay').height($(window).height());
	 $("#item_texto").val(incluirSaltosLinea (descripcion) );
     return false;
}

/*** 
 LISTA CUSTOMIZADA.
***/
function ListaN() {
    
	this.extend = detall_lista_n;
	
	this.extend();
	
	//Indica si es de tipo JOB o PENDIENTE
	this.tipo = "JOB"; //JOB o PENDIENTE
	
	
	/** Genera el contenido HTML segun los datos. **/
	this.getContenidoHTML = function (data) {
		if (this.tipo == "JOB") {
			return this.getContenidoHTMLJob(data);
		}
		else {
			return this.getContenidoHTMLPendiente(data);
		}
	};
	
	/** Contiendo HTML De job. **/
	this.getContenidoHTMLJob = function (data) {
		// codi cuerpo
		var width="8"; //Porcentaje del width de cada columna
		var width10="10"; 
		var width40="40";
		
			var contenido = "<div class=\"tbody\">";
			//$(data.nodes).each(function(i) {
			//Bucle sobre los nodoes.
			for(var i=0; i<data.nodes.length; i++) {
				var elemento = data.nodes[i]; 
				
				
				parClass = (i%2) ? " par": "";
				
				//Si tiene estado 3 o -1 se marca como erroneo
				if(elemento.estado == 3 || elemento.estado == -1 ){
					parClass =  " errorEstado";
				}
				contenido += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				contenido += "<div class=\"td id\" role=\"gridcell\" style=\"width:"+width+"%\">";
				
				contenido += "<span class=\"id\">" + elemento.id + "</span>";				
				contenido += "</div>";			
	
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width10+"%\">";
				var descEstado="";
				if (elemento.estado == 0) { 
					descEstado = txtEstadoCreado;	
				} else if (elemento.estado == 1){
					descEstado = txtEstadoEje;
				} else if(elemento.estado == 2){
					descEstado = txtEstadoEnviado;
				} else if(elemento.estado == 3){
					descEstado = txtEstadoEnvError;
				}else if(elemento.estado == -1){
					descEstado = txtEstadoError;
				}
				
				contenido += "<span class=\"fecha\">"+descEstado+"</span>";
				contenido += "</div>";			
				
				var tipo = elemento.tipo;
				if (elemento.tipo == 'TOT') { 
					tipo = 'Tots';	
				} else if (elemento.tipo == 'PDT') { 
					tipo = 'Pendents';
				} else if(elemento.tipo == 'TMP') { 
					tipo = 'Revisar proc. per temps';
				} else if(elemento.tipo == 'TOT') { 
					tipo = 'INFO';
				} 
				
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width+"%\">";
				contenido += "<span class=\"fecha\">" + tipo + "</span>";	
				contenido += "</div>";
				
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width+"%\">";
				contenido += "<span class=\"fecha\">" + this.getFechaString(elemento.fechaIni) + "</span>";	
				contenido += "</div>";
				
				contenido += "<div class=\"td fechaIni\" role=\"gridcell\" style=\"width:"+width+"%\">";
				contenido += "<span class=\"fecha\">" + this.getFechaString(elemento.fechaFin) + "</span>";	
				contenido += "</div>";			
				
				contenido += "<div class=\"td fechaFicha\" role=\"gridcell\" style=\"width:"+width40+"%\;text-align: left;\">";
				contenido += "<span class=\"descripcion\" >" + elemento.descBreve + "</span>";
				contenido += "</div>";
				
				contenido += "<div class=\"td fechaNormativa\" role=\"gridcell\" style=\"width:"+width+"%;align-items: center;\">";
				contenido += "<div class=\"btnGenerico\" style = \"float:left\"><a href=\"javascript:;\" style=\"width:100px;\" id = \"botonInfo\" class=\"btn unitatOrganica\" onclick=\"pintarPopUp('"+ this.limpiarTexto(elemento.descripcion) +"')\"><span>"+txtBotonInfo+"</span></a></div>";

				//FIN ROW
				contenido += "</div>";				
			}
			return contenido;
	};
	
	/** Contenido HTML de pendiente. **/
	this.getContenidoHTMLPendiente = function (data) {
		var contenidoHTML = "<div class=\"tbody\">";
			//Bucle sobre los nodoes.
			for(var i=0; i<data.nodes.length; i++) {
				var elemento = data.nodes[i]; 
				parClass = (i%2) ? " par": "";
				
				contenidoHTML += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				for(var j = 0 ; j < this.items.length; j++) {
					var item = this.items[j];
					contenidoHTML += "<div class=\"td "+item.nombre+"\" role=\"gridcell\" style=\"width:"+item.width+"%\">";
					var textoElemento = elemento[item.nombre];
					if (item.nombre == 'fecAlta' || item.nombre == 'fecIdx') {
						textoElemento = this.getFechaString(textoElemento);			
					}
					if (item.nombre == 'mensaje' && textoElemento == null) {
						textoElemento = '';
					}
					if (item.evento == null) {
						contenidoHTML += "<span class=\"td "+item.nombre+"\">" + textoElemento + "</span>";				
					} else {
						contenidoHTML += "<span class=\"td "+item.nombre+"\"><a onclick=\""+ elemento.evento +"\"')>" + textoElemento + "</a></span>";
					}
					contenidoHTML += "</div>";	
				}
				
				
				//FIN ROW
				contenidoHTML += "</div>";
				
			}
			
			//FIN TBODY
			contenidoHTML += "</div>";
			return contenidoHTML;
	};
	
	/** Devuelve la fecha en formato string DD/MM/YYYY HH:MM:SS **/
	this.getFechaString = function(ifecha) {
		var retorno = "";
		if (ifecha != null) {
			var fecha = new Date(ifecha);
			retorno = this.getLPAD(fecha.getMonth()+1 , 2)+"/"+
				this.getLPAD(fecha.getDate(), 2)+"/"+
				this.getLPAD(fecha.getFullYear(), 4)+" "+
				this.getLPAD(fecha.getHours(), 2)+":"+
				this.getLPAD(fecha.getMinutes(), 2)+":"+
				this.getLPAD(fecha.getSeconds(), 2);
		}
		
		return retorno;
	};
	
	/** Funcion LPDA. **/
	this.getLPAD = function(itexto, longitud) {
		var texto = itexto;
		while (texto.toString().length < longitud) {
			texto = "0"+texto;
		}
		return texto;
	};
	
	/** Obtiene el texto borrando el null. **/
	this.getTexto = function(itexto) {
		var texto = "";
		if (itexto != null && itexto != "null") {
			texto = itexto;
		}
		return texto;
	};
	
	/** Limpiar texto. **/
	this.limpiarTexto = function (descripcion) {
		while (descripcion.indexOf("'") != -1) {
			 descripcion = descripcion.replace("'","");
		}
		return descripcion;		
	};
	
}