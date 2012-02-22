// MODUL TRAMITS

$(document).ready(function() {	
	
	// elements
	modul_tramits_elm = jQuery("div.modulTramits:first");
	resultats_elm = jQuery("#resultats");
	resultats_actiu_elm = resultats_elm.find("div.actiu:first");
	escriptori_tramits_elm = jQuery("#escriptori_tramits");	
	cercador_elm = jQuery("#cercador");
	
	ModulTramit = new CModulTramit();
	EscriptoriTramit = new CEscriptoriTramit();		
	
	if (modul_tramits_elm.size() == 1) {
		ModulTramit.iniciar();
		EscriptoriTramit.iniciar();
	}
	
	// Evento para el botónn de volver al detalle    
    jQuery("#escriptori_tramits .menuPublicacion .btnVolver").click(function(){EscriptoriTramit.torna();});    
    
    // Evento para el botón de guardar
    jQuery("#escriptori_tramits .menuPublicacion a.btnGuardar").unbind("click").click(function(){EscriptoriTramit.guardar();});
    
    // Evento para el botón de eliminar
    jQuery("#escriptori_tramits .menuPublicacion a.btnEliminar").unbind("click").click(function(){EscriptoriTramit.eliminar();});
    
    // Sincronizar campos sin idioma en zona multi-idioma.   
    jQuery("#tramits_item_organ, #tramits_item_organ_es, #tramits_item_organ_ca, #tramits_item_organ_en, #tramits_item_organ_de, #tramits_item_organ_fr").change(function(){        
        jQuery("#tramits_item_organ, #tramits_item_organ_es, #tramits_item_organ_ca, #tramits_item_organ_en, #tramits_item_organ_de, #tramits_item_organ_fr").val( jQuery(this).val() );        
    });
});

function CModulTramit(){	
	
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	       
	this.iniciar = function() {
        
        jQuery("#tramit_item_data_actualitzacio").datepicker({ dateFormat: 'dd/mm/yy' });
        jQuery("#tramit_item_data_publicacio").datepicker({ dateFormat: 'dd/mm/yy' });		
		jQuery("#tramit_item_data_caducitat").datepicker({ dateFormat: 'dd/mm/yy' });
        
		tramits_seleccionats_elm = escriptori_tramits_elm.find("div.escriptori_items_seleccionats:first");
		escriptori_tramits_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});

		// idioma		
		if (escriptori_tramits_elm.find("div.idiomes").size() != 0) {
	        // Esconder todos menos el primero
			escriptori_tramits_elm.find('div.idioma').slice(1).hide();
	        
			var ul_idiomes_elm = escriptori_tramits_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
	        a_primer_elm.parent().addClass("seleccionat");
	        
	        var a_primer_elm_class = a_primer_elm.attr("class");
	        var a_primer_elm_text = a_primer_elm.text();
	        
	        a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
	        
	        var div_idiomes_elm = escriptori_tramits_elm.find("div.idiomes:first");
	        div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");	        	        
	        ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': true, 'idPare':'#escriptori_tramits'},Detall.idioma);
	    }	
		        
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "tramit",
			nodoOrigen: modul_tramits_elm.find(".listaOrdenable"),
			nodoDestino: modul_tramits_elm.find(".listaOrdenable"),
			atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
			});
		
		// one al botÃ³ de gestionar
		modul_tramits_elm.find("a.gestiona").one("click", function() { ModulTramit.gestiona(); } );		
	}	
    
	this.gestiona = function() {
				
		EscriptoriTramit.limpia();
		
		$("#item_data_actualitzacio").attr("value", "");
		$("#item_data_publicacio").attr("value", "");
		$("#item_data_caducitat").attr("value", "");
		
		$("#id_procediment_tramit").attr("value",  $("#item_id").val() );
				
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_tramits_elm.fadeIn(300, function() {
				//Ocultar el botón "eliminar" en la creación
				// y los módulos de documentos y formularios
				escriptori_tramits_elm.find(".btnEliminar").hide();			
				escriptori_tramits_elm.find("div#modul_documents").hide();
				escriptori_tramits_elm.find("div#modul_formularis").hide();				
			});			
		});
	}
    
    this.habilitarBotonGuardar = function() {        
        jQuery("#escriptori_tramits .menuPublicacion .btnGuardar").unbind("click").click(function(){EscriptoriTramit.guardar();}).parent().removeClass("off");
    }
    
    this.inicializarTramites = function( listaTramites ){
    
    	// Añadimos a los nombres de los trámites el tag <a> para que enlacen
    	// a la edición del trámite en cuestión.
    	for ( i in listaTramites )
    		listaTramites[i].nom = "<a href='#' class='tramit_id'>" + listaTramites[i].nom + "</a>";    	
    	
		if (typeof listaTramites != 'undefined' && listaTramites != null) {
            modul_tramits_elm.find(".listaOrdenable").empty();		
			this.agregaItems(listaTramites, true);
        }        		
		
        // Editar el tramite al hacer click sobre el.                
        modul_tramits_elm.find('div.tramit').each(function() {        	
            $(this).unbind("click").bind("click", function() {               
            	EscriptoriTramit.editarTramit(this);
            });
        });
		
		EscriptoriTramit.contaSeleccionats();
		
		modul_tramits_elm.find(".listaOrdenable ul").sortable({ 
			axis: 'y', 
			update: function(event,ui){
				that.calculaOrden(ui,"origen");
				that.habilitarBotonGuardar();
			}
		}).css({cursor:"move"});
		
		modul_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			ModulTramit.eliminaItem(itemLista);
			EscriptoriTramit.contaSeleccionats();
		});
	}
    
};

