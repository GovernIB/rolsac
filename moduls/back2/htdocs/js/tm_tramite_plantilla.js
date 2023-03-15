// TM tramite plantilla

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
    // Mostrar detall?
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	}

	CAMPOS_TRADUCTOR_TRAMITE_PLANTILLA = ["item_nombre_"];
	DATOS_TRADUCIDOS_TRAMITE_PLANTILLA = ["nombre"];

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
			ordre_c1 = (ordre_C == "titol") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "descripcio") ? " " + ordre_T : "";
			//ordre_c3 = (ordre_C == "ordre") ? " " + ordre_T : "";

			txt_ordenacio = "";

			if (resultats_total > 1) {

				txt_ordenats = (ordre_T == "DESC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				//txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";

				/*if (ordre_C == "descripcio") {
					txt_per = txtDescripcio;
				} else { // nom
					txt_per = txtLlistaItem;
				}*/

				var txt_per = txtOrdre;

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";

			// De momento no habra ordenacion.
// 			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";

			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th descripcio" + ordre_c2 + "\" role=\"columnheader\">" + txtDescripcio + "</div>";
			//codi_cap3 = "<div class=\"th enllas" + ordre_c3 + "\" role=\"columnheader\">" + txtOrdre + "</div>";
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
			//$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
			$(data.nodes).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";

				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";

				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				//codi_taula += "<span class=\"ordre\">" + (printStringFromNull(dada_node.ordre, txtSinValor) + 1) + "</span>";
				codi_taula += "<a id=\"model_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nom, txtSinValor) + "</a>";
				codi_taula += "</div>";
				codi_taula += "<div class=\"td descripcio\" role=\"gridcell\">" + printStringFromNull(dada_node.descripcio, txtSinValor) + "</div>";
				codi_taula += "<div class=\"td enllas\" role=\"gridcell\">";
				//codi_taula += that.getHtmlSelectorOrdenacion("model_"+dada_node.id, dada_node.ordre, resultats_total );
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
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.ficha(this);});

				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					cercador_elm.find("input, select").removeAttr("disabled");
				}
				jQuery("#resultats .llistat .tbody select.ordenacion").bind("change").bind("change", function() {

					var itemID = jQuery(this).attr("id").split("_")[1];
					var orden = jQuery(this).val();

					// Obtenemos el valor del orden anterior para saber en qué dirección reordenar los elementos
					var ordenAnterior = jQuery("#model_" + itemID).prev().html() -1;

					var dataVars = "id=" + itemID + "&orden=" + orden + "&ordenAnterior="+ ordenAnterior;

					$.ajax({
						type: "POST",
						url: pagReordenar,
						data: dataVars,
						dataType: "json",
						error: function(){
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						},
						success: function(data){
							that.anulaCache();
							that.carregar({});
						}
					});

				});
			});
		});
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
		// pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) :
		// parseInt(pagPagina_elm.val(),10);
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
				Llistat.finCargaListado(opcions,data);
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

        // Sincronizar campos sin idioma en zona multi-idioma.
        //jQuery("#item_nombre,#item_nombre_ca,#item_nombre_es,#item_nombre_en,#item_nombre_de,#item_nombre_fr").change(function(){
        //    jQuery("#item_nombre,#item_nombre_ca,#item_nombre_es,#item_nombre_en,#item_nombre_de,#item_nombre_fr").val( jQuery(this).val() );
        //});
        jQuery("#item_identificador,#item_identificador_ca,#item_identificador_es,#item_identificador_en,#item_identificador_de,#item_identificador_fr").change(function(){
            jQuery("#item_identificador,#item_identificador_ca,#item_identificador_es,#item_identificador_en,#item_identificador_de,#item_identificador_fr").val( jQuery(this).val() );
        });
        jQuery("#item_version,#item_version_ca,#item_version_es,#item_version_en,#item_version_de,#item_version_fr").change(function(){
            jQuery("#item_version,#item_version_ca,#item_version_es,#item_version_en,#item_version_de,#item_version_fr").val( jQuery(this).val() );
        });
        jQuery("#item_parametros,#item_parametros_ca,#item_parametros_es,#item_parametros_en,#item_parametros_de,#item_parametros_fr").change(function(){
            jQuery("#item_parametros,#item_parametros_ca,#item_parametros_es,#item_parametros_en,#item_parametros_de,#item_parametros_fr").val( jQuery(this).val() );
        });
        jQuery("#item_plataforma,#item_plataforma_es,#item_plataforma_en,#item_plataforma_de,#item_plataforma_fr").change(function(){
            jQuery("#item_plataforma,#item_plataforma_es,#item_plataforma_en,#item_plataforma_de,#item_plataforma_fr").val( jQuery(this).val() );
        });
        jQuery("#item_fase,#item_fase_es,#item_fase_en,#item_fase_de,#item_fase_fr").change(function(){
            jQuery("#item_fase,#item_fase_es,#item_fase_en,#item_fase_de,#item_fase_fr").val( jQuery(this).val() );
        });

        jQuery("#item_id").change(function(){
            jQuery("#item_id").val( jQuery(this).val() );
        });
        // boton de traducir
        jQuery("#botoTraduir").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });

		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");

		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;
	}

	this.traduirWrapper = function () {
		that.traduir(pagTraduirTramitePlantilla, CAMPOS_TRADUCTOR_TRAMITE_PLANTILLA, DATOS_TRADUCIDOS_TRAMITE_PLANTILLA);
	}

	this.nou = function() {

        $("#item_id").val("");
        $("#item_idAnt").val("");
        $("#item_id").removeAttr("readonly");
        $("#item_id").removeClass("readOnly");
        $('#formGuardar input').each(limpiarCampo);

		$("div.es #item_identificador").val("");
		$("div.es #item_version").val("");
		$("div.es #item_plataforma").val("");
		$("div.es #item_parametros").val("");
		$("div.es #item_fase").val("");

		$("div.ca #item_identificador").val("");
		$("div.ca #item_version").val("");
		$("div.ca #item_plataforma").val("");
		$("div.ca #item_parametros").val("");
		$("div.es #item_fase").val("");


		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);

		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				itemID_ultim = 0;
			});
		});

		this.actualizaEventos();

		this.modificado(false);
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
				Llistat.cacheDatosListado = null;

				if (data.nodo.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nodo.nom + "</p>"});
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

		$("#item_id").val(dades.item_id);
		$("#item_id").attr("readonly","readonly");
	    $("#item_id").addClass("readOnly");
		$("#item_idAnt").val(dades.item_id);
	//	$("#item_codigo").val(dades.item_codigo);

		// Bloque de pestanyas de idiomas
		var idioma;
		for (var i in idiomas) {
			idioma = idiomas[i];
			//$("#item_descripcio_" + idioma).val(printStringFromNull(dades[idioma]["item_descripcio"]));
			$("#item_nombre_" + idioma).val(printStringFromNull(dades[idioma]["item_nombre"]));

		}
		// Fin bloque de pestanyas de idiomas

		$("div.es #item_identificador").val(dades.item_identificador);
		$("div.es #item_version").val(dades.item_version);
		$("div.es #item_plataforma").val(dades.item_plataforma);
		$("div.es #item_parametros").val(dades.item_parametros);
		$("div.es #item_fase").val(dades.item_fase);
		$("div.ca #item_identificador").val(dades.item_identificador);
		$("div.ca #item_version").val(dades.item_version);
		$("div.ca #item_plataforma").val(dades.item_plataforma);
		$("div.ca #item_parametros").val(dades.item_parametros);
		$("div.ca #item_fase").val(dades.item_fase);

        // mostrem
        $("#modulLateral li.btnEliminar").show();

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

		this.modificado(false);
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
				Llistat.anulaCache();
				if (data.id > 0) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					Detall.array({id: dada_node.item_id, accio: "elimina"});
					Detall.recarregar();
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id == -4){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: "Hi ha tràmits amb la plantilla"});
				} else if (data.id < -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}
		});
	}
};