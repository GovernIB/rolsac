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
	escriptori_previsualitza_elm = $("#escriptori_previsualitza");
						
	// INICIEM
	Llistat = new CLlistat();
	Detall = new CDetall();	
	Error = new CError();
	
	// es un detall?
	window_href = window.location.href;
	if (window_href.indexOf('?/') != -1) {
		
		var vars = [], hash;
		var hashes = window.location.href.slice(window_href.indexOf('?/') + 2).split('&');
		var hashes_size = hashes.length;
		
		if (hashes_size > 0) {
		
			for(var i = 0; i < hashes_size; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			
			Detall.carregar(vars[0]);
			
		}
	
	}	
	
	Llistat.iniciar();
	Cercador.iniciar();
	Detall.iniciar();	
	//Docs.iniciar();
	
});

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
		$("#cerca_fechaCaducidad").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaPublicacion").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaActualizacion").datepicker({ dateFormat: 'dd/mm/yy' });

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
			ordre_c2 = (ordre_C == "publicacio") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "caducitat") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "nom") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "publicacio") {
					txt_per = txtPublicacio;
				} else {
					txt_per = txtCaducitat;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDel + " " + resultatInici + " " + txtAl + " " + resultatFinal + txt_ordenacio + ".</p>";
			
			// De momento no habra ordenacion.
//						codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
//						codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
//						codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
			
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
			codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\">" + txtPublicacio + "</div>";
			codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\">" + txtCaducitat + "</div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				caducat_nom_class = (dada_node.caducat == "S") ? " nomCaducat" : "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td nom" + caducat_nom_class + "\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"procediment_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = (dada_node.caducat == "S") ? " caducat" : "";
				codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.publicacio) + "</div>";
				codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.caducitat) + "</div>";
				
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
				funcionPagina: "Llistat.cambiaPagina",
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
			//dataVars_cercador = "&nom=" + $("#cerca_nom").val();
			//dataVars_cercador += "&codi=" + $("#cerca_codi").val();
			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&estat=" + $("#cerca_estat").val();
			dataVars_cercador += "&familia=" + $("#cerca_familia").val();
			dataVars_cercador += "&iniciacio=" + $("#cerca_iniciacio").val();
			dataVars_cercador += "&tramit=" + $("#cerca_tramit").val();
			dataVars_cercador += "&versio=" + $("#cerca_versio").val();
			dataVars_cercador += "&url=" + $("#cerca_url").val();
			dataVars_cercador += "&indicador=" + $("#cerca_indicador").val();
			dataVars_cercador += "&finestreta=" + $("#cerca_finestreta").val();
			dataVars_cercador += "&taxa=" + $("#cerca_taxa").val();
			dataVars_cercador += "&responsable=" + $("#cerca_responsable").val();
			dataVars_cercador += "&fechaCaducidad=" + $("#cerca_fechaCaducidad").val();
			dataVars_cercador += "&fechaPublicacion=" + $("#cerca_fechaPublicacion").val();
			dataVars_cercador += "&fechaActualizacion=" + $("#cerca_fechaActualizacion").val();
			dataVars_cercador += "&uaFilles=" + $("#cerca_uaFilles").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
			var uaMevesVal = $("#cerca_uaMeves").attr('checked') ? 1 : 0;
			dataVars_cercador += "&uaMeves=" + uaMevesVal;

		} else {

			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;
			
			// cercador
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
};

// items array
var Items_arr = new Array();

