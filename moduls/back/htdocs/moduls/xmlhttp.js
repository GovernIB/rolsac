// Definir objeto XMLHttpRequest
var xmlhttp=false;
/*@cc_on @*/
/*@if (@_jscript_version >= 5)
try {
    xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
    try {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    } catch (E) {
        xmlhttp = false;
    }
}
@end @*/
if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
    xmlhttp = new XMLHttpRequest();
}

// Hace un post sincrono, devolviendo el resultado.
function syncPost(urlString, postString) {
    xmlhttp.open("POST", urlString, false);
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlhttp.send(postString);
    // alert("syncPost->" + xmlhttp.responseText);
    return xmlhttp.responseText;
}


// Hace un post asincrono, devolviendo el resultado.
function asyncPost(urlString, postString, processReqChange) {

    xmlhttp.onreadystatechange = processReqChange;
	/*
	    open:
        primer parametro: Se puede especificar distintos comandos como GET, HEAD, etc.
        segundo parametro: Direccion relativa de nuestro sitio.
        tercer parametro: sincrono - asincrono
	*/
	xmlhttp.open("POST", urlString, true);
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // send: envia la peticion
	xmlhttp.send(postString);
}


/*function processReqChange() {*/
    /*
	readyState:
	0 = sin inicializar
	1 = cargando
	2 = cargado
	3 = interactivo
	4 = completo
	*/
    /*if (xmlhttp.readyState == 4) {*/
	    /*
        status:
        Nuemero de respuesta del servidor ejemplo
        200 = OK
        404 = Not Found
        */
		/*if (xmlhttp.status == 200) {
		    alert("processReqChange()->" + xmlhttp.responseText);            
        } else {
		    alert('Se genero un error:\n' + xmlhttp.statusText);
		}
	}
}*/