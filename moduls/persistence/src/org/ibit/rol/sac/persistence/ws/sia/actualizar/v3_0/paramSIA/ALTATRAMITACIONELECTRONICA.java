/**
 * ALTATRAMITACIONELECTRONICA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class ALTATRAMITACIONELECTRONICA  implements java.io.Serializable {
    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION;

    private java.lang.String[] CANALACCESO;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.FORMULARIOSFORMULARIO[] FORMULARIOS;

    private java.lang.String PORTAL;

    private java.lang.String REQUISITOSINICIACION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTATRAMITACIONELECTRONICAPRESENCIALNOADAPTABLE PRESENCIALNOADAPTABLE;

    private java.lang.String CODREQUISITOSIDENTPJ;

    private java.lang.String CODREQUISITOSIDENTPF;

    private java.lang.String IDINTEGRADOCLAVE;

    private java.lang.String OBSERVACIONINTEGRADOCLAVE;

    public ALTATRAMITACIONELECTRONICA() {
    }

    public ALTATRAMITACIONELECTRONICA(
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION,
           java.lang.String[] CANALACCESO,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.FORMULARIOSFORMULARIO[] FORMULARIOS,
           java.lang.String PORTAL,
           java.lang.String REQUISITOSINICIACION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTATRAMITACIONELECTRONICAPRESENCIALNOADAPTABLE PRESENCIALNOADAPTABLE,
           java.lang.String CODREQUISITOSIDENTPJ,
           java.lang.String CODREQUISITOSIDENTPF,
           java.lang.String IDINTEGRADOCLAVE,
           java.lang.String OBSERVACIONINTEGRADOCLAVE) {
           this.SISTEMASIDENTIFICACION = SISTEMASIDENTIFICACION;
           this.CANALACCESO = CANALACCESO;
           this.FORMULARIOS = FORMULARIOS;
           this.PORTAL = PORTAL;
           this.REQUISITOSINICIACION = REQUISITOSINICIACION;
           this.PRESENCIALNOADAPTABLE = PRESENCIALNOADAPTABLE;
           this.CODREQUISITOSIDENTPJ = CODREQUISITOSIDENTPJ;
           this.CODREQUISITOSIDENTPF = CODREQUISITOSIDENTPF;
           this.IDINTEGRADOCLAVE = IDINTEGRADOCLAVE;
           this.OBSERVACIONINTEGRADOCLAVE = OBSERVACIONINTEGRADOCLAVE;
    }


    /**
     * Gets the SISTEMASIDENTIFICACION value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return SISTEMASIDENTIFICACION
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] getSISTEMASIDENTIFICACION() {
        return SISTEMASIDENTIFICACION;
    }


    /**
     * Sets the SISTEMASIDENTIFICACION value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param SISTEMASIDENTIFICACION
     */
    public void setSISTEMASIDENTIFICACION(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[] SISTEMASIDENTIFICACION) {
        this.SISTEMASIDENTIFICACION = SISTEMASIDENTIFICACION;
    }


    /**
     * Gets the CANALACCESO value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return CANALACCESO
     */
    public java.lang.String[] getCANALACCESO() {
        return CANALACCESO;
    }


    /**
     * Sets the CANALACCESO value for this ALTATRAMITACIONELECTRONICA.
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
     * Gets the FORMULARIOS value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return FORMULARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.FORMULARIOSFORMULARIO[] getFORMULARIOS() {
        return FORMULARIOS;
    }


    /**
     * Sets the FORMULARIOS value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param FORMULARIOS
     */
    public void setFORMULARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.FORMULARIOSFORMULARIO[] FORMULARIOS) {
        this.FORMULARIOS = FORMULARIOS;
    }


    /**
     * Gets the PORTAL value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return PORTAL
     */
    public java.lang.String getPORTAL() {
        return PORTAL;
    }


    /**
     * Sets the PORTAL value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param PORTAL
     */
    public void setPORTAL(java.lang.String PORTAL) {
        this.PORTAL = PORTAL;
    }


    /**
     * Gets the REQUISITOSINICIACION value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return REQUISITOSINICIACION
     */
    public java.lang.String getREQUISITOSINICIACION() {
        return REQUISITOSINICIACION;
    }


    /**
     * Sets the REQUISITOSINICIACION value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param REQUISITOSINICIACION
     */
    public void setREQUISITOSINICIACION(java.lang.String REQUISITOSINICIACION) {
        this.REQUISITOSINICIACION = REQUISITOSINICIACION;
    }


    /**
     * Gets the PRESENCIALNOADAPTABLE value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return PRESENCIALNOADAPTABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTATRAMITACIONELECTRONICAPRESENCIALNOADAPTABLE getPRESENCIALNOADAPTABLE() {
        return PRESENCIALNOADAPTABLE;
    }


    /**
     * Sets the PRESENCIALNOADAPTABLE value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param PRESENCIALNOADAPTABLE
     */
    public void setPRESENCIALNOADAPTABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTATRAMITACIONELECTRONICAPRESENCIALNOADAPTABLE PRESENCIALNOADAPTABLE) {
        this.PRESENCIALNOADAPTABLE = PRESENCIALNOADAPTABLE;
    }


    /**
     * Gets the CODREQUISITOSIDENTPJ value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return CODREQUISITOSIDENTPJ
     */
    public java.lang.String getCODREQUISITOSIDENTPJ() {
        return CODREQUISITOSIDENTPJ;
    }


    /**
     * Sets the CODREQUISITOSIDENTPJ value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param CODREQUISITOSIDENTPJ
     */
    public void setCODREQUISITOSIDENTPJ(java.lang.String CODREQUISITOSIDENTPJ) {
        this.CODREQUISITOSIDENTPJ = CODREQUISITOSIDENTPJ;
    }


    /**
     * Gets the CODREQUISITOSIDENTPF value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return CODREQUISITOSIDENTPF
     */
    public java.lang.String getCODREQUISITOSIDENTPF() {
        return CODREQUISITOSIDENTPF;
    }


    /**
     * Sets the CODREQUISITOSIDENTPF value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param CODREQUISITOSIDENTPF
     */
    public void setCODREQUISITOSIDENTPF(java.lang.String CODREQUISITOSIDENTPF) {
        this.CODREQUISITOSIDENTPF = CODREQUISITOSIDENTPF;
    }


    /**
     * Gets the IDINTEGRADOCLAVE value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return IDINTEGRADOCLAVE
     */
    public java.lang.String getIDINTEGRADOCLAVE() {
        return IDINTEGRADOCLAVE;
    }


    /**
     * Sets the IDINTEGRADOCLAVE value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param IDINTEGRADOCLAVE
     */
    public void setIDINTEGRADOCLAVE(java.lang.String IDINTEGRADOCLAVE) {
        this.IDINTEGRADOCLAVE = IDINTEGRADOCLAVE;
    }


    /**
     * Gets the OBSERVACIONINTEGRADOCLAVE value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @return OBSERVACIONINTEGRADOCLAVE
     */
    public java.lang.String getOBSERVACIONINTEGRADOCLAVE() {
        return OBSERVACIONINTEGRADOCLAVE;
    }


    /**
     * Sets the OBSERVACIONINTEGRADOCLAVE value for this ALTATRAMITACIONELECTRONICA.
     * 
     * @param OBSERVACIONINTEGRADOCLAVE
     */
    public void setOBSERVACIONINTEGRADOCLAVE(java.lang.String OBSERVACIONINTEGRADOCLAVE) {
        this.OBSERVACIONINTEGRADOCLAVE = OBSERVACIONINTEGRADOCLAVE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ALTATRAMITACIONELECTRONICA)) return false;
        ALTATRAMITACIONELECTRONICA other = (ALTATRAMITACIONELECTRONICA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SISTEMASIDENTIFICACION==null && other.getSISTEMASIDENTIFICACION()==null) || 
             (this.SISTEMASIDENTIFICACION!=null &&
              java.util.Arrays.equals(this.SISTEMASIDENTIFICACION, other.getSISTEMASIDENTIFICACION()))) &&
            ((this.CANALACCESO==null && other.getCANALACCESO()==null) || 
             (this.CANALACCESO!=null &&
              java.util.Arrays.equals(this.CANALACCESO, other.getCANALACCESO()))) &&
            ((this.FORMULARIOS==null && other.getFORMULARIOS()==null) || 
             (this.FORMULARIOS!=null &&
              java.util.Arrays.equals(this.FORMULARIOS, other.getFORMULARIOS()))) &&
            ((this.PORTAL==null && other.getPORTAL()==null) || 
             (this.PORTAL!=null &&
              this.PORTAL.equals(other.getPORTAL()))) &&
            ((this.REQUISITOSINICIACION==null && other.getREQUISITOSINICIACION()==null) || 
             (this.REQUISITOSINICIACION!=null &&
              this.REQUISITOSINICIACION.equals(other.getREQUISITOSINICIACION()))) &&
            ((this.PRESENCIALNOADAPTABLE==null && other.getPRESENCIALNOADAPTABLE()==null) || 
             (this.PRESENCIALNOADAPTABLE!=null &&
              this.PRESENCIALNOADAPTABLE.equals(other.getPRESENCIALNOADAPTABLE()))) &&
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
              this.OBSERVACIONINTEGRADOCLAVE.equals(other.getOBSERVACIONINTEGRADOCLAVE())));
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
        if (getPORTAL() != null) {
            _hashCode += getPORTAL().hashCode();
        }
        if (getREQUISITOSINICIACION() != null) {
            _hashCode += getREQUISITOSINICIACION().hashCode();
        }
        if (getPRESENCIALNOADAPTABLE() != null) {
            _hashCode += getPRESENCIALNOADAPTABLE().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ALTATRAMITACIONELECTRONICA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTATRAMITACIONELECTRONICA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SISTEMASIDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "SISTEMASIDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "SISTEMAIDENTIFICACION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANALACCESO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CANALACCESO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMULARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "FORMULARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">FORMULARIOS>FORMULARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "FORMULARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "PORTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUISITOSINICIACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "REQUISITOSINICIACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRESENCIALNOADAPTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "PRESENCIALNOADAPTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">ALTATRAMITACIONELECTRONICA>PRESENCIALNOADAPTABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODREQUISITOSIDENTPJ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CODREQUISITOSIDENTPJ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODREQUISITOSIDENTPF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CODREQUISITOSIDENTPF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDINTEGRADOCLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "IDINTEGRADOCLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBSERVACIONINTEGRADOCLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "OBSERVACIONINTEGRADOCLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
