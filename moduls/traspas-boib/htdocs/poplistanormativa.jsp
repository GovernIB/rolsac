<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Traspas de normatives del BOIB</title>
<link rel="stylesheet" href='<html:rewrite page="/css/styleA.css"/>' type="text/css" />
<SCRIPT>
// PONER TROZO JAVASCRIPT METIDO EN EL REQUEST
<bean:write name="APPTRS_ArticulosListadojs"  filter ="false"   />


/*
// Una vez dejado caer el bean anterior, seguirá esta estructura
var articulo = new Array(3);
var articuloidiomac=new Array(3);//idioma castellano
var articuloidiomav=new Array(3);//idioma catalan


 articulo[0] = new Array("columna1","columna2","columna3","columna5","columna6","columna7","columna8");
 articulo[2] = new Array("columna1","columna2","columna3","columna4","columna4","columna4","columna4");
 articulo[1] = new Array("columna1","columna2","columna3","columna4","columna4","columna4","columna4");

 articuloidiomac[0] = new Array("columna1","columna2","columna3","columna5","columna6");
 articuloidiomac[2] = new Array("columna1","columna2","columna3","columna4","columna4");
 articuloidiomac[1] = new Array("columna1","columna2","columna3","columna4","columna4");

 articuloidiomav[0] = new Array("columna1","columna2","columna3","columna5","columna6");
 articuloidiomav[2] = new Array("columna1","columna2","columna3","columna4","columna4");
 articuloidiomav[1] = new Array("columna1","columna2","columna3","columna4","columna4");

*/

//funcion que mete todos los valores en el formulario de la pagina padre
function rellenaForm(valor) {
  elvalor = Number(valor);

  //es algo así. falta por definir los campos del formulario
  //validacion,idUA,nombreUA  no se pasan
  window.opener.document.EditarActionForm.idTipo.value= articulo[elvalor][0];
  window.opener.document.EditarActionForm.nombreTipo.value= articulo[elvalor][1];
  window.opener.document.EditarActionForm.idBoletin.value= articulo[elvalor][2];
  window.opener.document.EditarActionForm.nombreBoletin.value= articulo[elvalor][3];
  window.opener.document.EditarActionForm.valorRegistro.value= articulo[elvalor][4];
  window.opener.document.EditarActionForm.ley.value= articulo[elvalor][5];
  window.opener.document.EditarActionForm.textoFecha.value= articulo[elvalor][6];
  window.opener.document.EditarActionForm.textoFechaBoletin.value= articulo[elvalor][7];
  
  window.opener.document.EditarActionForm["tra_titulo_c"].value= articuloidiomac[elvalor][0];
  window.opener.document.EditarActionForm["tra_enlace_c"].value= articuloidiomac[elvalor][1];
  window.opener.document.EditarActionForm["tra_apartado_c"].value= articuloidiomac[elvalor][2];
  window.opener.document.EditarActionForm["tra_paginaInicial_c"].value= articuloidiomac[elvalor][3];
  window.opener.document.EditarActionForm["tra_paginaFinal_c"].value= articuloidiomac[elvalor][4];
  window.opener.document.EditarActionForm["tra_observaciones_c"].value= articuloidiomac[elvalor][5];
  
  window.opener.document.EditarActionForm["tra_titulo_v"].value= articuloidiomav[elvalor][0];
  window.opener.document.EditarActionForm["tra_enlace_v"].value= articuloidiomav[elvalor][1];
  window.opener.document.EditarActionForm["tra_apartado_v"].value= articuloidiomav[elvalor][2];
  window.opener.document.EditarActionForm["tra_paginaInicial_v"].value= articuloidiomav[elvalor][3];
  window.opener.document.EditarActionForm["tra_paginaFinal_v"].value= articuloidiomav[elvalor][4];
  window.opener.document.EditarActionForm["tra_observaciones_v"].value= articuloidiomav[elvalor][5];
      
	window.close();	 
}

</SCRIPT>
</head>
<body>
	<div id="contenidosGestorNoMenu">
		<h1>Resultats obtinguts</h1>

<logic:equal name="APPTRS_NumArticulos" value="0">
      No s'ha trobat cap resultat amb aquests criteris
</logic:equal>

<logic:notEqual name="APPTRS_NumArticulos" value="0">
      <table cellpadding="0" cellspacing="0" class="listadoItems">
      		<colgroup>
            <col class="centrado"></col>
            <col class="centrado"></col>
            <col></col>
            <col></col>
            <col></col>
          </colgroup>
            <tr>
              <th>N. BOIB </th>
              <th>Data </th>
              <th>N. Registre </th>
              <th>Títol </th>
              <th class="peque">&nbsp;</th>
            </tr>
            <logic:iterate id="i" name="APPTRS_ArticulosListado" indexId="indice">
            <tr class="<%=((indice.intValue()%2==0) ? "par" : "impar")%>">
              <td><bean:write name="i" property="nboid"/></td>
              <td><bean:write name="i" property="fecha"/></td>
              <td><bean:write name="i" property="nregistro"/></td>
              <td><bean:write name="i" property="tituloparcial"/></td>
              <td><a href="javascript:rellenaForm('<%=indice%>');"><img src="imgs/iconos/sap_enviar.gif" alt="Seleccionar esta Normativa" border="0"></a></td>
            </tr>
            </logic:iterate>      
      </table>
</logic:notEqual>

</div>
</body>
</html>