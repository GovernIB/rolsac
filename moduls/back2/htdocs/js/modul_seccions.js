//MODUL SECCIONS
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
	jQuery(".btnVolverDetalleFichas").bind("click", function() { EscriptoriSeccioFitxes.torna(); } );
	jQuery(".btnFinalizarFichas").bind("click",function() { EscriptoriSeccioFitxes.finalizar(); } );
	jQuery('.submenuUA .hijas').click(function() { $('#escriptori_fitxes').hide(); });

	jQuery("#escriptori_fitxes #btnBuscarSeccionesForm").bind("click", function() { 

		var idSeccion = $("#idSeccion").val();
		var nombreFicha = $("#cerca_fitxes_nom").val();
		var idFicha = $("#cerca_fitxes_codi").val();

		EscriptoriSeccioFitxes.buscarFicha(idSeccion, nombreFicha, idFicha);

	});

});

function CModulSeccio() {
	
	// Activa mensajes de debug.
	var debug = false;

	var that = this;
	var params;
	var paramsFicha;
	var seccions_llistat_seccions;

	//Necessitem una copia de la llista original de seccions-fitxes. Es necessari perque l'usuari podria
	//esborrar una seccio amb les seves fitxes i tornar a afegir-la des de els resultats de la cerca de 
	//seccions si troba que s'ha equivocat.
	var copiaNodesOrigen;

	if ( modul_seccions_elm.size() != 1 )
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

	this.mostraFitxes = function(e) {
		
		if (debug)
			console.log("Entrando en CModulSeccio.mostraFitxes");
        txtTituloCabeceraFichas = e.innerText;
		//Mostrar panel de fichas de la seccion actual

		$('#escriptori_fitxes').css('display', 'inline-block');
		fitxes_seleccionats_elm = escriptori_fitxes_elm.find("div.escriptori_items_seleccionats:first");

		// Mostrar panel de fichas de la seccion actual.
		idSeccion = $(e).prev().val();
		ulFichas = fitxes_seleccionats_elm.find(".listaOrdenable");

		$("#idSeccion").val(idSeccion);
		EscriptoriSeccioFitxes.listarFichasAsignadas(idSeccion, 0, 10);

		ModulSeccions.gestionaFitxes(e);
		
		if (debug)
			console.log("Saliendo de CModulSeccio.mostraFitxes");

		return false;

	};

	this.iniciarFichas = function()  {
		
		if (debug)
			console.log("Entrando en CModulSeccio.iniciarFichas");
		
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "fitxa",
			nodoOrigen: modul_fitxes_elm.find(".listaOrdenable"),
			nodoDestino: modul_fitxes_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "orden", "caducado", "idUA", "nomUA"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.iniciarFichas");
		
	};

	this.iniciarSeccion = function()  {
		
		if (debug)
			console.log("Entrando en CModulSeccio.iniciarSeccion");
		
		// Configuramos la lista ordenable.
		var _atributos = ordenar_secciones ? ["id", "nombre", "orden"] : ["id", "nombre"];  // Campos que queremos que aparezcan en las listas.   
		this.configurar({
			nombre: "seccio",
			nodoOrigen: modul_seccions_elm.find(".listaOrdenable"),
			nodoDestino: modul_seccions_elm.find(".listaOrdenable"),
			atributos: _atributos,
			multilang: false
		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.iniciarSeccion");
		
	};

	this.iniciar = function(dades) {
		
		if (debug)
			console.log("Entrando en CModulSeccio.iniciar");

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

		// enllassos
		modul_seccions_elm.bind("click", CModulSeccio.cerca);

		var _atributos = ordenar_secciones ? ["id", "nombre", "orden"] : ["id", "nombre"];  // Campos que queremos que aparezcan en las listas.
		params = {
				nombre: "seccio",
				nodoOrigen: modul_seccions_elm.find(".listaOrdenable:first"),
				nodoDestino: seccions_seleccionats_elm.find(".listaOrdenable"),
				atributos: _atributos,
				multilang: false
		};

		// En el cas de les fitxes, "nodoOrigen" varia en funcio de quina seccio 
		// s'esta� gestionant		
		paramsFicha = {
				nombre: "fitxa",
				nodoOrigen: "",
				nodoDestino: fitxes_seleccionats_elm.find(".listaOrdenable"),
				atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
				multilang: false
		};	

		if (seccions_nodes_size > 0) {

			codi_seccions = "<ul>";

			$(seccions_nodes).each( function(index) {

				seccio_node = this;

				texteFitxes = "(0 fitxes)";

				numFitxes = seccio_node.numFichas;

				if (numFitxes != null)
					texteFitxes = " (" + numFitxes  + " " + ( numFitxes > 1 ? txtFitxes : txtFitxa ) + ")";

				// crearem una llista per a cada enllass de seccio, que contindra�les fitxes que te assignades
				codi_seccions += "<li class=\"nodoListaSecciones\">";
				codi_seccions += '<input type="hidden" name="seccio_modificada_'+ seccio_node.id +'" value="0"/>';
				codi_seccions += "<input class=\"seccio_orden\" id=\"seccio_orden_"+ seccio_node.id +"\" name=\"seccio_orden_" + seccio_node.id + "\" type=\"hidden\" value=\"" + (index+1) + "\" />";
				codi_seccions += "<input class=\"seccio_id\" id=\"seccio_id_" + seccio_node.id + "\" name=\"seccio_id_" + seccio_node.id + "\"  type=\"hidden\" value=\"" + seccio_node.id + "\" /><a class=\"enllasGestioFitxa seccio_nombre\" href='javascript:;'>" + seccio_node.nom + "</a><span>" + texteFitxes + "</span>";

				codi_seccions += "<div class=\"contenedorFichas\" style=\"margin-top: 10px; display:none;\">";
				codi_seccions += "<div class=\"listaOrdenable\">";
				codi_seccions += "<ul>";

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

		}

		copiaNodesOrigen = modul_seccions_elm.find(".listaOrdenable:first").html();

		modul_seccions_elm.find("a.gestionaSeccions").one("click", function() { ModulSeccions.gestiona(); } );
		modul_seccions_elm.find("a.enllasGestioFitxa").click( function() { that.mostraFitxes(this); } );
		
		if (debug)
			console.log("Saliendo de CModulSeccio.iniciar");

	};

	this.gestionaSeccio = function() {	
		
		if (debug)
			console.log("Entrando en CModulSeccio.gestionaSeccio");
		
		Detall.nou($("#item_id").val(), $("#item_nom_ca").val());
		
		if (debug)
			console.log("Saliendo de CModulSeccio.gestionaSeccio");
		
	};

	//	Marcar el módulo como modificado.    
	this.modificado = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.modificado");
		
		$moduloModificado.val(1);
		
		if (debug)
			console.log("Saliendo de CModulSeccio.modificado");
		
	};

	this.gestiona = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.gestiona");

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
		
		if (debug)
			console.log("Saliendo de CModulSeccio.gestiona");
		
	};

	this.gestionaFitxes = function(el) {
		
		if (debug)
			console.log("Entrando en CModulSeccio.gestionaFitxes");

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

		// animacio
		escriptori_detall_elm.fadeOut(300, function() {			
			escriptori_fitxes_elm.fadeIn(300);			
		});

		//Asociamos la acción de limpiar el formulario de búsqueda al botón borrar
		$("#cercador #btnLimpiarSeccionesForm").click(function() {

			EscriptoriSeccioFitxes.limpiar();
			$("#seleccion-fichas").empty();

		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.gestionaFitxes");
		
	};

	this.cerca = function(e) {
		
		if (debug)
			console.log("Entrando en CModulSeccio.cerca");
		
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
		
		if (debug)
			console.log("Saliendo de CModulSeccio.cerca");
		
	};

	/**
	 * Copia les dades de la llista origen a la de destinació (mètode sobre-escrit).  
	 * En aquest cas hem de copiar només la llista de seccions sense tenir en compte les 
	 * seves fitxes (el mètode per defecte inclou ambues en fer ".find('li')" ).
	 */
	this.copiaInicial = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.copiaInicial");
		
		var i;
		var html;		
		var _this = this;

		html = "<ul>";

		jQuery(params.nodoOrigen).find(".nodoListaSecciones").each(function() {			

			var li_elm = jQuery(this);			
			var item = [];
			var atributo;

			for ( i = 0; i < params.atributos.length; i++ ) {

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
		
		if (debug)
			console.log("Saliendo de CModulSeccio.copiaInicial");
		
	};

	/**
	 * Copia les dades de la llista origen a la de destinació.  
	 * En aquest cas hem de copiar només la llista de fitxes de la secció actual sense 
	 * tenir en compte la resta d'informació.
	 */	
	this.copiaInicialFitxes = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.copiaInicialFitxes");

		var i;
		var html;
		var _this = this;

		html = "<ul>";

		jQuery( paramsFicha.nodoOrigen ).find("li").each(function(index) {			

			var li_elm = jQuery(this);			
			var item = [];
			var atributo;

			for ( i = 0; i < paramsFicha.atributos.length; i++ ) {

				atributo = paramsFicha.atributos[i];

				if ( atributo == "orden" ) {
					item[atributo] = index + 1;                    
				} else if ( atributo != "nombre" ) {
					item[atributo] = li_elm.find( "input." + paramsFicha.nombre + "_" + atributo ).val();                
				} else {
					item[atributo] = li_elm.find("span").html();					
				}
			}

			html += _this.getHtmlItem( item, true );
			
		});

		html += "</ul>";

		jQuery( paramsFicha.nodoDestino ).html(html);
		
		if (debug)
			console.log("Saliendo de CModulSeccio.copiaInicialFitxes");

	};

	/**
	 * Sobre-escrivim tambe el metode de finalitzacio i de copia final perque no hem de 
	 * tenir en compte els nodes fills (fitxes) en la llista de destinacio.
	 */	
	this.copiaFinal = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.copiaFinal");

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

				tmpSeccio.att;
				html += "<li class=\"nodoListaSecciones\">" +  tmpSeccio.html() + "</li>";

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
			seccions_llistat_seccions.find("p.info").html( txtHiHa + "<strong> " + numSecciones + " " + txt_seccions + ""  + "</strong>" );

		this.activaEnllasosFitxes();

		modul_seccions_elm.find("a.gestionaSeccions").one("click", function() { ModulSeccions.gestiona(); });
		modul_seccions_elm.find("a.enllasGestioFitxa").one("click", function() { ModulSeccions.gestionaFitxes(this); });

		// Recalculamos el orden
		jQuery(params.nodoOrigen).find("li.nodoListaSecciones input.seccio_orden").each(function(i) {				
			jQuery(this).val(i+1);
		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.copiaFinal");

		return numSecciones;
		
	};

	this.copiaFinalFitxes = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.copiaFinalFitxes");

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
		modul_seccions_elm.find("a.enllasGestioFitxa").one("click", function() { ModulSeccions.gestionaFitxes(this); } );
		
		if (debug)
			console.log("Saliendo de CModulSeccio.copiaFinalFitxes");

		return numFitxes;

	};

	this.finalizar = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.finalizar");
		
		if (debug)
			console.log("Saliendo de CModulSeccio.finalizar");
		
		return this.copiaFinal();
		
	};

	this.finalizarFitxes = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.finalizarFitxes");

		$subModuloModificado.val(1);
		
		//var numFichas = this.copiaFinalFitxes();
		var numFichas = $("#resultatsFitxes").data("fichas").length;//$("#escriptori_fitxes .tbody > div.tr").length;
		var idSeccion = $("#idSeccion").val();

		// Petición AJAX para guardar estado fichas-seccion-UA.
		this.guardaEstadoFichasSeccionUA();
		
		if (debug)
			console.log("Saliendo de CModulSeccio.finalizarFitxes");

		return { "numFichas" : numFichas, "idSeccion" : idSeccion};

	};

	this.guardaEstadoFichasSeccionUA = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.guardaEstadoFichasSeccionUA");

		//Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});

		// Construimos variable con los datos (idUA, idSeccion, idFicha1, idFicha2, etc.
		var idUA = $('#item_id').val();
		var idSeccion = $("#idSeccion").val();
		var cantidadFichas = $("#resultatsFitxes").data("fichas").length;
		var listaTotal = $("#resultatsFitxes").data("fichas");
		//Comprobamos si se ha borrado alguna l�nea
		var listaPantalla = $('#escriptori_fitxes .tbody > div.tr').length;
		
		
		 
		var listaFichas = '[';
		var contadorFichas = 0;
		
		

		//$('#escriptori_fitxes .tbody > div.tr').each(function() {
		for (var i=0; i<listaTotal.length; i++) {
			
			

			//var id = $(this).find(".id").val();
		    var id =  listaTotal[i].id;
			var orden = listaTotal[i].ordre;//$(this).find("select option:selected").val();
			contadorFichas++;

			listaFichas += '{"id": ' + id + ', "ordre":' + orden + '}';

			if ( contadorFichas != cantidadFichas )
				listaFichas += ",";

		};

		listaFichas += "]";

		$.ajax({
			type: "POST",
			url: pagGuardarFitxesUASeccio,
			data: "idUA=" + idUA + "&idSeccion=" + idSeccion + "&listaFichas=" + listaFichas.toString(),
			dataType: "json",
			error: function() {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				Error.llansar();
			},
			success: function(data) {

				//Missatge.cancelar();

				// Comprobar valor de retorno:
				if (data.id == -1) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});

				 
    			} else if ( data.id == -3 ) {
    				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.error});
    			}
    				else if ( data.id < -1 ) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});

				} 

			}

		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.guardaEstadoFichasSeccionUA");

	};

	this.activaEnllasosFitxes = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.activaEnllasosFitxes");

		$(".enllasGestioFitxa").each( function() {

			$(this).bind("click", function() {	

				return ModulSeccions.gestionaFitxes(this);

			});

		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.activaEnllasosFitxes");

	};

	this.contaFitxesSeleccionades = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.contaFitxesSeleccionades");
		
		seleccionats_val = modul_fitxes_elm.find(".seleccionats").find("li").size();
		info_elm = modul_fitxes_elm.find("p.info:first");

		if (seleccionats_val == 0) {
			
			modul_fitxes_elm.find("ul").remove();
			info_elm.text(txtNoHiHaFitxes + ".");
			
		} else if (seleccionats_val == 1) {
			
			info_elm.html(txtSeleccionat + " <strong>" + seleccionats_val + " " + txtFitxa.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtFitxes.toLowerCase() + "</strong>.");
			modul_fitxes_elm.find(".listaOrdenable ul").sortable({ 
				axis: 'y', 
				cursor: 'url(../img/cursor/grabbing.cur), move',
				update: function(event, ui) {
					ModulFitxes.calculaOrden(ui, "origen");
					that.contaFitxesSeleccionades();
					that.habilitarBotonGuardarFichas();
				}
			}).css({cursor:"url(../img/cursor/grab.cur), move"});

		}
		
		if (debug)
			console.log("Saliendo de CModulSeccio.contaFitxesSeleccionades");

	};

	this.contaSeleccionats = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.contaSeleccionats");
		
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
					update: function(event,ui) {
						ModulSeccions.calculaOrden(ui, "origen");
						that.contaSeleccionats();
						that.habilitarBotonGuardarSecciones();
					}
				}).css({cursor:"url(../img/cursor/grab.cur), move"});
			}
			
		}
		
		if (debug)
			console.log("Saliendo de CModulSeccio.contaSeleccionats");

	};

	//	Actualiza la lista de fichas cuando se carga la ficha de la sección
	this.inicializarFichas = function(listaFichas) {
		
		if (debug)
			console.log("Entrando en CModulSeccio.inicializarFichas");
		
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
		
		if (debug)
			console.log("Saliendo de CModulSeccio.inicializarFichas");
		
	};

	//	Actualiza la lista de secciones seleccionadas cuando se carga una ficha
	this.inicializarSecciones = function(listaSeccions) {
		
		if (debug)
			console.log("Entrando en CModulSeccio.inicializarSecciones");
		
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
							Detall.cargarModulos();
							
						} else if (data.item_id == -1) {
							
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});
							
						} else if (data.item_id < -1) {
							
							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});
							
						}
						
					}
				});
				
			});
			
		});

		that.contaSeleccionats();

		modul_seccions_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {

			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();

		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.inicializarSecciones");

	};

	//	devuelve un string con el formato seccions=n1,n2,...,nm donde nx son codigos de secciones
	this.listaSecciones = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.listaSecciones");
		
		var listaSecciones = "seccions=";

		$("div.modulSeccions div.seleccionats div.listaOrdenable input").each(function() {
			listaSecciones += $(this).val() + ",";										
		});

		if (listaSecciones[listaSecciones.length-1] == ",") {
			listaSecciones = listaSecciones.slice(0, -1);
		}
		
		if (debug)
			console.log("Saliendo de CModulSeccio.listaSecciones");

		return listaSecciones;
		
	};

	this.torna = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.torna");
		
		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_contingut_elm.fadeIn(300);
		});
		
		if (debug)
			console.log("Saliendo de CModulSeccio.torna");
		
	};

	/* Al acceder al formulario de creacion, limpia las listas de secciones, desmarca los checkboxes,
	 * marca las secciones por defecto, econder el listado y mostrar los seleccionados.
	 */
	this.nuevo = function() {
		
		if (debug)
			console.log("Entrando en CModulSeccio.nuevo");

		seccions_seleccionades_elm = escriptori_detall_elm.find("div.modulSeccions div.seleccionats");
		seccions_seleccionades_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaSeccions + ".");
		$("div.modulSeccions div.llistat input[type=checkbox]").attr('checked', false).removeClass(seccioDefaultClass);

		that.mostrarSeccionesSeleccionadas();
		
		if (debug)
			console.log("Saliendo de CModulSeccio.nuevo");
		
	};

	//	Econder el listado y mostrar los seleccionados.
	this.mostrarSeccionesSeleccionadas = function () {
		
		if (debug)
			console.log("Entrando en CModulSeccio.mostrarSeccionesSeleccionadas");
		
		escriptori_detall_elm.find("div.modulSeccions div.llistat").hide();
		escriptori_detall_elm.find("div.modulSeccions div.seleccionats").show();
		
		if (debug)
			console.log("Saliendo de CModulSeccio.mostrarSeccionesSeleccionadas");
		
	};
	
	this.botonGuardarSecciones = jQuery("#btnGuardar_seccions");
	
	this.existeBotonGuardarSecciones = function() {
		return (this.botonGuardarSecciones.length > 0);
	};
	
	this.habilitarBotonGuardarSecciones = function() {
		if (this.existeBotonGuardarSecciones()) {
			this.botonGuardarSecciones.show(500);
	        Detall.modificado();
		}
    };
    
    this.deshabilitarBotonGuardarSecciones = function() {
    	if (this.existeBotonGuardarSecciones()) {
    		this.botonGuardarSecciones.css("display", "none");
    	}
    };
    
    this.botonGuardarFichas = jQuery("#btnGuardar_fitxes");
	
	this.existeBotonGuardarFichas = function() {
		return (this.botonGuardarFichas.length > 0);
	};
	
	this.habilitarBotonGuardarFichas = function() {
		if (this.existeBotonGuardarFichas()) {
			this.botonGuardarFichas.show(500);
	        Detall.modificado();
		}
    };
    
    this.deshabilitarBotonGuardarFichas = function() {
    	if (this.existeBotonGuardarFichas()) {
    		this.botonGuardarFichas.css("display", "none");
    	}
    };
	
	this._eliminaItem = this.eliminaItem;
	
	this.eliminaItem = function( item ) {

		that._eliminaItem(item);
		
		// Si hay botón de guardado, hay que marcar la página como modificada.
		// Si no, el guardado se hace vía botón "Finalizar".
		if (this.existeBotonGuardarSecciones()) {
			Detall.modificado(true);
			this.habilitarBotonGuardarSecciones();
		} else if (this.existeBotonGuardarFichas()) {
			Detall.modificado(true);
			this.habilitarBotonGuardarFichas();
		}
		
	};
	
	this._agregaItem = this.agregaItem;
	
	this.agregaItem = function( item ) {
		
		that._agregaItem(item);
		
		// Si hay botón de guardado, hay que marcar la página como modificada.
		// Si no, el guardado se hace vía botón "Finalizar".
		if (this.existeBotonGuardarSecciones()) {
			Detall.modificado(true);
			this.habilitarBotonGuardarSecciones();
		} else if (this.existeBotonGuardarFichas()) {
			Detall.modificado(true);
			this.habilitarBotonGuardarFichas();
		}
		
	};
	
};

