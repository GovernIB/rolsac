// TM Edifici

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
	escriptori_previsualitza_elm = $("#escriptori_previsualitza");

	// INICIEM
	Llistat = new CLlistat();
	Detall = new CDetall();	
	
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
			ordre_c2 = (ordre_C == "idRemot") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "direccio") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "descripcio") {
					txt_per = txtPublicacio;
				} else {
					txt_per = txtCaducitat;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".</p>";
			
			// De momento no habra ordenacion.
// 			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			
			codi_cap1 = "<div class=\"th edifici" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th enllas" + ordre_c2 + "\" role=\"columnheader\">" + txtDescripcio + "</div>";

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

				codi_taula += "<div class=\"td edifici\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"edifici_"+dada_node.id+"\" href=\"javascript:;\" class=\"edifici\">" + printStringFromNull(dada_node.direccio, txtSinValor) + "</a>";
				codi_taula += "</div>";				
				codi_taula += "<div class=\"td enllas\" role=\"gridcell\">" + printStringFromNull(dada_node.descripcio, txtSinValor) + "</div>";
				
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
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC,
		// DESC), ordreCamp (tipus, carrec, tractament)
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;		
		
		dataVars = "";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;

			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&descripcio=" + $("#cerca_descripcio").val();
			dataVars_cercador += "&direccio=" + $("#cerca_direccio").val();
			dataVars_cercador += "&codiPostal=" + $("#cerca_codiPostal").val();
			dataVars_cercador += "&poblacio=" + $("#cerca_poblacio").val();
			dataVars_cercador += "&telefon=" + $("#cerca_telefon").val();
			dataVars_cercador += "&fax=" + $("#cerca_fax").val();
			dataVars_cercador += "&email=" + $("#cerca_email").val();
			
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
        jQuery("#item_direccio,#item_direccio_es,#item_direccio_en,#item_direccio_de,#item_direccio_fr").change(function(){
            jQuery("#item_direccio,#item_direccio_es,#item_direccio_en,#item_direccio_de,#item_direccio_fr").val( jQuery(this).val() );
        });
        jQuery("#item_codi_postal,#item_codi_postal_es,#item_codi_postal_en,#item_codi_postal_de,#item_codi_postal_fr").change(function(){
            jQuery("#item_codi_postal,#item_codi_postal_es,#item_codi_postal_en,#item_codi_postal_de,#item_codi_postal_fr").val( jQuery(this).val() );
        });
        jQuery("#item_poblacio,#item_poblacio_es,#item_poblacio_en,#item_poblacio_de,#item_poblacio_fr").change(function(){
            jQuery("#item_poblacio,#item_poblacio_es,#item_poblacio_en,#item_poblacio_de,#item_poblacio_fr").val( jQuery(this).val() );
        });
        jQuery("#item_latitud,#item_latitud_es,#item_latitud_en,#item_latitud_de,#item_latitud_fr").change(function(){
            jQuery("#item_latitud,#item_latitud_es,#item_latitud_en,#item_latitud_de,#item_latitud_fr").val( jQuery(this).val() );
        });
        jQuery("#item_longitud,#item_longitud_es,#item_longitud_en,#item_longitud_de,#item_longitud_fr").change(function(){
            jQuery("#item_longitud,#item_longitud_es,#item_longitud_en,#item_longitud_de,#item_longitud_fr").val( jQuery(this).val() );
        });
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");	
		
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos				
		this.guarda = this.guarda_upload;
	}
	
	//Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
				
		$("#llistaUnitatsAdministratives").val(ModulUnitatAdministrativa.listaUnidadesAdministrativas());
		
		// Validamos el formulario
		if(!that.formulariValid()){
			return false;
		}
		
		//Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
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

	this.nou = function() {
        $("#item_id").val("");
        
        ModulUnitatAdministrativa.nuevo();
        
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		
		//Resetear upload de archivos			
		$("#grup_item_foto_petita span").show();
		$("#grup_item_foto_petita input").hide();
		$("#grup_item_foto_petita label.eliminar").hide();
		$("#grup_item_foto_petita a").hide();
		
		$("#grup_item_foto_gran span").show();
		$("#grup_item_foto_gran input").hide();
		$("#grup_item_foto_gran label.eliminar").hide();
		$("#grup_item_foto_gran a").hide();
		
		$("#grup_item_planol span").show();
		$("#grup_item_planol input").hide();
		$("#grup_item_planol label.eliminar").hide();
		$("#grup_item_planol a").hide();
		
		
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				itemID_ultim = 0;
			});
		});

		this.actualizaEventos();
	}		
	
	this.pintar = function(dades) {
		escriptori_detall_elm.find("a.elimina").show().end().find("h2:first").text(txtDetallTitol);
		
		dada_node = dades;
		$("#item_id").val(dada_node.item_id);
		jQuery("#item_direccio").val(dada_node.item_direccio);
        jQuery("#item_direccio").change();
		jQuery("#item_codi_postal").val(dada_node.item_codi_postal);
        jQuery("#item_codi_postal").change();
		jQuery("#item_poblacio").val(dada_node.item_poblacio);
        jQuery("#item_poblacio").change();
		
		$("#item_telefon").val(dada_node.item_telefon);
		$("#item_fax").val(dada_node.item_fax);
		$("#item_email").val(dada_node.item_email);
		
		jQuery("#item_longitud").val(dada_node.item_longitud);
        jQuery("#item_longitud").change();
		jQuery("#item_latitud").val(dada_node.item_latitud);
        jQuery("#item_latitud").change();
		
		
		//Fotos
		//FotoPetita
		$("#item_foto_petita").val("");
		$("#grup_item_foto_petita input").removeAttr("checked");
		if (dada_node["item_foto_petita_enllas_arxiu"]) {
			
			$("#grup_item_foto_petita a").show();					
			
			$("#grup_item_foto_petita a").attr("href", pagArrel + dada_node["item_foto_petita_enllas_arxiu"]);
			$("#grup_item_foto_petita a").text(dada_node["item_foto_petita"]);
			
			$("#grup_item_foto_petita span").hide();
			$("#grup_item_foto_petita input").show();
			$("#grup_item_foto_petita label.eliminar").show();
						
		} else {
			$("#grup_item_foto_petita span").show();
			$("#grup_item_foto_petita input").hide();
			$("#grup_item_foto_petita label.eliminar").hide();
			$("#grup_item_foto_petita a").hide();			
		}
		
		
		//FotoGran
		$("#item_foto_gran").val("");
		$("#grup_item_foto_gran input").removeAttr("checked");
		if (dada_node["item_foto_gran_enllas_arxiu"]) {
			
			$("#grup_item_foto_gran a").show();
			
			$("#grup_item_foto_gran a").attr("href", pagArrel + dada_node["item_foto_gran_enllas_arxiu"]);
			$("#grup_item_foto_gran a").text(dada_node["item_foto_gran"]);
			
			$("#grup_item_foto_gran span").hide();
			$("#grup_item_foto_gran input").show();
			$("#grup_item_foto_gran label.eliminar").show();
						
		} else {
			$("#grup_item_foto_gran span").show();
			$("#grup_item_foto_gran input").hide();
			$("#grup_item_foto_gran label.eliminar").hide();
			$("#grup_item_foto_gran a").hide();			
		}
		
		//Planol
		$("#item_planol").val("");
		$("#grup_item_planol input").removeAttr("checked");
		if (dada_node["item_planol_enllas_arxiu"]) {
			
			$("#grup_item_planol a").show();					
			
			$("#grup_item_planol a").attr("href", pagArrel + dada_node["item_planol_enllas_arxiu"]);
			$("#grup_item_planol a").text(dada_node["item_planol"]);
			
			$("#grup_item_planol span").hide();
			$("#grup_item_planol input").show();
			$("#grup_item_planol label.eliminar").show();
						
		} else {
			$("#grup_item_planol span").show();
			$("#grup_item_planol input").hide();
			$("#grup_item_planol label.eliminar").hide();
			$("#grup_item_planol a").hide();			
		}
		
		// Bloque de pestanyas de idiomas
		for (var i in idiomas) {
			var idioma = idiomas[i];
			$("#item_descripcio_" + idioma).val(printStringFromNull(dada_node[idioma]["descripcion"]));
		}
		// Fin bloque de pestanyas de idiomas
		
		
		ModulUnitatAdministrativa.inicializarUnidadesAdministrativas(dada_node.unitatsAdm);
		
		
        // mostrem
        $("#modulLateral li.btnEliminar").show();
        
		if ($("#carregantDetall").size() > 0) {
			$("#carregantDetall").fadeOut(300, function() {
				$(this).remove();
				// array
				Detall.array({id: dada_node.item_id, accio: "guarda", dades: dada_node});
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