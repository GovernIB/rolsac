//MODUL NORMATIVES

$(document).ready(function() {	

	// elements
	escriptori_normatives_elm = jQuery("#escriptori_normatives");
	resultats_normativa_elm = jQuery("#resultats_normativa");
	modul_normatives_elm = jQuery("div.modulNormatives:first");

	ModulNormativa = new CModulNormativa();
	EscriptoriNormativa = new CEscriptoriNormativa();
	LlistatNormativa = EscriptoriNormativa;

	multipagina_normativa = new Multipagina();

	if (modul_normatives_elm.size() == 1) {
		ModulNormativa.iniciar();
	}

	// Evento para el botón de volver al detalle
	jQuery("#btnVolver_normatives").bind("click", function() { EscriptoriNormativa.torna(); });	
	jQuery("#btnGuardar_normatives").bind("click", function() { EscriptoriNormativa.finalizar(); });
	
});

function CModulNormativa() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListaOrdenable;
	this.extend();		

	var that = this;

	// Campo hidden para controlar los cambios sobre un módulo.
	var $moduloModificado = modul_normatives_elm.find('input[name="modulo_normativas_modificado"]');

	this.iniciar = function() {
		
		jQuery("#cerca_normativa_data").datepicker({ dateFormat: 'dd/mm/yy' });
		jQuery("#cerca_normativa_data_butlleti").datepicker({ dateFormat: 'dd/mm/yy' });
		
		normatives_llistat_elm = escriptori_normatives_elm.find("div.escriptori_items_llistat:first");
		normatives_cercador_elm = escriptori_normatives_elm.find("div.escriptori_items_cercador:first");
		normatives_seleccionades_elm = escriptori_normatives_elm.find("div.escriptori_items_seleccionats:first");

		normatives_dades_elm = normatives_llistat_elm.find("div.dades:first");

		pagPagina_normativa_elm = normatives_llistat_elm.find("input.pagPagina:first");
		ordreTipus_normativa_elm = normatives_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_normativa_elm = normatives_llistat_elm.find("input.ordreCamp:first");

		escriptori_normatives_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});

		normatives_llistat_elm.add(normatives_seleccionades_elm);							

		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "normativa",
			nodoOrigen: modul_normatives_elm.find(".listaOrdenable"),
			nodoDestino: normatives_seleccionades_elm.find(".listaOrdenable"),
			atributos: [			// Campos que queremos que aparezcan en las listas.
	            "id", 
	            "nombre", 
	            "orden", 
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem",		// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "color" 			//Indica si es datos validos o no.
            ],
			multilang: false
		});

		// one al botó de gestionar
		modul_normatives_elm.find("a.gestiona").one("click", function() { ModulNormativa.gestiona(); });
		
	};

	// Marcar el módulo como modificado.    
	this.modificado = function() {
		$moduloModificado.val(1);
	};

	this.nuevo = function() {       
		norma_seleccionats_elm = escriptori_detall_elm.find("div.modulNormatives div.seleccionats");
		norma_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaNormativa + ".");
	};

	this.gestiona = function() {
		
		lis_size = modul_normatives_elm.find("li").size();

		if (lis_size > 0) {
			this.copiaInicial();
			EscriptoriNormativa.contaSeleccionats();
		} else {
			normatives_seleccionades_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaNormativesSeleccionades + ".");			
			normatives_seleccionades_elm.find(".listaOrdenable").html("");
		}

		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_normatives_elm.fadeIn(300);			
		});
		
		this.deshabilitarBotonGuardar();
		
	};

	this.contaSeleccionats = function() {
		
		seleccionats_val = modul_normatives_elm.find(".seleccionat").find("li").size();
		info_elm = modul_normatives_elm.find("p.info:first");

		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaNormativesSeleccionades+ ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtNormativa.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtNormatives.toLowerCase() + "</strong>.");						
		}
		
	};

	this.inicializarNormativas = function(listaNormativas) {
		
		modul_normatives_elm.find(".listaOrdenable").empty();
		
		if (typeof listaNormativas != 'undefined' && listaNormativas != null && listaNormativas.length > 0) {
			that.agregaItems(listaNormativas);
		}
		
		that.contaSeleccionats();
		
	};

	// Devuelve un string con el formato normatives=n1,n2,...,nm donde n son codigos de normativas.
	this.listaNormativas = function () {
		
		var listaNormativas = "normatives=";

		modul_normatives_elm.find("div.listaOrdenable input.normativa_id").each(function() {
			listaNormativas += jQuery(this).val() + ",";
		});

		if (listaNormativas[listaNormativas.length-1] == ","){
			listaNormativas = listaNormativas.slice(0, -1);
		}

		return listaNormativas;
		
	};
	this.hayNormativaSeleccionada = function (idProc) {
		//Si es alta no se valida que haya normativa seleccionada
        return (idProc == "" || idProc == null) ? true : modul_normatives_elm.find('div.listaOrdenable input.normativa_id').length > 0;
    };
	
	this.botonGuardar = jQuery("#btnGuardar_normatives");
	
	this.existeBotonGuardar = function() {
		
		if (debug)
			console.log("Entrando en CModulNormativa.existeBotonGuardar");
		
		if (debug)
			console.log("Saliendo de CModulNormativa.existeBotonGuardar");
		
		return (this.botonGuardar.length > 0);
		
	};
	
	this.habilitarBotonGuardar = function() {
		
		if (debug)
			console.log("Entrando en CModulNormativa.habilitarBotonGuardar");
		
		if (this.existeBotonGuardar() && this.botonGuardar.parent().hasClass("off")) {
    		this.botonGuardar.parent().removeClass("off");
    	}
		
		if (debug)
			console.log("Saliendo de CModulNormativa.habilitarBotonGuardar");
		
    };
    
    this.deshabilitarBotonGuardar = function() {
    	
    	if (debug)
			console.log("Entrando en CModulNormativa.deshabilitarBotonGuardar");
    	
    	if (this.existeBotonGuardar() && !this.botonGuardar.parent().hasClass("off")) {
    		this.botonGuardar.parent().addClass("off");
    	}
    	
    	if (debug)
			console.log("Saliendo de CModulNormativa.deshabilitarBotonGuardar");
    	
    };
    
    this._eliminaItem = this.eliminaItem;
	
	this.eliminaItem = function( item ) {
		
		if (debug)
			console.log("Entrando en CModulNormativa.eliminaItem");

		that._eliminaItem(item);

		if (this.existeBotonGuardar()) {
			this.habilitarBotonGuardar();
		}
		
		if (debug)
			console.log("Saliendo de CModulNormativa.eliminaItem");
		
	};
	
	this._agregaItem = this.agregaItem;
	
	this.agregaItem = function( item ) {
		
		if (debug)
			console.log("Entrando en CModulNormativa.agregaItem");
		
		that._agregaItem(item);

		if (this.existeBotonGuardar()) {
			this.habilitarBotonGuardar();
		}
		
		if (debug)
			console.log("Saliendo de CModulNormativa.agregaItem");
		
	};
	
};

