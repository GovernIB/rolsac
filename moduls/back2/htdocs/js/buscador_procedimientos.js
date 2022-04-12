function BuscadorProcedimiento() {

	this.orden = { "tipo" : "DESC", "campo" : "id" };

	this.buscar = function(opcions, url, listado) {

		var procedimientoJSON = { "id" : "", "familia" : { "id" : "" }, "iniciacion" : { "id" : "" }, "tramite" : "", "indicador" : "", "ventanillaUnica" : "", "nombreProcedimiento" : "" ,"silencio" : { "id" : "" }, "codigoSIA" :"","estadoSIA" :""};

		var paginacionJSON = { "pagPag" : 0 , "pagRes" : 0 , "criterioOrdenacion" : "", "propiedadDeOrdenacion" : "" };


		var criteria = {
				"procedimiento" : procedimientoJSON,
				"uaHijas" : "",
				"uaPropias" : "",
				"visibilidad" : 0,
				"idHechoVital" : "",
				"idMateria" : "",
				"idPublicoObjetivo" : "",
				"idPlataforma" : "",
				"idTramiteTelematico" : "",
				"enPlazo" : null,
				"telematico" : null,
				"paginacion" : paginacionJSON,
				"comun" : ""
		};


		// cercador
		if (typeof opcions.cercador != "undefined" && opcions.cercador == "si") {

			criteria.procedimiento.id = $("#cerca_codi").val();
			criteria.procedimiento.familia.id = $("#cerca_familia").val();
			criteria.procedimiento.iniciacion.id = $("#cerca_iniciacio").val();
			criteria.procedimiento.tramite = $("#cerca_tramit").val();
			criteria.procedimiento.silencio.id = $("#cerca_silenci").val();
			criteria.procedimiento.codigoSIA = $("#cerca_sia").val();
			criteria.procedimiento.estadoSIA = $("#cerca_bolcat_sia").val();

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
			criteria.idPlataforma = parseInt( $("#cerca_plataforma").val() );
			criteria.idTramiteTelematico = parseInt( $("#cerca_plantilla").val() );
			criteria.pdtValidar = parseInt( $("#cerca_pdtValidar").val() );
			criteria.mensajePorLeer = parseInt( $("#cerca_mensajePorLeer").val() );
			criteria.estado = parseInt( $("#cerca_estado").val() );

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
		if ($("#cerca_comun") != null && $("#cerca_comun").val() != '') {
			criteria.comun = $("#cerca_comun").val();
		}

		//Si es tipo exportar
		if (typeof opcions.exportar != "undefined" && opcions.exportar == "si") {

			criteria.paginacion.pagPag = 0;
			criteria.paginacion.pagRes = 99999;

			Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtProcessant});
			var xhr = new XMLHttpRequest();
			xhr.open('POST', url, true);
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
			xhr.send("criteria=" + JSON.stringify(criteria));

		} else {
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

}

