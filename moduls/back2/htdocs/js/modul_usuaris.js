// MODUL USUARIS

$(document).ready(function() {
	
	// elements
	modul_usuaris_elm = jQuery("div.modulUsuaris:first");
	resultats_elm = jQuery("#resultatsUsuaris");
	resultats_actiu_elm = resultats_elm.find("div.actiu:first");
	escriptori_usuaris_elm = jQuery("#escriptori_usuaris");
	cercador_elm = jQuery("#cercadorUsuaris");
	
	ModulUsuari = new CModulUsuari();
	EscriptoriUsuari = new CEscriptoriUsuari();
	Llistat = EscriptoriUsuari;
	
	multipagina = new Multipagina();
	
	if (modul_usuaris_elm.size() == 1) {
		ModulUsuari.iniciar();
	}
	
	// Evento para el botón de volver al detalle
	jQuery(".btnVolverDetalle").bind("click",function(){EscriptoriUsuari.torna();});
	jQuery("#btnFinalizarUsuaris").bind("click",function(){EscriptoriUsuari.finalizar();});
	
});

//minim cercador
var numCercadorMinim = 0;

function CModulUsuari() {
	this.extend = ListaOrdenable;
	this.extend();
	
	// Campo hidden para controlar los cambios sobre un módulo.
	var $moduloModificado = modul_usuaris_elm.find('input[name="modulo_usuario_modificado"]');
	
	this.iniciar = function() {
		usuaris_llistat_elm = escriptori_usuaris_elm.find("div.escriptori_items_llistat:first");
		usuaris_cercador_elm = escriptori_usuaris_elm.find("div.escriptori_items_cercador:first");
		usuaris_seleccionats_elm = escriptori_usuaris_elm.find("div.escriptori_items_seleccionats:first");
		usuaris_dades_elm = usuaris_llistat_elm.find("div.dadesUsuaris:first");
		
		pagPagina_usuari_elm = usuaris_llistat_elm.find("input.pagPagina:first");
		ordreTipus_usuari_elm = usuaris_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_usuari_elm = usuaris_llistat_elm.find("input.ordreCamp:first");
		
		escriptori_usuaris_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
		});
		
		usuaris_llistat_elm.add(usuaris_seleccionats_elm);
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "usuari",
			nodoOrigen: modul_usuaris_elm.find(".listaOrdenable"),
			nodoDestino: usuaris_seleccionats_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
			//multilang: true
			multilang: false
		});
		
		// one al botó de gestionar
		modul_usuaris_elm.find("a.gestiona").one("click", function(){ModulUsuari.gestiona();} );
		EscriptoriUsuari.carregar({});
	}
	
	// Marcar el módulo como modificado.
	this.modificado = function() {
		$moduloModificado.val(1);
	}
	
	this.gestiona = function() {
		lis_size = modul_usuaris_elm.find("li").size();
		
		if (lis_size > 0) {
			this.copiaInicial();
			EscriptoriUsuari.contaSeleccionats();
			
		} else {
			usuaris_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaUsuarisSeleccionats + ".");
			usuaris_seleccionats_elm.find(".listaOrdenable").html("");
			
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_usuaris_elm.fadeIn(300);
		});
	}
};

function CEscriptoriUsuari() {
	this.extend = ListadoBase;
	this.extend("", "", "", "cercador_contingut_usuaris", "", "", "", "btnBuscarUsuarisForm", "btnLimpiarFormUsuaris");
	var that = this;
	
	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo ) {
		// tcerdà: Componemos el item para enviar a la lista.
		var item = {
				id: itemID,
				nombre: titulo
			};
		
		// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
		if (ModulUsuari.agregaItem( item )) {
			this.contaSeleccionats();
		}
	}
	
	// Cambia de página.
	this.cambiaPagina = function( pag ) {
		multipagina.setPaginaActual(pag-1);
		pag_Pag = pag;
		this.anar(pag);
	}
	
	this.finCargaListado = function(data,opcions) {
		// total
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			// minim per cercador
			if (resultats_total > numCercadorMinim) {
				opcions_elm.find("li.C").animate({duration: "slow", width: 'show'}, 300);
			}
			
			txtT = (resultats_total > 1) ? txtUsuaris : txtUsuari;
			
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
			ordre_c1 = (ordre_C == "nom") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "username") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "perfil") ? " " + ordre_T : "";
