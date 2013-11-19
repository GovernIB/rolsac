// MODUL DOCUMENTS REQUERITS
$(document).ready(function() {
	// elements
	escriptori_documents_requerits_elm = $("#escriptori_documents_requerits");
    moduls_elm = escriptori_tramits_elm.find("div.modul");
    modul_documents_requerits_elm = $("div.modulDocumentsRequerits:first");
    
	ModulDocumentsRequerits = new CModulDocumentsRequerits();
    ModulDocumentsRequerits.iniciar();
    
    EscriptoriPareReqTramit = new CEscriptoriPareRequerits();
    EscriptoriPareReqTramit.iniciar();
    
    // datos traductor
	CAMPOS_TRADUCTOR_DOCUMENTO_REQUERIDO = ["doc_requerit_titol_", "doc_requerit_descripcio_"];
	DATOS_TRADUCIDOS_DOCUMENTO_REQUERIDO = ["titulo", "descripcion"];
});

// Lista ordenable para eliminar/ordenar forms en la pantalla "padre"
function CEscriptoriPareRequerits(){
	this.extend = ListaOrdenable;
	this.extend();		
    
    // Configuracion de la lista ordenable.
    this.configuracion = {
        nombre: "documentsRequerits",
        nodoOrigen: modul_documents_requerits_elm.find(".listaOrdenable"),
        nodoDestino: "", 
        atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
        multilang: true
    }
	
	var that = this;
	
	this.iniciar = function() {		
		
		// botons
		modul_documents_requerits_elm.find("a.gestiona").bind("click", function() { ModulDocumentsRequerits.nou(false); } );			
		formularis_tramits_seleccionats_elm = escriptori_documents_requerits_elm.find("div.escriptori_items_seleccionats:first");
		
		escriptori_documents_requerits_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);		
		});
				
		// Configuramos la lista ordenable.
		this.configurar(that.configuracion);
	}	

	this.gestiona = function() {
		lis_size = modul_documents_requerits_elm.find('div.cajaIdioma:first li').length;
		if (lis_size > 0) {
			EscriptoriEdifici.contaSeleccionats();
		} else {
			edificis_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaDocumentsSeleccionats + ".");			
			edificis_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		escriptori_tramits_elm.fadeOut(300, function() {			
			escriptori_edificis_elm.fadeIn(300);			
		});
	}
	
	this.eliminaItem = function(item) {
		var id = $(item).find("input." + that.configuracion.nombre + "_id:first").val();						
		$(that.configuracion.nodoOrigen).find("input[name=" + that.configuracion.nombre + "_id_" + id + "]").parents("li").remove();
		that.habilitarBotonGuardar();
	}
    
    this.habilitarBotonGuardar = function() {
    	escriptori_documents_requerits_elm.find("#btnGuardar").unbind("click").bind("click",function(){this.guarda_upload();}).parent().removeClass("off");
    }
    
    /**
	 * Agrega o actualiza un item en la lista de origen.
	 * @return boolean Devuelve false si el item se agraga a la lista, true si lo actualiza.
	 */
	this.agregaActualizaItem = function(item){
		var listas = that.configuracion.nodoOrigen;
		var tamLista = listas.first().find("ul:first").find("li").size();
		var actualizar = false;
		
		if (tamLista == 0) {
			listas.html("<ul></ul>");
		} else {
			listas.first().find("input." + that.configuracion.nombre + "_id").each(function() {
				if ($(this).val() == item.id) {
					actualizar = true;
				}			
			});			
		}
		
		if (actualizar) {
			for (var i in idiomas) {
				var lang = idiomas[i];
				listas.find("input." + that.configuracion.nombre + "_nom_" + lang + "[name$=_" + item.id + "]").each(function() {
                    var $formInput = $(this);
                    var $formSpan = $formInput.next();
                    $formInput.val(item["nom"][lang]);
                    $formSpan.text(item["nom"][lang]);
                });
			}
		} else {
            if (typeof item["orden"] != "number") {
                item["orden"] = that.obtenerUltimoOrden(listas, tamLista) + 1;
            }
            
			listas.each(function(){
                var lista = $(this);
                var idioma = that.getIdiomaActivo(lista);
				var htmlCode = that.getHtmlItem(item, true, idioma);
				lista.find("ul").append(htmlCode);					
			});
            
            ModulDocumentsRequerits.inicializarDocumentsRequerits();
		}
		
		that.habilitarBotonGuardar();
		
		return actualizar;
	}
	

	this.obtenerUltimoOrden = function(listas, tamLista) {
		var ultimoOrden = -1;
		if (tamLista > 0) {
			ultimoOrden = parseInt(listas.first().find("li:last input." + that.configuracion.nombre + "_orden").val());  
		}
		return ultimoOrden;
	}
};

