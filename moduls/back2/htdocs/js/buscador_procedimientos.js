function BuscadorProcedimiento() {
	
	this.orden = { "tipo" : "DESC", "campo" : "id" };

	this.buscar = function(opcions, url, listado) {

		var procedimientoJSON = { "id" : "", "familia" : { "id" : "" }, "iniciacion" : { "id" : "" }, "tramite" : "", "indicador" : "", "ventanillaUnica" : "", "nombreProcedimiento" : "" ,"silencio" : { "id" : "" }, "codigoSIA" :""};

		var paginacionJSON = { "pagPag" : 0 , "pagRes" : 0 , "criterioOrdenacion" : "", "propiedadDeOrdenacion" : "" };

		var criteria = { 
				"procedimiento" : procedimientoJSON,
				"uaHijas" : "",
				"uaPropias" : "",
				"visibilidad" : 0,
				"idHechoVital" : "",
				"idMateria" : "",
				"idPublicoObjetivo" : "",
				"enPlazo" : null,
				"telematico" : null,
				"paginacion" : paginacionJSON
		};


		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {

			criteria.procedimiento.id = $("#cerca_codi").val();
			criteria.procedimiento.familia.id = $("#cerca_familia").val();
			criteria.procedimiento.iniciacion.id = $("#cerca_iniciacio").val();
			criteria.procedimiento.tramite = $("#cerca_tramit").val();
			criteria.procedimiento.silencio.id = $("#cerca_silenci").val();
			criteria.procedimiento.codigoSIA = $("#cerca_sia").val();

			switch ( $("#cerca_indicador").val() ) {

			case "1":
				criteria.procedimiento.indicador = 1;
				break;

			case "0":
				criteria.procedimiento.indicador = 0;
				break;
			
			case "-1":
				criteria.procedimiento.indicador = -1;
				break;

			default:
				criteria.procedimiento.indicador = null;
				break;

			}


			switch (  $("#cerca_finestreta").val() ) {

			case "1":
				criteria.procedimiento.ventanillaUnica = 1;
				break;

			case "0":
				criteria.procedimiento.ventanillaUnica = 0;
				break

			default:
				criteria.procedimiento.ventanillaUnica = null;
				break;

			}


			criteria.procedimiento.nombreProcedimiento = $("#cerca_textes").val();

			criteria.uaHijas = $("#cerca_uaFilles").is(':checked') ? 1 : 0;
			criteria.uaPropias = $("#cerca_uaMeves").is(':checked') ? 1 : 0;
			var visibilidad = $("#cerca_estat").val();

			criteria.visibilidad = (visibilidad == "") ? 0 : visibilidad;
			criteria.idHechoVital = $("#cerca_fet_vital").val();
			criteria.idMateria = $("#cerca_materia").val();
			criteria.idPublicoObjetivo = parseInt( $("#cerca_publicObjectiu").val() );

			switch ( $("#enPlazo").val() ) {

			case "1":
				criteria.enPlazo = true;
				break;

			case "0":
				criteria.enPlazo = false;
				break

			default:
				criteria.enPlazo = null;
				break;

			}


			switch ( $("#telematico").val() ) {

			case "1":
				criteria.telematico = true;
				break;

			case "0":
				criteria.telematico = false;
				break

			default:
				criteria.telematico = null;
				break;

			}


			pagPagina_elm = pagPagina_cercador_elm;
			ordreTipus_elm = ordreTipus_cercador_elm;
			ordreCamp_elm = ordreCamp_cercador_elm;

		} else {

			pagPagina_elm = pagPagina_llistat_elm;
			ordreTipus_elm = ordreTipus_llistat_elm;
			ordreCamp_elm = ordreCamp_llistat_elm;

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
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag, 10) : multipagina.getPaginaActual();

		// ordre
		ordre_Tipus = ordreTipus_elm.val();
		ordre_Camp = ordreCamp_elm.val();

		criteria.paginacion.pagPag = pag_Pag;
		criteria.paginacion.pagRes = pag_Res;
		criteria.paginacion.criterioOrdenacion = this.orden.tipo;
		criteria.paginacion.propiedadDeOrdenacion = this.orden.campo;

		// ajax		
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: "criteria=" + JSON.stringify(criteria),
			error: function() {

				if (!a_enllas) {
					// missatge
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					// error
					Error.llansar();
				}
			},
			success: function(data) {				
				listado.finCargaListado(opcions,data);					
			}
		});

	}

}

