// MODULO DE ESPACIOS TERRITORIALES

jQuery(document).ready(function() {
	modul_espais_elm = jQuery("div.modulEspais:first");

	ModulEspais = new CModulEspais();
	
	if (modul_espais_elm.size() == 1) {
		ModulEspais.iniciar();
	}
});

function CModulEspais(){
	this.extend = ListaOrdenable;
	this.extend();
	
	var that = this;
	
	this.iniciar = function() {
		// Configuramos la lista ordenable.
		this.configurar({
			nombre: "espai",
			nodoOrigen: modul_espais_elm.find(".listaOrdenable"),
			nodoDestino: modul_espais_elm.find(".listaOrdenable"),
			atributos: ["id", "nombre", "orden"],	// Campos que queremos que aparezcan en las listas.
			multilang: false
		});
	}
	
	var modul_espais_elm = jQuery("div.modulEspais");
	var espais_seleccionats_elm;
	var espais_llistat_elm;
	var espaiDefaultClass = "espaiDefault";
					 	
	if ( modul_espais_elm.size() == 1 ) {		

		modul_espais_elm.find("a.gestiona").bind("click", function(){	
			that.gestiona();
		});
		modul_espais_elm.find("a.cancela").bind("click", function(){
			that.cancela();
		});
		modul_espais_elm.find("a.finalitza").bind("click", function(){
			that.finaliza();
		});
		
		espais_seleccionats_elm = modul_espais_elm.find("div.seleccionats:first");
		espais_llistat_elm = modul_espais_elm.find("div.llistat:first");				
		
	}
	
	this.cancela = function(){
		mat_llistat_elm = escriptori_detall_elm.find("div.modulEspais div.llistat");
		mat_llistat_elm.find("input[type=checkbox]").each(function() {
			$this = jQuery(this);
			if ($this.hasClass(espaiDefaultClass)) {
				$this.attr("checked", "checked");
			} else {
				$this.removeAttr("checked");
			}
		});
		
		espais_seleccionats_elm.slideDown(300);
		espais_llistat_elm.slideUp(300);
	}
	
	this.gestiona = function(){
		Detall.nou($("#item_id").val(), $("#item_nom_ca").val());
	}
	
	this.finaliza = function(){
		nombre_llistat = 0;
				
		codi_llistat = "<ul>";
		
		espais_llistat_elm.find("li").each(function(i) {
					
			li_elm = $(this);
			input_elm = li_elm.find("input");
					
			if (input_elm.attr("checked") == "checked") {
				codi_llistat += "<li><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
				
				input_elm.addClass(espaiDefaultClass);
			} else {
				input_elm.removeClass(espaiDefaultClass);
			}
			
		});
		
		codi_llistat += "</ul>";
		
		codi_espai_txt = (nombre_llistat == 1) ? txtEspai : txtEspais;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaEspais + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_espai_txt + "</strong>.";
		
		espais_seleccionats_elm.find("p.info").html(codi_info);
		espais_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);		
		
		espais_seleccionats_elm.slideDown(300);
		espais_llistat_elm.slideUp(300);
		
	}
	
	this.contaSeleccionats = function() {
		seleccionats_val = modul_espais_elm.find(".seleccionat").find("li").size();
		info_elm = modul_espais_elm.find("p.info:first");
		
		if (seleccionats_val == 0) {
			info_elm.text(txtNoHiHaEspaisSeleccionats + ".");
		} else if (seleccionats_val == 1) {
			info_elm.html(txtSeleccionada + " <strong>" + seleccionats_val + " " + txtEspai.toLowerCase() + "</strong>.");
		} else if (seleccionats_val > 1) {
			info_elm.html(txtSeleccionades + " <strong>" + seleccionats_val + " " + txtEspais.toLowerCase() + "</strong>.");
		}
	}
	
	//Actualiza la lista de espacios seleccionados cuando se carga una ficha
	this.inicializarEspacios = function(listaEspais){
		modul_espais_elm.find(".listaOrdenable").empty();
		if (typeof listaEspais != 'undefined' && listaEspais != null && listaEspais.length > 0) {
			that.agregaItems(listaEspais, false);
		}
		
		modul_espais_elm.find('div.espai').each(function() {
            $(this).unbind("click").bind("click", function() {
                var espaiId = $(this).find("input.espai_id").val();
                Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtCarregantDetall});
                $.ajax({
                    type: "GET",
                    url: pagDetall,
                    data: "id=" + espaiId,
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
		
		modul_espais_elm.find(".listaOrdenable a.elimina").unbind("click").bind("click", function(){
			var itemLista = jQuery(this).parents("li:first");
			that.eliminaItem(itemLista);
			that.contaSeleccionats();
			Detall.modificado();
		});
	}
	
	//devuelve un string con el formato espais=n1,n2,...,nm donde nx son codigos de espacios
	this.listaEspacios = function (){
		var listaEspacios = "espais=";
		
		$("div.modulEspais div.seleccionats div.listaOrdenable input").each(function() {
			listaEspacios += $(this).val() + ",";										
		});

		if (listaEspacios[listaEspacios.length-1] == ","){
			listaEspacios = listaEspacios.slice(0, -1);
		}
		
		return listaEspacios;
	}
	
	this.torna = function() {
		escriptori_detall_elm.fadeOut(300, function() {
			escriptori_contingut_elm.fadeIn(300);
		});
	}
	
	/* Al acceder al formulario de creacion, limpia las listas de espacios, desmarca los checkboxes,
	 * marca los espacios por defecto, econder el listado y mostrar los seleccionados.
	 */
	this.nuevo = function() {
		
		espais_seleccionats_elm = escriptori_detall_elm.find("div.modulEspais div.seleccionats");
		espais_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaEspais + ".");
		$("div.modulEspais div.llistat input[type=checkbox]").attr('checked', false).removeClass(espaiDefaultClass);

		that.mostrarEspaciosSeleccionados();
	}

	// Econder el listado y mostrar los seleccionados.
	this.mostrarEspaciosSeleccionados = function () {
		escriptori_detall_elm.find("div.modulEspais div.llistat").hide();
		escriptori_detall_elm.find("div.modulEspais div.seleccionats").show();
	}
}
