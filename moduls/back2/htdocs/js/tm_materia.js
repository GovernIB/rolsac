//TM materia

$(document).ready(function() {
	
	// Listener para guardado de módulos laterales vía AJAX.
	jQuery(".lista-simple").click(function() {
		
		var element = $(this).parent().parent().find("li");
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimple.guardar(element, url, id);
		
	});
	
	ListaSimple = new CListaSimple();
	
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
	Estadistica = new ModulEstadistiques();
	Detall.iniciar();
	
	// Mostrar detall
	var itemACarregar = itemAEditar();

	if (itemACarregar > 0) 
		Detall.carregar(itemACarregar);

	Llistat.iniciar();

	// Datos traductor
	CAMPOS_TRADUCTOR_MATERIA = ["item_nom_", "item_descripcio_", "item_paraules_clau_"];
	DATOS_TRADUCIDOS_MATERIA = ["nombre", "descripcion", "palabrasclave"];
	
});

/**
 * (amartin) Explicación de extensión de clase:
 * 
 * Necesitamos extender la clase ListaSimple ya que el módulo lateral de unidades administrativas (modul_unitats_administratives.js)
 * dentro de la gestión de materias no sólo consta de valor para el registro principal (materia => main-item-id) y para sus N UAs
 * asociadas (ua => related-item-id), sino que también hay un tercer campo, que es el checkbox de UA principal para esa materia.
 * Con la extensión de la clase sobreescribimos los métodos para realizar el guardado y para obtener el dato adicional de UA principal.
 */
function CListaSimple() {
	
	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaSimple;
	this.extend();
	
	var that = this;
	
	this._guardar = this.guardar;
	
	this.guardar = function(element, url, id) {
		
		this._guardar(element, url, id);
		
		// XXX: ocultación del botón de guardado tras solicitar guardado AJAX (si el invoker es el guardado de iconos de la materia).
		urlGuardarIconos = "/materies/guardarIconosRelacionados.do";
		if ( url.indexOf(urlGuardarIconos) != -1 ) {
			
			// Objeto declarado en modul_icones.js
			if (typeof EscriptoriPare != 'undefined')
				EscriptoriPare.deshabilitarBotonGuardar();
			
		}
		
	};
	
	this._getFilters = this.getFilters;
	
	this.getFilters = function(elements, id) {
		
		if (debug)
			console.log("Entrando en CListaSimple.getFilters");
		
		var filters = this._getFilters(elements, id);
		
		if (elements.length > 0) {
			
			elements.each(function() {
				
				// Obtenemos el radiobutton de cada UA y comprobamos si está marcado.
				if ( $(this).find("input[type='radio']").length == 1 ) {
				
					var radio = $(this).find("input[type='radio']");
					
					// Si está seleccionado, lo pasamos por parámetro
					if ( radio.is(':checked') )
						filters += "&itemUAPrincipal=" + radio.val();
				
				}
				
			});
						
		}
				
		if (debug)
			console.log("Saliendo de CListaSimple.getFilters");
		
		return filters;
				
	};
	
};

//idioma
var pag_idioma = $("html").attr("lang");

//minim cercador
var numCercadorMinim = 0;

//paginacio
var paginacio_marge = 4;

