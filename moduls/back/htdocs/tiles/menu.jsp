<%@ page language="java" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<html:xhtml/>
<tiles:useAttribute id="root" name="root" scope="page" classname="java.lang.String"/>
<tiles:importAttribute scope="page" ignore="true" />
<% String microsites = System.getProperty("es.caib.rolsac.microsites"); 
   if (microsites == null) microsites = "N";
%>

<% String traspasboib = System.getProperty("es.caib.rolsac.traspasboib");
   if (traspasboib == null) traspasboib = "N";
%>

<logic:notEmpty name="<%=root%>">
    <%=root.equals("opcions")?"<ol>":"<ul>"%>
    <logic:iterate id="opcio" name="<%=root%>">
        <bean:define id="menuname" name="opcio" property="value" type="java.lang.String"/>
        <bean:define id="roles" name="opcio" property="icon" type="java.lang.String"/>
        <bean:define id="action" name="opcio" property="link" type="java.lang.String"/>

        <% if(!menuname.equals("microsites") && !menuname.equals("contenidos.normativas.traspasa")){  %>
            <logic:present role="<%=roles%>">
            <li>
                <logic:notEmpty name="action"><html:link action="<%=action%>"><bean:message name="opcio" property="tooltip" bundle="menu" /></html:link></logic:notEmpty>
                <logic:empty name="action"><bean:message name="opcio" property="tooltip" bundle="menu" /></logic:empty>
                <tiles:insert definition=".menu" flush="false"><tiles:put name="root" beanName="opcio" beanProperty="value"/></tiles:insert>
            </li>
            </logic:present>
         <% } else{
                if (menuname.equals("microsites") && !microsites.equals("N")) {%>
        <logic:present role="<%=roles%>">
        <li>
            <logic:notEmpty name="action"><html:link action="<%=action%>"><bean:message name="opcio" property="tooltip" bundle="menu" /></html:link></logic:notEmpty>
            <logic:empty name="action"><bean:message name="opcio" property="tooltip" bundle="menu" /></logic:empty>
            <tiles:insert definition=".menu" flush="false"><tiles:put name="root" beanName="opcio" beanProperty="value"/></tiles:insert>
        </li>
        </logic:present>
        <% } %>
               <% if (menuname.equals("contenidos.normativas.traspasa") && !traspasboib.equals("N")) {%>
                    <logic:present role="<%=roles%>">
                    <li>
                        <logic:notEmpty name="action"><html:link action="<%=action%>"><bean:message name="opcio" property="tooltip" bundle="menu" /></html:link></logic:notEmpty>
                        <logic:empty name="action"><bean:message name="opcio" property="tooltip" bundle="menu" /></logic:empty>
                        <tiles:insert definition=".menu" flush="false"><tiles:put name="root" beanName="opcio" beanProperty="value"/></tiles:insert>
                    </li>
                    </logic:present>
                <% } %>
         <% } %>
    </logic:iterate>
    <%=root.equals("opcions")?"</ol>":"</ul>"%>
</logic:notEmpty>
<% if (root.equals("opcions")) { %>
   <div class="salir"><html:link action="/logout" target="_parent"><bean:message key="session.close" /></html:link></div>
<% } %>