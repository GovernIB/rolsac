// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
				
	escriptori_detall_elm = $("#escriptori_detall");
	escriptori_previsualitza_elm = $("#escriptori_previsualitza");
			
	// INICIEM
	Error = new CError();
	Detall = new CDetall(false);
	
	Detall.iniciar();
	
});

// items array
var Items_arr = new Array();

// detall
function CDetall(soloFicha){	
	this.extend = DetallBase;
	this.extend(soloFicha);
		
	
	this.iniciar = function() {
		//redigirimos el método que guarda porque en este caso también hacemos un upload de archivos
		this.guarda = this.guarda_upload;
		
				
		// idioma
		if (escriptori_detall_elm.find("div.idiomes").size() != 0) {
			
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
			ul_idiomes_elm.bind("click",Detall.idioma);
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
				
		// carregar
		Detall.carregar();
		
	}
	
	
	//Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {
		
		// Validamos el formulario
		if( typeof FormulariComprovar != "undefined" ){
					
			FormulariComprovar.llansar();
			
			if (!formComprovacio) {				
				return false;
			}
		}
		
		//Enviamos el formulario mediante el método ajaxSubmit del plugin jquery.form
		$("#formGuardar").ajaxSubmit({			
			url: pagGuardar,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
							
				Llistat.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					Detall.recarregar();
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom});
				}					
			}
								
		});

		return false;	
		
	}
	
	
	// Método sobreescrito
	this.busca = function(){
			
		edificis_cercador_elm.find("input, select").attr("disabled", "disabled");
		
		// animacio
		edificis_dades_elm.fadeOut(300, function() {
			// pintem
			codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
			edificis_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				pagPagina_edifici_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				EscriptoriEdifici.carregar({});
				
			});
		});
	}
			
	// Método sobreescrito.
	this.carregar = function(itemID) {
			
		if (itemID == undefined){
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
				// error
				Error.llansar();
				
			},
			success: function(data) {
				Detall.pintar(data);								
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
		if (dada_node.id != -1){							
								
			$("#item_id").val(dada_node.id);
			
			//Bloque de pestanyas de idiomas
			
			$("#item_nom_ca").val(printStringFromNull(dada_node.ca.nombre));
			$("#item_presentacio_ca").val(printStringFromNull(dada_node.ca.presentacion));
			$("#item_abreviatura_ca").val(printStringFromNull(dada_node.ca.abreviatura));

			$("#item_nom_es").val(printStringFromNull(dada_node.es.nombre));
			$("#item_presentacio_es").val(printStringFromNull(dada_node.es.presentacion));
			$("#item_abreviatura_es").val(printStringFromNull(dada_node.es.abreviatura));
			
			$("#item_nom_en").val(printStringFromNull(dada_node.en.nombre));
			$("#item_presentacio_en").val(printStringFromNull(dada_node.en.presentacion));
			$("#item_abreviatura_en").val(printStringFromNull(dada_node.en.abreviatura));
			
			$("#item_nom_de").val(printStringFromNull(dada_node.de.nombre));
			$("#item_presentacio_de").val(printStringFromNull(dada_node.de.presentacion));
			$("#item_abreviatura_de").val(printStringFromNull(dada_node.de.abreviatura));
			$("#item_url_de").val(printStringFromNull(dada_node.de.url));
			
			$("#item_nom_fr").val(printStringFromNull(dada_node.fr.nombre));
			$("#item_presentacio_fr").val(printStringFromNull(dada_node.fr.presentacion));
			$("#item_abreviatura_fr").val(printStringFromNull(dada_node.fr.abreviatura));
			
			//Configuracion / gestion
			
			$("#item_clau_hita").val(dada_node.item_clau_hita);
			$("#item_codi_estandar").val(dada_node.item_codi_estandar);
			$("#item_domini").val(dada_node.item_domini);
			marcarOpcionSelect("item_validacio",dada_node.item_validacio);
			//$("#item_validacio").val(dada_node.item_validacio);
			$("#item_telefon").val(dada_node.item_telefon);
			$("#item_fax").val(dada_node.item_fax);
			$("#item_email").val(dada_node.item_email);
			marcarOpcionSelect("item_espai_territorial",dada_node.item_espai_territorial);
			//$("#item_espai_territorial").val(dada_node.item_espai_territorial);
			$("#item_pare_id").val(dada_node.pareId);
			$("#item_pare").val(dada_node.pareNom);
			
			//Responsable			
			$("#item_responsable").val(dada_node.item_responsable);	
			marcarOpcionSelect("item_responsable_sexe",dada_node.item_responsable_sexe);
			//$("#item_responsable_sexe").val(dada_node.item_responsable_sexe);
			
			//FotoPetita
			$("#item_responsable_foto_petita").val("");
			$("#grup_item_responsable_foto_petita input").removeAttr("checked");
			if (dada_node["enllas_arxiu"]) {
				$("#grup_item_responsable_foto_petita a").show();					
				$("#grup_item_responsable_foto_petita a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_item_responsable_foto_petita a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_item_responsable_foto_petita span").hide();
				$("#grup_item_responsable_foto_petita input").show();
				$("#grup_item_responsable_foto_petita label.eliminar").show();
							
			} else {
				$("#grup_item_responsable_foto_petita span").show();
				$("#grup_item_responsable_foto_petita input").hide();
				$("#grup_item_responsable_foto_petita label.eliminar").hide();
				$("#grup_item_responsable_foto_petita a").hide();			
			}	
			
			//FotoGran
			$("#item_responsable_foto_gran").val("");
			$("#grup_item_responsable_foto_gran input").removeAttr("checked");
			if (dada_node["enllas_arxiu"]) {
				$("#grup_item_responsable_foto_gran a").show();					
				$("#grup_item_responsable_foto_gran a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_item_responsable_foto_gran a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_item_responsable_foto_gran span").hide();
				$("#grup_item_responsable_foto_gran input").show();
				$("#grup_item_responsable_foto_gran label.eliminar").show();
							
			} else {
				$("#grup_item_responsable_foto_gran span").show();
				$("#grup_item_responsable_foto_gran input").hide();
				$("#grup_item_responsable_foto_gran label.eliminar").hide();
				$("#grup_item_responsable_foto_gran a").hide();			
			}
			
			$("#item_tractament").val(dada_node.item_tractament).attr('selected',true);			
			
			//Logotipos
			//LogoHoritzontal
			$("#item_logo_horizontal").val("");
			$("#grup_item_logo_horizontal input").removeAttr("checked");
			if (dada_node["enllas_arxiu"]) {
				$("#grup_item_logo_horizontal a").show();					
				$("#grup_item_logo_horizontal a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_item_logo_horizontal a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_item_logo_horizontal span").hide();
				$("#grup_item_logo_horizontal input").show();
				$("#grup_item_logo_horizontal label.eliminar").show();
							
			} else {
				$("#grup_item_logo_horizontal span").show();
				$("#grup_item_logo_horizontal input").hide();
				$("#grup_item_logo_horizontal label.eliminar").hide();
				$("#grup_item_logo_horizontal a").hide();			
			}
			//LogoVertical
			$("#item_logo_vertical").val("");
			$("#grup_item_logo_vertical input").removeAttr("checked");
			if (dada_node["enllas_arxiu"]) {
				$("#grup_item_logo_vertical a").show();					
				$("#grup_item_logo_vertical a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_item_logo_vertical a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_item_logo_vertical span").hide();
				$("#grup_item_logo_vertical input").show();
				$("#grup_item_logo_vertical label.eliminar").show();
							
			} else {
				$("#grup_item_logo_vertical span").show();
				$("#grup_item_logo_vertical input").hide();
				$("#grup_item_logo_vertical label.eliminar").hide();
				$("#grup_item_logo_vertical a").hide();			
			}
			//LogoSalutacioHoritzontal
			$("#item_logo_salutacio_horizontal").val("");
			$("#grup_item_logo_salutacio_horizontal input").removeAttr("checked");
			if (dada_node["enllas_arxiu"]) {
				$("#grup_item_logo_salutacio_horizontal a").show();					
				$("#grup_item_logo_salutacio_horizontal a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_item_logo_salutacio_horizontal a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_item_logo_salutacio_horizontal span").hide();
				$("#grup_item_logo_salutacio_horizontal input").show();
				$("#grup_item_logo_salutacio_horizontal label.eliminar").show();
							
			} else {
				$("#grup_item_logo_salutacio_horizontal span").show();
				$("#grup_item_logo_salutacio_horizontal input").hide();
				$("#grup_item_logo_salutacio_horizontal label.eliminar").hide();
				$("#grup_item_logo_salutacio_horizontal a").hide();			
			}
			//LogoSalutacioVertical
			$("#item_logo_salutacio_vertical").val("");
			$("#grup_item_logo_salutacio_vertical input").removeAttr("checked");
			if (dada_node["enllas_arxiu"]) {
				$("#grup_item_logo_salutacio_vertical a").show();					
				$("#grup_item_logo_salutacio_vertical a").attr("href", pagArrel + dada_node["idioma_" + idioma + "_enllas_arxiu"]);
				$("#grup_item_logo_salutacio_vertical a").text(dada_node["idioma_" + idioma + "_nom_arxiu"]);
				$("#grup_item_logo_salutacio_vertical span").hide();
				$("#grup_item_logo_salutacio_vertical input").show();
				$("#grup_item_logo_salutacio_vertical label.eliminar").show();
							
			} else {
				$("#grup_item_logo_salutacio_vertical span").show();
				$("#grup_item_logo_salutacio_vertical input").hide();
				$("#grup_item_logo_salutacio_vertical label.eliminar").hide();
				$("#grup_item_logo_salutacio_vertical a").hide();			
			}
			
			
			//Fitxes de la portada web
			$("#item_nivell_1").val(dada_node.item_nivell_1);
			$("#item_nivell_2").val(dada_node.item_nivell_2);
			$("#item_nivell_3").val(dada_node.item_nivell_3);
			$("#item_nivell_4").val(dada_node.item_nivell_4);		
			
			
			// materies
			mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
			mat_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
			materies_nodes = dada_node.materies;
			materes_nodes_size = materies_nodes.length;
			
			mat_llistat_elm.find("input").removeAttr("checked");
			
			if (materes_nodes_size == 0) {
				mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
			} else {
				codi_materies = "<ul>";
				$(materies_nodes).each(function() {
					materia_node = this;
					codi_materies += "<li><input type=\"hidden\" value=\"" + materia_node.id + "\" />" + materia_node.nom + "</li>";
					mat_llistat_elm.find("input[value=" + materia_node.id + "]").attr("checked","checked");
				});
				codi_materies += "<ul>";
				txt_materies = (materes_nodes_size == 1) ? txtMateria : txtMateries;
				//mat_seleccionats_elm.find("ul").remove().end().find("p.info").html(txtHiHa + " <strong>" + materes_nodes_size + " " + txt_materies + "</strong>.").after(codi_materies);
				mat_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + materes_nodes_size + " " + txt_materies + "</strong>.");
				mat_seleccionats_elm.find(".listaOrdenable").html(codi_materies);
			}

			
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
				var i=0;
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
			
		}
		// Eliminam missatge per poder crear de noves
 		//else {
			
		//	$("#escriptori_detall").html("<p class=\"noUnitat\">" + noUnitat +"</p>");			
			
		//}
			
		// mostrem
		//if ($("#carregantDetall").size() > 0) {
			
			$("#carregantDetall").fadeOut(300, function() {
				
				//$(this).remove();
				
				// array
				Detall.array({id: dada_node.id, accio: "guarda", dades: dada_node});
				
				escriptori_detall_elm.fadeIn(300);
											
			});
		/*
		} else {
			
			escriptori_contingut_elm.fadeOut(300, function() {
				escriptori_detall_elm.fadeIn(300);
			});			
			
		}
		*/
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
				
				// missatge
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				// error
				Error.llansar();
				
			},
			success: function(data) {
				
				Missatge.cancelar();
				
				if (data.id > -1) {
					Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: txtEsborrarCorrecte});
				}
//				else {
//					// tratar error;
//					data.id;
//					data.errores[]
//
//					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtTitulo, text: erroresHTML});
//				}
				
				// array
				Detall.array({id: dada_node.id, accio: "elimina"});
				
				// recarregar
				Detall.recarregar();				
					
			}			
		});
	}
	
	this.previsualitza = function() {
		
		escriptori_detall_elm.fadeOut(300, function() {
			
			fitxa_idiomaSeleccionat = escriptori_detall_elm.find("ul.idiomes li.seleccionat span").attr("class");
			fitxa_ID = escriptori_detall_elm.find("#item_id").val();
			
			previsualitza_url = "http://www.caib.es/govern/organigrama/area.do?lang=" + fitxa_idiomaSeleccionat + "&coduo=6"; //+ fitxa_ID;
			
			escriptori_previsualitza_elm.find("iframe").attr("src", previsualitza_url).end().fadeIn(300, function() {
			
				$(this).find("a.dePrevisualitzar").one("click", Detall.previsualitzaTorna);
			
			});		
		});
		
	}
}

function posarValorsInput(idInput, valor)
{
	$(idInput).val(valor);
}
