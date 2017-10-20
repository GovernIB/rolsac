//CATALEG SERVEIS
var hechosVitalesAsignados = null;

$(document).ready(function() {
	
	jQuery(".lista-simple").click(function() {
		
		var elements = $(this).parent().parent().find("li");
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleGenerica.guardar(elements, url, id);
		
	});
	
	// XXX amartin: creamos un listener singular para esta lista ya que el selector de elementos es diferente.
	// Es un caso singular ya que, al ser una lista multiidioma pero sólo tener que guardar el orden de los elementos
	// y borrar los que no están, la tratamos como una lista simple.
	jQuery(".lista-simple-documentos").click(function() {
		
		var elements = $(this).parent().parent().find("div.cajaIdioma.ca li"); // Con esto obtenemos los <li> que cuelgan de <div class="cajaIdioma ca">
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleDocumentos.guardar(elements, url, id);
	
	});
	
	jQuery(".lista-simple-normativas").click(function() {
		
		var elements = $('#escriptori_normatives .seleccionats').find("li");
		var id = $('#item_id').val();
		var url = $(this).attr('action');
		
		ListaSimpleNormativas.guardar(elements, url, id);
		
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
	
	jQuery(".modulFetsVitals").bind("finalizaHechosVitales", function() {
		
		var elements = $('.modulFetsVitals .seleccionats').find('li');
		var id = $('#item_id').val();
		var url = $('#btnFinalizar_hechosVitales').attr('action');
		
		ListaSimpleHechosVitales.guardar(elements, url, id);
		
	});

	ListaSimpleGenerica = new ListaSimple();
	ListaSimpleMaterias = new ListaSimple();
	ListaSimpleHechosVitales = new ListaSimple();
	ListaSimpleNormativas = new ListaSimple();
	ListaSimpleDocumentos = new CListaSimpleDocumentos();
	
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

	escriptori_tramits_elm = $("#escriptori_tramits");

	// datos traductor
	CAMPOS_TRADUCTOR_SERVEI = [
      "item_nom_",
	  "item_objecte_",
	  "item_destinatarios_",
	  "item_requisitos_",
	  "item_observaciones_"
    ];

	DATOS_TRADUCIDOS_SERVEI = [
      "nombre",
      "objeto",
      "destinatarios",
      "requisito",
      "observaciones"
    ];

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
	
	/*Listar hechos vitales al cambiar los publicos objetivos*/
	$('.ModulPublicObjectiu .finalitza').click(function() {

		var idServei = $('#item_clave_primaria').val();

		/* En el momento de crear un servicio no se asignan hechos vitales, solo durante la edición, 
		 * por lo tanto si no hay id de servicio significa que estamos creando un nuevo servicio y 
		 * no debe realizar la petición de carga del listado de hechos vitales */
		if ( idServei != "" ) {

			var publicosObjectivosSeleccionados = new Array();
			var htmlPublicosObjetivosSeleccionados = $('.ModulPublicObjectiu .llistat input:checked');
			var cantidadPOChecked = htmlPublicosObjetivosSeleccionados.length;
			var hechosVitalesSeleccionados = ModulFetsVitals.obtenerSeleccionados();

			//Se realiza la petición ajax si hay algún Público Objetivo seleccionado
			if ( cantidadPOChecked > 0 ) {

				//Guarda públicos objetivos asignados en una array auxiliar
				for ( var int = 0 ; int < cantidadPOChecked ; int++ ) {

					var htmlElement = htmlPublicosObjetivosSeleccionados[int];
					publicosObjectivosSeleccionados.push( $(htmlElement).val() );

				}

				var dataVars = "&publicosObjectivosSeleccionados=" + publicosObjectivosSeleccionados;

				$.ajax({
					type: "POST",
					url: pagListarHechosVitales,
					data: dataVars,
					dataType: "json",
					error: function() {

						if (!a_enllas) {

							// missatge
							Missatge.llansar({tipus: "missatge", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
							// error
							Error.llansar();

						}
						
					},
					success: function(data) {				

						ModulFetsVitals.pintar( data.listadoHechosVitales );
						ModulFetsVitals.inicializarHechosVitales( hechosVitalesAsignados );

					} // Fin success

				}); //Fin ajax 

				//Si no se realiza la petición se muestran únicamente los hechos vitales asignados        		
			} else {

				$("#fetsVitals .llistat > ul").empty();
				ModulFetsVitals.inicializarHechosVitales( hechosVitalesAsignados );

			} //End if

		} //end if

	}); //Fin evento click

	/*Evento que inicializa la variable de hechos vitales asignados cuando se abandona la ficha de un servicio*/
	$('.btnVolver').click(function() {

		hechosVitalesAsignados = "";

	});
	
}); //Fin $(document).ready

//idioma
var pag_idioma = $("html").attr("lang");

var Cercador = {
	iniciar: function() {}
};

//minim cercador
var numCercadorMinim = 0;

//paginacio
var paginacio_marge = 4;

//llistat
var itemID_ultim = 0;

function CLlistat() {
	
	this.extend = ListadoBase;
	this.extend();

	this.iniciar = function() {
		
		$("#cerca_fechaCaducidad").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaPublicacion").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#cerca_fechaActualizacion").datepicker({ dateFormat: 'dd/mm/yy' });

		this.carregar({});
		
	};

	this.finCargaListado = function(opcions, data) {

		// total
		resultats_total = parseInt(data.total, 10);

		if (resultats_total > 0) {

			// minim per cercador
			if (resultats_total > numCercadorMinim) {

				opcions_elm.find("li.C").animate({
					duration: "slow", width: 'show'
				}, 300);

			}

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
			ordre_c1 = (ordre_C == "id") ? " " + ordre_T : "";
			ordre_c2 = (ordre_C == "familia") ? " " + ordre_T : "";            
			ordre_c3 = (ordre_C == "fechaActualizacion") ? " " + ordre_T : "";

			txt_ordenacio = "";

			if (resultats_total > 1) {

				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";

				if (ordre_C == "id")
					txt_per = txtLlistaItem;

				else if (ordre_C == "familia")
					txt_per = txtFamilia;

				else
					txt_per = txtFechaActualizacion;

				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";

			}

			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + "</strong> " + txtT.toLowerCase() + ". " + txtMostrem + " " + txtDel + " " + resultatInici + " " + txtAl + " " + resultatFinal + txt_ordenacio + ".";
			codi_totals += this.getHtmlItemsPagina();
			codi_totals += "</p>";

			codi_cap1 = "<div class=\"th servicio "+ ordre_c1 +"\" role=\"columnheader\"><a href=\"javascript:void(0)\" class=\"id\">" + txtLlistaItem + "</a></div>";
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
			//$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
			$(data.nodes).each( function(i) {
				
				dada_node = this;

				parClass = (i%2) ? " par": "";
				caducat_nom_class = (dada_node.caducat) ? " servicio" : " serveiCaducat";

				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";

				codi_taula += "<div class=\"td " + caducat_nom_class + "\" role=\"gridcell\">";

				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += '<span class="id">'+ dada_node.id +'</span>';
				codi_taula += "<a id=\"servei_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + printStringFromNull(dada_node.nombre, txtSinValor) + "</a>";
				codi_taula += "</div>";

				caducat_class = (dada_node.caducat) ? " caducat" : "";
				//codi_taula += "<div class=\"td publicacio\" role=\"gridcell\">" + printStringFromNull(dada_node.publicacio) + "</div>";
				codi_taula += '<div class="td familia">' + printStringFromNull(dada_node.familia, txtSinValor) + '</div>';

				//codi_taula += "<div class=\"td caducitat" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.caducitat) + "</div>";
				codi_taula += "<div class=\"td fechaActualizacion" + caducat_class + "\" role=\"gridcell\">" + printStringFromNull(dada_node.fechaActualizacion, txtSinValor) + "</div>";

				codi_taula += "</div>";
				
			});

			codi_taula += "</div>";
			codi_taula += "</div>";

			if($.browser.opera)
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");

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
				escriptori_contingut_elm.find("#resultats .llistat .tbody a").unbind("click").bind("click", function() { Llistat.ficha(this); });

				// Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
				jQuery("#resultats .table .th a").unbind("click").click(function() {
					//Seteamos el ordreTipos y ordreCamp
					var tipo;
					if ( jQuery(this).parent().hasClass("DESC") ) {
						tipo = "ASC";
					} else if (  jQuery(this).parent().hasClass("ASC") ) {
						tipo = "DESC";
					} else {
						tipo = "ASC";
					}
					var campo = jQuery(this).attr("class");  
					ordreTipus_llistat_elm.val(tipo);
					ordreCamp_llistat_elm.val(campo);
					Llistat.ordena(this, opcions);
				});

				// Cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si")
					cercador_elm.find("input, select").removeAttr("disabled");

			});

		});

	};

	this.carregar = function(opcions) {
		// opcions: cercador (si, no), ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)

		Buscador = new BuscadorServicio();
		Buscador.orden.tipo = ordreTipus_llistat_elm.val();
		Buscador.orden.campo = ordreCamp_llistat_elm.val();
		Buscador.buscar(opcions, pagLlistat, Llistat);
		
	};
	
	
	// Exporta la búsqueda
	this.exporta = function(opcions) {	
			
		Buscador = new BuscadorServicio();
		Buscador.orden.tipo = ordreTipus_llistat_elm.val();
		Buscador.orden.campo = ordreCamp_llistat_elm.val();
		Buscador.buscar({cercador: "si", exportar: "si"}, pagExportar, Llistat);
	};

};

