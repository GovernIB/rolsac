<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<html:xhtml/>
<tiles:importAttribute scope="page" ignore="true" />
<bean:message key="identificacion.usuario" />: <bean:write name="username" ignore="true" /><br />
<bean:message key="identificacion.rol" />: <bean:write name="rolenames" ignore="true" /><br />