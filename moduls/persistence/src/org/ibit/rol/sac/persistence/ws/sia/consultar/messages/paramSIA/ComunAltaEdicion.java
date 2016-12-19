/**
 * ComunAltaEdicion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA;

public class ComunAltaEdicion  implements java.io.Serializable {
    private java.lang.String TITULOSERVICIO;

    private java.lang.String DESCRIPCIONSERVICIO;

    private java.lang.String CODTIPOLOGIA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO[] AQUIENESVADIRIGIDO;

    private java.lang.String CODPORTAL;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAGRUPACIONSERV AGRUPACIONSERV;

    private java.lang.String DURACIONSERVICIO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionTEMATICASTEMATICA[] TEMATICAS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionHECHOSVITALESHECHOVITAL[] HECHOSVITALES;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionFORMULARIOSFORMULARIO[] FORMULARIOS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO[] MULTILINGUISMOS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSEGUISERVICIO SEGUISERVICIO;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPRESTACIONESPRESTACION[] PRESTACIONES;

    private java.lang.String CODTIPOENCUESTA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION;

    private java.lang.String NIVELMAXIMOTRAMITACION;

    private java.lang.String PROACTIVO;

    private java.lang.String PUBLICACIONSEDE060;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA[] ACTUACIONESRELACIONADAS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionVOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES;

    private java.lang.String REQUISITOS;

    private java.lang.String DOCUMENTACION;

    private java.lang.String COMOSETRAMITA;

    private java.lang.String PRESENTACIONPRESENCIAL;

    private java.lang.String ORGANOINICIA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionINICIOSINICIO[] INICIOS;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPLAZORESOLUCION PLAZORESOLUCION;

    private java.lang.String FINVIA;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionNORMATIVASNORMATIVA[] NORMATIVAS;

    public ComunAltaEdicion() {
    }

    public ComunAltaEdicion(
           java.lang.String TITULOSERVICIO,
           java.lang.String DESCRIPCIONSERVICIO,
           java.lang.String CODTIPOLOGIA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO[] AQUIENESVADIRIGIDO,
           java.lang.String CODPORTAL,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAGRUPACIONSERV AGRUPACIONSERV,
           java.lang.String DURACIONSERVICIO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionTEMATICASTEMATICA[] TEMATICAS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionHECHOSVITALESHECHOVITAL[] HECHOSVITALES,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionFORMULARIOSFORMULARIO[] FORMULARIOS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO[] MULTILINGUISMOS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSEGUISERVICIO SEGUISERVICIO,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPRESTACIONESPRESTACION[] PRESTACIONES,
           java.lang.String CODTIPOENCUESTA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION,
           java.lang.String NIVELMAXIMOTRAMITACION,
           java.lang.String PROACTIVO,
           java.lang.String PUBLICACIONSEDE060,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA[] ACTUACIONESRELACIONADAS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionVOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES,
           java.lang.String REQUISITOS,
           java.lang.String DOCUMENTACION,
           java.lang.String COMOSETRAMITA,
           java.lang.String PRESENTACIONPRESENCIAL,
           java.lang.String ORGANOINICIA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionINICIOSINICIO[] INICIOS,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPLAZORESOLUCION PLAZORESOLUCION,
           java.lang.String FINVIA,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionNORMATIVASNORMATIVA[] NORMATIVAS) {
           this.TITULOSERVICIO = TITULOSERVICIO;
           this.DESCRIPCIONSERVICIO = DESCRIPCIONSERVICIO;
           this.CODTIPOLOGIA = CODTIPOLOGIA;
           this.AQUIENESVADIRIGIDO = AQUIENESVADIRIGIDO;
           this.CODPORTAL = CODPORTAL;
           this.AGRUPACIONSERV = AGRUPACIONSERV;
           this.DURACIONSERVICIO = DURACIONSERVICIO;
           this.TEMATICAS = TEMATICAS;
           this.HECHOSVITALES = HECHOSVITALES;
           this.FORMULARIOS = FORMULARIOS;
           this.MULTILINGUISMOS = MULTILINGUISMOS;
           this.SEGUISERVICIO = SEGUISERVICIO;
           this.PRESTACIONES = PRESTACIONES;
           this.CODTIPOENCUESTA = CODTIPOENCUESTA;
           this.SISTEMASIDENTIFICACION = SISTEMASIDENTIFICACION;
           this.NIVELMAXIMOTRAMITACION = NIVELMAXIMOTRAMITACION;
           this.PROACTIVO = PROACTIVO;
           this.PUBLICACIONSEDE060 = PUBLICACIONSEDE060;
           this.ACTUACIONESRELACIONADAS = ACTUACIONESRELACIONADAS;
           this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
           this.REQUISITOS = REQUISITOS;
           this.DOCUMENTACION = DOCUMENTACION;
           this.COMOSETRAMITA = COMOSETRAMITA;
           this.PRESENTACIONPRESENCIAL = PRESENTACIONPRESENCIAL;
           this.ORGANOINICIA = ORGANOINICIA;
           this.INICIOS = INICIOS;
           this.PLAZORESOLUCION = PLAZORESOLUCION;
           this.FINVIA = FINVIA;
           this.NORMATIVAS = NORMATIVAS;
    }


    /**
     * Gets the TITULOSERVICIO value for this ComunAltaEdicion.
     * 
     * @return TITULOSERVICIO
     */
    public java.lang.String getTITULOSERVICIO() {
        return TITULOSERVICIO;
    }


    /**
     * Sets the TITULOSERVICIO value for this ComunAltaEdicion.
     * 
     * @param TITULOSERVICIO
     */
    public void setTITULOSERVICIO(java.lang.String TITULOSERVICIO) {
        this.TITULOSERVICIO = TITULOSERVICIO;
    }


    /**
     * Gets the DESCRIPCIONSERVICIO value for this ComunAltaEdicion.
     * 
     * @return DESCRIPCIONSERVICIO
     */
    public java.lang.String getDESCRIPCIONSERVICIO() {
        return DESCRIPCIONSERVICIO;
    }


    /**
     * Sets the DESCRIPCIONSERVICIO value for this ComunAltaEdicion.
     * 
     * @param DESCRIPCIONSERVICIO
     */
    public void setDESCRIPCIONSERVICIO(java.lang.String DESCRIPCIONSERVICIO) {
        this.DESCRIPCIONSERVICIO = DESCRIPCIONSERVICIO;
    }


    /**
     * Gets the CODTIPOLOGIA value for this ComunAltaEdicion.
     * 
     * @return CODTIPOLOGIA
     */
    public java.lang.String getCODTIPOLOGIA() {
        return CODTIPOLOGIA;
    }


    /**
     * Sets the CODTIPOLOGIA value for this ComunAltaEdicion.
     * 
     * @param CODTIPOLOGIA
     */
    public void setCODTIPOLOGIA(java.lang.String CODTIPOLOGIA) {
        this.CODTIPOLOGIA = CODTIPOLOGIA;
    }


    /**
     * Gets the AQUIENESVADIRIGIDO value for this ComunAltaEdicion.
     * 
     * @return AQUIENESVADIRIGIDO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO[] getAQUIENESVADIRIGIDO() {
        return AQUIENESVADIRIGIDO;
    }


    /**
     * Sets the AQUIENESVADIRIGIDO value for this ComunAltaEdicion.
     * 
     * @param AQUIENESVADIRIGIDO
     */
    public void setAQUIENESVADIRIGIDO(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO[] AQUIENESVADIRIGIDO) {
        this.AQUIENESVADIRIGIDO = AQUIENESVADIRIGIDO;
    }


    /**
     * Gets the CODPORTAL value for this ComunAltaEdicion.
     * 
     * @return CODPORTAL
     */
    public java.lang.String getCODPORTAL() {
        return CODPORTAL;
    }


    /**
     * Sets the CODPORTAL value for this ComunAltaEdicion.
     * 
     * @param CODPORTAL
     */
    public void setCODPORTAL(java.lang.String CODPORTAL) {
        this.CODPORTAL = CODPORTAL;
    }


    /**
     * Gets the AGRUPACIONSERV value for this ComunAltaEdicion.
     * 
     * @return AGRUPACIONSERV
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAGRUPACIONSERV getAGRUPACIONSERV() {
        return AGRUPACIONSERV;
    }


    /**
     * Sets the AGRUPACIONSERV value for this ComunAltaEdicion.
     * 
     * @param AGRUPACIONSERV
     */
    public void setAGRUPACIONSERV(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionAGRUPACIONSERV AGRUPACIONSERV) {
        this.AGRUPACIONSERV = AGRUPACIONSERV;
    }


    /**
     * Gets the DURACIONSERVICIO value for this ComunAltaEdicion.
     * 
     * @return DURACIONSERVICIO
     */
    public java.lang.String getDURACIONSERVICIO() {
        return DURACIONSERVICIO;
    }


    /**
     * Sets the DURACIONSERVICIO value for this ComunAltaEdicion.
     * 
     * @param DURACIONSERVICIO
     */
    public void setDURACIONSERVICIO(java.lang.String DURACIONSERVICIO) {
        this.DURACIONSERVICIO = DURACIONSERVICIO;
    }


    /**
     * Gets the TEMATICAS value for this ComunAltaEdicion.
     * 
     * @return TEMATICAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionTEMATICASTEMATICA[] getTEMATICAS() {
        return TEMATICAS;
    }


    /**
     * Sets the TEMATICAS value for this ComunAltaEdicion.
     * 
     * @param TEMATICAS
     */
    public void setTEMATICAS(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionTEMATICASTEMATICA[] TEMATICAS) {
        this.TEMATICAS = TEMATICAS;
    }


    /**
     * Gets the HECHOSVITALES value for this ComunAltaEdicion.
     * 
     * @return HECHOSVITALES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionHECHOSVITALESHECHOVITAL[] getHECHOSVITALES() {
        return HECHOSVITALES;
    }


    /**
     * Sets the HECHOSVITALES value for this ComunAltaEdicion.
     * 
     * @param HECHOSVITALES
     */
    public void setHECHOSVITALES(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionHECHOSVITALESHECHOVITAL[] HECHOSVITALES) {
        this.HECHOSVITALES = HECHOSVITALES;
    }


    /**
     * Gets the FORMULARIOS value for this ComunAltaEdicion.
     * 
     * @return FORMULARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionFORMULARIOSFORMULARIO[] getFORMULARIOS() {
        return FORMULARIOS;
    }


    /**
     * Sets the FORMULARIOS value for this ComunAltaEdicion.
     * 
     * @param FORMULARIOS
     */
    public void setFORMULARIOS(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionFORMULARIOSFORMULARIO[] FORMULARIOS) {
        this.FORMULARIOS = FORMULARIOS;
    }


    /**
     * Gets the MULTILINGUISMOS value for this ComunAltaEdicion.
     * 
     * @return MULTILINGUISMOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO[] getMULTILINGUISMOS() {
        return MULTILINGUISMOS;
    }


    /**
     * Sets the MULTILINGUISMOS value for this ComunAltaEdicion.
     * 
     * @param MULTILINGUISMOS
     */
    public void setMULTILINGUISMOS(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO[] MULTILINGUISMOS) {
        this.MULTILINGUISMOS = MULTILINGUISMOS;
    }


    /**
     * Gets the SEGUISERVICIO value for this ComunAltaEdicion.
     * 
     * @return SEGUISERVICIO
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSEGUISERVICIO getSEGUISERVICIO() {
        return SEGUISERVICIO;
    }


    /**
     * Sets the SEGUISERVICIO value for this ComunAltaEdicion.
     * 
     * @param SEGUISERVICIO
     */
    public void setSEGUISERVICIO(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSEGUISERVICIO SEGUISERVICIO) {
        this.SEGUISERVICIO = SEGUISERVICIO;
    }


    /**
     * Gets the PRESTACIONES value for this ComunAltaEdicion.
     * 
     * @return PRESTACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPRESTACIONESPRESTACION[] getPRESTACIONES() {
        return PRESTACIONES;
    }


    /**
     * Sets the PRESTACIONES value for this ComunAltaEdicion.
     * 
     * @param PRESTACIONES
     */
    public void setPRESTACIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPRESTACIONESPRESTACION[] PRESTACIONES) {
        this.PRESTACIONES = PRESTACIONES;
    }


    /**
     * Gets the CODTIPOENCUESTA value for this ComunAltaEdicion.
     * 
     * @return CODTIPOENCUESTA
     */
    public java.lang.String getCODTIPOENCUESTA() {
        return CODTIPOENCUESTA;
    }


    /**
     * Sets the CODTIPOENCUESTA value for this ComunAltaEdicion.
     * 
     * @param CODTIPOENCUESTA
     */
    public void setCODTIPOENCUESTA(java.lang.String CODTIPOENCUESTA) {
        this.CODTIPOENCUESTA = CODTIPOENCUESTA;
    }


    /**
     * Gets the SISTEMASIDENTIFICACION value for this ComunAltaEdicion.
     * 
     * @return SISTEMASIDENTIFICACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] getSISTEMASIDENTIFICACION() {
        return SISTEMASIDENTIFICACION;
    }


    /**
     * Sets the SISTEMASIDENTIFICACION value for this ComunAltaEdicion.
     * 
     * @param SISTEMASIDENTIFICACION
     */
    public void setSISTEMASIDENTIFICACION(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionSISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION) {
        this.SISTEMASIDENTIFICACION = SISTEMASIDENTIFICACION;
    }


    /**
     * Gets the NIVELMAXIMOTRAMITACION value for this ComunAltaEdicion.
     * 
     * @return NIVELMAXIMOTRAMITACION
     */
    public java.lang.String getNIVELMAXIMOTRAMITACION() {
        return NIVELMAXIMOTRAMITACION;
    }


    /**
     * Sets the NIVELMAXIMOTRAMITACION value for this ComunAltaEdicion.
     * 
     * @param NIVELMAXIMOTRAMITACION
     */
    public void setNIVELMAXIMOTRAMITACION(java.lang.String NIVELMAXIMOTRAMITACION) {
        this.NIVELMAXIMOTRAMITACION = NIVELMAXIMOTRAMITACION;
    }


    /**
     * Gets the PROACTIVO value for this ComunAltaEdicion.
     * 
     * @return PROACTIVO
     */
    public java.lang.String getPROACTIVO() {
        return PROACTIVO;
    }


    /**
     * Sets the PROACTIVO value for this ComunAltaEdicion.
     * 
     * @param PROACTIVO
     */
    public void setPROACTIVO(java.lang.String PROACTIVO) {
        this.PROACTIVO = PROACTIVO;
    }


    /**
     * Gets the PUBLICACIONSEDE060 value for this ComunAltaEdicion.
     * 
     * @return PUBLICACIONSEDE060
     */
    public java.lang.String getPUBLICACIONSEDE060() {
        return PUBLICACIONSEDE060;
    }


    /**
     * Sets the PUBLICACIONSEDE060 value for this ComunAltaEdicion.
     * 
     * @param PUBLICACIONSEDE060
     */
    public void setPUBLICACIONSEDE060(java.lang.String PUBLICACIONSEDE060) {
        this.PUBLICACIONSEDE060 = PUBLICACIONSEDE060;
    }


    /**
     * Gets the ACTUACIONESRELACIONADAS value for this ComunAltaEdicion.
     * 
     * @return ACTUACIONESRELACIONADAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA[] getACTUACIONESRELACIONADAS() {
        return ACTUACIONESRELACIONADAS;
    }


    /**
     * Sets the ACTUACIONESRELACIONADAS value for this ComunAltaEdicion.
     * 
     * @param ACTUACIONESRELACIONADAS
     */
    public void setACTUACIONESRELACIONADAS(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA[] ACTUACIONESRELACIONADAS) {
        this.ACTUACIONESRELACIONADAS = ACTUACIONESRELACIONADAS;
    }


    /**
     * Gets the VOLUMENESTRAMITACIONES value for this ComunAltaEdicion.
     * 
     * @return VOLUMENESTRAMITACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionVOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] getVOLUMENESTRAMITACIONES() {
        return VOLUMENESTRAMITACIONES;
    }


    /**
     * Sets the VOLUMENESTRAMITACIONES value for this ComunAltaEdicion.
     * 
     * @param VOLUMENESTRAMITACIONES
     */
    public void setVOLUMENESTRAMITACIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionVOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[] VOLUMENESTRAMITACIONES) {
        this.VOLUMENESTRAMITACIONES = VOLUMENESTRAMITACIONES;
    }


    /**
     * Gets the REQUISITOS value for this ComunAltaEdicion.
     * 
     * @return REQUISITOS
     */
    public java.lang.String getREQUISITOS() {
        return REQUISITOS;
    }


    /**
     * Sets the REQUISITOS value for this ComunAltaEdicion.
     * 
     * @param REQUISITOS
     */
    public void setREQUISITOS(java.lang.String REQUISITOS) {
        this.REQUISITOS = REQUISITOS;
    }


    /**
     * Gets the DOCUMENTACION value for this ComunAltaEdicion.
     * 
     * @return DOCUMENTACION
     */
    public java.lang.String getDOCUMENTACION() {
        return DOCUMENTACION;
    }


    /**
     * Sets the DOCUMENTACION value for this ComunAltaEdicion.
     * 
     * @param DOCUMENTACION
     */
    public void setDOCUMENTACION(java.lang.String DOCUMENTACION) {
        this.DOCUMENTACION = DOCUMENTACION;
    }


    /**
     * Gets the COMOSETRAMITA value for this ComunAltaEdicion.
     * 
     * @return COMOSETRAMITA
     */
    public java.lang.String getCOMOSETRAMITA() {
        return COMOSETRAMITA;
    }


    /**
     * Sets the COMOSETRAMITA value for this ComunAltaEdicion.
     * 
     * @param COMOSETRAMITA
     */
    public void setCOMOSETRAMITA(java.lang.String COMOSETRAMITA) {
        this.COMOSETRAMITA = COMOSETRAMITA;
    }


    /**
     * Gets the PRESENTACIONPRESENCIAL value for this ComunAltaEdicion.
     * 
     * @return PRESENTACIONPRESENCIAL
     */
    public java.lang.String getPRESENTACIONPRESENCIAL() {
        return PRESENTACIONPRESENCIAL;
    }


    /**
     * Sets the PRESENTACIONPRESENCIAL value for this ComunAltaEdicion.
     * 
     * @param PRESENTACIONPRESENCIAL
     */
    public void setPRESENTACIONPRESENCIAL(java.lang.String PRESENTACIONPRESENCIAL) {
        this.PRESENTACIONPRESENCIAL = PRESENTACIONPRESENCIAL;
    }


    /**
     * Gets the ORGANOINICIA value for this ComunAltaEdicion.
     * 
     * @return ORGANOINICIA
     */
    public java.lang.String getORGANOINICIA() {
        return ORGANOINICIA;
    }


    /**
     * Sets the ORGANOINICIA value for this ComunAltaEdicion.
     * 
     * @param ORGANOINICIA
     */
    public void setORGANOINICIA(java.lang.String ORGANOINICIA) {
        this.ORGANOINICIA = ORGANOINICIA;
    }


    /**
     * Gets the INICIOS value for this ComunAltaEdicion.
     * 
     * @return INICIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionINICIOSINICIO[] getINICIOS() {
        return INICIOS;
    }


    /**
     * Sets the INICIOS value for this ComunAltaEdicion.
     * 
     * @param INICIOS
     */
    public void setINICIOS(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionINICIOSINICIO[] INICIOS) {
        this.INICIOS = INICIOS;
    }


    /**
     * Gets the PLAZORESOLUCION value for this ComunAltaEdicion.
     * 
     * @return PLAZORESOLUCION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPLAZORESOLUCION getPLAZORESOLUCION() {
        return PLAZORESOLUCION;
    }


    /**
     * Sets the PLAZORESOLUCION value for this ComunAltaEdicion.
     * 
     * @param PLAZORESOLUCION
     */
    public void setPLAZORESOLUCION(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionPLAZORESOLUCION PLAZORESOLUCION) {
        this.PLAZORESOLUCION = PLAZORESOLUCION;
    }


    /**
     * Gets the FINVIA value for this ComunAltaEdicion.
     * 
     * @return FINVIA
     */
    public java.lang.String getFINVIA() {
        return FINVIA;
    }


    /**
     * Sets the FINVIA value for this ComunAltaEdicion.
     * 
     * @param FINVIA
     */
    public void setFINVIA(java.lang.String FINVIA) {
        this.FINVIA = FINVIA;
    }


    /**
     * Gets the NORMATIVAS value for this ComunAltaEdicion.
     * 
     * @return NORMATIVAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionNORMATIVASNORMATIVA[] getNORMATIVAS() {
        return NORMATIVAS;
    }


    /**
     * Sets the NORMATIVAS value for this ComunAltaEdicion.
     * 
     * @param NORMATIVAS
     */
    public void setNORMATIVAS(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicionNORMATIVASNORMATIVA[] NORMATIVAS) {
        this.NORMATIVAS = NORMATIVAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComunAltaEdicion)) return false;
        ComunAltaEdicion other = (ComunAltaEdicion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TITULOSERVICIO==null && other.getTITULOSERVICIO()==null) || 
             (this.TITULOSERVICIO!=null &&
              this.TITULOSERVICIO.equals(other.getTITULOSERVICIO()))) &&
            ((this.DESCRIPCIONSERVICIO==null && other.getDESCRIPCIONSERVICIO()==null) || 
             (this.DESCRIPCIONSERVICIO!=null &&
              this.DESCRIPCIONSERVICIO.equals(other.getDESCRIPCIONSERVICIO()))) &&
            ((this.CODTIPOLOGIA==null && other.getCODTIPOLOGIA()==null) || 
             (this.CODTIPOLOGIA!=null &&
              this.CODTIPOLOGIA.equals(other.getCODTIPOLOGIA()))) &&
            ((this.AQUIENESVADIRIGIDO==null && other.getAQUIENESVADIRIGIDO()==null) || 
             (this.AQUIENESVADIRIGIDO!=null &&
              java.util.Arrays.equals(this.AQUIENESVADIRIGIDO, other.getAQUIENESVADIRIGIDO()))) &&
            ((this.CODPORTAL==null && other.getCODPORTAL()==null) || 
             (this.CODPORTAL!=null &&
              this.CODPORTAL.equals(other.getCODPORTAL()))) &&
            ((this.AGRUPACIONSERV==null && other.getAGRUPACIONSERV()==null) || 
             (this.AGRUPACIONSERV!=null &&
              this.AGRUPACIONSERV.equals(other.getAGRUPACIONSERV()))) &&
            ((this.DURACIONSERVICIO==null && other.getDURACIONSERVICIO()==null) || 
             (this.DURACIONSERVICIO!=null &&
              this.DURACIONSERVICIO.equals(other.getDURACIONSERVICIO()))) &&
            ((this.TEMATICAS==null && other.getTEMATICAS()==null) || 
             (this.TEMATICAS!=null &&
              java.util.Arrays.equals(this.TEMATICAS, other.getTEMATICAS()))) &&
            ((this.HECHOSVITALES==null && other.getHECHOSVITALES()==null) || 
             (this.HECHOSVITALES!=null &&
              java.util.Arrays.equals(this.HECHOSVITALES, other.getHECHOSVITALES()))) &&
            ((this.FORMULARIOS==null && other.getFORMULARIOS()==null) || 
             (this.FORMULARIOS!=null &&
              java.util.Arrays.equals(this.FORMULARIOS, other.getFORMULARIOS()))) &&
            ((this.MULTILINGUISMOS==null && other.getMULTILINGUISMOS()==null) || 
             (this.MULTILINGUISMOS!=null &&
              java.util.Arrays.equals(this.MULTILINGUISMOS, other.getMULTILINGUISMOS()))) &&
            ((this.SEGUISERVICIO==null && other.getSEGUISERVICIO()==null) || 
             (this.SEGUISERVICIO!=null &&
              this.SEGUISERVICIO.equals(other.getSEGUISERVICIO()))) &&
            ((this.PRESTACIONES==null && other.getPRESTACIONES()==null) || 
             (this.PRESTACIONES!=null &&
              java.util.Arrays.equals(this.PRESTACIONES, other.getPRESTACIONES()))) &&
            ((this.CODTIPOENCUESTA==null && other.getCODTIPOENCUESTA()==null) || 
             (this.CODTIPOENCUESTA!=null &&
              this.CODTIPOENCUESTA.equals(other.getCODTIPOENCUESTA()))) &&
            ((this.SISTEMASIDENTIFICACION==null && other.getSISTEMASIDENTIFICACION()==null) || 
             (this.SISTEMASIDENTIFICACION!=null &&
              java.util.Arrays.equals(this.SISTEMASIDENTIFICACION, other.getSISTEMASIDENTIFICACION()))) &&
            ((this.NIVELMAXIMOTRAMITACION==null && other.getNIVELMAXIMOTRAMITACION()==null) || 
             (this.NIVELMAXIMOTRAMITACION!=null &&
              this.NIVELMAXIMOTRAMITACION.equals(other.getNIVELMAXIMOTRAMITACION()))) &&
            ((this.PROACTIVO==null && other.getPROACTIVO()==null) || 
             (this.PROACTIVO!=null &&
              this.PROACTIVO.equals(other.getPROACTIVO()))) &&
            ((this.PUBLICACIONSEDE060==null && other.getPUBLICACIONSEDE060()==null) || 
             (this.PUBLICACIONSEDE060!=null &&
              this.PUBLICACIONSEDE060.equals(other.getPUBLICACIONSEDE060()))) &&
            ((this.ACTUACIONESRELACIONADAS==null && other.getACTUACIONESRELACIONADAS()==null) || 
             (this.ACTUACIONESRELACIONADAS!=null &&
              java.util.Arrays.equals(this.ACTUACIONESRELACIONADAS, other.getACTUACIONESRELACIONADAS()))) &&
            ((this.VOLUMENESTRAMITACIONES==null && other.getVOLUMENESTRAMITACIONES()==null) || 
             (this.VOLUMENESTRAMITACIONES!=null &&
              java.util.Arrays.equals(this.VOLUMENESTRAMITACIONES, other.getVOLUMENESTRAMITACIONES()))) &&
            ((this.REQUISITOS==null && other.getREQUISITOS()==null) || 
             (this.REQUISITOS!=null &&
              this.REQUISITOS.equals(other.getREQUISITOS()))) &&
            ((this.DOCUMENTACION==null && other.getDOCUMENTACION()==null) || 
             (this.DOCUMENTACION!=null &&
              this.DOCUMENTACION.equals(other.getDOCUMENTACION()))) &&
            ((this.COMOSETRAMITA==null && other.getCOMOSETRAMITA()==null) || 
             (this.COMOSETRAMITA!=null &&
              this.COMOSETRAMITA.equals(other.getCOMOSETRAMITA()))) &&
            ((this.PRESENTACIONPRESENCIAL==null && other.getPRESENTACIONPRESENCIAL()==null) || 
             (this.PRESENTACIONPRESENCIAL!=null &&
              this.PRESENTACIONPRESENCIAL.equals(other.getPRESENTACIONPRESENCIAL()))) &&
            ((this.ORGANOINICIA==null && other.getORGANOINICIA()==null) || 
             (this.ORGANOINICIA!=null &&
              this.ORGANOINICIA.equals(other.getORGANOINICIA()))) &&
            ((this.INICIOS==null && other.getINICIOS()==null) || 
             (this.INICIOS!=null &&
              java.util.Arrays.equals(this.INICIOS, other.getINICIOS()))) &&
            ((this.PLAZORESOLUCION==null && other.getPLAZORESOLUCION()==null) || 
             (this.PLAZORESOLUCION!=null &&
              this.PLAZORESOLUCION.equals(other.getPLAZORESOLUCION()))) &&
            ((this.FINVIA==null && other.getFINVIA()==null) || 
             (this.FINVIA!=null &&
              this.FINVIA.equals(other.getFINVIA()))) &&
            ((this.NORMATIVAS==null && other.getNORMATIVAS()==null) || 
             (this.NORMATIVAS!=null &&
              java.util.Arrays.equals(this.NORMATIVAS, other.getNORMATIVAS())));
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
        if (getTITULOSERVICIO() != null) {
            _hashCode += getTITULOSERVICIO().hashCode();
        }
        if (getDESCRIPCIONSERVICIO() != null) {
            _hashCode += getDESCRIPCIONSERVICIO().hashCode();
        }
        if (getCODTIPOLOGIA() != null) {
            _hashCode += getCODTIPOLOGIA().hashCode();
        }
        if (getAQUIENESVADIRIGIDO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAQUIENESVADIRIGIDO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAQUIENESVADIRIGIDO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODPORTAL() != null) {
            _hashCode += getCODPORTAL().hashCode();
        }
        if (getAGRUPACIONSERV() != null) {
            _hashCode += getAGRUPACIONSERV().hashCode();
        }
        if (getDURACIONSERVICIO() != null) {
            _hashCode += getDURACIONSERVICIO().hashCode();
        }
        if (getTEMATICAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTEMATICAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTEMATICAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHECHOSVITALES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHECHOSVITALES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHECHOSVITALES(), i);
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
        if (getMULTILINGUISMOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMULTILINGUISMOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMULTILINGUISMOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSEGUISERVICIO() != null) {
            _hashCode += getSEGUISERVICIO().hashCode();
        }
        if (getPRESTACIONES() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPRESTACIONES());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPRESTACIONES(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODTIPOENCUESTA() != null) {
            _hashCode += getCODTIPOENCUESTA().hashCode();
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
        if (getNIVELMAXIMOTRAMITACION() != null) {
            _hashCode += getNIVELMAXIMOTRAMITACION().hashCode();
        }
        if (getPROACTIVO() != null) {
            _hashCode += getPROACTIVO().hashCode();
        }
        if (getPUBLICACIONSEDE060() != null) {
            _hashCode += getPUBLICACIONSEDE060().hashCode();
        }
        if (getACTUACIONESRELACIONADAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getACTUACIONESRELACIONADAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getACTUACIONESRELACIONADAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getREQUISITOS() != null) {
            _hashCode += getREQUISITOS().hashCode();
        }
        if (getDOCUMENTACION() != null) {
            _hashCode += getDOCUMENTACION().hashCode();
        }
        if (getCOMOSETRAMITA() != null) {
            _hashCode += getCOMOSETRAMITA().hashCode();
        }
        if (getPRESENTACIONPRESENCIAL() != null) {
            _hashCode += getPRESENTACIONPRESENCIAL().hashCode();
        }
        if (getORGANOINICIA() != null) {
            _hashCode += getORGANOINICIA().hashCode();
        }
        if (getINICIOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getINICIOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getINICIOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPLAZORESOLUCION() != null) {
            _hashCode += getPLAZORESOLUCION().hashCode();
        }
        if (getFINVIA() != null) {
            _hashCode += getFINVIA().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComunAltaEdicion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "comunAltaEdicion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULOSERVICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "TITULOSERVICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCIONSERVICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "DESCRIPCIONSERVICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPOLOGIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODTIPOLOGIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AQUIENESVADIRIGIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "AQUIENESVADIRIGIDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>AQUIENESVADIRIGIDO>AQUIENVADIRIGIDO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "AQUIENVADIRIGIDO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODPORTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODPORTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGRUPACIONSERV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "AGRUPACIONSERV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">comunAltaEdicion>AGRUPACIONSERV"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DURACIONSERVICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "DURACIONSERVICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TEMATICAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "TEMATICAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>TEMATICAS>TEMATICA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "TEMATICA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HECHOSVITALES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "HECHOSVITALES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>HECHOSVITALES>HECHOVITAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "HECHOVITAL"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMULARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "FORMULARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>FORMULARIOS>FORMULARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "FORMULARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MULTILINGUISMOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "MULTILINGUISMOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>MULTILINGUISMOS>MULTILINGUISMO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "MULTILINGUISMO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGUISERVICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "SEGUISERVICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">comunAltaEdicion>SEGUISERVICIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRESTACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "PRESTACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>PRESTACIONES>PRESTACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "PRESTACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODTIPOENCUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODTIPOENCUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SISTEMASIDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "SISTEMASIDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "SISTEMAIDENTIFICACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NIVELMAXIMOTRAMITACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NIVELMAXIMOTRAMITACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROACTIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "PROACTIVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUBLICACIONSEDE060");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "PUBLICACIONSEDE060"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTUACIONESRELACIONADAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "ACTUACIONESRELACIONADAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>ACTUACIONESRELACIONADAS>ACTUACIONRELACIONADA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "ACTUACIONRELACIONADA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VOLUMENESTRAMITACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "VOLUMENESTRAMITACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "VOLUMENTRAMITACIONES"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUISITOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "REQUISITOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "DOCUMENTACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMOSETRAMITA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "COMOSETRAMITA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRESENTACIONPRESENCIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "PRESENTACIONPRESENCIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANOINICIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "ORGANOINICIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INICIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "INICIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>INICIOS>INICIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "INICIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLAZORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "PLAZORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">comunAltaEdicion>PLAZORESOLUCION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FINVIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "FINVIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NORMATIVAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NORMATIVAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>comunAltaEdicion>NORMATIVAS>NORMATIVA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NORMATIVA"));
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
