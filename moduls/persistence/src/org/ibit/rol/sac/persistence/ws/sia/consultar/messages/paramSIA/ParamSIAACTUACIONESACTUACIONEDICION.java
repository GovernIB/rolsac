/**
 * ParamSIAACTUACIONESACTUACIONEDICION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA;

public class ParamSIAACTUACIONESACTUACIONEDICION  implements java.io.Serializable {
    private java.lang.String TIPOACTUACION;

    private java.lang.String DENOMINACION;

    private java.lang.String DESCRIPCION;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.OrganismoResponsable ORGANISMORESPONSABLE;

    private java.lang.String CODNIVELADMINISTRACIONELECTRONICA;

    private java.lang.String URL;

    private java.lang.String CODREQUISITOSIDENTPJ;

    private java.lang.String CODREQUISITOSIDENTPF;

    private java.lang.String IDINTEGRADOCLAVE;

    private java.lang.String OBSERVACIONINTEGRADOCLAVE;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES;

    private org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicion COMUNALTAEDICION;

    private java.lang.String OPERACION;  // attribute

    public ParamSIAACTUACIONESACTUACIONEDICION() {
    }

    public ParamSIAACTUACIONESACTUACIONEDICION(
           java.lang.String TIPOACTUACION,
           java.lang.String DENOMINACION,
           java.lang.String DESCRIPCION,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.OrganismoResponsable ORGANISMORESPONSABLE,
           java.lang.String CODNIVELADMINISTRACIONELECTRONICA,
           java.lang.String URL,
           java.lang.String CODREQUISITOSIDENTPJ,
           java.lang.String CODREQUISITOSIDENTPF,
           java.lang.String IDINTEGRADOCLAVE,
           java.lang.String OBSERVACIONINTEGRADOCLAVE,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES,
           org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicion COMUNALTAEDICION,
           java.lang.String OPERACION) {
           this.TIPOACTUACION = TIPOACTUACION;
           this.DENOMINACION = DENOMINACION;
           this.DESCRIPCION = DESCRIPCION;
           this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
           this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
           this.URL = URL;
           this.CODREQUISITOSIDENTPJ = CODREQUISITOSIDENTPJ;
           this.CODREQUISITOSIDENTPF = CODREQUISITOSIDENTPF;
           this.IDINTEGRADOCLAVE = IDINTEGRADOCLAVE;
           this.OBSERVACIONINTEGRADOCLAVE = OBSERVACIONINTEGRADOCLAVE;
           this.NOTIFICACIONES = NOTIFICACIONES;
           this.COMUNALTAEDICION = COMUNALTAEDICION;
           this.OPERACION = OPERACION;
    }


    /**
     * Gets the TIPOACTUACION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return TIPOACTUACION
     */
    public java.lang.String getTIPOACTUACION() {
        return TIPOACTUACION;
    }


    /**
     * Sets the TIPOACTUACION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param TIPOACTUACION
     */
    public void setTIPOACTUACION(java.lang.String TIPOACTUACION) {
        this.TIPOACTUACION = TIPOACTUACION;
    }


    /**
     * Gets the DENOMINACION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return DENOMINACION
     */
    public java.lang.String getDENOMINACION() {
        return DENOMINACION;
    }


    /**
     * Sets the DENOMINACION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param DENOMINACION
     */
    public void setDENOMINACION(java.lang.String DENOMINACION) {
        this.DENOMINACION = DENOMINACION;
    }


    /**
     * Gets the DESCRIPCION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the ORGANISMORESPONSABLE value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return ORGANISMORESPONSABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.OrganismoResponsable getORGANISMORESPONSABLE() {
        return ORGANISMORESPONSABLE;
    }


    /**
     * Sets the ORGANISMORESPONSABLE value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param ORGANISMORESPONSABLE
     */
    public void setORGANISMORESPONSABLE(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.OrganismoResponsable ORGANISMORESPONSABLE) {
        this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
    }


    /**
     * Gets the CODNIVELADMINISTRACIONELECTRONICA value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return CODNIVELADMINISTRACIONELECTRONICA
     */
    public java.lang.String getCODNIVELADMINISTRACIONELECTRONICA() {
        return CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Sets the CODNIVELADMINISTRACIONELECTRONICA value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param CODNIVELADMINISTRACIONELECTRONICA
     */
    public void setCODNIVELADMINISTRACIONELECTRONICA(java.lang.String CODNIVELADMINISTRACIONELECTRONICA) {
        this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Gets the URL value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }


    /**
     * Gets the CODREQUISITOSIDENTPJ value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return CODREQUISITOSIDENTPJ
     */
    public java.lang.String getCODREQUISITOSIDENTPJ() {
        return CODREQUISITOSIDENTPJ;
    }


    /**
     * Sets the CODREQUISITOSIDENTPJ value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param CODREQUISITOSIDENTPJ
     */
    public void setCODREQUISITOSIDENTPJ(java.lang.String CODREQUISITOSIDENTPJ) {
        this.CODREQUISITOSIDENTPJ = CODREQUISITOSIDENTPJ;
    }


    /**
     * Gets the CODREQUISITOSIDENTPF value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return CODREQUISITOSIDENTPF
     */
    public java.lang.String getCODREQUISITOSIDENTPF() {
        return CODREQUISITOSIDENTPF;
    }


    /**
     * Sets the CODREQUISITOSIDENTPF value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param CODREQUISITOSIDENTPF
     */
    public void setCODREQUISITOSIDENTPF(java.lang.String CODREQUISITOSIDENTPF) {
        this.CODREQUISITOSIDENTPF = CODREQUISITOSIDENTPF;
    }


    /**
     * Gets the IDINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return IDINTEGRADOCLAVE
     */
    public java.lang.String getIDINTEGRADOCLAVE() {
        return IDINTEGRADOCLAVE;
    }


    /**
     * Sets the IDINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param IDINTEGRADOCLAVE
     */
    public void setIDINTEGRADOCLAVE(java.lang.String IDINTEGRADOCLAVE) {
        this.IDINTEGRADOCLAVE = IDINTEGRADOCLAVE;
    }


    /**
     * Gets the OBSERVACIONINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return OBSERVACIONINTEGRADOCLAVE
     */
    public java.lang.String getOBSERVACIONINTEGRADOCLAVE() {
        return OBSERVACIONINTEGRADOCLAVE;
    }


    /**
     * Sets the OBSERVACIONINTEGRADOCLAVE value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param OBSERVACIONINTEGRADOCLAVE
     */
    public void setOBSERVACIONINTEGRADOCLAVE(java.lang.String OBSERVACIONINTEGRADOCLAVE) {
        this.OBSERVACIONINTEGRADOCLAVE = OBSERVACIONINTEGRADOCLAVE;
    }


    /**
     * Gets the NOTIFICACIONES value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return NOTIFICACIONES
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.NOTIFICACIONESNOTIFICACION[] getNOTIFICACIONES() {
        return NOTIFICACIONES;
    }


    /**
     * Sets the NOTIFICACIONES value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param NOTIFICACIONES
     */
    public void setNOTIFICACIONES(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.NOTIFICACIONESNOTIFICACION[] NOTIFICACIONES) {
        this.NOTIFICACIONES = NOTIFICACIONES;
    }


    /**
     * Gets the COMUNALTAEDICION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return COMUNALTAEDICION
     */
    public org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicion getCOMUNALTAEDICION() {
        return COMUNALTAEDICION;
    }


    /**
     * Sets the COMUNALTAEDICION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param COMUNALTAEDICION
     */
    public void setCOMUNALTAEDICION(org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ComunAltaEdicion COMUNALTAEDICION) {
        this.COMUNALTAEDICION = COMUNALTAEDICION;
    }


    /**
     * Gets the OPERACION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @return OPERACION
     */
    public java.lang.String getOPERACION() {
        return OPERACION;
    }


    /**
     * Sets the OPERACION value for this ParamSIAACTUACIONESACTUACIONEDICION.
     * 
     * @param OPERACION
     */
    public void setOPERACION(java.lang.String OPERACION) {
        this.OPERACION = OPERACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamSIAACTUACIONESACTUACIONEDICION)) return false;
        ParamSIAACTUACIONESACTUACIONEDICION other = (ParamSIAACTUACIONESACTUACIONEDICION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TIPOACTUACION==null && other.getTIPOACTUACION()==null) || 
             (this.TIPOACTUACION!=null &&
              this.TIPOACTUACION.equals(other.getTIPOACTUACION()))) &&
            ((this.DENOMINACION==null && other.getDENOMINACION()==null) || 
             (this.DENOMINACION!=null &&
              this.DENOMINACION.equals(other.getDENOMINACION()))) &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.ORGANISMORESPONSABLE==null && other.getORGANISMORESPONSABLE()==null) || 
             (this.ORGANISMORESPONSABLE!=null &&
              this.ORGANISMORESPONSABLE.equals(other.getORGANISMORESPONSABLE()))) &&
            ((this.CODNIVELADMINISTRACIONELECTRONICA==null && other.getCODNIVELADMINISTRACIONELECTRONICA()==null) || 
             (this.CODNIVELADMINISTRACIONELECTRONICA!=null &&
              this.CODNIVELADMINISTRACIONELECTRONICA.equals(other.getCODNIVELADMINISTRACIONELECTRONICA()))) &&
            ((this.URL==null && other.getURL()==null) || 
             (this.URL!=null &&
              this.URL.equals(other.getURL()))) &&
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
            ((this.NOTIFICACIONES==null && other.getNOTIFICACIONES()==null) || 
             (this.NOTIFICACIONES!=null &&
              java.util.Arrays.equals(this.NOTIFICACIONES, other.getNOTIFICACIONES()))) &&
            ((this.COMUNALTAEDICION==null && other.getCOMUNALTAEDICION()==null) || 
             (this.COMUNALTAEDICION!=null &&
              this.COMUNALTAEDICION.equals(other.getCOMUNALTAEDICION()))) &&
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
        if (getTIPOACTUACION() != null) {
            _hashCode += getTIPOACTUACION().hashCode();
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
        if (getCODNIVELADMINISTRACIONELECTRONICA() != null) {
            _hashCode += getCODNIVELADMINISTRACIONELECTRONICA().hashCode();
        }
        if (getURL() != null) {
            _hashCode += getURL().hashCode();
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
        if (getCOMUNALTAEDICION() != null) {
            _hashCode += getCOMUNALTAEDICION().hashCode();
        }
        if (getOPERACION() != null) {
            _hashCode += getOPERACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamSIAACTUACIONESACTUACIONEDICION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>EDICION"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("OPERACION");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OPERACION"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOACTUACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "TIPOACTUACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DENOMINACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "DENOMINACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "DESCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANISMORESPONSABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "organismoResponsable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODNIVELADMINISTRACIONELECTRONICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODNIVELADMINISTRACIONELECTRONICA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "URL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODREQUISITOSIDENTPJ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODREQUISITOSIDENTPJ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODREQUISITOSIDENTPF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "CODREQUISITOSIDENTPF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDINTEGRADOCLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "IDINTEGRADOCLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBSERVACIONINTEGRADOCLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "OBSERVACIONINTEGRADOCLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOTIFICACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NOTIFICACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", ">NOTIFICACIONES>NOTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "NOTIFICACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMUNALTAEDICION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "COMUNALTAEDICION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/consultar/messages/ParamSIA", "comunAltaEdicion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
