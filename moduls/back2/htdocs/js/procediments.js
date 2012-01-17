// CATALEG PROCEDIMENTS

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
	
    Detall.iniciar();
    // Mostrar detall?
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0) {
		Detall.carregar(itemACarregar);
	}
    Llistat.iniciar();
    // Cercador.iniciar();
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
            ordre_c2 = (ordre_C == "familia") ? " " + ordre_T : "";
			ordre_c3 = (ordre_C == "fecha") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "nom") {
					txt_per = txtLlistaItem;
				} else if (ordre_C == "familia") {					
                    txt_per = txtFamilia;
				} else {
					txt_per = txtFechaActualizacion;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDel + " " + resultatInici + " " + txtAl + " " + resultatFinal + txt_ordenacio + ".</p>";
			
			// De momento no habra ordenacion.
            //	codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtLlistaItem + "</a></div>";
            //	codi_cap2 = "<div class=\"th publicacio" + ordre_c2 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtPublicacio + "</a></div>";
            //	codi_cap3 = "<div class=\"th caducitat" + ordre_c3 + "\" role=\"columnheader\"><a href=\"javascript:;\">" + txtCaducitat + "</a></div>";
			
			codi_cap1 = "<div class=\"th procedimiento "+ ordre_c1 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"nom\">" + txtLlistaItem + "</a></div>";
            codi_cap2 = "<div class=\"th familia "+ ordre_c2 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"familia\">" + txtFamilia + "</a></div>";
			codi_cap3 = "<div class=\"th fechaActualizacion "+ ordre_c3 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"fechaActualizacion\">" + txtFechaActualizacion + "</a></div>";
			
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
				caducat_nom_class = (dada_node.caducat) ? " procediment" : " procedimentCaducat";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td " + caducat_nom_class + "\" role=\"gridcell\">";

				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
                codi_taula += '<span class="id">'+ dada_node.id +'</span>';
				codi_taula += "<a id=\"procediment_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";
				
				caducat_class = (dada_node.caducat) ? " caducat" : "";
				//codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.publicacio) + "</div>";
                codi_taula += '<div class="td familia">[dinamizar]</div>';
                
				//codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.caducitat) + "</div>";
                codi_taula += "<div class=\"td fechaActualizacion" + caducat_class + "\" role=\"gridcell\">[dinamizar]</div>";
				
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
			
				// Asociamos el evento onclick a los elementos de la lista para poder ir a ver su ficha.
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
	
    var that = this;
    
	//Se anyaden los campos que no se van a serializar directamente mediante .serialize()	
	this._baseGuarda = this.guarda;	
	this.guarda = function() {
		urlParams = ModulNormativa.listaNormativas();
		urlParams += "&" + ModulMateries.listaMaterias();
		this._baseGuarda(urlParams);
	}
	
	this.urlPrevisualizar = "http://www.caib.es/govern/sac/visor_proc.do";

	this.iniciar = function() {	
		// dates
		//$("#item_data_publicacio, #item_data_caducitat").mask("99/99/9999").datepicker({ altField: '#actualDate' });
		$("#item_data_caducitat").datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });
		$("#item_data_publicacio").bind("blur",Detall.dataPublicacio).datepicker({ altField: '#actualDate', dateFormat: 'dd/mm/yy' });

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
       
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#item_fi_vida_administrativa,#item_fi_vida_administrativa_es,#item_fi_vida_administrativa_en,#item_fi_vida_administrativa_de,#item_fi_vida_administrativa_fr").change(function(){
            jQuery("#item_fi_vida_administrativa,#item_fi_vida_administrativa_es,#item_fi_vida_administrativa_en,#item_fi_vida_administrativa_de,#item_fi_vida_administrativa_fr").attr("checked", jQuery(this).is(":checked"));
        });
        
        jQuery("#item_taxa,#item_taxa_es,#item_taxa_en,#item_taxa_de,#item_taxa_fr").change(function(){
            jQuery("#item_taxa,#item_taxa_es,#item_taxa_en,#item_taxa_de,#item_taxa_fr").attr("checked", jQuery(this).is(":checked"));
        });
        
        jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").change(function(){
            jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").val( jQuery(this).val() );
        });
        
        jQuery("#item_organ, #item_organ_es, #item_organ_ca, #item_organ_en, #item_organ_de, #item_organ_fr").change(function(){        
            jQuery("#item_organ, #item_organ_es, #item_organ_ca, #item_organ_en, #item_organ_de, #item_organ_fr").val( jQuery(this).val() );        
        });
        
        jQuery("#item_iniciacio,#item_iniciacio_es,#item_iniciacio_ca,#item_iniciacio_en,#item_iniciacio_de,#item_iniciacio_fr").change(function(){
            jQuery("#item_iniciacio,#item_iniciacio_es,#item_iniciacio_ca,#item_iniciacio_en,#item_iniciacio_de,#item_iniciacio_fr").val( jQuery(this).val() );
        });
	}
	
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
	}
		
	this.nou = function() {
        //Ocultar paneles
		jQuery("#modul_documents").hide();
        jQuery("#modul_tramits").hide();
		
		ModulMateries.nuevo();
		ModulNormativa.nuevo();
        EscriptoriNormativa.nuevo();

        
		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");
		
//		tra_seleccionats_elm = escriptori_detall_elm.find("div.modulTramits div.seleccionats");
//		tra_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtTramitNouProcediment + ".").end().find("p.gestiona").hide();

		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}
		
