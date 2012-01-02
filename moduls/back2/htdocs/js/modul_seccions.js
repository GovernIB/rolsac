// MODUL SECCIONS
$(document).ready(function() {
		
	// elements
	modul_seccions_elm = $("div.modulSeccions");
	escriptori_seccions_elm = $("#escriptori_seccions");

	EscriptoriSeccio = new CEscriptoriSeccio();
	Llistat = EscriptoriSeccio;
	
	multipagina = new Multipagina();
	
	// Evento para el botón de volver al detalle
	jQuery(".btnVolverDetalleSecciones").bind("click",function(){EscriptoriSeccio.torna();});	
	jQuery("#btnFinalizarSecciones").bind("click",function(){EscriptoriSeccio.finalizar();});	
	
});

function CModulSeccio() {

	var that = this;
	var params; 
	var seccions_llistat_seccions;
	
	//Necessitem una còpia de la llista original de seccions-fitxes. Es necessari perquè l'usuari podria
	//esborrar una secció amb les seves fitxes i tornar a afegir-la des de els resultats de la cerca de 
	//seccions si troba que s'ha equivocat.
	var copiaNodesOrigen;
	
	if ( modul_seccions_elm.size() != 1)
		return 0;
	
	this.extend = ListaOrdenable;
	this.extend();
	
	this.mostraFitxes = function(e)  {
		
		//Mostrar panel de fichas de la sección actual
		divFichas = $(e).next();
		
		if ($(divFichas).is(":visible"))
			divFichas.fadeOut(200);
		else
			divFichas.fadeIn(200);
		
		return false;
	},

	this.iniciar = function(dades) {
		
		seccions_nodes = dades;		
		seccions_nodes_size = dades.length;
		
		seccions_llistat_elm = escriptori_seccions_elm.find("div.escriptori_items_llistat:first");
		
		seccions_cercador_elm = escriptori_seccions_elm.find("div.escriptori_items_cercador:first");
		seccions_seleccionats_elm = escriptori_seccions_elm.find("div.escriptori_items_seleccionats:first");
		
		seccions_dades_elm = seccions_llistat_elm.find("div.dades:first");
		
		pagPagina_seccio_elm = seccions_llistat_elm.find("input.pagPagina:first");
		ordreTipus_seccio_elm = seccions_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_seccio_elm = seccions_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_seccions_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
			if (botonera_elm.hasClass("dalt")) {
				botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			} else {
				botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			}
		});
				
		seccions_cercador_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
				
		// enllaços
		modul_seccions_elm.bind("click", CModulSeccio.cerca);
		
		if (seccions_nodes_size > 0) {
			
			codi_seccions = "<ul>";
			
			$(seccions_nodes).each( function() {
				seccio_node = this;
				
				texteFitxes = "(0 fitxes)";
				llistaFitxes = seccio_node.listaFichas;
				
				if (llistaFitxes != null)
					texteFitxes = " (" + llistaFitxes.length  + " " + ( llistaFitxes.length > 1 ? txtFitxes : txtFitxa ) + ")";
				
				// crearem una llista ordenable per a cada enllaç de secció, que contindrà les fitxes que té assignades
				codi_seccions += "<li class=\"nodoListaSecciones\"><input class=\"seccio_id\" id=\"seccio_id_" + seccio_node.id + "\"  type=\"hidden\" value=\"" + seccio_node.id + "\" /><a class=\"enllasGestioFitxa seccio_nombre\" href=\"#\">" + seccio_node.nom + "</a>" + texteFitxes;
				codi_seccions += "<div class=\"contenedorFichas\" style=\"margin-top: 10px; display:none;\">";
				codi_seccions += "<div class=\"listaOrdenable\">";
				codi_seccions += "<ul>";
				
				if ( llistaFitxes != null && llistaFitxes.length > 0 ) {
	
					for ( n = 0; n < llistaFitxes.length; n++ ) {
						codi_seccions += "<li>";
						codi_seccions += "<input type=\"hidden\" value=\"" + llistaFitxes[n].id + "\"/>" + llistaFitxes[n].titulo;
						codi_seccions += "</li>"
					}
										
				} else {
					codi_seccions += txtNoHiHaFitxes;
				}
				
				codi_seccions += "</ul>";
				codi_seccions += "</div>";
				
                codi_seccions += "<div class=\"btnGenerico\" style=\"float:none; width:145px;\" >";
                codi_seccions += "<a class=\"btn gestiona\" href=\"javascript:;\"><span><span>" + txtGestioFitxes + "</span></span></a>";
                codi_seccions += "</div>";
                
                codi_seccions += "</div>";
				codi_seccions += "</li>";
			});
			
			codi_seccions += "</ul>";
			
			txt_seccions = (seccions_nodes.size == 1) ? txtSeccio : txtSeccions;
			
			seccions_llistat_seccions = $("div.modulSeccions").find("div.seleccionats");		 
			seccions_llistat_seccions.find("p.info").html( txtHiHa + "<strong> " + seccions_nodes_size + " " + txt_seccions + ""  + "</strong>");			
			seccions_llistat_seccions.find(".listaOrdenable").html(codi_seccions);

			this.activaEnllasosFitxes();
		}
			
		copiaNodesOrigen = modul_seccions_elm.find(".listaOrdenable:first").html();
		params = {
				nombre: "seccio",
				nodoOrigen: modul_seccions_elm.find(".listaOrdenable:first"),
				nodoDestino: seccions_seleccionats_elm.find(".listaOrdenable"),
				atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
				//multilang: true
				multilang: false
		} 
		
		// Configuramos la lista ordenable  
		this.configurar( params );
		 
		modul_seccions_elm.find("a.gestiona").one("click", function(){ModulSeccions.gestiona();} );
		
	},
			
	this.gestiona = function() {
		
		lis_size = modul_seccions_elm.find(".nodoListaSecciones").size();
		
		if (lis_size > 0) {
		
			this.copiaInicial();
															
			EscriptoriSeccio.contaSeleccionats();
			
		} else {
			
			seccions_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaSeccionsSeleccionades + ".");			
			seccions_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_seccions_elm.fadeIn(300);			
		});
	}
	
	this.cerca = function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			seccio_ID = elm.find("input").val();
			
			// select cercador
			codi_select_seccions = "<select id=\"cerca_fixta_seccio\" name=\"cerca_fixta_seccio\">";
			modul_seccions_elm.find("a").each(function() {
				a_node = $(this);
				a_node_val = a_node.find("input").val();
				codi_selected = (a_node_val == seccio_ID) ? " selected=\"selected\"" : "";
				codi_select_seccions += "<option value=\"" + a_node_val + "\"" + codi_selected + ">" + a_node.text() + "</value>";
			});
			codi_select_seccions += "</select>";
			
			seccions_llistat_elm.find("label.cerca_fixta_seccio").attr("label","cerca_fixta_seccio").parents("div.element:first").find("div.control:first").html(codi_select_seccions);
			
			// animacio
			escriptori_detall_elm.fadeOut(300, function() {
				
				seccions_cercador_elm.find("input, select").attr("disabled", "disabled");
				
				escriptori_seccions_elm.fadeIn(300, function() {
					
					codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
					seccions_dades_elm.html(codi_cercant);
					
					//EscriptoriSeccio.carregar({});
					
					// activar
					//escriptori_seccions_elm.bind("click",EscriptoriSeccio.llansar);
					
				});
			});
		}
	}
	
	/**
	 * Copia les dades de la llista origen a la de destinació (mètode sobre-escrit).  
	 * En aquest cas hem de copiar només la llista de seccions sense tenir en compte les 
	 * seves fitxes (el mètode per defecte inclou ambues en fer ".find('li')" ).
	 */
	this.copiaInicial = function() {		
		var i;
		var html;
		var idioma;
		var clases;
		
		var _this = this;
		
		html = "<ul>";
	
		jQuery(params.nodoOrigen).find(".nodoListaSecciones").each(function() {			
			
			var li_elm = jQuery(this);			
			var item = [];
			var atributo;
					
			for( i = 0; i < params.atributos.length; i++ ){
				
				atributo = params.atributos[i];
				
				if ( atributo != "nombre" ) 						
					item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
				else 
					item[atributo] = li_elm.find("a." + params.nombre + "_" + atributo ).html();					
			}
						
			html += _this.getHtmlItem( item, true );
		});
		
		html += "</ul>";
		
		jQuery(params.nodoDestino).html(html);
	}
	
	/**
	 * Sobre-escrivim també el mètode de finalització i de còpia final perquè hem de 
	 * tenir en compte els nodes fills (fitxes) en la llista de destinació.
	 */	
	this.copiaFinal = function() {
		
		html = "<ul>";
		
		numSecciones = $(".escriptori_items_seleccionats .listaOrdenable ul").find("li").length;
		
		//Per a cada node comprovam si ha estat actualitzat i l'eliminarem o afegirem 
		//a la nova llista, des de copiaNodesOrigen, segons correspongui.		
		$(params.nodoDestino).find("li").each( function(i) {

			tmpSeccio = $(copiaNodesOrigen).find( "#seccio_id_" + $(this).find(".seccio_id").val() ).parent();
			
			//Si es troba el node, l'afegim a la nova llista
			//juntament amb les seves fitxes filles. Si no el trobem
			//es crearà un de nou
			if ( tmpSeccio.length != 0 ) 
				html += "<li class=\"nodoListaSecciones\">" +  tmpSeccio.html() + "</li>";
			else 				
				html += "<li class=\"nodoListaSecciones\"><a class=\"enllasGestioFitxa seccio_nombre\">" + 
						$(this).find("div.seccio span").html() + 
						"</a></li>";
		});
		
		html += "</ul>";
		$(params.nodoOrigen).html(html);
					
		if ( seccions_llistat_seccions != undefined ) {
			seccions_llistat_seccions.find("p.info").html( txtHiHa + "<strong> " + numSecciones + " " + txt_seccions + ""  + "</strong>");					
			this.activaEnllasosFitxes();
		}
		
		return numSecciones;
	}
	
	this.finalizar = function(){		
		return this.copiaFinal();		
	}	
	
	this.activaEnllasosFitxes = function() {
		$(".enllasGestioFitxa").each( function() {
			$(this).bind("click", function() {					
				return that.mostraFitxes( this ); 
			});
		});									
	};
	
};

