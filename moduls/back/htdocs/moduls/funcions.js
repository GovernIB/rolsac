// Obrir Confirmant
function obrirConfirmant(url, pregunta) {
	valor = confirm(pregunta);
	if (valor == true) {
		document.location = url;
	}else{
		return;
	}
}




// Obrir un pop up
function obrir(url, name, x, y) {
   nombre = window.open(url, name, 'scrollbars=no, resizable=yes, width=' + x + ',height=' + y);
   return nombre;
}

// Obrir un pop up amb scroll
function obrirScroll(url, name, x, y) {
   nombre = window.open(url, name, 'scrollbars=yes, resizable=yes, width=' + x + ',height=' + y);
   return nombre;
}

// Obrir un pop up per el calendari
function obrirFixa(url) {
   nombre = window.open(url, 'Calendario', 'scrollbars=no, resizable=no, width=235 ,height=175');
   return nombre;
}

function obrirTest(url) {
   nombre = window.open( url, '', 'toolbar, menubar, scrollbars, resizable');
   return nombre;
}

// Auxiliar para formularios
function myIndex(o) {
   var f = o.form;
   
   for (i = 0; i < f.elements.length ; i++) {
      if (f.elements[i] == o) return i;
   }
}

// Confirma una baja mediante un di?logo
function confirmaBaja(texto) {
    var result = confirm(texto)

    if (result == true) {
        return true;
    } else {
        return false;
    }
}

function obrirImatge(url) {
    obrir(url, '', 400, 300);
}