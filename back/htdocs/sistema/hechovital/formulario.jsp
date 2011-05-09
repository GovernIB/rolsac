<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<!-- (PORMAD) -->
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateHechoVitalForm(form);
    }


    <logic:present name="hechoVitalForm" property="id">

        // Función para abrir la lista de procedimientos a relacionar
        function listaProcedimientos(){
            obrir("<html:rewrite page='/sistema/hechovital/popproc.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }

        function asignarProcedimiento(idProcedimiento){
            accion = '<bean:message key="hechovital.relacion.procedimientos" />';
            idHecho = '<bean:write name="hechoVitalForm" property="id" />';
            document.location = "<html:rewrite page='/sistema/hechovital/editar.do'/>?action=" + accion + "&hecho=" + idHecho + "&procedimiento=" + idProcedimiento + "&operacion=alta";
        }

    </logic:present>

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

<div class="bloc">
    <h1>
        <logic:notPresent name="hechoVitalForm" property="id">
            <bean:message key="hechovital.alta" />
        </logic:notPresent>
        <logic:present name="hechoVitalForm" property="id">
            <bean:message key="hechovital.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="hechovital.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/hechovital/editar" styleId="hechoVitalForm" enctype="multipart/form-data">
    <logic:present name="hechoVitalForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
    	<div class="component">
        	<div class="etiqueta"><bean:message key="hechovital.codiestandar" /></div>
            <html:text styleClass="btext" property="codigoEstandar" maxlength="256" tabindex="1" />
        </div>
        <!-- foto -->
        <logic:notEmpty name="hechoVitalForm" property="nombrefoto">
           <div class="component">
                <div class="etiqueta"><bean:message key="hechovital.foto" /></div>
                <bean:define id="imgurl"><html:rewrite page="/hechovital/foto.do" paramId="idHecho" paramName="hechoVitalForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="hechoVitalForm" property="nombrefoto" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarfoto"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="hechovital.foto" /></div>
            <html:file styleClass="bfile" property="fotofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>


        <!-- icono -->
        <logic:notEmpty name="hechoVitalForm" property="nombreicono">
           <div class="component">
                <div class="etiqueta"><bean:message key="hechovital.icono" /></div>
                <bean:define id="imgurl"><html:rewrite page="/hechovital/icono.do" paramId="idHecho" paramName="hechoVitalForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="hechoVitalForm" property="nombreicono" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borraricono"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="hechovital.icono" /></div>
            <html:file styleClass="bfile" property="iconofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>

        <!-- icono grande -->
        <logic:notEmpty name="hechoVitalForm" property="nombreiconog">
           <div class="component">
                <div class="etiqueta"><bean:message key="hechovital.iconog" /></div>
                <bean:define id="imgurl"><html:rewrite page="/hechovital/icono/grande.do" paramId="idHecho" paramName="hechoVitalForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="hechoVitalForm" property="nombreiconog" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrariconog"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="hechovital.iconog" /></div>
            <html:file styleClass="bfile" property="iconogfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>
    </div>
    <br/>
    <br/>
	
	<% String idioma = ""; %>
    <div id="capes">
    <bean:size id="capes" name="hechoVitalForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="hechoVitalForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa">
            <div  class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) {  
                    	idioma = (String) lang;%>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                    <% } %>
                </logic:iterate>
            </div>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="2" maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" tabindex="3" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.palabras" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="palabrasclave" indexed="true" rows="3" cols="60" tabindex="4"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.distribComp.actual" /></div>
                    <logic:present name="traducciones" property="distribComp">
                        <html:link page='<%="/hechovital/distribComp.do?lang=" + idioma%>' paramId="idHechoVital" paramName="hechoVitalForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="distribComp.nombre" /></html:link>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrardistrib_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="distribComp">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="hechovital.distribComp.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.distribComp.nuevo" /></div>
                    <html:file styleClass="bfile" name="hechoVitalForm" property='<%="distribs[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="18" />
                </div>



                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.normativa.actual" /></div>
                    <logic:present name="traducciones" property="normativa">
                        <html:link page='<%="/hechovital/normativa.do?lang=" + idioma%>' paramId="idHechoVital" paramName="hechoVitalForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="normativa.nombre" /></html:link>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrarnormat_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="normativa">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="hechovital.normativa.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.normativa.nuevo" /></div>
                    <html:file styleClass="bfile" name="hechoVitalForm" property='<%="normats[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="18" />
                </div>


                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.contenido.actual" /></div>
                    <logic:present name="traducciones" property="contenido">
                        <html:link page='<%="/hechovital/contenido.do?lang=" + idioma%>' paramId="idHechoVital" paramName="hechoVitalForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="contenido.nombre" /></html:link>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrarconten_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="contenido">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="hechovital.contenido.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="hechovital.contenido.nuevo" /></div>
                    <html:file styleClass="bfile" name="hechoVitalForm" property='<%="contens[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="18" />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <br />

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="hechoVitalForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="hechoVitalForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="hechoVitalForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="hechoVitalForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="hechoVitalForm" property="id">
    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="hechovital.relacion.procedimientos" />
        </div>
        <logic:empty name="listaProcedimientos">
            <br /><h2><bean:message key="hechovital.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="listaProcedimientos">
            <logic:iterate id="procedimientoHv" name="listaProcedimientos" indexId="ixProc">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=ixProc.intValue() + 1%>
                    </div>
                    <div class="textgros3" >
                        <bean:write name="procedimientoHv" property="procedimiento.traduccion.nombre" />
                    </div>
                </div>
                <div class="botoneraconsulta3">
                    <html:form action="/contenido/procedimiento/seleccionar">
                        <input type="hidden" name="idSelect" value='<bean:write name="procedimientoHv" property="procedimiento.id" />' />
                        <html:submit property="action" styleClass="dreta" ><bean:message key="boton.seleccionar" /></html:submit>
                    </html:form>
                    <html:form action="/sistema/hechovital/editar">
                        <input type="hidden" name="hecho" value='<bean:write name="hechoVitalForm" property="id" />' />
                        <input type="hidden" name="idHechoProc" value='<bean:write name="procedimientoHv" property="id" />' />
                        <input type="hidden" name="operacion" value="baja" />
                        <input type="hidden" name="action" value='<bean:message key="hechovital.relacion.procedimientos" />' />
                        <html:submit styleClass="dreta" ><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                    <logic:notEqual name="ixProc" value="0">
                        <html:form action="/sistema/hechovital/editar">
                            <input type="hidden" name="hecho" value='<bean:write name="hechoVitalForm" property="id" />' />
                            <input type="hidden" name="idHechoProc" value='<bean:write name="procedimientoHv" property="id" />' />
                            <input type="hidden" name="operacion" value="subir" />
                            <input type="hidden" name="action" value='<bean:message key="hechovital.relacion.procedimientos" />' />
                            <html:submit styleClass="dreta"><bean:message key="boton.subir" /></html:submit>
                        </html:form>
                    </logic:notEqual>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaProcedimientos()"><bean:message key="boton.nuevo" /></a>]
    </center>
</logic:present>


<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>