function CEscriptoriSeccio() {
	
	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListadoBase;
	this.extend("", "resultatsSeccions", "", "", "", "", "", "btnBuscarSeccionesForm", "btnLimpiarSeccionesForm");

	var that = this;

	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo, idMainItem, idRelatedItem ) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.agregaItem");

		// dsanchez: Componemos el item para enviar a la lista.
		var item = {
			id: itemID,
			nombre: titulo,
			idMainItem : idMainItem,
			idRelatedItem : idRelatedItem
		};

		// Agrega el item, y si se ha añadido correctamente (si no existía previamente) actualiza el mensaje de items seleccionados.
		if ( ModulSeccions.agregaItem(item) ) {		
			this.contaSeleccionats();		
		}
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.agregaItem");
		
	};

	this.contaSeleccionats = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.contaSeleccionats");

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
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.contaSeleccionats");
		
	};

	this.finCargaListado = function(data, opcions) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.finCargaListado");

		// total
		resultats_total = parseInt(data.total, 10);		

		if (resultats_total > 0) {

			txtT = (resultats_total > 1) ? txtSeccions : txtSeccio;

			ultimaPag = Math.floor(resultats_total / pag_Res) - 1;
			
			if (resultats_total % pag_Res > 0) {
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
				
				parClass = (i % 2) ? " par": "";

				codi_taula += "<div class=\"tr" + parClass + "\" role=\"row\">";
				codi_taula += "<div class=\"td nom\" role=\"gridcell\">";
				codi_taula += "<input type=\"hidden\" value=\"" + dada_node.id + "\" class=\"id\" />";
				codi_taula += "<a class=\"seccion_" + dada_node.id + "\" href=\"javascript:;\">" + dada_node.nom + "</a>";
				codi_taula += "</div>";
				codi_taula += "</div>";
				
			});

			codi_taula += "</div>";
			codi_taula += "</div>";

			if ($.browser.opera) {
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
				jQuery("#resultatsSeccions .llistat .tbody a").unbind("click").bind("click", function() {
					
					var partesItem = jQuery(this).attr("class").split("_");
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					var idMainItem = jQuery('#item_id').val();
					var idRelatedItem = itemID;
					
					that.agregaItem(itemID, titulo, idMainItem, idRelatedItem);
					
				});

				// cercador
				seccions_cercador_elm.find("input, select").removeAttr("disabled");

			});
			
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.finCargaListado");

	};

	this.carregar = function(opcions) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.carregar");

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
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.carregar");
		
	};

	this.finalizar = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.finalizar");

		nombre_llistat = ModulSeccions.finalizar();

		// Marcamos el modulo como modificado
		ModulSeccions.modificado();

		this.torna();
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.finalizar");
		
	};

	// Metodo sobreescrito
	this.anar = function(enlace_html) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.anar");

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
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.anar");
		
	};

	this.torna = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccio.torna");
		
		// animacio
		escriptori_seccions_elm.fadeOut(300, function() {		
			escriptori_detall_elm.fadeIn(300, function() {
				// activar
				modul_seccions_elm.find("a.enllasGestioFitxa").one("click", function() { ModulSeccions.gestionaFitxes(this); } );
			});
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccio.torna");
		
	};
	
};

