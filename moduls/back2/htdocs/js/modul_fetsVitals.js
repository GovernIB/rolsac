// MODULO DE HECHOS VITALES RELACIONADOS

jQuery(document).ready(function() {
	ModulFetsVitals = new CModulFetsVitals();
});

function CModulFetsVitals(){
	var that = this;
	var modul_fets_elm = jQuery("div.modulFetsVitals");
	var fets_seleccionats_elm;
	var fets_llistat_elm;
					 	
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
	
	this.cancela = function(){
		fets_seleccionats_elm.slideDown(300);
		fets_llistat_elm.slideUp(300);
	}
	
	this.gestiona = function(){
		fets_seleccionats_elm.slideUp(300);
		fets_llistat_elm.slideDown(300);
	}
	
	this.finaliza = function(){
		nombre_llistat = 0;
				
		codi_llistat = "<ul>";
		
		fets_llistat_elm.find("li").each(function(i) {
		
			li_elm = $(this);
			input_elm = li_elm.find("input");
			
			if (input_elm.attr("checked")) {
				codi_llistat += "<li><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
			}
			
		});
		
		codi_llistat += "</ul>";
		
		codi_fet_txt = (nombre_llistat == 1) ? txtFet : txtFets;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaFets + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_fet_txt + "</strong>.";
				
		fets_seleccionats_elm.find("p.info").html(codi_info);
		fets_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);		
		
		fets_seleccionats_elm.slideDown(300);
		fets_llistat_elm.slideUp(300);
		
	}
	
	//Actualiza la lista de hechos vitales seleccionados y marca los checkboxes cuando se carga una ficha
	
	this.cargarHechosVitales = function(dades){
	
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
				codi_fets += "<li><input type=\"hidden\" value=\"" + fet_node.id + "\" />" + fet_node.nom + "</li>";
				fets_llistat_elm.find("input[value=" + fet_node.id + "]").attr("checked","checked");
			});
			codi_fets += "<ul>";
			txt_fets = (fets_nodes_size == 1) ? txtFet : txtFets;
			fets_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + fets_nodes_size + " " + txt_fets + "</strong>.");
			fets_seleccionats_elm.find(".listaOrdenable").html(codi_fets);			
		}
	}
	
	//devuelve un string con el formato fetsVitals=n1,n2,...,nm donde n son codigos de hechos vitales
	this.listaHechosVitales = function (){
		
		var llistaFets = "fetsVitals=";
		
		$("div.modulFetsVitals div.seleccionats div.listaOrdenable input").each(function() {
			llistaFets += $(this).val() + ",";										
		});
		if (llistaFets.length > 0){
			llistaFets = llistaFets.slice(0, llistaFets.length-1);			 
		}
		return llistaFets;
	}
	
	//Al acceder al formulario de creacion, limpia las listas de hechos vitales y desmarca los checkboxes	
	this.nuevo = function() {
		
		fets_seleccionats_elm = escriptori_detall_elm.find("div.modulFetsVitals div.seleccionats");
		fets_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaFets + ".");
		$("div.modulFetsVitals div.llistat input[type=checkbox]").attr('checked', false);
		
	}		
}	