//		$("#item_data_publicacio").val(txtImmediat);
        $("#item_id").val("");
		$("#item_organ_id").val("");
		$("#modulPrincipal :input").each(limpiarCampo);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar				
				itemID_ultim = 0;
			});
		});
		this.actualizaEventos();
	}		
	
	this.pintar = function(dades) {
		// Mostrar paneles
		jQuery("#modul_documents").show();
        jQuery("#modul_tramits").show();
        
		escriptori_detall_elm.find("a.elimina, a.previsualitza").show().end().find("h2:first").text(txtDetallTitol);
		
		dada_node = dades;

		$("#item_id").val(dada_node.item_id);
		
		// Bloque de pestanyas de idiomas
		for (var i in idiomas) {
			var idioma = idiomas[i];
			$("#item_nom_" + idioma).val(printStringFromNull(dada_node[idioma]["nombre"]));
			$("#item_objecte_" + idioma).val(printStringFromNull(dada_node[idioma]["resumen"]));
			$("#item_destinataris_" + idioma).val(printStringFromNull(dada_node[idioma]["destinatarios"]));
			$("#item_requisits_" + idioma).val(printStringFromNull(dada_node[idioma]["requisitos"]));
			$("#item_resolucio_" + idioma).val(printStringFromNull(dada_node[idioma]["resolucion"]));
			$("#item_notificacio_" + idioma).val(printStringFromNull(dada_node[idioma]["notificacion"]));
			$("#item_silenci_" + idioma).val(printStringFromNull(dada_node[idioma]["silencio"]));
			$("#item_observacions_" + idioma).val(printStringFromNull(dada_node[idioma]["observaciones"]));
		}
		// Fin bloque de pestanyas de idiomas
		
		
		marcarOpcionSelect("item_estat", dada_node.item_estat);
		
		$("#item_data_actualitzacio").val(dada_node.item_data_actualitzacio);
		
		$("#item_data_publicacio").val(dada_node.item_data_publicacio);

		$("#item_data_caducitat").val(dada_node.item_data_caducitat);
		
		$("#item_codi").val(dada_node.item_codi);
		
		if (dada_node.item_iniciacio != undefined) {
			jQuery("#item_iniciacio").val(dada_node.item_iniciacio);
            jQuery("#item_iniciacio").change();
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
			jQuery('#item_fi_vida_administrativa').attr('checked', dada_node.item_fi_vida_administrativa);                        
            jQuery("#item_fi_vida_administrativa").change();
		}
		
		if (dada_node.item_finestreta_unica != undefined) {
			$('#item_finestreta_unica').attr('checked', dada_node.item_finestreta_unica);
		}
		
		if (dada_node.item_taxa != undefined) {
			jQuery('#item_taxa').attr('checked', dada_node.item_taxa);                        
            jQuery("#item_taxa").change();
		}
		
		$("#item_notes").val(dada_node.item_notes);

        ModulTramit.inicializarTramites(dada_node.tramits);
        /*
        // debug
        ModulTramit.inicializarTramites(
            [
                {id:1,nombre:"Tramite inicial 1",orden:0},
                {id:2,nombre:"Tramite inicial 2",orden:1}
            ]);        
            */
		        
		ModulDocuments.inicializarDocuments(dada_node.documents);
        
		ModulMateries.inicializarMaterias(dada_node.materies);
		
		ModulNormativa.inicializarNormativas(dada_node.normatives);
		
		
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
				} else if (data.id == -2){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
				}
			}
		});
	}
};