<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code='arbreS.arbre'/></title>
    <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
    <jsp:include page="layout/favicon.jsp" flush="true"/>
    <link href="<c:url value='/css/comuns.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/arbreS.css'/>" rel="stylesheet" type="text/css" media="screen" />
    
    <script type="text/javascript" src="<c:url value='/js/jquery-1.6.4.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/seekAttention.min.jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/error.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/comuns.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/listado_base.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/detall_base.js'/>"></script>    

    <script type="text/javascript">
    <!--
        var ex=new Array;
        var max=1000
        var nivell=0

        function carregar(id, nombre){

            <c:choose>
                <c:when test="${not empty id_input}">
                    $("#" + "<c:out value='${id_hidden}'/>", window.top.document).val(id);
                    $("#" + "<c:out value='${id_input}'/>",window.top.document).val(nombre);
                    
                    // Al disparar eventos hay que hacerlo directamente desde la ventana a la que corresponden.
                    window.top.jQuery("#" + "<c:out value='${id_hidden}'/>").change();
                    window.top.jQuery("#" + "<c:out value='${id_input}'/>").change();                    
                    
                </c:when>
                <c:otherwise>
                    var etItem = new Object();
                    etItem['id'] = id;
                    etItem['nombre'] = nombre;                    
                    window.top.ModulSeccions.agregaItem(etItem);
                    window.top.ModulSeccions.inicializarSecciones();
                </c:otherwise>
            </c:choose>
            
            window.top.Detall.modificado();

            borrarPopUp('popS');
        }

        function init_ex(level, opened, descripcion, carpeta, codi) {
            this.level = level;
            this.opened = opened;
            this.descripcion = descripcion;
            this.carpeta = carpeta;
            this.codi = codi;
        }

        <c:if test='${not empty raizOptions}'>
	        ex.push(new init_ex(1, "", "<spring:message code='seccio.arrel' />", false, ""), true);
            <c:forEach items="${raizOptions}" var="raiz">
                <c:set var="idActual" value="${raiz.id}"/>
                <c:set var="actual" value="${raiz.id}"/>
        		
                <c:set var="id_coll" value="id_${actual}" scope="page" />
                <c:set var="coll" value="${requestScope[id_coll]}" />
                
                <%--value='<c:out value="${raiz.id}" />'/--%>
                <c:if test="${idSec != idActual}">
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
                        <c:import url = "/pantalles/popFillsS.jsp">
                            <c:param name = "padreActual" value = "${actual}" />
                            <c:param name = "nivel" value = "2" />
                            <c:param name = "inicial" value = "${idSec}" />
                        </c:import>
                    </c:if>
                </c:if>         
            </c:forEach>
            ex.push(new init_ex(-1,"","","",""));
        </c:if>
     
        
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
            open(nr);
            //window.location = "<c:url value='popArbreSContreure.do' />?idSec=<c:out value='${idSec}' />&idSelect=" + ex[nr].codi + "&idHidden=<c:out value='${id_hidden}' />&idInput=<c:out value='${id_input}' />";
        }
        
        function open(nr){
            window.location = "<c:url value='popArbreSExpandir.do' />?idSec=<c:out value='${idSec}' />&idSelect=" + ex[nr].codi + "&idHidden=<c:out value='${id_hidden}' />&idInput=<c:out value='${id_input}' />";
        }
     -->   
    </script>
</head>

<body>
    <div id="escript">
        <!-- escriptori_detall -->
        <div id="escriptori_detall" class="escriptori_detall" >
        <!-- <div id="modulPrincipal" class="modulPrincipal"> -->
            <!-- modul -->
            <div class="modul" >
                <fieldset>
                    <legend><spring:message code='arbreS.llistat'/></legend>
                    <div class="modul_continguts mostrat">
                        <c:if test='${not empty raizOptions}'>
                            <script language="JavaScript1.2" type="text/javascript">
                            <!--
                                    print_explorador();
                            -->     
                            </script>
                            <form action='<c:url value="/pantalles/popArbreS.do"/>' id="padreForm" method="post" >
                                <input type="hidden" name="action" value='Expandir' />
                                <input type="hidden" name="idSec" value='<c:out value="${idSec}" />' />
                                <input type="hidden" name="idSelect" value="" />
                                <c:if test="${not empty padres}">                        
                                    <input type="hidden" name="padres" value=""/>
                                </c:if>
                            </form>
                        </c:if>
                        <c:if test='${empty raizOptions}'>
                            <center>
                                <spring:message code='arbreS.buit'/>
                            </center>
                        </c:if>
                        <div class="botonera">
                            <div class="btnGenerico">
                               <a href="javascript:borrarPopUp('popS')" class="btn tancar"><span><span><spring:message code='boto.tancar'/></span></span></a>
                           </div>
                        </div>                              
                    </div>                              
                </fieldset>                         
            </div>
            <!-- /modul -->
       </div>
    </div>  
</body>
</html>