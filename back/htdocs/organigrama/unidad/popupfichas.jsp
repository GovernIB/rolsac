<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html locale="true" xhtml="true">
<head>
   <title>SAC Ficha - POPUP</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />

   <script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
   <script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>

   <script type="text/javascript">
    <!--
       function relaciona(id, nombre) {
			opener.actualizaFicha(id, nombre);
          	window.close();
       }

       // Funcio per mostrar una capa i ocultar la resta
	   function activarCapa(capa, n) {
            for (i = 0; i < n; i++) {
                capeta = document.getElementById("capa" + i);
                if (i == capa) {
                    capeta.style.visibility="visible";
                } else {
                    capeta.style.visibility="hidden";
                }
            }
	   }

		var mensa = "<bean:message key='ficha.busqueda.check'/>";
		function checkBuscarFichas() {
			if ( document.getElementById("titulo").value.length>=3 || document.getElementById("descripcion").value.length>=3 )
				document.getElementById("busquedaFichaForm").submit();
			else 
				alert(mensa);
		}
      -->
   </script>
</head>

<body>
    <div id="organigrama">
        <div id="capsalera">
            <h1><bean:message key="ficha.popup.busqueda" /></h1>
        </div>

    </div>
    <br />
    <html:form action="/ficha/busqueda" styleId="busquedaFichaForm" >
        <div class="bloc">
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.titulo" /></div>
                <html:text styleId="titulo" styleClass="ctext" property="titulo" />
            </div>
            <div class="component">
                <div class="etiqueta"><bean:message key="ficha.descabr" /></div>
                <html:text styleId="descripcion"  styleClass="ctext" property="descripcion" />
            </div>
        </div>
        <center>
        <div class="botonera">
             <html:button property="action" styleClass="dreta" onclick="checkBuscarFichas();"><bean:message key="boton.busqueda" /></html:button>
        </div>
        </center>
    </html:form>
    
    <logic:notEmpty name="fichas">
        <br />
        <logic:iterate id="ficha" name="fichas">
            <div id="llistat">
               <ul>
                   <bean:define id="idFicha" name="ficha" property="id"/>
                   <bean:define id="nombre" name="ficha" property="traduccion.titulo" type="java.lang.String"/>
                   <li><html:link page="#" onclick='<%="relaciona("+idFicha+",'"+ StringEscapeUtils.escapeJavaScript(nombre.replace( "\\"", ""))+"')"%>'>
                        <html:img page="/img/trasparent.gif" width="15" height="9" border="0" /><bean:write name="ficha" property="traduccion.titulo"/>
                    </html:link></li>
               </ul>
            </div>
        </logic:iterate>
    </logic:notEmpty>
    
    <logic:present name="fichas">
    	<logic:empty name="fichas">
	    	<bean:message key="ficha.busqueda.nofichas"/>
	    </logic:empty>
	</logic:present>
    
</body>
</html:html>
