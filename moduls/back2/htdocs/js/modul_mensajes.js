function abrirMensaje(idDato) {

	alert(idDato);

	debugger;

	dataVars = "id=" + idDato;

	$.ajax({
		type: "POST",
		url: pagUrlMensajes,
		data: dataVars,
		dataType: "json",
		error: function() {
			console.error("No se ha podido actualizar el organo");
		},
		success: function(data) {

			alert("OK");
			alert(data);
			// Get the modal
			var modal = document.getElementById("myModal");
			modal.style.display = "block";

		} // Fin success
	}); //Fin ajax




	// Get the button that opens the modal
	//var btn = document.getElementById("myBtn");

	// When the user clicks on the button, open the modal
	/*
	btn.onclick = function() {
	  modal.style.display = "block";
	}
*/

}