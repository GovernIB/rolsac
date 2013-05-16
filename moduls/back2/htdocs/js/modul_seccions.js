// MODUL SECCIONS
$(document).ready(function() {
    
	// En la pantalla de UAs no ha de haber ordenacion de secciones
	pantallaUAs = jQuery('#escriptori_seccions').length > 0;
	ordenar_secciones = !pantallaUAs;
	
	// Elements
	modul_seccions_elm = $("div.modulSeccions");
	modul_fitxes_elm = $("div.modulFitxes");
	escriptori_seccions_elm = $("#escriptori_seccions");
	escriptori_fitxes_elm = $("#escriptori_fitxes");

	ModulSeccions = new CModulSeccio();
	ModulFitxes = new CModulSeccio();
	
	if (modul_seccions_elm.size() == 1) {
		ModulSeccions.iniciarSeccion();
		ModulFitxes.iniciarFichas();
	}
	
	EscriptoriSeccio = new CEscriptoriSeccio();
	EscriptoriSeccioFitxes = new CEscriptoriSeccioFitxes();
	
	Llistat = EscriptoriSeccio;
	LlistatSeccioFitxes = EscriptoriSeccioFitxes;
	
	multipagina = new Multipagina();
	multipaginaFitxes = new Multipagina();
	
	// Evento para el boton de volver al detalle
	jQuery(".btnVolverDetalleSecciones").bind("click",function() { EscriptoriSeccio.torna(); } );	
	jQuery("#btnFinalizarSecciones").bind("click",function() { EscriptoriSeccio.finalizar(); } );
	
	jQuery(".btnVolverDetalleFichas").bind("click",function() { EscriptoriSeccioFitxes.torna(); } );	
	jQuery("#btnFinalizarFichas").bind("click",function() { EscriptoriSeccioFitxes.finalizar(); } );
	
});