// detall
function CDetall(){
	this.extend = DetallBase;
	this.extend();
	
	this.urlPrevisualizar = "http://www.caib.es/govern/sac/visor_proc.do";

	this.iniciar = function() {	
	
		// dates
		//$("#item_data_publicacio, #item_data_caducitat").mask("99/99/9999").datepicker({ altField: '#actualDate' });
		$("#item_data_caducitat").datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });
		$("#item_data_publicacio").bind("blur",Detall.dataPublicacio).datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });

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
			ul_idiomes_elm.bind("click",Detall.idioma);
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
	}
	
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
	}
		
	this.nou = function() {
				
        //escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");
		
		tra_seleccionats_elm = escriptori_detall_elm.find("div.modulTramits div.seleccionats");
		tra_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtTramitNouProcediment + ".").end().find("p.gestiona").hide();
		
		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		
		norma_seleccionats_elm = escriptori_detall_elm.find("div.modulNormativa div.seleccionats");
		norma_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaNormativa + ".");
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
		$("#item_data_publicacio").val(txtImmediat);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		escriptori_contingut_elm.fadeOut(300, function() {
			$("#item_id").val("");
			escriptori_detall_elm.fadeIn(300, function() {
				// activar				
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();
	}		
	
	this.pintar = function(dades) {
		
		escriptori_detall_elm.find("a.elimina, a.previsualitza").show().end().find("h2:first").text(txtDetallTitol);
		
		dada_node = dades;

		$("#item_id").val(dada_node.item_id);
		
		// Bloque de pestanyas de idiomas
		$("#item_nom_ca").val(printStringFromNull(dada_node.ca.nombre));
		$("#item_objecte_ca").val(printStringFromNull(dada_node.ca.resumen));
		$("#item_destinataris_ca").val(printStringFromNull(dada_node.ca.destinatarios));
		$("#item_requisits_ca").val(printStringFromNull(dada_node.ca.requisitos));
		$("#item_presentacio_ca").val(printStringFromNull(dada_node.ca.plazos));
		$("#item_resolucio_ca").val(printStringFromNull(dada_node.ca.resolucion));
		$("#item_notificacio_ca").val(printStringFromNull(dada_node.ca.notificacion));
		$("#item_lloc_ca").val(printStringFromNull(dada_node.ca.lugar));
		$("#item_silenci_ca").val(printStringFromNull(dada_node.ca.silencio));
		$("#item_observacions_ca").val(printStringFromNull(dada_node.ca.observaciones));
		
		$("#item_nom_es").val(printStringFromNull(dada_node.es.nombre));
		$("#item_objecte_es").val(printStringFromNull(dada_node.es.resumen));
		$("#item_destinataris_es").val(printStringFromNull(dada_node.es.destinatarios));
		$("#item_requisits_es").val(printStringFromNull(dada_node.es.requisitos));
		$("#item_presentacio_es").val(printStringFromNull(dada_node.es.plazos));
		$("#item_resolucio_es").val(printStringFromNull(dada_node.es.resolucion));
		$("#item_notificacio_es").val(printStringFromNull(dada_node.es.notificacion));
		$("#item_lloc_es").val(printStringFromNull(dada_node.es.lugar));
		$("#item_silenci_es").val(printStringFromNull(dada_node.es.silencio));
		$("#item_observacions_es").val(printStringFromNull(dada_node.es.observaciones));
		
		$("#item_nom_en").val(printStringFromNull(dada_node.en.nombre));
		$("#item_objecte_en").val(printStringFromNull(dada_node.en.resumen));
		$("#item_destinataris_en").val(printStringFromNull(dada_node.en.destinatarios));
		$("#item_requisits_en").val(printStringFromNull(dada_node.en.requisitos));
		$("#item_presentacio_en").val(printStringFromNull(dada_node.en.plazos));
		$("#item_resolucio_en").val(printStringFromNull(dada_node.en.resolucion));
		$("#item_notificacio_en").val(printStringFromNull(dada_node.en.notificacion));
		$("#item_lloc_en").val(printStringFromNull(dada_node.en.lugar));
		$("#item_silenci_en").val(printStringFromNull(dada_node.en.silencio));
		$("#item_observacions_en").val(printStringFromNull(dada_node.en.observaciones));
		
		$("#item_nom_de").val(printStringFromNull(dada_node.de.nombre));
		$("#item_objecte_de").val(printStringFromNull(dada_node.de.resumen));
		$("#item_destinataris_de").val(printStringFromNull(dada_node.de.destinatarios));
		$("#item_requisits_de").val(printStringFromNull(dada_node.de.requisitos));
		$("#item_presentacio_de").val(printStringFromNull(dada_node.de.plazos));
		$("#item_resolucio_de").val(printStringFromNull(dada_node.de.resolucion));
		$("#item_notificacio_de").val(printStringFromNull(dada_node.de.notificacion));
		$("#item_lloc_de").val(printStringFromNull(dada_node.de.lugar));
		$("#item_silenci_de").val(printStringFromNull(dada_node.de.silencio));
		$("#item_observacions_de").val(printStringFromNull(dada_node.de.observaciones));
		
		$("#item_nom_fr").val(printStringFromNull(dada_node.fr.nombre));
		$("#item_objecte_fr").val(printStringFromNull(dada_node.fr.resumen));
		$("#item_destinataris_fr").val(printStringFromNull(dada_node.fr.destinatarios));
		$("#item_requisits_fr").val(printStringFromNull(dada_node.fr.requisitos));
		$("#item_presentacio_fr").val(printStringFromNull(dada_node.fr.plazos));
		$("#item_resolucio_fr").val(printStringFromNull(dada_node.fr.resolucion));
		$("#item_notificacio_fr").val(printStringFromNull(dada_node.fr.notificacion));
		$("#item_lloc_fr").val(printStringFromNull(dada_node.fr.lugar));
		$("#item_silenci_fr").val(printStringFromNull(dada_node.fr.silencio));
		$("#item_observacions_fr").val(printStringFromNull(dada_node.fr.observaciones));
		// Fin bloque de pestanyas de idiomas
		
		
		marcarOpcionSelect("item_estat", dada_node.item_estat);
		
		$("#item_data_publicacio").val(dada_node.item_data_publicacio);

		$("#item_data_caducitat").val(dada_node.item_data_caducitat);
		
		$("#item_codi").val(dada_node.item_codi);
		
		if (dada_node.item_iniciacio != undefined) {
			$("#item_iniciacio").val(dada_node.item_iniciacio);
		}
		
		if (dada_node.item_organ_id != undefined) {
			$("#item_organ_id").val(dada_node.item_organ_id);
			$("#item_organ").val(dada_node.item_organ_nom);
		}
		
		if (dada_node.item_familia != undefined) {
			$("#item_familia").val(dada_node.item_familia);
		}
		
		if (dada_node.item_familia_id != undefined) {
			$("#item_familia").val(dada_node.item_familia_nom);
		}
		
		$("#item_tramite").val(dada_node.item_tramite);
		
		if (dada_node.item_version != undefined) {
			$("#item_version").val(dada_node.item_version);
		}
		
		$("#item_url").val(dada_node.item_url);
		
		if (dada_node.item_fi_vida_administrativa != undefined) {
			$('#item_fi_vida_administrativa').attr('checked', dada_node.item_fi_vida_administrativa);
		}
		
		if (dada_node.item_finestreta_unica != undefined) {
			$('#item_finestreta_unica').attr('checked', dada_node.item_finestreta_unica);
		}
		
		if (dada_node.item_taxa != undefined) {
			$('#item_taxa').attr('checked', dada_node.item_taxa);
		}
		
		$("#item_notes").val(dada_node.item_notes);
/*		
		// documents
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_nodes = dada_node.documents;
		doc_nodes_size = doc_nodes.length;
		
		if (doc_nodes_size == 0) {
			doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");
		} else {
			
			num_ca = 0;
			num_es = 0;
			num_en = 0;
			num_de = 0;
			num_fr = 0;
			
			codi_doc_ca = "<ul>";
			codi_doc_es = "<ul>";
			codi_doc_en = "<ul>";
			codi_doc_de = "<ul>";
			codi_doc_fr = "<ul>";
			
			$(doc_nodes).each(function() {
				doc_node = this;
				
				codi_doc = "<li>";
				codi_doc += "<div>";
				codi_doc += "<span class=\"doc\"><input type=\"hidden\" value=\"" + doc_node.id + "\" /><a href=\"#\">" + doc_node.nom + "</a></span>";
				codi_doc += "<a class=\"btn lleva\" href=\"javascript:;\"><span><span>" + txtLleva + "</span></span></a>";
				codi_doc += "</div>";
				codi_doc += "</li>";
				
				if (doc_node.idioma == "ca") {
					codi_doc_ca += codi_doc;
					num_ca++;
				} else if (doc_node.idioma == "es") {
					codi_doc_es += codi_doc;
					num_es++;
				} else if (doc_node.idioma == "en") {
					codi_doc_en += codi_doc;
					num_en++;
				} else if (doc_node.idioma == "de") {
					codi_doc_de += codi_doc;
					num_de++;
				} else if (doc_node.idioma == "fr") {
					codi_doc_fr += codi_doc;
					num_fr++;
				}
				
			});
			
			codi_doc_ca += "</ul>";
			codi_doc_es += "</ul>";
			codi_doc_en += "</ul>";
			codi_doc_de += "</ul>";
			codi_doc_fr += "</ul>";
			
			if (num_ca == 0) {
				doc_seleccionats_elm.find("div.ca ul").remove().end().find("div.ca p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_ca == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.ca ul").remove().end().find("div.ca p.info").html(txtHiHa + " <strong>" + num_ca + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_ca);
			}
			
			if (num_es == 0) {
				doc_seleccionats_elm.find("div.es ul").remove().end().find("div.es p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_es == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.es ul").remove().end().find("div.es p.info").html(txtHiHa + " <strong>" + num_es + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_es);
			}
			
			if (num_en == 0) {
				doc_seleccionats_elm.find("div.en ul").remove().end().find("div.en p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_en == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.en ul").remove().end().find("div.en p.info").html(txtHiHa + " <strong>" + num_en + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_en);
			}
			
			if (num_de == 0) {
				doc_seleccionats_elm.find("div.de ul").remove().end().find("div.de p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_de == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.de ul").remove().end().find("div.de p.info").html(txtHiHa + " <strong>" + num_de + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_de);
			}
			
			if (num_fr == 0) {
				doc_seleccionats_elm.find("div.fr ul").remove().end().find("div.fr p.info").text(txtNoHiHaDocuments + ".");
			} else {
				txt_documents = (num_de == 1) ? txtDocument : txtDocuments;
				doc_seleccionats_elm.find("div.fr ul").remove().end().find("div.fr p.info").html(txtHiHa + " <strong>" + num_fr + " " + txt_documents.toLowerCase() + "</strong>.").after(codi_doc_fr);
			}
			
			if (doc_nodes_size > 1) {
				doc_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			}
		}
		
		// tramits
		tra_seleccionats_elm = escriptori_detall_elm.find("div.modulTramits div.seleccionats");
		tra_nodes = dada_node.tramits;
		tra_nodes_size = tra_nodes.length;
		
		if (tra_nodes_size == 0) {
			tra_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaTramits + ".").end().find("p.gestiona").show();
		} else {
			codi_tramits = "<ul>";
			$(tra_nodes).each(function() {
				tramit_node = this;
				codi_tramits += "<li><input type=\"hidden\" value=\"" + tramit_node.id + "\" />" + tramit_node.nom + "</li>";
			});
			codi_tramits += "<ul>";
			txt_tramits = (tra_nodes_size == 1) ? txtTramit : txtTramits;
			tra_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + tra_nodes_size + " " + txt_tramits.toLowerCase() + "</strong>.").after(codi_tramits).end().find("p.gestiona").show();
			if (tra_nodes_size > 1) {
				tra_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			}
		}
		
		// materies
		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
		materies_nodes = dada_node.materies;
		materes_nodes_size = materies_nodes.length;
		
		mat_llistat_elm.find("input").removeAttr("checked");
		
		if (materes_nodes_size == 0) {
			mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		} else {
			codi_materies = "<ul>";
			$(materies_nodes).each(function() {
				materia_node = this;
				codi_materies += "<li><input type=\"hidden\" value=\"" + materia_node.id + "\" />" + materia_node.nom + "</li>";
				mat_llistat_elm.find("input[value=" + materia_node.id + "]").attr("checked","checked");
			});
			codi_materies += "<ul>";
			txt_materies = (materes_nodes_size == 1) ? txtMateria : txtMateries;
			mat_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + materes_nodes_size + " " + txt_materies + "</strong>.").after(codi_materies);
		}
		
		// normatives
		norma_seleccionats_elm = escriptori_detall_elm.find("div.modulNormativa div.seleccionats");
		normatives_nodes = dada_node.normatives;
		normatives_nodes_size = normatives_nodes.length;
		
		if (normatives_nodes_size == 0) {
			norma_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		} else {
			codi_normatives = "<ul>";
			$(normatives_nodes).each(function() {
				normativa_node = this;
				codi_normatives += "<li><input type=\"hidden\" value=\"" + normativa_node.id + "\" />" + normativa_node.nom + "</li>";
			});
			codi_normatives += "<ul>";
			txt_materies = (normatives_nodes_size == 1) ? txtNormativa : txtNormatives;
			norma_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + normatives_nodes_size + " " + txt_materies.toLowerCase() + "</strong>.").after(codi_normatives);
			if (normatives_nodes_size > 1) {
				norma_seleccionats_elm.find("ul").sortable({ axis: 'y', cursor: 'url(imgs/cursor/grabbing.cur), move' }).find("li").css("cursor","url(imgs/cursor/grab.cur), move");
			}
		}
*/		
		
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
				
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {

				Llistat.anulaCache();
			
				Missatge.cancelar();
				
				if (data.id > 0) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id == -2){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
								
				// array
				Detall.array({id: dada_node.item_id, accio: "elimina"});
				
				// recarregar
				Detall.recarregar();

			}
		});
	}
};