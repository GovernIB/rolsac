// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
				
	escriptori_detall_elm = $("#escriptori_detall");
			
	// INICIEM
	Error = new CError();
	Detall = new CDetall(false);
	Auditoria = new ModulAuditories();
	Estadistica = new ModulEstadistiques();
	
	Detall.iniciar();
	
});

// items array
var Items_arr = new Array();

// detall
function CDetall(soloFicha){	
		
	this.extend = DetallBase;
	this.extend(soloFicha);

	var that = this;
	this.tipusAuditoria = 'unitat';
	this.tipusEstadistica = 'unitat';

	var materias = "";
	
	// Unidad administrativa general (la primera que se carga).
	this.uaGeneral = null;
	
	// Datos de la �ltima UA cargada.
	this.actualUA = null;
	
	this.iniciar = function() {
		
		ModulSeccions = new CModulSeccio();
		
		//Sobreescribimos la funciÃ³n del botÃ³n finalizar para aÃ±adir a los parÃ¡metros enviados
		//las materias seleccionadas por el usuario
		ModulMateries.extend = CModulMateries;
		ModulMateries._finaliza = ModulMateries.finaliza;		
		ModulMateries.finaliza = function() {
						
			ModulMateries._finaliza();
			
			// AÃ±adir las materias a la informaciÃ³n a enviar una vez actualizada la selecciÃ³n		
			d = ModulMateries.listaMaterias();
			
			if ( $("#materies").val() == undefined ) {
				htmlMaterias = "<input type='hidden' id='materies' name='materies' value='" + d.replace("materies=", "") + "'/>";
				$("#formGuardar").append(htmlMaterias);
			} else {
				$("#materies").attr("value", d.replace("materies=", "") );
			}
			
		}

		// Redigirimos el método que guarda porque en este caso también hacemos un upload de archivos				
		this.guarda = this.guarda_upload;

		// Guardamos la cantidad de idiomas de idiomas con que se trabaja
		var numIdiomas = idiomas.length;

		// Idioma
		if (numIdiomas != 0) {
			// Esconder todos menos el primero
			$('div.idioma:gt(0)').hide();

			ul_idiomes_elm = escriptori_detall_elm.find("ul.idiomes:first");

			a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");

			a_primer_elm_class = a_primer_elm.attr("class");
			a_primer_elm_text = a_primer_elm.text();

			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");

			div_idiomes_elm = escriptori_detall_elm.find("div.idiomes:first");
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");

			ul_idiomes_elm.bind("click", Detall.idioma);

			// Mostramos DIV con el primer idioma en la sección de idiomas del responsable de la UA.
			$('div.idioma:eq(' + numIdiomas + ')').show();
			
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
        
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#item_codi_estandar,#item_codi_estandar_es,#item_codi_estandar_en,#item_codi_estandar_de,#item_codi_estandar_fr").change(function(){
            jQuery("#item_codi_estandar,#item_codi_estandar_es,#item_codi_estandar_en,#item_codi_estandar_de,#item_codi_estandar_fr").val( jQuery(this).val() );
        });
        
        jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").change(function(){
            jQuery("#item_clave_primaria,#item_clave_primaria_es,#item_clave_primaria_en,#item_clave_primaria_de,#item_clave_primaria_fr").val( jQuery(this).val() );
        });
        
        jQuery("#item_espai_territorial,#item_espai_territorial_es,#item_espai_territorial_en,#item_espai_territorial_de,#item_espai_territorial_fr").change(function(){
            jQuery("#item_espai_territorial,#item_espai_territorial_es,#item_espai_territorial_en,#item_espai_territorial_de,#item_espai_territorial_fr").val( jQuery(this).val() );
        });
                
		// carregar
		Detall.carregar();
		
	}
	
	// Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
		
		escriptori_fitxes_elm = $("#escriptori_fitxes");
		fitxes_seleccionats_elm = escriptori_fitxes_elm.find("div.escriptori_items_seleccionats:first");
		
		// Esta variable nos servirá para detectar si alguna de las secciones a guardar
		// no tiene fichas asignadas
		var errorSeccionSinFichas = false;
		
		// Validamos el formulario			
		if(!that.formulariValid()){
			return false;
		}
                
		// Preparamos una lista de edificios mas "amigable" para el controlador, con sus id's separados por comas
		if ( !$("#llistaEdificis").length ) {		
			htmlEdificios = '<input type="hidden" id="llistaEdificis" name="llistaEdificis" value="">';
			$("#formGuardar").append(htmlEdificios);
		} else {
			$("#llistaEdificis").attr("value", "");	
		}
				
		// Obtener todos los inputs que empiezan por edifici_id_ 
		$(".modulEdificis input[name^='edifici_id_']").each( function() {
			$("#llistaEdificis").attr("value", $("#llistaEdificis").val() + $(this).attr("value") + ",");			
		});		
		
		// Preparamos la lista de secciones-ficha
		// Formato: S1#F1|F2|...|Fs1n,S2#F1|F2|..|Fs2n,....,Sm#F1|F2|...|Fsmn
		// (S = Seccion, F = Ficha)
		if ( !$("#llistaSeccions").length ) {
			htmlSecciones = '<input type="hidden" id="llistaSeccions" name="llistaSeccions" value="">';
			$("#formGuardar").append(htmlSecciones);
		} else {
			$("#llistaSeccions").attr("value", "");
		}
		
		if ( errorSeccionSinFichas ) {
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + txtErrorSeccionsBuides + "</p>"});
			return false;
		}
		
		// Enviamos el formulario mediante el metodo ajaxSubmit del plugin jquery.form
		$("#formGuardar").ajaxSubmit({	
			url: pagGuardar,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
							
				Llistat.cacheDatosListado = null;
				LlistatSeccioFitxes.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					// recarregam per actualitzar la molla de pa
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom, funcio: Detall.carregarUA } );
				}										

			}

		});

		return false;	
		
	}
	
	// MÃ©todo sobreescrito
	this.busca = function(){
			
		edificis_cercador_elm.find("input, select").attr("disabled", "disabled");
		
		// animacio
		edificis_dades_elm.fadeOut(300, function() {
			// pintem
			codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
			edificis_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				pagPagina_edifici_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera pÃ¡gina.
				EscriptoriEdifici.carregar({});
				
			});
		});
	}
			
	// Método sobreescrito.
	this.carregar = function(itemID) {
		
	    if (itemID == undefined) {
            itemID = itemAEditar();
        }
	    if (itemID == 0 || itemID == "0"){
			itemID = $("#item_id").val();
		}
		
		escriptori_detall_elm.find("a.elimina").show();
				
		dataVars = "accio=carregar" + "&id=" + itemID;
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagDetall,
			data: dataVars,
			dataType: "json",
			beforeSend : function () {
				$("#carregantDetall").fadeIn(300);
			},
			error: function() {
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
			},
			success: function(dada) {
				
				that.actualUA = dada;
				if( !that.uaGeneral ){
					that.uaGeneral = dada;	
				}
				
				if (dada.id == -1) {
					$("#carregantDetall").fadeOut(300, function() {
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos, text: "<p>" + dada.error + "</p>"});
                    });
				} else if (dada.id < -1) {
					$("#carregantDetall").fadeOut(300, function() {
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio, text: "<p>" + dada.error + "</p>"});
                    });
				} else {
					Detall.pintar(dada);
					if (that.tipusAuditoria != null && typeof Auditoria.busca != 'undefined') { 
						//Existe auditoria para el detalle y se ha cargado el objeto de auditorías
						Auditoria.busca(that.tipusAuditoria, itemID);
					}
					if (that.tipusEstadistica != null && typeof Estadistica.pinta != 'undefined') { 
						//Existe auditoria para el detalle y se ha cargado el objeto de auditorías
						Estadistica.pinta(that.tipusEstadistica, itemID);
					}
				}
			}
		});
		
		this.actualizaEventos();		
	}
	
	this.recarregar = function() {
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			Detall.carregar();
		});		
		
	}
	
	this.pintar = function(dades) {
						
		dada_node = dades;
		
		
		if (dada_node.id > 0) {							
								
			$("#item_id").val(dada_node.id);
			
			jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").show();
			
			
			//Bloque de pestanyas de idiomas
			
			for (var i in idiomas) {
		            var idioma = idiomas[i];
		            	            
		        	$("#item_nom_" + idioma).val(printStringFromNull(dada_node[idioma].nombre));
					$("#item_abreviatura_" + idioma).val(printStringFromNull(dada_node[idioma].abreviatura));
					
					// El plugin de JQuery para TinyMCE parece que tiene un bug y a veces y en segun que navegador 
		            // no carga bien el contenido ni en el textarea ni en el editor.
		            var cvResponsable = printStringFromNull(dada_node[idioma].cvResponsable);
					document.getElementById("item_cvResponsable_" + idioma).value = cvResponsable;
					
				    var presentacion = printStringFromNull(dada_node[idioma].presentacion);
					document.getElementById("item_presentacio_" + idioma).value = presentacion;
					
				       if(tinyMCE.get("item_cvResponsable_" + idioma) != null){
				              //verificamos que coincida el contenido del textArea y tinyMCE                                
				             if(document.getElementById("item_cvResponsable_" + idioma).value != tinyMCE.get("item_cvResponsable_" + idioma).getContent()){
				                    tinyMCE.get("item_cvResponsable_" + idioma).setContent(cvResponsable);                               
				             }                                       
				       }else{
				              console.log("item_cvResponsable_" + idioma + " no tiene cargado tinyMCE - unitat.js - 313");
				       }    
				       if(tinyMCE.get("item_presentacio_" + idioma) != null){
				              if(document.getElementById("item_presentacio_" + idioma).value != tinyMCE.get("item_presentacio_" + idioma).getContent()){
				                     tinyMCE.get("item_presentacio_" + idioma).setContent(presentacion);             
				             }      
				       }else{
				             console.log("item_presentacio_" + idioma + " no tiene cargado tinyMCE - unitat.js - 321");
				       }
			}
						
			//Configuracion / gestion
			
			//$("#item_clau_hita").val(dada_node.item_clau_hita);
			$("#item_codi_estandar").val(dada_node.item_codi_estandar);
			jQuery("#item_codi_estandar").change();
			$("#item_clave_primaria").val(dada_node.id);
			$("#item_clave_primaria").change();
			$("#item_domini").val(dada_node.item_domini);
			marcarOpcionSelect("item_validacio",dada_node.item_validacio);
			//$("#item_validacio").val(dada_node.item_validacio);
			$("#item_telefon").val(dada_node.item_telefon);
			$("#item_fax").val(dada_node.item_fax);
			$("#item_email").val(dada_node.item_email);
			marcarOpcionSelect("item_espai_territorial",dada_node.item_espai_territorial);
			//$("#item_espai_territorial").val(dada_node.item_espai_territorial);			
            jQuery("#item_pare_id").val(dada_node.pareId);
			jQuery("#item_pare").val(dada_node.pareNom);
            jQuery("#item_pare").change();
                        			
			//Responsable			
			$("#item_responsable").val(dada_node.item_responsable);	
			marcarOpcionSelect("item_responsable_sexe",dada_node.item_responsable_sexe);
			//$("#item_responsable_sexe").val(dada_node.item_responsable_sexe);
			
			//FotoPetita
			
			pintarArchivo("item_responsable_foto_petita", dada_node);

			//FotoGran
			
			pintarArchivo("item_responsable_foto_gran", dada_node);

			$("#item_tractament").val(dada_node.item_tractament).attr('selected',true);			
			
			//Logotipos
			//LogoHoritzontal
			
			pintarArchivo("item_logo_horizontal", dada_node);
			
			//LogoVertical
			
			pintarArchivo("item_logo_vertical", dada_node);
					
			//LogoSalutacioHoritzontal
			
			pintarArchivo("item_logo_salutacio_horizontal", dada_node);
			
			//LogoSalutacioVertical
			
			pintarArchivo("item_logo_salutacio_vertical", dada_node);					
			
			//Fitxes de la portada web
			$("#item_nivell_1").val(dada_node.item_nivell_1);
			$("#item_nivell_2").val(dada_node.item_nivell_2);
			$("#item_nivell_3").val(dada_node.item_nivell_3);
			$("#item_nivell_4").val(dada_node.item_nivell_4);		
			
			//Materias
			ModulMateries.inicializarMaterias(dada_node.materies);

			//Seccions			
			ModulSeccions.iniciar(dada_node.seccions);
			
			//Edificis			
			edi_seleccionats_elm = escriptori_detall_elm.find("div.modulEdificis div.seleccionats");
			edi_llistat_elm = escriptori_detall_elm.find("div.modulEdificis div.llistat");
			edificis_nodes = dada_node.edificis;
			edificis_nodes_size = edificis_nodes.length;
			
			edi_llistat_elm.find("input").removeAttr("checked");
									
			if (edificis_nodes_size == 0) {
				
				edi_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaEdificis + ".");
				
			} else {
			
				var itemsLista = [];
				var i=1;
				jQuery(edificis_nodes).each(function(){
					edifici_node = this;

					// dsanchez: Creamos la lista de elementos iniciales.
					itemsLista.push( {
						id:edifici_node.id, 
						nombre: edifici_node.nom,
						// Para listas multi-idioma pasar un objeto con los strings de cada idioma, en lugar de un solo string.
						/*nombre:{
							es: edifici_node.nom, 
							en: edifici_node.nom, 
							ca: edifici_node.nom, 
							de: edifici_node.nom, 
							fr: edifici_node.nom
							},*/
						orden: i++	// Si no hay orden, lo calculamos previamente.
						} );
				});				
				ModulEdifici.agregaItems(itemsLista);
				
				txt_edificis = (edificis_nodes_size == 1) ? txtEdifici : txtEdificis;
				edi_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + edificis_nodes_size + " " + txt_edificis + "</strong>.");				
			}			

			$("#modul_materies").show();
			$("#modul_seccions").show();
			$("#modul_edificis").show();			
			
		} else {
			//No hay datos que pintar -> estamos en creaciÃ³n de UA
			//Esconder mÃ³dulos laterales
			$("#modul_materies").hide();
			$("#modul_seccions").hide();
			$("#modul_edificis").hide();
			//Deshabilitar botÃ³n eliminar
			$("#btnEliminar").parent().addClass("off");
			$("#btnEliminar").unbind("click");
			jQuery("#caja_item_clave_primaria, #caja_item_clave_primaria_es, #caja_item_clave_primaria_en, #caja_item_clave_primaria_de, #caja_item_clave_primaria_fr").hide();
		}

		$("#carregantDetall").fadeOut(300, function() {
			Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
			escriptori_detall_elm.fadeIn(300);
		});
		
	}
    
	this.elimina = function() {
		
		// missatge
		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
		
		tipusUnitat_ID = $("#item_id").val();
		
		dataVars = "accio=eliminar&id=" + tipusUnitat_ID;
				
		// ajax
		$.ajax({
			type: "POST",
			url: pagEsborrar,
			data: dataVars,
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				Error.llansar();
			},
			success: function(data) {
				if (parseInt(data.id) > 0) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte, funcio: Detall.carregarInici });
				} else {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});					
				}
				Detall.array({id: dada_node.id, accio: "elimina"});				
			}			
		});
	}
	
	this.previsualitza = function() {
					
		fitxa_idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
		fitxa_ID = escriptori_detall_elm.find("#item_id").val();
		
		previsualitza_url = urlPrevisualizarUA + "?lang=" + fitxa_idiomaSeleccionat + "&coduo=" + fitxa_ID;
		
		var ancho = 1024;
		var alto = 768;
		window.open(previsualitza_url, 'ventanaPrevisualizarUA', "width=" + ancho + ", height=" + alto);
		
	}
	
	this.carregarInici = function() { window.location.replace(pagLlistat); }
	this.carregarUA = function() { window.location.replace(pagLlistat); }
}

function posarValorsInput(idInput, valor)
{
	$(idInput).val(valor);
}