//items array
var Items_arr = new Array();

//detall
function CDetall() {
    
	this.extend = DetallBase;
	this.extend();
	
	var that = this;

	this.tipusAuditoria = 'servei';
	this.tipusEstadistica = 'servei';

	//Se comprueba que 
	this.guarda = function() {
		
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		item_ID = $("#item_id").val();

		dataVars = "id=" + item_ID;

		// ajax
		$.ajax({
			type: "POST",
			url: pagNormativaVigentes,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(data) {
				if (data.id == null || data.id > 0 ) {
					that.guardaFinal();
				} else if (data.id  == -66) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				} else {
					//Preguntar.
					Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtNormativaDerogadaTitol, text: txtNormativaDerogada, funcio: that.guardaFinal});
				}
			}
		});
		
	};
	//Se anyaden los campos que no se van a serializar directamente mediante .serialize()	
	//this._baseGuarda = this.guarda;	
	this.guardaFinal = function () {
		// Si el estado de publicación del servicio es distinto a 1 (Pública),
		// no comprobamos que existe un trámite de inicialización. Guardamos directamente.
		if ( ($('#item_estat').val() != 1) ) {

			urlParams = ModulNormativa.listaNormativas();
			urlParams += "&" + ModulMateries.listaMaterias();
			urlParams += "&" + ModulFetsVitals.listaHechosVitales();
			urlParams += "&" + ModulPublicObjectiu.listaPublics();
			
			that.guardaGenerico(urlParams);

		}

		// En cambio, si el estado de publicación es 1 (Pública), no hace falta comprobar normativas en servicios.
		else {

			urlParams = ModulNormativa.listaNormativas();
			urlParams += "&" + ModulMateries.listaMaterias();
			urlParams += "&" + ModulFetsVitals.listaHechosVitales();
			urlParams += "&" + ModulPublicObjectiu.listaPublics();

			that.guardaGenerico(urlParams);

		// Si no hay trámite de inicialización con estado de publicación 1, lanzamos mensaje de error.
		} 

	};

	this.urlPrevisualizar = urlPrevisualizarServicio;

	// Sobrecargo método para preview personalizado.
	// Se espera una URL de la forma:
	// - http://www.caib.es/govern/sac/visor_proc.do?lang=__lang__&amp;codi=__id__&amp;previ=__previ__  
	// - http://www.caib.es/seucaib/__lang__/__po_id__/__po_nombre__/tramites/tramite/__id__
	// - Etc.
	this.previsualitza = function() {

		var url = this.urlPrevisualizar;

		var idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
		var id = escriptori_detall_elm.find("#item_id").val();
		
		// Si la URL tiene parámetros separamos por &amp;, pasamos las entidades a &.
		url = url.replace(/&amp;/g, "&");
		
		// Obtenemos público objetivo, por si hay que mostrar previsualización en la SEU.
		var codigoPO = $(".ModulPublicObjectiu .seleccionats .listaOrdenable ul li:first-child input[type=hidden]").val();
		var nombrePO = $(".ModulPublicObjectiu .seleccionats .listaOrdenable ul li:first-child").text().toLowerCase();
		
		// Después, sustituimos el resto de parámetros.
		url = url.replace("__lang__", idiomaSeleccionat);
		url = url.replace("__id__", id);
		url = url.replace("__previ__", "s");
		url = url.replace("__po_id__", codigoPO);
		url = url.replace("__po_nombre__", nombrePO);

		var ancho = 1024;
		var alto = 768;
		window.open(url, 'ventanaPrevisualizarServicio', "width=" + ancho + ", height=" + alto);

	};

	this.iniciar = function() {
		
		// Desactivamos que se cambie el detalle a modificado por cambiar los checkboxes de materias relacionadas con el servicio.
		jQuery('#modul_materies .llistat li input[type=checkbox]').unbind('change');
		
		// dates
		$("#item_data_despublicacion").datetimepicker({ format: 'yyyy/MM/dd HH:mm' , setDate: new Date(),hour:'23', minute:'59' });
		$("#item_data_publicacion").bind("blur",Detall.dataPublicacio).datetimepicker({ timeFormat: 'hh:mm' });
		

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

			// Solo mostramos los idiomas activos para los campos multi-idioma.
			escriptori_detall_elm.find(".element.multilang .campoIdioma").hide();            
			escriptori_detall_elm.find(".element.multilang .campoIdioma:first-child").show().addClass("seleccionat");
			
		}

		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		                

		// Sincronizar campos sin idioma en zona multi-idioma.   
		jQuery("#item_codigo_servicio,#item_codigo_servicio_es,#item_codigo_servicio_en,#item_codigo_servicio_de,#item_codigo_servicio_fr").change(function(){
			jQuery("#item_codigo_servicio,#item_codigo_servicio_es,#item_codigo_servicio_en,#item_codigo_servicio_de,#item_codigo_servicio_fr").val( jQuery(this).val() );
		});
		//#366 se añade SIA
		jQuery("#item_codigo_sia,#item_codigo_sia_es,#item_codigo_sia_en,#item_codigo_sia_de,#item_codigo_sia_fr").change(function(){
			jQuery("#item_codigo_sia,#item_codigo_sia_es,#item_codigo_sia_en,#item_codigo_sia_de,#item_codigo_sia_fr").val( jQuery(this).val() );
		});
		
		jQuery("#item_estado_sia,#item_estado_sia_es,#item_estado_sia_en,#item_estado_sia_de,#item_estado_sia_fr").change(function(){
			jQuery("#item_estado_sia,#item_estado_sia_es,#item_estado_sia_en,#item_estado_sia_de,#item_estado_sia_fr").val( jQuery(this).val() );
		});
		
		jQuery("#item_fecha_sia,#item_fecha_sia_es,#item_fecha_sia_en,#item_fecha_sia_de,#item_fecha_sia_fr").change(function(){
			jQuery("#item_fecha_sia,#item_fecha_sia_es,#item_fecha_sia_en,#item_fecha_sia_de,#item_fecha_sia_fr").val( jQuery(this).val() );
		});
		
		jQuery("#item_fi_vida_administrativa,#item_fi_vida_administrativa_es,#item_fi_vida_administrativa_en,#item_fi_vida_administrativa_de,#item_fi_vida_administrativa_fr").change(function(){
			jQuery("#item_fi_vida_administrativa,#item_fi_vida_administrativa_es,#item_fi_vida_administrativa_en,#item_fi_vida_administrativa_de,#item_fi_vida_administrativa_fr").val( jQuery(this).val());
		});

		jQuery("#item_silenci_combo,#item_silenci_combo_es,#item_silenci_combo_en,#item_silenci_combo_de,#item_silenci_combo_fr").change(function(){
			jQuery("#item_silenci_combo,#item_silenci_combo_es,#item_silenci_combo_en,#item_silenci_combo_de,#item_silenci_combo_fr").val( jQuery(this).val());
		});
		
		jQuery("#item_taxa,#item_taxa_es,#item_taxa_en,#item_taxa_de,#item_taxa_fr").change(function(){
			jQuery("#item_taxa,#item_taxa_es,#item_taxa_en,#item_taxa_de,#item_taxa_fr").attr("checked", jQuery(this).is(":checked"));
		});

		jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").change(function(){
			jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").val( jQuery(this).val() );
		});

		jQuery("#item_organ_instructor, #item_organ_instructor_es, #item_organ_instructor_ca, #item_organ_instructor_en, #item_organ_instructor_de, #item_organ_instructor_fr").change(function(){        
			jQuery("#item_organ_instructor, #item_organ_instructor_es, #item_organ_instructor_ca, #item_organ_instructor_en, #item_organ_instructor_de, #item_organ_instructor_fr").val( jQuery(this).val() );        
		});
		
		jQuery("#item_servei_responsable, #item_servei_responsable_es, #item_servei_responsable_ca, #item_servei_responsable_en, #item_servei_responsable_de, #item_servei_responsable_fr").change(function(){        
			jQuery("#item_servei_responsable, #item_servei_responsable_es, #item_servei_responsable_ca, #item_servei_responsable_en, #item_servei_responsable_de, #item_servei_responsable_fr").val( jQuery(this).val() );        
		});
		jQuery("#item_organ, #item_organ_es, #item_organ_ca, #item_organ_en, #item_organ_de, #item_organ_fr").change(function(){        
			jQuery("#item_organ, #item_organ_es, #item_organ_ca, #item_organ_en, #item_organ_de, #item_organ_fr").val( jQuery(this).val() );        
		});

		jQuery("#item_iniciacio,#item_iniciacio_es,#item_iniciacio_ca,#item_iniciacio_en,#item_iniciacio_de,#item_iniciacio_fr").change(function(){
			jQuery("#item_iniciacio,#item_iniciacio_es,#item_iniciacio_ca,#item_iniciacio_en,#item_iniciacio_de,#item_iniciacio_fr").val( jQuery(this).val() );
		});

		jQuery("#item_finestreta_unica").change(function() { 
			$("#item_finestreta_unica").attr("checked", jQuery(this).is(":checked")); 
		});
		

		// boton de traducir
		jQuery("#botoTraduirServei").unbind("click").bind("click", function() {
			Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
		});
		
	};

	this.traduirWrapper = function () {
		that.traduir(pagTraduir, CAMPOS_TRADUCTOR_SERVEI, DATOS_TRADUCIDOS_SERVEI);
	};

	this.dataPublicacio = function(e) {		
//		if ($(this).val() == "") {
//		$(this).val(txtImmediat);
//		}
	};

	this.nou = function() {

		//Ocultar paneles
		jQuery("#modul_documents, #modul_tramits").hide();
		jQuery("#modulAuditories, #modulEstadistiques").hide();
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		//#366 se añade sia
		jQuery("#caja_item_codigo_sia, #caja_item_codigo_sia_es, #caja_item_codigo_sia_en, #caja_item_codigo_sia_de, #caja_item_codigo_sia_fr").hide();
		jQuery("#caja_item_estado_sia, #caja_item_estado_sia_es, #caja_item_estado_sia_en, #caja_item_estado_sia_de, #caja_item_estado_sia_fr").hide();
		jQuery("#caja_item_fecha_sia, #caja_item_fecha_sia_es, #caja_item_fecha_sia_en, #caja_item_fecha_sia_de, #caja_item_fecha_sia_fr").hide();
		
		
		// Borrar del desplegable de estado de publicación las opciones no válidas al crear un nuevo servicio:
		// (1 = Pública, 3 = Reserva).
		$("#item_estat option[value=]").remove(); // Opción por defecto, sin valor. La borramos también.
		$("#item_estat option[value=1]").remove();
		$("#item_estat option[value=3]").remove();

		ModulMateries.nuevo();
		ModulFetsVitals.nuevo();
		ModulNormativa.nuevo();
		ModulPublicObjectiu.nuevo();
		EscriptoriNormativa.nuevo();

		escriptori_detall_elm.find(".botonera li.btnEliminar,.botonera li.btnPrevisualizar").hide();
		escriptori_detall_elm.find("div.fila input.nou, div.fila textarea.nou").val("").end().find("h2:first").text(txtNouTitol);
		escriptori_detall_elm.find("div.fila input.nou[type=checkbox]").val("on");

		doc_seleccionats_elm = escriptori_detall_elm.find("div.modulDocuments div.seleccionats");
		doc_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaDocuments + ".");

		if (suggeriment_elm.size() != 0 && suggeriment_elm.css("display") != "none") {
			suggeriment_elm.slideUp(300);
		}

		$("#item_familia").val("");
		$("#item_id").val("");
		$("#item_organ_id").val("");
		$("#item_organ").val("");

		$("#modulPrincipal :input").each(limpiarCampo);

		if (typeof idUAMollapa == "undefined" || idUAMollapa == null || idUAMollapa == "") {
			
			$("#item_organ_instructor_id").val("");
			$("#item_servei_responsable_id").val("");

			//test
			$("#item_organ_id").val("");
			
		} else {
			
			$("#item_organ_instructor_id").val(idUAMollapa);
			$("#item_organ_instructor").val(nomUAMollapa).change();

			//test
			$("#item_organ_id").val(idUAMollapa);
			$("#item_organ").val(nomUAMollapa).change();
			
			$("#item_servei_responsable_id").val(idUAMollapa);
			$("#item_servei_responsable").val(nomUAMollapa).change();
			
		}

		$("#modulLateral p.baix:first").removeClass("iCaducat").removeClass("iPublicat");

		escriptori_contingut_elm.fadeOut(300, function() {
			escriptori_detall_elm.fadeIn(300, function() {
				// activar				
				itemID_ultim = 0;
			});
		});

		this.actualizaEventos();

		this.modificado(false);
		
	};

	this.pintar = function(dades) {
		dada_node = dades;

		// Mostrar paneles
		jQuery("#modul_documents").show();
		jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").show();        
		jQuery("#caja_item_codigo_sia, #caja_item_codigo_sia_es, #caja_item_codigo_sia_en, #caja_item_codigo_sia_de, #caja_item_codigo_sia_fr").show();        
		jQuery("#caja_item_estado_sia, #caja_item_estado_sia_es, #caja_item_estado_sia_en, #caja_item_estado_sia_de, #caja_item_estado_sia_fr").show();        
		jQuery("#caja_item_fecha_sia, #caja_item_fecha_sia_es, #caja_item_fecha_sia_en, #caja_item_fecha_sia_de, #caja_item_fecha_sia_fr").show();
		$("#modulLateral li.btnEliminar").show();
		
		// Previsualizar.
		escriptori_detall_elm.find("a.elimina, a.previsualitza").show().end().find("h2:first").text(txtDetallTitol);
		
		// Bloque de pestanyas de idiomas
		for (var i in idiomas) {

			var idioma = idiomas[i];

			$("#item_nom_" + idioma).val(printStringFromNull(dada_node[idioma]["nombre"]));
			$("#item_objeto_" + idioma).val(printStringFromNull(dada_node[idioma]["objeto"]));
			$("#item_destinatarios_" + idioma).val(printStringFromNull(dada_node[idioma]["destinatarios"]));
			$("#item_requisitos_" + idioma).val(printStringFromNull(dada_node[idioma]["requisitos"]));
			$("#item_observaciones_" + idioma).val(printStringFromNull(dada_node[idioma]["observaciones"]));

		}
		// Fin bloque de pestanyas de idiomas

		//ID
		$("#item_clave_primaria").val(dada_node.item_id);
		$("#item_clave_primaria").change();
		$("#item_id").val(dada_node.item_id);

		
		/********* MODULO ESCRIPTORI ************/
		//DADES
		if (dada_node.item_codigo_servicio != undefined) {			
			jQuery("#item_codigo_servicio").val(dada_node.item_codigo_servicio);
			jQuery("#item_codigo_servicio").change();
		} else {
			jQuery("#item_codigo_servicio,#item_codigo_servicio_es,#item_codigo_servicio_en,#item_codigo_servicio_de,#item_codigo_servicio_fr").val("");
		}
		
		if (dada_node.item_codigo_sia != undefined) {
			jQuery("#item_codigo_sia").val(dada_node.item_codigo_sia);
			jQuery("#item_codigo_sia").change();
		} else {
			jQuery("#item_codigo_sia").val("");
		}
		
		if (dada_node.item_fecha_sia != undefined) {
			jQuery("#item_fecha_sia").val(dada_node.item_fecha_sia);
			jQuery("#item_fecha_sia").change();
		} else {
			jQuery("#item_fecha_sia").val("");
		}
		
		if (dada_node.item_estado_sia != undefined) {
			
			if (dada_node.item_estado_sia == 'A') {
				jQuery("#item_estado_sia").val("Alta");
			} else if (dada_node.item_estado_sia == 'B') {
				jQuery("#item_estado_sia").val("Baixa");
			}  else if (dada_node.item_estado_sia == 'RC') {
				jQuery("#item_estado_sia").val("Reactivat");
			} else if (dada_node.item_estado_sia == 'M') {
				jQuery("#item_estado_sia").val("Modificat");
			} else {
				jQuery("#item_estado_sia").val(dada_node.item_estado_sia);
			}
			
			jQuery("#item_estado_sia").change();
			
		} else {
			
			jQuery("#item_estado_sia").val("");
			
		}
				
		//DADES DE CONTACTE
		if (dada_node.item_organ_instructor_id != undefined) {
			$("#item_organ_instructor_id").val(dada_node.item_organ_instructor_id);
			$("#item_organ_instructor").val(dada_node.item_organ_instructor_nom).change();
		} else {
			$("#item_organ_instructor_id").val("");
			$("#item_organ_instructor").val("");
		}

		if (dada_node.item_servei_responsable_id != undefined) {
			$("#item_servei_responsable_id").val(dada_node.item_servei_responsable_id);
			$("#item_servei_responsable").val(dada_node.item_servei_responsable_nom);
		} else {
			$("#item_servei_responsable_id").val("");
		}
		
		if (dada_node.item_responsable_nombre != undefined) {
			jQuery("#item_responsable_nombre").val(dada_node.item_responsable_nombre);
			jQuery("#item_responsable_nombre").change();
		} else {
			jQuery("#item_responsable_nombre").val("");
		}
		
		if (dada_node.item_email != undefined) {
			jQuery("#item_email").val(dada_node.item_email);
			jQuery("#item_email").change();
		} else {
			jQuery("#item_email").val("");
		}
		
		if (dada_node.item_telefon != undefined) {
			jQuery("#item_telefon").val(dada_node.item_telefon);
			jQuery("#item_telefon").change();
		} else {
			jQuery("#item_telefon").val("");
		}
		
		//DADES DEL TRAMITE
		if (dada_node.item_tramite_url != undefined) {
			jQuery("#item_tramite_url").val(dada_node.item_tramite_url);
			jQuery("#item_tramite_url").change();
		} else {
			jQuery("#item_tramite_url").val("");
		}
		
		if (dada_node.item_tramite_id != undefined) {
			jQuery("#item_tramite_id").val(dada_node.item_tramite_id);
			jQuery("#item_tramite_id").change();
		} else {
			jQuery("#item_tramite_id").val("");
		}
		
		if (dada_node.item_tramite_version != undefined) {
			jQuery("#item_tramite_version").val(dada_node.item_tramite_version);
			jQuery("#item_tramite_version").change();
		} else {
			jQuery("#item_tramite_version").val("");
		}
		
		
		/**********   Modulo Lateral ****************************/
		//Publicacio
		marcarOpcionSelect("item_estat", dada_node.item_estat);
		$("#item_data_publicacion").val(dada_node.item_data_publicacion);
		$("#item_data_despublicacion").val(dada_node.item_data_despublicacion);

		//Publico objetivo.
		ModulPublicObjectiu.inicializarPublics(dada_node.publicsObjectiu);

		//Tasa
		if (dada_node.item_tasa_url != undefined) {
			jQuery("#item_tasa_url").val(dada_node.item_tasa_url);
			jQuery("#item_tasa_url").change();
		} else {
			jQuery("#item_tasa_url").val("");
		}
		
		//Hechos vitales.
		ModulFetsVitals.pintar( dada_node.listadoHechosVitales );
		

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

		this.modificado(false);
	
	};

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
		
	};

	this.pintarModulos = function(dades) {

		hechosVitalesAsignados = dades.fetsVitals;

		ModulMateries.inicializarMaterias(dades.materies);
		ModulDocuments.inicializarDocuments(dades.documents);
		ModulFetsVitals.inicializarHechosVitales(hechosVitalesAsignados);
		ModulNormativa.inicializarNormativas(dades.normatives);

	};

	this.ocultarModulos = function(selector) {

		if ( !selector.hasClass("publicacio") && !selector.attr("id") == "modul_documents" 
				&& !selector.children().is(".modulMateries")
				&& !selector.children().is(".modulNormatives")
				&& !selector.attr("id") == "fetsVitals" )
			selector.addClass("invisible");
		
		//#349 si entramos en detalle y volvemos a nuevo se mostraban los modulos
		if (selector.attr("id") == "modul_materies" || selector.attr("id") == "modul_normatives"){
			selector.addClass("invisible");
		}

	};
	
};