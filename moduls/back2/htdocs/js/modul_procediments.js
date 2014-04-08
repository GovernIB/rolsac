// MODUL PROCEDIMENTS

$(document).ready(function() {	
	
	// elements
	escriptori_procediments_elm = jQuery("#escriptori_procediments");
    resultats_procediment_elm = jQuery("#resultats_procediments");
    modul_procediments_elm = jQuery("div.modulProcediments:first");

	ModulProcediment = new CModulProcediment();
	EscriptoriProcediment = new CEscriptoriProcediment();
	LlistatProcediment = EscriptoriProcediment;

	multipagina_procediment = new Multipagina();
	
	if (modul_procediments_elm.size() == 1) {
		ModulProcediment.iniciar();
	}
	
	// Evento para el botón de volver al detalle
	jQuery("#btnVolverDetalle_procediment").bind("click", function() { EscriptoriProcediment.torna(); });
	jQuery("#btnFinalizar_procediment").bind("click", function() { EscriptoriProcediment.finalizar(); });
	
});

function CModulProcediment() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	
	this.iniciar = function() {
		
		if (debug)
			console.log("Entrando en CModulProcediment.iniciar");
		
		jQuery("#cerca_fechaCaducidad, #cerca_fechaPublicacion, #cerca_fechaActualizacion").datepicker({ dateFormat: 'dd/mm/yy' });

        procediments_llistat_elm = escriptori_procediments_elm.find("div.escriptori_items_llistat:first");
		procediments_cercador_elm = escriptori_procediments_elm.find("div.escriptori_items_cercador:first");
		procediments_seleccionats_elm = escriptori_procediments_elm.find("div.escriptori_items_seleccionats:first");
		
		procediments_dades_elm = procediments_llistat_elm.find("div.dades:first");
		
		pagPagina_procediment_elm = procediments_llistat_elm.find("input.pagPagina:first");
		ordreTipus_procediment_elm = procediments_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_procediment_elm = procediments_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_procediments_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		procediments_llistat_elm.add(procediments_seleccionats_elm);							
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "procediment",
			nodoOrigen: modul_procediments_elm.find(".listaOrdenable"),
			nodoDestino: procediments_seleccionats_elm.find(".listaOrdenable"),
			atributos: [			// Campos que queremos que aparezcan en los elementos de las lista ordenable.
	            "id", 
	            "nombre", 
	            "orden", 
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem"		// Campo necesario para guardado AJAX genérico de módulos laterales.
            ],
			multilang: false
		});
				
		// one al botó de gestionar
		modul_procediments_elm.find("a.gestiona").one("click", function() { ModulProcediment.gestiona(); });
		
		if (debug)
			console.log("Saliendo de CModulProcediment.iniciar");
		
	};
	
	this.nuevo = function() {
		
		if (debug)
			console.log("Entrando en CModulProcediment.nuevo");
		
		proc_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.seleccionats");
		proc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaProcediment + ".");
		
		if (debug)
			console.log("Saliendo de CModulProcediment.nuevo");
		
	};
		
	this.gestiona = function() {
		
		if (debug)
			console.log("Entrando en CModulProcediment.gestiona");
		
		lis_size = modul_procediments_elm.find("li").size();

		if (lis_size > 0) {
			this.copiaInicial();
			EscriptoriProcediment.contaSeleccionats();
		} else {
			procediments_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaProcedimentsSeleccionats+ ".");			
			procediments_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_procediments_elm.fadeIn(300);			
		});
		
		if (debug)
			console.log("Saliendo de CModulProcediment.gestiona");
		
	};
	
	this.contaSeleccionats = function() {
		
		if (debug)
			console.log("Entrando en CModulProcediment.contaSeleccionats");
		
		seleccionats_val = modul_procediments_elm.find(".seleccionat").find("li").size();
		info_elm = modul_procediments_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaProcedimentsSeleccionats+ ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtProcediment.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtProcediments.toLowerCase() + "</strong>.");						
		}
		
		if (debug)
			console.log("Saliendo de CModulProcediment.contaSeleccionats");
		
	};
	
	this.inicializarProcediments = function(listaProcediments) {
		
		if (debug)
			console.log("Entrando en CModulProcediment.inicializarProcediments");
		
		var listaOrdenable = modul_procediments_elm.find(".listaOrdenable");
		listaOrdenable.empty();
		
		if (typeof listaProcediments != 'undefined' && listaProcediments != null && listaProcediments.length > 0)
			that.agregaItems(listaProcediments, true);
			
		that.contaSeleccionats();
		that.prepararListaProcedimientos(listaOrdenable);
		
		if (debug)
			console.log("Saliendo de CModulProcediment.inicializarProcediments");
		
	};

	this.prepararListaProcedimientos = function($listaOrdenable) {
		
		if (debug)
			console.log("Entrando en CModulProcediment.prepararListaProcedimientos");
		
		$listaOrdenable.find("ul").sortable({
			axis: 'y', 
			cursor: 'url(../img/cursor/grabbing.cur), move',
			update: function(event,ui){
				ModulProcediment.calculaOrden(ui, "origen");
				EscriptoriProcediment.contaSeleccionats();
			}
		}).find("li").css("cursor","url(../img/cursor/grab.cur), move");
	
		$listaOrdenable.find("a.elimina").unbind("click").bind("click", function(){
	        var itemLista = jQuery(this).parents("li:first");
	
			that.eliminaItem(itemLista);  // se elimina de la lista del nodoDestino
	        itemLista.remove(); // se elimina de la lista del NodoOrigen
	
			that.contaSeleccionats();
		});
		
		// Ir a la edicion del procedimiento al hacer click sobre el.
		$listaOrdenable.find('div.procediment').each(function() {
	        $(this).unbind("click").bind("click", function() {
	        	var url = pagDetallProcediment + "?itemId=" + $(this).find("input.procediment_idRelatedItem").val();
	            document.location = url;
	        });
	    });
		
		if (debug)
			console.log("Saliendo de CModulProcediment.prepararListaProcedimientos");
		
	};
	
	// Devuelve un string con el formato procediments=n1,n2,...,nm donde n son codigos de procediments.
	this.listaProcediments = function () {
		
		if (debug)
			console.log("Entrando en CModulProcediment.listaProcediments");
		
		var listaProcediments = "procediments=";
		
		modul_procediments_elm.find("div.listaOrdenable input.procediment_id").each(function() {
			listaProcediments += jQuery(this).val() + ",";
		});
		
		if (listaProcediments[listaProcediments.length-1] == ","){
			listaProcediments = listaProcediments.slice(0, -1);
		}
		
		if (debug)
			console.log("Saliendo de CModulProcediment.listaProcediments");
		
		return listaProcediments;
		
	};
	
	// Al añadir un procedimiento, al ser nuevo se le pone un id negativo. Esta variable se va decrementando cada vez que se anade uno.
	this.ultimoIdNuevo = 0;
	
	// Devuelve true si ya hay un procedimiento en la lista con el id "procedimientoId".
	this.contieneProcedimiento = function (procedimientoId) {
		
		if (debug)
			console.log("Entrando en CModulProcediment.contieneProcedimiento");
		
		var existe = false;
		var config = that.getConfiguracion();
		
		jQuery(config.nodoDestino).find("input.procediment_idRelatedItem").each(function() {
			if (jQuery(this).val() == procedimientoId) {
				existe = true;
			}			
		});
		
		if (debug)
			console.log("Saliendo de CModulProcediment.contieneProcedimiento");
		
		return existe;
		
	};
	
};

