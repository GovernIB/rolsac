<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html locale="true" xhtml="true">
<head>
   <title>SAC Procedimiento - POPUP</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />

   <script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
   <script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>

   <script type="text/javascript">
    <!--
        // Llamada al popup para seleccionar la fecha de un campo
       function abrirCalendario(field) {
            obrirFixa("<html:rewrite page='/moduls/calendario.do'/>?field=" + field);
       }

        // Llamada para el popup de calendario para fijar la fecha
       function seleccionaDia(data, field) {
            eval("document.forms[0]." + field + ".value=data");
       }

       function relaciona(id, nombre){
          opener.asignarNormativa(id, nombre);
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

      -->
   </script>
</head>

<body>
    <div id="organigrama">
        <div id="capsalera">
            <h1><bean:message key="normativa.busqueda" /></h1>
        </div>

    </div>
    <br />
    <html:form action="/normativa/busqueda" styleId="busquedaNormativaForm" >
        <div class="bloc">
            <div class="component">
			    <div class="etiqueta"><bean:message key="normativa.fecha" /></div>
			    <html:text styleClass="ctext" property="textoFecha"/>
                <div class="botoneraconsulta1">
                    <html:button property="boton" onclick="abrirCalendario('textoFecha')" ><bean:message key="boton.seleccionar" /></html:button>
                </div>
		    </div>
            <div class="component">
                <div class="etiqueta"><bean:message key="normativa.fechaBoletin" /></div>
                <html:text styleClass="ctext" property="textoFechaBoletin"/>
                <div class="botoneraconsulta1">
                    <html:button property="boton" onclick="abrirCalendario('textoFechaBoletin')" tabindex="11"><bean:message key="boton.seleccionar" /></html:button>
                </div>
            </div>

            <div class="component">
                <div class="etiqueta"><bean:message key="normativa.titulo" /></div>
                <html:text styleClass="ctext" property="titulo"  />
            </div>
        </div>
        <center>
        <div class="botonera">
             <html:submit property="action" styleClass="dreta" ><bean:message key="boton.busqueda" /></html:submit>
        </div>
        </center>
    </html:form>
    <logic:notEmpty name="normativas">
        <br />
        <logic:iterate id="normativa" name="normativas">
            <div id="llistat">
               <ul>
                   <bean:define id="idNorm" name="normativa" property="id"/>
                   <bean:define id="nombre" name="normativa" property="traduccion.titulo" type="java.lang.String"/>
                   <li><html:link page="#" onclick='<%="relaciona("+idNorm+",'"+ StringEscapeUtils.escapeJavaScript(nombre.replace( "\\"", ""))+"')"%>'>
                        <html:img page="/img/trasparent.gif" width="15" height="9" border="0" /><bean:write name="normativa" property="traduccion.titulo"/>
                    </html:link></li>
               </ul>
            </div>
        </logic:iterate>
    </logic:notEmpty>
</body>
</html:html>