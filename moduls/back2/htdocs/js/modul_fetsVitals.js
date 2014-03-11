// MODULO DE HECHOS VITALES RELACIONADOS PARA FICHAS

jQuery(document).ready(function() {
	ModulFetsVitals = new CModulFetsVitals();
});

function CModulFetsVitals() {
	
	var that = this;
	var fetVitalDefaultClass = "fetVitalDefault";
	var modul_fets_elm = jQuery("div.modulFetsVitals");
	var fets_seleccionats_elm;
	var fets_llistat_elm;
    
    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = modul_fets_elm.find('input[name="modulo_hechos_modificado"]');
    
    modul_fets_elm.find("input[type=checkbox]").change(function(){               
        $moduloModificado.val(1);
    });
					 	
	if ( modul_fets_elm.size() == 1 ) {		

		modul_fets_elm.find("a.gestiona").bind("click", function(){	
			that.gestiona();
		});
		
		modul_fets_elm.find("a.cancela").bind("click", function(){
			that.cancela();
		});
		
		modul_fets_elm.find("a.finalitza").bind("click", function(){
			that.finaliza();
		});
		
		fets_seleccionats_elm = modul_fets_elm.find("div.seleccionats:first");
		fets_llistat_elm = modul_fets_elm.find("div.llistat:first");				
		
	}
	
	this.cancela = function() {
    
        // Restauramos el estado del campo de control de cambios.
        $moduloModificado.val( $moduloModificado.data('oldvalue') );
    
//		fets_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
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
		
		nombre_llistat = 0;
				
		codi_llistat = "<ul>";
		
		fets_llistat_elm.find("li").each(function(i) {
		
			li_elm = $(this);
			input_elm = li_elm.find("input");
			
            if (input_elm.is(":checked")) {
				codi_llistat += "<li element-id='" + input_elm.val() + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + input_elm.val() + "'><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
				input_elm.addClass(fetVitalDefaultClass);
			} else {
				input_elm.removeClass(fetVitalDefaultClass);
			}
            
		});
		
		codi_llistat += "</ul>";
		
		codi_fet_txt = (nombre_llistat == 1) ? txtFet : txtFets;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaFets + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_fet_txt + "</strong>.";
                
		fets_seleccionats_elm.find("p.info").html(codi_info);
		fets_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);		
		
		fets_seleccionats_elm.slideDown(300);
		fets_llistat_elm.slideUp(300);
        
        // Marcamos el módulo como modificado.
        this.modificado();
        
	};
	
	// Actualiza la lista de hechos vitales seleccionados y marca los checkboxes cuando se carga una ficha
	this.cargarHechosVitales = function(dades) {
    
        // Nos aseguramos de que esté a 0 el campo de control de cambios.
        $moduloModificado.val(0);
    
		fets_seleccionats_elm = escriptori_detall_elm.find("div.modulFetsVitals div.seleccionats");
		fets_llistat_elm = escriptori_detall_elm.find("div.modulFetsVitals div.llistat");
		fets_nodes = dades;
		fets_nodes_size = fets_nodes.length;
		
		fets_llistat_elm.find("input").removeAttr("checked");
		
		if (fets_nodes_size == 0) {
			
			fets_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFets + ".");
			
		} else {
			
			codi_fets = "<ul>";
			
			$(fets_nodes).each(function() {
				fet_node = this;
				codi_fets += "<li element-id='" + fet_node.id + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + fet_node.id + "'><input type=\"hidden\" value=\"" + fet_node.id + "\" />" + fet_node.nom + "</li>";
				fets_llistat_elm.find("input[value=" + fet_node.id + "]").attr("checked","checked").addClass(fetVitalDefaultClass);
			});
			
			codi_fets += "<ul>";
			
			txt_fets = (fets_nodes_size == 1) ? txtFet : txtFets;
			fets_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + fets_nodes_size + " " + txt_fets + "</strong>.");
			fets_seleccionats_elm.find(".listaOrdenable").html(codi_fets);	
			
		}
		
	};
	
	// Devuelve un string con el formato fetsVitals=n1,n2,...,nm donde n son codigos de hechos vitales
	this.listaHechosVitales = function() {
		
		var llistaFets = "";
		
		$("div.modulFetsVitals div.seleccionats div.listaOrdenable input").each(function() {
			llistaFets += $(this).val() + ",";										
		});

		if (llistaFets[llistaFets.length-1] == ","){
			llistaFets = llistaFets.slice(0, -1);
		}
		
		return llistaFets;
		
	};
	
	// Al acceder al formulario de creacion, limpia las listas de hechos vitales y desmarca los checkboxes	
	this.nuevo = function() {
		
		fets_seleccionats_elm = escriptori_detall_elm.find("div.modulFetsVitals div.seleccionats");
		fets_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFets + ".");
		$("div.modulFetsVitals div.llistat input[type=checkbox]").removeAttr('checked').removeClass(fetVitalDefaultClass);
		
	};
	
}	