// MODULO DE HECHOS VITALES RELACIONADOS PARA PROCEDIMIENTOS

jQuery(document).ready(function() {
	
    fetVitalDefaultClass = "fetVitalDefault";
	modul_fets_elm = jQuery("div.modulFetsVitals:first");
	fets_seleccionats_elm = modul_fets_elm.find("div.seleccionats:first");
    fets_llistat_elm = modul_fets_elm.find("div.llistat:first");
            
	ModulFetsVitals = new CModulFetsVitals();
    if (modul_fets_elm.size() == 1) {
		ModulFetsVitals.iniciar();
	}
    
});

function CModulFetsVitals() {
	
    this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;
                                    
    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = modul_fets_elm.find('input[name="modulo_hechos_modificado"]');
    
    modul_fets_elm.find("input[type=checkbox]").change(function(){               
        $moduloModificado.val(1);
    });

    this.iniciar = function() {
    	
        // Bindings botones.
        modul_fets_elm.find("a.gestiona").bind("click", function(){	that.gestiona(); });
        modul_fets_elm.find("a.cancela").bind("click", function(){ that.cancela(); });
        modul_fets_elm.find("a.finalitza").bind("click", function(){ that.finaliza(); });
            
        // Configuramos la lista ordenable.
        this.configurar({
            nombre: "fetsVitals",
            nodoOrigen: modul_fets_elm.find(".listaOrdenable"),
            nodoDestino: [],
            atributos: [			// Campos que queremos que aparezcan en las listas.
	            "id", 
	            "nom", 
	            "orden", 
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem"		// Campo necesario para guardado AJAX genérico de módulos laterales.
            ],
            multilang: false
        });
        
    };
    
	this.cancela = function() {
    
        // Restauramos el estado del campo de control de cambios.
        $moduloModificado.val( $moduloModificado.data('oldvalue') );
    
		fets_llistat_elm.find("input[type=checkbox]").each(function() {
			$this = jQuery(this);
			if ($this.hasClass(fetVitalDefaultClass)) {
				$this.attr("checked", "checked");
			} else {
				$this.removeAttr("checked");
			}
		});
		
		fets_seleccionats_elm.slideDown(300);
		fets_llistat_elm.slideUp(300);
		
	};
	
	this.gestiona = function() {
    
        // Guardamos el estado del campo de control de cambios.
        $moduloModificado.data( 'oldvalue', $moduloModificado.val() );
        
		fets_seleccionats_elm.slideUp(300);
		fets_llistat_elm.slideDown(300);
		
	};
    
    this.modificado = function() {
        $moduloModificado.val(1);
    };
	
	this.finaliza = function() {
		
		var nombre_llistat = 0;
		var codi_llistat = "<ul>";
		var id;
		var orden;
		
		fets_llistat_elm.find("li").each(function(i) {
			
			li_elm = $(this);
			input_elm = li_elm.find("input");
			
            if (input_elm.is(":checked")) {
            	
				id = input_elm.val();
            	orden = "fetVital_orden_" + input_elm.val();
            	idMainItem = $('#item_id').val();
            	idRelatedItem = id;

            	codi_llistat += '<li element-id="' + id + '" main-item-id="' + idMainItem + '" related-item-id="' + idRelatedItem + '"><input type="hidden" class="fetsVitals_id" id="' + id + '" name="' + id + '" value="' + input_elm.val() + '" />' + li_elm.find("span").text();
				codi_llistat += '<input type="hidden" class="fetsVitals_orden" id="' + orden + '" name="' + orden + '" value="' + (nombre_llistat++) + '" /></li>';
				
				input_elm.addClass(fetVitalDefaultClass);
				
			} else {
				
				input_elm.removeClass(fetVitalDefaultClass);
				
			}
            
		});
		
		codi_llistat += "</ul>";
		codi_fet_txt = (nombre_llistat == 1) ? txtFet : txtFets;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaFetsSeleccionats + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_fet_txt + "</strong>.";
                
		fets_seleccionats_elm.find("p.info").html(codi_info);
		fets_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);
		
		if (nombre_llistat > 1) {			
			fets_seleccionats_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulFetsVitals.calculaOrden(ui,"origen");
					ModulFetsVitals.contaSeleccionats();
				}
			}).css({cursor:"move"});
		}
		
		fets_seleccionats_elm.slideDown(300);
		fets_llistat_elm.slideUp(300);
        
        // Marcamos el módulo como modificado.
        this.modificado();
        
        // amartin: emitimos señal de finalización, para que los guardados AJAX la detecten y procesen los nuevos elemento seleccionados.
        modul_fets_elm.trigger("finalizaHechosVitales");
        
	};
	
	this.contaSeleccionats = function() {
		
		var seleccionats_val = modul_fets_elm.find("div.llistat input:checked").size();
		var info_elm = fets_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			info_elm.text(txtNoHiHaFetsSeleccionats+ ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtFet.toLowerCase() + "</strong>.");
			
		} else if (seleccionats_val > 1) {
			
			info_elm.html(txtSeleccionats + " <strong>" + seleccionats_val + " " + txtFets.toLowerCase() + "</strong>.");
			
			fets_seleccionats_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				update: function(event,ui){
					ModulFetsVitals.calculaOrden(ui,"origen");
					ModulFetsVitals.contaSeleccionats();
				}
			}).css({cursor:"move"});
			
		}
		
	};
	
	this.inicializarHechosVitales = function(listaHechos) {

		$moduloModificado.val(0);
        
		// Vaciar lista.
        modul_fets_elm.find(".listaOrdenable").empty();		
		if (typeof listaHechos != 'undefined' && listaHechos != null && listaHechos.length > 0) {
			that.agregaItems(listaHechos);
		}
        
		/* Seleccionar checks */
		for ( var int = 0 ; int < listaHechos.length ; int++ ) {
			
			var input = "#HV_" + listaHechos[int].id;
			var htmlHechoVital = "";
			var existeHechoVital = $(input).length;
			var cantidadHechosVitales = $('#fetsVitals .llistat > ul > li').length;

			// Se comprueba si es un hecho vital que pertenece a algún público objetivo asignado.
			if ( existeHechoVital == 0 ) {
				
				switch ( cantidadHechosVitales % 2 ) {
        		
    				case 0:
    				
    					htmlHechoVital += "<li class='impar'>";	
    					break;

    				default:
    				
    					htmlHechoVital += "<li class='par'>" ;
    					break;
    				
            	}
            	
            	var idHechoVital = listaHechos[int].id;
            	htmlHechoVital += "<label><span>" + listaHechos[int].nom + "</span><input id='HV_" + idHechoVital + "' type='checkbox' value='" + idHechoVital + "' /></label></li>";
            	$("#fetsVitals .llistat > ul").append(htmlHechoVital);
            	
			} //End if
			
			$(input).addClass(fetVitalDefaultClass);
			$(input).attr('checked','checked');
			
		} //End for
		
		that.contaSeleccionats();		
		$("#fetsVitals").show();
		
	};
	
	// Devuelve un string con el formato fetsVitals=n1,n2,...,nm donde n son codigos de hechos vitales.
	this.listaHechosVitales = function () {
	
		var llistaFets = "fetsVitals=";
		
		$("div.modulFetsVitals div.seleccionats div.listaOrdenable input.fetsVitals_id").each(function() {
			llistaFets += $(this).val() + ",";										
		});

		if (llistaFets[llistaFets.length-1] == ","){
			llistaFets = llistaFets.slice(0, -1);
		}
		
		return llistaFets;
		
	};
	
	// Al acceder al formulario de creación, limpia las listas de hechos vitales, desmarca los checkboxes y se oculta el módulo.
	this.nuevo = function() {
	
		$("#fetsVitals").hide();
		fets_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFetsSeleccionats + ".");
		$("div.modulFetsVitals div.llistat input[type=checkbox]").removeAttr('checked').removeClass(fetVitalDefaultClass);
	
	};
	
	this.obtenerSeleccionados = function() {
		
		var hechosVitalesSeleccionados = new Array();
		var htmlhechosVitalesSeleccionados = $('.modulFetsVitals .llistat input:checked');
		var cantidadHechosVitalesChecked = htmlhechosVitalesSeleccionados.length;
		
		if ( cantidadHechosVitalesChecked > 0 ) {
			
			for ( var int = 0; int < cantidadHechosVitalesChecked; int++) {
				
				var htmlElement = htmlhechosVitalesSeleccionados[int];
				hechosVitalesSeleccionados.push( $(htmlElement).val() );
				
			}
			
		}

		return hechosVitalesSeleccionados;
		
	}; //End obtener seleccionados
	
	// Pinta los hechos vitales que están relacionados con los Públicos objetivos asignados a un procedimiento.
	this.pintar = function(listaHechosVitales) {
		
		var htmlHechosVitales = "";
		
		for ( var int = 0 ; int < listaHechosVitales.length ; int++ ) {
			
			var hechoVital = listaHechosVitales[int];
			
			switch ( int % 2 ) {
			
				case 0:
					htmlHechosVitales += "<li class='impar'>";	
					break;

				default:
					htmlHechosVitales += "<li class='par'>";
				
			}
			
			var idHechoVital = hechoVital.id;
			htmlHechosVitales += "<label><span>" + hechoVital.nom + "</span><input id='HV_" + idHechoVital + "' type='checkbox' value='" + idHechoVital + "' /></label></li>";
			
		}
		
		$("#fetsVitals .llistat > ul").html(htmlHechosVitales);
		
	}; //End pintar
	
}	