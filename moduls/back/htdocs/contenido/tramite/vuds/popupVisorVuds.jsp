<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ca" lang="ca">

  <head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	    <title>Tramite de Ventanilla Unica</title>
		
		<!-- Informacio de referencia -->
		
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="/sacback/css/estils.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="/sacback/css/estils_print.css" media="print" />				
		
		<script type="text/javascript" src="/root/js/comuns.js"></script>
		<!--[if lt IE 7]>
		<script type="text/javascript" src="/root/js/accessosDirectes.js"></script>
		<![endif]-->
		
  </head>
	
  <body>
  	
  		<br>
		<br>
  		<h2> Els següents camps no han estat traduits: </h2>
		<br>
		<br>

					<logic:present name="sinTraducir">
					<logic:iterate id="campo" name="sinTraducir">
						<li>
						<p><bean:write name="campo"/></p>
						</li>
					</logic:iterate>
					</logic:present>
		
		
		
		<br>
		<br>
		<h2> La següent informació será enviada al servidor de la finestreta única: </h2>
		<br>
		<br>
		<!-- contenidor -->
		<div id="contenidor">
		
			
			<!-- continguts -->
			<div id="continguts">
			
				<!-- columna esquerra -->
				<div class="esquerra">
					
				
				</div>
				<!-- /columna esquerra -->
				
				<!-- columna central -->
				<div class="centreDreta">			
	
				<!-- fitxa procediment -->
				<div id="fitxaProcediment">

					<h3>Código Vuds</h3>
					<p style="margin: 5px;">
					<logic:present name="tramiteVuds">
					<bean:write name="tramiteVuds" property="descripcionTramiteVuds"/>  (codi <bean:write name="tramiteVuds" property="idTramiteVuds"/>)
					</logic:present>
					<logic:notPresent name="tramiteVuds">
						vacío
					</logic:notPresent>
					</p>

					<br>
					<h3>Tipo De Registro</h3>
					<p style="margin: 5px;"><bean:write name="tipoRegistro"/>  </p>

					<br>
					<h3>Organismo Competente</h3>
					<p style="margin: 5px;"><bean:write name="organismoCompetente" property="descripcionOrganismo" />  (codi <bean:write name="organismoCompetente" property="idOrganismo" />)</p>
							
					<br>
					<h3>Area Tramitadora</h3>
					<p style="margin: 5px;"><bean:write name="areaTramitadora"/>  </p>
					
					<br>
					<h3>Canal Tramitacion</h3>
					<p style="margin: 5px;"><bean:write name="canalTramitacion" property="descripcionCanal" />  (codi <bean:write name="canalTramitacion" property="idCanal" />)</p>
					
					<br>
					<h3>codigo Identificador</h3>
					<p style="margin: 5px;"><bean:write name="codigoIdentificador"/></p>
					
					<br>
					<h3>denominación Trámite</h3>
					<p style="margin: 5px;"><bean:write name="denominacionTramite"/></p>
					
					<br>
					<h3>descripcion Trámite</h3>
					<p style="margin: 5px;">
					<logic:present name="descripcionTramite"><bean:write name="descripcionTramite"/>
					</logic:present>
					<logic:notPresent name="descripcionTramite">
					- vacío -
					</logic:notPresent>
					</p>
					
					<br>
					<h3>Forma Iniciación</h3>
					<p style="margin: 5px;">
					<logic:present name="formaIniciacion"><bean:write name="formaIniciacion" property="descripcionFormaIniciacion"/> (codi <bean:write name="formaIniciacion" property="idFormaIniciacion"/>)
					</logic:present>
					<logic:notPresent name="formaIniciacion">
					- vacío - 
					</logic:notPresent>
					</p>

					<br>
					<h3>observaciones</h3>
					<p style="margin: 5px;">
					<logic:present name="observaciones">
					<bean:write name="observaciones"/>
					</logic:present>
					<logic:notPresent name="observaciones">
					- vacío -
					</logic:notPresent>
					</p>
					
					
					<br>
					<h3>Plazos Legales</h3>
					<p style="margin: 5px;">
					<logic:present name="plazosLegales">
						<bean:write name="plazosLegales"/>
					</logic:present>
					<logic:notPresent name="plazosLegales">
					- vacío - 
					</logic:notPresent>
					</p>
					
					<br>
					<h3>Tasa</h3>
					<p style="margin: 5px;">
					<logic:present name="tasa">
					<li>
					codi: <bean:write name="tasa" property="codificacion"/>
					</li>
					<li>
					descripcio:<bean:write name="tasa" property="descripcionTasa"/>
					</li>
					<li>
					forma pagament: <bean:write name="tasa" property="modoPago"/>
					</li>
					</logic:present>
					<logic:notPresent name="tasa">
					- vacío -
					</logic:notPresent>
					</p>
					
					<br>
					<h3>Tiempo Resolucion</h3>
					<p style="margin: 5px;">
					<logic:present name="tiempoResolucion">
						<bean:write name="tiempoResolucion"/>
					</logic:present>
					<logic:notPresent name="tiempoResolucion">
					- vacío - 
					</logic:notPresent>
					</p>
					
					<br>
					<h3>Tipo Registro</h3>
					<p style="margin: 5px;">
					<logic:present name="tipoRegistro">
					<bean:write name="tipoRegistro"/>
					</logic:present>
					<logic:notPresent name="tipoRegistro">
					- vacío -
					</logic:notPresent>
					</p>
					
					<br>
					<h3>Tipologia Trámite</h3>
					<p style="margin: 5px;">
					<logic:present name="tipologia">
						<bean:write name="tipologia"/>
					</logic:present>
					<logic:notPresent name="tipologia">
					- vacío -
					</logic:notPresent>
					</p>
			
					<br>
					<h3>Enlace Consulta</h3>
					<p style="margin: 5px;">
					<logic:present name="enlaceConsulta">
					<a href="<bean:write name='enlaceConsulta'/>"><bean:write name='enlaceConsulta'/></a>
					</logic:present>
					<logic:notPresent name="enlaceConsulta">
					-
					</logic:notPresent>
					</p>
			
					<br>
					<h3>Resultado</h3>
					<p style="margin: 5px;">
					<logic:present name="resultado">
					<bean:write name="resultado"/>
					</logic:present>
					<logic:notPresent name="resultado">
					-
					</logic:notPresent>
					</p>

					<br>
					<h3>Formularios</h3>
					<logic:present name="formularios">
					<logic:iterate id="formulari" name="formularios">
						<li>
						<p><a href="<bean:write name="formulari" property='urlDescarga'/>"><bean:write name="formulari" property='urlDescarga'/></a>    (codi <bean:write name="formulari" property="idCodificacion"/>)
						<p><bean:write name="formulari" property="descripcionFormulario"/></p>
						</li>
					</logic:iterate>
					</logic:present>

					<br>
					<h3>Requisitos Previos</h3>
					<logic:present name="requisitos">
					<logic:iterate id="requisito" name="requisitos">
						<li>
						<p><bean:write name="requisito"/></p>
						</li>
					</logic:iterate>
					</logic:present>

					<br>
					<h3>Documentación a Presentar</h3>
					<logic:present name="documentos">
					<logic:iterate id="documento" name="documentos">
						<li>
						<p><bean:write name="documento"/></p>
						</li>
					</logic:iterate>
					</logic:present>
					
				</div>
				<!-- /fitxa procediment -->
		
				</div>
				<!-- /columna central -->

			</div>
			<!-- /continguts -->
			
		</div>
		<!-- /contenidor -->
	
  </body>
	
</html>
  