// MODUL TRAMITS

$(document).ready(function() {	
	
	// elements
	modul_tramits_elm = $("div.modulTramits:first");
	resultats_elm = $("#resultats");
	resultats_actiu_elm = resultats_elm.find("div.actiu:first");
	escriptori_tramits_elm = $("#escriptori_tramits");	
	cercador_elm = $("#cercador");
	
	ModulTramit = new CModulTramit();
	EscriptoriTramit = new CEscriptoriTramit();		
	
	if (modul_tramits_elm.size() == 1) {
		ModulTramit.iniciar();
		EscriptoriTramit.iniciar();
	}
	
	// Evento para el botónn de volver al detalle    
    $("#escriptori_tramits .menuPublicacion .btnVolver").click(function(){EscriptoriTramit.torna();});    
    
    // Evento para el botón de guardar
    $("#escriptori_tramits .menuPublicacion a.btnGuardar").unbind("click").click(function(){EscriptoriTramit.guardar();});
    
    // Evento para el botón de eliminar
    $("#escriptori_tramits .menuPublicacion a.btnEliminar").unbind("click").click(function(){EscriptoriTramit.eliminar();});
    
    // Sincronizar campos sin idioma en zona multi-idioma.   
    $("#tramits_item_organ, #tramits_item_organ_es, #tramits_item_organ_ca, #tramits_item_organ_en, #tramits_item_organ_de, #tramits_item_organ_fr").change(function(){        
        $("#tramits_item_organ, #tramits_item_organ_es, #tramits_item_organ_ca, #tramits_item_organ_en, #tramits_item_organ_de, #tramits_item_organ_fr").val( $(this).val() );        
    });    
});

