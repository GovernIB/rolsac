<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<html:html locale="true" xhtml="true">
<head>
   <title><bean:message key="vuds.title" /></title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
          function carregar(id, nom){
            opener.actualitzaCodiVuds(id, nom);
            window.close();
        }

		function informaConsulta(){
			//document.forms.estat.nodeValue="consultant";
			
			document.getElementById("estat").innerHTML="<bean:message key='tramite.codisVuds.consultant' />";
		}
  		
   -->
   </script>
</head>

<body>

<div id="organigrama">
    <div id="capsalera">
        <h1><bean:message key="tramite.codiVuds.busqueda" /></h1>
    </div>
</div>
<br/>

<html:form action="/contenido/tramite/vuds/buscarCodis.do" >
    <div class="bloc">
        <div class="component">
			<div class="etiqueta" title="filtre de búsqueda. Si el camp està buit busca tots els codis"><bean:message key="tramite.codisVuds.busqueda.texte" /></div>
			<html:text styleClass="ctext" property="filtre"  />
	        <div class="botoneraconsulta1">
             <html:submit property="action" styleClass="dreta" onclick="informaConsulta()"><bean:message key="boton.busqueda" /></html:submit>
			</div>
		</div>
    </div>

	   
	<ul><div id="estat"></div></ul>
	
	<logic:present name="alert">
		<bean:write name="alert"/>
	</logic:present>
	
	<logic:notPresent name="alert">

		<logic:present name="codisTram">
			<bean:size id="ncodis" name="codisTram" />

			<script type="text/javascript">	
			document.getElementById("estat").innerHTML="<bean:write name="ncodis"/> <bean:message key="tramite.codisVuds.trobats" />."
		</script>

			<logic:notEqual value="0" name="ncodis">
				<br />
				<div id="llistat">
				<ul>
					<logic:iterate id="codi" name="codisTram">
						<li><a><img
							src='<html:rewrite page="/img/trasparent.gif" />' width=15
							height=9 border=0 /></a><a
							href='javascript:carregar("<bean:write name="codi" property="idTramiteVuds"/>","<bean:write name="codi" property="descripcionTramiteVuds"/>")'><bean:write
							name="codi" property="idTramiteVuds" /> - <bean:write name="codi"
							property="descripcionTramiteVuds" /></a></li>
					</logic:iterate>
				</ul>
				</div>
			</logic:notEqual>
		</logic:present>

	</logic:notPresent>
	
    
    <div class="botonera"><center>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </center></div>

	</html:form>

</body>
</html:html>