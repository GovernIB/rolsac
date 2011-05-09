<%@ page language="java" contentType="text/html; charset=ISO-8859-15" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:xhtml/>
<script src="<html:rewrite page='/moduls/staticJs.jsp'/>" type="text/javascript"></script>
<script src="<html:rewrite page='/moduls/funcions.js'/>" type="text/javascript"></script>    
<link rel="stylesheet" href='<html:rewrite page="/css/styleA.css"/>' type="text/css" />

	<div class="bloc">
		<h1>Llistat de normatives trobades</h1>

<logic:equal name="APPTRS_listanormativa" value="0">
      No s'ha trobat cap article amb aquests criteris
</logic:equal>

<logic:present name="APPTRS_listanormativa">
      <table cellpadding="0" cellspacing="0" class="listadoItems">
      		<colgroup>
            <col class="centrado"></col>
            <col class="centrado"></col>
            <col></col>
            <col></col>
          </colgroup>
            <tr>
              <th>N. BOIB </th>
              <th>N. Registre </th>
              <th>Títol </th>
              <th class="peque">&nbsp;</th>
            </tr>
            <logic:iterate id="i" name="APPTRS_listanormativa" indexId="indice">
            <tr class="<%=((indice.intValue()%2==0) ? "par" : "impar")%>">
              <td><bean:write name="i" property="boib"/></td>
              <td><bean:write name="i" property="registro"/></td>
              <td><bean:write name="i" property="titulo"/></td>
              <td><button title="Seleccionar" onclick="document.location.href='searchnormativa.do?trsid=<bean:write name="i" property="trcodificacion"/>'" />
              <bean:message key="boton.seleccionar"/>
              </button>
              
              </td>
            </tr>
            </logic:iterate>      
      </table>
</logic:present>      

</div>
