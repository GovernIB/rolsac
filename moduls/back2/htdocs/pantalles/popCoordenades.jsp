<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<title><spring:message code='coordenada.coordenades'/></title>
	<meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
	<jsp:include page="layout/favicon.jsp" flush="true"/>
	<link href="<c:url value='/css/comuns.css'/>" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value='/css/coordenades.css'/>" rel="stylesheet" type="text/css" media="screen" />
	
	<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/error.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/comuns.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/detall_base.js'/>"></script>
    <jsp:include page="../layout/variablesGlobalsJavascript.jsp" flush="true"/> 
    
    <script src="http://maps.googleapis.com/maps/api/js?key=<c:out value='${googleMapKey }' />&amp;sensor=true" type="text/javascript"></script>

   	<script type="text/javascript">
   	<!--

	    var map = null;
	    var geocoder = null;
        var marcadorEdificio = null;

        function initialize() {

        	var b_buscadireccion = true;
	        var iniLat = "<c:out value='${latEdi}'/>";
	        var iniLng = "<c:out value='${lngEdi}'/>";

	        if (iniLat.length == 0 || iniLng.length == 0) {
		        
	            iniLat="39.5690036";
	            iniLng="2.6436571";
	            
	        } else {
		        
	            b_buscadireccion = false; 
	                  
	        }
	        
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
				icon : '<c:url value='/img/gps/gps_caib.png' />'
			});

			
	        marcadorEdificio = marker;
	        
			google.maps.event.addListener(marcadorEdificio, 'dragend', function(event) {

				var point = marker.getPoint();

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

            $("#" + "<c:out value='${latitud}'/>", window.top.document).val(lat);
            $("#" + "<c:out value='${longitud}'/>", window.top.document).val(lng);
            $("#" + "<c:out value='${latitud}'/>", window.top.document).change();
            $("#" + "<c:out value='${longitud}'/>", window.top.document).change();

            window.top.Detall.modificado();
            borrarPopUp('popCoordenades');
            
        }

        function borrar(){
            $("#" + "<c:out value='${latitud}'/>", window.top.document).val(' ');
            $("#" + "<c:out value='${longitud}'/>", window.top.document).val(' ');
            $("#" + "<c:out value='${latitud}'/>", window.top.document).change();
            $("#" + "<c:out value='${longitud}'/>", window.top.document).change();
        	
        	window.top.Detall.modificado();
        	borrarPopUp('popCoordenades');
        }         

     -->   
	</script>
</head>

<body onload="initialize()">
	<div id="escript">
	   <!-- escriptori_detall -->
	   <div id="escriptori_detall" class="escriptori_detall" >
            <c:choose>
                <c:when test="${not empty googleMapKey}">
                    <div id="cercador" class="modul " >
                        <fieldset>
	                        <div class="modul_continguts mostrat">
	                            <input type="hidden" size="10" name="lat" id="lat" value="<c:out value='${latEdi}'/>" />
	                            <input type="hidden" size="10" name="lng" id="lng" value="<c:out value='${lngEdi}'/>" />
	                            <input type="hidden" size="10" name="icon" id="icon" value="<c:url value='/img/gps/gps_caib.png' />" />
	                            <div class="fila">
	                                <div class="element t57">
	                                    <div class="etiqueta">
	                                        <label for="address"><spring:message code='edifici.formulari.direccio'/></label>
	                                    </div>
	                                    <div class="control">
	                                        <input id="address" name="address" type="text" class="nou" value="<c:out value='${dirEdi}' />" />
	                                    </div>
	                                </div>
	                                <div class="element">
	                                    <div class="etiqueta">
		                                    <label for="nothing">&nbsp;</label>
		                                </div>
		                                <div class="botonera">
	                                        <div class="btnGenerico">
	                                            <a id="btn-buscar" href="javascript:showAddress(); " class="btn consulta"><span><span><spring:message code='boto.cerca_direccio'/></span></span></a>
	                                        </div>
                                            <div class="btnGenerico">
                                                <a id="btn-centrar" href="javascript:centrar();" class="btn noIcona"><span><span><spring:message code='boto.centra_marca'/></span></span></a>
                                            </div>
                                        </div>
                                    </div>
	                            </div>
	                        </div>
	                        <div class="fila">
	                            <div id="map_canvas" style="width: 97%; height: 300px"></div>
	                        </div>
	                        <div class="fila">
	                           <div class="element">
		                            <div class="botonera">
		                                <div class="btnGenerico">
		                                    <a id="btn-acepta-coordenadas" href="javascript:aceptar()" class="btn noIcona"><span><span><spring:message code='boto.acepta_coordenades'/></span></span></a>
		                                </div>
		                                <div class="btnGenerico">
		                                    <a id="btn-borra-coordenadas" href="javascript:borrar()" class="btn borrar"><span><span><spring:message code='boto.esborra_coordenades'/></span></span></a>
		                                </div>
		                                <div class="btnGenerico">
		                                    <a id="btn-cierra-mapa" href="javascript:borrarPopUp('popCoordenades')" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
		                                </div>
		                            </div>
	                            </div>
	                        </div>
	                    </fieldset>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="bloc">
                        <spring:message code='coordenada.googlemapkey.undefined'/>
                    </div>
                </c:otherwise>
            </c:choose>	
	   </div>
	</div>	
</body>
</html>
