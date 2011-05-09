<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>

<bean:define id="iniDir" name="dirEdi" type="java.lang.String" />
<bean:define id="host" name="host" type="java.lang.String" />

<bean:define id="key" value="" />
<logic:present name="googleMapKey">
    <bean:define id="key" name="googleMapKey" type="java.lang.String" />
</logic:present>


<bean:define id="iniLat" value="39.5690036"/>
<bean:define id="iniLng" value="2.6436571"/>
<logic:present name="latEdi">
	<bean:define id="iniLat" name="latEdi" type="java.lang.String" />
</logic:present>
<logic:present name="lngEdi">
	<bean:define id="iniLng" name="lngEdi" type="java.lang.String" />
</logic:present>




<html:html locale="true" xhtml="true">
<logic:empty name="googleMapKey">
    <div class="bloc">
        <h2><bean:message key="edificio.googlemapkey.undefined"/> </h2>
    </div>
</logic:empty>
<logic:notEmpty name="googleMapKey">

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>Selector Coordenadas</title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2.x&amp;key=<%=key%>" type="text/javascript"></script>
    <script type="text/javascript">
    var context = '\<%=request.getContextPath()%>';
    var map = null;
    var geocoder = null;
    var marcadorEdificio = null;

    function initialize() {
      if (GBrowserIsCompatible()) {
      	var b_buscadireccion = true;
        map = new GMap2(document.getElementById("map_canvas"));
        var iniLat = "<%=iniLat%>";
        var iniLng = "<%=iniLng%>";
        if (iniLat.length == 0 || iniLng.length == 0) {
			iniLat="39.5690036";
			iniLng="2.6436571";
	    } else
	    {
			b_buscadireccion = false;	    
	    }
       	document.form.lat.value = iniLat;
      	document.form.lng.value = iniLng;	    
        map.setCenter(new GLatLng(iniLat, iniLng), 16);
        geocoder = new GClientGeocoder();
        
        map.addControl(new GSmallMapControl());
        var bottomLeft = new GControlPosition(G_ANCHOR_BOTTOM_LEFT, new GSize(10,10));
        map.addControl(new GMapTypeControl(), bottomLeft); 

        var latlng = new GLatLng(iniLat, iniLng);
        creaMarker(latlng);
        if (b_buscadireccion) showAddress();
      }
    }
	function creaMarker(point){
		var marker = new GMarker(point,{draggable:true});
		marker.enableDragging();			
		map.addOverlay(marker);
		marker.setImage('<%=host%>'+context+'/img/iconos/ico_marcadorGoogleMap.gif');
		marcadorEdificio = marker;
		
		GEvent.addListener(marcadorEdificio , "dragend", function() {
			var point = marker.getPoint();
			document.form.lat.value = point.lat();
			document.form.lng.value = point.lng();
		});
	}
    function showAddress() {
      address = document.form.address.value;
      if (geocoder) {
        geocoder.getLatLng(
          address,
          function(point) {
            if (!point) {
              	alert("L'adreça no ha estat trobada. Revisi-la i torni a sol·licitar la localització. Format adequat [Carrer, CP, Ciutat, País]");
            } else {
	      		document.form.lat.value = point.lat();
	      		document.form.lng.value = point.lng();
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
    
   <!--
   
        function centrar(){
            marcadorEdificio.setPoint(map.getCenter());
        }    
   
        function aceptar(){
            lat = document.form.lat.value;
            lng = document.form.lng.value;
            opener.actualizarCoordenadas(lat,lng);
            window.close();
        }

        function borrar(){
        	opener.actualizarCoordenadas("","");
            window.close();
        }         
        
        function cancelar(){
            window.close();
        }        
   -->    
    
    </script>
  </head>

  <body onload="initialize()" onunload="GUnload()">
    <form id="form" name="form" action="#" onsubmit="showAddress(); return false">
      <p>
        <input type="text" size="55" name="address" value="<bean:write name="iniDir"/>" />
		<input type="hidden" size="10" name="lat" value="" />
		<input type="hidden" size="10" name="lng" value="" />
        <input type="submit" value="Cerca Direcci&oacute;" />    
        <div align="center"><input type="button" value="Centrar Marca" onclick="centrar()"/></div>
      </p>
      <div id="map_canvas" style="width: 530px; height: 300px"></div>
      <br/>
      <input type="button" value="Acceptar coordenades " onclick="aceptar()"/>
      <input type="button" value="Esborrar coordenades " onclick="borrar()"/>      
      <input type="button" value="Cancel·lar" onclick="cancelar()"/>
    </form>
  </body>
</logic:notEmpty>
</html:html>