function CModulSeccio() {
	
	var that = this;
	var params;
	var paramsFicha;
	var seccions_llistat_seccions;
	
	//Necessitem una copia de la llista original de seccions-fitxes. Es necessari perque l'usuari podria
	//esborrar una seccio amb les seves fitxes i tornar a afegir-la des de els resultats de la cerca de 
	//seccions si troba que s'ha equivocat.
	var copiaNodesOrigen;
	
	if ( modul_seccions_elm.size() != 1)
		return 0;
	
    // Campo hidden para controlar los cambios sobre un modulo.
    var $moduloModificado = modul_seccions_elm.find('input[name="modulo_secciones_modificado"]');
    
    // Campo hidden para controlar los cambios sobre los sub-elementos de la lista.
    var $subModuloModificado;
    
	this.extend = ListaOrdenable;
	this.extend();
	
    if ( modul_seccions_elm.size() == 1 ) {

		modul_seccions_elm.find("a.gestiona").unbind("click").bind("click", function(){	
			that.gestionaSeccio();
		});
		
		seccions_seleccionats_elm = modul_seccions_elm.find("div.seleccionats:first");
		seccions_llistat_elm = modul_seccions_elm.find("div.llistat:first");				
		
	}
    
	this.mostraFitxes = function(e)  {
		
		//Mostrar panel de fichas de la seccion actual
		divFichas = $(e).next().next();
		
		if ($(divFichas).is(":visible"))
			divFichas.fadeOut(200);
		else
			divFichas.fadeIn(200);
		
		return false;
	}

	this.iniciarFichas = function()  {
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "fitxa",
			nodoOrigen: modul_fitxes_elm.find(".listaOrdenable"),
			nodoDestino: modul_fitxes_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "orden", "caducado", "idUA", "nomUA"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
	}
	
	this.iniciarSeccion = function()  {
		// Configuramos la lista ordenable.
		var _atributos = ordenar_secciones ? ["id", "nombre", "orden"] : ["id", "nombre"];  // Campos que queremos que aparezcan en las listas.   
		this.configurar({
			nombre: "seccio",
			nodoOrigen: modul_seccions_elm.find(".listaOrdenable"),
			nodoDestino: modul_seccions_elm.find(".listaOrdenable"),
			atributos: _atributos,
			multilang: false
		});
	}	   
	
	this.iniciar = function(dades) {
		
		seccions_nodes = dades;		
		seccions_nodes_size = dades.length;
		
		seccions_llistat_elm = escriptori_seccions_elm.find("div.escriptori_items_llistat:first");
		seccions_cercador_elm = escriptori_seccions_elm.find("div.escriptori_items_cercador:first");
		seccions_seleccionats_elm = escriptori_seccions_elm.find("div.escriptori_items_seleccionats:first");		
		seccions_dades_elm = seccions_llistat_elm.find("div.dades:first");
		
		pagPagina_seccio_elm = seccions_llistat_elm.find("input.pagPagina:first");
		ordreTipus_seccio_elm = seccions_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_seccio_elm = seccions_llistat_elm.find("input.ordreCamp:first");		

		fitxes_llistat_elm = escriptori_fitxes_elm.find("div.escriptori_items_llistat:first");		
		fitxes_cercador_elm = escriptori_fitxes_elm.find("div.escriptori_items_cercador:first");		
		fitxes_seleccionats_elm = escriptori_fitxes_elm.find("div.escriptori_items_seleccionats:first");
		fitxes_dades_elm = fitxes_llistat_elm.find("div.dades:first"); 		
		
		pagPagina_fitxa_elm = fitxes_llistat_elm.find("input.pagPagina:first");
		ordreTipus_fitxa_elm = fitxes_llistat_elm.find("input.ordreTipus:first");
		ordreCamp_fitxa_elm = fitxes_llistat_elm.find("input.ordreCamp:first");		
				
		/*seccions_cercador_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});
		fitxes_cercador_elm.css({"border-radius": "1em", "-moz-border-radius": "1em", "-webkit-border-radius": "1em"});*/
				
		// enllassos
	    modul_seccions_elm.bind("click", CModulSeccio.cerca);
				
		var _atributos = ordenar_secciones ? ["id", "nombre", "orden"] : ["id", "nombre"];  // Campos que queremos que aparezcan en las listas.
		params = {
				nombre: "seccio",
				nodoOrigen: modul_seccions_elm.find(".listaOrdenable:first"),
				nodoDestino: seccions_seleccionats_elm.find(".listaOrdenable"),
				atributos: _atributos,
				multilang: false
		} 
		
		// En el cas de les fitxes, "nodoOrigen" varia en funcio de quina seccio 
		// s'esta� gestionant		
		paramsFicha = {
				nombre: "fitxa",
				nodoOrigen: "",
				nodoDestino: fitxes_seleccionats_elm.find(".listaOrdenable"),
				atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
				multilang: false
		} 		
		
		if (seccions_nodes_size > 0) {
			
			codi_seccions = "<ul>";
			
			$(seccions_nodes).each( function(index) {
				seccio_node = this;
				
				texteFitxes = "(0 fitxes)";
				llistaFitxes = seccio_node.listaFichas;
				
				if (llistaFitxes != null)
					texteFitxes = " (" + llistaFitxes.length  + " " + ( llistaFitxes.length > 1 ? txtFitxes : txtFitxa ) + ")";
				
				// crearem una llista per a cada enllass de seccio, que contindra�les fitxes que te assignades
				codi_seccions += "<li class=\"nodoListaSecciones\">";
                codi_seccions += '<input type="hidden" name="seccio_modificada_'+ seccio_node.id +'" value="0"/>';
                codi_seccions += "<input class=\"seccio_orden\" id=\"seccio_orden_"+ seccio_node.id +"\" name=\"seccio_orden_" + seccio_node.id + "\" type=\"hidden\" value=\"" + (index+1) + "\" />";
                codi_seccions += "<input class=\"seccio_id\" id=\"seccio_id_" + seccio_node.id + "\" name=\"seccio_id_" + seccio_node.id + "\"  type=\"hidden\" value=\"" + seccio_node.id + "\" /><a class=\"enllasGestioFitxa seccio_nombre\" href=\"#\">" + seccio_node.nom + "</a><span>" + texteFitxes + "</span>";                
				codi_seccions += "<div class=\"contenedorFichas\" style=\"margin-top: 10px; display:none;\">";
				codi_seccions += "<div class=\"listaOrdenable\">";
				codi_seccions += "<ul>";
				
				if ( llistaFitxes != null && llistaFitxes.length > 0 ) {
	
					for ( n = 0; n < llistaFitxes.length; n++ ) {
						codi_seccions += "<li>";
						codi_seccions += "<input class=\"" + paramsFicha.nombre + "_" + paramsFicha.atributos[0] + "\" type=\"hidden\" value=\"" + llistaFitxes[n].id + "\"/><span>" + llistaFitxes[n].titulo + "</span>";  
						codi_seccions += "</li>"
					}
										
				} else {
					codi_seccions += "<span>" + txtNoHiHaFitxes + "</span>";  
				}
				
				codi_seccions += "</ul>";
				codi_seccions += "</div>";
				
                codi_seccions += "<div class=\"btnGenerico\" style=\"float:none; width:145px;\" >";
                codi_seccions += "<a class=\"btn gestionaFitxes\" href=\"javascript:;\"><span><span>" + txtGestioFitxes + "</span></span></a>";
                codi_seccions += "</div>";
                
                codi_seccions += "</div>";
				codi_seccions += "</li>";
			});
			
			codi_seccions += "</ul>";
			
			txt_seccions = (seccions_nodes.size == 1) ? txtSeccio : txtSeccions;
			
			seccions_llistat_seccions = $("div.modulSeccions").find("div.seleccionats");		 
			seccions_llistat_seccions.find("p.info").html( txtHiHa + "<strong> " + seccions_nodes_size + " " + txt_seccions + ""  + "</strong>");			
			seccions_llistat_seccions.find(".listaOrdenable").html(codi_seccions);

			this.activaEnllasosFitxes();
		}
						
		copiaNodesOrigen = modul_seccions_elm.find(".listaOrdenable:first").html();
		
		modul_seccions_elm.find("a.gestionaSeccions").one("click", function() { ModulSeccions.gestiona(); } );
		modul_seccions_elm.find("a.gestionaFitxes").one("click", function() { ModulSeccions.gestionaFitxes(this); } );		
	}
	
	this.gestionaSeccio = function() {	    
		Detall.nou($("#item_id").val(), $("#item_nom_ca").val());
	}
    
    // Marcar el módulo como modificado.    
    this.modificado = function(){
        $moduloModificado.val(1);
    }
	
	this.gestiona = function() {
    
        // Guardamos el estado del campo de control de cambios.
        $moduloModificado.data( 'oldvalue', $moduloModificado.val() );
		
		// Cada vegada que canviem de llista (seccions o fitxes), hem de reconfigurar-la per a que 
		// es tingui en compte els parametres corresponents 
		this.configurar( params );
		
		lis_size = modul_seccions_elm.find(".nodoListaSecciones").size();
		
		if (lis_size > 0) {
		
			this.copiaInicial();
															
			EscriptoriSeccio.contaSeleccionats();
			
		} else {
			
			seccions_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaSeccionsSeleccionades + ".");			
			seccions_seleccionats_elm.find(".listaOrdenable").html("");
		}
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_seccions_elm.fadeIn(300);			
		});
	}
		
	this.gestionaFitxes = function(el) {
		
        $subModuloModificado = $(el).closest("li").find('input[name^="seccio_modificada_"]');        
		
		// Guarda la referència al node principal de la secció escollida
		nodeSeccio = $(el).parent().parent(); // div.contenedorFichas        
        
		// Guarda el total de fitxes d'aquesta secció per a la còpia
		nomSeccio = nodeSeccio.prev().prev().html();
		
		// Si no hi ha fitxes en aquesta secció, el seu nom el trobarem en "parent"
		if (nomSeccio == null)
			nomSeccio = nodeSeccio.parent().prev().html();			
		
		lis_size = $(nodeSeccio).find("div.listaOrdenable ul").size();
		
		// Cada vegada que canviem de llista (seccions o fitxes), hem de reconfigurar-la per a que 
		// es tinguin en compte els parametres corresponents i actualitzar el node origen de secció.
		paramsFicha.nodoOrigen = nodeSeccio;		
		this.configurar( paramsFicha );
		
		if (lis_size > 0) {			
			this.copiaInicialFitxes();			
		} else {			
			fitxes_seleccionats_elm.find("ul").remove().end().find("p.info:first").text(txtNoHiHaFitxesSeleccionades + ".");			
			fitxes_seleccionats_elm.find(".listaOrdenable").html("");			
		}
		
		EscriptoriSeccioFitxes.contaSeleccionats( nomSeccio );
		
		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_fitxes_elm.fadeIn(300);			
		});
	}	
	
	this.cerca = function(e) {
		elm = $(e.target);
		
		if (elm.is("A")) {
			
			seccio_ID = elm.find("input").val();
			
			// select cercador
			codi_select_seccions = "<select id=\"cerca_fixta_seccio\" name=\"cerca_fixta_seccio\">";
			modul_seccions_elm.find("a").each(function() {
				a_node = $(this);
				a_node_val = a_node.find("input").val();
				codi_selected = (a_node_val == seccio_ID) ? " selected=\"selected\"" : "";
				codi_select_seccions += "<option value=\"" + a_node_val + "\"" + codi_selected + ">" + a_node.text() + "</value>";
			});
			codi_select_seccions += "</select>";
			
			seccions_llistat_elm.find("label.cerca_fixta_seccio").attr("label","cerca_fixta_seccio").parents("div.element:first").find("div.control:first").html(codi_select_seccions);
			
			// animacio
			escriptori_detall_elm.fadeOut(300, function() {
				
				seccions_cercador_elm.find("input, select").attr("disabled", "disabled");
				
				escriptori_seccions_elm.fadeIn(300, function() {
					
					codi_cercant = "<p class=\"executant\">" + txtCercantItems + "</p>";
					seccions_dades_elm.html(codi_cercant);
										
				});
			});
		}
	}
	
	/**
	 * Copia les dades de la llista origen a la de destinació (mètode sobre-escrit).  
	 * En aquest cas hem de copiar només la llista de seccions sense tenir en compte les 
	 * seves fitxes (el mètode per defecte inclou ambues en fer ".find('li')" ).
	 */
	this.copiaInicial = function() {		
		var i;
		var html;		
		var _this = this;
		
		html = "<ul>";
	
		jQuery(params.nodoOrigen).find(".nodoListaSecciones").each(function() {			
			
			var li_elm = jQuery(this);			
			var item = [];
			var atributo;
					
			for( i = 0; i < params.atributos.length; i++ ){
				
				atributo = params.atributos[i];
				
				if ( atributo != "nombre" ) 						
					item[atributo] = li_elm.find( "input." + params.nombre + "_" + atributo ).val();
				else 
					item[atributo] = li_elm.find("a." + params.nombre + "_" + atributo ).html();					
			}
						
			html += _this.getHtmlItem( item, true );
		});
		
		html += "</ul>";
		
		jQuery(params.nodoDestino).html(html);
	}
	
	/**
	 * Copia les dades de la llista origen a la de destinació.  
	 * En aquest cas hem de copiar només la llista de fitxes de la secció actual sense 
	 * tenir en compte la resta d'informació.
	 */	
	this.copiaInicialFitxes = function() {
		
		var i;
		var html;
		var _this = this;
				
		html = "<ul>";
		
		jQuery( paramsFicha.nodoOrigen ).find("li").each(function(index) {			
			
			var li_elm = jQuery(this);			
			var item = [];
			var atributo;
					
			for( i = 0; i < paramsFicha.atributos.length; i++ ){
				
				atributo = paramsFicha.atributos[i];
				
                if( atributo == "orden" ){
                    item[atributo] = index+1;                    
				}else if ( atributo != "nombre" ){
					item[atributo] = li_elm.find( "input." + paramsFicha.nombre + "_" + atributo ).val();                
				}else{
					item[atributo] = li_elm.find("span").html();					
                }
			}
						
			html += _this.getHtmlItem( item, true );
		});
		
		html += "</ul>";
		
		jQuery( paramsFicha.nodoDestino ).html(html);
				
	}
	
	/**
	 * Sobre-escrivim tambe el metode de finalitzacio i de copia final perque no hem de 
	 * tenir en compte els nodes fills (fitxes) en la llista de destinacio.
	 */	
	this.copiaFinal = function() {
		
		html = "<ul>";
		
		numSecciones = 0;
		
		//Per a cada node comprovam si ha estat actualitzat i l'eliminarem o afegirem 
		//a la nova llista, des de copiaNodesOrigen, segons correspongui.		
		$(params.nodoDestino).find("li").each( function(i) {
			
			numSecciones++;
			
			idSeccioNode = $(this).find(".seccio_id").val();			
			ordreSeccio = $(this).find(".seccio_orden").val();            
                        			
			tmpSeccio = $(copiaNodesOrigen).find( "#seccio_id_" + idSeccioNode ).parent();
			
			//Si es troba el node, l'afegim a la nova llista
			//juntament amb les seves fitxes filles. Si no el trobem
			//es creara un de nou amb el contenidor de fitxes corresponent
			if ( tmpSeccio.length != 0 ) {

                tmpSeccio.att
                   
				 html += "<li class=\"nodoListaSecciones\">" +  tmpSeccio.html() + "</li>";
				/*html += "<li class=\"nodoListaSecciones\">" +
				    	"<input type=\"text\" id=\"seccio_orden_" + idSeccioNode +"\" value=\"" + ordreSeccio + "\">" + tmpSeccio.html() + 
						"</li>";*/
			} else  {				
				
				html += "<li class=\"nodoListaSecciones\">" +
                        '<input type="hidden" name="seccio_modificada_'+ idSeccioNode +'" value="1"/>' +
                        "<input class=\"seccio_id\" id=\"seccio_id_" + idSeccioNode + "\" name=\"seccio_id_" + idSeccioNode + "\"  type=\"hidden\" value=\"" + idSeccioNode + "\" /> <a class=\"enllasGestioFitxa seccio_nombre\">" + 
						$(this).find("div.seccio span").html() + 
						"</a><span> (0 fitxes)</span>" +
						"<div class=\"contenedorFichas\" style=\"margin-top: 10px; display:none;\">" +
						"<div class=\"listaOrdenable\">" +
						"<ul>" + txtNoHiHaFitxes + "</ul>" +						
		                "<div class=\"btnGenerico\" style=\"float:none; width:145px;\" >" +
		                "<a class=\"btn gestionaFitxes\" href=\"javascript:;\"><span><span>" + txtGestioFitxes + "</span></span></a>" +
		                "</div>" +
						"</div>" +
						"</li>";
			}
		});
		
		html += "</ul>";
		$(params.nodoOrigen).html(html);
                
		if ( seccions_llistat_seccions != undefined )
			seccions_llistat_seccions.find("p.info").html( txtHiHa + "<strong> " + numSecciones + " " + txt_seccions + ""  + "</strong>");
		
		this.activaEnllasosFitxes();
						
		modul_seccions_elm.find("a.gestionaSeccions").one("click", function() { ModulSeccions.gestiona(); } );
		modul_seccions_elm.find("a.gestionaFitxes").one("click", function() { ModulSeccions.gestionaFitxes(this); } );
		
        // Recalculamos el orden
        jQuery(params.nodoOrigen).find("li.nodoListaSecciones input.seccio_orden").each(function(i) {				
            jQuery(this).val(i+1);
        });			
        
		return numSecciones;
	}
	
	this.copiaFinalFitxes = function() {

		//Actualizamos la lista de fichas de las seccion actual		
		idSeccio = $(paramsFicha.nodoOrigen).parent().find("input").val();
		
		html = "<div class=\"listaOrdenable\"><ul>";
		
		numFitxes = $(paramsFicha.nodoDestino).find("li").size();
		
		$(paramsFicha.nodoDestino).find("li").each( function(index) {
			
			idFitxaNode = $(this).find(".fitxa_id").val();            

			html += "<li>";			
            html += "<input class=\"fitxa_id\" type=\"hidden\" value=\"" + idFitxaNode + "\" />";
            html += "<span>" + $(this).find(".fitxa_nombre").val() + "</span>";
            html += "<input class=\"fitxa_orden\" type=\"hidden\" value=\"" + (index+1) + "\" />";
			html += "</li>";			
			
		});

		html += "</ul></div>";
        html += "<div class=\"btnGenerico\" style=\"float:none; width:145px;\" >";
		html += "<a class=\"btn gestionaFitxes\" href=\"javascript:;\"><span><span>" + txtGestioFitxes + "</span></span></a>";
		html += "</div>";
		
		$(paramsFicha.nodoOrigen).html(html);
				
		// Actualizar el numero de fichas de la seccion despues de los cambios
		texteFitxes = " (" + numFitxes + " " + ( numFitxes == 1 ? txtFitxa : txtFitxes  ) + ")";
		$(paramsFicha.nodoOrigen).parents(".nodoListaSecciones").find("span:first").html(texteFitxes);
		
		modul_seccions_elm.find("a.gestionaSeccions").one("click", function() { ModulSeccions.gestiona(); } );
		modul_seccions_elm.find("a.gestionaFitxes").one("click", function() { ModulSeccions.gestionaFitxes(this); } );
		
		return numFitxes;
		
	}
	
	this.finalizar = function(){		
		return this.copiaFinal();		
	}	
	
	this.finalizarFitxes = function() {        
        $subModuloModificado.val(1);
		return this.copiaFinalFitxes();
	}
	
	this.activaEnllasosFitxes = function() {		
		
		//debug
		/*$(".enllasGestioFitxa").each( function() {
			$(this).bind("click", function() {					
				return that.mostraFitxes( this ); 
			});
		});*/
	}
	
	////////////***********//////////
	this.contaFitxesSeleccionades = function() {
		seleccionats_val = modul_fitxes_elm.find(".seleccionats").find("li").size();
		info_elm = modul_fitxes_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			modul_fitxes_elm.find("ul").remove();
			info_elm.text(txtNoHiHaFitxes + ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtFitxa.toLowerCase() + "</strong>.");
		} else {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtFitxes.toLowerCase() + "</strong>.");
