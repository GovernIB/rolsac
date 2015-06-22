// MODUL PERFILS GESTOR RELACIONATS

jQuery(document).ready(function() {
	ModulPerfilsGestor = new CModulPerfilsGestor();
});

function CModulPerfilsGestor() {
	
	var that = this;
	var perfilsGestorDefaultClass = "perfilsGestorDefault";
	var modul_perfils_gestor_elm = jQuery("div.modulPerfilsGestor");
	var perfils_gestor_seleccionats_elm;
	var perfils_gestor_llistat_elm;
    
    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = modul_perfils_gestor_elm.find('input[name="modulo_perfils_gestor_modificado"]');
    
    modul_perfils_gestor_elm.find("input[type=checkbox]").change(function() {               
        $moduloModificado.val(1);
    });
					 	
	if ( modul_perfils_gestor_elm.size() == 1 ) {		

		modul_perfils_gestor_elm.find("a.gestiona").bind("click", function() {	
			that.gestiona();
		});
		
		modul_perfils_gestor_elm.find("a.cancela").bind("click", function() {
			that.cancela();
		});
		
		modul_perfils_gestor_elm.find("a.finalitza").bind("click", function() {
			that.finaliza();
		});
		
		perfils_gestor_seleccionats_elm = modul_perfils_gestor_elm.find("div.seleccionats:first");
		perfils_gestor_llistat_elm = modul_perfils_gestor_elm.find("div.llistat:first");				
		
	}
	
	this.cancela = function() {
    
        // Restauramos el estado del campo de control de cambios.
        $moduloModificado.val( $moduloModificado.data('oldvalue') );
    
//		perfils_gestor_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
		perfils_gestor_llistat_elm.find("input[type=checkbox]").each(function() {
			$this = jQuery(this);
			if ($this.hasClass(perfilsGestorDefaultClass)) {
				$this.attr("checked", "checked");
			} else {
				$this.removeAttr("checked");
			}
		});
		
		perfils_gestor_seleccionats_elm.slideDown(300);
		perfils_gestor_llistat_elm.slideUp(300);
		
	};
	
	this.gestiona = function() {
    
        // Guardamos el estado del campo de control de cambios.
        $moduloModificado.data( 'oldvalue', $moduloModificado.val() );
    
		perfils_gestor_seleccionats_elm.slideUp(300);
		perfils_gestor_llistat_elm.slideDown(300);
		
	};
    
    this.modificado = function() {
        $moduloModificado.val(1);
    };
	
	this.finaliza = function() {
		
		nombre_llistat = 0;
				
		codi_llistat = "<ul>";
		
		perfils_gestor_llistat_elm.find("li").each(function(i) {
		
			li_elm = $(this);
			input_elm = li_elm.find("input");
			
            if (input_elm.is(":checked")) {
				codi_llistat += "<li element-id='" + input_elm.val() + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + input_elm.val() + "'><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
				input_elm.addClass(perfilsGestorDefaultClass);
			} else {
				input_elm.removeClass(perfilsGestorDefaultClass);
			}
            
		});
		
		codi_llistat += "</ul>";
		
		codi_perfil_gestor_txt = (nombre_llistat == 1) ? txtPerfilGestor.toLowerCase() : txtPerfilsGestor.toLowerCase();
		codi_info = (nombre_llistat == 0) ? txtNoHiHaPerfilsGestor.toLowerCase() + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_perfil_gestor_txt + "</strong>.";
                
		perfils_gestor_seleccionats_elm.find("p.info").html(codi_info);
		perfils_gestor_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);		
		
		perfils_gestor_seleccionats_elm.slideDown(300);
		perfils_gestor_llistat_elm.slideUp(300);
        
        // Marcamos el módulo como modificado.
        //this.modificado(false);
        $moduloModificado.val(0);
        
        // amartin: emitimos señal de finalización, para que los guardados AJAX la detecten y procesen los nuevos elemento seleccionados.
        modul_perfils_gestor_elm.trigger("finalizaPerfilsGestor");
        
	};
	
	// Actualiza la lista de hechos vitales seleccionados y marca los checkboxes cuando se carga una ficha
	this.inicializarPerfilsGestor = function(dades) {
    
        // Nos aseguramos de que esté a 0 el campo de control de cambios.
        $moduloModificado.val(0);
    
		perfils_gestor_seleccionats_elm = escriptori_detall_elm.find("div.modulPerfilsGestor div.seleccionats");
		perfils_gestor_llistat_elm = escriptori_detall_elm.find("div.modulPerfilsGestor div.llistat");
		perfils_gestor_nodes = dades;
		perfils_gestor_nodes_size = perfils_gestor_nodes.length;
		
		perfils_gestor_llistat_elm.find("input").removeAttr("checked");
		
		if (perfils_gestor_nodes_size == 0) {
			
			perfils_gestor_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaPerfilsGestor + ".");
			
		} else {
			
			codi_perfil_gestor = "<ul>";
			
			$(perfils_gestor_nodes).each(function() {
				perfil_gestor_node = this;
				codi_perfil_gestor += "<li element-id='" + perfil_gestor_node.id + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + perfil_gestor_node.id + "'><input type=\"hidden\" value=\"" + perfil_gestor_node.id + "\" />" + perfil_gestor_node.nom + "</li>";
				perfils_gestor_llistat_elm.find("input[value=" + perfil_gestor_node.id + "]").attr("checked","checked").addClass(perfilsGestorDefaultClass);
			});
			
			codi_perfil_gestor += "<ul>";
			
			txt_perfils_gestor = (perfils_gestor_nodes_size == 1) ? txtPerfilGestor : txtPerfilsGestor;
			perfils_gestor_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + perfils_gestor_nodes_size + " " + txt_perfils_gestor + "</strong>.");
			perfils_gestor_seleccionats_elm.find(".listaOrdenable").html(codi_perfil_gestor);	
			
		}
		
	};
	
	// Al acceder al formulario de creacion, limpia las listas de perfils gestor y desmarca los checkboxes	
	this.nuevo = function() {
		
		perfils_gestor_seleccionats_elm = escriptori_detall_elm.find("div.modulPerfilsGestor div.seleccionats");
		perfils_gestor_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaPerfilsGestor + ".");
		$("div.modulPerfilsGestor div.llistat input[type=checkbox]").removeAttr('checked').removeClass(perfilsGestorDefaultClass);
		
	};
	
}	