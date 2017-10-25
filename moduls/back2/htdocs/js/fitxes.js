// Fitxes informatives

$(document).ready(function() {
	
	//#421 para comprobar que no tiene la longitud del nombre demasiado largo.
	jQuery("input:file").change(function(e) {
		  if (e.target.files.length > 0) {
		  var file = e.target.files[0];
			if (file != null && file.name != null) {
				if (file.name.length >= 128) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + txtErrorTamanyoFitxer + "</p>"});
				}
			}
		  }
	});
	
	jQuery("#btnInsertar").bind("click", function() { Detall.modificado(); });
	
	jQuery(".lista-simple").click(function() {
		
		var elementos = $(this).parent().parent().find("li"); // Con esto obtenemos los <li> que cuelgan de <div class="seleccionats">
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleGenerica.guardar(elementos, url, id);
		
	});
	
	// XXX amartin: creamos un listener singular para esta lista ya que el selector de elementos es diferente.
	// Es un caso singular ya que, al ser una lista multiidioma pero sólo tener que guardar el orden de los elementos
	// y borrar los que no están, la tratamos como una lista simple.
	jQuery(".lista-simple-documentos").click(function() {
		
		var elementos = $(this).parent().parent().find("div.cajaIdioma.ca li"); // Con esto obtenemos los <li> que cuelgan de <div class="cajaIdioma ca">
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleDocumentos.guardar(elementos, url, id);
	
	});
	
	jQuery(".lista-multiidioma-enlaces").click(function() {
				
		var elementos = $('#escriptori_enllassos .escriptori_items_seleccionats .seleccionats').find("div.cajaIdioma.ca li"); // Con esto obtenemos los <li> que cuelgan de <div class="cajaIdioma ca">
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaMultiidiomaEnlaces.guardar(elementos, url, id);
	
	});
	
	/*
	 * amartin: casos de guardado de listas de elementos donde su gestión se ha implementado con checkboxes.
	 * Es necesario hacerlo vía eventos personalizados, ya que el DOM es diferente y no podemos tirar de los
	 * elementos <li> generados dentro del listado de elementos seleccionados que cuelga de <div class="seleccionats">.
	 */
	jQuery(".modulMateries").bind("finalizaMaterias", function() {
		
		var elements = $('.modulMateries .seleccionats').find('li');
		var id = $('#item_id').val();
		var url = $('#btnFinalizar_materias').attr('action');
		
		ListaSimpleMaterias.guardar(elements, url, id);
		
	});
	
	jQuery("#btnDuplicar").bind("click", function() { Detall.duplicar(); });
	
	jQuery(".modulFetsVitals").bind("finalizaHechosVitales", function() {
		
		var elements = $('.modulFetsVitals .seleccionats').find('li');
		var id = $('#item_id').val();
		var url = $('#btnFinalizar_hechosVitales').attr('action');
		
		ListaSimpleHechosVitales.guardar(elements, url, id);
		
	});

    ListaSimpleGenerica = new ListaSimple();
    ListaSimpleMaterias = new ListaSimple();
    ListaSimpleHechosVitales = new ListaSimple();
    ListaSimpleDocumentos = new CListaSimpleDocumentos();
    ListaMultiidiomaEnlaces = new CListaMultiidiomaEnlaces();

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
	
	// Datos traductor
	CAMPOS_TRADUCTOR_FICHA = ["item_titol_", "item_des_curta_", "item_des_llarga_"];
    DATOS_TRADUCIDOS_FICHA = ["titulo", "descAbr", "descripcion"];
    
	// INICIEM

	Llistat = new CLlistat();
	Detall = new CDetall();
	Error = new CError();
	Auditoria = new ModulAuditories();
	Estadistica = new ModulEstadistiques();
	Detall.iniciar();
	
    // Mostrar detall
	var itemACarregar = itemAEditar();
	if (itemACarregar > 0)
		Detall.carregar(itemACarregar);

	Llistat.iniciar();
	
	$.suggeriments();
	
});

// idioma
var pag_idioma = $("html").attr("lang");

var Cercador = {iniciar: function() {}};

// minim cercador
var numCercadorMinim = 0;

// paginacio
var paginacio_marge = 4;

// llistat
var itemID_ultim = 0;

