<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<% String context=request.getContextPath();%>

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

    //validar el formulario de insercion
    function validarEnviar() {
          
      
            //comprobar que hay validacion
            var objvalidacion= document.normativaForm.validacion.value;
            if ((isNaN(Number(objvalidacion))) || (Number(objvalidacion)<1)) {
               alert("No se ha seleccionado la validación");
               return false;
            }
          
            //comprobar que hay tipo
            var objidtipo= document.normativaForm.idTipo.value;
            if ((isNaN(Number(objidtipo))) || (Number(objidtipo)<1)) {
               alert("No se ha seleccionado ningún tipo");
               return false;
            }
            
            //comprobar que hay ua
            
            var objidUA= document.normativaForm.idUA.value;
            if ((isNaN(Number(objidUA))) || (Number(objidUA)<1)) {
               alert("No se ha seleccionado ninguna Unidad Administrativa");
               return false;
            }
            //esto era para las pruebas 
            //document.normativaForm.idUA.value=3;
          
            document.normativaForm.submit();
    }

    //validar el formulario de busquedas
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

    // Llamada al popup para seleccionar la fecha de un campo
    function abrirCalendario(field) {
        obrirFixa("https://intranet.caib.es/sacback/moduls/calendario.do?field=" + field);
    }

 
    function abrirDato(opcion) {
        obrir("popnormativa.do", "Seleccionar", 538, 140);
    }

    function actualizaDato(id, nombre, field) {
        eval("document.normativaForm.id" + field + ".value=id");
        eval("document.normativaForm.nombre" + field + ".value=nombre");
    }

    function abrirUA(contextFrom) {
        poprealcion = obrir(contextFrom+"/organigrama/unidad/poparbol.do?idUA=0&action=&", "Seleccionar", 538, 440);
    }

    function actualizaUA(id, nombre) {
       
        eval("document.normativaForm.idUA.value=id");
        eval("document.normativaForm.nombreUA.value=nombre");
    }

    

	// Funcio per mostrar una capa i ocultar la resta
	function activarCapa(capa, n) {
        for (i = 0; i < n; i++) {
            capeta = document.getElementById("capa" + i);
            if (i == capa) {
                capeta.style.visibility="visible";
            } else {
                capeta.style.visibility="hidden";
            }
        }
	}
  
  
// Obrir un pop up
function obrir(url, name, x, y) {
   nombre = window.open(url, name, 'scrollbars=no, resizable=yes, width=' + x + ',height=' + y);
   return nombre;
}

// Obrir un pop up amb scroll
function obrirScroll(url, name, x, y) {
   nombre = window.open(url, name, 'scrollbars=yes, resizable=yes, width=' + x + ',height=' + y);
   return nombre;
}

// Obrir un pop up per el calendari
function obrirFixa(url) {
   nombre = window.open(url, 'Calendario', 'scrollbars=no, resizable=no, width=235 ,height=175');
   return nombre;
}

function obrirTest(url) {
   nombre = window.open( url, '', 'toolbar, menubar, scrollbars, resizable');
   return nombre;
}

// Auxiliar para formularios
function myIndex(o) {
   var f = o.form;
   
   for (i = 0; i < f.elements.length ; i++) {
      if (f.elements[i] == o) return i;
   }
}

// Confirma una baja mediante un diálogo
function confirmaBaja(texto) {
    var result = confirm(texto)

    if (result == true) {
        return true;
    } else {
        return false;
    }
}

function obrirImatge(url) {
    obrir(url, '', 400, 300);
}
  

  
// -->
</script>

<logic:present parameter="contextfrom">
	<logic:notPresent name="contextFrom_">
		<bean:parameter id="contextfrom" name="contextfrom"/>
		<bean:define id="contextFrom_" name="contextfrom" toScope="session"/>
	</logic:notPresent>
