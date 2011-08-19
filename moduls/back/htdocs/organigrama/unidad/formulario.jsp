<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<% String context=request.getContextPath();%>
<!-- (PORMAD) -->
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>
<style type="text/css">
	 <!--
	 	.itemsComponent { display:none; }
		.textpetit1 strong { cursor:pointer; }
		.textconsulta2 { padding:3px 2px !important; }
		.textconsulta2 input { width:30px; font:normal 1em Arial, Helvetica, sans-serif; text-align:center; margin-right:10px; }
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
	var context = '\<%=request.getContextPath()%>';
    function baja(){
        return confirm("<bean:message key='alerta.baja' />");
    }

    function validar(form) {
       return validateUnidadForm(form);
    }

    function cambiaPadre(){
       <%--obrir("<html:rewrite page='/organigrama/unidad/poparbol.do'/>?action=", "<bean:message key='boton.seleccionar' />", 538, 440);--%>
       obrir("<html:rewrite page='/organigrama/unidad/poparbol.do'/>?idUA=0&action=&ficha=true", "<bean:message key='boton.relacionar' />", 538, 440);
    }
    function popUA(){
            poprealcion = obrirScroll(context+"/organigrama/unidad/poparbol.do?idUA=0&action=&ficha=true", "Relacionar", 538, 440);
        }

    function actualizaUA(id, nombre) {
        if (id == 0){
            document.forms[0].idPadre.value = "";
        } else {
            document.forms[0].idPadre.value = id;
        }
        document.forms[0].nombrePadre.value = nombre;
    }

    // Función para abrir la lista de fichasUA para relacion
    function altaFichaUA(){
        obrir("<html:rewrite page='/organigrama/unidad/popFichaUA.do'/>", "<bean:message key='boton.seleccionar' />", 538, 175);
    }

	// Funcio per mostrar una capa i ocultar la resta
	function activarCapa(capa, n) {
        for (i = 0; i < n; i++) {
            capeta = document.getElementById("capa" + i);
            if (i == capa) {
                //capeta.style.visibility="visible";
                capeta.style.display='block';

            } else {
                 //capeta.style.visibility="hidden";
                capeta.style.display='none';
            }
        }
	}

    // Función para abrir la lista de edificios para relación
    function listaEdificios(){
        obrir("<html:rewrite page='/organigrama/unidad/popedificio.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    // Función para abrir la lista de materias para relación
    function listaMaterias(){
        obrir("<html:rewrite page='/organigrama/unidad/popmateria.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    // Función para abrir la lista de usuarios para relación
    function listaUsuarios(){
        obrir("<html:rewrite page='/organigrama/unidad/popusuario.do'/>", "<bean:message key='boton.seleccionar' />", 538, 140);
    }

    <logic:present name="unidadForm" property="id">

        function asignarFichaUA(idSeccion, idFicha){
            accion = '<bean:message key="ua.relacion.seccion" />';
            idUA = '<bean:write name="unidadForm" property="id" />';
            document.location = "<html:rewrite page='/organigrama/unidad/editar.do'/>?action=" + accion + "&ficha=" + idFicha + "&seccion=" + idSeccion + "&ua=" + idUA + "&operacion=alta";
        }

        function asignarEdificio(idEdificio){
            accion = '<bean:message key="ua.edificios" />';
            idUnidad = '<bean:write name="unidadForm" property="id" />';
            document.location = "<html:rewrite page='/organigrama/unidad/editar.do'/>?action=" + accion + "&unidad=" + idUnidad + "&edificio=" + idEdificio + "&operacion=alta";
        }

        function asignarMateria(idMateria){
            accion = '<bean:message key="ua.materias" />';
            idUnidad = '<bean:write name="unidadForm" property="id" />';
            document.location = "<html:rewrite page='/organigrama/unidad/editar.do'/>?action=" + accion + "&unidad=" + idUnidad + "&materia=" + idMateria + "&operacion=alta";
        }

        function asignarUsuario(idUsuario){
            accion = '<bean:message key="ua.usuarios" />';
            idUnidad = '<bean:write name="unidadForm" property="id" />';
            document.location = "<html:rewrite page='/organigrama/unidad/editar.do'/>?action=" + accion + "&unidad=" + idUnidad + "&usuario=" + idUsuario + "&operacion=alta";
        }



    </logic:present>

// -->
</script>

<!-- tinyMCE -->
<script language="javascript" type="text/javascript" src="../../tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript">

tinyMCE.init({
	mode : "textareas",
	theme : "advanced",
	plugins : "advlink",
	theme_advanced_buttons1 : "bold,italic,underline,separator,justifyleft,justifycenter,justifyright,justifyfull,bullist,numlist,separator,outdent,indent,separator,link,unlink,forecolor,removeformat,cleanup,code",
	theme_advanced_buttons2 : "",		
	theme_advanced_buttons3 : "",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	theme_advanced_path_location : "bottom",
	verify_html : false,
	theme_advanced_resizing : false,			
	language: "ca"	
});
</script>
<!-- /tinyMCE -->

<style type="text/css">
<!--
	.capa { position:static !important; visibility:inherit !important; }
	.tiny { display:block; float:none; width:580px; text-align:center; }
-->
</style>



<div class="bloc">
    <logic:notPresent name="unidadForm" property="id">
	    <h1><bean:message key="ua.alta" /></h1>
    </logic:notPresent>
    <logic:present name="unidadForm" property="id">
        <h1><bean:message key="ua.modificacion" /></h1>
    </logic:present>
	<h2>
	<bean:message key="ua.datos" />
    <logic:present name="unidadForm" property="id">
       &nbsp;(<bean:message key="ua.id" />:<bean:write name="unidadForm" property="id" />)
    </logic:present>	
	</h2>
</div>
<br />

<html:errors/>

<%session.setAttribute("action_path_key",null);%>
<html:form action="/organigrama/unidad/editar" styleId="unidadForm" enctype="multipart/form-data" >

     <html:hidden property="id" />
     <div class="bloc">
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.claveHita" /></div>
            <html:text styleClass="btext" property="claveHita" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.codigoEstandar" /></div>
            <html:text styleClass="btext" property="codigoEstandar" maxlength="256" tabindex="1" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.dominio" /></div>
            <html:text styleClass="btext" property="dominio" maxlength="256" tabindex="2" />
        </div>
       <div class="component">
            <div class="etiqueta"><bean:message key="ua.validacion" /></div>
            <html:select property="validacion" tabindex="4">
                <html:option value="" key="select.defecto" />
                <html:option value="1" key="validacion.publica" />
                <html:option value="2" key="validacion.interna" />
            </html:select>
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.responsable" /></div>
            <html:text styleClass="btext" property="responsable" maxlength="256" tabindex="5" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.telefono" /></div>
            <html:text styleClass="btext" property="telefono" maxlength="256" tabindex="6" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.fax" /></div>
            <html:text styleClass="btext" property="fax" maxlength="256" tabindex="7" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.email" /></div>
            <html:text styleClass="btext" property="email" maxlength="256" tabindex="8" />
        </div>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.sexoResponsable" /></div>
            <div class="areaboto">
					<div class="opcio"><html:radio property="sexoResponsable" value="1"><bean:message key="ua.hombre" /></html:radio></div>
					<div class="opcio"><html:radio property="sexoResponsable" value="2"><bean:message key="ua.mujer" /></html:radio></div>
			</div>
        </div>
        <logic:notEmpty name="unidadForm" property="nombrefotop">
            <div class="component">
                <div class="etiqueta"><bean:message key="ua.fotop" /></div>
                <bean:define id="imgurl"><html:rewrite page="/unidad/foto/pequenya.do" paramId="idUnidad" paramName="unidadForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="unidadForm" property="nombrefotop" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrefotop"/> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarfotop"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.fotop" /></div>
            <html:file styleClass="bfile" property="fotopfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="9" />
        </div>
        <logic:notEmpty name="unidadForm" property="nombrefotog">
            <div class="component">
                <div class="etiqueta"><bean:message key="ua.fotog" /></div>
                <bean:define id="imgurl"><html:rewrite page="/unidad/foto/grande.do" paramId="idUnidad" paramName="unidadForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="unidadForm" property="nombrefotog" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrefotog"/> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarfotog"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.fotog" /></div>
            <html:file styleClass="bfile" property="fotogfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="10" />
        </div>


        <logic:notEmpty name="mostrarLogos">

        <logic:notEmpty name="unidadForm" property="nombrelogoh">
            <div class="component">
                <div class="etiqueta"><bean:message key="ua.logoh" /></div>
                <bean:define id="imgurl"><html:rewrite page="/unidad/foto/logoh.do" paramId="idUnidad" paramName="unidadForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="unidadForm" property="nombrelogoh" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrefotog"/> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarlogoh"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.logoh" /></div>
            <html:file styleClass="bfile" property="logohfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="10" />
        </div>

        <logic:notEmpty name="unidadForm" property="nombrelogov">
            <div class="component">
                <div class="etiqueta"><bean:message key="ua.logov" /></div>
                <bean:define id="imgurl"><html:rewrite page="/unidad/foto/logov.do" paramId="idUnidad" paramName="unidadForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="unidadForm" property="nombrelogov" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrefotog"/> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarlogov"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.logov" /></div>
            <html:file styleClass="bfile" property="logovfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="10" />
        </div>

        <logic:notEmpty name="unidadForm" property="nombrelogos">
            <div class="component">
                <div class="etiqueta"><bean:message key="ua.logos" /></div>
                <bean:define id="imgurl"><html:rewrite page="/unidad/foto/logos.do" paramId="idUnidad" paramName="unidadForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="unidadForm" property="nombrelogos" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrefotog"/> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarlogos"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.logos" /></div>
            <html:file styleClass="bfile" property="logosfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="10" />
        </div>

        <logic:notEmpty name="unidadForm" property="nombrelogot">
            <div class="component">
                <div class="etiqueta"><bean:message key="ua.logot" /></div>
                <bean:define id="imgurl"><html:rewrite page="/unidad/foto/logot.do" paramId="idUnidad" paramName="unidadForm" paramProperty="id" /></bean:define>
                <a href="javascript:obrirImatge('<%=imgurl%>')" class="ctext" onfocus="this.blur()"><bean:write name="unidadForm" property="nombrelogot" /></a>
                <%-- <html:text styleClass="ctext" readonly="readonly" property="nombrefotog"/> --%>
                <div class="checkconsulta1">
                    <html:checkbox property="borrarlogot"><bean:message key="boton.eliminar" /></html:checkbox>
                </div>
            </div>
        </logic:notEmpty>
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.logot" /></div>
            <html:file styleClass="bfile" property="logotfile" size="43" style="font-family: verdana, arial, helvetica, sans-serif;font-size:9pt" tabindex="10" />
        </div>

        </logic:notEmpty>

        <div class="component">
            <div class="etiqueta"><bean:message key="ua.padre" /></div>
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
                    <input type="text" class="ctext" id="nombrePadre" readonly="1" value='- <bean:message key="ua.raiz" /> -' />
                </logic:notPresent>
                <div class="botoneraconsulta1">
                    <input type="button" onclick="cambiaPadre()" value="<bean:message key='boton.modificar' />" tabindex="11"/>
                </div>
        </div>


        <div class="component">
            <div class="etiqueta"><bean:message key="ua.tratamiento" /></div>
            <html:select styleClass="btext" property="idTratamiento" tabindex="13">
                <html:option value="" key="select.defecto" />
                <html:options collection="listaTratamientos" property="id" labelProperty="traduccion.tipo" />
            </html:select>
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="ua.espacio" /></div>
            <html:select styleClass="btext" property="idEspacioTerrit" tabindex="13">
                <html:option value="" key="select.defecto" />
                <html:options collection="listaEspacios" property="id" labelProperty="traduccion.nombre" />
            </html:select>
        </div>
        
        <logic:notEmpty name="mostrarFotosTipo">
        
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.confportada" /></div>
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="ua.numfototipo" /> 1</div>
            <html:text styleClass="btext" property="numfoto1" maxlength="3" tabindex="14" size="4" />
        </div>

        <div class="component">
            <div class="etiqueta"><bean:message key="ua.numfototipo" /> 2</div>
            <html:text styleClass="btext" property="numfoto2" maxlength="3" tabindex="15" size="4" />
        </div>
        
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.numfototipo" /> 3</div>
            <html:text styleClass="btext" property="numfoto3" maxlength="3" tabindex="16" size="4" />
        </div>
        
        <div class="component">
            <div class="etiqueta"><bean:message key="ua.numfototipo" /> 4</div>
            <html:text styleClass="btext" property="numfoto4" maxlength="3" tabindex="17" size="4" />
        </div>
        
        </logic:notEmpty>
    </div>
    <br/>
    <br/>

    <div id="capes">
        <bean:size id="capes" name="unidadForm" property="traducciones"/>
        <logic:iterate id="traducciones" name="unidadForm" property="traducciones" indexId="i" >
           	<div id="capa<%=i%>" class="capa" style="<%=(i.intValue()==0)?"display:block;":"display:none;" %>">
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
                        <div class="etiqueta"><bean:message key="ua.nombre" /></div>
                        <html:text styleClass="btext" property="nombre" name="traducciones" indexed="true" />
                    </div>
                    <div class="component">
                        
           				<!-- tiny  -->
						<div>
							<div class="etiqueta tiny"><bean:message key="ua.presentacion" /></div>
							<html:textarea name="traducciones" property="presentacion" indexed="true" rows="5" cols="60" style="width:585px; height:120px;" />
						</div>
						
                    </div>
                    <div class="component">
                        <div class="etiqueta"><bean:message key="ua.abreviatura" /></div>
                        <html:text styleClass="btext" property="abreviatura" name="traducciones" indexed="true" />
                    </div>
                    <div class="component">
                        <div class="etiqueta"><bean:message key="ua.url" /></div>
                        <html:text styleClass="btext" property="url" name="traducciones" indexed="true" maxlength="512"/>
                    </div>
                </div>
            </div>
        </logic:iterate>
    </div>
    <br />

     <div class="botonera">
        <html:submit styleClass="esquerra" property="action" onclick="return validar(this.form);" tabindex="100">
            <logic:notPresent name="unidadForm" property="id">
                <bean:message key="boton.insertar" />
            </logic:notPresent>
            <logic:present name="unidadForm" property="id">
                <bean:message key="boton.modificar" />
            </logic:present>
        </html:submit>
        <html:reset styleClass="esquerra" tabindex="101"><bean:message key="boton.reiniciar" /></html:reset>
        <html:cancel styleClass="dreta" tabindex="103"><bean:message key="boton.cancelar" /></html:cancel>
        <logic:notPresent name="unidadForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" >
                <bean:message key="boton.busqueda" />
            </html:submit>
        </logic:notPresent>
        <logic:present name="unidadForm" property="id">
            <html:submit styleClass="dreta" property="action" tabindex="102" onclick="return baja();">
                <bean:message key="boton.eliminar" />
            </html:submit>
        </logic:present>
    </div>
</html:form>


<logic:notPresent name="unidadForm" property="id">
    <center>
    [<a href="javascript: listaEdificios('busqueda')" ><bean:message key="ua.busqueda.edificio" /></a>]
    </center>
</logic:notPresent>

<bean:define id="etiquetaSelect"><bean:message key="boton.seleccionar" /></bean:define>
<logic:present name="unidadForm" property="id">

    <logic:present role="sacsystem,sacadmin">
    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ua.usuarios" />
        </div>
        <logic:empty name="usuarioOptions">
            <br /><h2><bean:message key="ua.usuarios.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="usuarioOptions">
            <logic:iterate id="usuario" name="usuarioOptions">
            <div class="component">
                <div class="textconsulta1">
                <html:link action='<%="/sistema/usuario/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idUsuario" paramName="usuario" paramProperty="id">
                    <bean:write name="usuario" property="nombre" />
                    (<bean:write name="usuario" property="username" />)
                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/unidad/editar" >
                        <input type="hidden" name="usuario" value='<bean:write name="usuario" property="id" />' />
                        <input type="hidden" name="unidad" value='<bean:write name="unidadForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="ua.usuarios" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript:listaUsuarios()" ><bean:message key="boton.nuevo" /></a>]
    </center>
    </logic:present>

    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ua.edificios" />
        </div>
        <logic:empty name="edificioOptions">
            <br /><h2><bean:message key="ua.edificios.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="edificioOptions">
            <logic:iterate id="edificio" name="edificioOptions">
            <div class="component">
                <div class="textconsulta1">
                <html:link action='<%="/organigrama/edificio/seleccionar?action=" + etiquetaSelect%>'
                           paramId="idSelect" paramName="edificio"paramProperty="id">
                    <logic:notEmpty name="edificio" property="traduccion">
                    <bean:write name="edificio" property="traduccion.descripcion" />
                    </logic:notEmpty>
                     <logic:empty name="edificio" property="traduccion">
                        <bean:message key="ua.edificio.noTraduccion" />
                    </logic:empty>

                </html:link>
                </div>
                <div class="botoneraconsulta1">
                    <html:form action="/organigrama/unidad/editar" >
                        <input type="hidden" name="edificio" value='<bean:write name="edificio" property="id" />' />
                        <input type="hidden" name="unidad" value='<bean:write name="unidadForm" property="id" />' />
                        <input type="hidden" name="action" value='<bean:message key="ua.edificios" />' />
                        <input type="hidden" name="operacion" value='baja' />
                        <html:submit><bean:message key="boton.eliminar" /></html:submit>
                    </html:form>
                </div>
            </div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript:listaEdificios()" ><bean:message key="boton.nuevo" /></a>]
    </center>


    <br /><br />

    <div class="bloc">
        <div class="titolbloc">

            <bean:message key="unimat.materias"  />
        </div>
        <logic:empty name="unidadesmateriasOptions">
            <br /><h2><bean:message key="unimat.vacio" /></h2><br />
        </logic:empty>
        <logic:notEmpty name="unidadesmateriasOptions">
            <logic:iterate id="unidadmateria" name="unidadesmateriasOptions">
            <div class="component">
                <div class="textconsulta2">
                    <bean:write name="unidadmateria" property="materia.traduccion.nombre" />
                </div>
                <div class="botoneraconsulta2">
                    <html:form action="/organigrama/unimat/seleccionar" >
                        <input type="hidden" name="idUnidad"  value="<bean:write name="unidadForm" property="id"/> "/>
                        <input type="hidden" name="id" value='<bean:write name="unidadmateria" property="id" />' />
                        <html:submit property="action">
                            <bean:message key="boton.seleccionar" />
                        </html:submit>
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
   <%-- [<a href="javascript:listaMaterias()" ><bean:message key="boton.nuevo" /></a>]--%>
    [<html:link page="/organigrama/unimat/form.do"
               paramId="idUnidad"
               paramName="unidadForm"
               paramProperty="id">
        <bean:message key="boton.nuevo" />
    </html:link>]
    </center>


    <br /><br />

    <div class="bloc">
        <div class="titolbloc">
            <bean:message key="ua.relacion.seccion.titulo" />
        </div>
        <logic:empty name="fichaUAOptions">
            <br /><h2><bean:message key="ficha.relacion.vacio" /></h2><br />
        </logic:empty>

	 <style type="text/css">
	 <!--
	 	.itemsComponent { display:none; }
		.textpetit1 strong { cursor:pointer; }
	 -->
	 </style>
	 <script type="text/javascript">
	 <!--
	 
	 	function veureComponents(obj) {
			pare = obj.parentNode.parentNode.parentNode.parentNode;
			items = pare.getElementsByTagName('div');
			for(i=0;i<items.length;i++) {
				if(items[i].className == 'itemsComponent') {
					items[i].style.display = (items[i].style.display == 'block') ? 'none' : 'block';
				}
			}
		}
	 -->
	 </script>

        <logic:notEmpty name="fichaUAOptions">

            <logic:iterate id="entry" name="fichaUAOptions">       
            <div class="itemsPare">     
                <div class="component">
                    <div class="textseparat">
                        <div class="textpetit1" style="background-color: #d8dfe7">
                        	<bean:define id="seccionidnombre"><bean:write name="entry" property="key" /></bean:define>
                        	<bean:define id="seccionnombre"><%=seccionidnombre.substring(seccionidnombre.indexOf("#")+1,seccionidnombre.length()) %></bean:define>
                                 	
                            	<strong onclick="veureComponents(this);">
                            	<bean:write name="seccionnombre" filter="false"/>
                            	                        <bean:define id="fichasSeccion" name="entry" property="value"/>
							(<%=((java.util.List)fichasSeccion).size()%> <bean:message key="ua.fichas" />)
				[+]			
                            </strong>
                        </div>
                        <div class="textgros5" style="background-color: #d8dfe7">
                            &nbsp;
                        </div>
                    </div>
                    <div class="botoneraconsulta1"></div>
                </div>                           		  
			<div class="itemsComponent">
                
				<html:form action="/organigrama/unidad/editar" >
                
                <logic:iterate id="fichaUA" name="entry" property="value" indexId="inFicha">
                
                <div class="component">
                    <div class="textconsulta2">
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
	                    <input type="text" name="orden_fic<bean:write name="fichaUA" property="id" />" size="4" maxlength="4" value="<bean:write name="fichaUA" property="orden" />" />
	                 </td>   
			<td>
                        <html:link action='<%="/contenido/ficha/seleccionar?action=" + etiquetaSelect%>'
                                       paramId="idSelect" paramName="fichaUA" paramProperty="ficha.id">
                            <%--<bean:write name="fichaUA" property="ficha.traduccion.titulo" />--%>

                            <logic:notEmpty name="fichaUA" property="ficha.traduccion">
                            <bean:write name="fichaUA" property="ficha.traduccion.titulo" />
                            </logic:notEmpty>
                             <logic:empty name="fichaUA" property="ficha.traduccion">
                            	<bean:message key="ua.ficha.noTraduccion" />
                            </logic:empty>
                        </html:link>
	                 </td>
	                 </tr>
               		 </table>                         
                    </div>
                    <div class="botoneraconsulta2">

	                    <input type="button" value="<bean:message key="boton.eliminar" />" name="<bean:message key="boton.eliminar" />" onclick='document.location.href="<%=context%>/organigrama/unidad/editar.do?action=<bean:message key="ua.relacion.seccion" />&operacion=baja&fichaUA=<bean:write name="fichaUA" property="id" />&ua=<bean:write name="unidadForm" property="id" />";' />
    
                        <logic:notEqual name="inFicha" value="0">
							<input type="button" value="<bean:message key="boton.subir" />" name="<bean:message key="boton.subir" />" onclick='document.location.href="<%=context%>/organigrama/unidad/editar.do?action=<bean:message key="ua.relacion.seccion" />&operacion=subir&fichaUA=<bean:write name="fichaUA" property="id" />&ua=<bean:write name="unidadForm" property="id" />";' />
                        </logic:notEqual>
                    </div>
                </div>
                
                </logic:iterate>

                <input type="hidden" name="ua" value='<bean:write name="unidadForm" property="id" />' />
                <input type="hidden" name="action" value='<bean:message key="ua.relacion.seccion" />' />
                <input type="hidden" name="operacion" value='actualizar_orden' />

                <html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
                </html:form>

        	</div></div>
            </logic:iterate>
        </logic:notEmpty>
    </div><br />
    <center>
    [<a href="javascript: altaFichaUA()" ><bean:message key="boton.nuevo" /></a>]
    </center>
    <br /><br />
</logic:present>

  
    <div class="bloc">
	        <div class="titolbloc">
	            <bean:message key="procedimiento.lista" />
	        </div>
        <logic:empty name="procedimientoOptions">
            <br /><h2><bean:message key="ficha.relacion.vacio" /></h2><br />
        </logic:empty>
        
        <logic:notEmpty name="procedimientoOptions">
         
         <html:form action="/organigrama/unidad/editar" >
            <logic:iterate id="procedimiento" name="procedimientoOptions">
              <div class="component">
	                <div class="textconsulta1">
	                   <table>
	                     <tr>
	                         <td>
	                         <logic:equal name="conse" value="1">
		                        <input type="text" name="orden_pro<bean:write name="procedimiento" property="id" />" size="4" maxlength="4" value=
		                        <logic:equal name="procedimiento" property="orden" value="9999">
		                        	''
		                        </logic:equal>
		                        <logic:notEqual  name="procedimiento" property="orden" value="9999">
		                        	'<bean:write name="procedimiento" property="orden" />'
		                        </logic:notEqual>
		                        />
		                     </logic:equal>
	                    
	                        <logic:equal name="conse" value="2">
		                        <input type="text" name="orden_pro<bean:write name="procedimiento" property="id" />" size="4" maxlength="4" value=
		                        <logic:equal name="procedimiento" property="orden2" value="9999">
		                        	''
		                        </logic:equal>
		                        <logic:notEqual  name="procedimiento" property="orden2" value="9999">
		                        	'<bean:write name="procedimiento" property="orden2" />'
		                        </logic:notEqual>
		                        />                        
	                         </logic:equal>
	                         
	                         <logic:equal name="conse" value="3">
		                        <input type="text" name="orden_pro<bean:write name="procedimiento" property="id" />" size="4" maxlength="4" value=
		                        <logic:equal name="procedimiento" property="orden3" value="9999">
		                        	''
		                        </logic:equal>
		                        <logic:notEqual  name="procedimiento" property="orden3" value="9999">
		                        	'<bean:write name="procedimiento" property="orden3" />'
		                        </logic:notEqual>
		                        />                        
	                         </logic:equal>
	                          
		                 </td>
		                 <td>   
	                         <logic:notEmpty name="procedimiento" property="traduccion">                                                       
	                    		<bean:write name="procedimiento" property="traduccion.nombre" />
                            </logic:notEmpty>
                            <logic:empty name="procedimiento" property="traduccion">
                            	<bean:write name="procedimiento" property="id" /> - <bean:message key="ua.procedimiento.noTraduccion" />
                            </logic:empty> 
	                         
	                         </td>
	                     </tr>
	                    </table> 
	                     
	                </div>
                    <div class="botoneraconsulta2">                
                		<input type="button" value="<bean:message key="boton.seleccionar" />" name="<bean:message key="boton.seleccionar" />" onclick='document.location.href="<%=context%>/contenido/procedimiento/seleccionar.do?action=<bean:message key="boton.seleccionar" />&idSelect=<bean:write name="procedimiento" property="id" />";' />                	
	                </div>
            	</div>
          
            </logic:iterate>
             	
	 	<html:submit styleClass="boton"><bean:message key="boton.actualizar_orden" /></html:submit>
	 	<input type="hidden" name="ua" value='<bean:write name="unidadForm" property="id" />' />
				  <logic:equal name="conse" value="1">	 	
               	 	 <input type="hidden" name="action" value='<bean:message key="ua.relacion.procedimientos" />' />
                  </logic:equal>
                 <logic:equal name="conse" value="2">
                  	 <input type="hidden" name="action" value='<bean:message key="ua.relacion.procedimientos2" />' />
                 </logic:equal>
                 <logic:equal name="conse" value="3">
                  	 <input type="hidden" name="action" value='<bean:message key="ua.relacion.procedimientos3" />' />
                 </logic:equal>
            	  <input type="hidden" name="operacion" value='actualizar_orden' />
	 </html:form>  
	</logic:notEmpty>
    </div><br />
      
 
<!-- XAPUÇA -->
<% pageContext.removeAttribute(Globals.XHTML_KEY);%>
<html:javascript
    formName="unidadForm"
    dynamicJavascript="true"
    staticJavascript="false"
    htmlComment="true"
    cdata="false"
/>
<% pageContext.setAttribute(Globals.XHTML_KEY, "true");%>


<!--  enric@dgtic Modificat el alert per poder ser capturat per Selenium -->

<logic:present name="alert">
	<script type="text/javascript">
	<!--
      function handleAlert() {
			 alert("<bean:message name='alert' />");
	  }
	-->
	</script>
 </logic:present>

<logic:present name="alert">
	<script type="text/javascript">
	<!--
	window.setTimeout("handleAlert()",1000)
	-->
	</script>
</logic:present>

