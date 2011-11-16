// Fitxes informatives

$(document).ready(function() {
	
	jQuery("#btnInsertar").bind("click",function(){Detall.modificado();});
	
	// elements
	opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
	
	multipagina = new Multipagina();
	
	pagPagina_llistat_elm = resultats_llistat_elm.find("input.pagPagina");
	pagResultats_llistat_elm = resultats_llistat_elm.find("input.pagResultats");
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
	
////	// es un detall?
////	window_href = window.location.href;
////	if (window_href.indexOf('?/') != -1) {
////		
////		var vars = [], hash;
////		var hashes = window.location.href.slice(window_href.indexOf('?/') + 2).split('&');
////		var hashes_size = hashes.length;
////		
////		if (hashes_size > 0) {
////		
////			for(var i = 0; i < hashes_size; i++) {
////				hash = hashes[i].split('=');
////				vars.push(hash[0]);
////				vars[hash[0]] = hash[1];
////			}
////			
////			Detall.carregar(vars[0]);
////			
////		}
////	
////	}
//	
//	Llistat.iniciar();
//	Cercador.iniciar();
//	Detall.iniciar();

	// Mostrar llistat o detall
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	} else {
        Detall.iniciar();
		// Cercador.iniciar();
	}
    Llistat.iniciar();
	
	
	//Fotos.iniciar();
	Docs.iniciar();
	//Enllasos.iniciar();
	
	$.suggeriments();
	
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
		$("#cerca_fechaPublicacion").datetimepicker({ timeFormat: 'hh:mm' });
		$("#cerca_fechaActualizacion").datetimepicker({ timeFormat: 'hh:mm' });
	
		Llistat.carregar({});
		
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
			
			resultatInici = ((pag_Pag*pag_Res)+1);
			resultatFinal = ((pag_Pag*pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag*pag_Res) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "titulo") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "fechaPublicacion") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "fechaCaducidad") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "titulo") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "fechaPublicacion") {
					txt_per = txtPublicacio;
				} else {
					txt_per = txtCaducitat;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
			
			/* De moment, sense ordre
			codi_cap1 = "<div class=\"th fitxa" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
			codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
			*/
			codi_cap1 = "<div class=\"th fitxa" + ordre_c1 + "\" role=\"columnheader\">" + txtLlistaItem + "</div>";
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
				caducat_titol_class = (dada_node.caducat) ? " fitxa" : " fitxaCaducat";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td " + caducat_titol_class + "\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a id=\"fitxa_"+dada_node.id+"\" href=\"javascript:;\" class=\"fitxa\">" + printStringFromNull(dada_node.titulo, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = (dada_node.caducat) ? " caducat" : "";
				codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaPublicacion, txtSinValor) + "</div>";
				codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaCaducidad, txtSinValor) + "</div>";
				
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
			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
			dataVars_cercador += "&estat=" + $("#cerca_estat").val();
			dataVars_cercador += "&materia=" + $("#cerca_materia").val();
			dataVars_cercador += "&fetVital=" + $("#cerca_fetVital").val();
			dataVars_cercador += "&url=" + $("#cerca_url").val();
			dataVars_cercador += "&responsable=" + $("#cerca_responsable").val();
			dataVars_cercador += "&fechaCaducidad=" + $("#cerca_fechaCaducidad").val();
			dataVars_cercador += "&fechaPublicacion=" + $("#cerca_fechaPublicacion").val();
			dataVars_cercador += "&fechaActualizacion=" + $("#cerca_fechaActualizacion").val();
			dataVars_cercador += "&uaFilles=" + $("#cerca_uaFilles").val();			
			var uaMevesVal = $("#cerca_uaMeves").attr('checked') ? 1 : 0;
			dataVars_cercador += "&uaMeves=" + uaMevesVal;
						
		} else {
			
			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;
			
			// cercador
			dataVars_cercador = "&idUA=" + $("#cerca_ua_id").val();//Siempre habra una UA
			
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
	this.extend(false, FormulariDades);
	var that = this;
	
	//Se anyaden los campos que no se van a serializar directamente mediante .serialize()	
	this._baseGuarda = this.guarda;	
	this.guarda = function() {
		var dataVars = EscriptoriSeccionsUA.llistaSeccUa() + "&";
		//TODO:incloure aquest error dins la verificacio de procediment.js

		if (dataVars == "seccUA=&") {
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtCampObligatori, text: "<p>" + txtSeccUa + "</p>"});
		} else {		
			dataVars += ModulMateries.listaMaterias() + "&" + ModulFetsVitals.listaHechosVitales()

			this._baseGuarda(dataVars);
		}
	}

	this.urlPrevisualizar = "http://www.caib.es/govern/sac/fitxa.do";
	
	this.iniciar = function() {
		// dates		
		$('#item_data_caducitat').datetimepicker({ timeFormat: 'hh:mm'});
		$("#item_data_publicacio").bind("blur",this.dataPublicacio).datetimepicker({ timeFormat: 'hh:mm'});
		
		//$("#item_data_caducitat").datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });
		//$("#item_data_publicacio").bind("blur",this.dataPublicacio).datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });
		
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
			ul_idiomes_elm.find("li.idioma").bind("click", {'actualizarIdiomasModulosLaterales': true, 'idPare':'#escriptori_detall'},that.idioma);			
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
		
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");

		ModulMateries.nuevo();
	
		ModulFetsVitals.nuevo();

		//TODO: moure a modul_seccion_ua.js
		secc_ua_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccionsUA div.seleccionats");
		secc_ua_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");
	
		ModulEnllas.nuevo();
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
		$("#item_data_publicacio").val(txtImmediat);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {				
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();		
	}
			
	this.pintar = function(dades) {
		
		escriptori_detall_elm.find("h2:first").text(txtDetallTitol);
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar").show();
		
		dada_node = dades;
							
		$("#item_id").val(dada_node.item_id);
		
		$("#item_estat").val(dada_node.item_estat);

		$("#item_data_publicacio").val(dada_node.item_data_publicacio);
		$("#item_data_caducitat").val(dada_node.item_data_caducitat);
		/*
		if (dada_node.caducat == "S") {
			escriptori_detall_elm.find("h2:first").append(", <span class=\"caducat\">" + txtCaducat.toLowerCase() + "</span>");
			$("#modulLateral p.baix:first").removeClass("iPublicat").addClass("iCaducat");
		} else {
			escriptori_detall_elm.find("h2:first span.caducat").remove();
			$("#modulLateral p.baix:first").removeClass("iCaducat").addClass("iPublicat");
		}
		*/
		$("#item_titol_ca").val(printStringFromNull(dada_node.ca.titulo));
		$("#item_des_curta_ca").val(printStringFromNull(dada_node.ca.descAbr));
		$("#item_des_llarga_ca").val(printStringFromNull(dada_node.ca.descripcion));
		$("#item_url_ca").val(printStringFromNull(dada_node.ca.url));
		
		$("#item_titol_es").val(printStringFromNull(dada_node.es.titol));
		$("#item_des_curta_es").val(printStringFromNull(dada_node.es.descAbr));
		$("#item_des_llarga_es").val(printStringFromNull(dada_node.es.descripcion));
		$("#item_url_es").val(printStringFromNull(dada_node.es.url));
		
		$("#item_titol_en").val(printStringFromNull(dada_node.en.titol));
		$("#item_des_curta_en").val(printStringFromNull(dada_node.en.descAbr));
		$("#item_des_llarga_en").val(printStringFromNull(dada_node.en.descripcion));
		$("#item_url_en").val(printStringFromNull(dada_node.en.url));
		
		$("#item_titol_de").val(printStringFromNull(dada_node.de.titol));
		$("#item_des_curta_de").val(printStringFromNull(dada_node.de.descAbr));
		$("#item_des_llarga_de").val(printStringFromNull(dada_node.de.descripcion));
		$("#item_url_de").val(printStringFromNull(dada_node.de.url));
		
		$("#item_titol_de").val(printStringFromNull(dada_node.fr.titol));
		$("#item_des_curta_de").val(printStringFromNull(dada_node.fr.descAbr));
		$("#item_des_llarga_de").val(printStringFromNull(dada_node.fr.descripcion));
		$("#item_url_de").val(printStringFromNull(dada_node.fr.url));
				
		$("#item_notes").val(dada_node.item_notes);
		$("#item_youtube").val(dada_node.item_youtube);
		$("#item_forum").val(dada_node.item_forum);				
	
		ModulMateries.inicializarMaterias(dada_node.materies);
		
		ModulFetsVitals.cargarHechosVitales(dada_node.fetsVitals);
		
		//TODO:moure a modul_seccions_ua
		seccUA_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccionsUA div.seleccionats");
		seccUA_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");

		seccUA_nodes = dades.seccUA;
		seccUA_nodes_size = seccUA_nodes.length;

		//mat_llistat_elm.find("input").removeAttr("checked");

		if (seccUA_nodes_size == 0) {
			seccUA_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");
		} else {
			codi_seccUA = "<ul>";
			$(seccUA_nodes).each(function() {
				seccUA_node = this;
				codi_seccUA += "<li>";
				codi_seccUA +="<input class=\"idSeccUa\" type=\"hidden\" value=\"" + seccUA_node.id + "\" /> ";
				codi_seccUA +="<input class=\"ua\" type=\"hidden\" value=\"" + seccUA_node.idUA + "\" /> ";
				codi_seccUA +="<input class=\"seccio\" type=\"hidden\" value=\"" + seccUA_node.idSec + "\" /> ";
				codi_seccUA += txtLaSeccio+" <em class=\"seccio\">"+seccUA_node.nombreSec+"</em>, "+txtAmbLaUnitat+" <em class=\"ua\">" + seccUA_node.nombreUA +"</em>" + "</li>";				
			});
			codi_seccUA += "</ul>";
			txt_seccUA = (seccUA_nodes_size == 1) ? txtSeccioUA : txtSeccionsUA;			
			seccUA_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + seccUA_nodes_size + " " + txt_seccUA + "</strong>.");
			seccUA_seleccionats_elm.find(".listaOrdenable").html(codi_seccUA);
						
		}	
		
		ModulEnllas.cargarEnlaces();
		
		// mostrem

		if ($("#carregantDetall").size() > 0) {

			$("#carregantDetall").fadeOut(300, function() {

				$(this).remove();
				
				// array
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				
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
		
		dataVars = "&id=" + item_ID;
				
		// ajax
		$.ajax({
			type: "POST",
			url: pagEsborrar,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.cancelar();
			
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {

				Llistat.anulaCache();
			
				Missatge.cancelar();//No s'ha de veure el missatge de confirmacio d'esborrat
								
				if (data.id > -1) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});					
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id == -2){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
								
				// array
				Detall.array({id: dada_node.id, accio: "elimina"});
				// recarregar
				Detall.recarregar();
				
			}			
		});		
	}
}

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

