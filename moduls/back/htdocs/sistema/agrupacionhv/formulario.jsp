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
       return validateAgrupacionHVForm(form);
    }


    <logic:present name="agrupacionHVForm" property="id">

        // Función para abrir la lista de hechosvitales a relacionar
        function listaHechosvitales(){
            obrir("<html:rewrite page='/sistema/agrupacion/pophecho.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }

        function asignarHechovital(idHechovital){
            accion = '<bean:message key="agrupacion.relacion.hechosvitales" />';
            idAgru = '<bean:write name="agrupacionHVForm" property="id" />';
            document.location = "<html:rewrite page='/sistema/agrupacion/editar.do'/>?action=" + accion + "&agru=" + idAgru + "&hechovital=" + idHechovital + "&operacion=alta";
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
        <logic:notPresent name="agrupacionHVForm" property="id">
            <bean:message key="agrupacion.alta" />
        </logic:notPresent>
        <logic:present name="agrupacionHVForm" property="id">
            <bean:message key="agrupacion.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="agrupacion.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/agrupacion/editar" styleId="agrupacionHVForm" enctype="multipart/form-data">
    <logic:present name="agrupacionHVForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
    	<div class="component">
        	<div class="etiqueta"><bean:message key="agrupacion.codiestandar" /></div>
            <html:text styleClass="btext" property="codigoEstandar" maxlength="256" tabindex="1" />
        </div>
        <logic:notEmpty name="agrupacionHVForm" property="nombrefoto">
           <div class="component">
                <div class="etiqueta"><bean:message key="agrupacion.foto" /></div>
                <bean:define id="imgurl"><html:rewrite page="/agrupacion/foto.do" paramId="idAgrup" paramName="agrupacionHVForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="agrupacionHVForm" property="nombrefoto" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarfoto"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="agrupacion.foto" /></div>
            <html:file styleClass="bfile" property="fotofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>


        <!-- icono -->
        <logic:notEmpty name="agrupacionHVForm" property="nombreicono">
           <div class="component">
                <div class="etiqueta"><bean:message key="agrupacion.icono" /></div>
                <bean:define id="imgurl"><html:rewrite page="/agrupacion/icono.do" paramId="idAgrup" paramName="agrupacionHVForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="agrupacionHVForm" property="nombreicono" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borraricono"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="agrupacion.icono" /></div>
            <html:file styleClass="bfile" property="iconofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>

        <!--icono grande-->
        <logic:notEmpty name="agrupacionHVForm" property="nombreiconog">
           <div class="component">
                <div class="etiqueta"><bean:message key="agrupacion.iconog" /></div>
                <bean:define id="imgurl"><html:rewrite page="/agrupacion/icono/grande.do" paramId="idAgrup" paramName="agrupacionHVForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="agrupacionHVForm" property="nombreiconog" /></a>
                <%--
                <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/>
                --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrariconog"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="agrupacion.iconog" /></div>
            <html:file styleClass="bfile" property="iconogfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="4" />
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="agrupacion.publico" /></div>
            <html:select styleClass="btext" property="idPublico" tabindex="13">
                <html:option value="" key="select.defecto" />
                <html:options collection="listaPublico" property="id" labelProperty="traduccion.titulo" />
            </html:select>
        </div>
    </div>
    <br/>
    <br/>
	
    <div id="capes">
    <bean:size id="capes" name="agrupacionHVForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="agrupacionHVForm" property="traducciones" indexId="i" >
        <div id="capa<%=i%>" class="capa">
            <div  class="pestanyes">
                <logic:iterate id="lang" name="org.ibit.rol.sac.back.LANGS_KEY" indexId="j">
                    <% if (i.intValue() == j.intValue()) {%>
                    <div class="tab"><bean:message name="lang" /></div>
                    <% } else { %>
                    <div class="notab"><a href="javascript:activarCapa(<%=j%>, <%=capes%>)" class="notabb"><bean:message name="lang" /></a></div>
                    <% } %>
                </logic:iterate>
            </div>
            <div class="bloc">
                <div class="component">
                    <div class="etiqueta"><bean:message key="agrupacion.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="2" maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="agrupacion.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" tabindex="3" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="agrupacion.palabras" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="palabrasclave" indexed="true" rows="3" cols="60" tabindex="4"  />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <br />

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="agrupacionHVForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="agrupacionHVForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="agrupacionHVForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="agrupacionHVForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="agrupacionHVForm" property="id">
    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="agrupacion.relacion.hechosvitales" />
        </div>
        <logic:empty name="listaHechosvitales">
            <br /><h2><bean:message key="agrupacion.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="listaHechosvitales">
            <logic:iterate id="hechovitalAg" name="listaHechosvitales" indexId="ixHech">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=ixHech.intValue() + 1%>
                    </div>
           			<logic:present name="hechovitalAg">
                    <div class="textgros3" >
                        <bean:write name="hechovitalAg" property="hechoVital.traduccion.nombre" />
                    </div>
                    </logic:present>
                </div>
                <div class="botoneraconsulta3">
                    <html:form action="/sistema/hechovital/seleccionar">
                        <logic:present name="hechovitalAg">
                        <input type="hidden" name="idSelect" value='<bean:write name="hechovitalAg" property="hechoVital.id" />' />
                        </logic:present> 
                        <html:submit property="action" styleClass="dreta" ><bean:message key="boton.seleccionar" /></html:submit>
                    </html:form>
                    <html:form action="/sistema/agrupacion/editar">
                        <input type="hidden" name="agru" value='<bean:write name="agrupacionHVForm" property="id" />' />
                        <logic:present name="hechovitalAg">
                        <input type="hidden" name="idAgruHecho" value='<bean:write name="hechovitalAg" property="id" />' />
                        </logic:present>
                        <input type="hidden" name="operacion" value="baja" />
                        <input type="hidden" name="action" value='<bean:message key="agrupacion.relacion.hechosvitales" />' />
                        <html:submit styleClass="dreta" ><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                    <logic:notEqual name="ixHech" value="0">
                        <html:form action="/sistema/agrupacion/editar">
                            <input type="hidden" name="agru" value='<bean:write name="agrupacionHVForm" property="id" />' />
                            <logic:present name="hechovitalAg">
                            <input type="hidden" name="idAgruHecho" value='<bean:write name="hechovitalAg" property="id" />' />
                            </logic:present>
                            <input type="hidden" name="operacion" value="subir" />
                            <input type="hidden" name="action" value='<bean:message key="agrupacion.relacion.hechosvitales" />' />
                            <html:submit styleClass="dreta"><bean:message key="boton.subir" /></html:submit>
                        </html:form>
                    </logic:notEqual>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaHechosvitales()"><bean:message key="boton.nuevo" /></a>]
    </center>
</logic:present>


<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>
