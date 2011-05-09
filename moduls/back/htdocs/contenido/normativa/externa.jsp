<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--

	function inicioTraduccion(){
	    return confirm("<bean:message key='traduccion.inicio' />");
	}

    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateNormativaForm(form);
    }

    // Llamada al popup para seleccionar la fecha de un campo
    function abrirCalendario(field) {
        obrirFixa("<html:rewrite page='/moduls/calendario.do'/>?field=" + field);
    }

    // Llamada para el popup de calendario para fijar la fecha
    function seleccionaDia(data, field) {
        eval("document.forms[0]." + field + ".value=data");
    }

    function abrirDato(opcion) {
        obrir("<html:rewrite page='/contenido/normativa/popup.do'/>?opcion=" + opcion, "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    function actualizaDato(id, nombre, field) {
        eval("document.forms[0].id" + field + ".value=id");
        eval("document.forms[0].nombre" + field + ".value=nombre");
    }

    // Función para abrir la lista de opciones para busqueda o relacion
    function listaOpciones(opcion, dato){
        if (opcion == "busqueda"){
            obrir("<html:rewrite page='/contenido/normativa/popup.do?accion=busqueda&opcion='/>" + dato, "<bean:message key='boton.seleccionar' />", 538, 140);
        }
        if (opcion == "relaciona"){
            obrir("<html:rewrite page='/contenido/normativa/popup.do?accion=relaciona&opcion='/>" + dato, "<bean:message key='boton.seleccionar' />", 538, 140);
        }
    }

    // Función para abrir el popup de afectaciones
    function listaAfectaciones(){
        obrir("<html:rewrite page='/contenido/normativa/popafectacion.do'/>", "<bean:message key='boton.seleccionar' />", 538, 180);
    }


    function formCodigo(){
        obrir("<html:rewrite page='/contenido/normativa/popupcodigo.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    function busquedaNoRelacionadas(){
        accion = '<bean:message key='boton.busqueda.norelac' />';
        document.location = "<html:rewrite page='/contenido/normativa/externa/seleccionar.do'/>?action=" + accion;
    }

    function mostrarNormativa(id){
        accion = '<bean:message key='boton.seleccionar' />';
        document.location = "<html:rewrite page='/contenido/normativa/externa/editar.do'/>?action=" + accion + "&idSelect="+ id;
    }

    <logic:present name="normativaForm" property="id">
        function asignarAfectacion(tafectacion, normativa2){
            accion = '<bean:message key="normativa.relacion.afectaciones" />';
            normativa1 = '<bean:write name="normativaForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/normativa/externa/editar.do'/>?action=" + accion + "&normativa1=" + normativa1 + "&normativa2=" + normativa2 + "&tafectacion=" + tafectacion + "&operacion=alta";
        }

        function asignarOpcion(id){
            accion = '<bean:message key="normativa.relacion.procedimientos" />';
            idNormativa = '<bean:write name="normativaForm" property="id" />';
            document.location = "<html:rewrite page='/contenido/normativa/externa/editar.do'/>?action=" + accion + "&normativa=" + idNormativa + "&procedimiento=" + id + "&operacion=alta";
        }
    </logic:present>

	// Funcio per mostrar una capa i ocultar la resta
	function activarCapa(capa, n) {

		document.getElementById("idio").value=capa;
		
	    if (capa== 0) {document.getElementById("langTrad").value="ca";}
	    if (capa== 1) {document.getElementById("langTrad").value="es";}
	    if (capa== 2) {document.getElementById("langTrad").value="en";}
	    if (capa== 3) {document.getElementById("langTrad").value="de";}
	    if (capa== 4) {document.getElementById("langTrad").value="fr";}
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


<div class="bloc">
    <h1>
        <logic:notPresent name="normativaForm" property="id">
                <bean:message key="normativa.externa.alta" />
        </logic:notPresent>
        <logic:present name="normativaForm" property="id">
                <bean:message key="normativa.externa.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="normativa.externa.datos" /></h2>
</div>

<br />

<html:errors/>
<%session.setAttribute("action_path_key",null);%>
<html:form action="/contenido/normativa/externa/editar" styleId="normativaForm" enctype="multipart/form-data">
     <input type="hidden" name="idio" value="ca" id="idio" />
     
     <logic:present name="normativaForm" property="id">
     <input type="hidden" name="espera" value="si" id="espera" />
     </logic:present>
     
     <input type="hidden" name="langTrad" id="langTrad" value="ca">
    <logic:present name="normativaForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <div class="bloc">
    
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.numero" /></div>
			<html:text styleClass="ctext" property="valorNumero" maxlength="256" tabindex="1" />
		</div>
        <logic:notPresent role="sacoper" >
            <div class="component">
                <div class="etiqueta"><bean:message key="normativa.validacion" /></div>
                <html:select property="validacion" tabindex="2">
                    <html:option value="" key="select.defecto" />
                    <html:option value="1" key="validacion.publica" />
                    <html:option value="2" key="validacion.interna" />
                </html:select>
            </div>
        </logic:notPresent>
        <logic:present role="sacoper">
            <logic:present name="normativaForm" property="id">
                <html:hidden property="validacion"/>
            </logic:present>
            <logic:notPresent name="normativaForm" property="id">
                <html:hidden property="validacion" value="2" />
            </logic:notPresent>
        </logic:present>
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.tipo" /></div>
            <html:hidden property="idTipo" />
            <html:text styleClass="ctext" property="nombreTipo" readonly="true" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirDato('Tipo')" tabindex="3"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.boletin" /></div>
            <html:hidden property="idBoletin" />
            <html:text styleClass="ctext" property="nombreBoletin" readonly="true" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirDato('Boletin')" tabindex="4"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.registro" /></div>
			<html:text styleClass="ctext" property="valorRegistro" maxlength="256" tabindex="5" />
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.ley" /></div>
			<html:text styleClass="btext" property="ley" maxlength="256" tabindex="6" />
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.fecha" /></div>
			<html:text styleClass="ctext" property="textoFecha" maxlength="15" tabindex="7" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFecha')" tabindex="8"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
        <div class="component">
			<div class="etiqueta"><bean:message key="normativa.fechaBoletin" /></div>
			<html:text styleClass="ctext" property="textoFechaBoletin" maxlength="15" tabindex="9" />
            <div class="botoneraconsulta1">
                <html:button property="boton" onclick="abrirCalendario('textoFechaBoletin')" tabindex="10"><bean:message key="boton.seleccionar" /></html:button>
            </div>
		</div>
    </div>

    <br />

    <%String idioma = "";%>
    <div id="capes">
    <bean:size id="capes" name="normativaForm" property="traducciones" />
    <logic:iterate id="traducciones" name="normativaForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa">
            <div class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) {
                           idioma = (String) lang;
                    %>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                    <% } %>
                </logic:iterate>
            </div>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.titulo" /></div>
                    <html:text styleClass="btext" name="traducciones" property="titulo" indexed="true" tabindex="11"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.responsable" /></div>
                    <html:text styleClass="btext" name="traducciones" property="responsable" indexed="true" tabindex="12"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.enlace" /></div>
                    <html:text styleClass="btext" name="traducciones" property="enlace" indexed="true" tabindex="13"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.apartado" /></div>
                    <html:text styleClass="btext" name="traducciones" property="apartado" indexed="true" tabindex="14"  maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.paginaInicial" /></div>
                    <html:text styleClass="ctext" name="traducciones" property="paginaInicial" indexed="true" tabindex="15"  maxlength="256"/>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.paginaFinal" /></div>
                    <html:text styleClass="ctext" name="traducciones" property="paginaFinal" indexed="true" tabindex="16"  maxlength="256"/>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.observaciones" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="observaciones" indexed="true" rows="3" cols="60" tabindex="17"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.archivo.actual" /></div>
                    <logic:present name="traducciones" property="archivo">
                        <html:link page='<%="/normativa/archivo.do?lang=" + idioma%>' paramId="idNormativa" paramName="normativaForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="archivo.nombre" /></html:link>
                        <%-- <input type="text" class="ctext" readonly="readonly" value='<bean:write name="traducciones" property="archivo.nombre" />' /> --%>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrarfichero_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="archivo">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="normativa.archivo.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="normativa.archivo.nuevo" /></div>
                    <html:file styleClass="bfile" name="normativaForm" property='<%="ficheros[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="18" />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" tabindex="100" onclick="return validar(this.form);">
            <logic:notPresent name="normativaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="normativaForm" property="id">
                <bean:message key="boton.modificar" />
             </logic:present>
         </html:submit>
         <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
         <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
         <logic:notPresent name="normativaForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" ><bean:message key="boton.busqueda" /></html:submit>
         </logic:notPresent>
         <logic:present name="normativaForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
         </logic:present>
    </div>

</html:form>

<logic:notPresent name="normativaForm" property="id">
<center>
    [<a href="javascript: formCodigo()" ><bean:message key="normativa.seleccionar.codigo" /></a>]
    [<a href="javascript: busquedaNoRelacionadas()" ><bean:message key="normativa.busqueda.norelac" /></a>]
</center>
</logic:notPresent>

<!-- XAPUÇA -->

<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="normativaForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="normativaForm" property="id">

    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="normativa.relacion.afectaciones" />
        </div>
        <logic:empty name="afectacionOptions">
            <br /><h2><bean:message key="normativa.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="afectacionOptions">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit1" style="background-color: #d8dfe7">
                        <bean:message key="normativa" />
                    </div>
                    <div class="textgros5" style="background-color: #d8dfe7">
                        <bean:message key="tafectacion" />
                    </div>
                </div>
                <div class="botoneraconsulta1"></div>
            </div>
            <bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>
            <logic:iterate id="afectacion" name="afectacionOptions">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit1">&nbsp;
                    <logic:equal name="afectacion" property="normativa.class" value="class org.ibit.rol.sac.model.NormativaLocal" >
                        <html:link action='<%="/contenido/normativa/local/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="afectacion"paramProperty="normativa.id">
                            <bean:write name="afectacion" property="normativa.traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                    <logic:equal name="afectacion" property="normativa.class" value="class org.ibit.rol.sac.model.NormativaExterna" >
                        <html:link action='<%="/contenido/normativa/externa/seleccionar?action=" + etiquetaSelect%>'
                                   paramId="idSelect" paramName="afectacion"paramProperty="normativa.id">
                            <bean:write name="afectacion" property="normativa.traduccion.titulo" />
                        </html:link>
                    </logic:equal>
                    </div>
                    <div class="textgros5">&nbsp;
                    <html:link action='<%="/sistema/tafectacion/seleccionar?action=" + etiquetaSelect%>'
                               paramId="idTipo" paramName="afectacion"paramProperty="tipoAfectacion.id">
                        <bean:write name="afectacion" property="tipoAfectacion.traduccion.nombre" />
                    </html:link>
                    </div>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/normativa/externa/editar" >
                        <input type="hidden" name="normativa1" value='<bean:write name="normativaForm" property="id" />' />
                        <input type="hidden" name="normativa2" value='<bean:write name="afectacion" property="normativa.id" />' />
                        <input type="hidden" name="tafectacion" value='<bean:write name="afectacion" property="tipoAfectacion.id" />' />
                        <input type="hidden" name="action" value='<bean:message key="normativa.relacion.afectaciones" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit ><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaAfectaciones()" ><bean:message key="boton.nuevo" /></a>]
    </center>
    <br /><br />

    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="normativa.relacion.procedimientos" />
        </div>
        <logic:empty name="procedimientoOptions">
            <br /><h2><bean:message key="normativa.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="procedimientoOptions">
            <logic:iterate id="procedimiento" name="procedimientoOptions">
            <div class="component">
                <div class="textconsulta1">
                    <bean:write name="procedimiento" property="traduccion.nombre" />
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/contenido/procedimiento/editar" >
                        <input type="hidden" name="idSelect" value='<bean:write name="procedimiento" property="id" />' />
                        <html:submit property="action"><bean:message key="boton.seleccionar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    <% //[<a href="javascript: listaOpciones('relaciona', 'procedimiento')" ><bean:message key="boton.nuevo" /></a>] %>
    </center>
    <br /><br />
</logic:present>

<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>