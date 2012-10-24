// Módulo de unidades hijas.
$(document).ready(function() {
	jQuery(".submenuUA .detalle").click(function(){
		jQuery("#escritorioUnidadesHijas").hide();
		jQuery("#escriptori_detall").show();
	});
	
	jQuery(".submenuUA .hijas").click(function(){
		jQuery("#escritorioUnidadesHijas").show();
		jQuery("#escriptori_detall").hide();
		
		escritorioUnidadesHijas.inicia();
	});
	
	escritorioUnidadesHijas = new CEscritorioUnidadesHijas();
	
	multipagina = new Multipagina();
});

function CEscritorioUnidadesHijas(){		
	this.extend = ListadoBase;
    this.extend("opcionesUnidadesHijas", "resultadosUnidadesHijas", "", "cercador_contingut_unitats_filles", "", "", "btnNuevaUAhija", "btnBuscarUnidadesHijasForm", "btnLimpiarUnidadesHijasForm");
    
    var that = this;

	var $obj;
	
	this.tipoOrden = "DESC";
	this.campoOrden = "nombre";
	
	this.inicia = function(){
		$obj = jQuery("#escritorioUnidadesHijas");
	
		this.carregar({});
	}
	
	// Cambia de página.
	this.cambiaPagina = function( pag ){
		multipagina.setPaginaActual(pag-1);
		pag_Pag = pag;
		this.anar(pag);
	}
	
	this.finCargaListado = function(opcions,data){
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");	
		
		// total
		resultats_total = parseInt(data.total,10);
		
		if (resultats_total > 0) {
			
			txtT = (resultats_total > 1) ? txtUnitats : txtUnitat;
			
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
			ordre_T = this.tipoOrden;
			ordre_C = this.campoOrden;
			ordre_c1 = (ordre_C == "nombre") ? " " + ordre_T : "";		
			ordre_c2 = (ordre_C == "fecha") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if (resultats_total > 1) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				
				if (ordre_C == "nombre") {
					txt_per = txtNombre;
				}else if( ordre_C == "fecha" ){
					txt_per = txtData;
				}
				
				txt_ordenacio += ", " + txt_ordenats + " " + txtPer + " <em>" + txt_per + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobats + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";

			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtNombre + "</a></div>";
			codi_cap2 = "<div class=\"th fecha" + ordre_c2 + "\" role=\"columnheader\">" + txtData + "</a></div>";
			
			if( !modoBuscador ){
				codi_cap3 = "<div class=\"th orden\" role=\"columnheader\">" + txtOrdre + "</a></div>";	
			}else{
				codi_cap3 = '';
			}
			
			// codi taula
			codi_taula = "<div class=\"table llistat uahijas\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
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
					
					codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
					
					codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
						codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
						codi_taula += "<a class=\"uahija_"+dada_node.id+"\" href=\"javascript:;\" class=\"nom\">" + dada_node.nombre + "</a>";
					codi_taula += "</div>";
					
					codi_taula += "<div class=\"td fecha\" role=\"gridcell\">" + dada_node.fecha + "</div>";
					
					if( !modoBuscador ){
						
						codi_taula += "<div class=\"td orden\" role=\"gridcell\">";
					
							codi_taula += that.getHtmlSelectorOrdenacion("uaHija_"+dada_node.id, dada_node.ordre, resultats_total );
					
						codi_taula += "</div>";
						
					}
					
					codi_taula += "</div>";

				});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			// Actualizamos el navegador multipágina.
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "escritorioUnidadesHijas.cambiaPagina"
			});
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaEdificis + ".</p>";
			
		}
		
		dades_elm = $obj.find("#resultadosUnidadesHijas div.actiu:first div.dades:first");
		dades_elm.fadeOut(300, function() {
			// pintem
			dades_elm.html(codi_final).fadeIn(300, function() {
			
				$obj.find(".resultats .llistat .tbody a").unbind("click").bind("click",function(){escritorioUnidadesHijas.ficha(this);});
                
                // Asociamos el evento onclick a las cabeceras del listado para que sea ordenable.
                jQuery("#resultadosUnidadesHijas .table .th a").unbind("click").click(function(){
                    escritorioUnidadesHijas.ordena(this,opcions);
                });                
							
				// cercador
				if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
					$obj.find("#cercador_contingut_unitats_filles").find("input, select").removeAttr("disabled");
				}
				
				jQuery("#resultadosUnidadesHijas .llistat .tbody select.ordenacion").bind("change").bind("change",function(){
					
					var itemID = jQuery(this).attr("id").split("_")[1];
					var orden = jQuery(this).val();
					
					//var dataVars = "id=" + itemID;
					var dataVars = "id=" + itemID+"&orden="+orden;
					
					$.ajax({
						type: "POST",
						url: pagPujar,
						data: dataVars,
						dataType: "json",
						error: function(){
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
						},
						success: function(data){
							that.anulaCache();
							that.carregar({});
						}
					});
					
				});
				
			});
		});
	}

	this.carregar = function(opcions) {		
		// opcions: ajaxPag (integer), ordreTipus (ASC, DESC), ordreCamp (tipus, carrec, tractament)
		var modoBuscador = (typeof opcions.cercador != "undefined" && opcions.cercador == "si");
		var modoListado = !modoBuscador;
		
		var $inputPagPagina, $inputOrdreTipus, $inputOrdreCamp;
		
		dataVars = "";
		
		// cercador
		dataVars_cercador="";
		
		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {
			
			$inputPagPagina = $obj.find(".resultats.C input.pagPagina");
			$inputOrdreTipus = $obj.find(".resultats.C input.ordreTipus");
			$inputOrdreCamp = $obj.find(".resultats.C input.ordreCamp");
			
			var uaMevesVal = $("#cerca_uaMeves").is(':checked') ? 1 : 0;
			var uaFillesVal = $("#cerca_uaFilles").is(':checked') ? 1 : 0;
			
			dataVars_cercador = "&codi=" + $("#cerca_codi").val();
			dataVars_cercador += "&textes=" + $("#cerca_textes").val();
			dataVars_cercador += "&espacio_territorial=" + $("#uahija_espacioTerritorial").val();
			dataVars_cercador += "&materia=" + $("#uahija_tratamiento").val();
			dataVars_cercador += "&fetVital=" + $("#cerca_fetVital").val();
			dataVars_cercador += "&uaMeves=" + uaMevesVal;
			dataVars_cercador += "&uaFilles=" + uaFillesVal;
			
		}else{
			
			$inputPagPagina = $obj.find(".resultats.L input.pagPagina");
			$inputOrdreTipus = $obj.find(".resultats.L input.ordreTipus");
			$inputOrdreCamp = $obj.find(".resultats.L input.ordreCamp");
			
		}
		
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			$inputOrdreTipus.val(opcions.ordreTipus);
		}
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			$inputOrdreCamp.val(opcions.ordreCamp);
		}
		
		// paginacio
		//pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt($inputPagPagina.val(),10);
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : multipagina.getPaginaActual();
			
		// ordre
		ordre_Tipus = $inputOrdreTipus.val();
		ordre_Camp = $inputOrdreCamp.val();
			
		// variables
		dataVars += "pagPagina=" + pag_Pag + "&ordreTipus=" + ordre_Tipus + "&ordreCamp=" + ordre_Camp + dataVars_cercador;
		
		// @todo Descomentar una vez fijada la variable urlListaUAs.
		// ajax
		/*if ( ( modoListado && !Llistat.cacheDatosListado ) || modoBuscador ){
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
		}*/
		// (MAQUETACIÓN) Llamamos al método para actualizar el listado directamente.
		var datos = {
			total: 27,
			nodes:[
				{id: 1, nombre: "Presidencia",	fecha: "06/01/2010"},
				{id: 2, nombre: "Conseller&iacute;a de Presidencia", fecha: "06/02/2010"},
				{id: 3, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 4, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 5, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 6, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 7, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 8, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 9, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 10, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 11, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 12, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 13, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 14, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 15, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 16, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 17, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 18, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 19, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 20, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 21, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 22, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 23, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 24, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 25, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 26, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"},
				{id: 27, nombre: "Conseller&iacute;a d&apos;Econom&iacute;a i Hisenda", fecha: "07/03/2010"}
				],
			};				
		that.finCargaListado(opcions,datos);
			
	}
	
	// Método sobreescrito.
	this.nuevaFicha = function(){
		alert("Implementar");
	}
};