</logic:present>

      <div class="bloc">
        <h1>Alta Normativa Local</h1>
        <p>
          <!-- VRS: aqu&iacute; se pone el formulario de busqueda //-->
		  
          <html:form action="/searchnormativa.do">N. Butlletí:
		  
            <html:text property="numeroboletin" onchange="ValidaNumero(this)" size="10"/>N. Registre:
            <html:text property="numeroregistro" onchange="ValidaNumero(this)" size="10"/>N. data:
            <html:text property="fecha" onchange="ValidaFecha(this)" size="10"/>
            
            <input type="button" value="Cercar" onclick="validarEnviarSearch()" class="boton">            
          </html:form>
          <!-- VRS: fin formulario de busqueda //-->
        </p>
        <h2>Dades de la Normativa Local</h2>
      </div>

      <br/>

      <form name="normativaForm" method="post" action="editar.do" enctype="multipart/form-data" id="normativaForm">
        <div class="bloc">
		
          <div class="component">
            <div class="etiqueta">N&uacute;mero</div>
            <input type="text" name="valorNumero" maxlength="256" tabindex="1" value="<bean:write name="APPTRS_normativa" property="numeroboib" />" class="ctext"/>
          </div>
          <div class="component">
            <div class="etiqueta">Validaci&oacute;</div>
            <select name="validacion" tabindex="2">
              <option value="">- Elegeixi una opci&oacute; -</option>
              <option value="1" selected>P&uacute;blic</option>
              <option value="2">Intern</option>
            </select>
          </div>
          <div class="component">
            <div class="etiqueta">Tipus Normativa</div>
            <input type="hidden" name="idTipo" value="<bean:write name="APPTRS_normativa" property="idTipo" />"/>
            <input type="text" name="nombreTipo" value="<bean:write name="APPTRS_normativa" property="nombreTipo" />" readonly="readonly" class="ctext"/>
            <div class="botoneraconsulta1">
            <!--
              <input type="button" name="boton" tabindex="3" value="Seleccionar" onclick="abrirDato('Tipo')"/>
            //-->              
            </div>
          </div>
          <div class="component">
            <div class="etiqueta">Butllet&iacute;</div>
            <input type="hidden" name="idBoletin" value="<bean:write name="APPTRS_normativa" property="idBoletin" />"/>
            <input type="text" name="nombreBoletin" value="<bean:write name="APPTRS_normativa" property="nombreBoletin" />" readonly="readonly" class="ctext"/>
          </div>
          <div class="component">
            <div class="etiqueta">Unitat Administrativa</div>
            <input type="hidden" name="idUA" value=""/>
            <input type="text" name="nombreUA" value="" readonly="readonly" class="ctext"/>
            <div class="botoneraconsulta1">
              <input type="button" name="boton" tabindex="5" value="Seleccionar" onclick="abrirUA('<bean:write name="contextFrom_"/>')"/>
            </div>
          </div>
          <div class="component">
            <div class="etiqueta">Registre</div>
            <input type="text" name="valorRegistro" maxlength="256" tabindex="6" value="<bean:write name="APPTRS_normativa" property="valorRegistro" />" class="ctext"/>
          </div>
          <div class="component">
            <div class="etiqueta">Data Butllet&iacute;</div>
            <input type="text" name="textoFechaBoletin" maxlength="15" tabindex="10" value="<bean:write name="APPTRS_normativa" property="textoFechaBoletin" />" class="ctext"/>
          </div>
        </div>
        <br/>
        <div id="capes">
          <div id="capa0" class="capa">
            <div class="pestanyes">
              <div class="tab">Català</div>
              <div class="notab">
                <a href="javascript:activarCapa(1, 4)" class="notabb">Castellà</a>
              </div>
            </div>
            <div class="bloc">
              <div class="component">
                <div class="etiqueta">T&iacute;tol</div>
                <input type="text" name="traducciones[0].titulo" maxlength="256" tabindex="12" value="<bean:write name="APPTRS_normativa" property="tra_titulo_c" />" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">URL</div>
                <input type="text" name="traducciones[0].enlace" maxlength="512" tabindex="13" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Apartat</div>
                <input type="text" name="traducciones[0].apartado" maxlength="256" tabindex="14" value="<bean:write name="APPTRS_normativa" property="tra_apartado_c" />" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Inicial</div>
                <input type="text" name="traducciones[0].paginaInicial" maxlength="256" tabindex="15" value="<bean:write name="APPTRS_normativa" property="tra_paginaInicial_c" />" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Final</div>
                <input type="text" name="traducciones[0].paginaFinal" maxlength="256" tabindex="16" value="<bean:write name="APPTRS_normativa" property="tra_paginaFinal_c" />" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Observacions
                  <br/>
                  <br/>
                  <br/>
                </div>
                <textarea name="traducciones[0].observaciones" tabindex="17" cols="60" rows="3"></textarea>
              </div>
            </div>
          </div>
          <div id="capa1" class="capa">
            <div class="pestanyes">
              <div class="notab">
                <a href="javascript:activarCapa(0, 4)" class="notabb">Català</a>
              </div>
              <div class="tab">Castellà</div>
            </div>
            <div class="bloc">
              <div class="component">
                <div class="etiqueta">T&iacute;tol</div>
                <input type="text" name="traducciones[1].titulo" maxlength="256" tabindex="12" value="<bean:write name="APPTRS_normativa" property="tra_titulo_v" />" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">URL</div>
                <input type="text" name="traducciones[1].enlace" maxlength="512" tabindex="13" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Apartat</div>
                <input type="text" name="traducciones[1].apartado" maxlength="256" tabindex="14" value="<bean:write name="APPTRS_normativa" property="tra_apartado_v" />" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Inicial</div>
                <input type="text" name="traducciones[1].paginaInicial" maxlength="256" tabindex="15" value="<bean:write name="APPTRS_normativa" property="tra_paginaInicial_v" />" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Final</div>
                <input type="text" name="traducciones[1].paginaFinal" maxlength="256" tabindex="16" value="<bean:write name="APPTRS_normativa" property="tra_paginaFinal_v" />" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Observacions
                  <br/>
                  <br/>
                  <br/>
                </div>
                <textarea name="traducciones[1].observaciones" tabindex="17" cols="60" rows="3"></textarea>
              </div>
            </div>
          </div>
          <div id="capa2" class="capa">
            <div class="pestanyes">
              <div class="notab">
                <a href="javascript:activarCapa(0, 4)" class="notabb">Catlà</a>
              </div>
              <div class="notab">
                <a href="javascript:activarCapa(1, 4)" class="notabb">Castellà</a>
              </div>
            </div>
            <div class="bloc">
              <div class="component">
                <div class="etiqueta">T&iacute;tol</div>
                <input type="text" name="traducciones[2].titulo" maxlength="256" tabindex="12" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">URL</div>
                <input type="text" name="traducciones[2].enlace" maxlength="512" tabindex="13" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Apartat</div>
                <input type="text" name="traducciones[2].apartado" maxlength="256" tabindex="14" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Inicial</div>
                <input type="text" name="traducciones[2].paginaInicial" maxlength="256" tabindex="15" value="" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Final</div>
                <input type="text" name="traducciones[2].paginaFinal" maxlength="256" tabindex="16" value="" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Observacions
                  <br/>
                  <br/>
                  <br/>
                </div>
                <textarea name="traducciones[2].observaciones" tabindex="17" cols="60" rows="3"></textarea>
              </div>
            </div>
          </div>
          <div id="capa3" class="capa">
            <div class="pestanyes">
              <div class="notab">
                <a href="javascript:activarCapa(0, 4)" class="notabb">Català</a>
              </div>
              <div class="notab">
                <a href="javascript:activarCapa(1, 4)" class="notabb">Castellà</a>
              </div>
            </div>
            <div class="bloc">
              <div class="component">
                <div class="etiqueta">T&iacute;ol</div>
                <input type="text" name="traducciones[3].titulo" maxlength="256" tabindex="12" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">URL</div>
                <input type="text" name="traducciones[3].enlace" maxlength="512" tabindex="13" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Apartat</div>
                <input type="text" name="traducciones[3].apartado" maxlength="256" tabindex="14" value="" class="btext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Inicial</div>
                <input type="text" name="traducciones[3].paginaInicial" maxlength="256" tabindex="15" value="" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Pàgina Final</div>
                <input type="text" name="traducciones[3].paginaFinal" maxlength="256" tabindex="16" value="" class="ctext"/>
              </div>
              <div class="component">
                <div class="etiqueta">Observacions
                  <br/>
                  <br/>
                  <br/>
                </div>
                <textarea name="traducciones[3].observaciones" tabindex="17" cols="60" rows="3"></textarea>
              </div>
              <input type="file" name="ficheros[0]" size="43" tabindex="18" value="" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" class="bfile"/>
              <input type="file" name="ficheros[1]" size="43" tabindex="18" value="" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" class="bfile"/>
              <input type="file" name="ficheros[2]" size="43" tabindex="18" value="" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" class="bfile"/>
              <input type="file" name="ficheros[3]" size="43" tabindex="18" value="" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" class="bfile"/>
            </div>
          </div>
        </div>
    <div class="botonera">
      <input type="button" name="action" tabindex="100" value="Insertar" class="esquerra" onclick="validarEnviar();"/>
      <input type="reset" tabindex="101" value="Reiniciar" class="esquerra"/>
    </div>
    </form>
 

