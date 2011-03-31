<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<bean:define id="inicial" name="idUA" type="java.lang.Long" />
<html:html locale="true" xhtml="true">
<head>
   <title>SAC Arbol - Seccion</title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
   <script type="text/javascript">
   <!--
        var ex=new Array;
        var max=1000
        var nivell=0

        function carregar(id, nombre){
            opener.actualizaUA(id, nombre);
            window.close();
        }

        function init_ex(level,opened,descripcion,carpeta,codi){
            this.level=level;
            this.opened=opened;
            this.descripcion=descripcion;
            this.carpeta=carpeta;
            this.codi=codi;
        }

        /*El segon argument nomes importa per a les carpetes, si es true es mostra el contingut, si es false no */
        <logic:notEmpty name="raizOptions">
            <bean:define id="tieneHijos" name="tieneHijos" type="java.util.List" />
            <logic:iterate id="raiz" name="raizOptions" indexId="idx" >
                <bean:define id="idActual" name="raiz" property="id" type="java.lang.Long" />
                <bean:define id="actual" name="raiz" property="id" type="java.lang.Long" />
                <%if (!idActual.equals(inicial)){%>
                    <logic:present name="<%=actual.toString()%>">
                        ex.push(new init_ex(1,true,"<bean:write name='raiz' property='traduccion.nombre' />",true,"<bean:write name='raiz' property='id' />"));
                    </logic:present>
                    <logic:notPresent name="<%=actual.toString()%>">
                        <% if (tieneHijos.contains(actual)) { %>
                            ex.push(new init_ex(1,false,"<bean:write name='raiz' property='traduccion.nombre' />",true,"<bean:write name='raiz' property='id' />"));
                        <% } else {%>
                            ex.push(new init_ex(1,false,"<bean:write name='raiz' property='traduccion.nombre' />",false,"<bean:write name='raiz' property='id' />"));
                        <% }%>
                    </logic:notPresent>
                    <logic:notEmpty name="<%=actual.toString()%>">
                        <tiles:insert page="/organigrama/unidad/pophijos.jsp" flush="false" >
                            <tiles:put name="padreActual" value="<%=actual.toString()%>" />
                            <tiles:put name="nivel" value="<%=new Integer(2)%>" />
                            <tiles:put name="inicial" value="<%=inicial%>" />
                        </tiles:insert>
                    </logic:notEmpty>
                <% } %>
            </logic:iterate>
        	ex.push(new init_ex(-1,"","","",""));
        </logic:notEmpty>

        function print_explorador(){
            var i=0
            document.write("<ul>")
            while (ex[i].level!=-1){
                if (ex[i].level<=max){
                    if (ex[i].carpeta==true){
                            excarpeta(i)
                    } else {
                            exarchivo(i)
                    }
                }
                i++
            }
            document.write("</ul>")
        }

        function excarpeta(i){
            switch (nivell-ex[i].level){
                case 1:
                    document.write("</ul></li>");
                    break;
                case 2:
                    document.write("</ul></li></ul></li>");
                    break;
                case 3:
                    document.write("</ul></li></ul></li></ul></li>");
                    break;
            }

            if (ex[i].opened==false){
                document.write("<li><a href='javascript:open(" + i + ")'><img src='<html:rewrite page="/img/trasparent.gif" />' width=15 height=9 border=0></a><a href='javascript:carregar(\"" + ex[i].codi + "\",\"" + ex[i].descripcion + "\")'>" + ex[i].descripcion + "</a></li>")
                max=ex[i].level
            } else {
                document.write("<li style='background-image: url(<html:rewrite page="/img/obert.gif" />)'><a href='javascript:unopen(" + i + ")'><img src='<html:rewrite page="/img/trasparent.gif" />' width=15 height=9 border=0></a><a href='javascript:carregar(\"" + ex[i].codi + "\",\"" + ex[i].descripcion + "\")'>" + ex[i].descripcion + "</a>")
                max=1000
                if (ex[i].level<ex[i+1].level){
                    document.write("<ul>")
                    } else {
                    document.write("</li>")
                }
            }
            nivell=ex[i].level
        }

        function exarchivo(i){
            switch (nivell-ex[i].level){
                case 1:
                    document.write("</ul></li>");
                    break;
                case 2:
                    document.write("</ul></li></ul></li>");
                    break;
                case 3:
                    document.write("</ul></li></ul></li></ul></li>");
                    break;
            }
            document.write("<li style='background-image: url(<html:rewrite page="/img/punt.gif" />)'><img src='<html:rewrite page="/img/trasparent.gif" />' width=15 height=9><a href='javascript:carregar(\"" + ex[i].codi +"\",\"" + ex[i].descripcion + "\")'>" + ex[i].descripcion +"</a></li>")
            nivell=ex[i].level
        }

        function unopen(nr){
            document.forms[0].idSelect.value = ex[nr].codi;
            document.forms[0].action.value = '<bean:message key="boton.contraer" />';
            document.forms[0].submit()
        }

        function open(nr){
            document.forms[0].idSelect.value = ex[nr].codi;
            document.forms[0].action.value = '<bean:message key="boton.expandir" />';
            document.forms[0].submit()
        }


   -->
   </script>
</head>

<body>
<div id="organigrama">
    <div id="capsalera">
        <h1><bean:message key="ua.lista" /></h1>
    </div>
    <div id="llistat">
    <logic:notEmpty name="raizOptions">
        <script language="JavaScript1.2" type="text/javascript">
        <!--
            print_explorador()
        //-->
        </script>
        <html:form action="/organigrama/unidad/poparbol" styleId="padreForm">
            <input type="hidden" name="action" value='<bean:message key="boton.expandir" />' />
            <input type="hidden" name="idUA" value="<%=inicial%>" />
            <input type="hidden" name="idSelect" value="" />
            <logic:present parameter="padres">
            	<input type="hidden" name="padres" value=""/>
            </logic:present>
        </html:form>
    </logic:notEmpty>
    <logic:empty name="raizOptions">
        <center>
        <bean:message key="ua.vacio" />
        </center>
    </logic:empty>
    </div>
    <div class="botonera"><center>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </center></div>
</div>
</body>
</html:html>