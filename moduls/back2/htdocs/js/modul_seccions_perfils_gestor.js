// MODUL SECCIONS PERFILS GESTOR

$(document).ready(function() {
	
	// elements
	modul_seccions_elm = $("div.modulSeccions");
	escriptori_seccions_elm = $("#escriptori_seccions");
	
    ModulSeccio = new CModulSeccio();
    EscriptoriSeccio = new CEscriptoriSeccio();
    
	if (modul_seccions_elm.size() == 1) {
		
		// INICIEM
		ModulSeccio.iniciar();
		
	}
	
});

function CModulSeccio() {
	
    var that = this;
    
    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = modul_seccions_elm.find('input[name="modulo_secciones_modificado"]');
    
	this.iniciar = function() {
    
        // Iniciamos el campo de control de cambios 0.
        $moduloModificado.val(0);
        
        seccions_llistat_elm = escriptori_seccions_elm.find("div.escriptori_items_llistat:first");
		seccions_seleccionades_elm = escriptori_seccions_elm.find("div.escriptori_items_seleccionats:first");
		
		seccions_dades_elm = seccions_llistat_elm.find("div.dades:first");
				
		escriptori_seccions_elm.find("div.botonera").each(function() {
			botonera_elm = $(this);
			if (botonera_elm.hasClass("dalt")) {
				botonera_elm.after("<div class=\"rabillo_dalt\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			} else {
				botonera_elm.before("<div class=\"rabillo\">&nbsp;</div>").css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
			}
		});				
		
		// one al botó de gestionar
		modul_seccions_elm.find("a.gestiona").one("click", ModulSeccio.gestiona);
		seccions_llistat_elm.add(seccions_seleccionades_elm);	
		
	};
	
	this.gestiona = function() {
		
		lis_size = modul_seccions_elm.find("li").size();
		
		if (lis_size > 0) {		
			
			codi_seleccionat = "<ul>";
			
			modul_seccions_elm.find("li").each(function() {
				
				li_elm = $(this);
				codi_seleccionat += "<li element-id='" + li_elm.find("input.seccio:first").val() + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + li_elm.find("input.seccio:first").val()  + "'>";
				codi_seleccionat += "<div class=\"seccions\">";
				codi_seleccionat += "<input type=\"hidden\" value=\"" + li_elm.find("input.seccio:first").val() + "\" class=\"seccio\" />";
				codi_seleccionat += "<span class=\"seccio\">" + li_elm.find("em.seccio:first").text() + "</span>";	
				codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"></a>";
				codi_seleccionat += "</div>";
				codi_seleccionat += "</li>";
			});
			
			codi_seleccionat += "</ul>";
						
			escriptori_seccions_elm.find("div.listaOrdenable").html(codi_seleccionat);
			
			EscriptoriSeccio.contaSeleccionats();
			
		} else {
			
			escriptori_seccions_elm.find("div.listaOrdenable").empty().end().find("p.info:first").text(txtNoHiHaSeccionsSeleccionades + ".");
			
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {
			
			escriptori_seccions_elm.fadeIn(300, function() {
				
				// tamanys moduls
				
				// modul seccions
				secc_nivell_ample = escriptori_seccions_arbre_elm.width();
				secc_nivell_ample_em = Math.round(parseFloat($(secc_nivell_ample).toEm()));
				secc_nivell_UL = secc_arbre_elm.find("ul:last");
				secc_nivell_UL_LIs = parseInt(secc_nivell_UL.find("li").size(),10);
				secc_nivell_seguent_alt = secc_nivell_UL.innerHeight() + secc_nivell_UL_LIs;
				secc_arbre_nivells_elm.css({ height: $(secc_nivell_seguent_alt).toEm() });
				
				// activar
				escriptori_seccions_elm.bind("click",EscriptoriSeccio.llansar);
			});
			
		});	
		
	};
    
    this.modificado = function() {
        $moduloModificado.val(1);
    };
    
   this.inicializarSeccions = function(dades) {
		
		seccions_seleccionades_elm = escriptori_detall_elm.find("div.modulSeccions div.seleccionats");
		seccions_seleccionades_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccionsSeleccionades + ".");

		seccio_nodes = dades;
		seccio_nodes_size = seccio_nodes.length;

		if (seccio_nodes_size == 0) {
			
			seccions_seleccionades_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccionsSeleccionades + ".");
			
		} else {
			
			codi_seccio = "<ul>";
			
			$(seccio_nodes).each(function() {
				seccio_node = this;
				codi_seccio += "<li element-id='" + seccio_node.id + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + seccio_node.id  + "'>";
				codi_seccio +="<input class=\"seccio\" type=\"hidden\" value=\"" + seccio_node.id + "\" /> ";
				codi_seccio +="<em class=\"seccio\">"+seccio_node.nom+"</em>" + "</li>";
			});
			
			codi_seccio += "</ul>";
			txt_seccio = (seccio_nodes_size == 1) ? txtSeccio : txtSeccions;			
			seccions_seleccionades_elm.find("p.info").html(txtHiHa + " <strong>" + seccio_nodes_size + " " + txt_seccio + "</strong>.");
			seccions_seleccionades_elm.find(".listaOrdenable").html(codi_seccio);
						
		}
		
		
	};
	
	
    
};