function CEscriptoriNormativa() {
	
	this.extend = ListadoBase;
	this.extend("opcions_normativa", "resultats_normativa", "cercador_normativa", "cercador_normativa_contingut", "", "", "", "btnBuscarForm_normativa", "btnLimpiarForm_normativa");

	var that = this;

	this.nuevo = function() {
		that.limpia();
		resultats_normativa_elm.find('div.dades').empty();
	};

	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo, idMainItem, idRelatedItem ) {	

		// Componemos el item para enviar a la lista.
		var item = {
			id: itemID,
			nombre: titulo,
			idMainItem: idMainItem,
			idRelatedItem: idRelatedItem
		};

		// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
		if ( ModulNormativa.agregaItem( item ) ){		
			this.contaSeleccionats();		
		}
		
	};

	// Cambia de página.
	this.cambiaPagina = function( pag ) {
		
		multipagina_normativa.setPaginaActual(pag - 1);
		pag_Pag = pag;
		this.anar(pag);
		
	};

	this.finCargaListado = function(data, opcions){

		// total
		resultats_total = parseInt(data.total, 10);

		if ( resultats_total > 0 ) {

			txtT = ( resultats_total > 1 ) ? txtNormatives : txtNormativa;

			ultimaPag = Math.floor( resultats_total / pag_Res ) - 1;
			if ( resultats_total % pag_Res > 0 )
				ultimaPag++;

			if ( pag_Pag > ultimaPag )
				pag_Pag = ultimaPag;

			resultatInici = ( ( pag_Pag * pag_Res ) + 1 );
			resultatFinal = ( ( pag_Pag * pag_Res ) + pag_Res > resultats_total ) ? resultats_total : ( pag_Pag * pag_Res ) + pag_Res;

			var isUltimaPagina = false;
			if ( resultats_total == resultatFinal ) {
				isUltimaPagina = true;
			}
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = ( ordre_C == "id" ) ? " " + ordre_T : "";
			ordre_c2 = ( ordre_C == "fecha" ) ? " " + ordre_T : "";
			ordre_c3 = ( ordre_C == "fechaBoletin" ) ? " " + ordre_T : "";

			txt_ordenacio = "";

			if ( resultats_total > 1 ) {

				txt_ordenats = ( ordre_T == "ASC" ) ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";

				if ( ordre_C == "id" )
					txt_per = txtTitol;
				else if (ordre_C == "fecha")
					txt_per = txtData;
				else
					txt_per = txtDataButlleti;

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

			/* Se desactiva la ordenacion
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtAdresa + "</a></div>";
			codi_cap2 = "<div class=\"th cp" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCodiPostal + "</a></div>";
			codi_cap3 = "<div class=\"th poblacio" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPoblacio + "</a></div>";
			 */
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtTitol + "</a></div>";
			codi_cap2 = "<div class=\"th data" + ordre_c2 + "\" role=\"columnheader\">" + txtData + "</a></div>";
			codi_cap3 = "<div class=\"th dataButlleti" + ordre_c3 + "\" role=\"columnheader\">" + txtDataButlleti + "</a></div>";

			// codi taula
			codi_taula = "<div class=\"table llistat normatives\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";

			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";

			// codi cuerpo
			$(data.nodes).slice().each(function(i) {
				
				dada_node = this;
				
				parClass = (i % 2) ? " par": "";
				codi_taula += "<div class=\"tr "+parClass+"\">";

				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a class=\"normativa_" + dada_node.id + "\" href=\"javascript:;\" class=\"nom\">" + dada_node.titulo + "</a>";
				codi_taula += "</div>";

				codi_taula += "<div class=\"td data\" role=\"gridcell\">" + (dada_node.fecha == null ? "" : dada_node.fecha) + "</div>";
				codi_taula += "<div class=\"td dataButlleti\" role=\"gridcell\">" + dada_node.fechaBoletin + "</div>";

				codi_taula += "</div>";

			});

			codi_taula += "</div>";
			codi_taula += "</div>";

			if ( $.browser.opera )
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");

			// Actualizamos el navegador multipágina.
			multipagina_normativa.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "EscriptoriNormativa.cambiaPagina",
				isUltimaPagina: isUltimaPagina
			});

			codi_navegacio = multipagina_normativa.getHtml();

			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;

		} else {

			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaNormatives + ".</p>";

		}

		// animacio
		normatives_dades_elm.fadeOut(300, function() {
			// pintem
			normatives_dades_elm.html(codi_final).fadeIn(300, function() {

				// Evento lanzado al hacer click en un elemento de la lista.
				//jQuery("#resultats .llistat .tbody a").unbind("click").bind("click",function(){
				resultats_normativa_elm.find(".llistat .tbody a").unbind("click").bind("click", function() {

					var partesItem = jQuery(this).attr("class").split("_");
					
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					var idMainItem = $('#item_id').val();
					var idRelatedItem = itemID;
					
					that.agregaItem(itemID, titulo, idMainItem, idRelatedItem);

				});

				// cercador
				normatives_cercador_elm.find("input, select").removeAttr("disabled");

			});
			
		});	
		
	};
	
	this.carregar = function(opcions) {
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)

		//dataVars = "";
		
		// cercador
		var filtrosBuscador = "&titol=" + $("#cerca_normativa_titol").val();
		filtrosBuscador += "&data=" + $("#cerca_normativa_data").val();
		filtrosBuscador += "&dataButlleti=" + $("#cerca_normativa_data_butlleti").val();
		filtrosBuscador += "&tipo=" + $("#cerca_tipus_normativa").val();
		filtrosBuscador += "&numNormativa=" + $("#cerca_num_normativa").val();
		filtrosBuscador += "&boletin=" + $("#cerca_butlleti").val();

		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_normativa_elm.val(opcions.ordreTipus);
		}
		
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_normativa_elm.val(opcions.ordreCamp);
		}

		// paginacio
		pag_Pag = ( opcions.pagina != undefined ) ? parseInt( opcions.pagina , 10 ) : multipagina.getPaginaActual();

		// ordre
		ordre_Tipus = ordreTipus_normativa_elm.val();
		ordre_Camp = ordreCamp_normativa_elm.val();

		// variables
		dataVars = "pagPagina=" + pag_Pag + "&pagRes=" + pag_Res + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + filtrosBuscador;		

		// ajax
		$.ajax({
			type: "POST",
			url: seccioNormatives,
			data: dataVars,
			dataType: "json",
			error: function() {

				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					// error
					Error.llansar();
				}
			},
			success: function(data) {
				if (data.error == undefined || data.error == '') {
					that.finCargaListado(data, opcions);
				} else {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: "Error", text: "<p>" + data.error + "</p>"});
					that.finCargaListado(data, opcions);
				}
			}
		});
		
	};

	this.finalizar = function() {		

		nombre_llistat = ModulNormativa.finalizar();

		codi_normatives_txt = (nombre_llistat == 1) ? txtNormativa : txtNormatives;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaNormatives + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_normatives_txt.toLowerCase() + "</strong>.";

		modul_normatives_elm.find("p.info").html(codi_info);

//		if (nombre_llistat > 1) {			
//		modul_normatives_elm.find(".listaOrdenable ul").sortable({ 
//		axis: 'y', 
//		update: function(event,ui){
//		ModulNormativa.calculaOrden(ui,"origen");
//		EscriptoriNormativa.contaSeleccionats();
//		}
//		}).css({cursor:"move"});
//		}

		// Marcamos el módulo como modificado.
		ModulNormativa.modificado();

		this.torna();
		
	};

	// Método sobreescrito
	this.anar = function(enlace_html) {

		num = parseInt(enlace_html,10);

		// text cercant
		txt = (num <= pag_Pag) ? txtCercantAnteriors : txtCercantItemsSeguents;
		
		normatives_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			normatives_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_normativa_elm.val(num - 1);								
				that.carregar({pagina: num - 1});				
			});
		});
		
	};

	this.torna = function() {

		// animacio
		escriptori_normatives_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_normatives_elm.find("a.gestiona").one("click", function() { ModulNormativa.gestiona(); });
			});

		});

	};

	this.contaSeleccionats = function() {

		seleccionats_val = normatives_seleccionades_elm.find(".seleccionat").find("li").size();
		info_elm = normatives_seleccionades_elm.find("p.info:first");

		if (seleccionats_val == 0) {

			normatives_seleccionades_elm.find("ul").remove();
			info_elm.text(txtNoHiHaNormativesSeleccionades+ ".");

		} else if (seleccionats_val == 1) {

			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtNormativa.toLowerCase() + "</strong>.");

		} else {

			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtNormatives.toLowerCase() + "</strong>.");						
			// normatives_seleccionades_elm.find(".listaOrdenable ul").sortable({ 
			// axis: 'y', 
			// update: function(event,ui){
			// ModulNormativa.calculaOrden(ui,"origen");
			// EscriptoriNormativa.contaSeleccionats();
			// }
			// }).css({cursor:"move"});

		}

		normatives_seleccionades_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {				
			var itemLista = jQuery(this).parents("li:first");
			ModulNormativa.eliminaItem(itemLista);
			ModulNormativa.habilitarBotonGuardar();
			EscriptoriNormativa.contaSeleccionats();
		});
		
	};
	    
};