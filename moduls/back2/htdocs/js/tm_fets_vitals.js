// TM FETS VITALS

$(document).ready(function() {
	
	// Listener para guardado de módulos laterales vía AJAX.
	jQuery(".lista-simple").click(function() {
		
		var elements = $(this).parent().parent().find('li');
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleGenerica.guardar(elements, url, id);
		
	});
	
	ListaSimpleGenerica = new ListaSimple();
	
	// Listener para guardado de módulos laterales vía AJAX.
	jQuery(".lista-simple-procedimientos").click(function() {
		
		var elements = $('#escriptori_procediments .modulLateral.escriptori_items_seleccionats .seleccionats').find('li');
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleProcedimientos.guardar(elements, url, id);
		
	});
	
	ListaSimpleGenerica = new ListaSimple();
	ListaSimpleProcedimientos = new ListaSimple();
    
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
	escriptori_previsualitza_elm = $("#escriptori_previsualitza");

	// INICIEM
	Llistat = new CLlistat();
	Detall = new CDetall();	
	
    Detall.iniciar();
    
    // Mostrar detall
	var itemACarregar = itemAEditar();

	if (itemACarregar > 0)
		Detall.carregar(itemACarregar);
	
	CAMPOS_TRADUCTOR_FET_VITAL = ["item_nom_", "item_descripcio_", "item_paraules_clau_"];
	DATOS_TRADUCIDOS_FET_VITAL = ["nombre", "descripcion", "palabrasclave"];
	
    Llistat.iniciar();
    
});

// idioma
var pag_idioma = $("html").attr("lang");

// minim cercador
var numCercadorMinim = 0;

// paginacio
var paginacio_marge = 4;

// llistat
var itemID_ultim = 0;

function CLlistat() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = ListadoBase;
	this.extend();
	
	var that = this;
	
	this.iniciar = function() {
		
		if (debug)
			console.log("Entrando en CLlistat.iniciar");
		
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos			
		this.carregar({});
		
		if (debug)
			console.log("Saliendo de CLlistat.iniciar");
		
	};
	
	this.finCargaListado = function(opcions, data) {
		
		if (debug)
			console.log("Entrando en CLlistat.finCargaListado");

		resultats_total = parseInt(data.total, 10);
		
		if (resultats_total > 0) {
			
			// minim per cercador
			if (resultats_total > numCercadorMinim) {
				opcions_elm.find("li.C").animate({
					duration: "slow", width: 'show'
					}, 300);
			}
			
			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			
			if (resultats_total % pag_Res > 0)
				ultimaPag++;

			if (pag_Pag > ultimaPag)
				pag_Pag = ultimaPag;

			
			resultatInici = ((pag_Pag * pag_Res) + 1);
			resultatFinal = ((pag_Pag * pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag * pag_Res) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "ordre") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "codiEstandard") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "DESC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				var txt_per = txtOrdre;
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";
			
			codi_cap1 = "<div class=\"th fetsVitals" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
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
			$(data.nodes).each(function(i) {
				
				dada_node = this;
				
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";

				codi_taula += "<div class=\"td fetsVitals\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<span class=\"ordre\">" + (printStringFromNull(dada_node.ordre, txtSinValor) + 1) + "</span>";
				
				codi_taula += "<a id=\"fetsVitals_"+dada_node.id+"\" href=\"javascript:;\" class=\"fetsVitals editarFetsVitals\">" + printStringFromNull(dada_node.nom, txtSinValor) + "</a>";
				codi_taula += "</div>";				
				codi_taula += "<div class=\"td enllas\" role=\"gridcell\">";
				
				codi_taula += that.getHtmlSelectorOrdenacion("fetsVitals_"+dada_node.id, dada_node.ordre, resultats_total );
				codi_taula += "</div>";
				codi_taula += "</div>";
				
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if ($.browser.opera)
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			
			// Instanciamos el navegador multipágina.
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
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() { // pintem
			
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Asociamos el evento onclick a los elementos de la lista para
				// poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a.editarFetsVitals").unbind("click").bind("click", function(){ Llistat.ficha(this); });
							
				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si")
					cercador_elm.find("input, select").removeAttr("disabled");
				
				jQuery("#resultats .llistat .tbody select.ordenacion").bind("change").bind("change", function() {
					
					var itemID = jQuery(this).attr("id").split("_")[1];
					var orden = jQuery(this).val();

					// Obtenemos el valor del orden anterior para saber en qué dirección reordenar los elementos
					var ordenAnterior = jQuery("#fetsVitals_" + itemID).prev().html() -1;					
					
					var dataVars = "id=" + itemID + "&orden=" + orden + "&ordenAnterior="+ ordenAnterior;					
					
					$.ajax({
						type: "POST",
						url: pagReordenar,
						data: dataVars,
						dataType: "json",
						error: function() {
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						},
						success: function(data) {
							
							that.anulaCache();
							that.carregar({});
							
						}
					});
					
				});
				
			});
		});
		
		if (debug)
			console.log("Saliendo de CLlistat.finCargaListado");
		
	};
	
	this.carregar = function(opcions) {
		
		if (debug)
			console.log("Entrando en CLlistat.carregar");

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
		
		if (typeof opcions.ordreTipus != "undefined") // ordreTipus
			ordreTipus_elm.val(opcions.ordreTipus);

		if (typeof opcions.ordreCamp != "undefined") // ordreCamp
			ordreCamp_elm.val(opcions.ordreCamp);
		
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
				
				if (!a_enllas)
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});

			},
			success: function(data) {				
				Llistat.finCargaListado(opcions,data);
			}
		});
		
		if (debug)
			console.log("Saliendo de CLlistat.carregar");
		
	};
	
};

