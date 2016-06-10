/**
 * Clase que implementa la l�gica del m�dulo de auditor�as
 * Todos los parametros son opcionales.
 * @param idModuloAuditorias = Id del div HTML de auditor�as
 */
function ModulAuditories(idModuloAuditorias){

	if (typeof idModuloAuditorias == "undefined" || idModuloAuditorias == "") idModuloAuditorias = "#modulAuditories";

	// Anadir los "#" por comodidad.
	var ID_MARK = "#";
	if (idModuloAuditorias.substring(0,1) != ID_MARK) idModuloAuditorias = ID_MARK + idOpciones;
	
	var that = this;
	
	// Atributo que contiene el id del elemento que se est� visualizando en la ficha.
	this.itemID;
	
	var moduloAuditorias_elm = jQuery(idModuloAuditorias);
	
	// Realizar una b�squeda
	this.busca = function(tipusAuditoria, itemID){		
			
		that.carregar(tipusAuditoria, itemID);

	}
	
	this.carregar = function(tipusAuditoria, itemID) {

		if (!itemID) {
			return;
		}
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
		codi_taula_audit = '<table>';
		codi_taula_audit +=' <thead>';
		codi_taula_audit +='  <th class="usuariocode"><div>'+txtColUsuario+'</div></th>';
		codi_taula_audit +='  <th class="nombre"><div>'+txtColNombre+'</div></th>';
		codi_taula_audit +='  <th class="fecha"><div>'+txtColFecha+'</div></th>';
		codi_taula_audit +='  <th class="operacion"><div>'+txtColOperacion+'</div></th>';
		codi_taula_audit +=' </thead>';                    
		codi_taula_audit +=' <tbody>';
		
		if (resultats_total > 0) {
			// codi cuerpo
			$(data.nodes).each(function(i) {
				dada_node = this;

				codi_taula_audit += '<tr>';				
				if (dada_node.usuario != null){
					codi_taula_audit += ' <td class="usuariocode"><div>' + dada_node.usuario.username + '</div></td>';
					codi_taula_audit += ' <td class="nombre"><div>' + dada_node.usuario.nombre + '</div></td>';
				}
				if (dada_node.usuario == null){
					codi_taula_audit += ' <td class="usuariocode"><div>' + txtUsuarioBaja + '</div></td>';
					codi_taula_audit += ' <td class="nombre"><div>' + "" + '</div></td>';
				}					
				codi_taula_audit += ' <td class="fecha"><div>' + dada_node.fecha + '</div></td>';
				codi_taula_audit += ' <td class="operacion"><div>' + dada_node.tituloOperacion + '</div></td>';
				codi_taula_audit += '</tr>';
			});
		} else {
			codi_taula_audit += '<tr>';
			codi_taula_audit += ' <td colspan="3" class="usuario"><div>' + txtNoHiHaAuditoriesRegistrades + '</div></td>';
			codi_taula_audit += '</tr>';
		}
		
        codi_taula_audit +=' </tbody>';
        codi_taula_audit +='</table>';

        /* TODO: comprobar si es necesario adaptarlo
		if($.browser.opera) {
			escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
		}
		*/
		
		// codi final
		codi_final_audit = codi_taula_audit;
		moduloAuditorias_elm.find("div.modul_continguts:first").html(codi_final_audit);
		moduloAuditorias_elm.show();
		
	}
	
	
}
