/**
 * Clase que implementa la lógica del módulo de auditorías
 * Todos los parametros son opcionales.
 * @param idModuloAuditorias = Id del div HTML de auditorías
 */
function ModulAuditories(idModuloAuditorias){

	if (typeof idModuloAuditorias == "undefined" || idModuloAuditorias == "") idModuloAuditorias = "#modulAuditories";

	// Anadir los "#" por comodidad.
	var ID_MARK = "#";
	if (idModuloAuditorias.substring(0,1) != ID_MARK) idModuloAuditorias = ID_MARK + idOpciones;
	
	var that = this;
	
	// Atributo que contiene el id del elemento que se está visualizando en la ficha.
	this.itemID;
	
	var moduloAuditorias_elm = jQuery(idModuloAuditorias);
	
	// Realizar una búsqueda
	this.busca = function(tipusAuditoria, itemID){		
			
		that.carregar(tipusAuditoria, itemID);

	}
	
	this.carregar = function(tipusAuditoria, itemID) {

		// variables
		var dataVars = "&id=" + itemID;
		dataVars += "&tipus=" + tipusAuditoria;
										
		// ajax		
		$.ajax({
			type: "POST",
			url: pagAuditories,
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
				Auditoria.finCargaListado(tipusAuditoria, itemID, data);
			}
			});
	
	}

	this.finCargaListado = function( tipusAuditoria, itemID, data ){

		// total
		var resultats_total = parseInt(data.total,10);
		
		// codi taula
		codi_taula = '<table>';
		codi_taula +=' <thead>';
		codi_taula +='  <th class="usuario"><div>USUARI</div></th>';
		codi_taula +='  <th class="fecha"><div>DATA</div></th>';
		codi_taula +='  <th class="operacion"><div>OPERACI&Oacute;</div></th>';
		codi_taula +=' </thead>';                    
		codi_taula +=' <tbody>';
		
		if (resultats_total > 0) {
			// codi cuerpo
			$(data.nodes).each(function(i) {
				dada_node = this;

				codi_taula += '<tr>';
				codi_taula += ' <td class="usuario"><div>' + dada_node.usuario + '</div></td>';
				codi_taula += ' <td class="fecha"><div>' + dada_node.fecha + '</div></td>';
				codi_taula += ' <td class="operacion"><div>' + dada_node.tituloOperacion + '</div></td>';
				codi_taula += '</tr>';
			});
		} else {
			codi_taula += '<tr>';
			//TODO: internacionalizar
			codi_taula += ' <td colspan="3" class="usuario"><div>no hi ha auditories registrades</div></td>';
			codi_taula += '</tr>';
		}
		
        codi_taula +=' </tbody>';
        codi_taula +='</table>';

        /* TODO: comprobar si es necesario adaptarlo
		if($.browser.opera) {
			escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
		}
		*/
		
		// codi final
		codi_final = codi_taula;
		
		moduloAuditorias_elm.find("div.modul_continguts:first").html(codi_final);
		
	}
	
	
}
