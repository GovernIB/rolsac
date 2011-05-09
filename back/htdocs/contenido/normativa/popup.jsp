<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<bean:define id="opcion" name="opcion" />
<html:html locale="true" xhtml="true">
<head>
   <title>SAC Normativa - POPUP</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        function busca(){
            id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
            nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
            opener.buscarDato(id, '<%=opcion%>');
            window.close();
        }
        <logic:notEqual name="opcion" value="procedimiento">
        <logic:notEqual name="opcion" value="Micro">
            function relaciona(){
                id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
                nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
                opener.actualizaDato(id, nombre, '<%=opcion%>');
                window.close();
            }
        </logic:notEqual>
        </logic:notEqual>
        <logic:equal name="opcion" value="procedimiento">
            function relaciona(){
                id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
                nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
                opener.asignarOpcion(id);
                window.close();
            }
        </logic:equal>
        <logic:equal name="opcion" value="Micro">
            function relaciona(){
                id = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].value;
                nombre = document.forms[0].elemento.options[document.forms[0].elemento.selectedIndex].text;
                opener.actualizaDato(id, nombre);
                window.close();
            }
        </logic:equal>
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
        <logic:equal name="opcion" value="Boletin">
            <h1><bean:message key="boletin.lista" /></h1>
        </logic:equal>
        <logic:equal name="opcion" value="Tipo">
            <h1><bean:message key="tipo.lista" /></h1>
        </logic:equal>
        <logic:equal name="opcion" value="procedimiento">
            <h1><bean:message key="procedimiento.lista" /></h1>
        </logic:equal>
        <logic:equal name="opcion" value="Micro">
            <h1><bean:message key="microsites.lista" /></h1>
        </logic:equal>
    </div>
    <br />

    <logic:empty name="elementOptions">
        <center>
            <br /><bean:message key="normativa.relacion.vacio" /></h2><br />
            <div class="botonera">
    </logic:empty>

    <form>
    <logic:notEmpty name="elementOptions">
            <div class="bloc">
            <div class="component">
                <div class="etiqueta">
                    <logic:equal name="opcion" value="Boletin">
                        <bean:message key="boletin.nombre" />
                    </logic:equal>
                    <logic:equal name="opcion" value="Tipo">
                        <bean:message key="tipo.nombre" />
                    </logic:equal>
                    <logic:equal name="opcion" value="procedimiento">
                        <bean:message key="procedimiento.nombre" />
                    </logic:equal>
                    <logic:equal name="opcion" value="Micro">
                        <bean:message key="microsites.nombre" />
                    </logic:equal>
                </div>
                <select name="elemento" >
                    <logic:iterate id="option" name="elementOptions" >
                    	<logic:equal name="opcion" value="Micro">
                        	<option value='<bean:write name="option" property="claveunica" />'>
	                        	<bean:write name="option" property="traduccion.titulo" />
	                        </option>
                    	</logic:equal>
                    	<logic:notEqual name="opcion" value="Micro">
	                        <option value='<bean:write name="option" property="id" />'>
	                            <logic:present name="option" property="traduccion">
	                                <bean:write name="option" property="traduccion.nombre" />
	                            </logic:present>
	                            <logic:notPresent name="option" property="traduccion">
	                                <bean:write name="option" property="nombre" />
	                            </logic:notPresent>
	                        </option>
	                    </logic:notEqual>
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
