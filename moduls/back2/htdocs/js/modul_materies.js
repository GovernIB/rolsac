// MODULO DE MATERIAS RELACIONADAS

jQuery(document).ready(function() {
	ModulMateries = new CModulMateries();
});

function CModulMateries(){
	var that = this;
	var modul_materies_elm = jQuery("div.modulMateries");
	var materies_seleccionats_elm;
	var materies_llistat_elm;
	var materiaDefaultClass = "materiaDefault";
					 	
	if ( modul_materies_elm.size() == 1 ) {		

		modul_materies_elm.find("a.gestiona").bind("click", function(){	
			that.gestiona();
		});
		modul_materies_elm.find("a.cancela").bind("click", function(){
			that.cancela();
		});
		modul_materies_elm.find("a.finalitza").bind("click", function(){
			that.finaliza();
		});
		
		materies_seleccionats_elm = modul_materies_elm.find("div.seleccionats:first");
		materies_llistat_elm = modul_materies_elm.find("div.llistat:first");				
		
	}
	
	this.cancela = function(){
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
	}
	
	this.gestiona = function(){
		materies_seleccionats_elm.slideUp(300);
		materies_llistat_elm.slideDown(300);
	}
	
	this.finaliza = function(){
		nombre_llistat = 0;
				
		codi_llistat = "<ul>";
		
		materies_llistat_elm.find("li").each(function(i) {
		
			li_elm = $(this);
			input_elm = li_elm.find("input");
			
			if (input_elm.attr("checked")) {
				codi_llistat += "<li><input type=\"hidden\" value=\"" + input_elm.val() + "\" />" + li_elm.find("span").text() + "</li>";
				nombre_llistat++;
				
				input_elm.addClass(materiaDefaultClass);
			} else {
				input_elm.removeClass(materiaDefaultClass);
			}
			
		});
		
		codi_llistat += "</ul>";
		
		codi_materia_txt = (nombre_llistat == 1) ? txtMateria : txtMateries;
		codi_info = (nombre_llistat == 0) ? txtNoHiHaMateries + "." : "Hi ha <strong>" + nombre_llistat + " " + codi_materia_txt + "</strong>.";
		
		materies_seleccionats_elm.find("p.info").html(codi_info);
		materies_seleccionats_elm.find(".listaOrdenable").html(codi_llistat);		
		
		materies_seleccionats_elm.slideDown(300);
		materies_llistat_elm.slideUp(300);
		
	}
	
	//Actualiza la lista de materias seleccionadas y marca los checkboxes cuando se carga una ficha
	this.inicializarMaterias = function(dades){
		
		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_llistat_elm = escriptori_detall_elm.find("div.modulMateries div.llistat");
		materies_nodes = dades;
		materies_nodes_size = materies_nodes.length;

		mat_llistat_elm.find("input").removeAttr("checked");
		
		if (materies_nodes_size == 0) {
			mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		} else {
			codi_materies = "<ul>";
			$(materies_nodes).each(function() {
				materia_node = this;
				codi_materies += "<li><input type=\"hidden\" value=\"" + materia_node.id + "\" />" + materia_node.nom + "</li>";
				mat_llistat_elm.find("input[value=" + materia_node.id + "]").attr("checked", "checked").addClass(materiaDefaultClass);
			});
			codi_materies += "<ul>";
			txt_materies = (materies_nodes_size == 1) ? txtMateria : txtMateries;			
			mat_seleccionats_elm.find("p.info").html(txtHiHa + " <strong>" + materies_nodes_size + " " + txt_materies + "</strong>.");
			mat_seleccionats_elm.find(".listaOrdenable").html(codi_materies);
		}
		
		that.mostrarMateriasSeleccionadas();
	}
	
	//devuelve un string con el formato materies=n1,n2,...,nm donde n son codigos de materias
	this.listaMaterias = function (){
		
		var listaMaterias = "materies=";
		
		$("div.modulMateries div.seleccionats div.listaOrdenable input").each(function() {
			listaMaterias += $(this).val() + ",";										
		});
		if (listaMaterias.length > 0){
			listaMaterias = listaMaterias.slice(0, listaMaterias.length-1);
		}
		return listaMaterias;
	}
	
	/* Al acceder al formulario de creacion, limpia las listas de materias, desmarca los checkboxes,
	 * marca las materias por defecto, econder el listado y mostrar los seleccionados.
	 */
	this.nuevo = function() {
		
		mat_seleccionats_elm = escriptori_detall_elm.find("div.modulMateries div.seleccionats");
		mat_seleccionats_elm.find("ul").remove().end().find("p.info").text(txtNoHiHaMateries + ".");
		$("div.modulMateries div.llistat input[type=checkbox]").attr('checked', false).removeClass(materiaDefaultClass);

		that.mostrarMateriasSeleccionadas();
	}

	// Econder el listado y mostrar los seleccionados.
	this.mostrarMateriasSeleccionadas = function () {
		escriptori_detall_elm.find("div.modulMateries div.llistat").hide();
		escriptori_detall_elm.find("div.modulMateries div.seleccionats").show();
	}
}