function CLlistat() {
	
	this.extend = ListadoBase;
	this.extend();
		
	this.iniciar = function() {
		
		$("#cerca_fechaCaducidad").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaPublicacion").datetimepicker({ timeFormat: 'hh:mm' });
		$("#cerca_fechaActualizacion").datetimepicker({ timeFormat: 'hh:mm' });
	
		Llistat.carregar({});
	
	};
	
	// Exporta la búsqueda
	this.exporta = function(opcions) {	
		this.carregar ({cercador: "si", exportar: "si"});
	};
	
	this.finCargaListado = function(opcions, data) {
		
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			if (resultats_total > numCercadorMinim)
				opcions_elm.find("li.C").animate({duration: "slow", width: 'show'}, 300);
			
			txtT = (resultats_total > 1) ? txtLlistaItems : txtLlistaItem;
			
			resultatInici = ((pag_Pag * pag_Res) + 1);
			resultatFinal = ((pag_Pag * pag_Res) + pag_Res > resultats_total) ? resultats_total : (pag_Pag * pag_Res) + pag_Res;
			
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
				
				if (ordre_C == "id")
					txt_per = txtLlistaItem;
				
				else if (ordre_C == "fechaPublicacion")
					txt_per = txtPublicacio;
				
				else if (ordre_C == "fechaCaducidad")
					txt_per = txtCaducitat;
				
				else
					txt_per = txtFechaModificacion;
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
						
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + ' ' + resultatInici + ' ' + txtMostremAl + ' ' + resultatFinal + txt_ordenacio + '.';
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";
			
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
			//$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
			$(data.nodes).each(function(i) {
				
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
		dades_elm.fadeOut(300, function() { // pintem
			
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click", function() { Llistat.ficha(this); });
                
                // Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
                jQuery("#resultats .table .th a").unbind("click").click(function() {
                    Llistat.ordena(this, opcions);
                });                
							
				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si")
					cercador_elm.find("input, select").removeAttr("disabled");
				
			});
			
		});	
		
	};

	this.carregar = function(opcions) {
		
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado  = !modoBuscador;
		var modoExportar = (typeof opcions.exportar != "undefined" && opcions.exportar == "si");
		
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
			dataVars_cercador += "&visibilitat=" + $("#cerca_estat").val();
			dataVars_cercador += "&publicObjectiu=" + $("#cerca_publicObjectiu").val();
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
		//El dataVarsExportar es para cuando se exporta poner el pagPag a 0 y el PagRes a 99999
		var dataVarsExportar = dataVars + "pagPag=0&pagRes=99999&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		dataVars += "pagPag=" + pag_Pag + "&pagRes=" + pag_Res + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		if (modoExportar) {
			
			
			Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtProcessant});
			var xhr = new XMLHttpRequest();
			xhr.open('POST', pagExportar, true);
			xhr.responseType = 'arraybuffer';
			xhr.onload = function () {
				Missatge.cancelar();
				if (this.status === 200) {
					var filename = "";
					var disposition = xhr.getResponseHeader('Content-Disposition');
					if (disposition && disposition.indexOf('attachment') !== -1) {
						var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
						var matches = filenameRegex.exec(disposition);
						if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
					}
					var type = xhr.getResponseHeader('Content-Type');

					var blob = new Blob([this.response], { type: type });
					if (typeof window.navigator.msSaveBlob !== 'undefined') {
						// IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
						window.navigator.msSaveBlob(blob, filename);
					} else {
						var URL = window.URL || window.webkitURL;
						var downloadUrl = URL.createObjectURL(blob);

						if (filename) {
							// use HTML5 a[download] attribute to specify filename
							var a = document.createElement("a");
							// safari doesn't support this yet
							if (typeof a.download === 'undefined') {
								window.location = downloadUrl;
							} else {
								a.href = downloadUrl;
								a.download = filename;
								document.body.appendChild(a);
								a.click();
							}
						} else {
							window.location = downloadUrl;
						}

						setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
					}
				}
			};
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.send(dataVarsExportar);
			
		} else if ( modoListado || modoBuscador ) {
			
			$.ajax({
				type: "POST",
				url: pagLlistat,
				data: dataVars,
				dataType: "json",
				error: function() {
					
					if (!a_enllas) {

						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						Error.llansar();
						
					}
					
				},
				success: function(data) {
					
					Llistat.finCargaListado(opcions,data);
					
					if ( modoListado )											
						Llistat.cacheDatosListado = data;
					
				}
			});
			
		} else {
			
			Llistat.finCargaListado(opcions,Llistat.cacheDatosListado);
			
		}
		
	};
	
};

