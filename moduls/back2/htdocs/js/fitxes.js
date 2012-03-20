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
	Error = new CError();
	Auditoria = new ModulAuditories();

	Detall.iniciar();
    // Mostrar detall?
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	}
    Llistat.iniciar();
	
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
			ordre_c1 = (ordre_C == "id") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "fechaPublicacion") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "fechaCaducidad") ? " " + ordre_T : "";
			ordre_c4 = (ordre_C == "fechaActualizacion") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "id") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "fechaPublicacion") {
					txt_per = txtPublicacio;
				} else if (ordre_C == "fechaCaducidad") {
					txt_per = txtCaducitat;
				} else {
					txt_per = txtFechaModificacion;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + ' ' + resultatInici + ' ' + txtMostremAl + ' ' + resultatFinal + txt_ordenacio + ".</p>";
			
			/* De moment, sense ordre
			codi_cap1 = "<div class=\"th fitxa" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
			codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
			*/
			codi_cap1 = "<div class=\"th fitxa" + ordre_c1 + "\" role=\"columnheader\"><a class=\"id\" href=\"javascript:void(0)\">" + txtLlistaItem + "</a></div>";
			codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a class=\"fechaPublicacion\" href=\"javascript:void(0)\">" + txtPublicacio + "</a></div>";
			codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a class=\"fechaCaducidad\" href=\"javascript:void(0)\">" + txtCaducitat + "</a></div>";
            codi_cap4 = "<div class=\"th modificacio" + ordre_c4 + "\" role=\"columnheader\"><a class=\"fechaActualizacion\" href=\"javascript:void(0)\">" + txtFechaModificacion + "</a></div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1 + codi_cap2 + codi_cap3 + codi_cap4;
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
                codi_taula += '<span class="id">'+dada_node.id+'</span>';
				codi_taula += "<a id=\"fitxa_"+dada_node.id+"\" href=\"javascript:;\" class=\"fitxa\">" + printStringFromNull(dada_node.titulo, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = (dada_node.caducat) ? " caducat" : "";
				codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaPublicacion, txtSinValor) + "</div>";
				codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaCaducidad, txtSinValor) + "</div>";
                codi_taula += "<div class=\"td modificacio\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaActualizacion, txtSinValor) + "</div>";
				
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
			
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click",function(){Llistat.ficha(this);});
                
                // Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
                jQuery("#resultats .table .th a").unbind("click").click(function(){
                    Llistat.ordena(this,opcions);
                });                
							
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
			var uaMevesVal = $("#cerca_uaMeves").is(':checked') ? 1 : 0;
			var uaFillesVal = $("#cerca_uaFilles").is(':checked') ? 1 : 0;
			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
			dataVars_cercador += "&estat=" + $("#cerca_estat").val();
			dataVars_cercador += "&materia=" + $("#cerca_materia").val();
			dataVars_cercador += "&fetVital=" + $("#cerca_fetVital").val();
			dataVars_cercador += "&uaMeves=" + uaMevesVal;
			dataVars_cercador += "&uaFilles=" + uaFillesVal;
						
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
	this.tipusAuditoria = 'fitxa';
	
	//Se anyaden los campos que no se van a serializar directamente mediante .serialize()	
	this.guarda = function() {
		// Omplim els camps amb els valors per enviar al formulari
		var llistaSeccions = EscriptoriSeccionsUA.llistaSeccUa();
		
		if (llistaSeccions.length < 1 ) {
            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtCampObligatori, text: "<p>" + txtSeccUa + "</p>"});	
		} else {		
			var llista_materies = ModulMateries.listaMaterias();
			llista_materies = llista_materies.slice(9);
            $("#llistaSeccions").val(llistaSeccions);
			$("#llistaMateries").val(llista_materies);
			$("#llistaFetsVitals").val(ModulFetsVitals.listaHechosVitales());	
			
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
//						Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
						Detall.recarregar(data.id);
					}					
				}
			});

			return false;
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
        
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").change(function(){
            jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").val( jQuery(this).val() );
        });
	}

	this.dataPublicacio = function(e) {
//		if ($(this).val() == "") {
//			$(this).val(txtImmediat);
//		}
	}
			
	this.nou = function() {
		//Ocultar paneles
        jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		
		$("#item_id").val("");
		
		jQuery("#modul_documents").hide();
		
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");

		limpiarArchivo("item_icona");
		limpiarArchivo("item_banner");	
		limpiarArchivo("item_imatge");	
		
		ModulMateries.nuevo();
	
		ModulFetsVitals.nuevo();

		//TODO: moure a modul_seccion_ua.js
		secc_ua_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccionsUA div.seleccionats");
		secc_ua_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");
	
		ModulEnllas.nuevo();
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
//		$("#item_data_publicacio").val(txtImmediat);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {				
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();		
	}
			
	this.pintar = function(dades) {
		// Mostrar paneles
		jQuery("#modul_documents").show();        
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").show();
		
		escriptori_detall_elm.find("h2:first").text(txtDetallTitol);
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar").show();
		
		dada_node = dades;
							
		$("#item_id").val(dada_node.item_id);
		
		$("#item_estat").val(dada_node.item_estat);
		marcarOpcionSelect("item_estat",dada_node.item_estat);

		$("#item_clave_primaria").val(dada_node.item_id);
		$("#item_clave_primaria").change();
		
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
		
		
		// Icona
			
		pintarArchivo("item_icona", dada_node);

		// Banner
		
		pintarArchivo("item_banner", dada_node);	
		
		// Imatge
		
		pintarArchivo("item_imatge", dada_node);

		$("#item_responsable").val(dada_node.item_responsable);
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
		
		ModulDocuments.inicializarDocuments(dada_node.documents);
		
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
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {
				Llistat.anulaCache();
				if (data.id > -1) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
					Detall.array({id: dada_node.id, accio: "elimina"});
					Detall.recarregar();
				} else if (data.id == -1){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
				} else if (data.id == -2){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}			
		});		
	}
}