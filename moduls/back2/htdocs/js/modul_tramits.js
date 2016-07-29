// MODUL TRAMITS

$(document).ready(function() {  
    
    // elements
    modul_tramits_elm = $("div.modulTramits:first");
    resultats_elm = $("#resultats");
    resultats_actiu_elm = resultats_elm.find("div.actiu:first");
    escriptori_tramits_elm = $("#escriptori_tramits");  
    cercador_elm = $("#cercador");
    eliminaCancelar = false;
    
    // datos traductor
    CAMPOS_TRADUCTOR_TRAMITE = [
        "item_nom_tramit_",
        "item_descripcio_tramit_",
        "item_requisits_tramit_",
        "item_documentacio_tramit_",
        "item_termini_tramit_",
        "item_lloc_tramit_"
    ];
    
    DATOS_TRADUCIDOS_TRAMITE = [
        "nombre",
        "descripcion",
        "requisits",
        "documentacion",
        "plazos",
        "lugar"
    ];
    
    ModulTramit = new CModulTramit();
    EscriptoriTramit = new CEscriptoriTramit();
    
    if (modul_tramits_elm.size() == 1) {
        ModulTramit.iniciar();
        EscriptoriTramit.iniciar();
    }
    
    // Evento para el botón de guardar
    //$("#escriptori_tramits .menuPublicacion a.btnGuardar").unbind("click").click(function(){EscriptoriTramit.guardar();});
    
    // Evento para el botón de eliminar
    $("#escriptori_tramits .menuPublicacion a.btnEliminar").unbind("click").click(function(){EscriptoriTramit.eliminar();});
    
    // Sincronizar campos sin idioma en zona multi-idioma.   
    $("#tramits_item_organ, #tramits_item_organ_es, #tramits_item_organ_ca, #tramits_item_organ_en, #tramits_item_organ_de, #tramits_item_organ_fr").change(function() {        
        $("#tramits_item_organ, #tramits_item_organ_es, #tramits_item_organ_ca, #tramits_item_organ_en, #tramits_item_organ_de, #tramits_item_organ_fr").val( $(this).val() );        
    });
    
});

