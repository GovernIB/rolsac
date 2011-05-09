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
       return validateAgrupacionMForm(form);
    }


    <logic:present name="agrupacionMForm" property="id">

        // Función para abrir la lista de materias a relacionar
        function listaMaterias(){
            obrir("<html:rewrite page='/sistema/agrupacionm/popmate.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
        }

        function asignarMateria(idMateria){
            accion = '<bean:message key="agrupacionm.relacion.materias" />';
            idAgru = '<bean:write name="agrupacionMForm" property="id" />';
            document.location = "<html:rewrite page='/sistema/agrupacionm/editar.do'/>?action=" + accion + "&agru=" + idAgru + "&materia=" + idMateria + "&operacion=alta";
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
        <logic:notPresent name="agrupacionMForm" property="id">
            <bean:message key="agrupacionm.alta" />
        </logic:notPresent>
        <logic:present name="agrupacionMForm" property="id">
            <bean:message key="agrupacionm.modificacion" />
        </logic:present>
    </h1>
    <h2><bean:message key="agrupacionm.datos" /></h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/agrupacionm/editar" styleId="agrupacionMForm" enctype="multipart/form-data">
    <logic:present name="agrupacionMForm" property="id">
        <html:hidden property="id" />
    </logic:present>

    <div class="bloc">
    	<div class="component">
        	<div class="etiqueta"><bean:message key="agrupacionm.codiestandar" /></div>
            <html:text styleClass="btext" property="codigoEstandar" maxlength="256" tabindex="1" />
        </div>


        <div class="component">
            <div class="etiqueta"><bean:message key="agrupacionm.seccion" /></div>
            <html:select styleClass="btext" property="idSeccion" tabindex="13">
                <html:option value="" key="select.defecto" />
                <html:options collection="listaSeccion" property="id" labelProperty="traduccion.nombre" />
            </html:select>
        </div>
    </div>
    <br/>
    <br/>
	
    <div id="capes">
    <bean:size id="capes" name="agrupacionMForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="agrupacionMForm" property="traducciones" indexId="i" >
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
                    <div class="etiqueta"><bean:message key="agrupacionm.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="2" maxlength="256" />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <br />

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100" >
            <logic:notPresent name="agrupacionMForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="agrupacionMForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="agrupacionMForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="102" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="agrupacionMForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="agrupacionMForm" property="id">
    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="agrupacionm.relacion.materias" />
        </div>
        <logic:empty name="listaMaterias">
            <br /><h2><bean:message key="agrupacionm.relacion.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="listaMaterias">
            <logic:iterate id="materiaAg" name="listaMaterias" indexId="ixHech">
            <div class="component">
                <div class="textseparat">
                    <div class="textpetit">
                        <%=ixHech.intValue() + 1%>
                    </div>
                    <div class="textgros3" >
                        <bean:write name="materiaAg" property="materia.traduccion.nombre" />
                    </div>
                </div>
                <div class="botoneraconsulta3">
                    <html:form action="/sistema/materia/seleccionar">
                        <input type="hidden" name="idSelect" value='<bean:write name="materiaAg" property="materia.id" />' />
                        <html:submit property="action" styleClass="dreta" ><bean:message key="boton.seleccionar" /></html:submit>
                    </html:form>
                    <html:form action="/sistema/agrupacionm/editar">
                        <input type="hidden" name="agru" value='<bean:write name="agrupacionMForm" property="id" />' />
                        <input type="hidden" name="idAgruMate" value='<bean:write name="materiaAg" property="id" />' />
                        <input type="hidden" name="operacion" value="baja" />
                        <input type="hidden" name="action" value='<bean:message key="agrupacionm.relacion.materias" />' />
                        <html:submit styleClass="dreta" ><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                    <logic:notEqual name="ixHech" value="0">
                        <html:form action="/sistema/agrupacionm/editar">
                            <input type="hidden" name="agru" value='<bean:write name="agrupacionMForm" property="id" />' />
                            <input type="hidden" name="idAgruMate" value='<bean:write name="materiaAg" property="id" />' />
                            <input type="hidden" name="operacion" value="subir" />
                            <input type="hidden" name="action" value='<bean:message key="agrupacionm.relacion.materias" />' />
                            <html:submit styleClass="dreta"><bean:message key="boton.subir" /></html:submit>
                        </html:form>
                    </logic:notEqual>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: listaMaterias()"><bean:message key="boton.nuevo" /></a>]
    </center>
</logic:present>


<script type="text/javascript">
<!--
    <logic:present name="alert">
	alert("<bean:message name='alert' />");
    </logic:present>
-->
</script>