function CEscriptoriSeccioFitxes() {
	
	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListadoBase;
	this.extend("", "resultatsFitxes", "", "", "", "", "", "btnBuscarFichasForm", "btnLimpiarFichasForm"); 

	var that = this;

	/**
	 * Agrega un item a la lista.
	 */
	this.agregaItem = function( itemID, titulo ) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.agregaItem");

		// dsanchez: Componemos el item para enviar a la lista.
		var item = {
			id: itemID,
			nombre: titulo
		};

		// Agrega el item, y si se ha anadido correctamente (si no existia previamente) actualiza el mensaje de items seleccionados.
		if ( ModulSeccions.agregaItem( item ) ) {		
			this.contaSeleccionats( $("#titolNomSeccio").html() );		
		}
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.agregaItem");
		
	};

	this.contaSeleccionats = function( nomSeccio ) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.contaSeleccionats");

		seleccionats_val = fitxes_seleccionats_elm.find(".seleccionat").find("li").size();
		info_elm = fitxes_seleccionats_elm.find("p.info:first");

		// Anadimos un titulo a las fichas para saber en que seccion nos encontramos
		info_elm.text("<legend id='titolSeccio'>" + txtSeccio + ": <span id='titolNomSeccio'>" + nomSeccio + "</span></legend>" );

		if (seleccionats_val == 0) {

			fitxes_seleccionats_elm.find("ul").remove();
			info_elm.html( info_elm.text() + txtNoHiHaFitxesSeleccionades + "." );

		} else if (seleccionats_val == 1) {
			
			info_elm.html( info_elm.text() + txtSeleccionat + " <strong>" + seleccionats_val + " " + txtFitxa.toLowerCase() + "</strong>.");
			
		} else {
			
			info_elm.html( info_elm.text() + txtSeleccionades + " <strong>" + seleccionats_val + " " + txtFitxes.toLowerCase() + "</strong>.");
			
			fitxes_seleccionats_elm.find(".listaOrdenable ul").sortable({
				axis: 'y',
				update: function(event, ui) {
					ModulFitxes.calculaOrden(ui, "origen");
					EscriptoriSeccioFitxes.contaSeleccionats( $("#titolSeccio").find("span").html() );
				}
			}).css({cursor:"move"});
			
		}

		fitxes_seleccionats_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {
			
			var itemLista = jQuery(this).parents("li:first");
			
			// variables
			dataVars = "idFitxa=" + jQuery(itemLista).find("input.fitxa_id:first").val();
			
			// ajax
			$.ajax({
				type: "POST",
				url: fitxaBorrable,
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
					if (data.num > 1) {
						ModulSeccions.eliminaItem(itemLista);
						EscriptoriSeccioFitxes.contaSeleccionats( nomSeccio );
					} else {
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorNumFicha, text: "<p></p>"});
					}
				}
			});
			
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.contaSeleccionats");
		
	};

	this.finCargaListado = function(data, opcions) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.finCargaListado");

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
				parClass = (i % 2) ? " par": "";

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

			if ($.browser.opera) {
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
			codi_final = "<p class=\"noItems\">" + txtNoHiHaFitxes + "</p>";

		}

		// animacio
		fitxes_dades_elm.fadeOut(300, function() {
			
			// pintem
			fitxes_dades_elm.html(codi_final).fadeIn(300, function() {

				// Evento lanzado al hacer click en un elemento de la lista.
				jQuery("#resultatsFitxes .llistat .tbody a").unbind("click").bind("click",function() {
					var partesItem = jQuery(this).attr("class").split("_");
					var itemID = partesItem[1];
					var titulo = jQuery(this).html();
					that.agregaItem(itemID,titulo);
				});

				// cercador
				fitxes_cercador_elm.find("input, select").removeAttr("disabled");

			});
			
		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.finCargaListado");

	};

	this.carregar = function(opcions) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.carregar");

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
		//this.buscarFicha();
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
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.carregar");
		
	};

	this.finalizar = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.finalizar");

		//nombre_llistat = ModulSeccions.finalizarFitxes();
		var data = ModulSeccions.finalizarFitxes();
		var seccion = "#seccio_id_" + data.idSeccion;
		var texto;

		//Actualiza el número de fichas en la UA.
		switch (data.numFichas) {
		
			case 1: 
				texto = " ($ fitxa)".replace("$", data.numFichas);
				break;
	
			default:
				texto = " ($ fitxes)".replace("$", data.numFichas);
			
		}

		$(seccion).parent().find("span").html(texto);

		// Marcamos el modulo como modificado.
		ModulSeccions.modificado();

		this.torna();
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.finalizar");
		
	};

	// Metodo sobreescrito
	this.anar = function(enlace_html) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.anar");

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
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.anar");
		
	};

	this.torna = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.torna");
		
		// animacio
		escriptori_fitxes_elm.fadeOut(300, function() {			
			escriptori_detall_elm.fadeIn(300, function() {

				// activar
				modul_seccions_elm.find("a.gestionaSeccions").one("click", function() { ModulSeccions.gestiona(); } );
				modul_seccions_elm.find("a.enllasGestioFitxa").one("click", function() { ModulSeccions.gestionaFitxes(this); } );

			});

		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.torna");

	};

	this.pintarListadoAsignadas = function(data, pagPag, pagRes, totalRegistros) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.pintarListadoAsignadas");

		resultats_total = parseInt(totalRegistros, 10);

		txtT = ( resultats_total > 1 ) ? txtFitxes : txtFitxa;

		ultimaPag = Math.floor(resultats_total / pagRes) - 1;
		if ( resultats_total % pagRes > 0 )
			ultimaPag++;

		if ( pagPag > ultimaPag ) 
			pagPag = ultimaPag;

		resultatInici = ( pagPag * pagRes ) + 1;
		resultatFinal = ( ( pagPag * pag_Res ) + pagRes > resultats_total ) ? resultats_total : ( pagPag * pagRes ) + pagRes;

		// ordenacio
		ordre_T = ordre_Tipus;
		ordre_C = ordre_Camp;
		ordre_c1 = (ordre_C == "seccio") ? " " + ordre_T : "";

		txt_ordenacio = "";

		if ( resultats_total > 1 ) {

			txt_ordenats = (ordre_T == "ASC") ? txtOrdenats + " <em>" + txtAscendentment + "</em>" : txtOrdenats + " <em>" + txtDescendentment + "</em>";
			txt_ordenacio += ", " + txt_ordenats + "</em>";

		}

		// Actualizamos el navegador multipágina.
		multipagina.init( {total: resultats_total, itemsPorPagina: pagRes, paginaActual: pagPag, funcionPagina: "EscriptoriSeccioFitxes.cambiarPagina"} );

		var listado = "";
		var numFitxes = data.length;

		/* Pintado cabeceras listado*/
		listado += "<input type='hidden' value='" + totalRegistros + "' id='totalRegistros'>";
		listado += "<p class=\"info\">" + txtTrobades + " <strong>" + resultats_total + " " + txtT.toLowerCase() + "</strong>" + ". " + txtMostrem + resultatInici + txtMostremAl + resultatFinal + txt_ordenacio + ".</p>";
		listado += '<p class="info">' + txtSeleccionados + ' <strong> ' + numFitxes + ' </strong> ' + txtFichas + '</p> ';
		listado += '<div class="table llistat" role="grid" aria-live="polite" aria-atomic="true" aria-relevant="text additions">';
		listado += '	<div class="thead">';
		listado += '		<div class="tr" role="rowheader">';
		listado += '			<div class="th nom" role="columnheader">' + txtTituloCabeceraFichas + '</div>';
		listado += '			<div class="th enllas" role="columnheader">' + txtOrdenCabeceraFichas + '</div>';
		listado += '		</div>';
		listado += '	</div>';
		listado += '	<div class="tbody">';

		/* Pintado de las filas */		
		paramsFicha = {
				nombre: "fitxa",
				nodoOrigen: "",
				nodoDestino: fitxes_seleccionats_elm.find(".listaOrdenable"),
				atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
				multilang: false
		};

		if ( data != null && numFitxes > 0 ) {

			longitud = data.length - 1;

			for ( n = 0 ; n <= longitud ; n++ ) {

				var idFicha = data[n].id;
				var filaPar = ( n % 2 == 0 ? "" : "par" );
				var orden = data[n].ordre;

				listado += "<div class='tr " + filaPar + "' role='row'>";
				listado += "	<div class='td nom fitxa' role='gridcell'>";
				listado	+= "		<input type='hidden' value='" + idFicha + "' class='id'>";
				listado += "		<a href='javascript:;' class='nom'>" + data[n].titulo + "</a>";
				listado += "	</div>";
				listado += "	<div class='td enllas' role='gridcell'>";
				listado += this.pintarSeleccionableOrdenFichas(totalRegistros, data[n].ordre);
				listado += "		<span></span>";
				listado += "	</div>";
				listado += "</div>";				

			}

		}

		listado += '	</div>';
		listado += '</div>';
		listado += multipagina.getHtml();

		$("#resultatsFitxes .dades").html(listado);

		this.asignarEventoSeleccionable(pagPag);
		this.quitarFicha();
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.pintarListadoAsignadas");

	};

	this.quitarFicha = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.quitarFicha");

		var cantidad = $("#resultatsFitxes .dades > div.table > div.tbody > div.tr").length;

		$("#resultatsFitxes .dades .tbody > .tr > div.enllas > span").each(function() {

			var element = $(this).parents('div.tr');

			$(this).click(function() {

				var id = $(element).find(".id").val();
				var dataVars = "idFitxa=" + id;
				
				
				$.ajax({
					type: "POST",
					url: validarBorrar,
					data: dataVars,
					dataType: "json",
					error: function(data) {
							// missatge
							Missatge.llansar( { tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: data.error } );
							// error
							Error.llansar();
					},
					success: function(data) {
						
						if (data.id <= 0) {

							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: data.nom});

						}else{
							
							$(element).remove();
							
							switch (cantidad) {
							
							case 0:
								$('#resultatsFitxes .dades').html( "<p class=\"noItems\">" + txtNoHiHaFitxes + "</p>" );
								break;
								
							default:
								$("#resultatsFitxes .dades > p.info > strong").html(cantidad);
							
							var contador = 1;
							
							$("#resultatsFitxes .dades > div.table > div.tbody > div.tr").each(function() {
								
								$(this).removeClass("par");
								
								if ( contador % 2 == 0 )
									$(this).addClass("par");
								
								contador += 1;
								
							});
							
							}
							//Borramos el valor de la lista de fitxas devueltas, para que al guardar no se guarden los valores borrados.
							
							var listaTotal = $("#resultatsFitxes").data("fichas");
							
							for (var i=0; i<listaTotal.length; i++) {
								if (listaTotal[i].id == id){
									listaTotal.splice(i,1);
								}
							}
							
							$("#resultatsFitxes").data("fichas", listaTotal);
							
						}
						
						
						
					}
				});
				
				
				

			});

		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.quitarFicha");

	};

	this.asignarEventoSeleccionable = function(pagPag) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.asignarEventoSeleccionable");

		$("#resultatsFitxes .dades .tbody > .tr > div.enllas > select").live("change", function() {

			var orden = $(this).val();
			var idFicha = $(this).parent().prev().children().first().val();
			var fichas = $("#resultatsFitxes").data("fichas");
			var auxFichas = new Array();

			for ( i in fichas ) {

				if ( parseInt(fichas[i].id, 10) == parseInt(idFicha, 10) )
					fichas[i].ordre = parseInt(orden, 10);

				auxFichas.push(fichas[i]);

			}
			
			

			fichas = auxFichas.sort(function(a, b) { return a.ordre - b.ordre; });

			EscriptoriSeccioFitxes.almacenarPaginas(fichas);
			$("#resultatsFitxes").data("fichas", fichas);
			var data =  $("#resultatsFitxes").data("paginas")[pagPag];
			EscriptoriSeccioFitxes.pintarListadoAsignadas(data, pagPag, 10, fichas.length);

		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.asignarEventoSeleccionable");

	};

	this.pintarNoItems = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.pintarNoItems");
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.pintarNoItems");
		
		return "<p class=\"noItems\">" + txtNoHiHaFitxes + ".</p>";
		
	};

	this.limpiar = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.limpiar");

		$("#cerca_fitxes_nom").val("");
		$("#cerca_fitxes_codi").val("");
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.limpiar");

	};

	//Eliminar los parametros nombreFicha e idFicha
	this.listarFichasAsignadas = function(idSeccion, pagPag, pagRes) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.listarFichasAsignadas");

		var dataVars = "";

		if ( this.validarParametro(idSeccion) )
			dataVars += "idSeccion=" + idSeccion + "&pagPag=" + pagPag + "&pagRes=" + pagRes;

		$.ajax({
			type: "POST",
			url: pagFitxesUASeccio,
			data: dataVars,
			dataType: "json",
			error: function() {

				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
				Error.llansar();

			},
			success: function(data) {

				$("#seleccion-fichas").empty();

				if (data.id == -1) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});

				} else if (data.id < -1) {

					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});

				} else if (data.fitxes.length == 0) {

					$("#resultatsFitxes .dades").html("<p class='noItems'>" + txtNoHiHaFitxes + "</p>");

				} else {

					$('#resultatsFitxes .dades').fadeOut(300, function() {

						$("#cerca_fitxes_nom").attr("disabled", "disabled");
						$("#cerca_fitxes_codi").attr("disabled", "disabled");

						$(this).html("<p class=\"executant\">" + txtCercantItems + "</p>");

						$(this).fadeIn(300, function() {

							EscriptoriSeccioFitxes.almacenarPaginas(data.fitxes);
							$("#resultatsFitxes").data("fichas", data.fitxes);

							var datos = $("#resultatsFitxes").data("paginas")[0];

							EscriptoriSeccioFitxes.pintarListadoAsignadas(datos, 0, pagRes, data.totalRegistros);

							$("#cerca_fitxes_nom").removeAttr("disabled");
							$("#cerca_fitxes_codi").removeAttr("disabled");


							$("#resultatsFitxes div.fitxa").each(function() {

								var idFicha = $(this).find("input").val();

								$(this).find("a").click(function() {
									that.goDetalleFicha(idFicha);	
								});					

							});

						});

					});

				}

			} //Fin success

		}); //Fin ajax
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.listarFichasAsignadas");

	};

	/*Valida que el parámetro no sea nulo, vacío o undefined*/
	this.validarParametro = function(parametro) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.validarParametro");

		var valido = false;
		if (parametro != null & parametro != 'undefined' & parametro != "")
			valido = true;
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.validarParametro");

		return valido;

	};

	this.buscarFicha = function(idSeccion, nombreFicha, idFicha) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.buscarFicha");

		//Comprobar si los parámetros son nulos, vacios o indefinidos
		var dataVars = "";
		if ( this.validarParametro(idSeccion) )
			dataVars += "idSeccion=" + idSeccion;

		if ( this.validarParametro(nombreFicha) )
			dataVars += "&nombreFicha=" + nombreFicha;

		if ( this.validarParametro(idFicha) )
			dataVars += "&idFicha=" + idFicha;

		
		
		
		$('#seleccion-fichas').fadeOut(300, function() {

			$("#cerca_fitxes_nom").attr("disabled", "disabled");
			$("#cerca_fitxes_codi").attr("disabled", "disabled");

			$(this).html("<p class=\"executant\">" + txtCercantItems + "</p>");

			$(this).fadeIn(300, function() {
					$.ajax({
						type: "POST",
						url: pagSeccionsFitxes,
						data: dataVars,
						dataType: "json",
						error: function() {

							Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtAjaxError, text: "<p>" + txtIntenteho + "</p>"});
							Error.llansar();

						},
						success: function(data) {
							$("#cerca_fitxes_nom").removeAttr("disabled");
							$("#cerca_fitxes_codi").removeAttr("disabled");
							
							if (data.id == -1) {

								Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorPermisos});

							} else if (data.id < -1) {

								Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtErrorOperacio});

							} else if (data.fitxes.length == 0) {
								
								$("#seleccion-fichas").css("display", "block");
								$("#seleccion-fichas").html("<p class='noItems'>" + txtNoHiHaFitxes + "</p>");	

							} else {

								EscriptoriSeccioFitxes.pintarListado(data);
								$('#seleccion-fichas ul > li > div').each(function() {

									var id = $(this).find("input[type=hidden]:first").val();

									$(this).find("a.asigna").click(function() {
										that.asignarFicha(id);
									});

								}); //End each

							}

						} //Fin success

					}); //Fin ajax
				
			});

		});
		
		if (debug) {
			console.log("Saliendo de CEscriptoriSeccioFitxes.buscarFicha");
		}
	};

	this.asignarFicha = function(id) {// TODO: Cuando se asigne una ficha y ya no exista el listado se debe volver a pintar el listado pasándole por parámetro la nueva ficha

		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.asignarFicha");
		
		var vectorPaginas = $("#resultatsFitxes").data("paginas");
		for(var i = 0 ; i < vectorPaginas.length; i++) {
			var vectorPagina = vectorPaginas[i];
			for(var j= 0 ; j < vectorPagina.length; j++) {
				var elemento = vectorPagina[j];
				if (elemento != null && elemento.id != null && elemento.id == id) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtFitxaDuplicada+" "+elemento.ordre});
					return;
				}
			}
		}
		/*var inputs = $("div .td.nom.fitxa input");
		for(var i = 0 ; i < inputs.size(); i++) {
			var inputValor = inputs[i].value;
			if (inputValor != null && inputValor == id) {
				Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtFitxaDuplicada+" "+(i+1)});
				return;
			}
		}*/
		
		var elementCaducidad = "#ficha-caducidad-" + id;
		var caducidad = $(elementCaducidad).val();
		var orden = 1;
		var cantidadFichas = this.contarFichasAsignadas();
		var nodo = "#nodo-ficha-" + id + " > div > span";
		var titulo = $(nodo).html();
		var existTable = $("#resultatsFitxes .dades > div.table").length;
		var ficha = { "id":id, "titulo":titulo, "ordre":orden};
		var fichas = $("#resultatsFitxes").data("fichas");

		if ( this.validarParametro(fichas) ) {

			fichas.unshift(ficha);

		} else {

			fichas = new Array();
			fichas.push(ficha);

		}

		$("#resultatsFitxes").data("fichas", fichas);
		EscriptoriSeccioFitxes.almacenarPaginas(fichas);
		var paginas = $("#resultatsFitxes").data("paginas")[0];

		this.pintarListadoAsignadas(paginas, 0, 10, fichas.length);
		this.quitarFicha();
		this.calcularColorFilas();
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.asignarFicha");

	};

	this.calcularColorFilas = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.calcularColorFilas");

		var contador = 1;

		$("#resultatsFitxes .dades > div.table > div.tbody > div.tr").each(function() {

			$(this).removeClass("par");

			if ( contador % 2 == 0 )
				$(this).addClass("par");

			contador += 1;

		});
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.calcularColorFilas");

	};

	this.contarFichasAsignadas = function() {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.contarFichasAsignadas");
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.contarFichasAsignadas");

		return  $("#resultatsFitxes .dades > div.table > div.tbody > div.tr").length;

	};

	this.pintarFicha = function(idFicha, orden, caducidad, cantidadFichas, titulo) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.pintarFicha");

		cantidadFichas += 1;
		var filaPar = ( cantidadFichas % 2 == 0 ? "par" : "" );

		var ficha = "<div class='tr " + filaPar + "' role='row'>";
		ficha += "	<div class='td nom fitxa' role='gridcell'>";
		ficha += "		<input type='hidden' value='" + idFicha + "' class='id'>";
		ficha += "		<a href='javascript:;' class='nom'>" + titulo + "</a>";
		ficha += "	</div>";
		ficha += "	<div class='td enllas' role='gridcell'>";
		ficha += "		<select class='ordenacion'>";

		for ( var i = 1 ; i < 11 ; i++ ) {

			var seleccionado = ( orden == i ) ? "selected=''" : "";
			ficha += "<option value='" + i + "' " + seleccionado + ">" + i + "</option>";

		}

		ficha += "		</select>";
		ficha += "		<span></span>";
		ficha += "	</div>";
		ficha += "</div>";
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.pintarFicha");

		return ficha;

	};

	this.goDetalleFicha = function(fitxaId) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.goDetalleFicha");

		Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregant});
		
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
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.goDetalleFicha");

	};

	this.pintarListado = function(data) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.pintarListado");

		//Obtener el número total de fichas
		var listado = "";
		var listaFichas = data.fitxes;
		var numFitxes = listaFichas.length;

		/* Pintado cabeceras listado*/
		listado += '<p class="info">' + txtSeleccionados + ' <strong> ' + numFitxes + ' </strong> ' + txtFichas + '</p> ';
		listado += '<div class="listaOrdenable">';
		listado += '	<ul class="ui-sortable" style="cursor: default;">';

		/* Pintado de las filas */		
		paramsFicha = {

			nombre: "fitxa",
			nodoOrigen: "",
			nodoDestino: fitxes_seleccionats_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
			multilang: false

		};

		if ( listaFichas != null && numFitxes > 0 ) {

			for ( n = 0 ; n < numFitxes ; n++ ) {

				var idFicha = listaFichas[n].id;

				listado += "<li id='nodo-ficha-" + idFicha + "' class='nodoListaSecciones'>";
				listado += "	<div class=''>";
				listado += "		<span>" + listaFichas[n].titulo + "</span>";
				listado += "		<input id='ficha-id-" + idFicha + "' type='hidden' value='" + idFicha + "' >";
				listado += "		<input id='ficha-caducidad-" + idFicha + "' type='hidden' value='" + listaFichas[n].caducat + "' />";
				listado += "		<a id='btn-ficha-'" + idFicha + "' class='btn asigna' href='javascript:;'></a>";
				listado += "			<span>";
				listado += "				<span><span>";
				listado += "			<span>";
				listado += "	</div>";
				listado += "</li>";

			}

		}

		listado += '	</ul>';
		listado += '</div>';

		$("#seleccion-fichas").html(listado);
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.pintarListado");

	};

	this.pintarSeleccionableOrdenFichas = function(totalRegistros, ordenFicha) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.pintarSeleccionableOrdenFichas");

		var select = "<select class='ordenacion'>";

		for ( var i = 1 ; i <= totalRegistros; i++ ) {

			var seleccionado = ( ordenFicha == i ) ? "selected=''" : "";
			select += "<option value='" + i + "' " + seleccionado + ">" + i + "</option>";

		}

		select += "</select>";
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.pintarSeleccionableOrdenFichas");

		return select;

	};

	this.cambiarPagina = function(pagPag) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.cambiarPagina");

		$("#idSeccion").val(idSeccion);
		var totalRegistros = $("#totalRegistros").val();
		var fichas = $("#resultatsFitxes").data("paginas")[pagPag - 1];

		EscriptoriSeccioFitxes.pintarListadoAsignadas(fichas, pagPag - 1, 10, totalRegistros);
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.cambiarPagina");

	};

	this.almacenarPaginas = function(fichas) {
		
		if (debug)
			console.log("Entrando en CEscriptoriSeccioFitxes.almacenarPaginas");

		var listado = new Array();
		var paginas = new Array();

		for ( var i = 1 ; i <= fichas.length; i++) {

			listado.push( fichas[i-1] );

			if ( i % 10 == 0 ) {

				paginas.push(listado);
				listado = new Array();

			}

		}

		paginas.push(listado);

		$("#resultatsFitxes").data("paginas", paginas);
		
		if (debug)
			console.log("Saliendo de CEscriptoriSeccioFitxes.almacenarPaginas");

	};

};