function CEscriptoriProcediment() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListadoBase;
	this.extend("opcions_procediment", "resultats_procediments", "cercador_procediments", "cercador_procediments_contingut", "", "", "", "btnBuscarForm_procediment", "btnLimpiarForm_procediment");
	
	var that = this;
	
	this.nuevo = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.nuevo");
		
		that.limpia();
		resultats_procediment_elm.find('div.dades').empty();
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.nuevo");
		
	};
	
	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo, procedimientoID, idMainItem ) {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.agregaItem");
		
		if (!ModulProcediment.contieneProcedimiento(procedimientoID) ) {
			
			// Componemos el item para enviar a la lista.
			var item = {
				id: itemID,
				nombre: titulo,
				idRelatedItem: procedimientoID,	// Campo necesario para guardado AJAX genérico de módulos laterales.
				idMainItem: idMainItem			// Campo necesario para guardado AJAX genérico de módulos laterales.
			};
						
			// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
			if ( ModulProcediment.agregaItem(item) ) {		
				this.contaSeleccionats();		
			}
			
		}
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.agregaItem");
		
	};
	
	// Cambia de página.
	this.cambiaPagina = function(pag) {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.cambiaPagina");
		
		multipagina_procediment.setPaginaActual(pag - 1);
		pag_Pag = pag;
		this.anar(pag);
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.cambiaPagina");
		
	};
	
	this.finCargaListado = function(opcions, data) {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.finCargaListado");
		
		// total
		resultats_total = parseInt(data.total, 10);
		
		if (resultats_total > 0) {
			
			txtT = (resultats_total > 1) ? txtProcediments : txtProcediment;
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}
			
			resultatInici = ((pag_Pag*pag_Res)+1);
			resultatFinal = ((pag_Pag*pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag*pag_Res) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "id") ? " " + ordre_T : "";            
            ordre_c2 = (ordre_C == "familia.id") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "fechaActualizacion") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "id") {
					txt_per = txtProcediment;
				} else if (ordre_C == "familia.id") {					
                    txt_per = txtFamilia;
				} else {
					txt_per = txtFechaActualizacion;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
			
			codi_cap1 = "<div class=\"th procediment "+ ordre_c1 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"id\">" + txtProcediment + "</a></div>";
            codi_cap2 = "<div class=\"th familia "+ ordre_c2 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"familia.id\">" + txtFamilia + "</a></div>";
			codi_cap3 = "<div class=\"th fechaActualizacion "+ ordre_c3 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"fechaActualizacion\">" + txtFechaActualizacion + "</a></div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat procediments\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(0,pag_Res).each(function(i) {
				dada_node = this;
				
				parClass = (i%2) ? " par": "";
				caducat_nom_class = (dada_node.caducat) ? " procediment" : " procedimentCaducat";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td " + caducat_nom_class + "\" role=\"gridcell\">";

				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
                codi_taula += '<span class="id">'+ dada_node.id +'</span>';
				codi_taula += "<a id=\"procediment_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = (dada_node.caducat) ? " caducat" : "";
				//codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.publicacio) + "</div>";
                codi_taula += '<div class="td familia">' + printStringFromNull(dada_node.familia) + '</div>';
                
				//codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.caducitat) + "</div>";
                codi_taula += "<div class=\"td fechaActualizacion" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaActualizacion) + "</div>";
				
				codi_taula += "</div>";                
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if ($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Actualizamos el navegador multip�gina.
			multipagina_procediment.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "EscriptoriProcediment.cambiaPagina"
			});
			
			codi_navegacio = multipagina_procediment.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaProcediments + ".</p>";
			
		}
		
		// animacio
		procediments_dades_elm.fadeOut(300, function() {
			// pintem
			procediments_dades_elm.html(codi_final).fadeIn(300, function() {
				// Evento lanzado al hacer click en un elemento de la lista.
                resultats_procediment_elm.find(".llistat .tbody a").unbind("click").bind("click", function() {
                	
					var partesItem = jQuery(this).attr("id").split("_");
					var itemID = --ModulProcediment.ultimoIdNuevo;
					var procedimientoID = partesItem[1];
					var titulo = jQuery(this).html();
					var idMainItem = $('#item_id').val();
					
					that.agregaItem(itemID, titulo, procedimientoID, idMainItem);
					
				});
                				
				// cercador
				procediments_cercador_elm.find("input, select").removeAttr("disabled");
				
			});
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.finCargaListado");
		
	};

	this.carregar = function(opcions) {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.carregar");
		
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		
		Buscador = new BuscadorProcedimiento();
		Buscador.orden.tipo = ordreTipus_procediment_elm.val();
		Buscador.orden.campo = ordreCamp_procediment_elm.val();
		Buscador.buscar(opcions, pagLlistatProcediments, EscriptoriProcediment);
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.carregar");
		
	};
	
	this.finalizar = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.finalizar");
								
		nombre_llistat = ModulProcediment.finalizar(true);
		
		codi_procediments_txt = (nombre_llistat == 1) ? txtProcediment : txtProcediments;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaProcediments + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_procediments_txt.toLowerCase() + "</strong>.";
		
		modul_procediments_elm.find("p.info").html(codi_info);
		
		if (nombre_llistat > 1) {			
			modul_procediments_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulProcediment.calculaOrden(ui, "origen");
					EscriptoriProcediment.contaSeleccionats();
				}
			}).css({cursor:"move"});
		}

		ModulProcediment.prepararListaProcedimientos(modul_procediments_elm.find(".listaOrdenable"));
		
		this.torna();
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.finalizar");
		
	};
	
	// Método sobreescrito
	this.anar = function(enlace_html) {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.anar");
				
		num = parseInt(enlace_html,10);
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantAnteriors : txtCercantItemsSeguents;
		procediments_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			procediments_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_procediment_elm.val(num-1);								
				that.carregar({pagina: num-1});				
			});
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.anar");
		
	};
	
	this.torna = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.torna");
		
		// animacio
		escriptori_procediments_elm.fadeOut(300, function() {		
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_procediments_elm.find("a.gestiona").one("click", function(){
					ModulProcediment.gestiona();
					that.limpia();
					jQuery('#cerca_fechaCaducidad, #cerca_fechaPublicacion, #cerca_fechaPublicacion, #cerca_fechaActualizacion').val('');
				});
			});
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.torna");
		
	};
	
	this.contaSeleccionats = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriProcediment.contaSeleccionats");
		
		seleccionats_val = procediments_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = procediments_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			procediments_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaProcedimentsSeleccionats+ ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtProcediment.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtProcediments.toLowerCase() + "</strong>.");						
			procediments_seleccionats_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulProcediment.calculaOrden(ui,"origen");
					EscriptoriProcediment.contaSeleccionats();
				}
			}).css({cursor:"move"});
			
		}
		
		procediments_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			ModulProcediment.eliminaItem(itemLista);
			EscriptoriProcediment.contaSeleccionats();
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriProcediment.contaSeleccionats");
		
	};
	
};