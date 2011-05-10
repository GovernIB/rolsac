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
		
		<style type="text/css">
				body {background-color: #EBEAD3 }
				p {margin-left: 20px}
		</style>
		
		
		<script type="text/javascript" src="/root/js/comuns.js"></script>
		<!--[if lt IE 7]>
		<script type="text/javascript" src="/root/js/accessosDirectes.js"></script>
		<![endif]-->
		
  </head>
	
  <body>
  	
  		<br>
		<br> 
		<h4> <img src="/sacback/img/fichaNoP.gif"> Aquest és un tràmit destinat a la Finestra Unica. Falten traduir-se al castellà els camps següents: </h4>
		<br>

					<logic:present name="sinTraducir">
					<logic:iterate id="campo" name="sinTraducir">
						<li>
						<p><bean:write name="campo"/></p>
						</li>
					</logic:iterate>
					</logic:present>
		
		
	
	
  </body>
	
</html>
  