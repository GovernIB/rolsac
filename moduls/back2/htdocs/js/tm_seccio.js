// TM seccio

$(document).ready(function() {
	// elements
	opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
	
	multipagina = new Multipagina();
	
	pagPagina_llistat_elm = resultats_llistat_elm.find("input.pagPagina");
	ordreTipus_llistat_elm = resultats_llistat_elm.find("input.ordreTipus");
	ordreCamp_llistat_elm = resultats_llistat_elm.find("input.ordreCamp");
	
	resultats_cercador_elm = resultats_elm.find("div.C");
	cercador_elm = $("#cercador_contingut");
	
	pagPagina_cercador_elm = resultats_cercador_elm.find("input.pagPagina");
	ordreTipus_cercador_elm = resultats_cercador_elm.find("input.ordreTipus");
	ordreCamp_cercador_elm = resultats_cercador_elm.find("input.ordreCamp");
	
	escriptori_detall_elm = $("#escriptori_detall");

	// INICIEM
	LlistatSeccions = new CLlistat();
	Detall = new CDetall();	
	
    Detall.iniciar();
    // Mostrar detall?
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	}
	
	CAMPOS_TRADUCTOR_SECCIO = ["item_nom_", "item_descripcio_"];
	DATOS_TRADUCIDOS_SECCIO = ["nombre", "descripcion"];
	
	LlistatSeccions.iniciar();
});


// idioma
var pag_idioma = $("html").attr("lang");

// minim cercador
var numCercadorMinim = 0;

// paginacio
var paginacio_marge = 4;

// llistat
var itemID_ultim = 0;
function CLlistat(){
	this.extend = ListadoBase;
	this.extend();
	
	var that = this;
	
	this.iniciar = function() {
		this.carregar({});
	}
	
	this.finCargaListado = function(opcions,data){
		// total
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			// minim per cercador
			if (resultats_total > numCercadorMinim) {
				opcions_elm.find("li.C").animate({
					duration: "slow", width: 'show'
					}, 300);
			}
			
			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			
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
			ordre_c2 = (ordre_C == "codiEstandard") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
//				if (ordre_C == "descripcio") {
//					txt_per = txtDescripcio;
//				} else { // nom
//					txt_per = txtLlistaItem;
//				}
				var txt_per = txtOrdre;
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";
			
			// De momento no habra ordenacion.
// 			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th enllas" + ordre_c2 + "\" role=\"columnheader\">" + txtOrdre + "</div>";

			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";

			// codi cuerpo
			$(data.nodes).each( function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";

				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<span class=\"ordre\">" + (printStringFromNull(dada_node.ordre, txtSinValor)+1) + "</span>";
				
				codi_taula += "<a id=\"seccio_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nom, txtSinValor) + "</a>";
				codi_taula += "</div>";
				codi_taula += "<div class=\"td enllas\" role=\"gridcell\">";
				
				codi_taula += that.getHtmlSelectorOrdenacion("seccio_"+dada_node.id, dada_node.ordre, resultats_total );
				codi_taula += "</div>";
				codi_taula += "</div>";
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Instanciamos el navegador multipágina.
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "LlistatSeccions.cambiaPagina"
			});					
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Asociamos el evento onclick a los elementos de la lista para
				// poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){LlistatSeccions.ficha(this);});
							
				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					cercador_elm.find("input, select").removeAttr("disabled");
				}
				
				jQuery("#resultats .llistat .tbody select.ordenacion").unbind("change").bind("change",function(){
					var itemID = jQuery(this).attr("id").split("_")[1];
					var orden = jQuery(this).val();
					
					// Obtenemos el valor del orden anterior para saber en qué dirección reordenar los elementos
					var ordenAnterior = jQuery("#seccio_" + itemID).prev().html() -1;					
					
					var dataVars = "id=" + itemID + "&orden=" + orden + "&ordenAnterior="+ ordenAnterior;
					
					$.ajax({
						type: "POST",
						url: pagReordenar,
						data: dataVars,
						dataType: "json",
						error: function() {
							if (!a_enllas) {
								// missatge
								Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
							}
						},
						success: function(data) {
							that.anulaCache();
							that.carregar({});
						}
					});
				});
			});
		});
		ModulSeccions.iniciar(data);
	}
	
	this.carregar = function(opcions) {
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		dataVars = "";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;
			
			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
		} else {
			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;

			dataVars_cercador = "";
		}
			
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_elm.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();
			
		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();
			
		// variables
		dataVars += "pagPag=" + pag_Pag + "&pagRes=" + pag_Res + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagLlistat,
			data: dataVars,
			dataType: "json",
			error: function() {
				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				}
			},
			success: function(data) {				
				LlistatSeccions.finCargaListado(opcions,data);
			}
		});
	}
};

