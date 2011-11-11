// QUADRE DE CONTROL

$(document).ready(function() {
	inicializarTab('#tabAlta');
	inicializarTab('#tabBaixa');
	inicializarTab('#tabModificacio');
});


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
				
		jQuery('#resultats div').each(function() {
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