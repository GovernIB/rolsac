<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>

<tiles:useAttribute name="padreActual" />
<tiles:useAttribute name="nivel" classname="java.lang.Integer" />
<tiles:useAttribute name="inicial" classname="java.lang.Long" />
<bean:define id="tieneHijos" name="tieneHijos" type="java.util.List" />
<bean:define id="tieneAcceso" name="tieneAcceso" type="java.util.List" />
<%
    String carpeta = "true";
    String access = "false";
%>
<logic:iterate id="hijo" name="<%=padreActual.toString()%>" indexId="idx" >
    <bean:define id="idActual" name="hijo" property="id" type="java.lang.Long"/>
    <bean:define id="actual" name="hijo" property="id" type="java.lang.Long" />
    <%if (!idActual.equals(inicial)){
        access = "false";

        if (tieneAcceso.contains(actual)){
            access = "true";
        }
    %>
        <logic:present name="<%=actual.toString()%>">
            ex.push(new init_ex(<%=nivel%>,true,"<bean:write name='hijo' property='traduccion.nombre' />",true,"<%=actual%>",<%=access%>));
        </logic:present>
        <logic:notPresent name="<%=actual.toString()%>">
            <%
                carpeta = "false";

                if (tieneHijos.contains(actual)) {
                    carpeta = "true";
                }
            %>
            ex.push(new init_ex(<%=nivel%>,false,"<bean:write name='hijo' property='traduccion.nombre' />",<%=carpeta%>,"<%=actual%>",<%=access%>));
        </logic:notPresent>
        <logic:notEmpty name="<%=actual.toString()%>">
            <tiles:insert page="/sistema/seccion/hijos.jsp" flush="false" >
                <tiles:put name="padreActual" value="<%=actual.toString()%>" />
                <tiles:put name="inicial" value="<%=inicial%>" />
                <tiles:put name="nivel" value="<%=new Integer(nivel.intValue() + 1)%>" />
            </tiles:insert>
        </logic:notEmpty>
    <%
    }
    %>
</logic:iterate>
