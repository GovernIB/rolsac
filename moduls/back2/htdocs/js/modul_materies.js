// MODULO DE MATERIAS RELACIONADAS

jQuery(document).ready(function() {

	modul_materies_elm = jQuery("div.modulMateries:first");

	ModulMateries = new CModulMateries();

	if (modul_materies_elm.size() == 1) {
		ModulMateries.iniciar();
	}

});

function CModulMateries() {

	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaOrdenable;
	this.extend();

    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = null;

	var that = this;

	this.iniciar = function() {

		if (debug)
			console.log("Entrando en CModulMateries.iniciar");

		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "materia",
			nodoOrigen: modul_materies_elm.find(".listaOrdenable"),
			nodoDestino: modul_materies_elm.find(".listaOrdenable"),
			atributos: [			// Campos que queremos que aparezcan en las listas.
	            "id",
	            "nom",
	            "orden",
	            "idRelatedItem", 	// Campo necesario para guardado AJAX genérico de módulos laterales.
	            "idMainItem"		// Campo necesario para guardado AJAX genérico de módulos laterales.
            ],
			multilang: false
		});

        // Obtenemos el campo oculto para controlar los cambios.
        $moduloModificado = modul_materies_elm.find('input[name="modulo_materias_modificado"]');

        // Controlamos los cambios sobre los elementos del módulo, poniendo el campo hidden correspondiente a 1.
        modul_materies_elm.find("input[type=checkbox]").change(function() {
            $moduloModificado.val(1);
        });

        if (debug)
			console.log("Saliendo de CModulMateries.iniciar");

	};

	var modul_materies_elm = jQuery("div.modulMateries");
	var materies_seleccionats_elm;
	var materies_llistat_elm;
	var materiaDefaultClass = "materiaDefault";

	if ( modul_materies_elm.size() == 1 ) {

		modul_materies_elm.find("a.gestiona").bind("click", function() {
			that.gestiona();
		});
		modul_materies_elm.find("a.cancela").bind("click", function() {
			that.cancela();
		});
		modul_materies_elm.find("a.finalitza").bind("click", function() {
			that.finaliza();
		});

		materies_seleccionats_elm = modul_materies_elm.find("div.seleccionats:first");
		materies_llistat_elm = modul_materies_elm.find("div.llistat:first");

	}

	this.cancela = function() {

		if (debug)
			console.log("Entrando en CModulMateries.cancela");

		mat_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
		mat_llistat_elm.find("input[type=checkbox]").each(function() {
			$this = jQuery(this);
			if ($this.hasClass(materiaDefaultClass)) {
				$this.attr("checked", "checked");
			} else {
				$this.removeAttr("checked");
			}
		});

		materies_seleccionats_elm.slideDown(300);
		materies_llistat_elm.slideUp(300);

        // Restauramos el estado del campo de control de cambios.
        $moduloModificado.val( $moduloModificado.data('oldvalue') );

        if (debug)
			console.log("Saliendo de CModulMateries.cancela");

	};

	this.gestiona = function() {

		if (debug)
			console.log("Entrando en CModulMateries.gestiona");

        // Guardamos el estado del campo de control de cambios.
        $moduloModificado.data( 'oldvalue', $moduloModificado.val() );

		materies_seleccionats_elm.slideUp(300);
		materies_llistat_elm.slideDown(300);

		if (debug)
			console.log("Saliendo de CModulMateries.gestiona");

	};

	this.modificado = function() {
        $moduloModificado.val(1);
    };

	this.finaliza = function() {

		// Validamos el servicio
    	if(!validarServeis()){
    		this.cancela();
    		 return false;
    	}
		// Validamos el procedimiento
	    	if(!validarProcediment()){
	    		this.cancela();
	    		 return false;
	    	}

		nombre_llistat = 0;
		codi_llistat = "<ul>";

		materies_llistat_elm.find("li").each(function(i) {

			li_elm = $(this);
			input_elm = li_elm.find("input");

			if (input_elm.attr("checked") == "checked") {

				codi_llistat += "<li item-id='" + input_elm.val() + "' main-item-id='" + jQuery('#item_id').val() + "' related-item-id='" + input_elm.val() + "'>";
				codi_llistat += "<input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text();
				codi_llistat += "</li>";

				nombre_llistat++;

				input_elm.addClass(materiaDefaultClass);

			} else {

				input_elm.removeClass(materiaDefaultClass);

			}

		});

		codi_llistat += "</ul>";

		codi_materia_txt = (nombre_llistat == 1) ? txtMateria : txtMateries;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaMateries + "." : txtHiHa + " <strong>" + nombre_llistat + " " + codi_materia_txt + "</strong>.";

		materies_seleccionats_elm.find("p.info").html(codi_info);
		materies_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);

		materies_seleccionats_elm.slideDown(300);
		materies_llistat_elm.slideUp(300);

		// Marcamos el módulo como modificado.
        this.modificado();

		// amartin: emitimos señal de finalización, para que los guardados AJAX la detecten y procesen los nuevos elemento seleccionados.
		modul_materies_elm.trigger("finalizaMaterias");

	};

	this.contaSeleccionats = function() {

		if (debug)
			console.log("Entrando en CModulMateries.contaSeleccionats");

		seleccionats_val = modul_materies_elm.find(".seleccionats").find("li").size();
		info_elm = modul_materies_elm.find("p.info:first");

		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaMateriesSeleccionades + ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtMateria.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {

			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtMateries.toLowerCase() + "</strong>.");

			modul_materies_elm.find(".listaOrdenable ul").sortable({
				axis: 'y',
				update: function(event, ui) {
					ModulMateries.calculaOrden(ui, "destino");
					that.contaSeleccionats();
				}
			}).css({cursor:"move"});

		}

		if (debug)
			console.log("Saliendo de CModulMateries.contaSeleccionats");

	};

	//Actualiza la lista de materias seleccionadas y marca los checkboxes cuando se carga una ficha
	this.inicializarMaterias = function(listaMateries, btnEliminar) {

		if (debug)
			console.log("Entrando en CModulMateries.inicializarMaterias");

		modul_materies_elm.find(".listaOrdenable").empty();
		if (typeof listaMateries != 'undefined' && listaMateries != null && listaMateries.length > 0) {
			that.agregaItems(listaMateries, btnEliminar);
		}
		that.contaSeleccionats();

		modul_materies_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function() {
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();
		});

		modul_materies_elm.find(".listaOrdenable a.edita").unbind("click").bind("click", function() {
			var itemID = jQuery(this).parents(".materia_id").val();
			// Mostrar datos de materia
			Detall.carregar(itemID);
		});

		if (!btnEliminar) {

			mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
			mat_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
			materies_nodes = listaMateries;
			materies_nodes_size = materies_nodes.length;

			mat_llistat_elm.find("input").removeAttr("checked");

			//Añadimos o eliminamos el atributo "checked" en función de si está o no marcado.
			mat_llistat_elm.find("input").each(
				function() {
					$( this ).bind( "click", function() {
						if ( !this.checked )
							$(this).removeAttr("checked");
						else
							$(this).attr("checked", "checked");
				});
			});

			if (materies_nodes_size == 0) {

				mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");

			} else {

				codi_materies = "<ul>";
				$(materies_nodes).each(function() {
					materia_node = this;
					codi_materies += "<li element-id=" + materia_node.id + " main-item-id= '" + materia_node.idMainItem + "' related-item-id= '" + materia_node.idRelatedItem + "'><input type=\"hidden\" value=\"" + materia_node.id + "\" />" + materia_node.nom + "</li>";
					mat_llistat_elm.find("input[value=" + materia_node.id + "]").attr("checked", "checked").addClass(materiaDefaultClass);
				});
				codi_materies += "<ul>";
				txt_materies = (materies_nodes_size == 1) ? txtMateria : txtMateries;
				mat_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + materies_nodes_size + " " + txt_materies + "</strong>.");
				mat_seleccionats_elm.find(".listaOrdenable").html(codi_materies);

			}

			that.mostrarMateriasSeleccionadas();

		}

		if (debug)
			console.log("Saliendo de CModulMateries.inicializarMaterias");

	};

	//devuelve un string con el formato materies=n1,n2,...,nm donde nx son codigos de materias
	this.listaMaterias = function () {

		if (debug)
			console.log("Entrando en CModulMateries.listaMaterias");

		var listaMaterias = "materies=";

		$("div.modulMateries div.seleccionats div.listaOrdenable input").each(function() {
			listaMaterias += $(this).val() + ",";
		});

		// Pasamos a array para evitar problemas con IE7
		var array = listaMaterias.split(",");
		if (array[array.length-1] == ""){
			listaMaterias = listaMaterias.slice(0, -1);
		}

		if (debug)
			console.log("Saliendo de CModulMateries.listaMaterias");

		return listaMaterias;

	};

	/* Al acceder al formulario de creacion, limpia las listas de materias, desmarca los checkboxes,
	 * marca las materias por defecto, esconde el listado y muestra los seleccionados.
	 */
	this.nuevo = function() {

		if (debug)
			console.log("Entrando en CModulMateries.nuevo");

		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		$("div.modulMateries div.llistat input[type=checkbox]").attr('checked', false).removeClass(materiaDefaultClass);

		that.mostrarMateriasSeleccionadas();

		if (debug)
			console.log("Saliendo de CModulMateries.nuevo");

	};

	// Econder el listado y mostrar los seleccionados.
	this.mostrarMateriasSeleccionadas = function () {

		if (debug)
			console.log("Entrando en CModulMateries.mostrarMateriasSeleccionadas");

		escriptori_detall_elm.find("div.modulMateries div.llistat").hide();
		escriptori_detall_elm.find("div.modulMateries div.seleccionats").show();

		if (debug)
			console.log("Saliendo de CModulMateries.mostrarMateriasSeleccionadas");

	};

	this.botonGuardar = jQuery("#btnGuardar_modul_materies");

	this.existeBotonGuardar = function() {
		return (this.botonGuardar.length > 0);
	};

	this._eliminaItem = this.eliminaItem;

	this.eliminaItem = function( item ) {

		that._eliminaItem(item);

		// Si hay botón de guardado, hay que marcar la página como modificada.
		// Si no, el guardado se hace vía botón "Finalizar".
		if (this.existeBotonGuardar()) {
			Detall.modificado(true);
			this.habilitarBotonGuardar();
		}

	};

	this._agregaItem = this.agregaItem;

	this.agregaItem = function( item ) {

		that._agregaItem(item);

		// Si hay botón de guardado, hay que marcar la página como modificada.
		// Si no, el guardado se hace vía botón "Finalizar".
		if (this.existeBotonGuardar()) {
			Detall.modificado(true);
			this.habilitarBotonGuardar();
		}

	};

	this.habilitarBotonGuardar = function() {
		if (this.existeBotonGuardar()) {
			this.botonGuardar.show(500);
	        Detall.modificado();
		}
    };

    this.deshabilitarBotonGuardar = function() {
    	if (this.existeBotonGuardar()) {
    		this.botonGuardar.css("display", "none");
    	}
    };

}