function CEscriptoriSeccio() {
	
    this.llansar = function(e) {
		
		elm = $(e.target);

		if (elm.is("SPAN") && elm.parent().parent().is("A") && elm.parent().parent().hasClass("btn")) {
			
			a_elm = elm.parents("a.btn:first");
			secc_pare_div_elm = $("div.seccions_arbre a.triat").parents("div:first");

			if (a_elm.hasClass("torna")) {
				
				escriptori_seccions_elm.unbind("click", EscriptoriSeccio.llansar);
				
				EscriptoriSeccio.torna();
				
			} else if (a_elm.hasClass("elimina")) {
				
				a_elm.parents("li:first").remove();
				
				EscriptoriSeccio.contaSeleccionats();

			} else if (a_elm.hasClass("inserta")) {	
											
				secc_ID = secc_pare_div_elm.find("input.id").val();
				secc_NOM = secc_pare_div_elm.find("a.selecciona").text();

				// No hi ha cap secció seleccionada							
				if ( secc_ID == undefined) {
					Missatge.llansar({tipus: "alerta", modo:"error", fundit: "si", titol: txtGenericError, text: "<p>" + txtNoHiHaSeccionsSeleccionades + "</p>"});					
				} else {								
					EscriptoriSeccio.afegir({id: secc_ID, nom: secc_NOM});
				}
				
			} else if (a_elm.hasClass("insertaDesc")) {	
				
				secc_ID = secc_pare_div_elm.find("input.id").val();
				secc_NOM = secc_pare_div_elm.find("a.selecciona").text();
				secc_FILLS = secc_pare_div_elm.find("a.btn.fills").val();
				
				// No hi ha cap secció amb filles seleccionada						
				if ( secc_ID == undefined || secc_FILLS == undefined ) {
					Missatge.llansar({tipus: "alerta", modo:"error", fundit: "si", titol: txtGenericError , text:  "<p>" + txtNoHiHaSeccionsSeleccionadesFilles + "</p>"});	
				} else if (secc_FILLS != undefined ) {
					EscriptoriSeccio.afegir({id: secc_ID, nom: secc_NOM});
					EscriptoriSeccio.carregarSeccionsFilles(secc_ID);
				}
				
			} else if (a_elm.hasClass("guarda")) {
               
                
				escriptori_seccions_elm.unbind("click", EscriptoriSeccio.llansar);
				
				
				nombre_llistat = 0;
				
				codi_llistat = "<ul>";
				seccions_seleccionades_elm = escriptori_seccions_elm.find("div.listaOrdenable ul");
				seccions_seleccionades_elm.find("li").each(function(i) {
					
					li_elm = $(this);
					input_elm = li_elm.find("input");
					codi_llistat += "<li element-id='" + li_elm.find("input.seccio").val() + "' main-item-id='" + $('#item_id').val() + "' related-item-id='" + li_elm.find("input.seccio").val() + "'>";
					codi_llistat +="<input class=\"seccio\" type=\"hidden\" value=\"" + li_elm.find("input.seccio").val() + "\" /> ";
					codi_llistat +="<em class=\"seccio\">"+ li_elm.find("span.seccio:first").text() +"</em>" + "</li>";
					nombre_llistat++;
					
				});
				
				codi_llistat += "</ul>";
				
				codi_seccions_txt = (nombre_llistat == 1) ? txtSeccio : txtSeccions;
				codi_info = (nombre_llistat == 0) ? txtNoHiHaSeccions + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_seccions_txt.toLowerCase() + "</strong>.";
								
				modul_seccions_elm.find("p.info").html(codi_info);
				modul_seccions_elm.find("div.listaOrdenable").html(codi_llistat);

				EscriptoriSeccio.torna();
				
			}

		}
		
		if (elm.hasClass("btn elimina")){
			
			elm.parents("li:first").remove();
			EscriptoriSeccio.contaSeleccionats();
			
		};
		
	};
	
	this.torna = function() {
		
		// animacio
		escriptori_seccions_elm.fadeOut(300, function() {
			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_elm.find("a.gestiona").one("click", ModulSeccio.gestiona);
			});
			
		});
		
	};
	
	this.contaSeleccionats = function() {
		
		seleccionats_val = escriptori_seccions_elm.find("div.listaOrdenable li").size();
		info_elm = escriptori_seccions_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			escriptori_seccions_elm.find("div.listaOrdenable ul").remove();
			info_elm.text(txtNoHiHaSeccionsSeleccionades + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtSeccio.toLowerCase() + "</strong>.");
			
		} else {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtSeccions.toLowerCase() + "</strong>.");
			escriptori_seccions_elm.find("div.listaOrdenable ul").sortable({ axis: 'y', cursor: 'url(../img/cursor/grabbing.cur), move' }).find("li").css("cursor","url(../img/cursor/grab.cur), move");
			
		}
	
	};
	
	this.afegir = function(dades) {
		
		seccio_id = dades.id;
		seccio_titol = dades.nom;
		
		if ($("#escriptori_seccions li input.seccio[type=hidden][value="+seccio_id+"]").size() == 0) {
			codi_seleccionat = "<li element-id='" + seccio_id + "' main-item-id='" +  $('#item_id').val()  + "' related-item-id='" + seccio_id + "'>";
			codi_seleccionat += "<div class=\"seccions\">";
			codi_seleccionat += "<input type=\"hidden\" value=\"" + seccio_id + "\" class=\"seccio\" />";
			codi_seleccionat += "<span class=\"seccio\">";
			codi_seleccionat += "<span class=\"seccio\">" + seccio_titol + "</span>";
			codi_seleccionat += "</span>";			
			codi_seleccionat += "<a href=\"javascript:;\" class=\"btn elimina\"></a>";
			codi_seleccionat += "</div>";
			codi_seleccionat += "</li>";

			seccions_seleccionades_elm_ul = escriptori_seccions_elm.find("div.listaOrdenable ul");
			
			if (seccions_seleccionades_elm_ul.size() > 0){				
				seccions_seleccionades_elm_ul.append(codi_seleccionat);				
			} else {
				escriptori_seccions_elm.find("div.listaOrdenable").append("<ul>"+codi_seleccionat+"</ul>");
			}					
			
		}
		EscriptoriSeccio.contaSeleccionats();
		
	}
	
	this.carregarSeccionsFilles = function(idSeccio) {			
		// ajax
		$.ajax({
			type: "POST",
			url: pagArbreSeccions,
			data:  "pare=" + idSeccio,
			dataType: "json",
			error: function() {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
					Error.llansar();
			},
			success: function(data) {
					data_seccio = data.llistaSeccions;
					$(data_seccio).each(function(index, value) {
						EscriptoriSeccio.afegir(value);
					});
			}
		});
		
	};
	
};
