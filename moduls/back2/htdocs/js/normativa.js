// TIPUS UNITATS ADMINISTRATIVES

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
	//escriptori_previsualitza_elm = $("#escriptori_previsualitza");	
	
	Error = new CError();	
	//Docs = new CDocs();
	Llistat = new CLlistat();	
	Detall = new CDetall();
	
	Llistat.iniciar();
	Cercador.iniciar();
	Detall.iniciar();
	
	$("#item_ua_id").change( function() {
		if ($(this).val() != "") {
			$("#tipoNormativa").text(txtNormativaLocal);
		} else {
			$("#tipoNormativa").text(txtNormativaExterna);
		}
	});
	
	//$.suggeriments();
});

var idiomas = ["ca", "es", "en", "fr", "de"];

// idioma
var pag_idioma = $("html").attr("lang");

var Cercador = {
	iniciar: function() {		
	}
};

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
		$("#cerca_data").mask("99/99/9999");
		$("#cerca_data_butlleti").mask("99/99/9999");		
		
		Llistat.carregar({});		
	}
	
	this.finCargaListado = function( opcions, data ){
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
			ordre_c2 = (ordre_C == "numero") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "tipus") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "tipologia") ? " " + ordre_T : "";
			ordre_c5 = (ordre_C == "data") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "titol") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "numero") {
					txt_per = txtNumero;
				} else if (ordre_C == "tipus") {
					txt_per = txtTipus;
				} else {
					txt_per = txtData;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

			//De momento no habr� ordenaci�n
			/*
			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th numero" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtNumero + "</a></div>";
			codi_cap3 = "<div class=\"th tipus" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtTipus + "</a></div>";
			codi_cap4 = "<div class=\"th data" + ordre_c4 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtData + "</a></div>";
			*/

			codi_cap1 = "<div class=\"th titol" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th numero" + ordre_c2 + "\" role=\"columnheader\">" + txtNumero + "</div>";
			codi_cap3 = "<div class=\"th tipus" + ordre_c3 + "\" role=\"columnheader\">" + txtTipus + "</div>";
			codi_cap4 = "<div class=\"th tipologia" + ordre_c4 + "\" role=\"columnheader\">" + txtTipologia + "</div>";
			codi_cap5 = "<div class=\"th data" + ordre_c4 + "\" role=\"columnheader\">" + txtData + "</div>";						
			
			
			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3 + codi_cap4 + codi_cap5;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			//$(data.json.data.nodes).each(function(i) {
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += '<div class="tr' + parClass + '" role="row">';
				
				codi_taula += '<div class="td titol" role="gridcell">';
				codi_taula += '<input type="hidden" value="' + dada_node.id + '" class="id" />';
				codi_taula += '<a id="normativa_'+dada_node.id+'" href="javascript:void(0);" class="titol">' + dada_node.titulo + '</a>';
				codi_taula += "</div>";
				
				codi_taula += "<div class=\"td numero\" role=\"gridcell\">" + dada_node.numero + "</div>";
				codi_taula += "<div class=\"td tipus\" role=\"gridcell\">" + dada_node.tipo + "</div>";
				codi_taula += "<div class=\"td tipologia\" role=\"gridcell\">" + dada_node.tipologia + "</div>";
				codi_taula += "<div class=\"td data\" role=\"gridcell\">" + dada_node.fecha + "</div>";
				
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
			codi_final = "<p class=\"noItems\">" + txtNoHiHaLlistat + ".</p>";
			
		}
		
		// animacio
		dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.ficha(this);});
			
				// events
				/*if (escriptori_contingut_elm.css("display") != "none") {
					escriptori_contingut_elm.bind("click",Llistat.llansar);
				}*/
				
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
			
			// cercador
			dataVars_cercador = "";
			dataVars_cercador += "&numero=" + $("#cerca_numero").val();
			dataVars_cercador += "&tipus=" + $("#cerca_tipus_normativa").val();
			dataVars_cercador += "&butlleti=" + $("#cerca_butlleti").val();
			dataVars_cercador += "&registre=" + $("#cerca_registre").val();
			dataVars_cercador += "&llei=" + $("#cerca_llei").val();
			dataVars_cercador += "&data=" + $("#cerca_data").val();			
			dataVars_cercador += "&text=" + $("#cerca_text").val();
			dataVars_cercador += "&data_butlleti=" + $("#cerca_data_butlleti").val();			
			dataVars_cercador += "&validacio=" + $("#cerca_validacio").val();			
			dataVars_cercador += "&totesUnitats=" + $("#cerca_totes_unitats").is(':checked');
			dataVars_cercador += "&cercaExternes=" + $("#cerca_externes").is(':checked');			
			dataVars_cercador += "&idUA=" + $("#cerca_ua_id").val();
		
		} else {
			
			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;
			
			// cercador			
			
			dataVars_cercador = "&idUA=" + $("#cerca_ua_id").val();
			
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
		//pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_elm.val(),10);
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
						// error
						Error.llansar();
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
			Llistat.finCargaListado(opcions,Llistat.cacheDatosListado);
		}
	
	}
	
	this.busca = function(){
		// desactivem taula
		//escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
		
		cercador_elm.find("input, select").attr("disabled", "disabled");
		
		resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");
		
		// animacio
		resultats_dades_elm.fadeOut(300, function() {
			// pintem
			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera p�gina.
				Llistat.carregar({cercador: "si"});
				
			});
		});
	}
};