/**
 * (amartin) Explicación de extensión de clase:
 *
 * Extendemos la clase para que, tras el guardado, se oculte el botón de guardado del módulo lateral.
 * Al marcar un elemento para ser borrado o al insertar uno nuevo, aparecerá el botón de guardar.
 * Al realizar la acción de guardado, el botón de guardar desaparecerá.
 */
function CListaSimpleMaterias() {

	// Activa mensajes de debug.
	var debug = false;

	this.extend = ListaSimple;
	this.extend();

	var that = this;

	this._guardar = this.guardar;

	this.guardar = function(element, url, id) {

		if (debug)
			console.log("Entrando en CListaSimpleMaterias.guardar");

		that._guardar(element, url, id);

		// XXX amartin: ocultación del botón de guardado tras solicitar guardado AJAX.
		// Ir añadiendo casos aquí.
		var urlGuardarMateriasAgrupacion = "/agrupacioMateries/guardarMateriasRelacionadas.do";

		if ( url.indexOf(urlGuardarMateriasAgrupacion) != -1 ) {

			if (typeof ModulMateries != 'undefined')
				ModulMateries.deshabilitarBotonGuardar();

		}

		Detall.modificado(false);

		if (debug)
			console.log("Entrando en CListaSimpleMaterias.guardar");

	};

};