// Creacion/edicion de forms
function CModulDocumentsRequerits(){
	this.extend = DetallBase;
	if (typeof FormulariDadesDocRequerit != 'undefined') {
		this.extend(true, FormulariDadesDocRequerit);
	} else {
		this.extend(true, null);
	}

	var that = this;
	
	this.iniciar = function() {			
        // botons        
        $("#btnVolver_documents_requerits").bind("click", that.torna);

        // El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario.
    	$("#formGuardarDocReq input, #formGuardarDocReq textarea, #formGuardarDocReq select").bind("change", function(){that.modificado();});
    	
		// idioma
		if (escriptori_documents_requerits_elm.find("div.idiomes").size() != 0) {
			// Esconder todos menos el primero
			escriptori_documents_requerits_elm.find('div.idioma').slice(1).hide();
			
			var ul_idiomes_elm = escriptori_documents_requerits_elm.find("ul.idiomes:first");

			var a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			var a_primer_elm_class = a_primer_elm.attr("class");
			var a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			var div_idiomes_elm = escriptori_documents_requerits_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", {'actualizarIdiomasModulosLaterales': false}, that.idioma);
			
			$("#item_tipdoc_ca, #item_tipdoc_es, #item_tipdoc_en, #item_tipdoc_de, #item_tipdoc_fr").bind("change",function(){that.actualitzarTipusDocument();});
			$("#item_cataleg_ca, #item_cataleg_es, #item_cataleg_en, #item_cataleg_de, #item_cataleg_fr").bind("change",function(){that.actualitzarExcepcioDocumentacio();});
			$("#item_check_excepcio_ca,#item_check_excepcio_es,#item_check_excepcio_en,#item_check_excepcio_de,#item_check_excepcio_fr").bind("change",function(){that.activaItemExcepcioDocumentacio();});
			
	        // Sincronizar campos sin idioma en zona multi-idioma.   
	        jQuery("#item_tipdoc_ca,#item_tipdoc_es,#item_tipdoc_en,#item_tipdoc_de,#item_tipdoc_fr").change(function(){
	            jQuery("#item_tipdoc_ca,#item_tipdoc_es,#item_tipdoc_en,#item_tipdoc_de,#item_tipdoc_fr").val( jQuery(this).val() );
	        });
	        jQuery("#item_cataleg_ca,#item_cataleg_es,#item_cataleg_en,#item_cataleg_de,#item_cataleg_fr").change(function(){
	            jQuery("#item_cataleg_ca,#item_cataleg_es,#item_cataleg_en,#item_cataleg_de,#item_cataleg_fr").val( jQuery(this).val() );
	        });
	        jQuery("#item_excepcio_ca,#item_excepcio_es,#item_excepcio_en,#item_excepcio_de,#item_excepcio_fr").change(function(){
	            jQuery("#item_excepcio_ca,#item_excepcio_es,#item_excepcio_en,#item_excepcio_de,#item_excepcio_fr").val( jQuery(this).val() );
	        });
	        jQuery("#item_excepcio_ca,#item_excepcio_es,#item_excepcio_en,#item_excepcio_de,#item_excepcio_fr").change(function(){
	            jQuery("#item_excepcio_ca,#item_excepcio_es,#item_excepcio_en,#item_excepcio_de,#item_excepcio_fr").val( jQuery(this).val() );
	        });
	        jQuery("#item_check_excepcio_ca,#item_check_excepcio_es,#item_check_excepcio_en,#item_check_excepcio_de,#item_check_excepcio_fr").change(function(){
	            jQuery("#item_check_excepcio_ca,#item_check_excepcio_es,#item_check_excepcio_en,#item_check_excepcio_de,#item_check_excepcio_fr").attr("checked", jQuery(this).is(":checked"));
	        });
	        
	        // boton de traducir
	        jQuery("#botoTraduirRequerit").unbind("click").bind("click", function() {
	            Missatge.llansar({tipus: "confirmacio", modo: "atencio", titol: txtTraductorAvisTitol, text: txtTraductorAvis, funcio: that.traduirWrapper});
	        });
		}
		
		// Redifinimos el m�todo que guarda porque en este caso tambi�n hacemos un upload de archivos.
		this.guarda = this.guarda_upload;
	}
	
	this.traduirWrapper = function () {
		that.traduir(pagTraduirDocumentTramit, CAMPOS_TRADUCTOR_DOCUMENTO_REQUERIDO, DATOS_TRADUCIDOS_DOCUMENTO_REQUERIDO);
	}
	
	this.torna = function () {
		escriptori_documents_requerits_elm.fadeOut(300, function() {
			escriptori_tramits_elm.fadeIn(300);
	    });
	}
	this.validaTipusDocument = function(){
		var tipDoc = $("#item_tipdoc_ca");
		var catDoc = $("#item_cataleg_ca");
		var titolDoc = $("#doc_requerit_titol_ca");
		
		if (tipDoc.val()=="1" && (catDoc.val() == "")){
			Missatge.llansar({tipus: "alerta", modo: "error", titol: txtCampObligatori, text: "<p>" + txtCatalegDocumentsObligatorio + "</p>"});
			return false;
		}
		else if (tipDoc.val()=="2" && (titolDoc.val()=="")){
			Missatge.llansar({tipus: "alerta", modo: "error", titol: txtCampObligatori, text: "<p>" + txtTituloDocRequeritObligatorio + "</p>"});
			return false;
		}
		else {
			return true;
		}
	}
	// Guardar haciendo upload de archivos.
	this.guarda_upload = function() {
        // Validamos el formulario
        if (!that.formulariValid() || !this.validaTipusDocument()) {
            return false;
        }
        
        // Coger el id del tr�mite
        var docReqTramitId = $("#docReqTramitId");         
        docReqTramitId.val($("#id_tramit_actual").val());

		//Enviamos el formulario mediante el m�todo ajaxSubmit del plugin $.form
		$("#formGuardarDocReq").ajaxSubmit({			
			url: pagGuardarDocRequerit,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
				Llistat.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {                   
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
                    
					
					var nom = new Object();		
					//COMPROVAR SI �S DOCUMENT ESPECIFIC O REQUERIT
					idioma_seleccionat=$("#formGuardarDocReq li.idioma.seleccionat span").attr("class");
					tipusDoc = $("#item_tipdoc_" + idioma_seleccionat).val();
					if (tipusDoc=="1"){
	                    escriptori_documents_requerits_elm.find("select[id^='item_cataleg_']").each(function (index) {
							var $titolDoc = $(this);
							var idi = $titolDoc.attr('id').split('_')[2];
							nom[idi] = $titolDoc.children("option:selected").text();
						});
					} else {
						escriptori_documents_requerits_elm.find("input[id^='doc_requerit_titol_']").each(function (index) {
							var $titolDoc = $(this);
							var idi = $titolDoc.attr('id').split('_')[3];
							nom[idi] = $titolDoc.val();
						});
					}
					
					var docItem = new Object();
					docItem['id'] = data.id;
					docItem['nom'] = nom;
					EscriptoriPareReqTramit.agregaActualizaItem(docItem);

					that.torna();
				}
			}

		});
        
		return false;
	}
	
	this.modificado = function(){
		// Habilitamos el bot�n de guardar.
		$("#btnGuardar_documents_requerits").unbind("click").bind("click",function(){that.guarda();}).parent().removeClass("off");
	}
	
	this.dataPublicacio = function(e) {		
		if ($(this).val() == "") {
			$(this).val(txtImmediat);
		}
	}
			
	this.nou = function(edicion) {
		
		$("#docReqTramitId").attr("value", $("#id_tramit_actual").val());		
		
		// El bot�n de guardar est� inicialmente deshabilitado hasta que se realice un cambio en el formulario.
		$("#btnGuardar_documents_requerits").parent().addClass("off");
		if (!edicion) {
            $("#docReqId").val("");
            idioma_seleccionat=$("#formGuardarDocReq li.idioma.seleccionat span").attr("class");
    		$("#item_tipdoc_" + idioma_seleccionat).val("1");
    		$("#item_cataleg_" + idioma_seleccionat).val("");
    		this.actualitzarExcepcioDocumentacio();
            for (var i in idiomas) {
                var idioma = idiomas[i];                
                $("#doc_requerit_descripcio_" + idioma + ", #doc_requerit_titol_" + idioma).each(limpiarCampo);
            }
		}
		
		escriptori_tramits_elm.fadeOut(300, function() {
			escriptori_documents_requerits_elm.fadeIn(300);
		});
		this.inicialitzarFormulari();
		this.actualizaEventos();
	}			
	
	this.pintar = function(dades) {		
		dada_node = dades;
		
		$("#docReqId").val(dada_node.item_id);

		// Bloque de pestanyas de idiomas.
		for (var i in idiomas) {
			var idioma = idiomas[i];
            $("#item_tipdoc_" + idioma).val(dada_node["item_tipdoc"]);
			$("#doc_requerit_titol_" + idioma).val(printStringFromNull(dada_node["idioma_titol_" + idioma]));
			$("#doc_requerit_descripcio_" + idioma).val(printStringFromNull(dada_node["idioma_descripcio_" + idioma]));
			$("#item_cataleg_" + idioma).val(dada_node["cataleg_id"]);

		}		
		that.pintarExcepcioDocumentacio(dada_node);
		// Fin bloque de pestanyas de idiomas
		
        // Mostrar la pantalla de edicion de documento
		that.nou(true);
	}
	
	this.pintarExcepcioDocumentacio = function(dada_node){
		for (var i in idiomas) {
			var idioma = idiomas[i];
			$("#item_excepcio_" + idioma).val(dada_node["excepcio_id"]);
			if (dada_node["excepcio_id"]!=""){
				$("#item_check_excepcio_" + idioma).attr('checked',true);
			} else {
				$("#item_check_excepcio_" + idioma).attr('checked',false);
			}
		}
	}
	
	this.contaSeleccionats = function() {		
		var seleccionats_val = modul_documents_requerits_elm.find(".seleccionat").find("li").size();
		var info_elms = modul_documents_requerits_elm.find("p.info");
		
		if (seleccionats_val == 0) {
			info_elms.text(txtNoHiHaDocumentsSeleccionats+ ".");
		} else if (seleccionats_val == 1) {
			info_elms.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtDocument.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elms.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtDocuments.toLowerCase() + "</strong>.");						
		}
	}
	
	this.inicializarDocumentsRequerits = function(listaDocuments) {
		
				
		if (typeof listaDocuments != 'undefined' && listaDocuments != null) {
            modul_documents_requerits_elm.find(".listaOrdenable").empty();		
			EscriptoriPareReqTramit.agregaItems(listaDocuments, true);
        }
        
        // Editar el documento al hacer click sobre el.
        modul_documents_requerits_elm.find('div.documentsRequerits').each(function() {
            $(this).unbind("click").bind("click", function() {
                var docReqId = $(this).find("input.documentsRequerits_id").val();
                var docReqTramitId  = $("#id_tramit_actual").val();
                
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
                $.ajax({
                    type: "POST",
                    url: pagCarregarDocRequerit,
                    data: "id=" + docReqId + "&docReqTramitId=" + docReqTramitId,
                    dataType: "json",
                    error: function() {
                        // Missatge.cancelar();
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                        Error.llansar();
                    },
                    success: function(data) {
                        Missatge.cancelar();
                        if (data.id > 0) {
                            that.pintar(data.documentRequerit);
                        } else if (data.id == -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                        } else if (data.id < -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                        }
                    }
                });
            });
        });
		
		that.contaSeleccionats();
		
		modul_documents_requerits_elm.find(".listaOrdenable ul").sortable({ 
			axis: 'y', 
			update: function(event,ui){
				EscriptoriPareReqTramit.calculaOrden(ui,"origen");
				EscriptoriPareReqTramit.habilitarBotonGuardar();
			}
		}).css({cursor:"move"});
		
		modul_documents_requerits_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = $(this).parents("li:first");
			EscriptoriPareReqTramit.eliminaItem(itemLista);
			that.contaSeleccionats();
		});
	}
	
	// Devuelve un string con el formato documentsRequerits=n1,n2,...,nm donde n son codigos de formularios de un tr�mite.
	this.listarDocumentosRequeridos = function (){
		var listaDocumentos = "documentsRequerits=";
		var separador = "";
		
		modul_documents_requerits_elm.find(".ca div.listaOrdenable input.documentsRequerits_id").each(function() {
			listaDocumentos += separador + $(this).val();
			separador = ",";
		});
		
		return listaDocumentos;
	}	
	this.inicialitzarFormulari = function(){
		idioma_seleccionat=$("#formGuardarDocReq li.idioma.seleccionat span").attr("class");
		tipusDoc = $("#item_tipdoc_" + idioma_seleccionat).val();
		for (var i in idiomas) {
			var idioma = idiomas[i];
			if (tipusDoc == "1"){
				//DOCUMENT DEL CAT�LEG COM�
				jQuery("#item_excepcio_" + idioma).attr('disabled', true);
				jQuery("#item_check_excepcio_" + idioma).prop('disabled', true);
				jQuery("#seccio_excepcions_" + idioma + " label").addClass("disabled");
				jQuery("#seccio_doc_especific_" + idioma).hide();
				jQuery("#seccio_doc_cataleg_" + idioma).show();

			}else{
				//DOCUMENT ESPEC�FIC
				var item_excepcio =jQuery("#item_check_excepcio_" + idioma);
				item_excepcio.prop('disabled', false);
				if (item_excepcio.is(':checked')){
					jQuery("label[for='item_excepcio_" + idioma_seleccionat + "']").removeClass("disabled");
				}else {
					jQuery("label[for='item_excepcio_" + idioma_seleccionat + "']").addClass("disabled");
				}
				jQuery("#seccio_excepcions_" + idioma + " label").removeClass("disabled");
				jQuery("label[for='item_check_excepcio_" + idioma_seleccionat + "']").removeClass("disabled");
				jQuery("#seccio_doc_especific_" + idioma).show();
				jQuery("#seccio_doc_cataleg_" + idioma).hide();
			}
		}

	}
	this.actualitzarTipusDocument = function(){
		idioma_seleccionat=$("#formGuardarDocReq li.idioma.seleccionat span").attr("class");
		tipusDoc = $("#item_tipdoc_" + idioma_seleccionat).val();
		for (var i in idiomas) {
			var idioma = idiomas[i];
			if (tipusDoc == "1"){
				//DOCUMENT DEL CAT�LEG COM�
				jQuery("#doc_requerit_titol_" + idioma).val("");
			}else{
				//DOCUMENT ESPEC�FIC
				jQuery("#item_cataleg_" + idioma).val("");
				jQuery("#item_excepcio_" + idioma).val("");
				jQuery("#item_check_excepcio_" + idioma).attr('checked', false);
			}
		}
		that.inicialitzarFormulari();
	}
	this.activaItemExcepcioDocumentacio = function(){
		var idioma_seleccionat=$("#formGuardarDocReq li.idioma.seleccionat span").attr("class");
		var item_check = jQuery("#item_check_excepcio_" + idioma_seleccionat);
		if (item_check.is(':checked')){
			jQuery("#item_excepcio_" + idioma_seleccionat).attr('disabled', false);
			jQuery("label[for='item_excepcio_" + idioma_seleccionat + "']").removeClass("disabled");
			jQuery("label[for='item_excepcio_" + idioma_seleccionat + "'] .obligatori").text("*");
		} else {
			jQuery("#item_excepcio_" + idioma_seleccionat).attr('disabled', true);
			jQuery("#item_excepcio_" + idioma_seleccionat).val("");
			jQuery("label[for='item_excepcio_" + idioma_seleccionat + "']").addClass("disabled");
			jQuery("label[for='item_excepcio_" + idioma_seleccionat + "'] .obligatori").text("");
		}
	}
	
	this.actualitzarExcepcioDocumentacio = function () {
		var idioma_seleccionat=$("#formGuardarDocReq li.idioma.seleccionat span").attr("class");
	    var catDocId = $("#item_cataleg_" + idioma_seleccionat).val();
	    var excepcio=[];
	    if (catDocId != "") {
	    	// Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
	        $.ajax({
	            type: "POST",
	            url: pagExcepcioDocRequerit,
	            data: "id=" + catDocId,
	            dataType: "json",
	            error: function() {
	                // Missatge.cancelar();
	                Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
	                Error.llansar();
	            },
	            success: function(data) {
	                Missatge.cancelar();
	                if (data.id > 0) {
	                    that.pintarExcepcioDocumentacio(data);
	                } else if (data.id == -1){
	                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
	                } else if (data.id < -1){
	                    Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
	                }
	            }
	        });
	   }else {
		   excepcio["excepcio_id"] = "";
		   that.pintarExcepcioDocumentacio(excepcio);
	   }
    }
	
};