// items array
var Items_arr = new Array();

// detall
function CDetall(){
	this.extend = DetallBase;
	this.extend();
	
	this.iniciar = function() {			
		//redigirimos el m�todo que guarda porque en este caso tambi�n hacemos un upload de archivos
		this.guarda = this.guarda_upload;
		
		// dates
		//$("#item_data").mask("99/99/9999").datepicker({ altField: '#actualDate' });
		//$("#item_data_publicacio").bind("blur",Detall.dataPublicacio).datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });
		
		//$("#item_data_butlleti").mask("99/99/9999");
		//$("#item_data").mask("99/99/9999");
		
		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			$('div.idioma:gt(0)').hide();			
			
			ul_idiomes_elm = escriptori_detall_elm.find("ul.idiomes:first");

			a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			a_primer_elm_class = a_primer_elm.attr("class");
			a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			div_idiomes_elm = escriptori_detall_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			
			ul_idiomes_elm.bind("click",function(e){Detall.idioma(e);});			
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
									
		// modul tipologia
		//$("#item_tipologia").bind("change",Detall.tipologia);
		
		// altres moduls
		modulAfectacions_pare_elm = $("#modulLateral div.modulAfectacions").parents("div.modul:first");
		modulProcediments_pare_elm = $("#modulLateral div.modulProcediments").parents("div.modul:first");
		
		//TODO m�scaras campos			
	},
	

	//Sobreescribe el m�todo guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
		
		// Validamos el formulario
		if( typeof FormulariComprovar != "undefined" ){
					
			FormulariComprovar.llansar();
			
			if (!formComprovacio) {				
				return false;
			}
		}
		
		
		//Preparar los datos de afectaciones relacionadas
		var listaAfectaciones = "{\"listaAfectaciones\" : [";
		var sep = "";
		$("div.modulAfectacions").find("li").each(function() {
			var li_elm = $(this);
			var idNormaAfectada = li_elm.find("input.norma").val();
			var idTipoAfectacion = li_elm.find("input.afectacio").val();
					
			listaAfectaciones += sep + "{ \"afectacioId\" : " + idTipoAfectacion + ", \"normaId\" : " + idNormaAfectada+ ", \"normaNom\" : \"\", \"afectacioNom\" : \"\" } ";
			sep=",";
		});
		listaAfectaciones += "]}";
		$("#afectaciones").val(listaAfectaciones);
		

		//Enviamos el formulario mediante el m�todo ajaxSubmit del plugin jquery.form
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
					Detall.recarregar();
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
				}					
			}
								
		});
		return false;		
		
	},
	
	this.tipologia = function(e) {
		
		if ($(this).val() == "B") {
			$("#gestioTraspas").fadeIn(300);
			modulAfectacions_pare_elm.fadeOut(300);
			modulProcediments_pare_elm.fadeOut(300);
		} else {
			$("#gestioTraspas").fadeOut(300);
			modulAfectacions_pare_elm.fadeIn(300);
			modulProcediments_pare_elm.fadeIn(300);
		}
		
	}
	
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}		
	}
	
	this.nou = function() {
		
		//Anular id
		$("#item_id").val("");
		
		//Ocultar botones
		$("#modulLateral li.btnEliminar").hide();
		
		//Mostrar botones cambio de UA / borrar UA.
		$("#botoneraCambioUA").show();
		$("#botonBorrarUA a").show();		

		//Borrar valores de los campos
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou, div.fila select.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		//Establecer UA por defecto
		$("#item_ua_id").val(idUaActual);
		$("#item_ua_nom").val(nomUaActual);
		
		//Poner tipo Normativa local por defecto
		$("#tipoNormativa").text(txtNormativaLocal);
		
		//Establecer Validaci�n por defecto si el usuario es operador
		if ( $("#rolusuario").val() == "RSC_OPER" ) {
			$("#item_validacio").val(2);
		}
		
		//Resetear upload de archivos			
		for (var i in idiomas) {
			$("#grup_arxiu_actual_" + idiomas[i] + " span").show();
			$("#grup_arxiu_actual_" + idiomas[i] + " input").hide();
			$("#grup_arxiu_actual_" + idiomas[i] + " label.eliminar").hide();
			$("#grup_arxiu_actual_" + idiomas[i] + " a").hide();	
		}
		
		//Ocultar paneles
		$("#modul_procediments, #modul_afectacions").hide();
				
		//escriptori_detall_elm.find("a.elimina, a.previsualitza").find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);		
		//escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").hide();
		escriptori_detall_elm.find(".botonera li.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);		

		
		pro_seleccionats_elm = escriptori_detall_elm.find("div.modulAfectacions div.seleccionats");
		pro_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaAfectacions + ".");
		
		afecta_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.seleccionats");
		afecta_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaProcediments + ".");
				
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
				
		$("#modulLateral p.baix:first").removeClass("iPublicat");
		
		//$("#item_tipologia").val($("#item_tipologia option:eq(0)").val());
		
		$("#gestioTraspas").hide();
		modulAfectacions_pare_elm.show();
		modulProcediments_pare_elm.show();
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				//escriptori_detall_elm.bind("click", Detall.llansar);
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();
	}
	
	this.pintar = function(dades) {	
		
		//escriptori_detall_elm.find("a.elimina, a.previsualitza").show().end().find("h2:first").text(txtDetallTitol);
		
		$("#modul_procediments, #modul_afectacions").show();
		
		$("#modulLateral li.btnEliminar").show();
		$("#modulLateral li.btnEliminar").css("visibility", "visible");
		
		dada_node = dades;
		
		$("#modulLateral p.baix:first").addClass("iPublicat");
							
		$("#item_id").val(dada_node.id);
		$("#item_tipologia").val(dada_node.tipologia);
		$("#item_validacio").val(dada_node.validacio);
		
		//$("#item_estat").val("R");
				
		for (var i in idiomas) {		
			var idioma = idiomas[i];
			
			$("#item_titol_" + idioma).val(nn(dada_node["idioma_" + idioma + "_titol"]));
			$("#item_enllas_" + idioma).val(nn(dada_node["idioma_" + idioma + "_enllac"]));
			$("#item_apartat_" + idioma).val(nn(dada_node["idioma_" + idioma + "_apartat"]));
			$("#item_pagina_inicial_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagini"]));
			$("#item_pagina_final_" + idioma).val(nn(dada_node["idioma_" + idioma + "_pagfin"]));
			$("#item_responsable_" + idioma).val(nn(dada_node["idioma_" + idioma + "_responsable"]));
			$("#item_des_curta_" + idioma).val(nn(dada_node["idioma_" + idioma + "_observacions"]));
			
			$("#item_arxiu_" + idioma).val("");
			$("#grup_arxiu_actual_" + idioma + " input").removeAttr("checked");
			if (dada_node["idioma_" + idioma + "_enllas_arxiu"]) {
				$("#grup_arxiu_actual_" + idioma + " a").show();					
				$("#grup_arxiu_actual_" + idioma + " a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_arxiu_actual_" + idioma + " a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_arxiu_actual_" + idioma + " span").hide();
				$("#grup_arxiu_actual_" + idioma + " input").show();
				$("#grup_arxiu_actual_" + idioma + " label.eliminar").show();
							
			} else {
				$("#grup_arxiu_actual_" + idioma + " span").show();
				$("#grup_arxiu_actual_" + idioma + " input").hide();
				$("#grup_arxiu_actual_" + idioma + " label.eliminar").hide();
				$("#grup_arxiu_actual_" + idioma + " a").hide();			
			}			
			
		}
	
		
		$("#item_numero").val(nn(dada_node.numero));
		$("#item_butlleti_id").val(nn(dada_node.butlleti_id));
		$("#item_butlleti").val(nn(dada_node.butlleti));
		$("#item_registre").val(nn(dada_node.registre));
		$("#item_llei").val(nn(dada_node.llei));
		
		$("#item_data_butlleti").val(nn(dada_node.data_butlleti));
		$("#item_data").val(nn(dada_node.data));
		
		$("#item_tipus").val(nn(dada_node.tipus));
		
		$("#item_ua_id").val(nn(dada_node.idUA));
		$("#item_ua_nom").val(nn(dada_node.nomUA));
		
		if (dada_node.tipus == "B") {
			
			$("#gestioTraspas").show();
			modulAfectacions_pare_elm.hide();
			modulProcediments_pare_elm.hide();
		
		} else {
			
			$("#gestioTraspas").hide();
			modulAfectacions_pare_elm.show();
			modulProcediments_pare_elm.show();
			
			// afectacions
			afecta_seleccionats_elm = escriptori_detall_elm.find("div.modulAfectacions div.seleccionats");
			afecta_nodes = dada_node.afectacions;
			afecta_nodes_size = afecta_nodes.length;
			
			if (afecta_nodes_size == 0) {
				afecta_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaAfectacions + ".");
			} else {
				codi_afecta = "<ul>";
				$(afecta_nodes).each(function() {
					afectacio_node = this;
					codi_afecta += "<li>";
					codi_afecta += "<input type=\"hidden\" value=\"" + afectacio_node.afectacioId + "\" class=\"afectacio\" />";
					codi_afecta += "<input type=\"hidden\" value=\"" + afectacio_node.normaId + "\" class=\"norma\" />";
					codi_afecta += afectacio_node.afectacioNom + ", " + txtAmbLaNorma + " <em>" + afectacio_node.normaNom + "</em>";
					codi_afecta += "</li>";
				});
				codi_afecta += "</ul>";
				txt_afectacions = (afecta_nodes_size == 1) ? txtAfectacio : txtAfectacions;
				afecta_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + afecta_nodes_size + " " + txt_afectacions.toLowerCase() + "</strong>.").after(codi_afecta);
				/*
				if (afecta_nodes_size > 1) {
					afecta_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
				}
				*/
			}
			
			// procediments
			pro_seleccionats_elm = escriptori_detall_elm.find("div.modulProcediments div.seleccionats");
			pro_nodes = dada_node.procediments;
			pro_nodes_size = pro_nodes.length;
			
			if (pro_nodes_size == 0) {
				pro_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaProcediments + ".");
			} else {
				codi_pro = "<ul>";
				$(pro_nodes).each(function() {
					pro_node = this;
					codi_pro += "<li><input type=\"hidden\" value=\"" + pro_node.id + "\" />" + pro_node.nombre + "</li>";
				});
				codi_pro += "</ul>";
				txt_procediments = (pro_nodes_size == 1) ? txtProcediment : txtProcediments;
				pro_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + pro_nodes_size + " " + txt_procediments + "</strong>.").after(codi_pro);
				if (pro_nodes_size > 1) {
					//pro_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
				}
			}
		
		}

		
		if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				$(this).remove();
				
				// array
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				
				escriptori_detall_elm.fadeIn(300, function() {
					// activar
					//escriptori_detall_elm.bind("click", Detall.llansar);
				});
											
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300, function() {
					// activar
					//escriptori_detall_elm.bind("click", Detall.llansar);
				});
			});
		
		}
		
		//Mostrar / ocultar campo de responsable y botonera cambio UA en normativa local/externa
		if ("E" == $("#item_tipologia").val()) {
			$("#tipoNormativa").text(txtNormativaExterna);
			$("#botoneraCambioUA").hide();						
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").show();
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").parent().parent().show();
		} else {
			$("#tipoNormativa").text(txtNormativaLocal);
			$("#botoneraCambioUA").show();
			$("#botonBorrarUA a").hide();
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").hide();
			$("#item_responsable_ca, #item_responsable_es, #item_responsable_en, #item_responsable_de, #item_responsable_fr").parent().parent().hide();
		}		
		
	}
	
	this.elimina = function() {
		
		// missatge
		//Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});				
														
		dataVars = "accio=eliminar&id=" + Llistat.itemID;
				
		// ajax
		$.ajax({
			type: "POST",
			url: pagEliminar,
			data: dataVars,
			dataType: "json",
			beforeSubmit : function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},			
			error: function() {
				
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {	
			
				Llistat.anulaCache();				
				
				if (data.id > -1) {		
					// recarregar
					Detall.recarregar();				
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
					
					// array
					Detall.array({id: data.id, accio: "elimina"});

				} else {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				}
						
			}
		});			
	}
	/*
	this.previsualitza = function() {
		
		escriptori_detall_elm.fadeOut(300, function() {
			
			fitxa_idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			fitxa_ID = escriptori_detall_elm.find("#item_id").val();
			
			previsualitza_url = "http://www.caib.es/govern/sac/fitxa.do?lang=" + fitxa_idiomaSeleccionat + "&codi=636513"; //+ fitxa_ID;
			
			escriptori_previsualitza_elm.find("iframe").attr("src", previsualitza_url).end().fadeIn(300, function() {
			
				$(this).find("a.dePrevisualitzar").one("click", Detall.previsualitzaTorna);
			
			});
		
		});
		
	}
	
	this.previsualitzaTorna = function() {
		
		escriptori_previsualitza_elm.fadeOut(300, function() {
		
			escriptori_detall_elm.fadeIn(300, function() {
				//escriptori_detall_elm.bind("click", Detall.llansar);
			});
		
		});
		
	}
	*/	
};

