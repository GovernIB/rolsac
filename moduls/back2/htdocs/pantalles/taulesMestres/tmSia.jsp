<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link href='<c:url value="/css/tm_index.css"/>' rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value='/js/tm_sia.js'/>"></script>
<script type="text/javascript">
    var pagLlistat = '<c:url value="/index/llistatSIA.do" />';
    var pagLlistatJob = '<c:url value="/index/llistatSIAJob.do" />';
    var pagEnviarTodo = '<c:url value="/index/enviarTodo.do" />';
    var pagEnviarPendientes = '<c:url value="/index/enviarPendientes.do" />';
	var pagCerrarJobs =  '<c:url value="/index/cerrarJobsSIA.do" />';
	var pagSiaTiempo =  '<c:url value="/index/procTiempoSIA.do" />';
    
    //texts
    var txt_per = "<spring:message code='txt.per'/>";
    var txtEsborrarCorrecte = "<spring:message code='txt.usuari_esborrat_correcte'/>";
    var txtEspere = "<spring:message code='txt.esperi'/>";
    var txtCarregant = "<spring:message code='txt.carregant'/>";
    var txtSi = "<spring:message code='txt.si'/>";
    var txtNo = "<spring:message code='txt.no'/>";
    var txtDela = "<spring:message code='txt.de_la'/>";
    var txtAla = "<spring:message code='txt.a_la'/>";
    var txtTrobats = "<spring:message code='txt.trobats'/>";
    var txtTrobades = "<spring:message code='txt.trobades'/>";
    var txtLlistaItem = "<spring:message code='txt.sia.envio'/>";
    var txtLlistaItems = "<spring:message code='txt.sia.envios'/>";
    var txtMostrem = "<spring:message code='txt.mostrem'/>";
    var txtMostremAl = " <spring:message code='txt.a_la'/> ";
    var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
    var txtNoHiHaItems = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtCarregantItems = txtCarregant + " " + txtLlistaItems.toLowerCase() + ". " + txtEspere;
    var txtOrdenats = "<spring:message code='txt.ordenats'/>";
    var txtOrdenades = "<spring:message code='txt.ordenades'/>";
    var txtAscendentment = "<spring:message code='txt.ascendentment'/>";
    var txtDescendentment = "<spring:message code='txt.descendentment'/>";
    var txtPer = "<spring:message code='txt.per'/>";
    var txtNoHiHaLlistat = txtNoHiHa + " " + txtLlistaItems.toLowerCase();
    var txtId = "<spring:message code='txt.indexaciones.id'/>";
    var txtDescripcio =  "<spring:message code='txt.indexaciones.descripcion'/>";
    var txtAccio =  "<spring:message code='txt.sia.accion'/>";
    var txtEstado =  "<spring:message code='txt.sia.estado'/>";
    var txtFalta =  "<spring:message code='txt.sia.fecha.alta'/>";
    var txtFenvio =  "<spring:message code='txt.sia.fecha.envio'/>";
    var txtIdElemento =  "<spring:message code='txt.sia.id.elemento'/>";
    var txtMensaje =  "<spring:message code='txt.indexaciones.mensaje'/>";
    var txtTipo = "<spring:message code='txt.sia.tipo'/>";
    var txtSiaDescripcionTipoPro =  "<spring:message code='index.descripcio.tipo.PRO'/>";
    var txtSiaDescripcionTipoUna =  "<spring:message code='index.descripcio.tipo.UNA'/>";
    var txtSiaDescripcionTipoNor=  "<spring:message code='index.descripcio.tipo.NOR'/>";
    var txtEnviantDades="<spring:message code='index.missatge.enviant_dades.sia'/>" ;
    var txtBotonEnvio="<spring:message code='sia.boton.envio_pendiente' />";
    var txtEstadoCreado="<spring:message code='sia.estado.creado' />";
    var txtEstadoEje="<spring:message code='sia.estado.ejecucion' />";
    var txtEstadoEnviado="<spring:message code='sia.estado.enviado' />";
    var txtEstadoEnvError="<spring:message code='sia.estado.enviado_errores' />";
    var txtEstadoError="<spring:message code='sia.estado.error' />";
	
    //taula    
    var txtNou = "<spring:message code='txt.afegir_nova'/> "; + txtLlistaItem.toLowerCase();
    var txtCodi = "<spring:message code='txt.codi'/>";
    var txtDescripcio = "<spring:message code='camp.descripcio'/>";
    var txtOrdre = "<spring:message code='camp.ordre'/>";
    var txtPujar = "<spring:message code='txt.pujar'/>";

    //paginacio
    var txtTrobat = "<spring:message code='txt.sha_trobat'/>";
    var txtSeguents = "<spring:message code='txt.seguents'/>";
    var txtAnteriors = "<spring:message code='txt.anteriors'/>";
    var txtInici = "<spring:message code='txt.inici'/>";
    var txtFinal = "<spring:message code='txt.final'/>";
    var txtPagines = "<spring:message code='txt.pagines'/>";
    var txtCercant = "<spring:message code='txt.cercant'/>";
    var txtCercantAnteriors = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
    var txtCercantSeguents = txtCercant + " " + txtLlistaItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
    var txtCercantElements = txtCercant + " <spring:message code='txt.elements'/>" + ". " + txtEspere;

    //Botones
    var txtCerrantJobs="<spring:message code='index.missatge.cerrando_jobs'/>" ;
    var txtTiempoJobs="<spring:message code='index.missatge.tiempo_sia_jobs'/>" ;
    var txtBotonContinuar = "<spring:message code='boto.continuar'/>";
    var txtBotonInfo = "<spring:message code='boto.info'/>";
    

