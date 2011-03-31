<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<% String nombreSeccion = null;%>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>

<style type="text/css">
	 <!--
	 	.itemsComponent { display:none; }
		.textpetit1 strong { cursor:pointer; }
		.textconsulta1 { padding:3px 2px !important; }
		.textconsulta1 input { width:30px; font:normal 1em Arial, Helvetica, sans-serif; text-align:center; margin-right:10px; }
		.boton {
			font-family: verdana, arial, helvetica, sans-serif;
			font-size: 8pt;
			color: #515B67;
			margin:2px;
			padding: 2px 0px 2px 0px;
			width: 150px;
			height: 23px;
			background-image: url(../img/fonsboto.gif);
			background-color: #dae2ed;
		}
	 -->
</style>

<script type="text/javascript">
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateSeccionForm(form);
    }

    <logic:notEmpty name="seccionForm">
        function cambiaPadre(){
            obrir("<html:rewrite page='/sistema/seccion/arbol.do'/>?idSeccion=<bean:write name="seccionForm" property="id" />&action=", "<bean:message key='boton.seleccionar' />", 538, 440);
        }
    </logic:notEmpty>

    function updatePadre(id, nombre) {
        if (id == 0){
            document.forms[0].idPadre.value = "";
        } else {
            document.forms[0].idPadre.value = id;
        }
        document.forms[0].nombrePadre.value = nombre;
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
// -->
</script>
 <% String context=request.getContextPath();%>
<div class="bloc">
    <logic:notPresent name="seccionForm" property="id">
        <h1><bean:message key="seccion.alta" /></h1>
    </logic:notPresent>
    <logic:present name="seccionForm" property="id">
        <h1><bean:message key="seccion.modificacion" /></h1>
    </logic:present>
    <h2><bean:message key="seccion.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/seccion/editar" styleId="seccionForm">
    <html:hidden property="id" />
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="seccion.padre" /></div>
            <logic:notEmpty name="idPadre">
                <bean:define id="idp" name="idPadre" type="java.lang.Long" />
                <html:hidden property="idPadre" value="<%=idp.toString()%>" />
            </logic:notEmpty>
            <logic:empty name="idPadre">
                <html:hidden property="idPadre" />
            </logic:empty>
            <logic:present name="padre">
                <input type="text" class="ctext" id="nombrePadre" readonly="1" value='<bean:write name="padre" property="traduccion.nombre" />' />
            </logic:present>
            <logic:notPresent name="padre">
                <input type="text" class="ctext" id="nombrePadre" readonly="1" value='- <bean:message key="seccion.raiz" /> -' />
            </logic:notPresent>
            <logic:notEmpty name="seccionForm" property="id" >
                <div class="botoneraconsulta1">
                    <input type="button" onclick="cambiaPadre()" value="<bean:message key='boton.modificar' />" tabindex="1"/>
                </div>
            </logic:notEmpty>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="seccion.perfil" /></div>
            <html:select property="perfil" tabindex="2" >
                <html:option value="sacadmin" key="perfil.sacadmin" />
                <html:option value="sacsuper" key="perfil.sacsuper" />
                <html:option value="sacoper" key="perfil.sacoper" />
            </html:select>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="seccion.codigoestandard"/></div>
             <html:text styleClass="btext" property="codigoEstandard" maxlength="20" tabindex="1" />
        </div>
    </div>

    <br /><br />

    <div id="capes">
    <bean:size id="capes" name="seccionForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="seccionForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa">
            <div  class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) { %>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                    <% } %>
                </logic:iterate>
            </div>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="seccion.nombre" /></div>
                    <logic:notEmpty name="seccionForm" property="id" >
                        <logic:equal name="i" value="0">
                            <bean:define id="nombreAux" name="traducciones" property="nombre" type="java.lang.String" />
                            <% nombreSeccion = nombreAux; %>
                        </logic:equal>
                    </logic:notEmpty>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" maxlength="256" tabindex="3" />
                </div>
                
                
                <div class="component">
                    <div class="etiqueta"><bean:message key="seccion.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" tabindex="2"/>
                </div>
                
            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="seccionForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="seccionForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="seccionForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>


