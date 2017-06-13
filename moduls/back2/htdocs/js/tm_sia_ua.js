/**
Clase principal de SIA UA.
**/
jQuery(document).ready(function() { 

	//Generamos la lista
	lista = new ListaN(); 
	

	//Evento sobre los tabs.
	eventoClickTabs();
	
	//Inicilizamos lista.
	inicializamosLista();
	
	//Inicializamos el detalle.
	Detall = new CDetall();	
	Detall.iniciar();
	Detall.setLlistat(lista);
	Detall.escriptori_contingut_elm = jQuery("#escriptori_contingut");
	Detall.escriptori_detall_elm = jQuery("#escriptori_detall");
	
	//Conectamos la lista con el detalle.
	lista.Detall = Detall;
	
	// Mostrar detall
	var itemACarregar = itemAEditar();
	
	//Si hay item que cargar, lo mostramos sino mostramos la lista. 
	if (itemACarregar <= 0) {
		lista.buscar();
	} else {
		Detall.carregar(itemACarregar);
	}
});

/** Inicalizamos los valores de la lista. **/
function inicializamosLista() {
	//Preparamos la lista de búsqueda de la primera página. 
	//Declaración de items.
	//var item1 = new itemLista();item1.nombre = "id";			item1.width=8;	item1.evento=null;
	var item2 = new itemLista();item2.nombre = "ua";			item2.width=35;	item2.evento=null;
	var item4 = new itemLista();item4.nombre = "usuario";		item4.width=15;	item4.evento=null;
	var item5 = new itemLista();item5.nombre = "contrasenya";	item5.width=20;	item5.evento=null;
	var items = new Array(item2, item4, item5);
	
	//Declaramos la cabecera.
	//var cabecera1 = new itemCabecera(); cabecera1.nombre = txtId; 	cabecera1.width=10; cabecera1.order ="id";cabecera1.eventoActivo = true;
	var cabecera3 = new itemCabecera(); cabecera3.nombre = txtUA; 	cabecera3.width=40; cabecera3.order ="unidadAdministrativa.id";cabecera3.eventoActivo = true;
	var cabecera4 = new itemCabecera(); cabecera4.nombre = txtUser; cabecera4.width=15; cabecera4.order ="usuario";cabecera4.eventoActivo = true;
	var cabecera5 = new itemCabecera(); cabecera5.nombre = txtPass; cabecera5.width=15; cabecera5.order ="contrasenya";cabecera5.eventoActivo = true;
	var cabecera6 = new itemCabecera(); cabecera6.nombre = ""; 		cabecera6.width=15; cabecera6.order =null; cabecera6.eventoActivo = false;
	var cabeceras = new Array(cabecera3, cabecera4, cabecera5, cabecera6);
	
	//Inicializamos los items de la tab lista.
	lista.inicializar  (formularioBusqueda, pagLlistat, items, cabeceras);
}

/** Incluir evento sobre el tab de listado. **/
function eventoClickTabs () {
	
	jQuery("#tabListado").click(function() {
 
		//Primero comprobamos que no esté activo.
		if( !jQuery(this).hasClass("actiu") ) {
			
			//Se lo borro a todos y se lo agrego a mi mismo
			jQuery("ul#opcions li").removeClass("actiu");
			jQuery(this).addClass("actiu");
			
			jQuery("div#resultats").show();
			jQuery("#escriptori_detall").hide();
			
			// resultats		
			$("div#resultats").find("div.actiu").slideUp(300, function() {
				
				jQuery(this).removeClass("actiu");
				
				$("div#resultats").find("div#divListado").slideDown(300, function() {
					
					jQuery(this).addClass("actiu");
					
				});
				
			});
		}
	});
		
}

/** Función limpiar datos. **/
function limpiarSiaUA() {
	jQuery("input#item_id").val("");  
	jQuery("input#item_ua_id").val("");  
	jQuery("input#item_usuario").val("");  
	jQuery("input#item_contrasenya").val("");  
}

/*** 
 LISTA CUSTOMIZADA.
***/
function ListaN() {
    
	this.extend = detall_lista_n;
	
	this.extend();
	
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
					if (this.items[j].nombre == "ua") {
						contenidoHTML += "<span class=\"td "+item.nombre+"\"><a id=\"siaua_"+elemento['id']+"\" onclick=\"lista.ficha(this)\">" + elemento[item.nombre] + "</a></span>";			
					} else {
						if (item.evento == null) {
							contenidoHTML += "<span class=\"td "+item.nombre+"\">" + elemento[item.nombre] + "</span>";				
						} else {
							contenidoHTML += "<span class=\"td "+item.nombre+"\"><a onclick=\""+ elemento.evento +"\"')>" + elemento[item.nombre] + "</a></span>";			
						}
					}
					contenidoHTML += "</div>";	
				}
				
				//BOTON PARA QUITAR.
				contenidoHTML += "<div class=\"td boton\" role=\"gridcell\" style=\"width:15%;align-items: center;\">";
				contenidoHTML += "<div class=\"btnGenerico\" style = \"float:left\"><a href=\"javascript:;\" style=\"width:100px;\" id = \"botonInfo\" class=\"btn unitatOrganica\" onclick=\"$('#item_id').val('"+elemento['id']+"');Detall.eliminar();\"><span>Excluir</span></a></div></div>";

				//FIN ROW
				contenidoHTML += "</div>";
				
			}
			
			//FIN TBODY
			contenidoHTML += "</div>";
			return contenidoHTML;
	};
}

