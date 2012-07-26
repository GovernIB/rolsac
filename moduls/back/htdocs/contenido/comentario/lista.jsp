<%@ page language="java"%>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>
<tiles:useAttribute id="comentarios" name="comentarios" classname="java.util.List"/>
<tiles:useAttribute id="tipo" name="tipo" classname="java.lang.String"/>
<tiles:useAttribute id="idRel" name="idRel" classname="java.lang.Long"/>
<script type="text/javascript">
    function mostrarcomentario(id) {
        obrir("<html:rewrite page='/contenido/comentario/mostrar.do'/>?id=" + id,
                "<bean:message key='boton.seleccionar' />", 538, 200);

    }
</script>
<div class="bloc">
    <div class="titolbloc">
        Comentarios
    </div>
    <logic:empty name="comentarios">
        <br /><h2><bean:message key="comentarios.vacio" /></h2><br />
    </logic:empty>
    <logic:notEmpty name="comentarios">
        <logic:iterate id="comentario" name="comentarios" type="org.ibit.rol.sac.model.Comentario" >
        <div class="component">
            <div class="textseparat">
                <div class="textpetit1">&nbsp;
                    <a href="javascript:mostrarcomentario(<bean:write name="comentario" property="id" />)">
                        <logic:notEmpty name="comentario" property="motivo">
                            (<bean:write name="comentario" property="motivo" />)
                        </logic:notEmpty>
                        <bean:write name="comentario" property="titulo" />                         
                    </a>
                </div>
                <div class="textgros5">&nbsp;
                    <bean:write name="comentario" property="autor" />
                </div>
            </div>
            <div class="botoneraconsulta1">
                <html:form action="/contenido/comentario/borrar" >
                    <html:hidden property="idComentario" value="<%=comentario.getId().toString()%>"/>
                    <html:hidden property="idRel" value="<%=idRel.toString()%>"/>
                    <html:hidden property="tipo" value="<%=tipo%>"/>
                    <html:submit><bean:message key="boton.eliminar" /></html:submit>
                </html:form>
            </div>
        </div>
        </logic:iterate>
    </logic:notEmpty>
</div><br />