//llistat
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

			if (resultats_total > numCercadorMinim)// minim per cercador
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

				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";

				if (ordre_C == "descripcio")
					txt_per = txtDescripcio;

				else // nom
					txt_per = txtLlistaItem;

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";

			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th descripcio" + ordre_c2 + "\" role=\"columnheader\">" + txtCodiEstandar + "</div>";

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
				parClass = (i%2) ? " par" : "";

				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";

				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"materia_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nom, txtSinValor) + "</a>";
				codi_taula += "</div>";				
				codi_taula += "<div class=\"td codi_estandar\" role=\"gridcell\">" + printStringFromNull(dada_node.codi_estandar, txtSinValor) + "</div>";

				codi_taula += "</div>";
				
			});

			codi_taula += "</div>";
			codi_taula += "</div>";

			if ($.browser.opera)
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");

			multipagina.init({ // Instanciamos el navegador multipágina.
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "Llistat.cambiaPagina"
			});					

			codi_navegacio = multipagina.getHtml();

			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;

		} else { // no hi ha items
			
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";

		}

		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			
			dades_elm.html(codi_final).fadeIn(300, function() { // pintem

				// Asociamos el evento onclick a los elementos de la lista para
				// poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.ficha(this);});

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
				
				if (!a_enllas) // missatge
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

//items array
var Items_arr = new Array();

//detall
function CDetall() {
	
	// Activa mensajes de debug.
	var debug = false;
	
	this.extend = DetallBase;
	this.extend();
	this.activar = 0;

	var that = this;

	this.tipusEstadistica = 'materia';

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
		
		jQuery('#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr').change(function(){
			jQuery('#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr').val( jQuery(this).val() );
		});
		
		jQuery("#item_destacada,#item_destacada_es,#item_destacada_en,#item_destacada_de,#item_destacada_fr").change(function(){
			jQuery("#item_destacada,#item_destacada_es,#item_destacada_en,#item_destacada_de,#item_destacada_fr").attr("checked", jQuery(this).is(":checked"));
		});

		// boton de traducir
		jQuery("#botoTraduirMateria").unbind("click").bind("click", function() {
			Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
		});

		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		

		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;
		
		if (debug)
			console.log("Saliendo de CDetall.iniciar");
		
	};

	this.traduirWrapper = function () {
		
		if (debug)
			console.log("Entrando en CDetall.traduirWrapper");
		
		that.traduir(pagTraduirMateria, CAMPOS_TRADUCTOR_MATERIA, DATOS_TRADUCIDOS_MATERIA);
		
		if (debug)
			console.log("Saliendo de CDetall.traduirWrapper");
		
	};

	this.nou = function() {
		
		if (debug)
			console.log("Entrando en CDetall.nou");
		
		//Ocultar paneles y campos
		jQuery("#modul_icones").hide();
		jQuery("#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr").closest("div.fila").hide();
		jQuery("#modulEstadistiques").hide();

		$("#item_id").val("");
		$('#formGuardar input').each(limpiarCampo);

		// Resetear upload de archivos
		for (var i in idiomas) {
			
			limpiarArchivoMultiidioma("item_distribucion", idiomas[i]);
			limpiarArchivoMultiidioma("item_normativa", idiomas[i]);
			limpiarArchivoMultiidioma("item_contenido", idiomas[i]);
			
		}
		
		limpiarArchivo("item_foto");
		limpiarArchivo("item_icona");
		limpiarArchivo("item_icona_gran");

		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);

		ModulUnitatAdministrativa.nuevo();

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
			
			if (debug)
				console.log("Saliendo de CDetall.guarda_upload");
			
			return false;
		
		}

		// Guardamos la relación con las UAs.
		$("#llistaUnitatsAdministratives").val(ModulUnitatAdministrativa.listaUnidadesAdministrativas());

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

		// Mostrar paneles
		jQuery("#modul_icones").show();

		$("#item_id").val(dades.item_id);

		// Bloque de pestanyas de idiomas
		var idioma;
		for (var i in idiomas) {
			
			idioma = idiomas[i];
			$("#item_nom_" + idioma).val(printStringFromNull(dades[idioma]["item_nombre"]));
			$("#item_descripcio_" + idioma).val(printStringFromNull(dades[idioma]["item_descripcion"]));
			$("#item_paraules_clau_" + idioma).val(printStringFromNull(dades[idioma]["item_palabras_clave"]));
			pintarArchivoMultiidioma("item_distribucion", idioma, dades);
			pintarArchivoMultiidioma("item_normativa", idioma, dades);
			pintarArchivoMultiidioma("item_contenido", idioma, dades);
			
		}
		// Fin bloque de pestanyas de idiomas

		jQuery("#item_codi_hita").val(dades.item_codi_hita);
		jQuery("#item_codi_estandard").val(dades.item_codi_estandard);
		jQuery("#item_codi_estandard").change();

		pintarArchivo("item_foto", dades);
		pintarArchivo("item_icona", dades);
		pintarArchivo("item_icona_gran", dades);

		// mostrem
		$("#modulLateral li.btnEliminar").show();

		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				Detall.array({id: dades.item_id, accio: "guarda", dades: dades});
				escriptori_detall_elm.fadeIn(300);
				
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);				
			});
			
		}

		this.modificado(false);
		
		// Objeto declarado en modul_icones.js
		if (typeof EscriptoriPare != 'undefined')
			EscriptoriPare.deshabilitarBotonGuardar();
		
		if (debug)
			console.log("Saliendo de CDetall.pintar");
		
	};

	this.elimina = function() {
		
		if (debug)
			console.log("Entrando en CDetall.elimina");

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
					
				} else if (data.id == -1) {
					
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
					
				} else if (data.id < -1) {
					
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacioMateries});
					
				}
				
			}
			
		});
		
		if (debug)
			console.log("Saliendo de CDetall.elimina");
		
	};
	
	this.pintarModulos = function(dades) {
		
		if (debug)
			console.log("Entrando en CDetall.pintarModulos");

		ModulIcones.inicializarIcones(dades.icones);
		ModulUnitatAdministrativa.inicializarUnidadesAdministrativas(dades.uas);

		// Añadimos opción de marcar UA principal en el módulo lateral de UAs de este mantenimiento.
		$('.modulUnitatAdministratives .listaOrdenable li div.unitatAdministrativa').each(function() {

			var inputIdUA = $(this).find('.unitatAdministrativa_id:first');
			var idUAPrincipal = dades.item_ua_principal;
			var checked = (idUAPrincipal == inputIdUA.val()) ? 'checked="checked"' : '';

			var inputRadio = '<p>' + 
			'<input type="radio" id="item_ua_principal_' + inputIdUA.val() + '" name="item_ua_principal" value="' + inputIdUA.val() + '" ' + checked + ' />' + 
			'<label for="item_ua_principal_' + inputIdUA.val() + '">Principal?</label>' + 
			'</p>';

			$(this).append(inputRadio);

		});
		
		if (debug)
			console.log("Saliendo de CDetall.pintarModulos");

	};

};