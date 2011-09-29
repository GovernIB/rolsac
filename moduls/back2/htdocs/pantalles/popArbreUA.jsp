<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--bean:define id="inicial" name="idUA" type="java.lang.Long" /--%>
<html>
<head>
   <title>SAC Arbol - Seccion</title>
	<meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
	<link href="<c:url value='/img/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" />
	<link href="<c:url value='/css/comuns.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/comuns_amplaria1.css'/>" rel="stylesheet" type="text/css" media="screen" />  
    <link href="<c:url value='/css/titol.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/menu.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/submenu.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/unitat.css'/>" rel="stylesheet" type="text/css" media="screen" />
   <script type="text/javascript">
   <!--
        var ex=new Array;
        var max=1000
        var nivell=0

        function carregar(id, nombre){
        	window.opener.$("#id_item_pare").val(id);
        	window.opener.$("#item_pare").val(nombre);
            window.close();
        }

        function init_ex(level,opened,descripcion,carpeta,codi){
            this.level=level;
            this.opened=opened;
            this.descripcion=descripcion;
            this.carpeta=carpeta;
            this.codi=codi;
        }
 		
        <c:if test='${not empty raizOptions}'>
			<c:forEach items="${raizOptions}" var="raiz">
				<c:set var="idActual" value="${raiz.id}"/>
				<c:set var="actual" value="${raiz.id}"/>
		
				<c:set var="id_coll" value="id_${actual}" scope="page" />
				<c:set var="coll" value="${requestScope[id_coll]}" />
							
				<%--value='<c:out value="${raiz.id}" />'/--%>
				<c:if test="${idUA != idActual}">
					<c:choose>
				    	<c:when test="${coll != null}">
				    		ex.push(new init_ex(1,true,"<c:out value='${raiz.traduccion.nombre}' />",true,"<c:out value='${raiz.id}' />"));
				    	</c:when>
					    <c:otherwise>
							<c:set var="auxId" value="" />
							<c:set var="auxNombre" value="" />
							<c:set var="hayHijos" value="no" />
				    		<c:forEach items="${tieneHijos}" var="hijo">
				    			<c:if test="${hijo == actual}" >
					    			<c:set var="auxId" value="${raiz.id}"  />
									<c:set var="auxNombre" value="${raiz.traduccion.nombre}" />		
									<c:set var="hayHijos" value="si" />		
				    			</c:if>
				    		</c:forEach>
				    		<c:choose>
		    					<c:when test="${hayHijos == 'si'}">
		    						ex.push(new init_ex(1,false,"<c:out value='${auxNombre}' />",true,"<c:out value='${auxId}' />"));
							    </c:when>
							    <c:otherwise>
							    	ex.push(new init_ex(1,false,"<c:out value='${raiz.traduccion.nombre}' />",false,"<c:out value='${raiz.id}' />"));
							    </c:otherwise>
							</c:choose>
					    </c:otherwise>
					</c:choose>
					<c:if test="${not empty coll}" >
						<c:import url = "/pantalles/popFillsUA.jsp">
							<c:param name = "padreActual" value = "${actual}" />
							<c:param name = "nivel" value = "2" />
							<c:param name = "inicial" value = "${idUA}" />
						</c:import>
					</c:if>
				</c:if>			
			</c:forEach>
			ex.push(new init_ex(-1,"","","",""));
        </c:if>
     
        
        /*El segon argument nomes importa per a les carpetes, si es true es mostra el contingut, si es false no */
        <%--
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
 		--%>

 		
        function print_explorador(){
            var i=0
            document.write("<ul style='list-style: none outside none;'>")
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
                document.write("<li style='font-size: 13px; padding: 5px 12px 0;'><a href='javascript:open(" + i + ")'><img src='../img/botons/mostra.gif' width=12 height=12 border=0></a><a style='color: #0099CC; text-decoration: none;' href='javascript:carregar(\"" + ex[i].codi + "\",\"" + ex[i].descripcion + "\")'>" + ex[i].descripcion + "</a></li>")
                max=ex[i].level
            } else {
                document.write("<li style='font-size: 13px; padding: 5px 12px 0;'><a href='javascript:unopen(" + i + ")'><img src='../img/botons/amaga.gif' width=12 height=12 border=0></a><a style='color: #0099CC; text-decoration: none;' href='javascript:carregar(\"" + ex[i].codi + "\",\"" + ex[i].descripcion + "\")'>" + ex[i].descripcion + "</a>");
                max=1000
                if (ex[i].level<ex[i+1].level){
                    document.write("<ul style='list-style: none outside none;'>")
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
            document.write("<li style='font-size: 13px; padding: 5px 12px 0;'><img src='../img/cercador/mesDades_off.gif' width=12 height=12><a style='color: #0099CC; text-decoration: none;' href='javascript:carregar(\"" + ex[i].codi +"\",\"" + ex[i].descripcion + "\")'>" + ex[i].descripcion +"</a></li>")
            nivell=ex[i].level
        }

        function unopen(nr){
        	window.location = "<c:url value='popArbreUAContreure.htm' />?idUA=<c:out value='${idUA}' />&idSelect=" + ex[nr].codi;
            //document.forms[0].idSelect.value = ex[nr].codi;
            //document.forms[0].action.value = '<c:url value="/pantalles/popArbreUAContreure.htm" />';
            //document.forms[0].submit()
        }

        

        function open(nr){
            window.location = "<c:url value='popArbreUAExpandir.htm' />?idUA=<c:out value='${idUA}' />&idSelect=" + ex[nr].codi;
            //document.forms[0].idSelect.value = ex[nr].codi;
            //document.forms[0].action.value = '<c:url value="/pantalles/popArbreUAExpandir.htm" />';
            //document.forms[0].submit()
        }
     -->   
	</script>
</head>

<body>
	<div id="escriptori">
	<!-- escriptori_detall -->
	<div id="escriptori_detall" style="display: block;">
		<div id="modulPrincipal" class="grupoModulosFormulario">					
			<!-- modul -->
			<div class="modul" style="-moz-border-radius: 1em 1em 0pt 0pt; margin-left: 10px; width: 472px; margin-top: 10px; height: 380px; overflow: auto;" >		
				<fieldset>
					<legend>Llistat de Tipus d'Unitat Administrativa</legend>
					<div class="modul_continguts mostrat">		
						<c:if test='${not empty raizOptions}'>
					        <script language="JavaScript1.2" type="text/javascript">
						    <!--
							        print_explorador();
							-->     
					        </script>
					        <form action='<c:url value="/pantalles/popArbreUA.htm"/>' id="padreForm" method="post" >
					            <input type="hidden" name="action" value='Expandir' />
					            <input type="hidden" name="idUA" value='<c:out value="${idUA}" />' />
					            <input type="hidden" name="idSelect" value="" />
					            <c:if test="${not empty padres}">                        
					            	<input type="hidden" name="padres" value=""/>
					            </c:if>
					        </form>
					    </c:if>
					    <c:if test='${empty raizOptions}'>
					        <center>
					        Arbre buït
					        </center>
					    </c:if>
						<div class="botonera">
							<div class="btnGenerico">
							   <a href="javascript:window.close();" class="btn tancar"><span><span>Tancar</span></span></a>
						   </div>
						</div>								
					</div>								
				</fieldset>							
			</div>
			<!-- /modul -->
	</div>
	</div>	
<%--
<div id="organigrama">
    <div id="capsalera">
        <h1><bean:message key="ua.lista" /></h1>
    </div>
    <div id="llistat">
    <logic:notEmpty name="raizOptions">
        <script language="JavaScript1.2" type="text/javascript">     
        </script>
        <html:form action="/organigrama/unidad/poparbol" styleId="padreForm">
            <input type="hidden" name="action" value='<bean:message key="boton.expandir" />' />
            <input type="hidden" name="idUA" value='<c:out value="${idUA}" />' />
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
--%>
</body>
</html>