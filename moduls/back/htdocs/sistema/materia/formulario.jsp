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
       return validateMateriaForm(form);
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

<div class="bloc">
    <h1>
        <logic:notPresent name="materiaForm" property="id">
            <bean:message key="materia.alta" />
        </logic:notPresent>
        <logic:present name="materiaForm" property="id">
            <bean:message key="materia.modificacion" />
        </logic:present>
    </h1>
    <h2>
        <bean:message key="materia.datos" />
    </h2>
</div>

<br />

<html:errors/>

<html:form action="/sistema/materia/editar" styleId="materiaForm" enctype="multipart/form-data">
    <logic:present name="materiaForm" property="id">
        <html:hidden property="id" />
    </logic:present>
    <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="materia.codihita" /></div>
            <html:text styleClass="btext" property="codiHita" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
        	<div class="etiqueta"><bean:message key="materia.codiestandar" /></div>
            <html:text styleClass="btext" property="codigoEstandar" maxlength="256" tabindex="2" />
        </div>
        <!-- foto materia -->
        <logic:notEmpty name="materiaForm" property="nombrefoto">
        <div class="component">
            <div class="etiqueta"><bean:message key="materia.foto" /></div>
            <bean:define id="imgurl"><html:rewrite page="/materia/foto.do" paramId="idMateria" paramName="materiaForm" paramProperty="id" /></bean:define>
            <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="materiaForm" property="nombrefoto" /></a>

            <div class="checkconsulta1">
                <html:checkbox property="borrarfoto"><bean:message key="boton.eliminar" /></html:checkbox>
            </div>
        </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="materia.foto" /></div>
            <html:file styleClass="bfile" property="fotofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>



        <!-- icono -->
        <logic:notEmpty name="materiaForm" property="nombreicono">
        <div class="component">
           <div class="etiqueta"><bean:message key="materia.icono" /></div>
           <bean:define id="imgurl"><html:rewrite page="/materia/icono.do" paramId="idMateria" paramName="materiaForm" paramProperty="id" /></bean:define>
           <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="materiaForm" property="nombreicono" /></a>

           <div class="checkconsulta1">
               <html:checkbox property="borraricono"><bean:message key="boton.eliminar" /></html:checkbox>
           </div>
        </div>
        </logic:notEmpty>
        <div class="component">
           <div class="etiqueta"><bean:message key="materia.icono" /></div>
           <html:file styleClass="bfile" property="iconofile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>


        
        <!-- icono grande -->
        <logic:notEmpty name="materiaForm" property="nombreiconog">
        <div class="component">
           <div class="etiqueta"><bean:message key="materia.iconog" /></div>
           <bean:define id="imgurl"><html:rewrite page="/materia/icono/grande.do" paramId="idMateria" paramName="materiaForm" paramProperty="id" /></bean:define>
           <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="materiaForm" property="nombreiconog" /></a>

           <div class="checkconsulta1">
               <html:checkbox property="borrariconog" ><bean:message key="boton.eliminar" /></html:checkbox>
           </div>
        </div>
        </logic:notEmpty>
        <div class="component">
           <div class="etiqueta"><bean:message key="materia.iconog" /></div>
           <html:file styleClass="bfile" property="iconogfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>

		<logic:present name="materiaForm" property="id">
			<div class="component">
            	<div class="etiqueta"><bean:message key="materia.uaprincipal" /></div>
	            <html:select styleClass="btext" property="idUAPrincipal" tabindex="10">
	                <html:option value="" key="select.defecto" />
	                <html:options collection="listaUAsMateria" property="id" labelProperty="unidad.traduccion.nombre" />
	            </html:select>
	        </div>
	    </logic:present>

        <div class="component">
        	<div class="etiqueta"><bean:message key="materia.destacada" /></div>
            <html:checkbox styleClass="check" tabindex="3" property="destacada" />
        </div>
    </div>
    <br />
	
	<% String idioma = ""; %>
    <div id="capes">
    <bean:size id="capes" name="materiaForm" property="traducciones"/>
    <logic:iterate id="traducciones" name="materiaForm" property="traducciones" indexId="i" >
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
                    <div class="etiqueta"><bean:message key="materia.nombre" /></div>
                    <html:text styleClass="btext" name="traducciones" property="nombre" indexed="true" tabindex="2" maxlength="256" />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.descripcion" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="descripcion" indexed="true" rows="3" cols="60" tabindex="3"  />
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.palabras" /><br /><br /><br /></div>
                    <html:textarea name="traducciones" property="palabrasclave" indexed="true" rows="3" cols="60" tabindex="4"  />
                </div>

                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.distribComp.actual" /></div>
                    <logic:present name="traducciones" property="distribComp">
                        <html:link page='<%="/materia/distribComp.do?lang=" + idioma%>' paramId="idMateria" paramName="materiaForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="distribComp.nombre" /></html:link>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrardistrib_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="distribComp">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="materia.distribComp.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.distribComp.nuevo" /></div>
                    <html:file styleClass="bfile" name="materiaForm" property='<%="distribs[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="5" />
                </div>


                
                 <div class="component">
                    <div class="etiqueta"><bean:message key="materia.normativa.actual" /></div>
                    <logic:present name="traducciones" property="normativa">
                        <html:link page='<%="/materia/normativa.do?lang=" + idioma%>' paramId="idMateria" paramName="materiaForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="normativa.nombre" /></html:link>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrarnormat_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="normativa">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="materia.normativa.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.normativa.nuevo" /></div>
                    <html:file styleClass="bfile" name="materiaForm" property='<%="normats[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="6" />
                </div>

                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.contenido.actual" /></div>
                    <logic:present name="traducciones" property="contenido">
                        <html:link page='<%="/materia/contenido.do?lang=" + idioma%>' paramId="idMateria" paramName="materiaForm" paramProperty="id" styleClass="ctext" onfocus="this.blur()"><bean:write name="traducciones" property="contenido.nombre" /></html:link>
                        <div class="checkconsulta1">
                            <input type="checkbox" name="<%="borrarconten_" + idioma%>" value="true" /><bean:message key="boton.eliminar" />
                        </div>
                    </logic:present>
                    <logic:notPresent name="traducciones" property="contenido">
                        <input type="text" id="aux" class="ctext" readonly="readonly" value='- <bean:message key="materia.contenido.vacio" /> -' />
                    </logic:notPresent>
                </div>
                <div class="component">
                    <div class="etiqueta"><bean:message key="materia.contenido.nuevo" /></div>
                    <html:file styleClass="bfile" name="materiaForm" property='<%="contens[" + i + "]"%>' size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="7" />
                </div>
            </div>
        </div>
    </logic:iterate>
    </div>

    <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="8" >
            <logic:notPresent name="materiaForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="materiaForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="9" ><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="11" ><bean:message key="boton.cancelar" /></html:cancel>
        <logic:present name="materiaForm" property="id">
            <html:submit property="action" styleClass="dreta" tabindex="10" onclick="return baja();"><bean:message key="boton.eliminar" /></html:submit>
        </logic:present>
    </div>