//			if (ordenar_secciones) {
				modul_fitxes_elm.find(".listaOrdenable ul").sortable({ 
					axis: 'y', 
					cursor: 'url(../img/cursor/grabbing.cur), move',
					update: function(event, ui) {
						ModulFitxes.calculaOrden(ui, "origen");
						that.contaFitxesSeleccionades();
						Detall.modificado();
					}
				}).css({cursor:"url(../img/cursor/grab.cur), move"});
//			}
		}
		
	}
	
	this.contaSeleccionats = function() {
		seleccionats_val = modul_seccions_elm.find(".seleccionats").find("li").size();
		info_elm = modul_seccions_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			modul_seccions_elm.find("ul").remove();
			info_elm.text(txtNoHiHaSeccionsSeleccionades + ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtSeccio.toLowerCase() + "</strong>.");
		} else {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtSeccions.toLowerCase() + "</strong>.");
			if (ordenar_secciones) {
				modul_seccions_elm.find(".listaOrdenable ul").sortable({ 
					axis: 'y', 
					cursor: 'url(../img/cursor/grabbing.cur), move',
					update: function(event,ui){
						ModulSeccions.calculaOrden(ui, "origen");
						that.contaSeleccionats();
						Detall.modificado();                    
					}
				}).css({cursor:"url(../img/cursor/grab.cur), move"});
			}
		}
		
	}
	
	//Actualiza la lista de fichas cuando se carga la ficha de la sección
	this.inicializarFichas = function(listaFichas){
		modul_fitxes_elm.find(".listaOrdenable").empty();
		if (typeof listaFichas != 'undefined' && listaFichas != null && listaFichas.length > 0) {
			this.agregaItems(listaFichas, false);
		}
		
		modul_fitxes_elm.find('div.fitxa').each(function() {
			// Añadimos imagen de OK o CADUCADO.
			var caducado = $(this).find("input.fitxa_caducado").val() == "N";
			var caducat_titol_class = caducado ? " fitxaCaducat" : " fitxaNoCaducat";
			
			$(this).addClass(caducat_titol_class);
			
			var idUA = $(this).find("input.fitxa_idUA").val();
			var nomUA = $(this).find("input.fitxa_nomUA").val();
			
			// Enlace Unidad Administrativa
			var urlUA = pagUADetall  + "?itemId=" + idUA;
			$(this).append(" [<a id=\""+idUA+"\" href=\"javascript:;\" class=\"fitxa_inf\">" + printStringFromNull(nomUA, txtSinValor) + "</a>]");
			
			// Evento click Ficha informativa			
            //$(this).unbind("click").bind("click", function() {
            // dsanchez: Cambiamos para que sea clicable el span que hay dentro de la lista para acceder a la edici�n de la ficha.
            $(this).find("span.fitxa").unbind("click").bind("click", function() {                        
                                        
                //var fitxaId = $(this).find("input.fitxa_id").val();
                var fitxaId = $(this).parent().find("input.fitxa_id").val();
                
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantFitxa});
                $.ajax({
                    type: "POST",
                    url: pagFitxa,
                    data: "id=" + fitxaId,
                    dataType: "json",
                    error: function() {
                        // Missatge.cancelar();
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                        Error.llansar();
                    },
                    success: function(data) {
                        Missatge.cancelar();
                        if (data.item_id > 0) {
                        	location = pagFitxaDetall + "?itemId=" + data.item_id;
                        } else if (data.item_id == -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                        } else if (data.item_id < -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                        }
                    }
                });
            });
        });
		
		modul_fitxes_elm.find('a.fitxa_inf').each(function() {
			var urlUA = pagUADetall  + "?itemId=" + $(this).attr("id");
			
			$(this).unbind("click").bind("click", function(event) {
				event.stopPropagation();
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantUA});
				location = urlUA;                
			});
		});
		
		that.contaFitxesSeleccionades();
	}
	
	//Actualiza la lista de secciones seleccionadas cuando se carga una ficha
	this.inicializarSecciones = function(listaSeccions){
		modul_seccions_elm.find(".listaOrdenable").empty();
		if (typeof listaSeccions != 'undefined' && listaSeccions != null && listaSeccions.length > 0) {
			that.agregaItems(listaSeccions, false);
		}
		
		modul_seccions_elm.find('div.seccio').each(function() {
            $(this).unbind("click").bind("click", function() {
                var seccioId = $(this).find("input.seccio_id").val();
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantDetall});
                $.ajax({
                    type: "GET",
                    url: pagDetall,
                    data: "id=" + seccioId,
                    dataType: "json",
                    error: function() {
                        // Missatge.cancelar();
                        Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
                        Error.llansar();
                    },
                    success: function(data) {
                        Missatge.cancelar();
                        if (data.item_id > 0) {
                            Detall.pintar(data);
                        } else if (data.item_id == -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
                        } else if (data.item_id < -1){
                            Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
                        }
                    }
                });
            });
        });
		
		that.contaSeleccionats();
		
		modul_seccions_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();
			Detall.modificado();
		});
		
	}
	
	//devuelve un string con el formato seccions=n1,n2,...,nm donde nx son codigos de secciones
	this.listaSecciones = function (){
		var listaSecciones = "seccions=";
		
		$("div.modulSeccions div.seleccionats div.listaOrdenable input").each(function() {
			listaSecciones += $(this).val() + ",";										
		});

		if (listaSecciones[listaSecciones.length-1] == ","){
			listaSecciones = listaSecciones.slice(0, -1);
		}
		
		return listaSecciones;
	}
	
	this.torna = function() {        
		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_contingut_elm.fadeIn(300);
		});
	}
	
	/* Al acceder al formulario de creacion, limpia las listas de secciones, desmarca los checkboxes,
	 * marca las secciones por defecto, econder el listado y mostrar los seleccionados.
	 */
	this.nuevo = function() {
		
		seccions_seleccionades_elm = escriptori_detall_elm.find("div.modulSeccions div.seleccionats");
		seccions_seleccionades_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccions + ".");
		$("div.modulSeccions div.llistat input[type=checkbox]").attr('checked', false).removeClass(seccioDefaultClass);

		that.mostrarSeccionesSeleccionadas();
	}

	// Econder el listado y mostrar los seleccionados.
	this.mostrarSeccionesSeleccionadas = function () {
		escriptori_detall_elm.find("div.modulSeccions div.llistat").hide();
		escriptori_detall_elm.find("div.modulSeccions div.seleccionats").show();
	}
};