// items array
var Items_arr = new Array();

// detall
function CDetall() {
	
	this.extend = DetallBase;
	this.extend(false, FormulariDades);
	
	var that = this;
	
	this.tipusAuditoria = 'fitxa';
	this.tipusEstadistica = 'fitxa';
	
	
	
	//Se anyaden los campos que no se van a serializar directamente mediante .serialize()	
	this.guarda = function() {
		
		// Omplim els camps amb els valors per enviar al formulari
		var llistaSeccions = EscriptoriSeccionsUA.llistaSeccUa();
		
		if (llistaSeccions.length < 1 ) {
			
            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtCampObligatori, text: "<p>" + txtSeccUa + "</p>"});
            
		} else {
			
			var llista_materies = ModulMateries.listaMaterias();
			llista_materies = llista_materies.slice(9);
			var llista_publics = ModulPublicObjectiu.listaPublics();
			llista_publics = llista_publics.slice(16);
            $("#llistaSeccions").val(llistaSeccions);
			$("#llistaMateries").val(llista_materies);
			$("#llistaFetsVitals").val(ModulFetsVitals.listaHechosVitales());
			$("#llistaPublicObjectiu").val(llista_publics);	
			
			// Validamos el formulario
			if (!that.formulariValid()) {
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

			return false;
			
		}
		
	};

	this.urlPrevisualizar = urlPrevisualizarFicha;
	
	this.iniciar = function() {
		
		// Desactivamos que se cambie el detalle a modificado por cambiar los checkboxes de materias relacionadas con la ficha.
		jQuery('#modul_materies .llistat li input[type=checkbox]').unbind('change');
		
		// Desactivamos que se cambie el detalle a modificado por cambiar los checkboxes de materias relacionadas con la ficha.
		jQuery('#modul_fetsVitals .llistat li input[type=checkbox]').unbind('change');

		$('#item_data_caducitat').datetimepicker({ timeFormat: 'hh:mm' });
		$("#item_data_publicacio").bind("blur", this.dataPublicacio).datetimepicker({ timeFormat: 'hh:mm' });
		
		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			
			// Esconder todos menos el primero y tercero
			escriptori_detall_elm.find('div.idioma').slice(3).hide();
			escriptori_detall_elm.find('div.idioma').slice(1,2).hide();
			
		
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
        
        // boton de traducir
        jQuery("#botoTraduirFitxa").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });
        
	};

    this.traduirWrapper = function () {
		that.traduir(pagTraduir, CAMPOS_TRADUCTOR_FICHA, DATOS_TRADUCIDOS_FICHA);
	};
	
	this.dataPublicacio = function(e) {
//		if ($(this).val() == "") {
//			$(this).val(txtImmediat);
//		}
	};
	
	this.activar = 0;
			
	this.nou = function() {
		
		//Ocultar paneles
        jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
        jQuery("#modulAuditories, #modulEstadistiques").hide();
		
		$("#item_id").val("");
		
		jQuery("#modul_documents").hide();
		
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar,.btnDuplicar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");

		 for (var i in idiomas) {
	        	
				limpiarArchivoMultiidioma("item_icona", idiomas[i]);
				limpiarArchivoMultiidioma("item_banner", idiomas[i]);
				limpiarArchivoMultiidioma("item_imatge", idiomas[i]);
						
		 }
		
		ModulMateries.nuevo();
	
		ModulFetsVitals.nuevo();
		
		ModulPublicObjectiu.nuevo();
		
		//TODO: moure a modul_seccion_ua.js
		secc_ua_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccionsUA div.seleccionats");
		secc_ua_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");
	
		ModulEnllas.nuevo();
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none")
			suggeriment_elm.slideUp(300);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		
		escriptori_contingut_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {				
				itemID_ultim = this.activar;
			});
		});
		
		this.actualizaEventos();
		
		this.modificado(false);
		
	};
	
	this.duplicar = function() {
		
		//Ocultar paneles
        jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
        jQuery("#modulAuditories, #modulEstadistiques").hide();
		
		$("#item_id").val("");
		
		jQuery("#modul_documents").hide();
		
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar,.btnDuplicar").hide();
		
		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");

		
		ModulMateries.nuevo();
	
		ModulFetsVitals.nuevo();
		
		ModulPublicObjectiu.nuevo();
		
		ModulEnllas.nuevo();
		
		//TODO: moure a modul_seccion_ua.js
		secc_ua_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccionsUA div.seleccionats");
		secc_ua_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");
		
		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none")
			suggeriment_elm.slideUp(300);
		
		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");
		
		escriptori_contingut_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {				
				itemID_ultim = this.activar;
			});
		});
		
		this.actualizaEventos();
		
		this.modificado(true);
		
	};
	
	this.pintar = function(dades) {
		
		jQuery("#modul_documents").show();        
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").show();
		
		escriptori_detall_elm.find("h2:first").text(txtDetallTitol);
		escriptori_detall_elm.find(".btnPrevisualizar,.btnEliminar").show();
		
		if (dades.permisDuplicacio){
			escriptori_detall_elm.find(".btnDuplicar").show();
		}else{
			escriptori_detall_elm.find(".btnDuplicar").hide();
		}
		
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

		// Bloque de pestanyas de idiomas
        for (var i in idiomas) {
        	
            var idioma = idiomas[i];

            $("#item_titol_" + idioma).val(printStringFromNull(dada_node[idioma].titulo));
            $("#item_url_" + idioma).val(printStringFromNull(dada_node[idioma].url));
            $("#item_forum_" + idioma).val(printStringFromNull(dada_node[idioma].urlForo));
            $("#item_youtube_" + idioma).val(printStringFromNull(dada_node[idioma].urlVideo));
            
            
            
            // El plugin de JQuery para TinyMCE parece que tiene un bug y a veces y en segun que navegador 
            // no carga bien el contenido ni en el textarea ni en el editor.
            var descAbr = printStringFromNull(dada_node[idioma].descAbr);
            var descripcion = printStringFromNull(dada_node[idioma].descripcion);
            
            document.getElementById("item_des_curta_" + idioma).value = descAbr;
            document.getElementById("item_des_llarga_" + idioma).value = descripcion;
            
            $("#item_des_curta_" + idioma).val(descAbr);
            $("#item_des_llarga_" + idioma).val(descripcion);
            
            // Icona
            pintarArchivoMultiidioma("item_icona", idioma, dada_node);
            
            // Banner
            pintarArchivoMultiidioma("item_banner", idioma, dada_node);	
            
            // Imatge
            pintarArchivoMultiidioma("item_imatge", idioma, dada_node);
        }
        
		
		// Fin bloque de pestanyas de idiomas	
		
		$("#item_responsable").val(dada_node.item_responsable);
		$("#item_notes").val(dada_node.item_notes);
		//$("#item_youtube").val(dada_node.item_youtube);
		//$("#item_forum").val(dada_node.item_forum);				
	
		ModulPublicObjectiu.inicializarPublics(dada_node.publicsObjectiu);
		
		//TODO: moure a modul_seccions_ua
		seccUA_seleccionats_elm = escriptori_detall_elm.find("div.modulSeccionsUA div.seleccionats");
		seccUA_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccioUA + ".");

		seccUA_nodes = dades.seccUA;
		seccUA_nodes_size = seccUA_nodes.length;

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
		
		// mostrem
		if ($("#carregantDetall").size() > 0) {

			$("#carregantDetall").fadeOut(300, function() {

				$(this).remove();
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				escriptori_detall_elm.fadeIn(300);
				
			});
			
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);
			});
		
		}
		
		// Marcamos el formulario como "no modificado".
		this.modificado(false);
		
	};
	
	this.elimina = function() {

		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		item_ID = $("#item_id").val();
		dataVars = "&id=" + item_ID;
				
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
					
				} else if (data.id == -1) {
					
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
					
				} else if (data.id == -2) {
					
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
					
				}
				
			}	
			
		});	
		
	};
	
	this.pintarModulos = function(dades) {

		ModulMateries.inicializarMaterias(dades.materies);
		ModulFetsVitals.cargarHechosVitales(dades.fetsVitals);
		ModulDocuments.inicializarDocuments(dades.documents);
		ModulEnllas.cargarEnlaces(dades);

	};
	
	this.ocultarModulos = function(selector) {
		
		if ( !selector.hasClass("publicacio") 
				&& !selector.children().is("#llistaPublicObjectiu") 
				&& !selector.children().is("#llistaSeccions") 
				&& !selector.children().is("div.escriptori_items_seleccionats") )
			selector.addClass("invisible");
		
		$("#escriptori_seccions_ua > .escriptori_items_seleccionats > .modul").removeClass("invisible");
		
	};
	
}