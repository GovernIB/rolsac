// TM INDEX

$(document).ready(function() {
	inicializarTab('#tabUnitatOraganica');
	inicializarTab('#tabFitxa');
	inicializarTab('#tabNormativa');
	inicializarTab('#tabProcediment');
	inicializarBtn();
});


function inicializarBtn() {
	jQuery('#opcionsindex a.btnIndex').bind("click", function() {
		var $btn = jQuery(this);
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		if ($btn.hasClass('unitatOrganica')) {
			$.ajax({
				type: "POST",
				url: pagIndexUnitatOrganica, 
				dataType: "json",
				error: function() {					
					// missatge
					Missatge.cancelar();
					setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"})', 400);
					
				},
				success: function(data) {
					Missatge.cancelar();
					if (data.id > 0) {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom})', 400);
					} else {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"})', 400);
					}
				}
			});
		}  else if ($btn.hasClass('fitxa')) {
			$.ajax({
				type: "POST",
				url: pagIndexFitxa, 
				dataType: "json",
				error: function() {					
					// missatge
					Missatge.cancelar();
					setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"})', 400);
					
				},
				success: function(data) {
					Missatge.cancelar();
					if (data.id > 0) {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom})', 400);
					} else {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"})', 400);
					}
				}
			});
		}  else if ($btn.hasClass('normativa')) {
			$.ajax({
				type: "POST",
				url: pagIndexNormativa, 
				dataType: "json",
				error: function() {					
					// missatge
					Missatge.cancelar();
					setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"})', 400);
					
				},
				success: function(data) {
					Missatge.cancelar();
					if (data.id > 0) {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom})', 400);
					} else {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"})', 400);
					}
				}
			});
		}  else if ($btn.hasClass('procediment')) {
			$.ajax({
				type: "POST",
				url: pagIndexProcediment, 
				dataType: "json",
				error: function() {					
					// missatge
					Missatge.cancelar();
					setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"})', 400);
					
				},
				success: function(data) {
					Missatge.cancelar();
					if (data.id > 0) {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom})', 400);
					} else {
						setTimeout('Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"})', 400);
					}
				}
			});
		}
	});
	
}

function inicializarTab(tab) {
	jQuery(tab).bind("click", function() {
			
		var lis = jQuery(this).parents('ul:first').find('li');
		var liClickat = jQuery(this).parent();
		var id;
				
		lis.each(function() {
			var liActual = jQuery(this);
			if (liClickat.attr('id') == liActual.attr('id')) {
				liActual.addClass('actiu');
				id = liActual.attr('id');
			} else {
				liActual.removeClass('actiu');
			}
		});
		
		id = id.replace(/li/i, "div");
		var divAActivar = jQuery('#' + id);
				
		jQuery('#opcionsindex div').each(function() {
			var divActual = jQuery(this);
			if (divActual.attr('id') == divAActivar.attr('id')) {
				divAActivar.addClass('actiu');
				divAActivar.show();
			} else {
				divActual.removeClass('actiu');
				divActual.hide();
			}
		});
		
	});
}