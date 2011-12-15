<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<title><spring:message code='coordenada.coordenades'/></title>
	<meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
	<link href="<c:url value='/img/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
	<link href="<c:url value='/css/comuns.css'/>" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value='/css/coordenades.css'/>" rel="stylesheet" type="text/css" media="screen" />
	
	<script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/error.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/comuns.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/detall_base.js'/>"></script>
    <jsp:include page="../layout/variablesGlobalsJavascript.jsp" flush="true"/> 
    
    <script src="http://maps.google.com/maps?file=api&amp;v=2.x&amp;key=<c:out value='${googleMapKey }' />" type="text/javascript"></script>

   	<script type="text/javascript">
   	<!--

	    var map = null;
	    var geocoder = null;
        var marcadorEdificio = null;

        function initialize() {
            if (GBrowserIsCompatible()) {
		        var b_buscadireccion = true;
		        map = new GMap2(document.getElementById("map_canvas"));
		        var iniLat = "<c:out value='${latEdi}'/>";
		        var iniLng = "<c:out value='${lngEdi}'/>";
		        
		        if (iniLat.length == 0 || iniLng.length == 0) {
		            iniLat="39.5690036";
		            iniLng="2.6436571";
		        } else {
		            b_buscadireccion = false;       
		        }
		        $('#lat').val(iniLat);
		        $('#lng').val(iniLng);
      
		        map.setCenter(new GLatLng(iniLat, iniLng), 16);
		        geocoder = new GClientGeocoder();
		        
		        map.addControl(new GSmallMapControl());
		        var bottomLeft = new GControlPosition(G_ANCHOR_BOTTOM_LEFT, new GSize(10,10));
		        map.addControl(new GMapTypeControl(), bottomLeft); 
		
		        var latlng = new GLatLng(iniLat, iniLng);
		        creaMarker(latlng);
		        if (b_buscadireccion){
			        showAddress();
		        }
		    }
	    }
        
	    function creaMarker(point){
	        var marker = new GMarker(point,{draggable:true});
	        marker.enableDragging();            
	        map.addOverlay(marker);
	        marker.setImage("<c:url value='/img/gps/gps_caib.png' />");
	        marcadorEdificio = marker;
	        
	        GEvent.addListener(marcadorEdificio , "dragend", function() {
	            var point = marker.getPoint();

	            $('#lat').val(point.lat());
                $('#lng').val(point.lng());

	        });
	    }

	    function showAddress() {
	    	address = $('#address').val();
	        
		    if (geocoder) {
		        geocoder.getLatLng(
		          address,
		          function(point) {
		            if (!point) {
		            	Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: "<spring:message code='coordenada.missatge.titol'/>", text: "<spring:message code='coordenada.missatge.text'/>"}); 
		            } else {
		            	$('#lat').val(point.lat());
		                $('#lng').val(point.lng());
		                
		                map.setCenter(point, map.getZoom());
		                if (marcadorEdificio == null) 
		                {
		                    creaMarker(point);
		                } else {
		                    var marker = marcadorEdificio;
		                    map.setCenter(point, map.getZoom());
		                    marker.setPoint(point);
		                }
		            }
		          }
		        );
		    }
		}
    
        function centrar(){
            marcadorEdificio.setPoint(map.getCenter());
        }    
   
        function aceptar(){
        	lat = $('#lat').val();
            lng = $('#lng').val();
            console.log('lat',lat);
            console.log('lng',lng);

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

<body onload="initialize()" onunload="GUnload()">
	<div id="escript">
	   <!-- escriptori_detall -->
	   <div id="escriptori_detall" class="escriptori_detall" >
            <c:choose>
                <c:when test="${not empty googleMapKey}">
                    <div id="cercador" class="modul " >
                        <fieldset>
	                        <div class="modul_continguts mostrat">
	                            <input type="hidden" size="10" name="lat" id="lat" value="" />
	                            <input type="hidden" size="10" name="lng" id="lng" value="" />
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
	                                            <a href="javascript:showAddress(); " class="btn consulta"><span><span><spring:message code='boto.cerca_direccio'/></span></span></a>
	                                        </div>
                                            <div class="btnGenerico">
                                                <a href="javascript:centrar();" class="btn noIcona"><span><span><spring:message code='boto.centra_marca'/></span></span></a>
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
		                                    <a href="javascript:aceptar()" class="btn noIcona"><span><span><spring:message code='boto.acepta_coordenades'/></span></span></a>
		                                </div>
		                                <div class="btnGenerico">
		                                    <a href="javascript:borrar()" class="btn borrar"><span><span><spring:message code='boto.esborra_coordenades'/></span></span></a>
		                                </div>
		                                <div class="btnGenerico">
		                                    <a href="javascript:borrarPopUp('popCoordenades')" class="btn torna"><span><span><spring:message code='boto.torna'/></span></span></a>
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