// items array
var Items_arr = new Array();

// detall
function CDetall() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = DetallBase;
	this.extend();
	
    var that = this;

	this.iniciar = function() {
		
		if (debug)
			console.log("Entrando en CDetall.iniciar");
		
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
		
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").change(function(){
            jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").val( jQuery(this).val() );
        });
        
        // boton de traducir
        jQuery("#botoTraduirFetsVitals").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });
        
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");
		
		this.guarda = this.guarda_upload;
		
		if (debug)
			console.log("Saliendo de CDetall.iniciar");
		
	};
	
	this.traduirWrapper = function () {
		
		if (debug)
			console.log("Entrando en CDetall.traduirWrapper");
		
		that.traduir(pagTraduirFetsVitals, CAMPOS_TRADUCTOR_FET_VITAL, DATOS_TRADUCIDOS_FET_VITAL);
		
		if (debug)
			console.log("Saliendo de CDetall.traduirWrapper");
		
	};
	
	// Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
		
		if (debug)
			console.log("Entrando en CDetall.guarda_upload");
		
		// Validamos el formulario
		if (!that.formulariValid()) {
			
			if (debug)
				console.log("Saliendo de CDetall.guarda_upload");
			
			return false;
			
		}
		
		ModulProcediment.calculaOrden();
		
		// Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
		$("#formGuardar").ajaxSubmit({	
			url: pagGuardar,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
							
				Llistat.cacheDatosListado = null;
				
				if (data.id < 0)
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				else
					Detall.recarregar(data.id);
										
			}
								
		});
		
		if (debug)
			console.log("Saliendo de CDetall.guarda_upload");

		return false;
		
	};
	
	this.activar = 0;

	this.nou = function() {
		
		if (debug)
			console.log("Entrando en CDetall.nou");
		
        $("#item_id").val("");
        
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		// Resetear upload de archivos
		limpiarArchivo("item_foto");
		limpiarArchivo("item_icona");
		limpiarArchivo("item_icona_gran");
		
		// Resetear upload de achivos multidioma
		for (var i in idiomas) {
			
			limpiarArchivoMultiidioma("item_distribucio", idiomas[i]);
			limpiarArchivoMultiidioma("item_normativa", idiomas[i]);
			limpiarArchivoMultiidioma("item_contingut", idiomas[i]);
			
		}
		
		escriptori_contingut_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				itemID_ultim = this.activar;
			});
			
		});

		this.actualizaEventos();
		
		this.modificado(false);
		
		if (debug)
			console.log("Saliendo de CDetall.nou");
		
	};
	
	this.pintar = function(dades) {
		
		if (debug)
			console.log("Entrando en CDetall.pintar");
		
		escriptori_detall_elm.find("a.elimina").show().end().find("h2:first").text(txtDetallTitol);
		
		dada_node = dades;
		$("#item_id").val(dada_node.item_id);
		
		// Bloque de pestañas de idiomas
		for (var i in idiomas) {
			
			var idioma = idiomas[i];
			
			if (dada_node[idioma]) {
				
				$("#item_nom_" + idioma).val(printStringFromNull(dada_node[idioma]["nombre"]));
				$("#item_descripcio_" + idioma).val(printStringFromNull(dada_node[idioma]["descripcion"]));
				$("#item_paraules_clau_" + idioma).val(printStringFromNull(dada_node[idioma]["palabrasclave"]));

				// Fitxers
				$("#item_distribucio_" + idioma).val("");
				$("#grup_item_distribucio_" + idioma + " input").removeAttr("checked");
				
				if (dada_node[idioma]["distribucio"]) {
					
					var a = $("#grup_item_distribucio_" + idioma + " a"); 
					a.show();					
					a.attr("href", pagArrel + dada_node[idioma]["distribucio_enllas_arxiu"]);
					a.text(dada_node[idioma]["distribucio"]);
					$("#grup_item_distribucio_" + idioma + " span").hide();
					$("#grup_item_distribucio_" + idioma + " input").show();
					$("#grup_item_distribucio_" + idioma + " label.eliminar").show();
								
				} else {
					
					$("#grup_item_distribucio_" + idioma + " span").show();
					$("#grup_item_distribucio_" + idioma + " input").hide();
					$("#grup_item_distribucio_" + idioma + " label.eliminar").hide();
					$("#grup_item_distribucio_" + idioma + " a").hide();
					
				}
				
				$("#item_normativa_" + idioma).val("");
				$("#grup_item_normativa_" + idioma + " input").removeAttr("checked");
				
				if (dada_node[idioma]["normativa"]) {
					
					$("#grup_item_normativa_" + idioma + " a").show();					
					$("#grup_item_normativa_" + idioma + " a").attr("href", pagArrel + dada_node[idioma]["normativa_enllas_arxiu"]);
					$("#grup_item_normativa_" + idioma + " a").text(dada_node[idioma]["normativa"]);
					$("#grup_item_normativa_" + idioma + " span").hide();
					$("#grup_item_normativa_" + idioma + " input").show();
					$("#grup_item_normativa_" + idioma + " label.eliminar").show();
								
				} else {
					
					$("#grup_item_normativa_" + idioma + " span").show();
					$("#grup_item_normativa_" + idioma + " input").hide();
					$("#grup_item_normativa_" + idioma + " label.eliminar").hide();
					$("#grup_item_normativa_" + idioma + " a").hide();
					
				}
				
				$("#item_contingut_" + idioma).val("");
				$("#grup_item_contingut_" + idioma + " input").removeAttr("checked");
				
				if (dada_node[idioma]["contingut"]) {
					
					$("#grup_item_contingut_" + idioma + " a").show();					
					$("#grup_item_contingut_" + idioma + " a").attr("href", pagArrel + dada_node[idioma]["contingut_enllas_arxiu"]);
					$("#grup_item_contingut_" + idioma + " a").text(dada_node[idioma]["contingut"]);
					$("#grup_item_contingut_" + idioma + " span").hide();
					$("#grup_item_contingut_" + idioma + " input").show();
					$("#grup_item_contingut_" + idioma + " label.eliminar").show();
								
				} else {
					
					$("#grup_item_contingut_" + idioma + " span").show();
					$("#grup_item_contingut_" + idioma + " input").hide();
					$("#grup_item_contingut_" + idioma + " label.eliminar").hide();
					$("#grup_item_contingut_" + idioma + " a").hide();
					
				}
				
			}
			
		}
		// Fin bloque de pestanyas de idiomas
		
		jQuery("#item_codi_estandard").val(dada_node.item_codi_estandard);
        jQuery("#item_codi_estandard").change();
		
		//Foto
        pintarArchivo("item_foto", dada_node);
		
		//Icona
		pintarArchivo("item_icona", dada_node);
		
		//FotoGran
		pintarArchivo("item_icona_gran", dada_node);
		
        // mostrem
        $("#modulLateral li.btnEliminar").show();
        
		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				Detall.array({id: dada_node.item_id, accio: "guarda", dades: dada_node});
                escriptori_detall_elm.fadeIn(300);
                
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() { escriptori_detall_elm.fadeIn(300); });
			
		}	
		
		this.modificado(false);
		
		if (debug)
			console.log("Saliendo de CDetall.pintar");
		
	};
	
	this.elimina = function() {
		
		if (debug)
			console.log("Entrando en CDetall.elimina");
		
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		
		item_ID = $("#item_id").val();
		dataVars = "id=" + item_ID;
				
		$.ajax({
			type: "POST",
			url: pagEsborrar,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {
				
				Llistat.anulaCache();
				
				if (data.id > 0) {
					
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					Detall.array({id: dada_node.item_id, accio: "elimina"});
					Detall.recarregar();
					
				} else if (data.id == -1) {
					
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
					
				} else if (data.id < -1) {
					
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
					
				}
				
			}
			
		});
		
		if (debug)
			console.log("Saliendo de CDetall.elimina");
		
	};
	
	this.pintarModulos = function(dades) {
		
		if (debug)
			console.log("Entrando en CDetall.pintarModulos");
		
		ModulProcediment.inicializarProcediments(dades.procediments);
		
		if (debug)
			console.log("Saliendo de CDetall.pintarModulos");
		
	};
	
};