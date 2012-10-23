<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">

	// idiomes configurats a l'aplicació
	var idiomesAplicacio = ["ca", "es", "fr", "en", "de"];

    // pagines comuns
    var pagArrel = '<c:url value="/" />';
    var pagSortirAplicacio = "<c:out value="${capUrlSortir}"/>"; 
    var tinyMceUrl = '<c:url value="/js/tiny_mce/tiny_mce.js" />';  
    var popupUA = '<c:url value="/pantalles/popArbreUA.do"/>';
    // pagines
    var pagProcediments = '<c:url value="/catalegProcediments/catalegProcediments.do"/>';
    //resultados por pagina
    var pag_Res = 5; //TODO: Se tiene que recuperar de BBDD
    
    var txtSinValor = '<spring:message code="aplicacio.sense_valor"/>';
    // missatge
    var txtAccepta = '<spring:message code="aplicacio.accepta"/>';
    var txtCancela = '<spring:message code="aplicacio.cancela"/>';
    var txtTancarAplicacio = '<spring:message code="aplicacio.logout.pregunta"/>';    
    var txtCargandoEntidades = '<spring:message code="aplicacio.carregant_entitas"/>';
    
    // cap
    var txtAmagar = '<spring:message code="cap.amagar"/>';
    var txtAmagarCap = txtAmagar + ' ' + '<spring:message code="cap.capsalera"/>';
    var txtMostrar = '<spring:message code="cap.mostrar"/>';
    var txtMostrarCap = txtMostrar + ' ' + '<spring:message code="cap.capsalera"/>';
    // molla pa
    var txtCarregantMollaFills = '<spring:message code="mollapa.unitats.carregant"/>';
    var pagMollaPa = '<c:url value="/unidadAdministrativa/listarHijos.do"/>';
    var cambioMollaPa = '<c:url value="/unidadAdministrativa/cambiarUA.do"/>';
    // planificacio
    var txtAvui = '<spring:message code="planificacio.avui"/>';
    var txtDema = '<spring:message code="planificacio.dema"/>';
    var txtDetallElement = '<spring:message code="planificacio.detall_element"/>';
    var txtAquest = '<spring:message code="planificacio.aquest"/>';
    var txtAquesta = '<spring:message code="planificacio.aquesta"/>';
    var txtAnomenada = '<spring:message code="planificacio.anomenada"/>';
    var txtPassara = '<spring:message code="planificacio.passara_a_estar"/>';
    var txtCaducada = '<spring:message code="planificacio.caducada"/>';
    var txtPublicada = '<spring:message code="planificacio.publicada"/>';

    var txtTitol = '<spring:message code="camp.titol_normativa"/>';
    var txtData = '<spring:message code="camp.data"/>';
    var txtDataButlleti = '<spring:message code="camp.data_butlleti"/>';
    
    var txtElimina = '<spring:message code="txt.elimina"/>';
    var txtSeleccionat = '<spring:message code="txt.seleccionat"/>';
    var txtSeleccionats = '<spring:message code="txt.seleccionats"/>';
    var txtSeleccionada = '<spring:message code="txt.seleccionada"/>';
    var txtSeleccionades = '<spring:message code="txt.seleccionades"/>';

    // grafica
    var mesosNom = new Array ("",
        '<spring:message code="data.mes.gener"/>',
        '<spring:message code="data.mes.febrer"/>',
        '<spring:message code="data.mes.mars"/>',
        '<spring:message code="data.mes.abril"/>',
        '<spring:message code="data.mes.maig"/>',
        '<spring:message code="data.mes.juny"/>',
        '<spring:message code="data.mes.juliol"/>',
        '<spring:message code="data.mes.agost"/>',
        '<spring:message code="data.mes.setembre"/>',
        '<spring:message code="data.mes.octubre"/>',
        '<spring:message code="data.mes.novembre"/>',
        '<spring:message code="data.mes.decembre"/>'
    );
    
    // taula
    var txtTipus = '<spring:message code="taula.tipus"/>';
    var txtActius = '<spring:message code="taula.actius"/>';
    var txtCaducats = '<spring:message code="taula.caducats"/>';
    var txtTotal = '<spring:message code="taula.total"/>';
    var txtProcediment = '<spring:message code="taula.procediment"/>';
    var txtProcediments = '<spring:message code="taula.procediments"/>';
    var txtNormativa = '<spring:message code="taula.normativa"/>';
    var txtFitxa = '<spring:message code="taula.fitxa"/>';
    var txtFitxes = '<spring:message code="taula.fitxes"/>';
    
    // nodes
    var txtNoHiHaNodes = '<spring:message code="nodes.no_hi_ha"/>';

    // error conexio
    var txtGenericError = '<spring:message code="error.error"/>';
    var txtCalcularTotal = '<spring:message code="error.conexio.calcular_total"/>';
    var txtAjaxError = '<spring:message code="error.conexio.ajax"/>';
    var txtIntenteho = '<spring:message code="error.conexio.intentar_de_nou"/>';
    var txtConexionIntentar = '<spring:message code="error.conexio.intentar_en"/>';
    var txtSegon = '<spring:message code="error.conexio.segon"/>';
    var txtSegons = '<spring:message code="error.conexio.segons"/>';
    var txtConectar = '<spring:message code="error.conexio.conectar_ara"/>';
    var txtFuncions = '<spring:message code="error.conexio.funcions"/>';
    var txtFuncionsFins = '<spring:message code="error.conexio.fins"/>';
    var txtErrorPermisos = '<spring:message code="error.permisos"/>';
    var txtErrorOperacio = '<spring:message code="error.operacio_fallida"/>';    
        
    // Errores de validaci�n
    var txtMaxim = "<spring:message code='txt.maxim'/>";
    var txtCaracters = "<spring:message code='txt.caracters'/>";
    
    // Traductor
    var txtTraduccioCorrecta = "<spring:message code='traductor.traduccio.correcta'/>";
    var txtTraductorAvis = "<spring:message code='traductor.avis'/>";
    var txtTraductorAvisTitol = "<spring:message code='traductor.avis.titol'/>";
</script>