function CModulTramit() {    
    
    this.extend = ListaOrdenable;
    this.extend();      
    
  //  this.bolTramiteInicio = false;
    var that = this;
    
    this.iniciar = function() {
        
        //$("#tramit_item_data_actualitzacio").datepicker({ dateFormat: 'dd/mm/yy' });
    	$("#tramit_item_data_actualitzacio").datetimepicker({ timeFormat: 'hh:mm' });
        //$("#tramit_item_data_publicacio").datepicker({ dateFormat: 'dd/mm/yy' });
        $("#tramit_item_data_publicacio").datetimepicker({ timeFormat: 'hh:mm' });
        //$("#tramit_item_data_caducitat").datepicker({ dateFormat: 'dd/mm/yy' });
        $("#tramit_item_data_caducitat").datetimepicker({ timeFormat: 'hh:mm' });
        $("#tramit_item_data_vuds").datetimepicker({ timeFormat: 'hh:mm' });            
        $("#tramit_item_data_inici").datetimepicker({ timeFormat: 'hh:mm' });
        var today = new Date();
        
        //$("#publication_datepicker").datetimepicker( "option", "disabled", false ).attr('value', $.datepicker.formatDate('dd-m-yy HH:mm', new Date()));`
        $("#tramit_item_data_tancament").datetimepicker({ format: 'yyyy/MM/dd HH:mm' , setDate: new Date(),hour:'23', minute:'59' });
        //$("#tramit_item_data_tancament").datetimepicker({ timeFormat: 'hh:mm' });
        
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
            ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': true, 'idPare':'#escriptori_tramits'}, Detall.idioma);
        }   
                
        // Configuramos la lista ordenable.
        this.configurar({
            nombre: "tramit",
            nodoOrigen: modul_tramits_elm.find(".listaOrdenable"),
            nodoDestino: modul_tramits_elm.find(".listaOrdenable"),
            atributos: ["id", "nom", "orden", "moment"],  // Campos que queremos que aparezcan en las listas.
            multilang: false
        });
        
        // one al botó de gestionar
        modul_tramits_elm.find("a.gestiona").one("click", function() { ModulTramit.gestiona(); });
        
    };
    
    this.gestiona = function() {
                        
        EscriptoriTramit.limpia();
        
        $("#tramit_item_data_publicacio").val("");
        $("#tramit_item_data_caducitat").val("");
        $("#tramit_item_data_inici").val("");
        $("#tramit_item_data_tancament").val("");
                
        $("#id_procediment_tramit").attr("value",  $("#item_id").val() );
        $("#nom_procediment_tramit").text( $("input#item_nom_ca").val());
        
        $("#tramits_item_organ_id").val($("#item_organ_id").val());
        $("#tramits_item_organ_ca").val($("#item_organ").val());
        $("#tramits_item_organ_es").val($("#item_organ").val());
        $("#tramits_item_organ_en").val($("#item_organ").val());
        $("#tramits_item_organ_de").val($("#item_organ").val());
        $("#tramits_item_organ_fr").val($("#item_organ").val());
        
        // animacio
        escriptori_detall_elm.fadeOut(300, function() {
            escriptori_tramits_elm.fadeIn(300, function() {
                //Ocultar el botón "eliminar" en la creación
                // y los módulos de documentos y formularios
                escriptori_tramits_elm.find(".btnEliminar").hide();
                escriptori_tramits_elm.find("div#modul_documents_tramits").hide();
                escriptori_tramits_elm.find("div#modul_formularis_tramits").hide();
				escriptori_tramits_elm.find("div#modul_documents_requerits").hide();
                escriptori_tramits_elm.find("div#modul_taxes_tramits").hide();              
            });         
        });
        
    };
    
    this.inicializarTramites = function( listaTramites ) {
    
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
                EscriptoriTramit.editarTramit(this, null);
            });
        });
        
        EscriptoriTramit.contaSeleccionats();
        
        modul_tramits_elm.find(".listaOrdenable ul").sortable({
            axis: 'y', 
            update: function(event, ui) {
                that.calculaOrden(ui, "origen");
                that.modificado();
            }
        }).css({cursor: "move"});
        
        modul_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {               
            var itemLista = $(this).parents("li:first");
            ModulTramit.eliminaItem(itemLista);
            EscriptoriTramit.contaSeleccionats();
        });
        
    };
    
    // Devuelve un string con el formato tramitsProcediment=n1,n2,...,nm donde n son codigos de tr�mites.
    this.listaTramites = function() {
    	
        var listaTramites = "tramitsProcediment=";
        var separador = "";
        
        modul_tramits_elm.find("div.listaOrdenable input.tramit_id").each(function() {
            listaTramites += separador + $(this).val();
            separador = ",";
        });
        
        return listaTramites;
        
    };
    
    this.hayTramiteInicializacion = function () {
    	//this.bolTramiteInicio = false;  //#4 Se valida que hay modelo de solicitud seleccionado en el tr�mite de inicializaci�n
//    	if(modul_tramits_elm.find('div.listaOrdenable input.tramit_moment[value="1"]').length > 0 ){
//    		this.bolTramiteInicio = true;
//    	}
    	
//        return modul_tramits_elm.find('div.listaOrdenable input.tramit_moment[value="1"]').length > 0 
//        		&& modul_formularis_tramits_elm.find('div.listaOrdenable input.formularisTramit_id').length > 0;
        		
        return modul_tramits_elm.find('div.listaOrdenable input.tramit_moment[value="1"]').length > 0;
    };
    
    // Actualiza el nombre.
    this.actualitzaNomTramit = function(tramit) {
    	
        var tramitInput = jQuery("#tramit_nom_" + tramit.id);
        
        tramitInput.val("<a href='#' class='tramit_id'>" + tramit.nom + "</a>");
        tramitInput.next().children().first().text($("<div/>").html(tramit.nom).text());
        
        if (tramit["moment"] != undefined) {
            jQuery("#tramit_moment_" + tramit.id).val(tramit.moment);
        }
        
    };
    
    this._eliminaItem = this.eliminaItem;
    
    this.eliminaItem = function(item) {
    	
    	that._eliminaItem(item);
    	Detall.modificado(true);
    	
    };
    
};

