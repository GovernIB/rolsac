<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p id="index"><spring:message code='index.reinicar_index'/></p>
<div id="escriptori_index">
    <!-- inici dades -->
    <div style="display: block;" id="dadesIndex">
        <!-- index -->
        <div id="index" class="modul m100">
            <div id="indexContainer">
                <div class="indexContainer actiu" id="divUnitatOrganica">
                    <span class="missatge"><spring:message code='index.missatge.unitat_organica'/></span>
                    <br />
                    <span class="btnGenerico">
                        <a href="javascript:;" class="btn unitatOrganica"><span><span><spring:message code='boto.continuar'/></span></span></a>
                    </span>
                </div> 
                
                <div class="indexContainer" id="divFitxa">
                    <span class="missatge"><spring:message code='index.missatge.fitxa_informativa'/></span>
                    <br />
                    <span class="btnGenerico">
                        <a href="javascript:;" class="btn fitxa"><span><span><spring:message code='boto.continuar'/></span></span></a>
                    </span>
                </div>
                
                <div class="indexContainer" id="divNormativa">
                    <span class="missatge"><spring:message code='index.missatge.normativa'/></span>
                    <br />
                    <span class="btnGenerico">
                        <a href="javascript:;" class="btn normativa"><span><span><spring:message code='boto.continuar'/></span></span></a>
                    </span>
                </div>
                
                <div class="indexContainer" id="divProcediment">
                    <span class="missatge"><spring:message code='index.missatge.procediment'/></span>
                    <br />
                    <span class="btnGenerico">
                        <a href="javascript:;" class="btn procediment"><span><span><spring:message code='boto.continuar'/></span></span></a>
                    </span>
                </div>
            </div>
        </div>
        <!-- /index -->
    </div> 
</div>