// TM LOPD LEGITIMACION

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
	Llistat = new CLlistat();
	Detall = new CDetall();
    Detall.iniciar();

    // Mostrar detall
	var itemACarregar = itemAEditar();

	if (itemACarregar > 0)
		Detall.carregar(itemACarregar);

	CAMPOS_TRADUCTOR_AGRUPACIO_MATERIES = ["item_nom_"];
	DATOS_TRADUCIDOS_AGRUPACIO_MATERIES = ["nombre"];

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

	this.iniciar = function() {

		if (debug)
			console.log("Entrando en CLlistat.iniciar");

		this.carregar({});

		if (debug)
			console.log("Saliendo de CLlistat.iniciar");

	};

	this.finCargaListado = function(opcions, data) {

		if (debug)
			console.log("Entrando en CLlistat.finCargaListado");

		resultats_total = parseInt(data.total, 10);

		if (resultats_total > 0) {

			if (resultats_total > numCercadorMinim) // minim per cercador
				opcions_elm.find("li.C").animate({duration: "slow", width: 'show'}, 300);

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
			ordre_c1 = (ordre_C == "nom") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "descripcio") ? " " + ordre_T : "";

			txt_ordenacio = "";

			if (resultats_total > 1) {

				txt_ordenats = (ordre_T == "ASC") ? txtOrdenades + " <em>" + txtAscendentment + "</em>" : txtOrdenades + " <em>" + txtDescendentment + "</em>";

				if (ordre_C == "descripcio")
					txt_per = txtDescripcio;

				else
					txt_per = txtLlistaItem; // nom

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";
			codi_cap1 = "<div class=\"th nom ml-20" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";

			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";

			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1; // + codi_cap2;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";

			// codi cuerpo
			$(data.nodes).each( function(i) {

				dada_node = this;
				parClass = (i%2) ? " par": "";

				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				codi_taula += "<div class=\"td nom ml-20\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"lopdlegitimacion_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				codi_taula += "</div>";

			});

			codi_taula += "</div>";
			codi_taula += "</div>";

			if ($.browser.opera)
				escriptori_contingut_elm.find("div.table:first").css("font-size", ".85em");

			multipagina.init({ // Instanciamos el navegador multipágina.
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
		dades_elm.fadeOut(300, function() {

			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {

				// Asociamos el evento onclick a los elementos de la lista para
				// poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click", function() {
					Llistat.ficha(this);
				});

				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si")
					cercador_elm.find("input, select").removeAttr("disabled");

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

		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined")
			ordreTipus_elm.val(opcions.ordreTipus);

		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined")
			ordreCamp_elm.val(opcions.ordreCamp);

		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag, 10) : multipagina.getPaginaActual();

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

		// Desactivamos que al cambiar un valor en este desplegable la vista se marque como modificada.
		// Esto se hace de forma genérica para elementos de los formularios en detall_base.js, de este modo:
		// 		jQuery("#" + ids.form + " input,#" + ids.form + " select,#" + ids.form + " textarea").bind("change", function() { that.modificado(); });
		jQuery('#item_lopd_legitimacion_relacionada').unbind('change');

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

        jQuery("#item_seccio,#item_seccio_es,#item_seccio_en,#item_seccio_de,#item_seccio_fr").change(function(){
            jQuery("#item_seccio,#item_seccio_es,#item_seccio_en,#item_seccio_de,#item_seccio_fr").val( jQuery(this).val() );
        });

		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");

		// Afegir nodes a la llista de legitimaciones
		$('#afegeixMateria').unbind("click").bind("click", function() {
	    	$('#popMateria').css({display:"block"});
	    });

		$('#tancaMateria').unbind("click").bind("click", function() {
	    	$('#popMateria').css({display:"none"});
	    });

		// Boto afegir node
		$('#addMateria').unbind("click").bind("click", function() {

	    	var id = jQuery('#item_lopd_legitimacion_relacionada').val();

	    	if ( id != '' || id > 0 ) {

	    		$('#popMateria').css({display:"none"});

	    		var vfItem = new Object();
		    	vfItem['id'] = jQuery('#item_lopd_legitimacion_relacionada').val();
		    	vfItem['nom'] = jQuery('#item_lopd_legitimacion_relacionada option:selected').text();

		    	var ordenItem = jQuery('#modul_lopd_legitimacions ul li:last input.lopd_legitimacion_orden').val();

		    	if (typeof ordenItem == 'undefined')
		    		vfItem['orden'] = 0;
		    	else
		    		vfItem['orden'] = parseInt(ordenItem) + 1;

		    	vfItem['idMainItem'] = jQuery('#item_id').val();
		    	vfItem['idRelatedItem'] = vfItem['id'];


		    	jQuery('#item_lopd_legitimacion_relacionada').each(limpiarCampo);

	    	} else {

	    		Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: errorMateria});

	    	}

	    });

		// boton de traducir
        jQuery("#botoTraduirAgrupacioMateries").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });

		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;

		if (debug)
			console.log("Saliendo de CDetall.iniciar");

	};

	this.traduirWrapper = function () {

		if (debug)
			console.log("Entrando en CDetall.traduirWrapper");

		that.traduir(pagTraduirAgrupacioMateries, CAMPOS_TRADUCTOR_AGRUPACIO_MATERIES, DATOS_TRADUCIDOS_AGRUPACIO_MATERIES);

		if (debug)
			console.log("Saliendo de CDetall.traduirWrapper");

	};

	this.activar = 0;

	this.nou = function() {

		if (debug)
			console.log("Entrando en CDetall.nou");

        $("#item_id").val("");
        $('#formGuardar input').each(limpiarCampo);

		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);

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

	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {

		if (debug)
			console.log("Entrando en CDetall.guarda_upload");

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

	this.pintar = function(dades) {

		if (debug)
			console.log("Entrando en CDetall.pintar");

		escriptori_detall_elm.find("a.elimina").show().end().find("h2:first").text(txtDetallTitol);

		$("#item_id").val(dades.item_id);

		// Bloque de pestanyas de idiomas
		var idioma;
		for (var i in idiomas) {

			idioma = idiomas[i];
			$("#item_nombre_" + idioma).val(printStringFromNull(dades[idioma]["item_nombre_"+idioma]));

		}
		// Fin bloque de pestanyas de idiomas

		jQuery("#item_codi_estandard").val(dades.item_codi_estandard);
        jQuery("#item_codi_estandard, #item_codi_estandard_ca, #item_codi_estandard_es").change(function(){
			jQuery("#item_codi_estandard, #item_codi_estandard_ca, #item_codi_estandard_es").val( jQuery(this).val() );
		});

		jQuery("#item_identificador, #item_identificador_es, #item_identificador_ca").val(dades.item_identificador);
		jQuery("#item_identificador, #item_identificador_es, #item_identificador_ca").change(function(){
			jQuery("#item_identificador, #item_identificador_es, #item_identificador_ca").val( jQuery(this).val() );
		});

		jQuery("#item_por_defecto, #item_por_defecto_ca, #item_por_defecto_es").attr("checked", dades.item_por_defecto);
		 jQuery("#item_por_defecto, #item_por_defecto_ca, #item_por_defecto_es").change(function(){
			jQuery("#item_por_defecto, #item_por_defecto_ca, #item_por_defecto_es").attr("checked", jQuery(this).is(":checked"));
		});

        // mostrem
        $("#modulLateral li.btnEliminar").show();

		if ($("#carregantDetall").size() > 0) {

			$("#carregantDetall").fadeOut(300, function() {

				$(this).remove();
				Detall.array({id: dada_node.item_id, accio: "guarda", dades: dada_node});
                escriptori_detall_elm.fadeIn(300);

			});

		} else {

			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);
			});

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
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id < -1){
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

		if (debug)
			console.log("Saliendo de CDetall.pintarModulos");

	};

};