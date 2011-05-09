<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<bean:define id="opcion" name="opcion" />
<html:html locale="true" xhtml="true">
<head>
   <title>SAC Procedimiento - POPUP</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        <logic:equal parameter="opcion" value="Familia">
            function relaciona(){
                id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
                nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
                opener.actualizaDato(id, nombre, '<%=opcion%>');
                window.close();
            }
        </logic:equal>
        <logic:notEqual parameter="opcion" value="Familia">
            function relaciona(){
                id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
                nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
                opener.asignarOpcion(id, '<%=opcion%>');
                window.close();
            }
        </logic:notEqual>
         <logic:equal parameter="opcion" value="Iniciacion">
            function relaciona(){
                id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
                nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
                opener.actualizaDato(id, nombre, '<%=opcion%>');
                window.close();
            }
        </logic:equal>
        
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
        <logic:equal name="opcion" value="Familia">
            <h1><bean:message key="familia.lista" /></h1>
        </logic:equal>
        <logic:equal name="opcion" value="normativa">
            <h1><bean:message key="normativa.lista" /></h1>
        </logic:equal>
        <logic:equal name="opcion" value="materia">
            <h1><bean:message key="materia.lista" /></h1>
        </logic:equal>
        <logic:equal name="opcion" value="Iniciacion">
            <h1><bean:message key="procedimiento.iniciacion.lista" /></h1>
        </logic:equal>
    </div>
    <br />

    <logic:empty name="elementOptions">
        <center>
            <br /><h2><bean:message key="procedimiento.relacion.vacio" /></h2><br />
            <div class="botonera">
    </logic:empty>

    <form>
    <logic:notEmpty name="elementOptions">
            <div class="bloc">
            <div class="component">
                <div class="etiqueta">
                    <bean:message key="familia.nombre" />
                </div>
                <select name="elemento" >
                    <logic:iterate id="option" name="elementOptions" >
                        <option value='<bean:write name="option" property="id" />'>
                         <logic:notEqual name="opcion" value="Iniciacion">
                                <logic:present name="option" property="traduccion.nombre">
                                    <bean:write name="option" property="traduccion.nombre" />
                                </logic:present>
                                <logic:present name="option" property="traduccion.titulo">
                                    <bean:write name="option" property="traduccion.titulo" />
                                </logic:present>
                                <logic:notPresent name="option" property="traduccion">
                                     <bean:write name="option" property="nombre" />
                                 </logic:notPresent>
                         </logic:notEqual>  
                         <logic:equal name="opcion" value="Iniciacion"> 
                                <logic:present name="option" property="traduccion">
                                    <bean:write name="option" property="traduccion.nombre" />
                                </logic:present>
                         </logic:equal>                           
                        </option>
                    </logic:iterate>
                </select>
            </div>
            </div>
        <center>
        <div class="botonera">
            <input type="button" value="<bean:message key="boton.seleccionar" />" onclick="relaciona()" />
    </logic:notEmpty>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </div>
    </center>
    </form>
</div>
</body>
</html:html>