</script>
<script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript">
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtMax = "<spring:message code='txt.max'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
    var txtAnyMal = "<spring:message code='txt.any_mal'/>";
    var txtMesMal = "<spring:message code='txt.mes_mal'/>";
    var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
    var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";
    var txtNombreObligatorio = "<spring:message code='personal.formulari.nom.obligatori'/>";
    var txtNombreNoSoloNumeros = "<spring:message code='personal.formulari.nom.no_nomes_numeros'/>";
    
</script>

<div id="escriptori_contingut"> 
    <ul id="opcions">
         <li class="opcio L actiu sinicon">
            <a id="tabListado" href="javascript:void(0)"><spring:message code='sia.principal'/></a>
        </li>
        <li class="opcio C sinicon">
            <a id="tabBuscador" href="javascript:;"><spring:message code='sia.envio_pendiente'/></a>
        </li>     
        
    </ul>
    <div id="resultats">
        <div class="resultats L actiu">   
          <div class="tabBlanco">            
          	<div class="dadesJob">
                <p class="executant"><spring:message code='index.carregant_llistat_index'/></p>
            </div>
          	<br /><br /><br />     
            <span class="missatge"><spring:message code='sia.volver_enviar'/></span>
            <span class="btnGenerico">
                 <a href="javascript:;" class="btn unitatOrganica" id = "btnContinuar"><span><span><spring:message code='boto.continuar'/></span></span></a>
            </span> 
            <br /><br /><br />     
            <span class="missatge"><spring:message code='sia.enviar_tiempo'/></span>
            <span class="btnGenerico">
                 <a href="javascript:;" class="btn unitatOrganica" id = "btnIndexarTiempo"><span><span><spring:message code='boto.continuar'/></span></span></a>
            </span> 
            <br />
           
            <span class="missatge"><spring:message code='sia.cerrar_job'/></span>
            <span class="btnGenerico">
                 <a href="javascript:;" class="btn unitatOrganica" id = "btnCerrarJobs"><span><span><spring:message code='boto.continuar'/></span></span></a>
            </span>
            <br />
           
            
          </div>            
        </div>
        
        <div class="resultats C">
                                            
            <div class="dades">
                <p class="executant"><spring:message code='index.carregant_llistat_index'/></p>
            </div>
            <input type="hidden" value="0" class="pagPagina" /> 
            <input type="hidden" value="DESC" class="ordreTipus" /> 
            <input type="hidden" value="id" class="ordreCamp" />				                               
                                        
        </div>
    </div>
</div>

<div id="popup" class="popup" style="display: none;">
    <div class="content-popup">
        <div class="close"><a href="#" id="close"><img src="../img/botons/eliminar.gif"/></a></div>
        <div>
           <textarea id="item_texto" name="item_texto" cols="70" rows="15" class="nou"></textarea>     
        </div>
    </div>
</div>
</form>
</div>