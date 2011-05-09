<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:html locale="true" xhtml="true">
<head>
   <title>SAC Unidad - Usuario</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        function relaciona(){
            id = document.forms[0].usuario.options[document.forms[0].usuario.selectedIndex].value;
            opener.asignarUsuario(id);
            window.close();
        }
   -->
   </script>
</head>
<body>
<div id="organigrama">
    <div id="capsalera">
            <h1><bean:message key="usuario.lista" /></h1>
    </div>
    <br />
    <logic:empty name="usuarioOptions">
        <center>
            <br /><bean:message key="usuario.vacio" /></h2><br />
            <div class="botonera">
    </logic:empty>

    <form>
    <logic:notEmpty name="usuarioOptions">
            <div class="bloc">
            <div class="component">
                <div class="etiqueta">
                    <bean:message key="usuario.usuario" />
                </div>
                <select name="usuario" >
                    <logic:iterate id="usuario" name="usuarioOptions" >
                        <option value='<bean:write name="usuario" property="id" />'>
                            <bean:write name="usuario" property="nombre"/> (<bean:write name="usuario" property="username" />)
                        </option>
                    </logic:iterate>
                    <logic:notEmpty name="usuarioOptionsSuper">
	                    <logic:iterate id="usuario" name="usuarioOptionsSuper" >
	                        <option value='<bean:write name="usuario" property="id" />'>
	                            <bean:write name="usuario" property="nombre"/> (<bean:write name="usuario" property="username" />)
	                        </option>
	                    </logic:iterate>                    
				    </logic:notEmpty>                    
                    <logic:notEmpty name="usuarioOptionsOper">
	                    <logic:iterate id="usuario" name="usuarioOptionsOper" >
	                        <option value='<bean:write name="usuario" property="id" />'>
	                            <bean:write name="usuario" property="nombre"/> (<bean:write name="usuario" property="username" />)
	                        </option>
	                    </logic:iterate>                    
				    </logic:notEmpty>                    
                </select>
            </div>
            </div>
        <center>
        <div class="botonera">
            <input type="button" value="<bean:message key="boton.relacionar" />" onclick="relaciona()" />

    </logic:notEmpty>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </div>
    </center>
    </form>

</div>
</body>
</html:html>
