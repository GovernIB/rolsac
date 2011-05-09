<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>    
<link rel="stylesheet" href='<html:rewrite page="/css/styleA.css"/>' type="text/css" />
    <script type="text/javascript">
<!--


function ValidaNumero(obj){
  // Si Oracle admite , como separador decimal es necesario hacer el replace.
  obj.value=obj.value.replace(",",".")
  if(isNaN(Number(obj.value))){
    alert('Formato numérico incorrecto.\n\n Formato válido:\n\t9999'); 
    obj.value=obj.defaultValue;
    obj.focus();
    obj.select();
    return false;
  }
  return true;
}


function esDigito(sChr){
  var sCod = sChr.charCodeAt(0);
  return ((sCod > 47) && (sCod < 58));
}
function valSep(oTxt){
  var bOk = false;
  bOk = bOk || ((oTxt.value.charAt(2) == "-") && (oTxt.value.charAt(5) == "-"));
  bOk = bOk || ((oTxt.value.charAt(2) == "/") && (oTxt.value.charAt(5) == "/"));
  return bOk;
}
function finMes(oTxt){
  var nMes = parseInt(oTxt.value.substr(3, 2), 10);
  var nRes = 0;
  switch (nMes){
    case 1: nRes = 31; break;
    case 2: nRes = 29; break;
    case 3: nRes = 31; break;
    case 4: nRes = 30; break;
    case 5: nRes = 31; break;
    case 6: nRes = 30; break;
    case 7: nRes = 31; break;
    case 8: nRes = 31; break;
    case 9: nRes = 30; break;
    case 10: nRes = 31; break;
    case 11: nRes = 30; break;
    case 12: nRes = 31; break;
  }
  return nRes;
}
function valDia(oTxt){
  var bOk = false;
  var nDia = parseInt(oTxt.value.substr(0, 2), 10);
  bOk = bOk || ((nDia >= 1) && (nDia <= finMes(oTxt)));
  return bOk;
}
function valMes(oTxt){
  var bOk = false;
  var nMes = parseInt(oTxt.value.substr(3, 2), 10);
  bOk = bOk || ((nMes >= 1) && (nMes <= 12));
  return bOk;
}
function valAno(oTxt){
  var bOk = true;
  var nAno = oTxt.value.substr(6);
  bOk = bOk && ((nAno.length == 2) || (nAno.length == 4));
  if (bOk){
    for (var i = 0; i < nAno.length; i++){
      bOk = bOk && esDigito(nAno.charAt(i));
    }
  }
  return bOk;
}

function ValidaFecha(oTxt){
  var bOk = true;
  if (oTxt.value != ""){
      bOk = bOk && (valAno(oTxt));
      bOk = bOk && (valMes(oTxt));
      bOk = bOk && (valDia(oTxt));
      bOk = bOk && (valSep(oTxt));
      if (!bOk){
      alert('Formato fecha incorrecto.\n\n Formatos válidos:\n\tdd/mm/aaaa\n\tdd-mm-aaaa');
      oTxt.value = "";
      oTxt.focus();
    }
  }
}


//VRS: fin de las funciones que añado



    
    function validarEnviarSearch() {
    
    
          //comprobar que hay registro
          /* anterior validacion 
          var objregistro= document.SearchnormativaActionForm.numeroregistro.value;
          if ((isNaN(Number(objregistro))) || (Number(objregistro)<=1)) {
             alert("No hay introducido ningun numero de registro correcto");
             return false;
          }
          */
          var objregistro= document.SearchnormativaActionForm.numeroregistro.value;
          if ((isNaN(Number(objregistro))) || (Number(objregistro)<=1)) {
             document.SearchnormativaActionForm.numeroregistro.value="";
          }
      
      var hayboib=false;
      //comprobar que hay boib
      var objboib= document.SearchnormativaActionForm.numeroboletin.value;
      if ((isNaN(Number(objboib))) || (Number(objboib)<=1)) {
         hayboib = false;
      } else {
         hayboib = true;
      }


      var objfecha= document.SearchnormativaActionForm.fecha.value;
      if (!hayboib) {
          //validar fecha
          if (!isNaN(Number(objfecha))) {
              alert("Hay que introducir un boib o una fecha");
              return false;
          }
      }
      
        
      
       document.SearchnormativaActionForm.submit();
    }    



// -->
</script>

      <div class="bloc">
        <h1>Alta Normativa Local</h1>
        <p>
          <!-- VRS: aqu&amp;iacute; se pone el formulario de busqueda //-->
          <html:form action="/searchnormativa.do">N. Boletin:
            <html:text property="numeroboletin" onchange="ValidaNumero(this)" size="10"/>N. Registre:
            <html:text property="numeroregistro" onchange="ValidaNumero(this)" size="10"/>N. data:
            <html:text property="fecha" onchange="ValidaFecha(this)" size="10"/>
            <input type="button" value="BUSCAR" onclick="validarEnviarSearch()" class="boton"/>
          </html:form>
          <!-- VRS: fin formulario de busqueda //-->
        </p>
      </div>
      <br/>
      
      <table cellpadding="0" cellspacing="10" align="center" id="mensaAviso">
        <tr>
          <td class="etiqueta">Avís: </td><td><bean:write name="APPTRS_aviso" property="cabecera"/></td>
        </tr>   
        <tr>
          <td class="etiqueta">Missatge: </td><td><bean:write name="APPTRS_aviso" property="subcabecera"/></td>
        </tr>
        <tr>
          <td class="etiqueta">Causa: </td><td><bean:write name="APPTRS_aviso" property="descripcion"/></td>
        </tr>
      </table>
      
