// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	modul_fets_elm = $("div.modulFetsVitals");
	
	if (modul_fets_elm.size() == 1) {
		
		// INICIEM
		ModulFetsVitals.iniciar();
		
	}
	
});

var ModulFetsVitals = {
	iniciar: function() {
		
		fets_seleccionats_elm = modul_fets_elm.find("div.seleccionats:first");
		fets_llistat_elm = modul_fets_elm.find("div.llistat:first");
		
		modul_fets_elm.bind("click", ModulFetsVitals.llansar);
		
	},
	llansar: function(e) {
		
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			if (elm.hasClass("cancela")) {
				
				fets_seleccionats_elm.slideDown(300);
				fets_llistat_elm.slideUp(300);
				
			}
			
		} else if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
		
			a_elm = elm.parents("a.btn:first");
			
			if (a_elm.hasClass("gestiona")) {
				
				fets_seleccionats_elm.slideUp(300);
				fets_llistat_elm.slideDown(300);
				
			} else if (a_elm.hasClass("finalitza")) {
				
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
				
				codi_materia_txt = (nombre_llistat == 1) ? txtFet : txtFets;
				codi_info = (nombre_llistat == 0) ? txtNoHiHaFets + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_materia_txt + "</strong>.";
				
				fets_seleccionats_elm.find("ul").remove().end().find("p.info").html(codi_info).after(codi_llistat);
				
				fets_seleccionats_elm.slideDown(300);
				fets_llistat_elm.slideUp(300);
				
			}
		
		}
		
	}
};