//detall
function CDetall() {
    
	this.extend = DetallBase_n;
	
	this.extend();
	
	var that = this;

	
	//Se anyaden los campos que no se van a serializar directamente mediante .serialize()	
	//this._baseGuarda = this.guarda;	
	this.guarda = function() {
		DebugJS.debug("Entrando en DetallBase.guardaEnTmSiaUA");

		if (!that.formulariValid()) {
			DebugJS.debug("Saliendo de DetallBase.guardaEnTmSiaUA");
			return false;
		}

		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		dataForm = $("#formGuardar").serialize();

		if (typeof dataVars != 'undefined' && dataVars.length > 0)
			dataForm += "&" + dataVars;

		$.ajax({
			type: "POST",
			url: pagGuardar,
			data: dataForm,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {

				//Cargamos
				if (data.error == undefined || data.error =='') {
					
					/*
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
					*/
					
					// animacio
					jQuery("#escriptori_detall").fadeOut(300, function() {		
						jQuery("#escriptori_contingut").fadeIn(300, function() {
							Missatge.cancelar();
							lista.buscar();
						});
					});
			
				} else {
					//Hay un error
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.error + "</p>"});	
				}//End if

			} //Fin success

		});//Fin ajax
		
		DebugJS.debug("Saliendo de DetallBase.guardaEnTmSiaUA");
	};

	this.urlPrevisualizar = "/XXX/previsualiza.do";

	// Sobrecargo método para preview personalizado.
	// - Etc.
	this.previsualitza = function() {
		/*
		*/
	};

	/** Si quieres rehacer el iniciar. 
	this.iniciar = function() {
		
	};**/

	this.traduirWrapper = function () {
		
	};

	this.dataPublicacio = function(e) {		
		
	};

	this.nou = function() {
		
		jQuery("#item_id").val("");
		jQuery("#item_ua_id").val("");
		jQuery("#item_ua").val("");
		jQuery("#item_usuario").val("");
		jQuery("#item_contrasenya").val("");
		
		jQuery("div#escriptori_contingut").fadeOut(300, function() {
			jQuery("div#escriptori_detall").fadeIn(300, function() {
				// activar				
				itemID_ultim = 0;
			});
		});

		this.actualizaEventos();

		this.modificado(false);
		
	};

	this.pintar = function(dada_node) {

		if (dada_node.item_id == undefined || dada_node.item_id =='') {
			jQuery("#item_id").val("");
		} else {
			jQuery("#item_id").val(dada_node.item_id);
		}
		
		if (dada_node.item_ua_id == undefined || dada_node.item_ua_id =='') {
			jQuery("#item_ua_id").val("");
		} else {
			jQuery("#item_ua_id").val(dada_node.item_ua_id);
		}
		
		if (dada_node.item_ua == undefined || dada_node.item_ua == '') {
			jQuery("#item_ua").val("");
		} else {
			jQuery("#item_ua").val(dada_node.item_ua);
		}
		
		if (dada_node.item_usuario == undefined || dada_node.item_usuario == '') {
			jQuery("#item_usuario").val("");
		} else {
			jQuery("#item_usuario").val(dada_node.item_usuario);
		}
		
		
		if (dada_node.item_contrasenya == undefined || dada_node.item_contrasenya == '') {
			jQuery("#item_contrasenya").val("");
		} else {
			jQuery("#item_contrasenya").val(dada_node.item_contrasenya);
		}
		
		
	};

	this.elimina = function() {
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		item_ID = $("#item_id").val();

		dataVars = "id=" + item_ID;

		// ajax
		$.ajax({
			type: "POST",
			url: pagEsborrar,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {
				if (data.id > 0) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					//Detall.array({id: dada_node.item_id, accio: "elimina"});
					Detall.recarregar();
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id == -2){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}
		});		
	};

	this.pintarModulos = function(dades) {
		console.log("pintarModulos");		
	};

	this.ocultarModulos = function(selector) {
		console.log("ocultarModulos");		
	};
	
};