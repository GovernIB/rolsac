// MODUL PUBLIC OBJECTIU

jQuery(document).ready(function() {
	modul_publics_elm = jQuery("div.ModulPublicObjectiu:first");

	ModulPublicObjectiu = new CModulPublicObjectiu();

	if (modul_publics_elm.size() == 1) {
		ModulPublicObjectiu.iniciar();
	}
});

function CModulPublicObjectiu(){
	this.extend = ListaOrdenable;
	this.extend();

    // Campo hidden para controlar los cambios sobre un módulo.
    var $moduloModificado = null;

    // Campo Hidden que contiene el código del publicoObjetivo interno
    var $publicoObjetivoInterno = null;

	var that = this;

	this.iniciar = function() {
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "publicObjectiu",
			nodoOrigen: modul_publics_elm.find(".listaOrdenable"),
			nodoDestino: modul_publics_elm.find(".listaOrdenable"),
			atributos: ["id", "nom", "orden"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});

        // Obtenemos el campo oculto para controlar los cambios.
        $moduloModificado = modul_publics_elm.find('input[name="modul_public_modificat"]');

        // Obtenemos el campo oculto con el id del publico objetivo interno.
        $publicoObjetivoInterno = modul_publics_elm.find('input[name="modul_public_intern"]').val();

        // Controlamos los cambios sobre los elementos del módulo, poniendo el campo hidden correspondiente a 1.
        modul_publics_elm.find("input[type=checkbox]").change(function(){
            $moduloModificado.val(1);
            $this = jQuery(this);
            if($this.val()==$publicoObjetivoInterno && $this.attr("checked") == "checked"){
            	//si estamos marcando el check del publico objetivo interno, desmarcamos los otros
            	modul_publics_elm.find("input[type=checkbox]").each(function() {
         			$this2 = jQuery(this);
         			if ($this2.val()!=$publicoObjetivoInterno) {
         				$this2.removeAttr("checked");
         			}
         		});

            }else if ($this.val()!=$publicoObjetivoInterno && $this.attr("checked") == "checked"){
            	//si estamos marcando el check de otro publico objetivo, desmarcamos el de los internos
            	modul_publics_elm.find("input[type=checkbox]").each(function() {
         			$this2 = jQuery(this);
         			if ($this2.val()==$publicoObjetivoInterno) {
         				$this2.removeAttr("checked");
         			}
         		});
            }

        });
	}

	var modul_publics_elm = jQuery("div.ModulPublicObjectiu");
	var publics_seleccionats_elm;
	var publics_llistat_elm;
	var publicObjectiuDefaultClass = "publicObjectiuDefault";

	if ( modul_publics_elm.size() == 1 ) {

		modul_publics_elm.find("a.gestiona").bind("click", function(){
			that.gestiona();
		});
		modul_publics_elm.find("a.cancela").bind("click", function(){
			that.cancela();
		});
		modul_publics_elm.find("a.finalitza").bind("click", function(){
			that.finaliza();
		});

		publics_seleccionats_elm = modul_publics_elm.find("div.seleccionats:first");
		publics_llistat_elm = modul_publics_elm.find("div.llistat:first");

	}

	this.cancela = function(){
		pub_llistat_elm = escriptori_detall_elm.find("div.ModulPublicObjectiu div.llistat");
		pub_llistat_elm.find("input[type=checkbox]").each(function() {
			$this = jQuery(this);
			if ($this.hasClass(publicObjectiuDefaultClass)) {
				$this.attr("checked", "checked");
			} else {
				$this.removeAttr("checked");
			}
		});

		publics_seleccionats_elm.slideDown(300);
		publics_llistat_elm.slideUp(300);

        // Restauramos el estado del campo de control de cambios.
        $moduloModificado.val( $moduloModificado.data('oldvalue') );
	}

	this.gestiona = function(){

        // Guardamos el estado del campo de control de cambios.
        $moduloModificado.data( 'oldvalue', $moduloModificado.val() );

		publics_seleccionats_elm.slideUp(300);
		publics_llistat_elm.slideDown(300);
	}
	

	this.finaliza = function(){


		nombre_llistat = 0;

		codi_llistat = "<ul>";

		publics_llistat_elm.find("li").each(function(i) {

			li_elm = $(this);
			input_elm = li_elm.find("input");

			if ( input_elm.attr("checked") == "checked" ) {

				codi_llistat += "<li><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
				input_elm.addClass(publicObjectiuDefaultClass);

				if (input_elm.val()==$publicoObjetivoInterno){
					//Si el publico objetivo es interno marcar a false disponibleApoderadoHabilitado, 
					//en otro caso debe ser true(tener en cuenta que si pointerno es true, no puede haber otro po, y esto solo aplica a procedimientos )
					jQuery('#item_disponibleApoderadoHabilitado').attr('checked', false);
				}else{
					jQuery('#item_disponibleApoderadoHabilitado').attr('checked', true);
				}
				
			} else {

				input_elm.removeClass(publicObjectiuDefaultClass);

			}

		});

		codi_llistat += "</ul>";

		codi_public_txt = (nombre_llistat == 1) ? txtPublic : txtPublics;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaPublics + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_public_txt + "</strong>.";

		publics_seleccionats_elm.find("p.info").html(codi_info);
		publics_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);

		publics_seleccionats_elm.slideDown(300);
		publics_llistat_elm.slideUp(300);

	}

	this.contaSeleccionats = function() {
		seleccionats_val = modul_publics_elm.find(".seleccionats").find("li").size();
		info_elm = modul_publics_elm.find("p.info:first");

		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaPublics + ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtPublic.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtPublics.toLowerCase() + "</strong>.");

			modul_publics_elm.find(".listaOrdenable ul").sortable({
				axis: 'y',
				update: function(event, ui) {
					ModulPublicObjectiu.calculaOrden(ui, "destino");
					that.contaSeleccionats();
					Detall.modificado();
				}
			}).css({cursor:"move"});

		}
	}

	//Actualiza la lista de materias seleccionadas y marca los checkboxes cuando se carga una ficha
	this.inicializarPublics = function(listaPublics, btnEliminar){
		modul_publics_elm.find(".listaOrdenable").empty();
		if (typeof listaPublics != 'undefined' && listaPublics != null && listaPublics.length > 0) {
			that.agregaItems(listaPublics, btnEliminar);
		}
		that.contaSeleccionats();

		modul_publics_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();
			Detall.modificado();
		});

		modul_publics_elm.find(".listaOrdenable a.edita").unbind("click").bind("click", function(){
			var itemID = jQuery(this).parents(".materia_id").val();
			// Mostrar datos de public
			Detall.carregar(itemID);
		});

		if (!btnEliminar) {
			publics_seleccionats_elm = escriptori_detall_elm.find("div.ModulPublicObjectiu div.seleccionats");
			pub_llistat_elm = escriptori_detall_elm.find("div.ModulPublicObjectiu div.llistat");
			public_nodes = listaPublics;
			public_nodes_size = public_nodes.length;

			pub_llistat_elm.find("input").removeAttr("checked");

			//Añadimos o eliminamos el atributo "checked" en función de si está o no marcado.
			pub_llistat_elm.find("input").each(
				function() {
					$( this ).bind( "click", function() {
						if ( !this.checked )
							$(this).removeAttr("checked");
						else
							$(this).attr("checked", "checked");
				});
			});

			if (public_nodes_size == 0) {
				publics_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaPublics + ".");
			} else {
				codi_publics = "<ul>";
				$(public_nodes).each(function() {
					public_node = this;
					codi_publics += "<li><input type=\"hidden\" value=\"" + public_node.id + "\" />" + public_node.nom + "</li>";
					pub_llistat_elm.find("input[value=" + public_node.id + "]").attr("checked", "checked").addClass(publicObjectiuDefaultClass);
				});
				codi_publics += "<ul>";
				txt_publics = (public_nodes_size == 1) ? txtPublic : txtPublics;
				publics_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + public_nodes_size + " " + txt_publics + "</strong>.");
				publics_seleccionats_elm.find(".listaOrdenable").html(codi_publics);
			}

			that.mostrarPublicsSeleccionats();
		}
	}

	//devuelve un string con el formato materies=n1,n2,...,nm donde nx son codigos de materias
	this.listaPublics = function (){
		var listaPublics = "publicsObjectiu=";

		$("div.ModulPublicObjectiu div.seleccionats div.listaOrdenable input").each(function() {
			listaPublics += $(this).val() + ",";
		});

		if (listaPublics[listaPublics.length-1] == ","){
			listaPublics = listaPublics.slice(0, -1);
		}

		return listaPublics;
	}

	/* Al acceder al formulario de creacion, limpia las listas de materias, desmarca los checkboxes,
	 * marca las materias por defecto, econder el listado y mostrar los seleccionados.
	 */
	this.nuevo = function() {

		publics_seleccionats_elm = escriptori_detall_elm.find("div.ModulPublicObjectiu div.seleccionats");
		publics_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaPublics + ".");
		$("div.ModulPublicObjectiu div.llistat input[type=checkbox]").attr('checked', false).removeClass(publicObjectiuDefaultClass);

		that.mostrarPublicsSeleccionats();
	}

	// Econder el listado y mostrar los seleccionados.
	this.mostrarPublicsSeleccionats = function () {
		escriptori_detall_elm.find("div.ModulPublicObjectiu div.llistat").hide();
		escriptori_detall_elm.find("div.ModulPublicObjectiu div.seleccionats").show();
	}
}
