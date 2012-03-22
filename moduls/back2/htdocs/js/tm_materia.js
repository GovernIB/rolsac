// TM materia

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
	Estadistica = new ModulEstadistiques();
	
    Detall.iniciar();
    // Mostrar detall?
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	}
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
			ordre_c2 = (ordre_C == "descripcio") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "descripcio") {
					txt_per = txtDescripcio;
				} else { // nom
					txt_per = txtLlistaItem;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".</p>";
			
			// De momento no habra ordenacion.
// 			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			
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
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
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
				
			});
		});
	}
	
	this.carregar = function(opcions) {
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;		
		
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
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// ajax
		if ( ( modoListado && !Llistat.cacheDatosListado ) || modoBuscador ){
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
					if( modoListado ){											
						Llistat.cacheDatosListado = data;
					}
				}
			});
		}else{
			Llistat.finCargaListado(opcions, Llistat.cacheDatosListado);
		}
	}
};

// items array
var Items_arr = new Array();

// detall
function CDetall(){
	this.extend = DetallBase;
	this.extend();
	
    var that = this;

	this.tipusEstadistica = 'materia';
    
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
        jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").change(function(){
            jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").val( jQuery(this).val() );
        });        
        jQuery('#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr').change(function(){
            jQuery('#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr').val( jQuery(this).val() );
        });
        jQuery("#item_destacada,#item_destacada_es,#item_destacada_en,#item_destacada_de,#item_destacada_fr").change(function(){
            jQuery("#item_destacada,#item_destacada_es,#item_destacada_en,#item_destacada_de,#item_destacada_fr").attr("checked", jQuery(this).is(":checked"));
        });
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
		
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;
	}

	this.nou = function() {
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
				Llistat.cacheDatosListado = null;
				
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
		
		// Rellenar y mostrar el select de uas si hay uas
		if (dades.item_uas_materia.length > 0) {
			var selected;
			var $ua_select = $('#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr');
			$ua_select.find('option').remove();
			$ua_select.append('<option value="0">' + txtTria + '</option>');
			for (var i in dades.item_uas_materia) {
				selected = dades.item_ua_principal == dades.item_uas_materia[i].id ? ' selected="selected"' : '';
				$ua_select.append('<option value="' + dades.item_uas_materia[i].id + '"' + selected + '>' + dades.item_uas_materia[i].nom + '</option>');
			}
			$ua_select.closest("div.fila").show();
	        $ua_select.change();
	        $('#item_destacada').attr('checked', dades.item_destacada).change();
		} else {
            jQuery("#item_ua_principal,#item_ua_principal_es,#item_ua_principal_en,#item_ua_principal_de,#item_ua_principal_fr").closest("div.fila").hide();
        }
		
		ModulIcones.inicializarIcones(dades.icones);
		
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
				} else if (data.id < -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}
		});
	}
};