</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="seccionForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:notEmpty name="seccionForm" property="id">
    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="seccion.fichas" />
        </div>
        <logic:empty name="listaFichas">
            <br /><h2><bean:message key="seccion.fichas.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="listaFichas">
        	
        	<html:form action="/sistema/seccion/editar" >

            <logic:iterate id="fichaUA" name="listaFichas">
            <div class="component">
                <div class="textconsulta1">
                <table>
                <tr>
                        <%String pub="S";%>
	                <logic:equal name="fichaUA" property="ficha.validacion" value="1">
                         <logic:present name="fichaUA" property="ficha.fechaCaducidad">
			                	<bean:define id="caduca" name="fichaUA" property="ficha.fechaCaducidad" type="java.util.Date"/>
			                	<%if((new java.util.Date().getTime() > caduca.getTime())){pub="N";}else{pub="S";}%>
						</logic:present>
						<logic:present name="fichaUA" property="ficha.fechaPublicacion">
			            	<bean:define id="publica" name="fichaUA" property="ficha.fechaPublicacion" type="java.util.Date"/>
			                	<%if ((new java.util.Date().getTime() > publica.getTime())&&pub.equals("S")){pub="S";}else{pub="N";}%>
  						</logic:present>   	
						<%if(pub.equals("N")){ %>
						<td>
				   		<img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>"/>&nbsp;
				   		</td>
  						    <%}else{%>		
  						<td>    
		                		<img src="<%=context%>/img/fichaP.gif" alt="<bean:message key="validacion.publica"/>"/>&nbsp;		                            		
		                		</td>
						<% } %>
                        </logic:equal>
	                    
	                    <logic:notEqual name="fichaUA" property="ficha.validacion" value="1">
	                    <td>
                            <img src="<%=context%>/img/fichaNoP.gif" alt="No <bean:message key="validacion.publica"/>"/>&nbsp;
                            </td>
                        </logic:notEqual>
                      <td>  
                    <input type="text" name="orden_fic<bean:write name="fichaUA" property="id" />" size="4" maxlength="4" value="<bean:write name="fichaUA" property="ordenseccion" />" />
                    </td>
                    <td>
                    <bean:write name="fichaUA" property="ficha.traduccion.titulo" />
                    <logic:present name="fichaUA" property="unidadAdministrativa.traduccion.abreviatura">
                    [<a href="<%=context%>/organigrama/unidad/editar.do?espera=si&idUA=<bean:write name="fichaUA" property="unidadAdministrativa.id" />&action=Seleccionar"><bean:write name="fichaUA" property="unidadAdministrativa.traduccion.abreviatura" /></a>]
                    </logic:present>
                    <logic:notPresent name="fichaUA" property="unidadAdministrativa.traduccion.abreviatura">
                    [<a href="<%=context%>/organigrama/unidad/editar.do?espera=si&idUA=<bean:write name="fichaUA" property="unidadAdministrativa.id" />&action=Seleccionar"><bean:write name="fichaUA" property="unidadAdministrativa.id" /></a>]
                    </logic:notPresent>
                 </td>
                </tr>
                </table>
                </div>
              
                <div class="botoneraconsulta1">
                	<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="<%=context%>/contenido/ficha/seleccionar.do?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="fichaUA" property="ficha.id" />&idSeccion=<bean:write name="seccionForm" property="id" />";' />
                </div>
                  
            </div>
            </logic:iterate>
            
            <input type="hidden" name="idSelect" value="<bean:write name="seccionForm" property="id" />" /> 
            <input type="hidden" name="action" value="<bean:message key="boton.actualizar_orden" />" />                        
            <html:submit styleClass="boton" ><bean:message key="boton.actualizar_orden" /></html:submit>
            <bean:message key="seccion.reordenar" />:<input type="checkbox" name="actOrden" value="S" checked>
            
            </html:form>
            
            
        </logic:notEmpty>
    </div><br />
    <center>
    <logic:notEmpty name="mostrarNuevaFicha">
    [<html:link page='<%="/contenido/ficha/form.do?nombreSeccion=" + nombreSeccion%>'
               paramId="idSeccion"
               paramName="seccionForm"
               paramProperty="id">
        <bean:message key="boton.nuevo" />
    </html:link>]
    </logic:notEmpty>
    </center>
</logic:notEmpty>

<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>
