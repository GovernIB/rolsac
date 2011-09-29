/**
 * Clase de la que debe heredar el objeto Llistat.
 */
function ListadoBase(){
	this.cacheDatosListado = null;
	
	// Atributo que contiene el id del elemento que se está visualizando en la ficha.
	this.itemID;
	
	/**
	 * Marca la cache como nula para forzar a que se vuelve a actualizar.
	 */
	this.anulaCache = function(){
		this.cacheDatosListado = null;
	}
	
	// Cambia de página.
	this.cambiaPagina = function( pag ){
		multipagina.setPaginaActual(pag-1);
		pag_Pag = pag;
		this.anar(pag);
	}
		
	/**
	 * Carga la ficha de un item del listado.
	 * @param link Objeto <A> sobre el que se realizó la acción.
	 */
	this.ficha = function( link ){
		// Obtenemos el id del item a partir del id del enlace.
		itemID = jQuery(link).attr("id").split("_")[1];
		Detall.carregar(itemID);		
		itemID_ultim = itemID;
						
		this.itemID = itemID;
	}
	
	// Limpia el formulario de búsqueda.
	this.limpia = function(){
		jQuery('#cercador_contingut :input').each(limpiarCampo);
	}
	
	// Cambia a la pestaña de listado.
	this.tabListado = function() {		
		jQuery("#opcions .actiu").removeClass("actiu");
		jQuery("#tabListado").parent().addClass("actiu");
		
		opcio_unitat = "L";
				
		// resultats		
		resultats_elm.find("div.actiu").slideUp(300,function() {
			jQuery(this).removeClass("actiu");
			resultats_elm.find("div.L").slideDown(300,function() {
				jQuery(this).addClass("actiu");

				resultats_actiu_elm = resultats_elm.find("div.actiu:first");
				escriptori_contingut_elm.bind("click",Llistat.llansar);
			});
		});
	}
	
	// Cambia a la pestaña del buscador.
	this.tabBuscador = function(){
		jQuery("#opcions .actiu").removeClass("actiu");
		jQuery("#tabBuscador").parent().addClass("actiu");
		
		opcio_unitat = "C";
		
		// resultats
		resultats_elm.find("div.actiu").slideUp(300,function() {
			jQuery(this).removeClass("actiu");
			resultats_elm.find("div."+opcio_unitat).slideDown(300,function() {
				jQuery(this).addClass("actiu");
				
				resultats_actiu_elm = resultats_elm.find("div.actiu:first");				
				escriptori_contingut_elm.bind("click",Llistat.llansar);
			});
		});
	}
	
	// Muestra el formulario para insertar una nueva ficha.
	this.nuevaFicha = function(){
		// desactivem taula
		escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);		
		Detall.nou();
	}
	
	// Realizar una búsqueda
	this.busca = function(){		
	
		// desactivem taula
		escriptori_contingut_elm.attr('aria-disabled', 'true').unbind("click",Llistat.llansar);
		cercador_elm.find("input, select").attr("disabled", "disabled");
		
		resultats_dades_elm = resultats_actiu_elm.find("div.dades:first");
		
		// animacio
		resultats_dades_elm.fadeOut(300, function() {
			// pintem
			codi_cercant = "<p class=\"executant\">" + txtCercantElements + "</p>";
			resultats_dades_elm.html(codi_cercant).fadeIn(300, function() {
			
				// events taula
				pagPagina_cercador_elm.val(0); // Al pulsar el boton de consulta, los resultados se han de mostrar desde la primera página.
				Llistat.carregar({cercador: "si"});
				
			});
		});	
	}
	
	/**
	 * Método llamado al cambiar de página.
	 * @param enlace_html Número de la página de destino.
	 */
	this.anar = function(enlace_html){
		
		resultats_actiu_elm = resultats_elm.find("div.actiu:first");
		
		if (isNaN(parseInt(enlace_html,10))) {
			if (elm.hasClass("inici")) {
				num = 1;
			} else if (elm.hasClass("anteriors")) {
				num = parseInt(pag_Pag,10);
			} else if (elm.hasClass("final")) {
				num = paginasNum;
			} else {
				num = parseInt(pag_Pag,10)+2;
			}
		} else {
			num = parseInt(enlace_html,10);
		}
		
		// text cercant
		txt = (num <= pag_Pag) ? txtCercantAnteriors : txtCercantSeguents;
		resultats_dades_elm = resultats_elm.find("div.actiu:first div.dades:first");
		resultats_dades_elm.fadeOut(300, function() {
			// pintem
			codi_anar = "<p class=\"executant\">" + txt + "</p>";
			resultats_dades_elm.html(codi_anar).fadeIn(300, function() {
				
				//pagPagina_elm.val(num-1);
				
				// llancem!
				
				if (resultats_actiu_elm.hasClass("C")) {
					Llistat.carregar({pagina: num-1, cercador: "si"});
				} else {
					Llistat.carregar({pagina: num-1});
				}
				
			});
		});	
	}
}