</html:form>

<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="materiaForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>

<logic:present name="materiaForm" property="id">
    <br /><br />
    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="materia.iconos" />
        </div>
        <logic:empty name="iconosMateria">
            <br /><h2><bean:message key="icomat.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="iconosMateria">
            <logic:iterate id="icomat" name="iconosMateria">
            <div class="component">
                <div class="textconsulta1">
                     <bean:define id="imgurl"><html:rewrite page="/icono/materia.do" paramId="idIcoMat" paramName="icomat" paramProperty="id" /></bean:define>
                     <a href="javascript:obrirImatge('<%=imgurl%>')" onfocus="this.blur()"><bean:write name="icomat" property="icono.nombre" /></a>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/sistema/icomat" styleId="iconoMateriaForm">
                        <input type="hidden" name="id" value='<bean:write name="icomat" property="id" />' />
                        <html:submit property="action">
                            <bean:message key="boton.eliminar" />
                        </html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<html:link page="/sistema/materia/icono.do"
               paramId="idMateria"
               paramName="materiaForm"
               paramProperty="id">
        <bean:message key="boton.nuevo" />
    </html:link>]
    </center>
</logic:present>

<script type="text/javascript">
<!--
	<logic:present name="alert">
	alert("<bean:message name='alert' />");
	</logic:present>
-->
</script>