function CEscriptoriTramit() {
	
    this.extend = DetallBase;
    
    this.extend(null, FormulariTramits, {
        btnGuardar: "btnGuardarTramit",
        btnVolver: "escriptori_tramits .menuPublicacion .btnVolver",
        form: "formTramits"
    });
    
    var that = this;    
        
    this.iniciar = function() {
        
        jQuery("#botoTraduirTramit").unbind("click").bind("click", function() {
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
        });
    
    };

    this.traduirWrapper = function () {
        that.traduir(pagTraduirTramit, CAMPOS_TRADUCTOR_TRAMITE, DATOS_TRADUCIDOS_TRAMITE);
    };
    
    this.guarda = function() {
        
    	var idTramit = $("#id_tramit_actual").val(); 
        // Validam el formulari de tramit        
        if (!this.formulariValid()) {
            return false;
        }
        //Controlamos que haya un modelo seleccionado
        if (modul_formularis_tramits_elm.find('div.listaOrdenable input.formularisTramit_id').length == 0 && idTramit != "" ){
    		Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtModelObligatori, text: ""});
    		return false;
    	}
        
        // Coger el id del procedimiento o de la ficha (depende del mantenimiento/jsp en el que estemos).
        var procId = $("#procId");
        if (procId.length > 0) {
            procId.val($("#item_id").val());
        } else {
            $("#fitxaId").val($("#item_id").val());
        }

        var moment = $('#item_moment_tramit');
        moment = moment.length > 0 ? moment.val() : undefined;
        
        var paramsUrl = "?" + ModulDocumentsTramit.listarDocumentos() + 
                        "&" + ModulFormularisTramit.listarFormularios() +
                        "&" + ModulDocumentsRequerits.listarDocumentosRequeridos() +
                        "&" + ModulTaxesTramit.listarTasas();           
       
        
        //Enviamos el formulario mediante el m�todo ajaxSubmit del plugin $.form
        $("#formTramits").ajaxSubmit({
            type: "POST",
            url: pagGuardarTramit + paramsUrl,                       
            dataType: 'json',               
            beforeSubmit: function() {
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
            },
            success: function(data) {    
            	var mensaje = "";
                if (data.id < 0) {
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
                } else {        
                    data.moment = moment;
                    var idTramit = $("#id_tramit_actual").val();   
                    var edita = false;
                    if (idTramit != "" && idTramit != undefined) {
                    	mensaje = txtTramitModificatCorrecte;                        
                        ModulTramit.actualitzaNomTramit(data);
                      //#358 Cuando es modificación se quiere volver a la pantalla del procedimiento
                  //      Missatge.cancelar();
                   //     that.vuelve();
                        edita = true;
                    } else {                    	 
                    	mensaje = txtTramitCreatCorrecte;                      
                        var idTramit = "nou_tramit_" + data.id;
                        ModulTramit.agregaItem({
                            id:  data.id, 
                            nom: "<a href='#' class='tramit_id' id='" + idTramit + "'>" + data.nom + "</a>",                                                                   
                            orden: 0,
                            moment: moment
                        });
                        // Asignamos la funci�n de edici�n al nuevo enlace creado
                        nouTramit = $("#" + idTramit).parent().parent();
                        nouTramit.unbind("click").bind("click", function() { that.editarTramit(nouTramit, null); });
                        
                        that.contaSeleccionats();
                    }
                    //#4 no se muestra mensaje "Correcto"
                    eliminaCancelar=true;
                    that.editarTramit(null, data.id); 
                    
                    //#358 Cuando es modificación se quiere volver a la pantalla del procedimiento
                    if(edita){
                    	 escriptori_tramits_elm.fadeOut(300, function() {            
                             escriptori_detall_elm.fadeIn(300, function() {
                                 // activar
                                 modul_tramits_elm.find("a.gestiona").one("click", function() { ModulTramit.gestiona(); });
                             });
                         });
                    	Detall.carregar($("#id_procediment_tramit").val());
                    }
                                 	
                    
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: mensaje});
                    
                }
            }
        });
        
        this.modificado(false);
        
      
        return false;
        
    };
    
    this.eliminar = function() {
        
        var idTramit = $("#id_tramit_actual").val();
        var idProcediment = $("#id_procediment_tramit").val();
        
        Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtTramitEliminar, funcio: function() {        
            // missatge
            Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
            
            dataVars = "id=" + idTramit + "&idProcediment=" + idProcediment;
            
            //Enviamos el formulario mediante el método ajaxSubmit del plugin $.form
            $.ajax({
                type: "POST",           
                url: pagEsborrarTramit,
                data: dataVars,            
                dataType: 'json',
                success: function(data) {
                	
                    if (data.id > 0) {
                        Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarTramitCorrecte});
                    } else if (data.id == -1) {
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                    } else if (data.id == -2) {
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                    } else if (data.id == -3) {
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorTramitIniciacio});
                    }
                                        
                    // Eliminamos el item de la lista ordenable.
                    if (data.id > 0) {
	                    modul_tramits_elm.find(".listaOrdenable input[name=tramit_id_" + idTramit + "]").parents("li").remove();        
	                    that.contaSeleccionats();
	                    that.vuelve();
                    }
                    
                }
            
            });
            
        }});
        
    };
    
    this.vuelve = function() {
        
        if ( this.cambiosSinGuardar() ) {
        	
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtAvisoCambiosSinGuardar, funcio: function() {
                
                // animacio
                escriptori_tramits_elm.fadeOut(300, function() {            
                    escriptori_detall_elm.fadeIn(300, function() {
                        // activar
                        modul_tramits_elm.find("a.gestiona").one("click", function() { ModulTramit.gestiona(); });
                    });
                });
                
                that.modificado(false);
                Missatge.cancelar();
                
            }});
            
        } else {
            
            this.modificado(false);
            
            escriptori_tramits_elm.fadeOut(300, function() {            
                escriptori_detall_elm.fadeIn(300, function() {
                    // activar
                    modul_tramits_elm.find("a.gestiona").one("click", function() { ModulTramit.gestiona(); });
                });
            });
            
        }
        
    };
    
    this.limpia = function() {
        $("#formTramits :input").each(limpiarCampo);        
        $("#id_tramit_actual").val(""); //Se neteja manualment ja que limpiarCampo no afecta els input hidden
    };
    
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
        
        modul_tramits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {               
            var itemLista = $(this).parents("li:first");
            ModulTramit.eliminaItem(itemLista);
            EscriptoriTramit.contaSeleccionats();
        });
        
    };
    
    this.pintar = function( datos ) {    
        
        // Importante mantener el id del trámite que estamos mostrando.
        $("#id_tramit_actual").val(datos.id_tramit_actual);
        $("#id_procediment_tramit").val(datos.id_procediment_tramit);
        $("#nom_procediment_tramit").text(datos.nom_procediment_tramit);        
        $("#tramit_item_data_actualitzacio").val(datos.tramit_item_data_actualitzacio);     
        $("#tramit_item_data_publicacio").val(datos.tramit_item_data_publicacio);
        $("#tramit_item_data_caducitat").val(datos.tramit_item_data_caducitat);
        $("#tramit_item_data_inici").val(datos.tramit_item_data_inici);
        $("#tramit_item_data_tancament").val(datos.tramit_item_data_tancament);
        
        $("#item_tramite_tramit").val(datos.item_tramite_tramit);
        $("#item_url_tramit").val(datos.item_url_tramit );
        $("#item_version_tramit").val( datos.item_version_tramit != 0 ? datos.item_version_tramit : 0);        
                
        $("#item_codivuds_tramit").val( datos.item_codivuds_tramit );
        $("#tramit_item_data_vuds").val( datos.tramit_item_data_vuds );
        $("#item_moment_tramit").val( datos.item_moment_tramit );
        $("#item_validacio_tramit").val( datos.item_validacio_tramit ); 
        $("#tramits_item_organ_id").val( datos.tramits_item_organ_id );
        
        // Bloque de pestanyas de idiomas
        idiomas = datos.idiomas;
        
        for (var i in idiomas) {
        	
            var idioma = idiomas[i];
            var idiomaUA = "ua_" + idioma;
            
            if (datos[idioma] != null) {
                $("#item_nom_tramit_" + idioma).val(printStringFromNull(datos[idioma]["nombre"]));
                $("#item_descripcio_tramit_" + idioma).val(printStringFromNull(datos[idioma]["observaciones"]));
                $("#item_requisits_tramit_" + idioma).val(printStringFromNull(datos[idioma]["requisits"]));
                $("#item_documentacio_tramit_" + idioma).val(printStringFromNull(datos[idioma]["documentacion"]));
                $("#item_termini_tramit_" + idioma).val(printStringFromNull(datos[idioma]["plazos"]));
                $("#item_lloc_tramit_" + idioma).val(printStringFromNull(datos[idioma]["lugar"]));
                //$("#item_observacions_tramit_" + idioma).val(printStringFromNull(datos[idioma]["observaciones"]));                              
            }
                            
            if (datos[idiomaUA] != null) 
                $("#tramits_item_organ_" + idioma).val(printStringFromNull(datos[idiomaUA]));
            
        }       
        // Fin bloque de pestañas de idiomas
        
        //#350 Pidieron que siempre se viese oculta.
        escriptori_tramits_elm.find(".modulFinestretaUnica").hide();
        // Mostrar bloque de ventanilla �nica según la información del procedimiento
        //if (datos.item_finestreta_unica == "1")
         //   escriptori_tramits_elm.find(".modulFinestretaUnica").show();
        //else
        //    escriptori_tramits_elm.find(".modulFinestretaUnica").hide();        
            
        // Mostrar módulo de tasas según la información del procedimiento
//      if (datos.item_taxes == "1")
//          escriptori_tramits_elm.find("#modul_taxes_tramits").show();
//      else
//          escriptori_tramits_elm.find("#modul_taxes_tramits").hide();
                
        // Cargar documentos, formularios y tasas
        ModulDocumentsRequerits.inicializarDocumentsRequerits(datos.docRequeritsTramite);
        ModulDocumentsTramit.inicializarDocuments(datos.documentosTramite);
        ModulFormularisTramit.inicializarFormularis(datos.formulariosTramite);              
        ModulTaxesTramit.inicializarTaxes(datos.tasasTramite);
        
    };
    
    this.editarTramit = function( el, id ) {

        var tramitId = $(el).find("input.tramit_id").val();
        if (tramitId == null) {
        	tramitId = id;
        }
        
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
            	if(!eliminaCancelar){
            		//#4 no se muestra mensaje "Correcto"
            		//casuistica especifica al guardar (TODO: buscar soluci�n alternativa)
            		Missatge.cancelar();            		
            	}else{
            		eliminaCancelar = false;
            	}
                
                if (data.idTramit > 0) {
                    
                    escriptori_detall_elm.fadeOut(300, function() {
                        escriptori_tramits_elm.fadeIn(300, function() {                         
                            escriptori_tramits_elm.find(".btnEliminar").show();
                            escriptori_tramits_elm.find("div#modul_documents_requerits").show();
                            escriptori_tramits_elm.find("div#modul_documents_tramits").show();
                            escriptori_tramits_elm.find("div#modul_formularis_tramits").show();
                            
                            if ( $("#item_taxa").attr("checked") != undefined) {
                                escriptori_tramits_elm.find("div#modul_taxes_tramits").show();          
                            } else 
                                escriptori_tramits_elm.find("div#modul_taxes_tramits").hide();                          
                        });
                    });   
                    
                    EscriptoriTramit.pintar(data);
                    
                } else if (data.id == -1){
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                } else if (data.id < -1){
                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                }
                
                that.modificado(false);
            }
        });
        
    };
    
};