/*
// documents
var Docs = {
	iniciar: function() {
		
		docs_elm = $("div.documentsRelacionats");
		docs_table_existix = false;
		
		docs_elm.bind("click",Docs.llansar);
		
		codi_table_docs = "<div class=\"table llistat docs\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
		codi_table_docs += "<div class=\"thead\">";
		codi_table_docs += "<div role=\"rowheader\" class=\"tr\">";
		codi_table_docs += "<div role=\"columnheader\" class=\"th nom\">" + txtNom + "</div>";
		codi_table_docs += "<div role=\"columnheader\" class=\"th arxiu\">" + txtArxiu + "</div>";
		codi_table_docs += "<div role=\"columnheader\" class=\"th opcions\"></div>";
		codi_table_docs += "</div>";
		codi_table_docs += "</div>";
		codi_table_docs += "<div class=\"tbody\">";
		codi_table_docs += "</div>";
		codi_table_docs += "</div>";
		
		return codi_table_docs;
		
	},
	llansar: function(e) {
		elm = $(e.target);
		
		if (elm.is("A") || (elm.is("SPAN") && !elm.hasClass("doc"))) {
			
			docs_elm.unbind("click",Docs.llansar);
			
			A_elm = elm.parents("a:first");
			doc_elm = elm.parents("div.documentsRelacionats:first");
			
			if (A_elm.hasClass("afegeix")) {
				
				if (doc_elm.find("div.table").size() == 0) {
			
					elm.parents("p:first").before(codi_table_docs);
					
					docs_table_elm = doc_elm.find("div.table:first");
					docs_table_tbody_elm = docs_table_elm.find("div.tbody");
					
					docs_table_existix = true;
					
				}
				
				tr_num = docs_table_tbody_elm.find("div.tr").size();
				
				par_class = (tr_num%2) ? " par" : "";
				
				codi_docs = "<div role=\"row\" class=\"tr nou" + par_class + "\">";
				codi_docs += "<div role=\"gridcell\" class=\"td nom\">";
				codi_docs += "<input name=\"doc\" type=\"text\" />";
				codi_docs += "</div>";
				codi_docs += "<div role=\"gridcell\" class=\"td arxiu\">";
				codi_docs += "<input name=\"doc\" type=\"file\" />";
				codi_docs += "</div>";
				codi_docs += "<div role=\"gridcell\" class=\"td opcions\">";
				codi_docs += "<a href=\"javascript:;\" class=\"btn lleva\"><span><span>" + txtLleva + "</span></span></a>";
				codi_docs += "</div>";
				codi_docs += "</div>";
				
				docs_table_tbody_elm.append(codi_docs);
				docs_table_tbody_elm.find("div.tr:last").slideDown(300,function() {
					$(this).removeClass("nou");
				});
				
			} else if (A_elm.hasClass("lleva")) {
				
				tr_elm = elm.parents("div.tr:first");
				
				tr_elm.slideUp(300,function() {
					$(this).remove();
					
					tr_num = docs_table_tbody_elm.find("div.tr").size();
					if (tr_num == 0) {
						docs_table_elm.slideUp(300,function() {
							$(this).remove();
							docs_table_existix = false;
						});
					}
					
				});
				
			} else if (A_elm.hasClass("elimina") || A_elm.hasClass("inclou")) {
				
				if (A_elm.hasClass("inclou")) {
					A_elm.removeClass("inclou").addClass("elimina").html("<span><span>" + txtElimina + "</span></span>");
					A_elm.parents("div.tr:first").find("span.doc").removeClass("elimina");
				} else {
					A_elm.removeClass("elimina").addClass("inclou").html("<span><span>" + txtInclou + "</span></span>");
					A_elm.parents("div.tr:first").find("span.doc").addClass("elimina");
				}
				
			}
			
			docs_elm.bind("click",Docs.llansar);
			
		}
		
	}
};

// enllasos
var Enllasos = {
	iniciar: function() {
		
		en_elm = $("div.enllasosRelacionats");
		en_table_existix = false;
		
		en_elm.bind("click",Enllasos.llansar);
		
		codi_table_en = "<div class=\"table llistat enllasos\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
		codi_table_en += "<div class=\"thead\">";
		codi_table_en += "<div role=\"rowheader\" class=\"tr\">";
		codi_table_en += "<div role=\"columnheader\" class=\"th nom\">" + txtAdresa + "</div>";
		codi_table_en += "<div role=\"columnheader\" class=\"th opcions\"></div>";
		codi_table_en += "</div>";
		codi_table_en += "</div>";
		codi_table_en += "<div class=\"tbody\">";
		codi_table_en += "</div>";
		codi_table_en += "</div>";
		
		return codi_table_en;
		
	},
	llansar: function(e) {
		elm = $(e.target);
		
		if (elm.is("A") || (elm.is("SPAN") && !elm.hasClass("doc"))) {
			
			en_elm.unbind("click",Enllasos.llansar);
			
			A_elm = elm.parents("a:first");
			en_elm = elm.parents("div.enllasosRelacionats:first");
			
			if (A_elm.hasClass("afegeix")) {
				
				if (en_elm.find("div.table").size() == 0) {
			
					elm.parents("p:first").before(codi_table_en);
					
					en_table_elm = en_elm.find("div.table:first");
					en_table_tbody_elm = en_table_elm.find("div.tbody");
					
					en_table_existix = true;
					
				}
				
				tr_num = en_table_tbody_elm.find("div.tr").size();
				
				par_class = (tr_num%2) ? " par" : "";
				
				codi_en = "<div role=\"row\" class=\"tr nou" + par_class + "\">";
				codi_en += "<div role=\"gridcell\" class=\"td nom\">";
				codi_en += "<input name=\"enllas\" type=\"text\" />";
				codi_en += "</div>";
				codi_en += "<div role=\"gridcell\" class=\"td opcions\">";
				codi_en += "<a href=\"javascript:;\" class=\"btn lleva\"><span><span>" + txtLleva + "</span></span></a>";
				codi_en += "</div>";
				codi_en += "</div>";
				
				en_table_tbody_elm.append(codi_en);
				en_table_tbody_elm.find("div.tr:last").slideDown(300,function() {
					$(this).removeClass("nou");
				});
				
			} else if (A_elm.hasClass("lleva")) {
				
				tr_elm = elm.parents("div.tr:first");
				
				tr_elm.slideUp(300,function() {
					$(this).remove();
					
					tr_num = en_table_tbody_elm.find("div.tr").size();
					if (tr_num == 0) {
						en_table_elm.slideUp(300,function() {
							$(this).remove();
							en_table_existix = false;
						});
					}
					
				});
				
			} else if (A_elm.hasClass("elimina") || A_elm.hasClass("inclou")) {
				
				if (A_elm.hasClass("inclou")) {
					A_elm.removeClass("inclou").addClass("elimina").html("<span><span>" + txtElimina + "</span></span>");
					A_elm.parents("div.tr:first").find("span.doc").removeClass("elimina");
				} else {
					A_elm.removeClass("elimina").addClass("inclou").html("<span><span>" + txtInclou + "</span></span>");
					A_elm.parents("div.tr:first").find("span.doc").addClass("elimina");
				}
				
			}
			
			en_elm.bind("click",Enllasos.llansar);
			
		}
		
	}
};

// fotos
var Fotos = {
	iniciar: function() {
		
		fotos_elm = $("#fotos");
		fotos_table_existix = false;
		
		fotos_elm.bind("click",Fotos.llansar);
		
		codi_table_fotos = "<div class=\"table fotos\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
		codi_table_fotos += "<div class=\"thead\">";
		codi_table_fotos += "<div role=\"rowheader\" class=\"tr\">";
		codi_table_fotos += "<div role=\"columnheader\" class=\"th img\">" + txtImatge + "</div>";
		codi_table_fotos += "<div role=\"columnheader\" class=\"th petita\">" + txtFotoPetita + "</div>";
		codi_table_fotos += "<div role=\"columnheader\" class=\"th gran ASC\">" + txtFotoGran + "</div>";
		codi_table_fotos += "<div role=\"columnheader\" class=\"th opcions\"></div>";
		codi_table_fotos += "</div>";
		codi_table_fotos += "</div>";
		codi_table_fotos += "<div class=\"tbody\">";
		codi_table_fotos += "</div>";
		codi_table_fotos += "</div>";
		
		return codi_table_fotos;
		
	},
	llansar: function(e) {
		elm = $(e.target);
		
		if (elm.is("A") || (elm.is("SPAN") && !elm.hasClass("foto"))) {
			
			fotos_elm.unbind("click",Fotos.llansar);
			
			A_elm = elm.parents("a:first");
			
			if (A_elm.hasClass("afegeix")) {
				
				if (!fotos_table_existix) {
			
					elm.parents("p:first").before(codi_table_fotos);
					
					fotos_table_elm = $("#fotos div.table");
					fotos_table_tbody_elm = fotos_table_elm.find("div.tbody");
					
					fotos_table_existix = true;
					
				}
				
				tr_num = fotos_table_tbody_elm.find("div.tr").size();
				
				par_class = (tr_num%2) ? " par" : "";
				
				codi_foto = "<div role=\"row\" class=\"tr nou" + par_class + "\">";
				codi_foto += "<div role=\"gridcell\" class=\"td img\">&nbsp;</div>";
				codi_foto += "<div role=\"gridcell\" class=\"td petita\">";
				codi_foto += "<input name=\"foto_petita\" type=\"file\" />";
				codi_foto += "</div>";
				codi_foto += "<div role=\"gridcell\" class=\"td gran\">";
				codi_foto += "<input name=\"foto_gran\" type=\"file\" />";
				codi_foto += "</div>";
				codi_foto += "<div role=\"gridcell\" class=\"td opcions\">";
				codi_foto += "<a href=\"javascript:;\" class=\"btn lleva\"><span><span>" + txtLleva + "</span></span></a>";
				codi_foto += "</div>";
				codi_foto += "</div>";
				
				fotos_table_tbody_elm.append(codi_foto);
				fotos_table_tbody_elm.find("div.tr:last").slideDown(300,function() {
					$(this).removeClass("nou");
				});
				
			} else if (A_elm.hasClass("lleva")) {
				
				tr_elm = elm.parents("div.tr:first");
				
				tr_elm.slideUp(300,function() {
					$(this).remove();
					
					tr_num = fotos_table_tbody_elm.find("div.tr").size();
					if (tr_num == 0) {
						fotos_table_elm.slideUp(300,function() {
							$(this).remove();
							fotos_table_existix = false;
						});
					}
					
				});
				
			} else if (A_elm.hasClass("elimina") || A_elm.hasClass("inclou")) {
				
				if (A_elm.hasClass("inclou")) {
					A_elm.removeClass("inclou").addClass("elimina").html("<span><span>" + txtElimina + "</span></span>");
					A_elm.parents("div.tr:first").find("span.foto").removeClass("elimina");
				} else {
					A_elm.removeClass("elimina").addClass("inclou").html("<span><span>" + txtInclou + "</span></span>");
					A_elm.parents("div.tr:first").find("span.foto").addClass("elimina");
				}
				
			}
			
			fotos_elm.bind("click",Fotos.llansar);
			
		}
		
	}
};
*/