function CEscriptoriSeccio() {
	
	this.extend = ListadoBase;
	this.extend("", "resultatsSeccions", "", "", "", "", "", "btnBuscarSeccionesForm", "btnLimpiarSeccionesForm");

	var that = this;
	
	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo ){	
					
		// dsanchez: Componemos el item para enviar a la lista.
		var item = {
			id: itemID,
			nombre: titulo
		};
		
		// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
		if( ModulSeccions.agregaItem( item ) ){		
			this.contaSeleccionats();		
		}				
	}	
	
	this.contaSeleccionats = function() {
		
		seleccionats_val = seccions_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = seccions_seleccionats_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			
			seccions_seleccionats_elm.find("ul").remove();
			info_elm.text(txtNoHiHaSeccionsSeleccionades + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtSeccio.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtSeccions.toLowerCase() + "</strong>.");						
			if (ordenar_secciones) {
				seccions_seleccionats_elm.find(".listaOrdenable ul").sortable({ 
					axis: 'y', 
					update: function(event,ui){                    
	                    ModulSeccions.calculaOrden(ui,"origen");
						EscriptoriSeccio.contaSeleccionats();                                        
					}
				}).css({cursor:"move"});
			}
			
		}
		
		seccions_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			ModulSeccions.eliminaItem(itemLista);
			EscriptoriSeccio.contaSeleccionats();
		});
	}
	
	this.finCargaListado = function(data, opcions) {

		// total
		resultats_total = parseInt(data.total, 10);		
		
		if (resultats_total > 0) {
			
			txtT = (resultats_total > 1) ? txtSeccions : txtSeccio;
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}
			
			resultatInici = (pag_Pag * pag_Res) + 1;
			resultatFinal = ( ( pag_Pag * pag_Res) + pag_Res > resultats_total ) ? resultats_total : ( pag_Pag * pag_Res ) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "seccio") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if ( resultats_total > 1 ) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				txt_ordenacio += ", " + txt_ordenats + " " + txtSeccio + " <em>" + txtSeccio + "</em>";
			
			}
			
			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtSeccio + "</a></div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat seccions\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a class=\"seccion_" + dada_node.id + "\" href=\"javascript:;\">" + dada_node.nom + "</a>";
				codi_taula += "</div>";
				codi_taula += "</div>";
			});
			
			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Actualizamos el navegador multipágina.
			multipagina.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "EscriptoriSeccio.cambiaPagina"
			});
			
			codi_navegacio = multipagina.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaSeccions + ".</p>";
			
		}
		
		// animacio
		seccions_dades_elm.fadeOut(300, function() {
			// pintem
			seccions_dades_elm.html(codi_final).fadeIn(300, function() {
														
				// Evento lanzado al hacer click en un elemento de la lista.
				jQuery("#resultatsSeccions .llistat .tbody a").unbind("click").bind("click",function(){
					var partesItem = jQuery(this).attr("class").split("_");
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					that.agregaItem(itemID,titulo);
					});
				
				// cercador
				seccions_cercador_elm.find("input, select").removeAttr("disabled");
				
			});
		});	
		
	}	
	
	this.carregar = function(opcions) {		
							
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_seccio_elm.val(opcions.ordreTipus);
		}
		
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_seccio_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_seccio_elm.val(),10);
			
		// ordre
		ordre_Tipus = ordreTipus_seccio_elm.val();
		ordre_Camp = ordreCamp_seccio_elm.val();
			
		// variables
		dataVars = "nomSeccio=" + $("#cerca_seccions_nom").val();
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagSeccions,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				if (!a_enllas) {
					// missatge
					Missatge.llansar( { tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>" } );
					// error
					Error.llansar();
				}
				
			},
			success: function(data) {
				that.finCargaListado(data, opcions);
			}
		});	
	}
	
	this.finalizar = function(){		
            
		nombre_llistat = ModulSeccions.finalizar();
		
        // Marcamos el modulo como modificado
        ModulSeccions.modificado();
        
		// Marcamos el formulario como modificado para habilitar el boton de guardar.
		Detall.modificado();
		
		this.torna();
	}
		
	// Metodo sobreescrito
	this.anar = function(enlace_html) {
				
		num = parseInt(enlace_html,10);
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantItemsAnteriors : txtCercantItemsAnteriors;
		seccions_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			seccions_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_seccio_elm.val(num-1);								
				that.carregar({pagina: num-1});				
			});
		});
	}	
	
	this.torna = function() {
		// animacio
		escriptori_seccions_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_elm.find("a.gestionaSeccions").one( "click", function() { ModulSeccions.gestiona(); } );				
			});
			
		});
	}	
};

