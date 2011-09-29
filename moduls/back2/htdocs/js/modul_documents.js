// MODUL DOCUMENTS

$(document).ready(function() {
	
	// elements
	modul_documents_elm = $("div.modulDocuments");
	
	if (modul_documents_elm.size() == 1) {
		
		if (typeof modul_documents_elm != "undefined") {
			modul_documents_elm.find("ul.idiomes:first li").css({"border-radius": "0.5em", "-moz-border-radius": "0.5em", "-webkit-border-radius": "0.5em"})
			//modul_documents_elm.find("ul.idiomes:first").css("background-image", "-moz-linear-gradient(0% 50% 270deg, #fff6ee, #ffddbc)");
		}
		
		// INICIEM
		ModulDocuments.iniciar();
		
	}
	
});

var ModulDocuments = {
	iniciar: function() {
		
		modul_documents_idiomes_elm = modul_documents_elm.find("ul.idiomes:first");
		documents_seleccionats_elm = modul_documents_elm.find("div.seleccionats:first");
		
		modul_documents_elm.bind("click", ModulDocuments.llansar);
		
	},
	llansar: function(e) {
		
		elm = $(e.target);
		
		if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a.btn:first");
			
			if (a_elm.hasClass("afegeix")) {
		
				div_seleccionat_elm = documents_seleccionats_elm.find("div.seleccionat")
				
				div_classes = div_seleccionat_elm.attr("class").split(" ");
				idioma_class = div_classes[0];
				
				if (div_seleccionat_elm.find("ul").size() == 0) {
					div_seleccionat_elm.append("<ul></ul>");
				}
				
				ul_seleccionat_elm = div_seleccionat_elm.find("ul:first");
				
				codi_docs = "<li>";
				codi_docs += "<div>";
				codi_docs += "<span class=\"doc\"><input name=\"doc_" + idioma_class + "\" type=\"file\" size=\"5\" /></span>";
				codi_docs += "<a href=\"javascript:;\" class=\"btn esborra\"><span><span>" + txtEsborra + "</span></span></a>";
				codi_docs += "</div>";
				codi_docs += "</li>";
				
				ul_seleccionat_elm.append(codi_docs);
			
			} else if (a_elm.hasClass("esborra")) {
				
				a_elm.parents("li:first").remove();
				
			} else if (a_elm.hasClass("lleva")) {
				
				div_elm = a_elm.parents("div:first");
				div_elm.find("span.doc").css("text-decoration","line-through");
				
				a_elm.removeClass("lleva").addClass("inclou").html("<span><span>" + txtInclou + "</span></span>");
				
			} else if (a_elm.hasClass("inclou")) {
				
				div_elm = a_elm.parents("div:first");
				div_elm.find("span.doc").css("text-decoration","none");
				
				a_elm.removeClass("inclou").addClass("lleva").html("<span><span>" + txtLleva + "</span></span>");
				
			}
			
		}
		
	}
};
