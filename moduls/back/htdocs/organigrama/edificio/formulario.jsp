<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
	var context = '\<%=request.getContextPath()%>';
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateEdificioForm(form);
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

	function popupUA() {
		obrir(context+"/organigrama/unidad/poparbol.do?idUA=0&action=&ficha=true", "Relacionar", 538, 440);
	}

	function actualizaUA(id) {
		
		<logic:present name="edificioForm" property="id">
			document.location = context+"/organigrama/edificio/editar.do?action=<bean:message key="edificio.relacion.unidades" />&unidad=" + id + "&edificio=" + <bean:write name="edificioForm" property="id"/> + "&operacion=alta";
	    </logic:present>		
	}
	
	function popCoordenadas(id) {
		url = context+"/organigrama/edificio/popCoordenadas.do?idEdificio=" + id;
		obrir( url , "Coordenadas", 550, 440);
	}	
	
	function actualizarCoordenadas(lan,lng) {
		document.getElementById("latitud").value = lan.substr (0, 15);
		document.getElementById("longitud").value = lng.substr (0, 15);
		//alert("Meter los datos en el formulario ("+lan+","+lng+")");
	}

// -->
</script>


<div class="bloc">
    <h1>
        <logic:notPresent name="edificioForm" property="id">
            <bean:message key="edificio.alta" />
        </logic:notPresent>
        <logic:present name="edificioForm" property="id">
            <bean:message key="edificio.modificacion" />
        </logic:present>
    </h1>
	<h2><bean:message key="edificio.datos" /></h2>
</div>
<br />

<html:errors/>

<html:form action="/organigrama/edificio/editar" styleId="edificioForm" enctype="multipart/form-data" >

     <logic:present name="edificioForm" property="id">
         <html:hidden property="id" />
     </logic:present>
     <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.direccion" /></div>
            <html:text styleClass="btext" property="direccion" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.codigoPostal" /></div>
            <html:text styleClass="btext" property="codigoPostal" maxlength="64" tabindex="2" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.poblacion" /></div>
            <html:text styleClass="btext" property="poblacion" maxlength="64" tabindex="3" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.gps" /></div>
               <html:text styleClass="btext2" property="latitud"  styleId="latitud" maxlength="15" tabindex="4" />
               <html:text styleClass="btext3" property="longitud"  styleId="longitud" maxlength="15"  tabindex="5" />
               <input type="button" Class="bcorde" value="<bean:message key="edificio.coordenadas" />" onclick="javascript:popCoordenadas(<bean:write name="edificioForm" property="id" />)">  
  	</div>
          
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.telefono" /></div>
            <html:text styleClass="btext" property="telefono" maxlength="64" tabindex="6" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.fax" /></div>
            <html:text styleClass="btext" property="fax" maxlength="64" tabindex="7" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.email" /></div>
            <html:text styleClass="btext" property="email" maxlength="256" tabindex="8" />
       </div>
       
        <logic:notEmpty name="edificioForm" property="nombrefotop">
            <div class="component">
                <div class="etiqueta"><bean:message key="edificio.fotop" /></div> 
                <bean:define id="imgurl"><html:rewrite page="/edificio/foto/pequenya.do" paramId="idEdificio" paramName="edificioForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="edificioForm" property="nombrefotop" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarfotop"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.fotop" /></div>
            <html:file styleClass="bfile" property="fotopfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>
        <logic:notEmpty name="edificioForm" property="nombrefotog">
            <div class="component">
                <div class="etiqueta"><bean:message key="edificio.fotog" /></div>
                <bean:define id="imgurl"><html:rewrite page="/edificio/foto/grande.do" paramId="idEdificio" paramName="edificioForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="edificioForm" property="nombrefotog" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotog"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarfotog"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.fotog" /></div>
            <html:file styleClass="bfile" property="fotogfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="10" />
        </div>
        <logic:notEmpty name="edificioForm" property="nombreplano">
            <div class="component">
                <div class="etiqueta"><bean:message key="edificio.plano" /></div>
                <bean:define id="imgurl"><html:rewrite page="/edificio/plano.do" paramId="idEdificio" paramName="edificioForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="edificioForm" property="nombreplano" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombreplano"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarplano"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="edificio.plano" /></div>
            <html:file styleClass="bfile" property="planofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="11" />
        </div>
         
    </div>
    <br/>
    <br/>

    <div id="capes">
        <bean:size id="capes" name="edificioForm" property="traducciones"/>
        <logic:iterate id="traducciones" name="edificioForm" property="traducciones" indexId="i" >
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
                        <div class="etiqueta"><bean:message key="edificio.descripcion" /></div>
                        <html:text styleClass="btext" property="descripcion" name="traducciones" indexed="true" />
                    </div>
                </div>
            </div>
        </logic:iterate>
    </div>
    <br />


     <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100">
            <logic:notPresent name="edificioForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="edificioForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101"><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103"><bean:message key="boton.cancelar" /></html:cancel>
        <logic:notPresent name="edificioForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" >
                <bean:message key="boton.busqueda" />
            </html:submit>
        </logic:notPresent>
        <logic:present name="edificioForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" onclick="return baja();">
                <bean:message key="boton.eliminar" />
            </html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="edificioForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>

<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>
<logic:present name="edificioForm" property="id">

    <br /><br />

    <!-- Unidades -->
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="edificio.relacion.unidades" />
        </div>
        <logic:empty name="unidadOptions">
            <br /><h2><bean:message key="edificio.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="unidadOptions">
            <logic:iterate id="unidad" name="unidadOptions">
            <div class="component">
                <div class="textconsulta1">
	                <html:link action='<%="/organigrama/unidad/seleccionar?action=" + etiquetaSelect%>'
                          paramId="idUA" paramName="unidad" paramProperty="id">
    	                <bean:write name="unidad" property="traduccion.nombre" />
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/edificio/editar" >
                        <input type="hidden" name="unidad" value='<bean:write name="unidad" property="id" />' />
                        <input type="hidden" name="edificio" value='<bean:write name="edificioForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="edificio.relacion.unidades" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript:popupUA();" ><bean:message key="boton.nuevo" /></a>]
    </center>
    <!-- /Unidades -->

    <br /><br />

</logic:present>