// items array
var Items_arr = new Array();

// detall
function CDetall(){
	this.extend = DetallBase;
	this.extend();
	
    var that = this;

	this.iniciar = function() {
		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_detall_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_detall_elm.find("ul.idiomes:first");
			
			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_detall_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", that.idioma);
		}
		
        // Sincronizar campos sin multi-idioma en zona multi-idioma.
        jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").change(function(){
            jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").val( jQuery(this).val() );
        });
        jQuery("#item_pare,#item_pare_es,#item_pare_en,#item_pare_de,#item_pare_fr").change(function(){
            jQuery("#item_pare,#item_pare_es,#item_pare_en,#item_pare_de,#item_pare_fr").val( jQuery(this).val() );
        });
        jQuery("#item_perfil,#item_perfil_es,#item_perfil_en,#item_perfil_de,#item_perfil_fr").change(function(){
            jQuery("#item_perfil,#item_perfil_es,#item_perfil_en,#item_perfil_de,#item_perfil_fr").val( jQuery(this).val() );
        });
        
        // boton de traducir
        jQuery("#botoTraduirSeccio").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });
        
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");
		
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;
	}
	
	this.traduirWrapper = function () {
		that.traduir(pagTraduirSeccions, CAMPOS_TRADUCTOR_SECCIO, DATOS_TRADUCIDOS_SECCIO);
	}
	
	this.nou = function(idPare, nomPare) {
		//Ocultar paneles y campos
		jQuery("#modul_seccions_relacionades").hide();
        //jQuery("#item_ua_principal").parent().parent().hide();
		
        $("#item_id").val("");
        $("#item_perfil").val("");
        $('#formGuardar input, #formGuardar textarea').each(limpiarCampo);
        
        if (typeof idPare != 'undefined' && idPare != null && idPare != '') {
        	$("#item_codi_pare").val(idPare);
        } else {
        	$("#item_codi_pare").val("");
        }
        if (typeof nomPare != 'undefined' && nomPare != null && nomPare != '') {
        	$("#item_pare").val(nomPare).change();
        } else {
        	$("#item_pare").val(txtSeccioArrel).change();
        }
        if ((typeof idPare != 'undefined' && idPare != null && idPare != '') &&
        	(typeof nomPare != 'undefined' && nomPare != null && nomPare != '')) {
        	jQuery("#btnVolver").unbind("click").bind("click", function() {
        		that.torna();
				if ($(".breadItems").length) {
					$(".breadItems").remove();
					$("#submenu").removeClass("breadcrumb");
				}
        	});
        }
        
        
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("#modulPrincipal div#cercador").hide();
		//escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		ModulFitxes.inicializarFichas();
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				itemID_ultim = 0;
			});
		});
		
		this.actualizaEventos();
	}		
	
	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {
        // Validamos el formulario
        if (!that.formulariValid()) {
            return false;
        }

		$("#formGuardar").ajaxSubmit({			
			url: pagGuardar,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
				LlistatSeccions.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {                   
//					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
					Detall.recarregar(data.id);
				}
			}

		});
        
		return false;
	}
	
	this.pintar = function(dades) {
		escriptori_detall_elm.find("a.elimina").show().end().find("h2:first").text(txtDetallTitol);
		
		// Mostrar paneles
		jQuery("#modul_seccions_relacionades").show();
		
		$("#item_id").val(dades.item_id);
		
		// Bloque de pestanyas de idiomas
		var idioma;
		for (var i in idiomas) {
			idioma = idiomas[i];
			$("#item_nom_" + idioma).val(printStringFromNull(dades[idioma]["item_nombre"]));
			$("#item_descripcio_" + idioma).val(printStringFromNull(dades[idioma]["item_descripcio"]));
		}
		// Fin bloque de pestanyas de idiomas
		
		$("#item_codi_pare").val(printStringFromNull(dades.item_codi_pare));
		$("#item_pare").val(printStringFromNull(dades.item_pare)).change();
		$("#item_codi_estandard").val(printStringFromNull(dades.item_codi_estandard)).change();
		
		
		// Rellenar el select de perfils
		var selected;
		var $per_select = $('#item_perfil');
		$per_select.find('option').remove();
		$per_select.append('<option value="0">' + txtTria + '</option>');
		for (var i in dades.item_perfils) {
			selected = dades.item_perfil == dades.item_perfils[i].id ? ' selected="selected"' : '';
			$per_select.append('<option value="' + dades.item_perfils[i].id + '"' + selected + '>' + dades.item_perfils[i].nom + '</option>');
		}
		$per_select.parent().parent().show();
        $per_select.change();

		ModulSeccions.inicializarSecciones(dades.seccionsRelacionades);
		ModulFitxes.inicializarFichas(dades.fitxesInformatives);
		
        // mostrem
        $("#modulLateral li.btnEliminar").show();
        $("#modulPrincipal div#cercador").show();
        
		if ($("#carregantDetall").size() > 0) {
			$("#carregantDetall").fadeOut(300, function() {
				$(this).remove();
				// array
				Detall.array({id: dades.item_id, accio: "guarda", dades: dades});
                escriptori_detall_elm.fadeIn(300);				
			});
		} else {
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);				
			});
		}
		
		if (typeof dades.item_codi_pare != 'undefined' && dades.item_codi_pare != null && dades.item_codi_pare != '') {
    		jQuery("#btnVolver").unbind("click").bind("click", function() {
    			that.carregar(dades.item_codi_pare);
    		});
    	} else {
        	jQuery("#btnVolver").unbind("click").bind("click", function() {
        		that.torna();
				if ($(".breadItems").length) {
					$(".breadItems").remove();
					$("#submenu").removeClass("breadcrumb");
				}
        	});
    	}
		
		montarBreadcrumb();
	}
	
	// Sobreescribimos este método para que nos salga el mensaje de "Cargando" correctamente.
	this.carregar = function(itemID){

		// Deshabilitamos inicialmente el botón de guardar.
		jQuery("#btnGuardar").unbind("click").parent().removeClass("off").addClass("off");

		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").show();
		
		if (itemID == undefined){
			itemID = $("#item_id").val();
			LlistatSeccions.itemID = itemID;
		}
		
		escriptori_contingut_elm.fadeOut(300, function() {

			dataVars = "accio=carregar" + "&id=" + itemID;
			
			Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantDetall});
			// ajax
			$.ajax({
				type: "POST",
				url: pagDetall,
				data: dataVars,
				dataType: "json",
				error: function() {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				},
				success: function(data) {
					Missatge.cancelar();
					if (typeof data.error != 'undefined') {
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.error + "</p>"});
					} else {
						Detall.pintar(data);
					}
				}
			});
		});
		this.actualizaEventos();
	}
	
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
				LlistatSeccions.anulaCache();
				if (data.id > 0) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					Detall.array({id: dada_node.item_id, accio: "elimina"});
					Detall.recarregar();
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id < -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}
		});
	}
};