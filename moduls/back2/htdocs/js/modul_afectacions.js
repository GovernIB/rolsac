// MODUL NORMATIVA

$(document).ready(function() {
	
	// elements
	modul_afectacions_elm = $("div.modulAfectacions:first");
	escriptori_afectacions_elm = $("#escriptori_afectacions");
	resultats_afectacions_elm = jQuery("#resultats_afectacions");
	
	ModulAfectacions = new CModulAfectacions();
	EscriptoriAfectacions = new CEscriptoriAfectacions();
	
	multipagina_afec = new Multipagina();
	
	if (modul_afectacions_elm.size() == 1) {
		ModulAfectacions.iniciar();	
	}
		
	// Evento para el botón de volver al detalle
	jQuery("#btnVolverDetalle_afectacions").bind("click", function() { EscriptoriAfectacions.torna(); });	
	jQuery("#btnFinalizar_afectacions").bind("click", function() { EscriptoriAfectacions.finalizar(); });
	
});

function CModulAfectacions() {
	
	this.extend = ListaOrdenable;
	this.extend();
	
	var that = this;
        
    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = modul_afectacions_elm.find('input[name="modulo_afectaciones_modificado"]');
	
	this.iniciar = function() {
		
		jQuery("#afec_cerca_data").datepicker({ dateFormat: 'dd/mm/yy' });
		jQuery("#afec_cerca_data_butlleti").datepicker({ dateFormat: 'dd/mm/yy' });		
		
		afectacions_llistat_elm = escriptori_afectacions_elm.find("div.escriptori_items_llistat:first");
		afectacions_cercador_elm = escriptori_afectacions_elm.find("div.escriptori_items_cercador:first");
		afectacions_seleccionats_elm = escriptori_afectacions_elm.find("div.escriptori_items_seleccionats:first");
		
		afectacions_dades_elm = afectacions_llistat_elm.find("div.dades:first");
		
		pagPagina_afec_elm = afectacions_llistat_elm.find("input.pagPagina:first");
		ordreTipus_afec_elm = afectacions_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_afec_elm = afectacions_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_afectacions_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
		});
		
		afectacions_llistat_elm.add(afectacions_seleccionats_elm);
		
		// one al botó de gestionar
		modul_afectacions_elm.find("a.gestiona").one("click", function() {ModulAfectacions.gestiona();});
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "afectacions",
			nodoOrigen: modul_afectacions_elm.find(".listaOrdenable"),
			nodoDestino: afectacions_seleccionats_elm.find(".listaOrdenable"),
			atributos: [			// Campos que queremos que aparezcan en los elementos de las lista ordenable.
    			"afectacioId", 
    			"normaId", 
    			"afectacioNom", 
    			"normaNom", 
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem"		// Campo necesario para guardado AJAX genérico de módulos laterales.
            ],
			multilang: false
		});
		
		// one al botó de gestionar
		modul_afectacions_elm.find("a.gestiona").one("click", function(){ModulAfectacions.gestiona();} );		
		
		//Sobreescribimos los métodos de ListaOrdenable para este caso particular
		this.getHtmlItem = this.getHtmlItemPropio;		
		this.copiaFinal = this.copiaFinalPropia;
		this.copiaInicial = this.copiaInicialPropia;
		this.eliminaItem = this.eliminaItemPropio;
		
	};
	
	/**
	 * Obtiene el html de un item de la lista.
	 */
	this.getHtmlItemPropio = function( item, btnEliminar, idioma ) {
		
		var sufijoIdioma = "";
		var idiomaAtributo = "";
		var partesAtributo;
		
		if ( idioma ) {
			sufijoIdioma += "_" + idioma;
		}
			
		// item => org.ibit.rol.sac.model.dto.AfectacionDTO pasado vía Spring.
		var html = "<li element-id='" + item.normaId + "' main-item-id='" + item.idMainItem + "' related-item-id='" + item.idRelatedItem + "'>";
			html += '<div class="afectacio">';
			
			html += "<input type=\"hidden\" value=\"" + item.afectacioId + "\" class=\"afectacio\" />";
			html += "<input type=\"hidden\" value=\"" + item.normaId + "\" class=\"norma\" />";
			html += item.afectacioNom + ", " + txtAmbLaNorma + " <em>" + item.normaNom + "</em>";
			
			if ( btnEliminar ) {
				html += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
			}
			
			html += "</div>";
			html += "</li>";
		
		return html;
		
	};
		
    this.modificado = function() {
        $moduloModificado.val(1);
    };
        
	this.nuevo = function() {       
		afec_seleccionats_elm = escriptori_detall_elm.find("div.modulAfectacions div.seleccionats");
		afec_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaAfectacions + ".");
	};
		
	this.gestiona = function() {
		
		lis_size = modul_afectacions_elm.find("li").size();
		
		if (lis_size > 0) {
			this.copiaInicial();
			EscriptoriAfectacions.contaSeleccionats();
		} else {
			
			afectacions_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaAfectacionsSeleccionats + ".");			
			afectacions_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_afectacions_elm.fadeIn(300);			
		});
		
	};
	
	/**
	 * Copia los datos de la lista origen a la de destino.
	 */
	this.copiaInicialPropia = function() {		

		var i;
		var html;
		var idioma;
		var clases;

		html = "<ul>";
					
		modul_afectacions_elm.find(".listaOrdenable").find("li").each(function() {
			
			li_elm = $(this);
			
			html += "<li element-id='" + li_elm.find("input.norma").val() + "' main-item-id='" + $('#item_clave_primaria').val() + "' related-item-id='" + li_elm.find("input.norma").val() + "'>";
			html += "<div class=\"afectacio\">";
			html += "<input type=\"hidden\" value=\"" + li_elm.find("input.norma").val() + "\" class=\"norma\" />";
			html += "<span class=\"afectacio\">";
			html += "<select>";
			afectacio_ID = li_elm.find("input.afectacio").val();
			$(Afectacions_arr).each(function(i) {
				codi_selected = (this.id == afectacio_ID) ? "selected=\"selected\"" : "";
				html += "<option value=\"" + this.id + "\" " + codi_selected + ">" + this.nom + "</option>";
			});
			html += "</select>";
			html += "<br />";
			html += ", " + txtAmbLaNorma + " <em>" + li_elm.find("em").text() + "</em>";
			html += "</span>";
			html += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
			html += "</div>";
			html += "</li>";
		
		});
		
		html += "</ul>";
											
		afectacions_seleccionats_elm.find(".listaOrdenable").html(html);
		
	};
	
	/**
	 * Copia los datos de la lista destino a la de origen.
	 */
	this.copiaFinalPropia = function() {
		
		var html = "";
		var numItems = 0;
		
		html = "<ul>";
				
		afectacions_seleccionats_elm.find("li").each(function(i) {
		
			li_elm = $(this);
			afectacio_select = li_elm.find("select");
			afectacio_select_id = afectacio_select.val();
			afectacio_select_nom = afectacio_select.find("option:selected").text();
						
			html += "<li element-id='" + li_elm.find("input.norma").val() + "' main-item-id='" + $('#item_clave_primaria').val() + "' related-item-id='" + li_elm.find("input.norma").val() + "'>";
			html += "<input type=\"hidden\" value=\"" + afectacio_select_id + "\" class=\"afectacio\" />";
			html += "<input type=\"hidden\" value=\"" + li_elm.find("input.norma").val() + "\" class=\"norma\" />";
			html += afectacio_select_nom + ", " + txtAmbLaNorma + " <em>" + li_elm.find("em").text() + "</em>";
			html += "</li>";
			
			numItems++;
			
		});
		
		html += "</ul>";

		if (numItems > 0)
			modul_afectacions_elm.find(".listaOrdenable").html(html);
		else
			modul_afectacions_elm.find(".listaOrdenable").html("");
		
		return numItems;
		
	};
	
	/**
	 * Elimina un item de la lista.
	 */ 
	this.eliminaItemPropio = function( item ) {		
		item.remove();
	};
	
	this.contaSeleccionats = function() {
		
		seleccionats_val = modul_afectacions_elm.find(".seleccionat").find("li").size();
		info_elm = modul_afectacions_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaAfectacionsSeleccionats + ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtAfectacio.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtAfectacions.toLowerCase() + "</strong>.");						
		}
		
	};
	
	this.inicializarAfectacions = function(listaAfectacions) {
    
        $moduloModificado.val(0);
        
		modul_afectacions_elm.find(".listaOrdenable").empty();		
		
		if (typeof listaAfectacions != 'undefined' && listaAfectacions != null && listaAfectacions.length > 0) {
			that.agregaItems(listaAfectacions);
		}
		
		that.contaSeleccionats();
		
	};
	
	this.jsonAfectacions = function() {
		
		//Construir el JSON que se devuelve en el guarda_upload de normativa.js
		
		var listaAfectaciones = "{\"listaAfectaciones\" : [";
		var sep = "";
		
		$("div.modulAfectacions").find("li").each(function() {
			var li_elm = $(this);
			var idNormaAfectada = li_elm.find("input.norma").val();
			var idTipoAfectacion = li_elm.find("input.afectacio").val();
					
			listaAfectaciones += sep + "{ \"afectacioId\" : " + idTipoAfectacion + ", \"normaId\" : " + idNormaAfectada+ ", \"normaNom\" : \"\", \"afectacioNom\" : \"\" } ";
			sep = ",";
		});
		
		listaAfectaciones += "]}";		
		
		return listaAfectaciones;
		
	};
		
};

