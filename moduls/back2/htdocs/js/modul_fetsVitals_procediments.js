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

function CModulFetsVitals(){
    this.extend = ListaOrdenable;
	this.extend();		
	
	var that = this;

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
            atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
            multilang: false
        });
    }
    
	this.cancela = function(){
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
	}
	
	this.gestiona = function(){
		fets_seleccionats_elm.slideUp(300);
		fets_llistat_elm.slideDown(300);
	}
	
	this.finaliza = function(){
		var nombre_llistat = 0;
		var codi_llistat = "<ul>";
		var id;
		var orden;
		
		fets_llistat_elm.find("li").each(function(i) {
			li_elm = $(this);
			input_elm = li_elm.find("input");
            if (input_elm.is(":checked")) {
            	id = "fetVital_id_" + input_elm.val();
            	orden = "fetVital_orden_" + input_elm.val();
            	
            	codi_llistat += '<li><input type="hidden" class="fetsVitals_id" id="' + id + '" name="' + id + '" value="' + input_elm.val() + '" />' + li_elm.find("span").text();
				codi_llistat += '<input type="hidden" class="fetsVitals_orden" id="' + orden + '" name="' + orden + '" value="' + nombre_llistat++ + '" /></li>';
				
				input_elm.addClass(fetVitalDefaultClass);
			} else {
				input_elm.removeClass(fetVitalDefaultClass);
			}
		});
		
		codi_llistat += "</ul>";
		codi_fet_txt = (nombre_llistat == 1) ? txtFet : txtFets;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaFetsSeleccionats + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_fet_txt + "</strong>.";
                
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
		
		// Marcamos el formulario como modificado para habilitar el botón de guardar.
		Detall.modificado();
	}
	
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
	}

	this.inicializarHechosVitales = function(listaHechos) {
		// Vaciar lista
        modul_fets_elm.find(".listaOrdenable").empty();		
		if (typeof listaHechos != 'undefined' && listaHechos != null && listaHechos.length > 0) {
			that.agregaItems(listaHechos);
		}
        
        // Seleccionar checks
        var listaChecks = fets_llistat_elm.find("li input[type=checkbox]");
        var checksSize = listaChecks.length;
        var hechosSize = listaHechos.length;
        var hechoEncontrado;
        var input;
        for (i = 0; i < checksSize; i++) {
            hechoEncontrado = false;
            for (j = 0; j < hechosSize; j++) {
                if (listaHechos[j].id == listaChecks[i].value) {
                    input = listaChecks.filter('[value=' + listaChecks[i].value + ']:first');
                    input.attr('checked', 'checked');
                    input.addClass(fetVitalDefaultClass);
                    hechoEncontrado = true;
                }
            }
            if (!hechoEncontrado) {
                input = listaChecks.filter('[value=' + listaChecks[i].value + ']:first');
                input.removeAttr('checked');
                input.removeClass(fetVitalDefaultClass);
            }
        }
        
		that.contaSeleccionats();		
		$("#fetsVitals").show();
	}
	
	//devuelve un string con el formato fetsVitals=n1,n2,...,nm donde n son codigos de hechos vitales
	this.listaHechosVitales = function (){
		var llistaFets = "fetsVitals=";
		
		$("div.modulFetsVitals div.seleccionats div.listaOrdenable input.fetsVitals_id").each(function() {
			llistaFets += $(this).val() + ",";										
		});

		if (llistaFets[llistaFets.length-1] == ","){
			llistaFets = llistaFets.slice(0, -1);
		}
		
		return llistaFets;
	}
	
	//Al acceder al formulario de creacion, limpia las listas de hechos vitales, desmarca los checkboxes y se oculta el modulo
	this.nuevo = function() {
		$("#fetsVitals").hide();
		fets_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFetsSeleccionats + ".");
		$("div.modulFetsVitals div.llistat input[type=checkbox]").removeAttr('checked').removeClass(fetVitalDefaultClass);
	}		
}	