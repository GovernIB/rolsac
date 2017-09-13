/**
 * Clase de la que debe heredar el objeto Llistat. 
 * Todos los parametros son opcionales.
 * Si no se pasan, se les asigna un valor por defecto para mantener la compatibilidad con la implementacion anterior.
 * 
 * @param idOpciones = Id del de la lista de pestanas (listado, buscador, crear nuevo).
 * @param idResultados = Id del div de resultados.
 * @param idBuscador = Id fel div del buscador.
 * @param idBuscadorContenido = Id del div con el contenido del buscador.
 * @param idTabListado = Id del enlace de la pestana del listado. 
 * @param idTabBuscador = Id del enlace de la pestana del buscador.
 * @param idBtnNuevo = Id del <li> de la pestaña de crear "algo" nuevo.
 * @param idBtnBuscadorForm = Id del boton de busqueda.
 * @param idBtnLimpiarForm = Id del boton de limpiar formulario.
 */
function ListadoBase(idOpciones, idResultados, idBuscador, idBuscadorContenido, idTabListado, idTabBuscador, idBtnNuevo, idBtnBuscadorForm, idBtnLimpiarForm) {

    // Valores por defecto por compatibilidad con implementacion anterior.
	if (typeof idOpciones == "undefined" || idOpciones == "") idOpciones = "#opcions";
	if (typeof idResultados == "undefined" || idResultados == "") idResultados = "#resultats";
	if (typeof idBuscador == "undefined" || idBuscador == "") idBuscador = "#cercador";
	if (typeof idBuscadorContenido == "undefined" || idBuscadorContenido == "") idBuscadorContenido = "#cercador_contingut";
	if (typeof idTabListado == "undefined" || idTabListado == "") idTabListado = "#tabListado";
	if (typeof idTabBuscador == "undefined" || idTabBuscador == "") idTabBuscador = "#tabBuscador";
	if (typeof idBtnNuevo == "undefined" || idBtnNuevo == "") idBtnNuevo = "#btnNuevaFicha";
	if (typeof idBtnBuscadorForm == "undefined" || idBtnBuscadorForm == "") idBtnBuscadorForm = "#btnBuscarForm";
	if (typeof idBtnLimpiarForm == "undefined" || idBtnLimpiarForm == "") idBtnLimpiarForm = "#btnLimpiarForm";

	// Anadir los "#" por comodidad.
	var ID_MARK = "#";
	if (idOpciones.substring(0,1) != ID_MARK) idOpciones = ID_MARK + idOpciones;
	if (idResultados.substring(0,1) != ID_MARK) idResultados = ID_MARK + idResultados;
	if (idBuscador.substring(0,1) != ID_MARK) idBuscador = ID_MARK + idBuscador;
	if (idBuscadorContenido.substring(0,1) != ID_MARK) idBuscadorContenido = ID_MARK + idBuscadorContenido;
	if (idTabListado.substring(0,1) != ID_MARK) idTabListado = ID_MARK + idTabListado;
	if (idTabBuscador.substring(0,1) != ID_MARK) idTabBuscador = ID_MARK + idTabBuscador;
	if (idBtnNuevo.substring(0,1) != ID_MARK) idBtnNuevo = ID_MARK + idBtnNuevo;
	if (idBtnBuscadorForm.substring(0,1) != ID_MARK) idBtnBuscadorForm = ID_MARK + 	idBtnBuscadorForm;
	if (idBtnLimpiarForm.substring(0,1) != ID_MARK) idBtnLimpiarForm = ID_MARK + idBtnLimpiarForm;
	
	var that = this;
	this.cacheDatosListado = null;
	
	// Atributo que contiene el id del elemento que se está visualizando en la ficha.
	this.itemID;
	
	var resultats_elm = jQuery(idResultados);
	var cercador_elm = jQuery(idBuscador);
	
	/**
	 * Marca la cache como nula para forzar a que se vuelve a actualizar.
	 */
	this.anulaCache = function() {
		this.cacheDatosListado = null;
	};
	
	// Cambia de página.
	this.cambiaPagina = function( pag ){
		
		multipagina.setPaginaActual(pag - 1);
		pag_Pag = pag;
		this.anar(pag);
		
	};
		
	/**
	 * Carga la ficha de un item del listado.
	 * @param link Objeto <A> sobre el que se realizó la acción.
	 */
	this.ficha = function( link ) {
		
		// Obtenemos el id del item a partir del id del enlace.
		itemID = jQuery(link).attr("id").split("_")[1];
		Detall.carregar(itemID);		
		itemID_ultim = itemID;
		this.itemID = itemID;
		
	};
	
	// Limpia el formulario de búsqueda.
	this.limpia = function() {
        jQuery(idBuscadorContenido + ' :input').each(limpiarCampo);
	};
	
	// Cambia a la pestaña de listado.
	this.tabListado = function() {
		
		jQuery(idOpciones + " .actiu").removeClass("actiu");
		jQuery(idTabListado).parent().addClass("actiu");
		
		opcio_unitat = "L";
		
		// resultats		
		resultats_elm.find("div.actiu").slideUp(300, function() {
			
			jQuery(this).removeClass("actiu");
			
			resultats_elm.find("div.L").slideDown(300, function() {
				
				jQuery(this).addClass("actiu");
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");
				
			});
			
		});
		
	};
	
	// Cambia a la pestaña del buscador.
	this.tabBuscador = function() {
		
		jQuery(idOpciones + " .actiu").removeClass("actiu");
		jQuery(idTabBuscador).parent().addClass("actiu");
		
		opcio_unitat = "C";
		
		// resultats
		resultats_elm.find("div.actiu").slideUp(300, function() {
			
			jQuery(this).removeClass("actiu");
			resultats_elm.find("div."+opcio_unitat).slideDown(300, function() {
				
				jQuery(this).addClass("actiu");
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");
				
			});
			
		});
		
	};
	
	// Muestra el formulario para insertar una nueva ficha.
	this.nuevaFicha = function() {		
		Detall.nou();
	};
	
	// Realizar una búsqueda
	this.busca = function() {	
			
		cercador_elm.find("input, select").attr("disabled", "disabled");
		resultats_dades_elm = resultats_elm.find("div.actiu:first").find("div.dades:first");
		
		// animacio
		resultats_dades_elm.fadeOut(300, function() { // pintem
			
			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				//pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				that.carregar({cercador: "si"});
				
			});
		
		});
		
	};
	
	/**
	 * Método llamado al cambiar de página.
	 * @param enlace_html Número de la página de destino.
	 */
	this.anar = function(enlace_html, carregarFunc){
		
		if (!carregarFunc || carregarFunc == 'undefined' )
			carregarFunc = that.carregar;

		resultats_actiu_elm = resultats_elm.find("div.actiu:first");
		num = parseInt(enlace_html,10);
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantAnteriors : txtCercantSeguents;
		resultats_dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		
		resultats_dades_elm.fadeOut(300, function() { // pintem
			
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			resultats_dades_elm.html(codi_anar).fadeIn(300, function() {
				
				if (resultats_actiu_elm.hasClass("C"))
					carregarFunc({pagina: num - 1, cercador: "si"});
				
				else
					carregarFunc({pagina: num - 1});
				
			});
			
		});	
		
	};
	
	this.getHtmlSelectorOrdenacion = function( id, orden, numItems ) {
		
		var html = '';
		var selected;
		html += '<select id="'+id+'" class="ordenacion">';
		
		for ( var i = 0; i < numItems; i++ ) {
			
			selected = ( orden == i );
			html += '<option value="' + i + '"' + (selected ? ' selected' : '') + '>' + (i + 1) + '</option>';
			
		}
		
		html += '</select>';
		return html;
		
	};
	
	this.getHtmlItemsPagina = function() {
		
		var items = [10,20,50];
		var numTodos = 99999;
		var html = '<span class="itemsPagina">' + txtMostrar + '&nbsp;';
		
		for (var i = 0; i < items.length; i++) {
			
			if ( pag_Res == items[i] )
				html += '<strong>'+items[i]+'</strong>&nbsp;';
			
			else
				html += '<a href="javascript:void(0);" onclick="multipagina.cambiaItemsPorPagina('+items[i]+')">'+items[i]+'</a>&nbsp;';
			
		}
		
		if ( pag_Res == numTodos )
			html += '<strong>'+ txtTodos +'</strong>';
		
		else
			html += '<a href="javascript:void(0)" onclick="multipagina.cambiaItemsPorPagina('+numTodos+')">'+ txtTodos +'</a>';
		
		
		html += '&nbsp;'+txtItemsPagina+'</span>';
		
		return html;
		
	};
	
    /**
     * Método de ordenación.
     */
    this.ordena = function(domObj, opciones) {
    	
        var $enlace = jQuery(domObj);
        var $header = $enlace.parent();
        var tipo = "asc";
        
        // Obtenemos el tipo de ordenación a partir de la clase del enlace.
        if ( $header.hasClass("DESC") ) {
        	
            $header.removeClass("DESC").addClass("ASC");
            tipo = "ASC";
            
        } else if ( $header.hasClass("ASC") ) {
        	
            $header.removeClass("ASC").addClass("DESC");
            tipo = "DESC";
            
        } else {
        	
            $header.addClass("ASC");
            tipo = "ASC";
            
        }
        
        // Actualizamos los campos de información de orden.
        ordreTipus_elm.val(tipo);
		ordreCamp_elm.val($enlace.attr("class"));        
        
        this.anulaCache();        
        
        // Recargamos los datos.
        resultats_dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		resultats_dades_elm.fadeOut(300, function() {
			
            resultats_dades_elm.html( "<p class=\"executant\">Ordenant...</p>" ).fadeIn(300, function() {
                that.carregar(opciones);
            });
            
        });
		
    };
    
    // Bindings
	jQuery(idTabListado).unbind("click").bind("click", function() { that.tabListado(); });
	jQuery(idBtnNuevo).unbind("click").bind("click", function(){ that.nuevaFicha(); });
	jQuery(idTabBuscador).unbind("click").bind("click", function() { that.tabBuscador(); });	
	jQuery(idBtnBuscadorForm).unbind("click").bind("click", function() { that.busca(); });
	jQuery(idBtnLimpiarForm).unbind("click").bind("click", function() { that.limpia(); });
	jQuery("#btnExportar").unbind("click").bind("click", function() { 
		Llistat.exporta(); 
	});
	
}
