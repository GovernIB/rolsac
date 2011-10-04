function CError(){	
	this.llansar = function() {
		if (escriptori_detall_elm.css("display") != "none") {
			escriptori_detall_elm.attr('aria-hidden', 'true').attr('aria-disabled', 'true').fadeOut(300);
		}
		escriptori_elm.fadeOut(300, function() {
			segundos = 60;
			conex = setInterval("Error.conexion()",1000);
			codi = "<div id=\"error\">";
			codi += "<h1>" + txtAjaxError + "</h1>";
			codi += "<p><strong>" + txtFuncions + "</strong> " + txtFuncionsFins + ".</p>";
			codi += "<p>" + txtConexionIntentar + " <span id=\"temps\">" + segundos + " " + txtSegons + "</span>.</p>";
			codi += "<p><a onclick=\"Error.reiniciar();\">" + txtConectar + "</a></p>";
			codi += "</div>";
			// mostrem
			escriptori_elm.attr('aria-hidden', 'false').attr('aria-disabled', 'false').html(codi).fadeIn(300);
		});
	}
	
	this.conexion = function() {
		segundos--;
		if (segundos == 0) {
			Error.reiniciar();
		} else if (segundos == 1) {
			$("#temps").html(segundos + " " + txtSegon);
		} else {
			$("#temps").html(segundos + " " + txtSegons);
		}
	}
	
	this.reiniciar = function() {
		escriptori_elm.fadeOut(300, function() {
			if (conex) { clearInterval(conex); }
			// escriptori, carregant
			codi = "<p class=\"executant\">" + txtCargandoEntidades + "</p>";
			escriptori_elm.html(codi).fadeIn(300, function() {
				// INICIAMOS
				Entidades.carregar({entidad:entidad_ID});
			});
		});
	}
}