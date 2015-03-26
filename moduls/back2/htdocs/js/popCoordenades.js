// PopupCoordenades GoogleMaps v3

$(document).ready(function() {
	b_buscadireccion = true;
    iniLat = $('#item_latitud',window.top.document).val();
    iniLng = $('#item_longitud',window.top.document).val();

    if (iniLat.length == 0 || iniLng.length == 0) {
        iniLat="39.5690036";
        iniLng="2.6436571";
    } else {
        b_buscadireccion = false;    
    }
    initialize();
});


function initialize() {
	var center = new google.maps.LatLng(iniLat, iniLng);
	var mapOptions = {zoom: 16, center: center};
	map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

	creaMarker(center);

    if (b_buscadireccion)
        showAddress();
	      
}
        
function creaMarker(point) {

	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(point.lat(), point.lng()),
		map: map,
		draggable: true,
		icon : '../img/gps/gps_caib.png'
	});

	
    marcadorEdificio = marker;
    
	google.maps.event.addListener(marcadorEdificio, 'dragend', function(event) {

		var point = marker.getPosition();

        $('#lat').val(point.lat());
        $('#lng').val(point.lng());
        
	});
	
}

function showAddress() {

	address = $('#address').val();

	geocoder = new google.maps.Geocoder();


	geocoder.geocode({'address': address}, function(results, status) {

		if (status != google.maps.GeocoderStatus.OK) {

    		Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: "<spring:message code='coordenada.missatge.titol'/>", text: "<spring:message code='coordenada.missatge.text'/>"});
    		
		} else {

			var point = results[0].geometry.location;
    		map.setCenter(point);

    		$('#lat').val(point.lat());
            $('#lng').val(point.lng());
            
            
            if (marcadorEdificio == null) {
                
                creaMarker(point);
                
            } else {
                
                var marker = marcadorEdificio;
                map.setCenter(point, map.getZoom());
                marker.setPosition(point);
                
            }
			
	    }
		
    });

}
		

function centrar(){
	map.setCenter(marcadorEdificio.getPosition());
}    
   
function aceptar(){
	lat = $('#lat').val();
    lng = $('#lng').val();
	window.top.Detall.refreshCoordenadas(lat,lng);
    window.top.Detall.modificado();
    borrarPopUp('popCoordenades');
    
}

function borrar(){	
    window.top.Detall.refreshCoordenadas('','');
	window.top.Detall.modificado();
	borrarPopUp('popCoordenades');
} 