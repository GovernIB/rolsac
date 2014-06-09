// TM Agrupacio Fets Vitals

$(document).ready(function() {
	
	// Listener para guardado de módulos laterales vía AJAX.
	jQuery(".lista-simple").click(function() {
		
		var element = $(this).parent().parent().find("li");
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleGenerica.guardar(element, url, id);
		
	});
	
	// Listener para guardado de módulos laterales vía AJAX.
	jQuery(".lista-simple-hechosVitales").click(function() {
		
		var element = $(this).parent().parent().find("li");
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleHechosVitales.guardar(element, url, id);
		
	});
	
	ListaSimpleGenerica = new ListaSimple();
	ListaSimpleHechosVitales = new CListaSimpleHechosVitales();
	
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
	
	CAMPOS_TRADUCTOR_AGRUPACIO_FET_VITAL = ["item_nom_", "item_descripcio_", "item_paraules_clau_"];
	DATOS_TRADUCIDOS_AGRUPACIO_FET_VITAL = ["nombre", "descripcion", "palabrasclave"];
	
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
		
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			// minim per cercador
			if (resultats_total > numCercadorMinim)
				opcions_elm.find("li.C").animate({duration: "slow", width: 'show'}, 300);
			
			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			
			if (resultats_total % pag_Res > 0)
				ultimaPag++;

			if (pag_Pag > ultimaPag)
				pag_Pag = ultimaPag;
			
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
				
				if (ordre_C == "nom")
					txt_per = txtLlistaItem;
				
				else if (ordre_C == "codiEstandard")
					txt_per = txtPublicacio;
				
				else
					txt_per = txtCaducitat;
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDela + " " + resultatInici + " " + txtAla + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";
			
			codi_cap1 = "<div class=\"th agrupacioFV" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
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
			$(data.nodes).each(function(i) {
				
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				codi_taula += "<div class=\"td agrupacioFV\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"agrupacioFV_"+dada_node.id+"\" href=\"javascript:;\" class=\"agrupacioFV\">" + printStringFromNull(dada_node.nom, txtSinValor) + "</a>";
				codi_taula += "</div>";				
				codi_taula += "<div class=\"td enllas\" role=\"gridcell\">" + printStringFromNull(dada_node.codiEstandard, txtSinValor) + "</div>";
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
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click", function(){ Llistat.ficha(this); });
							
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
			dataVars_cercador += "&textes=" + $("#cerca_texte").val();
			
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
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");	
		
		// Afegir nodes a la llista de fets vitals
		$('#afegeixFetVital').unbind("click").bind("click", function() {
	    	$('#popFetVital').css({display:"block"});
	    });
		
		$('#tancaFetVital').unbind("click").bind("click", function() {
	    	$('#popFetVital').css({display:"none"});
	    });
		
		// Boto afegir node
		$('#addFetVital').unbind("click").bind("click",function() {
			
	    	var id = jQuery('#item_fetVital_relacionat').val();
	    	
	    	if (id != '' || id > 0 ) {
	    		
	    		$('#popFetVital').css({display:"none"});
	    		
	    		var vfItem = new Object();
		    	vfItem['id'] = jQuery('#item_fetVital_relacionat').val();
		    	vfItem['nombre'] = jQuery('#item_fetVital_relacionat option:selected').text();
		    	var ordenItem = jQuery('#modul_fetsVitals ul li:last input.fetVital_orden').val();
		    	
		    	if (typeof ordenItem == 'undefined')
		    		vfItem['orden'] = 0;
		    	else
		    		vfItem['orden'] = parseInt(ordenItem) + 1;
		    	
		    	// (amartin): Campos para guardado AJAX. Por algún motivo, el agregarItem no está sobreescrito en
		    	// modul_fet_vital.js y se hizo aquí esta chapuza.
		    	vfItem['idMainItem'] = jQuery('#item_id').val();
		    	vfItem['idRelatedItem'] = vfItem['id'];
		    	
		    	ModulFetVital.agregaItem(vfItem);
		    	ModulFetVital.inicializarFetsVitals();
		    	
		    	jQuery('#item_fetVital_relacionat').each(limpiarCampo);
		    	
	    	} else {
	    		
	    		Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: errorFetVital});
	    		
	    	} 
	    	
	    });
		
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").change(function(){
            jQuery("#item_codi_estandard,#item_codi_estandard_es,#item_codi_estandard_en,#item_codi_estandard_de,#item_codi_estandard_fr").val( jQuery(this).val() );
        });
        
        // boton de traducir
        jQuery("#botoTraduirAHV").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });
		
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos				
		this.guarda = this.guarda_upload;
		
		if (debug)
			console.log("Saliendo de CDetall.iniciar");
		
	};
	
	this.traduirWrapper = function() {
		
		if (debug)
			console.log("Entrando en CDetall.traduirWrapper");
		
		that.traduir(pagTraduirAgrupacioFetsVitals, CAMPOS_TRADUCTOR_AGRUPACIO_FET_VITAL, DATOS_TRADUCIDOS_AGRUPACIO_FET_VITAL);
		
		if (debug)
			console.log("Saliendo de CDetall.traduirWrapper");
		
	};
	
	//Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
		
		if (debug)
			console.log("Entrando en CDetall.guarda_upload");
		
		$("#llistaFetsVitals").val(ModulFetVital.listaFetsVitals());
		
		// Validamos el formulario
		if (!that.formulariValid()) {
			
			if (debug)
				console.log("Saliendo de CDetall.guarda_upload");
			
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
		
		//Ocultar paneles
		jQuery("#modul_fetsVitals").hide();
		
		ModulFetVital.nuevo();
		
        $("#item_id").val("");
                
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		$("#modulPrincipal select").each(limpiarCampo);
		
		//Resetear upload de archivos
		limpiarArchivo("item_foto");
		limpiarArchivo("item_icona");
		limpiarArchivo("item_icona_gran");
		
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
		
		jQuery("#modul_fetsVitals").show();
		
		escriptori_detall_elm.find("a.elimina").show().end().find("h2:first").text(txtDetallTitol);
		
		dada_node = dades;
		$("#item_id").val(dada_node.item_id);
		jQuery("#item_codi_estandard").val(dada_node.item_codi_estandard);
        jQuery("#item_codi_estandard").change();

		$("#item_public_objectiu").val(dada_node.item_public_objectiu).attr('selected',true);
		
		//Foto
		pintarArchivo("item_foto", dada_node);							
		
		//Icona
		pintarArchivo("item_icona", dada_node);	
		
		//FotoGran
		pintarArchivo("item_icona_gran", dada_node);
		
		// Bloque de pestanyas de idiomas
		for (var i in idiomas) {
			
			var idioma = idiomas[i];
			$("#item_nom_" + idioma).val(printStringFromNull(dada_node[idioma]["nombre"]));
			$("#item_descripcio_" + idioma).val(printStringFromNull(dada_node[idioma]["descripcion"]));
			$("#item_paraules_clau_" + idioma).val(printStringFromNull(dada_node[idioma]["palabrasclave"]));
			
		}
		// Fin bloque de pestanyas de idiomas
		
		
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
		
		ModulFetVital.inicializarFetsVitals(dades.fetsVitals);
		
		if (debug)
			console.log("Saliendo de CDetall.pintarModulos");
		
	};
	
};