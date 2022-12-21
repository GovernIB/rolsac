/**
 * ParamSIAACTUACIONESACTUACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA;

public class ParamSIAACTUACIONESACTUACION  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO INTERNO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN ESCOMUN;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO ACTIVO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE TIPOTRAMITE;

    private java.lang.String TITULOCIUDADANO;

    private java.lang.String DENOMINACION;

    private java.lang.String DESCRIPCION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONSUJETOATASAS SUJETOATASAS;

    /* 1 - continuo; 2 sujeto a plazos; 3 sujeto a convocatoria. */
    private java.lang.String PERIODICIDAD;

    private java.lang.String UNIDADGESTORATRAMITE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES;

    private java.lang.String CODNIVELADMINISTRACIONELECTRONICA;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION;

    private java.lang.String[] CANALACCESO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.FORMULARIOSFORMULARIO[] FORMULARIOS;

    private java.lang.String ENLACEWEB;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESRESPONSIVE ESRESPONSIVE;

    private java.lang.String PORTAL;

    private java.lang.String REQUISITOSINICIACION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONPRESENCIALNOADAPTABLE PRESENCIALNOADAPTABLE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO DISPONIBLEFUNCIONARIOHABILITADO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO DISPONIBLEAPODERADOHABILITADO;

    private java.lang.String CODREQUISITOSIDENTPJ;

    private java.lang.String CODREQUISITOSIDENTPF;

    private java.lang.String IDINTEGRADOCLAVE;

    private java.lang.String OBSERVACIONINTEGRADOCLAVE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES;

    private java.lang.String TIEMPOMEDIORESOLUCION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] VOLUMENNOTIFICACIONES;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA[] MATERIAS;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SUBMATERIASSUBMATERIA[] SUBMATERIAS;

    private java.lang.String CODCLASETRAMITE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] TRAMITESRELACIONADOS;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONNOREQUIEREDOCUMENTACION NOREQUIEREDOCUMENTACION;

    private java.lang.String DOCUMENTACION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOS INICIOS;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA FINVIA;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.PLAZORESOLUCION PLAZORESOLUCION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS;

    private java.lang.String CODIGOORIGEN;  // attribute

    private java.lang.String CODIGOACTUACION;  // attribute

    private java.lang.String OPERACION;  // attribute

    public ParamSIAACTUACIONESACTUACION() {
    }

    public ParamSIAACTUACIONESACTUACION(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO INTERNO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN ESCOMUN,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO ACTIVO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE TIPOTRAMITE,
           java.lang.String TITULOCIUDADANO,
           java.lang.String DENOMINACION,
           java.lang.String DESCRIPCION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONSUJETOATASAS SUJETOATASAS,
           java.lang.String PERIODICIDAD,
           java.lang.String UNIDADGESTORATRAMITE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES,
           java.lang.String CODNIVELADMINISTRACIONELECTRONICA,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION,
           java.lang.String[] CANALACCESO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.FORMULARIOSFORMULARIO[] FORMULARIOS,
           java.lang.String ENLACEWEB,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESRESPONSIVE ESRESPONSIVE,
           java.lang.String PORTAL,
           java.lang.String REQUISITOSINICIACION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONPRESENCIALNOADAPTABLE PRESENCIALNOADAPTABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO DISPONIBLEFUNCIONARIOHABILITADO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO DISPONIBLEAPODERADOHABILITADO,
           java.lang.String CODREQUISITOSIDENTPJ,
           java.lang.String CODREQUISITOSIDENTPF,
           java.lang.String IDINTEGRADOCLAVE,
           java.lang.String OBSERVACIONINTEGRADOCLAVE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES,
           java.lang.String TIEMPOMEDIORESOLUCION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] VOLUMENNOTIFICACIONES,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA[] MATERIAS,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SUBMATERIASSUBMATERIA[] SUBMATERIAS,
           java.lang.String CODCLASETRAMITE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] TRAMITESRELACIONADOS,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONNOREQUIEREDOCUMENTACION NOREQUIEREDOCUMENTACION,
           java.lang.String DOCUMENTACION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOS INICIOS,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA FINVIA,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.PLAZORESOLUCION PLAZORESOLUCION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS,
           java.lang.String CODIGOORIGEN,
           java.lang.String CODIGOACTUACION,
           java.lang.String OPERACION) {
           this.INTERNO = INTERNO;
           this.ESCOMUN = ESCOMUN;
           this.ACTIVO = ACTIVO;
           this.TIPOTRAMITE = TIPOTRAMITE;
           this.TITULOCIUDADANO = TITULOCIUDADANO;
           this.DENOMINACION = DENOMINACION;
           this.DESCRIPCION = DESCRIPCION;
           this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
           this.DESTINATARIOS = DESTINATARIOS;
           this.SUJETOATASAS = SUJETOATASAS;
           this.PERIODICIDAD = PERIODICIDAD;
           this.UNIDADGESTORATRAMITE = UNIDADGESTORATRAMITE;
           this.NOTIFICACIONES = NOTIFICACIONES;
           this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
           this.SISTEMASIDENTIFICACION = SISTEMASIDENTIFICACION;
           this.CANALACCESO = CANALACCESO;
           this.FORMULARIOS = FORMULARIOS;
           this.ENLACEWEB = ENLACEWEB;
           this.ESRESPONSIVE = ESRESPONSIVE;
           this.PORTAL = PORTAL;
           this.REQUISITOSINICIACION = REQUISITOSINICIACION;
           this.PRESENCIALNOADAPTABLE = PRESENCIALNOADAPTABLE;
           this.DISPONIBLEFUNCIONARIOHABILITADO = DISPONIBLEFUNCIONARIOHABILITADO;
           this.DISPONIBLEAPODERADOHABILITADO = DISPONIBLEAPODERADOHABILITADO;
           this.CODREQUISITOSIDENTPJ = CODREQUISITOSIDENTPJ;
           this.CODREQUISITOSIDENTPF = CODREQUISITOSIDENTPF;
           this.IDINTEGRADOCLAVE = IDINTEGRADOCLAVE;
           this.OBSERVACIONINTEGRADOCLAVE = OBSERVACIONINTEGRADOCLAVE;
           this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
           this.TIEMPOMEDIORESOLUCION = TIEMPOMEDIORESOLUCION;
           this.VOLUMENNOTIFICACIONES = VOLUMENNOTIFICACIONES;
           this.MATERIAS = MATERIAS;
           this.SUBMATERIAS = SUBMATERIAS;
           this.CODCLASETRAMITE = CODCLASETRAMITE;
           this.TRAMITESRELACIONADOS = TRAMITESRELACIONADOS;
           this.NOREQUIEREDOCUMENTACION = NOREQUIEREDOCUMENTACION;
           this.DOCUMENTACION = DOCUMENTACION;
           this.ALTADOCUMENTOSESPECIFICOS = ALTADOCUMENTOSESPECIFICOS;
           this.DOCUMENTOSCATALOGO = DOCUMENTOSCATALOGO;
           this.INICIOS = INICIOS;
           this.FINVIA = FINVIA;
           this.PLAZORESOLUCION = PLAZORESOLUCION;
           this.NORMATIVAS = NORMATIVAS;
           this.CODIGOORIGEN = CODIGOORIGEN;
           this.CODIGOACTUACION = CODIGOACTUACION;
           this.OPERACION = OPERACION;
    }


    /**
     * Gets the INTERNO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return INTERNO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO getINTERNO() {
        return INTERNO;
    }


    /**
     * Sets the INTERNO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param INTERNO
     */
    public void setINTERNO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO INTERNO) {
        this.INTERNO = INTERNO;
    }


    /**
     * Gets the ESCOMUN value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ESCOMUN
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN getESCOMUN() {
        return ESCOMUN;
    }


    /**
     * Sets the ESCOMUN value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ESCOMUN
     */
    public void setESCOMUN(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN ESCOMUN) {
        this.ESCOMUN = ESCOMUN;
    }


    /**
     * Gets the ACTIVO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ACTIVO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO getACTIVO() {
        return ACTIVO;
    }


    /**
     * Sets the ACTIVO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ACTIVO
     */
    public void setACTIVO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO ACTIVO) {
        this.ACTIVO = ACTIVO;
    }


    /**
     * Gets the TIPOTRAMITE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return TIPOTRAMITE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE getTIPOTRAMITE() {
        return TIPOTRAMITE;
    }


    /**
     * Sets the TIPOTRAMITE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param TIPOTRAMITE
     */
    public void setTIPOTRAMITE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE TIPOTRAMITE) {
        this.TIPOTRAMITE = TIPOTRAMITE;
    }


    /**
     * Gets the TITULOCIUDADANO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return TITULOCIUDADANO
     */
    public java.lang.String getTITULOCIUDADANO() {
        return TITULOCIUDADANO;
    }


    /**
     * Sets the TITULOCIUDADANO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param TITULOCIUDADANO
     */
    public void setTITULOCIUDADANO(java.lang.String TITULOCIUDADANO) {
        this.TITULOCIUDADANO = TITULOCIUDADANO;
    }


    /**
     * Gets the DENOMINACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DENOMINACION
     */
    public java.lang.String getDENOMINACION() {
        return DENOMINACION;
    }


    /**
     * Sets the DENOMINACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DENOMINACION
     */
    public void setDENOMINACION(java.lang.String DENOMINACION) {
        this.DENOMINACION = DENOMINACION;
    }


    /**
     * Gets the DESCRIPCION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the ORGANISMORESPONSABLE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ORGANISMORESPONSABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
        return ORGANISMORESPONSABLE;
    }


    /**
     * Sets the ORGANISMORESPONSABLE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ORGANISMORESPONSABLE
     */
    public void setORGANISMORESPONSABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE) {
        this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
    }


    /**
     * Gets the DESTINATARIOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DESTINATARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO[] getDESTINATARIOS() {
        return DESTINATARIOS;
    }


    /**
     * Sets the DESTINATARIOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DESTINATARIOS
     */
    public void setDESTINATARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS) {
        this.DESTINATARIOS = DESTINATARIOS;
    }


    /**
     * Gets the SUJETOATASAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return SUJETOATASAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONSUJETOATASAS getSUJETOATASAS() {
        return SUJETOATASAS;
    }


    /**
     * Sets the SUJETOATASAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param SUJETOATASAS
     */
    public void setSUJETOATASAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONSUJETOATASAS SUJETOATASAS) {
        this.SUJETOATASAS = SUJETOATASAS;
    }


    /**
     * Gets the PERIODICIDAD value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return PERIODICIDAD   * 1 - continuo; 2 sujeto a plazos; 3 sujeto a convocatoria.
     */
    public java.lang.String getPERIODICIDAD() {
        return PERIODICIDAD;
    }


    /**
     * Sets the PERIODICIDAD value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param PERIODICIDAD   * 1 - continuo; 2 sujeto a plazos; 3 sujeto a convocatoria.
     */
    public void setPERIODICIDAD(java.lang.String PERIODICIDAD) {
        this.PERIODICIDAD = PERIODICIDAD;
    }


    /**
     * Gets the UNIDADGESTORATRAMITE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return UNIDADGESTORATRAMITE
     */
    public java.lang.String getUNIDADGESTORATRAMITE() {
        return UNIDADGESTORATRAMITE;
    }


    /**
     * Sets the UNIDADGESTORATRAMITE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param UNIDADGESTORATRAMITE
     */
    public void setUNIDADGESTORATRAMITE(java.lang.String UNIDADGESTORATRAMITE) {
        this.UNIDADGESTORATRAMITE = UNIDADGESTORATRAMITE;
    }


    /**
     * Gets the NOTIFICACIONES value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return NOTIFICACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NOTIFICACIONESNOTIFICACION[] getNOTIFICACIONES() {
        return NOTIFICACIONES;
    }


    /**
     * Sets the NOTIFICACIONES value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param NOTIFICACIONES
     */
    public void setNOTIFICACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES) {
        this.NOTIFICACIONES = NOTIFICACIONES;
    }


    /**
     * Gets the CODNIVELADMINISTRACIONELECTRONICA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODNIVELADMINISTRACIONELECTRONICA
     */
    public java.lang.String getCODNIVELADMINISTRACIONELECTRONICA() {
        return CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Sets the CODNIVELADMINISTRACIONELECTRONICA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODNIVELADMINISTRACIONELECTRONICA
     */
    public void setCODNIVELADMINISTRACIONELECTRONICA(java.lang.String CODNIVELADMINISTRACIONELECTRONICA) {
        this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Gets the SISTEMASIDENTIFICACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return SISTEMASIDENTIFICACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] getSISTEMASIDENTIFICACION() {
        return SISTEMASIDENTIFICACION;
    }


    /**
     * Sets the SISTEMASIDENTIFICACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param SISTEMASIDENTIFICACION
     */
    public void setSISTEMASIDENTIFICACION(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION) {
        this.SISTEMASIDENTIFICACION = SISTEMASIDENTIFICACION;
    }


    /**
     * Gets the CANALACCESO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CANALACCESO
     */
    public java.lang.String[] getCANALACCESO() {
        return CANALACCESO;
    }


    /**
     * Sets the CANALACCESO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CANALACCESO
     */
    public void setCANALACCESO(java.lang.String[] CANALACCESO) {
        this.CANALACCESO = CANALACCESO;
    }

    public java.lang.String getCANALACCESO(int i) {
        return this.CANALACCESO[i];
    }

    public void setCANALACCESO(int i, java.lang.String _value) {
        this.CANALACCESO[i] = _value;
    }


    /**
     * Gets the FORMULARIOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return FORMULARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.FORMULARIOSFORMULARIO[] getFORMULARIOS() {
        return FORMULARIOS;
    }


    /**
     * Sets the FORMULARIOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param FORMULARIOS
     */
    public void setFORMULARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.FORMULARIOSFORMULARIO[] FORMULARIOS) {
        this.FORMULARIOS = FORMULARIOS;
    }


    /**
     * Gets the ENLACEWEB value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ENLACEWEB
     */
    public java.lang.String getENLACEWEB() {
        return ENLACEWEB;
    }


    /**
     * Sets the ENLACEWEB value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ENLACEWEB
     */
    public void setENLACEWEB(java.lang.String ENLACEWEB) {
        this.ENLACEWEB = ENLACEWEB;
    }


    /**
     * Gets the ESRESPONSIVE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ESRESPONSIVE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESRESPONSIVE getESRESPONSIVE() {
        return ESRESPONSIVE;
    }


    /**
     * Sets the ESRESPONSIVE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ESRESPONSIVE
     */
    public void setESRESPONSIVE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESRESPONSIVE ESRESPONSIVE) {
        this.ESRESPONSIVE = ESRESPONSIVE;
    }


    /**
     * Gets the PORTAL value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return PORTAL
     */
    public java.lang.String getPORTAL() {
        return PORTAL;
    }


    /**
     * Sets the PORTAL value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param PORTAL
     */
    public void setPORTAL(java.lang.String PORTAL) {
        this.PORTAL = PORTAL;
    }


    /**
     * Gets the REQUISITOSINICIACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return REQUISITOSINICIACION
     */
    public java.lang.String getREQUISITOSINICIACION() {
        return REQUISITOSINICIACION;
    }


    /**
     * Sets the REQUISITOSINICIACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param REQUISITOSINICIACION
     */
    public void setREQUISITOSINICIACION(java.lang.String REQUISITOSINICIACION) {
        this.REQUISITOSINICIACION = REQUISITOSINICIACION;
    }


    /**
     * Gets the PRESENCIALNOADAPTABLE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return PRESENCIALNOADAPTABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONPRESENCIALNOADAPTABLE getPRESENCIALNOADAPTABLE() {
        return PRESENCIALNOADAPTABLE;
    }


    /**
     * Sets the PRESENCIALNOADAPTABLE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param PRESENCIALNOADAPTABLE
     */
    public void setPRESENCIALNOADAPTABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONPRESENCIALNOADAPTABLE PRESENCIALNOADAPTABLE) {
        this.PRESENCIALNOADAPTABLE = PRESENCIALNOADAPTABLE;
    }


    /**
     * Gets the DISPONIBLEFUNCIONARIOHABILITADO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DISPONIBLEFUNCIONARIOHABILITADO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO getDISPONIBLEFUNCIONARIOHABILITADO() {
        return DISPONIBLEFUNCIONARIOHABILITADO;
    }


    /**
     * Sets the DISPONIBLEFUNCIONARIOHABILITADO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DISPONIBLEFUNCIONARIOHABILITADO
     */
    public void setDISPONIBLEFUNCIONARIOHABILITADO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO DISPONIBLEFUNCIONARIOHABILITADO) {
        this.DISPONIBLEFUNCIONARIOHABILITADO = DISPONIBLEFUNCIONARIOHABILITADO;
    }


    /**
     * Gets the DISPONIBLEAPODERADOHABILITADO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DISPONIBLEAPODERADOHABILITADO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO getDISPONIBLEAPODERADOHABILITADO() {
        return DISPONIBLEAPODERADOHABILITADO;
    }


    /**
     * Sets the DISPONIBLEAPODERADOHABILITADO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DISPONIBLEAPODERADOHABILITADO
     */
    public void setDISPONIBLEAPODERADOHABILITADO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO DISPONIBLEAPODERADOHABILITADO) {
        this.DISPONIBLEAPODERADOHABILITADO = DISPONIBLEAPODERADOHABILITADO;
    }


    /**
     * Gets the CODREQUISITOSIDENTPJ value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODREQUISITOSIDENTPJ
     */
    public java.lang.String getCODREQUISITOSIDENTPJ() {
        return CODREQUISITOSIDENTPJ;
    }


    /**
     * Sets the CODREQUISITOSIDENTPJ value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODREQUISITOSIDENTPJ
     */
    public void setCODREQUISITOSIDENTPJ(java.lang.String CODREQUISITOSIDENTPJ) {
        this.CODREQUISITOSIDENTPJ = CODREQUISITOSIDENTPJ;
    }


    /**
     * Gets the CODREQUISITOSIDENTPF value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODREQUISITOSIDENTPF
     */
    public java.lang.String getCODREQUISITOSIDENTPF() {
        return CODREQUISITOSIDENTPF;
    }


    /**
     * Sets the CODREQUISITOSIDENTPF value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODREQUISITOSIDENTPF
     */
    public void setCODREQUISITOSIDENTPF(java.lang.String CODREQUISITOSIDENTPF) {
        this.CODREQUISITOSIDENTPF = CODREQUISITOSIDENTPF;
    }


    /**
     * Gets the IDINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return IDINTEGRADOCLAVE
     */
    public java.lang.String getIDINTEGRADOCLAVE() {
        return IDINTEGRADOCLAVE;
    }


    /**
     * Sets the IDINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param IDINTEGRADOCLAVE
     */
    public void setIDINTEGRADOCLAVE(java.lang.String IDINTEGRADOCLAVE) {
        this.IDINTEGRADOCLAVE = IDINTEGRADOCLAVE;
    }


    /**
     * Gets the OBSERVACIONINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return OBSERVACIONINTEGRADOCLAVE
     */
    public java.lang.String getOBSERVACIONINTEGRADOCLAVE() {
        return OBSERVACIONINTEGRADOCLAVE;
    }


    /**
     * Sets the OBSERVACIONINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param OBSERVACIONINTEGRADOCLAVE
     */
    public void setOBSERVACIONINTEGRADOCLAVE(java.lang.String OBSERVACIONINTEGRADOCLAVE) {
        this.OBSERVACIONINTEGRADOCLAVE = OBSERVACIONINTEGRADOCLAVE;
    }


    /**
     * Gets the VOLUMENESTRAMITACIONES value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return VOLUMENESTRAMITACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] getVOLUMENESTRAMITACIONES() {
        return VOLUMENESTRAMITACIONES;
    }


    /**
     * Sets the VOLUMENESTRAMITACIONES value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param VOLUMENESTRAMITACIONES
     */
    public void setVOLUMENESTRAMITACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES) {
        this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
    }


    /**
     * Gets the TIEMPOMEDIORESOLUCION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return TIEMPOMEDIORESOLUCION
     */
    public java.lang.String getTIEMPOMEDIORESOLUCION() {
        return TIEMPOMEDIORESOLUCION;
    }


    /**
     * Sets the TIEMPOMEDIORESOLUCION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param TIEMPOMEDIORESOLUCION
     */
    public void setTIEMPOMEDIORESOLUCION(java.lang.String TIEMPOMEDIORESOLUCION) {
        this.TIEMPOMEDIORESOLUCION = TIEMPOMEDIORESOLUCION;
    }


    /**
     * Gets the VOLUMENNOTIFICACIONES value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return VOLUMENNOTIFICACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] getVOLUMENNOTIFICACIONES() {
        return VOLUMENNOTIFICACIONES;
    }


    /**
     * Sets the VOLUMENNOTIFICACIONES value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param VOLUMENNOTIFICACIONES
     */
    public void setVOLUMENNOTIFICACIONES(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[] VOLUMENNOTIFICACIONES) {
        this.VOLUMENNOTIFICACIONES = VOLUMENNOTIFICACIONES;
    }


    /**
     * Gets the MATERIAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return MATERIAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA[] getMATERIAS() {
        return MATERIAS;
    }


    /**
     * Sets the MATERIAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param MATERIAS
     */
    public void setMATERIAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA[] MATERIAS) {
        this.MATERIAS = MATERIAS;
    }


    /**
     * Gets the SUBMATERIAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return SUBMATERIAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SUBMATERIASSUBMATERIA[] getSUBMATERIAS() {
        return SUBMATERIAS;
    }


    /**
     * Sets the SUBMATERIAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param SUBMATERIAS
     */
    public void setSUBMATERIAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SUBMATERIASSUBMATERIA[] SUBMATERIAS) {
        this.SUBMATERIAS = SUBMATERIAS;
    }


    /**
     * Gets the CODCLASETRAMITE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODCLASETRAMITE
     */
    public java.lang.String getCODCLASETRAMITE() {
        return CODCLASETRAMITE;
    }


    /**
     * Sets the CODCLASETRAMITE value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODCLASETRAMITE
     */
    public void setCODCLASETRAMITE(java.lang.String CODCLASETRAMITE) {
        this.CODCLASETRAMITE = CODCLASETRAMITE;
    }


    /**
     * Gets the TRAMITESRELACIONADOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return TRAMITESRELACIONADOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] getTRAMITESRELACIONADOS() {
        return TRAMITESRELACIONADOS;
    }


    /**
     * Sets the TRAMITESRELACIONADOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param TRAMITESRELACIONADOS
     */
    public void setTRAMITESRELACIONADOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[] TRAMITESRELACIONADOS) {
        this.TRAMITESRELACIONADOS = TRAMITESRELACIONADOS;
    }


    /**
     * Gets the NOREQUIEREDOCUMENTACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return NOREQUIEREDOCUMENTACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONNOREQUIEREDOCUMENTACION getNOREQUIEREDOCUMENTACION() {
        return NOREQUIEREDOCUMENTACION;
    }


    /**
     * Sets the NOREQUIEREDOCUMENTACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param NOREQUIEREDOCUMENTACION
     */
    public void setNOREQUIEREDOCUMENTACION(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONNOREQUIEREDOCUMENTACION NOREQUIEREDOCUMENTACION) {
        this.NOREQUIEREDOCUMENTACION = NOREQUIEREDOCUMENTACION;
    }


    /**
     * Gets the DOCUMENTACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DOCUMENTACION
     */
    public java.lang.String getDOCUMENTACION() {
        return DOCUMENTACION;
    }


    /**
     * Sets the DOCUMENTACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DOCUMENTACION
     */
    public void setDOCUMENTACION(java.lang.String DOCUMENTACION) {
        this.DOCUMENTACION = DOCUMENTACION;
    }


    /**
     * Gets the ALTADOCUMENTOSESPECIFICOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return ALTADOCUMENTOSESPECIFICOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] getALTADOCUMENTOSESPECIFICOS() {
        return ALTADOCUMENTOSESPECIFICOS;
    }


    /**
     * Sets the ALTADOCUMENTOSESPECIFICOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param ALTADOCUMENTOSESPECIFICOS
     */
    public void setALTADOCUMENTOSESPECIFICOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[] ALTADOCUMENTOSESPECIFICOS) {
        this.ALTADOCUMENTOSESPECIFICOS = ALTADOCUMENTOSESPECIFICOS;
    }


    /**
     * Gets the DOCUMENTOSCATALOGO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return DOCUMENTOSCATALOGO
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] getDOCUMENTOSCATALOGO() {
        return DOCUMENTOSCATALOGO;
    }


    /**
     * Sets the DOCUMENTOSCATALOGO value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param DOCUMENTOSCATALOGO
     */
    public void setDOCUMENTOSCATALOGO(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[] DOCUMENTOSCATALOGO) {
        this.DOCUMENTOSCATALOGO = DOCUMENTOSCATALOGO;
    }


    /**
     * Gets the INICIOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return INICIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOS getINICIOS() {
        return INICIOS;
    }


    /**
     * Sets the INICIOS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param INICIOS
     */
    public void setINICIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOS INICIOS) {
        this.INICIOS = INICIOS;
    }


    /**
     * Gets the FINVIA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return FINVIA
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA getFINVIA() {
        return FINVIA;
    }


    /**
     * Sets the FINVIA value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param FINVIA
     */
    public void setFINVIA(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA FINVIA) {
        this.FINVIA = FINVIA;
    }


    /**
     * Gets the PLAZORESOLUCION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return PLAZORESOLUCION
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.PLAZORESOLUCION getPLAZORESOLUCION() {
        return PLAZORESOLUCION;
    }


    /**
     * Sets the PLAZORESOLUCION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param PLAZORESOLUCION
     */
    public void setPLAZORESOLUCION(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.PLAZORESOLUCION PLAZORESOLUCION) {
        this.PLAZORESOLUCION = PLAZORESOLUCION;
    }


    /**
     * Gets the NORMATIVAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return NORMATIVAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA[] getNORMATIVAS() {
        return NORMATIVAS;
    }


    /**
     * Sets the NORMATIVAS value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param NORMATIVAS
     */
    public void setNORMATIVAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA[] NORMATIVAS) {
        this.NORMATIVAS = NORMATIVAS;
    }


    /**
     * Gets the CODIGOORIGEN value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODIGOORIGEN
     */
    public java.lang.String getCODIGOORIGEN() {
        return CODIGOORIGEN;
    }


    /**
     * Sets the CODIGOORIGEN value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODIGOORIGEN
     */
    public void setCODIGOORIGEN(java.lang.String CODIGOORIGEN) {
        this.CODIGOORIGEN = CODIGOORIGEN;
    }


    /**
     * Gets the CODIGOACTUACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return CODIGOACTUACION
     */
    public java.lang.String getCODIGOACTUACION() {
        return CODIGOACTUACION;
    }


    /**
     * Sets the CODIGOACTUACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param CODIGOACTUACION
     */
    public void setCODIGOACTUACION(java.lang.String CODIGOACTUACION) {
        this.CODIGOACTUACION = CODIGOACTUACION;
    }


    /**
     * Gets the OPERACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @return OPERACION
     */
    public java.lang.String getOPERACION() {
        return OPERACION;
    }


    /**
     * Sets the OPERACION value for this ParamSIAACTUACIONESACTUACION.
     * 
     * @param OPERACION
     */
    public void setOPERACION(java.lang.String OPERACION) {
        this.OPERACION = OPERACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIAACTUACIONESACTUACION)) return false;
        ParamSIAACTUACIONESACTUACION other = (ParamSIAACTUACIONESACTUACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INTERNO==null && other.getINTERNO()==null) || 
             (this.INTERNO!=null &&
              this.INTERNO.equals(other.getINTERNO()))) &&
            ((this.ESCOMUN==null && other.getESCOMUN()==null) || 
             (this.ESCOMUN!=null &&
              this.ESCOMUN.equals(other.getESCOMUN()))) &&
            ((this.ACTIVO==null && other.getACTIVO()==null) || 
             (this.ACTIVO!=null &&
              this.ACTIVO.equals(other.getACTIVO()))) &&
            ((this.TIPOTRAMITE==null && other.getTIPOTRAMITE()==null) || 
             (this.TIPOTRAMITE!=null &&
              this.TIPOTRAMITE.equals(other.getTIPOTRAMITE()))) &&
            ((this.TITULOCIUDADANO==null && other.getTITULOCIUDADANO()==null) || 
             (this.TITULOCIUDADANO!=null &&
              this.TITULOCIUDADANO.equals(other.getTITULOCIUDADANO()))) &&
            ((this.DENOMINACION==null && other.getDENOMINACION()==null) || 
             (this.DENOMINACION!=null &&
              this.DENOMINACION.equals(other.getDENOMINACION()))) &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.ORGANISMORESPONSABLE==null && other.getORGANISMORESPONSABLE()==null) || 
             (this.ORGANISMORESPONSABLE!=null &&
              this.ORGANISMORESPONSABLE.equals(other.getORGANISMORESPONSABLE()))) &&
            ((this.DESTINATARIOS==null && other.getDESTINATARIOS()==null) || 
             (this.DESTINATARIOS!=null &&
              java.util.Arrays.equals(this.DESTINATARIOS, other.getDESTINATARIOS()))) &&
            ((this.SUJETOATASAS==null && other.getSUJETOATASAS()==null) || 
             (this.SUJETOATASAS!=null &&
              this.SUJETOATASAS.equals(other.getSUJETOATASAS()))) &&
            ((this.PERIODICIDAD==null && other.getPERIODICIDAD()==null) || 
             (this.PERIODICIDAD!=null &&
              this.PERIODICIDAD.equals(other.getPERIODICIDAD()))) &&
            ((this.UNIDADGESTORATRAMITE==null && other.getUNIDADGESTORATRAMITE()==null) || 
             (this.UNIDADGESTORATRAMITE!=null &&
              this.UNIDADGESTORATRAMITE.equals(other.getUNIDADGESTORATRAMITE()))) &&
            ((this.NOTIFICACIONES==null && other.getNOTIFICACIONES()==null) || 
             (this.NOTIFICACIONES!=null &&
              java.util.Arrays.equals(this.NOTIFICACIONES, other.getNOTIFICACIONES()))) &&
            ((this.CODNIVELADMINISTRACIONELECTRONICA==null && other.getCODNIVELADMINISTRACIONELECTRONICA()==null) || 
             (this.CODNIVELADMINISTRACIONELECTRONICA!=null &&
              this.CODNIVELADMINISTRACIONELECTRONICA.equals(other.getCODNIVELADMINISTRACIONELECTRONICA()))) &&
            ((this.SISTEMASIDENTIFICACION==null && other.getSISTEMASIDENTIFICACION()==null) || 
             (this.SISTEMASIDENTIFICACION!=null &&
              java.util.Arrays.equals(this.SISTEMASIDENTIFICACION, other.getSISTEMASIDENTIFICACION()))) &&
            ((this.CANALACCESO==null && other.getCANALACCESO()==null) || 
             (this.CANALACCESO!=null &&
              java.util.Arrays.equals(this.CANALACCESO, other.getCANALACCESO()))) &&
            ((this.FORMULARIOS==null && other.getFORMULARIOS()==null) || 
             (this.FORMULARIOS!=null &&
              java.util.Arrays.equals(this.FORMULARIOS, other.getFORMULARIOS()))) &&
            ((this.ENLACEWEB==null && other.getENLACEWEB()==null) || 
             (this.ENLACEWEB!=null &&
              this.ENLACEWEB.equals(other.getENLACEWEB()))) &&
            ((this.ESRESPONSIVE==null && other.getESRESPONSIVE()==null) || 
             (this.ESRESPONSIVE!=null &&
              this.ESRESPONSIVE.equals(other.getESRESPONSIVE()))) &&
            ((this.PORTAL==null && other.getPORTAL()==null) || 
             (this.PORTAL!=null &&
              this.PORTAL.equals(other.getPORTAL()))) &&
            ((this.REQUISITOSINICIACION==null && other.getREQUISITOSINICIACION()==null) || 
             (this.REQUISITOSINICIACION!=null &&
              this.REQUISITOSINICIACION.equals(other.getREQUISITOSINICIACION()))) &&
            ((this.PRESENCIALNOADAPTABLE==null && other.getPRESENCIALNOADAPTABLE()==null) || 
             (this.PRESENCIALNOADAPTABLE!=null &&
              this.PRESENCIALNOADAPTABLE.equals(other.getPRESENCIALNOADAPTABLE()))) &&
            ((this.DISPONIBLEFUNCIONARIOHABILITADO==null && other.getDISPONIBLEFUNCIONARIOHABILITADO()==null) || 
             (this.DISPONIBLEFUNCIONARIOHABILITADO!=null &&
              this.DISPONIBLEFUNCIONARIOHABILITADO.equals(other.getDISPONIBLEFUNCIONARIOHABILITADO()))) &&
            ((this.DISPONIBLEAPODERADOHABILITADO==null && other.getDISPONIBLEAPODERADOHABILITADO()==null) || 
             (this.DISPONIBLEAPODERADOHABILITADO!=null &&
              this.DISPONIBLEAPODERADOHABILITADO.equals(other.getDISPONIBLEAPODERADOHABILITADO()))) &&
            ((this.CODREQUISITOSIDENTPJ==null && other.getCODREQUISITOSIDENTPJ()==null) || 
             (this.CODREQUISITOSIDENTPJ!=null &&
              this.CODREQUISITOSIDENTPJ.equals(other.getCODREQUISITOSIDENTPJ()))) &&
            ((this.CODREQUISITOSIDENTPF==null && other.getCODREQUISITOSIDENTPF()==null) || 
             (this.CODREQUISITOSIDENTPF!=null &&
              this.CODREQUISITOSIDENTPF.equals(other.getCODREQUISITOSIDENTPF()))) &&
            ((this.IDINTEGRADOCLAVE==null && other.getIDINTEGRADOCLAVE()==null) || 
             (this.IDINTEGRADOCLAVE!=null &&
              this.IDINTEGRADOCLAVE.equals(other.getIDINTEGRADOCLAVE()))) &&
            ((this.OBSERVACIONINTEGRADOCLAVE==null && other.getOBSERVACIONINTEGRADOCLAVE()==null) || 
             (this.OBSERVACIONINTEGRADOCLAVE!=null &&
              this.OBSERVACIONINTEGRADOCLAVE.equals(other.getOBSERVACIONINTEGRADOCLAVE()))) &&
            ((this.VOLUMENESTRAMITACIONES==null && other.getVOLUMENESTRAMITACIONES()==null) || 
             (this.VOLUMENESTRAMITACIONES!=null &&
              java.util.Arrays.equals(this.VOLUMENESTRAMITACIONES, other.getVOLUMENESTRAMITACIONES()))) &&
            ((this.TIEMPOMEDIORESOLUCION==null && other.getTIEMPOMEDIORESOLUCION()==null) || 
             (this.TIEMPOMEDIORESOLUCION!=null &&
              this.TIEMPOMEDIORESOLUCION.equals(other.getTIEMPOMEDIORESOLUCION()))) &&
            ((this.VOLUMENNOTIFICACIONES==null && other.getVOLUMENNOTIFICACIONES()==null) || 
             (this.VOLUMENNOTIFICACIONES!=null &&
              java.util.Arrays.equals(this.VOLUMENNOTIFICACIONES, other.getVOLUMENNOTIFICACIONES()))) &&
            ((this.MATERIAS==null && other.getMATERIAS()==null) || 
             (this.MATERIAS!=null &&
              java.util.Arrays.equals(this.MATERIAS, other.getMATERIAS()))) &&
            ((this.SUBMATERIAS==null && other.getSUBMATERIAS()==null) || 
             (this.SUBMATERIAS!=null &&
              java.util.Arrays.equals(this.SUBMATERIAS, other.getSUBMATERIAS()))) &&
            ((this.CODCLASETRAMITE==null && other.getCODCLASETRAMITE()==null) || 
             (this.CODCLASETRAMITE!=null &&
              this.CODCLASETRAMITE.equals(other.getCODCLASETRAMITE()))) &&
            ((this.TRAMITESRELACIONADOS==null && other.getTRAMITESRELACIONADOS()==null) || 
             (this.TRAMITESRELACIONADOS!=null &&
              java.util.Arrays.equals(this.TRAMITESRELACIONADOS, other.getTRAMITESRELACIONADOS()))) &&
            ((this.NOREQUIEREDOCUMENTACION==null && other.getNOREQUIEREDOCUMENTACION()==null) || 
             (this.NOREQUIEREDOCUMENTACION!=null &&
              this.NOREQUIEREDOCUMENTACION.equals(other.getNOREQUIEREDOCUMENTACION()))) &&
            ((this.DOCUMENTACION==null && other.getDOCUMENTACION()==null) || 
             (this.DOCUMENTACION!=null &&
              this.DOCUMENTACION.equals(other.getDOCUMENTACION()))) &&
            ((this.ALTADOCUMENTOSESPECIFICOS==null && other.getALTADOCUMENTOSESPECIFICOS()==null) || 
             (this.ALTADOCUMENTOSESPECIFICOS!=null &&
              java.util.Arrays.equals(this.ALTADOCUMENTOSESPECIFICOS, other.getALTADOCUMENTOSESPECIFICOS()))) &&
            ((this.DOCUMENTOSCATALOGO==null && other.getDOCUMENTOSCATALOGO()==null) || 
             (this.DOCUMENTOSCATALOGO!=null &&
              java.util.Arrays.equals(this.DOCUMENTOSCATALOGO, other.getDOCUMENTOSCATALOGO()))) &&
            ((this.INICIOS==null && other.getINICIOS()==null) || 
             (this.INICIOS!=null &&
              this.INICIOS.equals(other.getINICIOS()))) &&
            ((this.FINVIA==null && other.getFINVIA()==null) || 
             (this.FINVIA!=null &&
              this.FINVIA.equals(other.getFINVIA()))) &&
            ((this.PLAZORESOLUCION==null && other.getPLAZORESOLUCION()==null) || 
             (this.PLAZORESOLUCION!=null &&
              this.PLAZORESOLUCION.equals(other.getPLAZORESOLUCION()))) &&
            ((this.NORMATIVAS==null && other.getNORMATIVAS()==null) || 
             (this.NORMATIVAS!=null &&
              java.util.Arrays.equals(this.NORMATIVAS, other.getNORMATIVAS()))) &&
            ((this.CODIGOORIGEN==null && other.getCODIGOORIGEN()==null) || 
             (this.CODIGOORIGEN!=null &&
              this.CODIGOORIGEN.equals(other.getCODIGOORIGEN()))) &&
            ((this.CODIGOACTUACION==null && other.getCODIGOACTUACION()==null) || 
             (this.CODIGOACTUACION!=null &&
              this.CODIGOACTUACION.equals(other.getCODIGOACTUACION()))) &&
            ((this.OPERACION==null && other.getOPERACION()==null) || 
             (this.OPERACION!=null &&
              this.OPERACION.equals(other.getOPERACION())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getINTERNO() != null) {
            _hashCode += getINTERNO().hashCode();
        }
        if (getESCOMUN() != null) {
            _hashCode += getESCOMUN().hashCode();
        }
        if (getACTIVO() != null) {
            _hashCode += getACTIVO().hashCode();
        }
        if (getTIPOTRAMITE() != null) {
            _hashCode += getTIPOTRAMITE().hashCode();
        }
        if (getTITULOCIUDADANO() != null) {
            _hashCode += getTITULOCIUDADANO().hashCode();
        }
        if (getDENOMINACION() != null) {
            _hashCode += getDENOMINACION().hashCode();
        }
        if (getDESCRIPCION() != null) {
            _hashCode += getDESCRIPCION().hashCode();
        }
        if (getORGANISMORESPONSABLE() != null) {
            _hashCode += getORGANISMORESPONSABLE().hashCode();
        }
        if (getDESTINATARIOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDESTINATARIOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDESTINATARIOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSUJETOATASAS() != null) {
            _hashCode += getSUJETOATASAS().hashCode();
        }
        if (getPERIODICIDAD() != null) {
            _hashCode += getPERIODICIDAD().hashCode();
        }
        if (getUNIDADGESTORATRAMITE() != null) {
            _hashCode += getUNIDADGESTORATRAMITE().hashCode();
        }
        if (getNOTIFICACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNOTIFICACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNOTIFICACIONES(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODNIVELADMINISTRACIONELECTRONICA() != null) {
            _hashCode += getCODNIVELADMINISTRACIONELECTRONICA().hashCode();
        }
        if (getSISTEMASIDENTIFICACION() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSISTEMASIDENTIFICACION());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSISTEMASIDENTIFICACION(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCANALACCESO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCANALACCESO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCANALACCESO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFORMULARIOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFORMULARIOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFORMULARIOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getENLACEWEB() != null) {
            _hashCode += getENLACEWEB().hashCode();
        }
        if (getESRESPONSIVE() != null) {
            _hashCode += getESRESPONSIVE().hashCode();
        }
        if (getPORTAL() != null) {
            _hashCode += getPORTAL().hashCode();
        }
        if (getREQUISITOSINICIACION() != null) {
            _hashCode += getREQUISITOSINICIACION().hashCode();
        }
        if (getPRESENCIALNOADAPTABLE() != null) {
            _hashCode += getPRESENCIALNOADAPTABLE().hashCode();
        }
        if (getDISPONIBLEFUNCIONARIOHABILITADO() != null) {
            _hashCode += getDISPONIBLEFUNCIONARIOHABILITADO().hashCode();
        }
        if (getDISPONIBLEAPODERADOHABILITADO() != null) {
            _hashCode += getDISPONIBLEAPODERADOHABILITADO().hashCode();
        }
        if (getCODREQUISITOSIDENTPJ() != null) {
            _hashCode += getCODREQUISITOSIDENTPJ().hashCode();
        }
        if (getCODREQUISITOSIDENTPF() != null) {
            _hashCode += getCODREQUISITOSIDENTPF().hashCode();
        }
        if (getIDINTEGRADOCLAVE() != null) {
            _hashCode += getIDINTEGRADOCLAVE().hashCode();
        }
        if (getOBSERVACIONINTEGRADOCLAVE() != null) {
            _hashCode += getOBSERVACIONINTEGRADOCLAVE().hashCode();
        }
        if (getVOLUMENESTRAMITACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVOLUMENESTRAMITACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVOLUMENESTRAMITACIONES(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTIEMPOMEDIORESOLUCION() != null) {
            _hashCode += getTIEMPOMEDIORESOLUCION().hashCode();
        }
        if (getVOLUMENNOTIFICACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVOLUMENNOTIFICACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVOLUMENNOTIFICACIONES(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMATERIAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMATERIAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMATERIAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSUBMATERIAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSUBMATERIAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSUBMATERIAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODCLASETRAMITE() != null) {
            _hashCode += getCODCLASETRAMITE().hashCode();
        }
        if (getTRAMITESRELACIONADOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTRAMITESRELACIONADOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTRAMITESRELACIONADOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNOREQUIEREDOCUMENTACION() != null) {
            _hashCode += getNOREQUIEREDOCUMENTACION().hashCode();
        }
        if (getDOCUMENTACION() != null) {
            _hashCode += getDOCUMENTACION().hashCode();
        }
        if (getALTADOCUMENTOSESPECIFICOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getALTADOCUMENTOSESPECIFICOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getALTADOCUMENTOSESPECIFICOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDOCUMENTOSCATALOGO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDOCUMENTOSCATALOGO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDOCUMENTOSCATALOGO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getINICIOS() != null) {
            _hashCode += getINICIOS().hashCode();
        }
        if (getFINVIA() != null) {
            _hashCode += getFINVIA().hashCode();
        }
        if (getPLAZORESOLUCION() != null) {
            _hashCode += getPLAZORESOLUCION().hashCode();
        }
        if (getNORMATIVAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNORMATIVAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNORMATIVAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODIGOORIGEN() != null) {
            _hashCode += getCODIGOORIGEN().hashCode();
        }
        if (getCODIGOACTUACION() != null) {
            _hashCode += getCODIGOACTUACION().hashCode();
        }
        if (getOPERACION() != null) {
            _hashCode += getOPERACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamSIAACTUACIONESACTUACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("CODIGOORIGEN");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CODIGOORIGEN"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("CODIGOACTUACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CODIGOACTUACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("OPERACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OPERACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "INTERNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>INTERNO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESCOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ESCOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ESCOMUN"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ACTIVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ACTIVO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOTRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TIPOTRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>TIPOTRAMITE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULOCIUDADANO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TITULOCIUDADANO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DENOMINACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DENOMINACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DESCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANISMORESPONSABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESTINATARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DESTINATARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">DESTINATARIOS>DESTINATARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DESTINATARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUJETOATASAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SUJETOATASAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>SUJETOATASAS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODICIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "PERIODICIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UNIDADGESTORATRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "UNIDADGESTORATRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOTIFICACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NOTIFICACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">NOTIFICACIONES>NOTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NOTIFICACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODNIVELADMINISTRACIONELECTRONICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "CODNIVELADMINISTRACIONELECTRONICA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SISTEMASIDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SISTEMASIDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SISTEMAIDENTIFICACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANALACCESO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "CANALACCESO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMULARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "FORMULARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">FORMULARIOS>FORMULARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "FORMULARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENLACEWEB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ENLACEWEB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESRESPONSIVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ESRESPONSIVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ESRESPONSIVE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "PORTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUISITOSINICIACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "REQUISITOSINICIACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRESENCIALNOADAPTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "PRESENCIALNOADAPTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>PRESENCIALNOADAPTABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPONIBLEFUNCIONARIOHABILITADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DISPONIBLEFUNCIONARIOHABILITADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>DISPONIBLEFUNCIONARIOHABILITADO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPONIBLEAPODERADOHABILITADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DISPONIBLEAPODERADOHABILITADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>DISPONIBLEAPODERADOHABILITADO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODREQUISITOSIDENTPJ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "CODREQUISITOSIDENTPJ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODREQUISITOSIDENTPF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "CODREQUISITOSIDENTPF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDINTEGRADOCLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "IDINTEGRADOCLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBSERVACIONINTEGRADOCLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "OBSERVACIONINTEGRADOCLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLUMENESTRAMITACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENESTRAMITACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENTRAMITACIONES"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIEMPOMEDIORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TIEMPOMEDIORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLUMENNOTIFICACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENNOTIFICACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENNOTIFICACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "MATERIAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">MATERIAS>MATERIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "MATERIA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUBMATERIAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SUBMATERIAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">SUBMATERIAS>SUBMATERIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SUBMATERIA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODCLASETRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "CODCLASETRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAMITESRELACIONADOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TRAMITESRELACIONADOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TRAMITERELACIONADO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOREQUIEREDOCUMENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NOREQUIEREDOCUMENTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>NOREQUIEREDOCUMENTACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DOCUMENTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTADOCUMENTOSESPECIFICOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ALTADOCUMENTOSESPECIFICOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ALTADOCUMENTOESPECIFICO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTOSCATALOGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DOCUMENTOSCATALOGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DOCUMENTOCATALOGO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INICIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "INICIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "INICIOS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FINVIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "FINVIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>FINVIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLAZORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "PLAZORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "PLAZORESOLUCION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NORMATIVAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NORMATIVAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">NORMATIVAS>NORMATIVA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NORMATIVA"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
