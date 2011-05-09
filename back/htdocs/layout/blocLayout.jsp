<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%--
    Layout que muestra una lista de definiciones.
        list - Lista de definiciones.
--%>
<html:xhtml/>
<tiles:importAttribute name="list" scope="page" />
<logic:iterate id="definicio" name="list">
    <tiles:insert beanName="definicio" flush="false" />
</logic:iterate>