function CEscriptoriSeccio() {
	
	this.extend = ListadoBase;
	this.extend();
	
	var that = this;
	
	this.agregarItem = function( itemID, titulo ) {
		
		var item = {
			id: itemID,
			nombre: titulo
		};
			
		// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
		if( ModulSeccio.agregaItem( item ) ) {		
			this.contaSeleccionats();		
		}		
	}			
	
	this.contaSeleccionats = function() {
		
		seleccionats_val = seccions_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = seccions_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			seccions_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaSeccionsSeleccionades + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtSeccio.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtSeccions.toLowerCase() + "</strong>.");						
			seccions_seleccionats_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulSeccions.calculaOrden(ui,"origen");
					EscriptoriSeccio.contaSeleccionats();
				}
			}).css({cursor:"move"});
			
		}
		
		seccions_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			ModulSeccions.eliminaItem(itemLista);
			EscriptoriSeccio.contaSeleccionats();
		});
	}
	
	this.finCargaListado = function(data, opcions) {

		// total
		resultats_total = parseInt(data.total, 10);		
		
		if (resultats_total > 0) {
			
			txtT = (resultats_total > 1) ? txtSeccions : txtSeccio;
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}
			
			resultatInici = (pag_Pag * pag_Res) + 1;
			resultatFinal = ( ( pag_Pag * pag_Res) + pag_Res > resultats_total ) ? resultats_total : ( pag_Pag * pag_Res ) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "seccio") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if ( resultats_total > 1 ) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				txt_ordenacio += ", " + txt_ordenats + " " + txtSeccio + " <em>" + txtSeccio + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtSeccio + "</a></div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat seccions\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a class=\"seccion_" + dada_node.id + "\" href=\"javascript:;\" class=\"nom\">" + dada_node.nom + "</a>";
				codi_taula += "</div>";
				codi_taula += "<div class=\"td cp\" role=\"gridcell\">" + dada_node.codigoPostal + "</div>";
				codi_taula += "<div class=\"td poblacio\" role=\"gridcell\">" + dada_node.poblacion + "</div>";
				codi_taula += "</div>";
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Actualizamos el navegador multipágina.
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "EscriptoriSeccio.cambiaPagina",
			});
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaSeccions + ".</p>";
			
		}
		
		// animacio
		seccions_dades_elm.fadeOut(300, function() {
			// pintem
			seccions_dades_elm.html(codi_final).fadeIn(300, function() {
														
				// Evento lanzado al hacer click en un elemento de la lista.
				jQuery("#resultats .llistat .tbody a").unbind("click").bind("click",function(){
					var partesItem = jQuery(this).attr("class").split("_");
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					that.agregaItem(itemID,titulo);
					});
				
				// cercador
				seccions_cercador_elm.find("input, select").removeAttr("disabled");
				
			});
		});	
		
	}	
	
	this.carregar = function(opcions) {		
							
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_seccio_elm.val(opcions.ordreTipus);
		}
		
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_seccio_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_seccio_elm.val(),10);
			
		// ordre
		ordre_Tipus = ordreTipus_seccio_elm.val();
		ordre_Camp = ordreCamp_seccio_elm.val();
			
		// variables
		dataVars = "nomSeccio=" + $("#cerca_seccions_nom").val();
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagSeccions,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				if (!a_enllas) {
					// missatge
					Missatge.llansar( { tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>" } );
					// error
					Error.llansar();
				}
				
			},
			success: function(data) {
				that.finCargaListado(data, opcions);
			}
		});	
	}
	
	this.finalizar = function(){		
		
		nombre_llistat = ModulSeccions.finalizar();
		
//		codi_seccions_txt = (nombre_llistat == 1) ? txtSeccio : txtSeccions;
//		codi_info = (nombre_llistat == 0) ? txtNoHiHaSeccions + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_seccions_txt.toLowerCase() + "</strong>.";
//		
//		modul_seccions_elm.find("p.info").html(codi_info);		
//		
//		if (nombre_llistat > 1) {			
//			modul_seccions_elm.find(".listaOrdenable ul").sortable({ 
//				axis: 'y', 
//				update: function(event,ui){
//					ModulSeccions.calculaOrden(ui,"origen");
//					EscriptoriSeccio.contaSeleccionats();
//				}
//			}).css({cursor:"move"});
//		}
		
		// Marcamos el formulario como modificado para habilitar el botón de guardar.
		Detall.modificado();
		
		this.torna();
	}	
	
	// Método sobreescrito
	this.anar = function(enlace_html) {
				
		num = parseInt(enlace_html,10);
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantItemsAnteriors : txtCercantItemsAnteriors;
		seccions_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			seccions_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_seccio_elm.val(num-1);								
				that.carregar({pagina: num-1});				
			});
		});
	}	
	
	this.torna = function() {
		// animacio
		escriptori_seccions_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_elm.find("a.gestiona").one( "click", function() { ModulSeccions.gestiona(); } );
			});
			
		});
	}	
		
	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo ){	
					
		// dsanchez: Componemos el item para enviar a la lista.
		var item = {
			id: itemID,
			nombre: titulo
		};
		
		// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
		if( ModulSeccions.agregaItem( item ) ){		
			this.contaSeleccionats();		
		}				
	}	
		
}