<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<html:xhtml/>
<div class="bloc" style="width: 800px;">
    <h1><bean:message key="microsites.lista" /></h1>
    <h2><bean:message key="microsites.datos" /></h2>
<br />

<logic:empty name="micrositeOptions">   
        <br /><h2>
        	<bean:message key="microsites.vacio" />
        </h2><br />
</logic:empty>

<logic:notEmpty name="micrositeOptions">
 
        <logic:iterate id="micro" name="micrositeOptions">
        <div class="component">
            <div class="textconsulta1" style="width: 520px;">
                <strong><bean:write name="micro" property="traduccion.titulo"/></strong><logic:notEmpty name="micro" property="nombreUA"> <bean:message key="microsites.ua" /> <strong><bean:write name="micro" property="nombreUA"/></strong></logic:notEmpty>
            </div> 
            <div class="botoneraconsulta2" style="left: 516px;width: 276px;">

            	<html:button property="selecciona">
				<bean:message key="boton.eliminar" />"
					onclick="borrar(<bean:write name="micro" property="id"/>)
			</html:button>  	

			<html:button property="selecciona">
				<bean:message key="boton.exportar" />"
 					onclick="exportar(<bean:write name="micro" property="id"/>)
			</html:button>  

            	<html:button property="selecciona">
				<bean:message key="boton.seleccionar" />"
					onclick="abrir(<bean:write name="micro" property="id"/>)
			</html:button>  	


            </div>    
        </div>
        </logic:iterate>
    
</logic:notEmpty>
</div>
<br />

<script type="text/javascript">
<!--
    function abrir(id) {
     location.href="/sacmicroback/index.do?idsite="+id;
    } 
    function exportar(id) {
     location.href="/sacmicroback/home.do?accion=exportar&idsite="+id;
    } 
    function borrar(id) {
    	if (confirm('<bean:message key="alerta.baja" />'))
     		location.href="/sacmicroback/home.do?accion=eliminar&idsite="+id;
    } 

    
//--> 

</script>