/**
 * (amartin) Explicación de extensión de clase:
 * 
 * Extendemos la clase para que, tras el guardado, se oculte el botón de guardado del módulo lateral de secciones (caso especial de secciones
 * relacionadas con una sección). Esto es porque la lista simple sólo gestionará el orden de las secciones relacionadas. Al reordenar alguna
 * aparecerá el botón de guardar y al realizar la acción de guardado éste desaparecerá.
 * 
 * Se conserva la dualidad de una clase para la gestión de Secciones o Fichas relacionadas con una gestión, como ya se hace al principio:
 * 
 * ModulSeccions = new CModulSeccio();
 * ModulFitxes = new CModulSeccio();
 * 
 * Son de la misma clase.
 * 
 */
function CListaSimpleSeccionesOFichas() {
	
	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaSimple;
	this.extend();
	
	var that = this;
	
	this._guardar = this.guardar;
	
	this.guardar = function(element, url, id) {
		
		if (debug)
			console.log("Entrando en CListaSimpleSeccionesOFichas.guardar");
		
		that._guardar(element, url, id);
		
		// XXX amartin: ocultación del botón de guardado tras solicitar guardado AJAX
		// Ir añadiendo casos aquí.
		var urlGuardarOrdenSecciones = "/seccions/guardarOrdenSeccionesRelacionadas.do";
		var urlGuardarOrdenFichas = "/seccions/guardarOrdenFichasRelacionadas.do";
		
		if ( url.indexOf(urlGuardarOrdenSecciones) != -1 ) {
			
			if (typeof ModulSeccions != 'undefined')
				ModulSeccions.deshabilitarBotonGuardarSecciones();
			
		} else if ( url.indexOf(urlGuardarOrdenFichas) != -1 ) {
			
			if (typeof ModulFitxes != 'undefined')
				ModulFitxes.deshabilitarBotonGuardarFichas();
			
		}
		
		Detall.modificado(false);
		
		if (debug)
			console.log("Entrando en CListaSimpleSeccionesOFichas.guardar");
		
	};
	
};