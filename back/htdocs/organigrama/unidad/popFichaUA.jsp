<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<html:html locale="true" xhtml="true">
<head>
   <title>Ficha - Seccion - Unidad Administrativa</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
   <script type="text/javascript">
   <!--
        var poprelacion;

        function popSeccion(){
            poprelacion = obrir("<html:rewrite page='/sistema/seccion/arbol.do'/>?idSeccion=0&action=&ficha=true", "<bean:message key='boton.relacionar' />", 538, 440);
        }

        function actualizaSeccion(id, nombre){
            document.forms[0].idSeccion.value = id;
            document.forms[0].seccion.value = nombre;
        }

        function actualizaFicha(id, nombre){
            document.forms[0].idFicha.value = id;
            document.forms[0].ficha.value = nombre;
        }

 		function relaciona(){
            idSeccion = document.forms[0].idSeccion.value;
            idFicha = document.forms[0].idFicha.value;
            if (idSeccion != "" && idFicha != ""){
                opener.asignarFichaUA(idSeccion, idFicha);
            }
            window.close();
        }

        function closeAll(){
            if (poprelacion != null)
                poprelacion.close();
            window.close();
        }
        
        function popFicha(){
            poprelacion = obrirScroll("<html:rewrite page='/organigrama/unidad/popupfichas.do'/>", "<bean:message key='boton.relacionar' />", 538, 440);
        }
        
        
   -->
   </script>
</head>
<body>

<div id="organigrama">
    <div id="capsalera">
        <h1><bean:message key="ua.relacion.seccion" /></h1>
    </div>
    <br />

        <form>
            <input type="hidden" name="idSeccion" value="" />
            <input type="hidden" name="idFicha" value="" />
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="seccion.nombre" /></div>
                    <input type="text" class="ctext" readonly="readonly" name="seccion" value='<bean:message key="seccion.selecciona"/>' />
                    <div class="botoneraconsulta1">
                        <input type="button" value="<bean:message key="boton.seleccionar" />" onclick="popSeccion()" />
                    </div>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="ficha.titulo" /></div>
    	            <input type="text" class="ctext" readonly="readonly" name="ficha" value='<bean:message key="ficha.selecciona"/>' />
                    <div class="botoneraconsulta1">
                        <input type="button" value="<bean:message key="boton.seleccionar" />" onclick="popFicha()" />
                    </div>
                </div>
            </div>
            <center>
            <div class="botonera">
                <input type="button" value="<bean:message key="boton.relacionar" />" onclick="relaciona()" />
                <input type="button" value="<bean:message key="boton.cerrar" />" onclick="closeAll()" />
            </div>
            </center>
        </form>

</div>
</body>
</html:html>
