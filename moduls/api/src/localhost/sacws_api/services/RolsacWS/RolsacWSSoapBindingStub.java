/**
 * RolsacWSSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.RolsacWS;

public class RolsacWSSoapBindingStub extends org.apache.axis.client.Stub implements localhost.sacws_api.services.RolsacWS.RolsacQueryServiceEJBRemote {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[60];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarUnitatsAdministratives");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaCriteria"), es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarUnitatsAdministrativesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirEspaiTerritorial");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialCriteria"), es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirEspaiTerritorialReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirProcediment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentCriteria"), es.caib.rolsac.api.v2.procediment.ProcedimentCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.procediment.ProcedimentDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirProcedimentReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarProcediments");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentCriteria"), es.caib.rolsac.api.v2.procediment.ProcedimentCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarProcedimentsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirMateria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaCriteria"), es.caib.rolsac.api.v2.materia.MateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.materia.MateriaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirMateriaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarMateries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaCriteria"), es.caib.rolsac.api.v2.materia.MateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarMateriesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirTramit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitCriteria"), es.caib.rolsac.api.v2.tramit.TramitCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.tramit.TramitDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirTramitReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarTramit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitCriteria"), es.caib.rolsac.api.v2.tramit.TramitCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarTramitReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirUnitatAdministrativa");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaCriteria"), es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirUnitatAdministrativaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirFetVital");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://fetVital.v2.api.rolsac.caib.es", "FetVitalCriteria"), es.caib.rolsac.api.v2.fetVital.FetVitalCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://fetVital.v2.api.rolsac.caib.es", "FetVitalDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.fetVital.FetVitalDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirFetVitalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarFetsVitals");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://fetVital.v2.api.rolsac.caib.es", "FetVitalCriteria"), es.caib.rolsac.api.v2.fetVital.FetVitalCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarFetsVitalsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirFitxa");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaCriteria"), es.caib.rolsac.api.v2.fitxa.FitxaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.fitxa.FitxaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirFitxaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarFitxes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaCriteria"), es.caib.rolsac.api.v2.fitxa.FitxaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarFitxesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirDocumentTramit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitCriteria"), es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirDocumentTramitReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarDocumentTramit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitCriteria"), es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarDocumentTramitReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirNormativa");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaCriteria"), es.caib.rolsac.api.v2.normativa.NormativaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.normativa.NormativaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirNormativaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarNormatives");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaCriteria"), es.caib.rolsac.api.v2.normativa.NormativaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarNormativesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirPersonal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalCriteria"), es.caib.rolsac.api.v2.personal.PersonalCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.personal.PersonalDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirPersonalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarPersonal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalCriteria"), es.caib.rolsac.api.v2.personal.PersonalCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarPersonalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirUsuari");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://usuari.v2.api.rolsac.caib.es", "UsuariCriteria"), es.caib.rolsac.api.v2.usuari.UsuariCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://usuari.v2.api.rolsac.caib.es", "UsuariDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.usuari.UsuariDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirUsuariReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarUsuaris");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://usuari.v2.api.rolsac.caib.es", "UsuariCriteria"), es.caib.rolsac.api.v2.usuari.UsuariCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarUsuarisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirTaxa");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaCriteria"), es.caib.rolsac.api.v2.taxa.TaxaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.taxa.TaxaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirTaxaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarTaxes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaCriteria"), es.caib.rolsac.api.v2.taxa.TaxaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarTaxesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirAgrupacioFetVital");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://agrupacioFetVital.v2.api.rolsac.caib.es", "AgrupacioFetVitalCriteria"), es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://agrupacioFetVital.v2.api.rolsac.caib.es", "AgrupacioFetVitalDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirAgrupacioFetVitalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarAgrupacionsFetsVitals");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://agrupacioFetVital.v2.api.rolsac.caib.es", "AgrupacioFetVitalCriteria"), es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarAgrupacionsFetsVitalsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirAgrupacioMateria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://agrupacioMateria.v2.api.rolsac.caib.es", "AgrupacioMateriaCriteria"), es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://agrupacioMateria.v2.api.rolsac.caib.es", "AgrupacioMateriaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirAgrupacioMateriaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarAgrupacionsMateries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://agrupacioMateria.v2.api.rolsac.caib.es", "AgrupacioMateriaCriteria"), es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarAgrupacionsMateriesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirButlleti");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiCriteria"), es.caib.rolsac.api.v2.butlleti.ButlletiCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.butlleti.ButlletiDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirButlletiReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarButlletins");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiCriteria"), es.caib.rolsac.api.v2.butlleti.ButlletiCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarButlletinsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirDocument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://document.v2.api.rolsac.caib.es", "DocumentCriteria"), es.caib.rolsac.api.v2.document.DocumentCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://document.v2.api.rolsac.caib.es", "DocumentDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.document.DocumentDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirDocumentReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarDocuments");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://document.v2.api.rolsac.caib.es", "DocumentCriteria"), es.caib.rolsac.api.v2.document.DocumentCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarDocumentsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirEdifici");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiCriteria"), es.caib.rolsac.api.v2.edifici.EdificiCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.edifici.EdificiDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirEdificiReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarEdificis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiCriteria"), es.caib.rolsac.api.v2.edifici.EdificiCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarEdificisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirEnllac");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacCriteria"), es.caib.rolsac.api.v2.enllac.EnllacCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.enllac.EnllacDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirEnllacReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarEnllacos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacCriteria"), es.caib.rolsac.api.v2.enllac.EnllacCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarEnllacosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarEspaisTerritorials");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialCriteria"), es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarEspaisTerritorialsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirFamilia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://familia.v2.api.rolsac.caib.es", "FamiliaCriteria"), es.caib.rolsac.api.v2.familia.FamiliaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://familia.v2.api.rolsac.caib.es", "FamiliaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.familia.FamiliaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirFamiliaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarFamilies");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://familia.v2.api.rolsac.caib.es", "FamiliaCriteria"), es.caib.rolsac.api.v2.familia.FamiliaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarFamiliesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirPublicObjectiu");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuCriteria"), es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirPublicObjectiuReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarPublicsObjectius");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuCriteria"), es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarPublicsObjectiusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirFitxaUA");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUACriteria"), es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUADTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirFitxaUAReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarFitxesUA");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUACriteria"), es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarFitxesUAReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirFormulari");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariCriteria"), es.caib.rolsac.api.v2.formulari.FormulariCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.formulari.FormulariDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirFormulariReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarFormularis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariCriteria"), es.caib.rolsac.api.v2.formulari.FormulariCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarFormularisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirIconaFamilia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iconaFamilia.v2.api.rolsac.caib.es", "IconaFamiliaCriteria"), es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iconaFamilia.v2.api.rolsac.caib.es", "IconaFamiliaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirIconaFamiliaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarIconesFamilies");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iconaFamilia.v2.api.rolsac.caib.es", "IconaFamiliaCriteria"), es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarIconesFamiliesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirIconaMateria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iconaMateria.v2.api.rolsac.caib.es", "IconaMateriaCriteria"), es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://iconaMateria.v2.api.rolsac.caib.es", "IconaMateriaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirIconaMateriaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarIconesMateries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iconaMateria.v2.api.rolsac.caib.es", "IconaMateriaCriteria"), es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarIconesMateriesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirMateriaAgrupacio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioCriteria"), es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirMateriaAgrupacioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarMateriesAgrupacions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioCriteria"), es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarMateriesAgrupacionsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirPerfil");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilCriteria"), es.caib.rolsac.api.v2.perfil.PerfilCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.perfil.PerfilDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirPerfilReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarPerfils");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilCriteria"), es.caib.rolsac.api.v2.perfil.PerfilCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarPerfilsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirSeccio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioCriteria"), es.caib.rolsac.api.v2.seccio.SeccioCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.seccio.SeccioDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirSeccioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarSeccions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioCriteria"), es.caib.rolsac.api.v2.seccio.SeccioCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarSeccionsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirUnitatMateria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaCriteria"), es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirUnitatMateriaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarUnitatsMateries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaCriteria"), es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarUnitatsMateriesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirTipus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tipus.v2.api.rolsac.caib.es", "TipusCriteria"), es.caib.rolsac.api.v2.tipus.TipusCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tipus.v2.api.rolsac.caib.es", "TipusDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.tipus.TipusDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirTipusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarTipus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tipus.v2.api.rolsac.caib.es", "TipusCriteria"), es.caib.rolsac.api.v2.tipus.TipusCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarTipusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenirTipusAfectacio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tipusAfectacio.v2.api.rolsac.caib.es", "TipusAfectacioCriteria"), es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tipusAfectacio.v2.api.rolsac.caib.es", "TipusAfectacioDTO"));
        oper.setReturnClass(es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "obtenirTipusAfectacioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("llistarTipusAfectacio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tipusAfectacio.v2.api.rolsac.caib.es", "TipusAfectacioCriteria"), es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "llistarTipusAfectacioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[59] = oper;

    }

    public RolsacWSSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RolsacWSSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RolsacWSSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://agrupacioFetVital.v2.api.rolsac.caib.es", "AgrupacioFetVitalCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://agrupacioFetVital.v2.api.rolsac.caib.es", "AgrupacioFetVitalDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://agrupacioMateria.v2.api.rolsac.caib.es", "AgrupacioMateriaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://agrupacioMateria.v2.api.rolsac.caib.es", "AgrupacioMateriaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.butlleti.ButlletiCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.butlleti.ButlletiDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://document.v2.api.rolsac.caib.es", "DocumentCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.document.DocumentCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://document.v2.api.rolsac.caib.es", "DocumentDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.document.DocumentDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.edifici.EdificiCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.edifici.EdificiDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.enllac.EnllacCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.enllac.EnllacDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://familia.v2.api.rolsac.caib.es", "FamiliaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.familia.FamiliaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://familia.v2.api.rolsac.caib.es", "FamiliaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.familia.FamiliaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://fetVital.v2.api.rolsac.caib.es", "FetVitalCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.fetVital.FetVitalCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://fetVital.v2.api.rolsac.caib.es", "FetVitalDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.fetVital.FetVitalDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.fitxa.FitxaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.fitxa.FitxaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUACriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUADTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.formulari.FormulariCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.formulari.FormulariDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://general.v2.api.rolsac.caib.es", "BasicCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.general.BasicCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://general.v2.api.rolsac.caib.es", "EntitatRemota");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.general.EntitatRemota.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iconaFamilia.v2.api.rolsac.caib.es", "IconaFamiliaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iconaFamilia.v2.api.rolsac.caib.es", "IconaFamiliaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iconaMateria.v2.api.rolsac.caib.es", "IconaMateriaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://iconaMateria.v2.api.rolsac.caib.es", "IconaMateriaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.materia.MateriaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.materia.MateriaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.normativa.NormativaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.normativa.NormativaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.perfil.PerfilCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.perfil.PerfilDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.personal.PersonalCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.personal.PersonalDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.procediment.ProcedimentCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.procediment.ProcedimentDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.seccio.SeccioCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.seccio.SeccioDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.taxa.TaxaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.taxa.TaxaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tipus.v2.api.rolsac.caib.es", "TipusCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.tipus.TipusCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tipus.v2.api.rolsac.caib.es", "TipusDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.tipus.TipusDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tipusAfectacio.v2.api.rolsac.caib.es", "TipusAfectacioCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tipusAfectacio.v2.api.rolsac.caib.es", "TipusAfectacioDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.tramit.TramitCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.tramit.TramitDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://usuari.v2.api.rolsac.caib.es", "UsuariCriteria");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.usuari.UsuariCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://usuari.v2.api.rolsac.caib.es", "UsuariDTO");
            cachedSerQNames.add(qName);
            cls = es.caib.rolsac.api.v2.usuari.UsuariDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://localhost:8443/sacws-api/services/RolsacWS", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = java.lang.Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.Object[] llistarUnitatsAdministratives(es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarUnitatsAdministratives"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO obtenirEspaiTerritorial(es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirEspaiTerritorial"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.procediment.ProcedimentDTO obtenirProcediment(es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirProcediment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.procediment.ProcedimentDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.procediment.ProcedimentDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.procediment.ProcedimentDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarProcediments(es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarProcediments"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.materia.MateriaDTO obtenirMateria(es.caib.rolsac.api.v2.materia.MateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirMateria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.materia.MateriaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.materia.MateriaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.materia.MateriaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarMateries(es.caib.rolsac.api.v2.materia.MateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarMateries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.tramit.TramitDTO obtenirTramit(es.caib.rolsac.api.v2.tramit.TramitCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirTramit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.tramit.TramitDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.tramit.TramitDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.tramit.TramitDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarTramit(es.caib.rolsac.api.v2.tramit.TramitCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarTramit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO obtenirUnitatAdministrativa(es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirUnitatAdministrativa"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.fetVital.FetVitalDTO obtenirFetVital(es.caib.rolsac.api.v2.fetVital.FetVitalCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirFetVital"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.fetVital.FetVitalDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.fetVital.FetVitalDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.fetVital.FetVitalDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarFetsVitals(es.caib.rolsac.api.v2.fetVital.FetVitalCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarFetsVitals"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.fitxa.FitxaDTO obtenirFitxa(es.caib.rolsac.api.v2.fitxa.FitxaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirFitxa"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.fitxa.FitxaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.fitxa.FitxaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.fitxa.FitxaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarFitxes(es.caib.rolsac.api.v2.fitxa.FitxaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarFitxes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO obtenirDocumentTramit(es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirDocumentTramit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarDocumentTramit(es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarDocumentTramit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.normativa.NormativaDTO obtenirNormativa(es.caib.rolsac.api.v2.normativa.NormativaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirNormativa"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.normativa.NormativaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.normativa.NormativaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.normativa.NormativaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarNormatives(es.caib.rolsac.api.v2.normativa.NormativaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarNormatives"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.personal.PersonalDTO obtenirPersonal(es.caib.rolsac.api.v2.personal.PersonalCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirPersonal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.personal.PersonalDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.personal.PersonalDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.personal.PersonalDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarPersonal(es.caib.rolsac.api.v2.personal.PersonalCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarPersonal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.usuari.UsuariDTO obtenirUsuari(es.caib.rolsac.api.v2.usuari.UsuariCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirUsuari"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.usuari.UsuariDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.usuari.UsuariDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.usuari.UsuariDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarUsuaris(es.caib.rolsac.api.v2.usuari.UsuariCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarUsuaris"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.taxa.TaxaDTO obtenirTaxa(es.caib.rolsac.api.v2.taxa.TaxaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirTaxa"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.taxa.TaxaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.taxa.TaxaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.taxa.TaxaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarTaxes(es.caib.rolsac.api.v2.taxa.TaxaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarTaxes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO obtenirAgrupacioFetVital(es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirAgrupacioFetVital"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarAgrupacionsFetsVitals(es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarAgrupacionsFetsVitals"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO obtenirAgrupacioMateria(es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirAgrupacioMateria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarAgrupacionsMateries(es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarAgrupacionsMateries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.butlleti.ButlletiDTO obtenirButlleti(es.caib.rolsac.api.v2.butlleti.ButlletiCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirButlleti"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.butlleti.ButlletiDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.butlleti.ButlletiDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.butlleti.ButlletiDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarButlletins(es.caib.rolsac.api.v2.butlleti.ButlletiCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarButlletins"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.document.DocumentDTO obtenirDocument(es.caib.rolsac.api.v2.document.DocumentCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirDocument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.document.DocumentDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.document.DocumentDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.document.DocumentDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarDocuments(es.caib.rolsac.api.v2.document.DocumentCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarDocuments"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.edifici.EdificiDTO obtenirEdifici(es.caib.rolsac.api.v2.edifici.EdificiCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirEdifici"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.edifici.EdificiDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.edifici.EdificiDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.edifici.EdificiDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarEdificis(es.caib.rolsac.api.v2.edifici.EdificiCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarEdificis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.enllac.EnllacDTO obtenirEnllac(es.caib.rolsac.api.v2.enllac.EnllacCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirEnllac"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.enllac.EnllacDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.enllac.EnllacDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.enllac.EnllacDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarEnllacos(es.caib.rolsac.api.v2.enllac.EnllacCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarEnllacos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarEspaisTerritorials(es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarEspaisTerritorials"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.familia.FamiliaDTO obtenirFamilia(es.caib.rolsac.api.v2.familia.FamiliaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirFamilia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.familia.FamiliaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.familia.FamiliaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.familia.FamiliaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarFamilies(es.caib.rolsac.api.v2.familia.FamiliaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarFamilies"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO obtenirPublicObjectiu(es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirPublicObjectiu"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarPublicsObjectius(es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarPublicsObjectius"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO obtenirFitxaUA(es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirFitxaUA"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarFitxesUA(es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarFitxesUA"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.formulari.FormulariDTO obtenirFormulari(es.caib.rolsac.api.v2.formulari.FormulariCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirFormulari"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.formulari.FormulariDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.formulari.FormulariDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.formulari.FormulariDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarFormularis(es.caib.rolsac.api.v2.formulari.FormulariCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarFormularis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO obtenirIconaFamilia(es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirIconaFamilia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarIconesFamilies(es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarIconesFamilies"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO obtenirIconaMateria(es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirIconaMateria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarIconesMateries(es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarIconesMateries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO obtenirMateriaAgrupacio(es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirMateriaAgrupacio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarMateriesAgrupacions(es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarMateriesAgrupacions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.perfil.PerfilDTO obtenirPerfil(es.caib.rolsac.api.v2.perfil.PerfilCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirPerfil"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.perfil.PerfilDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.perfil.PerfilDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.perfil.PerfilDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarPerfils(es.caib.rolsac.api.v2.perfil.PerfilCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarPerfils"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.seccio.SeccioDTO obtenirSeccio(es.caib.rolsac.api.v2.seccio.SeccioCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirSeccio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.seccio.SeccioDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.seccio.SeccioDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.seccio.SeccioDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarSeccions(es.caib.rolsac.api.v2.seccio.SeccioCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarSeccions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO obtenirUnitatMateria(es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirUnitatMateria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarUnitatsMateries(es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarUnitatsMateries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.tipus.TipusDTO obtenirTipus(es.caib.rolsac.api.v2.tipus.TipusCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirTipus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.tipus.TipusDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.tipus.TipusDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.tipus.TipusDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarTipus(es.caib.rolsac.api.v2.tipus.TipusCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarTipus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO obtenirTipusAfectacio(es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "obtenirTipusAfectacio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO) org.apache.axis.utils.JavaUtils.convert(_resp, es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] llistarTipusAfectacio(es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://intf.ejb.rolsac.v2.api.rolsac.caib.es", "llistarTipusAfectacio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