function CEscriptoriAfectacions() {
	
	this.extend = ListadoBase;
	this.extend("opcions_afectacions", "resultats_afectacions", "cercador_afectacions", "cercador_afectacions_contingut", "", "", "", "btnBuscarForm_afectacions", "btnLimpiarForm_afectacions");
	
	var that = this;
	
	// Sobreescribimos la función porque la genérica no funciona con
	// componentes de fecha y hay que limpiarlos a mano
	this._limpia = this.limpia;	
	this.limpia = function() {
			
		this._limpia();
		$("#afec_cerca_data").val("");
		$("#afec_cerca_data_butlleti").val("");
		
	};
	
	this.nuevo = function() {
		this.limpiarTodo();
		resultats_afectacions_elm.find('div.dades').empty();
	};
		
	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function(itemID, titulo) {
		
		// En este caso, itemID es la PK de la normativa que se va a asociar al registro principal en modo de afectación.
		// Corresponde al valor de la columna AFE_CODNOA de la tabla RSC_AFECTA.
		
		list_size = afectacions_seleccionats_elm.find("li").size();
		norma_esta = false;
		
		if (list_size == 0) {			
			$("<ul>").appendTo(afectacions_seleccionats_elm.find(".listaOrdenable"));			
		} else {			
			afectacions_seleccionats_elm.find("input.norma").each(function() {				
				if ($(this).val() == itemID) {
					norma_esta = true;
				}			
			});			
		}
		
		if (!norma_esta) {
						
			codi_seleccionat = "<li element-id='" + itemID + "' main-item-id='" + $('#item_clave_primaria').val() + "' related-item-id='" + itemID + "'>";
			codi_seleccionat += "<div class=\"afectacio\">";
			codi_seleccionat += "<input type=\"hidden\" value=\"" + itemID + "\" class=\"norma\" />";
			codi_seleccionat += "<span class=\"afectacio\">";
			codi_seleccionat += "<select>";
			
			$(Afectacions_arr).each(function(i) {
				codi_selected = (i == 0) ? "selected=\"selected\"" : "";
				codi_seleccionat += "<option value=\"" + this.id + "\" " + codi_selected + ">" + this.nom + "</option>";
			});
			
			codi_seleccionat += "</select>";
			codi_seleccionat += "<br />";
			codi_seleccionat += ", " + txtAmbLaNorma + " <em>" + titulo + "</em>";
			codi_seleccionat += "</span>";
			codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"><span><span>" + txtElimina + "</span></span></a>";
			codi_seleccionat += "</div>";
			codi_seleccionat += "</li>";
			
			afectacions_seleccionats_elm.find("ul").append(codi_seleccionat);
			
			this.contaSeleccionats();
			
		}
		
	};
	
	// Cambia de página.
	this.cambiaPagina = function( pag ) {
		multipagina_afec.setPaginaActual(pag - 1);
		pag_Pag = pag;
		this.anar(pag);
	};
	
	this.finCargaListado = function(data, opcions) {
		// total
		resultats_total = parseInt(data.total, 10);
		
		if (resultats_total > 0) {
			
			txtT = (resultats_total > 1) ? txtNormatives : txtNormativa;
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}							
			
			resultatInici = 1;// ((pag_Pag*pag_Res)+1);
			resultatFinal = 10;// ((pag_Pag*pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag*pag_Res) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "titol") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "data") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "data_butlleti") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "titol") {
					txt_per = txtNormativa;
				} else if (ordre_C == "data") {
					txt_per = txtData;
				} else {
					txt_per = txtDataButlleti;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + " " + txtDeLa + " " + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
			
			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\">" + txtNormativa + "</div>";
			codi_cap2 = "<div class=\"th data" + ordre_c2 + "\" role=\"columnheader\">" + txtData + "</div>";
			codi_cap3 = "<div class=\"th data_butlleti" + ordre_c3 + "\" role=\"columnheader\">" + txtDataButlleti + "</div>";						
			
			// codi taula
			codi_taula = "<div class=\"table llistat normativa\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				
				dada_node = this;
				parClass = (i % 2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td titol\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />" + dada_node.id;
				codi_taula += "<a href=\"javascript:;\" class=\"titol\">" + dada_node.titulo + "</a>";
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td data\" role=\"gridcell\">" + dada_node.fecha + "</div>";
				codi_taula += "<div class=\"td data_butlleti\" role=\"gridcell\">" + dada_node.fecha_boletin + "</div>";
				
				codi_taula += "</div>";
				
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if ($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Instanciamos el navegador multipágina.					
			multipagina_afec.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "EscriptoriAfectacions.cambiaPagina"
			});					
			
			codi_navegacio = multipagina_afec.getHtml();						
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaNormatives + ".</p>";
			
		}
		
		// animacio
		afectacions_dades_elm.fadeOut(300, function() {
			// pintem
			afectacions_dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Evento lanzado al hacer click en un elemento de la lista.
				//jQuery("#resultats .llistat .tbody a").unbind("click").bind("click",function(){
                resultats_afectacions_elm.find(".llistat .tbody a").unbind("click").bind("click", function() {
                	
                	var elem = $(this);
                	norma_id = elem.parent().find("input.id").val();
                	norma_titol = elem.html();
                	that.agregaItem(norma_id, norma_titol);
                	
				});
				
				// cercador
				afectacions_cercador_elm.find("input, select").removeAttr("disabled");
				
			});
			
		});
			
	};
	
	this.carregar = function(opcions) {
		
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		
		dataVars = "";
				
		// cercador
		dataVars_cercador = "";		
		dataVars_cercador += "&data=" + $("#afec_cerca_data").val();			
		dataVars_cercador += "&titol=" + $("#afec_cerca_normativa_titol").val();
		dataVars_cercador += "&data_butlleti=" + $("#afec_cerca_data_butlleti").val();			
		
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_afec_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_afec_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_afec_elm.val(),10);		
			
		// ordre
		ordre_Tipus = ordreTipus_afec_elm.val();
		ordre_Camp = ordreCamp_afec_elm.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagNormativa,
			//url: pagNormativaAfectades,
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
				that.finCargaListado(data, opcions);
			}
		});
	
	};
	
	this.finalizar = function() {		
		
		nombre_llistat = ModulAfectacions.finalizar();
		
		codi_afectacions_txt = (nombre_llistat == 1) ? txtAfectacio : txtAfectacions;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaAfectacions + "." : txtHiha + " <strong>" + nombre_llistat + " " + codi_afectacions_txt.toLowerCase() + "</strong>.";
		
		modul_afectacions_elm.find("p.info").html(codi_info);
		
        // Marcamos el módulo como modificado.
        ModulAfectacions.modificado();
		
		this.torna();
		
	};

	this.anar = function(enlace_html) {
		
		num = parseInt(enlace_html,10);
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantAnteriors : txtCercantItemsSeguents;
		afectacions_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			afectacions_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_afec_elm.val(num-1);
				that.carregar({pagina: num-1});
			});
		});
		
	};
	
	this.torna = function() {
		
		// animacio
		escriptori_afectacions_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_afectacions_elm.find("a.gestiona").one("click", function() { ModulAfectacions.gestiona(); });
			});
			
		});
		
	};
	
	this.contaSeleccionats = function() {
		
		seleccionats_val = afectacions_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = afectacions_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			afectacions_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaAfectacionsSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtAfectacio.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtAfectacions.toLowerCase() + "</strong>.");
			//afectacions_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(..img/cursor/grabbing.cur), move' }).find("li").css("cursor","url(..img/cursor/grab.cur), move");
			
		}
		
		afectacions_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {				
			var itemLista = jQuery(this).parents("li:first");
			ModulAfectacions.eliminaItem(itemLista);
			EscriptoriAfectacions.contaSeleccionats();
		});
		
	};
	
};

