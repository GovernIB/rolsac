// TM INDEX

$(document).ready(function() {
	inicializarTab('#tabUnitatOraganica');
	inicializarTab('#tabFitxa');
	inicializarTab('#tabNormativa');
	inicializarTab('#tabProcediment');
	inicializarBtn();
});


function inicializarBtn() {
	jQuery('#indexContainer a.btn').bind("click", function() {
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
        
        var $btn = jQuery(this);
        var url;        

		if ($btn.hasClass('unitatOrganica')) {
            url = pagIndexUnitatOrganica;
		} else if ($btn.hasClass('fitxa')) {
			url = pagIndexFitxa;
		} else if ($btn.hasClass('normativa')) {
            url = pagIndexNormativa;
		} else if ($btn.hasClass('procediment')) {
            url = pagIndexProcediment;
        }
        
        $.ajax({
            type: "POST",
            url: url, 
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
				
		jQuery('#indexContainer div.indexContainer').each(function() {
			var divActual = jQuery(this);
			if (divActual.attr('id') == divAActivar.attr('id')) {
				divAActivar.addClass('actiu');
			} else {
				divActual.removeClass('actiu');
			}
		});
		
	});
}