function CModulTramit(){	
	
	this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
	       
	this.iniciar = function() {
        
        $("#tramit_item_data_actualitzacio").datepicker({ dateFormat: 'dd/mm/yy' });
        $("#tramit_item_data_publicacio").datepicker({ dateFormat: 'dd/mm/yy' });		
		$("#tramit_item_data_caducitat").datepicker({ dateFormat: 'dd/mm/yy' });
        $("#tramit_item_data_vuds").datepicker({ dateFormat: 'dd/mm/yy' });				
		
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
		
		$("#tramit_item_data_publicacio").val("");
		$("#tramit_item_data_caducitat").val("");
				
		$("#id_procediment_tramit").attr("value",  $("#item_id").val() );
				
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_tramits_elm.fadeIn(300, function() {
				//Ocultar el botón "eliminar" en la creación
				// y los módulos de documentos y formularios
				escriptori_tramits_elm.find(".btnEliminar").hide();			
				escriptori_tramits_elm.find("div#modul_documents_tramits").hide();
				escriptori_tramits_elm.find("div#modul_formularis_tramits").hide();				
			});			
		});
	}
    
    this.habilitarBotonGuardar = function() {        
        $("#escriptori_tramits .menuPublicacion .btnGuardar").unbind("click").click(function(){EscriptoriTramit.guardar();}).parent().removeClass("off");
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
			var itemLista = $(this).parents("li:first");
			ModulTramit.eliminaItem(itemLista);
			EscriptoriTramit.contaSeleccionats();
		});
	}
    
	// Devuelve un string con el formato tramitsProcediment=n1,n2,...,nm donde n son codigos de trámites.
	this.listaTramites = function (){
		var listaTramites = "tramitsProcediment=";
		var separador = "";
		
		modul_tramits_elm.find("div.listaOrdenable input.tramit_id").each(function() {
			listaTramites += separador + $(this).val();
			separador = ",";
		});
		
		return listaTramites;
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
        var procId = $("#procId");
        if (procId.length > 0) {
        	procId.val($("#item_id").val());
        } else {
        	$("#fitxaId").val($("#item_id").val());
        }
        		
        // Si existe idTramit es que estamos editando un trámite.
		var idTramit = $("#id_tramit_actual").val();
		var paramsUrl = "?" + ModulDocumentsTramit.listarDocumentos() + "&" + ModulFormularisTramit.listarFormularios();			
		
        //Enviamos el formulario mediante el método ajaxSubmit del plugin $.form
        $("#formTramits").ajaxSubmit({
        	type: "POST",
            url: pagGuardarTramit + paramsUrl,                       
            dataType: 'json',               
            beforeSubmit: function() {
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
            },
            success: function(data) {                    
                
                if (data.id < 0) {
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
                } else {                   	
                	
                	var textoAccion = idTramit != "" ? txtTramitModificatCorrecte : txtTramitCreatCorrecte ;
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: textoAccion });
                    
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
        
        Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtTramitEliminar, funcio: function() {        
			// missatge
			Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
	        
	        dataVars = "id=" + idTramit + "&idProcediment=" + idProcediment;
	        
	        //Enviamos el formulario mediante el mÃ©todo ajaxSubmit del plugin $.form
	        $.ajax({
	        	type: "POST",        	
	            url: pagEsborrarTramit,
	            data: dataVars,            
	            dataType: 'json',
	            success: function(data) {                
	                if (data.id > 0) {
	                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarTramitCorrecte});
					} else if (data.id == -1){
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
					} else if (data.id == -2){
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
					}	                	
	                    
	                // Eliminamos el item de la lista ordenable.
	                modul_tramits_elm.find(".listaOrdenable input[name=tramit_id_" + idTramit + "]").parents("li").remove();        
	                that.contaSeleccionats();
	                that.torna();
	            }
	        }); 
        }});
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
        $('#formTramits :input').each(limpiarCampo);        
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
			var itemLista = $(this).parents("li:first");
			ModulTramit.eliminaItem(itemLista);
			EscriptoriTramit.contaSeleccionats();
		});
	}
    
    this.pintar = function( datos ){    
        
        // Importante mantener el id del trámite que estamos mostrando.
        $("#id_tramit_actual").val(datos.idTramit);
                         
        $("#id_tramit_actual").val(datos.id_tramit_actual);
        $("#id_procediment_tramit").val(datos.id_procediment_tramit);
        $("#nom_procediment_tramit").text(datos.nom_procediment_tramit);        
		$("#tramit_item_data_actualitzacio").val(datos.tramit_item_data_actualitzacio);		
		$("#tramit_item_data_publicacio").val(datos.tramit_item_data_publicacio);
		$("#tramit_item_data_caducitat").val(datos.tramit_item_data_caducitat);        
		
		$("#item_tramite_tramit").val(datos.item_tramite_tramit);
		$("#item_url_tramit").val(datos.item_url_tramit );
		$("#item_version_tramit").val( datos.item_version_tramit != 0 ? datos.item_version_tramit : 0);        
				
		$("#item_codivuds_tramit").val( datos.item_codivuds_tramit );
		$("#tramit_item_data_vuds").val( datos.tramit_item_data_vuds );
		$("#item_moment_tramit").val( datos.item_moment_tramit );
		$("#item_validacio_tramit").val( datos.item_validacio_tramit ); 
		
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
		// Fin bloque de pestañas de idiomas
		
		// Mostrar bloque de ventanilla única según la información del procedimiento
		if (datos.item_finestreta_unica == "1")
			escriptori_tramits_elm.find(".modulFinestretaUnica").show();
		else
			escriptori_tramits_elm.find(".modulFinestretaUnica").hide();		
			
		// Cargar documentos, formularios y tasas
		ModulDocumentsTramit.inicializarDocuments(datos.documentosTramite);
		ModulFormularisTramit.inicializarFormularis(datos.formulariosTramite);
		//ModulTaxesTramit.inicializarTaxes()		
    }
    
    this.editarTramit = function( el ) {

    	var tramitId = $(el).find("input.tramit_id").val();
        
        Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
        
        $.ajax({
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
                        					            	// Mostrar el botón "eliminar" en la edición
                        					                escriptori_tramits_elm.find(".btnEliminar").show(function() {
                        					                	escriptori_tramits_elm.find("#modul_documents").show(function() {
                        					                		escriptori_tramits_elm.find("#modul_formularis").show();
                        					                	});	
                        					                });
                        								});
                    });   
                    
                    EscriptoriTramit.pintar(data);
                    
                } else if (data.id == -1){
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                } else if (data.id < -1){
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                }
            }
        });                
    }
};
