<%@ page language="java" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<html:xhtml/>


 <script  type="text/javascript">
if (window.innerHeight){
   //navegadores basados en mozilla
   espacio_iframe = window.innerHeight - 110
}else{
   if (document.body.clientHeight){
       //Navegadores basados en IExplorer, es que no tengo innerheight
       espacio_iframe = document.body.clientHeight - 110
   }else{
       //otros navegadores
       espacio_iframe = 478
   }
}
document.write ('<iframe frameborder="0" src="/traspasboib/traspasa.do?contextfrom=<%=request.getContextPath()%>" width="770" height="' + espacio_iframe + '">')
document.write ('</iframe>')
</script>
<noscript>
<iframe frameborder="0" src="/traspasboib/traspasa.do?contextfrom=<%=request.getContextPath()%>" width="770" height=478>
</iframe>
</noscript>