function CEscriptoriSeccioFitxes() {
	
	this.extend = ListadoBase;
	this.extend("", "resultatsFitxes", "", "", "", "", "", "btnBuscarFichasForm", "btnLimpiarFichasForm"); 
		
	var that = this;
	
	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo ){	
					
		// dsanchez: Componemos el item para enviar a la lista.
		var item = {
			id: itemID,
			nombre: titulo
		};
		
		// Agrega el item, y si se ha anadido correctamente (si no existia previamente) actualiza el mensaje de items seleccionados.
		if( ModulSeccions.agregaItem( item ) ) {		
			this.contaSeleccionats( $("#titolNomSeccio").html() );		
		}				
	}	
	
	this.contaSeleccionats = function( nomSeccio ) {
		
		seleccionats_val = fitxes_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = fitxes_seleccionats_elm.find("p.info:first");
		
		// Anadimos un titulo a las fichas para saber en que seccion nos encontramos
		info_elm.text("<legend id='titolSeccio'>" + txtSeccio + ": <span id='titolNomSeccio'>" + nomSeccio + "</span></legend>" );
		
		if (seleccionats_val == 0) {
			
			fitxes_seleccionats_elm.find("ul").remove();
			info_elm.html( info_elm.text() + txtNoHiHaFitxesSeleccionades + "." );
			
		} else if (seleccionats_val == 1)			
			info_elm.html( info_elm.text() + txtSeleccionat + " <strong>" + seleccionats_val + " " + txtFitxa.toLowerCase() + "</strong>.");			
		else {			
			info_elm.html( info_elm.text() + txtSeleccionades + " <strong>" + seleccionats_val + " " + txtFitxes.toLowerCase() + "</strong>.");
			fitxes_seleccionats_elm.find(".listaOrdenable ul").sortable({
				axis: 'y',
				update: function(event, ui) {
					ModulFitxes.calculaOrden(ui, "origen");
					EscriptoriSeccioFitxes.contaSeleccionats( $("#titolSeccio").find("span").html() );
				}
			}).css({cursor:"move"});
		}
		
		fitxes_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){				
			var itemLista = jQuery(this).parents("li:first");
			ModulSeccions.eliminaItem(itemLista);
			EscriptoriSeccioFitxes.contaSeleccionats( nomSeccio );
		});
	}
		
	this.finCargaListado = function(data, opcions) {

		// total
		resultats_total = parseInt(data.total, 10);		
		
		if (resultats_total > 0) {
			
			txtT = (resultats_total > 1) ? txtFitxes : txtFitxa;
			
			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			if (resultats_total % pag_Res > 0){
				ultimaPag++;
			}
			if (pag_Pag > ultimaPag) {
				pag_Pag = ultimaPag;
			}
			
			resultatInici = (pag_Pag * pag_Res) + 1;
			resultatFinal = ( ( pag_Pag * pag_Res) + pag_Res > resultats_total ) ? resultats_total : ( pag_Pag * pag_Res ) + pag_Res;
			
			// ordenacio
			ordre_T = ordre_Tipus;
			ordre_C = ordre_Camp;
			ordre_c1 = (ordre_C == "fitxa") ? " " + ordre_T : "";
			
			txt_ordenacio = "";
			
			if ( resultats_total > 1 ) {
			
				txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
				txt_ordenacio += ", " + txt_ordenats + " <em> per" + txtFitxa + "</em>";
			
			}

			codi_totals = "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
			codi_cap1 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\">" + txtFitxa + "</a></div>";
			codi_cap2 = "<div class=\"th nom" + ordre_c1 + "\" role=\"columnheader\"></a></div>";
			
			// codi taula
			codi_taula = "<div class=\"table llistat fitxes\" role=\"grid\" aria-live=\"polite\" aria-atomic=\"true\" aria-relevant=\"text additions\">";
			
			// codi cap + cuerpo
			codi_taula += "<div class=\"thead\">";
			codi_taula += "<div class=\"tr\" role=\"rowheader\">";
			codi_taula += codi_cap1;
			codi_taula += codi_cap2;
			codi_taula += "</div>";
			codi_taula += "</div>";
			codi_taula += "<div class=\"tbody\">";
			
			// codi cuerpo
			$(data.nodes).slice(resultatInici-1,resultatFinal).each(function(i) {
				dada_node = this;
				parClass = (i%2) ? " par": "";
				
				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				
				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<span class=\"id\">" + dada_node.id + "</span>";
				codi_taula += "<a class=\"ficha_" + dada_node.id + "\" href=\"javascript:;\">" + dada_node.titulo + "</a>";
				codi_taula += "</div>";
				codi_taula += "</div>";				
			});

			codi_taula += "</div>";
			codi_taula += "</div>";
			
			if($.browser.opera) {
				escriptori_contingut_elm.find("div.table:first").css("font-size",".85em");
			}
			
			// Actualizamos el navegador multipágina.
			multipaginaFitxes.init({
				total: resultats_total,
				itemsPorPagina: pag_Res,
				paginaActual: pag_Pag,
				funcionPagina: "EscriptoriSeccioFitxes.cambiaPagina"
			});
			
			codi_navegacio = multipaginaFitxes.getHtml();
			
			// codi final
			codi_final = codi_totals + codi_taula + codi_navegacio;
		
		} else {
			
			// no hi ha items
			codi_final = "<p class=\"noItems\">" + txtNoHiHaFitxes + ".</p>";
			
		}
		
		// animacio
		fitxes_dades_elm.fadeOut(300, function() {
			// pintem
			fitxes_dades_elm.html(codi_final).fadeIn(300, function() {
														
				// Evento lanzado al hacer click en un elemento de la lista.
				jQuery("#resultatsFitxes .llistat .tbody a").unbind("click").bind("click",function(){
					var partesItem = jQuery(this).attr("class").split("_");
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					that.agregaItem(itemID,titulo);
					});
				
				// cercador
				fitxes_cercador_elm.find("input, select").removeAttr("disabled");
				
			});
		});	
		
	}	
	
	this.carregar = function(opcions) {		
		
		// ordreTipus
		if (typeof opcions.ordreTipus != "undefined") {
			ordreTipus_seccio_elm.val(opcions.ordreTipus);
		}
		
		// ordreCamp
		if (typeof opcions.ordreCamp != "undefined") {
			ordreCamp_fitxa_elm.val(opcions.ordreCamp);
		}
			
		// paginacio
		pag_Pag = (opcions.ajaxPag) ? parseInt(opcions.ajaxPag,10) : parseInt(pagPagina_fitxa_elm.val(),10);
			
		// ordre
		ordre_Tipus = ordreTipus_fitxa_elm.val();
		ordre_Camp = ordreCamp_fitxa_elm.val();
			
		// variables
		dataVars = "texteFitxa=" + $("#cerca_fitxes_nom").val();
		dataVars += "&codiFitxa=" + $("#cerca_fitxes_codi").val();
		
		// ajax
		$.ajax({
			type: "POST",
			url: pagSeccionsFitxes,
			data: dataVars,
			dataType: "json",
			error: function() {
				
				if (!a_enllas) {
					// missatge
					Missatge.llansar( { tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>" } );
					// error
					Error.llansar();
				}
				
			},
			success: function(data) {
				that.finCargaListado(data, opcions);
			}
		});	
	}	
	
	this.finalizar = function(){		
            	
		nombre_llistat = ModulSeccions.finalizarFitxes();
        
        // Marcamos el modulo como modificado.
        ModulSeccions.modificado();
		
		// Marcamos el formulario como modificado para habilitar el boton de guardar.
		Detall.modificado();
		
		this.torna();
	}	
	
	// Metodo sobreescrito
	this.anar = function(enlace_html) {
				
		num = parseInt(enlace_html,10);
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantItemsAnteriors : txtCercantItemsAnteriors;
		fitxes_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			fitxes_dades_elm.html(codi_anar).fadeIn(300, function() {
				pagPagina_fitxa_elm.val(num-1);								
				that.carregar({pagina: num-1});				
			});
		});
	}	
	
	this.torna = function() {
		// animacio
		escriptori_fitxes_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_elm.find("a.gestionaSeccions").one( "click", function() { ModulSeccions.gestiona(); } );				
				modul_seccions_elm.find("a.gestionaFitxes").one("click", function() { ModulSeccions.gestionaFitxes(this); } );				
				
			});
			
		});
	}
};