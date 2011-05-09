<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<bean:define id="inicial" name="idSeccion" type="java.lang.Long" />
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

        <logic:present name="ficha">
        function carregar(id, nombre, acceso){
            if (!acceso){
                alert("<bean:message key='seccion.error.acceso' />");
            } else {
                opener.actualizaSeccion(id, nombre);
                window.close();
            }
        }
        </logic:present>

        <logic:notPresent name="ficha">
        function carregar(id, nombre, acceso){
            idPadre = id;
            nombrePadre = nombre;
            opener.updatePadre(idPadre, nombrePadre);
            window.close();
        }
        </logic:notPresent>

        function init_ex(level,opened,descripcion,carpeta,codi,acceso){
            this.level=level;
            this.opened=opened;
            this.descripcion=descripcion;
            this.carpeta=carpeta;
            this.codi=codi;
            this.acceso=acceso;
        }

        /*El segon argument nomes importa per a les carpetes, si es true es mostra el contingut, si es false no */
        <logic:notEmpty name="raizOptions">
            <bean:define id="tieneHijos" name="tieneHijos" type="java.util.List" />
            <bean:define id="tieneAcceso" name="tieneAcceso" type="java.util.List" />

            <logic:notPresent name="ficha">
                ex.push(new init_ex(1,"","- <bean:message key='seccion.raiz' /> -",false,"0"),true);
            </logic:notPresent>
            <%
                String carpeta = "true";
                String access = "false";
            %>
            <logic:iterate id="raiz" name="raizOptions" indexId="idx" >
                <bean:define id="idActual" name="raiz" property="id" type="java.lang.Long" />
                <bean:define id="actual" name="raiz" property="id" type="java.lang.Long" />
                <%if (!idActual.equals(inicial)){
                    access = "false";

                    if (tieneAcceso.contains(actual)){
                        access="true";
                    }
                %>
                    <logic:present name="<%=actual.toString()%>">
                            ex.push(new init_ex(1,true,"<bean:write name='raiz' property='traduccion.nombre' />",true,"<%=actual%>",<%=access%>));
                    </logic:present>
                    <logic:notPresent name="<%=actual.toString()%>">
                        <%
                            carpeta = "false";

                            if (tieneHijos.contains(actual)) {
                                carpeta = "true";
                            }
                        %>
                        ex.push(new init_ex(1,false,"<bean:write name='raiz' property='traduccion.nombre' />",<%=carpeta%>,"<%=actual%>",<%=access%>));
                    </logic:notPresent>
                    <logic:notEmpty name="<%=actual.toString()%>">
                        <tiles:insert page="/sistema/seccion/hijos.jsp" flush="false" >
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
                document.write("<li><a href='javascript:unopen(" + i + ")'><img src='<html:rewrite page="/img/trasparent.gif" />' width=15 height=9 border=0></a><a href='javascript:carregar(\"" + ex[i].codi + "\",\"" + ex[i].descripcion + "\"," + ex[i].acceso + ")'>" + ex[i].descripcion + "</a></li>")
                max=ex[i].level
            } else {
                document.write("<li style='background-image: url(<html:rewrite page="/img/obert.gif" />)'><a href='javascript:unopen(" + i + ")'><img src='<html:rewrite page="/img/trasparent.gif" />' width=15 height=9 border=0></a><a href='javascript:carregar(\"" + ex[i].codi + "\",\"" + ex[i].descripcion + "\"," + ex[i].acceso + ")'>" + ex[i].descripcion + "</a>")
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
            document.write("<li style='background-image: url(<html:rewrite page="/img/punt.gif" />)'><img src='<html:rewrite page="/img/trasparent.gif" />' width=15 height=9><a href='javascript:carregar(\"" + ex[i].codi +"\",\"" + ex[i].descripcion + "\"," + ex[i].acceso + ")'>" + ex[i].descripcion +"</a></li>")
            nivell=ex[i].level
        }

        function unopen(nr){
            //ex[nr].opened=!ex[nr].opened
            //window.location.href="xhtml-pop-v06.htm"
            document.forms.padreForm.idSelect.value = ex[nr].codi;
            document.forms.padreForm.submit()
        }
   -->
   </script>
</head>

<body>
	<div id="organigrama">
		<div id="capsalera">
            <logic:present name="ficha">
                <h1><bean:message key="seccion.lista" /></h1>
            </logic:present>
            <logic:notPresent name="ficha">
                <h1><bean:message key="seccion.padre.titulo" /></h1>
            </logic:notPresent>
        </div>
        <div id="llistat">
        <logic:notEmpty name="raizOptions">
            <script language="JavaScript1.2" type="text/javascript">
            <!--
                print_explorador()
            //-->
            </script>
            <html:form action="/sistema/seccion/arbol" styleId="padreForm">
                <input type="hidden" name="action" value='<bean:message key="boton.expandir" />' />
                <input type="hidden" name="idSeccion" value="<%=inicial%>" />
                <logic:present name="ficha">
                    <input type="hidden" name="ficha" value="true" />
                </logic:present>
                <input type="hidden" name="idSelect" value="" />
            </html:form>
        </logic:notEmpty>
        <logic:empty name="raizOptions">
            <center>
            <bean:message key="seccion.vacio" />
            </center>
        </logic:empty>
        </div>
        <div class="botonera"><center>
            <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
        </center></div>
	</div>
</body>
</html:html>