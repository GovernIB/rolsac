function abrirMensaje(idDato) {

	alert(idDato);

	debugger;

	dataVars = "id=" + idDato;
	document.getElementById("chatmsg").innerHTML="";

	$.ajax({
		type: "POST",
		url: pagUrlMensajes,
		data: dataVars,
		dataType: "json",
		error: function() {
			console.error("No se ha podido actualizar el organo");
		},
		success: function(data) {
			$
            .each(
                data.mensajes,
                function(i, item) {

                  recuperarMensaje(data,i)

                });


//			alert("OK");
//			alert(data);
			// Get the modal
			var modal = document.getElementById("myModalChat");
			modal.style.display = "block";

		} // Fin success
	}); //Fin ajax

	function recuperarMensaje(data,i){

		  var texto = data.mensajes[i].texto;
		  var leido = data.mensajes[i].leido;
		  var gestor = data.mensajes[i].gestor;
		  var  creado = data.mensajes[i].usuario;
		  var  leidomsg = data.mensajes[i].usuarioLectura;
		  var  dataCreado =  new Date(data.mensajes[i].fechaCreacion);
		  var horaCreado = ((dataCreado.getHours() < 10 ? '0' : '') + dataCreado.getHours());
		  var minutosCreado = ((dataCreado.getMinutes() < 10 ? '0' : '') + dataCreado.getMinutes());
		  var fechaCreado =  dataCreado.getDate() + '/' + (dataCreado.getMonth() +1 ) + '/' + dataCreado.getFullYear() +' ' + horaCreado+ ':' + minutosCreado;
		  var chatCreado = "Creado ("+creado+"):"+fechaCreado;
		  var  dataLeido =  new Date(data.mensajes[i].fechaLectura);
		  if(dataLeido!=null){
		  var horaLeido = ((dataLeido.getHours() < 10 ? '0' : '') + dataLeido.getHours());
		  var minutosLeido = ((dataLeido.getMinutes() < 10 ? '0' : '') + dataLeido.getMinutes());
		  var fechaLeido=  dataLeido.getDate() + '/' + (dataLeido.getMonth() +1 ) + '/' + dataLeido.getFullYear() +' ' + horaLeido+ ':' + minutosLeido;
		  var chatLeido = chatCreado + "<br/>Leido ("+leidomsg+"):"+fechaLeido;
		  }


//		  if(tipo=="true"){
//		    tipo="INFORMADOR";
//		  }
//		  if(tipo=="false"){
//		    tipo="SISTEMA";
//		  }
		  if(gestor==false && leido==true){
		  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-mensaje">' + texto + '</p></div>');
		  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-usuario">' + chatLeido + '</p></div>');

		  }
		  if(gestor==false && leido==false){
			  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-mensaje">' + texto + '</p></div>');
			  $('#chatmsg').append('<div class ="chat supervisor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-usuario">' + chatCreado + '</p></div>');

		   }

		  if(gestor==true && leido==true){
		  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-mensaje">' + texto + '</p></div>');
		  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-usuario">' + chatLeido + '</p></div>');

		  }
		  if(gestor==true && leido==false){
			  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-mensaje">' + texto + '</p></div>');
			  $('#chatmsg').append('<div class ="chat gestor"><input type="button" class ="chat-leido ocultar" value="Marcar como leido" id="marcar" name="marcar"  /><p class="chat-usuario">' + chatCreado + '</p></div>');

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