/**
 * (amartin) Explicación de extensión de clase:
 * 
 * Necesitamos extender la clase ListaSimple ya que el módulo lateral de afectaciones (modul_afectacions.js)
 * no sólo consta de valor para el registro principal (normativa => main-item-id) y para sus N normativas
 * afectadas (afectación => related-item-id), sino que también hay un tercer campo, que es el tipo de afcetación
 * entre la normativa que afecta y la afectada. Con la extensión de la clase sobreescribimos los métodos para
 * realizar el guardado y para obtener el dato adicional de tipo de afectación.
 */
function CListaSimpleAfectaciones() {
	
	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaSimple;
	this.extend();
	
	var that = this;
	
	this._getFilters = this.getFilters;
	
	this.getFilters = function(elements, id) {
		
		if (debug)
			console.log("Entrando en CListaSimpleAfectaciones.getFilters");
		
		var lista = new Array();
		var filters = this._getFilters(elements, id);
		
		if (elements.length > 0) {
			
			elements.each(function() {
				
				var value = $(this).find('input.afectacio').val(); // Obtenemos el ID del tipo de afectación.
				lista.push(value);
				
			});
			
			filters += "&tiposAfectacion=" + lista;
			
		}
				
		if (debug)
			console.log("Saliendo de CListaSimpleAfectaciones.getFilters");
		
		return filters;
				
	};
	
};