/*
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



/*
var Error = {
	llansar: function() {
		if (escriptori_detall_elm.css("display") != "none") {
			escriptori_detall_elm.attr('aria-hidden', 'true').attr('aria-disabled', 'true').fadeOut(300);
		}
		escriptori_elm.fadeOut(300, function() {
			segundos = 60;
			conex = setInterval("Error.conexion()",1000);
			codi = "<div id=\"error\">";
			codi += "<h1>" + txtAjaxError + "</h1>";
			codi += "<p><strong>" + txtFuncions + "</strong> " + txtFuncionsFins + ".</p>";
			codi += "<p>" + txtConexionIntentar + " <span id=\"temps\">" + segundos + " " + txtSegons + "</span>.</p>";
			codi += "<p><a onclick=\"Error.reiniciar();\">" + txtConectar + "</a></p>";
			codi += "</div>";
			// mostrem
			escriptori_elm.attr('aria-hidden', 'false').attr('aria-disabled', 'false').html(codi).fadeIn(300);
		});
	},
	conexion: function() {
		segundos--;
		if (segundos == 0) {
			Error.reiniciar();
		} else if (segundos == 1) {
			$("#temps").html(segundos + " " + txtSegon);
		} else {
			$("#temps").html(segundos + " " + txtSegons);
		}
	},
	reiniciar: function() {
		escriptori_elm.fadeOut(300, function() {
			if (conex) { clearInterval(conex); }
			// escriptori, carregant
			codi = "<p class=\"executant\">" + txtCargandoEntidades + "</p>";
			escriptori_elm.html(codi).fadeIn(300, function() {
				// INICIAMOS
				Entidades.carregar({entidad:entidad_ID});
			});
		});
	}
};
*/