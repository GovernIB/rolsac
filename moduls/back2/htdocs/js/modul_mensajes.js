var idDatoSave="";

function abrirMensaje(idDato) {

    idDatoSave=idDato;
	dataVars = "id=" + idDato;
	document.getElementById("chatmsg").innerHTML="";

	$.ajax({
		type: "POST",
		url: pagUrlMensajes,
		data: dataVars,
		dataType: "json",
		error: function() {
			console.error("No se ha podido cargar la info del chat");
		},
		success: function(data) {

			$('#modalID').val(idDato);
			$
            .each(
                data.mensajes,
                function(i, item) {
                  recuperarMensaje(data,i);
                });

			// Get the modal
			var modal = document.getElementById("myModalChat");
			modal.style.display = "block";
			$(".chatbox").css('z-index', 2);

		} // Fin success
	}); //Fin ajax

	function recuperarMensaje(data,i){

		if (tienePermisoPublicar != 'S') {
			$("#divEnviarEmailChat").hide();
		} else {
			$("#divEnviarEmailChat").show();
		}

		  var id = data.mensajes[i].id;
		  var texto = data.mensajes[i].texto;
		  var leido = data.mensajes[i].leido;
		  var gestor = data.mensajes[i].gestor;
		  var  creado = data.mensajes[i].usuarioNombre + " " + data.mensajes[i].usuario;
		  var  leidomsg = data.mensajes[i].usuarioLectura;
		  var  dataCreado =  new Date(data.mensajes[i].fechaCreacion);
		  var horaCreado = ((dataCreado.getHours() < 10 ? '0' : '') + dataCreado.getHours());
		  var minutosCreado = ((dataCreado.getMinutes() < 10 ? '0' : '') + dataCreado.getMinutes());
		  var fechaCreado =  dataCreado.getDate() + '/' + (dataCreado.getMonth() +1 ) + '/' + dataCreado.getFullYear() +' ' + horaCreado+ ':' + minutosCreado;
		  var chatCreadoPersona = "";
		  if(gestor) {
			  chatCreadoPersona = " - Gestor "
		  } else {
			  chatCreadoPersona = " - Supervisor "
		  }
		  var chatCreado = txtCreado +" ("+creado+chatCreadoPersona+"):"+fechaCreado;
		  var  dataLeido =  new Date(data.mensajes[i].fechaLectura);

		  if ( dataLeido != null ) {
			  var horaLeido = ((dataLeido.getHours() < 10 ? '0' : '') + dataLeido.getHours());
			  var minutosLeido = ((dataLeido.getMinutes() < 10 ? '0' : '') + dataLeido.getMinutes());
			  var fechaLeido=  dataLeido.getDate() + '/' + (dataLeido.getMonth() +1 ) + '/' + dataLeido.getFullYear() +' ' + horaLeido+ ':' + minutosLeido;
			  var chatLeido = chatCreado + "<br/>"+txtLeido+" ("+leidomsg+"):"+fechaLeido;
		  }

		  if(gestor==false && leido==true){
			  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-mensaje chat-mensaje-supervisor">' + texto + '</p></div>');
			  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-usuario">' + chatLeido + '</p></div>');
		  }

		  if(gestor==false && leido==false){
			  if (tienePermisoPublicar != 'S') {
				  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido" value="Marcar como leido" id="marcar" name="marcar" onClick="marcarComoLeido('+id+',this)" /><p class="chat-mensaje chat-mensaje-supervisor">' + texto + '</p></div>');
			  }else{
				  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-mensaje chat-mensaje-supervisor">' + texto + '</p></div>');
			  }
			  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-usuario">' + chatCreado + '</p></div>');
		  }

		  if(gestor==true && leido==true){
			  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar" onClick="marcarComoLeido('+id+',this)" /><p class="chat-mensaje chat-mensaje-gestor">' + texto + '</p></div>');
			  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar" onClick="marcarComoLeido('+id+',this)" /><p class="chat-usuario">' + chatLeido + '</p></div>');
		  }

		  if(gestor==true && leido==false){
			  if (tienePermisoPublicar != 'S') {
				  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="'+txtMarcarComoLeido+'" id="marcar" name="marcar" onClick="marcarComoLeido('+id+',this)"  /><p class="chat-mensaje chat-mensaje-gestor">' + texto + '</p></div>');
			  } else {
				  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido" value="'+txtMarcarComoLeido+'" id="marcar" name="marcar" onClick="marcarComoLeido('+id+',this)"  /><p class="chat-mensaje chat-mensaje-gestor">' + texto + '</p></div>');
			  }
			  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="'+txtMarcarComoLeido+'" id="marcar" name="marcar"  /><p class="chat-usuario" >' + chatCreado + '</p></div>');
		  }


		}


	// Get the button that opens the modal
	//var btn = document.getElementById("myBtn");

	// When the user clicks on the button, open the modal
	/*
	btn.onclick = function() {
	  modal.style.display = "block";
	}
*/

}


function marcarComoLeido(idDato, boton) {

	dataVars = "id=" + idDato;

	$.ajax({
		type: "POST",
		url: pagUrlMensajeLeido,
		data: dataVars,
		dataType: "json",
		error: function() {
			console.error("No se ha podido marcar ");
		},
		success: function(data) {
			if (data.error != null && data.error != undefined) {
				Missatge.llansar({tipus: "missatge", modo: "error", fundit: "si", titol: data.error, text: ""});
			} else {
				boton.style.visibility = "hidden"; //.style.display="none";
			}
		} // Fin success
	}); //Fin ajax

}



function enviarChat() {

	var texto = $("#textChat").val();
	if (texto == '' || texto == undefined) {
		console.error("Texto del mensaje a enviar vacío");
		return;
	}
	var id = $('#modalID').val();
	var enviarEmail = "N";
	if ($('#enviarEmailChat').is(':checked')) {
		enviarEmail ="S";
	}
	dataVars = "texto=" + texto +"&idEntidad="+id+"&enviarEmail="+enviarEmail;

	$.ajax({
		type: "POST",
		url: pagUrlEnviarMensaje,
		data: dataVars,
		dataType: "json",
		error: function() {
			console.error("No se ha podido marcar ");
		},
		success: function(data) {
			if (data.error != null && data.error != undefined) {
				Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.error, text: "<p>" + txtIntenteho + "</p>"});
				// error
				//Error.llansar();
			} else {
				$("#textChat").val('');
				abrirMensaje(idDatoSave);
				 $('#chatmsg').animate({ scrollTop: 1000000 }, 800);


				//Missatge.llansar({tipus: "missatge", modo: "error", fundit: "si", titol: data.error, text: "<p>" + txtIntenteho + "</p>"});
			}
		} // Fin success
	}); //Fin ajax

}