//			ordre_c4 = (ordre_C == "email") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "username") {
					txt_per = txtUsername;
				} else if (ordre_C == "perfil") {
					txt_per = txtPerfil;
				} else if (ordre_C == "email") {
					txt_per = txtEmail;
				} else {
					txt_per = txtUsuari;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDel + " " + resultatInici + " " + txtAl + " " + resultatFinal + txt_ordenacio + ".</p>";
			
			// De momento no habra ordenacion.
//						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
//						codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
//						codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
			
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtNom + "</div>";
			codi_cap2 = "<div class=\"th username" + ordre_c2 + "\" role=\"columnheader\">" + txtUsername + "</div>";
			codi_cap3 = "<div class=\"th perfil" + ordre_c3 + "\" role=\"columnheader\">" + txtPerfil + "</div>";
//			codi_cap4 = "<div class=\"th email" + ordre_c4 + "\" role=\"columnheader\">" + txtEmail + "</div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3;// + codi_cap4;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";

			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				
				parClass = (i%2) ? " par": "";
				caducat_nom_class = " usuari";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td " + caducat_nom_class + "\" role=\"gridcell\">";

				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"usuari_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = "";
				codi_taula += "<div class=\"td username\" role=\"gridcell\">" + printStringFromNull(dada_node.username) + "</div>";
				codi_taula += "<div class=\"td perfil\" role=\"gridcell\">" + printStringFromNull(dada_node.perfil) + "</div>";
//				codi_taula += "<div class=\"td email\" role=\"gridcell\">" + printStringFromNull(dada_node.email) + "</div>";
				
				codi_taula += "</div>";
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Instanciamos el navegador multip�gina.					
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "Llistat.cambiaPagina"
			});					
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaUsuaris + ".</p>";
			
		}
		
		// animacio
		usuaris_dades_elm.fadeOut(300, function() {
			// pintem
			usuaris_dades_elm.html(codi_final).fadeIn(300, function() {
														
				// Evento lanzado al hacer click en un elemento de la lista.
				jQuery("#resultatsUsuaris .llistat .tbody a").unbind("click").bind("click",function(){
					var partesItem = jQuery(this).attr("id").split("_");
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					that.agregaItem(itemID,titulo);
					});
				
				// cercador
				edificis_cercador_elm.find("input, select").removeAttr("disabled");
				
			});
		});	
		
	}
	
	this.carregar = function(opcions) {
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;		
		
		dataVars = "";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			
			pagPagina_elm = pagPagina_usuari_elm;
			ordreTipus_elm = ordreTipus_usuari_elm;
			ordreCamp_elm = ordreCamp_usuari_elm;
			
			// cercador
			dataVars_cercador = "&nom=" + $("#cerca_usuaris_nom").val();
			dataVars_cercador += "&username=" + $("#cerca_usuaris_codi").val();
			dataVars_cercador += "&email=" + $("#cerca_usuaris_correu").val();
			dataVars_cercador += "&perfil=" + $("#cerca_usuaris_perfil").val();
			dataVars_cercador += "&observacions=" + $("#cerca_usuaris_observacions").val();
			
		} else {

			pagPagina_elm = pagPagina_usuari_elm;
			ordreTipus_elm = ordreTipus_usuari_elm;
			ordreCamp_elm = ordreCamp_usuari_elm;
			
			// cercador
			dataVars_cercador = "";
			
		}
		
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_usuari_elm.val(opcions.ordreTipus);
		}
		
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_usuari_elm.val(opcions.ordreCamp);
		}
		
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_usuari_elm.val(),10);
		
		// ordre
		ordre_Tipus = ordreTipus_usuari_elm.val();
		ordre_Camp = ordreCamp_usuari_elm.val();
		
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagUsuaris,
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
				that.finCargaListado(data,opcions);
			}
		});
	}
	
	this.finalizar = function() {
		nombre_llistat = ModulUsuari.finalizar();
		codi_usuaris_txt = (nombre_llistat == 1) ? txtUsuari : txtUsuaris;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaUsuaris + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_usuaris_txt.toLowerCase() + "</strong>.";
		
		modul_usuaris_elm.find("p.info").html(codi_info);
		if (nombre_llistat > 1) {
			modul_usuaris_elm.find(".listaOrdenable ul").sortable({
				axis: 'y',
				update: function(event,ui) {
					ModulUsuari.calculaOrden(ui,"origen");
					EscriptoriUsuari.contaSeleccionats();
				}
			}).css({cursor:"move"});
		}
		
		// Marcamos el módulo como modificado.
		ModulUsuari.modificado();
		
		// Marcamos el formulario como modificado para habilitar el botón de guardar.
		Detall.modificado();
		
		this.torna();
	}
	
	// Método sobreescrito
	this.anar = function(enlace_html) {
		num = parseInt(enlace_html,10);
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantItemsAnteriors : txtCercantItemsAnteriors;
		usuaris_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			usuaris_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_usuari_elm.val(num-1);
				that.carregar({pagina: num-1});
			});
		});
	}
	
	this.torna = function() {
		// animacio
		escriptori_usuaris_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_usuaris_elm.find("a.gestiona").one("click", function(){ModulUsuari.gestiona();});
			});
		});
	}
	
	this.contaSeleccionats = function() {
		seleccionats_val = usuaris_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = usuaris_seleccionats_elm.find("p.info:first");
		if (seleccionats_val == 0) {
			usuaris_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaUsuarisSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtUsuari.toLowerCase() + "</strong>.");
			
		} else {
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtUsuaris.toLowerCase() + "</strong>.");
			usuaris_seleccionats_elm.find(".listaOrdenable ul").sortable({
				axis: 'y',
				update: function(event,ui) {
					ModulUsuari.calculaOrden(ui,"origen");
					EscriptoriUsuari.contaSeleccionats();
				}
			}).css({cursor:"move"});
		}
		
		usuaris_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {
			var itemLista = jQuery(this).parents("li:first");
			ModulUsuari.eliminaItem(itemLista);
			EscriptoriUsuari.contaSeleccionats();
		});
	}
	
};
