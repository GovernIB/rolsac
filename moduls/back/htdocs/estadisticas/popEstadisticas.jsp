<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<html:html locale="true" xhtml="true">
<head>
   <title>
        <bean:message key="estadisticas.poptitulo" />
        <logic:present name="idProcedimiento">
            <bean:message key="estadisticas.popprocedimiento" />
        </logic:present>
        <logic:present name="idNormativa">
            <bean:message key="estadisticas.popnormativa" />
        </logic:present>
        <logic:present name="idFicha">
            <bean:message key="estadisticas.popficha" />
        </logic:present>
        <logic:present name="idMateria">
            <bean:message key="estadisticas.popmateria" />
        </logic:present>
        
   </title>
   <meta content="text/html; charset='ISO-8859-15'" http-equiv="Content-type" />
   <link rel="stylesheet" href='<html:rewrite page="/css/styleB.css"/>' type="text/css" />
</head>

<body>
<div id="organigrama">
    <div id="capsalera">
        <h1>
            <logic:present name="idProcedimiento">
                <bean:message key="estadisticas.popprocedimiento" />:
            </logic:present>
            <logic:present name="idNormativa">
                <bean:message key="estadisticas.popnormativa" />:
            </logic:present>
            <logic:present name="idFicha">
                <bean:message key="estadisticas.popficha" />:
            </logic:present>
	        <logic:present name="idMateria">
    	        <bean:message key="estadisticas.popmateria" />
        	</logic:present>
            <logic:present name="titulo">
                <bean:write name="titulo" filter="no"/>
            </logic:present>
        </h1>
    </div>
    <br />
    <div id="llistat">
        <center>
            <logic:present name="idProcedimiento">
                <html:img page="/estadisticas/graficopro.do"
                          paramId="id"
                          paramName="idProcedimiento" />
            </logic:present>

            <logic:present name="idNormativa">
                <html:img page="/estadisticas/graficonorm.do"
                          paramId="id"
                          paramName="idNormativa" />
            </logic:present>

            <logic:present name="idFicha">
				
                <logic:present name="idFicMat">
	                <%  
					java.util.HashMap params = new java.util.HashMap();
					params.put("id", request.getAttribute("idFicha"));
					params.put("idFicMat", request.getAttribute("idFicMat"));
					request.setAttribute("parametros", params);
					%>
                <html:img page="/estadisticas/graficofi.do" name="parametros"/>

                </logic:present>          

                <logic:present name="idFicUA">
	                <%  
					java.util.HashMap params = new java.util.HashMap();
					params.put("id", request.getAttribute("idFicha"));
					params.put("idFicUA", request.getAttribute("idFicUA"));
					request.setAttribute("parametros", params);
					%>
                <html:img page="/estadisticas/graficofi.do" name="parametros"/>

                </logic:present>          

				<logic:notPresent name="idFicMat">
					<logic:notPresent name="idFicUA">
						<html:img page="/estadisticas/graficofi.do"
                          paramId="id" paramName="idFicha" />
					</logic:notPresent>
				</logic:notPresent>
                          
            </logic:present>

            <logic:present name="idMateria">
                <html:img page="/estadisticas/graficomat.do"
                          paramId="id"
                          paramName="idMateria" />
            </logic:present>
            
        </center>
    </div>
    <div class="botonera"><center>
        <input type="button" value="<bean:message key="boton.cerrar" />" onclick="window.close()" />
    </center></div>
</div>
</body>
</html:html>