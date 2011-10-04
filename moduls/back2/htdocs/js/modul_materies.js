// MODULO DE MATERIAS RELACIONADAS

jQuery(document).ready(function() {
	ModulMateries = new CModulMateries();
});

function CModulMateries(){
	var that = this;
	var modul_materies_elm = jQuery("div.modulMateries");
	var materies_seleccionats_elm;
	var materies_llistat_elm;
					 	
	if ( modul_materies_elm.size() == 1 ) {		

		modul_materies_elm.find("a.gestiona").bind("click", function(){	
			that.gestiona();
		});
		modul_materies_elm.find("a.cancela").bind("click", function(){
			that.cancela();
		});
		modul_materies_elm.find("a.finalitza").bind("click", function(){
			that.finaliza();
		});
		
		materies_seleccionats_elm = modul_materies_elm.find("div.seleccionats:first");
		materies_llistat_elm = modul_materies_elm.find("div.llistat:first");				
		
	}
	
	this.cancela = function(){
		materies_seleccionats_elm.slideDown(300);
		materies_llistat_elm.slideUp(300);
	}
	
	this.gestiona = function(){
		materies_seleccionats_elm.slideUp(300);
		materies_llistat_elm.slideDown(300);
	}
	
	this.finaliza = function(){
		nombre_llistat = 0;
				
		codi_llistat = "<ul>";
		
		materies_llistat_elm.find("li").each(function(i) {
		
			li_elm = $(this);
			input_elm = li_elm.find("input");
			
			if (input_elm.attr("checked")) {
				codi_llistat += "<li><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
			}
			
		});
		
		codi_llistat += "</ul>";
		
		codi_materia_txt = (nombre_llistat == 1) ? txtMateria : txtMateries;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaMateries + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_materia_txt + "</strong>.";
		
		//materies_seleccionats_elm.find("ul").remove().end().find("p.info").html(codi_info).after(codi_llistat);
		materies_seleccionats_elm.find("p.info").html(codi_info);
		materies_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);		
		
		materies_seleccionats_elm.slideDown(300);
		materies_llistat_elm.slideUp(300);
		
	}
};