function CEscriptoriTramit(){	
    this.extend = DetallBase;
	this.extend(null,FormulariTramits);
	
	var that = this;    
	
	//var formulariComprovarTramits;
	
	this.iniciar = function () {
        
        /*	formulariComprovarTramits = new FormulariComprovar(FormulariTramits);		
		formulariComprovarTramits.iniciar();*/
		
	}
    	
	this.guardar = function (){			
        
        //Validam el formulari de tramit		
        if( !this.formulariValid() ){
            return false;
        }
        
        // Coger el id del procedimiento o de la ficha (depende del mantenimiento/jsp en el que estemos).
        var procId = jQuery("#procId");
        if (procId.length > 0) {
        	procId.val(jQuery("#item_id").val());
        } else {
        	jQuery("#fitxaId").val(jQuery("#item_id").val());
        }
        		
        // Si existe idTramit es que estamos editando un trámite.
		var idTramit = $("#id_tramit_actual").val();
					
        //Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
        $("#formTramits").ajaxSubmit({
        	type: "POST",
            url: pagGuardarTramit,
            dataType: 'json',            
            beforeSubmit: function() {
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
            },
            success: function(data) {                    
                
                if (data.id < 0) {
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
                } else {                   
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});                    
                    // Actualizamos la lista con el item añadido
                    ModulTramit.agregaItem({
	            		id:  data.id, 
	            		nom: "<a href='#' class='tramit_id' id='nou_tramit'>" + data.nom + "</a>",	            			            			            	
	            		orden: 0
	            	});
                  
                    // Asignamos la función de edición al nuevo enlace creado
                    nouTramit = $("#nou_tramit").parent().parent();
                    nouTramit.unbind("click").bind("click", function() { that.editarTramit(nouTramit)});
                    
                    that.contaSeleccionats();
                    that.torna();
                }
            }
        });
        
		return false;
	} 
	
    this.eliminar = function(){
    	
        var idTramit = $("#id_tramit_actual").val();
        var idProcediment = $("#id_procediment_tramit").val();
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
        
        dataVars = "id=" + idTramit + "&idProcediment=" + idProcediment;
        
        //Enviamos el formulario mediante el mÃ©todo ajaxSubmit del plugin jquery.form
        $.ajax({
        	type: "POST",        	
            url: pagEsborrarTramit,
            data: dataVars,            
            dataType: 'json',
            success: function(data) {                
                if (data.id < 0) {
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
                } else {                   
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
                    
                    // Eliminamos el item de la lista ordenable.
                    modul_tramits_elm.find(".listaOrdenable input[name=tramit_id_" + idTramit + "]").parents("li").remove();        
                    that.contaSeleccionats();
                    that.torna();
                }
            }
        });       
    }
    
	this.torna = function() {		
		// animacio
		escriptori_tramits_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_tramits_elm.find("a.gestiona").one("click", function() { ModulTramit.gestiona(); });
			});
        });		
	}
	
	this.limpia = function(){
        jQuery('#formTramits :input').each(limpiarCampo);        
        $("#id_tramit_actual").val(""); //Se neteja manualment ja que limpiarCampo no afecta els input hidden
	}
    
	this.contaSeleccionats = function() {
		
		var seleccionats_val = modul_tramits_elm.find(".seleccionat").find("li").size();
		var info_elm = modul_tramits_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			modul_tramits_elm.find("ul").remove();
			info_elm.text(txtNoHiHaTramitsSeleccionats + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtTramit.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtTramits.toLowerCase() + "</strong>.");						
			modul_tramits_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulTramit.calculaOrden(ui,"origen");
					EscriptoriTramit.contaSeleccionats();
				}
			}).css({cursor:"move"});
			
		}
		
		modul_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			ModulTramit.eliminaItem(itemLista);
			EscriptoriTramit.contaSeleccionats();
		});
	}
    
    this.pintar = function( datos ){    
        
        // Importante mantener el id del trÃ¡mite que estamos mostrando.
        jQuery("#id_tramit_actual").val(datos.idTramit);
                        
        $("#id_tramit_actual").val(datos.id_tramit_actual);
        $("#id_procediment_tramit").val(datos.id_procediment_tramit);        
		$("#tramit_item_data_actualitzacio").val(datos.tramit_item_data_actualitzacio);		
		$("#tramit_item_data_publicacio").val(datos.tramit_item_data_publicacio);
		$("#tramit_item_data_caducitat").val(datos.tramit_item_data_caducitat);        
		
		$("#item_tramite_tramit").val(datos.item_tramite_tramit);
		$("#item_url_tramit").val(datos.item_url_tramit );
		$("#item_version_tramit").val( datos.item_version_tramit != 0 ? datos.item_version_tramit : 0);        
		
		// Bloque de pestanyas de idiomas
        idiomas = datos.idiomas;
        
		for (var i in idiomas) {
			var idioma = idiomas[i];
			
			if (datos[idioma] != null) {
				$("#item_nom_tramit_" + idioma).val(printStringFromNull(datos[idioma]["nombre"]));
				$("#item_descripcio_tramit_" + idioma).val(printStringFromNull(datos[idioma]["descripcion"]));
				$("#item_requisits_tramit_" + idioma).val(printStringFromNull(datos[idioma]["requisits"]));
				$("#item_documentacio_tramit_" + idioma).val(printStringFromNull(datos[idioma]["documentacion"]));
				$("#item_termini_tramit_" + idioma).val(printStringFromNull(datos[idioma]["plazos"]));
				$("#item_lloc_tramit_" + idioma).val(printStringFromNull(datos[idioma]["lugar"]));
				$("#item_observacions_tramit_" + idioma).val(printStringFromNull(datos[idioma]["observaciones"]));
			}
		}
		// Fin bloque de pestanyas de idiomas				       
    }
    
    this.editarTramit = function( el ) {

    	var tramitId = $(el).find("input.tramit_id").val();
        
        Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
        
        jQuery.ajax({
            type: "POST",
            url: pagDetallTramit,
            data: "id=" + tramitId,
            dataType: "json",
            error: function() {
                // Missatge.cancelar();
                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                Error.llansar();
            },
            success: function(data) {
                Missatge.cancelar();
                if (data.idTramit > 0) {
                	
                    escriptori_detall_elm.fadeOut(300, function() {
                        escriptori_tramits_elm.fadeIn(300, function() { 
                        									EscriptoriTramit.pintar(data);
                        					            	// Mostrar el botón "eliminar" en la edición
                        					                escriptori_tramits_elm.find(".btnEliminar").show();
                        					        		escriptori_tramits_elm.find("div#modul_documents").show();
                        					        		escriptori_tramits_elm.find("div#modul_formularis").show();                        					                
                        								});
                    });                            
                } else if (data.id == -1){
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                } else if (data.id < -1){
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                }
            }
        });                
    }
};
